package br.com.elo7.sonda.candidato.service;

import java.util.List;

import br.com.elo7.sonda.candidato.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.model.Probe;

public interface ProbeService {
	
	//public List<Probe> probes(InputDTO input);
	
	public Probe probe(String id);
	public List<Probe> probes(); 
	public Probe saveProbe(ProbeDTO planetDTO);
	public void updateProbe(String id, ProbeDTO planetDTO);
	public void deleteProbe(String id);

}
