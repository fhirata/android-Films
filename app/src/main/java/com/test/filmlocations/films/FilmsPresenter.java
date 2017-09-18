package com.test.filmlocations.films;

import android.support.annotation.NonNull;

import com.test.filmlocations.R;
import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.models.MovieItem;
import com.test.filmlocations.data.source.FilmsDataSource;
import com.test.filmlocations.data.source.FilmsRepository;
import com.test.filmlocations.data.source.MoviesDataSource;
import com.test.filmlocations.data.source.MoviesRepository;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;

import java.util.List;

import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.INVALID_KEY;
import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.RETROFIT_ERROR;

/**
 *
 */

public class FilmsPresenter implements FilmsContract.PresenterInterface {
    private final int DEFAULT_FETCH_COUNT = 50;
    private FilmsContract.ActivityInterface mActivityInterface;
    private FilmsContract.ViewInterface mViewInterface;
    private String mApiKey;
    private String mLanguage;

    @NonNull
    private final FilmsRepository mFilmRepository;

    @NonNull
    private final MoviesRepository mMoviesRepository;

    @Override
    public void start() {
        loadFilmLocations(DEFAULT_FETCH_COUNT, 0);
    }

    public FilmsPresenter(FilmsContract.ViewInterface filmFragment,
                          FilmsContract.ActivityInterface activityInterface,
                          FilmsRepository repository,
                          MoviesRepository moviesRepository,
                          String apiKey,
                          String language) {
        mViewInterface = filmFragment;
        mActivityInterface = activityInterface;
        mFilmRepository = repository;
        mMoviesRepository = moviesRepository;
        mApiKey = apiKey;
        mLanguage = language;

        mViewInterface.setPresenter(this);
    }

    private void loadFilmLocations(int limit, int offset) {
        mFilmRepository.getFilmLocations(limit, offset, new FilmsDataSource.GetFilmsCallback() {
            @Override
            public void onFilmsLoaded(@NonNull List<FilmLocationItem> filmList) {
                mViewInterface.updateFilms(filmList);

                for(int index=0; index < filmList.size(); index++) {
                    final FilmLocationItem filmItem = filmList.get(index);
                    mMoviesRepository.getMovieDetail(index, mApiKey, mLanguage, filmItem.getTitle(), new MoviesDataSource.GetMovieDetailCallback() {
                        @Override
                        public void onMovieDetailLoaded(@NonNull MovieItem movieItem, final int index) {
                            if (!movieItem.getPosterPath().isEmpty()) {
                                mViewInterface.updateFilmItemPoster(index, movieItem.getPosterPath());
                            }
                        }

                        @Override
                        public void onDataNotAvailable(@NonNull FilmsDataSourceErrorInterface dataSourceError) {
                            handleErrorStatus(dataSourceError);
                        }
                    });
                }
            }

            @Override
            public void onDataNotAvailable(FilmsDataSourceErrorInterface errorMessage) {
                handleErrorStatus(errorMessage);
            }
        });
    }

    private void handleErrorStatus(FilmsDataSourceErrorInterface errorInterface) {
        switch(errorInterface.getErrorCode()) {
            case RETROFIT_ERROR:
                mActivityInterface.showErrorMessage(R.string.retrofit_fetch_error);
                return;
            case INVALID_KEY:
                mActivityInterface.showErrorMessage(R.string.invalid_api_key_error);
                return;
        }
    }
}
