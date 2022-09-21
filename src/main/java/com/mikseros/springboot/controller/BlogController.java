package com.mikseros.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mikseros.springboot.dto.PostDto;
import com.mikseros.springboot.service.PostService;

@Controller
public class BlogController {

	private PostService postService;

	public BlogController(PostService postService) {
		this.postService = postService;
	}
	
	// handler method to handle http://localhost:8080/
	@GetMapping("/")
	public String viewBlogPosts(Model model) {
		List<PostDto> postsResponse = postService.findAllPosts();
		model.addAttribute("postsResponse", postsResponse);
		return "blog/view_posts";
	}
	
}
