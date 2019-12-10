package com.codes.amr.movietrailer.di.module;

import com.codes.amr.movietrailer.ui.MainActivity;

import dagger.Module;

@Module
public abstract class ActivityBindingModule {

    abstract MainActivity bindMainActivity();
}
