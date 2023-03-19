package com.sandip.blog.Exceptions;

public class ApiException extends RuntimeException{

	public ApiException(String message) {
		super(message);

	}

	public ApiException() {
		super();

	}
}
