package br.com.elo7.sonda.candidato.service;

import java.util.List;

import br.com.elo7.sonda.candidato.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbePosition;

public interface ProbeService {
		
	public Probe probe(String id);
	public List<Probe> probes(); 
	public Probe saveProbe(ProbeDTO planetDTO);
	public void updateProbe(String id, ProbeDTO planetDTO);
	public void deleteProbe(String id);
	public boolean existProbe(String id);
	public Probe probePosition(String probeId);
	public Probe probePosition(String probeId, String planetId);
	public void saveProbePosition(String id, ProbePosition probePosition);
}
