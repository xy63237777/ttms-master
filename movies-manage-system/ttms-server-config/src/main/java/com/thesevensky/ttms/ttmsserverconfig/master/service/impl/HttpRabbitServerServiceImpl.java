package com.thesevensky.ttms.ttmsserverconfig.master.service.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.rabbit.RabbitMQConstants;
import com.thesevensky.ttms.ttmsserverconfig.master.service.HttpRabbitServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * @Author TheSevenSky
 * @Date: 2019/5/21 17:34
 * @Version 1.0
 */
@Service
public class HttpRabbitServerServiceImpl implements HttpRabbitServerService {


    @Autowired
    private MasterProperties masterProperties = null;

    @Autowired
    private HttpRabbitServerServiceHolder httpRabbitServerServiceHolder = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @RabbitListener(queues = "rabbit.ttms")
    public void monitor(String str) {
        logger.info(" " +RabbitMQConstants.TOUTINGKEY + " [收到消息] I----]===========> " + str);
        httpRabbitServerServiceHolder.invoke(str);
    }


    @Component
    class  HttpRabbitServerServiceHolder {

        @Autowired
        private StringRedisTemplate stringRedisTemplate = null;

        @Autowired
        private RedisTemplate redisTemplate = null;

        @Async
        public void invoke(String str) {
            //13639558669$13&123123$2,5,6,3
            String[] arr = str.split("\\$"); //anonymousUser$1$5,6,8
            Set<String> set = getRedisSet(MoviesSeatConstants.MOVIES_SEAT_FOR_REDIS,
                    MoviesSeatConstants.MOVIES_SEAT_FOR_LOCK + arr[1]);
            String[] arrTwo = arr[1].split("\\&");
            String key = arr[0] + "_" + arrTwo[0] +"_" + arrTwo[1]; //anonymousUser_1
            putRedisSet(set,key);
            redisTemplate.opsForHash().put(MoviesSeatConstants.MOVIES_SEAT_FOR_REDIS,
                    MoviesSeatConstants.MOVIES_SEAT_FOR_LOCK + arr[1],set);
            System.out.println(set);
            putRedisSet(key,arr[2]);
        }

        private Set<String> getRedisSet(Object h, Object k) {
            Set<String> set =(HashSet<String>) redisTemplate.opsForHash().get(h,k);
            if(set == null) {
                set = new HashSet<>();
            }
            return set;
        }

        private void putRedisSet(Set<String> set, String str) {
            if (!set.contains(str)) set.add(str);
        }

        private void putRedisSet(String key, String val) {
            logger.info("["  + key + "] - [锁定了库存,座位编号] " + val);
            stringRedisTemplate.opsForValue().set(key, val, masterProperties.getMovies().getSeat().getTime(),
                    masterProperties.getMovies().getSeat().getTimeUnit());
        }
    }
}
