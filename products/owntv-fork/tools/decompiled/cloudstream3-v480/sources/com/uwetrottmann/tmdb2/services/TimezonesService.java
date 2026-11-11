package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Timezones;
import retrofit2.Call;
import retrofit2.http.GET;

/* JADX INFO: loaded from: classes7.dex */
public interface TimezonesService {
    @GET("timezones/list")
    Call<Timezones> timezones();
}
