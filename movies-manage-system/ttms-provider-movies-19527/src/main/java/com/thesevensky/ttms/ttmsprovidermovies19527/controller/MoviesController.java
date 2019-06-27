package com.thesevensky.ttms.ttmsprovidermovies19527.controller;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MovieStatues;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSearchService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MoviesConstants.PREFIX)
public class MoviesController {

    @Autowired
    private MoviesService moviesService = null;

    @Autowired
    private MoviesSearchService moviesSearchService = null;

    @GetMapping(MoviesConstants.ALL_MOVIES_AND_NUM)
    @ApiOperation("返回所有的电影(占位符为页数,1代表第一页)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getAllMovies(@PathVariable(value = "num") Integer num) {
        return moviesService.queryAll(num, null);
    }

    @PostMapping(MoviesConstants.MOVIES_SEARCH_FOR_NAME)
    @ApiOperation("按照名字进行分布式查询电影")
    @PreAuthorize("permitAll()")
    public List<Movies> searchAllMovies(String name) {
        return moviesSearchService.searchMoviesForName(name);
    }

//    @GetMapping("/num")
//    @ApiOperation("返回电影有几页 最好先进行查询")
//    public Integer getNum() {
//        return moviesService.getNum();
//    }


    @GetMapping(MoviesConstants.ALL_MOVIES_NUM_DETAILS)
    @ApiOperation("返回所有的电影的详细信息(占位符为页数,1代表第一页)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getAllMoviesAndDetails(@PathVariable(value = "num") Integer num) {
        return moviesService.queryAllOfDetailed(num, null);
    }

    @GetMapping(MoviesConstants.MOVIES_VOL_DESC)
    @ApiOperation("返回销量排序的电影 num是页数")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getAllForVolDesc(@PathVariable(value = "num") Integer num) {
        return moviesService.queryAllByVolDesc(num);
    }


    @GetMapping(MoviesConstants.ONE_MOVIE_ID)
    @ApiOperation("返回单个的电影的详细信息(占位符是电影的id)")
    @PreAuthorize("permitAll()")
    public Movies getMoviesById(@PathVariable("id") Long id) {
        return moviesService.queryByIdOfDetailed(id);
    }

    @GetMapping(MoviesConstants.TYPE_MOVIES_NUM_BIT)
    @ApiOperation("按某个类型返回电影集合(第一个是页数,第二个是类型的位操作)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByType(@PathVariable("num") Integer num,
            @PathVariable("bit") Integer bit) {
        return moviesService.queryByTypeBit(bit, num, null);
    }


    @GetMapping(MoviesConstants.TYPE_MOVIES_NUM_BIT_DETAILS)
    @ApiOperation("按某个类型返回电影详情集合(第一个是页数,第二个是类型的位操作)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByTypeAndDetails(@PathVariable("num") Integer num,
                                        @PathVariable("bit") Integer bit) {
        return moviesService.queryByTypeBitOfDetailed(bit, num, null);
    }

    @GetMapping(MoviesConstants.MOVIES_STATUS_FOR_HOT)
    @ApiOperation("热销电影返回(num代表页数)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByHot(@PathVariable("num") Integer num) {
        return moviesService.queryByStatus(num, MovieStatues.OnHost.getState());
    }

    @GetMapping(MoviesConstants.MOVIES_TIME_AFTER)
    @ApiOperation("返回所有即将上映的电影 占位符表示页数,1代表第一页)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesAfterNow(@PathVariable(value = "num") Integer num) {
        return moviesService.queryAllByAfterTime(num);
    }

    @GetMapping(MoviesConstants.MOVIES_STATUS_FOR_HOT_DETAILED)
    @ApiOperation("热销电影详情返回(num代表页数)")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByHotForDetailed(@PathVariable("num") Integer num) {
        return moviesService.queryByStatusOfDetailed(num, MovieStatues.OnHost.getState());
    }

    @GetMapping(MoviesConstants.MOVIES_STATUS_FOR_ALL)
    @ApiOperation("按照电影状态返回电影")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByStatus(@PathVariable("num") Integer num,
                                                  @PathVariable("status") Integer status) {
        return moviesService.queryByStatus(num, status);
    }

    @GetMapping(MoviesConstants.MOVIES_STATUS_FOR_ALL_DETAILED)
    @ApiOperation("按照电影状态返回电影详情")
    @PreAuthorize("permitAll()")
    public TTMSPageInfo<Movies> getMoviesByStatusOfDetailed(@PathVariable("num") Integer num,
                                                  @PathVariable("status") Integer status) {
        return moviesService.queryByStatusOfDetailed(num, status);
    }



//    @PostMapping(MoviesConstants.DEFAULT_MOVIES)
//    @ApiOperation("添加电影")
//    @PreAuthorize("hasAnyRole('root','admin','hallAdmin')")
//    public HttpMessage addMovies(Movies movies) {
//        moviesService.insertMovies(movies);
//        return new HttpMessage(HttpMsgStatus.INSERT_MSG_OK);
//    }
//
//    @DeleteMapping(MoviesConstants.ID_DEFAULT_MOVIES)
//    @ApiOperation("删除电影(占位符是电影的id)")
//    @PreAuthorize("hasAnyRole('root','admin','hallAdmin')")
//    public HttpMessage deleteMovies(@PathVariable("id") Integer id) {
//        moviesService.deleteMovies(id);
//        return new HttpMessage(HttpMsgStatus.DELETE_MSG_OK);
//    }
//
//    @PutMapping(MoviesConstants.DEFAULT_MOVIES)
//    @ApiOperation("修改电影")
//    @PreAuthorize("hasAnyRole('root','admin','hallAdmin')")
//    public HttpMessage updateMovies(Movies movies) {
//        moviesService.updateMovies(movies);
//        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
//    }

}
