package com.thesevensky.ttms.ttmszuulgatewaymaster.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 1:15
 * @Version 1.0
 */
public class MoviesCartAndStatus implements Serializable {

    private static final long serialVersionUID = 8462993052049766029L;
    @ApiModelProperty(value = "用户订单的Id",required = true)
    private Long moviesCartId;
    @ApiModelProperty(value = "订单的状态",required = true)
    private Byte status;

    public MoviesCartAndStatus() {
    }

    public MoviesCartAndStatus(Long moviesCartId, Byte status) {
        this.moviesCartId = moviesCartId;
        this.status = status;
    }

    public Long getMoviesCartId() {
        return moviesCartId;
    }

    public void setMoviesCartId(Long moviesCartId) {
        this.moviesCartId = moviesCartId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "MoviesCartAndStatus{" +
                "moviesCartId=" + moviesCartId +
                ", status=" + status +
                '}';
    }
}
