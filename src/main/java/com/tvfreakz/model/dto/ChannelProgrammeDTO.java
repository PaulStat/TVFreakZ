package com.tvfreakz.model.dto;

import java.util.Date;

public class ChannelProgrammeDTO {
  
  private Long channelProgrammeId;
  
  private ChannelDTO channel;
  
  private ProgrammeDTO programme;
  
  private EpisodeDTO episode;
  
  private boolean repeat;
  
  private boolean newSeries;
  
  private boolean premiere;
  
  private boolean choice;
  
  private boolean subtitles;
  
  private boolean deafSigned;
  
  private Integer starRating;
  
  private Date startTime;
  
  private Date endTime;
  
  private Integer duration;

  public ChannelDTO getChannel() {
    return channel;
  }

  public Long getChannelProgrammeId() {
    return channelProgrammeId;
  }

  public Integer getDuration() {
    return duration;
  }

  public Date getEndTime() {
    return endTime;
  }

  public EpisodeDTO getEpisode() {
    return episode;
  }

  public ProgrammeDTO getProgramme() {
    return programme;
  }

  public Integer getStarRating() {
    return starRating;
  }

  public Date getStartTime() {
    return startTime;
  }

  public boolean isChoice() {
    return choice;
  }

  public boolean isDeafSigned() {
    return deafSigned;
  }

  public boolean isNewSeries() {
    return newSeries;
  }

  public boolean isPremiere() {
    return premiere;
  }

  public boolean isRepeat() {
    return repeat;
  }

  public boolean isSubtitles() {
    return subtitles;
  }

  public void setChannel(ChannelDTO channel) {
    this.channel = channel;
  }

  public void setChannelProgrammeId(Long channelProgrammeId) {
    this.channelProgrammeId = channelProgrammeId;
  }

  public void setChoice(boolean choice) {
    this.choice = choice;
  }

  public void setDeafSigned(boolean deafSigned) {
    this.deafSigned = deafSigned;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public void setEpisode(EpisodeDTO episode) {
    this.episode = episode;
  }

  public void setNewSeries(boolean newSeries) {
    this.newSeries = newSeries;
  }

  public void setPremiere(boolean premiere) {
    this.premiere = premiere;
  }

  public void setProgramme(ProgrammeDTO programme) {
    this.programme = programme;
  }

  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }

  public void setStarRating(Integer starRating) {
    this.starRating = starRating;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public void setSubtitles(boolean subtitles) {
    this.subtitles = subtitles;
  }  

}
