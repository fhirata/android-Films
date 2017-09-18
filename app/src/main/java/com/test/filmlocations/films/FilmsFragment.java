package com.test.filmlocations.films;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.filmlocations.R;
import com.test.filmlocations.data.models.FilmLocationItem;

import java.util.List;

/**
 * Displays film results in Results Fragment
 */

public class FilmsFragment extends Fragment implements FilmsContract.ViewInterface {
    FilmsContract.PresenterInterface mPresenterInterface;

    private RecyclerView mFilmsRecyclerView;
    private FilmsAdapter mFilmsAdapter;

    @Override
    public void onResume() {
        super.onResume();
        mPresenterInterface.start();
    }

    public static FilmsFragment getInstance() {
        return new FilmsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_film_results, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.films_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle(R.string.film_locations_title);

        setHasOptionsMenu(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mFilmsRecyclerView = (RecyclerView) view.findViewById(R.id.films_recyclerview);
        mFilmsRecyclerView.setLayoutManager(layoutManager);
        mFilmsAdapter = new FilmsAdapter(mPresenterInterface);
        mFilmsRecyclerView.setAdapter(mFilmsAdapter);

        return view;
    }

    @Override
    public void updateFilms(List<FilmLocationItem> itemList) {
        mFilmsAdapter.updateData(itemList);
    }

    @Override
    public void updateFilmItemPoster(int index, String posterPath) {
        mFilmsAdapter.updateFilmItemData(index, posterPath);
    }

    @Override
    public void setPresenter(FilmsContract.PresenterInterface presenter) {
        mPresenterInterface = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }


    /**
     * Adapter for film results recycler view
     */
    private static class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {
        private List<FilmLocationItem> mFilmLocationItems;
        FilmsContract.PresenterInterface mPresenterInterface;

        public FilmsAdapter(FilmsContract.PresenterInterface presenterInterface) {
            mPresenterInterface = presenterInterface;
        }

        void updateData(List<FilmLocationItem> itemList) {
            mFilmLocationItems = itemList;
            notifyDataSetChanged();
        }

        void updateFilmItemData(int index, String posterPath) {
            mFilmLocationItems.get(index).setPosterPath(posterPath);
            notifyItemChanged(index);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            FilmLocationItem filmItem = mFilmLocationItems.get(position);

            holder.mTitle.setText(filmItem.getTitle());
            holder.mDate.setText(filmItem.getReleaseYear());
            holder.mLocation.setText(filmItem.getLocations());
            holder.attachClickHandler(filmItem.getId(), filmItem.getTitle());

            if (null != mFilmLocationItems.get(position).getPosterPath()) {
                Glide.with(holder.itemView.getContext())
                        .load(mFilmLocationItems.get(position).getPosterPath())
                        .into(holder.mFilmImage);
            }
        }

        @Override
        public int getItemCount() {
            if (null != mFilmLocationItems) {
                return mFilmLocationItems.size();
            }

            return 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTitle;
            ImageView mFilmImage;
            TextView mLocation;
            TextView mDate;

            private ViewHolder(View itemView) {
                super(itemView);

                mFilmImage = (ImageView) itemView.findViewById(R.id.film_image);
                mTitle = (TextView) itemView.findViewById(R.id.title_textview);
                mDate = (TextView) itemView.findViewById(R.id.release_year_textview);
                mLocation = (TextView) itemView.findViewById(R.id.location_textview);
            }

            public void attachClickHandler(final Integer filmId, final String title) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenterInterface.handleClick(filmId, title);
                    }
                });

            }
        }
    }
}
