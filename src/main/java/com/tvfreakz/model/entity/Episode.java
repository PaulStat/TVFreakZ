/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

/**
 * Episode entity
 * 
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "EPISODE")
public class Episode {

    public static class Builder {
        private Long episodeId;
        private Director director;
        private String subtitle;
        private String episode;
        private String description;
        private Set<ProgEpPerfAssociation> episodeAssociations;

        public Episode build() {
            return new Episode(episodeId, director, subtitle,
                    episode, description, episodeAssociations);
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDirector(Director director) {
            this.director = director;
            return this;
        }

        public Builder withEpisode(String episode) {
            this.episode = episode;
            return this;
        }

        public Builder withEpisodeId(Long episodeId) {
            this.episodeId = episodeId;
            return this;
        }

        public Builder withSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder withEpisodeAssociations(
                Set<ProgEpPerfAssociation> episodeAssociations) {
            this.episodeAssociations = episodeAssociations;
            return this;
        }
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long episodeId;

    @Type(type = "com.tvfreakz.model.Director")
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "DIRECTORID")
    private Director director;

    private String subtitle;

    private String episode;

    private String description;

    @OneToMany(mappedBy = "episode")
    private Set<ProgEpPerfAssociation> episodeAssociations;

    public Episode() {
    }

    public Episode(Long episodeId, Director director,
            String subtitle, String episode, String description,
            Set<ProgEpPerfAssociation> episodeAssociations) {
        this.episodeId = episodeId;
        this.director = director;
        this.subtitle = subtitle;
        this.episode = episode;
        this.description = description;
        this.episodeAssociations = episodeAssociations;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Episode other = (Episode) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (director == null) {
            if (other.director != null)
                return false;
        } else if (!director.equals(other.director))
            return false;
        if (episode == null) {
            if (other.episode != null)
                return false;
        } else if (!episode.equals(other.episode))
            return false;
        if (episodeId == null) {
            if (other.episodeId != null)
                return false;
        } else if (!episodeId.equals(other.episodeId))
            return false;
        if (subtitle == null) {
            if (other.subtitle != null)
                return false;
        } else if (!subtitle.equals(other.subtitle))
            return false;
        return true;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the director
     */
    public Director getDirector() {
        return director;
    }

    /**
     * @return the episode
     */
    public String getEpisode() {
        return episode;
    }

    /**
     * @return the episodeId
     */
    public Long getEpisodeId() {
        return episodeId;
    }

    /**
     * @return the subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result
                + ((director == null) ? 0 : director.hashCode());
        result = prime * result + ((episode == null) ? 0 : episode.hashCode());
        result = prime * result
                + ((episodeId == null) ? 0 : episodeId.hashCode());
        result = prime * result
                + ((subtitle == null) ? 0 : subtitle.hashCode());
        return result;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param director
     *            the director to set
     */
    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * @param episode
     *            the episode to set
     */
    public void setEpisode(String episode) {
        this.episode = episode;
    }

    /**
     * @param episodeId
     *            the episodeId to set
     */
    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    /**
     * @param subtitle
     *            the subtitle to set
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonIgnore
    public Set<ProgEpPerfAssociation> getEpisodeAssociations() {
        return episodeAssociations;
    }

    public void setEpisodeAssociations(
            Set<ProgEpPerfAssociation> episodeAssociations) {
        this.episodeAssociations = episodeAssociations;
    }
}
