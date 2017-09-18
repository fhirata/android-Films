package com.test.filmlocations.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Parses a Movie json
 */

public class MovieApiResponse {
    @SerializedName("page")
    @Expose
    public Integer mPage;
    @SerializedName("total_results")
    @Expose
    public Integer mTotalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer mTotalPages;
    @SerializedName("results")
    @Expose
    public List<MovieItem> mResults = null;

    public List<MovieItem> makeMovieItems() {
        return mResults;
    }
}
