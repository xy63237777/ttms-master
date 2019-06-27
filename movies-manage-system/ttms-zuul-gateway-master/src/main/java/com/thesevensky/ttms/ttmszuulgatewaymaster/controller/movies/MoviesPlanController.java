package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.movies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.PlanAndLockSeats;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSTimeUtils;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;
import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.dto.MoviesAndPlanId;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/1 15:03
 * @Version 1.0
 */
@RestController
@RequestMapping(value = MoviesPlanConstants.PREFIX)
public class MoviesPlanController {

    @Autowired
    private ObjectMapper objectMapper = null;

    @Autowired
    private MoviesClientService moviesClientService = null;

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesPlanConstants.MOVIES_PLAN_OF_DAY)
    @PreAuthorize("permitAll()")
    @ApiOperation(value = "得到某个电影某一天的所有演出计划今天的day是0 明天的day是1 后天是2")
    public List<MoviesPlan> getAllMoviesPlan(@PathVariable("moviesId") Long moviesId,
                                             @PathVariable("day")Integer day,
                                             HttpServletRequest request) {
        return moviesClientService.getAllMoviesPlan(moviesId, day
                ,request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX +MoviesPlanConstants.MOVIES_FIND_ONE_PLAN)
    @PreAuthorize("permitAll()")
    @ApiOperation(value = "查询某个演出计划的详细信息")
    public PlanAndLockSeats findMoviesPlan(@PathVariable("planId") Long planId,
                                           HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.findMoviesPlan(planId
                ,request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX + MoviesPlanConstants.DEFAULT_MOVIES_PLAN)
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    @ApiOperation(value = "添加一个演出计划")
    public HttpMessage insertMoviesPlan(@RequestBody MoviesPlan moviesPlan,
                                        HttpServletRequest request) throws JsonProcessingException {
        System.out.println(moviesPlan);
        if (moviesPlan.getMoviePlanStartTime() < TTMSTimeUtils.getLocalDay())
            return new HttpMessage("415", "演出计划开始的时间 必须在未来");
        return moviesClientService.insertMoviesPlan(objectMapper.writeValueAsString(moviesPlan)
                , request.getHeader("Authorization"));
    }

    @PutMapping(ClientConstants.CLENT_PREFIX + MoviesPlanConstants.DEFAULT_MOVIES_PLAN)
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    @ApiOperation(value = "更新演出计划")
    public HttpMessage updateMoviesPlan(@RequestBody MoviesPlan moviesPlan,
                                        HttpServletRequest request) throws JsonProcessingException {
        return moviesClientService.updateMoviesPlan(objectMapper.writeValueAsString(moviesPlan)
                ,request.getHeader("Authorization"));
    }

    @DeleteMapping(ClientConstants.CLENT_PREFIX + MoviesPlanConstants.MOVIES_DELETE_PLAN)
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    @ApiOperation(value = "删除演出计划")
    public HttpMessage deleteMoviesPlan(@PathVariable("moviesId")Long moviesId,
                                        HttpServletRequest request) {
        return moviesClientService.deleteMoviesPlan(moviesId
                ,request.getHeader("Authorization"));
    }
}
