package com.test.filmlocations.filmdetail;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.filmlocations.R;

import java.io.IOException;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * Displays movie detail (if available) and map information of filming location
 */

public class FilmDetailFragment extends Fragment implements FilmDetailContract.ViewInterface, OnMapReadyCallback {
    private static final String TAG = FilmDetailFragment.class.getSimpleName();

    private FilmDetailContract.PresenterInterface mPresenterInterface;
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private int DEFAULT_ZOOM = 10;
    private double mLat;
    private double mLong;
    private TextView mMovieTitle;
    private ImageView mPosterImageView;

    private TextView mDirectorTextView;
    private TextView mDirectorLabel;

    private TextView mActorsTextView;
    private TextView mActorsLabel;

    private TextView mFunFact;
    private TextView mFunFactLabel;

    private TextView mDescription;
    private TextView getmDescriptionLabel;

    private TextView mLocations;
    private TextView mLocationsLabel;

    public static FilmDetailFragment getInstance() {
        return new FilmDetailFragment();
    }

    @Override
    public void setPresenter(FilmDetailContract.PresenterInterface presenterInterface) {
        mPresenterInterface = presenterInterface;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterInterface.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_detail, container, false);

        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        mMovieTitle = (TextView) view.findViewById(R.id.movie_title_textview);
        mPosterImageView = (ImageView) view.findViewById(R.id.poster_imageview);

        mDirectorTextView = (TextView) view.findViewById(R.id.director_textview);
        mDirectorLabel = (TextView) view.findViewById(R.id.director_label_textview);

        mActorsTextView = (TextView) view.findViewById(R.id.actors_textview);
        mActorsLabel = (TextView) view.findViewById(R.id.actors_label_textview);

        mFunFact = (TextView) view.findViewById(R.id.trivia_textview);
        mFunFactLabel = (TextView) view.findViewById(R.id.trivia_label_textview);

        mLocations = (TextView) view.findViewById(R.id.location_textview);
        mLocationsLabel = (TextView) view.findViewById(R.id.location_label_textview);

        mDescription = (TextView) view.findViewById(R.id.description_textview);

        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        updateMap();
    }

    private void updateMap() {
        LatLng latLong = new LatLng(mLat, mLong);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, DEFAULT_ZOOM));
        mMap.addMarker(new MarkerOptions().position(latLong).title(getString(R.string.film_locations_title)));
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    @Override
    public void setMapAddressLocation(@NonNull String locations) {
        StringBuffer cityState = new StringBuffer();
        cityState.append(locations)
                .append(", ")
                .append(getString(R.string.san_francisco_ca));

        Geocoder geocoder = new Geocoder(getContext());
        List<Address> address;
        try {
            address = geocoder.getFromLocationName(locations, 3);
            if (null == address || address.isEmpty()) {
                return;
            }
            Address location = address.get(0);
            mLat = location.getLatitude();
            mLong = location.getLongitude();

        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        mLocations.setText(cityState);
        mLocationsLabel.setVisibility(VISIBLE);
    }

    // Normally, I would add into a listview or recyclerview, just not enough time right now...
    @Override
    public void setActors(List<String> actors) {
        mActorsLabel.setVisibility(VISIBLE);
        mActorsTextView.setText(TextUtils.join("\n", actors));
    }

    @Override
    public void setDirector(String director) {
        mDirectorTextView.setText(director);
        mDirectorLabel.setVisibility(VISIBLE);
    }

    @Override
    public void setPoster(String posterPath) {
        Glide.with(getContext())
                .load(posterPath)
                .into(mPosterImageView);
    }

    @Override
    public void setDescription(String overview) {
        mDescription.setText(overview);
    }

    @Override
    public void setMovieTitle(String title) {
        mMovieTitle.setText(title);
    }

    @Override
    public void setFunFacts(String funFacts) {
        mFunFact.setText(funFacts);
        mFunFactLabel.setVisibility(VISIBLE);
    }
}
