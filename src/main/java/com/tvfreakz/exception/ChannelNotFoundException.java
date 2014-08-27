/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.exception;

@SuppressWarnings("serial")
public class ChannelNotFoundException extends Exception {
  
  public ChannelNotFoundException() {
    
  }
  
  public ChannelNotFoundException(String message) {
    super(message);
  }

}
