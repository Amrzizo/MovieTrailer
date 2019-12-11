package com.codes.amr.movietrailer.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.codes.amr.movietrailer.di.utils.ViewModelKey;
import com.codes.amr.movietrailer.ui.movie_details.MovieDetailsViewModel;
import com.codes.amr.movietrailer.ui.movielist.MovieListViewModel;
import com.codes.amr.movietrailer.utils.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    abstract ViewModel bindListViewModel(MovieListViewModel carsListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel.class)
    abstract ViewModel bindMovieDetailsViewModel(MovieDetailsViewModel movieDetailsViewModel);



    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
