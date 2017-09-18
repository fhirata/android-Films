package com.test.filmlocations;

/**
 * Base behavior of view contracts
 */

public interface BaseViewInterface<V extends BasePresenterInterface> {
    void setPresenter(V presenter);
}
