/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
  
  public static class Builder {
    private Long performerId;
    private String performerName;
    private Set<Programme> programmes;
    
    public Performer build() {
      return new Performer(performerId, performerName, programmes);    
    }
    
    public Builder withPeformerName(String performerName) {
      this.performerName = performerName;
      return this;
    }
    
    public Builder withPerformerId(Long performerId) {
      this.performerId = performerId;
      return this;
    }
    
    public Builder withProgrammes(Set<Programme> programmes) {
      this.programmes = programmes;
      return this;
    }
    
  }
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long performerId;
  
  @Column(name = "PERFORMER")
  private String performerName;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      mappedBy = "performers",
      targetEntity = Programme.class
  )
  public Set<Programme> programmes;
  
  public Performer() {}
  
  public Performer(Long performerId, String performerName, Set<Programme> programmes) {
    this.performerId = performerId;
    this.performerName = performerName;
    this.programmes = programmes;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Performer other = (Performer) obj;
    if (performerId == null) {
      if (other.performerId != null)
        return false;
    } else if (!performerId.equals(other.performerId))
      return false;
    if (performerName == null) {
      if (other.performerName != null)
        return false;
    } else if (!performerName.equals(other.performerName))
      return false;
    return true;
  }

  public Long getPerformerId() {
    return performerId;
  }

  public String getPerformerName() {
    return performerName;
  }
  
  /**
   * Returns a list of programmes the performer is
   * associate with
   * @return programmes
   */
  public Set<Programme> getProgrammes() {
    return programmes;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((performerId == null) ? 0 : performerId.hashCode());
    result = prime * result + ((performerName == null) ? 0 : performerName.hashCode());
    return result;
  }
  
  public void setPerformerId(Long performerId) {
    this.performerId = performerId;
  }
  
  public void setPerformerName(String performerName) {
    this.performerName = performerName;
  }
  
  public void setProgrammes(Set<Programme> programmes) {
    this.programmes = programmes;
  }

}
