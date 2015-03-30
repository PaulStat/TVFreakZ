/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

/**
 * Peformer entity
 * 
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
        private Set<ProgEpPerfAssociation> performerAssociations;

        public Performer build() {
            return new Performer(performerId, performerName,
                    performerAssociations);
        }

        public Builder withPeformerName(String performerName) {
            this.performerName = performerName;
            return this;
        }

        public Builder withPerformerId(Long performerId) {
            this.performerId = performerId;
            return this;
        }

        public Builder withPerformerAssociations(
                Set<ProgEpPerfAssociation> performerAssociations) {
            this.performerAssociations = performerAssociations;
            return this;
        }

    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long performerId;

    @Column(name = "PERFORMER")
    private String performerName;

    @OneToMany(mappedBy = "performer")
    private Set<ProgEpPerfAssociation> performerAssociations;

    public Performer() {
    }

    public Performer(Long performerId, String performerName,
            Set<ProgEpPerfAssociation> performerAssociations) {
        this.performerId = performerId;
        this.performerName = performerName;
        this.performerAssociations = performerAssociations;
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
     * Returns a list of programmes the performer is associate with
     * 
     * @return performerAssociations
     */
    @JsonIgnore
    public Set<ProgEpPerfAssociation> getPerformerAssociations() {
        return performerAssociations;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((performerId == null) ? 0 : performerId.hashCode());
        result = prime * result
                + ((performerName == null) ? 0 : performerName.hashCode());
        return result;
    }

    public void setPerformerId(Long performerId) {
        this.performerId = performerId;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public void setPerformerAssociations(
            Set<ProgEpPerfAssociation> performerAssociations) {
        this.performerAssociations = performerAssociations;
    }

}
