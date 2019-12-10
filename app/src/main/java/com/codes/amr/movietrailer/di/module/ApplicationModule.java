package com.codes.amr.movietrailer.di.module;

import com.codes.amr.movietrailer.data.rest.MoviesApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    public static final String BASE_URL = "https://developers.themoviedb.org";
    public static final String BASE_Service = "/3/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static MoviesApiService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(MoviesApiService.class);
    }
}
