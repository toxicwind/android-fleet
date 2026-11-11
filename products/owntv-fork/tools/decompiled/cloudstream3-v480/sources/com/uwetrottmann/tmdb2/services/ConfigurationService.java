package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Configuration;
import com.uwetrottmann.tmdb2.entities.Jobs;
import com.uwetrottmann.tmdb2.entities.TmdbLanguage;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/* JADX INFO: loaded from: classes7.dex */
public interface ConfigurationService {
    @GET("configuration")
    Call<Configuration> configuration();

    @GET("configuration/jobs")
    Call<List<Jobs>> jobs();

    @GET("configuration/languages")
    Call<List<TmdbLanguage>> languages();

    @GET("configuration/primary_translations")
    Call<List<String>> primary_translations();
}
