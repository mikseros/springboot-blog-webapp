// This class is a part of service layer for retrieving the list of posts from the DB table
// This is an interface, and it must be implemented in the another class (impl)
package com.mikseros.springboot.service;

import java.util.List;

import com.mikseros.springboot.dto.PostDto;

public interface PostService {

	List<PostDto> findAllPosts();
}
