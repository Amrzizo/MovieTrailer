package com.codes.amr.movietrailer.di.module;

import com.codes.amr.movietrailer.ui.main.MainActivity;
import com.codes.amr.movietrailer.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
