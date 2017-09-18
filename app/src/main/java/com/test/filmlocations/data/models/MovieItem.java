package com.test.filmlocations.data.models;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Models information about a Movie that complements the item in the list and detail screen.
 */

public class MovieItem {

    private static String TAG = MovieItem.class.getSimpleName();

    private String mName;
    private String mImgUrl;
    private String mDescription;
    private int mVoteCount;
    private double mVoteAverage;
    private Date mReleaseDate;
    private int mDuration;
    private String mBackDropUrl;
    private long mMovieId;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    static SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");


    public MovieItem() {
        Log.d(TAG, "MovieItem() called");
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getReleaseDate() {
        if (mReleaseDate != null) {
            return dateFormat.format(mReleaseDate);
        }
        return "(Unknown)";
    }

    public String getReleaseYear() {
        if (mReleaseDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mReleaseDate);
            return String.valueOf(calendar.get(Calendar.YEAR));
        }
        return "(Unknown)";
    }


    public void setReleaseDate(String releaseDate) {
        try {

            mReleaseDate = dateFormat.parse(releaseDate);
        } catch (ParseException dpe) {
            Log.e(TAG, "Failed to parse full date: " + dpe.getMessage());
        }
    }

    public String getBackDropUrl() {
        return mBackDropUrl;
    }

    public void setBackDropUrl(String mBackDrop) {
        mBackDropUrl = mBackDrop;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        mImgUrl = imgUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getVoteCount() {
        return String.valueOf(mVoteCount);
    }

    public void setVoteCount(String voteCount) {
        mVoteCount = Integer.valueOf(voteCount);
    }

    public String getAverageVote() {
        return String.valueOf(mVoteAverage);
    }

    public void setAverageVote(String averageVote) {
        mVoteAverage = Double.valueOf(averageVote);
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int mDuration) {
        mDuration = mDuration;
    }

    public long getMovieId() {
        return mMovieId;
    }

    public void setMovieId(long movieId) {
        Log.d(TAG, "setMovieId() called with: movieId = [" + movieId + "]");
        mMovieId = movieId;
    }
}
