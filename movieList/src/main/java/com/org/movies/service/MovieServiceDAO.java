package com.org.movies.service;

import com.org.movies.model.Movie;
import com.org.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceDAO {

    @Autowired
    private MovieRepository movieRepository;

    public void saveMovie(Movie movie) {
         movieRepository.save(movie);
    }

    public void deleteMovie(Movie movie){
        movieRepository.delete(movie);
    }

    public List<Movie> getRunningMovies(){
        return movieRepository.findByIsRunning(true);
    }

    public boolean deleteNonRunningMovies(){
         movieRepository.deleteByIsRunning(false);
         return true;
    }
}
