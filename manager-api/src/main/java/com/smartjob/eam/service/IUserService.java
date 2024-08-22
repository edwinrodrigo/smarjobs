package com.smartjob.eam.service;

import java.util.UUID;

import com.smartjob.eam.dto.request.UserRequestDTO;
import com.smartjob.eam.dto.response.UserResponseDTO;

public interface IUserService {

	public UserResponseDTO save(UserRequestDTO userDTO) throws Exception;
	
	public UserResponseDTO findByUUID(UUID id);
	
	public UserResponseDTO update(UserRequestDTO usDTO);
	
	public void delete(UUID id);
	
}