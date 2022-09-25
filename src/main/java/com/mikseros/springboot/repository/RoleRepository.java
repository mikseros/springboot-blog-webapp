package com.mikseros.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikseros.springboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
