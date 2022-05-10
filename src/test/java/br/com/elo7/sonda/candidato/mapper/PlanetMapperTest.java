package br.com.elo7.sonda.candidato.mapper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.elo7.sonda.candidato.dto.PlanetDTO;
import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.service.impl.PlanetServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PlanetMapperTest {
	
    @InjectMocks
    private PlanetMapper planetMapper;
    
    @Test
    public void buildPlanets() {
    	
    	var planets = planetMapper.buildPlanets(planetEntitiesMock());
    	
    	assertFalse(planets.isEmpty());
    }
    
    @Test
    public void builPlanet() {
    	var planet = planetMapper.buildPlanet(planetEntityMock().get());
    	assertNotNull(planet);
    }
    
    public void buildPlanetEntity() {
		var planetEntity = planetMapper.buildPlanetEntity(planetDTOMock());
		
		assertNotNull(planetEntity);
		assertNotNull(planetEntity.getDateCreateded());
    }

    
   @Test
   public void buildPlanetEntityWithId() {
	   final var ID = "ID123";
	   var planetEntity = planetMapper.buildPlanetEntity(ID, planetDTOMock());
	   
		assertNotNull(planetEntity);
		assertNotNull(planetEntity.getDateCreateded());
		assertNotNull(planetEntity.getId());
		assertEquals(planetEntity.getId(), ID);
   }
    
	private List<PlanetEntity>  planetEntitiesMock() {
		List<PlanetEntity> laboratories = new ArrayList<PlanetEntity>();

		laboratories.add(PlanetEntity.builder().id("123").name("PLANETNAME1").width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build());
		laboratories.add(PlanetEntity.builder().id("1234").name("PLANETNAME2").width(new BigDecimal(1.8).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.1).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build());

		return laboratories;
	}
	
	private Optional<PlanetEntity> planetEntityMock() {
		return Optional.of(PlanetEntity.builder().id("123").name("PLANETNAME1").width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build());

	}
	
	private PlanetDTO planetDTOMock() {
		return  PlanetDTO.builder().name("PLANETNAME1").height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).build();

	}
}
