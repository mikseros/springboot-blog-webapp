package com.mikseros.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikseros.springboot.dto.CommentDto;
import com.mikseros.springboot.dto.PostDto;
import com.mikseros.springboot.service.CommentService;
import com.mikseros.springboot.service.PostService;

import jakarta.validation.Valid;

@Controller
public class PostController {
	
	private PostService postService;
	private CommentService commentService;

	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}
	
	// Handler method, GET request & return Model & View
	@GetMapping("/admin/posts")
	public String posts(Model model) {
		List<PostDto> posts = postService.findPostByUser();
		model.addAttribute("posts", posts);
		return "/admin/posts";
	}
	
	// handler method to handle list comments request
	@GetMapping("/admin/posts/comments")
	public String postComments(Model model) {
		List<CommentDto> comments = commentService.findAllComments();
		model.addAttribute("comments", comments);
		return "admin/comments";
	}
	
	// handler method to handle delete comment request
	@GetMapping("/admin/posts/comments/{commentId}")
	public String deleteComment(@PathVariable("commentId") Long commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/admin/posts/comments";
	}
	
	// Handler method to handle new post request
	@GetMapping("admin/posts/newpost")
	public String newPostForm(Model model) {
		PostDto postDto = new PostDto();
		model.addAttribute("post", postDto);
		return "admin/create_post";
	}
	
	// Handler method to handle form submit request
	@PostMapping("/admin/posts")
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
							 BindingResult result, 
							 Model model) {
		if(result.hasErrors()) {
			model.addAttribute("post", postDto);
			return "admin/create_post";
		}
		postDto.setUrl(getUrl(postDto.getTitle()));
		postService.createPost(postDto);
		return "redirect:/admin/posts";
	}
	
	// handler method to handle edit post request
	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostForm(@PathVariable("postId") Long postId,
							   Model model) {
		PostDto postDto = postService.findPostById(postId);
		model.addAttribute("post", postDto);
		return "admin/edit_post";
	}
	
	// handler method to handle edit post form submit request
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
							 @Valid @ModelAttribute("post") PostDto post,
							 BindingResult result,
							 Model model) {
		if(result.hasErrors()) {
			model.addAttribute("post", post);
			return "admin/edit_post";
		}
		
		post.setId(postId);
		postService.updatePost(post);
		return "redirect:/admin/posts";
	}
	
	// handler method to handle delete post request
	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable("postId") Long postId) {
		postService.deletePost(postId);
		return "redirect:/admin/posts";
	}
	
	// handler method to handle view post request
	@GetMapping("admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable("postUrl") String postUrl,
						   Model model) {
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("post", postDto);
		return "admin/view_post";
	}
	
	// handler method to handle search blog post request
	// localhost:8080/admin/posts/search?query=java
	@GetMapping("/admin/posts/search")
	public String searchPosts(@RequestParam(value = "query") String query,
							  Model model) {
		List<PostDto> posts = postService.searchPosts(query);
		model.addAttribute("posts", posts);
		return "admin/posts";
	}
	
	private static String getUrl(String postTitle) {
		// OOPS Concepts Explained in Java
		// oops-concepts-explained-in-java
		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		url = url.replaceAll("[^A-Za-z0-9]", "-");
		return url;
	}

}
