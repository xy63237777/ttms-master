package com.thesevensky.ttms.ttmsproviderusermaster.dao;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/19 21:05
 * @Version 1.0
 */
@Mapper
public interface AuthorityDao {

    public String[] findAllSimpleName(Integer num);

    public List<Authority> findAll();
}
