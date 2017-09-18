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

    }

    @Override
    public void getFilmLocationDetail(@NonNull String filmId, @NonNull GetFilmDetailCallback callback) {

    }
}
