package com.tvfreakz.controller;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Genre;
import com.tvfreakz.model.entity.Programme;
import com.tvfreakz.service.ChannelProgrammeService;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/testContext.xml", "file:src/main/webapp/WEB-INF/config//servlet-config.xml"})
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
  public void testFindAllProgrammesByDirectorWhenDirectorIsFound() throws Exception {
    Director ridley = new Director();
    ridley.setDirectorId(1L);
    ridley.setDirectorName("Ridley Scott");
    
    Genre genre = new Genre();
    genre.setGenreId(1L);
    genre.setGenreName("Scifi");
    
    Channel bbc1 = new Channel();
    bbc1.setChannelId(1L);
    bbc1.setChannelName("BBC 1");
    Programme prog1 = new Programme();
    prog1.setProgrammeId(1L);
    prog1.setBlackAndWhite(false);
    prog1.setCertificate("18");
    prog1.setDescription("Fast paced action thriller");
    prog1.setDirector(ridley);
    prog1.setFilm(true);
    prog1.setGenre(genre);
    prog1.setPerformers(null);
    prog1.setProgTitle("Aliens");
    prog1.setWideScreen(false);
    prog1.setYear(new LocalDate(1986,1,1).toDate());
    
    Programme prog2 = new Programme();
    prog2.setProgrammeId(2L);
    prog2.setBlackAndWhite(false);
    prog2.setCertificate("18");
    prog2.setDescription("Fast paced action thriller");
    prog2.setDirector(ridley);
    prog2.setFilm(true);
    prog2.setGenre(genre);
    prog2.setPerformers(null);
    prog2.setProgTitle("Alien");
    prog2.setWideScreen(false);
    prog2.setYear(new LocalDate(1979,1,1).toDate());
    
    Programme prog3 = new Programme();
    prog3.setProgrammeId(3L);
    prog3.setBlackAndWhite(false);
    prog3.setCertificate("18");
    prog3.setDescription("A blade runner must pursue and try to terminate four replicants who stole a ship in space and have returned to Earth to find their creator");
    prog3.setDirector(ridley);
    prog3.setFilm(true);
    prog3.setGenre(genre);
    prog3.setPerformers(null);
    prog3.setProgTitle("Blade Runner");
    prog3.setWideScreen(false);
    prog3.setYear(new LocalDate(1982,1,1).toDate());
    
    ChannelProgramme chanprog1 = new ChannelProgramme();
    chanprog1.setChannelProgrammeId(1L);
    chanprog1.setChannel(bbc1);
    chanprog1.setChoice(false);
    chanprog1.setDeafSigned(false);
    chanprog1.setDuration(60);
    chanprog1.setEndTime(new LocalTime(12,30,0).toDateTimeToday().toDate());
    chanprog1.setEpisode(null);
    chanprog1.setNewSeries(false);
    chanprog1.setPremiere(false);
    chanprog1.setProgDate(new DateTime().toDate());
    chanprog1.setRepeat(false);
    chanprog1.setStarRating(5);
    chanprog1.setStartTime(new LocalTime(11,30,0).toDateTimeToday().toDate());
    chanprog1.setSubtitles(false);
    chanprog1.setProgramme(prog1);
    
    ChannelProgramme chanprog2 = new ChannelProgramme();
    chanprog2.setChannelProgrammeId(2L);
    chanprog2.setChannel(bbc1);
    chanprog2.setChoice(false);
    chanprog2.setDeafSigned(false);
    chanprog2.setDuration(60);
    chanprog2.setEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate());
    chanprog2.setEpisode(null);
    chanprog2.setNewSeries(false);
    chanprog2.setPremiere(false);
    chanprog2.setProgDate(new DateTime().minusDays(1).toDate());
    chanprog2.setRepeat(false);
    chanprog2.setStarRating(5);
    chanprog2.setStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate());
    chanprog2.setSubtitles(false);
    chanprog2.setProgramme(prog2);
    
    ChannelProgramme chanprog3 = new ChannelProgramme();
    chanprog3.setChannelProgrammeId(3L);
    chanprog3.setChannel(bbc1);
    chanprog3.setChoice(false);
    chanprog3.setDeafSigned(false);
    chanprog3.setDuration(60);
    chanprog3.setEndTime(new LocalTime(23,30,0).toDateTimeToday().toDate());
    chanprog3.setEpisode(null);
    chanprog3.setNewSeries(false);
    chanprog3.setPremiere(false);
    chanprog3.setProgDate(new DateTime().plusDays(1).toDate());
    chanprog3.setRepeat(false);
    chanprog3.setStarRating(5);
    chanprog3.setStartTime(new LocalTime(22,30,0).toDateTimeToday().toDate());
    chanprog3.setSubtitles(false);
    chanprog3.setProgramme(prog3);
    
    when(channelProgrammeServiceMock.findByDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc(ridley, new DateTime().toDate(),
        new DateTime().plusWeeks(2).toDate())).thenReturn(Arrays.asList(new ChannelProgramme[]{chanprog2, chanprog1, chanprog3}));
    
    mockMvc.perform(get("/api/directorshowings/{id}", 1L))
           .andExpect(status().isOk())
           .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
           .andExpect(jsonPath("$", hasSize(2)))
           .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
           .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")))
           .andExpect(jsonPath("$[2].programme.progTitle", is("Blade Runner")));
    
    verify(channelProgrammeServiceMock, times(1)).findByDirectorAndProgDateBetweenOrderByProgDateStartTimeAsc(ridley, new DateTime().toDate(),
        new DateTime().plusWeeks(2).toDate());
    verifyNoMoreInteractions(channelProgrammeServiceMock);
  }

}
