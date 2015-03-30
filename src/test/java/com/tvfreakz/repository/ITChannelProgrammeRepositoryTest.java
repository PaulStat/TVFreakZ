/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.tvfreakz.model.entity.ChannelProgramme;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/config/testMVCContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("testDataset.xml")
public class ITChannelProgrammeRepositoryTest {
    
    @Autowired
    private ChannelProgrammeRepository repository;
    
    @Test
    public void testFindAllByOrderByStartTimeAsc() {
        List<ChannelProgramme> channelProgrammes = repository.findAllByOrderByStartTimeAsc();
        final int expectedListSize = 14;
        assertEquals(expectedListSize, channelProgrammes.size());
        assertEquals((Long) 1L, channelProgrammes.get(0).getChannelProgrammeId());
        assertEquals((Long) 3L, channelProgrammes.get(1).getChannelProgrammeId());
        assertEquals((Long) 4L, channelProgrammes.get(2).getChannelProgrammeId());
        assertEquals((Long) 5L, channelProgrammes.get(3).getChannelProgrammeId());
        assertEquals((Long) 6L, channelProgrammes.get(4).getChannelProgrammeId());
        assertEquals((Long) 2L, channelProgrammes.get(5).getChannelProgrammeId());
        assertEquals((Long) 7L, channelProgrammes.get(6).getChannelProgrammeId());
        assertEquals((Long) 8L, channelProgrammes.get(7).getChannelProgrammeId());
        assertEquals((Long) 9L, channelProgrammes.get(8).getChannelProgrammeId());
        assertEquals((Long) 10L, channelProgrammes.get(9).getChannelProgrammeId());
        assertEquals((Long) 11L, channelProgrammes.get(10).getChannelProgrammeId());
        assertEquals((Long) 12L, channelProgrammes.get(11).getChannelProgrammeId());
        assertEquals((Long) 13L, channelProgrammes.get(12).getChannelProgrammeId());
        assertEquals((Long) 14L, channelProgrammes.get(13).getChannelProgrammeId());
    }
    
    @Test
    public void testFindByChannelProgrammeIdWhenChannelProgrammeIsFound() {
        ChannelProgramme cp = repository.findByChannelProgrammeId(1L);
        assertEquals("Alien", cp.getProgramme().getProgTitle());
    }
    
    @Test
    public void testFindByChannelProgrammeIdWhenChannelProgrammeIsNotFound() {
        ChannelProgramme cp = repository.findByChannelProgrammeId(15L);
        assertNull(cp);
    }
    
    @Test
    public void testFindScheduledChannelProgrammesForPeriodWhenProgrammesFound() {
        final long channel5 = 134L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledChannelProgrammesForPeriod(channel5,
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2015)
                .withHourOfDay(9).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2015)
                .withHourOfDay(13).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate());
        final int expectedListSize = 4;
        assertEquals(expectedListSize, channelProgrammes.size());
    }
    
    @Test
    public void testFindScheduledChannelProgrammesForPeriodWhenProgrammesNotFound() {
        final long channel5 = 134L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledChannelProgrammesForPeriod(channel5,
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2017)
                .withHourOfDay(9).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2017)
                .withHourOfDay(13).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate());
        assertTrue(channelProgrammes.isEmpty());
    }
    
    @Test
    public void testFindScheduledDirectorProgrammesWhenProgrammesFound() {
        final long ridleyScott = 1L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledDirectorProgrammes(ridleyScott,
                new DateTime().withMonthOfYear(1).withDayOfMonth(19).withYear(2015)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2015)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(0).withMillisOfSecond(0).toDate());
        final int expectedListSize = 4;
        assertEquals(expectedListSize, channelProgrammes.size());
    }
    
    @Test
    public void testFindScheduledDirectorProgrammesWhenProgrammesNotFound() {
        final long ridleyScott = 1L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledDirectorProgrammes(ridleyScott,
                new DateTime().withMonthOfYear(1).withDayOfMonth(19).withYear(2017)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2017)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(0).withMillisOfSecond(0).toDate());
        assertTrue(channelProgrammes.isEmpty());
    }
    
    @Test
    public void testFindScheduledPerformerProgrammesWhenProgrammesFound() {
        final long sigourneyWeaver = 1L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledPerformerProgrammes(sigourneyWeaver,
                new DateTime().withMonthOfYear(1).withDayOfMonth(19).withYear(2015)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2015)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(0).withMillisOfSecond(0).toDate());
        final int expectedListSize = 5;
        assertEquals(expectedListSize, channelProgrammes.size());
    }
    
    @Test
    public void testFindScheduledPerformerProgrammesWhenProgrammesNotFound() {
        final long sigourneyWeaver = 1L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledPerformerProgrammes(sigourneyWeaver,
                new DateTime().withMonthOfYear(1).withDayOfMonth(19).withYear(2017)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2017)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(0).withMillisOfSecond(0).toDate());
        assertTrue(channelProgrammes.isEmpty());
    }
    
    @Test
    public void testFindScheduledProgrammesForPeriodWhenProgrammesFound() {
        List<ChannelProgramme> channelProgrammes = repository.findScheduledProgrammesForPeriod(
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2015)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2015)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(0).toDate());
        final int expectedListSize = 7;
        assertEquals(expectedListSize, channelProgrammes.size());
    }
    
    @Test
    public void testFindScheduledProgrammesForPeriodWhenProgrammesNotFound() {
        List<ChannelProgramme> channelProgrammes = repository.findScheduledProgrammesForPeriod(
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2017)
                .withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate(),
                new DateTime().withMonthOfYear(1).withDayOfMonth(31).withYear(2017)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(0).toDate());
        assertTrue(channelProgrammes.isEmpty());
    }
    
    @Test
    public void testFindSheduledProgrammeShowingsWhenShowingsFound() {
        final long heroes = 8L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledProgrammeShowings(heroes);
        final int expectedListSize = 6;
        assertEquals(expectedListSize, channelProgrammes.size());
    }
    
    @Test
    public void testFindSheduledProgrammeShowingsWhenShowingsNotFound() {
        final long fakeProgramme = 15L;
        List<ChannelProgramme> channelProgrammes = repository.findScheduledProgrammeShowings(fakeProgramme);
        assertTrue(channelProgrammes.isEmpty());
    }

}
