package com.org.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="theatre")
@Table(name="theatre")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theatreId")
    private int theatreId;

    private String city;
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "theatres", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();;

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Hall> movieHall;

    public Theatre(String city, String name,Set<Hall> movieHall) {
        this.city = city;
        this.name = name;
        this.movieHall = movieHall;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return new HashSet<>(movies);
    }

    public boolean addMovie(Movie movie){
        return this.movies.add(movie);
    }

    public boolean removeMovie(Movie movie){
        return this.movies.remove(movie);
    }

    public Set<Hall> getMovieHall() {
        return new HashSet<>(movieHall);
    }

    public boolean addMovieHall(Hall hall){
       return this.movieHall.add(hall);
    }

    public boolean removeMovieHall(Hall hall){
       return this.movieHall.remove(hall);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theatre theatre = (Theatre) o;
        return theatreId == theatre.theatreId &&
                Objects.equals(name, theatre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theatreId, name);
    }
}
