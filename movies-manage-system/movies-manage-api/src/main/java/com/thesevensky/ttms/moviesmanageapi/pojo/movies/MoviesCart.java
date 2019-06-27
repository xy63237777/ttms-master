package com.thesevensky.ttms.moviesmanageapi.pojo.movies;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 10:42
 * @Version 1.0
 */
public class MoviesCart implements Serializable {

    private static final long serialVersionUID = 4584843838742734975L;
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty(value = "电影订单的Id",required = false)
    private Long moviesCartId;
    @ApiModelProperty(value = "此订单订购电影的数量",required = true)
    private Byte moviesCartCount;
    @ApiModelProperty(value = "取票码",required = true)
    private String moviesCode;
    @ApiModelProperty(value = "二维码地址",required = true)
    private String moviesCodeImage;
    @ApiModelProperty(value = "座位信息",required = true)
    private String moviesSeats;
    @ApiModelProperty(value = "座位文字信息",required = true)
    private String moviesSeatsText;
    @ApiModelProperty(value = "总价钱",required = true)
    private Double moviesPrice;
    @ApiModelProperty(value = "绑定的电影名字",required = true)
    private String moviesMovieName;
    @ApiModelProperty(value = "电影厅的名字",required = true)
    private String moviesHallName;
    @ApiModelProperty(value = "用户的id",required = true)
    private String moviesUserId;
    @ApiModelProperty(value = "电影厅的名字",required = true)
    private String moviesCartCreateTime;
    @ApiModelProperty(value = "电影票状态",required = true)
    private Byte moviesStatus = 0;
    @ApiModelProperty(value = "绑定的电影计划",required = true)
    private MoviesPlan moviesPlan;

    public MoviesCart() {
    }

    public MoviesCart(Long moviesCartId, Byte moviesCartCount, String moviesCode, String moviesCodeImage, String moviesSeats, String moviesSeatsText, Double moviesPrice, String moviesMovieName, String moviesHallName, String moviesUserId, String moviesCartCreateTime, Byte moviesStatus, MoviesPlan moviesPlan) {
        this.moviesCartId = moviesCartId;
        this.moviesCartCount = moviesCartCount;
        this.moviesCode = moviesCode;
        this.moviesCodeImage = moviesCodeImage;
        this.moviesSeats = moviesSeats;
        this.moviesSeatsText = moviesSeatsText;
        this.moviesPrice = moviesPrice;
        this.moviesMovieName = moviesMovieName;
        this.moviesHallName = moviesHallName;
        this.moviesUserId = moviesUserId;
        this.moviesCartCreateTime = moviesCartCreateTime;
        this.moviesStatus = moviesStatus;
        this.moviesPlan = moviesPlan;
    }

    public Long getMoviesCartId() {
        return moviesCartId;
    }

    public void setMoviesCartId(Long moviesCartId) {
        this.moviesCartId = moviesCartId;
    }

    public Byte getMoviesCartCount() {
        return moviesCartCount;
    }

    public void setMoviesCartCount(Byte moviesCartCount) {
        this.moviesCartCount = moviesCartCount;
    }

    public String getMoviesCode() {
        return moviesCode;
    }

    public void setMoviesCode(String moviesCode) {
        this.moviesCode = moviesCode;
    }

    public String getMoviesCodeImage() {
        return moviesCodeImage;
    }

    public void setMoviesCodeImage(String moviesCodeImage) {
        this.moviesCodeImage = moviesCodeImage;
    }

    public String getMoviesSeats() {
        return moviesSeats;
    }

    public void setMoviesSeats(String moviesSeats) {
        this.moviesSeats = moviesSeats;
    }

    public Double getMoviesPrice() {
        return moviesPrice;
    }

    public void setMoviesPrice(Double moviesPrice) {
        this.moviesPrice = moviesPrice;
    }

    public String getMoviesMovieName() {
        return moviesMovieName;
    }

    public void setMoviesMovieName(String moviesMovieName) {
        this.moviesMovieName = moviesMovieName;
    }

    public String getMoviesHallName() {
        return moviesHallName;
    }

    public void setMoviesHallName(String moviesHallName) {
        this.moviesHallName = moviesHallName;
    }

    public String getMoviesUserId() {
        return moviesUserId;
    }

    public void setMoviesUserId(String moviesUserId) {
        this.moviesUserId = moviesUserId;
    }

    public Byte getMoviesStatus() {
        return moviesStatus;
    }

    public void setMoviesStatus(Byte moviesStatus) {
        this.moviesStatus = moviesStatus;
    }

    public String getMoviesCartCreateTime() {
        return moviesCartCreateTime;
    }

    public void setMoviesCartCreateTime(String moviesCartCreateTime) {
        this.moviesCartCreateTime = moviesCartCreateTime;
    }

    public MoviesPlan getMoviesPlan() {
        return moviesPlan;
    }

    public void setMoviesPlan(MoviesPlan moviesPlan) {
        this.moviesPlan = moviesPlan;
    }

    public String getMoviesSeatsText() {
        return moviesSeatsText;
    }

    public void setMoviesSeatsText(String moviesSeatsText) {
        this.moviesSeatsText = moviesSeatsText;
    }

    @Override
    public String toString() {
        return "MoviesCart{" +
                "moviesCartId=" + moviesCartId +
                ", moviesCartCount=" + moviesCartCount +
                ", moviesCode='" + moviesCode + '\'' +
                ", moviesCodeImage='" + moviesCodeImage + '\'' +
                ", moviesSeats='" + moviesSeats + '\'' +
                ", moviesSeatsText='" + moviesSeatsText + '\'' +
                ", moviesPrice=" + moviesPrice +
                ", moviesMovieName='" + moviesMovieName + '\'' +
                ", moviesHallName='" + moviesHallName + '\'' +
                ", moviesUserId='" + moviesUserId + '\'' +
                ", moviesCartCreateTime='" + moviesCartCreateTime + '\'' +
                ", moviesStatus=" + moviesStatus +
                ", moviesPlan=" + moviesPlan +
                '}';
    }
}
