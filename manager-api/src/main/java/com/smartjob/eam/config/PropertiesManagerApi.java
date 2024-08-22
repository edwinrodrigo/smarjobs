package com.smartjob.eam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "manager.api.validation.email")
public class PropertiesManagerApi {
	
	private String regex;

}
