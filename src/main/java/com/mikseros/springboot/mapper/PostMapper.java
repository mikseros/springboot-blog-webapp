// This class is for mapping PostDto to Post & vice-versa.
package com.mikseros.springboot.mapper;

import java.util.stream.Collectors;

import com.mikseros.springboot.dto.PostDto;
import com.mikseros.springboot.entity.Post;

public class PostMapper {
	
	// Map Post Entity to PostDto
	public static PostDto mapToPostDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.url(post.getUrl())
				.content(post.getContent())
				.shortDescription(post.getShortDescription())
				.createdOn(post.getCreatedOn())
				.updatedOn(post.getUpdatedOn())
				.comments(post.getComments().stream()
						.map((comment) -> CommentMapper.mapToCommentDto(comment))
						.collect(Collectors.toSet()))
				.build();
	}
	
	// Map PostDto to Post Entity
	public static Post mapToPost(PostDto postDto) {
		return Post.builder()
				.id(postDto.getId())
				.title(postDto.getTitle())
				.url(postDto.getUrl())
				.content(postDto.getContent())
				.shortDescription(postDto.getShortDescription())
				.createdOn(postDto.getCreatedOn())
				.updatedOn(postDto.getUpdatedOn())
				.build();
	}
}
