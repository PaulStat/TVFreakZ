/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Genre;
import com.tvfreakz.model.entity.Performer;
import com.tvfreakz.model.entity.ProgEpPerfAssociation;
import com.tvfreakz.model.entity.Programme;

public class ProgrammeBuilderTest {

    private Programme expected;
    private Long programmeId = 1L;
    private String progTitle = "Aliens";
    private Genre genre = new Genre.Builder().withGenreId(1L)
            .withGenreName("Scifi").build();
    private Director director = new Director.Builder().withDirectorId(1L)
            .withDirectorName("James Cameron").build();
    private Date year = new Date();
    private boolean film = true;
    private boolean wideScreen = false;
    private boolean blackAndWhite = false;
    private String certificate = "15";
    private String description = "Fast paced action thriller";
    private Performer sigourneyWeaver = new Performer();
    private Set<ProgEpPerfAssociation> programmeAssociations = new HashSet<>(
            Arrays.asList(new ProgEpPerfAssociation[] { new ProgEpPerfAssociation.Builder()
                    .withPerformer(sigourneyWeaver).build() }));

    @Before
    public void setup() {
        expected = new Programme(programmeId, progTitle, genre, director, year,
                film, wideScreen, blackAndWhite, certificate, description,
                programmeAssociations);
    }

    @Test
    public void testProgrammeBuilderBuildObjectsShouldBeEqual() {
        Programme actual = new Programme.Builder().withProgrammeId(programmeId)
                .withProgTitle(progTitle).withGenre(genre)
                .withDirector(director).withYear(year).withFilm(film)
                .withWideScreen(wideScreen).withBlackAndWhite(blackAndWhite)
                .withCertificate(certificate).withDescription(description)
                .withProgrammeAssociations(programmeAssociations).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testProgrammeBuilderBuildObjectsShouldNotBeEqual() {
        Programme actual = new Programme.Builder().withProgrammeId(programmeId)
                .withProgTitle(progTitle).withGenre(genre).build();

        assertNotEquals(expected, actual);
    }

}
