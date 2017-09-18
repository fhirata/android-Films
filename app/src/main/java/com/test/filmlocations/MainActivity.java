package com.test.filmlocations;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.test.filmlocations.films.FilmsContract;
import com.test.filmlocations.films.FilmsFragment;
import com.test.filmlocations.films.FilmsPresenter;

public class MainActivity extends AppCompatActivity implements FilmsContract.ActivityInterface {

    private CoordinatorLayout mSnackbarLayout;

    @Override
    public void showErrorMessage(int messageResourceId) {
        Snackbar snackbar = Snackbar.make(mSnackbarLayout, messageResourceId, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed));
        snackbar.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_results);

        FilmsFragment filmsFragment = FilmsFragment.getInstance();
        new FilmsPresenter(filmsFragment, this, Injection.provideFilmsRepository());

        mSnackbarLayout = (CoordinatorLayout) findViewById(R.id.snackbar_layout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.filmresult_fragment_container, filmsFragment);
        fragmentTransaction.commit();
    }

}
