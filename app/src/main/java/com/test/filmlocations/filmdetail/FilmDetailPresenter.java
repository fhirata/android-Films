package com.test.filmlocations.filmdetail;

import android.support.annotation.NonNull;

import com.test.filmlocations.R;
import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.models.MovieItem;
import com.test.filmlocations.data.source.FilmsDataSource;
import com.test.filmlocations.data.source.FilmsRepository;
import com.test.filmlocations.data.source.MoviesDataSource;
import com.test.filmlocations.data.source.MoviesRepository;
import com.test.filmlocations.data.source.errors.FilmsDataSourceErrorInterface;

import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.CACHE_MISS_ERROR;
import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.INVALID_KEY;
import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.RETROFIT_ERROR;

/**
 * Handles all business logic related to Film Detail Fragment
 */

public class FilmDetailPresenter implements FilmDetailContract.PresenterInterface {

    private FilmDetailContract.ActivityInterface mActivityInterface;
    private FilmDetailContract.ViewInterface mViewInterface;
    private String mApiKey;
    private String mLanguage;
    @NonNull private final FilmsRepository mFilmRepository;
    @NonNull private final MoviesRepository mMoviesRepository;
    private Integer mFilmLocationId;
    private String mFilmName;

    public FilmDetailPresenter(FilmDetailContract.ViewInterface filmFragment,
                               FilmDetailContract.ActivityInterface activityInterface,
                               FilmsRepository repository,
                               MoviesRepository moviesRepository,
                               String apiKey,
                               String language,
                               Integer filmLocationId,
                               String filmName) {

        mViewInterface = filmFragment;
        mActivityInterface = activityInterface;
        mFilmRepository = repository;
        mMoviesRepository = moviesRepository;
        mApiKey = apiKey;
        mLanguage = language;
        mFilmLocationId = filmLocationId;
        mFilmName = filmName;

        mViewInterface.setPresenter(this);
    }


    @Override
    public void start() {
        getFilmById();
        getMovieDetailById();
    }

    private void getFilmById() {
        mFilmRepository.getFilmLocationById(mFilmLocationId, new FilmsDataSource.GetFilmDetailCallback() {
            @Override
            public void onFilmLoaded(@NonNull FilmLocationItem filmLocationItem) {

                mViewInterface.setMovieTitle(filmLocationItem.getTitle());

                if (null != filmLocationItem.getLocations()) {
                    mViewInterface.setMapAddressLocation(filmLocationItem.getLocations());
                }
                mViewInterface.setActors(filmLocationItem.getActors());
                mViewInterface.setDirector(filmLocationItem.getDirector());
                mViewInterface.setPoster(filmLocationItem.getPosterPath());
            }

            @Override
            public void onDataNotAvailable(FilmsDataSourceErrorInterface errorInterface) {
                handleErrorStatus(errorInterface);
            }
        });
    }

    private void getMovieDetailById() {
        mMoviesRepository.searchMovieByTitle(-1, mApiKey, mLanguage, mFilmName, new MoviesDataSource.GetMovieDetailCallback() {
            @Override
            public void onMovieDetailLoaded(@NonNull MovieItem movieItem, int index) {
                mViewInterface.setDescription(movieItem.getOverview());
            }

            @Override
            public void onDataNotAvailable(@NonNull FilmsDataSourceErrorInterface errorInterface) {
                handleErrorStatus(errorInterface);
            }
        });
    }

    /**
     * Duplicated for now, can be consolidated in the future
     * @param errorInterface
     */
    private void handleErrorStatus(FilmsDataSourceErrorInterface errorInterface) {
        switch(errorInterface.getErrorCode()) {
            case RETROFIT_ERROR:
                mActivityInterface.showErrorMessage(R.string.retrofit_fetch_error);
                return;
            case INVALID_KEY:
                mActivityInterface.showErrorMessage(R.string.invalid_api_key_error);
                return;
            case CACHE_MISS_ERROR:
                mActivityInterface.showErrorMessage(R.string.cache_miss_error);
                return;
        }
    }
}
