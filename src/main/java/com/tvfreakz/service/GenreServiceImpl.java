/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvfreakz.model.entity.Genre;

@Service("genreService")
public class GenreServiceImpl implements GenreService {
  
  private GenreRepository genreRepository;

  @Autowired
  public GenreServiceImpl(GenreRepository genreRepository) {
    this.genreRepository = genreRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Genre> findAll() {
    return genreRepository.findAll(sortByGenreNameAsc());
  }

  private Sort sortByGenreNameAsc() {
    return new Sort(Sort.Direction.ASC, "genreName");
  }

}
