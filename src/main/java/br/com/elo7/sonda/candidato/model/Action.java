package br.com.elo7.sonda.candidato.model;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Action {

	private String id;
	private Planet planet;
	private Probe probe;
	private Command command;

	public Action(String id, Planet planet, Probe probe, Command command) {
		this.id = id;
		this.planet = planet;
		this.probe = probe;
		this.command = command;
	}
	
	public Action(Planet planet, Probe probe, Command command) {
		this.id = this.generateId();
		this.planet = planet;
		this.probe = probe;
		this.command = command;
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

	private String generateId() {
		Random random = new Random();
		
		return "ACTION-"+String.valueOf((random.nextInt(1000) + 1) * 3);
	}

}
