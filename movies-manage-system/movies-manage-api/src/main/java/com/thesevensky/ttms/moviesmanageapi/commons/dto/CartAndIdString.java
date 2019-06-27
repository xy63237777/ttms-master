package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesCart;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/13 2:09
 * @Version 1.0
 */
public class CartAndIdString implements Serializable {

    private static final long serialVersionUID = -2440111455647235954L;
    private MoviesCart moviesCart;
    @ApiModelProperty("值的类型是 id1,id2,id3 例如 123,124,126")
    private String idString;

    public CartAndIdString() {
    }

    public CartAndIdString(MoviesCart moviesCart, String idString) {
        this.moviesCart = moviesCart;
        this.idString = idString;
    }

    public MoviesCart getMoviesCart() {
        return moviesCart;
    }

    public void setMoviesCart(MoviesCart moviesCart) {
        this.moviesCart = moviesCart;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    @Override
    public String toString() {
        return "CartAndIdString{" +
                "moviesCart=" + moviesCart +
                ", idString='" + idString + '\'' +
                '}';
    }
}
