package com.mikseros.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mikseros.springboot.dto.CommentDto;
import com.mikseros.springboot.entity.Comment;
import com.mikseros.springboot.entity.Post;
import com.mikseros.springboot.entity.User;
import com.mikseros.springboot.mapper.CommentMapper;
import com.mikseros.springboot.repository.CommentRepository;
import com.mikseros.springboot.repository.PostRepository;
import com.mikseros.springboot.repository.UserRepository;
import com.mikseros.springboot.service.CommentService;
import com.mikseros.springboot.util.SecurityUtils;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository,
			PostRepository postRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		
		Post post = postRepository.findByUrl(postUrl).get();
		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		commentRepository.save(comment);
	}

	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream()
					.map(CommentMapper::mapToCommentDto)
					.collect(Collectors.toList());
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}

	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByEmail(email);
		Long userId = createdBy.getId();
		List<Comment> comments = commentRepository.findCommentsByPost(userId);
		return comments.stream()
				.map((comment) -> CommentMapper.mapToCommentDto(comment))
				.collect(Collectors.toList());
	}

}
