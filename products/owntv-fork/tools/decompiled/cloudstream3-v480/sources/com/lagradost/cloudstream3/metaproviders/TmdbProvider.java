package com.lagradost.cloudstream3.metaproviders;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.Actor;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.HomePageList;
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageRequest;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.ProviderType;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SearchResponseList;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.CastMember;
import com.uwetrottmann.tmdb2.entities.ContentRating;
import com.uwetrottmann.tmdb2.entities.ContentRatings;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.MediaResultsPage;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.entities.ReleaseDate;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResult;
import com.uwetrottmann.tmdb2.entities.ReleaseDatesResults;
import com.uwetrottmann.tmdb2.entities.TvEpisode;
import com.uwetrottmann.tmdb2.entities.TvSeason;
import com.uwetrottmann.tmdb2.entities.TvShow;
import com.uwetrottmann.tmdb2.entities.TvShowResultsPage;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.enumerations.VideoType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.mozilla.classfile.ByteCode;
import org.mozilla.universalchardet.prober.HebrewProber;
import retrofit2.Call;
import retrofit2.KotlinExtensions;
import retrofit2.Response;

/* JADX INFO: compiled from: TmdbProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000bH\u0002J\u001f\u0010\u001a\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\u001eJ\f\u0010\u001f\u001a\u00020 *\u00020!H\u0002J\f\u0010\u001f\u001a\u00020\"*\u00020#H\u0002J,\u0010$\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020'\u0012\u0006\u0012\u0004\u0018\u00010\u000b0&\u0018\u00010%*\f\u0012\u0006\u0012\u0004\u0018\u00010(\u0018\u00010%H\u0002J\u0012\u0010)\u001a\u00020**\u00020+H\u0082@¢\u0006\u0002\u0010,J\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010%*\u0004\u0018\u00010.H\u0002J\u0012\u0010)\u001a\u00020/*\u000200H\u0082@¢\u0006\u0002\u00101J\u001e\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u001c2\u0006\u00105\u001a\u000206H\u0096@¢\u0006\u0002\u00107J \u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020\u000b2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0%H\u0016J \u0010=\u001a\u0004\u0018\u0001092\u0006\u0010\u0016\u001a\u00020\u001c2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0%H\u0016J\u0012\u00108\u001a\u0004\u0018\u0001092\u0006\u0010:\u001a\u00020\u000bH\u0016J\u0012\u0010=\u001a\u0004\u0018\u0001092\u0006\u0010\u0016\u001a\u00020\u001cH\u0016J\"\u0010>\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010?\u001a\u00020\u000bH\u0096@¢\u0006\u0002\u0010@J\u0018\u0010A\u001a\u0004\u0018\u0001092\u0006\u0010B\u001a\u00020\u000bH\u0096@¢\u0006\u0002\u0010CJ \u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010F\u001a\u00020\u000b2\u0006\u00104\u001a\u00020\u001cH\u0096@¢\u0006\u0002\u0010GR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TmdbProvider;", "Lcom/lagradost/cloudstream3/MainAPI;", "<init>", "()V", "includeAdult", "", "getIncludeAdult", "()Z", "useMetaLoadResponse", "getUseMetaLoadResponse", "apiName", "", "getApiName", "()Ljava/lang/String;", "disableSeasonZero", "getDisableSeasonZero", "hasMainPage", "getHasMainPage", "providerType", "Lcom/lagradost/cloudstream3/ProviderType;", "getProviderType", "()Lcom/lagradost/cloudstream3/ProviderType;", "tmdb", "Lcom/uwetrottmann/tmdb2/Tmdb;", "getImageUrl", "link", "getUrl", "id", "", "tvShow", "(Ljava/lang/Integer;Z)Ljava/lang/String;", "toSearchResponse", "Lcom/lagradost/cloudstream3/TvSeriesSearchResponse;", "Lcom/uwetrottmann/tmdb2/entities/BaseTvShow;", "Lcom/lagradost/cloudstream3/MovieSearchResponse;", "Lcom/uwetrottmann/tmdb2/entities/BaseMovie;", "toActors", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/Actor;", "Lcom/uwetrottmann/tmdb2/entities/CastMember;", "toLoadResponse", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;", "Lcom/uwetrottmann/tmdb2/entities/TvShow;", "(Lcom/uwetrottmann/tmdb2/entities/TvShow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toTrailers", "Lcom/uwetrottmann/tmdb2/entities/Videos;", "Lcom/lagradost/cloudstream3/MovieLoadResponse;", "Lcom/uwetrottmann/tmdb2/entities/Movie;", "(Lcom/uwetrottmann/tmdb2/entities/Movie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMainPage", "Lcom/lagradost/cloudstream3/HomePageResponse;", "page", "request", "Lcom/lagradost/cloudstream3/MainPageRequest;", "(ILcom/lagradost/cloudstream3/MainPageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadFromImdb", "Lcom/lagradost/cloudstream3/LoadResponse;", "imdb", "seasons", "Lcom/uwetrottmann/tmdb2/entities/TvSeason;", "loadFromTmdb", "fetchContentRating", "country", "(Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "Lcom/lagradost/cloudstream3/SearchResponseList;", SearchIntents.EXTRA_QUERY, "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class TmdbProvider extends MainAPI {
    private final boolean includeAdult;
    private final boolean useMetaLoadResponse;
    private final String apiName = "TMDB";
    private final boolean disableSeasonZero = true;
    private final boolean hasMainPage = true;
    private final ProviderType providerType = ProviderType.MetaProvider;
    private final Tmdb tmdb = new Tmdb("e6333b32409e02a4a6eba6fb7ff866bb");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$fetchContentRating$1, reason: invalid class name */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider", f = "TmdbProvider.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {310, 316}, m = "fetchContentRating$suspendImpl", n = {"$this", "id", "country", "$this", "id", "country", "contentRatings"}, nl = {311, TypedValues.AttributesType.TYPE_EASING}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TmdbProvider.fetchContentRating$suspendImpl(TmdbProvider.this, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider", f = "TmdbProvider.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {255}, m = "getMainPage$suspendImpl", n = {"$this", "request", "discoverMovies", "discoverSeries", "topMovies", "topSeries", "page"}, nl = {283}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, v = 2)
    static final class C07321 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C07321(Continuation<? super C07321> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TmdbProvider.getMainPage$suspendImpl(TmdbProvider.this, 0, (MainPageRequest) null, (Continuation<? super HomePageResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider", f = "TmdbProvider.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10}, l = {350, 351, 354, 362, 378, 379, 382, 390, 399, TypedValues.CycleType.TYPE_VISIBILITY, 410}, m = "load$suspendImpl", n = {"$this", ImagesContract.URL, "idRegex", "found", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", TtmlNode.TAG_BODY, "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", TtmlNode.TAG_BODY, "response", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", TtmlNode.TAG_BODY, "response", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", TtmlNode.TAG_BODY, "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", TtmlNode.TAG_BODY, "response", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", TtmlNode.TAG_BODY, "response", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", "isTvSeries", "id", "$this", ImagesContract.URL, "idRegex", "found", "it", "fromImdb", "isTvSeries", "id", "$i$a$-let-TmdbProvider$load$9", "$this", ImagesContract.URL, "idRegex", "found", "isTvSeries", "id"}, nl = {341, 352, 355, 363, 369, 380, 383, 391, 400, TypedValues.CycleType.TYPE_ALPHA, MediaError.DetailedErrorCode.HLS_MANIFEST_MASTER}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0"}, v = 2)
    static final class C07331 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07331(Continuation<? super C07331> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TmdbProvider.load$suspendImpl(TmdbProvider.this, (String) null, (Continuation<? super LoadResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider", f = "TmdbProvider.kt", i = {0, 0, 0}, l = {417}, m = "search$suspendImpl", n = {"$this", SearchIntents.EXTRA_QUERY, "page"}, nl = {418}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07341 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07341(Continuation<? super C07341> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TmdbProvider.search$suspendImpl(TmdbProvider.this, (String) null, 0, (Continuation<? super SearchResponseList>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$toLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider", f = "TmdbProvider.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {145, 170}, m = "toLoadResponse", n = {"$this$toLoadResponse", "tvSeasonsService", "episodes", "validSeasons", "season", "seasonNumber", "$this$toLoadResponse", "tvSeasonsService", "episodes", "validSeasons"}, nl = {143, -1}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C07351 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C07351(Continuation<? super C07351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TmdbProvider.this.toLoadResponse((TvShow) null, this);
        }
    }

    public Object fetchContentRating(Integer num, String str, Continuation<? super String> continuation) {
        return fetchContentRating$suspendImpl(this, num, str, continuation);
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Object getMainPage(int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) {
        return getMainPage$suspendImpl(this, i, mainPageRequest, continuation);
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Object load(String str, Continuation<? super LoadResponse> continuation) {
        return load$suspendImpl(this, str, continuation);
    }

    public LoadResponse loadFromImdb(String imdb) {
        Intrinsics.checkNotNullParameter(imdb, "imdb");
        return null;
    }

    public LoadResponse loadFromImdb(String imdb, List<? extends TvSeason> seasons) {
        Intrinsics.checkNotNullParameter(imdb, "imdb");
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        return null;
    }

    public LoadResponse loadFromTmdb(int tmdb) {
        return null;
    }

    public LoadResponse loadFromTmdb(int tmdb, List<? extends TvSeason> seasons) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        return null;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Object search(String str, int i, Continuation<? super SearchResponseList> continuation) {
        return search$suspendImpl(this, str, i, continuation);
    }

    public boolean getIncludeAdult() {
        return this.includeAdult;
    }

    public boolean getUseMetaLoadResponse() {
        return this.useMetaLoadResponse;
    }

    public String getApiName() {
        return this.apiName;
    }

    public boolean getDisableSeasonZero() {
        return this.disableSeasonZero;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public boolean getHasMainPage() {
        return this.hasMainPage;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public ProviderType getProviderType() {
        return this.providerType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getImageUrl(String link) {
        if (link == null) {
            return null;
        }
        if (!StringsKt.startsWith$default(link, "/", false, 2, (Object) null)) {
            return link;
        }
        return "https://image.tmdb.org/t/p/w500/" + link;
    }

    private final String getUrl(Integer id, boolean tvShow) {
        if (tvShow) {
            StringBuilder sb = new StringBuilder("https://www.themoviedb.org/tv/");
            sb.append(id != null ? id.intValue() : -1);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder("https://www.themoviedb.org/movie/");
        sb2.append(id != null ? id.intValue() : -1);
        return sb2.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TvSeriesSearchResponse toSearchResponse(final BaseTvShow baseTvShow) {
        TmdbProvider tmdbProvider = this;
        String str = baseTvShow.name;
        if (str == null) {
            str = baseTvShow.original_name;
        }
        Intrinsics.checkNotNull(str);
        return MainAPIKt.newTvSeriesSearchResponse(tmdbProvider, str, getUrl(baseTvShow.id, true), TvType.TvSeries, false, new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TmdbProvider$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TmdbProvider.toSearchResponse$lambda$0(baseTvShow, this, (TvSeriesSearchResponse) obj);
            }
        });
    }

    static final Unit toSearchResponse$lambda$0(BaseTvShow baseTvShow, TmdbProvider tmdbProvider, TvSeriesSearchResponse newTvSeriesSearchResponse) {
        Integer numValueOf;
        Intrinsics.checkNotNullParameter(newTvSeriesSearchResponse, "$this$newTvSeriesSearchResponse");
        newTvSeriesSearchResponse.setId(baseTvShow.id);
        newTvSeriesSearchResponse.setPosterUrl(tmdbProvider.getImageUrl(baseTvShow.poster_path));
        newTvSeriesSearchResponse.setScore(Score.INSTANCE.from10(baseTvShow.vote_average));
        Date date = baseTvShow.first_air_date;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            numValueOf = Integer.valueOf(calendar.get(1));
        } else {
            numValueOf = null;
        }
        newTvSeriesSearchResponse.setYear(numValueOf);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MovieSearchResponse toSearchResponse(final BaseMovie baseMovie) {
        TmdbProvider tmdbProvider = this;
        String str = baseMovie.title;
        if (str == null) {
            str = baseMovie.original_title;
        }
        Intrinsics.checkNotNull(str);
        return MainAPIKt.newMovieSearchResponse(tmdbProvider, str, getUrl(baseMovie.id, false), TvType.Movie, false, new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TmdbProvider$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TmdbProvider.toSearchResponse$lambda$1(baseMovie, this, (MovieSearchResponse) obj);
            }
        });
    }

    static final Unit toSearchResponse$lambda$1(BaseMovie baseMovie, TmdbProvider tmdbProvider, MovieSearchResponse newMovieSearchResponse) {
        Integer numValueOf;
        Intrinsics.checkNotNullParameter(newMovieSearchResponse, "$this$newMovieSearchResponse");
        newMovieSearchResponse.setId(baseMovie.id);
        newMovieSearchResponse.setPosterUrl(tmdbProvider.getImageUrl(baseMovie.poster_path));
        newMovieSearchResponse.setScore(Score.INSTANCE.from10(baseMovie.vote_average));
        Date date = baseMovie.release_date;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            numValueOf = Integer.valueOf(calendar.get(1));
        } else {
            numValueOf = null;
        }
        newMovieSearchResponse.setYear(numValueOf);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Pair<Actor, String>> toActors(List<? extends CastMember> list) {
        String str;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (CastMember castMember : list) {
            Pair pair = (castMember == null || (str = castMember.name) == null) ? null : new Pair(new Actor(str, getImageUrl(castMember.profile_path)), castMember.character);
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:35:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:40:0x0116  */
    /* JADX WARN: Code duplicated, block: B:44:0x0127  */
    /* JADX WARN: Code duplicated, block: B:49:0x0137  */
    /* JADX WARN: Code duplicated, block: B:51:0x014b  */
    /* JADX WARN: Code duplicated, block: B:55:0x0153  */
    /* JADX WARN: Code duplicated, block: B:57:0x0157  */
    /* JADX WARN: Code duplicated, block: B:58:0x015a  */
    /* JADX WARN: Code duplicated, block: B:61:0x0168  */
    /* JADX WARN: Code duplicated, block: B:66:0x0199  */
    /* JADX WARN: Code duplicated, block: B:70:0x01d7 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:73:0x016a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x0192 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0116 -> B:41:0x011c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:49:0x0137
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object toLoadResponse(com.uwetrottmann.tmdb2.entities.TvShow r24, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.TvSeriesLoadResponse> r25) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.TmdbProvider.toLoadResponse(com.uwetrottmann.tmdb2.entities.TvShow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toLoadResponse$lambda$1$0(TvEpisode tvEpisode, TmdbProvider tmdbProvider, Episode newEpisode) {
        Intrinsics.checkNotNullParameter(newEpisode, "$this$newEpisode");
        newEpisode.setName(tvEpisode.name);
        newEpisode.setSeason(tvEpisode.season_number);
        newEpisode.setEpisode(tvEpisode.episode_number);
        newEpisode.setScore(Score.INSTANCE.from10(tvEpisode.vote_average));
        newEpisode.setDescription(tvEpisode.overview);
        Date date = tvEpisode.air_date;
        newEpisode.setDate(date != null ? Long.valueOf(date.getTime()) : null);
        newEpisode.setPosterUrl(tmdbProvider.getImageUrl(tvEpisode.still_path));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$toLoadResponse$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider$toLoadResponse$3", f = "TmdbProvider.kt", i = {0, 1}, l = {187, ByteCode.ATHROW}, m = "invokeSuspend", n = {"$this$newTvSeriesLoadResponse", "$this$newTvSeriesLoadResponse"}, nl = {188, 192}, s = {"L$0", "L$0"}, v = 2)
    static final class C07363 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ TvShow $this_toLoadResponse;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07363(TvShow tvShow, Continuation<? super C07363> continuation) {
            super(2, continuation);
            this.$this_toLoadResponse = tvShow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07363 c07363 = TmdbProvider.this.new C07363(this.$this_toLoadResponse, continuation);
            c07363.L$0 = obj;
            return c07363;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return ((C07363) create(tvSeriesLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:53:0x0157, code lost:
        
            if (r15 == r1) goto L54;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 354
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.TmdbProvider.C07363.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> toTrailers(Videos videos) {
        List<Videos.Video> list;
        String lowerCase;
        String string;
        if (videos != null && (list = videos.results) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                Videos.Video video = (Videos.Video) obj;
                if (video.type != VideoType.OPENING_CREDITS && video.type != VideoType.FEATURETTE) {
                    arrayList.add(obj);
                }
            }
            List<Videos.Video> listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.lagradost.cloudstream3.metaproviders.TmdbProvider$toTrailers$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    VideoType videoType = ((Videos.Video) t).type;
                    Integer numValueOf = Integer.valueOf(videoType != null ? videoType.ordinal() : 10000);
                    VideoType videoType2 = ((Videos.Video) t2).type;
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(videoType2 != null ? videoType2.ordinal() : 10000));
                }
            });
            if (listSortedWith != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Videos.Video video2 : listSortedWith) {
                    String str = video2.site;
                    if (str == null || (string = StringsKt.trim((CharSequence) str).toString()) == null) {
                        lowerCase = null;
                    } else {
                        lowerCase = string.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    }
                    String str2 = Intrinsics.areEqual(lowerCase, "youtube") ? "https://www.youtube.com/watch?v=" + video2.key : null;
                    if (str2 != null) {
                        arrayList2.add(str2);
                    }
                }
                return arrayList2;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object toLoadResponse(Movie movie, Continuation<? super MovieLoadResponse> continuation) {
        TmdbProvider tmdbProvider = this;
        String str = movie.title;
        if (str == null) {
            str = movie.original_title;
        }
        Intrinsics.checkNotNull(str);
        String url = getUrl(movie.id, false);
        TvType tvType = TvType.Movie;
        AppUtils appUtils = AppUtils.INSTANCE;
        String str2 = movie.imdb_id;
        Integer num = movie.id;
        String str3 = movie.title;
        if (str3 == null) {
            str3 = movie.original_title;
        }
        return MainAPIKt.newMovieLoadResponse((MainAPI) tmdbProvider, str, url, tvType, appUtils.toJson(new TmdbLink(str2, num, null, null, str3)), (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) new C07375(movie, null), continuation);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$toLoadResponse$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider$toLoadResponse$5", f = "TmdbProvider.kt", i = {0, 1}, l = {229, HebrewProber.NORMAL_KAF}, m = "invokeSuspend", n = {"$this$newMovieLoadResponse", "$this$newMovieLoadResponse"}, nl = {231, 236}, s = {"L$0", "L$0"}, v = 2)
    static final class C07375 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Movie $this_toLoadResponse;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07375(Movie movie, Continuation<? super C07375> continuation) {
            super(2, continuation);
            this.$this_toLoadResponse = movie;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C07375 c07375 = TmdbProvider.this.new C07375(this.$this_toLoadResponse, continuation);
            c07375.L$0 = obj;
            return c07375;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return ((C07375) create(movieLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:49:0x0148, code lost:
        
            if (r15 == r1) goto L50;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 339
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.TmdbProvider.C07375.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r12v0, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r13v0, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r14v0, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.util.List] */
    static /* synthetic */ Object getMainPage$suspendImpl(TmdbProvider tmdbProvider, int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) {
        C07321 c07321;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        Ref.ObjectRef objectRef3;
        Ref.ObjectRef objectRef4;
        if (continuation instanceof C07321) {
            c07321 = (C07321) continuation;
            if ((c07321.label & Integer.MIN_VALUE) != 0) {
                c07321.label -= Integer.MIN_VALUE;
            } else {
                c07321 = tmdbProvider.new C07321(continuation);
            }
        } else {
            c07321 = tmdbProvider.new C07321(continuation);
        }
        Object obj = c07321.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07321.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            objectRef = new Ref.ObjectRef();
            objectRef.element = CollectionsKt.emptyList();
            Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
            objectRef5.element = CollectionsKt.emptyList();
            Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
            objectRef6.element = CollectionsKt.emptyList();
            Ref.ObjectRef objectRef7 = new Ref.ObjectRef();
            objectRef7.element = CollectionsKt.emptyList();
            Function1[] function1Arr = {new AnonymousClass2(objectRef, tmdbProvider, i, null), new AnonymousClass3(objectRef5, tmdbProvider, i, null), new AnonymousClass4(objectRef6, tmdbProvider, i, null), new AnonymousClass5(objectRef7, tmdbProvider, i, null)};
            c07321.L$0 = SpillingKt.nullOutSpilledVariable(tmdbProvider);
            c07321.L$1 = SpillingKt.nullOutSpilledVariable(mainPageRequest);
            c07321.L$2 = objectRef;
            c07321.L$3 = objectRef5;
            c07321.L$4 = objectRef6;
            c07321.L$5 = objectRef7;
            c07321.I$0 = i;
            c07321.label = 1;
            if (ParCollectionsKt.runAllAsync(function1Arr, c07321) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef2 = objectRef5;
            objectRef3 = objectRef6;
            objectRef4 = objectRef7;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07321.I$0;
            objectRef4 = (Ref.ObjectRef) c07321.L$5;
            objectRef3 = (Ref.ObjectRef) c07321.L$4;
            objectRef2 = (Ref.ObjectRef) c07321.L$3;
            objectRef = (Ref.ObjectRef) c07321.L$2;
            ResultKt.throwOnFailure(obj);
        }
        return MainAPIKt.newHomePageResponse$default(CollectionsKt.listOf((Object[]) new HomePageList[]{new HomePageList("Popular Movies", (List) objectRef.element, false, 4, null), new HomePageList("Popular Series", (List) objectRef2.element, false, 4, null), new HomePageList("Top Movies", (List) objectRef3.element, false, 4, null), new HomePageList("Top Series", (List) objectRef4.element, false, 4, null)}), (Boolean) null, 2, (Object) null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$2, reason: invalid class name */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$2", f = "TmdbProvider.kt", i = {}, l = {257}, m = "invokeSuspend", n = {}, nl = {423}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<List<MovieSearchResponse>> $discoverMovies;
        final /* synthetic */ int $page;
        Object L$0;
        int label;
        final /* synthetic */ TmdbProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Ref.ObjectRef<List<MovieSearchResponse>> objectRef, TmdbProvider tmdbProvider, int i, Continuation<? super AnonymousClass2> continuation) {
            super(1, continuation);
            this.$discoverMovies = objectRef;
            this.this$0 = tmdbProvider;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass2(this.$discoverMovies, this.this$0, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef<List<MovieSearchResponse>> objectRef;
            T tEmptyList;
            Iterable iterable;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<List<MovieSearchResponse>> objectRef2 = this.$discoverMovies;
                Call<MovieResultsPage> callBuild = this.this$0.tmdb.discoverMovie().page(Boxing.boxInt(this.$page)).build();
                Intrinsics.checkNotNullExpressionValue(callBuild, "build(...)");
                this.L$0 = objectRef2;
                this.label = 1;
                Object objAwaitResponse = KotlinExtensions.awaitResponse(callBuild, this);
                if (objAwaitResponse == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = objAwaitResponse;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            MovieResultsPage movieResultsPage = (MovieResultsPage) ((Response) obj).body();
            if (movieResultsPage == null || (iterable = movieResultsPage.results) == null) {
                tEmptyList = CollectionsKt.emptyList();
            } else {
                Iterable<BaseMovie> iterable2 = iterable;
                TmdbProvider tmdbProvider = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (BaseMovie baseMovie : iterable2) {
                    Intrinsics.checkNotNull(baseMovie);
                    arrayList.add(tmdbProvider.toSearchResponse(baseMovie));
                }
                tEmptyList = arrayList;
            }
            objectRef.element = tEmptyList;
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$3, reason: invalid class name */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$3", f = "TmdbProvider.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED}, m = "invokeSuspend", n = {}, nl = {423}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<List<TvSeriesSearchResponse>> $discoverSeries;
        final /* synthetic */ int $page;
        Object L$0;
        int label;
        final /* synthetic */ TmdbProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.ObjectRef<List<TvSeriesSearchResponse>> objectRef, TmdbProvider tmdbProvider, int i, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$discoverSeries = objectRef;
            this.this$0 = tmdbProvider;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$discoverSeries, this.this$0, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef<List<TvSeriesSearchResponse>> objectRef;
            T tEmptyList;
            Iterable iterable;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<List<TvSeriesSearchResponse>> objectRef2 = this.$discoverSeries;
                Call<TvShowResultsPage> callBuild = this.this$0.tmdb.discoverTv().page(Boxing.boxInt(this.$page)).build();
                Intrinsics.checkNotNullExpressionValue(callBuild, "build(...)");
                this.L$0 = objectRef2;
                this.label = 1;
                Object objAwaitResponse = KotlinExtensions.awaitResponse(callBuild, this);
                if (objAwaitResponse == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = objAwaitResponse;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            TvShowResultsPage tvShowResultsPage = (TvShowResultsPage) ((Response) obj).body();
            if (tvShowResultsPage == null || (iterable = tvShowResultsPage.results) == null) {
                tEmptyList = CollectionsKt.emptyList();
            } else {
                Iterable<BaseTvShow> iterable2 = iterable;
                TmdbProvider tmdbProvider = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (BaseTvShow baseTvShow : iterable2) {
                    Intrinsics.checkNotNull(baseTvShow);
                    arrayList.add(tmdbProvider.toSearchResponse(baseTvShow));
                }
                tEmptyList = arrayList;
            }
            objectRef.element = tEmptyList;
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$4, reason: invalid class name */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$4", f = "TmdbProvider.kt", i = {}, l = {267}, m = "invokeSuspend", n = {}, nl = {268}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Ref.ObjectRef<List<MovieSearchResponse>> $topMovies;
        Object L$0;
        int label;
        final /* synthetic */ TmdbProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<List<MovieSearchResponse>> objectRef, TmdbProvider tmdbProvider, int i, Continuation<? super AnonymousClass4> continuation) {
            super(1, continuation);
            this.$topMovies = objectRef;
            this.this$0 = tmdbProvider;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass4(this.$topMovies, this.this$0, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef<List<MovieSearchResponse>> objectRef;
            T tEmptyList;
            Iterable iterable;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<List<MovieSearchResponse>> objectRef2 = this.$topMovies;
                Call<MovieResultsPage> call = this.this$0.tmdb.moviesService().topRated(Boxing.boxInt(this.$page), "en-US", "US");
                Intrinsics.checkNotNullExpressionValue(call, "topRated(...)");
                this.L$0 = objectRef2;
                this.label = 1;
                Object objAwaitResponse = KotlinExtensions.awaitResponse(call, this);
                if (objAwaitResponse == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = objAwaitResponse;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            MovieResultsPage movieResultsPage = (MovieResultsPage) ((Response) obj).body();
            if (movieResultsPage == null || (iterable = movieResultsPage.results) == null) {
                tEmptyList = CollectionsKt.emptyList();
            } else {
                Iterable<BaseMovie> iterable2 = iterable;
                TmdbProvider tmdbProvider = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (BaseMovie baseMovie : iterable2) {
                    Intrinsics.checkNotNull(baseMovie);
                    arrayList.add(tmdbProvider.toSearchResponse(baseMovie));
                }
                tEmptyList = arrayList;
            }
            objectRef.element = tEmptyList;
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$5, reason: invalid class name */
    /* JADX INFO: compiled from: TmdbProvider.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TmdbProvider$getMainPage$5", f = "TmdbProvider.kt", i = {}, l = {273}, m = "invokeSuspend", n = {}, nl = {423}, s = {}, v = 2)
    static final class AnonymousClass5 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ int $page;
        final /* synthetic */ Ref.ObjectRef<List<TvSeriesSearchResponse>> $topSeries;
        Object L$0;
        int label;
        final /* synthetic */ TmdbProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(Ref.ObjectRef<List<TvSeriesSearchResponse>> objectRef, TmdbProvider tmdbProvider, int i, Continuation<? super AnonymousClass5> continuation) {
            super(1, continuation);
            this.$topSeries = objectRef;
            this.this$0 = tmdbProvider;
            this.$page = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass5(this.$topSeries, this.this$0, this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Ref.ObjectRef<List<TvSeriesSearchResponse>> objectRef;
            T tEmptyList;
            Iterable iterable;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef<List<TvSeriesSearchResponse>> objectRef2 = this.$topSeries;
                Call<TvShowResultsPage> call = this.this$0.tmdb.tvService().topRated(Boxing.boxInt(this.$page), "en-US");
                Intrinsics.checkNotNullExpressionValue(call, "topRated(...)");
                this.L$0 = objectRef2;
                this.label = 1;
                Object objAwaitResponse = KotlinExtensions.awaitResponse(call, this);
                if (objAwaitResponse == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
                obj = objAwaitResponse;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            TvShowResultsPage tvShowResultsPage = (TvShowResultsPage) ((Response) obj).body();
            if (tvShowResultsPage == null || (iterable = tvShowResultsPage.results) == null) {
                tEmptyList = CollectionsKt.emptyList();
            } else {
                Iterable<BaseTvShow> iterable2 = iterable;
                TmdbProvider tmdbProvider = this.this$0;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                for (BaseTvShow baseTvShow : iterable2) {
                    Intrinsics.checkNotNull(baseTvShow);
                    arrayList.add(tmdbProvider.toSearchResponse(baseTvShow));
                }
                tEmptyList = arrayList;
            }
            objectRef.element = tEmptyList;
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:49:0x0101  */
    /* JADX WARN: Code duplicated, block: B:51:0x0104  */
    /* JADX WARN: Code duplicated, block: B:54:0x0110  */
    /* JADX WARN: Code duplicated, block: B:65:0x0135  */
    /* JADX WARN: Code duplicated, block: B:71:0x014b  */
    /* JADX WARN: Code duplicated, block: B:77:0x0154  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code duplicated, block: B:80:0x0120 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x014f A[SYNTHETIC] */
    static /* synthetic */ Object fetchContentRating$suspendImpl(TmdbProvider tmdbProvider, Integer num, String str, Continuation<? super String> continuation) {
        AnonymousClass1 anonymousClass1;
        String str2;
        Object next;
        ReleaseDatesResults releaseDatesResults;
        List<ReleaseDatesResult> list;
        Iterator<T> it;
        Object next2;
        ReleaseDatesResult releaseDatesResult;
        List<ReleaseDate> list2;
        Iterator<T> it2;
        Object next3;
        ReleaseDate releaseDate;
        String str3;
        boolean z;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = tmdbProvider.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = tmdbProvider.new AnonymousClass1(continuation);
        }
        Object objAwaitResponse = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objAwaitResponse);
            if (num != null) {
                num.intValue();
                Call<ContentRatings> callContent_ratings = tmdbProvider.tmdb.tvService().content_ratings(num.intValue());
                Intrinsics.checkNotNullExpressionValue(callContent_ratings, "content_ratings(...)");
                anonymousClass1.L$0 = tmdbProvider;
                anonymousClass1.L$1 = num;
                anonymousClass1.L$2 = str;
                anonymousClass1.label = 1;
                objAwaitResponse = KotlinExtensions.awaitResponse(callContent_ratings, anonymousClass1);
                if (objAwaitResponse != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            return null;
        }
        if (i == 1) {
            str = (String) anonymousClass1.L$2;
            num = (Integer) anonymousClass1.L$1;
            tmdbProvider = (TmdbProvider) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objAwaitResponse);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str2 = (String) anonymousClass1.L$2;
            ResultKt.throwOnFailure(objAwaitResponse);
        }
        releaseDatesResults = (ReleaseDatesResults) ((Response) objAwaitResponse).body();
        if (releaseDatesResults != null) {
            list = releaseDatesResults.results;
        } else {
            list = null;
        }
        if (list != null) {
            it = list.iterator();
            do {
                if (it.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it.next();
            } while (!Intrinsics.areEqual(((ReleaseDatesResult) next2).iso_3166_1, str2));
            releaseDatesResult = (ReleaseDatesResult) next2;
            if (releaseDatesResult != null && (list2 = releaseDatesResult.release_dates) != null) {
                it2 = list2.iterator();
                do {
                    if (it2.hasNext()) {
                        next3 = null;
                        break;
                    }
                    next3 = it2.next();
                    str3 = ((ReleaseDate) next3).certification;
                    if (str3 != null || StringsKt.isBlank(str3)) {
                        z = true;
                    } else {
                        z = false;
                    }
                } while (z);
                releaseDate = (ReleaseDate) next3;
                if (releaseDate != null) {
                    return releaseDate.certification;
                }
            }
        }
        return null;
        ContentRatings contentRatings = (ContentRatings) ((Response) objAwaitResponse).body();
        List<ContentRating> list3 = contentRatings != null ? contentRatings.results : null;
        List<ContentRating> list4 = list3;
        if (list4 == null || list4.isEmpty()) {
            Call<ReleaseDatesResults> callReleaseDates = tmdbProvider.tmdb.moviesService().releaseDates(num.intValue());
            Intrinsics.checkNotNullExpressionValue(callReleaseDates, "releaseDates(...)");
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(tmdbProvider);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(num);
            anonymousClass1.L$2 = str;
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(list3);
            anonymousClass1.label = 2;
            objAwaitResponse = KotlinExtensions.awaitResponse(callReleaseDates, anonymousClass1);
            if (objAwaitResponse != coroutine_suspended) {
                str2 = str;
                releaseDatesResults = (ReleaseDatesResults) ((Response) objAwaitResponse).body();
                if (releaseDatesResults != null) {
                    list = releaseDatesResults.results;
                } else {
                    list = null;
                }
                if (list != null) {
                    it = list.iterator();
                    do {
                        if (it.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it.next();
                    } while (!Intrinsics.areEqual(((ReleaseDatesResult) next2).iso_3166_1, str2));
                    releaseDatesResult = (ReleaseDatesResult) next2;
                    if (releaseDatesResult != null) {
                        it2 = list2.iterator();
                        do {
                            if (it2.hasNext()) {
                                next3 = null;
                                break;
                            }
                            next3 = it2.next();
                            str3 = ((ReleaseDate) next3).certification;
                            if (str3 != null) {
                                z = true;
                            } else {
                                z = true;
                            }
                        } while (z);
                        releaseDate = (ReleaseDate) next3;
                        if (releaseDate != null) {
                            return releaseDate.certification;
                        }
                    }
                }
                return null;
            }
            return coroutine_suspended;
        }
        Iterator<T> it3 = list3.iterator();
        do {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
        } while (!Intrinsics.areEqual(((ContentRating) next).iso_3166_1, str));
        ContentRating contentRating = (ContentRating) next;
        if (contentRating != null) {
            return contentRating.rating;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0401  */
    /* JADX WARN: Code duplicated, block: B:104:0x040e  */
    /* JADX WARN: Code duplicated, block: B:106:0x0415  */
    /* JADX WARN: Code duplicated, block: B:108:0x041d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0463  */
    /* JADX WARN: Code duplicated, block: B:118:0x0472  */
    /* JADX WARN: Code duplicated, block: B:120:0x0476  */
    /* JADX WARN: Code duplicated, block: B:123:0x048f A[LOOP:0: B:121:0x0489->B:123:0x048f, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:125:0x04a3  */
    /* JADX WARN: Code duplicated, block: B:127:0x04a7  */
    /* JADX WARN: Code duplicated, block: B:131:0x04b8  */
    /* JADX WARN: Code duplicated, block: B:136:0x04f7  */
    /* JADX WARN: Code duplicated, block: B:139:0x0502  */
    /* JADX WARN: Code duplicated, block: B:141:0x050b  */
    /* JADX WARN: Code duplicated, block: B:142:0x0510  */
    /* JADX WARN: Code duplicated, block: B:170:0x05c3  */
    /* JADX WARN: Code duplicated, block: B:173:0x05cd  */
    /* JADX WARN: Code duplicated, block: B:176:0x05d3  */
    /* JADX WARN: Code duplicated, block: B:179:0x05dc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:46:0x0243  */
    /* JADX WARN: Code duplicated, block: B:49:0x026b  */
    /* JADX WARN: Code duplicated, block: B:51:0x0276  */
    /* JADX WARN: Code duplicated, block: B:53:0x027b  */
    /* JADX WARN: Code duplicated, block: B:55:0x0283  */
    /* JADX WARN: Code duplicated, block: B:62:0x02cb  */
    /* JADX WARN: Code duplicated, block: B:65:0x02da  */
    /* JADX WARN: Code duplicated, block: B:67:0x02de  */
    /* JADX WARN: Code duplicated, block: B:70:0x02f7 A[LOOP:1: B:68:0x02f1->B:70:0x02f7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:72:0x030b  */
    /* JADX WARN: Code duplicated, block: B:74:0x030f  */
    /* JADX WARN: Code duplicated, block: B:77:0x031e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:82:0x035c  */
    /* JADX WARN: Code duplicated, block: B:85:0x0367  */
    /* JADX WARN: Code duplicated, block: B:87:0x0370  */
    /* JADX WARN: Code duplicated, block: B:88:0x0375  */
    /* JADX WARN: Code duplicated, block: B:99:0x03d8  */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x060c, code lost:
    
        if (r1 == r3) goto L184;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.lagradost.cloudstream3.TvSeriesLoadResponse, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v25, types: [com.lagradost.cloudstream3.MovieLoadResponse, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v55 */
    /* JADX WARN: Type inference failed for: r0v56 */
    /* JADX WARN: Type inference failed for: r0v57 */
    /* JADX WARN: Type inference failed for: r0v58 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r0v61 */
    /* JADX WARN: Type inference failed for: r0v62 */
    /* JADX WARN: Type inference failed for: r0v63 */
    /* JADX WARN: Type inference failed for: r0v64 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v20, types: [com.lagradost.cloudstream3.TvSeriesLoadResponse, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v32, types: [com.lagradost.cloudstream3.TvSeriesLoadResponse] */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v44, types: [com.lagradost.cloudstream3.MovieLoadResponse, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v56, types: [com.lagradost.cloudstream3.MovieLoadResponse] */
    /* JADX WARN: Type inference failed for: r6v59 */
    /* JADX WARN: Type inference failed for: r6v61 */
    /* JADX WARN: Type inference failed for: r6v64 */
    /* JADX WARN: Type inference failed for: r6v65 */
    /* JADX WARN: Type inference failed for: r6v66 */
    /* JADX WARN: Type inference failed for: r6v67 */
    /* JADX WARN: Type inference failed for: r6v68 */
    /* JADX WARN: Type inference failed for: r6v69 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object load$suspendImpl(com.lagradost.cloudstream3.metaproviders.TmdbProvider r19, java.lang.String r20, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r21) throws com.lagradost.cloudstream3.ErrorLoadingException {
        /*
            Method dump skipped, instruction units count: 1608
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.TmdbProvider.load$suspendImpl(com.lagradost.cloudstream3.metaproviders.TmdbProvider, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object search$suspendImpl(TmdbProvider tmdbProvider, String str, int i, Continuation<? super SearchResponseList> continuation) {
        C07341 c07341;
        Iterable<Media> iterable;
        SearchResponse searchResponse;
        SearchResponse searchResponse2;
        if (continuation instanceof C07341) {
            c07341 = (C07341) continuation;
            if ((c07341.label & Integer.MIN_VALUE) != 0) {
                c07341.label -= Integer.MIN_VALUE;
            } else {
                c07341 = tmdbProvider.new C07341(continuation);
            }
        } else {
            c07341 = tmdbProvider.new C07341(continuation);
        }
        Object objAwaitResponse = c07341.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07341.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objAwaitResponse);
            Call<MediaResultsPage> callMulti = tmdbProvider.tmdb.searchService().multi(str, Boxing.boxInt(i), "en-US", "US", Boxing.boxBoolean(tmdbProvider.getIncludeAdult()));
            Intrinsics.checkNotNullExpressionValue(callMulti, "multi(...)");
            c07341.L$0 = tmdbProvider;
            c07341.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07341.I$0 = i;
            c07341.label = 1;
            objAwaitResponse = KotlinExtensions.awaitResponse(callMulti, c07341);
            if (objAwaitResponse == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07341.I$0;
            tmdbProvider = (TmdbProvider) c07341.L$0;
            ResultKt.throwOnFailure(objAwaitResponse);
        }
        MediaResultsPage mediaResultsPage = (MediaResultsPage) ((Response) objAwaitResponse).body();
        if (mediaResultsPage == null || (iterable = mediaResultsPage.results) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Media media : iterable) {
            BaseMovie baseMovie = media.movie;
            if (baseMovie == null || (searchResponse2 = tmdbProvider.toSearchResponse(baseMovie)) == null) {
                BaseTvShow baseTvShow = media.tvShow;
                searchResponse = baseTvShow != null ? tmdbProvider.toSearchResponse(baseTvShow) : null;
            } else {
                searchResponse = searchResponse2;
            }
            if (searchResponse != null) {
                arrayList.add(searchResponse);
            }
        }
        return MainAPIKt.toNewSearchResponseList$default(arrayList, null, 1, null);
    }
}
