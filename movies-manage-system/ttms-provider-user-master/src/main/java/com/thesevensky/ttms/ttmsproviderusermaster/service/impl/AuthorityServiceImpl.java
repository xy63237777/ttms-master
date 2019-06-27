package com.thesevensky.ttms.ttmsproviderusermaster.service.impl;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.UserEmailAndAuthority;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.UserException;
import com.thesevensky.ttms.moviesmanageapi.constants.user.AuthorityConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.AuthorityDao;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.UserDao;
import com.thesevensky.ttms.ttmsproviderusermaster.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 16:50
 * @Version 1.0
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao = null;

    @Autowired
    private UserDao userDao = null;

    @Override
    @Cacheable(cacheNames = AuthorityConstants.ADMIN_CACHE, key = "'all'")
    public List<Authority> getAll() {
        return authorityDao.findAll();
    }

    @Override
    public void updateUserForAuthority(UserEmailAndAuthority userEmailAndAuthority) {
        if(getPrincipal().equals(userEmailAndAuthority.getUserEmail())) throw new UserException("不可以提高自己的权限");

        Integer authForNumberByMe = userDao.findAuthForNumberByMe(getPrincipal());
        System.out.println("authForNumberByMe -- "  + authForNumberByMe);
        List<Authority> allByNumber = authorityDao.findAllByNumber(authForNumberByMe);
        System.out.println("allByNumber " + allByNumber);
        Authority authority = getMaxAuthority(allByNumber);
        System.out.println("authority " + authority);
        if(authority.getAuthorityBit() < userEmailAndAuthority.getAuthority().getAuthorityBit() - 1 ) {
            userDao.updateAuthorityForUser(userEmailAndAuthority);
        } else {
            throw new UserException("你没有权利提高权限");
        }
    }

    @Override
    public Authority getMaxAuthority(List<Authority> authorities) {
        Authority authority = null;
        byte min = Byte.MAX_VALUE;
        for(Authority temp : authorities) {
            System.out.println(temp);
            if(temp.getAuthorityBit() < min) {
                min = temp.getAuthorityBit();
                authority = temp;
            }
        }
        return authority;
    }

    private String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
