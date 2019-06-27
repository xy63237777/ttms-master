package com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies;


import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MoviesDao {
    public List<Movies> queryAll();

    public void insertMovies(Movies movies);

    public void updateMovies(Movies movies);

    public void deleteMovies(Long id);

    public Movies queryById(Long id);

    public List<Movies> queryByTypeBit(Integer bit);

    public List<Movies> queryAllOfDetailed();

    public Movies queryByIdOfDetailed(Long id);

    public List<Movies> queryByTypeBitOfDetailed(Integer bit);

    public List<Movies> queryByStatus(Integer status);

    public List<Movies> queryByStatusOfDetailed(Integer status);

    public void updateMoviesForStatus(@Param("movieId") Long movieId, @Param("status") Integer status);

    public List<Movies> queryByAfterTimeForNow(long time);

    public Movies queryByNotDetails(Long id);

    public Movies queryForId(Long id);

    public void updateMoviesForVol(@Param("id") Long id, @Param("vol") Integer vol);

    public List<Movies> queryByVolForDesc();
}
