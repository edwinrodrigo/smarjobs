package com.smartjob.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;

import com.smartjob.eam.dao.IPhoneDAO;
import com.smartjob.eam.dao.IUserDAO;
import com.smartjob.eam.dto.request.UserRequestDTO;
import com.smartjob.eam.dto.response.UserResponseDTO;
import com.smartjob.eam.entity.User;
import com.smartjob.eam.exception.BadRequestException;
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

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDAO, phoneDAO, jwtTokenUtil, genericConverterUtils);
    }

    @Test
    void testFindByUUID_ValidUUID() {
        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setPhones(new ArrayList<>());

        when(userDAO.findById(uuid)).thenReturn(Optional.of(user));
        when(genericConverterUtils.convertToDto(any(User.class), any(Class.class))).thenReturn(new UserResponseDTO());

        UserResponseDTO response = userService.findByUUID(uuid);

        assertNotNull(response);
        verify(userDAO, times(1)).findById(uuid);
    }

    @Test
    void testUpdate_ValidUser() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(UUID.randomUUID().toString());
        User user = new User();
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        when(userDAO.findById(UUID.fromString(userRequestDTO.getId()))).thenReturn(Optional.of(user));
        when(userDAO.saveAndFlush(any(User.class))).thenReturn(user);
        when(genericConverterUtils.convertToDto(any(User.class), any(Class.class))).thenReturn(userResponseDTO);

        UserResponseDTO response = userService.update(userRequestDTO);

        assertNotNull(response);
        verify(userDAO, times(1)).saveAndFlush(any(User.class));
    }

    @Test
    void testUpdate_UserNotFound() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(UUID.randomUUID().toString());

        when(userDAO.findById(UUID.fromString(userRequestDTO.getId()))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userService.update(userRequestDTO);
        });
    }

    @Test
    void testDelete_ValidUUID() {
        UUID uuid = UUID.randomUUID();
        User user = new User();

        when(userDAO.findById(uuid)).thenReturn(Optional.of(user));
        when(userDAO.saveAndFlush(any(User.class))).thenReturn(user);

        userService.delete(uuid);

        verify(userDAO, times(1)).saveAndFlush(any(User.class));
        assertFalse(user.getIsactive());
    }

    @Test
    void testDelete_UserNotFound() {
        UUID uuid = UUID.randomUUID();

        when(userDAO.findById(uuid)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            userService.delete(uuid);
        });
    }
	
}