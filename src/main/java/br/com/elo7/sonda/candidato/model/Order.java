package br.com.elo7.sonda.candidato.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class Order {

	private String id;
	private Planet planet;
	private Probe probe;
	private Command command;
	private LocalDateTime dateCreated;

	public Order(String id, Planet planet, Probe probe, Command command, LocalDateTime dateCreated) {
		this.id = id;
		this.planet = planet;
		this.probe = probe;
		this.command = command;
		this.dateCreated = dateCreated;
	}
	
	
	public Order() {
		this.id = this.generateId();
		this.dateCreated = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}
	
	public Planet getPlanet() {
		return planet;
	}
	
	public Probe getProbe() {
		return probe;
	}
	
	public Command getCommand() {
		return command;
	}


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	private String generateId() {
		Random random = new Random();
	    Timestamp timestamp_object = Timestamp.valueOf(LocalDateTime.now());
	    
		return "ORDER-"+String.valueOf((random.nextInt(1000) + timestamp_object.getTime()));
	}

}
