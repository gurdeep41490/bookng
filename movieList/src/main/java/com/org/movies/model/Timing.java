package com.org.movies.model;

import javax.persistence.Entity;

public enum Timing {
    TenAM("10AM"),
    OnePM("1PM"),
    FourPM("4PM"),
    SEVENPM("7PM"),
    TENPM("10PM");

    private String value;

    private Timing(String value){
        this.value = value;
    }

}
