package com.smartjob.eam.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author eamaguaya
 * @version <1.0>
 * 
 * Entidad representa los datos del telefono de un usuario
 * 
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class Phone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number", nullable = false, length = 50)
	private String number;
	
	@Column(name = "citycode", nullable = false, length = 10)
	private String citycode;
	
	@Column(name = "contrycode", nullable = false, length = 10)
	private String contrycode;
	

	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name= "user_id", nullable = false)
	private User user;
	
		
}