package com.thesevensky.ttms.ttmsprovidermovies19527.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesHallConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesHall;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesHallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

@RestController
@RequestMapping(MoviesHallConstants.PREFIX)
public class MoviesHallController {
    @Autowired
    private MoviesHallService moviesHallService = null;

    @Autowired
    private ObjectMapper objectMapper = null;

    @GetMapping(MoviesHallConstants.DEFAULT_MOVIES_Hall)
    @ApiOperation("得到电影厅的集合")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    //@PreAuthorize("permitAll()")
    public List<MoviesHall> getMoviesHallAll() {
        return moviesHallService.queryAll();
    }

    @GetMapping(MoviesHallConstants.ID_DEFAULT_MOVIES_TYPE)
    @ApiOperation("得到单个电影厅(id为唯一约束)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    //@PreAuthorize("permitAll()")
    public MoviesHall queryById(@PathVariable("id") Long id) {

        return moviesHallService.queryById(id);
    }

    @DeleteMapping(MoviesHallConstants.ID_DEFAULT_MOVIES_TYPE)
    @ApiOperation("删除单个电影厅(id为唯一约束)")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage deleteMoviesHall(@PathVariable("id") Long id) {
        moviesHallService.deleteMoviesHall(id);
        return new HttpMessage("200",id + "");
    }

    @PutMapping(MoviesHallConstants.DEFAULT_MOVIES_Hall)
    @ApiOperation("修改单个电影厅")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    //@PreAuthorize("permitAll()")
    public HttpMessage updateMoviesHall(String moviesHall) throws IOException {
        MoviesHall moviesHall1 = objectMapper.readValue(moviesHall, MoviesHall.class);
        moviesHallService.updateMoviesHall(moviesHall1);
        return new HttpMessage("200",moviesHall1.getMovieHallId() + "");
    }

    @PostMapping(MoviesHallConstants.DEFAULT_MOVIES_Hall)
    @ApiOperation("添加单个电影厅")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    //@PreAuthorize("permitAll()")
    public HttpMessage addMoviesHall(String moviesHall) throws IOException {
        MoviesHall moviesHall1 = objectMapper.readValue(moviesHall, MoviesHall.class);
        HttpMessage httpMessage = new HttpMessage();
        try{
            Integer integer = moviesHallService.cmpInsert();
            if (integer > 10) throw new MoviesException("影厅已经达到上限 不能超过10个");
            moviesHallService.insertMoviesHall(moviesHall1);
            httpMessage.setMsg(moviesHall1.getMovieHallId()+"");
        } catch (Exception e){
            httpMessage.setCode("400");
            httpMessage.setMsg(e.getMessage());
        }
        return httpMessage;
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public List<MoviesHall> moviesHalls(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return moviesHallService.queryAll();
    }
}
