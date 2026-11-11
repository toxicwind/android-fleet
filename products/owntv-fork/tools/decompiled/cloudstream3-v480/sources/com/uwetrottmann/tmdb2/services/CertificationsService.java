package com.uwetrottmann.tmdb2.services;

import com.uwetrottmann.tmdb2.entities.Certifications;
import retrofit2.Call;
import retrofit2.http.GET;

/* JADX INFO: loaded from: classes7.dex */
public interface CertificationsService {
    @GET("certification/movie/list")
    Call<Certifications> movie();

    @GET("certification/tv/list")
    Call<Certifications> tv();
}
