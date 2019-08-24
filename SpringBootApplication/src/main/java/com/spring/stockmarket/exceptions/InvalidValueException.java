package com.spring.stockmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value=HttpStatus.BAD_REQUEST)
public class InvalidValueException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public InvalidValueException(String exceptionMessage) {
		super(exceptionMessage);
	}
	

}
