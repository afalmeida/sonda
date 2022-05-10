package br.com.elo7.sonda.candidato.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Probe {

	public String id;
	private String name;
	private LocalDateTime dateCreateded;
}
