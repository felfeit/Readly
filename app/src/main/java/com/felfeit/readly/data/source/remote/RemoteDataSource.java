package com.felfeit.readly.data.source.remote;

import com.felfeit.readly.data.source.remote.api.ApiService;
import com.felfeit.readly.data.source.remote.response.search.SearchBooksResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;

@Singleton
public class RemoteDataSource {
    private final ApiService apiService;

    @Inject
    RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    // Search books (Tanpa Paging, digunakan jika butuh fetch manual)
    public Single<SearchBooksResponse> searchWorks(String query) {
        return apiService.searchWorks(query, "title,author_name,key,cover_edition_key,first_publish_year,edition_count");
    }
}
