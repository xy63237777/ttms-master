package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.movies.MoviesSeatProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatus;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatusList;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesHallConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MoviesSeatStatues;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesSeat;
import com.thesevensky.ttms.ttmsprovidermovies19527.commons.advice.DaoForBath;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesSeatDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSeatService;

import com.thesevensky.ttms.ttmsprovidermovies19527.service.rabbitmq.HttpRabbitMqService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class MoviesSeatServiceImpl implements MoviesSeatService {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;

    @Autowired
    private MoviesSeatDao moviesSeatDao = null;

    @Autowired
    protected MasterProperties masterProperties = null;

    @Autowired
    private HttpRabbitMqService httpRabbitMqService = null;

    private static final String CACHE_ONE = MoviesSeatConstants.MOVIES_SEAT_CACHE + "One";

    @Override
    public List<MoviesSeat> queryAll() {
        return moviesSeatDao.queryAll();
    }

    @Override
    public MoviesSeat queryById(Long id) {
        return moviesSeatDao.queryById(id);
    }

    @Override
    @Cacheable(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #hallId")
    public List<MoviesSeat> queryByHallId(Long hallId) {
        return moviesSeatDao.queryByHallId(hallId);
    }

//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void deleteMoviesSeat(Long id) {
//        moviesSeatDao.deleteMoviesSeat(id);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #hallId")
//    public void deleteMoviesSeatByHall(Long hallId) {
//        moviesSeatDao.deleteMoviesSeatByHall(hallId);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void updateMoviesSeat(MoviesSeat moviesSeat) {
//        moviesSeatDao.updateMoviesSeat(moviesSeat);
//    }
//
//    @Override
//    public void updateMoviesNextDay(Integer bit) {
//        moviesSeatDao.updateMoviesNextDay(bit);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void insertMoviesSeat(MoviesSeat moviesSeat) {
//        moviesSeatDao.insertMoviesSeat(moviesSeat);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void updateSeatForBuy(Long id, Integer bit) {
//        moviesSeatDao.updateSeatForBuy(id,bit);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void updateSeatForNotBuy(Long id, Integer bit) {
//        moviesSeatDao.updateSeatForNotBuy(id, bit);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void updateSeatForOk(Long id, Integer bit) {
//        moviesSeatDao.updateSeatForOk(id, bit);
//    }
//
//    @Override
//    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true)
//    public void updateSeatForBed(Long id, Integer bit) {
//        moviesSeatDao.updateSeatForBed(id, bit);
//    }

    @Override
    @DaoForBath
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,allEntries = true),
                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true),
                    @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, allEntries = true)
//                    @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId"),
                    //@CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #seatList.planId")
            }
    )
    public Integer seatsForBuy(SeatList seatList,SqlSession sqlSession) {
        System.out.println("在这里 " + seatList);
        MoviesSeatDao mapper = sqlSession.getMapper(MoviesSeatDao.class);
        int count = 0;
            for (Long id : seatList.getIdList()) {
                count += mapper.updateSeatForBuy(id, seatList.getBit());
            }
        return count;
    }

//    @Override
//    @DaoForBath
//    @Caching(
//            evict = {@CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId")
//            }
//    )
@Override
@DaoForBath
@Caching(
        evict = {
                @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,allEntries = true),
                @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true),
                @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, allEntries = true)
//                    @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId"),
                //@CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #seatList.planId")
        }
)
    public Integer seatsForNotBuy(SeatList seatList,SqlSession sqlSession) {
    System.out.println("这里进来了");
        MoviesSeatDao mapper = sqlSession.getMapper(MoviesSeatDao.class);
        int count = 0;
        for (Long id : seatList.getIdList()) {
            count += mapper.updateSeatForNotBuy(id, seatList.getBit());
        }
        return count;
    }

//    @Override
//    @DaoForBath
//    @Caching(
//            evict = {@CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId")
//            }
//    )
@Override
@DaoForBath
@Caching(
        evict = {
                @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,allEntries = true),
                @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true),
                @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, allEntries = true)
//                    @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId"),
                //@CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #seatList.planId")
        }
)
    public Integer seatsForOk(SeatList seatList,SqlSession sqlSession) {
        MoviesSeatDao mapper = sqlSession.getMapper(MoviesSeatDao.class);
        int count = 0;
        for (Long id : seatList.getIdList()) {
            count += mapper.updateSeatForOk(id, seatList.getBit());
        }
        return count;
    }

//    @Override
//    @DaoForBath
//    @Caching(
//            evict = {@CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId")
//            }
//    )
@Override
@DaoForBath
@Caching(
        evict = {
                @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,allEntries = true),
                @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,allEntries = true),
                @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, allEntries = true)
//                    @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
//                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId"),
                //@CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #seatList.planId")
        }
)

    public Integer seatsForBed(SeatList seatList,SqlSession sqlSession) {
        MoviesSeatDao mapper = sqlSession.getMapper(MoviesSeatDao.class);
        int count = 0;
        for (Long id : seatList.getIdList()) {
            count += mapper.updateSeatForBed(id, seatList.getBit());
        }
        return count;
    }

    @Override
    @DaoForBath
    @Caching(
            evict = {@CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #seatList.hallId"),
                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE,key = "'cache_seat_hall_' + #seatList.hallId"),
                    @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, allEntries = true)
            }
    )
    public Integer seatsForNot(SeatAndStatusList seatList, SqlSession sqlSession) {
        MoviesSeatDao mapper = sqlSession.getMapper(MoviesSeatDao.class);
        int count = 0;
        for(SeatAndStatus seatAndStatus : seatList.getList()) {
            System.out.println(seatAndStatus);
            count += mapper.updateMoviesSeatStatus(seatAndStatus.getSeatId(), seatAndStatus.getStatus());
        }
        return count;
    }

    /**
     * 对座位进行锁定的逻辑
     */
    @Override
    public String seatsForLock(SeatList seatList) {
        System.out.println("锁定的座位 -- " + seatList);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringOfOK = new StringBuilder();
        MoviesSeatProperties seat = masterProperties.getMovies().getSeat();
        for(Long integer : seatList.getIdList()) {
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(seatList.getPlanId() + "-" + integer,
                    "1",seat.getTime(),seat.getTimeUnit());
            if(!flag) {
                stringBuilder.append(integer + ",");
            } else {
                stringOfOK.append(integer + ",");
            }
        }
        System.out.println("ok的 " + stringOfOK.toString());
        System.out.println("不OK的 " + stringBuilder.toString());
        StringBuilder result = new StringBuilder();
        result.append(stringBuilder.toString().substring(0, Math.max(0, stringBuilder.toString().length() - 1)));
        //13639558669$13&123123$2,5,6,3
        long l = System.currentTimeMillis();
        if(stringOfOK.length() >= 1) {
            sendMessage(MoviesSeatServiceHolder.principalKey + "$" +
                    seatList.getPlanId() + "&" + l +  "$" + stringOfOK.toString().substring(0,Math.max(0,stringOfOK.length()-1)));
        }
        //2,4,7#
        result.append("#" + MoviesSeatServiceHolder.principalKey + "_" + seatList.getPlanId() + "_" + l + "#" +
                stringOfOK.toString().substring(0,Math.max(0,stringOfOK.length()-1)));
        return result.toString();
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void seatsForNotLock(String str) {
        logger.info("取消锁定库存 ===> " + str);
        if(StringUtils.isNotEmpty(str)) {
            String[] arr = str.split("\\#");
            String[] tempArr = arr[1].split("_");
            if(!tempArr[0].equals(getPrincipal())) {
                logger.info("用户 " + getPrincipal() + " 没有权限取消锁定");
                return;
            }
            logger.info("有需要取消的库存 ===> " + Arrays.toString(arr));
            stringRedisTemplate.opsForValue().set(arr[1],"1",1,TimeUnit.MILLISECONDS);


            if(arr.length == 3) {
                String[] arrTwo = arr[2].split(",");
                for(String s : arrTwo) {
                    stringRedisTemplate.opsForValue().set(tempArr[1] + "-" + s,"1",1,TimeUnit.MILLISECONDS);
                }
            }
        }

    }

    private String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private void sendMessage(Object object) {
        httpRabbitMqService.sendMessage(object);
    }

    static class MoviesSeatServiceHolder{
        static Authentication authentication;
        static String principalKey;
        static {
            authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null)
                principalKey = (String) authentication.getPrincipal();
        }
    }

}
