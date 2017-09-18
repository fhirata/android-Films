package com.test.filmlocations.data.source.remote;

import android.support.annotation.NonNull;

import com.test.filmlocations.BuildConfig;
import com.test.filmlocations.data.models.MovieApiResponse;
import com.test.filmlocations.data.models.MovieItem;
import com.test.filmlocations.data.source.MoviesDataSource;
import com.test.filmlocations.data.source.MoviesInterface;
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
 * Fetches Movies information the api.themoviedb.org
 */

public class RemoteMoviesRepository implements MoviesDataSource {

    private static RemoteMoviesRepository sRemoteMoviesRepository;

    private MoviesInterface mMoviesInterface;

    private static final String BASE_URL = BuildConfig.MOVIES_API_BASE_URL;

    public static RemoteMoviesRepository getInstance() {
        if (null == sRemoteMoviesRepository) {
            sRemoteMoviesRepository = new RemoteMoviesRepository();
        }

        return sRemoteMoviesRepository;
    }

    private RemoteMoviesRepository() {
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

        mMoviesInterface = retrofit.create(MoviesInterface.class);
    }

    @Override
    public void getMovieDetail(final int index, @NonNull String apiKey, @NonNull String language, @NonNull String movieTitle, final @NonNull GetMovieDetailCallback callback) {
        Call<MovieApiResponse> call = mMoviesInterface.searchMovies(apiKey, movieTitle, language);
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
                if (response.isSuccessful()) {
                    MovieApiResponse movieApiResponse = response.body();
                    List<MovieItem> movieResponse = movieApiResponse.makeMovieItems();
                    callback.onMovieDetailLoaded(movieResponse.get(0), index);
                } else {
                    try {
                        callback.onDataNotAvailable(new FilmsDataSourceError(response.code(), response.errorBody().string()));
                    } catch (IOException e) {
                        callback.onDataNotAvailable(new FilmsDataSourceError(response.code(), "Error fetching movie detail via Retrofit."));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
                callback.onDataNotAvailable(new FilmsDataSourceError(RETROFIT_ERROR, t.getMessage()));
            }
        });
    }
}
