package com.tvfreakz.model.dto;

import java.util.Set;

public class PerformerDTO {
  
  private Long performerId;
  
  private String performer;
  
  public Set<ProgrammeDTO> programmes;

  public String getPerformer() {
    return performer;
  }

  public Long getPerformerId() {
    return performerId;
  }

  public Set<ProgrammeDTO> getProgrammes() {
    return programmes;
  }

  public void setPerformer(String performer) {
    this.performer = performer;
  }

  public void setPerformerId(Long performerId) {
    this.performerId = performerId;
  }

  public void setProgrammes(Set<ProgrammeDTO> programmes) {
    this.programmes = programmes;
  }  


}
