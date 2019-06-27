package com.thesevensky.ttms.ttmsproviderusermaster.commons.mail;

import com.thesevensky.ttms.moviesmanageapi.commons.exception.UserException;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/8 18:54
 * @Version 1.0
 */
public interface EmailTemplate {

    void send(String sendTo);

    void cmpCode(String sendTo, String code) throws UserException;
}
