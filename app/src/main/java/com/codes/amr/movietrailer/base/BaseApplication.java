package com.codes.amr.movietrailer.base;

import com.codes.amr.movietrailer.di.component.ApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = null;
//        = DaggerApplicationComponent.builder().application(this).build();
//        component.inject(this);

        return component;
    }
}
