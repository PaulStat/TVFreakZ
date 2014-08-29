/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tvfreakz.model.entity.ChannelProgramme;

@Repository("channelProgrammeRepository")
public interface ChannelProgrammeRepository extends JpaRepository<ChannelProgramme, Long> {

  List<ChannelProgramme> findAllOrderByProgDateAscStartTimeAsc();

  @Query("Select new com.tvfreakz.model.ChannelProgramme() from ChannelProgramme cp, Programme p"
      + " where (cp.programme.programmeId = p.programmeId) AND (programme.director.directorId = :directorID) AND (cp.progDate BETWEEN :fromDate AND :toDate)"
      + " ORDER BY cp.progDate, cp.startTime")
  List<ChannelProgramme> findScheduledDirectorProgrammes(@Param("directorID") Long directorID, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

  @Query("Select new com.tvfreakz.model.ChannelProgramme() from ChannelProgramme cp"
      + " where cp.programme.programmeId in (select programmeId from Programme programme"
      + " inner join programme.performers performer"
      + " where performer.performerId in :performerID)"
      + " AND (cp.progDate BETWEEN :fromDate AND :toDate)"
      + " ORDER BY cp.progDate, cp.startTime")
  List<ChannelProgramme> findScheduledPerformerProgrammes(@Param("performerID") Long performerId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

  @Query("to do")
  List<ChannelProgramme> findScheduledChannelProgrammesForPeriod(Long channelID, Date from, Date to);

  ChannelProgramme findByChannelProgrammeId(Long channelProgrammeId);

  @Query("to do")
  List<ChannelProgramme> findScheduledProgrammeShowings(Long channelProgrammeId);

  @Query("to do")
  List<ChannelProgramme> findScheduledProgrammesForPeriod(Date from, Date to);
  
}
