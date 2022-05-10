package br.com.elo7.sonda.candidato.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	
	private String id;
	private Planet planet;
	private Probe probe;
	private List<Action> actions;
	private LocalDateTime dateCreated;

}
