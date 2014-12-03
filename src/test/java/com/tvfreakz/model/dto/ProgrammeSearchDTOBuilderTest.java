/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class ProgrammeSearchDTOBuilderTest {

  private ProgrammeSearchDTO expectedDTO;

  private String[] channelIdList = new String[]{"1"};
  private String[] genreIdList = new String[]{"1"};
  private DateTime fromDateTime = new DateTime();
  private DateTime toDateTime = new DateTime();
  private String progName = "Aliens";
  private boolean subtitled = false;
  private boolean signed = false;
  private boolean film = true;

  @Before
  public void setup() {
    expectedDTO = new ProgrammeSearchDTO(channelIdList, genreIdList, fromDateTime,
        toDateTime, progName, subtitled, signed, film);    
  }

  @Test
  public void testProgrammeSearchDTOBuilderBuildObjectsShouldBeEqual() {
    ProgrammeSearchDTO.Builder builder = new ProgrammeSearchDTO.Builder();
    ProgrammeSearchDTO actualDTO = builder.withChannelIdList(channelIdList).withGenreIdList(genreIdList).withFromDateTime(fromDateTime)
        .withToDateTime(toDateTime).withProgName(progName).withSubtitled(subtitled).withSigned(signed).withFilm(film).build();

    assertEquals(expectedDTO, actualDTO);
  }

  @Test
  public void testProgrammeSearchDTOBuilderBuildObjectsShouldNotBeEqual() {
    ProgrammeSearchDTO.Builder builder = new ProgrammeSearchDTO.Builder();
    ProgrammeSearchDTO actualDTO = builder.withChannelIdList(channelIdList).build();
    
    assertNotEquals(expectedDTO, actualDTO);
  }

}
