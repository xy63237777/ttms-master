package com.thesevensky.starter.properties.movies;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/27 17:20
 * @Version 1.0
 */
public class MoviesPlanProperties {
    private Long lockMoviesTime = 1000 * 60 * 20L;

    public Long getLockMoviesTime() {
        return lockMoviesTime;
    }

    public void setLockMoviesTime(Long lockMoviesTime) {
        this.lockMoviesTime = lockMoviesTime;
    }
}
