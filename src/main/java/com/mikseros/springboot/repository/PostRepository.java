package com.mikseros.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mikseros.springboot.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	Optional<Post> findByUrl(String url);
	
	@Query("SELECT p FROM Post p WHERE " +
			" p.title LIKE CONCAT('%', :query, '%') OR " +
			" p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Post> searchPosts(String query);
}
