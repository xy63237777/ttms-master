package com.thesevensky.ttms.ttmsprovidermovies19527.controller;


import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.PlanAndLockSeats;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.*;
import com.thesevensky.ttms.ttmsprovidermovies19527.commons.advice.DistributiveRedisLock;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesHallService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesPlanService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSeatService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    @Autowired
    private HelloController helloController = null;

//    @Autowired
//    private Redisson redisson = null;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private MasterProperties masterProperties = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @Autowired
    private MoviesSeatService moviesSeatService = null;

    @Autowired
    private MoviesHallService moviesHallService = null;

    @Autowired
    private MoviesService moviesService = null;

    @Autowired
    private MoviesPlanService moviesPlanService = null;

    @Autowired
    private MoviesPlanController moviesPlanController = null;

//    @GetMapping("/movies/hello")
//    //@PreAuthorize("permitAll()")
//    public PlanAndLockSeats hello() throws InterruptedException, ExecutionException {
//        return moviesPlanController.findMoviesPlan(moviesService.queryById(1),1);
//    }


    @GetMapping("/abc")
    @PreAuthorize("permitAll()")
    public String abc() {
        Movies movies = moviesService.queryById(3L);
        System.out.println(movies);
        movies.setMovieBeginTime(System.currentTimeMillis());
        moviesService.updateMovies(movies);

        return "ok";
    }

    @GetMapping("/hall")
    @PreAuthorize("permitAll()")
    public String insert() {
        MoviesHall moviesHall = new MoviesHall();
        moviesHall.setMovieHallName("亚特兰蒂斯");
        List<MoviesSeat> moviesSeats = new ArrayList<>();
        for(int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                MoviesSeat moviesSeat = new MoviesSeat();
                moviesSeat.setMovieSeatX(i);
                moviesSeat.setMovieSeatY(j);
                moviesSeat.setMoviesHall(moviesHall);
                moviesSeats.add(moviesSeat);
            }
        }
        moviesHall.setMoviesSeats(moviesSeats);
        moviesHallService.insertMoviesHall(moviesHall);
        return "ok";
    }

    @GetMapping("/ttt/{day}")
    public List<MoviesPlan> moviesPlans(@PathVariable("day") Integer day) {
        return moviesPlanController.getAllMoviesPlan(1L, day);
    }

    @GetMapping("/plan")
    @PreAuthorize("permitAll()")
    public String insertPlan() {
        MoviesPlan moviesPlan = moviesPlanService.queryById(1L);
        moviesPlan.setMoviesHall(moviesHallService.queryById(7L));
        moviesPlanService.updateByMoviePlan(moviesPlan);
        return "ok";
    }

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello1() {
        SeatList seatList = new SeatList();
        List<Long> integers = new ArrayList<>();
        integers.add(2L);
        integers.add(4L);
        integers.add(7L);
        seatList.setIdList(integers);
        seatList.setBit(4);
        seatList.setPlanId(1L);
        return moviesSeatService.seatsForLock(seatList);
    }


    @Autowired
    private RestTemplate restTemplate = null;

    @RequestMapping("/res")
    public String res() {
        return restTemplate.getForObject("http://www.ttms.com:11111/hello", String.class);
    }

//    @DistributiveRedisLock("lock")
//    public void lockMoviesTicket() {
//        System.out.println(123);
//    }
}
