package com.sandip.blog.services;

import java.util.List;

import com.sandip.blog.entities.Post;
import com.sandip.blog.payloads.PostDto;
import com.sandip.blog.payloads.PostResponse;


public interface PostService {

	//create //postdto bata
	PostDto CreatePost(PostDto postdto,Integer userId,Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postdto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
//------------------get all post-----------------------------
	List<PostDto>getAllPost();
	//PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
//-----------------get single Post-----------------------------
	PostDto getPostById(Integer postId);
	
	
//------------------getAll post by category-------------------
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	
	//getall post By user
	List<PostDto>getPostsByUser(Integer userId);
	
//-------------------search post---------------------------------------------
	List<PostDto>searchPosts(String keyword);

	
}
