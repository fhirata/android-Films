package com.test.filmlocations.films;

import android.support.annotation.NonNull;

import com.test.filmlocations.R;
import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.source.FilmsDataSource;
import com.test.filmlocations.data.source.FilmsRepository;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;

import java.util.List;

import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.RETROFIT_ERROR;

/**
 *
 */

public class FilmsPresenter implements FilmsContract.PresenterInterface {
    private final int DEFAULT_FETCH_COUNT = 50;
    private FilmsContract.ActivityInterface mActivityInterface;
    private FilmsContract.ViewInterface mViewInterface;

    @NonNull
    private final FilmsRepository mFilmRepository;

    @Override
    public void start() {
        loadFilmLocations(DEFAULT_FETCH_COUNT, 0);
    }

    public FilmsPresenter(FilmsContract.ViewInterface filmFragment, FilmsContract.ActivityInterface activityInterface, FilmsRepository repository) {
        mViewInterface = filmFragment;
        mActivityInterface = activityInterface;
        mFilmRepository = repository;

        mViewInterface.setPresenter(this);
    }

    private void loadFilmLocations(int limit, int offset) {
        mFilmRepository.getFilmLocations(limit, offset, new FilmsDataSource.GetFilmsCallback() {
            @Override
            public void onFilmsLoaded(@NonNull List<FilmLocationItem> filmList) {
                mViewInterface.updateFilms(filmList);
            }

            @Override
            public void onDataNotAvailable(FilmsDataSourceErrorInterface errorMessage) {
                switch(errorMessage.getErrorCode()) {
                    case RETROFIT_ERROR:
                        mActivityInterface.showErrorMessage(R.string.retrofit_fetch_error);
                }

            }
        });
    }

}
