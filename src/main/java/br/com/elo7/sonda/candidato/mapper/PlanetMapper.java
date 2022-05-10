package br.com.elo7.sonda.candidato.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.elo7.sonda.candidato.dto.PlanetDTO;
import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.model.Planet;

@Component
public class PlanetMapper {
	
	public List<Planet> buildPlanets(List<PlanetEntity> planetEntities){
		var planets = new ArrayList<Planet>();
		planetEntities.forEach(planetEntityInfo ->{
			planets.add(this.buildPlanet(planetEntityInfo));
		});
		
		return planets;
	}
	
	public Planet buildPlanet(PlanetEntity planetEntity) {
		return Planet.builder()
				.id(planetEntity.getId())
				.name(planetEntity.getName())
				.height(planetEntity.getHeight())
				.width(planetEntity.getWidth())
				.build();
	}
	
	public PlanetEntity buildPlanetEntity(PlanetDTO planetDTO) {
		return PlanetEntity.builder()
				.name(planetDTO.getName())
				.height(planetDTO.getHeight())
				.width(planetDTO.getWidth())
				.dateCreateded(LocalDateTime.now())
				.build();
		
	}
	
	public PlanetEntity buildPlanetEntity(String id,PlanetDTO planetDTO) {
		return PlanetEntity.builder()
				.id(id)
				.name(planetDTO.getName())
				.height(planetDTO.getHeight())
				.width(planetDTO.getWidth())
				.dateCreateded(LocalDateTime.now())
				.build();
		
	}
}