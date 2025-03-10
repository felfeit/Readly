package com.felfeit.readly.di;

import com.felfeit.readly.domain.usecase.WorkInteractor;
import com.felfeit.readly.domain.usecase.WorkUseCase;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Binds
    @Singleton
    public abstract WorkUseCase provideUseCase(WorkInteractor interactor);
}
