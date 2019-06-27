package com.thesevensky.ttms.moviesmanageapi.constants.user;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 14:14
 * @Version 1.0
 */
public interface AuthorityConstants {

    public final static String PREFIX = "/admin";

    public final static String DEFAULT_ADMIN = "/admin";

    public final static String ADMIN_USER = "/{num}/{bit}/user";

    public final static String ADMIN_CACHE = "authorityCache";

    public final static String ROOT = "root";

    public final static String ADMIN = "admin";

    public final static String HALL_ADMIN = "hallAdmin";

    public final static String VIP = "VIP";

    public final static String USER = "user";

    public final static String DEFAULT_USER = "anonymousUser";

    public final static String[] SECURITY_USER = {
       VIP,USER
    } ;

    public final static String[] SECURITY_ADMIN = {
        ROOT,ADMIN,HALL_ADMIN
    };

    public final static String[] SECURITY_SUPER_ADMIN = {
            ROOT,ADMIN
    };

}
