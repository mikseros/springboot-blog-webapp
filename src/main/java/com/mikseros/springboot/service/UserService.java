package com.mikseros.springboot.service;

import com.mikseros.springboot.dto.RegistrationDto;

public interface UserService {

	void saveUser(RegistrationDto registrationDto);
}
