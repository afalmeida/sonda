package br.com.elo7.sonda.candidato.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Order {

	private String id;
	private List<Action> actions;
	private LocalDateTime dateCreated;

	public Order(String id, List<Action> actions, LocalDateTime dateCreated) {
		this.id = id;
		this.actions = actions;
		this.dateCreated = dateCreated;
	}
	
	public Order(List<Action> actions) {
		this.id = this.generateId();
		this.actions = actions;
		this.dateCreated = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public List<Action> getActions() {
		return actions;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	
	private String generateId() {
		Random random = new Random();
		
		return "ORDER-"+String.valueOf((random.nextInt(1000) + 1) * 3);
	}

}
