package com.org.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name="bookingModel")
@Table(name="bookingModel")
public class BookingModel implements Serializable {

    private long userId;
    private int theatreId;
    private String movieName;
    private long movieId;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("bookingId")
    private long bookingId;

    public BookingModel(@JsonProperty("movieName") String movieName,
                        @JsonProperty("movieId") long movieId,
                        @JsonProperty("theatreId") int theatreId,
                        @JsonProperty("userId") long userId,
                        @JsonProperty("date") Date date) {
        this.userId = userId;
        this.theatreId = theatreId;
        this.movieId = movieId;
        this.movieName = movieName;
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
