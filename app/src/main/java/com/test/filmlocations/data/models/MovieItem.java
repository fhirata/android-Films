package com.test.filmlocations.data.models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Models information about a Movie that complements the item in the list and detail screen.
 */

public class MovieItem {

    private static String TAG = MovieItem.class.getSimpleName();

    @SerializedName("vote_average")
    @Expose
    public Double mVoteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer mVoteCount;
    @SerializedName("id")
    @Expose
    public Integer mId;
    @SerializedName("video")
    @Expose
    public Boolean mVideo;
    @SerializedName("media_type")
    @Expose
    public String mMediaType;
    @SerializedName("title")
    @Expose
    public String mTitle;
    @SerializedName("popularity")
    @Expose
    public Double mPopularity;
    @SerializedName("poster_path")
    @Expose
    public String mPosterPath;
    @SerializedName("original_language")
    @Expose
    public String mOriginalLanguage;
    @SerializedName("original_title")
    @Expose
    public String mOriginalTitle;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> mGenreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    public String mBackdropPath;
    @SerializedName("adult")
    @Expose
    public Boolean mAdult;
    @SerializedName("overview")
    @Expose
    public String mOverview;
    @SerializedName("release_date")
    @Expose
    public String mReleaseDateStr;

    private transient Date mReleaseDate;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    
    public MovieItem() {
        Log.d(TAG, "MovieItem() called");
    }

    /**
     *
     * @return if Unknown, returns empty
     */
    public String getReleaseDate() {
        if (mReleaseDate != null) {
            return dateFormat.format(mReleaseDate);
        }
        return "";
    }

    /**
     *
     * @return if Unknown, returns empty
     */
    public String getReleaseYear() {
        if (mReleaseDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mReleaseDate);
            return String.valueOf(calendar.get(Calendar.YEAR));
        }
        return "";
    }

    public void setReleaseDate(String releaseDate) {
        try {
            mReleaseDate = dateFormat.parse(releaseDate);
        } catch (ParseException dpe) {
            Log.e(TAG, "Failed to parse full date: " + dpe.getMessage());
        }
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public Integer getVoteCount() {
        return mVoteCount;
    }

    public Integer getId() {
        return mId;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public String getTitle() {
        return mTitle;
    }

    public Double getPopularity() {
        return mPopularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public String getOverview() {
        return mOverview;
    }
}
