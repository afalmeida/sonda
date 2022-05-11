package br.com.elo7.sonda.candidato.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ActionEntity implements Serializable{
	private static final long serialVersionUID = -2370365590287202314L;
	private String id;
	private PlanetEntity PlanetEntity;
	private ProbeEntity probeEntity;
	private CommandEntity commandEntity;
}
