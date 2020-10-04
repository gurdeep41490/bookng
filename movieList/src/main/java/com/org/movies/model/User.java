package com.org.movies.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="user")
@Table(name="user")
public class User {

    private String name;
    private String emailId;
    @Id
    private long mobileNo;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserFeedback> feedback = new HashSet<>();

    public User(String name, String emailId, long mobileNo, String password) {
        this.name = name;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }

    public boolean addFeedback(UserFeedback feedback){
        return this.feedback.add(feedback);
    }

    public boolean removeFeedback(UserFeedback feedback){
        return this.feedback.remove(feedback);
    }

    public Set<UserFeedback> getFeedback(){
        return new HashSet<>(this.feedback);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return mobileNo == user.mobileNo &&
                name.equals(user.name) &&
                emailId.equals(user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailId, mobileNo);
    }
}
