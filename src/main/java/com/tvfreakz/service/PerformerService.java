/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.entity.Performer;

public interface PerformerService {

  Performer findByPerformerId(Long performerId) throws PerformerNotFoundException;

}
