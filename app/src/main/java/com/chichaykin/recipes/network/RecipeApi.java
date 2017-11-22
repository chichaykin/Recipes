package com.chichaykin.recipes.network;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {

    @GET("/api/search")
    Observable<SearchResponse> search(@Query("q") String query);

    @GET("/api/get")
    Observable<GetResponse> get(@Query("rId") String id);
}
