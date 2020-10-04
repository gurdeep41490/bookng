package com.org.movies.repositories;

import com.org.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByIsRunning(boolean isRunning);
    void deleteByIsRunning(boolean isRunning);
}
