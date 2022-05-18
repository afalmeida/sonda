package br.com.elo7.sonda.candidato.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.elo7.sonda.candidato.entity.ProbePositionEntity;

public interface ProbePositionRepository  extends MongoRepository<ProbePositionEntity, String> {

	public ProbePositionEntity findByProbeId(String probeId);
}
