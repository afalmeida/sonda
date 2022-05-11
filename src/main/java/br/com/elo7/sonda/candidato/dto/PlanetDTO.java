package br.com.elo7.sonda.candidato.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class PlanetDTO {
	
	@NotEmpty
	private String name;
	@NotNull
	private BigDecimal width;
	@NotNull
	private BigDecimal height;

}
