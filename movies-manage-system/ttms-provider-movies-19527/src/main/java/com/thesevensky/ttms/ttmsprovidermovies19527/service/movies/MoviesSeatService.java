package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatusList;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesSeat;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MoviesSeatService {

    public List<MoviesSeat> queryAll();

    public MoviesSeat queryById(Long id);

    public List<MoviesSeat> queryByHallId(Long hallId);

//    public void deleteMoviesSeat(Long id);
//
//    public void deleteMoviesSeatByHall(Long hallId);
//
//    public void updateMoviesSeat(MoviesSeat moviesSeat);
//
//    public void updateMoviesNextDay(Integer bit);
//
//    public void insertMoviesSeat(MoviesSeat moviesSeat);
//
//    public void updateSeatForBuy(@Param("id") Long id, @Param("bit") Integer bit);
//
//    public void updateSeatForNotBuy(@Param("id") Long id, @Param("bit") Integer bit);
//
//    public void updateSeatForOk(@Param("id") Long id, @Param("bit") Integer bit);
//
//    public void updateSeatForBed(@Param("id") Long id, @Param("bit") Integer bit);

    public Integer seatsForBuy(SeatList seatList,SqlSession sqlSession);

    public Integer seatsForNotBuy(SeatList seatList,SqlSession sqlSession);

    public Integer seatsForOk(SeatList seatList,SqlSession sqlSession);

    public Integer seatsForBed(SeatList seatList,SqlSession sqlSession);

    public String seatsForLock(SeatList seatList);

    public Integer seatsForNot(SeatAndStatusList seatList, SqlSession sqlSession);

    public void seatsForNotLock(String str);
}
