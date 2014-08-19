/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

/**
 * ChannelProgramme entity
 * Represents a programme instance for a specified channel
 * @author pstatham
 *
 */
@Entity
@Immutable
@Table(name = "CHANNELPROGRAMME")
public class ChannelProgramme {
  
  public ChannelProgramme() {}
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  private Long channelProgrammeId;
  
  @Type(type="com.tvfreakz.model.Channel")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="CHANNELID")
  private Channel channel;
  
  @Type(type="com.tvfreakz.model.Programme")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="PROGRAMMEID")
  private Programme programme;
  
  @Type(type="com.tvfreakz.model.Episode")
  @OneToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
  @JoinColumn(name="EPISODEID")
  private Episode episode;
  
  @Column(name = "RPEAT")
  @Type(type="org.hibernate.type.BooleanType")
  private boolean repeat;
  
  @Type(type="org.hibernate.type.BooleanType")
  private boolean newSeries;
  
  @Type(type="org.hibernate.type.BooleanType")
  private boolean premiere;
  
  @Type(type="org.hibernate.type.BooleanType")
  private boolean choice;
  
  @Type(type="org.hibernate.type.BooleanType")
  private boolean subtitles;
  
  @Type(type="org.hibernate.type.BooleanType")
  private boolean deafSigned;
  
  private Integer starRating;
  
  @Temporal(TemporalType.DATE)
  private Date progDate;
  
  @Temporal(TemporalType.TIME)
  private Date startTime;
  
  @Temporal(TemporalType.TIME)
  private Date endTime;
  
  private Integer duration;

  /**
   * @return the channelProgrammeId
   */
  public Long getChannelProgrammeId() {
    return channelProgrammeId;
  }

  /**
   * @param channelProgrammeId the channelProgrammeId to set
   */
  public void setChannelProgrammeId(Long channelProgrammeId) {
    this.channelProgrammeId = channelProgrammeId;
  }

  /**
   * @return the channel
   */
  public Channel getChannel() {
    return channel;
  }

  /**
   * @param channel the channel to set
   */
  public void setChannel(Channel channel) {
    this.channel = channel;
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
  public void setProgramme(Programme programme) {
    this.programme = programme;
  }

  /**
   * @return the episode
   */
  public Episode getEpisode() {
    return episode;
  }

  /**
   * @param episode the episode to set
   */
  public void setEpisode(Episode episode) {
    this.episode = episode;
  }

  /**
   * @return the repeat
   */
  public boolean isRepeat() {
    return repeat;
  }

  /**
   * @param repeat the repeat to set
   */
  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }

  /**
   * @return the newSeries
   */
  public boolean isNewSeries() {
    return newSeries;
  }

  /**
   * @param newSeries the newSeries to set
   */
  public void setNewSeries(boolean newSeries) {
    this.newSeries = newSeries;
  }

  /**
   * @return the premiere
   */
  public boolean isPremiere() {
    return premiere;
  }

  /**
   * @param premiere the premiere to set
   */
  public void setPremiere(boolean premiere) {
    this.premiere = premiere;
  }

  /**
   * @return the choice
   */
  public boolean isChoice() {
    return choice;
  }

  /**
   * @param choice the choice to set
   */
  public void setChoice(boolean choice) {
    this.choice = choice;
  }

  /**
   * @return the subtitles
   */
  public boolean isSubtitles() {
    return subtitles;
  }

  /**
   * @param subtitles the subtitles to set
   */
  public void setSubtitles(boolean subtitles) {
    this.subtitles = subtitles;
  }

  /**
   * @return the deafSigned
   */
  public boolean isDeafSigned() {
    return deafSigned;
  }

  /**
   * @param deafSigned the deafSigned to set
   */
  public void setDeafSigned(boolean deafSigned) {
    this.deafSigned = deafSigned;
  }

  /**
   * @return the starRating
   */
  public Integer getStarRating() {
    return starRating;
  }

  /**
   * @param starRating the starRating to set
   */
  public void setStarRating(Integer starRating) {
    this.starRating = starRating;
  }

  /**
   * @return the progDate
   */
  public Date getProgDate() {
    return progDate;
  }

  /**
   * @param progDate the progDate to set
   */
  public void setProgDate(Date progDate) {
    this.progDate = progDate;
  }

  /**
   * @return the startTime
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * @param startTime the startTime to set
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * @return the endTime
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * @param endTime the endTime to set
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * @return the duration
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(Integer duration) {
    this.duration = duration;
  }

}
