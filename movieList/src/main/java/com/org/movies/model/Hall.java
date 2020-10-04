package com.org.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hallId;

    @Column(name="hallNumber")
    private int hallNumber;

    @Column(name="premiumSeats")
    private int premiumSeats;

    @Column(name="basicSeats")
    private int basicSeats;

    @Column(name="upperClassSeats")
    private int upperClassSeats;

    @ElementCollection(targetClass=Timing.class)
    @JoinTable(name = "timings", joinColumns = @JoinColumn(name = "hallId"))
    @Column(name = "timings", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Timing> timings;

    @ManyToOne
    @JoinColumn(name="theatreId")
    @JsonIgnore
    private Theatre theatre;

    public Hall(int hallNumber, int premiumSeats, int basicSeats, int upperClassSeats) {
        this.hallNumber = hallNumber;
        this.premiumSeats = premiumSeats;
        this.basicSeats = basicSeats;
        this.upperClassSeats = upperClassSeats;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getPremiumSeats() {
        return premiumSeats;
    }

    public void setPremiumSeats(int premiumSeats) {
        this.premiumSeats = premiumSeats;
    }

    public int getBasicSeats() {
        return basicSeats;
    }

    public void setBasicSeats(int basicSeats) {
        this.basicSeats = basicSeats;
    }

    public int getUpperClassSeats() {
        return upperClassSeats;
    }

    public void setUpperClassSeats(int upperClassSeats) {
        this.upperClassSeats = upperClassSeats;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
