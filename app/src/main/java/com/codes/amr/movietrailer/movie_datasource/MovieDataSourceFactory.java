package com.codes.amr.movietrailer.movie_datasource;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.codes.amr.movietrailer.data.model.Movie;
import com.codes.amr.movietrailer.data.rest.MoviesRepository;

import io.reactivex.disposables.CompositeDisposable;


public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieDataSourceClass> liveData;
    private MoviesRepository moviesRepository;
    private CompositeDisposable compositeDisposable;

    public MovieDataSourceFactory(MoviesRepository moviesRepository, CompositeDisposable compositeDisposable) {
        this.moviesRepository = moviesRepository;
        this.compositeDisposable = compositeDisposable;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<MovieDataSourceClass> getMutableLiveData() {
        return liveData;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        MovieDataSourceClass dataSourceClass = new MovieDataSourceClass(moviesRepository, compositeDisposable);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
