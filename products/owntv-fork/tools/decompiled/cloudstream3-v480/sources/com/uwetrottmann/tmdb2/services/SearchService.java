package com.uwetrottmann.tmdb2.services;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.actions.SearchIntents;
import com.uwetrottmann.tmdb2.entities.CollectionResultsPage;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.entities.KeywordResultsPage;
import com.uwetrottmann.tmdb2.entities.MediaResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.PersonResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/* JADX INFO: loaded from: classes7.dex */
public interface SearchService {
    @GET("search/collection")
    Call<CollectionResultsPage> collection(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num, @Query("language") String str2);

    @GET("search/company")
    Call<CompanyResultsPage> company(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num);

    @GET("search/keyword")
    Call<KeywordResultsPage> keyword(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num);

    @GET("search/movie")
    Call<MovieResultsPage> movie(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num, @Query("language") String str2, @Query(TtmlNode.TAG_REGION) String str3, @Query("include_adult") Boolean bool, @Query("year") Integer num2, @Query("primary_release_year") Integer num3);

    @GET("search/multi")
    Call<MediaResultsPage> multi(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num, @Query("language") String str2, @Query(TtmlNode.TAG_REGION) String str3, @Query("include_adult") Boolean bool);

    @GET("search/person")
    Call<PersonResultsPage> person(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num, @Query("language") String str2, @Query(TtmlNode.TAG_REGION) String str3, @Query("include_adult") Boolean bool);

    @GET("search/tv")
    Call<TvShowResultsPage> tv(@Query(SearchIntents.EXTRA_QUERY) String str, @Query("page") Integer num, @Query("language") String str2, @Query("first_air_date_year") Integer num2, @Query("include_adult") Boolean bool);
}
