package com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies;


import com.thesevensky.ttms.moviesmanageapi.commons.dto.PlanAndLockSeats;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.StringCommons;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.*;
import com.thesevensky.ttms.moviesmanageapi.constants.provider.Providers;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MovieStatues;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.*;


import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@FeignClient(value = Providers.MOVIES, fallbackFactory = MoviesClientFallbackFactory.class)
public interface MoviesClientService {

    /**
     * 电影的服务类
     */
        @GetMapping(MoviesConstants.PREFIX + MoviesConstants.ALL_MOVIES_AND_NUM)
        TTMSPageInfo<Movies> findMovies(@PathVariable("num") Integer num,@RequestHeader("Authorization") String authorization);

        @GetMapping(MoviesConstants.PREFIX + MoviesConstants.ALL_MOVIES_NUM_DETAILS)
        TTMSPageInfo<Movies> findMoviesDetails(@PathVariable("num") Integer num,@RequestHeader("Authorization") String authorization);

        @GetMapping(MoviesConstants.PREFIX + MoviesConstants.ONE_MOVIE_ID)
        Movies findMovieJustOne(@PathVariable("id") Long id,@RequestHeader("Authorization") String authorization);

        @GetMapping(MoviesConstants.PREFIX + MoviesConstants.TYPE_MOVIES_NUM_BIT)
        TTMSPageInfo<Movies> findMoviesByType(@PathVariable("num") Integer num,
                                                     @PathVariable("bit") Integer bit,@RequestHeader("Authorization") String authorization);

        @GetMapping(MoviesConstants.PREFIX + MoviesConstants.TYPE_MOVIES_NUM_BIT_DETAILS)
        TTMSPageInfo<Movies> findMoviesByTypeAndDetails(@PathVariable("num") Integer num,
                                                               @PathVariable("bit") Integer bit,@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_STATUS_FOR_HOT)
    public TTMSPageInfo<Movies> getMoviesByHotForFeign(@PathVariable("num") Integer num,
                                                        @RequestHeader("Authorization") String authorization);

    @PostMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_SEARCH_FOR_NAME)
    public List<Movies> searchAllMovies(@RequestParam("name") String name,
                                        @RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_TIME_AFTER)
    public TTMSPageInfo<Movies> getMoviesAfterForTime(@PathVariable(value = "num") Integer num
            ,@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_STATUS_FOR_HOT_DETAILED)
    @ApiOperation("热销电影详情返回(num代表页数)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByHotForDetailed(@PathVariable("num") Integer num
            ,@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_STATUS_FOR_ALL)
    public TTMSPageInfo<Movies> getMoviesByStatus(@PathVariable("num") Integer num,
                                                  @PathVariable("status") Integer status
            ,@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_STATUS_FOR_ALL_DETAILED)
    public TTMSPageInfo<Movies> getMoviesByStatusOfDetailed(@PathVariable("num") Integer num,
                                                            @PathVariable("status") Integer status
            ,@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesConstants.PREFIX + MoviesConstants.MOVIES_VOL_DESC)
    public TTMSPageInfo<Movies> getAllForVolDesc(@PathVariable(value = "num") Integer num,
                                                 @RequestHeader("Authorization") String authorization);

        //管理员的



    /**
     * 电影订单的
     */
    @GetMapping(MoviesCartConstants.PREFIX + MoviesCartConstants.MOVIES_CART_PAGE)
    public TTMSPageInfo<MoviesCart> getMoviesCartList(@PathVariable("num") Integer num,@RequestHeader("Authorization") String authorization);

    @PutMapping(MoviesCartConstants.PREFIX + MoviesCartConstants.MOVIES_CART_STATUS)
    public HttpMessage updateStatus(@RequestParam("moviesCartId") Long moviesCartId,
                                    @RequestParam("status") Byte status,@RequestHeader("Authorization") String authorization);

    @PostMapping(MoviesCartConstants.PREFIX + MoviesCartConstants.DEFAULT_MOVIES)
    public HttpMessage insertMoviesCart(@RequestParam("moviesCart") String moviesCart,
                                        @RequestHeader("Authorization") String authorization);

    @DeleteMapping(MoviesCartConstants.PREFIX + MoviesCartConstants.MOVIES_CART_DELETE)
    public HttpMessage deleteMoviesCart(@PathVariable("moviesId") Long moviesId,@RequestHeader("Authorization") String authorization);

    //管理员的


    /**
     * 电影计划的
     */

    @GetMapping(MoviesPlanConstants.PREFIX + MoviesPlanConstants.MOVIES_PLAN_OF_DAY)
    public List<MoviesPlan> getAllMoviesPlan(@PathVariable("moviesId") Long moviesId,
                                             @PathVariable("day")Integer day,@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesPlanConstants.PREFIX + MoviesPlanConstants.MOVIES_FIND_ONE_PLAN)
    public PlanAndLockSeats findMoviesPlan(@PathVariable("planId") Long planId,@RequestHeader("Authorization") String authorization);

    //管理员
    @PostMapping(MoviesPlanConstants.PREFIX + MoviesPlanConstants.DEFAULT_MOVIES_PLAN)
    public HttpMessage insertMoviesPlan(@RequestParam("moviesPlan") String moviesPlan,
                                        @RequestHeader("Authorization") String authorization);

    @PutMapping(MoviesPlanConstants.PREFIX + MoviesPlanConstants.DEFAULT_MOVIES_PLAN)
    public HttpMessage updateMoviesPlan(@RequestParam("moviesPlan") String moviesPlan,@RequestHeader("Authorization") String authorization);

    @DeleteMapping(MoviesPlanConstants.PREFIX + MoviesPlanConstants.MOVIES_DELETE_PLAN)
    public HttpMessage deleteMoviesPlan(@PathVariable("moviesId")Long moviesId,@RequestHeader("Authorization") String authorization);

    /**
     * 电影座位的
     */
    @PostMapping(MoviesSeatConstants.PREFIX + MoviesSeatConstants.DEFAULT_MOVIES_SEAT)
    public HttpMessage lockSeats(@RequestParam("seatList") String seatList,@RequestHeader("Authorization") String authorization);

    //管理员
    @PutMapping(MoviesSeatConstants.PREFIX + MoviesSeatConstants.MOVIES_SEAT_OK)
    public HttpMessage updateMoviesSeatForOk(@RequestParam("seatList") String seatList,@RequestHeader("Authorization") String authorization);

    @PutMapping(MoviesSeatConstants.PREFIX + MoviesSeatConstants.MOVIES_SEAT_NOT_ALL)
    public HttpMessage updateMoviesSeatForNotAll(@RequestParam("seatList") String seatList
            ,@RequestHeader("Authorization") String authorization);

    @PutMapping(MoviesSeatConstants.PREFIX + MoviesSeatConstants.MOVIES_SEAT_BED)
    public HttpMessage updateMoviesSeatForBed(@RequestParam("seatList") String seatList,@RequestHeader("Authorization") String authorization);

    @PostMapping(MoviesSeatConstants.PREFIX + MoviesSeatConstants.MOVIES_SEAT_NOT_LOCK)
    public HttpMessage notLock(@RequestParam("msg") String msg,
                               @RequestHeader("Authorization") String authorization);

    /**
     * 电影类型
     */
    @GetMapping(MoviesTypeConstants.PREFIX + MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    //@ApiOperation(value = "电影类别查询服务")
    public List<MoviesType> getMoviesTypeList(@RequestHeader("Authorization") String authorization);

    //管理员

    @PostMapping(MoviesTypeConstants.PREFIX + MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    public HttpMessage insertMoviesType(@RequestParam("moviesType") String moviesType,@RequestHeader("Authorization") String authorization);

    @DeleteMapping(MoviesTypeConstants.PREFIX + MoviesTypeConstants.ID_DEFAULT_MOVIES_TYPE)
    public HttpMessage deleteMoviesType(@PathVariable("id") Long id,@RequestHeader("Authorization") String authorization);

    @PutMapping(MoviesTypeConstants.PREFIX + MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    public HttpMessage updateMoviesType(@RequestParam("moviesType") String moviesType,@RequestHeader("Authorization") String authorization);

    /**
     * 电影厅
     */

    //管理员
    @GetMapping(MoviesHallConstants.PREFIX + MoviesHallConstants.DEFAULT_MOVIES_Hall)
    public List<MoviesHall> getMoviesHallList(@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesHallConstants.PREFIX+"/hello")
    public List<MoviesHall> get(@RequestHeader("Authorization") String authorization);

    @GetMapping(MoviesHallConstants.PREFIX + MoviesHallConstants.ID_DEFAULT_MOVIES_TYPE)
    public MoviesHall queryByHallId(@PathVariable("id") Long id,@RequestHeader("Authorization") String authorization);

    @DeleteMapping(MoviesHallConstants.PREFIX + MoviesHallConstants.ID_DEFAULT_MOVIES_TYPE)
    public HttpMessage deleteMoviesHall(@PathVariable("id") Long id,@RequestHeader("Authorization") String authorization);

    @PutMapping(MoviesHallConstants.PREFIX + MoviesHallConstants.DEFAULT_MOVIES_Hall)
    public HttpMessage updateMoviesHall(@RequestParam("moviesHall") String moviesHall,@RequestHeader("Authorization") String authorization);

    @PostMapping(MoviesHallConstants.PREFIX + MoviesHallConstants.DEFAULT_MOVIES_Hall)
    public HttpMessage addMoviesHall(@RequestParam("moviesHall") String moviesHall,@RequestHeader("Authorization") String authorization);

    @GetMapping("/movies/hello")
    public PlanAndLockSeats test();
}
