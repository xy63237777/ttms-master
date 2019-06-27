package com.thesevensky.ttms.ttmsproviderusermaster.controller;

import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 14:39
 * @Version 1.0
 */
@RestController
public class HelloController {

    @PostMapping("/user/tt1")
    @PreAuthorize("permitAll()")
    public String tt1(@RequestParam(value = "files", required = false) MultipartFile[] files, String movieJson) {
        System.out.println(movieJson);
        for (MultipartFile multipartFile : files) {
            System.out.println(multipartFile.getOriginalFilename());
        }

        return "ok";
    }

    @Autowired
    private EmailTemplate emailTemplate = null;

    @PostMapping("/email")
    @PreAuthorize("permitAll()")
    public String hello(String sendTo) {
        emailTemplate.send(sendTo);
        return "ok";
    }
}
