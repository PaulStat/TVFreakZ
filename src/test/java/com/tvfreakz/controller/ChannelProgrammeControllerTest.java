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
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
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

import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.service.ChannelProgrammeService;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/testMVCContext.xml", "file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
@WebAppConfiguration
public class ChannelProgrammeControllerTest {

  private MockMvc mockMvc;
  
  @Autowired
  private ChannelProgrammeService channelProgrammeServiceMock;  

  @Autowired
  private WebApplicationContext webApplicationContext; 
  

  @Before
  public void setUp() {
    Mockito.reset(channelProgrammeServiceMock);

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();    
  }

  @Test
  public void testFindAllProgrammes() throws Exception {
    ChannelProgramme[] channelProgrammes = TestUtil.createChannelProgrammeTestData();
    
    Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
    Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();

    when(channelProgrammeServiceMock.findByProgDateBetweenOrderByProgDateAscStartTimeAsc(today,
        twoWeeks)).thenReturn(Arrays.asList(channelProgrammes));

    mockMvc.perform(get("/api/all"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(3)))
    .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
    .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")))
    .andExpect(jsonPath("$[2].programme.progTitle", is("Blade Runner")));

    verify(channelProgrammeServiceMock, times(1)).findByProgDateBetweenOrderByProgDateAscStartTimeAsc(today, twoWeeks);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

  @Test
  public void testFindAllProgrammesByDirectorWhenDirectorIsFound() throws Exception {

    ChannelProgramme[] channelProgrammesAll = TestUtil.createChannelProgrammeTestData();
    Director ridley = channelProgrammesAll[1].getProgramme().getDirector();
    
    ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[]{channelProgrammesAll[0], channelProgrammesAll[2]};
    
    Date today = new LocalDate().toDateTimeAtStartOfDay().toDate();
    Date twoWeeks = new LocalDate().toDateTimeAtStartOfDay().plusWeeks(2).toDate();

    when(channelProgrammeServiceMock.findScheduledDirectorProgrammes(1L, today,
        twoWeeks)).thenReturn(Arrays.asList(channelProgrammeForDirector));

    mockMvc.perform(get("/api/directorshowings/{id}", 1L))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(2)))
    .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))    
    .andExpect(jsonPath("$[1].programme.progTitle", is("Blade Runner")));

    verify(channelProgrammeServiceMock, times(1)).findScheduledDirectorProgrammes(1L, today,
        twoWeeks);
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }
  
  @Ignore
  @Test
  public void testFindAllProgrammesByDirectorWhenDirectorIsNotFound() throws Exception {
    //TODO
  }


}
