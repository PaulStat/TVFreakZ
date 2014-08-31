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

import com.tvfreakz.model.dto.DirectorDTO;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.service.DirectorService;

@Controller
public class DirectorController {
  
  private DirectorService directorService;
  
  @Autowired
  public void setDirectorService(DirectorService directorService) {
    this.directorService = directorService;
  }
  
  @ResponseBody
  @RequestMapping(value = "/api/directors", method = RequestMethod.GET)
  public List<DirectorDTO> findAll() {
    List<Director> directors = directorService.findAll();
    return createDTOList(directors);
  }

  private List<DirectorDTO> createDTOList(List<Director> directors) {
    List<DirectorDTO> directorDTOs = new ArrayList<>();
    for(Director director: directors) {
      DirectorDTO dto = new DirectorDTO();
      dto.setDirectorId(director.getDirectorId());
      dto.setDirectorName(director.getDirectorName());
      directorDTOs.add(dto);
    }
    return directorDTOs;
  }

}
