package br.com.elo7.sonda.candidato.dto;

import java.util.List;

import br.com.elo7.sonda.candidato.model.CommandEnum;
import br.com.elo7.sonda.candidato.model.DirectionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ActionDTO {
	
	private Long x;
	private Long y;
	private DirectionEnum direction;
	private List<CommandEnum> commands;
	private String probeId;

}
