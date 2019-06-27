package com.thesevensky.ttms.ttmsfileuploadmaster.controller;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesForType;
import com.thesevensky.ttms.ttmsfileuploadmaster.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 17:17
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Autowired
    private MoviesService moviesService = null;

    @GetMapping("/hello")
    @PreAuthorize("permitAll()")
    public void hello() {
        Movies movies = new Movies();
        movies.setMovieAuthor("123");
        movies.setMovieName("指环王");
        MoviesForType moviesForType = new MoviesForType();
        moviesForType.setTypeNumber(7);
        movies.setMoviesTypes(moviesForType);
        movies.setMovieLength(150);
        System.out.println(movies);
        //moviesService.insertMovies(movies);
        System.out.println(movies);
    }

    @GetMapping("/tt1/{id}")
    @PreAuthorize("permitAll()")
    public void tt1(@PathVariable Long id) {
        moviesService.deleteMovies(id);
    }

//    @GetMapping("/tt2")
//    @PreAuthorize("permitAll()")
//    public void tt2() throws IOException {
//        Movies movies = new Movies();
//        movies.setMovieAuthor("123");
//        movies.setMovieName("指环王");
//        MoviesForType moviesForType = new MoviesForType();
//        moviesForType.setTypeNumber(7);
//        movies.setMoviesTypes(moviesForType);
//        movies.setMovieLength(150);
//        System.out.println(movies);
//        moviesService.insertMovies(movies,null,null);
//        System.out.println(movies);
//    }
}
