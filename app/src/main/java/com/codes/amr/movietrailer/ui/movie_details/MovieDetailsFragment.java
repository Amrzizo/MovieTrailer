package com.codes.amr.movietrailer.ui.movie_details;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.codes.amr.movietrailer.R;
import com.codes.amr.movietrailer.base.BaseFragment;
import com.codes.amr.movietrailer.ui.main.MainActivity;
import com.codes.amr.movietrailer.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;

public class MovieDetailsFragment extends BaseFragment {

    @BindView(R.id.backdropImage)
    ImageView backdropImage;
    @BindView(R.id.titleOfMovie)
    TextView titleOfMovie;
    @BindView(R.id.ratingOfMovie)
    TextView ratingOfMovie;
    @BindView(R.id.descriptionOfMovie)
    TextView descriptionOfMovie;


    @Inject
    ViewModelFactory viewModelFactory;
    private MovieDetailsViewModel movieDetailsViewModel;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_movie_detials;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ((MainActivity)getActivity()).showSearchView(false);

        movieDetailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(MovieDetailsViewModel.class);
        movieDetailsViewModel.restoreFromBundle(savedInstanceState);
        viewMovie();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (movieDetailsViewModel != null)
            movieDetailsViewModel.saveToBundle(outState);
    }

    private void viewMovie() {
        movieDetailsViewModel.getClickedMovie().observe(this, movie -> {
            if (movie != null) {

                Glide.with(getBaseActivity()).load(movie.getPoster_path()).into(backdropImage);
                titleOfMovie.setText(movie.getTitle());
                ratingOfMovie.setText(String.valueOf(movie.getVote_average()));
                descriptionOfMovie.setText(movie.getOverview());



            }
        });


    }


}
