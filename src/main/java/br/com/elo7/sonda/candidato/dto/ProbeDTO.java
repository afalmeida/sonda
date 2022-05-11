package br.com.elo7.sonda.candidato.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProbeDTO {
	
	@NotEmpty
	private String name;

}
