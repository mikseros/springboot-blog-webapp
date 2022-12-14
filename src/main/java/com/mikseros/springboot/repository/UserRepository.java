package com.mikseros.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikseros.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
}
