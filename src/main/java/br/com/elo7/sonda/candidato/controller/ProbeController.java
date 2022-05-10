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

import br.com.elo7.sonda.candidato.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.exception.BadRequestException;
import br.com.elo7.sonda.candidato.exception.FieldError;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.service.ProbeService;

@RestController
@RequestMapping(value = "/probes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProbeController {
	
	@Autowired
	private ProbeService probeService;
	
	@GetMapping
	public ResponseEntity<List<Probe>> probes(){
		return ResponseEntity.ok(probeService.probes());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Probe> probe(@PathVariable("id") String id){
		return ResponseEntity.ok(probeService.probe(id));
	}
	
	@PostMapping
	public ResponseEntity<Probe> saveProbe(
			@Valid @RequestBody ProbeDTO probeDTO, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			
    		handleErrorBadRequestException(result);
    	}
		
		var probe = probeService.saveProbe(probeDTO);
		
		return new ResponseEntity<Probe>(probe,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProbe(
			@PathVariable("id") String id,
			@Valid @RequestBody ProbeDTO probeDTO, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			
    		handleErrorBadRequestException(result);
    	}
		
		probeService.updateProbe(id,probeDTO);
		
		return ResponseEntity.noContent().build();	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProbe(
			@PathVariable("id") String id) {
		
		probeService.deleteProbe(id);
		
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
