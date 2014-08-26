/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.repository.ChannelProgrammeRepository;

@Service("channelProgrammeService")
public class ChannelProgrammeServiceImpl implements ChannelProgrammeService {
  
  private ChannelProgrammeRepository channelProgrammeRepository;
  
  @Autowired
  public void setChannelProgrammeRepository(ChannelProgrammeRepository channelProgrammeRepository) {
    this.channelProgrammeRepository = channelProgrammeRepository;
  }  

  @Override
  public List<ChannelProgramme> findScheduledDirectorProgrammes(Long directorID, Date fromDate, Date toDate) {
    List<ChannelProgramme> channelProgrammes = channelProgrammeRepository.findScheduledDirectorProgrammes(directorID, fromDate, toDate);
    return channelProgrammes;
  }

  @Override
  public List<ChannelProgramme> findScheduledProgrammes(Date fromDate, Date toDate) {    
    return channelProgrammeRepository.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(fromDate, toDate);
  }

}
