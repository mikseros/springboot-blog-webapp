package com.mikseros.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mikseros.springboot.dto.CommentDto;
import com.mikseros.springboot.service.CommentService;

@Controller
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	// handler method to create form submit request
	@PostMapping("/{postUrl}/comments")
	public String createComment(@PathVariable("postUrl") String postUrl,
								@ModelAttribute("comment") CommentDto commentDto,
								Model model) {
		commentService.createComment(postUrl, commentDto);
		return "redirect:/post/" + postUrl;
	}
}
