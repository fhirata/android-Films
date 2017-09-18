package com.test.filmlocations.data.source;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.models.MovieItem;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;

/**
 *
 */

public interface MoviesDataSource extends BaseDataSource {
    interface GetMovieDetailCallback {
        void onMovieDetailLoaded(@NonNull MovieItem movieItem, int index);

        void onDataNotAvailable(@NonNull FilmsDataSourceErrorInterface dataSourceError);
    }

    void searchMovieByTitle(final int index, @NonNull String apiKey, @NonNull String language, @NonNull String movieTitle, @NonNull final GetMovieDetailCallback callback);
}
