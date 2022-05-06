package br.com.elo7.sonda.candidato.service;

import java.util.List;

import br.com.elo7.sonda.candidato.dto.InputDTO;
import br.com.elo7.sonda.candidato.model.Probe;

public interface ProbeService {
	
	public List<Probe> probes(InputDTO input);

}
