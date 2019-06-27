package com.thesevensky.ttms.ttmsfileuploadmaster;

import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesForType;
import com.thesevensky.ttms.ttmsfileuploadmaster.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TtmsFileUploadMasterApplicationTests {

    @Autowired
    private MoviesService moviesService = null;

    @Test
    public void contextLoads() {
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

}
