package com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/22 15:04
 * @Version 1.0
 */
public interface MoviesPlanDao {

    public List<MoviesPlan> queryAllByMoviesId(Long moviesId);

    public List<MoviesPlan> queryAllByMoviesIdForTime(@Param("id") Long moviesId,
                                                      @Param("leftTime") Long leftTime, @Param("rightTime") Long rightTime);
    public MoviesPlan queryById(Long id);

    public void deleteById(Long id);

    public void updateByMoviePlan(MoviesPlan moviesPlan);

    public void insertMoviePlan(MoviesPlan moviesPlan);

    public MoviesPlan queryByIdForDetails(Long id);

    public MoviesPlan cmpCanInsert(MoviesPlan moviesPlan);

    public MoviesPlan findByIdMap(Long id);
}
