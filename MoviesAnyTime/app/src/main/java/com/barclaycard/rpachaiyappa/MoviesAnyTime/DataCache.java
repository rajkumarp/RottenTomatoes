package com.barclaycard.rpachaiyappa.MoviesAnyTime;

import com.barclaycard.rpachaiyappa.MoviesAnyTime.model.MovieItemList;

/**
 * Created by rpachaiyappa on 8/3/14.
 */
public class DataCache {

    private static final DataCache sInstance = new DataCache();

    public static DataCache getInstance() {
        return sInstance;
    }

    private final MovieItemList mMovieItemList = new MovieItemList();

    public MovieItemList getMovieItemList() {
        return mMovieItemList;
    }
}
