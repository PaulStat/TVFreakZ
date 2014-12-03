/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

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
  
  public static class Builder {
    private Long genreId;
    private String genreName;
    
    public Genre build() {
      return new Genre(genreId, genreName);
    }
    
    public Builder withGenreId(Long genreId) {
      this.genreId = genreId;
      return this;
    }
    
    public Builder withGenreName(String genreName) {
      this.genreName = genreName;
      return this;
    }
  }
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long genreId;
  
  private String genreName;
  
  public Genre() {}
  
  public Genre(Long genreId, String genreName) {
    this.genreId = genreId;
    this.genreName = genreName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Genre other = (Genre) obj;
    if (genreId == null) {
      if (other.genreId != null)
        return false;
    } else if (!genreId.equals(other.genreId))
      return false;
    if (genreName == null) {
      if (other.genreName != null)
        return false;
    } else if (!genreName.equals(other.genreName))
      return false;
    return true;
  }
  
  public Long getGenreId() {
    return genreId;
  }

  /**
   * Return the Genre Name
   * @return genreName
   */
  public String getGenreName() {
    return genreName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
    result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
    return result;
  }  

  public void setGenreId(Long genreId) {
    this.genreId = genreId;
  }
  
  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

}
