package com.thesevensky.ttms.moviesmanageapi.enums.movies;

/**
 * @author TheSevenSky
 */
public enum MovieStatues {
    /**
     * 默认的正常状态
     */
    Default(0),
    /**
     * 即将上映
     */
    OnNext(2),
    /**
     * 热映
     */
    OnHost(1);

    private Integer state;

    MovieStatues(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
