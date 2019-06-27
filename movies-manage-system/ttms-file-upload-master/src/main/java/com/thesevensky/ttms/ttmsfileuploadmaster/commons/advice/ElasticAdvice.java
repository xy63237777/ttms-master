package com.thesevensky.ttms.ttmsfileuploadmaster.commons.advice;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.elastic.ElasticMoviesProperties;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.swagger.models.auth.In;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 1:09
 * @Version 1.0
 */
@Component
@Aspect
public class ElasticAdvice {

    @Autowired
    private JestClient jestClient = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MasterProperties masterProperties = null;

    @Around("@annotation(ElasticAnnocation)")
    public Object ElasticSearchAround(ProceedingJoinPoint pjp) {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        ElasticAnnocation annotation = method.getAnnotation(ElasticAnnocation.class);
        logger.info("Elastic 执行方法 ===> " + annotation.value());
        Object result = null;
        try{
            if(annotation.value().equals(ElasticEnum.DELETE)) result = deleteMethod(pjp,method);
            else result = updateMethod(pjp, method);
        }catch (Throwable throwable) {
            logger.error("AOP发生错误" + throwable.getMessage());
            throwable.printStackTrace();
        }
        return result;
    }

    private Object deleteMethod(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Object[] args = pjp.getArgs();
        Long id = null;
        if(args.length == 1) id = (Long) args[0];
        else {
            int i = 0;
            for(Class clazz : method.getParameterTypes()) {
                if(clazz.getAnnotation(DeleteId.class) != null) {
                    id = (Long) args[i];
                }
            }
            i++;
        }
        ElasticMoviesProperties movies = masterProperties.getElastic().getMovies();
        Delete build = new Delete.Builder(id + "").index(movies.getIndex()).type(movies.getType()).build();
        logger.info("Elastic执行结果 ===> " + jestClient.execute(build));
        return pjp.proceed();
    }

    private Object updateMethod(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Object[] args = pjp.getArgs();
        Movies movies = null;
        int i = 0;
        if(args.length == 1) movies = (Movies) args[0];
        else {
            for(Class clazz : method.getParameterTypes()) {
                if(clazz.equals(Movies.class)) {
                    movies = (Movies) args[i];
                    break;
                }
            }
            i++;
        }
        Assert.notNull(movies, "电影信息不应该为空");
        ElasticMoviesProperties moviesProperties = masterProperties.getElastic().getMovies();
        Object object = pjp.proceed();
        Index build = new Index.Builder(movies).index(moviesProperties.getIndex()).type(moviesProperties.getType())
                .id(movies.getMovieId() + "").build();
        logger.info("Elastic执行结果 ===> " + jestClient.execute(build));
        return object;
    }

}
