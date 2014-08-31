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

import com.tvfreakz.service.DirectorService;
import com.tvfreakz.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/config/testMVCContext.xml", "file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
@WebAppConfiguration
public class DirectorControllerTest {
  
  private MockMvc mockMvc;
  
  @Autowired
  private DirectorService directorServiceMock;
  
  @Autowired
  private WebApplicationContext webApplicationContext;
  
  @Before
  public void setUp() {
    Mockito.reset(directorServiceMock);
    
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }
  
  @Test
  public void testFindAll() throws Exception {    
    when(directorServiceMock.findAll()).thenReturn(Arrays.asList(TestUtil.DIRECTORS));
    
    mockMvc.perform(get("/api/directors"))
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
    .andExpect(jsonPath("$", hasSize(TestUtil.DIRECTORS.length)))
    .andExpect(jsonPath("$[0].directorName", is("Ridley Scott")))
    .andExpect(jsonPath("$[1].directorName", is("James Cameron")))
    .andExpect(jsonPath("$[2].directorName", is("Jean-Pierre Jeunet")));

    verify(directorServiceMock, times(1)).findAll();
    verifyNoMoreInteractions(directorServiceMock);
  }

}
