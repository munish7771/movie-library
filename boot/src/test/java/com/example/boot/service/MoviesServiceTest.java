package com.example.boot.service;

import com.example.boot.model.Movie;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MoviesServiceTest {
    @Autowired
    MoviesService service = mock(MoviesService.class);

    @Test
    public void getAllMoviesShouldPass() {
        List<String> mockList = new ArrayList<>();
        mockList.add("mock-movie");
        Mockito.when(service.getAllMovies()).thenReturn(mockList);
        List<String> movies = service.getAllMovies();
        // list size should be 1
        Assert.assertEquals(movies.size(), 1);
        // movie name should match
        Assert.assertEquals(movies.get(0).toLowerCase(), "mock-movie");
    }

    @Test
    public void getAllMoviesSortedShouldPass() {
        List<String> mockList = new ArrayList<>();
        mockList.add("mock-movie");
        Mockito.when(service.getMoviesSorted("mock-column", "mock-order")).thenReturn(mockList);

        List<String> movies = service.getMoviesSorted("mock-column", "mock-order");
        // list size should be 1
        Assert.assertEquals(movies.size(), 1);
        // movie name should match
        Assert.assertEquals(movies.get(0).toLowerCase(), "mock-movie");
    }

    @Test
    public void getMovieByNameShouldPass() {
        List<Movie> mockList = new ArrayList<>();
        Movie mockMovie = new Movie();
        mockMovie.SerialNo = "99";
        mockList.add(mockMovie);
        Mockito.when(service.getMovieByName("mock-name")).thenReturn(mockList);

        List<Movie> movies = service.getMovieByName("mock-name");
        // list size should be 1
        Assert.assertEquals(movies.size(), 1);
        // movie name should match
        Assert.assertEquals(movies.get(0).SerialNo, mockMovie.SerialNo);
    }
}
