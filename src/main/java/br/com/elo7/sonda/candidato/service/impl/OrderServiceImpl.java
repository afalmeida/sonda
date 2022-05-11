package br.com.elo7.sonda.candidato.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper orderMapper;

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
	public Order saveOrder(OrderDTO orderDTO) {
		var orderEntity = orderMapper.buildOderEntity(orderDTO);
		var orderEntitySaved = orderRepository.save(orderEntity);
		
		try {
			return orderMapper.buildOrder(orderEntitySaved);
		}catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}
}
