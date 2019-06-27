package com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping;

import org.apache.ibatis.session.SqlSession;

public interface MoviesSeatConstants {

    public final static SqlSession DEFAULT_SQL_SESSION = null;

    public final static String PREFIX = "/moviesSeat";

    public final static String DEFAULT_MOVIES_SEAT = "/moviesSeat";

    public final static String ID_DEFAULT_MOVIES_SEAT = "/{id}/moviesSeat";

    public final static String MOVIES_SEAT_BUY = "/buy/moviesSeat";

    public final static String MOVIES_SEAT_OK = "/ok/moviesSeat";

    public final static String MOVIES_SEAT_BED = "/bed/moviesSeat";

    public final static String MOVIES_SEAT_NOT_LOCK = "/not/moviesSeat";

    public final static String MOVIES_SEAT_FOR_LOCK = "seatLock_";

    public final static String MOVIES_SEAT_FOR_REDIS = "redis_seat";

    public final static String MOVIES_SEAT_CACHE = "MoviesSeatCache";

    public final static String MOVIES_SEAT_NOT_ALL = "/not/all";
}
