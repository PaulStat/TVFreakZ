package com.tvfreakz.model.dto;

import java.util.Set;

import com.tvfreakz.model.entity.ProgEpPerfAssociation;
import com.tvfreakz.model.entity.Programme;

public class PerformerDTO {
  
  private Long performerId;
  
  private String performerName;
  
  public Set<ProgEpPerfAssociation> performerAssociations;

  public String getPerformerName() {
    return performerName;
  }

  public Long getPerformerId() {
    return performerId;
  }

  public Set<ProgEpPerfAssociation> getProgrammes() {
    return performerAssociations;
  }

  public void setPerformerName(String performerName) {
    this.performerName = performerName;
  }

  public void setPerformerId(Long performerId) {
    this.performerId = performerId;
  }

  public void setPerformerAssociations(Set<ProgEpPerfAssociation> performerAssociations) {
    this.performerAssociations = performerAssociations;
  }  


}
