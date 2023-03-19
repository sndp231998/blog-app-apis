package com.sandip.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sandip.blog.Exceptions.ResourceNotFoundException;
import com.sandip.blog.config.AppConstants;
import com.sandip.blog.entities.Role;
import com.sandip.blog.entities.User;
import com.sandip.blog.payloads.UserDto;
import com.sandip.blog.repositories.RoleRepo;
import com.sandip.blog.repositories.UserRepo;
import com.sandip.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo rolerepo;
	
	//----------------------save user--------------------------
	@Override
	public UserDto createUser(UserDto userDto) {
	    User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}
//---------------------------Update--------------------------------------
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		
		return userDto1;
	}
//-------------------------Get--------------------------------------
	@Override
	public UserDto getUserById(Integer userId) {
	
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","Id",userId));
		return this.userToDto(user);
		
	}
//---------------------------GetAll---------------------------------------
	@Override
	public List<UserDto> getAllUser() {
	      List<User> users=this.userRepo.findAll();
	   List<UserDto>userDtos =   users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
//---------------delete------------------------------------
	@Override
	public void deleteUser(Integer userId) {
	 User user=this.userRepo.findById(userId)
			 .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
     this.userRepo.delete(user);
	}
	//----------------------------------------
	private User dtoToUser(UserDto userDto) {
		//converting dto to user method -2
		User user=this.modelMapper.map(userDto, User.class);
		
		// converting dto to user --Method-1(manually) 
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getAbout());
		
		return user;
		
	}
	public UserDto userToDto(User user) {
		//user to userDto method-2
		// converting user to userdto 
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
		
		//method-1 manually method
//		UserDto userDto=new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
		
	}
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
		
		//encoded the password..(First new user le register garda, pw change vayra incoded hunxa
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
//---------------assign role during register role-------------------------
		Role role=this.rolerepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
	User newuser=this.userRepo.save(user);
		return this.modelMapper.map(newuser, UserDto.class);
	}

	
	

}
