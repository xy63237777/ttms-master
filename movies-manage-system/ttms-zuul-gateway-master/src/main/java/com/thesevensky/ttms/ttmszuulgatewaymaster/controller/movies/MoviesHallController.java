package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.movies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesHallConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesHall;

import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping(MoviesHallConstants.PREFIX)
public class MoviesHallController {
    @Autowired
    private MoviesClientService moviesClientService = null;

    @Autowired
    private ObjectMapper objectMapper = null;

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesHallConstants.DEFAULT_MOVIES_Hall)
    @ApiOperation("得到电影厅的集合")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public List<MoviesHall> getMoviesHallList(
            HttpServletRequest request) {
        System.out.println();
        return moviesClientService.getMoviesHallList(request.getHeader("Authorization"));

    }
    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesHallConstants.ID_DEFAULT_MOVIES_TYPE)
    @ApiOperation("得到单个电影厅(id为唯一约束)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public MoviesHall queryById(@PathVariable("id") Long id,
                                HttpServletRequest request) {
        return moviesClientService.queryByHallId(id
                ,request.getHeader("Authorization"));
    }

    @DeleteMapping(ClientConstants.CLENT_PREFIX + MoviesHallConstants.ID_DEFAULT_MOVIES_TYPE)
    @ApiOperation("删除单个电影厅(id为唯一约束)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage deleteMoviesHall(@PathVariable("id") Long id,
                                        HttpServletRequest request) {
        return moviesClientService.deleteMoviesHall(id
                ,request.getHeader("Authorization"));
    }

    @PutMapping(ClientConstants.CLENT_PREFIX + MoviesHallConstants.DEFAULT_MOVIES_Hall)
    @ApiOperation("修改单个电影厅")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesHall(@RequestBody MoviesHall moviesHall,
                                        HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.updateMoviesHall(objectMapper.writeValueAsString(moviesHall)
                ,request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX + MoviesHallConstants.DEFAULT_MOVIES_Hall)
    @ApiOperation("添加单个电影厅")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage addMoviesHall(@RequestBody MoviesHall moviesHall,
                                     HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.addMoviesHall(objectMapper.writeValueAsString(moviesHall)
                ,request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + "hello")
    @ApiOperation("测试")
    @PreAuthorize("permitAll()")
    public List<MoviesHall> get(HttpServletRequest request) throws JsonProcessingException {
        System.out.println(request.getHeader("Authorization"));
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return moviesClientService.get(request.getHeader("Authorization"));
    }
}
