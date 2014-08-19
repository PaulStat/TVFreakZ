package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;

public interface ChannelProgrammeService {

  List<ChannelProgramme> findByDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc(Director ridley, Date fromDate, Date toDate);

}
