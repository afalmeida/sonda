package br.com.elo7.sonda.candidato.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.sonda.candidato.dto.ProbeDTO;
import br.com.elo7.sonda.candidato.entity.ProbeEntity;
import br.com.elo7.sonda.candidato.exception.InternalServerException;
import br.com.elo7.sonda.candidato.exception.NotFoundException;
import br.com.elo7.sonda.candidato.mapper.ProbeMapper;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.repository.ProbeRepository;
import br.com.elo7.sonda.candidato.service.ProbeService;

@Service
public class ProbeServiceImpl implements ProbeService {

	@Autowired
	private ProbeRepository probeRepository;
	
	@Autowired
	private ProbeMapper probeMapper;

	@Override
	public Probe probe(String id) {
		ProbeEntity probeEntity = null;
		try {
			probeEntity = probeRepository.findById(id).get();
			
			if (probeEntity == null) {
				throw new NotFoundException();
			}
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		

		return probeMapper.buildProbe(probeEntity);
	}
	

	@Override
	public List<Probe> probes() {
		List<ProbeEntity> probeEntities = null;
		try {
			probeEntities = probeRepository.findAll();
			
			if (probeEntities.isEmpty()) {
				throw new NotFoundException();
			}
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		

		return probeMapper.buildProbes(probeEntities);
	}

	@Override
	public Probe saveProbe(ProbeDTO probeDTO) {
		var probeEntity = probeMapper.buildProbeEntity(probeDTO);
		var probeEntitySaveded = probeRepository.save(probeEntity);
		
		try {
			return probeMapper.buildProbe(probeEntitySaveded);
		}catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}

	@Override
	public void updateProbe(String id, ProbeDTO probeDTO) {
		try {
			if (existProbe(id)) {
				ProbeEntity probeEntity = probeMapper.buildProbeEntity(id,probeDTO);
				probeRepository.save(probeEntity);
			
			} else {
				throw new NotFoundException();
			}
			
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}

	@Override
	public void deleteProbe(String id) {
		try {
			if (existProbe(id)) {
				probeRepository.deleteById(id);
			
			} else {
				throw new InternalServerException();
			}
			
		} catch (NotFoundException e) {
			throw e;
		
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
		
	}
	
	private boolean existProbe(String id) {
		var probeEntity = probeRepository.findById(id).get();
		
		if (probeEntity != null) {
			return true;
		}
		
		return false;
	}
	
//	public List<Probe> probes(InputDTO input) {
//		Probe probe = concertProbe(input);
//		//probes.save(probe);
//		
//		List<Probe> convertedProbes = convertAndMoveProbes(input, probe);
//		//convertedProbes.forEach(probe -> probes.save(probe));
//		
//		return convertedProbes;
//	}
	
//	@VisibleForTesting
//	public void applyCommandToProbe(Probe probe, char command) {
//		switch (command) {
//			case Command.R:
//				turnProbeRight(probe);
//				break;
//			case Command.L:
//				turnProbeLeft(probe);
//				break;
//			case Command.M:
//				moveProbeForward(probe);
//				break;
//		}
//	}

//	private void moveProbeForward(Probe probe) {
//		int newX = probe.getX();
//		int newY = probe.getY();
//		switch (probe.getDirection()) {
//			case Direction.N:
//				newY++;
//				break;
//			case Direction.W:
//				newX--;
//				break;
//			case Direction.S:
//				newY--;
//				break;
//			case Direction.E:
//				newX++;
//				break;
//		}
//		probe.setX(newX);
//		probe.setY(newY);
//	}

//	private void turnProbeLeft(Probe probe) {
//		char newDirection = Direction.N;
//		switch (probe.getDirection()) {
//			case Direction.N:
//				newDirection = Direction.W;
//				break;
//			case Direction.W:
//				newDirection = Direction.S;
//				break;
//			case Direction.S:
//				newDirection = Direction.E;
//				break;
//			case Direction.E:
//				newDirection = Direction.N;
//				break;
//		}
//		probe.setDirection(newDirection);
//	}
	
//	private void turnProbeRight(Probe probe) {
//		char newDirection = Direction.N;
//		switch (probe.getDirection()) {
//			case Direction.N:
//				newDirection = Direction.E;
//				break;
//			case Direction.E:
//				newDirection = Direction.S;
//				break;
//			case Direction.S:
//				newDirection = Direction.W;
//				break;
//			case Direction.W:
//				newDirection = Direction.N;
//				break;
//		}
//		System.out.println(newDirection);
//		probe.setDirection(newDirection);
//		
//	}
	
//	private List<Probe> convertAndMoveProbes(InputDTO input, Probe probe) {
//		return input.getProbes()
//						.stream().map(probeDto -> {
//							Probe probe = convertProbe(probeDto, probe);
//							moveProbeWithAllCommands(probe, probeDto);
//							return probe;
//						}).collect(Collectors.toList());
//	}
//
//	private void moveProbeWithAllCommands(Probe probe, ProbeDTO2 probeDTO) {
//		for (char command : probeDTO.getCommands().toCharArray()) {
//			applyCommandToProbe(probe, command);
//		}
//	}
	
//	private Probe convertProbe(ProbeDTO2 probeDto, Probe probe) {
//		Probe probe = new Probe();
//		probe.setProbe(probe);
//		probe.setX(probeDto.getX());
//		probe.setY(probeDto.getY());
//		probe.setDirection(probeDto.getDirection());
//		return probe;
//	}
//	
//	private Probe concertProbe(InputDTO input) {
//		Probe probe = new Probe();
//		probe.setHeight(input.getHeight());
//		probe.setWidth(input.getWidth());
//		return probe;
//	}



}
