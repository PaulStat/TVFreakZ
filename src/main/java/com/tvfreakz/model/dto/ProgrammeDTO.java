package com.tvfreakz.model.dto;

import java.util.Date;
import java.util.Set;

import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Genre;

public class ProgrammeDTO {
  
  private Long programmeId;
  
  private Genre genre;
  
  private Director director;
  
  private String progTitle;
  
  private Date year;
  
  private boolean film;

  private boolean wideScreen;
  
  private boolean blackAndWhite;
  
  private String certificate;
  
  private String description;
  
  private Set<PerformerDTO> performers;

  public String getCertificate() {
    return certificate;
  }

  public String getDescription() {
    return description;
  }

  public Director getDirector() {
    return director;
  }

  public Genre getGenre() {
    return genre;
  }

  public Set<PerformerDTO> getPerformers() {
    return performers;
  }

  public Long getProgrammeId() {
    return programmeId;
  }

  public String getProgTitle() {
    return progTitle;
  }

  public Date getYear() {
    return year;
  }

  public boolean isBlackAndWhite() {
    return blackAndWhite;
  }

  public boolean isFilm() {
    return film;
  }

  public boolean isWideScreen() {
    return wideScreen;
  }

  public void setBlackAndWhite(boolean blackAndWhite) {
    this.blackAndWhite = blackAndWhite;
  }

  public void setCertificate(String certificate) {
    this.certificate = certificate;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDirector(Director director) {
    this.director = director;
  }

  public void setFilm(boolean film) {
    this.film = film;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public void setPerformers(Set<PerformerDTO> performers) {
    this.performers = performers;
  }

  public void setProgrammeId(Long programmeId) {
    this.programmeId = programmeId;
  }

  public void setProgTitle(String progTitle) {
    this.progTitle = progTitle;
  }

  public void setWideScreen(boolean wideScreen) {
    this.wideScreen = wideScreen;
  }

  public void setYear(Date year) {
    this.year = year;
  }  
  

}