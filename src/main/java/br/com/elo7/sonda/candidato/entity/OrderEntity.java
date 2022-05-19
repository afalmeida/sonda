package br.com.elo7.sonda.candidato.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Document(collection = "order")
public class OrderEntity  implements Serializable{
	private static final long serialVersionUID = 5477745164386611009L;
	private String id;
	private PlanetEntity planetEntity;
	private ProbeEntity probeEntity;
	private CommandEntity commandEntity;
	private LocalDateTime dateCreated;

}
