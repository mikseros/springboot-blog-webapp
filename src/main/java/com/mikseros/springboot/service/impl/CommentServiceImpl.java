package com.mikseros.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.mikseros.springboot.dto.CommentDto;
import com.mikseros.springboot.entity.Comment;
import com.mikseros.springboot.entity.Post;
import com.mikseros.springboot.mapper.CommentMapper;
import com.mikseros.springboot.repository.CommentRepository;
import com.mikseros.springboot.repository.PostRepository;
import com.mikseros.springboot.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		
		Post post = postRepository.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		commentRepository.save(comment);
	}

}
