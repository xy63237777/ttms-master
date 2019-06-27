package com.thesevensky.starter.properties.movies;

import com.thesevensky.starter.properties.movies.page.MoviesPageProperties;

public class MoviesProperties {
    private MoviesPageProperties page = new MoviesPageProperties();
    private MoviesNumberProperties number = new MoviesNumberProperties();
    private MoviesSeatProperties seat = new MoviesSeatProperties();
    private MoviesPlanProperties plan = new MoviesPlanProperties();

    public MoviesPageProperties getMoviesPageProperties() {
        return page;
    }

    public void setMoviesPageProperties(MoviesPageProperties page) {
        this.page = page;
    }

    public MoviesNumberProperties getNumber() {
        return number;
    }

    public void setNumber(MoviesNumberProperties number) {
        this.number = number;
    }


    public MoviesSeatProperties getSeat() {
        return seat;
    }

    public void setSeat(MoviesSeatProperties seat) {
        this.seat = seat;
    }


    public MoviesPlanProperties getPlan() {
        return plan;
    }

    public void setPlan(MoviesPlanProperties plan) {
        this.plan = plan;
    }
}
