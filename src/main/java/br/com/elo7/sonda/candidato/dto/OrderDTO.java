package br.com.elo7.sonda.candidato.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDTO {
	
	@NotNull @Size(min = 1, max= 1000)
	private List<ActionDTO> actions;
	

}
