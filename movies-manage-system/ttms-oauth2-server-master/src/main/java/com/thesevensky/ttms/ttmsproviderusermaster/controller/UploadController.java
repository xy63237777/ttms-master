package com.thesevensky.ttms.ttmsproviderusermaster.controller;

import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.ttmsproviderusermaster.service.HttpAsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/3 21:32
 * @Version 1.0
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private HttpAsyncService httpAsyncService = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/user")
    public HttpMessage uploadForUser(MultipartFile file, String fileName) {
        HttpMessage httpMessage = new HttpMessage();
        logger.info("用户上传头像 到达服务端 : " + fileName);
        try {
            httpAsyncService.upLoadForUser(file, fileName);
        } catch (Exception e) {
            logger.error(e.getMessage());
            httpMessage.setCode("500");
            httpMessage.setMsg(e.getMessage());
        }
        return httpMessage;
    }
}
