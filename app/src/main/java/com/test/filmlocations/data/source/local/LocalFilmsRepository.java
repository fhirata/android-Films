package com.test.filmlocations.data.source.local;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.source.FilmsDataSource;

/**
 * Local repository for fetching films from local storage (Realm)
 */

public class LocalFilmsRepository implements FilmsDataSource {
    private static LocalFilmsRepository sLocalFilmsRepository;

    public static LocalFilmsRepository getInstance() {
        if (null == sLocalFilmsRepository) {
            sLocalFilmsRepository = new LocalFilmsRepository();
        }
        return sLocalFilmsRepository;
    }

    @Override
    public void getFilmLocations(final int limit, final int offset, @NonNull GetFilmsCallback callback) {
        // no-op - no local storage for film locations yet
    }

    @Override
    public void getFilmLocationById(@NonNull Integer filmId, @NonNull GetFilmDetailCallback callback) {
        // no-op - no local storage for film locations yet
    }
}
