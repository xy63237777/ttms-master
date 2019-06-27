package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSTimeUtils;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MovieStatues;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesPlanDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesHallService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesPlanService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/22 20:48
 * @Version 1.0
 */
@Service
public class MoviesPlanServiceImpl implements MoviesPlanService {

    @Autowired
    private MasterProperties masterProperties = null;

    @Autowired
    private MoviesPlanDao moviesPlanDao = null;

    @Autowired
    private MoviesService moviesService = null;

    @Autowired
    private MoviesDao moviesDao = null;

    @Autowired
    private MoviesHallService moviesHallService = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Override
    public List<MoviesPlan> queryAllByMoviesId(Long moviesId) {
        return moviesPlanDao.queryAllByMoviesId(moviesId);
    }

    //private static final String CACHE_ONE = MoviesPlanConstants.MOVIES_PLAN_CACHE + "One";

    @Override
    @Cacheable(cacheNames = MoviesPlanConstants.MOVIES_PLAN_CACHE, key = "'cache_movies_plan_' + #moviesId +'_' +#leftTime + '-' + #rightTime ")
    public List<MoviesPlan> queryAllByMoviesIdForTime(Long moviesId, Long leftTime, Long rightTime) {
        return moviesPlanDao.queryAllByMoviesIdForTime(moviesId, leftTime, rightTime);
    }

    @Override
    @Cacheable(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #planId")
    public MoviesPlan queryById(Long planId) {
        MoviesPlan moviesPlan = moviesPlanDao.queryByIdForDetails(planId);
        moviesPlan.setMovies(moviesService.queryById(moviesPlan.getMovies().getMovieId()));
        moviesPlan.setMoviesHall(moviesHallService.queryById(moviesPlan.getMoviesHall().getMovieHallId()));
        Long moviePlanStartTime = moviesPlan.getMoviePlanStartTime();
        long localTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        int day = (int)(TTMSTimeUtils.getDay(moviePlanStartTime) - TTMSTimeUtils.getDay(localTime));
        moviesPlan.setMoviePlanNumber(masterProperties.getMovies().getNumber().getNumberOfDay() * day +
                moviesPlan.getMoviePlanNumber());
        return moviesPlan;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #id"),
            @CacheEvict(cacheNames = MoviesPlanConstants.MOVIES_PLAN_CACHE, allEntries = true)
    })
    public void deleteById(Long id) {
        MoviesPlan moviesPlan = moviesPlanDao.queryById(id);
        pushForRedis(TTMSTimeUtils.getDay(moviesPlan.getMoviePlanStartTime()) + ""
                ,moviesPlan.getMoviePlanNumber());
        moviesPlanDao.deleteById(id);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesPlanConstants.MOVIES_PLAN_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #moviesPlan.moviePlanId ")
            }
    )
    public void updateByMoviePlan(MoviesPlan moviesPlan) {
        moviesPlan.setMoviePlanEndTime(moviesPlan.getMoviePlanStartTime() +
                moviesPlan.getMovies().getMovieLength() * 1000 * 60);
        moviesPlanDao.updateByMoviePlan(moviesPlan);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE,allEntries = true),
                    @CacheEvict(cacheNames = MoviesPlanConstants.MOVIES_PLAN_CACHE,allEntries = true),
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE, key = "'cache_movie_' + #moviesPlan.movies.movieId"),
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE, key = "'cache_movie_delailed_' + #moviesPlan.movies.movieId")
            }
    )
    public void insertMoviePlan(MoviesPlan moviesPlan) {
        if(moviesPlanDao.cmpCanInsert(moviesPlan) != null) throw new MoviesException("该影厅该时间段有电影了");
        String numberKey = getNumberKey(moviesPlan.getMoviePlanStartTime());
        Integer number = getNumber(numberKey);
        moviesPlan.setMoviePlanNumber(number);
        moviesPlan.setMoviePlanEndTime(moviesPlan.getMoviePlanStartTime() +
                moviesPlan.getMovies().getMovieLength() * 1000 * 60);
        try{
            moviesPlanDao.insertMoviePlan(moviesPlan);
            moviesService.updateMoviesForStatus(moviesPlan.getMovies().getMovieId(), MovieStatues.OnHost.getState());
        } catch (Exception e) {
            logger.error("添加演出计划出现错误 --- " + e.getMessage());
            pushForRedis(numberKey,number);
            throw new RuntimeException(e);
        }

    }

    private void pushForRedis(String key, Integer integer) {
        List<Integer> list = (List<Integer>) redisTemplate.opsForHash()
                .get(masterProperties.getMovies().getNumber().getCacheName(), key);
        if(list != null) {
            list.add(integer);
            pushNumberList(key, list);
        }
    }

    private String getNumberKey(long time) {
        return "" + TTMSTimeUtils.getDay(time);
    }

    private Integer getNumber(String key) {
        List<Integer> list = (List<Integer>) redisTemplate.opsForHash()
                .get(masterProperties.getMovies().getNumber().getCacheName(), key);
        if(list == null) list = initNumberList();
        if(list.size() <= 0) throw new MoviesException("当天电影计划不可以再添加");
        Integer result = list.remove(0);
        pushNumberList(key, list);
        return result;
    }

    private List<Integer> initNumberList() {
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < masterProperties.getMovies().getNumber().getNumberOfDay(); i++) {
            list.add(i);
        }
        return list;
    }

    private void pushNumberList(String key, List<Integer> list) {
        redisTemplate.opsForHash().put(masterProperties.getMovies().getNumber().getCacheName(),
                key,list);
    }
}
