package com.thesevensky.ttms.ttmsprovidermovies19527.commons.until;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/29 13:22
 * @Version 1.0
 */
public class SecurtyUntils {
    public static String Principal() {
        return (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
