package com.example.boot.dto;

import com.example.boot.controller.MovieController;
import com.example.boot.model.Movie;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MovieDto {

    Logger log = Logger.getLogger(MovieController.class.getName());

    public Movie mapMovie(String[] movieArray){
        log.info("map movie invoked with movie string-" + movieArray.toString());
        Movie movie = new Movie();
        // less time, so mapping manually
        movie.SerialNo = movieArray[0];
        movie.Title = movieArray[1];
        movie.Category = movieArray[2];
        movie.Description = movieArray[3];
        movie.Director = movieArray[4];
        movie.Actors = movieArray[5];
        movie.Release = movieArray[6];
        movie.Runtime = movieArray[7];
        movie.Rating = movieArray[8];
        movie.Votes = movieArray[9];
        movie.Revenue = movieArray[10];
        movie.MetaScore = movieArray[11];
        log.info("returning data object");
        return movie;
    }
}
