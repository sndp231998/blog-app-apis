package com.sandip.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sandip.blog.Exceptions.ResourceNotFoundException;
import com.sandip.blog.entities.*;
import com.sandip.blog.entities.Post;
import com.sandip.blog.payloads.PostDto;
import com.sandip.blog.payloads.PostResponse;
import com.sandip.blog.repositories.CategoryRepo;
import com.sandip.blog.repositories.PostRepo;
import com.sandip.blog.repositories.UserRepo;
import com.sandip.blog.services.PostService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	//----------------------------create the Post--------------------------------------------
	@Override
	public PostDto CreatePost(PostDto postdto,Integer userId, Integer categoryId) {
		
		//userfeatching
		//User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));
		
		//category featching
		 Category category = this.categoryRepo.findById(categoryId)
	                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id ", categoryId));
		
		
		Post post=this.modelMapper.map(postdto, Post.class);
		
		post.setImageName("defult.png");
		 post.setAddedDate( new Date());
		 post.setUser(user);
		 post.setCategory(category);
		 
		 Post newPost = this.postRepo.save(post);

	        return this.modelMapper.map(newPost, PostDto.class);
	}
//-----------------------Update the Post--------------------------------------------
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		 Post post=this.postRepo.findById(postId)
				 .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));	
	     post.setTitle(postDto.getTitle());
		 post.setContent(postDto.getContent());
		 post.setImageName(postDto.getImageName());
		Post updatePost=this.postRepo.save(post);
		 return this.modelMapper.map(updatePost,PostDto.class);
	}

	//-------------Delete Post Implementation--------------------
	@Override
	public void deletePost(Integer postId) {
	 Post post=this.postRepo.findById(postId)
			 .orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));	
	this.postRepo.delete(post);
	}
	
	

	//----------Get_All_Post (Method ko implementation)------------------

    @Override
   public List<PostDto>getAllPost(){
    	List<Post>allPosts=this.postRepo.findAll();
    	List<PostDto>postDtos=allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    	return postDtos;
    
  }
//----------------Get_Post_By_ID---------------------------------
	@Override
	public PostDto getPostById(Integer postId) {
	Post post=	this.postRepo.findById(postId)
			.orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

//---------------get_post by category --------------------------------------------
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		//category fetch garako jun id pass garako yadi vatyana vane exception throw garxa
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		
		//sabai post lai Dto ma convert garako
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	
//--------------get_Post by user----------------------------------------------------------
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		//first user lai fetch garne
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
		List<Post>posts=this.postRepo.findByUser(user);
		List<PostDto>postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	
	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post>posts=this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream()
			    .map((post) -> this.modelMapper.map(post, PostDto.class))
			    .collect(Collectors.toList());

		
		return postDtos;
	}
//	@Override
//	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
