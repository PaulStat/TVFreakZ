package com.tvfreakz.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.repository.ChannelProgrammeRepository;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/testContext.xml"})
public class ChannelProgrammeServiceTest {
  
  private ChannelProgrammeService channelProgrammeService;
  
  private ChannelProgrammeRepository channelProgrammeRepositoryMock;
  
  ApplicationContext appContext = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config/testContext.xml");
  
  @Before
  public void setUp() {
	channelProgrammeRepositoryMock = appContext.getBean("channelProgrammeRepositoryMock",ChannelProgrammeRepository.class);
	channelProgrammeService = appContext.getBean("channelProgrammeService", ChannelProgrammeService.class);
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
