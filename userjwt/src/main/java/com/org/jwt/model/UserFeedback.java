package com.org.jwt.model;

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

    private String feedback;

    private int rating;

    public UserFeedback(@JsonProperty("feedbackId") long feedbackId,
                        @JsonProperty("User") User user,
                        @JsonProperty("feedback") String feedback,
                        @JsonProperty("rating") int rating) {
        this.feedbackId = feedbackId;
        this.user = user;
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
        return Objects.hash(feedbackId, user, feedback, rating);
    }
}
