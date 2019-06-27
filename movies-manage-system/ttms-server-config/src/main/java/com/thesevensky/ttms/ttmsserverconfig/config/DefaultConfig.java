package com.thesevensky.ttms.ttmsserverconfig.config;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.elastic.ElasticMoviesProperties;

import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSTimeUtils;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.ttmsserverconfig.master.dao.ServerDao;
import com.thesevensky.ttms.ttmsserverconfig.master.pojo.Master;
import com.thesevensky.ttms.ttmsserverconfig.master.scheduling.ServerScheduling;
import com.thesevensky.ttms.ttmsserverconfig.master.service.ServerService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Configuration
@EnableRabbit
@EnableAsync
@EnableCaching
public class DefaultConfig {

    @Autowired
    private ServerDao serverDao = null;

    @Autowired
    private MasterProperties masterProperties = null;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private ServerService serverService = null;

    @Autowired
    private JestClient jestClient = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public ServerScheduling initUpdateTimeScheduling() throws IOException {
        Master master = null;
        if((master = serverDao.queryBy(Master.DEFAULT_NAME)) == null) {
            logger.info(LocalDateTime.now() + "   --- [INFO] insert Time" );
            serverDao.insertTime(new Master());
        } else {
            long newTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long oldTime = master.getTime();
            long day = (newTime - oldTime) / 1000 / 60 / 60 / 24;
            int dayNumber = masterProperties.getMovies().getNumber().getDayNumber();
            for(int i = 0; i < Math.min(dayNumber,day); i++) {
                logger.info(LocalDateTime.now() + "   --- [INFO] update day ==> Next Day" );
                serverDao.updateNextDay(masterProperties.getMovies().getNumber().getNumberOfDay());
            }
            serverDao.updateTime(new Master());
            if(day >= 1) {
                serverService.invoke();
            }
        }
        init();
        return new ServerScheduling();
    }

    private void init() throws IOException {
        initElastic();
        initPlanNumber();
    }

    private void initElastic() throws IOException {

        ElasticMoviesProperties elasticMoviesProperties = masterProperties.getElastic().getMovies();
        logger.info("初始化所有电影");
        Delete build = new Delete.Builder(elasticMoviesProperties.getIndex()).build();
        jestClient.execute(build);
        List<Movies> movies = serverDao.queryAllMoviesForConfig();
        if(movies != null) {
            for(Movies movie : movies) {
                logger.info("添加索引 " + movie);
                Index index = new Index.Builder(movie).index(elasticMoviesProperties.getIndex())
                        .type(elasticMoviesProperties.getType()).id(movie.getMovieId() + "").build();
                jestClient.execute(index);
            }
        }
    }

    private void initPlanNumber() {
        logger.info("初始化PlanNumber");
        long localDay = TTMSTimeUtils.getLocalDay();

        for(long i = localDay - 10; i < localDay; i++) {
            Object o = redisTemplate.opsForHash().get(masterProperties.getMovies().getNumber().getCacheName(),
                    i + "");
            if(o != null) {
                logger.info("第 " +i + " 天的集合 " + o);
                redisTemplate.opsForHash().delete(masterProperties.getMovies().getNumber().getCacheName(), i + "");
            }
        }

    }
}
