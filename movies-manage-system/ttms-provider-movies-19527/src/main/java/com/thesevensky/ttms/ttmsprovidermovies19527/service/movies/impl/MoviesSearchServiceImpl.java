package com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.impl;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.elastic.ElasticMoviesProperties;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.ttmsprovidermovies19527.service.movies.MoviesSearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 23:52
 * @Version 1.0
 */
@Service
public class MoviesSearchServiceImpl implements MoviesSearchService {

    private static final String SEARCH_PARTTEN_FOR_NAME = "{\"query\":{\"match\":{\"movieName\":\"%s\"}}}";

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JestClient jestClient = null;

    @Override
    public List<Movies> searchMoviesForName(String name) {
        ElasticMoviesProperties movies = masterProperties.getElastic().getMovies();
        String searchJson = String.format(SEARCH_PARTTEN_FOR_NAME, name);
        logger.info("进行分布式搜索 " + searchJson);
        Search build = new Search.Builder(searchJson).addIndex(movies.getIndex()).addType(movies.getType()).build();
        SearchResult execute = null;
        try {
            execute = jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(execute);
        if(execute != null) {
            List<SearchResult.Hit<Movies, Void>> hits = execute.getHits(Movies.class);
            List<Movies> moviesList = new ArrayList<>();
            for(SearchResult.Hit<Movies, Void> moviesVoidHit : hits) {
                moviesList.add(moviesVoidHit.source);
            }
            return moviesList;
        }
        return null;
    }
}
