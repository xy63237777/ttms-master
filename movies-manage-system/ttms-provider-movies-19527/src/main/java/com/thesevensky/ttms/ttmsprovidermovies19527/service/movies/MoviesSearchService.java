package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;

import java.io.IOException;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 23:51
 * @Version 1.0
 */
public interface MoviesSearchService {

    public List<Movies> searchMoviesForName(String name);
}
