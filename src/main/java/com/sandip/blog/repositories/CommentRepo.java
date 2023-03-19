package com.sandip.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandip.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
