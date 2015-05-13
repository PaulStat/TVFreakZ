/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvfreakz.model.entity.ChannelProgramme;

@Repository("channelProgrammeRepository")
public interface ChannelProgrammeRepository extends JpaRepository<ChannelProgramme, Long> {

  @Query("Select cp from ChannelProgramme cp")
  List<ChannelProgramme> filterChannelProgrammes(String progName, String[] channelIdList, String[] genreIdList, DateTime fromDateTime, DateTime toDateTime, boolean subtitled, boolean signed, boolean film);

  List<ChannelProgramme> findAllByOrderByProgDateAscStartTimeAsc();

  ChannelProgramme findByChannelProgrammeId(Long channelProgrammeId);

  @Query("Select cp from ChannelProgramme cp" 
      +" where (cp.channel.channelId = :channelID) AND (cp.progDate BETWEEN :fromDate AND :toDate)"
      +" ORDER BY cp.progDate, cp.startTime")
  List<ChannelProgramme> findScheduledChannelProgrammesForPeriod(@Param("channelID") Long channelID, @Param("fromDate") Date from, @Param("toDate") Date to);

  @Query("Select cp from ChannelProgramme cp, Programme p"
      + " where (cp.programme.programmeId = p.programmeId) AND (cp.programme.director.directorId = :directorID) AND (cp.progDate BETWEEN :fromDate AND :toDate)"
      + " ORDER BY cp.progDate, cp.startTime")
  List<ChannelProgramme> findScheduledDirectorProgrammes(@Param("directorID") Long directorID, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

  @Query("Select cp from ChannelProgramme cp"
          + " where cp.programme.programmeId in (select programme.programmeId from ProgEpPerfAssociation programme"
          + " inner join programme.performer performer"
          + " where performer.performerId in :performerID)"
          + " AND (cp.progDate BETWEEN :fromDate AND :toDate)"
          + " ORDER BY cp.progDate, cp.startTime")
  List<ChannelProgramme> findScheduledPerformerProgrammes(@Param("performerID") Long performerId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

  @Query("Select cp from ChannelProgramme cp"
      +" where cp.progDate BETWEEN :fromDate AND :toDate")
  List<ChannelProgramme> findScheduledProgrammesForPeriod(@Param("fromDate") Date from, @Param("toDate") Date to);

  @Query("Select cp from ChannelProgramme cp"
      +" where cp.programme.programmeId = :programmeID")
  List<ChannelProgramme> findScheduledProgrammeShowings(@Param("programmeID") Long programmeId);
  
}
