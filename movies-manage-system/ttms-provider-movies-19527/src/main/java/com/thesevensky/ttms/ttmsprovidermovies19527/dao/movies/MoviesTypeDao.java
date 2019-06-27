package com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies;



import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MoviesTypeDao {

    public List<MoviesType> queryAll();

    public MoviesType queryById(Long id);

    public void deleteById(Long id);

    public void insertMoviesType(MoviesType moviesType);

    public void updateMoviesType(MoviesType moviesType);

    public List<MoviesType> queryAllByNumber(Integer number);
}
