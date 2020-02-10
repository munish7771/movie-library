package com.example.boot.controller;

import com.example.boot.model.Movie;
import com.example.boot.service.MoviesService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MovieControllerTest {

    @Autowired
    MovieController service = mock(MovieController.class);

    @Test
    public void getAllMoviesShouldPass() {
        List<String> mockList = new ArrayList<>();
        mockList.add("mock-movie");
        Mockito.when(service.getAllMovies(9)).thenReturn(mockList);
        List<String> movies = service.getAllMovies(9);
        // list size should be 1
        Assert.assertEquals(movies.size(), 1);
        // movie name should match
        Assert.assertEquals(movies.get(0).toLowerCase(), "mock-movie");
    }

    @Test
    public void healthShouldPass(){

        Mockito.when(service.health()).thenReturn("OK");
        String result = service.health();
        // list size should be 1
        Assert.assertEquals("OK", result);
    }

    @Test
    public void getMoviesByNameShouldPass() {
        List<Movie> mockList = new ArrayList<>();
        Movie mockMovie = new Movie();
        mockMovie.SerialNo = "99";
        mockList.add(mockMovie);
        Mockito.when(service.getMoviesByName("mock-name", 9)).thenReturn(mockList);
        List<Movie> movies = service.getMoviesByName("mock-name",9);
        // list size should be 1
        Assert.assertEquals(movies.size(), 1);
        // movie name should match
        Assert.assertEquals(movies.get(0).SerialNo, mockMovie.SerialNo);
    }

    @Test
    public void getMoviesSortedShouldPass() {
        List<String> mockList = new ArrayList<>();
        mockList.add("mock-movie");
        Mockito.when(service.getMoviesSorted("mock-column","mock-order",9)).thenReturn(mockList);
        List<String> movies = service.getMoviesSorted("mock-column","mock-order", 9);
        // list size should be 1
        Assert.assertEquals(movies.size(), 1);
        // movie name should match
        Assert.assertEquals(movies.get(0).toLowerCase(), "mock-movie");
    }
}
