package com.example.boot.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.example.boot.model.Movie;
import com.example.boot.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MoviesService service;

    Logger log = Logger.getLogger(MovieController.class.getName());

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health(){
        log.info("health method invoked");
        return "OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<String> getAllMovies(@RequestParam(required = false, name="size") Integer size){
        log.info("get all movies method invoked, size is-" + size.toString());
        List<String> movieList = service.getAllMovies();
        if(size != null) {
            if(size > movieList.size()) size = movieList.size();
            return movieList.subList(0, size);
        }else{
            return movieList;
        }
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Movie> getMoviesByName(@PathVariable(name = "name") String name, @RequestParam(required = false, name="size") Integer size){
        name = name.replaceAll("%20","");
        log.info("get movie by name method invoked, name is-" + name);
        List<Movie> movieList = service.getMovieByName(name);
        if(size != null) {
            if(size > movieList.size()) size = movieList.size();
            return movieList.subList(0, size);
        }else{
            return movieList;
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, params={"column", "order"})
    public List<String> getMoviesSorted(@RequestParam(name="column") String column,@RequestParam(name="order") String order, @RequestParam(required = false, name="size") Integer size){
        log.info("getMoviesSorted method invoked");
        List<String> movieList = service.getMoviesSorted(column, order);
        if(size != null) {
            if(size > movieList.size()) size = movieList.size();
            return movieList.subList(0, size);
        }else{
            return movieList;
        }
    }

//    @RequestMapping(value = "/", method = RequestMethod.GET, params={"filter"}, produces = "application/xml")
//    public List<Movie> getMoviesFiltered(@RequestParam(required = false, name = "filter") String filter){
//        log.info("getMoviesFiltered method invoked");
//        return null;
//    }

//    @RequestMapping(value = "/", method = RequestMethod.GET, params={"page" ,"size"}, produces = "application/xml")
//    public List<Movie> getMoviesPage(@RequestParam(required = true, name = "page") Integer page, @RequestParam(required = true, name = "size") Integer size){
//        log.info("getMoviesPage method invoked");
//        return null;
//    }

}
