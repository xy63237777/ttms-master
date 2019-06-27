package com.thesevensky.ttms.ttmszuulgatewaymaster.dto;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 2:11
 * @Version 1.0
 */
public class MoviesAndPlanId {
    @ApiModelProperty(value = "电影信息",required = true)
    private Movies movies;
    @ApiModelProperty(value = "电影计划的Id",required = true)
    private Long planId;

    public MoviesAndPlanId() {
    }

    public MoviesAndPlanId(Movies movies, Long planId) {
        this.movies = movies;
        this.planId = planId;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "MoviesAndPlanId{" +
                "movies=" + movies +
                ", planId=" + planId +
                '}';
    }
}
