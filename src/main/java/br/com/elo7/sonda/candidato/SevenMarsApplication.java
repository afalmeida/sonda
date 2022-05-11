package br.com.elo7.sonda.candidato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Probe API", version = "2.0", description = "Order Probe"))
public class SevenMarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SevenMarsApplication.class, args);
		
	}

}
