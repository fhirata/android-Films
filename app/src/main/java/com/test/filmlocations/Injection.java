package com.test.filmlocations;

import com.test.filmlocations.data.source.FilmsRepository;
import com.test.filmlocations.data.source.MoviesRepository;
import com.test.filmlocations.data.source.local.LocalFilmsRepository;
import com.test.filmlocations.data.source.local.LocalMoviesRepository;
import com.test.filmlocations.data.source.remote.RemoteFilmsRepository;
import com.test.filmlocations.data.source.remote.RemoteMoviesRepository;

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

    public static MoviesRepository provideMoviesRepository() {
        return MoviesRepository.getInstance(provideLocalMoviesDataSource(), provideRemoteMoviesDataSource());
    }

    public static LocalMoviesRepository provideLocalMoviesDataSource() {
        return LocalMoviesRepository.getInstance();
    }

    public static RemoteMoviesRepository provideRemoteMoviesDataSource() {
        return RemoteMoviesRepository.getInstance();
    }
}
