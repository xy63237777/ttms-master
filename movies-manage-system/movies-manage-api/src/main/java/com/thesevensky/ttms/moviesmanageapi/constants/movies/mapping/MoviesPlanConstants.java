package com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/28 11:33
 * @Version 1.0
 */
public interface MoviesPlanConstants {

    public final static String PREFIX = "/moviesPlan";

    public final static String MOVIES_PLAN_OF_DAY = "/{moviesId}/{day}/moviesPlan";

    public final static String MOVIES_FIND_ONE_PLAN = "/{planId}/moviesPlan/one";

    public final static String DEFAULT_MOVIES_PLAN = "/moviesPlan";

    public final static String MOVIES_DELETE_PLAN = "/{moviesId}/moviesPlan";

    public final static String MOVIES_PLAN_CACHE = "MoviesPlanCache";

    public final static String MOVIES_PLAN_PLAN_FOR_DAY = "/{day}/moviesPlan";

    public static final String CACHE_ONE = MoviesPlanConstants.MOVIES_PLAN_CACHE + "One";
}
