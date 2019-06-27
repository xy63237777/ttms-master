package com.thesevensky.ttms.moviesmanageapi.pojo.movies;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MoviesSeat implements Serializable {

    private static final long serialVersionUID = -349441964544872501L;
    @ApiModelProperty(value = "电影唯一约束(增加数据的时候不需要该字段)", readOnly = true, required = true)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long movieSeatId;
    @ApiModelProperty(value = "这个座位被购买的情况",required = false)
    private Integer movieSeatIsBuy = 0;
    @ApiModelProperty(value = "这个座位损坏的情况",required = false)
    private Integer movieSeatIsOk = 0;
    @ApiModelProperty(value = "这个座位整体的状态",required = false)
    private Integer movieSeatState = 0;
    @ApiModelProperty(value = "这个座位的X坐标",required = true)
    private Integer movieSeatX;
    @ApiModelProperty(value = "这个座位的Y坐标",required = true)
    private Integer movieSeatY;
    @ApiModelProperty(value = "这个座位绑定的影厅",required = true)
    private MoviesHall moviesHall;

    public MoviesSeat() {
    }

    public MoviesSeat(Long movieSeatId, Integer movieSeatIsBuy, Integer movieSeatIsOk, Integer movieSeatState, Integer movieSeatX, Integer movieSeatY, MoviesHall moviesHall) {
        this.movieSeatId = movieSeatId;
        this.movieSeatIsBuy = movieSeatIsBuy;
        this.movieSeatIsOk = movieSeatIsOk;
        this.movieSeatState = movieSeatState;
        this.movieSeatX = movieSeatX;
        this.movieSeatY = movieSeatY;
        this.moviesHall = moviesHall;
    }

    public Long getMovieSeatId() {
        return movieSeatId;
    }

    public void setMovieSeatId(Long movieSeatId) {
        this.movieSeatId = movieSeatId;
    }

    public Integer getMovieSeatIsBuy() {
        return movieSeatIsBuy;
    }

    public void setMovieSeatIsBuy(Integer movieSeatIsBuy) {
        this.movieSeatIsBuy = movieSeatIsBuy;
    }

    public Integer getMovieSeatIsOk() {
        return movieSeatIsOk;
    }

    public void setMovieSeatIsOk(Integer movieSeatIsOk) {
        this.movieSeatIsOk = movieSeatIsOk;
    }

    public Integer getMovieSeatState() {
        return movieSeatState;
    }

    public void setMovieSeatState(Integer movieSeatState) {
        this.movieSeatState = movieSeatState;
    }

    public Integer getMovieSeatX() {
        return movieSeatX;
    }

    public void setMovieSeatX(Integer movieSeatX) {
        this.movieSeatX = movieSeatX;
    }

    public Integer getMovieSeatY() {
        return movieSeatY;
    }

    public void setMovieSeatY(Integer movieSeatY) {
        this.movieSeatY = movieSeatY;
    }

    public MoviesHall getMoviesHall() {
        return moviesHall;
    }

    public void setMoviesHall(MoviesHall moviesHall) {
        this.moviesHall = moviesHall;
    }

    @Override
    public String toString() {
        return "MoviesSeat{" +
                "movieSeatId=" + movieSeatId +
                ", movieSeatIsBuy=" + movieSeatIsBuy +
                ", movieSeatIsOk=" + movieSeatIsOk +
                ", movieSeatState=" + movieSeatState +
                ", movieSeatX=" + movieSeatX +
                ", movieSeatY=" + movieSeatY +
                ", moviesHall=" + moviesHall +
                '}';
    }
}
