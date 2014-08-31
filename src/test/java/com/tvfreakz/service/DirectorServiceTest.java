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

import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.repository.DirectorRepository;
import com.tvfreakz.util.TestUtil;

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
  
  @Test
  public void testFindAll() throws Exception {
    Sort directorSort = new Sort(Sort.Direction.ASC, "directorName");
    when(directorRepositoryMock.findAll(directorSort)).thenReturn(Arrays.asList(TestUtil.DIRECTORS));
    
    List<Director> directorList = directorService.findAll();
    
    assertEquals("Director list is the wrong size: ", TestUtil.DIRECTORS.length, directorList.size());
    assertEquals("Ridley Scott", directorList.get(0).getDirectorName());
    assertEquals("James Cameron", directorList.get(1).getDirectorName());
    assertEquals("Jean-Pierre Jeunet", directorList.get(2).getDirectorName());
    verify(directorRepositoryMock, times(1)).findAll(directorSort);
    verifyNoMoreInteractions(directorRepositoryMock);
  }
  

}
