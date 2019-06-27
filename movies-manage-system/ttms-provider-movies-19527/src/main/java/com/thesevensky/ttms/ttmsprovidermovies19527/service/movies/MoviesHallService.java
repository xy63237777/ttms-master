package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;



import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesHall;

import java.util.List;

public interface MoviesHallService {

    public List<MoviesHall> queryAll();

    public MoviesHall queryById(Long id);

    public void insertMoviesHall(MoviesHall moviesHall);

    public void deleteMoviesHall(Long id);

    public void updateMoviesHall(MoviesHall moviesHall);

    public Integer cmpInsert();
}
