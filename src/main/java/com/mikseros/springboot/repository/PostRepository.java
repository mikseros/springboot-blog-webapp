package com.mikseros.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikseros.springboot.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByUrl(String url);

}
