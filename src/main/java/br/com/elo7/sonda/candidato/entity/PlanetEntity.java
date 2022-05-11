package br.com.elo7.sonda.candidato.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Document(collection = "planet")
public class PlanetEntity {
	
	private String id;
	private String name;
	private BigDecimal width;
	private BigDecimal height;
	private LocalDateTime dateCreated;

}
