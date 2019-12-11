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
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }


}
