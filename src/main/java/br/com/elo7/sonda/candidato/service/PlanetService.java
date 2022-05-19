package br.com.elo7.sonda.candidato.service;

import java.util.List;

import br.com.elo7.sonda.candidato.dto.PlanetDTO;
import br.com.elo7.sonda.candidato.model.Planet;

public interface PlanetService {
	
	public Planet planet(String id);
	public List<Planet> planets(); 
	public Planet savePlanet(PlanetDTO planetDTO);
	public void updatePlanet(String id, PlanetDTO planetDTO);
	public void deletePlanet(String id);
	public boolean existPlanet(String id);
	

}
