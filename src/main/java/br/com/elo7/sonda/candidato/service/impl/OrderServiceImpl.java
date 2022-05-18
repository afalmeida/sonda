package br.com.elo7.sonda.candidato.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.elo7.sonda.candidato.dto.OrderDTO;
import br.com.elo7.sonda.candidato.entity.OrderEntity;
import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.entity.ProbeEntity;
import br.com.elo7.sonda.candidato.exception.FieldError;
import br.com.elo7.sonda.candidato.exception.InternalServerException;
import br.com.elo7.sonda.candidato.exception.NotFoundException;
import br.com.elo7.sonda.candidato.exception.UnprocessableEntityException;
import br.com.elo7.sonda.candidato.mapper.CommandMapper;
import br.com.elo7.sonda.candidato.mapper.PlanetMapper;
import br.com.elo7.sonda.candidato.mapper.ProbeMapper;
import br.com.elo7.sonda.candidato.model.Command;
import br.com.elo7.sonda.candidato.model.Order;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.ProbePosition;
import br.com.elo7.sonda.candidato.repository.OrderRepository;
import br.com.elo7.sonda.candidato.service.OrderService;
import br.com.elo7.sonda.candidato.service.PlanetService;
import br.com.elo7.sonda.candidato.service.ProbeService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RedisTemplate<String, OrderDTO> redis;
	
	@Autowired
	private CommandMapper commandMapper;
	
	@Autowired
	private ProbeService probeService;
	
	@Autowired
	private PlanetService planetService;
	
	@Autowired
	private PlanetMapper planetMapper;
	
	@Autowired
	private ProbeMapper probeMapper;

	@Override
	public Order order(String id) {
		OrderEntity orderEntity = null;
		try {
			orderEntity = orderRepository.findById(id).get();
			
			var planet = planetMapper.buildPlanet(orderEntity.getPlanetEntity().getId());
			var probe = probeMapper.buildProbe(orderEntity.getProbeEntity().getId());
			var command = commandMapper.buildCommand(orderEntity.getCommandEntity());
			
			return new Order(
					orderEntity.getId(), 
					planet,
					probe,
					command,
					orderEntity.getDateCreated());
			
		} catch (NoSuchElementException e) {
			throw new NotFoundException();
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		

	}

	@Override
    @Retryable(value = InternalServerException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
	public Order saveOrder(OrderDTO orderDTO) throws InternalServerException {
		OrderEntity orderEntity = null;
		OrderEntity orderEntitySaved = null;
		
		if (existPlanetAndProbe(orderDTO)) {
			orderEntity = this.buildOrderEntity(orderDTO);
		
		} else {
			throw new NotFoundException("Planet OR Probe notfound");
		}
		
		try {
			var probePosition = new ProbePosition(
					orderEntity.getPlanetEntity().getId(),
					orderEntity.getCommandEntity().getX(),
					orderEntity.getCommandEntity().getY());
			probeService.saveProbePosition(orderEntity.getProbeEntity().getId(),probePosition);
			orderEntitySaved = orderRepository.save(orderEntity);

		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
		
		
		return this.orderSaved(orderEntitySaved);

	}

	private OrderEntity buildOrderEntity(OrderDTO orderDTO) {
		Order order  = new Order();
		
		var probePosition = probeService.probePosition(orderDTO.getProbeId(),orderDTO.getPlanetId());
		Long lastPositionX = probePosition.getPosition()!= null && probePosition.getPosition().getLastPositionX() != null ? probePosition.getPosition().getLastPositionX(): 0l;
		Long lastPositionY = probePosition.getPosition()!= null && probePosition.getPosition().getLastPositionY() != null ? probePosition.getPosition().getLastPositionY(): 0l;
		
		var command = new Command(lastPositionX, lastPositionY, orderDTO.getDirection(), orderDTO.getCommands());
		var newCommand = commandMapper.buildCommand(command);
		this.validateCartesianPlane(command, orderDTO.getPlanetId());
		
		return OrderEntity.builder()
				.planetEntity(PlanetEntity.builder().id(orderDTO.getPlanetId()).build())
				.probeEntity(ProbeEntity.builder().id(orderDTO.getProbeId()).build())
				.commandEntity(newCommand)
				.id(order.getId())
				.dateCreated(order.getDateCreated())
				.build();
	}

	private void validateCartesianPlane(Command command, String planetId) {
		var isNotValid =false;
		
		Planet planet = planetService.planet(planetId);
		
		if (
			(command.getY().intValue()<0 && new BigDecimal(command.getY()).compareTo(planet.getHeight().negate()) <0) || (new BigDecimal(command.getY()).compareTo(planet.getHeight()) >0) ||  
			(command.getX().intValue()<0 && new BigDecimal(command.getX()).compareTo(planet.getWidth().negate())  <0) || (new BigDecimal(command.getX()).compareTo(planet.getWidth())  >0)) {
			
				isNotValid = true;
		}
		

		
		if(isNotValid) {
			List<FieldError> validationErrors = new ArrayList<FieldError>();
			validationErrors.add(FieldError.builder()
					.name("Command")
					.error("Movimento cancelado, limite do plano ultrapassado para o Planeta["+planet+"], ")
					.build());
			throw new UnprocessableEntityException(validationErrors);
		}
		
	}
	

	@Recover
	private Order saveOrderRetry(InternalServerException cause, OrderDTO orderDTO) {
		LOG.info("#####RETRY INITIALIZED TO SAVEORDER####");
		var orderEntity = this.buildOrderEntity(orderDTO);
		
		redis.opsForValue().set(orderEntity.getId(), orderDTO);
		
		return this.orderSaved(orderEntity);
//		
	} 
	
	@Scheduled(fixedRate = 50000)
	public void saveOrder() {
		LOG.info("##### SCHEDULE INITIALIZED TO SAVEORDER#####");
		
		String key = redis.randomKey();
		
		if (StringUtils.isNotBlank(key)) {
			//redis.delete(key);
			var orderDTO = redis.opsForValue().get(key);
			var orderEntity = this.buildOrderEntity(orderDTO);
			
			LOG.info("##### SCHEDULE INITIALIZED TO SAVEORDER##### OrderEntity="+orderEntity);
			var orderEntitySaved = orderRepository.save(orderEntity);
			
			if(orderEntitySaved != null) {
				redis.delete(key);
			}
		}
	}
	
	private Order orderSaved (OrderEntity orderEntity) {
		var planet = planetMapper.buildPlanet(orderEntity.getPlanetEntity().getId());
		var probe = probeMapper.buildProbe(orderEntity.getProbeEntity().getId());
		var command = commandMapper.buildCommand(orderEntity.getCommandEntity());
		
		return new Order(
				orderEntity.getId(), 
				planet,
				probe,
				command,
				orderEntity.getDateCreated());
	}
	
	private boolean existPlanetAndProbe(OrderDTO orderDTO) {
		boolean existPlanet = planetService.existPlanet(orderDTO.getPlanetId());
		 
		if (existPlanet) {
			return probeService.existProbe(orderDTO.getProbeId());
		}
		
		return false;
	}
	
}
