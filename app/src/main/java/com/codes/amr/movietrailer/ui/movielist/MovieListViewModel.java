package com.codes.amr.movietrailer.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.codes.amr.movietrailer.data.model.Movie;
import com.codes.amr.movietrailer.data.model.MovieApiResponse;
import com.codes.amr.movietrailer.data.rest.MoviesApiService;
import com.codes.amr.movietrailer.data.rest.MoviesRepository;
import com.codes.amr.movietrailer.movie_datasource.MovieDataSourceClass;
import com.codes.amr.movietrailer.movie_datasource.MovieDataSourceFactory;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieListViewModel extends ViewModel {


    private MovieDataSourceFactory movieDataSourceFactory;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public LiveData<PagedList<Movie>> moviePagedList;
    public MutableLiveData<Boolean> movieLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private LiveData<PageKeyedDataSource<Integer, Movie>> liveDataSource;
    private MoviesRepository moviesRepository;
    private CompositeDisposable disposable;
    private CompositeDisposable searchDisposable;

    public MovieListViewModel() {
    }


    private final MutableLiveData<MovieApiResponse> searchMovieResponse = new MutableLiveData<>();


    @Inject
    public MovieListViewModel(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
        disposable = new CompositeDisposable();
        searchDisposable= new CompositeDisposable();
        movieDataSourceFactory = new MovieDataSourceFactory(moviesRepository, compositeDisposable);
        initializePaging();
    }

    LiveData<MovieApiResponse> getSearchMovieResponse() {
        return searchMovieResponse;
    }

    LiveData<Boolean> getError() {
        return movieLoadError;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<PagedList<Movie>> getMoviePagedList() {
        return moviePagedList;
    }

    private void initializePaging() {

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(MovieDataSourceClass.PAGE_SIZE).build();

        moviePagedList = new LivePagedListBuilder<>(movieDataSourceFactory, pagedListConfig)
                .build();

        movieLoadError = (MutableLiveData<Boolean>) Transformations.switchMap(movieDataSourceFactory.getMutableLiveData(), MovieDataSourceClass::getMovieLoadError);
        loading = (MutableLiveData<Boolean>) Transformations.switchMap(movieDataSourceFactory.getMutableLiveData(), MovieDataSourceClass::getLoading);

    }


    public void searchForMovie(String query) {
        loading.setValue(true);
        searchDisposable.add(moviesRepository.searchForMovies(query, MoviesApiService.API_KEY).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieApiResponse>() {
                    @Override
                    public void onSuccess(MovieApiResponse value) {
                        movieLoadError.setValue(false);
                        searchMovieResponse.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }
}
