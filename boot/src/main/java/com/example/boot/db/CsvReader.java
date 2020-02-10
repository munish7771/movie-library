package com.example.boot.db;

import com.example.boot.controller.MovieController;
import com.example.boot.dto.MovieDto;
import com.example.boot.model.Movie;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CsvReader {

    @Autowired
    private MovieDto dto;

    Logger log = Logger.getLogger(MovieController.class.getName());
    static String file = "/Users/munishsharma/Documents/GitHub/movie-library/boot/src/main/resources/static/movies.csv";

    // all movies data object
    private List<String[]> allData;

    private void loadData(){
        if(allData != null) return;
        log.info("load data invoked");
        try{
            FileReader filereader = new FileReader(file);

            // create csvReader object and skip first Line
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                        .withSkipLines(1)
                        .build();
            allData = csvReader.readAll();
            log.info("All data loaded in memory");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getAllMovieTitles(){
        log.info("inside csv get all movie titles");
        List<String> movies = new ArrayList<>();
        loadData();
        for (String[] row : allData) {
            movies.add(row[1]);
        }
        return movies;
    }

    public List<String> getMoviesSorted(String column, String order){
        log.info("inside csv get movies sorted, column is-" + column + " and order is-" + order);
        int mapNumber = getColumnMapNumber(column);
        HashMap<String, String> map = new HashMap<>();
        loadData();
        for (String[] row: allData){
            map.put(row[mapNumber],row[1]);
        }
        return sortbykey(map, order);
    }

    public List<Movie> getMovieByName(String name){
        log.info("inside csv get movie by name, name is-" + name);
        List<Movie> movies = new ArrayList<>();
        loadData();
        for (String[] row: allData){

            if(row[1].toLowerCase().contains(name.toLowerCase())) movies.add(dto.mapMovie(row));
        }
        return movies;
    }

    public int getColumnMapNumber(String column){
        log.info("inside get column map number, column is-" + column);
        switch(column){
            case "category": return 2;
            case "release" : return 6;
            case "title" : return 1;
            // in case of fault param, return id i.e default sort
            default: return 0;
        }
    }

    // sorts a map by key and returns the list of value in specific order
    public List<String> sortbykey(HashMap<String, String> map, String order)
    {
        log.info("inside sort by order, order is-" + order);
        ArrayList<String> sortedKeys =
                new ArrayList<>(map.keySet());
        List<String> movieList = new ArrayList<>();
        if(order.equals("asc")) Collections.sort(sortedKeys, Collections.reverseOrder());
        else Collections.sort(sortedKeys);

        // Display the TreeMap which is naturally sorted
        for (String x : sortedKeys)
            movieList.add(map.get(x));
        return movieList;
    }

}
