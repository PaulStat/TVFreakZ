package com.tvfreakz.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.repository.ChannelProgrammeRepository;

@Service("channelProgrammeService")
public class ChannelProgrammeServiceImpl implements ChannelProgrammeService {
  
  @Autowired
  private ChannelProgrammeRepository channelProgrammeRepository;

  @Override
  public List<ChannelProgramme> findByDirectorAndProgDateBetweenOrderByProgDateAscStartTimeAsc(Director ridley, Date fromDate, Date toDate) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ChannelProgramme> findByProgDateBetweenOrderByProgDateAscStartTimeAsc(Date fromDate, Date toDate) {    
    return channelProgrammeRepository.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(fromDate, toDate);
  }

}
