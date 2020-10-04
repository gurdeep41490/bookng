package com.org.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="userfeedback")
@Table(name="userfeedback")
public class UserFeedback {

    @Id
    private long feedbackId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mobileNo", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private String feedback;

    private String movieName;

    private int rating;

    public UserFeedback(@JsonProperty("feedbackId") long feedbackId,
                        @JsonProperty("User") User user,
                        @JsonProperty("movieName") String movieName,
                        @JsonProperty("feedback") String feedback,
                        @JsonProperty("rating") int rating) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.movieName = movieName;
        this.feedback = feedback;
        this.rating = rating;
    }

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setMovie(Movie movie) {
        //prevent endless loop
        if (sameAsFormer(movie))
            return ;
        //set new owner
        Movie oldMovie = this.movie;
        this.movie = movie;
        //remove from the old owner
        if (oldMovie!=null)
            oldMovie.removeFeedback(this);
        //set myself into new owner
        if (movie!=null)
            movie.addFeedback(this);
    }

    private boolean sameAsFormer(Movie newMovie) {
        return movie==null? newMovie == null : movie.equals(newMovie);
    }

    public void setUser(User user) {
        //prevent endless loop
        if (sameAsFormerUser(user))
            return ;
        //set new owner
        User oldUser = this.user;
        this.user = user;
        //remove from the old owner
        if (oldUser!=null)
            oldUser.removeFeedback(this);
        //set myself into new owner
        if (user!=null)
            user.addFeedback(this);
    }

    private boolean sameAsFormerUser(User newUser) {
        return user==null? newUser == null : user.equals(newUser);
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFeedback that = (UserFeedback) o;
        return user == that.user &&
                Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackId, user, movie, feedback, rating);
    }
}
