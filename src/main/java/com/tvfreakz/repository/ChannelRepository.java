/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvfreakz.model.entity.Channel;

@Repository("channelRepository")
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Channel findByChannelId(Long channelId);

}
