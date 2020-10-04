package com.org.movies.util;

import com.org.movies.model.*;
import com.org.movies.service.MovieServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DataLoader {

    @Autowired
    MovieServiceDAO service;

    public void loadData(){
        Movie movie = fillOutMovieDetails();
        service.saveMovie(movie);
    }

    private User loadUsers() {
       return new User("admin", "admin@123", 873458276, "admin");
    }

    private Movie fillOutMovieDetails() {
        Movie movie = new Movie( Language.HINDI, "abc", true, 75,
                LocalDate.of(2020, 04, 14), LocalDate.of(2020, 04, 28));
        Set<Theatre> theatres = getTheatres(movie);
        for(Theatre theatre: theatres){
            movie.addTheatre(theatre);
        }
        return movie;
    }

    private Theatre getTheatre(){
        return new Theatre("def", "ghi", getHalls(3));
    }

    private Set<Theatre> getTheatres(Movie movie) {
        Theatre theatre = new Theatre("def", "ghi", getHalls(3));
        Theatre theatre2 = new Theatre("abc", "jkl", getHalls(2));
        theatre.addMovie(movie);
        theatre2.addMovie(movie);

        Set<Theatre> theatres = new HashSet<>();
        theatres.add(theatre);
        theatres.add(theatre2);

        return theatres;
    }

    private Set<Hall> getHalls(int number){
        Set<Hall> halls = new HashSet<>();
        for(int i  = 0; i < number; i++){
            Hall hall = new Hall(i, 10, 10 ,10);
            halls.add(hall);
        }
        return halls;
    }
}
