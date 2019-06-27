package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.file.ImageFileProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.CartAndIdString;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatList;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.moviesmanageapi.commons.until.QRCodeUtil;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSFileUtils;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSPageHolder;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MoviesCartStatues;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesCart;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesCartDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesPlanDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.dao.movies.MoviesSeatDao;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.ImageService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesCartService;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSeatService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/26 20:02
 * @Version 1.0
 */
@Service
public class MoviesCartServiceImpl implements MoviesCartService {

    @Autowired
    private MoviesCartDao moviesCartDao = null;



    @Autowired
    private MoviesSeatService moviesSeatService = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoviesPlanDao moviesPlanDao = null;

    @Autowired
    private MoviesDao moviesDao = null;

    @Autowired
    private ImageService imageService = null;

    @Autowired
    private MasterProperties masterProperties = null;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;

    private static final String REDIS_TABLE = "cache_Movies_Cart";
    private static final String CACHE_FOR_MOVIES_CART = "cache_cart_";

    @Override
    public TTMSPageInfo<MoviesCart> findAllByUserId(Integer num) {
        Page<MoviesCart> moviesCarts = PageHelper.startPage(num, masterProperties.getCart().getPage().getPageSize())
                .doSelectPage(() -> moviesCartDao.findAllByUserId(getPrincipal()));
        return TTMSPageHolder.changePageForObj(moviesCarts);
    }

    @Override
    public TTMSPageInfo<MoviesCart> findAllByUserIdForAdmin(Integer num, String userId) {
        Page<MoviesCart> moviesCarts = PageHelper.startPage(num, masterProperties.getCart().getPage().getPageSize())
                .doSelectPage(() -> moviesCartDao.findAllByUserId(userId));
        return TTMSPageHolder.changePageForObj(moviesCarts);
    }

    @Override
    public void updateMoviesCartStatus(Long id, Byte status) throws MoviesException {
        int count = 0;
        count = moviesCartDao.updateMoviesCartStatus(id, status,getPrincipal());
        if(count == 0) throw new MoviesException("权限不匹配");
    }

    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #moviesCart.moviesPlan.moviePlanId")
            }
    )
    public void insertMoviesCart(MoviesCart moviesCart) throws Exception {
        SeatList seatList = new SeatList(moviesCart.getMoviesPlan().getMoviePlanId(),
                moviesCart.getMoviesPlan().getMoviePlanNumber(),
                moviesCart.getMoviesSeats());
        int count = moviesSeatService.seatsForBuy(seatList,null);
        moviesCart.setMoviesUserId(getPrincipal());
        String code = System.currentTimeMillis() % 1008611 +""+ getPrincipal().hashCode();
        moviesCart.setMoviesCode(code);
        ImageFileProperties image = masterProperties.getFile().getImage();
        String hashPath = TTMSFileUtils.getHashPath(getPrincipal());
        String filePath = image.getLocalCodePath() + hashPath;
        String fileName = getPrincipal() + "-" + code + ".jpg";
        String urlPath = image.getClientPath()  + image.getCodePath() + hashPath + fileName;
        moviesCart.setMoviesCodeImage(urlPath);
        System.out.println(moviesCart);
        System.out.println(filePath + " --- " + fileName);
        imageService.generate(code,filePath,fileName,getPrincipal());
        moviesCart.setMoviesPrice(getPrice(moviesCart.getMoviesPrice(),moviesCart.getMoviesCartCount()));
        moviesCartDao.insertMoviesCart(moviesCart);
        //QRCodeUtil.encode(code,"",filePath,fileName,true);
        moviesDao.updateMoviesForVol(moviesCart.getMoviesPlan().getMovies().getMovieId(),
                (int) moviesCart.getMoviesCartCount());
    }

    private Double getPrice(Double onePrice, int count) {
        return new BigDecimal(count + "").multiply(new BigDecimal(onePrice)).
                setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    private boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }


    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesPlanConstants.CACHE_ONE, key = "'cache_movies_plan_one_' + #result.moviesPlan.moviePlanId")
            }
    )
    public MoviesCart deleteMoviesCart(Long moviesCartId) throws MoviesException {
        Assert.notNull(moviesCartId,"moviesCartId 字段 不可以为空");
        MoviesCart moviesCart = moviesCartDao.queryById(moviesCartId);
        System.out.println(moviesCart);
        int count = 0;
        String message = null;
        System.out.println(moviesCart);
        System.out.println(moviesCart.getMoviesPlan().getMoviePlanStartTime());
        System.out.println(new Date(moviesCart.getMoviesPlan().getMoviePlanStartTime()));
        boolean flag = false;
        if(moviesCart.getMoviesPlan() == null || canDelete(moviesCart)) {
            logger.info("电影票可以删除");
            flag = true;
            count = moviesCartDao.deleteMoviesCart(moviesCartId,getPrincipal());
        }
        else if((message = exceptionDelete(moviesCart)) != null) throw new MoviesException(message);
        else if(isBegin(moviesCart)) {
            flag = true;
            count = removePlanAndSeats(moviesCart);
        }
        else {
            throw new MoviesException("出现未知错误");
        }
        if(flag) {
            String path = moviesCart.getMoviesCodeImage();
            ImageFileProperties image = masterProperties.getFile().getImage();
            String localPath = path.replaceAll(image.getClientPath(), image.getLocalPath());
            logger.info("删除 网络路径 " + path + " 本地路径 " + localPath + " 的结果是" +
                    deleteFile(localPath));
        }
        return moviesCart;
    }

    private int removePlanAndSeats(MoviesCart moviesCart) throws MoviesException {
        int c = moviesCartDao.deleteMoviesCart(moviesCart.getMoviesCartId(),getPrincipal());
        SeatList seatList = new SeatList(moviesCart.getMoviesPlan().getMoviePlanId(), getBit(moviesCart),
                moviesCart.getMoviesSeats());
        logger.info("用户 " + getPrincipal() + "删除 编号:"  + moviesCart.getMoviesCartId());
        logger.info("它的座位信息" + seatList);
        int count = moviesSeatService.seatsForNotBuy(seatList,null);
        //if(count != seatList.getIdList().size()) throw new MoviesException("座位数量和订单的座位不匹配");
        return c;
    }

    private int getBit(MoviesCart moviesCart) {
        long nowTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long moviePlanStartTime = moviesCart.getMoviesPlan().getMoviePlanStartTime();
        int day = (int)(moviePlanStartTime / 1000 / 60 /60 /24 - nowTime / 1000 / 60 /60 / 24);
        logger.info("天数是 : " + day);
        int bit = moviesCart.getMoviesPlan().getMoviePlanNumber() +
                masterProperties.getMovies().getNumber().getNumberOfDay() * day;
        logger.info("确切的位数是 : " +  bit);
        return bit;
    }

    private boolean canDelete(MoviesCart moviesCart) {
        Assert.notNull(moviesCart,"moviesCart 不可以为空");
        if(moviesCart.getMoviesStatus().equals(MoviesCartStatues.Ended.getStatus())
                || moviesCart.getMoviesStatus().equals(MoviesCartStatues.Exception.getStatus())
                ||LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() >
                moviesCart.getMoviesPlan().getMoviePlanStartTime()) {
            logger.info("可以退票 票info: " + moviesCart);
            return true;
        }
        return false;
    }

    private String exceptionDelete(MoviesCart moviesCart) {
        Assert.notNull(moviesCart,"moviesCart 不可以为空");
        Long nowTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long moviePlanStartTime = moviesCart.getMoviesPlan().getMoviePlanStartTime();
        if(nowTime < moviePlanStartTime && nowTime > moviePlanStartTime - masterProperties.getMovies().getPlan().getLockMoviesTime()) {
            logger.info("电影开始前20分钟 用户" + moviesCart.getMoviesUserId() + " 竟然想退票?");
            return "电影开始的前20分钟 不可以退票";
        }
        return null;
    }

    private boolean isBegin(MoviesCart moviesCart) {
        if(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli()
                < moviesCart.getMoviesPlan().getMoviePlanStartTime() +
                masterProperties.getMovies().getPlan().getLockMoviesTime()) {
            return true;
        }
        return false;
    }

    private String getPrincipal() {
        return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
