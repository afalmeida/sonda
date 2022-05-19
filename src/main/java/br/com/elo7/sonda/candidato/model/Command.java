package br.com.elo7.sonda.candidato.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Command {
	
	private Long x;
	private Long y;
	private DirectionEnum direction;
	private List<CommandEnum> commands;
		
	public Command(Long x, Long y, DirectionEnum direction, List<CommandEnum> commands) {
		this.x = x;
		this.y = y;
		this.moveProbeWithAllCommands(direction, commands);
	}
	
	public Command(Long x, Long y, String direction) {
		this.x = x;
		this.y = y;
		this.direction = DirectionEnum.valueOf(direction);
	}

	
	public Long getX() {
		return x;
	}

	public Long getY() {
		return y;
	}


	public DirectionEnum getDirection() {
		return direction;
	}
	
	public List<CommandEnum> getCommands() {
		return commands;
	}
	
	private void moveProbeWithAllCommands(DirectionEnum direction, List<CommandEnum> commands) {
		for (CommandEnum command : commands) {
			applyCommandToProbe(direction, command);
		}
	}
	
	private void applyCommandToProbe(DirectionEnum direction, CommandEnum command ) {
		switch (command) {
			case R:
				turnProbeRight(direction);
				break;
			case L:
				turnProbeLeft(direction);
				break;
			case M:
				moveProbeForward(direction);
				break;
		}
	}

	private void moveProbeForward(DirectionEnum direction) {		
		Long newX = this.x;
		Long newY = this.y;
		switch (direction) {
			case N:
				newY++;
				break;
			case W:
				newX--;
				break;
			case S:
				newY--;
				break;
			case E:
				newX++;
				break;
		}
		this.direction = direction;
		this.x = newX;
		this.y = newY;
	}

	private void turnProbeLeft(DirectionEnum direction) {
		DirectionEnum newDirection = DirectionEnum.N;
		switch (direction) {
			case N:
				newDirection = DirectionEnum.W;
				break;
			case W:
				newDirection = DirectionEnum.S;
				break;
			case S:
				newDirection = DirectionEnum.E;
				break;
			case E:
				newDirection = DirectionEnum.N;
				break;
		}
		this.direction = newDirection;
	}
	
	private void turnProbeRight(DirectionEnum direction) {
		DirectionEnum newDirection = DirectionEnum.N;
		switch (direction) {
			case N:
				newDirection = DirectionEnum.E;
				break;
			case E:
				newDirection = DirectionEnum.S;
				break;
			case S:
				newDirection = DirectionEnum.W;
				break;
			case W:
				newDirection = DirectionEnum.N;
				break;
		}

		this.direction = newDirection;
		
	}
}