/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.exception;

@SuppressWarnings("serial")
public class InvalidDateFormatException extends Exception {
	
	public InvalidDateFormatException() {
		
	}
	
	public InvalidDateFormatException(String message) {
      super(message);
	}

}
