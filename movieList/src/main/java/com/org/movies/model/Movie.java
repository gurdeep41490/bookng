package com.org.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="movie")
@Table(name="movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="movieId")
    private int movieId;

    private Language language;

    private String name;

    private boolean isRunning;

    private int basePrice;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movieId")
    private Set<UserFeedback> userFeedback = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "theatres",
            joinColumns = @JoinColumn(name = "movieId"),
            inverseJoinColumns = @JoinColumn(name = "theatreId"))
    private Set<Theatre> theatres = new HashSet<>();

    public Movie(Language language,
                 String name, boolean isRunning,
                 int basePrice, LocalDate startDate, LocalDate endDate) {
        this.language = language;
        this.name = name;
        this.isRunning = isRunning;
        this.basePrice = basePrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Set<UserFeedback> getUserFeedback() {
        return new HashSet<>(userFeedback);
    }

    public Set<Theatre> getTheatres() {
        return new HashSet<>(theatres);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addTheatre(Theatre theatre){
        return this.theatres.add(theatre);
    }

    public boolean removeTheatre(Theatre theatre){
        return this.theatres.remove(theatre);
    }

    public boolean addFeedback(UserFeedback userFeedback){
       return this.userFeedback.add(userFeedback);
    }

    public boolean removeFeedback(UserFeedback userFeedback){
       return  this.userFeedback.remove(userFeedback);
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId == movie.movieId &&
                language == movie.language &&
                name.equals(movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, language, name);
    }
}
