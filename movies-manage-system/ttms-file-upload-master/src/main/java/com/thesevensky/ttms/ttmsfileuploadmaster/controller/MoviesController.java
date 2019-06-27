package com.thesevensky.ttms.ttmsfileuploadmaster.controller;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.ttmsfileuploadmaster.service.MoviesService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(MoviesConstants.PREFIX)
public class MoviesController {

    @Autowired
    private MoviesService moviesService = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(MoviesConstants.ALL_MOVIES_AND_NUM)
    @ApiOperation("返回所有的电影(占位符为页数,1代表第一页)")
    public TTMSPageInfo<Movies> getAllMovies(@PathVariable(value = "num") Integer num) {
        return moviesService.queryAll(num, null);
    }


//    @GetMapping("/num")
//    @ApiOperation("返回电影有几页 最好先进行查询")
//    public Integer getNum() {
//        return moviesService.getNum();
//    }


    @GetMapping(MoviesConstants.ALL_MOVIES_NUM_DETAILS)
    @ApiOperation("返回所有的电影的详细信息(占位符为页数,1代表第一页)")
    public TTMSPageInfo<Movies> getAllMoviesAndDetails(@PathVariable(value = "num") Integer num) {
        return moviesService.queryAllOfDetailed(num, null);
    }

    @GetMapping(MoviesConstants.ONE_MOVIE_ID)
    @ApiOperation("返回单个的电影的详细信息(占位符是电影的id)")
    public Movies getMoviesById(@PathVariable("id") Long id) {
        return moviesService.queryById(id);
    }

    @GetMapping(MoviesConstants.TYPE_MOVIES_NUM_BIT)
    @ApiOperation("按某个类型返回电影集合(第一个是页数,第二个是类型的位操作)")
    public TTMSPageInfo<Movies> getMoviesByType(@PathVariable("num") Integer num,
            @PathVariable("bit") Integer bit) {
        return moviesService.queryByTypeBit(bit, num, null);
    }


    @GetMapping(MoviesConstants.TYPE_MOVIES_NUM_BIT_DETAILS)
    @ApiOperation("按某个类型返回电影详情集合(第一个是页数,第二个是类型的位操作)")
    public TTMSPageInfo<Movies> getMoviesByTypeAndDetails(@PathVariable("num") Integer num,
                                        @PathVariable("bit") Integer bit) {
        return moviesService.queryByTypeBitOfDetailed(bit, num, null);
    }


    @PostMapping(MoviesConstants.DEFAULT_MOVIES)
    @ApiOperation("添加电影")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage addMovies(@RequestBody Movies movies) {
        HttpMessage httpMessage = new HttpMessage("200",HttpMsgStatus.UPDATE_MSG_OK);
        System.out.println(movies);
        try{
            moviesService.insertMovies(movies);
        } catch (Exception ex) {
            logger.info("添加电影失败 --- " + ex.getMessage());
            httpMessage.setCode("500");
            httpMessage.setMsg(ex.getMessage());
        }
        return httpMessage;
    }

    @DeleteMapping(MoviesConstants.ID_DEFAULT_MOVIES)
    @ApiOperation("删除电影(占位符是电影的id)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage deleteMovies(@PathVariable("id") Long id) {
        HttpMessage httpMessage = new HttpMessage("200",HttpMsgStatus.DELETE_MSG_OK);
        try{
            moviesService.deleteMovies(id);
        } catch (Exception e) {
            logger.info("删除电影出错 --- " + e.getMessage());
            httpMessage.setCode("400");
            httpMessage.setMsg(e.getMessage());
        }
        return httpMessage;
    }

    @PutMapping(MoviesConstants.DEFAULT_MOVIES)
    @ApiOperation("修改电影")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMovies(@RequestBody Movies movies) {
        System.out.println(movies);
        HttpMessage httpMessage = new HttpMessage("200", HttpMsgStatus.UPDATE_MSG_OK);
        try {
            if (movies.getMovieId() == null) throw new Exception("传入的电影ID为null");
            moviesService.updateMovies(movies);
        }  catch (Exception ex) {
            logger.info("添加电影出错 --- " + ex.getMessage());
            httpMessage.setCode("500");
            httpMessage.setMsg(ex.getMessage());
        }
        return httpMessage;
    }

    @PutMapping(MoviesConstants.MOVIES_UPDATE_IMAGE)
    @ApiOperation("修改电影的图片使用formData")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesForImage(@PathVariable("id") Long id,
                                            MultipartFile file) {
        HttpMessage httpMessage = new HttpMessage("200","id");
        try{
            logger.info("进入到修改图片接口");
            String s = moviesService.updateMoviesForDefaultImage(id, file);
            httpMessage.setMsg(s);
        } catch (Exception e) {
            e.printStackTrace();
            httpMessage.setMsg("500");
            httpMessage.setCode("上传电影失败");
        }
        return httpMessage;
    }

}
