package com.mikseros.springboot.service;

import com.mikseros.springboot.dto.RegistrationDto;
import com.mikseros.springboot.entity.User;

public interface UserService {

	void saveUser(RegistrationDto registrationDto);

	User findByEmail(String email);
}
