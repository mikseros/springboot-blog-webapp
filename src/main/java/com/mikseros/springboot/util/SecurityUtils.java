package com.mikseros.springboot.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

	public static User getCurrentUser() {
		Object principle =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principle instanceof User) {
			return (User) principle;
		}
		return null;
	}
}
