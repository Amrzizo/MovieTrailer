package com.codes.amr.movietrailer.di.module;

import com.codes.amr.movietrailer.data.rest.MoviesApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {



        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Singleton
    @Provides
    static MoviesApiService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(MoviesApiService.class);
    }
}
