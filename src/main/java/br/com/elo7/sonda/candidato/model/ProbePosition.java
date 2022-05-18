package br.com.elo7.sonda.candidato.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.Link;

import br.com.elo7.sonda.candidato.controller.PlanetController;
import br.com.elo7.sonda.candidato.entity.ProbePositionEntity;

public class ProbePosition {

	private Planet planet;
	private Long lastPositionX;
	private Long lastPositionY;
	
	public ProbePosition () {}
	
	public ProbePosition(String planetId, Long lastPositionX, Long lastPositionY) {
		this.planet = Planet.builder().id(planetId).build();
		this.lastPositionX = lastPositionX;
		this.lastPositionY = lastPositionY;
	}

	public ProbePosition probePositionEntityTOProbePosition(ProbePositionEntity probePositionEntity) {
		Link linkSelf = linkTo(methodOn(PlanetController.class).planet(probePositionEntity.getPlanetId())).withSelfRel();
		Planet planet = Planet.builder().id(probePositionEntity.getPlanetId()).build().add(linkSelf);
		
		this.planet = planet;
		this.lastPositionX = probePositionEntity.getLastPositionX();
		this.lastPositionY = probePositionEntity.getLastPositionY();
		
		return this;
	}

	public Planet getPlanet() {
		return planet;
	}

	public Long getLastPositionX() {
		return lastPositionX;
	}


	public Long getLastPositionY() {
		return lastPositionY;
	}

}
