package com.smartjob.eam.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.smartjob.eam.dao.IPhoneDAO;
import com.smartjob.eam.dao.IUserDAO;
import com.smartjob.eam.dto.request.PhoneRequestDTO;
import com.smartjob.eam.dto.request.UserRequestDTO;
import com.smartjob.eam.dto.response.UserResponseDTO;
import com.smartjob.eam.entity.Phone;
import com.smartjob.eam.entity.User;
import com.smartjob.eam.exception.BadRequestException;
import com.smartjob.eam.exception.NotFoundException;
import com.smartjob.eam.jwt.JwtTokenUtil;
import com.smartjob.eam.util.GenericMapperConverterUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	@Value("${manager.api.validation.email.regex}")
	private String regex;
	
	private final IUserDAO userDAO;
	
	private final IPhoneDAO phoneDAO;
	
	private final JwtTokenUtil jwtTokenUtil;
	
	private final GenericMapperConverterUtils genericConverterUtils;
	
	
	@Override
	@Transactional
	public UserResponseDTO save(UserRequestDTO userDTO) throws Exception {

        boolean result = !userDTO.getEmail().matches(regex);
        if(result) {
        	throw new BadRequestException("Email no es valido");
        }
		
		boolean mailFound = this.userDAO.existsByEmail(userDTO.getEmail());
		if(mailFound) {
			throw new BadRequestException("El correo ya esta registrado");
		}
		
		User user = null;
		try {
			
			final String TOKEN =jwtTokenUtil.generateToken(userDTO.getEmail());
			List<PhoneRequestDTO> phonesDTO = new ArrayList<>(userDTO.getPhones());
			userDTO.getPhones().clear();
			
			user = this.genericConverterUtils.convertToEntity(userDTO, User.class);
			user.setCreated(new Date());
			user.setModified(new Date());
			user.setLastLogin(new Date());
			user.setIsactive(Boolean.TRUE);
			user.setToken(TOKEN);
 			user = this.userDAO.save(user);
			
			for (PhoneRequestDTO phoneDTO : phonesDTO) {
				Phone phone = this.genericConverterUtils.convertToEntity(phoneDTO, Phone.class);
				phone.setUser(user);
				
				this.phoneDAO.save(phone);
			}
			
		} catch (DataIntegrityViolationException e) {
			throw new Exception("Error de Integridad: "+e.getMessage());
		} catch (Exception e) {
			throw new Exception("Error "+e.getMessage());
		}
		
		UserResponseDTO response = this.genericConverterUtils.convertToDto(user, UserResponseDTO.class);
		response.setEmail(null);
		response.setPhones(null);
		
		return response;
	}
	
	
	@Override
	public UserResponseDTO findByUUID(UUID id) {
		User user = this.userDAO.findById(id).get();
		user.getPhones().size();
		for (Phone ph : user.getPhones()) {
			System.out.println(ph.getCitycode()); 
		}
		UserResponseDTO response = this.genericConverterUtils.convertToDto(user, UserResponseDTO.class);
		return response;
	}
	
	@Override
	public UserResponseDTO update(UserRequestDTO usDTO) {
		User user = this.userDAO.findById(UUID.fromString(usDTO.getId())).orElseThrow(() -> new NotFoundException("User not found: "+ usDTO.getId().toString()));
		user.setName(usDTO.getName());
		user.setEmail(usDTO.getEmail());
		user.setModified(new Date());
		user.setLastLogin(new Date());
		user = userDAO.saveAndFlush(user);
		
		UserResponseDTO response = this.genericConverterUtils.convertToDto(user, UserResponseDTO.class);
		return response;
	}
	
	@Override
	public void delete(UUID id) {
		User user = this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User not found: "+ id));
		user.setIsactive(false);
		user = userDAO.saveAndFlush(user);
	}	
	
}