package com.smartjob.eam.dto.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * @author eamaguaya
 * @version <1.0>
 * 
 * Modela la respuesta de el usuario 
 * 
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
	
	private String id;
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date created;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modified;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastLogin;
	private String token;
	private Boolean isactive;
	
	private List<PhoneResponseDTO> phones;
	
	
	
}