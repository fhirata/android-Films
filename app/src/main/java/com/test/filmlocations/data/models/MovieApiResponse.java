package com.test.filmlocations.data.models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * Parses a Movie json
 */

public class MovieApiResponse {

    Map mData;

    public List<MovieItem> makeMovieItems() {
        Gson gson = new Gson();
        String json = gson.toJson(mData);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        List<MovieItem> movieItemList = null;

        try {
            JsonArray movieResults = jsonObject.get("results").getAsJsonArray();
            for (int i=0; i< movieItemList.size(); i++) {
                MovieItem item = gson.fromJson(movieResults.get(i), MovieItem.class);
                movieItemList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieItemList;
    }
}
