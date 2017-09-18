package com.test.filmlocations.data.source;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;

import java.util.List;

/**
 *  Specifies the behavior of the films repository classes
 */

public interface FilmsDataSource extends BaseDataSource {
    interface GetFilmsCallback {
        void onFilmsLoaded(@NonNull List<FilmLocationItem> filmList);

        void onDataNotAvailable(FilmsDataSourceErrorInterface errorMessage);
    }

    void getFilmLocations(final int limit, final int offset, @NonNull GetFilmsCallback callback);
}
