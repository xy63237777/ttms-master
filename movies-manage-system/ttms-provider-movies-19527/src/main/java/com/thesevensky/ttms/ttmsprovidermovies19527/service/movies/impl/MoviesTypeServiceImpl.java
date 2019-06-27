package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;



import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesTypeConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesType;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesTypeDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesTypeServiceImpl implements MoviesTypeService {

    private static final String CACHE_ONE = MoviesTypeConstants.MOVIES_TYPE_CACHE + "One";

    @Autowired
    private MoviesTypeDao moviesTypeDao = null;

    @Override
    @Cacheable(cacheNames = MoviesTypeConstants.MOVIES_TYPE_CACHE, key = "'All'")
    public List<MoviesType> queryAll() {
        return moviesTypeDao.queryAll();
    }

    @Override
    @Cacheable(cacheNames = CACHE_ONE, key = "'cache_movie_type_' + #id")
    public MoviesType queryById(Long id) {
        return moviesTypeDao.queryById(id);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesTypeConstants.MOVIES_TYPE_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = CACHE_ONE, key = "'cache_movie_type_' + #id")
            }
    )
    public void deleteById(Long id) {
        moviesTypeDao.deleteById(id);
    }

    @Override
    @CacheEvict(cacheNames = MoviesTypeConstants.MOVIES_TYPE_CACHE, allEntries = true)
    public void insertMoviesType(MoviesType moviesType) {
        moviesTypeDao.insertMoviesType(moviesType);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesTypeConstants.MOVIES_TYPE_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = CACHE_ONE, key = "'cache_movie_type_' + #moviesType.movieTypeId")
            }
    )
    public void updateMoviesType(MoviesType moviesType) {
        moviesTypeDao.updateMoviesType(moviesType);
    }

    @Override
    @Cacheable(cacheNames = MoviesTypeConstants.MOVIES_TYPE_CACHE, key = "'cache_movie_number_' + #number")
    public List<MoviesType> queryAllByNumber(Integer number) {
        return moviesTypeDao.queryAllByNumber(number);
    }
}
