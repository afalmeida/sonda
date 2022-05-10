package br.com.elo7.sonda.candidato.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.elo7.sonda.candidato.dto.PlanetDTO;
import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.exception.NotFoundException;
import br.com.elo7.sonda.candidato.mapper.PlanetMapper;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.repository.PlanetRepository;
import br.com.elo7.sonda.candidato.service.impl.PlanetServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {
	    
    @InjectMocks
    private PlanetServiceImpl planetService;
    
	@Mock
	private PlanetRepository planetRepository;
	
	@Spy
	private PlanetMapper planetMapper;
	
	private final String ID ="PLANET1";
	
	@BeforeEach
	public void setUp() {
        ReflectionTestUtils.setField(planetService, "planetMapper", planetMapper);

	}
	
	@Test
	public void planet() {
		when(planetRepository.findById(ID)).thenReturn(planetEntityMock());
		Planet planet = planetService.planet(ID);
		
		assertNotNull(planet);
		assertEquals(planet.getName(), "PLANETNAME1");
	}
	
	@Test
	public void planetNotFound() {
		when(planetRepository.findById("NAOENCONTRADO")).thenThrow(NotFoundException.class);
		assertThrows(NotFoundException.class, () -> {
			planetService.planet("NAOENCONTRADO");
		});
	}
	
	@Test
	public void planets() {
		when(planetRepository.findAll()).thenReturn(planetEntitiesMock());
		var planets = planetService.planets();
		assertNotNull(planets);
		assertTrue(planets.size()==2);
	}
	
	@Test
	public void update() {
		var  planetDTO = PlanetDTO.builder().name("PLANETNAME1").height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).build();
		when(planetRepository.findById(ID)).thenReturn(planetEntityMock());
		
		planetService.updatePlanet(ID, planetDTO);
	}
	
	@Test
	public void delete() {
		when(planetRepository.findById(ID)).thenReturn(planetEntityMock());
		
		planetService.deletePlanet(ID);
	}
	
	
	private Optional<PlanetEntity> planetEntityMock() {
		return Optional.of(PlanetEntity.builder().id(ID).name("PLANETNAME1").width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build());

	}
	
	private List<PlanetEntity>  planetEntitiesMock() {
		List<PlanetEntity> laboratories = new ArrayList<PlanetEntity>();

		laboratories.add(PlanetEntity.builder().id(ID).name("PLANETNAME1").width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build());
		laboratories.add(PlanetEntity.builder().id(ID).name("PLANETNAME2").width(new BigDecimal(1.8).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.1).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build());

		return laboratories;
	}

}
