/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.hibernate.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

/**
 * Episode entity
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "EPISODE")
public class Episode {
  
  public Episode() {}
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long episodeId;
  
  @Type(type="com.tvfreakz.hibernate.entity.Programme")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="PROGRAMMEID")
  private Programme programme;
  
  @Type(type="com.tvfreakz.hibernate.entity.Director")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="DIRECTORID")
  private Director director;
  
  private String subtitle;
  
  private String episode;
  
  private String description;
  
  @ManyToMany(
      targetEntity=Performer.class,
      cascade={CascadeType.PERSIST, CascadeType.MERGE}
  )
  @JoinTable(
      name="PROG_PERFORMER",
      joinColumns=@JoinColumn(name="EPISODEID"),
      inverseJoinColumns=@JoinColumn(name="PERFORMERID")
  )
  private Set<Performer> performers;

  /**
   * @return the episodeId
   */
  public Long getEpisodeId() {
    return episodeId;
  }

  /**
   * @param episodeId the episodeId to set
   */
  private void setEpisodeId(Long episodeId) {
    this.episodeId = episodeId;
  }

  /**
   * @return the programme
   */
  public Programme getProgramme() {
    return programme;
  }

  /**
   * @param programme the programme to set
   */
  private void setProgramme(Programme programme) {
    this.programme = programme;
  }

  /**
   * @return the director
   */
  public Director getDirector() {
    return director;
  }

  /**
   * @param director the director to set
   */
  private void setDirector(Director director) {
    this.director = director;
  }

  /**
   * @return the subtitle
   */
  public String getSubtitle() {
    return subtitle;
  }

  /**
   * @param subtitle the subtitle to set
   */
  private void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  /**
   * @return the episode
   */
  public String getEpisode() {
    return episode;
  }

  /**
   * @param episode the episode to set
   */
  private void setEpisode(String episode) {
    this.episode = episode;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  private void setDescription(String description) {
    this.description = description;
  }
  
  public Set<Performer> getPerformers() {
    return performers;
  }

  private void setPerformers(Set<Performer> performers) {
    this.performers = performers;
  }
}
