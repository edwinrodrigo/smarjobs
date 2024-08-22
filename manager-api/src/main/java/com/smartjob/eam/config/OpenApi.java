package com.smartjob.eam.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info = 	@Info(
				title = "Prueba Tecnica - Smart Jobs",
				version = "1.0.0", contact = @Contact(
						name = "Edwin Amaguaya",
						email = "eamaguaya89@gmail.com",
						url = "https://www.linkedin.com/in/edwinamaguayadev/"
						),
				description = "En este proyecto se desarrolla la prueba solicitada por el departamento de talento humano de Smart Jobs"
		)
)
public class OpenApi {

}
