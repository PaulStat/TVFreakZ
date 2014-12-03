/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Director;

public class DirectorBuilderTest {
  
  private Director expected;
  
  private Long directorId = 1L;
  private String directorName = "Ridley Scott";
  
  @Before
  public void setup() {
    expected = new Director(directorId, directorName);    
  }
  
  @Test
  public void testDirectorBuilderBuildObjectsShouldBeEqual() {
    Director actual = new Director.Builder().withDirectorId(directorId).withDirectorName(directorName).build();
    
    assertEquals(expected, actual);
  }
  
  @Test
  public void testDirectorBuilderBuildObjectsShouldNotBeEqual() {
    Director actual = new Director.Builder().withDirectorId(directorId).build();
    
    assertNotEquals(expected, actual);
  }

}
