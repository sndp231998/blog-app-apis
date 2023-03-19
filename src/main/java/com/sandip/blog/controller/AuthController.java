package com.sandip.blog.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandip.blog.Exceptions.ApiException;
import com.sandip.blog.entities.User;
import com.sandip.blog.payloads.JwtAuthRequest;
import com.sandip.blog.payloads.UserDto;
import com.sandip.blog.security.JwtAuthResponse;
import com.sandip.blog.security.JwtTokenHelper;
import com.sandip.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins="http://localhost:3000")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getUsername(),request.getPassword());	
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		this.jwtTokenHelper.generateToken(userDetails);	
		String token=this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		//frontend maa user ko detail featch garna lai
		response.setUser(this.mapper.map((User) userDetails, UserDto.class));
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
//---------------------------------------------------------------------------------	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
	try {
		this.authenticationManager.authenticate(authenticationToken);
	}catch(BadCredentialsException e) {
	System.out.println("Invalid details");
	throw new ApiException("Invalid username or password !!");
	}
	
	}
//------------------------ register new user api--------------------

@PostMapping("/register")
public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
UserDto registeredUser = this.userService.registerNewUser(userDto);
return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
}
		}

	
