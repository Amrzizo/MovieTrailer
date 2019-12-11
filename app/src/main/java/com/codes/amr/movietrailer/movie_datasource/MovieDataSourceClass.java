package com.codes.amr.movietrailer.movie_datasource;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.codes.amr.movietrailer.data.model.Movie;
import com.codes.amr.movietrailer.data.model.MovieApiResponse;
import com.codes.amr.movietrailer.data.rest.MoviesApiService;
import com.codes.amr.movietrailer.data.rest.MoviesRepository;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MovieDataSourceClass extends PageKeyedDataSource<Integer, Movie> {

    private static String NOW_PLAYING = "now_playing";
    public static final int FIRST_PAGE = 1;

    public static final int PAGE_SIZE = 20;

    private MoviesRepository moviesRepository;
    private CompositeDisposable compositeDisposable;
    private final MutableLiveData<Boolean> movieLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    MovieDataSourceClass(MoviesRepository moviesRepository, CompositeDisposable compositeDisposable) {
        this.moviesRepository = moviesRepository;
        this.compositeDisposable = compositeDisposable;

    }


    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {


        moviesRepository.getAllMovies(NOW_PLAYING, FIRST_PAGE, MoviesApiService.API_KEY).subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    loading.postValue(true);
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieApiResponse>() {
            @Override
            public void onSuccess(MovieApiResponse value) {
                movieLoadError.setValue(false);
                loading.setValue(false);
//                        movieResponse.setValue(value);
                callback.onResult(value.getResults(), null, FIRST_PAGE + 1);

            }

            @Override
            public void onError(Throwable e) {
                movieLoadError.setValue(true);
                loading.setValue(false);
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {


        moviesRepository.getAllMovies(NOW_PLAYING, params.key, MoviesApiService.API_KEY).subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    loading.postValue(true);
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieApiResponse>() {
            @Override
            public void onSuccess(MovieApiResponse value) {
                movieLoadError.setValue(false);
//                movieResponse.setValue(value);
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                callback.onResult(value.getResults(), adjacentKey);
                loading.setValue(false);
            }

            @Override
            public void onError(Throwable e) {
                movieLoadError.setValue(true);
                loading.setValue(false);
            }
        });


    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {


        moviesRepository.getAllMovies(NOW_PLAYING, params.key, MoviesApiService.API_KEY).subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    loading.postValue(true);
                })
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<MovieApiResponse>() {
            @Override
            public void onSuccess(MovieApiResponse value) {
                movieLoadError.setValue(false);
                loading.setValue(false);
                Integer key = value.getResults().size() == PAGE_SIZE ? params.key + 1 : null;
                callback.onResult(value.getResults(), key);

            }

            @Override
            public void onError(Throwable e) {
                movieLoadError.setValue(true);
                loading.setValue(false);
            }
        });
    }

    public MutableLiveData<Boolean> getMovieLoadError() {
        return movieLoadError;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }
}
