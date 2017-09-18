package com.test.filmlocations.data.source.errors;

/**
 * Specifies the behavior for the Films error classes
 */

public interface FilmsDataSourceErrorInterface {
    int getErrorCode();

    String getErrorMessage();
}
