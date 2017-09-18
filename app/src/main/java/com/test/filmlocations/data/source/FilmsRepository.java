package com.test.filmlocations.data.source;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;
import com.test.filmlocations.data.source.local.LocalFilmsRepository;
import com.test.filmlocations.data.source.remote.RemoteFilmsRepository;

import java.util.List;

/**
 * Repository for retrieving film location information
 */

public class FilmsRepository implements FilmsDataSource {
    private static FilmsRepository sFilmRepository;

    private LocalFilmsRepository mLocalRepository;
    private RemoteFilmsRepository mRemoteRepository;

    public static FilmsRepository getInstance(LocalFilmsRepository localFilmsRepository, RemoteFilmsRepository remoteFilmsRepository) {
        if (null == sFilmRepository) {
            sFilmRepository = new FilmsRepository(localFilmsRepository, remoteFilmsRepository);
        }

        return sFilmRepository;
    }

    public FilmsRepository(LocalFilmsRepository localFilmsRepository, RemoteFilmsRepository remoteFilmsRepository) {
        mLocalRepository = localFilmsRepository;
        mRemoteRepository = remoteFilmsRepository;
    }

    @Override
    public void getFilmLocations(int limit, int offset, @NonNull final GetFilmsCallback callback) {
        mRemoteRepository.getFilmLocations(limit, offset, new GetFilmsCallback() {
            @Override
            public void onFilmsLoaded(@NonNull List<FilmLocationItem> filmList) {
                callback.onFilmsLoaded(filmList);
            }

            @Override
            public void onDataNotAvailable(FilmsDataSourceErrorInterface errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }
//
//    @Override
//    public void getFilmLocationDetail(@NonNull String filmId, @NonNull GetFilmDetailCallback callback) {
//
//    }
}
