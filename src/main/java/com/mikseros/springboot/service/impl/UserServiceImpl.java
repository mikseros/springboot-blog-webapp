package com.mikseros.springboot.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.mikseros.springboot.dto.RegistrationDto;
import com.mikseros.springboot.entity.Role;
import com.mikseros.springboot.entity.User;
import com.mikseros.springboot.repository.RoleRepository;
import com.mikseros.springboot.repository.UserRepository;
import com.mikseros.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setName(registrationDto.getFirstName() + " " + registrationDto.getLastName());
		user.setEmail(registrationDto.getEmail());
		// use spring security to encrypt the password
		user.setPassword(registrationDto.getPassword());
		Role role = roleRepository.findByName("ROLE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}

}
