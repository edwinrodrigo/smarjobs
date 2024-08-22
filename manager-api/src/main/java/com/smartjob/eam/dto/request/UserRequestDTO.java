package com.smartjob.eam.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDTO {
	
	private String id;
	
	@NotNull(message = "no pueded ser nulo")
	private String name;
	
	@NotNull(message = "no pueded ser nulo")
	private String email;
	
	@NotNull(message = "no pueded ser nulo")
	private String password;
	
	private List<PhoneRequestDTO> phones;	
	
}