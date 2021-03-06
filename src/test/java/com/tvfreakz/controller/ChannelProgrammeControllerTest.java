/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.controller;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tvfreakz.exception.ChannelNotFoundException;
import com.tvfreakz.exception.ChannelProgrammeNotFoundException;
import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.dto.ProgrammeSearchDTO;
import com.tvfreakz.model.entity.ChannelProgramme;
import com.tvfreakz.service.ChannelProgrammeService;
import com.tvfreakz.service.ChannelService;
import com.tvfreakz.service.DirectorService;
import com.tvfreakz.service.PerformerService;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/testMVCContext.xml",
        "file:src/main/webapp/WEB-INF/config/servlet-config.xml" })
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

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testFilterChannelProgrammesWhenChannelListAndGenreListAreEmptyAndProgNameIsTooLong()
            throws Exception {
        String progName = TestUtil.createStringWithLength(301);

        mockMvc.perform(
                post("/api/filter")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("progName", progName)
                        .sessionAttr("filter", new ProgrammeSearchDTO()))
                .andExpect(status().isOk())
                .andExpect(view().name("api/filter"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/api/filter.jsp"))
                .andExpect(
                        model().attributeHasFieldErrors("filter", "progName"))
                .andExpect(
                        model().attributeHasFieldErrors("filter", "genreIdList"))
                .andExpect(
                        model().attributeHasFieldErrors("filter",
                                "channelIdList"))
                .andExpect(
                        model().attributeHasFieldErrors("filter",
                                "fromDateTime"))
                .andExpect(
                        model().attributeHasFieldErrors("filter", "toDateTime"))
                .andExpect(
                        model().attribute("filter",
                                hasProperty("progName", is(progName))))
                .andExpect(
                        model().attribute("filter",
                                hasProperty("subtitled", is(false))))
                .andExpect(
                        model().attribute("filter",
                                hasProperty("signed", is(false))))
                .andExpect(
                        model().attribute("filter",
                                hasProperty("film", is(false))));

        verifyZeroInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFilterChannelProgrammesShouldReturnRedirectionToResults()
            throws Exception {
        String progName = "Aliens";
        String[] channelIdList = new String[] { "1" };
        String[] genreIdList = new String[] { "1" };

        mockMvc.perform(
                post("/api/filter")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("progName", progName)
                        .param("channelIdList", channelIdList)
                        .param("genreIdList", genreIdList)
                        .param("toDateTime", "24/11/2014")
                        .param("fromDateTime", "24/11/2014")
                        .param("film", "true")
                        .sessionAttr("filter", new ProgrammeSearchDTO()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/filterResults"))
                .andExpect(redirectedUrl("/filterResults"));
    }

    @Test
    public void testFilterChannelProgrammesFilterRedirectionResults()
            throws Exception {
        ChannelProgramme[] channelProgrammeShowings = new ChannelProgramme[] { TestUtil.CHANNEL_PROGRAMMES[1] };

        when(
                channelProgrammeServiceMock
                        .filterChannelProgrammes(any(ProgrammeSearchDTO.class)))
                .thenReturn(Arrays.asList(channelProgrammeShowings));

        String progName = "Aliens";
        String[] channelIdList = new String[] { "1" };
        String[] genreIdList = new String[] { "1" };

        mockMvc.perform(
                get("/filterResults").param("progName", progName)
                        .param("channelIdList", channelIdList)
                        .param("genreIdList", genreIdList)
                        .param("toDateTime", "24/11/2014")
                        .param("fromDateTime", "24/11/2014")
                        .param("film", "true")
                        .sessionAttr("filter", new ProgrammeSearchDTO()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(
                        jsonPath("$", hasSize(channelProgrammeShowings.length)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Aliens")));

        verify(channelProgrammeServiceMock, times(1)).filterChannelProgrammes(
                any(ProgrammeSearchDTO.class));
        verifyNoMoreInteractions(channelProgrammeServiceMock);

    }

    @Test
    public void testFindScheduledProgrammes() throws Exception {
        when(channelProgrammeServiceMock.findScheduledProgrammes()).thenReturn(
                Arrays.asList(TestUtil.CHANNEL_PROGRAMMES));

        mockMvc.perform(get("/api/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(
                        jsonPath("$",
                                hasSize(TestUtil.CHANNEL_PROGRAMMES.length)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
                .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")))
                .andExpect(
                        jsonPath("$[2].programme.progTitle", is("Blade Runner")));

        verify(channelProgrammeServiceMock, times(1)).findScheduledProgrammes();
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledProgrammesForPeriod() throws Exception {
        String from = new DateTime(TestUtil.NOW)
                .toString(TestUtil.DATE_TIME_FORMAT);
        String to = new DateTime(TestUtil.TWO_HOURS)
                .toString(TestUtil.DATE_TIME_FORMAT);

        when(
                channelProgrammeServiceMock.findScheduledProgrammesForPeriod(
                        from, to)).thenReturn(
                Arrays.asList(TestUtil.CHANNEL_PROGRAMMES));

        mockMvc.perform(get("/api/all/{from}/{to}", from, to))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(
                        jsonPath("$",
                                hasSize(TestUtil.CHANNEL_PROGRAMMES.length)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
                .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")))
                .andExpect(
                        jsonPath("$[2].programme.progTitle", is("Blade Runner")));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledProgrammesForPeriod(from, to);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledProgrammesForPeriodWhenRequestPathHasInvalidDateFormat()
            throws Exception {
        mockMvc.perform(get("/api/all/{from}/{to}", "sss", "sss"))
                .andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void testFindScheduledProgrammeWhenProgrammeIsFound()
            throws Exception {
        ChannelProgramme chanProg = TestUtil.CHANNEL_PROGRAMMES[0];

        when(channelProgrammeServiceMock.findScheduledProgramme(2L))
                .thenReturn(chanProg);

        mockMvc.perform(get("/api/programme/{channelProgrammeId}", 2L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$programme.progTitle", is("Alien")));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledProgramme(2L);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledProgrammeWhenProgrammeIsNotFound()
            throws Exception {
        when(channelProgrammeServiceMock.findScheduledProgramme(5L)).thenThrow(
                new ChannelProgrammeNotFoundException());

        mockMvc.perform(get("/api/programme/{channelProgrammeId}", 5L))
                .andDo(print()).andExpect(status().isNotFound());

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledProgramme(5L);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledProgrammeShowingsWhenProgrammeIsFound()
            throws Exception {
        ChannelProgramme[] channelProgrammeShowings = new ChannelProgramme[] {
                TestUtil.CHANNEL_PROGRAMMES[4], TestUtil.CHANNEL_PROGRAMMES[5] };

        when(channelProgrammeServiceMock.findScheduledProgrammeShowings(4L))
                .thenReturn(Arrays.asList(channelProgrammeShowings));

        mockMvc.perform(get("/api/programmeshowings/{channelProgrammeId}", 4L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Alien 3")))
                .andExpect(jsonPath("$[1].programme.progTitle", is("Alien 3")));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledProgrammeShowings(4L);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledProgrammeShowingsWhenProgrammeIsNotFound()
            throws Exception {
        when(channelProgrammeServiceMock.findScheduledProgrammeShowings(7L))
                .thenThrow(new ChannelProgrammeNotFoundException());

        mockMvc.perform(get("/api/programmeshowings/{channelProgrammeId}", 7L))
                .andDo(print()).andExpect(status().isNotFound());

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledProgrammeShowings(7L);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledProgrammeShowingsWhenNoMoreScheduledProgrammes()
            throws Exception {
        ChannelProgramme[] channelProgrammeShowings = new ChannelProgramme[] {};

        when(channelProgrammeServiceMock.findScheduledProgrammeShowings(4L))
                .thenReturn(Arrays.asList(channelProgrammeShowings));

        mockMvc.perform(get("/api/programmeshowings/{channelProgrammeId}", 4L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledProgrammeShowings(4L);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledDirectorProgrammesWhenDirectorIsFound()
            throws Exception {

        ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[] {
                TestUtil.CHANNEL_PROGRAMMES[0], TestUtil.CHANNEL_PROGRAMMES[2] };

        when(
                channelProgrammeServiceMock.findScheduledDirectorProgrammes(1L,
                        TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(
                Arrays.asList(channelProgrammeForDirector));

        mockMvc.perform(get("/api/directorshowings/{directorId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
                .andExpect(
                        jsonPath("$[1].programme.progTitle", is("Blade Runner")));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
                        TestUtil.TWO_WEEKS);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledDirectorProgrammesWhenDirectorIsNotFound()
            throws Exception {

        when(directorServiceMock.findByDirectorId(3L)).thenThrow(
                new DirectorNotFoundException());

        mockMvc.perform(get("/api/directorshowings/{directorId}", 3L))
                .andExpect(status().isNotFound());

        verify(directorServiceMock, times(1)).findByDirectorId(3L);
        verifyNoMoreInteractions(directorServiceMock);
    }

    @Test
    public void testFindScheduledDirectorProgrammesWhenNoScheduledProgrammes()
            throws Exception {
        ChannelProgramme[] channelProgrammeForDirector = new ChannelProgramme[] {};

        when(
                channelProgrammeServiceMock.findScheduledDirectorProgrammes(1L,
                        TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(
                Arrays.asList(channelProgrammeForDirector));

        mockMvc.perform(get("/api/directorshowings/{directorId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledDirectorProgrammes(1L, TestUtil.TODAY,
                        TestUtil.TWO_WEEKS);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledPerformerProgrammesWhenPerformerIsFound()
            throws Exception {
        ChannelProgramme[] channelProgrammeForPerformer = new ChannelProgramme[] {
                TestUtil.CHANNEL_PROGRAMMES[0], TestUtil.CHANNEL_PROGRAMMES[1] };

        when(
                channelProgrammeServiceMock.findScheduledPerformerProgrammes(
                        1L, TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(
                Arrays.asList(channelProgrammeForPerformer));

        mockMvc.perform(get("/api/performershowings/{performerId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Alien")))
                .andExpect(jsonPath("$[1].programme.progTitle", is("Aliens")));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
                        TestUtil.TWO_WEEKS);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledPerformerProgrammesWhenPerformerIsNotFound()
            throws Exception {
        when(performerServiceMock.findByPerformerId(3L)).thenThrow(
                new PerformerNotFoundException());

        mockMvc.perform(get("/api/performershowings/{performerId}", 3L))
                .andExpect(status().isNotFound());

        verify(performerServiceMock, times(1)).findByPerformerId(3L);
        verifyNoMoreInteractions(performerServiceMock);
    }

    @Test
    public void testFindScheduledPerformerProgrammesWhenNoScheduledProgrammes()
            throws Exception {
        ChannelProgramme[] channelProgrammeForPerformer = new ChannelProgramme[] {};

        when(
                channelProgrammeServiceMock.findScheduledPerformerProgrammes(
                        1L, TestUtil.TODAY, TestUtil.TWO_WEEKS)).thenReturn(
                Arrays.asList(channelProgrammeForPerformer));

        mockMvc.perform(get("/api/performershowings/{performerId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledPerformerProgrammes(1L, TestUtil.TODAY,
                        TestUtil.TWO_WEEKS);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledChannelProgrammesForPeriodWhenChannelIsFound()
            throws Exception {
        String from = new DateTime(TestUtil.NOW)
                .toString(TestUtil.DATE_TIME_FORMAT);
        String to = new DateTime(TestUtil.TWO_HOURS)
                .toString(TestUtil.DATE_TIME_FORMAT);
        ChannelProgramme[] channelProgrammeForBBC2 = new ChannelProgramme[] { TestUtil.CHANNEL_PROGRAMMES[3] };

        when(
                channelProgrammeServiceMock
                        .findScheduledChannelProgrammesForPeriod(2L, from, to))
                .thenReturn(Arrays.asList(channelProgrammeForBBC2));

        mockMvc.perform(
                get("/api/channelshowings/{channelId}/{from}/{to}", 2L, from,
                        to))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].programme.progTitle", is("Alien 3")));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledChannelProgrammesForPeriod(2L, from, to);
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

    @Test
    public void testFindScheduledChannelProgrammesForPeriodWhenRequestPathHasInvalidDateTimeFormat()
            throws Exception {
        mockMvc.perform(
                get("/api/channelshowings/{channelId}/{from}/{to}", 2L, "sss",
                        "sss")).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindScheduledChannelProgrammesForPeriodWhenChannelIsNotFound()
            throws Exception {
        when(channelServiceMock.findByChannelId(3L)).thenThrow(
                new ChannelNotFoundException());

        mockMvc.perform(
                get("/api/channelshowings/{channelId}/{from}/{to}", 3L,
                        new DateTime(TestUtil.NOW)
                                .toString(TestUtil.DATE_TIME_FORMAT),
                        new DateTime(TestUtil.TWO_HOURS)
                                .toString(TestUtil.DATE_TIME_FORMAT)))
                .andDo(print()).andExpect(status().isNotFound());

        verify(channelServiceMock, times(1)).findByChannelId(3L);
        verifyNoMoreInteractions(channelServiceMock);
    }

    @Test
    public void testFindScheduledChannelProgrammesForPeriodWhenNoScheduledProgrammes()
            throws Exception {
        ChannelProgramme[] channelProgrammeForBBC2 = new ChannelProgramme[] {};

        when(
                channelProgrammeServiceMock
                        .findScheduledChannelProgrammesForPeriod(2L,
                                new DateTime(TestUtil.NOW)
                                        .toString(TestUtil.DATE_TIME_FORMAT),
                                new DateTime(TestUtil.TWO_HOURS)
                                        .toString(TestUtil.DATE_TIME_FORMAT)))
                .thenReturn(Arrays.asList(channelProgrammeForBBC2));

        mockMvc.perform(
                get("/api/channelshowings/{channelId}/{from}/{to}", 2L,
                        new DateTime(TestUtil.NOW)
                                .toString(TestUtil.DATE_TIME_FORMAT),
                        new DateTime(TestUtil.TWO_HOURS)
                                .toString(TestUtil.DATE_TIME_FORMAT)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));

        verify(channelProgrammeServiceMock, times(1))
                .findScheduledChannelProgrammesForPeriod(
                        2L,
                        new DateTime(TestUtil.NOW)
                                .toString(TestUtil.DATE_TIME_FORMAT),
                        new DateTime(TestUtil.TWO_HOURS)
                                .toString(TestUtil.DATE_TIME_FORMAT));
        verifyNoMoreInteractions(channelProgrammeServiceMock);
    }

}
