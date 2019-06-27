package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;



import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;

import java.util.List;

public interface MoviesTypeService {

    public List<MoviesType> queryAll();

    public MoviesType queryById(Long id);

    public void deleteById(Long id);

    public void insertMoviesType(MoviesType moviesType);

    public void updateMoviesType(MoviesType moviesType);

    public List<MoviesType> queryAllByNumber(Integer number);
}
