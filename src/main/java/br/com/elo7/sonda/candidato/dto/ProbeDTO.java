package br.com.elo7.sonda.candidato.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ProbeDTO {
	
	@NotEmpty
	private String name;

}
