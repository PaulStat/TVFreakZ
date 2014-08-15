/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

/**
 * Peformer entity
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "PERFORMER")
public class Performer {
  
  public Performer() {}
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long performerId;
  
  private String performer;
  
  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      mappedBy = "performers",
      targetEntity = Programme.class
  )
  public Set<Programme> programmes;
  
  public String getPerformer() {
    return performer;
  }
  
  private void setPerformer(String performer) {
    this.performer = performer;
  }
  
  public Long getPerformerId() {
    return performerId;
  }
  
  private void setPerformerId(Long performerId) {
    this.performerId = performerId;
  }
  
  /**
   * Returns a list of programmes the performer is
   * associate with
   * @return programmes
   */
  public Set<Programme> getProgrammes() {
    return programmes;
  }
  
  private void setProgrammes(Set<Programme> programmes) {
    this.programmes = programmes;
  }

}
