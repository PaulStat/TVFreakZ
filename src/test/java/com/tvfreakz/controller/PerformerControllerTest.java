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

import com.tvfreakz.service.PerformerService;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/config/testMVCContext.xml",
        "file:src/main/webapp/WEB-INF/config/servlet-config.xml" })
@WebAppConfiguration
public class PerformerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PerformerService performerServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(performerServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testFindAll() throws Exception {
        when(performerServiceMock.findAll()).thenReturn(
                Arrays.asList(TestUtil.PERFORMERS));

        mockMvc.perform(get("/api/performers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(TestUtil.PERFORMERS.length)))
                .andExpect(
                        jsonPath("$[0].performerName", is("Sigourney Weaver")))
                .andExpect(jsonPath("$[1].performerName", is("John Hurt")))
                .andExpect(jsonPath("$[2].performerName", is("Harrison Ford")));

        verify(performerServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(performerServiceMock);
    }

}
