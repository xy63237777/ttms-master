package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.movies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesTypeConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(MoviesTypeConstants.PREFIX)
public class MoviesTypeController {
    @Autowired
    private MoviesClientService moviesClientService = null;

    @Autowired
    private ObjectMapper objectMapper = null;

    @GetMapping(MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别查询服务")
    @PreAuthorize("permitAll()")
    public List<MoviesType> getMoviesTypeList(
            HttpServletRequest request) {
        return moviesClientService.getMoviesTypeList(request.getHeader("Authorization"));
    }

    @PostMapping(MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别增加服务")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage insertMoviesType(@RequestBody MoviesType moviesType,
                                        HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.insertMoviesType(objectMapper.writeValueAsString(moviesType)
                ,request.getHeader("Authorization"));
    }

    @DeleteMapping(MoviesTypeConstants.ID_DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别删除服务(占位符是要删除的电影id)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage deleteMoviesType(@PathVariable("id") Long id,
                                        HttpServletRequest request) {
        return moviesClientService.deleteMoviesType(id
                ,request.getHeader("Authorization"));
    }

    @PutMapping(MoviesTypeConstants.DEFAULT_MOVIES_TYPE)
    @ApiOperation(value = "电影类别修改服务")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesType(@RequestBody MoviesType moviesType,
                                        HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.updateMoviesType(objectMapper.writeValueAsString(moviesType)
                ,request.getHeader("Authorization"));
    }

}
