package com.test.filmlocations.data.source;

import com.test.filmlocations.data.models.MovieApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit Movies Interface
 */

public interface MoviesInterface {
    @GET("/3/search/multi?")
    Call<MovieApiResponse> searchMovies(@Query("api_key") String apiKey, @Query("query") String query, @Query("language") String language);
}
