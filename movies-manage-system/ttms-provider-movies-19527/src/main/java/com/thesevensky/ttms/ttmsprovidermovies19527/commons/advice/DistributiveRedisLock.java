package com.thesevensky.ttms.ttmsprovidermovies19527.commons.advice;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;

@Target(value={METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented()
@Inherited
public @interface DistributiveRedisLock {
    @AliasFor("key")
    String value();
}
