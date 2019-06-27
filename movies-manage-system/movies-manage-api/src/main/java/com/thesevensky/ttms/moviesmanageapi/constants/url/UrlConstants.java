package com.thesevensky.ttms.moviesmanageapi.constants.url;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/23 17:33
 * @Version 1.0
 */
public interface UrlConstants {

    public static final String COMMONS = "/**";

    public static final String[] STATIC_RESOURCES = {
            COMMONS+"/*.jpg",
            COMMONS+"/*.css",
            COMMONS+"/*.html",
            COMMONS+"/*.js",
            COMMONS+"/*.png",
            "/static" + COMMONS,
            "/swagger*/**",
            "/webjars/**",
            "/v2/api-docs",
            "/"
    };
}
