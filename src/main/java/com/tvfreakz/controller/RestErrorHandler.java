/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.exception.InvalidDateFormatException;
import com.tvfreakz.exception.PerformerNotFoundException;

@ControllerAdvice
public class RestErrorHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorHandler.class);

  @ExceptionHandler(DirectorNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleDirectorNotFoundException(DirectorNotFoundException ex) {
    LOGGER.debug("handling 404 error on a director entry");
  }

  @ExceptionHandler(PerformerNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handlePerformerNotFoundException(PerformerNotFoundException ex) {
    LOGGER.debug("handling 404 error on a performer entry");
  }
  
  @ExceptionHandler(ChannelNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public void handleChannelNotFoundException(ChannelNotFoundException ex) {
    LOGGER.debug("handling 404 error on a channel entry");
  }
  
  @ExceptionHandler(InvalidDateFormatException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public void handleInvalidDateFormatException(InvalidDateFormatException ex) {
	  LOGGER.debug("handling bad request on date string");  
  }

}
