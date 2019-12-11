package com.codes.amr.movietrailer.ui.main;

import com.codes.amr.movietrailer.ui.movie_details.MovieDetailsFragment;
import com.codes.amr.movietrailer.ui.movielist.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract MovieListFragment provideMovieListFragment();

    @ContributesAndroidInjector
    abstract MovieDetailsFragment provideMovieDetailsFragment();
}
