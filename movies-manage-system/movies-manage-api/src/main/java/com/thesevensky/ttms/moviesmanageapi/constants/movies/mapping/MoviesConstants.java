package com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping;

public interface MoviesConstants {

    public final static String PREFIX = "/movies";

    public final static String ALL_MOVIES_AND_NUM = "/{num}/movies";

    public final static String ALL_MOVIES_NUM_DETAILS = "/{num}/movies/details";

    public final static String ONE_MOVIE_ID = "/{id}/movies/one";

    public final static String TYPE_MOVIES_NUM_BIT = "/{num}/movies/{bit}/type";

    public final static String TYPE_MOVIES_NUM_BIT_DETAILS = "/{num}/movies/{bit}/type/details";

    public final static String MOVIES_VOL_DESC = "/{num}/vol";

    public final static String DEFAULT_MOVIES = "/movies";

    public final static String ID_DEFAULT_MOVIES = "/{id}/movies";

    public final static String MOVIES_CACHE = "MoviesCache";

    public final static String CACHE_ONE = MoviesConstants.MOVIES_CACHE + "One";

    public final static String MOVIES_SEARCH_FOR_NAME = "/movies/search";

    public final static String MOVIES_STATUS_FOR_ALL = "/status/{status}/{num}";

    public final static String MOVIES_STATUS_FOR_ALL_DETAILED = "/status/{status}/{num}/details";

    public final static String MOVIES_STATUS_FOR_HOT = "/hot/{num}";

    public final static String MOVIES_STATUS_FOR_HOT_DETAILED = "/hot/{num}/details";

    public final static String MOVIES_TIME_AFTER = "/after/{num}";

    public final static String MOVIES_UPDATE_IMAGE = "/image/{id}/movies";


}
