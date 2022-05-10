package br.com.elo7.sonda.candidato.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Document(collection = "probe")
public class ProbeEntity {
	
	private String id;
	private String name;
	private LocalDateTime dateCreateded;

}
