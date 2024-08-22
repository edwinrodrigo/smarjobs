package com.smartjob.eam.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartjob.eam.util.GenericMapperConverterUtils;

@Configuration
public class ServiceConfiguration {

	 @Bean
	 ModelMapper modelMapper() {
	        return new ModelMapper();
	 }

	 @Bean
	 GenericMapperConverterUtils genericConverterUtils(ModelMapper modelMapper) {
	        return new GenericMapperConverterUtils(modelMapper);
	 }

}