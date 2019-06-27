package com.thesevensky.ttms.ttmsprovidermovies19527.commons.advice;



import org.springframework.stereotype.Component;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;

@Target(value={METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented()
@Inherited
public @interface DaoForBath {

}
