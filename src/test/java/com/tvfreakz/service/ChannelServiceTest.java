/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.repository.ChannelRepository;

public class ChannelServiceTest {
  
  private ChannelService channelService;
  
  private ChannelRepository channelRepositoryMock;
  
  private Channel channel;
  
  @Before
  public void setUp() {
    channelRepositoryMock = mock(ChannelRepository.class);
    channelService = new ChannelServiceImpl(channelRepositoryMock);
    channel = new Channel();
    channel.setChannelId(1L);
    channel.setChannelName("BBC 1");
  }
  
  @Test
  public void testFindByChannelIdWhenChannelIsFound() throws Exception {
    when(channelRepositoryMock.findByChannelId(1L)).thenReturn(channel);
    
    Channel actual = channelService.findByChannelId(1L);
    
    assertEquals(channel, actual);
    
    verify(channelRepositoryMock, times(1)).findByChannelId(1L);
    verifyNoMoreInteractions(channelRepositoryMock);
  }
  
  @Test(expected = ChannelNotFoundException.class)
  public void testFindByChannelIdWhenChannelIsNotFound() throws Exception {
    when(channelRepositoryMock.findByChannelId(1L)).thenReturn(null);
    
    channelService.findByChannelId(1L);
    
    verify(channelRepositoryMock, times(1)).findByChannelId(1L);
    verifyNoMoreInteractions(channelRepositoryMock);
  }

}
