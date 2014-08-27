/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utils {
	
  public static final String DATE_TIME_FORMAT = "yyyyMMddHHmm";
  public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMAT);

}
