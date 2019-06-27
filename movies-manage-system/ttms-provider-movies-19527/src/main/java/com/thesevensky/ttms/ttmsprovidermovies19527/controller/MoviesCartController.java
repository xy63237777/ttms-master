package com.thesevensky.ttms.ttmsprovidermovies19527.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.CartAndIdString;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesCartConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesCart;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/28 15:34
 * @Version 1.0
 */
@RestController
@RequestMapping(MoviesCartConstants.PREFIX)
public class MoviesCartController {

    @Autowired
    private ObjectMapper objectMapper = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoviesCartService moviesCartService = null;

    @GetMapping(MoviesCartConstants.MOVIES_CART_PAGE)
    @PreAuthorize("isAuthenticated()")
    public TTMSPageInfo<MoviesCart> getMoviesCartList(@PathVariable("num") Integer num) {
        return moviesCartService.findAllByUserId(num);
    }

    @PutMapping(MoviesCartConstants.MOVIES_CART_STATUS)
    @PreAuthorize("isAuthenticated()")
    public HttpMessage updateStatus(Long moviesCartId, Byte status) {
        moviesCartService.updateMoviesCartStatus(moviesCartId, status);
        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
    }

    @PostMapping(MoviesCartConstants.DEFAULT_MOVIES)
    //@PreAuthorize("isAuthenticated()")
    @PreAuthorize("isAuthenticated()")
    public HttpMessage insertMoviesCart(String moviesCart)  {
        HttpMessage httpMessage = new HttpMessage();
        System.out.println(moviesCart);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        try {
            MoviesCart cartAndIdString = objectMapper.readValue(moviesCart, MoviesCart.class);
            System.out.println(cartAndIdString);
            moviesCartService.insertMoviesCart(cartAndIdString);
        } catch (Exception e) {
            logger.error("insertMoviesCart 方法出现错误 -" + e.getMessage());
            e.printStackTrace();
            httpMessage.setCode("400");
            httpMessage.setMsg("添加电影订单错误");
        }
        return httpMessage;
    }

    @DeleteMapping(MoviesCartConstants.MOVIES_CART_DELETE)
    @PreAuthorize("isAuthenticated()")
    public HttpMessage deleteMoviesCart(@PathVariable("moviesId") Long moviesId) {
        HttpMessage httpMessage = new HttpMessage("200", HttpMsgStatus.DELETE_MSG_OK);
        try{
            moviesCartService.deleteMoviesCart(moviesId);
        } catch (Exception e) {
            e.printStackTrace();
            httpMessage.setCode("400");
            httpMessage.setMsg(e.getMessage());
        }

        return httpMessage;
    }
}
