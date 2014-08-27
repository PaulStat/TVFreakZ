/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.repository.ChannelProgrammeRepository;

@Service("channelProgrammeService")
public class ChannelProgrammeServiceImpl implements ChannelProgrammeService {

  private ChannelProgrammeRepository channelProgrammeRepository;

  private static final String DATE_TIME_FORMAT = "yyyyMMddHHmm";
  private static final DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_FORMAT);

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
  public List<ChannelProgramme> findScheduledProgrammes(Date fromDate, Date toDate) {    
    return channelProgrammeRepository.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(fromDate, toDate);
  }

  @Transactional(readOnly = true)
  @Override
  public List<ChannelProgramme> findScheduledPerformerProgrammes(Long performerID, Date fromDate, Date toDate) {
    return channelProgrammeRepository.findScheduledPerformerProgrammes(performerID, fromDate, toDate);
  }

  @Transactional(readOnly = true)
  @Override
  public List<ChannelProgramme> findScheduledChannelProgrammesForPeriod(Long channelID, String startDateTime, String endDateTime) {
    Date from = formatter.parseDateTime(startDateTime).toDate();
    Date to   = formatter.parseDateTime(endDateTime).toDate();
    return channelProgrammeRepository.findScheduledChannelProgrammesForPeriod(channelID, from, to);
  }

}
