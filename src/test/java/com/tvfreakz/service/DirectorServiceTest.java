package com.tvfreakz.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tvfreakz.exception.DirectorNotFoundException;
import com.tvfreakz.model.entity.Director;
import com.tvfreakz.repository.DirectorRepository;

public class DirectorServiceTest {
  
  private DirectorService directorService;
  
  private DirectorRepository directorRepositoryMock;
  
  private Director director;
  
  private static final ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config/testContext.xml");  
  
  @Before
  public void setUp() {
    directorRepositoryMock = APP_CONTEXT.getBean("directorRepositoryMock",DirectorRepository.class);
    directorService = APP_CONTEXT.getBean("directorService", DirectorService.class);
    director = new Director();
    director.setDirectorId(1L);
    director.setDirectorName("Ridley Scott");
  }
  
  @Test
  public void testFindByDirectorId() throws Exception {
    when(directorRepositoryMock.findByDirectorId(1L)).thenReturn(director);
    
    Director actual = directorService.findByDirectorId(1L);
    
    assertEquals(director, actual);    
  }
  
  @Test(expected = DirectorNotFoundException.class)
  public void testFindByDirectorIdWhenDirectorIsNotFound() throws Exception {
    when(directorRepositoryMock.findByDirectorId(1L)).thenReturn(null);
    
    directorService.findByDirectorId(1L);    
  }
  

}
