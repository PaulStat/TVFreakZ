/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvfreakz.model.entity.Performer;

@Repository("performerRepository")
public interface PerformerRepository extends JpaRepository<Performer, Long> {
  
  Performer findByPerformerId(Long performerId);

}
