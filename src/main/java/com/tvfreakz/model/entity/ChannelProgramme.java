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
  
  public static class Builder {
    private Long channelProgrammeId;
    private Channel channel;
    private Programme programme;
    private Episode episode;
    private boolean repeat;
    private boolean newSeries;
    private boolean premiere;
    private boolean choice;
    private boolean subtitles;
    private boolean deafSigned;
    private Integer starRating;
    private Date progDate;
    private Date startTime;
    private Date endTime;
    private Integer duration;
    
    public ChannelProgramme build() {
      return new ChannelProgramme(channelProgrammeId, channel, programme, episode, repeat, newSeries,
          premiere, choice, subtitles, deafSigned, starRating, progDate, startTime, endTime, duration);
    }
    
    public Builder withChannel(Channel channel) {
      this.channel = channel;
      return this;
    }
    
    public Builder withChannelProgrammeId(Long channelProgrammeId) {
      this.channelProgrammeId = channelProgrammeId;
      return this;
    }
    
    public Builder withChoice(boolean choice) {
      this.choice = choice;
      return this;
    }
    
    public Builder withDeafSigned(boolean deafSigned) {
      this.deafSigned = deafSigned;
      return this;
    }
    
    public Builder withDuration(Integer duration) {
      this.duration = duration;
      return this;
    }
    
    public Builder withEndTime(Date endTime) {
      this.endTime = endTime;
      return this;
    }
    
    public Builder withEpisode(Episode episode) {
      this.episode = episode;
      return this;
    }
    
    public Builder withNewSeries(boolean newSeries) {
      this.newSeries = newSeries;
      return this;
    }
    
    public Builder withPremiere(boolean premiere) {
      this.premiere = premiere;
      return this;
    }
    
    public Builder withProgDate(Date progDate) {
      this.progDate = progDate;
      return this;
    }
    
    public Builder withProgramme(Programme programme) {
      this.programme = programme;
      return this;
    }
    
    public Builder withRepeat(boolean repeat) {
      this.repeat = repeat;
      return this;
    }
    
    public Builder withStarRating(Integer starRating) {
      this.starRating = starRating;
      return this;
    }
    
    public Builder withStartTime(Date startTime) {
      this.startTime = startTime;
      return this;
    }
    
    public Builder withSubtitles(boolean subtitles) {
      this.subtitles = subtitles;
      return this;
    }
  }
  
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
  
  public ChannelProgramme() {}
  
  public ChannelProgramme(Long channelProgrammeId, Channel channel, Programme programme, Episode episode, boolean repeat, boolean newSeries, boolean premiere, boolean choice, boolean subtitles, boolean deafSigned,
      Integer starRating, Date progDate, Date startTime, Date endTime, Integer duration) {
    this.channelProgrammeId = channelProgrammeId;
    this.channel = channel;
    this.programme = programme;
    this.episode = episode;
    this.repeat = repeat;
    this.newSeries = newSeries;
    this.premiere = premiere;
    this.choice = choice;
    this.subtitles = subtitles;
    this.deafSigned = deafSigned;
    this.starRating = starRating;
    this.progDate = progDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.duration = duration;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ChannelProgramme other = (ChannelProgramme) obj;
    if (channel == null) {
      if (other.channel != null)
        return false;
    } else if (!channel.equals(other.channel))
      return false;
    if (channelProgrammeId == null) {
      if (other.channelProgrammeId != null)
        return false;
    } else if (!channelProgrammeId.equals(other.channelProgrammeId))
      return false;
    if (choice != other.choice)
      return false;
    if (deafSigned != other.deafSigned)
      return false;
    if (duration == null) {
      if (other.duration != null)
        return false;
    } else if (!duration.equals(other.duration))
      return false;
    if (endTime == null) {
      if (other.endTime != null)
        return false;
    } else if (!endTime.equals(other.endTime))
      return false;
    if (episode == null) {
      if (other.episode != null)
        return false;
    } else if (!episode.equals(other.episode))
      return false;
    if (newSeries != other.newSeries)
      return false;
    if (premiere != other.premiere)
      return false;
    if (progDate == null) {
      if (other.progDate != null)
        return false;
    } else if (!progDate.equals(other.progDate))
      return false;
    if (programme == null) {
      if (other.programme != null)
        return false;
    } else if (!programme.equals(other.programme))
      return false;
    if (repeat != other.repeat)
      return false;
    if (starRating == null) {
      if (other.starRating != null)
        return false;
    } else if (!starRating.equals(other.starRating))
      return false;
    if (startTime == null) {
      if (other.startTime != null)
        return false;
    } else if (!startTime.equals(other.startTime))
      return false;
    if (subtitles != other.subtitles)
      return false;
    return true;
  }

  /**
   * @return the channel
   */
  public Channel getChannel() {
    return channel;
  }

  /**
   * @return the channelProgrammeId
   */
  public Long getChannelProgrammeId() {
    return channelProgrammeId;
  }

  /**
   * @return the duration
   */
  public Integer getDuration() {
    return duration;
  }

  /**
   * @return the endTime
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * @return the episode
   */
  public Episode getEpisode() {
    return episode;
  }

  /**
   * @return the progDate
   */
  public Date getProgDate() {
    return progDate;
  }

  /**
   * @return the programme
   */
  public Programme getProgramme() {
    return programme;
  }

  /**
   * @return the starRating
   */
  public Integer getStarRating() {
    return starRating;
  }

  /**
   * @return the startTime
   */
  public Date getStartTime() {
    return startTime;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((channel == null) ? 0 : channel.hashCode());
    result = prime * result + ((channelProgrammeId == null) ? 0 : channelProgrammeId.hashCode());
    result = prime * result + (choice ? 1231 : 1237);
    result = prime * result + (deafSigned ? 1231 : 1237);
    result = prime * result + ((duration == null) ? 0 : duration.hashCode());
    result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
    result = prime * result + ((episode == null) ? 0 : episode.hashCode());
    result = prime * result + (newSeries ? 1231 : 1237);
    result = prime * result + (premiere ? 1231 : 1237);
    result = prime * result + ((progDate == null) ? 0 : progDate.hashCode());
    result = prime * result + ((programme == null) ? 0 : programme.hashCode());
    result = prime * result + (repeat ? 1231 : 1237);
    result = prime * result + ((starRating == null) ? 0 : starRating.hashCode());
    result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
    result = prime * result + (subtitles ? 1231 : 1237);
    return result;
  }

  /**
   * @return the choice
   */
  public boolean isChoice() {
    return choice;
  }

  /**
   * @return the deafSigned
   */
  public boolean isDeafSigned() {
    return deafSigned;
  }

  /**
   * @return the newSeries
   */
  public boolean isNewSeries() {
    return newSeries;
  }

  /**
   * @return the premiere
   */
  public boolean isPremiere() {
    return premiere;
  }

  /**
   * @return the repeat
   */
  public boolean isRepeat() {
    return repeat;
  }

  /**
   * @return the subtitles
   */
  public boolean isSubtitles() {
    return subtitles;
  }

  /**
   * @param channel the channel to set
   */
  public void setChannel(Channel channel) {
    this.channel = channel;
  }

  /**
   * @param channelProgrammeId the channelProgrammeId to set
   */
  public void setChannelProgrammeId(Long channelProgrammeId) {
    this.channelProgrammeId = channelProgrammeId;
  }

  /**
   * @param choice the choice to set
   */
  public void setChoice(boolean choice) {
    this.choice = choice;
  }

  /**
   * @param deafSigned the deafSigned to set
   */
  public void setDeafSigned(boolean deafSigned) {
    this.deafSigned = deafSigned;
  }

  /**
   * @param duration the duration to set
   */
  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  /**
   * @param endTime the endTime to set
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * @param episode the episode to set
   */
  public void setEpisode(Episode episode) {
    this.episode = episode;
  }

  /**
   * @param newSeries the newSeries to set
   */
  public void setNewSeries(boolean newSeries) {
    this.newSeries = newSeries;
  }

  /**
   * @param premiere the premiere to set
   */
  public void setPremiere(boolean premiere) {
    this.premiere = premiere;
  }

  /**
   * @param progDate the progDate to set
   */
  public void setProgDate(Date progDate) {
    this.progDate = progDate;
  }

  /**
   * @param programme the programme to set
   */
  public void setProgramme(Programme programme) {
    this.programme = programme;
  }

  /**
   * @param repeat the repeat to set
   */
  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }

  /**
   * @param starRating the starRating to set
   */
  public void setStarRating(Integer starRating) {
    this.starRating = starRating;
  }

  /**
   * @param startTime the startTime to set
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  /**
   * @param subtitles the subtitles to set
   */
  public void setSubtitles(boolean subtitles) {
    this.subtitles = subtitles;
  }

}
