package br.com.elo7.sonda.candidato.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class Planet extends RepresentationModel<Planet>{

	public String id;
	private String name;
	private BigDecimal width;
	private BigDecimal height;
	private LocalDateTime dateCreateded;
}
