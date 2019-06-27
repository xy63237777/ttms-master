package com.thesevensky.ttms.ttmsfileuploadmaster.service;


import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MoviesService {
    public TTMSPageInfo<Movies> queryAll(Integer num, Integer size);

    public void insertMovies(Movies movies) throws IOException;

    public void updateMovies(Movies movies) throws IOException;

    public String updateMoviesForDefaultImage(Long movieId, MultipartFile file) throws IOException;

    public Movies queryById(Long id);

    public TTMSPageInfo<Movies> queryByTypeBit(Integer bit, Integer num, Integer size);

    public TTMSPageInfo<Movies> queryAllOfDetailed(Integer num, Integer size);

    public Movies queryByIdOfDetailed(Long id);

    public TTMSPageInfo<Movies> queryByTypeBitOfDetailed(Integer bit, Integer num, Integer size);

    public void deleteMovies(Long id);

    public Integer getNum();

}
