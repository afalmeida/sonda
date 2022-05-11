package br.com.elo7.sonda.candidato.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.elo7.sonda.candidato.dto.OrderDTO;
import br.com.elo7.sonda.candidato.exception.BadRequestException;
import br.com.elo7.sonda.candidato.exception.FieldError;
import br.com.elo7.sonda.candidato.model.Order;
import br.com.elo7.sonda.candidato.service.OrderService;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> planet(@PathVariable("id") String id){
		return ResponseEntity.ok(orderService.order(id));
	}
	
	@PostMapping
	public ResponseEntity<Order> saveOrder(
			@Valid @RequestBody OrderDTO orderDTO, 
			BindingResult result) {
		
		if (result.hasErrors()) {
			
    		handleErrorBadRequestException(result);
    	}
		
		var order = orderService.saveOrder(orderDTO);
		
		return new ResponseEntity<Order>(order,HttpStatus.CREATED);
		
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
