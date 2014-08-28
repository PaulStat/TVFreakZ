/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvfreakz.model.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

  Channel findByChannelId(Long channelId);

}
