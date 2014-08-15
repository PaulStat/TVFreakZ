/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.hibernate.entity;

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
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long directorId;
  
  @Column(name = "DIRECTOR")
  private String directorName;
  
  public Director() {}  

  /**
   * Returns the Director ID
   * @return directorId
   */
  public Long getDirectorId() {
    return directorId;
  }
  
  private void setDirectorId(Long directorId) {
    this.directorId = directorId;
  }
  
  /**
   * Returns the name of the Director
   * @return directorName
   */
  public String getDirectorName() {
    return directorName;
  }
  
  private void setDirectorName(String directorName) {
    this.directorName = directorName;
  }


}
