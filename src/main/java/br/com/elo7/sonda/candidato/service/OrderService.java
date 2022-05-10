package br.com.elo7.sonda.candidato.service;

import java.util.List;

import br.com.elo7.sonda.candidato.dto.OrderDTO;
import br.com.elo7.sonda.candidato.model.Order;

public interface OrderService {
	
	public List<Order> saveOrders(List<OrderDTO> orders);

}
