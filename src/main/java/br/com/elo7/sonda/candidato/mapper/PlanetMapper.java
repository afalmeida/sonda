package br.com.elo7.sonda.candidato.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.model.Planet;

@Component
//TODO Implemetar builds
public class PlanetMapper {
	
	public List<Planet> buildPlanets(List<PlanetEntity> planetEntities){
	
		return new ArrayList<>();
	}
	public Planet buildPlanet(PlanetEntity planetEntity) {
		
		return new Planet();
	}

}
