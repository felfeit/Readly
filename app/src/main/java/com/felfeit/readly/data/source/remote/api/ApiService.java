package com.felfeit.readly.data.source.remote.api;


import com.felfeit.readly.data.source.remote.response.editions.WorkEditionsResponse;
import com.felfeit.readly.data.source.remote.response.ratings.WorkRatingsResponse;
import com.felfeit.readly.data.source.remote.response.search.SearchBooksResponse;
import com.felfeit.readly.data.source.remote.response.works.WorkDetailsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/search.json")
    Single<SearchBooksResponse> searchWorks(@Query("q") String title, @Query("fields") String fields);

    @GET("{olid}.json")
    Single<WorkDetailsResponse> getWorkDetails(@Path("olid") String olid);

    @GET("{olid}/editions.json")
    Single<WorkEditionsResponse> getWorkEditions(@Path("olid") String olid);

    @GET("{olid}/ratings.json")
    Single<WorkRatingsResponse> getWorkRatings(@Path("olid") String olid);
}
