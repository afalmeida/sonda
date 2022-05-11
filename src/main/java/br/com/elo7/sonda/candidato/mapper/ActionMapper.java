package br.com.elo7.sonda.candidato.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.elo7.sonda.candidato.dto.ActionDTO;
import br.com.elo7.sonda.candidato.entity.ActionEntity;
import br.com.elo7.sonda.candidato.entity.PlanetEntity;
import br.com.elo7.sonda.candidato.entity.ProbeEntity;
import br.com.elo7.sonda.candidato.model.Action;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;

@Component
public class ActionMapper {
	
	@Autowired
	private PlanetMapper planetMapper;
	
	@Autowired
	private ProbeMapper probeMapper;
	
	@Autowired
	private CommandMapper commandMapper;
	
	public List<Action> buildActionsByEntity(List<ActionEntity> actionEntities) {
		var actions = new ArrayList<Action>();
		actionEntities.forEach(actionInfo -> {
			actions.add(new Action(
					actionInfo.getId(), 
					planetMapper.buildPlanet(actionInfo.getPlanetEntity()), 
					probeMapper.buildProbe(actionInfo.getProbeEntity()),
					commandMapper.buildCommand(actionInfo.getCommandEntity())));
		});
		
		return actions;
	}
	
	public List<Action> buildActionsByDTO(List<ActionDTO> actionsDTO) {
		var actions = new ArrayList<Action>();
		
		actionsDTO.forEach(actionInfo ->{
			var planet = Planet.builder().id(actionInfo.getPlanetId()).build();
			var probe  = Probe.builder().id(actionInfo.getProbeId()).build();
			var command = commandMapper.buildCommand(actionInfo);
			actions.add(new Action(planet, probe, command));
			
		});
		
		return actions;
	}
	
	public List<ActionEntity> buildActions(List<Action> actions) {
		var actionEntities = new ArrayList<ActionEntity>();
		actions.forEach(actionInfo ->{
			var planetEntity = PlanetEntity.builder().id(actionInfo.getPlanet().id).build();
			var probeEntity = ProbeEntity.builder().id(actionInfo.getProbe().id).build();
			var commandEntity  = commandMapper.buildCommand(actionInfo.getCommand());
			
			actionEntities.add(ActionEntity.builder()
				.id(actionInfo.getId())
				.PlanetEntity(planetEntity)
				.probeEntity(probeEntity)
				.commandEntity(commandEntity)
				.build());
			
		});
		
		return actionEntities;
	}
}
