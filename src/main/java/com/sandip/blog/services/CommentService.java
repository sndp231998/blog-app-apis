package com.sandip.blog.services;

import com.sandip.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentID);
	
}
