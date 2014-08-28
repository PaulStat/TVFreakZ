/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import com.tvfreakz.util.Utils;

public class DateTimeParameterValidator {
	
	public static boolean validateDateTimeString(String dateString) {
	  boolean result = true;
	  try {
	    Utils.DATE_TIME_FORMATTER.parseDateTime(dateString);
	  } catch(IllegalArgumentException e) {
        result = false;
	  }	  
	  return result;
	}

}
