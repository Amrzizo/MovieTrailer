package com.codes.amr.movietrailer.data.rest;

import com.codes.amr.movietrailer.data.model.MovieApiResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApiService {

    String API_KEY ="a1d797c3cd4df492cf679f280ec434d7";

    @GET("/movie/{filterBy}")
    Single<MovieApiResponse>getAllMovies(@Path("filterBy") String sortBy, @Query("page") int page, @Query("api_key") String apiKey);

    @GET("search/movie")
    Single<MovieApiResponse> searchForMovies(@Query("query") String query , @Query("api_key") String apiKey);

}
