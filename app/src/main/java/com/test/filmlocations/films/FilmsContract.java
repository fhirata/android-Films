package com.test.filmlocations.films;

import com.test.filmlocations.BasePresenterInterface;
import com.test.filmlocations.BaseViewInterface;
import com.test.filmlocations.data.models.FilmLocationItem;

import java.util.List;

/**
 * Contract for Films
 */

public interface FilmsContract {

    interface PresenterInterface extends BasePresenterInterface {

    }

    interface ViewInterface extends BaseViewInterface<PresenterInterface> {
        void updateFilms(List<FilmLocationItem> itemList);
        void updateFilmItemPoster(int index, String posterPath);
    }

    interface ActivityInterface {
        void showErrorMessage(int messageResourceId);
    }
}
