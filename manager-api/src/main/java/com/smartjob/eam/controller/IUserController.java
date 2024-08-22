package com.smartjob.eam.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartjob.eam.dto.request.UserRequestDTO;
import com.smartjob.eam.util.SwaggerConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = SwaggerConstants.USER_TAG, description = SwaggerConstants.USER_DESCRIPTION)
@RequestMapping("/user")
public interface IUserController {

	@Operation(summary = "Saludar desde la api")
	@ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Saludo")
	})
	@GetMapping("/greet")
	public String greet();
	
	@Operation(summary = "Guardar un usuario con sus telefonos")
	@ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Usuario creado"),
	     @ApiResponse(responseCode = "400", description = "Validacion Usuario"),
	     @ApiResponse(responseCode = "404", description = "Request Malo")
	})
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@Valid @RequestBody UserRequestDTO user) throws Exception;
	
	
	@Operation(summary = "Buscar por UUID")
	@ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Usuario Encontrado"),
	     @ApiResponse(responseCode = "400", description = "Validacion Usuario"),
	     @ApiResponse(responseCode = "404", description = "Request Malo")
	})
	@GetMapping(value = "/find/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByUUID(@PathVariable String uuid) throws Exception; 
	
	
	@Operation(summary = "Modificar un Usuario")
	@ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Usuario Modificado"),
	     @ApiResponse(responseCode = "400", description = "Validacion Usuario"),
	     @ApiResponse(responseCode = "404", description = "Request Malo")
	})
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> modify(@RequestBody UserRequestDTO user) throws Exception;
	
	
	@Operation(summary = "Elimina un Usuario")
	@ApiResponses({
	     @ApiResponse(responseCode = "200", description = "Usuario Eliminado"),
	     @ApiResponse(responseCode = "400", description = "Validacion Usuario"),
	     @ApiResponse(responseCode = "404", description = "Request Malo")
	})
	@DeleteMapping(value = "/delete/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable String uuid) throws Exception;
	
	
}
