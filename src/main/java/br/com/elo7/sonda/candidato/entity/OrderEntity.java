package br.com.elo7.sonda.candidato.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Document(collection = "order")
public class OrderEntity {
	
	private String id;
	private List<ActionEntity> actions;
	private LocalDateTime dateCreated;

}
