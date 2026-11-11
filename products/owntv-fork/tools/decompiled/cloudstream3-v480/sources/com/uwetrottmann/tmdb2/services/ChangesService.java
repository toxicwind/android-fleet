package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.ChangeResultsPage;
import com.uwetrottmann.tmdb2.entities.TmdbDate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes7.dex */
public interface ChangesService {
    @GET("movie/changes")
    Call<ChangeResultsPage> movie(@Query("start_date") TmdbDate tmdbDate, @Query("end_date") TmdbDate tmdbDate2);

    @GET("person/changes")
    Call<ChangeResultsPage> person(@Query("start_date") TmdbDate tmdbDate, @Query("end_date") TmdbDate tmdbDate2);

    @GET("tv/changes")
    Call<ChangeResultsPage> tv(@Query("start_date") TmdbDate tmdbDate, @Query("end_date") TmdbDate tmdbDate2);
}
