/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ProgrammeSearchDTO {
  
  @NotEmpty(message = "Please select at least one channel")
  private String[] channelIdList;
  
  @NotEmpty(message = "Please select at least one genre")
  private String[] genreIdList;  
  
  @NotNull
  @DateTimeFormat(pattern="dd/MM/yyyy")
  private DateTime fromDateTime;
  
  @NotNull
  @DateTimeFormat(pattern="dd/MM/yyyy")
  private DateTime toDateTime;
  
  @Length(max = 200)
  private String progName;
  
  private boolean subtitled;
  private boolean signed;
  private boolean film;
  
  public String[] getChannelIdList() {
    return channelIdList;
  }
  
  public DateTime getFromDateTime() {
    return fromDateTime;
  }
  
  public String[] getGenreIdList() {
    return genreIdList;
  }  
  
  public String getProgName() {
    return progName;
  }
  
  public DateTime getToDateTime() {
    return toDateTime;
  }
  
  public boolean isFilm() {
    return film;
  }
  
  public boolean isSigned() {
    return signed;
  }
  
  public boolean isSubtitled() {
    return subtitled;
  }
  
  public void setChannelIdList(String[] channelIdList) {
    this.channelIdList = channelIdList;
  }
  
  public void setFilm(boolean film) {
    this.film = film;
  }
  
  public void setFromDateTime(DateTime fromDateTime) {
    this.fromDateTime = fromDateTime;
  }
  
  public void setGenreIdList(String[] genreIdList) {
    this.genreIdList = genreIdList;
  }
  
  public void setProgName(String progName) {
    this.progName = progName;
  }
  
  public void setSigned(boolean signed) {
    this.signed = signed;
  }
  
  public void setSubtitled(boolean subtitled) {
    this.subtitled = subtitled;
  }
  
  public void setToDateTime(DateTime toDateTime) {
    this.toDateTime = toDateTime;
  }  
  

}
