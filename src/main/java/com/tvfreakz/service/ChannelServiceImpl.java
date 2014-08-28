/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.repository.ChannelRepository;

public class ChannelServiceImpl implements ChannelService {
  
  private ChannelRepository channelRepository;
  
  @Autowired
  public ChannelServiceImpl(ChannelRepository channelRepository) {
    this.channelRepository = channelRepository;
  }

  @Override
  public Channel findByChannelId(Long channelId) throws ChannelNotFoundException {
    Channel channel = channelRepository.findByChannelId(channelId);
    if(channel == null) {
      throw new ChannelNotFoundException("Channel with an id of " +channelId +" was not found");
    }
    return channel;
  }

}
