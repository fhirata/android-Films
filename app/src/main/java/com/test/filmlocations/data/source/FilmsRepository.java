package com.test.filmlocations.data.source;

import android.support.annotation.NonNull;

import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.source.errors.FilmsDataSourceError;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;
import com.test.filmlocations.data.source.local.LocalFilmsRepository;
import com.test.filmlocations.data.source.remote.RemoteFilmsRepository;

import java.util.HashMap;
import java.util.List;

import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.CACHE_MISS_ERROR;

/**
 * Repository for retrieving film location information
 */

public class FilmsRepository implements FilmsDataSource {
    private static FilmsRepository sFilmRepository;

    private LocalFilmsRepository mLocalRepository;
    private RemoteFilmsRepository mRemoteRepository;
    private HashMap<Integer, FilmLocationItem> sFilmLocationCache;

    public static FilmsRepository getInstance(LocalFilmsRepository localFilmsRepository, RemoteFilmsRepository remoteFilmsRepository) {
        if (null == sFilmRepository) {
            sFilmRepository = new FilmsRepository(localFilmsRepository, remoteFilmsRepository);
        }

        return sFilmRepository;
    }

    public FilmsRepository(LocalFilmsRepository localFilmsRepository, RemoteFilmsRepository remoteFilmsRepository) {
        mLocalRepository = localFilmsRepository;
        mRemoteRepository = remoteFilmsRepository;

        sFilmLocationCache = new HashMap<>();
    }

    @Override
    public void getFilmLocations(int limit, int offset, @NonNull final GetFilmsCallback callback) {
        mRemoteRepository.getFilmLocations(limit, offset, new GetFilmsCallback() {
            @Override
            public void onFilmsLoaded(@NonNull List<FilmLocationItem> filmList) {
                // Fetch and store in cache for now
                for(int i=0; i<filmList.size(); i++) {
                    FilmLocationItem filmLocationItem = filmList.get(i);
                    sFilmLocationCache.put(filmLocationItem.getId(), filmLocationItem);
                }
                callback.onFilmsLoaded(filmList);
            }

            @Override
            public void onDataNotAvailable(FilmsDataSourceErrorInterface errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        });
    }

    @Override
    public void getFilmLocationById(@NonNull Integer filmId, @NonNull GetFilmDetailCallback callback) {
        if (sFilmLocationCache.containsKey(filmId)) {
            callback.onFilmLoaded(sFilmLocationCache.get(filmId));
            return;
        }

        callback.onDataNotAvailable(new FilmsDataSourceError(CACHE_MISS_ERROR, "FilmId not found in cache."));
        return;
    }
}
