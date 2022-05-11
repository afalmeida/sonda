package br.com.elo7.sonda.candidato.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.elo7.sonda.candidato.entity.OrderEntity;

public interface OrderRepository  extends MongoRepository<OrderEntity, String> {

}
