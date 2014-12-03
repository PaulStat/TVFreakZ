/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.dto;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ProgrammeSearchDTO {
  
  public static class Builder {
    
    private String[] channelIdList;
    private String[] genreIdList;
    
    private DateTime fromDateTime;
    private DateTime toDateTime;
    private String progName;
    
    private boolean subtitled;
    private boolean signed;
    private boolean film;
    
    public ProgrammeSearchDTO build() {
      return new ProgrammeSearchDTO(channelIdList, genreIdList, fromDateTime, toDateTime, progName, subtitled, signed, film);
    }

    public Builder withChannelIdList(String[] channelIdList) {
      this.channelIdList = channelIdList;
      return this;
    }

    public Builder withFilm(boolean film) {
      this.film = film;
      return this;
    }

    public Builder withFromDateTime(DateTime fromDateTime) {
      this.fromDateTime = fromDateTime;
      return this;
    }

    public Builder withGenreIdList(String[] genreIdList) {
      this.genreIdList = genreIdList;
      return this;
    }

    public Builder withProgName(String progName) {
      this.progName = progName;
      return this;
    }

    public Builder withSigned(boolean signed) {
      this.signed = signed;
      return this;
    }

    public Builder withSubtitled(boolean subtitled) {
      this.subtitled = subtitled;
      return this;
    }

    public Builder withToDateTime(DateTime toDateTime) {
      this.toDateTime = toDateTime;
      return this;
    }
    
  }
  
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
  
  public ProgrammeSearchDTO() {
    
  }

  public ProgrammeSearchDTO(String[] channelIdList, String[] genreIdList, DateTime fromDateTime, DateTime toDateTime,
      String progName, boolean subtitled, boolean signed, boolean film) {
    this.channelIdList = channelIdList;
    this.genreIdList = genreIdList;
    this.fromDateTime = fromDateTime;
    this.toDateTime = toDateTime;
    this.progName = progName;
    this.subtitled = subtitled;
    this.signed = signed;
    this.film = film;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProgrammeSearchDTO other = (ProgrammeSearchDTO) obj;
    if (!Arrays.equals(channelIdList, other.channelIdList))
      return false;
    if (film != other.film)
      return false;
    if (fromDateTime == null) {
      if (other.fromDateTime != null)
        return false;
    } else if (!fromDateTime.equals(other.fromDateTime))
      return false;
    if (!Arrays.equals(genreIdList, other.genreIdList))
      return false;
    if (progName == null) {
      if (other.progName != null)
        return false;
    } else if (!progName.equals(other.progName))
      return false;
    if (signed != other.signed)
      return false;
    if (subtitled != other.subtitled)
      return false;
    if (toDateTime == null) {
      if (other.toDateTime != null)
        return false;
    } else if (!toDateTime.equals(other.toDateTime))
      return false;
    return true;
  }
  
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
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(channelIdList);
    result = prime * result + (film ? 1231 : 1237);
    result = prime * result + ((fromDateTime == null) ? 0 : fromDateTime.hashCode());
    result = prime * result + Arrays.hashCode(genreIdList);
    result = prime * result + ((progName == null) ? 0 : progName.hashCode());
    result = prime * result + (signed ? 1231 : 1237);
    result = prime * result + (subtitled ? 1231 : 1237);
    result = prime * result + ((toDateTime == null) ? 0 : toDateTime.hashCode());
    return result;
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
