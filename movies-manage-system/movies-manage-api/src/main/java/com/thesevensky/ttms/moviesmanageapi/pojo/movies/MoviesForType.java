package com.thesevensky.ttms.moviesmanageapi.pojo.movies;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class MoviesForType implements Serializable {

    private static final long serialVersionUID = -6631370651728488295L;
    @ApiModelProperty(value = "电影类型的位操作",required = true)
    private Integer typeNumber = 0;
    @ApiModelProperty(value = "电影类型的集合", required = true)
    private List<MoviesType> moviesTypes;

    public MoviesForType() {
    }

    public MoviesForType(Integer typeNumber, List<MoviesType> moviesTypes) {
        this.typeNumber = typeNumber;
        this.moviesTypes = moviesTypes;
    }

    public Integer getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(Integer typeNumber) {
        this.typeNumber = typeNumber;
    }

    public List<MoviesType> getMoviesTypes() {
        return moviesTypes;
    }

    public void setMoviesTypes(List<MoviesType> moviesTypes) {
        this.moviesTypes = moviesTypes;
    }

    @Override
    public String toString() {
        return "MoviesForType{" +
                "typeNumber=" + typeNumber +
                ", moviesTypes=" + moviesTypes +
                '}';
    }
}
