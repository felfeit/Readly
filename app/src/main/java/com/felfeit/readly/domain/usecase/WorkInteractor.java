package com.felfeit.readly.domain.usecase;


import com.felfeit.readly.domain.model.Work;
import com.felfeit.readly.domain.repository.WorkRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class WorkInteractor implements WorkUseCase {
    private final WorkRepository repository;

    @Inject
    WorkInteractor(WorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<List<Work>> searchWorks(String query) {
        return this.repository.searchWorks(query);
    }
}
