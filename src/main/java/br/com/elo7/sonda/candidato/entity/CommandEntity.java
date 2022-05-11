package br.com.elo7.sonda.candidato.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CommandEntity {
	
	private Long x;
	private Long y;
	private String direction;

}
