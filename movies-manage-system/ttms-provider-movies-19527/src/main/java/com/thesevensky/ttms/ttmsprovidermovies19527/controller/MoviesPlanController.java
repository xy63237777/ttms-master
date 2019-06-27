package com.thesevensky.ttms.ttmsprovidermovies19527.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.PlanAndLockSeats;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSTimeUtils;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesPlanService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/28 11:32
 * @Version 1.0
 */
@RestController
@RequestMapping(MoviesPlanConstants.PREFIX)
public class MoviesPlanController {

    @Autowired
    private MoviesPlanService moviesPlanService = null;

    @Autowired
    private ExecutorService executorService = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @Autowired
    private ObjectMapper objectMapper = null;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @GetMapping(MoviesPlanConstants.MOVIES_PLAN_OF_DAY)
    @PreAuthorize("permitAll()")
    public List<MoviesPlan> getAllMoviesPlan(@PathVariable("moviesId") Long moviesId,
                                             @PathVariable("day")Integer day) {
        long nowTime = TTMSTimeUtils.getLocalDay();
        return moviesPlanService.queryAllByMoviesIdForTime(moviesId,
                nowTime + day, nowTime + day + 1);
    }

    @GetMapping(MoviesPlanConstants.MOVIES_FIND_ONE_PLAN)
    @PreAuthorize("permitAll()")
    //@PreAuthorize("permitAll()")
    public PlanAndLockSeats findMoviesPlan(@PathVariable("planId") Long planId) throws ExecutionException, InterruptedException, IOException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            return getPlanLockSeats(planId+"");
        });
        executorService.submit(futureTask);
        MoviesPlan moviesPlan = moviesPlanService.queryById(planId);
        return new PlanAndLockSeats(moviesPlan, futureTask.get());
    }

    @PostMapping(MoviesPlanConstants.DEFAULT_MOVIES_PLAN)
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage insertMoviesPlan(String moviesPlan) throws IOException {
        MoviesPlan moviesPlan1 = objectMapper.readValue(moviesPlan, MoviesPlan.class);
        HttpMessage httpMessage = new HttpMessage();
        try{
            moviesPlanService.insertMoviePlan(moviesPlan1);
            return new HttpMessage("200", moviesPlan1.getMoviePlanId() + "");
        } catch (Exception e) {
            httpMessage.setCode("500");
            httpMessage.setMsg( e.getMessage());
        }
        return httpMessage;
    }

    @PutMapping(MoviesPlanConstants.DEFAULT_MOVIES_PLAN)
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage updateMoviesPlan(String moviesPlan) throws IOException {
        MoviesPlan moviesPlan1 = objectMapper.readValue(moviesPlan, MoviesPlan.class);
        moviesPlanService.updateByMoviePlan(moviesPlan1);
        return new HttpMessage("200", moviesPlan1.getMoviePlanId() + "");
    }

    @DeleteMapping(MoviesPlanConstants.MOVIES_DELETE_PLAN)
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage deleteMoviesPlan(@PathVariable("moviesId")Long moviesId) {
        moviesPlanService.deleteById(moviesId);
        return new HttpMessage("200", moviesId + "");
    }

    private String getPlanLockSeats(String planId) {
        Set<String> set =(Set<String>) redisTemplate.opsForHash().get(MoviesSeatConstants.MOVIES_SEAT_FOR_REDIS,
                MoviesSeatConstants.MOVIES_SEAT_FOR_LOCK + planId);
        StringBuilder stringBuilder = new StringBuilder();
        if(set != null) {
            Iterator<String> iterator = set.iterator();
            while(iterator.hasNext()) {
                String str = iterator.next();
                String s = stringRedisTemplate.opsForValue().get(str);
                if(StringUtils.isNotEmpty(s)) {
                    System.out.println("进来了");
                    stringBuilder.append(s).append(",");
                } else {
                    iterator.remove();
                }
            }

            if(set.size() == 0) {
                redisTemplate.opsForHash().delete(MoviesSeatConstants.MOVIES_SEAT_FOR_REDIS,MoviesSeatConstants.MOVIES_SEAT_FOR_LOCK + planId);
            } else {
                redisTemplate.opsForHash().put(MoviesSeatConstants.MOVIES_SEAT_FOR_REDIS,MoviesSeatConstants.MOVIES_SEAT_FOR_LOCK + planId,set);
            }
        }
        return stringBuilder.toString().substring(0, Math.max(0, stringBuilder.length() - 1));
    }

}
