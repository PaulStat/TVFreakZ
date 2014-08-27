/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.exception;

@SuppressWarnings("serial")
public class DirectorNotFoundException extends Exception {
	
	public DirectorNotFoundException() {
		
	}
	
	public DirectorNotFoundException(String message) {
		super(message);
	}

}
