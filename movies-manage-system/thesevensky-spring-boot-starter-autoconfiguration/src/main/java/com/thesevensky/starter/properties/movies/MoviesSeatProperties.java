package com.thesevensky.starter.properties.movies;

import java.util.concurrent.TimeUnit;

public class MoviesSeatProperties {
    private long time = 1000 * 60 * 10;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public MoviesSeatProperties() {
    }

    public MoviesSeatProperties(long time, TimeUnit timeUnit) {
        this.time = time;
        this.timeUnit = timeUnit;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
