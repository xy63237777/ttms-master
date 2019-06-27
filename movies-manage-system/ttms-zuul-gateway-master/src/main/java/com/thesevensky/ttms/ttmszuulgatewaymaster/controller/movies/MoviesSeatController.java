package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.movies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatusList;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.StringCommons;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/1 14:39
 * @Version 1.0
 */
@RestController
@RequestMapping(MoviesSeatConstants.PREFIX)
public class MoviesSeatController {

    @Autowired
    private ObjectMapper objectMapper = null;

    @Autowired
    private MoviesClientService moviesClientService = null;

    @PostMapping(ClientConstants.CLENT_PREFIX + MoviesSeatConstants.DEFAULT_MOVIES_SEAT)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "锁定电影座位")
    public HttpMessage lockSeats(@RequestBody SeatList seatList,
                                 HttpServletRequest request) throws JsonProcessingException {
        System.out.println(seatList);
        return moviesClientService.lockSeats(objectMapper.writeValueAsString(seatList)
                , request.getHeader("Authorization"));
    }

    @PutMapping(ClientConstants.CLENT_PREFIX + MoviesSeatConstants.MOVIES_SEAT_NOT_ALL)
    @ApiOperation("假删除电影座位")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesSeatForNotAll(@RequestBody SeatAndStatusList seatList,
                                                 HttpServletRequest request) throws JsonProcessingException {
        System.out.println(seatList);
        return moviesClientService.updateMoviesSeatForNotAll(objectMapper.writeValueAsString(seatList),
                request.getHeader("Authorization"));
    }

    @PutMapping(ClientConstants.CLENT_PREFIX + MoviesSeatConstants.MOVIES_SEAT_OK)
    @ApiOperation("座位调整为好的(集合的形式)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesSeatForOk(@RequestBody SeatList seatList,
                                             HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.updateMoviesSeatForOk(objectMapper.writeValueAsString(seatList)
                ,request.getHeader("Authorization"));
    }


    @PutMapping(ClientConstants.CLENT_PREFIX + MoviesSeatConstants.MOVIES_SEAT_BED)
    @ApiOperation("座位调整为坏(集合的形式)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesSeatForBed(@RequestBody SeatList seatList,
                                              HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.updateMoviesSeatForBed(objectMapper.writeValueAsString(seatList)
                ,request.getHeader("Authorization"));
    }

    @PostMapping(MoviesSeatConstants.MOVIES_SEAT_NOT_LOCK)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(("座位取消锁定"))
    public HttpMessage notLock(@RequestBody StringCommons msg,
                               HttpServletRequest request) {
        System.out.println(msg);
        return moviesClientService.notLock(msg.getMsg(), request.getHeader("Authorization"));
    }

}
