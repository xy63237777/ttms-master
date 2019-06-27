package com.thesevensky.ttms.ttmsserverconfig.master.dao;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;
import com.thesevensky.ttms.ttmsserverconfig.master.pojo.Master;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServerDao {

    public void updateTime(Master master);

    public void insertTime(Master master);

    public Master queryBy(String name);

    public void updateNextDay(Integer bit);

    public void updateHotStatusForNextDay(@Param("status") Integer status,@Param("time") Long time, @Param("oldStatus") Integer oldStatus);

    public List<Long> findAllStartPlan(@Param("beginTime") Long beginTime,@Param("endTime") Long endTime);

    public void updateMoviesStatus(@Param("id") Long id, @Param("status") Integer status);

    public List<MoviesType> queryAllByNumber(Integer number);

    public List<Movies> queryAllMoviesForConfig();

    public void updateCartForBit(byte status);
}
