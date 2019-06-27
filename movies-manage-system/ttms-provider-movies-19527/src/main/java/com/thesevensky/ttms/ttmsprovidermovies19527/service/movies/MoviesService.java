package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;




import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import org.apache.ibatis.annotations.Param;

public interface MoviesService {
    public TTMSPageInfo<Movies> queryAll(Integer num, Integer size);

    public void insertMovies(Movies movies);

    public void updateMovies(Movies movies);

    public void updateMoviesForStatus(Long movieId, Integer status);

    public Movies queryById(Long id);

    public TTMSPageInfo<Movies> queryByTypeBit(Integer bit,Integer num, Integer size);

    public TTMSPageInfo<Movies> queryAllOfDetailed(Integer num, Integer size);

    public Movies queryByIdOfDetailed(Long id);

    public TTMSPageInfo<Movies> queryByTypeBitOfDetailed(Integer bit,Integer num, Integer size);

    public void deleteMovies(Long id);

    public TTMSPageInfo<Movies> queryByStatus(Integer num, Integer status);

    public TTMSPageInfo<Movies> queryByStatusOfDetailed(Integer num, Integer status);

    public TTMSPageInfo<Movies> queryAllByAfterTime(Integer num);

    public Integer getNum();

    public TTMSPageInfo<Movies> queryAllByVolDesc(Integer num);

}
