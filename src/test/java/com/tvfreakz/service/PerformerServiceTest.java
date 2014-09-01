/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Sort;

import com.tvfreakz.exception.PerformerNotFoundException;
import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.repository.PerformerRepository;
import com.tvfreakz.util.TestUtil;

public class PerformerServiceTest {
  
  private PerformerService performerService;
  
  private PerformerRepository performerRepositoryMock;
  
  private Performer performer;
  
  @Before
  public void setUp() {
    performerRepositoryMock = mock(PerformerRepository.class);
    performerService = new PerformerServiceImpl(performerRepositoryMock);
    performer = new Performer();
    performer.setPerformerId(1L);
    performer.setPerformerName("Sigourney Weaver");
  }
  
  @Test
  public void testFindByPerformerIdWhenPerformerIsFound() throws Exception {
    when(performerRepositoryMock.findByPerformerId(1L)).thenReturn(performer);
    
    Performer actual = performerService.findByPerformerId(1L);
    
    assertEquals(performer, actual);
    verify(performerRepositoryMock, times(1)).findByPerformerId(1L);
    verifyNoMoreInteractions(performerRepositoryMock);    
  }
  
  @Test(expected = PerformerNotFoundException.class)
  public void testFindByPerformerIdWhenPerformerIsNotFound() throws Exception {
    when(performerRepositoryMock.findByPerformerId(1L)).thenReturn(null);
    
    performerService.findByPerformerId(1L);
    verify(performerRepositoryMock, times(1)).findByPerformerId(1L);
    verifyNoMoreInteractions(performerRepositoryMock);
  }
  
  @Test
  public void testFindAll() throws Exception {
    Sort sort = new Sort(Sort.Direction.ASC, "performerName");
    when(performerRepositoryMock.findAll(sort)).thenReturn(Arrays.asList(TestUtil.PERFORMERS));
    
    List<Performer> performerList = performerService.findAll();
    
    assertEquals("Performer list is of the wrong size: ", TestUtil.PERFORMERS.length, performerList.size());
    assertEquals("Sigourney Weaver", performerList.get(0).getPerformerName());
    assertEquals("John Hurt", performerList.get(1).getPerformerName());
    assertEquals("Harrison Ford", performerList.get(2).getPerformerName());
    verify(performerRepositoryMock, times(1)).findAll(sort);
    verifyNoMoreInteractions(performerRepositoryMock);
  }

}
