package com.thesevensky.ttms.ttmsproviderusermaster.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.UserEmailAndAuthority;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.user.AuthorityConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.user.UserConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.ttmsproviderusermaster.service.AuthorityService;
import com.thesevensky.ttms.ttmsproviderusermaster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 16:38
 * @Version 1.0
 */
@RestController
@RequestMapping(UserConstants.PREFIX +AuthorityConstants.PREFIX)
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper = null;

    @Autowired
    private UserService userService = null;

    @Autowired
    private AuthorityService authorityService = null;

//    @GetMapping(AuthorityConstants.DEFAULT_ADMIN)
//    @PreAuthorize("hasAnyAuthority('root','admin')")
//    public List<Authority> getAllAuth() {
//        return authorityService.getAll();
//    }
//
//    @PostMapping(AuthorityConstants.DEFAULT_ADMIN)
//    @PreAuthorize("hasAnyAuthority('root','admin')")
//    public HttpMessage updateUserForAuthority(String user, Authority authority) {
//        return null;
//    }

    @PostMapping(AuthorityConstants.DEFAULT_ADMIN)
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public HttpMessage updateUserAuthority(String userEmailAndAuthority) {
        HttpMessage httpMessage = new HttpMessage("200","修改权限成功");
        try{
            authorityService.updateUserForAuthority(objectMapper.readValue(userEmailAndAuthority, UserEmailAndAuthority.class));
        } catch (Exception e) {
            logger.info(userEmailAndAuthority + "修改权限失败" + e.getMessage());
            httpMessage.setCode("401");
            httpMessage.setMsg(e.getMessage());
        }
        return httpMessage;
    }

    @GetMapping(AuthorityConstants.ADMIN_USER)
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public HttpMessage getAllUserFor(@PathVariable("num") Integer num,
                                     @PathVariable("bit") Byte bit) {
        HttpMessage httpMessage = new HttpMessage("200");
        try{
            httpMessage.setMsg(userService.getAllUserForAuth(bit,num));
        } catch (Exception e) {
            logger.error("用户 " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " 获取用户信息 " + e.getMessage());
            httpMessage.setCode("403");
            httpMessage.setMsg(e.getMessage());
        }
        return httpMessage;
    }

    @GetMapping(AuthorityConstants.DEFAULT_ADMIN)
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public List<Authority> getAuthorities() {
        return authorityService.getAll();
    }
}
