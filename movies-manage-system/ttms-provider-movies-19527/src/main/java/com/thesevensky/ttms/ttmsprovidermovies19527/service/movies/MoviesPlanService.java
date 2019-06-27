package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/22 20:47
 * @Version 1.0
 */
public interface MoviesPlanService {

    public List<MoviesPlan> queryAllByMoviesId(Long moviesId);

    public List<MoviesPlan> queryAllByMoviesIdForTime(Long moviesId,
                                                       Long leftTime, Long rightTime);
    public MoviesPlan queryById(Long planId);

    public void deleteById(Long id);

    public void updateByMoviePlan(MoviesPlan moviesPlan);

    public void insertMoviePlan(MoviesPlan moviesPlan);

}
