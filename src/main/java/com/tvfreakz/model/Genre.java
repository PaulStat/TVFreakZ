/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

/**
 * Genre entity
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "GENRE")
public class Genre {
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long genreId;
  
  private String genreName;
  
  public Genre() {}
  
  /**
   * Return the Genre Name
   * @return genreName
   */
  public String getGenreName() {
    return genreName;
  }
  
  private void setGenreName(String genreName) {
    this.genreName = genreName;
  }  

  public Long getGenreId() {
    return genreId;
  }
  
  private void setGenreId(Long genreId) {
    this.genreId = genreId;
  }

}
