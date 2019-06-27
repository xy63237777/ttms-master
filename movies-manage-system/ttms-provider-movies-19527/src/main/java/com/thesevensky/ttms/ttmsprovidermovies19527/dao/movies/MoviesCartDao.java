package com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesCart;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 10:47
 * @Version 1.0
 */
public interface MoviesCartDao {

    public List<MoviesCart> findAllByUserId(String userId);

    public Integer updateMoviesCartStatus(@Param("id") Long id, @Param("status") Byte status,
                                       @Param("userId")String userId);

    public Integer insertMoviesCart(MoviesCart moviesCart);

    public Integer deleteMoviesCart(@Param("moviesCartId") Long moviesCartId, @Param("userId") String userId);

    public Integer deleteMoviesCartForUser(String userId);

    public MoviesCart queryById(@Param("id") Long id);
}
