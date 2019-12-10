package com.codes.amr.movietrailer.data.rest;

import com.codes.amr.movietrailer.data.model.MovieApiResponse;

import javax.inject.Inject;

import io.reactivex.Single;

public class MoviesRepository {

    private final MoviesApiService moviesApiService;

    @Inject
    public MoviesRepository(MoviesApiService moviesApiService) {
        this.moviesApiService = moviesApiService;
    }

    public Single<MovieApiResponse> getAllMovies(String sortBy, int page, String apiKey) {
        return moviesApiService.getAllMovies(sortBy, page, apiKey);
    }
}
