package com.tvfreakz.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvfreakz.model.dto.ChannelDTO;
import com.tvfreakz.model.dto.ChannelProgrammeDTO;
import com.tvfreakz.model.dto.DirectorDTO;
import com.tvfreakz.model.dto.EpisodeDTO;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.service.ChannelProgrammeService;

@Controller
public class ChannelProgrammeController {
  
  private ChannelProgrammeService channelProgrammeService;
  
  @ResponseBody
  @RequestMapping(value = "/api/directorshowings/{id}", method = RequestMethod.GET)
  public List<ChannelProgrammeDTO> findByDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc(@PathVariable("id") Long id) {
    List<ChannelProgramme> chanprogs = channelProgrammeService.findByDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc(null, new DateTime().toDate(), new DateTime().plusWeeks(2).toDate());
    return createDTO(chanprogs);
  }

  private List<ChannelProgrammeDTO> createDTO(List<ChannelProgramme> chanprogs) {
    List<ChannelProgrammeDTO> chanprogDTOS = new ArrayList<ChannelProgrammeDTO>();
    for(ChannelProgramme chanprog: chanprogs) {
      ChannelProgrammeDTO channelProgrammeDTO = new ChannelProgrammeDTO();
      
      ChannelDTO channelDTO = new ChannelDTO();
      channelDTO.setChannelId(chanprog.getChannel().getChannelId());
      channelDTO.setChannelName(chanprog.getChannel().getChannelName());
      
      EpisodeDTO episodeDTO = new EpisodeDTO();
      DirectorDTO directorDTO = new DirectorDTO();
      directorDTO.setDirectorId(chanprog.getEpisode().getDirector().getDirectorId());
      directorDTO.setDirectorName(chanprog.getEpisode().getDirector().getDirectorName());
      episodeDTO.setEpisodeId(chanprog.getEpisode().getEpisodeId());
      episodeDTO.setDescription(chanprog.getEpisode().getDescription());
      episodeDTO.setDirector(directorDTO);
      episodeDTO.setEpisode(chanprog.getEpisode().getEpisode());
//      Set<PerformerDTO> perfomerDTO = new
      episodeDTO.setPerformers(chanprog.getEpisode().getPerformers());
      
      channelProgrammeDTO.setChannel(channelDTO);
      channelProgrammeDTO.setChannelProgrammeId(chanprog.getChannelProgrammeId());
      channelProgrammeDTO.setChoice(chanprog.isChoice());
      channelProgrammeDTO.setDeafSigned(chanprog.isDeafSigned());
      channelProgrammeDTO.setDuration(chanprog.getDuration());
      channelProgrammeDTO.setEndTime(chanprog.getEndTime());
      channelProgrammeDTO.setEpisode(chanprog.getEpisode());
      channelProgrammeDTO.setNewSeries(chanprog.isNewSeries());
      channelProgrammeDTO.setPremiere(chanprog.isPremiere());
      channelProgrammeDTO.setProgDate(chanprog.getProgDate());
      channelProgrammeDTO.setProgramme(chanprog.getProgramme());
      channelProgrammeDTO.setRepeat(chanprog.isRepeat());
      channelProgrammeDTO.setStarRating(chanprog.getStarRating());
      channelProgrammeDTO.setStartTime(chanprog.getStartTime());
      channelProgrammeDTO.setSubtitles(chanprog.isSubtitles());
      chanprogDTOS.add(channelProgrammeDTO);
    }
    return chanprogDTOS;
  }

}
