package edu.utdallas.wpl.cookies.spring.common.exceptions;

import static edu.utdallas.wpl.cookies.spring.common.enums.Constants.*;

public class CookiesApplicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public CookiesApplicationException() {
		super();
	}
	
	public CookiesApplicationException(String message) {
		super(EXCEPTION_PREFIX + message);
	}

	public CookiesApplicationException(String message, Throwable cause) {
		super(EXCEPTION_PREFIX + message, cause);
	}
	
}
