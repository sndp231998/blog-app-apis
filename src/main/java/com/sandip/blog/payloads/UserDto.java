package com.sandip.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty(message="name must be")
	@Size(min=4, message="username must be min of 4 character")
	private String name;
	@NotEmpty(message="Email must be")
	@Email(message="Email address is not valid !!")
	private String email;
	@NotEmpty
	@Size (min=3, max=16, message="Password must be 3 or max 16")
	//@Pattern
	private String password;
	@NotEmpty
	private String about;
	
	

}
