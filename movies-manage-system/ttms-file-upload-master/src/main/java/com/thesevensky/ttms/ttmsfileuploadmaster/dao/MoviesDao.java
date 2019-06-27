package com.thesevensky.ttms.ttmsfileuploadmaster.dao;


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

    public Integer deleteMovies(Long id);

    public Movies queryById(Long id);

    public List<Movies> queryByTypeBit(Integer bit);

    public List<Movies> queryAllOfDetailed();

    public Movies queryByIdOfDetailed(Long id);

    public List<Movies> queryByTypeBitOfDetailed(Integer bit);

    public void updateMoviesForImage(Movies movies);

    public Movies queryByIdForImage(Long id);

    public void updateMoviesDefaultImage(@Param("id") Long id,
                                         @Param("defaultImage") String defaultImage);
}
