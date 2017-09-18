package com.test.filmlocations.data.source;

import com.test.filmlocations.data.models.FilmLocationItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Films URI and parameter definition for Retrofit
 */

public interface FilmsInterface {
    @GET("/resource/yitu-d5am.json?$$exclude_system_fields=false")
    Call<List<FilmLocationItem>> getFilms(@Query("$limit") int limit, @Query("$offset") int offset);


// To apply sort based on release year, use the following parameters
//    https://data.sfgov.org/resource/yitu-d5am.json?$$exclude_system_fields=false&$limit=20&$offset=0&$order=release_year%20DESC

}
