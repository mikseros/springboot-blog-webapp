package com.mikseros.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mikseros.springboot.dto.RegistrationDto;
import com.mikseros.springboot.service.UserService;

@Controller
public class AuthController {

	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	// handler method to handle user registration request
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		
		// this object will hold form data
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user" ,user);
		return "register";
	}
	
	// handler method to handle user registration form submission request
	@PostMapping("/register/save")
	public String register(@ModelAttribute("user") RegistrationDto user ) {
		userService.saveUser(user);
		return "redirect:/register?success";
	}
}
