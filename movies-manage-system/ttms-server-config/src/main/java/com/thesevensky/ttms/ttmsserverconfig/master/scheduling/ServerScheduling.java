package com.thesevensky.ttms.ttmsserverconfig.master.scheduling;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSTimeUtils;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesHallConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesPlanConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.ttmsserverconfig.master.dao.ServerDao;
import com.thesevensky.ttms.ttmsserverconfig.master.pojo.Master;
import com.thesevensky.ttms.ttmsserverconfig.master.service.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

public class ServerScheduling {

    @Autowired
    private ServerDao serverDao = null;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServerService serverService = null;

    @Scheduled(cron = "0 0 2 * * ?")
    public void updateNextDayTime() {
        logger.info(LocalDateTime.now() + "   --- [INFO] update day ==> Next Day" );
        serverDao.updateNextDay(masterProperties.getMovies().getNumber().getNumberOfDay());
        serverDao.updateTime(new Master());
        serverService.invoke();
        clearCache();
        deletePlanNumber();
        serverService.updateStatusForCart();
    }

    private void deletePlanNumber() {
        long localDay = TTMSTimeUtils.getLocalDay();
        while(true) {
            localDay -= 1;
            Object o = redisTemplate.opsForHash().get(masterProperties.getMovies().getNumber().getCacheName(),
                    localDay + "");
            logger.info("第 " +localDay + " 天的集合 " + o);
            if(o == null) break;
            redisTemplate.opsForHash().delete(masterProperties.getMovies().getNumber().getCacheName(), localDay + "");
        }
    }


    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesConstants.CACHE_ONE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesPlanConstants.MOVIES_PLAN_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesHallConstants.MOVIES_HALL_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesSeatConstants.MOVIES_SEAT_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = MoviesPlanConstants.MOVIES_PLAN_CACHE, allEntries = true)
            }
    )
    public void clearCache() {
        logger.info("清除了缓存");
    }


}
