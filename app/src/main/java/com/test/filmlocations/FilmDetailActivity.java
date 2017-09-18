package com.test.filmlocations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.test.filmlocations.filmdetail.FilmDetailContract;
import com.test.filmlocations.filmdetail.FilmDetailFragment;
import com.test.filmlocations.filmdetail.FilmDetailPresenter;

/**
 * Container for Film Detail Fragment
 */

public class FilmDetailActivity extends AppCompatActivity implements FilmDetailContract.ActivityInterface {
    CoordinatorLayout mSnackbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_film_detail);

        Integer filmId = getIntent().getIntExtra("film_id", -1);
        String filmName = getIntent().getStringExtra("film_name");

        FilmDetailFragment filmDetailFragment = FilmDetailFragment.getInstance();

        new FilmDetailPresenter(
                filmDetailFragment,
                this,
                Injection.provideFilmsRepository(),
                Injection.provideMoviesRepository(),
                getString(R.string.movies_api_key),
                getString(R.string.language),
                filmId,
                filmName
        );

        mSnackbarLayout = (CoordinatorLayout) findViewById(R.id.snackbar_layout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.filmdetail_fragment_container, filmDetailFragment);
        fragmentTransaction.commit();
    }


    // Duplicated for now, can be consolidated with a common FilmsBaseActivity
    @Override
    public void showErrorMessage(int messageResId) {
        Snackbar snackbar = Snackbar.make(mSnackbarLayout, messageResId, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
        snackbar.show();
    }
}
