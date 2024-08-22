package com.smartjob.eam.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author eamaguaya
 * @version <1.0>
 * 
 * Entidad representa los los datos de un usuario
 * 
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "name", nullable = false, length = 200)
	private String name;
	
	@Column(name = "email", unique = false, nullable = false, length = 100)
	private String email;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;
	
	@Column(name = "created", nullable = false)
	private Date created;
	
	@Column(name = "modified", nullable = false)
	private Date modified;
	
	@Column(name = "last_login")
	private Date lastLogin;
	
	@Column(name = "isactive", nullable = false)
	private Boolean isactive;
	
	@Column(name = "token")
	private String token;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Phone> phones;

}