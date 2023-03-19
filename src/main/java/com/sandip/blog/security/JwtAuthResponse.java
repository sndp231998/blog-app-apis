package com.sandip.blog.security;

import com.sandip.blog.payloads.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	private UserDto user;
}
