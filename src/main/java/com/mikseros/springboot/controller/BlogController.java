package com.mikseros.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	// handler method to handle view post request
	@GetMapping("/post/{postUrl}")
	private String showPost(@PathVariable("postUrl") String postUrl,
							Model model) {
		PostDto post = postService.findPostByUrl(postUrl);
		model.addAttribute("post", post);
		return "blog/blog_post";
	}
	
}
