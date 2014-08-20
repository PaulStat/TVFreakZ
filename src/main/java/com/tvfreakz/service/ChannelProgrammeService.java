package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;

public interface ChannelProgrammeService {

  List<ChannelProgramme> findByDirectorAndProgDateBetweenOrderByProgDateAscStartTimeAsc(Director ridley, Date fromDate, Date toDate);

  List<ChannelProgramme> findByProgDateBetweenOrderByProgDateAscStartTimeAsc(Date fromDate, Date toDate);

}
