package br.com.elo7.sonda.candidato.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.elo7.sonda.candidato.entity.ProbeEntity;

public interface ProbeRepository extends MongoRepository<ProbeEntity, String> {

}
