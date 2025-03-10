package com.felfeit.readly.data.source.remote.api;


import com.felfeit.readly.data.source.remote.response.search.SearchBooksResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/search.json")
    Single<SearchBooksResponse> searchWorks(@Query("q") String title, @Query("fields") String fields);
}
