package com.thesevensky.ttms.ttmsprovidermovies19527.commons.advice;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Aspect
public class MovieSeatAdvice {

    @Pointcut("execution(public * com.thesevensky.ttms.*.*(..))")
    public void point(){}

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate = null;


    @Around("@annotation(DaoForBath)")
    public Object TransactionalAround(ProceedingJoinPoint pjp) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        Object[] args = pjp.getArgs();
        int i = 0;
        for (Class clazz : ((MethodSignature)pjp.getSignature()).getMethod().getParameterTypes()) {
            if(SqlSession.class.equals(clazz)) {
                if (args[i] == null) args[i] = sqlSession;
                break;
            }
            i++;
        }
        try{
            Object object = pjp.proceed(args);
            sqlSession.commit();
            return object;
        }  catch (Throwable throwable) {
            throwable.printStackTrace();
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return null;
    }

    @Around("@annotation(DistributiveRedisLock)")
    public Object lockAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(123);
        DistributiveRedisLock annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(DistributiveRedisLock.class);
        System.out.println(annotation.value());
        return pjp.proceed();
    }
}
