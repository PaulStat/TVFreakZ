/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.repository.ChannelProgrammeRepository;
import com.tvfreakz.util.TestUtil;

public class ChannelProgrammeServiceTest {
  
  private ChannelProgrammeService channelProgrammeService;
  
  private ChannelProgrammeRepository channelProgrammeRepositoryMock;  
  
  @Before
  public void setUp() {
	channelProgrammeRepositoryMock = mock(ChannelProgrammeRepository.class);
	channelProgrammeService = new ChannelProgrammeServiceImpl(channelProgrammeRepositoryMock);
  }
  
  @Test
  public void testFindScheduledProgrammes() {    
    when(channelProgrammeRepositoryMock.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(TestUtil.CHANNEL_PROGRAMMES));
    
    List<ChannelProgramme> channelProgrammeList = channelProgrammeService.findScheduledProgrammes(TestUtil.TODAY, TestUtil.TWO_WEEKS);
    
    assertEquals("Channel Programme List is of the wrong size", 3, channelProgrammeList.size());
    assertEquals("Alien", channelProgrammeList.get(0).getProgramme().getProgTitle());
    assertEquals("Aliens", channelProgrammeList.get(1).getProgramme().getProgTitle());
    assertEquals("Blade Runner", channelProgrammeList.get(2).getProgramme().getProgTitle());    
  }
  
  @Test
  public void testFindScheduledDirectorProgrammesWhenDirectorIsFound() throws Exception {    
    ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[]{TestUtil.CHANNEL_PROGRAMMES[0], TestUtil.CHANNEL_PROGRAMMES[2]};
    
    when(channelProgrammeRepositoryMock.findScheduledDirectorProgrammes(1L, TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForDirector));
    
    List<ChannelProgramme> channelProgrammeList = channelProgrammeService.findScheduledDirectorProgrammes(1L, TestUtil.TODAY, TestUtil.TWO_WEEKS);
    
    assertEquals("Channel Programme List is of the wrong size", 2, channelProgrammeList.size());
    assertEquals("Alien", channelProgrammeList.get(0).getProgramme().getProgTitle());
    assertEquals("Blade Runner", channelProgrammeList.get(1).getProgramme().getProgTitle());
    verify(channelProgrammeRepositoryMock, times(1)).findScheduledDirectorProgrammes(1L, TestUtil.TODAY, TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeRepositoryMock);
  }
  
  @Test
  public void testFindScheduledDirectorProgrammesWhenNoScheduledProgrammes() {
    ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[]{};
    
    when(channelProgrammeRepositoryMock.findScheduledDirectorProgrammes(1L, TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForDirector));
    
    List<ChannelProgramme> channelProgrammeList = channelProgrammeService.findScheduledDirectorProgrammes(1L, TestUtil.TODAY, TestUtil.TWO_WEEKS);
    
    assertEquals("Channel Programme List is of the wrong size", 0, channelProgrammeList.size());
    verify(channelProgrammeRepositoryMock, times(1)).findScheduledDirectorProgrammes(1L, TestUtil.TODAY, TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeRepositoryMock);
  }

}
