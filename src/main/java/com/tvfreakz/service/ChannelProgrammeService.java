/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import com.tvfreakz.exception.ChannelProgrammeNotFoundException;
import com.tvfreakz.model.entity.ChannelProgramme;

public interface ChannelProgrammeService {

  List<ChannelProgramme> findScheduledDirectorProgrammes(Long directorID, Date fromDate, Date toDate);

  List<ChannelProgramme> findScheduledProgrammes(Date fromDate, Date toDate);

  List<ChannelProgramme> findScheduledPerformerProgrammes(Long performerID, Date fromDate, Date toDate);

  List<ChannelProgramme> findScheduledChannelProgrammesForPeriod(Long channelID, String startDateTime, String endDateTime);

  ChannelProgramme findScheduledProgramme(Long channelProgrammeId) throws ChannelProgrammeNotFoundException;

  List<ChannelProgramme> findScheduledProgrammeShowings(Long channelProgrammeId) throws ChannelProgrammeNotFoundException;

}
