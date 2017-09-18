package com.test.filmlocations.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a film item
 */
public class FilmLocationItem {

    @SerializedName("actor_1")
    @Expose
    private String mActor1;
    @SerializedName(":updated_at")
    @Expose
    private Integer mUpdatedAt;
    @SerializedName("director")
    @Expose
    private String mDirector;
    @SerializedName(":created_meta")
    @Expose
    private String mCreatedMeta;
    @SerializedName("release_year")
    @Expose
    private String mReleaseYear;
    @SerializedName("actor_3")
    @Expose
    private String mActor3;
    @SerializedName("actor_2")
    @Expose
    private String mActor2;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("production_company")
    @Expose
    private String mProductionCompany;
    @SerializedName(":created_at")
    @Expose
    private Integer mCreatedAt;
    @SerializedName(":id")
    @Expose
    private Integer mId;
    @SerializedName("locations")
    @Expose
    private String mLocations;
    @SerializedName("writer")
    @Expose
    private String mWriter;
    @SerializedName(":updated_meta")
    @Expose
    private String mUpdatedMeta;

    /**
     * No args constructor for use in serialization
     */
    public FilmLocationItem() {
    }

    /**
     * @param release_year
     * @param _updated_meta
     * @param director
     * @param _updated_at
     * @param title
     * @param actor_1
     * @param _id
     * @param locations
     * @param production_company
     * @param _created_at
     * @param _created_meta
     * @param writer
     * @param actor_2
     * @param actor_3
     */
    public FilmLocationItem(String actor_1, Integer _updated_at, String director, String _created_meta, String release_year, String actor_3, String actor_2, String title, String production_company, Integer _created_at, Integer _id, String locations, String writer, String _updated_meta) {
        super();
        mActor1 = actor_1;
        mUpdatedAt = _updated_at;
        mDirector = director;
        mCreatedMeta = _created_meta;
        mReleaseYear = release_year;
        mActor3 = actor_3;
        mActor2 = actor_2;
        mTitle = title;
        mProductionCompany = production_company;
        mCreatedAt = _created_at;
        mId = _id;
        mLocations = locations;
        mWriter = writer;
        mUpdatedMeta = _updated_meta;
    }

    public String getActor1() {
        return mActor1;
    }

    public Integer getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getDirector() {
        return mDirector;
    }

    public String getCreatedMeta() {
        return mCreatedMeta;
    }

    public String getReleaseYear() {
        return mReleaseYear;
    }

    public String getActor3() {
        return mActor3;
    }

    public String getActor2() {
        return mActor2;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getProductionCompany() {
        return mProductionCompany;
    }
    public Integer getCreatedAt() {
        return mCreatedAt;
    }

    public Integer getId() {
        return mId;
    }

    public String getLocations() {
        return mLocations;
    }

    public String getWriter() {
        return mWriter;
    }

    public String getUpdatedMeta() {
        return mUpdatedMeta;
    }
}
