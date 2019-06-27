package com.thesevensky.ttms.ttmsprovidermovies19527.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesTypeConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;

import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(MoviesTypeConstants.PREFIX)
public class MoviesTypeController {
    @Autowired
    private MoviesTypeService moviesTypeService = null;

    @Autowired
    private ObjectMapper objectMapper = null;

    @GetMapping(MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别查询服务")
    @PreAuthorize("permitAll()")
    public List<MoviesType> getMoviesTypeList() {
        return moviesTypeService.queryAll();
    }

    @PostMapping(MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别增加服务")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage insertMoviesType(String moviesType) throws IOException {
        moviesTypeService.insertMoviesType(objectMapper.readValue(moviesType,MoviesType.class));
        return new HttpMessage(HttpMsgStatus.INSERT_MSG_OK);
    }

    @DeleteMapping(MoviesTypeConstants.ID_DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别删除服务(占位符是要删除的电影id)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage deleteMoviesType(@PathVariable("id") Long id) {
        moviesTypeService.deleteById(id);
        return new HttpMessage(HttpMsgStatus.DELETE_MSG_OK);
    }

    @PutMapping(MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别修改服务")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesType(String moviesType) throws IOException {
        moviesTypeService.updateMoviesType(objectMapper.readValue(moviesType,MoviesType.class));
        return new HttpMessage(HttpMsgStatus.UPDATE_MSG_OK);
    }

}
