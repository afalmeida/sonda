package br.com.elo7.sonda.candidato.entity;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CommandEntity implements Serializable{
	private static final long serialVersionUID = 4977680932547634976L;
	private Long x;
	private Long y;
	private String direction;

}
