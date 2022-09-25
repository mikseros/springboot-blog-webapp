package com.mikseros.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mikseros.springboot.dto.RegistrationDto;

@Controller
public class AuthController {

	// handler method to handle user registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		
		// this object will hold form data
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user" ,user);
		return "register";
	}
}
