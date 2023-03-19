package com.sandip.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

//import com.sandip.blog.entities.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	  private Integer PostId;
	  
	@NotEmpty(message="title must be")
	private String title;
	
	@NotEmpty(message="content must be")
	private String content;
	
	//@NotEmpty(message="name must be")
	private String imageName;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	//post lai fetch garyaum vane comment afai aaunxa
		private Set<CommentDto> comments=new HashSet<>();
	
}
