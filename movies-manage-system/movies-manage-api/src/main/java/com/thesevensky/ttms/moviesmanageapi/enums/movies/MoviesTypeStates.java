package com.thesevensky.ttms.moviesmanageapi.enums.movies;

public enum MoviesTypeStates  {
    ;

    MoviesTypeStates(Integer state) {
        this.state = state;
    }

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
