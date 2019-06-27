package com.thesevensky.ttms.moviesmanageapi.pojo.movies;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class Movies implements Serializable {

    private static final long serialVersionUID = 2567812883162408980L;
    @ApiModelProperty(value = "电影的唯一约束(增加数据的时候不需要该字段)", required = true, readOnly = true)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long movieId;
    @ApiModelProperty(value = "电影的名字",required = true)
    private String movieName;
    @ApiModelProperty(value = "电影的状态",required = false)
    private Integer movieState = 0;
    @ApiModelProperty(value = "电影的显示的图片",required = false)
    private String movieDefaultImage;
    @ApiModelProperty(value = "电影的上映时间",required = false)
    private Long movieBeginTime;
    @ApiModelProperty(value = "电影的作者",required = false)
    private String movieAuthor;
    @ApiModelProperty(value = "电影的编剧",required = false)
    private String movieScriptWriter;
    @ApiModelProperty(value = "电影的演员",required = false)
    private String movieActor;
    @ApiModelProperty(value = "电影的国家",required = false)
    private String movieCountry;
    @ApiModelProperty(value = "电影的语言",required = false)
    private String movieLanguage;
    @ApiModelProperty(value = "电影的类型",required = true)
    private MoviesForType moviesTypes;
    @ApiModelProperty(value = "电影的时长",required = true)
    private Integer movieLength;
    @ApiModelProperty(value = "电影的销量",required = false)
    private Long movieSalesVolume = 0L;
    @ApiModelProperty(value = "电影的评分",required = false)
    private Double movieGrade = 10.0;
    @ApiModelProperty(value = "电影的详细图片",required = false)
    private String movieDetailImage;
    @ApiModelProperty(value = "电影详细介绍",required = false)
    private String movieDetailTest;
    @ApiModelProperty(value = "电影的类型运算",required = false)
    private Integer movieTypeNumber;

    public Movies() {
    }

    public Movies(Long movieId, String movieName, Integer movieState, String movieDefaultImage, Long movieBeginTime, String movieAuthor, String movieScriptWriter, String movieActor, String movieCountry, String movieLanguage, MoviesForType moviesTypes, Integer movieLength, Long movieSalesVolume, Double movieGrade, String movieDetailImage, String movieDetailTest, Integer movieTypeNumber) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieState = movieState;
        this.movieDefaultImage = movieDefaultImage;
        this.movieBeginTime = movieBeginTime;
        this.movieAuthor = movieAuthor;
        this.movieScriptWriter = movieScriptWriter;
        this.movieActor = movieActor;
        this.movieCountry = movieCountry;
        this.movieLanguage = movieLanguage;
        this.moviesTypes = moviesTypes;
        this.movieLength = movieLength;
        this.movieSalesVolume = movieSalesVolume;
        this.movieGrade = movieGrade;
        this.movieDetailImage = movieDetailImage;
        this.movieDetailTest = movieDetailTest;
        this.movieTypeNumber = movieTypeNumber;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getMovieState() {
        return movieState;
    }

    public void setMovieState(Integer movieState) {
        this.movieState = movieState;
    }

    public String getMovieDefaultImage() {
        return movieDefaultImage;
    }

    public void setMovieDefaultImage(String movieDefaultImage) {
        this.movieDefaultImage = movieDefaultImage;
    }

    public Long getMovieBeginTime() {
        return movieBeginTime;
    }

    public void setMovieBeginTime(Long movieBeginTime) {
        this.movieBeginTime = movieBeginTime;
    }

    public String getMovieAuthor() {
        return movieAuthor;
    }

    public void setMovieAuthor(String movieAuthor) {
        this.movieAuthor = movieAuthor;
    }

    public String getMovieScriptWriter() {
        return movieScriptWriter;
    }

    public void setMovieScriptWriter(String movieScriptWriter) {
        this.movieScriptWriter = movieScriptWriter;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public MoviesForType getMoviesTypes() {
        return moviesTypes;
    }

    public void setMoviesTypes(MoviesForType moviesTypes) {
        this.moviesTypes = moviesTypes;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
    }

    public Long getMovieSalesVolume() {
        return movieSalesVolume;
    }

    public void setMovieSalesVolume(Long movieSalesVolume) {
        this.movieSalesVolume = movieSalesVolume;
    }

    public Double getMovieGrade() {
        return movieGrade;
    }

    public void setMovieGrade(Double movieGrade) {
        this.movieGrade = movieGrade;
    }

    public String getMovieDetailImage() {
        return movieDetailImage;
    }

    public void setMovieDetailImage(String movieDetailImage) {
        this.movieDetailImage = movieDetailImage;
    }

    public String getMovieDetailTest() {
        return movieDetailTest;
    }

    public void setMovieDetailTest(String movieDetailTest) {
        this.movieDetailTest = movieDetailTest;
    }

    public Integer getMovieTypeNumber() {
        return movieTypeNumber;
    }

    public void setMovieTypeNumber(Integer movieTypeNumber) {
        this.movieTypeNumber = movieTypeNumber;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieState=" + movieState +
                ", movieDefaultImage='" + movieDefaultImage + '\'' +
                ", movieBeginTime=" + movieBeginTime +
                ", movieAuthor='" + movieAuthor + '\'' +
                ", movieScriptWriter='" + movieScriptWriter + '\'' +
                ", movieActor='" + movieActor + '\'' +
                ", movieCountry='" + movieCountry + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", moviesTypes=" + moviesTypes +
                ", movieLength=" + movieLength +
                ", movieSalesVolume=" + movieSalesVolume +
                ", movieGrade=" + movieGrade +
                ", movieDetailImage='" + movieDetailImage + '\'' +
                ", movieDetailTest='" + movieDetailTest + '\'' +
                ", movieTypeNumber=" + movieTypeNumber +
                '}';
    }
}
