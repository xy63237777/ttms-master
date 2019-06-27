package com.thesevensky.ttms.ttmsprovidermovies19527.service;


import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.file.ImageFileProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.until.QRCodeUtil;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/13 23:00
 * @Version 1.0
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Async
    public void generate(String text, String filePath, String fileName, String principal) {
        System.out.println(principal);
        try {
            String path = getHeadPath(principal);
            text = "http://www.linlixs.com:8080/images/ttms/timg.jpg";
            logger.info("用户 : "+ principal + " 的头像地址 " + path);
            logger.info(principal + "的订单二维码 " + filePath + "/" +fileName);
            QRCodeUtil.encode(text,path,filePath,fileName,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHeadPath(String principal) {
        ImageFileProperties image = masterProperties.getFile().getImage();
        String localHeadPath = image.getLocalHeadPath();
        String hashPath = TTMSFileUtils.getHashPath(principal);
        String path = localHeadPath + hashPath + principal + ".jpg";
        File file = new File(path);
        if(file.exists()) return path;
        return image.getDefaultHead().replaceAll(image.getClientPath(),image.getLocalPath());
    }


}
