package com.tvfreakz.model.dto;

import java.util.Set;

import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Programme;

public class EpisodeDTO {
  
  private Long episodeId;
  
  private ProgrammeDTO programme;
  
  private DirectorDTO director;
  
  private String subtitle;
  
  private String episode;
  
  private String description;
  
  private Set<PerformerDTO> performers;

  public String getDescription() {
    return description;
  }

  public DirectorDTO getDirector() {
    return director;
  }

  public String getEpisode() {
    return episode;
  }

  public Long getEpisodeId() {
    return episodeId;
  }

  public Set<PerformerDTO> getPerformers() {
    return performers;
  }

  public ProgrammeDTO getProgramme() {
    return programme;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDirector(DirectorDTO director) {
    this.director = director;
  }

  public void setEpisode(String episode) {
    this.episode = episode;
  }

  public void setEpisodeId(Long episodeId) {
    this.episodeId = episodeId;
  }

  public void setPerformers(Set<PerformerDTO> performers) {
    this.performers = performers;
  }

  public void setProgramme(ProgrammeDTO programme) {
    this.programme = programme;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }
  
  

}
