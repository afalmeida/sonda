package br.com.elo7.sonda.candidato.entity;

import java.io.Serializable;
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
public class PlanetEntity implements Serializable{
	private static final long serialVersionUID = 2387672037258117397L;
	private String id;
	private String name;
	private BigDecimal width;
	private BigDecimal height;
	private LocalDateTime dateCreated;

}
