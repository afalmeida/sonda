package br.com.elo7.sonda.candidato.service.impl;

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
import br.com.elo7.sonda.candidato.exception.InternalServerException;
import br.com.elo7.sonda.candidato.exception.NotFoundException;
import br.com.elo7.sonda.candidato.mapper.OrderMapper;
import br.com.elo7.sonda.candidato.model.Order;
import br.com.elo7.sonda.candidato.repository.OrderRepository;
import br.com.elo7.sonda.candidato.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Log LOG = LogFactory.getLog(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private RedisTemplate<String, OrderEntity> redis;

	@Override
	public Order order(String id) {
		OrderEntity orderEntity = null;
		try {
			orderEntity = orderRepository.findById(id).get();
			
		} catch (NoSuchElementException e) {
			throw new NotFoundException();
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		

		return orderMapper.buildOrder(orderEntity);
	}

	@Override
    @Retryable(value = InternalServerException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
	public Order saveOrder(OrderDTO orderDTO) throws InternalServerException{
		var orderEntity = orderMapper.buildOderEntity(orderDTO);
		
		try {
			var orderEntitySaved = orderRepository.save(orderEntity);
			return orderMapper.buildOrder(orderEntitySaved);
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}
	

	@Recover
	private Order saveOrderRetry(InternalServerException cause, OrderDTO orderDTO) {
		LOG.info("#####RETRY INITIALIZED TO SAVEORDER####");
		var orderEntity = orderMapper.buildOderEntity(orderDTO);
		
		redis.opsForValue().set(orderEntity.getId(), orderEntity);

		
		return orderMapper.buildOrder(orderEntity);
//		
	} 
	
	@Scheduled(fixedRate = 50000)
	public void saveOrder() {
		LOG.info("##### SCHEDULE INITIALIZED TO SAVEORDER#####");
		
		String key = redis.randomKey();
		
		if (StringUtils.isNotBlank(key)) {
			//redis.delete(key);
			var orderEntity = redis.opsForValue().get(key);
			LOG.info("##### SCHEDULE INITIALIZED TO SAVEORDER##### OrderEntity="+orderEntity);
			var orderEntitySaved = orderRepository.save(orderEntity);
			
			if(orderEntitySaved != null) {
				redis.delete(key);
			}
		}
	}
	
}
