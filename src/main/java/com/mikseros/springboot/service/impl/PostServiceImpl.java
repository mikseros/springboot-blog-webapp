// This class is a part of service layer for retrieving the list of posts from the DB table
// This class is the implementation of the interface
package com.mikseros.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mikseros.springboot.dto.PostDto;
import com.mikseros.springboot.entity.Post;
import com.mikseros.springboot.entity.User;
import com.mikseros.springboot.mapper.PostMapper;
import com.mikseros.springboot.repository.PostRepository;
import com.mikseros.springboot.repository.UserRepository;
import com.mikseros.springboot.service.PostService;
import com.mikseros.springboot.util.SecurityUtils;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private UserRepository userRepository;
	
	public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<PostDto> findAllPosts() {
		List<Post> posts = postRepository.findAll();
//		return posts.stream().map((post) -> PostMapper.mapToPostDto(post))
//				.collect(Collectors.toList());
		return posts.stream().map(PostMapper::mapToPostDto)
				.collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepository.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(user);
		postRepository.save(post);
	}

	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public void updatePost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(createdBy);
		postRepository.save(post);
	}

	@Override
	public void deletePost(Long postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepository.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> searchPosts(String query) {
		List<Post> posts = postRepository.searchPosts(query);
		return posts.stream()
				.map(PostMapper::mapToPostDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostByUser() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Post> posts = postRepository.findPostByUser(userId);
		return posts.stream()
					.map((post) -> PostMapper.mapToPostDto(post))
					.collect(Collectors.toList());
				
	}

}
