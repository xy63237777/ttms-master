package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.movies;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesTypeConstants;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MovieStatues;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;
import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(MoviesConstants.PREFIX)
public class MoviesController {

    @Autowired
    private MoviesClientService moviesClientService = null;

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.ALL_MOVIES_AND_NUM)
    @ApiOperation("返回所有的电影(占位符为页数,1代表第一页)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getAllMovies(@PathVariable(value = "num") Integer num,
                                             HttpServletRequest request) {
        return moviesClientService.findMovies(num,request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_TIME_AFTER)
    @ApiOperation("返回所有即将上映的电影 占位符表示页数,1代表第一页)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesAfterNow(@PathVariable(value = "num") Integer num,
                                                  HttpServletRequest request) {
        return moviesClientService.getMoviesAfterForTime(num,
                request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_STATUS_FOR_HOT_DETAILED)
    @ApiOperation("热销电影详情返回(num代表页数)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByHotForDetailedClient(@PathVariable("num") Integer num,
                                                                HttpServletRequest request) {
        return moviesClientService.getMoviesByHotForDetailed(num,
                request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_VOL_DESC)
    @ApiOperation("返回销量排序的电影 num是页数")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getAllForVolDesc(@PathVariable(value = "num") Integer num,
                                                 HttpServletRequest request) {
        return moviesClientService.getAllForVolDesc(num,
                request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_STATUS_FOR_ALL)
    @ApiOperation("按照电影状态返回电影")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByStatusClient(@PathVariable("num") Integer num,
                                                  @PathVariable("status") Integer status,
                                                        HttpServletRequest request) {
        return moviesClientService.getMoviesByStatus(num,status,
                request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_STATUS_FOR_ALL_DETAILED)
    @ApiOperation("按照电影状态返回电影详情")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByStatusOfDetailedClient(@PathVariable("num") Integer num,
                                                            @PathVariable("status") Integer status,
                                                                  HttpServletRequest request) {
        return moviesClientService.getMoviesByStatusOfDetailed(num, status,
                request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_SEARCH_FOR_NAME)
    @ApiOperation("按照名字进行分布式查询电影")
    @PreAuthorize("permitAll()")
    public List<Movies> searchAllMovie(String name,
                                       HttpServletRequest request) {
        return moviesClientService.searchAllMovies(name,request.getHeader("Authorization"));
    }


    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.ALL_MOVIES_NUM_DETAILS)
    @ApiOperation("返回所有的电影的详细信息(占位符为页数,1代表第一页)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getAllMoviesAndDetails(@PathVariable(value = "num") Integer num,HttpServletRequest request) {
        return moviesClientService.findMoviesDetails(num,request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.ONE_MOVIE_ID)
    @ApiOperation("返回单个的电影的详细信息(占位符是电影的id)")
    @PreAuthorize("permitAll()")
    public Movies getMoviesById(@PathVariable("id") Long id,
                                HttpServletRequest request) {
        return moviesClientService.findMovieJustOne(id,request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.TYPE_MOVIES_NUM_BIT)
    @ApiOperation("按某个类型返回电影集合(第一个是页数,第二个是类型的位操作)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByType(@PathVariable("num") Integer num,
                                        @PathVariable("bit") Integer bit,
                                                HttpServletRequest request) {
        return moviesClientService.findMoviesByType(num, bit,request.getHeader("Authorization"));
    }


    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.TYPE_MOVIES_NUM_BIT_DETAILS)
    @ApiOperation("按某个类型返回电影详情集合(第一个是页数,第二个是类型的位操作)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByTypeAndDetails(@PathVariable("num") Integer num,
                                                  @PathVariable("bit") Integer bit,
                                                          HttpServletRequest request) {
        return moviesClientService.findMoviesByTypeAndDetails(num, bit,request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesConstants.MOVIES_STATUS_FOR_HOT)
    @ApiOperation("热销电影返回(num代表页数)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByHot(@PathVariable("num") Integer num, HttpServletRequest request) {
        return moviesClientService.getMoviesByHotForFeign(num,request.getHeader("Authorization"));
    }

}
