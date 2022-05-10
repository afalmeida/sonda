package br.com.elo7.sonda.candidato.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Planet {

	public String id;
	private String name;
	private BigDecimal width;
	private BigDecimal height;
	private LocalDateTime dateCreateded;
}
