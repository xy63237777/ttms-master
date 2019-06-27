package com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies;


import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesHall;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoviesHallDao {

    public List<MoviesHall> queryAll();

    public MoviesHall queryById(Long id);

    public void insertMoviesHall(MoviesHall moviesHall);

    public void deleteMoviesHall(Long id);

    public void updateMoviesHall(MoviesHall moviesHall);

    public MoviesHall queryForId(Long id);

    public Integer cmpCanInsert();
}
