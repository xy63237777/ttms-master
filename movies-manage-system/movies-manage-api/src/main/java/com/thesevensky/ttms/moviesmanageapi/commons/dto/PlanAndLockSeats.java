package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/29 13:05
 * @Version 1.0
 */
public class PlanAndLockSeats implements Serializable {


    private static final long serialVersionUID = 8266907994791671836L;
    private MoviesPlan moviesPlan;
    private String lockSeats;

    public PlanAndLockSeats() {
    }

    public PlanAndLockSeats(MoviesPlan moviesPlan, String lockSeats) {
        this.moviesPlan = moviesPlan;
        this.lockSeats = lockSeats;
    }

    public MoviesPlan getMoviesPlan() {
        return moviesPlan;
    }

    public void setMoviesPlan(MoviesPlan moviesPlan) {
        this.moviesPlan = moviesPlan;
    }

    public String getLockSeats() {
        return lockSeats;
    }

    public void setLockSeats(String lockSeats) {
        this.lockSeats = lockSeats;
    }

    @Override
    public String toString() {
        return "PlanAndLockSeats{" +
                "moviesPlan=" + moviesPlan +
                ", lockSeats='" + lockSeats + '\'' +
                '}';
    }
}
