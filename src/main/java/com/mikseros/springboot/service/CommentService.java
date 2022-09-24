package com.mikseros.springboot.service;

import com.mikseros.springboot.dto.CommentDto;

public interface CommentService {
	void createComment(String postUrl, CommentDto commentDto);
}
