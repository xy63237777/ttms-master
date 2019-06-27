package com.thesevensky.starter.properties.movies;

public class MoviesNumberProperties {
    private Integer numberOfDay = 10;
    private Integer dayNumber = 32 / numberOfDay;
    private String cacheName = "cacheOfNumber";

    public Integer getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(Integer numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }
}
