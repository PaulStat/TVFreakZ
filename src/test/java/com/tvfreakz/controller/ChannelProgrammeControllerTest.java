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

import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.service.ChannelProgrammeService;
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
  private WebApplicationContext webApplicationContext; 


  @Before
  public void setUp() {
    Mockito.reset(channelProgrammeServiceMock);
    Mockito.reset(directorServiceMock);
    Mockito.reset(performerServiceMock);

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
    .andExpect(jsonPath("$", hasSize(3)))
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

    mockMvc.perform(get("/api/directorshowings/{id}", 1L))
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

    mockMvc.perform(get("/api/directorshowings/{id}", 3L))
    .andExpect(status().isNotFound());

    verify(directorServiceMock, times(1)).findByDirectorId(3L);
    verifyNoMoreInteractions(directorServiceMock);
  }  

  @Test
  public void testFindScheduledDirectorProgrammesWhenNoScheduledProgrammes() throws Exception {
    ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[]{};

    when(channelProgrammeServiceMock.findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForDirector));

    mockMvc.perform(get("/api/directorshowings/{id}", 1L))
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
	  
    mockMvc.perform(get("/api/performershowings/{id}", 1L))
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
    
    mockMvc.perform(get("/api/performershowings/{id}", 3L))
    .andExpect(status().isNotFound());

    verify(performerServiceMock, times(1)).findByPerformerId(3L);
    verifyNoMoreInteractions(performerServiceMock);
  }

  @Test
  public void testFindScheduledPerformerProgrammesWhenNoScheduledProgrammes() throws Exception {
    ChannelProgramme[] channelProgrammeForPerformer = new ChannelProgramme[]{};
    
    when(channelProgrammeServiceMock.findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
        TestUtil.TWO_WEEKS)).thenReturn(Arrays.asList(channelProgrammeForPerformer));
      
    mockMvc.perform(get("/api/performershowings/{id}", 1L))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(0)));
        
    verify(channelProgrammeServiceMock, times(1)).findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
            TestUtil.TWO_WEEKS);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

}
