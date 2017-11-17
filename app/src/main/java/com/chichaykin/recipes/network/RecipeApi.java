package com.chichaykin.recipes.network;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {
    @GET("/search")
    Observable<SearchResponse> search(@Query("q") String query, @Query("page") int page);
}
