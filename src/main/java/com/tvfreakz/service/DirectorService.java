/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.model.entity.Director;

public interface DirectorService {
  
  Director findByDirectorId(Long directorId) throws DirectorNotFoundException;

}
