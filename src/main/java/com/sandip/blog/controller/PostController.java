package com.sandip.blog.controller;

import javax.servlet.http.HttpServletResponse;
import com.sandip.blog.payloads.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import com.sandip.blog.entities.Post;
import com.sandip.blog.payloads.PostDto;
import com.sandip.blog.services.FileService;
import com.sandip.blog.services.PostService;



@RestController
@RequestMapping("/api/")
public class PostController {
//-----------------------------------------------	
	@Autowired
private PostService postService;
//------------------------------------------------	
	@Autowired
	private FileService fileservice;
//-----------------------------------------------	
	@Value("${project.image}")
	private String path;
//------------------------------------------------	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto>
	createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			){
		PostDto createPost=this.postService.CreatePost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}

	//------------------------Post_get By user-------------------------------
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity< List<PostDto>>getPostsByUser(@PathVariable Integer userId){
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
//-----------------get by category(category wise post nikalne--------------
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity< List<PostDto>>getPostsByCategory(@PathVariable Integer categoryId){
	List<PostDto>posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
//--------------------------------Get_all_post------------------------

	@GetMapping("/posts")
public ResponseEntity<List<PostDto>>getAllPost(){
		List<PostDto>allPost=this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
	
	
	
//------------------------------Get_Single_Post--------------------------------------
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId){

		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	
	//-------------Delete_post_Controller-------------
	@DeleteMapping("/posts/{postId}")
	public ApiResponce deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponce("post is successfully deleted !!",true);
	}
	
	//--------------Update_Post_--------------------------------------
	@PutMapping("/posts/{postId}")
	public ResponseEntity <PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
		PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
//---------------------Post-Search_Controller-----------------------------------
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>searchByTitle(
		@PathVariable("keywords")String keywords){
			List<PostDto>result=this.postService.searchPosts(keywords);
			return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
		}
//-----------------------Uploading_Image_Controller----------------------------
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadPostImage(
			@RequestParam("image")MultipartFile image, @PathVariable Integer postId)throws IOException{
		//first --post xa ki xina ? check garya xina vane yae bata resource not found exception return hunxa
		PostDto postDto=this.postService.getPostById(postId);
		
		String fileName=this.fileservice.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
//------------------------method to serve file/image------------------	
	
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource = this.fileservice.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream())   ;

    }
	
	}

	

