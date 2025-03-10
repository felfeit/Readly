package com.felfeit.readly.domain.usecase;

import com.felfeit.readly.domain.model.Work;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface WorkUseCase {
    Single<List<Work>> searchWorks(String query);
}