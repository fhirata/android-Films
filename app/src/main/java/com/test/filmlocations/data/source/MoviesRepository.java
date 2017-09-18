package com.test.filmlocations.data.source;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.models.MovieItem;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;
import com.test.filmlocations.data.source.local.LocalMoviesRepository;
import com.test.filmlocations.data.source.remote.RemoteMoviesRepository;

import java.util.HashMap;

/**
 * Movies Repository retriving from api.themoviedb.org (local and remote)
 */

public class MoviesRepository implements MoviesDataSource {
    private static MoviesRepository sMoviesRepository;
    private static LocalMoviesRepository mLocalMoviesRepository;
    private static RemoteMoviesRepository mRemoteMoviesRepository;
    private static HashMap<String, MovieItem> sMovieCache = new HashMap<>();

    public static MoviesRepository getInstance(LocalMoviesRepository localMoviesRepository, RemoteMoviesRepository remoteMoviesRepository) {
        if (null == sMoviesRepository) {
            sMoviesRepository = new MoviesRepository(localMoviesRepository, remoteMoviesRepository);
        }
        return sMoviesRepository;
    }

    public MoviesRepository(LocalMoviesRepository localMoviesRepository, RemoteMoviesRepository remoteMoviesRepository) {
        mLocalMoviesRepository = localMoviesRepository;
        mRemoteMoviesRepository = remoteMoviesRepository;
    }

    @Override
    public void getMovieDetail(final int index, @NonNull String apiKey, @NonNull String language, @NonNull final String movieTitle, @NonNull final GetMovieDetailCallback callback) {
        if (sMovieCache.containsKey(movieTitle)) {
            callback.onMovieDetailLoaded(sMovieCache.get(movieTitle), index);
            return;
        }
        mRemoteMoviesRepository.getMovieDetail(index, apiKey, language, movieTitle, new GetMovieDetailCallback() {
            @Override
            public void onMovieDetailLoaded(@NonNull MovieItem movieItem, int index) {
                if (!sMovieCache.containsKey(movieTitle)) {
                    sMovieCache.put(movieTitle, movieItem);
                }
                callback.onMovieDetailLoaded(movieItem, index);
            }

            @Override
            public void onDataNotAvailable(@NonNull FilmsDataSourceErrorInterface dataSourceError) {
                callback.onDataNotAvailable(dataSourceError);
            }
        });
    }
}
