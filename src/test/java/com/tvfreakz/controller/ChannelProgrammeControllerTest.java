/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.service.ChannelProgrammeService;
import com.tvfreakz.service.ChannelService;
import com.tvfreakz.service.DirectorService;
import com.tvfreakz.service.PerformerService;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/testMVCContext.xml", "file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
@WebAppConfiguration
public class ChannelProgrammeControllerTest {

  private MockMvc mockMvc;  

  @Autowired
  private ChannelProgrammeService channelProgrammeServiceMock;

  @Autowired
  private DirectorService directorServiceMock;

  @Autowired
  private PerformerService performerServiceMock;
  
  @Autowired
  private ChannelService channelServiceMock;

  @Autowired
  private WebApplicationContext webApplicationContext; 


  @Before
  public void setUp() {
    Mockito.reset(channelProgrammeServiceMock);
    Mockito.reset(directorServiceMock);
    Mockito.reset(performerServiceMock);
    Mockito.reset(channelServiceMock);

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();    
  }

  @Test
  public void testFindScheduledProgrammes() throws Exception {    

    when(channelProgrammeServiceMock.findScheduledProgrammes(TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(TestUtil.CHANNEL_PROGRAMMES));

    mockMvc.perform(get("/api/all"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(TestUtil.CHANNEL_PROGRAMMES.length)))
    .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
    .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")))
    .andExpect(jsonPath("$[2].programme.progTitle", is("Blade Runner")));

    verify(channelProgrammeServiceMock, times(1)).findScheduledProgrammes(TestUtil.TODAY, TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindScheduledDirectorProgrammesWhenDirectorIsFound() throws Exception {    

    ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[]{TestUtil.CHANNEL_PROGRAMMES[0], TestUtil.CHANNEL_PROGRAMMES[2]};

    when(channelProgrammeServiceMock.findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForDirector));

    mockMvc.perform(get("/api/directorshowings/{directorId}", 1L))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(2)))
    .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))    
    .andExpect(jsonPath("$[1].programme.progTitle", is("Blade Runner")));

    verify(channelProgrammeServiceMock, times(1)).findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindScheduledDirectorProgrammesWhenDirectorIsNotFound() throws Exception {

    when(directorServiceMock.findByDirectorId(3L)).thenThrow(new DirectorNotFoundException());

    mockMvc.perform(get("/api/directorshowings/{directorId}", 3L))
    .andExpect(status().isNotFound());

    verify(directorServiceMock, times(1)).findByDirectorId(3L);
    verifyNoMoreInteractions(directorServiceMock);
  }  

  @Test
  public void testFindScheduledDirectorProgrammesWhenNoScheduledProgrammes() throws Exception {
    ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[]{};

    when(channelProgrammeServiceMock.findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForDirector));

    mockMvc.perform(get("/api/directorshowings/{directorId}", 1L))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(0)));

    verify(channelProgrammeServiceMock, times(1)).findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindScheduledPerformerProgrammesWhenPerformerIsFound() throws Exception {	  
    ChannelProgramme[] channelProgrammeForPerformer = new ChannelProgramme[]{TestUtil.CHANNEL_PROGRAMMES[0], TestUtil.CHANNEL_PROGRAMMES[1]};

    when(channelProgrammeServiceMock.findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForPerformer));

    mockMvc.perform(get("/api/performershowings/{performerId}", 1L))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(2)))
    .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))    
    .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")));

    verify(channelProgrammeServiceMock, times(1)).findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindScheduledPerformerProgrammesWhenPerformerIsNotFound() throws Exception {
    when(performerServiceMock.findByPerformerId(3L)).thenThrow(new PerformerNotFoundException());

    mockMvc.perform(get("/api/performershowings/{performerId}", 3L))
    .andExpect(status().isNotFound());

    verify(performerServiceMock, times(1)).findByPerformerId(3L);
    verifyNoMoreInteractions(performerServiceMock);
  }

  @Test
  public void testFindScheduledPerformerProgrammesWhenNoScheduledProgrammes() throws Exception {
    ChannelProgramme[] channelProgrammeForPerformer = new ChannelProgramme[]{};

    when(channelProgrammeServiceMock.findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForPerformer));

    mockMvc.perform(get("/api/performershowings/{performerId}", 1L))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(0)));

    verify(channelProgrammeServiceMock, times(1)).findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindScheduledChannelProgrammesForPeriodWhenChannelIsFound() throws Exception {
    ChannelProgramme[] channelProgrammeForBBC2 = new ChannelProgramme[]{TestUtil.CHANNEL_PROGRAMMES[3]};

    when(channelProgrammeServiceMock.findScheduledChannelProgrammesForPeriod(2L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT),
        new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT))).thenReturn(Arrays.asList(channelProgrammeForBBC2));

    mockMvc.perform(get("/api/channelshowings/{channelId}/{from}/{to}", 2L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT), new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT)))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(1)))
    .andExpect(jsonPath("$[0].programme.progTitle", is("Alien 3")));

    verify(channelProgrammeServiceMock, times(1)).findScheduledChannelProgrammesForPeriod(2L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT),
        new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT));
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindScheduledChannelProgrammesForPeriodWhenRequestPathHasInvalidDateTimeFormat() throws Exception {       
    mockMvc.perform(get("/api/channelshowings/{channelId}/{from}/{to}", 2L, "sss", "sss"))
    .andDo(print())
    .andExpect(status().isBadRequest());
  }

  @Test
  public void testFindScheduledChannelProgrammesForPeriodWhenChannelIsNotFound() throws Exception {
    when(channelServiceMock.findByChannelId(3L)).thenThrow(new ChannelNotFoundException());
    
    mockMvc.perform(get("/api/channelshowings/{channelId}/{from}/{to}", 3L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT), new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT)))
    .andDo(print())
    .andExpect(status().isNotFound());

    verify(channelServiceMock, times(1)).findByChannelId(3L);
    verifyNoMoreInteractions(channelServiceMock);
  }

  @Test
  public void testFindScheduledChannelProgrammesForPeriodWhenNoScheduledProgrammes() throws Exception {
    ChannelProgramme[] channelProgrammeForBBC2 = new ChannelProgramme[]{};

    when(channelProgrammeServiceMock.findScheduledChannelProgrammesForPeriod(2L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT),
        new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT))).thenReturn(Arrays.asList(channelProgrammeForBBC2));

    mockMvc.perform(get("/api/channelshowings/{channelId}/{from}/{to}", 2L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT), new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT)))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(0)));    

    verify(channelProgrammeServiceMock, times(1)).findScheduledChannelProgrammesForPeriod(2L, new DateTime(TestUtil.NOW).toString(TestUtil.DATE_TIME_FORMAT),
        new DateTime(TestUtil.TWO_HOURS).toString(TestUtil.DATE_TIME_FORMAT));
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

}
