/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvfreakz.model.dto.GenreDTO;
import com.tvfreakz.model.entity.Genre;
import com.tvfreakz.service.GenreService;

@Controller
public class GenreController {
  
  private GenreService genreService;
  
  @Autowired
  public void setGenreService(GenreService genreService) {
    this.genreService = genreService;
  }
  
  @ResponseBody
  @RequestMapping(value = "/api/genres", method = RequestMethod.GET)
  public List<GenreDTO> findAll() {
    List<Genre> genres = genreService.findAll();
    return createDTOList(genres);
  }

  private List<GenreDTO> createDTOList(List<Genre> genres) {
    List<GenreDTO> genreDTOs = new ArrayList<>();
    for(Genre genre: genres) {
      GenreDTO dto = new GenreDTO();
      dto.setGenreId(genre.getGenreId());
      dto.setGenreName(genre.getGenreName());
      genreDTOs.add(dto);
    }
    return genreDTOs;
  }

}
