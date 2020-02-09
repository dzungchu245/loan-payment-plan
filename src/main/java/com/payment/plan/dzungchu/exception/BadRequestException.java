package com.payment.plan.dzungchu.exception;

import java.util.Collections;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class BadRequestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3342972854123293128L;
	public static final String BAD_REQUEST = "BAD_REQUEST";
	
	private final Set<String> errors;

	public BadRequestException(String error) {
		super(error);
		this.errors = Collections.singleton(error);
	}

	public BadRequestException() {
		this(BAD_REQUEST);
	}

}
