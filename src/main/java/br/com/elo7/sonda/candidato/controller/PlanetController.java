package br.com.elo7.sonda.candidato.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.sonda.candidato.dto.PlanetDTO;
import br.com.elo7.sonda.candidato.exception.BadRequestException;
import br.com.elo7.sonda.candidato.exception.FieldError;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.service.PlanetService;

@RestController
@RequestMapping(value = "/planets", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlanetController {
	
	@Autowired
	private PlanetService planetService;
	
	@GetMapping
	public ResponseEntity<List<Planet>> planets(){
		return ResponseEntity.ok(planetService.planets());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Planet> planet(@PathVariable("id") String id){
		return ResponseEntity.ok(planetService.planet(id));
	}
	
	@PostMapping
	public ResponseEntity<Planet> savePlanet(
			@Valid @RequestBody PlanetDTO planetDTO, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			
    		handleErrorBadRequestException(result);
    	}
		
		var planet = planetService.savePlanet(planetDTO);
		
		return new ResponseEntity<Planet>(planet,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updatePlanet(
			@PathVariable("id") String id,
			@Valid @RequestBody PlanetDTO planetDTO, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			
    		handleErrorBadRequestException(result);
    	}
		
		planetService.updatePlanet(id,planetDTO);
		
		return ResponseEntity.noContent().build();	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePlanet(
			@PathVariable("id") String id) {
		
		planetService.deletePlanet(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	public void handleErrorBadRequestException(BindingResult bindingResult) throws BadRequestException {
		List<FieldError> validationErrors = new ArrayList<FieldError>();
		if (bindingResult.hasErrors()){

	        for (org.springframework.validation.FieldError error :bindingResult.getFieldErrors()){
	        	FieldError build = FieldError.builder().error(error.getDefaultMessage()).name(error.getField()).build();
	        	validationErrors.add(build);
	        }

            throw new BadRequestException(validationErrors);
        }
	}

}
