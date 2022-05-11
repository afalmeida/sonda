package br.com.elo7.sonda.candidato.model;

import java.util.ArrayList;
import java.util.List;

public enum CommandEnum {
	L, M, R;
	
	
	public static List<CommandEnum> commands(List<String> commands){
		var commandsEnum = new ArrayList<CommandEnum>();
		
		commands.forEach(commandInfo ->{
			commandsEnum.add(CommandEnum.valueOf(commandInfo));
		});
		
		return commandsEnum;
	}
}
