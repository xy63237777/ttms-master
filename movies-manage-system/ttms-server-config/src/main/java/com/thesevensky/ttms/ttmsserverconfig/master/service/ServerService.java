package com.thesevensky.ttms.ttmsserverconfig.master.service;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MovieStatues;
import com.thesevensky.ttms.moviesmanageapi.enums.movies.MoviesCartStatues;
import com.thesevensky.ttms.ttmsserverconfig.master.dao.ServerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/22 16:41
 * @Version 1.0
 */
@Service
public class ServerService {

    @Autowired
    private ServerDao serverDao = null;

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void invoke() {
        updateMoviesStatusForDefault();
        updateMoviesForHot();
    }

    private void updateMoviesStatusForDefault() {
        long nowTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        logger.info("[ 更 新 状 态 所 有 热 映 电 影 变 为 正 常] ");
        serverDao.updateHotStatusForNextDay(MovieStatues.Default.getState(),nowTime, MovieStatues.OnHost.getState());
    }

    private void updateMoviesForHot() {
        long nowTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long endTime = (nowTime / 1000 / 60 / 60 / 24) + masterProperties.getMovies().getNumber().getDayNumber() - 1;
        List<Long> allStartPlan = serverDao.findAllStartPlan(nowTime / 1000 / 60 / 60 / 24, endTime);
        if(allStartPlan != null) {
            for(Long integer : allStartPlan) {
                logger.info("[ 更 新 电 影 编 号 为 " + integer + " 的 电 影 为 热 映 ]");
                serverDao.updateMoviesStatus(integer, MovieStatues.OnHost.getState());
            }
        }
    }

    public void updateStatusForCart() {
        serverDao.updateCartForBit(MoviesCartStatues.Ended.getStatus());
    }


}
