package com.tvfreakz.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.repository.DirectorRepository;

public class DirectorServiceTest {
  
  private DirectorService directorService;
  
  private DirectorRepository directorRepositoryMock;
  
  private Director director;  
  
  @Before
  public void setUp() {
    directorRepositoryMock = mock(DirectorRepository.class);
    directorService = new DirectorServiceImpl(directorRepositoryMock);
    director = new Director();
    director.setDirectorId(1L);
    director.setDirectorName("Ridley Scott");
  }
  
  @Test
  public void testFindByDirectorIdWhenDirectorIsFound() throws Exception {
    when(directorRepositoryMock.findByDirectorId(1L)).thenReturn(director);
    
    Director actual = directorService.findByDirectorId(1L);
    
    assertEquals(director, actual);
    verify(directorRepositoryMock, times(1)).findByDirectorId(1L);
    verifyNoMoreInteractions(directorRepositoryMock);
  }
  
  @Test(expected = DirectorNotFoundException.class)
  public void testFindByDirectorIdWhenDirectorIsNotFound() throws Exception {
    when(directorRepositoryMock.findByDirectorId(1L)).thenReturn(null);
    
    directorService.findByDirectorId(1L);
    verify(directorRepositoryMock, times(1)).findByDirectorId(1L);
    verifyNoMoreInteractions(directorRepositoryMock);
  }
  

}
