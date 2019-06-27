package com.thesevensky.ttms.ttmsfileuploadmaster.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.file.ImageFileProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.MoviesException;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSFileUtils;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSPageHolder;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;

import com.thesevensky.ttms.ttmsfileuploadmaster.commons.advice.ElasticAnnocation;
import com.thesevensky.ttms.ttmsfileuploadmaster.commons.advice.ElasticEnum;
import com.thesevensky.ttms.ttmsfileuploadmaster.dao.MoviesDao;
import com.thesevensky.ttms.ttmsfileuploadmaster.service.MoviesService;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class MoviesServiceImpl implements MoviesService {


    private static volatile Integer pageNum = 0;
    private static final String CACHE_ONE = MoviesConstants.MOVIES_CACHE + "One";

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MoviesDao moviesDao = null;


    @Override
    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE,allEntries = true)
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @ElasticAnnocation(ElasticEnum.INSERT)
    public void insertMovies(Movies movies) throws IOException {
        logger.info(getPrincipal() + " 上传电影 ");
        movies.setMovieDefaultImage(masterProperties.getFile().getImage().getDefaultMovie());
        moviesDao.insertMovies(movies);
//        if(files != null) setMoviesForDefaultImage(movies, defaultImage);
//        else movies.setMovieDefaultImage(masterProperties.getFile().getImage().getDefaultMovie());
//        if(files != null) setMoviesForDetailsImage(movies,files);
//        moviesDao.updateMoviesForImage(movies);
        logger.info(getPrincipal() + "上传电影成功" + movies);
    }

    protected void setMoviesForDefaultImage(Movies movies, MultipartFile defaultImage) throws IOException {
        ImageFileProperties image = masterProperties.getFile().getImage();
        String defaultImageStr =  upLoadFile(defaultImage,movies.getMovieId() + "",image.getMoviePath());
        movies.setMovieDefaultImage(defaultImageStr);
    }

    protected String setMoviesForDefaultImage(Long id, MultipartFile defaultImage) throws IOException {
        ImageFileProperties image = masterProperties.getFile().getImage();
        return  upLoadFile(defaultImage,id + "",image.getMoviePath());
    }

    protected void setMoviesForDetailsImage(Movies movies, MultipartFile[] files) throws IOException {
        Assert.notNull(files, "详情图片不可以为空");
        ImageFileProperties image = masterProperties.getFile().getImage();
        StringBuilder stringBuilder = new StringBuilder();
            for(MultipartFile multipartFile : files)
                stringBuilder.append(upLoadFile(multipartFile, movies.getMovieId() + "", image.getLocalDetailPath())).append(",");
        Assert.notNull(stringBuilder, "异常错误");
        movies.setMovieDetailImage(stringBuilder.toString().substring(Math.max(0,stringBuilder.length()-1)));
    }

    private String upLoadFile(MultipartFile multipartFile,String key, String localPath) throws IOException {
        if(multipartFile == null) {
            throw new NullPointerException("文件为空");
        }
        ImageFileProperties image = masterProperties.getFile().getImage();
        String localFilePath = image.getLocalPath();
        String hashPath = TTMSFileUtils.getHashPath(key);
        String filePath = localFilePath + image.getMoviePath() + hashPath;
        TTMSFileUtils.mkdirs(filePath);
        String suffix = TTMSFileUtils.isImage(multipartFile.getOriginalFilename());
        if(suffix == null) throw new RuntimeException("文件格式只支持.jpg和.png");
        String fileName = key + suffix;
        String file = filePath  + fileName;
        logger.info(getPrincipal() + " 上传图片" + file);
        IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
        return image.getClientPath() +  image.getMoviePath() + hashPath + fileName;
    }


    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = CACHE_ONE,key = "'cache_movie_' + #movies.movieId"),
                    @CacheEvict(cacheNames = CACHE_ONE,key = "'cache_movie_detailed_' + #movies.movieId"),
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE,allEntries = true)
}
    )
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @ElasticAnnocation(ElasticEnum.UPDATE)
    public void updateMovies(Movies movies) throws IOException {
//        if(defaultImage != null) setMoviesForDefaultImage(movies, defaultImage);
//        if(files != null) setMoviesForDetailsImage(movies, files);
        moviesDao.updateMovies(movies);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = CACHE_ONE,key = "'cache_movie_' + #movieId"),
                    @CacheEvict(cacheNames = CACHE_ONE,key = "'cache_movie_detailed_' + #movieId"),
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE,allEntries = true)
            }
    )
    public String updateMoviesForDefaultImage(Long movieId, MultipartFile file) throws IOException {
        String s = setMoviesForDefaultImage(movieId, file);
        logger.info(getPrincipal() + " 用户上传的网页 url -- " + s);
        moviesDao.updateMoviesDefaultImage(movieId, s);
        return s;
    }

    @Override
    @Cacheable(cacheNames = CACHE_ONE, key = "'cache_movie_' + #id")
    public Movies queryById(Long id) {
        return moviesDao.queryById(id);
    }


    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_delailed_' + #num +'-' + #size")
    public TTMSPageInfo<Movies> queryAllOfDetailed(Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryAllOfDetailed() );
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = CACHE_ONE, key = "'cache_movie_detailed_' + #id")
    public Movies queryByIdOfDetailed(Long id) {
        return moviesDao.queryByIdOfDetailed(id);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_' + #num +'-' + #size")
    public TTMSPageInfo<Movies> queryAll(Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryAll());
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_' + #num +'-' + #size + '-' + #bit")
    public TTMSPageInfo<Movies> queryByTypeBit(Integer bit, Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryByTypeBit(bit));
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Cacheable(cacheNames = MoviesConstants.MOVIES_CACHE, key = "'cache_movie_detailed_' + #num +'-' + #size + '-' + #bit")
    public TTMSPageInfo<Movies> queryByTypeBitOfDetailed(Integer bit, Integer num, Integer size) {
        Page<Movies> movies = PageHelper.startPage(num,
                size == null?masterProperties.getMovies().getMoviesPageProperties().getPageSize():size)
                .doSelectPage(() -> moviesDao.queryByTypeBitOfDetailed(bit));
        pageNum = movies.getPages();
        return TTMSPageHolder.changePageForObj(movies);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = MoviesConstants.MOVIES_CACHE, allEntries = true),
                    @CacheEvict(cacheNames = CACHE_ONE,key = "'cache_movie_' + #id"),
                    @CacheEvict(cacheNames = CACHE_ONE,key = "'cache_movie_detailed_' + #id")
            }
    )
    @ElasticAnnocation(ElasticEnum.DELETE)
    public void deleteMovies(Long id) {
        Movies movies = moviesDao.queryByIdForImage(id);
        Integer integer = moviesDao.deleteMovies(id);
        if(integer == 0) throw new MoviesException("电影为热映状态不可以删除");
        else {
            String imageStr = movies.getMovieDefaultImage();
            if(StringUtils.isNotEmpty(imageStr) && !imageStr.equals(masterProperties.getFile().getImage().getDefaultMovie())) {
                logger.info("删除默认显示图片" + imageStr + "结果是" + deleteImageFile(imageStr));
            }
            imageStr = movies.getMovieDetailImage();
            if(imageStr != null) {
                String[] imageArr = imageStr.split(",");
                for (String str : imageArr)
                    logger.info("删除详情图片" + str + "的结果是" + deleteImageFile(str));
            }
        }
    }

    private boolean deleteImageFile(String urlPath) {
        if(!StringUtils.isNotEmpty(urlPath)) return false;
        ImageFileProperties image = masterProperties.getFile().getImage();
        String filePath = urlPath.replaceAll(image.getClientPath(), image.getLocalPath());
        File file = new File(filePath);
        if(file.exists()) return file.delete();
        return false;
    }


    @Override
    public Integer getNum() {
        if(pageNum == 0)
            synchronized (this) {
                if(pageNum == 0)
                    queryAll(1, null);
            }
        return pageNum;
    }

    private String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
