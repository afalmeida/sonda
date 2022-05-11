package br.com.elo7.sonda.candidato.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.elo7.sonda.candidato.model.CommandEnum;
import br.com.elo7.sonda.candidato.model.DirectionEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Valid
public class ActionDTO {
	
	@NotNull
	private Long x;
	@NotNull
	private Long y;
	@NotNull
	private DirectionEnum direction;
	@NotNull @Size(min = 1)
	private List<CommandEnum> commands;
	@NotEmpty
	private String probeId;
	@NotEmpty
	private String planetId;

}
