package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.WatchProvidersResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes7.dex */
public interface WatchProvidersService {
    @GET("watch/providers/movie")
    Call<WatchProvidersResults> movie(@Query("language") String str, @Query("watch_region") String str2);

    @GET("watch/providers/tv")
    Call<WatchProvidersResults> tv(@Query("language") String str, @Query("watch_region") String str2);
}
