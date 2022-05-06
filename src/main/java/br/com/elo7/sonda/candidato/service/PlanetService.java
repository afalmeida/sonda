package br.com.elo7.sonda.candidato.service;

import java.util.List;

import br.com.elo7.sonda.candidato.model.Planet;

public interface PlanetService {
	
	public Planet planet(Long id);
	public List<Planet> planets(); 
	
	

}
