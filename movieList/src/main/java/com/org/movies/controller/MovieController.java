package com.org.movies.controller;

import com.org.movies.model.Movie;
import com.org.movies.service.MovieServiceDAO;
import com.org.movies.util.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    @Autowired
    private MovieServiceDAO movieServiceDAO;

    @Autowired
    DataLoader dataLoader;

    @GetMapping(path="/movie")
    public List<Movie> getRunningMovies(){
        return movieServiceDAO.getRunningMovies();
    }

    @PostMapping(path="/cleanMovies")
    @RolesAllowed("Admin")
    public boolean cleanMovies(){
       return movieServiceDAO.deleteNonRunningMovies();
    }

    @GetMapping(path="/home")
    public List<Movie> getMovieData(){
        dataLoader.loadData();
        return movieServiceDAO.getRunningMovies();
    }

}
