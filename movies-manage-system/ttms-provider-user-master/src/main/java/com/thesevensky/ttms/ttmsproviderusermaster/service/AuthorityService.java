package com.thesevensky.ttms.ttmsproviderusermaster.service;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.UserEmailAndAuthority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 16:48
 * @Version 1.0
 */
public interface AuthorityService {

    public List<Authority> getAll();

    public void updateUserForAuthority(UserEmailAndAuthority userEmailAndAuthority);

    public Authority getMaxAuthority(List<Authority> authorities);
}
