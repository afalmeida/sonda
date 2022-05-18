package br.com.elo7.sonda.candidato.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Document(collection = "probe_position")
public class ProbePositionEntity implements Serializable{
	private static final long serialVersionUID = -2142631781521206020L;
	@Id
	private String probeId;
	private String planetId;
	private Long lastPositionX;
	private Long lastPositionY;

}
