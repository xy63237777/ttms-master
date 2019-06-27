package com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.PlanAndLockSeats;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.*;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;


@Component
public class MoviesClientFallbackFactory implements FallbackFactory<MoviesClientService> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static class MoviesClientFallbackFactoryHolder{
        private static List<Movies> errorList = new ArrayList<>();
        private static Movies movies = new Movies();
        static {
            movies.setMovieId(-1L);
            movies.setMovieName("出错啦");
            errorList.add(movies);
        }
    }


    private static class MoviesDefaultHttpMessageHolder{
        private static HttpMessage httpMessage = new HttpMessage("500");
    }

    private static class MoviesPlanClientFallbackFactoryHolder{
        private static MoviesPlan moviesPlan = new MoviesPlan();
        private static List<MoviesPlan> errorList = new ArrayList<>();
        private static PlanAndLockSeats planAndLockSeats = new PlanAndLockSeats();
        static {
            moviesPlan.setMoviePlanId(-1L);
            errorList.add(moviesPlan);
            planAndLockSeats.setMoviesPlan(moviesPlan);
        }
    }

    private static class MoviesTypeClientFallbackFactoryHolder{
        private static MoviesType moviesType = new MoviesType();
        private static List<MoviesType> errorList = new ArrayList<>();
        static {
            moviesType.setMovieTypeId(-1L);
            errorList.add(moviesType);
        }
    }



    private static class MoviesHallClientFallbackFactoryHolder{
        private static MoviesHall moviesHall = new MoviesHall();
        private static List<MoviesHall> errorList = new ArrayList<>();
        static {
            moviesHall.setMovieHallId(-1L);
            errorList.add(moviesHall);
        }
    }


    @Override
    public MoviesClientService create(Throwable throwable) {
        MoviesDefaultHttpMessageHolder.httpMessage.setMsg(throwable.getMessage());
        logger.error("客户端服务出现错误 -- " + throwable.getMessage());
        throwable.printStackTrace();
        MoviesClientFallbackFactoryHolder.errorList.get(0).setMovieAuthor(throwable.getMessage());
        return new MoviesClientService() {
            @Override
            public TTMSPageInfo<Movies> findMovies(Integer num, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public TTMSPageInfo<Movies> getAllForVolDesc(Integer num, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public HttpMessage notLock(String msg, String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public TTMSPageInfo<Movies> getMoviesAfterForTime(Integer num, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }


            @Override
            public TTMSPageInfo<Movies> getMoviesByHotForDetailed(Integer num, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public TTMSPageInfo<Movies> getMoviesByStatus(Integer num, Integer status, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public TTMSPageInfo<Movies> getMoviesByStatusOfDetailed(Integer num, Integer status, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public HttpMessage updateMoviesSeatForNotAll(String seatList, String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public List<MoviesHall> get(String authorization) {
                return null;
            }

            @Override
            public List<Movies> searchAllMovies(String name,String authorization) {
                return MoviesClientFallbackFactoryHolder.errorList;
            }

            @Override
            public PlanAndLockSeats test() {
                return null;
            }

            @Override
            public TTMSPageInfo<Movies> findMoviesDetails(Integer num,String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public Movies findMovieJustOne(Long id,String authorization) {
                return MoviesClientFallbackFactoryHolder.movies;
            }

            @Override
            public TTMSPageInfo<Movies> getMoviesByHotForFeign(Integer num, String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public TTMSPageInfo<Movies> findMoviesByType(Integer num, Integer bit,String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public TTMSPageInfo<Movies> findMoviesByTypeAndDetails(Integer num, Integer bit,String authorization) {
                return new TTMSPageInfo<Movies>(MoviesClientFallbackFactoryHolder.errorList, 0L, 0, 0);
            }

            @Override
            public TTMSPageInfo<MoviesCart> getMoviesCartList(Integer num,String authorization) {
                return null;
            }

            @Override
            public HttpMessage updateStatus(Long moviesCartId, Byte status,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage insertMoviesCart(String moviesCart,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage deleteMoviesCart(Long moviesId,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public List<MoviesPlan> getAllMoviesPlan(Long moviesId, Integer day,String authorization) {
                return MoviesPlanClientFallbackFactoryHolder.errorList;
            }

            @Override
            public PlanAndLockSeats findMoviesPlan(Long planId,String authorization) {
                return MoviesPlanClientFallbackFactoryHolder.planAndLockSeats;
            }

            @Override
            public HttpMessage lockSeats(String seatList,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public List<MoviesType> getMoviesTypeList(String authorization) {
                return MoviesTypeClientFallbackFactoryHolder.errorList;
            }


            @Override
            public HttpMessage insertMoviesPlan(String moviesPlan, String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage updateMoviesPlan(String moviesPlan,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage deleteMoviesPlan(Long moviesId,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage updateMoviesSeatForOk(String seatList,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage updateMoviesSeatForBed(String seatList,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage insertMoviesType(String moviesType,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage deleteMoviesType(Long id,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }


            @Override
            public HttpMessage updateMoviesType(String moviesType,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public List<MoviesHall> getMoviesHallList(String authorization) {
                return MoviesHallClientFallbackFactoryHolder.errorList;
            }

            @Override
            public MoviesHall queryByHallId(Long id,String authorization) {
                return MoviesHallClientFallbackFactoryHolder.moviesHall;
            }

            @Override
            public HttpMessage deleteMoviesHall(Long id,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage updateMoviesHall(String moviesHall,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }

            @Override
            public HttpMessage addMoviesHall(String moviesHall,String authorization) {
                return MoviesDefaultHttpMessageHolder.httpMessage;
            }
        };
    }
}
