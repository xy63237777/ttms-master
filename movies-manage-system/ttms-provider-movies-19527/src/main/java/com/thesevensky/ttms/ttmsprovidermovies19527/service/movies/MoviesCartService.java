package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.CartAndIdString;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesCart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/26 20:02
 * @Version 1.0
 */
public interface MoviesCartService {

    public TTMSPageInfo<MoviesCart> findAllByUserId(Integer num);

    public void updateMoviesCartStatus(Long id, Byte status) throws MoviesException;

    public void insertMoviesCart(MoviesCart moviesCart) throws Exception;

    public MoviesCart deleteMoviesCart(Long moviesCartId) throws MoviesException;

    public TTMSPageInfo<MoviesCart> findAllByUserIdForAdmin(Integer num, String userId);

    //public void deleteMoviesCartForUser(List<Long> moviesIdList, String userId);
}
