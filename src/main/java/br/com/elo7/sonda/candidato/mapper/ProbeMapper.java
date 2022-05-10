package br.com.elo7.sonda.candidato.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.elo7.sonda.candidato.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.entity.ProbeEntity;
import br.com.elo7.sonda.candidato.model.Probe;

@Component
public class ProbeMapper {
	
	public List<Probe> buildProbes(List<ProbeEntity> probeEntities){
		var probes = new ArrayList<Probe>();
		probeEntities.forEach(probeEntityInfo ->{
			probes.add(this.buildProbe(probeEntityInfo));
		});
		
		return probes;
	}
	
	public Probe buildProbe(ProbeEntity probeEntity) {
		return Probe.builder()
				.id(probeEntity.getId())
				.name(probeEntity.getName())
				.dateCreateded(probeEntity.getDateCreateded())
				.build();
	}
	
	public ProbeEntity buildProbeEntity(ProbeDTO probeDTO) {
		return ProbeEntity.builder()
				.name(probeDTO.getName())
				.dateCreateded(LocalDateTime.now())
				.build();
		
	}
	
	public ProbeEntity buildProbeEntity(String id,ProbeDTO probeDTO) {
		return ProbeEntity.builder()
				.id(id)
				.name(probeDTO.getName())
				.dateCreateded(LocalDateTime.now())
				.build();
		
	}
}