package com.thesevensky.ttms.ttmsproviderusermaster.service.impl;

import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.ttmsproviderusermaster.service.HttpService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/3 21:02
 * @Version 1.0
 */
@Service
public class HttpServiceImpl implements HttpService {

    private final static String LOCAL_PATH = "D:\\temp\\ttms\\";

    @Autowired
    private RestTemplate restTemplate = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public HttpMessage uploadFileForTemplate(String url, MultipartFile multipartFile, String principal) {

        mkdir();
        System.out.println("这里的 " + principal);
        logger.info(principal + " -> 用户调用上传头像服务");
        HttpMessage httpMessage = new HttpMessage();
        try {
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.length() - 4);
            if (StringUtils.isNotEmpty(fileName) && !(suffix.equals(".jpg") || suffix.equals(".png"))) {
                logger.error("图片格式不对");
                return new HttpMessage("400", "文件格式只支持jpg或者png");
            }
            String tempFile = LOCAL_PATH + principal + suffix;
            IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(new File(tempFile)));
            return sendRequest(url, tempFile, principal);
        } catch (Exception e) {
            logger.error(e.getMessage());
            httpMessage.setMsg("上传出错");
            httpMessage.setCode("500");
        }
        return httpMessage;
    }

//    private String getPrincipal() {
//        //return "13639558669";
//        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }

    private void mkdir() {
        File file = new File(LOCAL_PATH);
        if(!file.exists()) file.mkdirs();
    }

    protected HttpMessage sendRequest(String url,String tempFile,String principal) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        File file = new File(tempFile);
        logger.info("上传临时文件 --> " + tempFile);
        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", resource);
        form.add("fileName", principal);
        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        ResponseEntity<HttpMessage> httpMessageResponseEntity = restTemplate.postForEntity(url, files, HttpMessage.class);
        new File(tempFile).delete();
        return httpMessageResponseEntity.getBody();
    }
}
