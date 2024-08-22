package com.smartjob.eam.controller.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.smartjob.eam.controller.IUserController;
import com.smartjob.eam.dto.request.UserRequestDTO;
import com.smartjob.eam.dto.response.UserResponseDTO;
import com.smartjob.eam.service.IUserService;
import com.smartjob.eam.util.SwaggerConstants;

@RestController
public class UserController implements IUserController{

	@Autowired
	private IUserService userService;
	
	@Override
	public String greet() {
		return SwaggerConstants.GREET;
	}
	
	@Override
	public ResponseEntity<?> save(UserRequestDTO user) throws Exception {
		UserResponseDTO resp = this.userService.save(user);
		return new ResponseEntity<UserResponseDTO>(resp, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> findByUUID(String uuid) throws Exception {
		UserResponseDTO user = this.userService.findByUUID(UUID.fromString(uuid));
		return new ResponseEntity<UserResponseDTO>(user, HttpStatus.OK); 
	}
	
	@Override
	public ResponseEntity<?> modify(UserRequestDTO userDTO) throws Exception {
		UserResponseDTO user = this.userService.update(userDTO);
		return new ResponseEntity<UserResponseDTO>(user, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> delete(String uuid) throws Exception {
		this.userService.delete(UUID.fromString(uuid));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	public UserController() {
		super();
	}
	
}