/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.repository.DirectorRepository;

@Service("directorService")
public class DirectorServiceImpl implements DirectorService {
  
  private DirectorRepository directorRepository;
  
  @Autowired
  public DirectorServiceImpl(DirectorRepository directorRepository) {
    this.directorRepository = directorRepository;
  }  

  @Transactional(readOnly = true)
  @Override
  public Director findByDirectorId(Long directorId) throws DirectorNotFoundException {
    Director director = directorRepository.findByDirectorId(directorId);
    if(director == null) {
      throw new DirectorNotFoundException("Director with an id of " +directorId +" was not found");
    }
    return director;    
  }

  @Transactional(readOnly = true)
  @Override
  public List<Director> findAll() {
    return directorRepository.findAll(sortByDirectorNameAsc());
  }

  private Sort sortByDirectorNameAsc() {
    return new Sort(Sort.Direction.ASC, "directorName");
  }

}
