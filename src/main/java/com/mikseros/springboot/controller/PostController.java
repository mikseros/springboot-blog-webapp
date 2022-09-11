package com.mikseros.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mikseros.springboot.dto.PostDto;
import com.mikseros.springboot.service.PostService;

@Controller
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	// Handler method, GET request & return Model & View
	@GetMapping("/admin/posts")
	public String posts(Model model) {
		List<PostDto> posts = postService.findAllPosts();
		model.addAttribute("posts", posts);
		return "/admin/posts";
	}

}
