package br.com.elo7.sonda.candidato.mapper;

import org.springframework.stereotype.Component;

import br.com.elo7.sonda.candidato.dto.ActionDTO;
import br.com.elo7.sonda.candidato.entity.CommandEntity;
import br.com.elo7.sonda.candidato.model.Command;

@Component
public class CommandMapper {
	
	public Command buildCommand(CommandEntity commandEntity) {
		return new Command(commandEntity.getX(), commandEntity.getY(), commandEntity.getDirection());
	}
	
	public Command buildCommand(ActionDTO actionDTO) {
		return new Command(actionDTO.getX(), actionDTO.getY(), actionDTO.getDirection(), actionDTO.getCommands());

	}
	
	public CommandEntity buildCommand(Command command) {		
		return CommandEntity.builder()
				.x(command.getX())
				.y(command.getY())
				.direction(command.getDirection().name())
				.build();
	}

}
