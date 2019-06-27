package com.thesevensky.starter.properties.file;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 17:45
 * @Version 1.0
 */
public class ImageFileProperties {
    private String localPath = "D:/ttms/images/";
    private String localHeadPath = localPath + "head/";
    private String localMoviePath = localPath + "movies/";
    private String localDetailPath = localPath + "detail/";
    private String localCodePath = localPath + "code/";
    private String clientPath = "http://www.thesevensky.com:11111/images/";
    private String defaultHead = clientPath + "head/default.png";
    private String defaultMovie = clientPath + "movies/default.jpg";
    private String headPath = "head/";
    private String moviePath = "movies/";
    private String detailPath = "detail/";
    private String codePath = "code/";
    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getLocalHeadPath() {
        return localHeadPath;
    }

    public void setLocalHeadPath(String localHeadPath) {
        this.localHeadPath = localHeadPath;
    }

    public String getLocalMoviePath() {
        return localMoviePath;
    }

    public void setLocalMoviePath(String localMoviePath) {
        this.localMoviePath = localMoviePath;
    }

    public String getLocalDetailPath() {
        return localDetailPath;
    }

    public void setLocalDetailPath(String localDetailPath) {
        this.localDetailPath = localDetailPath;
    }

    public String getClientPath() {
        return clientPath;
    }

    public void setClientPath(String clientPath) {
        this.clientPath = clientPath;
    }

    public String getDefaultHead() {
        return defaultHead;
    }

    public void setDefaultHead(String defaultHead) {
        this.defaultHead = defaultHead;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getMoviePath() {
        return moviePath;
    }

    public void setMoviePath(String moviePath) {
        this.moviePath = moviePath;
    }

    public String getDetailPath() {
        return detailPath;
    }

    public void setDetailPath(String detailPath) {
        this.detailPath = detailPath;
    }

    public String getDefaultMovie() {
        return defaultMovie;
    }

    public void setDefaultMovie(String defaultMovie) {
        this.defaultMovie = defaultMovie;
    }

    public String getLocalCodePath() {
        return localCodePath;
    }

    public void setLocalCodePath(String localCodePath) {
        this.localCodePath = localCodePath;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }
}
