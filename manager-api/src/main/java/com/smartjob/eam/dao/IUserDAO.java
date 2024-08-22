package com.smartjob.eam.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartjob.eam.entity.User;

@Repository
public interface IUserDAO extends JpaRepository<User, UUID>{

	User findByEmail(String email);
	
	boolean existsByEmail(String email);
}