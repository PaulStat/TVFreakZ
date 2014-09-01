/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import java.util.List;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.model.entity.Channel;

public interface ChannelService {

  Channel findByChannelId(Long channelId) throws ChannelNotFoundException;

  List<Channel> findAll();

}
