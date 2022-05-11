package br.com.elo7.sonda.candidato.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class CommandTest {
	
	@Test
	public void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.L);
		Command command = new Command(null, null, DirectionEnum.N, commands);

		assertEquals(DirectionEnum.W, command.getDirection());		
	}
	
	@Test
	public void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.L);
		Command command = new Command(null, null, DirectionEnum.W, commands);

		assertEquals(DirectionEnum.S, command.getDirection());		
	}
	
	
	@Test
	public void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {		
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.L);
		Command command = new Command(null, null, DirectionEnum.S, commands);

		assertEquals(DirectionEnum.E, command.getDirection());
	}
	
	
	@Test
	public void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {		
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.L);
		Command command = new Command(null, null, DirectionEnum.E, commands);

		assertEquals(DirectionEnum.N, command.getDirection());
	}
	
	
	@Test
	public void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.R);
		Command command = new Command(null, null, DirectionEnum.N, commands);

		assertEquals(DirectionEnum.E, command.getDirection());
	}
	
	
	@Test
	public void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.R);
		Command command = new Command(null, null, DirectionEnum.E, commands);

		assertEquals(DirectionEnum.S, command.getDirection());
	}
	
	@Test
	public void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.R);
		Command command = new Command(null, null, DirectionEnum.S, commands);

		assertEquals(DirectionEnum.W, command.getDirection());
	}
	
	
	@Test
	public void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.R);
		Command command = new Command(null, null, DirectionEnum.W, commands);

		assertEquals(DirectionEnum.N, command.getDirection());
	}

	@Test
	public void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.M);
		Command command = new Command(1l, 1l, DirectionEnum.N , commands);

		assertEquals(1, command.getX());
		assertEquals(2, command.getY());
		assertEquals(DirectionEnum.N, command.getDirection());
	}
	
	@Test
	public void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.M);
		Command command = new Command(1l, 1l, DirectionEnum.S , commands);

		assertEquals(1, command.getX());
		assertEquals(0, command.getY());
		assertEquals(DirectionEnum.S, command.getDirection());
	}
	
	@Test
	public void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.M);
		Command command = new Command(1l, 1l, DirectionEnum.W , commands);

		assertEquals(0, command.getX());
		assertEquals(1, command.getY());
		assertEquals(DirectionEnum.W, command.getDirection());
	}
	
	@Test
	public void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {		
		var commands = new ArrayList<CommandEnum>();
		commands.add(CommandEnum.M);
		Command command = new Command(1l, 1l, DirectionEnum.E , commands);

		assertEquals(2, command.getX());
		assertEquals(1, command.getY());
		assertEquals(DirectionEnum.E, command.getDirection());
	}

}
