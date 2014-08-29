/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.exception;

@SuppressWarnings("serial")
public class ChannelProgrammeNotFoundException extends Exception {
  
  public ChannelProgrammeNotFoundException() {
    
  }
  
  public ChannelProgrammeNotFoundException(String message) {
    super(message);
  }

}
