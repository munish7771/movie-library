package com.example.boot.service;

import com.example.boot.db.CsvReader;
import com.example.boot.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MoviesService {

    @Autowired
    public CsvReader reader;

    public List<String> getAllMovies(){
        return reader.getAllMovieTitles();
    }

    public List<String> getMoviesSorted(String column, String order){
        return reader.getMoviesSorted(column, order);
    }

    public List<Movie> getMovieByName(String name){
        return reader.getMovieByName(name);
    }
}
