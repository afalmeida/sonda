package br.com.elo7.sonda.candidato.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ActionEntity {
	
	private String id;
	private PlanetEntity PlanetEntity;
	private ProbeEntity probeEntity;
	private CommandEntity commandEntity;
}
