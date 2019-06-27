package com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesSeat;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MoviesSeatDao {

    public List<MoviesSeat> queryAll();

    public MoviesSeat queryById(Long id);

    public List<MoviesSeat> queryByHallId(Long hallId);

    public void deleteMoviesSeat(Long id);

    public void deleteMoviesSeatByHall(Long hallId);

    public void updateMoviesSeat(MoviesSeat moviesSeat);

    public void updateMoviesNextDay(Integer bit);

    public Integer updateMoviesSeatStatus(@Param("seatId") Long seatId,
                                       @Param("status") Integer status);

    public void insertMoviesSeat(MoviesSeat moviesSeat);

    public Integer updateSeatForBuy(@Param("id") Long id, @Param("bit") Integer bit);

    public Integer updateSeatForNotBuy(@Param("id") Long id, @Param("bit") Integer bit);

    public Integer updateSeatForOk(@Param("id") Long id, @Param("bit") Integer bit);

    public Integer updateSeatForBed(@Param("id") Long id, @Param("bit") Integer bit);
}
