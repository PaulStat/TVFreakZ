/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

/**
 * Director entity
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "DIRECTOR")
public class Director {
  
  public static class Builder {
    private Long directorId;
    private String directorName;
    
    public Director build() {
      return new Director(directorId, directorName);
    }
    
    public Builder withDirectorId(Long directorId) {
      this.directorId = directorId;
      return this;
    }
    
    public Builder withDirectorName(String directorName) {
      this.directorName = directorName;
      return this;
    }
    
  }
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long directorId;
  
  @Column(name = "DIRECTOR")
  private String directorName;  

  public Director() {}
  
  public Director(Long directorId, String directorName) {
    this.directorId = directorId;
    this.directorName = directorName;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Director other = (Director) obj;
    if (directorId == null) {
      if (other.directorId != null)
        return false;
    } else if (!directorId.equals(other.directorId))
      return false;
    if (directorName == null) {
      if (other.directorName != null)
        return false;
    } else if (!directorName.equals(other.directorName))
      return false;
    return true;
  }
  
  /**
   * Returns the Director ID
   * @return directorId
   */
  public Long getDirectorId() {
    return directorId;
  }

  /**
   * Returns the name of the Director
   * @return directorName
   */
  public String getDirectorName() {
    return directorName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((directorId == null) ? 0 : directorId.hashCode());
    result = prime * result + ((directorName == null) ? 0 : directorName.hashCode());
    return result;
  }
  
  public void setDirectorId(Long directorId) {
    this.directorId = directorId;
  }
  
  public void setDirectorName(String directorName) {
    this.directorName = directorName;
  }


}
