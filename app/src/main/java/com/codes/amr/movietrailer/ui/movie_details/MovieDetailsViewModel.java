package com.codes.amr.movietrailer.ui.movie_details;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codes.amr.movietrailer.data.model.Movie;
import com.codes.amr.movietrailer.data.rest.MoviesRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailsViewModel extends ViewModel {

    private final MoviesRepository moviesRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<Movie> selectedMovie = new MutableLiveData<>();

    public LiveData<Movie> getClickedMovie() {
        return selectedMovie;
    }

    @Inject
    public MovieDetailsViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
        disposable = new CompositeDisposable();
    }

    public void setselectedMovie(Movie movie) {
        this.selectedMovie.setValue(movie);
    }

    public void saveToBundle(Bundle outState) {
        if (selectedMovie.getValue() != null) {
            outState.putStringArray("Movie_details", new String[]{
                    selectedMovie.getValue().getTitle(),
                    selectedMovie.getValue().getOverview()
            });
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if (selectedMovie.getValue() == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("Movie_details")) {

            }
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
