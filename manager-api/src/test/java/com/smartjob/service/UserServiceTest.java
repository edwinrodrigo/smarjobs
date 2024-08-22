package com.smartjob.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smartjob.eam.dao.IPhoneDAO;
import com.smartjob.eam.dao.IUserDAO;
import com.smartjob.eam.dto.request.UserRequestDTO;
import com.smartjob.eam.dto.response.UserResponseDTO;
import com.smartjob.eam.entity.User;
import com.smartjob.eam.exception.NotFoundException;
import com.smartjob.eam.jwt.JwtTokenUtil;
import com.smartjob.eam.service.UserServiceImpl;
import com.smartjob.eam.util.GenericMapperConverterUtils;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private IUserDAO userDAO;

	@Mock
	private IPhoneDAO phoneDAO;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	private GenericMapperConverterUtils genericConverterUtils;

	@InjectMocks
	private UserServiceImpl userService;
//
//	@Test
//	void testFindByUUID_Found() {
//		UUID id = UUID.randomUUID();
//		User user = new User();
//		user.setId(id);
//
//		when(userDAO.findById(id)).thenReturn(Optional.of(user));
//
//		User result = userService.findByUUID(id);
//
//		assertNotNull(result);
//		assertEquals(id, result.getId());
//	}

	@Test
	void testFindByUUID_NotFound() {
		UUID id = UUID.randomUUID();

		when(userDAO.findById(id)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> userService.findByUUID(id));
	}

	@Test
	void testUpdate_ValidUser() {
		UserRequestDTO userDTO = new UserRequestDTO();
		userDTO.setId(UUID.randomUUID().toString());
		userDTO.setName("John Doe");

		User user = new User();
		user.setId(UUID.fromString(userDTO.getId()));

		when(userDAO.findById(UUID.fromString(userDTO.getId()))).thenReturn(Optional.of(user));
		when(userDAO.saveAndFlush(any(User.class))).thenReturn(user);
		when(genericConverterUtils.convertToDto(user, UserResponseDTO.class)).thenReturn(new UserResponseDTO());

		UserResponseDTO result = userService.update(userDTO);

		assertNotNull(result);
		verify(userDAO, times(1)).saveAndFlush(user);
	}

	@Test
	void testUpdate_UserNotFound() {
		UserRequestDTO userDTO = new UserRequestDTO();
		userDTO.setId(UUID.randomUUID().toString());

		when(userDAO.findById(UUID.fromString(userDTO.getId()))).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> userService.update(userDTO));
	}

	@Test
	void testDelete_ExistingUser() {
		UUID id = UUID.randomUUID();

		doNothing().when(userDAO).deleteById(id);

		userService.delete(id);

		verify(userDAO, times(1)).deleteById(id);
	}
}