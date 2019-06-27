package com.thesevensky.ttms.moviesmanageapi.pojo.movies;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MoviesPlan implements Serializable {

    private static final long serialVersionUID = 6708229680312026571L;
    @ApiModelProperty(value = "电影计划唯一约束(增加数据的时候不需要该字段)", required = true, readOnly = true)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long moviePlanId;
    @ApiModelProperty(value = "电影计划绑定的电影",required = true)
    private Movies movies;
    @ApiModelProperty(value = "电影计划绑定的电影厅",required = true)
    private MoviesHall moviesHall;
    @ApiModelProperty(value = "电影计划的开始时间",required = true)
    private Long moviePlanStartTime;
    @ApiModelProperty(value = "电影计划的结束时间",required = true)
    private Long moviePlanEndTime;
    @ApiModelProperty(value = "电影计划的状态",required = false)
    private Integer moviePlanState = 0;
    @ApiModelProperty(value = "电影计划的价钱",required = true)
    private Double moviePlanPrice;
    @ApiModelProperty(value = "当天电影计划的编号",required = true)
    private Integer moviePlanNumber;

    public MoviesPlan() {
    }

    public MoviesPlan(Long moviePlanId, Movies movies, MoviesHall moviesHall, Long moviePlanStartTime, Long moviePlanEndTime, Integer moviePlanState, Double moviePlanPrice, Integer moviePlanNumber) {
        this.moviePlanId = moviePlanId;
        this.movies = movies;
        this.moviesHall = moviesHall;
        this.moviePlanStartTime = moviePlanStartTime;
        this.moviePlanEndTime = moviePlanEndTime;
        this.moviePlanState = moviePlanState;
        this.moviePlanPrice = moviePlanPrice;
        this.moviePlanNumber = moviePlanNumber;
    }

    public Long getMoviePlanId() {
        return moviePlanId;
    }

    public void setMoviePlanId(Long moviePlanId) {
        this.moviePlanId = moviePlanId;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public MoviesHall getMoviesHall() {
        return moviesHall;
    }

    public void setMoviesHall(MoviesHall moviesHall) {
        this.moviesHall = moviesHall;
    }

    public Long getMoviePlanStartTime() {
        return moviePlanStartTime;
    }

    public void setMoviePlanStartTime(Long moviePlanStartTime) {
        this.moviePlanStartTime = moviePlanStartTime;
    }

    public Integer getMoviePlanState() {
        return moviePlanState;
    }

    public void setMoviePlanState(Integer moviePlanState) {
        this.moviePlanState = moviePlanState;
    }

    public Double getMoviePlanPrice() {
        return moviePlanPrice;
    }

    public void setMoviePlanPrice(Double moviePlanPrice) {
        this.moviePlanPrice = moviePlanPrice;
    }

    public Integer getMoviePlanNumber() {
        return moviePlanNumber;
    }

    public void setMoviePlanNumber(Integer moviePlanNumber) {
        this.moviePlanNumber = moviePlanNumber;
    }

    public Long getMoviePlanEndTime() {
        return moviePlanEndTime;
    }

    public void setMoviePlanEndTime(Long moviePlanEndTime) {
        this.moviePlanEndTime = moviePlanEndTime;
    }

    @Override
    public String toString() {
        return "MoviesPlan{" +
                "moviePlanId=" + moviePlanId +
                ", movies=" + movies +
                ", moviesHall=" + moviesHall +
                ", moviePlanStartTime=" + moviePlanStartTime +
                ", moviePlanEndTime=" + moviePlanEndTime +
                ", moviePlanState=" + moviePlanState +
                ", moviePlanPrice=" + moviePlanPrice +
                ", moviePlanNumber=" + moviePlanNumber +
                '}';
    }
}
