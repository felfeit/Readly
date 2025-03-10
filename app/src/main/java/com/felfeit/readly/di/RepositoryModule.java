package com.felfeit.readly.di;

import com.felfeit.readly.data.repository.WorkRepositoryImpl;
import com.felfeit.readly.domain.repository.WorkRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoryModule {

    @Binds
    public abstract WorkRepository bindWorkRepository(WorkRepositoryImpl repositoryImpl);
}