package com.codes.amr.movietrailer.di.component;

import android.app.Application;

import com.codes.amr.movietrailer.base.BaseApplication;
import com.codes.amr.movietrailer.di.module.ActivityBindingModule;
import com.codes.amr.movietrailer.di.module.ApplicationModule;
import com.codes.amr.movietrailer.di.module.ContextModule;
import com.codes.amr.movietrailer.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

@Singleton
@Component(modules = {ActivityBindingModule.class, ApplicationModule.class, ContextModule.class, ViewModelModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {


    public void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }


}
