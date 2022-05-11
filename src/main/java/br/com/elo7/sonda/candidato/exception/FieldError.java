package br.com.elo7.sonda.candidato.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class FieldError {
	
	private String name;
	private String error;

}