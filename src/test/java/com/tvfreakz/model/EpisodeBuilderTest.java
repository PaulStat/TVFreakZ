/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Director;
import com.tvfreakz.model.entity.Episode;
import com.tvfreakz.model.entity.ProgEpPerfAssociation;
import com.tvfreakz.model.entity.Programme;

public class EpisodeBuilderTest {

    private Episode expected;
    private Long episodeId = 1L;
    private Programme programme = new Programme.Builder().withProgrammeId(1L)
            .withProgTitle("Aliens").build();
    private Director director = new Director.Builder().withDirectorId(1L)
            .withDirectorName("James Cameron").build();
    private String subtitle = "";
    private String episode = "";
    private String description = "";
    private Set<ProgEpPerfAssociation> episodeAssociations;

    @Before
    public void setup() {
        expected = new Episode(episodeId, programme, director, subtitle,
                episode, description, episodeAssociations);
    }

    @Test
    public void testEpisodeBuilderBuildObjectsShouldBeEqual() {
        Episode actual = new Episode.Builder().withEpisodeId(episodeId)
                .withProgramme(programme).withDirector(director)
                .withSubtitle(subtitle).withEpisode(episode)
                .withDescription(description)
                .withEpisodeAssociations(episodeAssociations).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testEpisodeBuilderBuildObjectsShouldNotBeEqual() {
        Episode actual = new Episode.Builder().withEpisodeId(episodeId).build();

        assertNotEquals(expected, actual);
    }

}
