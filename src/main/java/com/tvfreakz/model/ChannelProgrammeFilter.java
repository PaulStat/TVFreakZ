/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import com.tvfreakz.model.entity.Genre;

/**
 * ChannelProgrammeFilter
 * @author pstatham
 *
 */
public class ChannelProgrammeFilter {
  
  private boolean deafSigned;
  private boolean subtitled;
  private boolean film;
  private Genre genre;
  
  public Genre getGenre() {
    return genre;
  }
  
  public boolean isDeafSigned() {
    return deafSigned;
  }
  
  public boolean isFilm() {
    return film;
  }
  
  public boolean isSubtitled() {
    return subtitled;
  }
  
  public void setDeafSigned(boolean deafSigned) {
    this.deafSigned = deafSigned;
  }
  
  public void setFilm(boolean film) {
    this.film = film;
  }
  
  public void setGenre(Genre genre) {
    this.genre = genre;
  }
  
  public void setSubtitled(boolean subtitled) {
    this.subtitled = subtitled;
  }
  
  

}
