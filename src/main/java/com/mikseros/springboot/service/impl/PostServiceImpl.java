// This class is a part of service layer for retrieving the list of posts from the DB table
// This class is the implementation of the interface
package com.mikseros.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mikseros.springboot.dto.PostDto;
import com.mikseros.springboot.entity.Post;
import com.mikseros.springboot.mapper.PostMapper;
import com.mikseros.springboot.repository.PostRepository;
import com.mikseros.springboot.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
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
		Post post = PostMapper.mapToPost(postDto);
		postRepository.save(post);
	}

	@Override
	public PostDto findPostById(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}

}
