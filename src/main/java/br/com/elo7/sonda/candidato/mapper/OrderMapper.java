package br.com.elo7.sonda.candidato.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.elo7.sonda.candidato.dto.OrderDTO;
import br.com.elo7.sonda.candidato.entity.ActionEntity;
import br.com.elo7.sonda.candidato.entity.OrderEntity;
import br.com.elo7.sonda.candidato.model.Order;

@Component
public class OrderMapper {
	
	@Autowired
	private ActionMapper actionMapper;
	
	public Order buildOrder(OrderEntity orderEntity) {
		return new Order(
				orderEntity.getId(), 
				actionMapper.buildActionsByEntity(orderEntity.getActions()), 
				orderEntity.getDateCreated());
	}

	public OrderEntity buildOderEntity(OrderDTO orderDTO) {
		var order = new Order(actionMapper.buildActionsByDTO(orderDTO.getActions()));
		List<ActionEntity> actionEntities = actionMapper.buildActions(order.getActions());
		return OrderEntity.builder()
				.id(order.getId())
				.actions(actionEntities)
				.dateCreated(order.getDateCreated())
				.build();
	}

}
