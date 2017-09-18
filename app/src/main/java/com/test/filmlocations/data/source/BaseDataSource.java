package com.test.filmlocations.data.source;

/**
 * Base DataSource configurations and default values
 */

public interface BaseDataSource {
    int READ_TIMEOUT = 10;
    int WRITE_TIMEOUT = 10;
    int CONNECT_TIMEOUT = 10;
}
