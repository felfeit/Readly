package com.felfeit.readly.domain.repository;

import com.felfeit.readly.domain.model.Work;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface WorkRepository {
    Single<List<Work>> searchWorks(String query);
}
