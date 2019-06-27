package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;



import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesHallConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesHall;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesSeat;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesHallDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesSeatDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesHallService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSeatService;
import io.swagger.models.auth.In;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesHallServiceImpl implements MoviesHallService {

    @Autowired
    private MoviesHallDao moviesHallDao = null;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;

    @Autowired
    private MoviesSeatDao moviesSeatDao = null;

    @Autowired
    private MoviesSeatService moviesSeatService = null;



    @Override
    @Cacheable(cacheNames = MoviesHallConstants.MOVIES_HALL_CACHE,key = "'All'")
    public List<MoviesHall> queryAll() {
        return moviesHallDao.queryAll();
    }

    @Override
    @Cacheable(cacheNames = MoviesHallConstants.CACHE_ONE,key = "'cache_movies_hall_' + #id")
    public MoviesHall queryById(Long id) {
        MoviesHall moviesHall = moviesHallDao.queryById(id);
        moviesHall.setMoviesSeats(moviesSeatService.queryByHallId(id));
        return moviesHall;
    }

    @Override
    @Async
    @CacheEvict(cacheNames = MoviesHallConstants.MOVIES_HALL_CACHE, allEntries = true)
    public void insertMoviesHall(MoviesHall moviesHall) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        MoviesSeatDao mapper = sqlSession.getMapper(MoviesSeatDao.class);
        MoviesHallDao hallDao = sqlSession.getMapper(MoviesHallDao.class);
        try {
            try {
                moviesHallDao.insertMoviesHall(moviesHall);
            } catch (Exception e) {
                throw new MoviesException();
            }

            for(int i = 1; i <= moviesHall.getMovieHallRow(); i++) {
                for(int j = 1; j <= moviesHall.getMovieHallCol(); j++) {
                    MoviesSeat moviesSeat = new MoviesSeat();
                    moviesSeat.setMovieSeatX(i);
                    moviesSeat.setMovieSeatY(j);
                    moviesSeat.setMoviesHall(moviesHall);
                    mapper.insertMoviesSeat(moviesSeat);
                }
            }
            sqlSession.commit();
        }catch (MoviesException mov) {
            mov.printStackTrace();
            sqlSession.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            moviesHallDao.deleteMoviesHall(moviesHall.getMovieHallId());
        } finally {
            sqlSession.close();
        }

    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE, key =  "'cache_movies_hall_' + #id"),
            @CacheEvict(cacheNames = MoviesHallConstants.MOVIES_HALL_CACHE, allEntries = true)
    })
    public void deleteMoviesHall(Long id) {
        moviesSeatDao.deleteMoviesSeatByHall(id);
        moviesHallDao.deleteMoviesHall(id);
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = MoviesHallConstants.MOVIES_HALL_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesHallConstants.CACHE_ONE, key =  "'cache_movies_hall_' + #moviesHall.movieHallId")
            }
    )
    public void updateMoviesHall(MoviesHall moviesHall) {
        moviesHallDao.updateMoviesHall(moviesHall);
    }

    @Override
    public Integer cmpInsert() {
        return moviesHallDao.cmpCanInsert();
    }
}
