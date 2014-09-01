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

import com.tvfreakz.model.dto.PerformerDTO;
import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.service.PerformerService;

@Controller
public class PerformerController {
  
  private PerformerService performerService;
  
  @Autowired
  public void setPerformerService(PerformerService performerService) {
    this.performerService = performerService;
  }
  
  @ResponseBody
  @RequestMapping(value = "/api/performers", method = RequestMethod.GET)
  public List<PerformerDTO> findAll() {
    List<Performer> performers = performerService.findAll();
    return createDTOList(performers);
  }

  private List<PerformerDTO> createDTOList(List<Performer> performers) {
    List<PerformerDTO> performerDTOs = new ArrayList<>();
    for(Performer performer: performers) {
      PerformerDTO dto = new PerformerDTO();
      dto.setPerformerId(performer.getPerformerId());
      dto.setPerformerName(performer.getPerformerName());
      dto.setProgrammes(performer.getProgrammes());
      performerDTOs.add(dto);
    }
    return performerDTOs;
  }

}
