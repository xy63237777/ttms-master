package com.thesevensky.starter.properties.elastic;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 0:42
 * @Version 1.0
 */
public class ElasticProperties {
    private ElasticMoviesProperties movies = new ElasticMoviesProperties();

    public ElasticMoviesProperties getMovies() {
        return movies;
    }

    public void setMovies(ElasticMoviesProperties movies) {
        this.movies = movies;
    }
}
