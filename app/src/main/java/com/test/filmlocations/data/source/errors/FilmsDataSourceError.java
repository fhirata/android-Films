package com.test.filmlocations.data.source.errors;

/**
 * Films Data Source Error definitions
 */

public class FilmsDataSourceError implements FilmsDataSourceErrorInterface {

    // Parsing or issue retrieving data from Retrofit
    public final static int RETROFIT_ERROR = 1000;

    private int mErrorCode;

    private String mMessage;

    public FilmsDataSourceError(int error, String message) {
        mErrorCode = error;
        mMessage = message;
    }

    @Override
    public int getErrorCode() {
        return mErrorCode;
    }

    @Override
    public String getErrorMessage() {
        return mMessage;
    }
}
