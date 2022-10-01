package com.mikseros.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mikseros.springboot.dto.RegistrationDto;
import com.mikseros.springboot.entity.User;
import com.mikseros.springboot.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

	private UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	// handler method to handle login page request
	@GetMapping("/login")
	public String loginPage() {
		return "login";
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
	public String register(@Valid @ModelAttribute("user") RegistrationDto user,
							BindingResult result,
							Model model) {
		User existingUser = userService.findByEmail(user.getEmail());
		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null, "The account for this email already exist.");
		}
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		userService.saveUser(user);
		return "redirect:/register?success";
	}
}
