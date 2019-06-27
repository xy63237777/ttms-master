package com.thesevensky.ttms.moviesmanageapi.commons.exception;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 12:03
 * @Version 1.0
 */
public class UserException extends RuntimeException {

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
