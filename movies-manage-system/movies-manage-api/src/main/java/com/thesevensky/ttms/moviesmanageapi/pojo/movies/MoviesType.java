package com.thesevensky.ttms.moviesmanageapi.pojo.movies;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MoviesType implements Serializable {

    private static final long serialVersionUID = -8270313523137497412L;
    @ApiModelProperty(value = "电影类别的唯一约束(增加数据的时候不需要该字段)", required = true, readOnly = true)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long movieTypeId;
    @ApiModelProperty(value = "电影类别的名字", required = true)
    private String movieTypeName;
    @ApiModelProperty(value = "电影类别的状态",required = false)
    private Integer movieTypeState = 0;
    @ApiModelProperty(value = "电影类别的位操作", required = true)
    private Integer movieTypeBit;

    public MoviesType() {
    }

    public MoviesType(Long movieTypeId, String movieTypeName, Integer movieTypeState, Integer movieTypeBit) {
        this.movieTypeId = movieTypeId;
        this.movieTypeName = movieTypeName;
        this.movieTypeState = movieTypeState;
        this.movieTypeBit = movieTypeBit;
    }


    public Long getMovieTypeId() {
        return movieTypeId;
    }

    public void setMovieTypeId(Long movieTypeId) {
        this.movieTypeId = movieTypeId;
    }

    public String getMovieTypeName() {
        return movieTypeName;
    }

    public void setMovieTypeName(String movieTypeName) {
        this.movieTypeName = movieTypeName;
    }

    public Integer getMovieTypeState() {
        return movieTypeState;
    }

    public void setMovieTypeState(Integer movieTypeState) {
        this.movieTypeState = movieTypeState;
    }

    public Integer getMovieTypeBit() {
        return movieTypeBit;
    }

    public void setMovieTypeBit(Integer movieTypeBit) {
        this.movieTypeBit = movieTypeBit;
    }

    @Override
    public String toString() {
        return "MoviesType{" +
                "movieTypeId=" + movieTypeId +
                ", movieTypeName='" + movieTypeName + '\'' +
                ", movieTypeState=" + movieTypeState +
                ", movieTypeBit=" + movieTypeBit +
                '}';
    }
}
