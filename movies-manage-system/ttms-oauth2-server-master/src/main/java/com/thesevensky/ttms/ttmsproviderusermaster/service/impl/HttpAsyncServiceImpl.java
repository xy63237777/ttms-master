package com.thesevensky.ttms.ttmsproviderusermaster.service.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSFileUtils;
import com.thesevensky.ttms.ttmsproviderusermaster.service.HttpAsyncService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/3 21:39
 * @Version 1.0
 */
@Service
public class HttpAsyncServiceImpl implements HttpAsyncService {

    //private final static String filePath = "D:\\ttms\\images\\head\\";

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    //@Async
    public void upLoadForUser(MultipartFile file, String fileName) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String endName = fileName;
        if(StringUtils.isNotEmpty(originalFilename) &&
                (originalFilename.endsWith(".jpg") || originalFilename.endsWith(".png"))) {
            endName += ".jpg";
        } else throw new Exception("图片类型不支持");
        String endFilePath = masterProperties.getFile().getImage().getHeadPath()
                + TTMSFileUtils.getHashPath(fileName);
        String localPath = masterProperties.getFile().getImage().getLocalPath();
        logger.info("上传头像 --> " + localPath + endFilePath + endName);
        TTMSFileUtils.mkdirs(localPath + endFilePath);
        IOUtils.copy(file.getInputStream(), new FileOutputStream(localPath + endFilePath + endName));
    }

}
