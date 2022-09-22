package com.mikseros.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mikseros.springboot.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
