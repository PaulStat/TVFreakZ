/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tvfreakz.model.dto.ChannelDTO;
import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.service.ChannelService;

@Controller
public class ChannelController {
  
  private ChannelService channelService;
  
  @Autowired
  public void setChannelService(ChannelService channelService) {
    this.channelService = channelService;
  }
  
  @ResponseBody
  @RequestMapping(value = "/api/channels", method = RequestMethod.GET)
  public List<ChannelDTO> findAll() {
    List<Channel> channels = channelService.findAll();
    return createDTOList(channels);
  }

  private List<ChannelDTO> createDTOList(List<Channel> channels) {
    List<ChannelDTO> channelDTOs = new ArrayList<>();
    for(Channel channel: channels) {
      ChannelDTO dto = new ChannelDTO();
      dto.setChannelId(channel.getChannelId());
      dto.setChannelName(channel.getChannelName());
      channelDTOs.add(dto);
    }
    return channelDTOs;
  }

}
