package com.thesevensky.ttms.ttmsfileuploadmaster.commons.advice;


import java.lang.annotation.*;

import static java.lang.annotation.ElementType.METHOD;

@Target(value={METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented()
@Inherited
public @interface ElasticAnnocation {

    ElasticEnum value() default ElasticEnum.INSERT;
}
