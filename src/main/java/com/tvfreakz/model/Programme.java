/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

/**
 * Programme entity
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "PROGRAMME")
public class Programme {
  
  public Programme() {}
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long programmeId;
  
  @Type(type="com.tvfreakz.model.Genre")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="GENREID")
  private Genre genre;
  
  @Type(type="com.tvfreakz.model.Director")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="DIRECTORID")
  private Director director;
  
  private String progTitle;
  
  @Column(name = "YR")
  @Temporal(TemporalType.DATE)
  private Date year;
  
  @Type(type="org.hibernate.type.BooleanType")
  private boolean film;

  @Type(type="org.hibernate.type.BooleanType")
  private boolean wideScreen;
  
  @Column(name = "BNW")
  @Type(type="org.hibernate.type.BooleanType")
  private boolean blackAndWhite;
  
  private String certificate;
  
  private String description;
  
  @ManyToMany(
      targetEntity=Performer.class,
      cascade={CascadeType.PERSIST, CascadeType.MERGE}
  )
  @JoinTable(
      name="PROG_PERFORMER",
      joinColumns=@JoinColumn(name="PROGRAMMEID"),
      inverseJoinColumns=@JoinColumn(name="PERFORMERID")
  )
  private Set<Performer> performers;

  public Genre getGenre() {
    return genre;
  }  

  public Director getDirector() {
    return director;
  }
  
  /**
   * @return the programmeId
   */
  public Long getProgrammeId() {
    return programmeId;
  }

  /**
   * @param programmeId the programmeId to set
   */
  private void setProgrammeId(Long programmeId) {
    this.programmeId = programmeId;
  }

  /**
   * @return the progTitle
   */
  public String getProgTitle() {
    return progTitle;
  }

  /**
   * @param progTitle the progTitle to set
   */
  private void setProgTitle(String progTitle) {
    this.progTitle = progTitle;
  }

  /**
   * @return the year
   */
  public Date getYear() {
    return year;
  }

  /**
   * @param year the year to set
   */
  private void setYear(Date year) {
    this.year = year;
  }

  /**
   * @return the film
   */
  public boolean isFilm() {
    return film;
  }

  /**
   * @param film the film to set
   */
  private void setFilm(boolean film) {
    this.film = film;
  }

  /**
   * @return the wideScreen
   */
  public boolean isWideScreen() {
    return wideScreen;
  }

  /**
   * @param wideScreen the wideScreen to set
   */
  private void setWideScreen(boolean wideScreen) {
    this.wideScreen = wideScreen;
  }

  /**
   * @return the blackAndWhite
   */
  public boolean isBlackAndWhite() {
    return blackAndWhite;
  }

  /**
   * @param blackAndWhite the blackAndWhite to set
   */
  private void setBlackAndWhite(boolean blackAndWhite) {
    this.blackAndWhite = blackAndWhite;
  }

  /**
   * @return the certificate
   */
  public String getCertificate() {
    return certificate;
  }

  /**
   * @param certificate the certificate to set
   */
  private void setCertificate(String certificate) {
    this.certificate = certificate;
  }

  /**
   * @param genre the genre to set
   */
  private void setGenre(Genre genre) {
    this.genre = genre;
  }

  /**
   * @param director the director to set
   */
  private void setDirector(Director director) {
    this.director = director;
  }
  
  public Set<Performer> getPerformers() {
    return performers;
  }
  
  private void setPerformers(Set<Performer> performers) {
    this.performers = performers;
  }
  
  public String getDescription() {
    return description;
  }
  
  private void setDescription(String description) {
    this.description = description;
  }

}
