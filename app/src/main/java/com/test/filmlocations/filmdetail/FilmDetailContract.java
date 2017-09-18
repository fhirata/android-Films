package com.test.filmlocations.filmdetail;

import com.test.filmlocations.BasePresenterInterface;
import com.test.filmlocations.BaseViewInterface;

import java.util.List;

/**
 * Contract for Film Detail
 */

public interface FilmDetailContract {
    interface ViewInterface extends BaseViewInterface<FilmDetailContract.PresenterInterface> {
        void setMapAddressLocation(String locations);
        void setActors(List<String> actors);
        void setDirector(String director);
        void setPoster(String posterPath);
        void setDescription(String overview);
        void setMovieTitle(String title);
        void setFunFacts(String funFacts);
    }

    interface PresenterInterface extends BasePresenterInterface {

    }

    interface ActivityInterface {
        void showErrorMessage(int messageResId);
    }
}
