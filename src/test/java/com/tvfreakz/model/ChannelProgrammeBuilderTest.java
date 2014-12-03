/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Channel;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.model.entity.Episode;
import com.tvfreakz.model.entity.Programme;
import com.tvfreakz.util.TestUtil;

public class ChannelProgrammeBuilderTest {
  
  private ChannelProgramme expected;
  private Long channelProgrammeId = 1L;
  private Channel channel = new Channel.Builder().withChannelId(1L).withChannelName("BBC1").build();
  private Programme programme = new Programme.Builder().withProgrammeId(1L).withProgTitle("Aliens").build();
  private Episode episode;
  private boolean repeat = true;
  private boolean newSeries = false;
  private boolean premiere = false;
  private boolean choice = true;
  private boolean subtitles = false;
  private boolean deafSigned = false;
  private Integer starRating = 5;
  private Date progDate = TestUtil.TODAY;
  private Date startTime = TestUtil.NOW;
  private Date endTime = TestUtil.TWO_HOURS;
  private Integer duration = 120;
  
  @Before
  public void setup() {
    expected = new ChannelProgramme(channelProgrammeId, channel, programme, episode, repeat, newSeries, premiere, choice, subtitles,
        deafSigned, starRating, progDate, startTime, endTime, duration);
  }
  
  
  @Test
  public void testChannelProgrammeBuilderBuildObjectsShouldBeEqual() {
    ChannelProgramme actual = new ChannelProgramme.Builder().withChannelProgrammeId(channelProgrammeId).withChannel(channel).withProgramme(programme)
        .withEpisode(episode).withRepeat(repeat).withNewSeries(newSeries).withPremiere(premiere).withChoice(choice).withSubtitles(subtitles)
        .withDeafSigned(deafSigned).withStarRating(starRating).withProgDate(progDate).withStartTime(startTime).withEndTime(endTime)
        .withDuration(duration).build();
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testChannelProgrammeBuilderBuildObjectsShouldNotBeEqual() {
    ChannelProgramme actual = new ChannelProgramme.Builder().withChannelProgrammeId(1L).build();
    
    assertNotEquals(expected, actual);
  }
  
}
