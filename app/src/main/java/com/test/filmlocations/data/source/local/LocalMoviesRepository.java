package com.test.filmlocations.data.source.local;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.source.MoviesDataSource;

/**
 * Local storage for movies repository
 */

public class LocalMoviesRepository implements MoviesDataSource {
    private static LocalMoviesRepository sLocalMoviesRepository;

    public static LocalMoviesRepository getInstance() {
        if (null == sLocalMoviesRepository) {
            sLocalMoviesRepository = new LocalMoviesRepository();
        }
        return sLocalMoviesRepository;
    }

    @Override
    public void getMovieDetail(final int index, @NonNull String apiKey, @NonNull String language, @NonNull String movieTitle, @NonNull GetMovieDetailCallback callback) {
        // no-op - local storage not implemented yet.
    }
}
