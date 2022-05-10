package br.com.elo7.sonda.candidato.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class OrderDTO {
	
	private String planetId;
	private List<ActionDTO> actions;
	

}
