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

import com.tvfreakz.model.entity.Genre;
import com.tvfreakz.util.TestUtil;

public class GenreServiceTest {

    private GenreService genreService;

    private GenreRepository genreRepositoryMock;

    @Before
    public void setUp() {
        genreRepositoryMock = mock(GenreRepository.class);
        genreService = new GenreServiceImpl(genreRepositoryMock);
    }

    @Test
    public void testFindAll() throws Exception {
        Sort sort = new Sort(Sort.Direction.ASC, "genreName");
        when(genreRepositoryMock.findAll(sort)).thenReturn(
                Arrays.asList(TestUtil.GENRES));

        List<Genre> genres = genreService.findAll();

        assertEquals("Genre list is of the wrong size: ",
                TestUtil.GENRES.length, genres.size());
        assertEquals("Arts", genres.get(0).getGenreName());
        assertEquals("Childrens", genres.get(1).getGenreName());
        assertEquals("Comedy", genres.get(2).getGenreName());
        assertEquals("Current affairs", genres.get(3).getGenreName());
        assertEquals("Documentary", genres.get(4).getGenreName());
        verify(genreRepositoryMock, times(1)).findAll(sort);
        verifyNoMoreInteractions(genreRepositoryMock);
    }

}
