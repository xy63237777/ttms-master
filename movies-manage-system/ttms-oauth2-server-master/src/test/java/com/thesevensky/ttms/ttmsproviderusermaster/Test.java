package com.thesevensky.ttms.ttmsproviderusermaster;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.AuthorityDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 13:44
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private AuthorityDao authorityDao = null;

    @org.junit.Test
    public void test1() {
        String[] strings = authorityDao.findAllSimpleName(5);
        for(String s : strings) {
            System.out.println(s);
        }
    }
}
