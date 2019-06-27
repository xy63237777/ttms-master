package com.thesevensky.ttms.ttmsproviderusermaster.service;

import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/3 21:39
 * @Version 1.0
 */
public interface HttpAsyncService {

    void upLoadForUser(MultipartFile file, String fileName) throws Exception ;
}
