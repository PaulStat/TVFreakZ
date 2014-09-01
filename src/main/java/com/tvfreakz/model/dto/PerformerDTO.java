package com.tvfreakz.model.dto;

import java.util.Set;

import com.tvfreakz.model.entity.Programme;

public class PerformerDTO {
  
  private Long performerId;
  
  private String performerName;
  
  public Set<Programme> programmes;

  public String getPerformerName() {
    return performerName;
  }

  public Long getPerformerId() {
    return performerId;
  }

  public Set<Programme> getProgrammes() {
    return programmes;
  }

  public void setPerformerName(String performerName) {
    this.performerName = performerName;
  }

  public void setPerformerId(Long performerId) {
    this.performerId = performerId;
  }

  public void setProgrammes(Set<Programme> programmes) {
    this.programmes = programmes;
  }  


}
