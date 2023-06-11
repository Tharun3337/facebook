package com.tim.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	
	

}
