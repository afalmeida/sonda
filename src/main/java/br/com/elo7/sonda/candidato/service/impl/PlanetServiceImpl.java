package br.com.elo7.sonda.candidato.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.sonda.candidato.dto.PlanetDTO;
import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.exception.InternalServerException;
import br.com.elo7.sonda.candidato.exception.NotFoundException;
import br.com.elo7.sonda.candidato.mapper.PlanetMapper;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.repository.PlanetRepository;
import br.com.elo7.sonda.candidato.service.PlanetService;


@Service
public class PlanetServiceImpl implements PlanetService {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetMapper planetMapper;

	@Override
	public Planet planet(String id) {
		PlanetEntity planetEntity = null;
		try {
			planetEntity = planetRepository.findById(id).get();
			
			if (planetEntity == null) {
				throw new NotFoundException();
			}
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		

		return planetMapper.buildPlanet(planetEntity);
	}
	

	@Override
	public List<Planet> planets() {
		List<PlanetEntity> planetEntities = null;
		try {
			planetEntities = planetRepository.findAll();
			
			if (planetEntities.isEmpty()) {
				throw new NotFoundException();
			}
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		

		return planetMapper.buildPlanets(planetEntities);
	}

	@Override
	public Planet savePlanet(PlanetDTO planetDTO) {
		var planetEntity = planetMapper.buildPlanetEntity(planetDTO);
		var planetEntitySaveded = planetRepository.save(planetEntity);
		
		try {
			return planetMapper.buildPlanet(planetEntitySaveded);
		}catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}

	@Override
	public void updatePlanet(String id, PlanetDTO planetDTO) {
		try {
			if (existPlanet(id)) {
				PlanetEntity planetEntity = planetMapper.buildPlanetEntity(id,planetDTO);
				planetRepository.save(planetEntity);
			
			} else {
				throw new NotFoundException();
			}
			
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}

	@Override
	public void deletePlanet(String id) {
		try {
			if (existPlanet(id)) {
				planetRepository.deleteById(id);
			
			} else {
				throw new InternalServerException();
			}
			
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}
	
	private boolean existPlanet(String id) {
		var planetEntity = planetRepository.findById(id).get();
		
		if (planetEntity != null) {
			return true;
		}
		
		return false;
	}

}
