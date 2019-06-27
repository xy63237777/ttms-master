package com.thesevensky.ttms.ttmsprovidermovies19527.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatusList;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;

import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSeatService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(MoviesSeatConstants.PREFIX)
public class MoviesSeatController {

    @Autowired
    private ObjectMapper objectMapper = null;

    @Autowired
    private MoviesSeatService moviesSeatService = null;

    @PutMapping(MoviesSeatConstants.MOVIES_SEAT_OK)
    @ApiOperation("座位调整为好的(集合的形式)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesSeatForOk(String seatList) throws IOException {
        moviesSeatService.seatsForOk(objectMapper.readValue(seatList,SeatList.class), MoviesSeatConstants.DEFAULT_SQL_SESSION);
        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
    }

    @PutMapping(MoviesSeatConstants.MOVIES_SEAT_NOT_ALL)
    @ApiOperation("假删除电影座位")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesSeatForNotAll(String seatList) throws IOException {
        System.out.println(seatList);
        moviesSeatService.seatsForNot(objectMapper.readValue(seatList, SeatAndStatusList.class), null);
        return new HttpMessage("200", HttpMsgStatus.UPDATE_MSG_OK);
    }

//    @PutMapping(MoviesSeatConstants.MOVIES_SEAT_BUY)
//    @ApiOperation("座位调整为已购买(集合的形式)")
//    public HttpMessage updateMoviesSeatForBuy(@RequestBody SeatList seatList) {
//        moviesSeatService.seatsForBuy(seatList, MoviesSeatConstants.DEFAULT_SQL_SESSION);
//        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
//    }

    @PutMapping(MoviesSeatConstants.MOVIES_SEAT_BED)
    @ApiOperation("座位调整为坏(集合的形式)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesSeatForBed(String seatList) throws IOException {
        moviesSeatService.seatsForBed(objectMapper.readValue(seatList,SeatList.class), MoviesSeatConstants.DEFAULT_SQL_SESSION);
        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
    }

    @PostMapping(MoviesSeatConstants.MOVIES_SEAT_NOT_LOCK)
    @PreAuthorize("isAuthenticated()")
    public HttpMessage notLock(String msg) {
        System.out.println(msg);
        moviesSeatService.seatsForNotLock(msg);
        return new HttpMessage("200", "取消锁定成功");
    }

//    @PutMapping(MoviesSeatConstants.MOVIES_SEAT_NOT_BUY)
//    @ApiOperation("座位调整为取消购买(集合的形式)")
//    public HttpMessage updateMoviesSeatForNotBuy(@RequestBody SeatList seatList) {
//        moviesSeatService.seatsForNotBuy(seatList, MoviesSeatConstants.DEFAULT_SQL_SESSION);
//        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
//    }
    
    @PostMapping(MoviesSeatConstants.DEFAULT_MOVIES_SEAT)
    @PreAuthorize("isAuthenticated()")
    public HttpMessage lockSeats(String seatList) throws IOException {

        String str = moviesSeatService.seatsForLock(objectMapper.readValue(seatList,SeatList.class));
        HttpMessage httpMessage = new HttpMessage("200","锁定成功");
        if(str.length() != 0) {
            httpMessage.setCode("0");
            httpMessage.setMsg(str);
        }
        return httpMessage;
    }


}
