package br.com.elo7.sonda.candidato.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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

		} catch (NoSuchElementException e) {
			throw new NotFoundException();
		
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
}
