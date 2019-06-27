package com.thesevensky.ttms.ttmsfileuploadmaster.commons.advice;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 18:01
 * @Version 1.0
 */
@Target(value={FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented()
@Inherited
public @interface DeleteId {
}
