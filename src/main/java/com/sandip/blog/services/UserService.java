package com.sandip.blog.services;


import java.util.List;

import com.sandip.blog.payloads.UserDto;



public interface UserService {
	
	//regester user 
		UserDto registerNewUser(UserDto user);
		
		//normal user
		UserDto createUser(UserDto user);
	

	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
    void deleteUser(Integer userId);
}
