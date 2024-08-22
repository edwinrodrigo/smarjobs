package com.smartjob.eam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartjob.eam.entity.Phone;

@Repository
public interface IPhoneDAO extends  JpaRepository<Phone, Long>{

}
