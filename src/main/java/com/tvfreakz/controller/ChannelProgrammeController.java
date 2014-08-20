package com.tvfreakz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvfreakz.model.dto.ChannelDTO;
import com.tvfreakz.model.dto.ChannelProgrammeDTO;
import com.tvfreakz.model.dto.DirectorDTO;
import com.tvfreakz.model.dto.EpisodeDTO;
import com.tvfreakz.model.dto.ProgrammeDTO;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.service.ChannelProgrammeService;

@Controller
public class ChannelProgrammeController {

  @Autowired
  private ChannelProgrammeService channelProgrammeService;

  @ResponseBody
  @RequestMapping(value = "/api/all", method = RequestMethod.GET)
  public List<ChannelProgrammeDTO> findByProgDateBetweenOrderByProgDateAscStartTimeAsc() {
    Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
    Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();
    List<ChannelProgramme> chanprogs = channelProgrammeService.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(today, twoWeeks);
    return createDTO(chanprogs);
  }

  @ResponseBody
  @RequestMapping(value = "/api/directorshowings/{id}", method = RequestMethod.GET)
  public List<ChannelProgrammeDTO> findByDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc(@PathVariable("id") Long id) {
    Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
    Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();
    List<ChannelProgramme> chanprogs = channelProgrammeService.findByDirectorAndProgDateBetweenOrderByProgDateAscStartTimeAsc(null, today, twoWeeks);
    return createDTO(chanprogs);
  }

  private List<ChannelProgrammeDTO> createDTO(List<ChannelProgramme> chanprogs) {
    List<ChannelProgrammeDTO> chanprogDTOS = new ArrayList<>();
    for(ChannelProgramme chanprog: chanprogs) {
      ChannelProgrammeDTO channelProgrammeDTO = new ChannelProgrammeDTO();

      ChannelDTO channelDTO = new ChannelDTO();
      channelDTO.setChannelId(chanprog.getChannel().getChannelId());
      channelDTO.setChannelName(chanprog.getChannel().getChannelName());

      EpisodeDTO episodeDTO = null; 
      if(chanprog.getEpisode() != null) {
        episodeDTO = new EpisodeDTO();
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setDirectorId(chanprog.getEpisode().getDirector().getDirectorId());
        directorDTO.setDirectorName(chanprog.getEpisode().getDirector().getDirectorName());
        episodeDTO.setEpisodeId(chanprog.getEpisode().getEpisodeId());
        episodeDTO.setDescription(chanprog.getEpisode().getDescription());
        episodeDTO.setDirector(directorDTO);
        episodeDTO.setEpisode(chanprog.getEpisode().getEpisode());
        episodeDTO.setPerformers(chanprog.getEpisode().getPerformers() == null ? null : chanprog.getEpisode().getPerformers());
      }

      ProgrammeDTO programmeDTO = new ProgrammeDTO();
      programmeDTO.setBlackAndWhite(chanprog.getProgramme().isBlackAndWhite());
      programmeDTO.setCertificate(chanprog.getProgramme().getCertificate());
      programmeDTO.setDescription(chanprog.getProgramme().getDescription());
      programmeDTO.setDirector(chanprog.getProgramme().getDirector());
      programmeDTO.setFilm(chanprog.getProgramme().isFilm());
      programmeDTO.setGenre(chanprog.getProgramme().getGenre());
      programmeDTO.setPerformers(chanprog.getProgramme().getPerformers() == null ? null : chanprog.getProgramme().getPerformers());
      programmeDTO.setProgrammeId(chanprog.getProgramme().getProgrammeId());
      programmeDTO.setProgTitle(chanprog.getProgramme().getProgTitle());
      programmeDTO.setWideScreen(chanprog.getProgramme().isWideScreen());
      programmeDTO.setYear(chanprog.getProgramme().getYear());

      channelProgrammeDTO.setChannel(channelDTO);
      channelProgrammeDTO.setChannelProgrammeId(chanprog.getChannelProgrammeId());
      channelProgrammeDTO.setChoice(chanprog.isChoice());
      channelProgrammeDTO.setDeafSigned(chanprog.isDeafSigned());
      channelProgrammeDTO.setDuration(chanprog.getDuration());
      channelProgrammeDTO.setEndTime(chanprog.getEndTime());
      channelProgrammeDTO.setEpisode(episodeDTO);
      channelProgrammeDTO.setNewSeries(chanprog.isNewSeries());
      channelProgrammeDTO.setPremiere(chanprog.isPremiere());
      channelProgrammeDTO.setProgDate(chanprog.getProgDate());
      channelProgrammeDTO.setProgramme(programmeDTO);
      channelProgrammeDTO.setRepeat(chanprog.isRepeat());
      channelProgrammeDTO.setStarRating(chanprog.getStarRating());
      channelProgrammeDTO.setStartTime(chanprog.getStartTime());
      channelProgrammeDTO.setSubtitles(chanprog.isSubtitles());
      chanprogDTOS.add(channelProgrammeDTO);
    }
    return chanprogDTOS;
  }

}
