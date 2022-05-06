package br.com.elo7.sonda.candidato.repository;

import br.com.elo7.sonda.candidato.entity.ProbeEntity;

public interface ProbeRepository {

	void save(ProbeEntity probe);

	ProbeEntity findById(Long id);

}
