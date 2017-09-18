package com.test.filmlocations.data.models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Films response payload containing list of FilmLocationItem objects
 */

public class FilmApiResponse {

    Map mData;

    public List<FilmLocationItem> makeFilmItems() {
        Gson gson = new Gson();
        String json = gson.toJson(mData);

        List<FilmLocationItem> filmLocationItemList = null;

        try {

            Type collectionType = new TypeToken<Collection<FilmLocationItem>>(){}.getType();
            Collection<FilmLocationItem> filmItemArray = gson.fromJson(json, collectionType);

            filmLocationItemList = new ArrayList<>();

            for (FilmLocationItem filmItem : filmItemArray) {
                filmLocationItemList.add(filmItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filmLocationItemList;
    }
}
