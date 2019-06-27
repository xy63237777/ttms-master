package com.thesevensky.ttms.moviesmanageapi.pojo.movies;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class MoviesHall implements Serializable {

    private static final long serialVersionUID = 651443348218848182L;
    @ApiModelProperty(value = "电影厅的唯一约束(增加数据的时候不需要该字段)", required = true, readOnly = true)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long movieHallId;
    @ApiModelProperty(value = "电影厅的名字",required = true)
    private String movieHallName;
    @ApiModelProperty(value = "电影厅的状态",required = false)
    private Integer movieHallState = 0;
    @ApiModelProperty(value = "电影厅的座位",required = true)
    private List<MoviesSeat> moviesSeats;
    @ApiModelProperty(value = "电影厅的行",required = true)
    private Integer movieHallRow;
    @ApiModelProperty(value = "电影厅的列",required = true)
    private Integer movieHallCol;

    public MoviesHall() {
    }

    public MoviesHall(Long movieHallId, String movieHallName, Integer movieHallState, List<MoviesSeat> moviesSeats, Integer movieHallRow, Integer movieHallCol) {
        this.movieHallId = movieHallId;
        this.movieHallName = movieHallName;
        this.movieHallState = movieHallState;
        this.moviesSeats = moviesSeats;
        this.movieHallRow = movieHallRow;
        this.movieHallCol = movieHallCol;
    }

    public Long getMovieHallId() {
        return movieHallId;
    }

    public void setMovieHallId(Long movieHallId) {
        this.movieHallId = movieHallId;
    }

    public String getMovieHallName() {
        return movieHallName;
    }

    public void setMovieHallName(String movieHallName) {
        this.movieHallName = movieHallName;
    }

    public Integer getMovieHall_State() {
        return movieHallState;
    }

    public void setMovieHall_State(Integer movieHall_State) {
        this.movieHallState = movieHall_State;
    }

    public List<MoviesSeat> getMoviesSeats() {
        return moviesSeats;
    }

    public void setMoviesSeats(List<MoviesSeat> moviesSeats) {
        this.moviesSeats = moviesSeats;
    }


    public Integer getMovieHallRow() {
        return movieHallRow;
    }

    public void setMovieHallRow(Integer movieHallRow) {
        this.movieHallRow = movieHallRow;
    }

    public Integer getMovieHallCol() {
        return movieHallCol;
    }

    public void setMovieHallCol(Integer movieHallCol) {
        this.movieHallCol = movieHallCol;
    }

    @Override
    public String toString() {
        return "MoviesHall{" +
                "movieHallId=" + movieHallId +
                ", movieHallName='" + movieHallName + '\'' +
                ", movieHallState=" + movieHallState +
                ", moviesSeats=" + moviesSeats +
                ", movieHallRow=" + movieHallRow +
                ", movieHallCol=" + movieHallCol +
                '}';
    }
}
