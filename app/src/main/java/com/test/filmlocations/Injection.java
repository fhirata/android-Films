package com.test.filmlocations;

import com.test.filmlocations.data.source.FilmsRepository;
import com.test.filmlocations.data.source.local.LocalFilmsRepository;
import com.test.filmlocations.data.source.remote.RemoteFilmsRepository;

/**
 *
 */

public class Injection {
    public static FilmsRepository provideFilmsRepository() {
        return FilmsRepository.getInstance(provideLocalDataSource(), provideRemoteDataSource());
    }

    public static LocalFilmsRepository provideLocalDataSource() {
        return LocalFilmsRepository.getInstance();
    }

    public static RemoteFilmsRepository provideRemoteDataSource() {
        return RemoteFilmsRepository.getInstance();
    }
}
