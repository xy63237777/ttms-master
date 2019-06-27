package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSPageHolder;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesDao;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class MoviesServiceImpl implements MoviesService {

    private static volatile Integer pageNum = 0;
    //private static final String CACHE_ONE = MoviesConstants.MOVIES_CACHE + "One";

    @Autowired
    private MoviesDao moviesDao = null;

    @Autowired
    private MoviesTypeService moviesTypeService = null;

    @Autowired
    private MasterProperties masterProperties = null;

    @Override
    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE,allEntries = true)
    public void insertMovies(Movies movies) {
        moviesDao.insertMovies(movies);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE,key = "'cache_movie_' + #movies.movieId"),
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE,key = "'cache_movie_detailed_' + #movies.movieId"),
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE,allEntries = true)
}
    )
    public void updateMovies(Movies movies) {
        moviesDao.updateMovies(movies);
    }

    private void getAndSetMoviesTypes(Movies movies) {
        Integer typeNumber = movies.getMoviesTypes().getTypeNumber();
        movies.setMovieTypeNumber(typeNumber);
        movies.getMoviesTypes().setMoviesTypes(moviesTypeService.queryAllByNumber(typeNumber));
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.CACHE_ONE, key = "'cache_movie_' + #id")
    public Movies queryById(Long id) {
        Movies movies = moviesDao.queryById(id);
        getAndSetMoviesTypes(movies);
        return movies;
    }



    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_delailed_' + #num +'-' + #size")
    public TTMSPageInfo<Movies> queryAllOfDetailed(Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryAllOfDetailed() );
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_after_' +#num")
    public TTMSPageInfo<Movies> queryAllByAfterTime(Integer num) {
        Page<Movies> movies = PageHelper.startPage(num,
                masterProperties.getMovies().getMoviesPageProperties().getPageSize())
                .doSelectPage(() -> moviesDao.queryByAfterTimeForNow(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.CACHE_ONE, key = "'cache_movie_detailed_' + #id")
    public Movies queryByIdOfDetailed(Long id) {
        Movies movies = moviesDao.queryByIdOfDetailed(id);
        getAndSetMoviesTypes(movies);
        return movies;
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_' + #num +'-' + #size")
    public TTMSPageInfo<Movies> queryAll(Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryAll());
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_' + #num +'-' + #size + '-' + #bit")
    public TTMSPageInfo<Movies> queryByTypeBit(Integer bit, Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryByTypeBit(bit));
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_detailed_' + #num +'-' + #size + '-' + #bit")
    public TTMSPageInfo<Movies> queryByTypeBitOfDetailed(Integer bit, Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryByTypeBitOfDetailed(bit));
        pageNum = movies.getPages();
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE,key = "'cache_movie_' + #id"),
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE,key = "'cache_movie_detailed_' + #id")
            }
    )
    public void deleteMovies(Long id) {
        moviesDao.deleteMovies(id);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_status_' + #num +'-' + #status")
    public TTMSPageInfo<Movies> queryByStatus(Integer num,Integer status) {
        Page<Movies> movies = PageHelper.startPage(num,
                masterProperties.getMovies().getMoviesPageProperties().getPageSize())
                .doSelectPage(() -> moviesDao.queryByStatus(status) );
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    public TTMSPageInfo<Movies> queryByStatusOfDetailed(Integer num, Integer status) {
        Page<Movies> movies = PageHelper.startPage(num,
                masterProperties.getMovies().getMoviesPageProperties().getPageSize())
                .doSelectPage(() -> moviesDao.queryByStatusOfDetailed(status) );
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    public void updateMoviesForStatus(Long movieId, Integer status) {
        moviesDao.updateMoviesForStatus(movieId, status);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_vol_' + #num")
    public TTMSPageInfo<Movies> queryAllByVolDesc(Integer num) {
        Page<Movies> movies = PageHelper.startPage(num,
                masterProperties.getMovies().getMoviesPageProperties().getPageSize())
                .doSelectPage(() -> moviesDao.queryByVolForDesc());
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    public Integer getNum() {
        if(pageNum == 0)
            synchronized (this) {
                if(pageNum == 0)
                    queryAll(1, null);
            }
        return pageNum;
    }
}
