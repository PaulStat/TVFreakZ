/**
 * TVFreakZ (c) 2014 - Paul Statham
 */
package com.tvfreakz.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import com.tvfreakz.model.entity.Genre;

public class GenreBuilderTest {

    private Genre expected;
    private Long genreId = 1L;
    private String genreName = "Scifi";

    @Before
    public void setup() {
        expected = new Genre(genreId, genreName);
    }

    @Test
    public void testGenreBuilderBuildObjectsShouldBeEqual() {
        Genre actual = new Genre.Builder().withGenreId(genreId)
                .withGenreName(genreName).build();

        assertEquals(expected, actual);
    }

    @Test
    public void testGenreBuilderBuildObjectsShouldNotBeEqual() {
        Genre actual = new Genre.Builder().withGenreId(genreId).build();

        assertNotEquals(expected, actual);
    }

}
