package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.dto.OrderDTO;
import br.com.elo7.sonda.candidato.model.Order;

public interface OrderService {
	
	public Order order(String id);
	public Order saveOrder(OrderDTO order);


}
