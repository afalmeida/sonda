package br.com.elo7.sonda.candidato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.elo7.sonda.candidato.dto.InputDTO;
import br.com.elo7.sonda.candidato.model.Probe2;
import br.com.elo7.sonda.candidato.service.impl.ProbeServiceImpl;

@Controller
@RequestMapping("/planet-with-probes")
public class PlanetAndProbeController {
	
	@Autowired
	private ProbeServiceImpl probeService;

//	@PostMapping
//    public ResponseEntity<List<Probe2>> register(@RequestBody InputDTO inputDto) {
//		return ResponseEntity.ok(probeService.probes(inputDto));        
//    }
}
