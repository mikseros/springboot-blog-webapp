package com.mikseros.springboot.service;

import java.util.List;

import com.mikseros.springboot.dto.CommentDto;

public interface CommentService {
	void createComment(String postUrl, CommentDto commentDto);
	
	List<CommentDto> findAllComments();

	void deleteComment(Long commentId);
}
