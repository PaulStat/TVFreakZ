/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvfreakz.exception.ChannelProgrammeNotFoundException;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.repository.ChannelProgrammeRepository;
import com.tvfreakz.util.Utils;

@Service("channelProgrammeService")
public class ChannelProgrammeServiceImpl implements ChannelProgrammeService {

  private ChannelProgrammeRepository channelProgrammeRepository;

  @Autowired
  public ChannelProgrammeServiceImpl(ChannelProgrammeRepository channelProgrammeRepository) {
    this.channelProgrammeRepository = channelProgrammeRepository;
  }  

  @Transactional(readOnly = true)
  @Override
  public List<ChannelProgramme> findScheduledDirectorProgrammes(Long directorID, Date fromDate, Date toDate) {    
    return channelProgrammeRepository.findScheduledDirectorProgrammes(directorID, fromDate, toDate);
  }

  @Transactional(readOnly = true)
  @Override
  public List<ChannelProgramme> findScheduledProgrammes() {
    return channelProgrammeRepository.findAllByOrderByProgDateAscStartTimeAsc();
  }

  @Transactional(readOnly = true)
  @Override
  public List<ChannelProgramme> findScheduledPerformerProgrammes(Long performerID, Date fromDate, Date toDate) {
    return channelProgrammeRepository.findScheduledPerformerProgrammes(performerID, fromDate, toDate);
  }

  @Transactional(readOnly = true)
  @Override
  public List<ChannelProgramme> findScheduledChannelProgrammesForPeriod(Long channelID, String startDateTime, String endDateTime) {    
    Date from = Utils.DATE_TIME_FORMATTER.parseDateTime(startDateTime).toDate();
    Date to   = Utils.DATE_TIME_FORMATTER.parseDateTime(endDateTime).toDate();
    return channelProgrammeRepository.findScheduledChannelProgrammesForPeriod(channelID, from, to);
  }

  @Override
  public ChannelProgramme findScheduledProgramme(Long channelProgrammeId) throws ChannelProgrammeNotFoundException {
    ChannelProgramme chanProg = channelProgrammeRepository.findByChannelProgrammeId(channelProgrammeId);
    if(chanProg == null) {
      throw new ChannelProgrammeNotFoundException("ChannelProgramme with an id of " +channelProgrammeId +" was not found");
    }
    return chanProg;
  }

  @Override
  public List<ChannelProgramme> findScheduledProgrammeShowings(Long channelProgrammeId) throws ChannelProgrammeNotFoundException {
    //First check to see if the Channel Programme exists
    findScheduledProgramme(channelProgrammeId);
    return channelProgrammeRepository.findScheduledProgrammeShowings(channelProgrammeId);
  }

  @Override
  public List<ChannelProgramme> findScheduledProgrammesForPeriod(String startDateTime, String endDateTime) {
    Date from = Utils.DATE_TIME_FORMATTER.parseDateTime(startDateTime).toDate();
    Date to   = Utils.DATE_TIME_FORMATTER.parseDateTime(endDateTime).toDate();
    return channelProgrammeRepository.findScheduledProgrammesForPeriod(from, to);
  }

}
