package com.thesevensky.ttms.moviesmanageapi.enums.movies;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/11 20:45
 * @Version 1.0
 */
public enum MoviesSeatStatues {
    DEFAULT(0),BED(2)
    ;

    private Integer value;

    MoviesSeatStatues(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
