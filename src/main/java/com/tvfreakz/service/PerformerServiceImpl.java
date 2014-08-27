/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.repository.PerformerRepository;

@Service("performerService")
public class PerformerServiceImpl implements PerformerService {
  
  private PerformerRepository performerRepository;
  
  @Autowired
  public PerformerServiceImpl(PerformerRepository performerRepository) {
    this.performerRepository = performerRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public Performer findByPerformerId(Long performerId) throws PerformerNotFoundException {
    Performer performer = performerRepository.findByPerformerId(performerId);
    if(performer == null) {
      throw new PerformerNotFoundException("Performer with an id of " +performerId +" was not found");
    }
    return performer;
  }

}
