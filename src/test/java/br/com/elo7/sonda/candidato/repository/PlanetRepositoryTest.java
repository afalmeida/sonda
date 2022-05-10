package br.com.elo7.sonda.candidato.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.elo7.sonda.candidato.entity.PlanetEntity;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class PlanetRepositoryTest {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private final String ID ="PLANET1";
	
    @BeforeEach 
    public void setUp() throws Exception {
    	
    	PlanetEntity planetEntity1 = PlanetEntity.builder().id(ID).name("PLANETNAME1").width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build();
    	PlanetEntity planetEntity2 = PlanetEntity.builder().name("PLANETNAME2").width(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build();

    	
    	mongoTemplate.save(planetEntity1);
    	mongoTemplate.save(planetEntity2);
    	
    }
    
    @AfterEach
    void clean() {
    	mongoTemplate.dropCollection("planet");
    }
	
    
    @Test
    public void findAll() {
    	var planetEntities = planetRepository.findAll();
    	
    	assertTrue(!planetEntities.isEmpty());
    }
    
    @Test
    public void findById() {
    	var planetEntity = planetRepository.findById(ID).get();
    	assertNotNull(planetEntity);
    }
    
    @Test
    public void save() {
    	var planetEntity = PlanetEntity.builder().id(ID).name("PLANETNAME1").width(new BigDecimal(5.0).setScale(2,RoundingMode.HALF_UP)).height(new BigDecimal(1.0).setScale(2,RoundingMode.HALF_UP)).dateCreateded(LocalDateTime.now()).build();

    	 var planetEntitySaveded = planetRepository.save(planetEntity);
    	 
    	 assertNotNull(planetEntitySaveded);
    	 assertEquals(planetEntitySaveded.getName(), "PLANETNAME1");
    }
    
    @Test
    public void update () {
     	var planetEntity = planetRepository.findById(ID).get();
    	
     	assertEquals(planetEntity.getName(), "PLANETNAME1");
     	assertEquals(planetEntity.getHeight(),new BigDecimal(1.332).setScale(2,RoundingMode.HALF_UP));
   	 
     	planetEntity.setName("PLANETNAME1234");
     	planetEntity.setHeight(new BigDecimal(6.0).setScale(2,RoundingMode.HALF_UP));
   	 
     	var planetEntityUpdated = planetRepository.save(planetEntity);
   	 
     	assertEquals(planetEntityUpdated.getName(), "PLANETNAME1234");
     	assertEquals(planetEntityUpdated.getHeight(), new BigDecimal(6.0).setScale(2,RoundingMode.HALF_UP));
    }

}
