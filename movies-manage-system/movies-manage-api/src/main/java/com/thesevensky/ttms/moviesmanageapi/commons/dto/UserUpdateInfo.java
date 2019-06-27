package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/4 15:53
 * @Version 1.0
 */
public class UserUpdateInfo implements Serializable {

    private static final long serialVersionUID = 4276839465427252921L;
    private User user;
    private MultipartFile file;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UserUpdateInfo{" +
                "user=" + user +
                ", file=" + file +
                '}';
    }
}
