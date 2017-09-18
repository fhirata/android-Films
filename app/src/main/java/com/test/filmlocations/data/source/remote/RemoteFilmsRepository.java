package com.test.filmlocations.data.source.remote;

import android.support.annotation.NonNull;

import com.test.filmlocations.BuildConfig;
import com.test.filmlocations.data.models.FilmLocationItem;
import com.test.filmlocations.data.source.FilmsDataSource;
import com.test.filmlocations.data.source.FilmsInterface;
import com.test.filmlocations.data.source.errors.FilmsDataSourceError;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.filmlocations.data.source.errors.FilmsDataSourceError.RETROFIT_ERROR;

/**
 * Remote repository for fetching films from network
 */

public class RemoteFilmsRepository implements FilmsDataSource {
    private static final int READ_TIMEOUT = 10;
    private static final int WRITE_TIMEOUT = 10;
    private static final int CONNECT_TIMEOUT = 10;
    private static RemoteFilmsRepository sRemoteFilmsRepository;

    private FilmsInterface mFilmsInterface;

    private static final String BASE_URL = BuildConfig.FILMS_API_BASE_URL;

    public static RemoteFilmsRepository getInstance() {
        if (null == sRemoteFilmsRepository) {
            sRemoteFilmsRepository = new RemoteFilmsRepository();
        }

        return sRemoteFilmsRepository;
    }

    private RemoteFilmsRepository() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient()
                .newBuilder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor bodyInterceptor = new HttpLoggingInterceptor();
        bodyInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(bodyInterceptor);

        OkHttpClient client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        mFilmsInterface = retrofit.create(FilmsInterface.class);
    }

    @Override
    public void getFilmLocations(int limit, int offset, @NonNull final GetFilmsCallback callback) {
        Call<List<FilmLocationItem>> call = mFilmsInterface.getFilms(limit, offset);
        call.enqueue(new Callback<List<FilmLocationItem>>() {
            @Override
            public void onResponse(Call<List<FilmLocationItem>> call, Response<List<FilmLocationItem>> response) {
                if (response.isSuccessful()) {
                    List<FilmLocationItem> filmResponse = response.body();
                    callback.onFilmsLoaded(filmResponse);
                } else {
                    try {
                        callback.onDataNotAvailable(new FilmsDataSourceError(response.code(), response.errorBody().string()));
                    } catch (IOException e) {
                        callback.onDataNotAvailable(new FilmsDataSourceError(response.code(), "Error with Retrofit fetch."));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FilmLocationItem>> call, Throwable t) {
                callback.onDataNotAvailable(new FilmsDataSourceError(RETROFIT_ERROR, t.getMessage()));
            }
        });
    }

    @Override
    public void getFilmLocationDetail(@NonNull String filmId, @NonNull GetFilmDetailCallback callback) {

    }
}
