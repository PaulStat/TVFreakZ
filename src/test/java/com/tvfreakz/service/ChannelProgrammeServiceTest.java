package com.tvfreakz.service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.repository.ChannelProgrammeRepository;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/testContext.xml", "file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
public class ChannelProgrammeServiceTest {
  
  private ChannelProgrammeService channelProgrammeService;
  
  @Autowired
  private ChannelProgrammeRepository channelProgrammeRepositoryMock;
  
  @Before
  public void setUp() {
    Mockito.reset(channelProgrammeRepositoryMock);
    
    channelProgrammeService = new ChannelProgrammeServiceImpl();
  }
  
  @Test
  public void testFindByProgDateBetweenOrderByProgDateAscStartTimeAsc() {
    Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
    Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();
    
    ChannelProgramme[] channelProgramme = TestUtil.createChannelProgrammeTestData();
    
    when(channelProgrammeRepositoryMock.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(today, twoWeeks)).thenReturn(Arrays.asList(channelProgramme));
    
    List<ChannelProgramme> channelProgrammeList = channelProgrammeService.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(today, twoWeeks);
    
    assertEquals("Channel Programme List is of the wrong size", 3, channelProgrammeList.size());
    assertEquals("Alien", channelProgrammeList.get(0).getProgramme().getProgTitle());
    assertEquals("Aliens", channelProgrammeList.get(1).getProgramme().getProgTitle());
    assertEquals("Blade Runner", channelProgrammeList.get(2).getProgramme().getProgTitle());
  }

}
