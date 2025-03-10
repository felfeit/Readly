package com.felfeit.readly.data.repository;

import com.felfeit.readly.data.source.remote.RemoteDataSource;
import com.felfeit.readly.domain.mapper.WorkMapper;
import com.felfeit.readly.domain.model.Work;
import com.felfeit.readly.domain.repository.WorkRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public class WorkRepositoryImpl implements WorkRepository {
    private final RemoteDataSource remoteDataSource;

    @Inject
    WorkRepositoryImpl(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Single<List<Work>> searchWorks(String query) {
        return remoteDataSource.searchWorks(query).map(response ->
                WorkMapper.mapWorkResponseListToWorkDomainList(response.getDocs())
        );
    }
}
