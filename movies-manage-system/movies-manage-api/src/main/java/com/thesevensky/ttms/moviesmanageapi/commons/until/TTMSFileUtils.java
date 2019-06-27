package com.thesevensky.ttms.moviesmanageapi.commons.until;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 17:52
 * @Version 1.0
 */
public class TTMSFileUtils {
    private TTMSFileUtils() {
        throw new AssertionError();
    }

    private static Logger logger = LoggerFactory.getLogger(TTMSFileUtils.class);

    public static String getHashPath(Object o) {
        if(o == null) throw new NullPointerException();
        String str = Math.abs(o.hashCode()) + "";
        return str.substring(0, 1) + "/" + str.substring(1, 3) + "/";
    }

    public static void mkdirs(String path) {
        File file = new File(path);
        if(!file.exists()) logger.info("路径" + path + " 创建文件夹的结果 : " + file.mkdirs());
    }

    public static String isImage(String fileName) {
        String suffix = fileName.substring(fileName.length()-4);
        if (StringUtils.isNotEmpty(suffix) && (suffix.equals(".jpg") || suffix.equals(".png"))) {
            return suffix;
        }
        return null;
    }
}
