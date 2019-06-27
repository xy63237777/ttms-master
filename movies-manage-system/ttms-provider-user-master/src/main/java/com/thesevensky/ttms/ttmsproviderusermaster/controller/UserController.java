package com.thesevensky.ttms.ttmsproviderusermaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.UserException;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMsgStatus;
import com.thesevensky.ttms.moviesmanageapi.constants.client.ClientImageConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.user.UserConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;

import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailTemplate;
import com.thesevensky.ttms.ttmsproviderusermaster.service.HttpService;
import com.thesevensky.ttms.ttmsproviderusermaster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 18:55
 * @Version 1.0
 */
@RestController
@RequestMapping(UserConstants.PREFIX)
public class UserController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EmailTemplate emailTemplate = null;

    @Autowired
    private UserService userService = null;

//    @PostMapping(UserConstants.DEFAULT_USER)
//    public User getUser(@RequestBody String key) {
//        System.out.println(key);
//        return userService.queryByKeyForClient(key);
//    }

    @PostMapping(UserConstants.USER_FOR_ADD)
    @PreAuthorize("permitAll()")
    public HttpMessage addUser(String userJson, String code) throws IOException {
        User user = objectMapper.readValue(userJson, User.class);
        System.out.println(user);
        System.out.println(code);
        HttpMessage httpMessage = new HttpMessage("200", "欢迎 " + user.getUserName() +" 光临乐多影城");
        try{
            emailTemplate.cmpCode(user.getUserEmail(), code);
            userService.addUser(user);
        } catch (UserException e) {
            httpMessage.setMsg(e.getMessage());
            httpMessage.setCode("400");
            logger.error("用户注册失败" + user.getUserEmail() + " 失败原因 --->" + e.getMessage());
        }
        return httpMessage;
    }

    @PostMapping(UserConstants.USER_ADD_FOR_SEND_CODE)
    @PreAuthorize("permitAll()")
    public HttpMessage sendCode(String userEmail) {
        userService.sendMessage(userEmail);
        return new HttpMessage();
    }

    @GetMapping(UserConstants.USER_GET_ME)
    @PreAuthorize("isAuthenticated()")
    public User getMe() {
        return userService.getUserForMe();
    }

    @Autowired
    private HttpService httpService = null;

    @Autowired
    private ExecutorService executorService = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getPrincipal() {
        return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

   @PutMapping(UserConstants.USER_UPDATE_IMAGE)
   @PreAuthorize("isAuthenticated()")
   public HttpMessage updateUser(MultipartFile file, String userJson) throws IOException {
       User user = objectMapper.readValue(userJson, User.class);
       System.out.println(getPrincipal());
       HttpMessage httpMessage = new HttpMessage();
       logger.info("用户修改个人头像");
        if(file!= null) {
            try{
//                FutureTask<HttpMessage> futureTask = new FutureTask<>(() -> {
//                    return httpService.uploadFileForTemplate(ClientImageConstants.UPDATE_IMAGE_FOR_USER, file,getPrincipal());
//                    //return new HttpMessage("123");
//                });
//
//                Future<HttpMessage> submit = executorService.submit(callable);
//
                userService.updateUser(user);
//
                return httpService.uploadFileForTemplate(ClientImageConstants.UPDATE_IMAGE_FOR_USER, file,getPrincipal());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                httpMessage.setMsg("头像上传失败");
                httpMessage.setCode("500");
            }
        }
       return httpMessage;
   }

   @PutMapping(UserConstants.USER_UPDATE_NAME)
   @PreAuthorize("isAuthenticated()")
   public HttpMessage updateUserForName(String userJson) throws IOException {
       User user = objectMapper.readValue(userJson, User.class);
       user.setUserEmail(getPrincipal());
       userService.updateUserName(user);
       return new HttpMessage();
   }

   @PutMapping(UserConstants.USER_PASSWORD_URL)
   @PreAuthorize("permitAll()")
   public HttpMessage updateForPassword(String password,String newPassword) {
       HttpMessage httpMessage = new HttpMessage("200","修改成功");
       try{
           userService.cmpPasswordForUser(password);
           userService.updateForPassword(newPassword);
       } catch (Exception e) {
           httpMessage.setCode("415");
           httpMessage.setMsg(e.getMessage());
       }
       return httpMessage;
   }


//    @GetMapping(UserConstants.USER_INFO)
//    @PreAuthorize("isAuthenticated()")
//    public User showUserInfoForController() {
//        return userService.showUserInfoForMe();
//    }
//
    @PostMapping(UserConstants.USER_CMP)
    @PreAuthorize("permitAll()")
    public HttpMessage cmpHasUser(String userEmail) {
        HttpMessage httpMessage = new HttpMessage("200","用户可以注册");
        try{
            userService.cmpUser(userEmail);
        } catch (Exception e) {
            httpMessage.setCode("415");
            httpMessage.setMsg(e.getMessage());
        }
        return httpMessage;
    }


}
