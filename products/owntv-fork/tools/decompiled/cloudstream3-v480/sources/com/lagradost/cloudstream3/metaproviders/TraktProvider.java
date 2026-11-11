package com.lagradost.cloudstream3.metaproviders;

import androidx.compose.ui.spatial.RectListKt;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.BuildConfig;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.Actor;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MainPageRequest;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.NextAiring;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ProviderType;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SearchResponseList;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.classfile.ByteCode;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: compiled from: TraktProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u00002\u00020\u0001:\u000b456789:;<=>B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u001c\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0096@¢\u0006\u0002\u0010#J\f\u0010$\u001a\u00020%*\u00020&H\u0002J \u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 H\u0096@¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010.J\u0016\u0010/\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010.J\u0012\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0005H\u0002J\u0014\u00103\u001a\u0004\u0018\u00010\u00052\b\u0010-\u001a\u0004\u0018\u00010\u0005H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006?"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider;", "Lcom/lagradost/cloudstream3/MainAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "hasMainPage", "", "getHasMainPage", "()Z", "providerType", "Lcom/lagradost/cloudstream3/ProviderType;", "getProviderType", "()Lcom/lagradost/cloudstream3/ProviderType;", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "traktApiUrl", "traktClientId", "mainPage", "", "Lcom/lagradost/cloudstream3/MainPageData;", "getMainPage", "()Ljava/util/List;", "Lcom/lagradost/cloudstream3/HomePageResponse;", "page", "", "request", "Lcom/lagradost/cloudstream3/MainPageRequest;", "(ILcom/lagradost/cloudstream3/MainPageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSearchResponse", "Lcom/lagradost/cloudstream3/SearchResponse;", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;", "search", "Lcom/lagradost/cloudstream3/SearchResponseList;", SearchIntents.EXTRA_QUERY, "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Lcom/lagradost/cloudstream3/LoadResponse;", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApi", "getStatus", "Lcom/lagradost/cloudstream3/ShowStatus;", "t", "fixPath", "Data", "MediaDetails", "Airs", "Ids", "Images", "People", "Cast", "Person", "Seasons", "TraktEpisode", "LinkData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class TraktProvider extends MainAPI {
    private String name = "Trakt";
    private final boolean hasMainPage = true;
    private final ProviderType providerType = ProviderType.MetaProvider;
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.Movie, TvType.TvSeries, TvType.Anime});
    private final String traktApiUrl = "https://api.trakt.tv";
    private final String traktClientId = BuildConfig.INSTANCE.getTRAKT_CLIENT_ID();
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf((Pair<String, String>[]) new Pair[]{TuplesKt.to("https://api.trakt.tv/movies/trending", "Trending Movies"), TuplesKt.to("https://api.trakt.tv/movies/popular", "Popular Movies"), TuplesKt.to("https://api.trakt.tv/shows/trending", "Trending Shows"), TuplesKt.to("https://api.trakt.tv/shows/popular", "Popular Shows")});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TraktProvider$getApi$1, reason: invalid class name */
    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TraktProvider", f = "TraktProvider.kt", i = {0}, l = {287}, m = "getApi", n = {ImagesContract.URL}, nl = {294}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktProvider.this.getApi(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TraktProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TraktProvider", f = "TraktProvider.kt", i = {0, 0, 0}, l = {60}, m = "getMainPage$suspendImpl", n = {"$this", "request", "page"}, nl = {62}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07381 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07381(Continuation<? super C07381> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktProvider.getMainPage$suspendImpl(TraktProvider.this, 0, (MainPageRequest) null, (Continuation<? super HomePageResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TraktProvider", f = "TraktProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {119, 132, 166, ByteCode.MONITOREXIT, 254}, m = "load$suspendImpl", n = {"$this", ImagesContract.URL, "data", "mediaDetails", "moviesOrShows", "posterUrl", "backDropUrl", "logoUrl", "$this", ImagesContract.URL, "data", "mediaDetails", "moviesOrShows", "posterUrl", "backDropUrl", "logoUrl", "resActor", "actors", "$this", ImagesContract.URL, "data", "mediaDetails", "moviesOrShows", "posterUrl", "backDropUrl", "logoUrl", "resActor", "actors", "resRelated", "relatedMedia", "uniqueUrl", "linkData", "isCartoon", "isAnime", "isAsian", "isBollywood", "$this", ImagesContract.URL, "data", "mediaDetails", "moviesOrShows", "posterUrl", "backDropUrl", "logoUrl", "resActor", "actors", "resRelated", "relatedMedia", "uniqueUrl", "isCartoon", "isAnime", "isAsian", "isBollywood", "$this", ImagesContract.URL, "data", "mediaDetails", "moviesOrShows", "posterUrl", "backDropUrl", "logoUrl", "resActor", "actors", "resRelated", "relatedMedia", "uniqueUrl", "resSeasons", "episodes", "seasons", "nextAir", "isCartoon", "isAnime", "isAsian", "isBollywood"}, nl = {118, 131, ByteCode.MONITOREXIT, ByteCode.MONITORENTER, -1}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "I$0", "I$1", "I$2", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "I$1", "I$2", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "I$0", "I$1", "I$2", "Z$0"}, v = 2)
    static final class C07391 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07391(Continuation<? super C07391> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktProvider.load$suspendImpl(TraktProvider.this, (String) null, (Continuation<? super LoadResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TraktProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TraktProvider", f = "TraktProvider.kt", i = {0, 0, 0}, l = {102}, m = "search$suspendImpl", n = {"$this", SearchIntents.EXTRA_QUERY, "page"}, nl = {101}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07401 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07401(Continuation<? super C07401> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktProvider.search$suspendImpl(TraktProvider.this, (String) null, 0, (Continuation<? super SearchResponseList>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Object getMainPage(int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) {
        return getMainPage$suspendImpl(this, i, mainPageRequest, continuation);
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Object load(String str, Continuation<? super LoadResponse> continuation) {
        return load$suspendImpl(this, str, continuation);
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Object search(String str, int i, Continuation<? super SearchResponseList> continuation) {
        return search$suspendImpl(this, str, i, continuation);
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public boolean getHasMainPage() {
        return this.hasMainPage;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public ProviderType getProviderType() {
        return this.providerType;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object getMainPage$suspendImpl(TraktProvider traktProvider, int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) throws JsonProcessingException {
        C07381 c07381;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof C07381) {
            c07381 = (C07381) continuation;
            if ((c07381.label & Integer.MIN_VALUE) != 0) {
                c07381.label -= Integer.MIN_VALUE;
            } else {
                c07381 = traktProvider.new C07381(continuation);
            }
        } else {
            c07381 = traktProvider.new C07381(continuation);
        }
        Object api = c07381.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07381.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(api);
            String str = mainPageRequest.getData() + "?extended=full,images&page=" + i;
            c07381.L$0 = traktProvider;
            c07381.L$1 = mainPageRequest;
            c07381.I$0 = i;
            c07381.label = 1;
            api = traktProvider.getApi(str, c07381);
            if (api == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07381.I$0;
            mainPageRequest = (MainPageRequest) c07381.L$1;
            traktProvider = (TraktProvider) c07381.L$0;
            ResultKt.throwOnFailure(api);
        }
        String str2 = (String) api;
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.MediaDetails")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
            objM8936constructorimpl = null;
        }
        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
        if (kSerializer != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str2);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$getMainPage$suspendImpl$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$getMainPage$suspendImpl$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$getMainPage$suspendImpl$$inlined$parseJson$1
            });
        }
        Iterable iterable = (Iterable) objDecodeFromString;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(traktProvider.toSearchResponse((MediaDetails) it.next()));
        }
        return MainAPIKt.newHomePageResponse$default(mainPageRequest.getName(), arrayList, (Boolean) null, 4, (Object) null);
    }

    private final SearchResponse toSearchResponse(final MediaDetails mediaDetails) {
        List<String> poster;
        MediaDetails media = mediaDetails.getMedia();
        if (media != null) {
            mediaDetails = media;
        }
        Ids ids = mediaDetails.getIds();
        final String str = null;
        TvType tvType = (ids != null ? ids.getTvdb() : null) == null ? TvType.Movie : TvType.TvSeries;
        Images images = mediaDetails.getImages();
        if (images != null && (poster = images.getPoster()) != null) {
            str = (String) CollectionsKt.firstOrNull((List) poster);
        }
        if (tvType == TvType.Movie) {
            TraktProvider traktProvider = this;
            String title = mediaDetails.getTitle();
            return MainAPIKt.newMovieSearchResponse$default(traktProvider, title == null ? "" : title, AppUtils.INSTANCE.toJson(new Data(tvType, mediaDetails)), TvType.Movie, false, new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TraktProvider.toSearchResponse$lambda$0(mediaDetails, this, str, (MovieSearchResponse) obj);
                }
            }, 8, null);
        }
        TvType tvType2 = tvType;
        TraktProvider traktProvider2 = this;
        String title2 = mediaDetails.getTitle();
        String str2 = title2 != null ? title2 : "";
        final String str3 = str;
        return MainAPIKt.newTvSeriesSearchResponse$default(traktProvider2, str2, AppUtils.INSTANCE.toJson(new Data(tvType2, mediaDetails)), TvType.TvSeries, false, new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TraktProvider.toSearchResponse$lambda$1(mediaDetails, this, str3, (TvSeriesSearchResponse) obj);
            }
        }, 8, null);
    }

    static final Unit toSearchResponse$lambda$0(MediaDetails mediaDetails, TraktProvider traktProvider, String str, MovieSearchResponse newMovieSearchResponse) {
        Intrinsics.checkNotNullParameter(newMovieSearchResponse, "$this$newMovieSearchResponse");
        newMovieSearchResponse.setScore(Score.INSTANCE.from10(mediaDetails.getRating()));
        newMovieSearchResponse.setPosterUrl(traktProvider.fixPath(str));
        return Unit.INSTANCE;
    }

    static final Unit toSearchResponse$lambda$1(MediaDetails mediaDetails, TraktProvider traktProvider, String str, TvSeriesSearchResponse newTvSeriesSearchResponse) {
        Intrinsics.checkNotNullParameter(newTvSeriesSearchResponse, "$this$newTvSeriesSearchResponse");
        newTvSeriesSearchResponse.setScore(Score.INSTANCE.from10(mediaDetails.getRating()));
        newTvSeriesSearchResponse.setPosterUrl(traktProvider.fixPath(str));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    static /* synthetic */ Object search$suspendImpl(TraktProvider traktProvider, String str, int i, Continuation<? super SearchResponseList> continuation) throws JsonProcessingException {
        C07401 c07401;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof C07401) {
            c07401 = (C07401) continuation;
            if ((c07401.label & Integer.MIN_VALUE) != 0) {
                c07401.label -= Integer.MIN_VALUE;
            } else {
                c07401 = traktProvider.new C07401(continuation);
            }
        } else {
            c07401 = traktProvider.new C07401(continuation);
        }
        Object api = c07401.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07401.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(api);
            String str2 = traktProvider.traktApiUrl + "/search/movie,show?extended=full,images&limit=20&page=" + i + "&query=" + str;
            c07401.L$0 = traktProvider;
            c07401.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07401.I$0 = i;
            c07401.label = 1;
            api = traktProvider.getApi(str2, c07401);
            if (api == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07401.I$0;
            traktProvider = (TraktProvider) c07401.L$0;
            ResultKt.throwOnFailure(api);
        }
        String str3 = (String) api;
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.MediaDetails")));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
            objM8936constructorimpl = null;
        }
        KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
        if (kSerializer != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str3);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(str3, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$search$suspendImpl$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str3, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$search$suspendImpl$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(str3, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$search$suspendImpl$$inlined$parseJson$1
            });
        }
        Iterable iterable = (Iterable) objDecodeFromString;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(traktProvider.toSearchResponse((MediaDetails) it.next()));
        }
        return MainAPIKt.newSearchResponseList$default(arrayList, null, 2, null);
    }

    /* JADX WARN: Code duplicated, block: B:147:0x0488  */
    /* JADX WARN: Code duplicated, block: B:154:0x049e  */
    /* JADX WARN: Code duplicated, block: B:158:0x04c8 A[LOOP:2: B:156:0x04c2->B:158:0x04c8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:166:0x04ec  */
    /* JADX WARN: Code duplicated, block: B:168:0x04ef  */
    /* JADX WARN: Code duplicated, block: B:172:0x04fd A[PHI: r1
      0x04fd: PHI (r1v12 int) = (r1v50 int), (r1v52 int) binds: [B:171:0x04fb, B:164:0x04e9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:176:0x0504  */
    /* JADX WARN: Code duplicated, block: B:181:0x051d  */
    /* JADX WARN: Code duplicated, block: B:183:0x0521 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:184:0x0523  */
    /* JADX WARN: Code duplicated, block: B:185:0x0528  */
    /* JADX WARN: Code duplicated, block: B:188:0x052f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:189:0x0531  */
    /* JADX WARN: Code duplicated, block: B:190:0x0536  */
    /* JADX WARN: Code duplicated, block: B:194:0x0542  */
    /* JADX WARN: Code duplicated, block: B:196:0x0546  */
    /* JADX WARN: Code duplicated, block: B:197:0x054b  */
    /* JADX WARN: Code duplicated, block: B:200:0x0558  */
    /* JADX WARN: Code duplicated, block: B:206:0x056c  */
    /* JADX WARN: Code duplicated, block: B:209:0x057c  */
    /* JADX WARN: Code duplicated, block: B:211:0x0582  */
    /* JADX WARN: Code duplicated, block: B:214:0x058f  */
    /* JADX WARN: Code duplicated, block: B:216:0x0593  */
    /* JADX WARN: Code duplicated, block: B:219:0x05a0  */
    /* JADX WARN: Code duplicated, block: B:221:0x05a4  */
    /* JADX WARN: Code duplicated, block: B:224:0x05b1  */
    /* JADX WARN: Code duplicated, block: B:226:0x05b5  */
    /* JADX WARN: Code duplicated, block: B:229:0x05c2  */
    /* JADX WARN: Code duplicated, block: B:231:0x05c6  */
    /* JADX WARN: Code duplicated, block: B:234:0x05d1  */
    /* JADX WARN: Code duplicated, block: B:237:0x05d8  */
    /* JADX WARN: Code duplicated, block: B:240:0x05e5  */
    /* JADX WARN: Code duplicated, block: B:242:0x05e9  */
    /* JADX WARN: Code duplicated, block: B:245:0x05f6  */
    /* JADX WARN: Code duplicated, block: B:248:0x0602  */
    /* JADX WARN: Code duplicated, block: B:249:0x0609  */
    /* JADX WARN: Code duplicated, block: B:251:0x060d  */
    /* JADX WARN: Code duplicated, block: B:252:0x0614  */
    /* JADX WARN: Code duplicated, block: B:254:0x0618  */
    /* JADX WARN: Code duplicated, block: B:255:0x061f  */
    /* JADX WARN: Code duplicated, block: B:257:0x0623  */
    /* JADX WARN: Code duplicated, block: B:261:0x062d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:262:0x062f  */
    /* JADX WARN: Code duplicated, block: B:263:0x0634  */
    /* JADX WARN: Code duplicated, block: B:266:0x065e  */
    /* JADX WARN: Code duplicated, block: B:267:0x0665  */
    /* JADX WARN: Code duplicated, block: B:270:0x0678  */
    /* JADX WARN: Code duplicated, block: B:271:0x067b  */
    /* JADX WARN: Code duplicated, block: B:275:0x0700 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:276:0x0701  */
    /* JADX WARN: Code duplicated, block: B:278:0x071b  */
    /* JADX WARN: Code duplicated, block: B:281:0x0726  */
    /* JADX WARN: Code duplicated, block: B:285:0x077e  */
    /* JADX WARN: Code duplicated, block: B:298:0x07cb  */
    /* JADX WARN: Code duplicated, block: B:310:0x0808  */
    /* JADX WARN: Code duplicated, block: B:317:0x081e  */
    /* JADX WARN: Code duplicated, block: B:321:0x0843  */
    /* JADX WARN: Code duplicated, block: B:323:0x084f  */
    /* JADX WARN: Code duplicated, block: B:326:0x0870  */
    /* JADX WARN: Code duplicated, block: B:328:0x087c  */
    /* JADX WARN: Code duplicated, block: B:331:0x0887  */
    /* JADX WARN: Code duplicated, block: B:333:0x088b  */
    /* JADX WARN: Code duplicated, block: B:336:0x0896  */
    /* JADX WARN: Code duplicated, block: B:338:0x089a  */
    /* JADX WARN: Code duplicated, block: B:341:0x08a5  */
    /* JADX WARN: Code duplicated, block: B:343:0x08a9  */
    /* JADX WARN: Code duplicated, block: B:346:0x08b4  */
    /* JADX WARN: Code duplicated, block: B:348:0x08b8  */
    /* JADX WARN: Code duplicated, block: B:351:0x08c3  */
    /* JADX WARN: Code duplicated, block: B:354:0x08cb  */
    /* JADX WARN: Code duplicated, block: B:357:0x08d6  */
    /* JADX WARN: Code duplicated, block: B:359:0x08da  */
    /* JADX WARN: Code duplicated, block: B:362:0x08e5  */
    /* JADX WARN: Code duplicated, block: B:365:0x08f9  */
    /* JADX WARN: Code duplicated, block: B:366:0x0900  */
    /* JADX WARN: Code duplicated, block: B:368:0x0904  */
    /* JADX WARN: Code duplicated, block: B:369:0x090b  */
    /* JADX WARN: Code duplicated, block: B:371:0x090f  */
    /* JADX WARN: Code duplicated, block: B:372:0x0916  */
    /* JADX WARN: Code duplicated, block: B:374:0x091a  */
    /* JADX WARN: Code duplicated, block: B:375:0x091d  */
    /* JADX WARN: Code duplicated, block: B:377:0x0921  */
    /* JADX WARN: Code duplicated, block: B:378:0x0928  */
    /* JADX WARN: Code duplicated, block: B:381:0x0940  */
    /* JADX WARN: Code duplicated, block: B:382:0x0943  */
    /* JADX WARN: Code duplicated, block: B:384:0x0947  */
    /* JADX WARN: Code duplicated, block: B:385:0x094a  */
    /* JADX WARN: Code duplicated, block: B:388:0x0998  */
    /* JADX WARN: Code duplicated, block: B:392:0x09c2  */
    /* JADX WARN: Code duplicated, block: B:393:0x09c7  */
    /* JADX WARN: Code duplicated, block: B:396:0x09d3  */
    /* JADX WARN: Code duplicated, block: B:397:0x09d6  */
    /* JADX WARN: Code duplicated, block: B:400:0x09de  */
    /* JADX WARN: Code duplicated, block: B:401:0x09e1  */
    /* JADX WARN: Code duplicated, block: B:405:0x0a74 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:420:0x07d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:424:0x045c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:430:0x048d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:432:0x080d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v108 */
    /* JADX WARN: Type inference failed for: r25v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r47v0 */
    /* JADX WARN: Type inference failed for: r47v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r47v3 */
    /* JADX WARN: Type inference failed for: r54v0 */
    /* JADX WARN: Type inference failed for: r54v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r54v2 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v23, types: [int] */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v30 */
    static /* synthetic */ Object load$suspendImpl(TraktProvider traktProvider, String str, Continuation<? super LoadResponse> continuation) throws JsonProcessingException {
        C07391 c07391;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Data data;
        String str2;
        String strFixPath;
        String strFixPath2;
        String strFixPath3;
        MediaDetails mediaDetails;
        Object obj;
        Ids ids;
        Images images;
        List<String> logo;
        Images images2;
        List<String> fanart;
        Images images3;
        List<String> poster;
        Object objM8936constructorimpl2;
        Object objDecodeFromString2;
        ArrayList arrayList;
        List list;
        String str3;
        Object obj2;
        String str4;
        String str5;
        MediaDetails mediaDetails2;
        String str6;
        final TraktProvider traktProvider2;
        String str7;
        Ids ids2;
        List<String> headshot;
        String str8;
        Object objM8936constructorimpl3;
        KSerializer kSerializer;
        Object objDecodeFromString3;
        ArrayList arrayList2;
        Iterator it;
        ArrayList arrayList3;
        int i;
        int i2;
        ?? r47;
        ?? r54;
        String country;
        boolean zAreEqual;
        MediaDetails mediaDetails3;
        String json;
        String str9;
        String str10;
        MediaDetails mediaDetails4;
        ?? r0;
        ?? r7;
        Integer trakt;
        String str11;
        Object api;
        boolean z;
        String str12;
        MediaDetails mediaDetails5;
        String str13;
        String str14;
        String str15;
        List list2;
        List list3;
        String str16;
        int i3;
        Object obj3;
        String str17;
        String str18;
        Ids ids3;
        Integer tmdb;
        Integer trakt2;
        String slug;
        Integer tmdb2;
        String imdb;
        Integer tvdb;
        String tvrage;
        String title;
        Integer year;
        String title2;
        String str19;
        String title3;
        TvType tvType;
        Object objNewMovieLoadResponse;
        Ids ids4;
        Ids ids5;
        Ids ids6;
        Ids ids7;
        Ids ids8;
        Ids ids9;
        Ids ids10;
        Ids ids11;
        Integer trakt3;
        String language;
        String language2;
        List<String> genres;
        List<String> genres2;
        ?? r8;
        String str20;
        ArrayList arrayList4;
        String str21;
        Object objM8936constructorimpl4;
        List list4;
        String str22;
        Object obj4;
        KSerializer kSerializer2;
        Object objDecodeFromString4;
        List list5;
        final Ref.ObjectRef objectRef;
        Iterator it2;
        String title4;
        TvType tvType2;
        boolean z2;
        Object objNewTvSeriesLoadResponse;
        List<TraktEpisode> episodes;
        List list6;
        List list7;
        String str23;
        ArrayList arrayList5;
        Iterator it3;
        Integer tmdb3;
        Integer trakt4;
        String slug2;
        Integer tmdb4;
        String imdb2;
        Integer tvdb2;
        String tvrage2;
        String title5;
        Integer year2;
        String title6;
        boolean z3;
        Integer year3;
        boolean z4;
        boolean z5;
        Ids ids12;
        Ids ids13;
        Ids ids14;
        Ids ids15;
        Ids ids16;
        Ids ids17;
        Ids ids18;
        TraktProvider traktProvider3 = traktProvider;
        String str24 = str;
        if (continuation instanceof C07391) {
            c07391 = (C07391) continuation;
            if ((c07391.label & Integer.MIN_VALUE) != 0) {
                c07391.label -= Integer.MIN_VALUE;
            } else {
                c07391 = traktProvider3.new C07391(continuation);
            }
        } else {
            c07391 = traktProvider3.new C07391(continuation);
        }
        C07391 c07392 = c07391;
        Object obj5 = c07392.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = c07392.label;
        try {
            if (i4 == 0) {
                ResultKt.throwOnFailure(obj5);
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.Data"));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Data.class), null, 2, null));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                    objM8936constructorimpl = null;
                }
                KSerializer kSerializer3 = (KSerializer) objM8936constructorimpl;
                if (kSerializer3 != null) {
                    try {
                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer3, str24);
                    } catch (SerializationException e) {
                        ArchComponentExtKt.logError(e);
                        objDecodeFromString = MainAPIKt.getMapper().readValue(str24, new TypeReference<Data>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$1
                        });
                    } catch (Throwable unused) {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(str24, new TypeReference<Data>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$1
                        });
                    }
                } else {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str24, new TypeReference<Data>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$1
                    });
                }
                data = (Data) objDecodeFromString;
                MediaDetails mediaDetails6 = data.getMediaDetails();
                str2 = data.getType() == TvType.Movie ? "movies" : "shows";
                strFixPath = traktProvider3.fixPath((mediaDetails6 == null || (images3 = mediaDetails6.getImages()) == null || (poster = images3.getPoster()) == null) ? null : (String) CollectionsKt.firstOrNull((List) poster));
                strFixPath2 = traktProvider3.fixPath((mediaDetails6 == null || (images2 = mediaDetails6.getImages()) == null || (fanart = images2.getFanart()) == null) ? null : (String) CollectionsKt.firstOrNull((List) fanart));
                strFixPath3 = traktProvider3.fixPath((mediaDetails6 == null || (images = mediaDetails6.getImages()) == null || (logo = images.getLogo()) == null) ? null : (String) CollectionsKt.firstOrNull((List) logo));
                StringBuilder sb = new StringBuilder();
                sb.append(traktProvider3.traktApiUrl);
                sb.append('/');
                sb.append(str2);
                sb.append('/');
                sb.append((mediaDetails6 == null || (ids = mediaDetails6.getIds()) == null) ? null : ids.getTrakt());
                sb.append("/people?extended=full,images");
                String string = sb.toString();
                c07392.L$0 = traktProvider3;
                c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                c07392.L$2 = data;
                c07392.L$3 = mediaDetails6;
                c07392.L$4 = str2;
                c07392.L$5 = strFixPath;
                c07392.L$6 = strFixPath2;
                c07392.L$7 = strFixPath3;
                c07392.label = 1;
                Object api2 = traktProvider3.getApi(string, c07392);
                if (api2 != coroutine_suspended) {
                    mediaDetails = mediaDetails6;
                    obj = api2;
                }
                return coroutine_suspended;
            }
            try {
                try {
                    if (i4 == 1) {
                        String str25 = (String) c07392.L$7;
                        String str26 = (String) c07392.L$6;
                        String str27 = (String) c07392.L$5;
                        str2 = (String) c07392.L$4;
                        MediaDetails mediaDetails7 = (MediaDetails) c07392.L$3;
                        data = (Data) c07392.L$2;
                        String str28 = (String) c07392.L$1;
                        TraktProvider traktProvider4 = (TraktProvider) c07392.L$0;
                        ResultKt.throwOnFailure(obj5);
                        mediaDetails = mediaDetails7;
                        strFixPath = str27;
                        str24 = str28;
                        strFixPath2 = str26;
                        traktProvider3 = traktProvider4;
                        strFixPath3 = str25;
                        obj = obj5;
                    } else {
                        if (i4 == 2) {
                            List list8 = (List) c07392.L$9;
                            String str29 = (String) c07392.L$8;
                            String str30 = (String) c07392.L$7;
                            String str31 = (String) c07392.L$6;
                            String str32 = (String) c07392.L$5;
                            String str33 = (String) c07392.L$4;
                            MediaDetails mediaDetails8 = (MediaDetails) c07392.L$3;
                            Data data2 = (Data) c07392.L$2;
                            String str34 = (String) c07392.L$1;
                            traktProvider2 = (TraktProvider) c07392.L$0;
                            ResultKt.throwOnFailure(obj5);
                            list = list8;
                            str5 = str30;
                            str4 = str31;
                            str3 = str32;
                            str7 = str33;
                            mediaDetails2 = mediaDetails8;
                            data = data2;
                            str24 = str34;
                            obj2 = obj5;
                            str6 = str29;
                            str8 = (String) obj2;
                            AppUtils appUtils2 = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                objM8936constructorimpl3 = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.MediaDetails")));
                            } catch (Throwable th3) {
                                Result.Companion companion6 = Result.INSTANCE;
                                objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) != null) {
                                try {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    objM8936constructorimpl3 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                                } catch (Throwable th4) {
                                    Result.Companion companion8 = Result.INSTANCE;
                                    objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                                }
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                                objM8936constructorimpl3 = null;
                            }
                            kSerializer = (KSerializer) objM8936constructorimpl3;
                            if (kSerializer != null) {
                                try {
                                    objDecodeFromString3 = MainAPIKt.getJson().decodeFromString(kSerializer, str8);
                                } catch (SerializationException e2) {
                                    ArchComponentExtKt.logError(e2);
                                    objDecodeFromString3 = MainAPIKt.getMapper().readValue(str8, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$3
                                    });
                                } catch (Throwable unused2) {
                                    objDecodeFromString3 = MainAPIKt.getMapper().readValue(str8, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$3
                                    });
                                }
                            } else {
                                objDecodeFromString3 = MainAPIKt.getMapper().readValue(str8, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$3
                                });
                            }
                            Iterable iterable = (Iterable) objDecodeFromString3;
                            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                            it = iterable.iterator();
                            while (it.hasNext()) {
                                arrayList2.add(traktProvider2.toSearchResponse((MediaDetails) it.next()));
                            }
                            arrayList3 = arrayList2;
                            if (mediaDetails2 == null && (genres2 = mediaDetails2.getGenres()) != null) {
                                i = 1;
                                i2 = genres2.contains("animation") ? i : 0;
                                if (i2 == 0 && (Intrinsics.areEqual(mediaDetails2.getLanguage(), "zh") || Intrinsics.areEqual(mediaDetails2.getLanguage(), "ja"))) {
                                    r47 = i;
                                } else {
                                    r47 = 0;
                                }
                                if (r47 != 0) {
                                    r54 = 0;
                                } else {
                                    if (mediaDetails2 != null) {
                                        language = mediaDetails2.getLanguage();
                                    } else {
                                        language = null;
                                    }
                                    if (!Intrinsics.areEqual(language, "zh")) {
                                        if (mediaDetails2 != null) {
                                            language2 = mediaDetails2.getLanguage();
                                        } else {
                                            language2 = null;
                                        }
                                        if (!Intrinsics.areEqual(language2, "ko")) {
                                            r54 = 0;
                                        }
                                    }
                                    r54 = i;
                                }
                                if (mediaDetails2 != null) {
                                    country = mediaDetails2.getCountry();
                                } else {
                                    country = null;
                                }
                                zAreEqual = Intrinsics.areEqual(country, "in");
                                mediaDetails3 = data.getMediaDetails();
                                if (mediaDetails3 != null || (ids11 = mediaDetails3.getIds()) == null || (trakt3 = ids11.getTrakt()) == null || (json = AppUtils.INSTANCE.toJson(trakt3)) == null) {
                                    json = AppUtils.INSTANCE.toJson(data);
                                }
                                str9 = json;
                                if (data.getType() == TvType.Movie) {
                                    AppUtils appUtils3 = AppUtils.INSTANCE;
                                    if (mediaDetails2 != null || (ids10 = mediaDetails2.getIds()) == null) {
                                        tmdb = null;
                                    } else {
                                        tmdb = ids10.getTmdb();
                                    }
                                    if (mediaDetails2 != null || (ids9 = mediaDetails2.getIds()) == null) {
                                        trakt2 = null;
                                    } else {
                                        trakt2 = ids9.getTrakt();
                                    }
                                    if (mediaDetails2 != null || (ids8 = mediaDetails2.getIds()) == null) {
                                        slug = null;
                                    } else {
                                        slug = ids8.getSlug();
                                    }
                                    if (mediaDetails2 != null || (ids7 = mediaDetails2.getIds()) == null) {
                                        tmdb2 = null;
                                    } else {
                                        tmdb2 = ids7.getTmdb();
                                    }
                                    if (mediaDetails2 != null || (ids6 = mediaDetails2.getIds()) == null) {
                                        imdb = null;
                                    } else {
                                        imdb = ids6.getImdb();
                                    }
                                    String strValueOf = String.valueOf(imdb);
                                    if (mediaDetails2 != null || (ids5 = mediaDetails2.getIds()) == null) {
                                        tvdb = null;
                                    } else {
                                        tvdb = ids5.getTvdb();
                                    }
                                    if (mediaDetails2 != null || (ids4 = mediaDetails2.getIds()) == null) {
                                        tvrage = null;
                                    } else {
                                        tvrage = ids4.getTvrage();
                                    }
                                    String string2 = data.getType().toString();
                                    if (mediaDetails2 != null) {
                                        title = mediaDetails2.getTitle();
                                    } else {
                                        title = null;
                                    }
                                    if (mediaDetails2 != null) {
                                        year = mediaDetails2.getYear();
                                    } else {
                                        year = null;
                                    }
                                    if (mediaDetails2 != null) {
                                        title2 = mediaDetails2.getTitle();
                                    } else {
                                        title2 = null;
                                    }
                                    if (mediaDetails2 == null && (firstAired = mediaDetails2.getReleased()) != null) {
                                        str19 = firstAired;
                                    } else if (mediaDetails2 != null) {
                                        String firstAired = mediaDetails2.getFirstAired();
                                        str19 = firstAired;
                                    } else {
                                        str19 = null;
                                    }
                                    ?? r9 = r54;
                                    String json2 = appUtils3.toJson(new LinkData(tmdb, trakt2, slug, tmdb2, strValueOf, tvdb, tvrage, string2, null, null, null, null, title, year, title2, r47, null, null, null, null, null, str19, r54, zAreEqual, false, 18812672, null));
                                    if (mediaDetails2 != null) {
                                        title3 = mediaDetails2.getTitle();
                                    } else {
                                        title3 = null;
                                    }
                                    Intrinsics.checkNotNull(title3);
                                    String json3 = AppUtils.INSTANCE.toJson(data);
                                    String json4 = AppUtils.INSTANCE.toJson(json2);
                                    if (r47 != 0) {
                                        tvType = TvType.AnimeMovie;
                                    } else {
                                        tvType = TvType.Movie;
                                    }
                                    ?? r25 = r47;
                                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(str9, mediaDetails2, r25, str3, arrayList3, list, str4, str5, null);
                                    c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider2);
                                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                                    c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                                    c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails2);
                                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                                    c07392.L$5 = SpillingKt.nullOutSpilledVariable(str3);
                                    c07392.L$6 = SpillingKt.nullOutSpilledVariable(str4);
                                    c07392.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str6);
                                    c07392.L$9 = SpillingKt.nullOutSpilledVariable(list);
                                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                                    c07392.L$11 = SpillingKt.nullOutSpilledVariable(arrayList3);
                                    c07392.L$12 = SpillingKt.nullOutSpilledVariable(str9);
                                    c07392.L$13 = SpillingKt.nullOutSpilledVariable(json2);
                                    c07392.I$0 = i2;
                                    c07392.I$1 = r25 == true ? 1 : 0;
                                    c07392.I$2 = r9 == true ? 1 : 0;
                                    c07392.Z$0 = zAreEqual;
                                    c07392.label = 3;
                                    objNewMovieLoadResponse = MainAPIKt.newMovieLoadResponse((MainAPI) traktProvider2, title3, json3, tvType, json4, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) anonymousClass2, (Continuation<? super MovieLoadResponse>) c07392);
                                    if (objNewMovieLoadResponse != coroutine_suspended) {
                                        return objNewMovieLoadResponse;
                                    }
                                } else {
                                    str10 = str6;
                                    mediaDetails4 = mediaDetails2;
                                    r0 = r47;
                                    r7 = r54;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(traktProvider2.traktApiUrl);
                                    sb2.append("/shows/");
                                    if (mediaDetails4 != null || (ids3 = mediaDetails4.getIds()) == null) {
                                        trakt = null;
                                    } else {
                                        trakt = ids3.getTrakt();
                                    }
                                    sb2.append(trakt);
                                    sb2.append("/seasons?extended=full,images,episodes");
                                    String string3 = sb2.toString();
                                    c07392.L$0 = traktProvider2;
                                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                                    c07392.L$2 = data;
                                    c07392.L$3 = mediaDetails4;
                                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                                    str11 = str3;
                                    c07392.L$5 = str11;
                                    c07392.L$6 = str4;
                                    c07392.L$7 = str5;
                                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str10);
                                    c07392.L$9 = list;
                                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                                    c07392.L$11 = arrayList3;
                                    c07392.L$12 = str9;
                                    c07392.I$0 = i2;
                                    c07392.I$1 = r0 == true ? 1 : 0;
                                    c07392.I$2 = r7 == true ? 1 : 0;
                                    c07392.Z$0 = zAreEqual;
                                    c07392.label = 4;
                                    api = traktProvider2.getApi(string3, c07392);
                                    if (api != coroutine_suspended) {
                                        z = zAreEqual;
                                        str12 = str10;
                                        mediaDetails5 = mediaDetails4;
                                        str13 = str11;
                                        str14 = str8;
                                        str15 = str9;
                                        list2 = arrayList3;
                                        list3 = list;
                                        str16 = str5;
                                        i3 = r0 == true ? 1 : 0;
                                        obj3 = api;
                                        str17 = str7;
                                        str18 = str4;
                                        r8 = r7;
                                    }
                                }
                                return coroutine_suspended;
                            }
                            i = 1;
                            if (mediaDetails2 != null || (genres = mediaDetails2.getGenres()) == null || genres.contains("anime") != i) {
                            }
                            if (i2 == 0) {
                                r47 = 0;
                            } else {
                                r47 = 0;
                            }
                            if (r47 != 0) {
                                r54 = 0;
                            } else {
                                if (mediaDetails2 != null) {
                                    language = mediaDetails2.getLanguage();
                                } else {
                                    language = null;
                                }
                                if (!Intrinsics.areEqual(language, "zh")) {
                                    if (mediaDetails2 != null) {
                                        language2 = mediaDetails2.getLanguage();
                                    } else {
                                        language2 = null;
                                    }
                                    if (!Intrinsics.areEqual(language2, "ko")) {
                                        r54 = 0;
                                    }
                                }
                                r54 = i;
                            }
                            if (mediaDetails2 != null) {
                                country = mediaDetails2.getCountry();
                            } else {
                                country = null;
                            }
                            zAreEqual = Intrinsics.areEqual(country, "in");
                            mediaDetails3 = data.getMediaDetails();
                            if (mediaDetails3 != null) {
                                json = AppUtils.INSTANCE.toJson(data);
                            } else {
                                json = AppUtils.INSTANCE.toJson(data);
                            }
                            str9 = json;
                            if (data.getType() == TvType.Movie) {
                                AppUtils appUtils4 = AppUtils.INSTANCE;
                                if (mediaDetails2 != null) {
                                    tmdb = null;
                                } else {
                                    tmdb = null;
                                }
                                if (mediaDetails2 != null) {
                                    trakt2 = null;
                                } else {
                                    trakt2 = null;
                                }
                                if (mediaDetails2 != null) {
                                    slug = null;
                                } else {
                                    slug = null;
                                }
                                if (mediaDetails2 != null) {
                                    tmdb2 = null;
                                } else {
                                    tmdb2 = null;
                                }
                                if (mediaDetails2 != null) {
                                    imdb = null;
                                } else {
                                    imdb = null;
                                }
                                String strValueOf2 = String.valueOf(imdb);
                                if (mediaDetails2 != null) {
                                    tvdb = null;
                                } else {
                                    tvdb = null;
                                }
                                if (mediaDetails2 != null) {
                                    tvrage = null;
                                } else {
                                    tvrage = null;
                                }
                                String string4 = data.getType().toString();
                                if (mediaDetails2 != null) {
                                    title = mediaDetails2.getTitle();
                                } else {
                                    title = null;
                                }
                                if (mediaDetails2 != null) {
                                    year = mediaDetails2.getYear();
                                } else {
                                    year = null;
                                }
                                if (mediaDetails2 != null) {
                                    title2 = mediaDetails2.getTitle();
                                } else {
                                    title2 = null;
                                }
                                if (mediaDetails2 == null) {
                                    if (mediaDetails2 != null) {
                                        String firstAired2 = mediaDetails2.getFirstAired();
                                        str19 = firstAired2;
                                    } else {
                                        str19 = null;
                                    }
                                } else if (mediaDetails2 != null) {
                                    String firstAired3 = mediaDetails2.getFirstAired();
                                    str19 = firstAired3;
                                } else {
                                    str19 = null;
                                }
                                ?? r10 = r54;
                                String json5 = appUtils4.toJson(new LinkData(tmdb, trakt2, slug, tmdb2, strValueOf2, tvdb, tvrage, string4, null, null, null, null, title, year, title2, r47, null, null, null, null, null, str19, r54, zAreEqual, false, 18812672, null));
                                if (mediaDetails2 != null) {
                                    title3 = mediaDetails2.getTitle();
                                } else {
                                    title3 = null;
                                }
                                Intrinsics.checkNotNull(title3);
                                String json6 = AppUtils.INSTANCE.toJson(data);
                                String json7 = AppUtils.INSTANCE.toJson(json5);
                                if (r47 != 0) {
                                    tvType = TvType.AnimeMovie;
                                } else {
                                    tvType = TvType.Movie;
                                }
                                ?? r26 = r47;
                                AnonymousClass2 anonymousClass3 = new AnonymousClass2(str9, mediaDetails2, r26, str3, arrayList3, list, str4, str5, null);
                                c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider2);
                                c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                                c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                                c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails2);
                                c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                                c07392.L$5 = SpillingKt.nullOutSpilledVariable(str3);
                                c07392.L$6 = SpillingKt.nullOutSpilledVariable(str4);
                                c07392.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                                c07392.L$8 = SpillingKt.nullOutSpilledVariable(str6);
                                c07392.L$9 = SpillingKt.nullOutSpilledVariable(list);
                                c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                                c07392.L$11 = SpillingKt.nullOutSpilledVariable(arrayList3);
                                c07392.L$12 = SpillingKt.nullOutSpilledVariable(str9);
                                c07392.L$13 = SpillingKt.nullOutSpilledVariable(json5);
                                c07392.I$0 = i2;
                                c07392.I$1 = r26 == true ? 1 : 0;
                                c07392.I$2 = r10 == true ? 1 : 0;
                                c07392.Z$0 = zAreEqual;
                                c07392.label = 3;
                                objNewMovieLoadResponse = MainAPIKt.newMovieLoadResponse((MainAPI) traktProvider2, title3, json6, tvType, json7, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) anonymousClass3, (Continuation<? super MovieLoadResponse>) c07392);
                                if (objNewMovieLoadResponse != coroutine_suspended) {
                                    return objNewMovieLoadResponse;
                                }
                            } else {
                                str10 = str6;
                                mediaDetails4 = mediaDetails2;
                                r0 = r47;
                                r7 = r54;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(traktProvider2.traktApiUrl);
                                sb3.append("/shows/");
                                if (mediaDetails4 != null) {
                                    trakt = null;
                                } else {
                                    trakt = null;
                                }
                                sb3.append(trakt);
                                sb3.append("/seasons?extended=full,images,episodes");
                                String string5 = sb3.toString();
                                c07392.L$0 = traktProvider2;
                                c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                                c07392.L$2 = data;
                                c07392.L$3 = mediaDetails4;
                                c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                                str11 = str3;
                                c07392.L$5 = str11;
                                c07392.L$6 = str4;
                                c07392.L$7 = str5;
                                c07392.L$8 = SpillingKt.nullOutSpilledVariable(str10);
                                c07392.L$9 = list;
                                c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                                c07392.L$11 = arrayList3;
                                c07392.L$12 = str9;
                                c07392.I$0 = i2;
                                c07392.I$1 = r0 == true ? 1 : 0;
                                c07392.I$2 = r7 == true ? 1 : 0;
                                c07392.Z$0 = zAreEqual;
                                c07392.label = 4;
                                api = traktProvider2.getApi(string5, c07392);
                                if (api != coroutine_suspended) {
                                    z = zAreEqual;
                                    str12 = str10;
                                    mediaDetails5 = mediaDetails4;
                                    str13 = str11;
                                    str14 = str8;
                                    str15 = str9;
                                    list2 = arrayList3;
                                    list3 = list;
                                    str16 = str5;
                                    i3 = r0 == true ? 1 : 0;
                                    obj3 = api;
                                    str17 = str7;
                                    str18 = str4;
                                    r8 = r7;
                                }
                            }
                            return coroutine_suspended;
                        }
                        if (i4 == 3) {
                            boolean z6 = c07392.Z$0;
                            int i5 = c07392.I$2;
                            int i6 = c07392.I$1;
                            int i7 = c07392.I$0;
                            ResultKt.throwOnFailure(obj5);
                            return obj5;
                        }
                        if (i4 != 4) {
                            if (i4 != 5) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            boolean z7 = c07392.Z$0;
                            int i8 = c07392.I$2;
                            int i9 = c07392.I$1;
                            int i10 = c07392.I$0;
                            ResultKt.throwOnFailure(obj5);
                            return obj5;
                        }
                        boolean z8 = c07392.Z$0;
                        int i11 = c07392.I$2;
                        i3 = c07392.I$1;
                        i2 = c07392.I$0;
                        String str35 = (String) c07392.L$12;
                        list2 = (List) c07392.L$11;
                        String str36 = (String) c07392.L$10;
                        list3 = (List) c07392.L$9;
                        str12 = (String) c07392.L$8;
                        String str37 = (String) c07392.L$7;
                        String str38 = (String) c07392.L$6;
                        str13 = (String) c07392.L$5;
                        String str39 = (String) c07392.L$4;
                        MediaDetails mediaDetails9 = (MediaDetails) c07392.L$3;
                        Data data3 = (Data) c07392.L$2;
                        String str40 = (String) c07392.L$1;
                        TraktProvider traktProvider5 = (TraktProvider) c07392.L$0;
                        ResultKt.throwOnFailure(obj5);
                        r8 = i11;
                        str24 = str40;
                        str15 = str35;
                        mediaDetails5 = mediaDetails9;
                        z = z8;
                        str16 = str37;
                        str14 = str36;
                        data = data3;
                        str17 = str39;
                        str18 = str38;
                        traktProvider2 = traktProvider5;
                        obj3 = obj5;
                    }
                    objM8936constructorimpl4 = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.Seasons")));
                } catch (Throwable th5) {
                    th = th5;
                    Result.Companion companion9 = Result.INSTANCE;
                    objM8936constructorimpl4 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                Result.Companion companion10 = Result.INSTANCE;
                str21 = str12;
            } catch (Throwable th6) {
                th = th6;
                str21 = str12;
            }
            str20 = (String) obj3;
            String str41 = str24;
            arrayList4 = new ArrayList();
            AppUtils appUtils5 = AppUtils.INSTANCE;
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl4) == null) {
                list4 = list2;
                str22 = str13;
                obj4 = null;
            } else {
                try {
                    Result.Companion companion11 = Result.INSTANCE;
                    list4 = list2;
                    str22 = str13;
                    obj4 = null;
                    try {
                        objM8936constructorimpl4 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    } catch (Throwable th7) {
                        th = th7;
                        Result.Companion companion12 = Result.INSTANCE;
                        objM8936constructorimpl4 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                } catch (Throwable th8) {
                    th = th8;
                    list4 = list2;
                    str22 = str13;
                    obj4 = null;
                }
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl4)) {
                objM8936constructorimpl4 = obj4;
            }
            kSerializer2 = (KSerializer) objM8936constructorimpl4;
            if (kSerializer2 != null) {
                try {
                    objDecodeFromString4 = MainAPIKt.getJson().decodeFromString(kSerializer2, str20);
                } catch (SerializationException e3) {
                    ArchComponentExtKt.logError(e3);
                    objDecodeFromString4 = MainAPIKt.getMapper().readValue(str20, new TypeReference<List<? extends Seasons>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$4
                    });
                } catch (Throwable unused3) {
                    objDecodeFromString4 = MainAPIKt.getMapper().readValue(str20, new TypeReference<List<? extends Seasons>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$4
                    });
                }
            } else {
                objDecodeFromString4 = MainAPIKt.getMapper().readValue(str20, new TypeReference<List<? extends Seasons>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$4
                });
            }
            list5 = (List) objDecodeFromString4;
            objectRef = new Ref.ObjectRef();
            it2 = list5.iterator();
            while (it2.hasNext()) {
                episodes = ((Seasons) it2.next()).getEpisodes();
                if (episodes != null) {
                    List<TraktEpisode> list9 = episodes;
                    list6 = list5;
                    arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list9, 10));
                    it3 = list9.iterator();
                    while (it3.hasNext()) {
                        final TraktEpisode traktEpisode = (TraktEpisode) it3.next();
                        AppUtils appUtils6 = AppUtils.INSTANCE;
                        if (mediaDetails5 != null || (ids18 = mediaDetails5.getIds()) == null) {
                            tmdb3 = null;
                        } else {
                            tmdb3 = ids18.getTmdb();
                        }
                        if (mediaDetails5 != null || (ids17 = mediaDetails5.getIds()) == null) {
                            trakt4 = null;
                        } else {
                            trakt4 = ids17.getTrakt();
                        }
                        if (mediaDetails5 != null || (ids16 = mediaDetails5.getIds()) == null) {
                            slug2 = null;
                        } else {
                            slug2 = ids16.getSlug();
                        }
                        if (mediaDetails5 != null || (ids15 = mediaDetails5.getIds()) == null) {
                            tmdb4 = null;
                        } else {
                            tmdb4 = ids15.getTmdb();
                        }
                        if (mediaDetails5 != null || (ids14 = mediaDetails5.getIds()) == null) {
                            imdb2 = null;
                        } else {
                            imdb2 = ids14.getImdb();
                        }
                        String strValueOf3 = String.valueOf(imdb2);
                        if (mediaDetails5 != null || (ids13 = mediaDetails5.getIds()) == null) {
                            tvdb2 = null;
                        } else {
                            tvdb2 = ids13.getTvdb();
                        }
                        if (mediaDetails5 != null || (ids12 = mediaDetails5.getIds()) == null) {
                            tvrage2 = null;
                        } else {
                            tvrage2 = ids12.getTvrage();
                        }
                        String strValueOf4 = String.valueOf(data.getType());
                        Integer season = traktEpisode.getSeason();
                        Integer number = traktEpisode.getNumber();
                        if (mediaDetails5 != null) {
                            title5 = mediaDetails5.getTitle();
                        } else {
                            title5 = null;
                        }
                        if (mediaDetails5 != null) {
                            year2 = mediaDetails5.getYear();
                        } else {
                            year2 = null;
                        }
                        if (mediaDetails5 != null) {
                            title6 = mediaDetails5.getTitle();
                        } else {
                            title6 = null;
                        }
                        if (i3 != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (mediaDetails5 != null) {
                            year3 = mediaDetails5.getYear();
                        } else {
                            year3 = null;
                        }
                        Integer numBoxInt = Boxing.boxInt(list6.size());
                        String title7 = traktEpisode.getTitle();
                        String firstAired4 = traktEpisode.getFirstAired();
                        String firstAired5 = traktEpisode.getFirstAired();
                        if (r8 != 0) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (i2 != 0) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        arrayList5.add(Boxing.boxBoolean(arrayList4.add(MainAPIKt.newEpisode(traktProvider2, AppUtils.INSTANCE.toJson(appUtils6.toJson(new LinkData(tmdb3, trakt4, slug2, tmdb4, strValueOf3, tvdb2, tvrage2, strValueOf4, season, number, null, null, title5, year2, title6, z3, year3, numBoxInt, title7, null, firstAired4, firstAired5, z4, z, z5, 527360, null))), new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj6) {
                                return TraktProvider.load$lambda$2$0$0(traktEpisode, traktProvider2, objectRef, (Episode) obj6);
                            }
                        }))));
                        it3 = it3;
                        list3 = list3;
                        str18 = str18;
                    }
                    list7 = list3;
                    str23 = str18;
                } else {
                    list6 = list5;
                    list7 = list3;
                    str23 = str18;
                }
                z = z;
                it2 = it2;
                list5 = list6;
                list3 = list7;
                str18 = str23;
                str20 = str20;
            }
            List list10 = list5;
            String str42 = str20;
            List list11 = list3;
            String str43 = str18;
            boolean z9 = z;
            TraktProvider traktProvider6 = traktProvider2;
            if (mediaDetails5 != null) {
                title4 = mediaDetails5.getTitle();
            } else {
                title4 = null;
            }
            Intrinsics.checkNotNull(title4);
            String json8 = AppUtils.INSTANCE.toJson(data);
            if (i3 != 0) {
                tvType2 = TvType.Anime;
            } else {
                tvType2 = TvType.TvSeries;
            }
            List list12 = list4;
            if (i3 != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            String str44 = str16;
            TraktProvider traktProvider7 = traktProvider2;
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(str15, mediaDetails5, z2, arrayList4, str22, traktProvider7, list12, list11, objectRef, str43, str44, null);
            c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider7);
            c07392.L$1 = SpillingKt.nullOutSpilledVariable(str41);
            c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
            c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails5);
            c07392.L$4 = SpillingKt.nullOutSpilledVariable(str17);
            c07392.L$5 = SpillingKt.nullOutSpilledVariable(str22);
            c07392.L$6 = SpillingKt.nullOutSpilledVariable(str43);
            c07392.L$7 = SpillingKt.nullOutSpilledVariable(str44);
            c07392.L$8 = SpillingKt.nullOutSpilledVariable(str21);
            c07392.L$9 = SpillingKt.nullOutSpilledVariable(list11);
            c07392.L$10 = SpillingKt.nullOutSpilledVariable(str14);
            c07392.L$11 = SpillingKt.nullOutSpilledVariable(list12);
            c07392.L$12 = SpillingKt.nullOutSpilledVariable(str15);
            c07392.L$13 = SpillingKt.nullOutSpilledVariable(str42);
            c07392.L$14 = SpillingKt.nullOutSpilledVariable(arrayList4);
            c07392.L$15 = SpillingKt.nullOutSpilledVariable(list10);
            c07392.L$16 = SpillingKt.nullOutSpilledVariable(objectRef);
            c07392.I$0 = i2;
            c07392.I$1 = i3;
            c07392.I$2 = r8;
            c07392.Z$0 = z9;
            c07392.label = 5;
            objNewTvSeriesLoadResponse = MainAPIKt.newTvSeriesLoadResponse(traktProvider6, title4, json8, tvType2, arrayList4, anonymousClass4, c07392);
            if (objNewTvSeriesLoadResponse != coroutine_suspended) {
                return coroutine_suspended;
            }
            return objNewTvSeriesLoadResponse;
            Result.Companion companion13 = Result.INSTANCE;
            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.People"));
        } catch (Throwable th9) {
            Result.Companion companion14 = Result.INSTANCE;
            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th9));
        }
        String str45 = (String) obj;
        AppUtils appUtils7 = AppUtils.INSTANCE;
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) != null) {
            try {
                Result.Companion companion15 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(People.class), null, 2, null));
            } catch (Throwable th10) {
                Result.Companion companion16 = Result.INSTANCE;
                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th10));
            }
        }
        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
            objM8936constructorimpl2 = null;
        }
        KSerializer kSerializer4 = (KSerializer) objM8936constructorimpl2;
        if (kSerializer4 != null) {
            try {
                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer4, str45);
            } catch (SerializationException e4) {
                ArchComponentExtKt.logError(e4);
                objDecodeFromString2 = MainAPIKt.getMapper().readValue(str45, new TypeReference<People>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$2
                });
            } catch (Throwable unused4) {
                objDecodeFromString2 = MainAPIKt.getMapper().readValue(str45, new TypeReference<People>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$2
                });
            }
        } else {
            objDecodeFromString2 = MainAPIKt.getMapper().readValue(str45, new TypeReference<People>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$2
            });
        }
        List<Cast> cast = ((People) objDecodeFromString2).getCast();
        if (cast != null) {
            List<Cast> list13 = cast;
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list13, 10));
            for (Iterator it4 = list13.iterator(); it4.hasNext(); it4 = it4) {
                Cast cast2 = (Cast) it4.next();
                Person person = cast2.getPerson();
                String name = person != null ? person.getName() : null;
                Intrinsics.checkNotNull(name);
                Images images4 = cast2.getPerson().getImages();
                arrayList6.add(new ActorData(new Actor(name, traktProvider3.fixPath((images4 == null || (headshot = images4.getHeadshot()) == null) ? null : (String) CollectionsKt.firstOrNull((List) headshot))), null, cast2.getCharacter(), null, 10, null));
            }
            arrayList = arrayList6;
        } else {
            arrayList = null;
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append(traktProvider3.traktApiUrl);
        sb4.append('/');
        sb4.append(str2);
        sb4.append('/');
        sb4.append((mediaDetails == null || (ids2 = mediaDetails.getIds()) == null) ? null : ids2.getTrakt());
        sb4.append("/related?extended=full,images&limit=20");
        String string6 = sb4.toString();
        c07392.L$0 = traktProvider3;
        c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
        c07392.L$2 = data;
        c07392.L$3 = mediaDetails;
        c07392.L$4 = SpillingKt.nullOutSpilledVariable(str2);
        c07392.L$5 = strFixPath;
        c07392.L$6 = strFixPath2;
        c07392.L$7 = strFixPath3;
        c07392.L$8 = SpillingKt.nullOutSpilledVariable(str45);
        c07392.L$9 = arrayList;
        c07392.label = 2;
        Object api3 = traktProvider3.getApi(string6, c07392);
        if (api3 != coroutine_suspended) {
            list = arrayList;
            str3 = strFixPath;
            obj2 = api3;
            str4 = strFixPath2;
            str5 = strFixPath3;
            mediaDetails2 = mediaDetails;
            str6 = str45;
            traktProvider2 = traktProvider3;
            str7 = str2;
            str8 = (String) obj2;
            AppUtils appUtils8 = AppUtils.INSTANCE;
            Result.Companion companion17 = Result.INSTANCE;
            objM8936constructorimpl3 = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.MediaDetails")));
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) != null) {
                Result.Companion companion18 = Result.INSTANCE;
                objM8936constructorimpl3 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                objM8936constructorimpl3 = null;
            }
            kSerializer = (KSerializer) objM8936constructorimpl3;
            if (kSerializer != null) {
                objDecodeFromString3 = MainAPIKt.getJson().decodeFromString(kSerializer, str8);
            } else {
                objDecodeFromString3 = MainAPIKt.getMapper().readValue(str8, new TypeReference<List<? extends MediaDetails>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$3
                });
            }
            Iterable iterable2 = (Iterable) objDecodeFromString3;
            arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            it = iterable2.iterator();
            while (it.hasNext()) {
                arrayList2.add(traktProvider2.toSearchResponse((MediaDetails) it.next()));
            }
            arrayList3 = arrayList2;
            if (mediaDetails2 == null) {
                i = 1;
                if (mediaDetails2 != null) {
                }
                if (i2 == 0) {
                    r47 = 0;
                } else {
                    r47 = 0;
                }
                if (r47 != 0) {
                    r54 = 0;
                } else {
                    if (mediaDetails2 != null) {
                        language = mediaDetails2.getLanguage();
                    } else {
                        language = null;
                    }
                    if (!Intrinsics.areEqual(language, "zh")) {
                        if (mediaDetails2 != null) {
                            language2 = mediaDetails2.getLanguage();
                        } else {
                            language2 = null;
                        }
                        if (!Intrinsics.areEqual(language2, "ko")) {
                            r54 = 0;
                        }
                    }
                    r54 = i;
                }
                if (mediaDetails2 != null) {
                    country = mediaDetails2.getCountry();
                } else {
                    country = null;
                }
                zAreEqual = Intrinsics.areEqual(country, "in");
                mediaDetails3 = data.getMediaDetails();
                if (mediaDetails3 != null) {
                    json = AppUtils.INSTANCE.toJson(data);
                } else {
                    json = AppUtils.INSTANCE.toJson(data);
                }
                str9 = json;
                if (data.getType() == TvType.Movie) {
                    AppUtils appUtils9 = AppUtils.INSTANCE;
                    if (mediaDetails2 != null) {
                        tmdb = null;
                    } else {
                        tmdb = null;
                    }
                    if (mediaDetails2 != null) {
                        trakt2 = null;
                    } else {
                        trakt2 = null;
                    }
                    if (mediaDetails2 != null) {
                        slug = null;
                    } else {
                        slug = null;
                    }
                    if (mediaDetails2 != null) {
                        tmdb2 = null;
                    } else {
                        tmdb2 = null;
                    }
                    if (mediaDetails2 != null) {
                        imdb = null;
                    } else {
                        imdb = null;
                    }
                    String strValueOf5 = String.valueOf(imdb);
                    if (mediaDetails2 != null) {
                        tvdb = null;
                    } else {
                        tvdb = null;
                    }
                    if (mediaDetails2 != null) {
                        tvrage = null;
                    } else {
                        tvrage = null;
                    }
                    String string7 = data.getType().toString();
                    if (mediaDetails2 != null) {
                        title = mediaDetails2.getTitle();
                    } else {
                        title = null;
                    }
                    if (mediaDetails2 != null) {
                        year = mediaDetails2.getYear();
                    } else {
                        year = null;
                    }
                    if (mediaDetails2 != null) {
                        title2 = mediaDetails2.getTitle();
                    } else {
                        title2 = null;
                    }
                    if (mediaDetails2 == null) {
                        if (mediaDetails2 != null) {
                            String firstAired6 = mediaDetails2.getFirstAired();
                            str19 = firstAired6;
                        } else {
                            str19 = null;
                        }
                    } else if (mediaDetails2 != null) {
                        String firstAired7 = mediaDetails2.getFirstAired();
                        str19 = firstAired7;
                    } else {
                        str19 = null;
                    }
                    ?? r11 = r54;
                    String json9 = appUtils9.toJson(new LinkData(tmdb, trakt2, slug, tmdb2, strValueOf5, tvdb, tvrage, string7, null, null, null, null, title, year, title2, r47, null, null, null, null, null, str19, r54, zAreEqual, false, 18812672, null));
                    if (mediaDetails2 != null) {
                        title3 = mediaDetails2.getTitle();
                    } else {
                        title3 = null;
                    }
                    Intrinsics.checkNotNull(title3);
                    String json10 = AppUtils.INSTANCE.toJson(data);
                    String json11 = AppUtils.INSTANCE.toJson(json9);
                    if (r47 != 0) {
                        tvType = TvType.AnimeMovie;
                    } else {
                        tvType = TvType.Movie;
                    }
                    ?? r27 = r47;
                    AnonymousClass2 anonymousClass5 = new AnonymousClass2(str9, mediaDetails2, r27, str3, arrayList3, list, str4, str5, null);
                    c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider2);
                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                    c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                    c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails2);
                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                    c07392.L$5 = SpillingKt.nullOutSpilledVariable(str3);
                    c07392.L$6 = SpillingKt.nullOutSpilledVariable(str4);
                    c07392.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str6);
                    c07392.L$9 = SpillingKt.nullOutSpilledVariable(list);
                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                    c07392.L$11 = SpillingKt.nullOutSpilledVariable(arrayList3);
                    c07392.L$12 = SpillingKt.nullOutSpilledVariable(str9);
                    c07392.L$13 = SpillingKt.nullOutSpilledVariable(json9);
                    c07392.I$0 = i2;
                    c07392.I$1 = r27 == true ? 1 : 0;
                    c07392.I$2 = r11 == true ? 1 : 0;
                    c07392.Z$0 = zAreEqual;
                    c07392.label = 3;
                    objNewMovieLoadResponse = MainAPIKt.newMovieLoadResponse((MainAPI) traktProvider2, title3, json10, tvType, json11, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) anonymousClass5, (Continuation<? super MovieLoadResponse>) c07392);
                    if (objNewMovieLoadResponse != coroutine_suspended) {
                        return objNewMovieLoadResponse;
                    }
                } else {
                    str10 = str6;
                    mediaDetails4 = mediaDetails2;
                    r0 = r47;
                    r7 = r54;
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(traktProvider2.traktApiUrl);
                    sb5.append("/shows/");
                    if (mediaDetails4 != null) {
                        trakt = null;
                    } else {
                        trakt = null;
                    }
                    sb5.append(trakt);
                    sb5.append("/seasons?extended=full,images,episodes");
                    String string8 = sb5.toString();
                    c07392.L$0 = traktProvider2;
                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                    c07392.L$2 = data;
                    c07392.L$3 = mediaDetails4;
                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                    str11 = str3;
                    c07392.L$5 = str11;
                    c07392.L$6 = str4;
                    c07392.L$7 = str5;
                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str10);
                    c07392.L$9 = list;
                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                    c07392.L$11 = arrayList3;
                    c07392.L$12 = str9;
                    c07392.I$0 = i2;
                    c07392.I$1 = r0 == true ? 1 : 0;
                    c07392.I$2 = r7 == true ? 1 : 0;
                    c07392.Z$0 = zAreEqual;
                    c07392.label = 4;
                    api = traktProvider2.getApi(string8, c07392);
                    if (api != coroutine_suspended) {
                        z = zAreEqual;
                        str12 = str10;
                        mediaDetails5 = mediaDetails4;
                        str13 = str11;
                        str14 = str8;
                        str15 = str9;
                        list2 = arrayList3;
                        list3 = list;
                        str16 = str5;
                        i3 = r0 == true ? 1 : 0;
                        obj3 = api;
                        str17 = str7;
                        str18 = str4;
                        r8 = r7;
                        str20 = (String) obj3;
                        String str46 = str24;
                        arrayList4 = new ArrayList();
                        AppUtils appUtils10 = AppUtils.INSTANCE;
                        Result.Companion companion19 = Result.INSTANCE;
                        str21 = str12;
                        objM8936constructorimpl4 = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.Seasons")));
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl4) == null) {
                            list4 = list2;
                            str22 = str13;
                            obj4 = null;
                        } else {
                            Result.Companion companion110 = Result.INSTANCE;
                            list4 = list2;
                            str22 = str13;
                            obj4 = null;
                            objM8936constructorimpl4 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl4)) {
                            objM8936constructorimpl4 = obj4;
                        }
                        kSerializer2 = (KSerializer) objM8936constructorimpl4;
                        if (kSerializer2 != null) {
                            objDecodeFromString4 = MainAPIKt.getJson().decodeFromString(kSerializer2, str20);
                        } else {
                            objDecodeFromString4 = MainAPIKt.getMapper().readValue(str20, new TypeReference<List<? extends Seasons>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$4
                            });
                        }
                        list5 = (List) objDecodeFromString4;
                        objectRef = new Ref.ObjectRef();
                        it2 = list5.iterator();
                        while (it2.hasNext()) {
                            episodes = ((Seasons) it2.next()).getEpisodes();
                            if (episodes != null) {
                                List<TraktEpisode> list14 = episodes;
                                list6 = list5;
                                arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list14, 10));
                                it3 = list14.iterator();
                                while (it3.hasNext()) {
                                    final TraktEpisode traktEpisode2 = (TraktEpisode) it3.next();
                                    AppUtils appUtils11 = AppUtils.INSTANCE;
                                    if (mediaDetails5 != null) {
                                        tmdb3 = null;
                                    } else {
                                        tmdb3 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        trakt4 = null;
                                    } else {
                                        trakt4 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        slug2 = null;
                                    } else {
                                        slug2 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        tmdb4 = null;
                                    } else {
                                        tmdb4 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        imdb2 = null;
                                    } else {
                                        imdb2 = null;
                                    }
                                    String strValueOf6 = String.valueOf(imdb2);
                                    if (mediaDetails5 != null) {
                                        tvdb2 = null;
                                    } else {
                                        tvdb2 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        tvrage2 = null;
                                    } else {
                                        tvrage2 = null;
                                    }
                                    String strValueOf7 = String.valueOf(data.getType());
                                    Integer season2 = traktEpisode2.getSeason();
                                    Integer number2 = traktEpisode2.getNumber();
                                    if (mediaDetails5 != null) {
                                        title5 = mediaDetails5.getTitle();
                                    } else {
                                        title5 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        year2 = mediaDetails5.getYear();
                                    } else {
                                        year2 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        title6 = mediaDetails5.getTitle();
                                    } else {
                                        title6 = null;
                                    }
                                    if (i3 != 0) {
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                    if (mediaDetails5 != null) {
                                        year3 = mediaDetails5.getYear();
                                    } else {
                                        year3 = null;
                                    }
                                    Integer numBoxInt2 = Boxing.boxInt(list6.size());
                                    String title8 = traktEpisode2.getTitle();
                                    String firstAired8 = traktEpisode2.getFirstAired();
                                    String firstAired9 = traktEpisode2.getFirstAired();
                                    if (r8 != 0) {
                                        z4 = true;
                                    } else {
                                        z4 = false;
                                    }
                                    if (i2 != 0) {
                                        z5 = true;
                                    } else {
                                        z5 = false;
                                    }
                                    arrayList5.add(Boxing.boxBoolean(arrayList4.add(MainAPIKt.newEpisode(traktProvider2, AppUtils.INSTANCE.toJson(appUtils11.toJson(new LinkData(tmdb3, trakt4, slug2, tmdb4, strValueOf6, tvdb2, tvrage2, strValueOf7, season2, number2, null, null, title5, year2, title6, z3, year3, numBoxInt2, title8, null, firstAired8, firstAired9, z4, z, z5, 527360, null))), new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj6) {
                                            return TraktProvider.load$lambda$2$0$0(traktEpisode2, traktProvider2, objectRef, (Episode) obj6);
                                        }
                                    }))));
                                    it3 = it3;
                                    list3 = list3;
                                    str18 = str18;
                                }
                                list7 = list3;
                                str23 = str18;
                            } else {
                                list6 = list5;
                                list7 = list3;
                                str23 = str18;
                            }
                            z = z;
                            it2 = it2;
                            list5 = list6;
                            list3 = list7;
                            str18 = str23;
                            str20 = str20;
                        }
                        List list15 = list5;
                        String str47 = str20;
                        List list16 = list3;
                        String str48 = str18;
                        boolean z10 = z;
                        TraktProvider traktProvider8 = traktProvider2;
                        if (mediaDetails5 != null) {
                            title4 = mediaDetails5.getTitle();
                        } else {
                            title4 = null;
                        }
                        Intrinsics.checkNotNull(title4);
                        String json12 = AppUtils.INSTANCE.toJson(data);
                        if (i3 != 0) {
                            tvType2 = TvType.Anime;
                        } else {
                            tvType2 = TvType.TvSeries;
                        }
                        List list17 = list4;
                        if (i3 != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        String str49 = str16;
                        TraktProvider traktProvider9 = traktProvider2;
                        AnonymousClass4 anonymousClass6 = new AnonymousClass4(str15, mediaDetails5, z2, arrayList4, str22, traktProvider9, list17, list16, objectRef, str48, str49, null);
                        c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider9);
                        c07392.L$1 = SpillingKt.nullOutSpilledVariable(str46);
                        c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                        c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails5);
                        c07392.L$4 = SpillingKt.nullOutSpilledVariable(str17);
                        c07392.L$5 = SpillingKt.nullOutSpilledVariable(str22);
                        c07392.L$6 = SpillingKt.nullOutSpilledVariable(str48);
                        c07392.L$7 = SpillingKt.nullOutSpilledVariable(str49);
                        c07392.L$8 = SpillingKt.nullOutSpilledVariable(str21);
                        c07392.L$9 = SpillingKt.nullOutSpilledVariable(list16);
                        c07392.L$10 = SpillingKt.nullOutSpilledVariable(str14);
                        c07392.L$11 = SpillingKt.nullOutSpilledVariable(list17);
                        c07392.L$12 = SpillingKt.nullOutSpilledVariable(str15);
                        c07392.L$13 = SpillingKt.nullOutSpilledVariable(str47);
                        c07392.L$14 = SpillingKt.nullOutSpilledVariable(arrayList4);
                        c07392.L$15 = SpillingKt.nullOutSpilledVariable(list15);
                        c07392.L$16 = SpillingKt.nullOutSpilledVariable(objectRef);
                        c07392.I$0 = i2;
                        c07392.I$1 = i3;
                        c07392.I$2 = r8;
                        c07392.Z$0 = z10;
                        c07392.label = 5;
                        objNewTvSeriesLoadResponse = MainAPIKt.newTvSeriesLoadResponse(traktProvider8, title4, json12, tvType2, arrayList4, anonymousClass6, c07392);
                        if (objNewTvSeriesLoadResponse != coroutine_suspended) {
                            return objNewTvSeriesLoadResponse;
                        }
                    }
                }
            } else {
                i = 1;
                if (mediaDetails2 != null) {
                }
                if (i2 == 0) {
                    r47 = 0;
                } else {
                    r47 = 0;
                }
                if (r47 != 0) {
                    r54 = 0;
                } else {
                    if (mediaDetails2 != null) {
                        language = mediaDetails2.getLanguage();
                    } else {
                        language = null;
                    }
                    if (!Intrinsics.areEqual(language, "zh")) {
                        if (mediaDetails2 != null) {
                            language2 = mediaDetails2.getLanguage();
                        } else {
                            language2 = null;
                        }
                        if (!Intrinsics.areEqual(language2, "ko")) {
                            r54 = 0;
                        }
                    }
                    r54 = i;
                }
                if (mediaDetails2 != null) {
                    country = mediaDetails2.getCountry();
                } else {
                    country = null;
                }
                zAreEqual = Intrinsics.areEqual(country, "in");
                mediaDetails3 = data.getMediaDetails();
                if (mediaDetails3 != null) {
                    json = AppUtils.INSTANCE.toJson(data);
                } else {
                    json = AppUtils.INSTANCE.toJson(data);
                }
                str9 = json;
                if (data.getType() == TvType.Movie) {
                    AppUtils appUtils12 = AppUtils.INSTANCE;
                    if (mediaDetails2 != null) {
                        tmdb = null;
                    } else {
                        tmdb = null;
                    }
                    if (mediaDetails2 != null) {
                        trakt2 = null;
                    } else {
                        trakt2 = null;
                    }
                    if (mediaDetails2 != null) {
                        slug = null;
                    } else {
                        slug = null;
                    }
                    if (mediaDetails2 != null) {
                        tmdb2 = null;
                    } else {
                        tmdb2 = null;
                    }
                    if (mediaDetails2 != null) {
                        imdb = null;
                    } else {
                        imdb = null;
                    }
                    String strValueOf8 = String.valueOf(imdb);
                    if (mediaDetails2 != null) {
                        tvdb = null;
                    } else {
                        tvdb = null;
                    }
                    if (mediaDetails2 != null) {
                        tvrage = null;
                    } else {
                        tvrage = null;
                    }
                    String string9 = data.getType().toString();
                    if (mediaDetails2 != null) {
                        title = mediaDetails2.getTitle();
                    } else {
                        title = null;
                    }
                    if (mediaDetails2 != null) {
                        year = mediaDetails2.getYear();
                    } else {
                        year = null;
                    }
                    if (mediaDetails2 != null) {
                        title2 = mediaDetails2.getTitle();
                    } else {
                        title2 = null;
                    }
                    if (mediaDetails2 == null) {
                        if (mediaDetails2 != null) {
                            String firstAired10 = mediaDetails2.getFirstAired();
                            str19 = firstAired10;
                        } else {
                            str19 = null;
                        }
                    } else if (mediaDetails2 != null) {
                        String firstAired11 = mediaDetails2.getFirstAired();
                        str19 = firstAired11;
                    } else {
                        str19 = null;
                    }
                    ?? r12 = r54;
                    String json13 = appUtils12.toJson(new LinkData(tmdb, trakt2, slug, tmdb2, strValueOf8, tvdb, tvrage, string9, null, null, null, null, title, year, title2, r47, null, null, null, null, null, str19, r54, zAreEqual, false, 18812672, null));
                    if (mediaDetails2 != null) {
                        title3 = mediaDetails2.getTitle();
                    } else {
                        title3 = null;
                    }
                    Intrinsics.checkNotNull(title3);
                    String json14 = AppUtils.INSTANCE.toJson(data);
                    String json15 = AppUtils.INSTANCE.toJson(json13);
                    if (r47 != 0) {
                        tvType = TvType.AnimeMovie;
                    } else {
                        tvType = TvType.Movie;
                    }
                    ?? r28 = r47;
                    AnonymousClass2 anonymousClass7 = new AnonymousClass2(str9, mediaDetails2, r28, str3, arrayList3, list, str4, str5, null);
                    c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider2);
                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                    c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                    c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails2);
                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                    c07392.L$5 = SpillingKt.nullOutSpilledVariable(str3);
                    c07392.L$6 = SpillingKt.nullOutSpilledVariable(str4);
                    c07392.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str6);
                    c07392.L$9 = SpillingKt.nullOutSpilledVariable(list);
                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                    c07392.L$11 = SpillingKt.nullOutSpilledVariable(arrayList3);
                    c07392.L$12 = SpillingKt.nullOutSpilledVariable(str9);
                    c07392.L$13 = SpillingKt.nullOutSpilledVariable(json13);
                    c07392.I$0 = i2;
                    c07392.I$1 = r28 == true ? 1 : 0;
                    c07392.I$2 = r12 == true ? 1 : 0;
                    c07392.Z$0 = zAreEqual;
                    c07392.label = 3;
                    objNewMovieLoadResponse = MainAPIKt.newMovieLoadResponse((MainAPI) traktProvider2, title3, json14, tvType, json15, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) anonymousClass7, (Continuation<? super MovieLoadResponse>) c07392);
                    if (objNewMovieLoadResponse != coroutine_suspended) {
                        return objNewMovieLoadResponse;
                    }
                } else {
                    str10 = str6;
                    mediaDetails4 = mediaDetails2;
                    r0 = r47;
                    r7 = r54;
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(traktProvider2.traktApiUrl);
                    sb6.append("/shows/");
                    if (mediaDetails4 != null) {
                        trakt = null;
                    } else {
                        trakt = null;
                    }
                    sb6.append(trakt);
                    sb6.append("/seasons?extended=full,images,episodes");
                    String string10 = sb6.toString();
                    c07392.L$0 = traktProvider2;
                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                    c07392.L$2 = data;
                    c07392.L$3 = mediaDetails4;
                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                    str11 = str3;
                    c07392.L$5 = str11;
                    c07392.L$6 = str4;
                    c07392.L$7 = str5;
                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str10);
                    c07392.L$9 = list;
                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                    c07392.L$11 = arrayList3;
                    c07392.L$12 = str9;
                    c07392.I$0 = i2;
                    c07392.I$1 = r0 == true ? 1 : 0;
                    c07392.I$2 = r7 == true ? 1 : 0;
                    c07392.Z$0 = zAreEqual;
                    c07392.label = 4;
                    api = traktProvider2.getApi(string10, c07392);
                    if (api != coroutine_suspended) {
                        z = zAreEqual;
                        str12 = str10;
                        mediaDetails5 = mediaDetails4;
                        str13 = str11;
                        str14 = str8;
                        str15 = str9;
                        list2 = arrayList3;
                        list3 = list;
                        str16 = str5;
                        i3 = r0 == true ? 1 : 0;
                        obj3 = api;
                        str17 = str7;
                        str18 = str4;
                        r8 = r7;
                        str20 = (String) obj3;
                        String str410 = str24;
                        arrayList4 = new ArrayList();
                        AppUtils appUtils13 = AppUtils.INSTANCE;
                        Result.Companion companion111 = Result.INSTANCE;
                        str21 = str12;
                        objM8936constructorimpl4 = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.Seasons")));
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl4) == null) {
                            list4 = list2;
                            str22 = str13;
                            obj4 = null;
                        } else {
                            Result.Companion companion112 = Result.INSTANCE;
                            list4 = list2;
                            str22 = str13;
                            obj4 = null;
                            objM8936constructorimpl4 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl4)) {
                            objM8936constructorimpl4 = obj4;
                        }
                        kSerializer2 = (KSerializer) objM8936constructorimpl4;
                        if (kSerializer2 != null) {
                            objDecodeFromString4 = MainAPIKt.getJson().decodeFromString(kSerializer2, str20);
                        } else {
                            objDecodeFromString4 = MainAPIKt.getMapper().readValue(str20, new TypeReference<List<? extends Seasons>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$4
                            });
                        }
                        list5 = (List) objDecodeFromString4;
                        objectRef = new Ref.ObjectRef();
                        it2 = list5.iterator();
                        while (it2.hasNext()) {
                            episodes = ((Seasons) it2.next()).getEpisodes();
                            if (episodes != null) {
                                List<TraktEpisode> list18 = episodes;
                                list6 = list5;
                                arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list18, 10));
                                it3 = list18.iterator();
                                while (it3.hasNext()) {
                                    final TraktEpisode traktEpisode3 = (TraktEpisode) it3.next();
                                    AppUtils appUtils14 = AppUtils.INSTANCE;
                                    if (mediaDetails5 != null) {
                                        tmdb3 = null;
                                    } else {
                                        tmdb3 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        trakt4 = null;
                                    } else {
                                        trakt4 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        slug2 = null;
                                    } else {
                                        slug2 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        tmdb4 = null;
                                    } else {
                                        tmdb4 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        imdb2 = null;
                                    } else {
                                        imdb2 = null;
                                    }
                                    String strValueOf9 = String.valueOf(imdb2);
                                    if (mediaDetails5 != null) {
                                        tvdb2 = null;
                                    } else {
                                        tvdb2 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        tvrage2 = null;
                                    } else {
                                        tvrage2 = null;
                                    }
                                    String strValueOf10 = String.valueOf(data.getType());
                                    Integer season3 = traktEpisode3.getSeason();
                                    Integer number3 = traktEpisode3.getNumber();
                                    if (mediaDetails5 != null) {
                                        title5 = mediaDetails5.getTitle();
                                    } else {
                                        title5 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        year2 = mediaDetails5.getYear();
                                    } else {
                                        year2 = null;
                                    }
                                    if (mediaDetails5 != null) {
                                        title6 = mediaDetails5.getTitle();
                                    } else {
                                        title6 = null;
                                    }
                                    if (i3 != 0) {
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                    if (mediaDetails5 != null) {
                                        year3 = mediaDetails5.getYear();
                                    } else {
                                        year3 = null;
                                    }
                                    Integer numBoxInt3 = Boxing.boxInt(list6.size());
                                    String title9 = traktEpisode3.getTitle();
                                    String firstAired12 = traktEpisode3.getFirstAired();
                                    String firstAired13 = traktEpisode3.getFirstAired();
                                    if (r8 != 0) {
                                        z4 = true;
                                    } else {
                                        z4 = false;
                                    }
                                    if (i2 != 0) {
                                        z5 = true;
                                    } else {
                                        z5 = false;
                                    }
                                    arrayList5.add(Boxing.boxBoolean(arrayList4.add(MainAPIKt.newEpisode(traktProvider2, AppUtils.INSTANCE.toJson(appUtils14.toJson(new LinkData(tmdb3, trakt4, slug2, tmdb4, strValueOf9, tvdb2, tvrage2, strValueOf10, season3, number3, null, null, title5, year2, title6, z3, year3, numBoxInt3, title9, null, firstAired12, firstAired13, z4, z, z5, 527360, null))), new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj6) {
                                            return TraktProvider.load$lambda$2$0$0(traktEpisode3, traktProvider2, objectRef, (Episode) obj6);
                                        }
                                    }))));
                                    it3 = it3;
                                    list3 = list3;
                                    str18 = str18;
                                }
                                list7 = list3;
                                str23 = str18;
                            } else {
                                list6 = list5;
                                list7 = list3;
                                str23 = str18;
                            }
                            z = z;
                            it2 = it2;
                            list5 = list6;
                            list3 = list7;
                            str18 = str23;
                            str20 = str20;
                        }
                        List list19 = list5;
                        String str411 = str20;
                        List list110 = list3;
                        String str412 = str18;
                        boolean z11 = z;
                        TraktProvider traktProvider10 = traktProvider2;
                        if (mediaDetails5 != null) {
                            title4 = mediaDetails5.getTitle();
                        } else {
                            title4 = null;
                        }
                        Intrinsics.checkNotNull(title4);
                        String json16 = AppUtils.INSTANCE.toJson(data);
                        if (i3 != 0) {
                            tvType2 = TvType.Anime;
                        } else {
                            tvType2 = TvType.TvSeries;
                        }
                        List list111 = list4;
                        if (i3 != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        String str413 = str16;
                        TraktProvider traktProvider11 = traktProvider2;
                        AnonymousClass4 anonymousClass8 = new AnonymousClass4(str15, mediaDetails5, z2, arrayList4, str22, traktProvider11, list111, list110, objectRef, str412, str413, null);
                        c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider11);
                        c07392.L$1 = SpillingKt.nullOutSpilledVariable(str410);
                        c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                        c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails5);
                        c07392.L$4 = SpillingKt.nullOutSpilledVariable(str17);
                        c07392.L$5 = SpillingKt.nullOutSpilledVariable(str22);
                        c07392.L$6 = SpillingKt.nullOutSpilledVariable(str412);
                        c07392.L$7 = SpillingKt.nullOutSpilledVariable(str413);
                        c07392.L$8 = SpillingKt.nullOutSpilledVariable(str21);
                        c07392.L$9 = SpillingKt.nullOutSpilledVariable(list110);
                        c07392.L$10 = SpillingKt.nullOutSpilledVariable(str14);
                        c07392.L$11 = SpillingKt.nullOutSpilledVariable(list111);
                        c07392.L$12 = SpillingKt.nullOutSpilledVariable(str15);
                        c07392.L$13 = SpillingKt.nullOutSpilledVariable(str411);
                        c07392.L$14 = SpillingKt.nullOutSpilledVariable(arrayList4);
                        c07392.L$15 = SpillingKt.nullOutSpilledVariable(list19);
                        c07392.L$16 = SpillingKt.nullOutSpilledVariable(objectRef);
                        c07392.I$0 = i2;
                        c07392.I$1 = i3;
                        c07392.I$2 = r8;
                        c07392.Z$0 = z11;
                        c07392.label = 5;
                        objNewTvSeriesLoadResponse = MainAPIKt.newTvSeriesLoadResponse(traktProvider10, title4, json16, tvType2, arrayList4, anonymousClass8, c07392);
                        if (objNewTvSeriesLoadResponse != coroutine_suspended) {
                            return objNewTvSeriesLoadResponse;
                        }
                    }
                }
            }
            if (i2 == 0) {
                r47 = 0;
            } else {
                r47 = 0;
            }
            if (r47 != 0) {
                r54 = 0;
            } else {
                if (mediaDetails2 != null) {
                    language = mediaDetails2.getLanguage();
                } else {
                    language = null;
                }
                if (!Intrinsics.areEqual(language, "zh")) {
                    if (mediaDetails2 != null) {
                        language2 = mediaDetails2.getLanguage();
                    } else {
                        language2 = null;
                    }
                    if (!Intrinsics.areEqual(language2, "ko")) {
                        r54 = 0;
                    }
                }
                r54 = i;
            }
            if (mediaDetails2 != null) {
                country = mediaDetails2.getCountry();
            } else {
                country = null;
            }
            zAreEqual = Intrinsics.areEqual(country, "in");
            mediaDetails3 = data.getMediaDetails();
            if (mediaDetails3 != null) {
                json = AppUtils.INSTANCE.toJson(data);
            } else {
                json = AppUtils.INSTANCE.toJson(data);
            }
            str9 = json;
            if (data.getType() == TvType.Movie) {
                AppUtils appUtils15 = AppUtils.INSTANCE;
                if (mediaDetails2 != null) {
                    tmdb = null;
                } else {
                    tmdb = null;
                }
                if (mediaDetails2 != null) {
                    trakt2 = null;
                } else {
                    trakt2 = null;
                }
                if (mediaDetails2 != null) {
                    slug = null;
                } else {
                    slug = null;
                }
                if (mediaDetails2 != null) {
                    tmdb2 = null;
                } else {
                    tmdb2 = null;
                }
                if (mediaDetails2 != null) {
                    imdb = null;
                } else {
                    imdb = null;
                }
                String strValueOf11 = String.valueOf(imdb);
                if (mediaDetails2 != null) {
                    tvdb = null;
                } else {
                    tvdb = null;
                }
                if (mediaDetails2 != null) {
                    tvrage = null;
                } else {
                    tvrage = null;
                }
                String string11 = data.getType().toString();
                if (mediaDetails2 != null) {
                    title = mediaDetails2.getTitle();
                } else {
                    title = null;
                }
                if (mediaDetails2 != null) {
                    year = mediaDetails2.getYear();
                } else {
                    year = null;
                }
                if (mediaDetails2 != null) {
                    title2 = mediaDetails2.getTitle();
                } else {
                    title2 = null;
                }
                if (mediaDetails2 == null) {
                    if (mediaDetails2 != null) {
                        String firstAired14 = mediaDetails2.getFirstAired();
                        str19 = firstAired14;
                    } else {
                        str19 = null;
                    }
                } else if (mediaDetails2 != null) {
                    String firstAired15 = mediaDetails2.getFirstAired();
                    str19 = firstAired15;
                } else {
                    str19 = null;
                }
                ?? r13 = r54;
                String json17 = appUtils15.toJson(new LinkData(tmdb, trakt2, slug, tmdb2, strValueOf11, tvdb, tvrage, string11, null, null, null, null, title, year, title2, r47, null, null, null, null, null, str19, r54, zAreEqual, false, 18812672, null));
                if (mediaDetails2 != null) {
                    title3 = mediaDetails2.getTitle();
                } else {
                    title3 = null;
                }
                Intrinsics.checkNotNull(title3);
                String json18 = AppUtils.INSTANCE.toJson(data);
                String json19 = AppUtils.INSTANCE.toJson(json17);
                if (r47 != 0) {
                    tvType = TvType.AnimeMovie;
                } else {
                    tvType = TvType.Movie;
                }
                ?? r29 = r47;
                AnonymousClass2 anonymousClass9 = new AnonymousClass2(str9, mediaDetails2, r29, str3, arrayList3, list, str4, str5, null);
                c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider2);
                c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails2);
                c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                c07392.L$5 = SpillingKt.nullOutSpilledVariable(str3);
                c07392.L$6 = SpillingKt.nullOutSpilledVariable(str4);
                c07392.L$7 = SpillingKt.nullOutSpilledVariable(str5);
                c07392.L$8 = SpillingKt.nullOutSpilledVariable(str6);
                c07392.L$9 = SpillingKt.nullOutSpilledVariable(list);
                c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                c07392.L$11 = SpillingKt.nullOutSpilledVariable(arrayList3);
                c07392.L$12 = SpillingKt.nullOutSpilledVariable(str9);
                c07392.L$13 = SpillingKt.nullOutSpilledVariable(json17);
                c07392.I$0 = i2;
                c07392.I$1 = r29 == true ? 1 : 0;
                c07392.I$2 = r13 == true ? 1 : 0;
                c07392.Z$0 = zAreEqual;
                c07392.label = 3;
                objNewMovieLoadResponse = MainAPIKt.newMovieLoadResponse((MainAPI) traktProvider2, title3, json18, tvType, json19, (Function2<? super MovieLoadResponse, ? super Continuation<? super Unit>, ? extends Object>) anonymousClass9, (Continuation<? super MovieLoadResponse>) c07392);
                if (objNewMovieLoadResponse != coroutine_suspended) {
                    return objNewMovieLoadResponse;
                }
            } else {
                str10 = str6;
                mediaDetails4 = mediaDetails2;
                r0 = r47;
                r7 = r54;
                StringBuilder sb7 = new StringBuilder();
                sb7.append(traktProvider2.traktApiUrl);
                sb7.append("/shows/");
                if (mediaDetails4 != null) {
                    trakt = null;
                } else {
                    trakt = null;
                }
                sb7.append(trakt);
                sb7.append("/seasons?extended=full,images,episodes");
                String string12 = sb7.toString();
                c07392.L$0 = traktProvider2;
                c07392.L$1 = SpillingKt.nullOutSpilledVariable(str24);
                c07392.L$2 = data;
                c07392.L$3 = mediaDetails4;
                c07392.L$4 = SpillingKt.nullOutSpilledVariable(str7);
                str11 = str3;
                c07392.L$5 = str11;
                c07392.L$6 = str4;
                c07392.L$7 = str5;
                c07392.L$8 = SpillingKt.nullOutSpilledVariable(str10);
                c07392.L$9 = list;
                c07392.L$10 = SpillingKt.nullOutSpilledVariable(str8);
                c07392.L$11 = arrayList3;
                c07392.L$12 = str9;
                c07392.I$0 = i2;
                c07392.I$1 = r0 == true ? 1 : 0;
                c07392.I$2 = r7 == true ? 1 : 0;
                c07392.Z$0 = zAreEqual;
                c07392.label = 4;
                api = traktProvider2.getApi(string12, c07392);
                if (api != coroutine_suspended) {
                    z = zAreEqual;
                    str12 = str10;
                    mediaDetails5 = mediaDetails4;
                    str13 = str11;
                    str14 = str8;
                    str15 = str9;
                    list2 = arrayList3;
                    list3 = list;
                    str16 = str5;
                    i3 = r0 == true ? 1 : 0;
                    obj3 = api;
                    str17 = str7;
                    str18 = str4;
                    r8 = r7;
                    str20 = (String) obj3;
                    String str414 = str24;
                    arrayList4 = new ArrayList();
                    AppUtils appUtils16 = AppUtils.INSTANCE;
                    Result.Companion companion113 = Result.INSTANCE;
                    str21 = str12;
                    objM8936constructorimpl4 = Result.m8936constructorimpl(new ArrayListSerializer(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.metaproviders.TraktProvider.Seasons")));
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl4) == null) {
                        list4 = list2;
                        str22 = str13;
                        obj4 = null;
                    } else {
                        Result.Companion companion114 = Result.INSTANCE;
                        list4 = list2;
                        str22 = str13;
                        obj4 = null;
                        objM8936constructorimpl4 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), null, 2, null));
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl4)) {
                        objM8936constructorimpl4 = obj4;
                    }
                    kSerializer2 = (KSerializer) objM8936constructorimpl4;
                    if (kSerializer2 != null) {
                        objDecodeFromString4 = MainAPIKt.getJson().decodeFromString(kSerializer2, str20);
                    } else {
                        objDecodeFromString4 = MainAPIKt.getMapper().readValue(str20, new TypeReference<List<? extends Seasons>>() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$suspendImpl$$inlined$parseJson$4
                        });
                    }
                    list5 = (List) objDecodeFromString4;
                    objectRef = new Ref.ObjectRef();
                    it2 = list5.iterator();
                    while (it2.hasNext()) {
                        episodes = ((Seasons) it2.next()).getEpisodes();
                        if (episodes != null) {
                            List<TraktEpisode> list112 = episodes;
                            list6 = list5;
                            arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list112, 10));
                            it3 = list112.iterator();
                            while (it3.hasNext()) {
                                final TraktEpisode traktEpisode4 = (TraktEpisode) it3.next();
                                AppUtils appUtils17 = AppUtils.INSTANCE;
                                if (mediaDetails5 != null) {
                                    tmdb3 = null;
                                } else {
                                    tmdb3 = null;
                                }
                                if (mediaDetails5 != null) {
                                    trakt4 = null;
                                } else {
                                    trakt4 = null;
                                }
                                if (mediaDetails5 != null) {
                                    slug2 = null;
                                } else {
                                    slug2 = null;
                                }
                                if (mediaDetails5 != null) {
                                    tmdb4 = null;
                                } else {
                                    tmdb4 = null;
                                }
                                if (mediaDetails5 != null) {
                                    imdb2 = null;
                                } else {
                                    imdb2 = null;
                                }
                                String strValueOf12 = String.valueOf(imdb2);
                                if (mediaDetails5 != null) {
                                    tvdb2 = null;
                                } else {
                                    tvdb2 = null;
                                }
                                if (mediaDetails5 != null) {
                                    tvrage2 = null;
                                } else {
                                    tvrage2 = null;
                                }
                                String strValueOf13 = String.valueOf(data.getType());
                                Integer season4 = traktEpisode4.getSeason();
                                Integer number4 = traktEpisode4.getNumber();
                                if (mediaDetails5 != null) {
                                    title5 = mediaDetails5.getTitle();
                                } else {
                                    title5 = null;
                                }
                                if (mediaDetails5 != null) {
                                    year2 = mediaDetails5.getYear();
                                } else {
                                    year2 = null;
                                }
                                if (mediaDetails5 != null) {
                                    title6 = mediaDetails5.getTitle();
                                } else {
                                    title6 = null;
                                }
                                if (i3 != 0) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                if (mediaDetails5 != null) {
                                    year3 = mediaDetails5.getYear();
                                } else {
                                    year3 = null;
                                }
                                Integer numBoxInt4 = Boxing.boxInt(list6.size());
                                String title10 = traktEpisode4.getTitle();
                                String firstAired16 = traktEpisode4.getFirstAired();
                                String firstAired17 = traktEpisode4.getFirstAired();
                                if (r8 != 0) {
                                    z4 = true;
                                } else {
                                    z4 = false;
                                }
                                if (i2 != 0) {
                                    z5 = true;
                                } else {
                                    z5 = false;
                                }
                                arrayList5.add(Boxing.boxBoolean(arrayList4.add(MainAPIKt.newEpisode(traktProvider2, AppUtils.INSTANCE.toJson(appUtils17.toJson(new LinkData(tmdb3, trakt4, slug2, tmdb4, strValueOf12, tvdb2, tvrage2, strValueOf13, season4, number4, null, null, title5, year2, title6, z3, year3, numBoxInt4, title10, null, firstAired16, firstAired17, z4, z, z5, 527360, null))), new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.TraktProvider$$ExternalSyntheticLambda0
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj6) {
                                        return TraktProvider.load$lambda$2$0$0(traktEpisode4, traktProvider2, objectRef, (Episode) obj6);
                                    }
                                }))));
                                it3 = it3;
                                list3 = list3;
                                str18 = str18;
                            }
                            list7 = list3;
                            str23 = str18;
                        } else {
                            list6 = list5;
                            list7 = list3;
                            str23 = str18;
                        }
                        z = z;
                        it2 = it2;
                        list5 = list6;
                        list3 = list7;
                        str18 = str23;
                        str20 = str20;
                    }
                    List list113 = list5;
                    String str415 = str20;
                    List list114 = list3;
                    String str416 = str18;
                    boolean z12 = z;
                    TraktProvider traktProvider12 = traktProvider2;
                    if (mediaDetails5 != null) {
                        title4 = mediaDetails5.getTitle();
                    } else {
                        title4 = null;
                    }
                    Intrinsics.checkNotNull(title4);
                    String json110 = AppUtils.INSTANCE.toJson(data);
                    if (i3 != 0) {
                        tvType2 = TvType.Anime;
                    } else {
                        tvType2 = TvType.TvSeries;
                    }
                    List list115 = list4;
                    if (i3 != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    String str417 = str16;
                    TraktProvider traktProvider13 = traktProvider2;
                    AnonymousClass4 anonymousClass10 = new AnonymousClass4(str15, mediaDetails5, z2, arrayList4, str22, traktProvider13, list115, list114, objectRef, str416, str417, null);
                    c07392.L$0 = SpillingKt.nullOutSpilledVariable(traktProvider13);
                    c07392.L$1 = SpillingKt.nullOutSpilledVariable(str414);
                    c07392.L$2 = SpillingKt.nullOutSpilledVariable(data);
                    c07392.L$3 = SpillingKt.nullOutSpilledVariable(mediaDetails5);
                    c07392.L$4 = SpillingKt.nullOutSpilledVariable(str17);
                    c07392.L$5 = SpillingKt.nullOutSpilledVariable(str22);
                    c07392.L$6 = SpillingKt.nullOutSpilledVariable(str416);
                    c07392.L$7 = SpillingKt.nullOutSpilledVariable(str417);
                    c07392.L$8 = SpillingKt.nullOutSpilledVariable(str21);
                    c07392.L$9 = SpillingKt.nullOutSpilledVariable(list114);
                    c07392.L$10 = SpillingKt.nullOutSpilledVariable(str14);
                    c07392.L$11 = SpillingKt.nullOutSpilledVariable(list115);
                    c07392.L$12 = SpillingKt.nullOutSpilledVariable(str15);
                    c07392.L$13 = SpillingKt.nullOutSpilledVariable(str415);
                    c07392.L$14 = SpillingKt.nullOutSpilledVariable(arrayList4);
                    c07392.L$15 = SpillingKt.nullOutSpilledVariable(list113);
                    c07392.L$16 = SpillingKt.nullOutSpilledVariable(objectRef);
                    c07392.I$0 = i2;
                    c07392.I$1 = i3;
                    c07392.I$2 = r8;
                    c07392.Z$0 = z12;
                    c07392.label = 5;
                    objNewTvSeriesLoadResponse = MainAPIKt.newTvSeriesLoadResponse(traktProvider12, title4, json110, tvType2, arrayList4, anonymousClass10, c07392);
                    if (objNewTvSeriesLoadResponse != coroutine_suspended) {
                        return objNewTvSeriesLoadResponse;
                    }
                }
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TraktProvider$load$2", f = "TraktProvider.kt", i = {0}, l = {188}, m = "invokeSuspend", n = {"$this$newMovieLoadResponse"}, nl = {189}, s = {"L$0"}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $backDropUrl;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ String $logoUrl;
        final /* synthetic */ MediaDetails $mediaDetails;
        final /* synthetic */ String $posterUrl;
        final /* synthetic */ List<SearchResponse> $relatedMedia;
        final /* synthetic */ String $uniqueUrl;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(String str, MediaDetails mediaDetails, boolean z, String str2, List<? extends SearchResponse> list, List<ActorData> list2, String str3, String str4, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$uniqueUrl = str;
            this.$mediaDetails = mediaDetails;
            this.$isAnime = z;
            this.$posterUrl = str2;
            this.$relatedMedia = list;
            this.$actors = list2;
            this.$backDropUrl = str3;
            this.$logoUrl = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$uniqueUrl, this.$mediaDetails, this.$isAnime, this.$posterUrl, this.$relatedMedia, this.$actors, this.$backDropUrl, this.$logoUrl, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(movieLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MovieLoadResponse movieLoadResponse = (MovieLoadResponse) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                movieLoadResponse.setUniqueUrl(this.$uniqueUrl);
                movieLoadResponse.setName(this.$mediaDetails.getTitle());
                movieLoadResponse.setType(this.$isAnime ? TvType.AnimeMovie : TvType.Movie);
                movieLoadResponse.setPosterUrl(this.$posterUrl);
                movieLoadResponse.setYear(this.$mediaDetails.getYear());
                movieLoadResponse.setPlot(this.$mediaDetails.getOverview());
                movieLoadResponse.setScore(Score.INSTANCE.from10(this.$mediaDetails.getRating()));
                movieLoadResponse.setTags(this.$mediaDetails.getGenres());
                movieLoadResponse.setDuration(this.$mediaDetails.getRuntime());
                movieLoadResponse.setRecommendations(this.$relatedMedia);
                movieLoadResponse.setActors(this.$actors);
                movieLoadResponse.setComingSoon(MainAPIKt.isUpcoming(this.$mediaDetails.getReleased()));
                movieLoadResponse.setBackgroundPosterUrl(this.$backDropUrl);
                movieLoadResponse.setLogoUrl(this.$logoUrl);
                movieLoadResponse.setContentRating(this.$mediaDetails.getCertification());
                this.L$0 = movieLoadResponse;
                this.label = 1;
                if (LoadResponse.Companion.addTrailer$default(LoadResponse.INSTANCE, (LoadResponse) movieLoadResponse, this.$mediaDetails.getTrailer(), (String) null, false, (Continuation) this, 6, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LoadResponse.Companion companion = LoadResponse.INSTANCE;
            MovieLoadResponse movieLoadResponse2 = movieLoadResponse;
            Ids ids = this.$mediaDetails.getIds();
            companion.addImdbId(movieLoadResponse2, ids != null ? ids.getImdb() : null);
            LoadResponse.Companion companion2 = LoadResponse.INSTANCE;
            Ids ids2 = this.$mediaDetails.getIds();
            companion2.addTMDbId(movieLoadResponse2, String.valueOf(ids2 != null ? ids2.getTmdb() : null));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r6v9, types: [T, com.lagradost.cloudstream3.NextAiring] */
    public static final Unit load$lambda$2$0$0(TraktEpisode traktEpisode, TraktProvider traktProvider, Ref.ObjectRef objectRef, Episode newEpisode) {
        Integer season;
        List<String> screenshot;
        Intrinsics.checkNotNullParameter(newEpisode, "$this$newEpisode");
        newEpisode.setName(traktEpisode.getTitle());
        newEpisode.setSeason(traktEpisode.getSeason());
        newEpisode.setEpisode(traktEpisode.getNumber());
        newEpisode.setDescription(traktEpisode.getOverview());
        newEpisode.setRunTime(traktEpisode.getRuntime());
        Images images = traktEpisode.getImages();
        newEpisode.setPosterUrl(traktProvider.fixPath((images == null || (screenshot = images.getScreenshot()) == null) ? null : (String) CollectionsKt.firstOrNull((List) screenshot)));
        newEpisode.setScore(Score.INSTANCE.from10(traktEpisode.getRating()));
        MainAPIKt.addDate(newEpisode, traktEpisode.getFirstAired(), "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        if (objectRef.element == 0 && newEpisode.getDate() != null) {
            Long date = newEpisode.getDate();
            Intrinsics.checkNotNull(date);
            if (date.longValue() > APIHolder.INSTANCE.getUnixTimeMS() && ((season = newEpisode.getSeason()) == null || season.intValue() != 0)) {
                Integer episode = newEpisode.getEpisode();
                Intrinsics.checkNotNull(episode);
                int iIntValue = episode.intValue();
                Long date2 = newEpisode.getDate();
                Intrinsics.checkNotNull(date2);
                long jLongValue = date2.longValue() / 1000;
                Integer season2 = newEpisode.getSeason();
                objectRef.element = new NextAiring(iIntValue, jLongValue, (season2 == null || season2.intValue() != 1) ? newEpisode.getSeason() : null);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.TraktProvider$load$4, reason: invalid class name */
    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.TraktProvider$load$4", f = "TraktProvider.kt", i = {0}, l = {279}, m = "invokeSuspend", n = {"$this$newTvSeriesLoadResponse"}, nl = {280}, s = {"L$0"}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $backDropUrl;
        final /* synthetic */ List<Episode> $episodes;
        final /* synthetic */ boolean $isAnime;
        final /* synthetic */ String $logoUrl;
        final /* synthetic */ MediaDetails $mediaDetails;
        final /* synthetic */ Ref.ObjectRef<NextAiring> $nextAir;
        final /* synthetic */ String $posterUrl;
        final /* synthetic */ List<SearchResponse> $relatedMedia;
        final /* synthetic */ String $uniqueUrl;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TraktProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass4(String str, MediaDetails mediaDetails, boolean z, List<Episode> list, String str2, TraktProvider traktProvider, List<? extends SearchResponse> list2, List<ActorData> list3, Ref.ObjectRef<NextAiring> objectRef, String str3, String str4, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$uniqueUrl = str;
            this.$mediaDetails = mediaDetails;
            this.$isAnime = z;
            this.$episodes = list;
            this.$posterUrl = str2;
            this.this$0 = traktProvider;
            this.$relatedMedia = list2;
            this.$actors = list3;
            this.$nextAir = objectRef;
            this.$backDropUrl = str3;
            this.$logoUrl = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.$uniqueUrl, this.$mediaDetails, this.$isAnime, this.$episodes, this.$posterUrl, this.this$0, this.$relatedMedia, this.$actors, this.$nextAir, this.$backDropUrl, this.$logoUrl, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(tvSeriesLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TvSeriesLoadResponse tvSeriesLoadResponse = (TvSeriesLoadResponse) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                tvSeriesLoadResponse.setUniqueUrl(this.$uniqueUrl);
                tvSeriesLoadResponse.setName(this.$mediaDetails.getTitle());
                tvSeriesLoadResponse.setType(this.$isAnime ? TvType.Anime : TvType.TvSeries);
                tvSeriesLoadResponse.setEpisodes(this.$episodes);
                tvSeriesLoadResponse.setPosterUrl(this.$posterUrl);
                tvSeriesLoadResponse.setYear(this.$mediaDetails.getYear());
                tvSeriesLoadResponse.setPlot(this.$mediaDetails.getOverview());
                tvSeriesLoadResponse.setShowStatus(this.this$0.getStatus(this.$mediaDetails.getStatus()));
                tvSeriesLoadResponse.setScore(Score.INSTANCE.from10(this.$mediaDetails.getRating()));
                tvSeriesLoadResponse.setTags(this.$mediaDetails.getGenres());
                tvSeriesLoadResponse.setDuration(this.$mediaDetails.getRuntime());
                tvSeriesLoadResponse.setRecommendations(this.$relatedMedia);
                tvSeriesLoadResponse.setActors(this.$actors);
                tvSeriesLoadResponse.setComingSoon(MainAPIKt.isUpcoming(this.$mediaDetails.getReleased()));
                tvSeriesLoadResponse.setNextAiring(this.$nextAir.element);
                tvSeriesLoadResponse.setBackgroundPosterUrl(this.$backDropUrl);
                tvSeriesLoadResponse.setLogoUrl(this.$logoUrl);
                tvSeriesLoadResponse.setContentRating(this.$mediaDetails.getCertification());
                this.L$0 = tvSeriesLoadResponse;
                this.label = 1;
                if (LoadResponse.Companion.addTrailer$default(LoadResponse.INSTANCE, (LoadResponse) tvSeriesLoadResponse, this.$mediaDetails.getTrailer(), (String) null, false, (Continuation) this, 6, (Object) null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LoadResponse.Companion companion = LoadResponse.INSTANCE;
            TvSeriesLoadResponse tvSeriesLoadResponse2 = tvSeriesLoadResponse;
            Ids ids = this.$mediaDetails.getIds();
            companion.addImdbId(tvSeriesLoadResponse2, ids != null ? ids.getImdb() : null);
            LoadResponse.Companion companion2 = LoadResponse.INSTANCE;
            Ids ids2 = this.$mediaDetails.getIds();
            companion2.addTMDbId(tvSeriesLoadResponse2, String.valueOf(ids2 != null ? ids2.getTmdb() : null));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getApi(String str, Continuation<? super String> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Content-Type", "application/json"), TuplesKt.to("trakt-api-version", ExifInterface.GPS_MEASUREMENT_2D), TuplesKt.to("trakt-api-key", this.traktClientId));
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.label = 1;
            Object obj2 = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 4092, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return ((NiceResponse) obj).getText();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ShowStatus getStatus(String t) {
        if (!Intrinsics.areEqual(t, "returning series") && !Intrinsics.areEqual(t, "continuing")) {
            return ShowStatus.Completed;
        }
        return ShowStatus.Ongoing;
    }

    private final String fixPath(String url) {
        if (url == null) {
            return null;
        }
        return Utils.HTTPS + url;
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Data;", "", "type", "Lcom/lagradost/cloudstream3/TvType;", "mediaDetails", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;", "<init>", "(Lcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;)V", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "getMediaDetails", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Data {
        private final MediaDetails mediaDetails;
        private final TvType type;

        /* JADX WARN: Multi-variable type inference failed */
        public Data() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ Data copy$default(Data data, TvType tvType, MediaDetails mediaDetails, int i, Object obj) {
            if ((i & 1) != 0) {
                tvType = data.type;
            }
            if ((i & 2) != 0) {
                mediaDetails = data.mediaDetails;
            }
            return data.copy(tvType, mediaDetails);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaDetails getMediaDetails() {
            return this.mediaDetails;
        }

        public final Data copy(TvType type, MediaDetails mediaDetails) {
            return new Data(type, mediaDetails);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return this.type == data.type && Intrinsics.areEqual(this.mediaDetails, data.mediaDetails);
        }

        public int hashCode() {
            TvType tvType = this.type;
            int iHashCode = (tvType == null ? 0 : tvType.hashCode()) * 31;
            MediaDetails mediaDetails = this.mediaDetails;
            return iHashCode + (mediaDetails != null ? mediaDetails.hashCode() : 0);
        }

        public String toString() {
            return "Data(type=" + this.type + ", mediaDetails=" + this.mediaDetails + ')';
        }

        public Data(TvType tvType, MediaDetails mediaDetails) {
            this.type = tvType;
            this.mediaDetails = mediaDetails;
        }

        public /* synthetic */ Data(TvType tvType, MediaDetails mediaDetails, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : tvType, (i & 2) != 0 ? null : mediaDetails);
        }

        public final TvType getType() {
            return this.type;
        }

        public final MediaDetails getMediaDetails() {
            return this.mediaDetails;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bF\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BÑ\u0002\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018\u0012\u0010\b\u0003\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018\u0012\u0010\b\u0003\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018\u0012\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b$\u0010%J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010M\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010)J\u000b\u0010N\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010)J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010X\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u00107J\u0010\u0010Y\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0002\u0010:J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0002\u0010:J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018HÆ\u0003J\u0011\u0010]\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018HÆ\u0003J\u0011\u0010^\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0018HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010`\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010)J\u000b\u0010a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u001fHÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\"HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0000HÆ\u0003JØ\u0002\u0010f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00182\u0010\b\u0003\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00182\u0010\b\u0003\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00182\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u0000HÆ\u0001¢\u0006\u0002\u0010gJ\u0014\u0010h\u001a\u00020i2\b\u0010j\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010k\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010l\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010'R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010'R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010'R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010*\u001a\u0004\b0\u0010)R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010'R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010'R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010'R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010'R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010'R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\n\n\u0002\u00108\u001a\u0004\b6\u00107R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010;\u001a\u0004\b9\u0010:R\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010;\u001a\u0004\b<\u0010:R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010'R\u001e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u001e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010?R\u001e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010?R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010'R\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010*\u001a\u0004\bC\u0010)R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010'R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0018\u0010 \u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010'R\u0018\u0010!\u001a\u0004\u0018\u00010\"8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0018\u0010#\u001a\u0004\u0018\u00010\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010K¨\u0006m"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;", "", "title", "", "year", "", "ids", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "tagline", "overview", "released", "runtime", "country", "updatedAt", "trailer", "homepage", NotificationCompat.CATEGORY_STATUS, "rating", "", "votes", "", "commentCount", "language", "languages", "", "availableTranslations", "genres", "certification", "airedEpisodes", "firstAired", "airs", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Airs;", "network", "images", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", LinkHeader.Parameters.Media, "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Airs;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;)V", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "getTagline", "getOverview", "getReleased", "getRuntime", "getCountry", "getUpdatedAt", "getTrailer", "getHomepage", "getStatus", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getVotes", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCommentCount", "getLanguage", "getLanguages", "()Ljava/util/List;", "getAvailableTranslations", "getGenres", "getCertification", "getAiredEpisodes", "getFirstAired", "getAirs", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Airs;", "getNetwork", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "getMedia", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Airs;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;)Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$MediaDetails;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MediaDetails {

        @JsonProperty("aired_episodes")
        private final Integer airedEpisodes;

        @JsonProperty("airs")
        private final Airs airs;

        @JsonProperty("available_translations")
        private final List<String> availableTranslations;

        @JsonProperty("certification")
        private final String certification;

        @JsonProperty("comment_count")
        private final Long commentCount;

        @JsonProperty("country")
        private final String country;

        @JsonProperty("first_aired")
        private final String firstAired;

        @JsonProperty("genres")
        private final List<String> genres;

        @JsonProperty("homepage")
        private final String homepage;

        @JsonProperty("ids")
        private final Ids ids;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("language")
        private final String language;

        @JsonProperty("languages")
        private final List<String> languages;

        @JsonProperty("movie")
        @JsonAlias({"show"})
        private final MediaDetails media;

        @JsonProperty("network")
        private final String network;

        @JsonProperty("overview")
        private final String overview;

        @JsonProperty("rating")
        private final Double rating;

        @JsonProperty("released")
        private final String released;

        @JsonProperty("runtime")
        private final Integer runtime;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("tagline")
        private final String tagline;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("trailer")
        private final String trailer;

        @JsonProperty("updatedAt")
        private final String updatedAt;

        @JsonProperty("votes")
        private final Long votes;

        @JsonProperty("year")
        private final Integer year;

        public MediaDetails() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, RectListKt.Lower26Bits, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MediaDetails copy$default(MediaDetails mediaDetails, String str, Integer num, Ids ids, String str2, String str3, String str4, Integer num2, String str5, String str6, String str7, String str8, String str9, Double d, Long l, Long l2, String str10, List list, List list2, List list3, String str11, Integer num3, String str12, Airs airs, String str13, Images images, MediaDetails mediaDetails2, int i, Object obj) {
            MediaDetails mediaDetails3;
            Images images2;
            String str14 = (i & 1) != 0 ? mediaDetails.title : str;
            Integer num4 = (i & 2) != 0 ? mediaDetails.year : num;
            Ids ids2 = (i & 4) != 0 ? mediaDetails.ids : ids;
            String str15 = (i & 8) != 0 ? mediaDetails.tagline : str2;
            String str16 = (i & 16) != 0 ? mediaDetails.overview : str3;
            String str17 = (i & 32) != 0 ? mediaDetails.released : str4;
            Integer num5 = (i & 64) != 0 ? mediaDetails.runtime : num2;
            String str18 = (i & 128) != 0 ? mediaDetails.country : str5;
            String str19 = (i & 256) != 0 ? mediaDetails.updatedAt : str6;
            String str20 = (i & 512) != 0 ? mediaDetails.trailer : str7;
            String str21 = (i & 1024) != 0 ? mediaDetails.homepage : str8;
            String str22 = (i & 2048) != 0 ? mediaDetails.status : str9;
            Double d2 = (i & 4096) != 0 ? mediaDetails.rating : d;
            Long l3 = (i & 8192) != 0 ? mediaDetails.votes : l;
            String str23 = str14;
            Long l4 = (i & 16384) != 0 ? mediaDetails.commentCount : l2;
            String str24 = (i & 32768) != 0 ? mediaDetails.language : str10;
            List list4 = (i & 65536) != 0 ? mediaDetails.languages : list;
            List list5 = (i & 131072) != 0 ? mediaDetails.availableTranslations : list2;
            List list6 = (i & 262144) != 0 ? mediaDetails.genres : list3;
            String str25 = (i & 524288) != 0 ? mediaDetails.certification : str11;
            Integer num6 = (i & 1048576) != 0 ? mediaDetails.airedEpisodes : num3;
            String str26 = (i & 2097152) != 0 ? mediaDetails.firstAired : str12;
            Airs airs2 = (i & 4194304) != 0 ? mediaDetails.airs : airs;
            String str27 = (i & 8388608) != 0 ? mediaDetails.network : str13;
            Images images3 = (i & 16777216) != 0 ? mediaDetails.images : images;
            if ((i & 33554432) != 0) {
                images2 = images3;
                mediaDetails3 = mediaDetails.media;
            } else {
                mediaDetails3 = mediaDetails2;
                images2 = images3;
            }
            return mediaDetails.copy(str23, num4, ids2, str15, str16, str17, num5, str18, str19, str20, str21, str22, d2, l3, l4, str24, list4, list5, list6, str25, num6, str26, airs2, str27, images2, mediaDetails3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getTrailer() {
            return this.trailer;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getHomepage() {
            return this.homepage;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Double getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Long getVotes() {
            return this.votes;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Long getCommentCount() {
            return this.commentCount;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        public final List<String> component17() {
            return this.languages;
        }

        public final List<String> component18() {
            return this.availableTranslations;
        }

        public final List<String> component19() {
            return this.genres;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getCertification() {
            return this.certification;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final Integer getAiredEpisodes() {
            return this.airedEpisodes;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final String getFirstAired() {
            return this.firstAired;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final Airs getAirs() {
            return this.airs;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final String getNetwork() {
            return this.network;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        /* JADX INFO: renamed from: component26, reason: from getter */
        public final MediaDetails getMedia() {
            return this.media;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Ids getIds() {
            return this.ids;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTagline() {
            return this.tagline;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getOverview() {
            return this.overview;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getReleased() {
            return this.released;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getRuntime() {
            return this.runtime;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getCountry() {
            return this.country;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final MediaDetails copy(@JsonProperty("title") String title, @JsonProperty("year") Integer year, @JsonProperty("ids") Ids ids, @JsonProperty("tagline") String tagline, @JsonProperty("overview") String overview, @JsonProperty("released") String released, @JsonProperty("runtime") Integer runtime, @JsonProperty("country") String country, @JsonProperty("updatedAt") String updatedAt, @JsonProperty("trailer") String trailer, @JsonProperty("homepage") String homepage, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("rating") Double rating, @JsonProperty("votes") Long votes, @JsonProperty("comment_count") Long commentCount, @JsonProperty("language") String language, @JsonProperty("languages") List<String> languages, @JsonProperty("available_translations") List<String> availableTranslations, @JsonProperty("genres") List<String> genres, @JsonProperty("certification") String certification, @JsonProperty("aired_episodes") Integer airedEpisodes, @JsonProperty("first_aired") String firstAired, @JsonProperty("airs") Airs airs, @JsonProperty("network") String network, @JsonProperty("images") Images images, @JsonProperty("movie") @JsonAlias({"show"}) MediaDetails media) {
            return new MediaDetails(title, year, ids, tagline, overview, released, runtime, country, updatedAt, trailer, homepage, status, rating, votes, commentCount, language, languages, availableTranslations, genres, certification, airedEpisodes, firstAired, airs, network, images, media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaDetails)) {
                return false;
            }
            MediaDetails mediaDetails = (MediaDetails) other;
            return Intrinsics.areEqual(this.title, mediaDetails.title) && Intrinsics.areEqual(this.year, mediaDetails.year) && Intrinsics.areEqual(this.ids, mediaDetails.ids) && Intrinsics.areEqual(this.tagline, mediaDetails.tagline) && Intrinsics.areEqual(this.overview, mediaDetails.overview) && Intrinsics.areEqual(this.released, mediaDetails.released) && Intrinsics.areEqual(this.runtime, mediaDetails.runtime) && Intrinsics.areEqual(this.country, mediaDetails.country) && Intrinsics.areEqual(this.updatedAt, mediaDetails.updatedAt) && Intrinsics.areEqual(this.trailer, mediaDetails.trailer) && Intrinsics.areEqual(this.homepage, mediaDetails.homepage) && Intrinsics.areEqual(this.status, mediaDetails.status) && Intrinsics.areEqual((Object) this.rating, (Object) mediaDetails.rating) && Intrinsics.areEqual(this.votes, mediaDetails.votes) && Intrinsics.areEqual(this.commentCount, mediaDetails.commentCount) && Intrinsics.areEqual(this.language, mediaDetails.language) && Intrinsics.areEqual(this.languages, mediaDetails.languages) && Intrinsics.areEqual(this.availableTranslations, mediaDetails.availableTranslations) && Intrinsics.areEqual(this.genres, mediaDetails.genres) && Intrinsics.areEqual(this.certification, mediaDetails.certification) && Intrinsics.areEqual(this.airedEpisodes, mediaDetails.airedEpisodes) && Intrinsics.areEqual(this.firstAired, mediaDetails.firstAired) && Intrinsics.areEqual(this.airs, mediaDetails.airs) && Intrinsics.areEqual(this.network, mediaDetails.network) && Intrinsics.areEqual(this.images, mediaDetails.images) && Intrinsics.areEqual(this.media, mediaDetails.media);
        }

        public int hashCode() {
            String str = this.title;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.year;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Ids ids = this.ids;
            int iHashCode3 = (iHashCode2 + (ids == null ? 0 : ids.hashCode())) * 31;
            String str2 = this.tagline;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.overview;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.released;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num2 = this.runtime;
            int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str5 = this.country;
            int iHashCode8 = (iHashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.updatedAt;
            int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.trailer;
            int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.homepage;
            int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.status;
            int iHashCode12 = (iHashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
            Double d = this.rating;
            int iHashCode13 = (iHashCode12 + (d == null ? 0 : d.hashCode())) * 31;
            Long l = this.votes;
            int iHashCode14 = (iHashCode13 + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.commentCount;
            int iHashCode15 = (iHashCode14 + (l2 == null ? 0 : l2.hashCode())) * 31;
            String str10 = this.language;
            int iHashCode16 = (iHashCode15 + (str10 == null ? 0 : str10.hashCode())) * 31;
            List<String> list = this.languages;
            int iHashCode17 = (iHashCode16 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.availableTranslations;
            int iHashCode18 = (iHashCode17 + (list2 == null ? 0 : list2.hashCode())) * 31;
            List<String> list3 = this.genres;
            int iHashCode19 = (iHashCode18 + (list3 == null ? 0 : list3.hashCode())) * 31;
            String str11 = this.certification;
            int iHashCode20 = (iHashCode19 + (str11 == null ? 0 : str11.hashCode())) * 31;
            Integer num3 = this.airedEpisodes;
            int iHashCode21 = (iHashCode20 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str12 = this.firstAired;
            int iHashCode22 = (iHashCode21 + (str12 == null ? 0 : str12.hashCode())) * 31;
            Airs airs = this.airs;
            int iHashCode23 = (iHashCode22 + (airs == null ? 0 : airs.hashCode())) * 31;
            String str13 = this.network;
            int iHashCode24 = (iHashCode23 + (str13 == null ? 0 : str13.hashCode())) * 31;
            Images images = this.images;
            int iHashCode25 = (iHashCode24 + (images == null ? 0 : images.hashCode())) * 31;
            MediaDetails mediaDetails = this.media;
            return iHashCode25 + (mediaDetails != null ? mediaDetails.hashCode() : 0);
        }

        public String toString() {
            return "MediaDetails(title=" + this.title + ", year=" + this.year + ", ids=" + this.ids + ", tagline=" + this.tagline + ", overview=" + this.overview + ", released=" + this.released + ", runtime=" + this.runtime + ", country=" + this.country + ", updatedAt=" + this.updatedAt + ", trailer=" + this.trailer + ", homepage=" + this.homepage + ", status=" + this.status + ", rating=" + this.rating + ", votes=" + this.votes + ", commentCount=" + this.commentCount + ", language=" + this.language + ", languages=" + this.languages + ", availableTranslations=" + this.availableTranslations + ", genres=" + this.genres + ", certification=" + this.certification + ", airedEpisodes=" + this.airedEpisodes + ", firstAired=" + this.firstAired + ", airs=" + this.airs + ", network=" + this.network + ", images=" + this.images + ", media=" + this.media + ')';
        }

        public MediaDetails(@JsonProperty("title") String str, @JsonProperty("year") Integer num, @JsonProperty("ids") Ids ids, @JsonProperty("tagline") String str2, @JsonProperty("overview") String str3, @JsonProperty("released") String str4, @JsonProperty("runtime") Integer num2, @JsonProperty("country") String str5, @JsonProperty("updatedAt") String str6, @JsonProperty("trailer") String str7, @JsonProperty("homepage") String str8, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String str9, @JsonProperty("rating") Double d, @JsonProperty("votes") Long l, @JsonProperty("comment_count") Long l2, @JsonProperty("language") String str10, @JsonProperty("languages") List<String> list, @JsonProperty("available_translations") List<String> list2, @JsonProperty("genres") List<String> list3, @JsonProperty("certification") String str11, @JsonProperty("aired_episodes") Integer num3, @JsonProperty("first_aired") String str12, @JsonProperty("airs") Airs airs, @JsonProperty("network") String str13, @JsonProperty("images") Images images, @JsonProperty("movie") @JsonAlias({"show"}) MediaDetails mediaDetails) {
            this.title = str;
            this.year = num;
            this.ids = ids;
            this.tagline = str2;
            this.overview = str3;
            this.released = str4;
            this.runtime = num2;
            this.country = str5;
            this.updatedAt = str6;
            this.trailer = str7;
            this.homepage = str8;
            this.status = str9;
            this.rating = d;
            this.votes = l;
            this.commentCount = l2;
            this.language = str10;
            this.languages = list;
            this.availableTranslations = list2;
            this.genres = list3;
            this.certification = str11;
            this.airedEpisodes = num3;
            this.firstAired = str12;
            this.airs = airs;
            this.network = str13;
            this.images = images;
            this.media = mediaDetails;
        }

        public /* synthetic */ MediaDetails(String str, Integer num, Ids ids, String str2, String str3, String str4, Integer num2, String str5, String str6, String str7, String str8, String str9, Double d, Long l, Long l2, String str10, List list, List list2, List list3, String str11, Integer num3, String str12, Airs airs, String str13, Images images, MediaDetails mediaDetails, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : ids, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : str7, (i & 1024) != 0 ? null : str8, (i & 2048) != 0 ? null : str9, (i & 4096) != 0 ? null : d, (i & 8192) != 0 ? null : l, (i & 16384) != 0 ? null : l2, (i & 32768) != 0 ? null : str10, (i & 65536) != 0 ? null : list, (i & 131072) != 0 ? null : list2, (i & 262144) != 0 ? null : list3, (i & 524288) != 0 ? null : str11, (i & 1048576) != 0 ? null : num3, (i & 2097152) != 0 ? null : str12, (i & 4194304) != 0 ? null : airs, (i & 8388608) != 0 ? null : str13, (i & 16777216) != 0 ? null : images, (i & 33554432) != 0 ? null : mediaDetails);
        }

        public final String getTitle() {
            return this.title;
        }

        public final Integer getYear() {
            return this.year;
        }

        public final Ids getIds() {
            return this.ids;
        }

        public final String getTagline() {
            return this.tagline;
        }

        public final String getOverview() {
            return this.overview;
        }

        public final String getReleased() {
            return this.released;
        }

        public final Integer getRuntime() {
            return this.runtime;
        }

        public final String getCountry() {
            return this.country;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final String getTrailer() {
            return this.trailer;
        }

        public final String getHomepage() {
            return this.homepage;
        }

        public final String getStatus() {
            return this.status;
        }

        public final Double getRating() {
            return this.rating;
        }

        public final Long getVotes() {
            return this.votes;
        }

        public final Long getCommentCount() {
            return this.commentCount;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final List<String> getLanguages() {
            return this.languages;
        }

        public final List<String> getAvailableTranslations() {
            return this.availableTranslations;
        }

        public final List<String> getGenres() {
            return this.genres;
        }

        public final String getCertification() {
            return this.certification;
        }

        public final Integer getAiredEpisodes() {
            return this.airedEpisodes;
        }

        public final String getFirstAired() {
            return this.firstAired;
        }

        public final Airs getAirs() {
            return this.airs;
        }

        public final String getNetwork() {
            return this.network;
        }

        public final Images getImages() {
            return this.images;
        }

        public final MediaDetails getMedia() {
            return this.media;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Airs;", "", "day", "", "time", "timezone", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDay", "()Ljava/lang/String;", "getTime", "getTimezone", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Airs {

        @JsonProperty("day")
        private final String day;

        @JsonProperty("time")
        private final String time;

        @JsonProperty("timezone")
        private final String timezone;

        public Airs() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Airs copy$default(Airs airs, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = airs.day;
            }
            if ((i & 2) != 0) {
                str2 = airs.time;
            }
            if ((i & 4) != 0) {
                str3 = airs.timezone;
            }
            return airs.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDay() {
            return this.day;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTime() {
            return this.time;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTimezone() {
            return this.timezone;
        }

        public final Airs copy(@JsonProperty("day") String day, @JsonProperty("time") String time, @JsonProperty("timezone") String timezone) {
            return new Airs(day, time, timezone);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Airs)) {
                return false;
            }
            Airs airs = (Airs) other;
            return Intrinsics.areEqual(this.day, airs.day) && Intrinsics.areEqual(this.time, airs.time) && Intrinsics.areEqual(this.timezone, airs.timezone);
        }

        public int hashCode() {
            String str = this.day;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.time;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.timezone;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Airs(day=" + this.day + ", time=" + this.time + ", timezone=" + this.timezone + ')';
        }

        public Airs(@JsonProperty("day") String str, @JsonProperty("time") String str2, @JsonProperty("timezone") String str3) {
            this.day = str;
            this.time = str2;
            this.timezone = str3;
        }

        public /* synthetic */ Airs(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        public final String getDay() {
            return this.day;
        }

        public final String getTime() {
            return this.time;
        }

        public final String getTimezone() {
            return this.timezone;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003JV\u0010\u001b\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0011\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0013\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "", "trakt", "", "slug", "", "tvdb", "imdb", "tmdb", "tvrage", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getTrakt", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSlug", "()Ljava/lang/String;", "getTvdb", "getImdb", "getTmdb", "getTvrage", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Ids {

        @JsonProperty("imdb")
        private final String imdb;

        @JsonProperty("slug")
        private final String slug;

        @JsonProperty("tmdb")
        private final Integer tmdb;

        @JsonProperty("trakt")
        private final Integer trakt;

        @JsonProperty("tvdb")
        private final Integer tvdb;

        @JsonProperty("tvrage")
        private final String tvrage;

        public Ids() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ Ids copy$default(Ids ids, Integer num, String str, Integer num2, String str2, Integer num3, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                num = ids.trakt;
            }
            if ((i & 2) != 0) {
                str = ids.slug;
            }
            if ((i & 4) != 0) {
                num2 = ids.tvdb;
            }
            if ((i & 8) != 0) {
                str2 = ids.imdb;
            }
            if ((i & 16) != 0) {
                num3 = ids.tmdb;
            }
            if ((i & 32) != 0) {
                str3 = ids.tvrage;
            }
            Integer num4 = num3;
            String str4 = str3;
            return ids.copy(num, str, num2, str2, num4, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getTrakt() {
            return this.trakt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getTvdb() {
            return this.tvdb;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getImdb() {
            return this.imdb;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getTmdb() {
            return this.tmdb;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getTvrage() {
            return this.tvrage;
        }

        public final Ids copy(@JsonProperty("trakt") Integer trakt, @JsonProperty("slug") String slug, @JsonProperty("tvdb") Integer tvdb, @JsonProperty("imdb") String imdb, @JsonProperty("tmdb") Integer tmdb, @JsonProperty("tvrage") String tvrage) {
            return new Ids(trakt, slug, tvdb, imdb, tmdb, tvrage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Ids)) {
                return false;
            }
            Ids ids = (Ids) other;
            return Intrinsics.areEqual(this.trakt, ids.trakt) && Intrinsics.areEqual(this.slug, ids.slug) && Intrinsics.areEqual(this.tvdb, ids.tvdb) && Intrinsics.areEqual(this.imdb, ids.imdb) && Intrinsics.areEqual(this.tmdb, ids.tmdb) && Intrinsics.areEqual(this.tvrage, ids.tvrage);
        }

        public int hashCode() {
            Integer num = this.trakt;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.slug;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num2 = this.tvdb;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str2 = this.imdb;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num3 = this.tmdb;
            int iHashCode5 = (iHashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str3 = this.tvrage;
            return iHashCode5 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Ids(trakt=" + this.trakt + ", slug=" + this.slug + ", tvdb=" + this.tvdb + ", imdb=" + this.imdb + ", tmdb=" + this.tmdb + ", tvrage=" + this.tvrage + ')';
        }

        public Ids(@JsonProperty("trakt") Integer num, @JsonProperty("slug") String str, @JsonProperty("tvdb") Integer num2, @JsonProperty("imdb") String str2, @JsonProperty("tmdb") Integer num3, @JsonProperty("tvrage") String str3) {
            this.trakt = num;
            this.slug = str;
            this.tvdb = num2;
            this.imdb = str2;
            this.tmdb = num3;
            this.tvrage = str3;
        }

        public /* synthetic */ Ids(Integer num, String str, Integer num2, String str2, Integer num3, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : num3, (i & 32) != 0 ? null : str3);
        }

        public final Integer getTrakt() {
            return this.trakt;
        }

        public final String getSlug() {
            return this.slug;
        }

        public final Integer getTvdb() {
            return this.tvdb;
        }

        public final String getImdb() {
            return this.imdb;
        }

        public final Integer getTmdb() {
            return this.tmdb;
        }

        public final String getTvrage() {
            return this.tvrage;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0099\u0001\u0010\u001f\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0004HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u001e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "", "poster", "", "", "fanart", TvContractCompat.Channels.Logo.CONTENT_DIRECTORY, "clearArt", "banner", "thumb", "screenshot", "headshot", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getPoster", "()Ljava/util/List;", "getFanart", "getLogo", "getClearArt", "getBanner", "getThumb", "getScreenshot", "getHeadshot", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Images {

        @JsonProperty("banner")
        private final List<String> banner;

        @JsonProperty("clearart")
        private final List<String> clearArt;

        @JsonProperty("fanart")
        private final List<String> fanart;

        @JsonProperty("headshot")
        private final List<String> headshot;

        @JsonProperty(TvContractCompat.Channels.Logo.CONTENT_DIRECTORY)
        private final List<String> logo;

        @JsonProperty("poster")
        private final List<String> poster;

        @JsonProperty("screenshot")
        private final List<String> screenshot;

        @JsonProperty("thumb")
        private final List<String> thumb;

        public Images() {
            this(null, null, null, null, null, null, null, null, 255, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Images copy$default(Images images, List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8, int i, Object obj) {
            if ((i & 1) != 0) {
                list = images.poster;
            }
            if ((i & 2) != 0) {
                list2 = images.fanart;
            }
            if ((i & 4) != 0) {
                list3 = images.logo;
            }
            if ((i & 8) != 0) {
                list4 = images.clearArt;
            }
            if ((i & 16) != 0) {
                list5 = images.banner;
            }
            if ((i & 32) != 0) {
                list6 = images.thumb;
            }
            if ((i & 64) != 0) {
                list7 = images.screenshot;
            }
            if ((i & 128) != 0) {
                list8 = images.headshot;
            }
            List list9 = list7;
            List list10 = list8;
            List list11 = list5;
            List list12 = list6;
            return images.copy(list, list2, list3, list4, list11, list12, list9, list10);
        }

        public final List<String> component1() {
            return this.poster;
        }

        public final List<String> component2() {
            return this.fanart;
        }

        public final List<String> component3() {
            return this.logo;
        }

        public final List<String> component4() {
            return this.clearArt;
        }

        public final List<String> component5() {
            return this.banner;
        }

        public final List<String> component6() {
            return this.thumb;
        }

        public final List<String> component7() {
            return this.screenshot;
        }

        public final List<String> component8() {
            return this.headshot;
        }

        public final Images copy(@JsonProperty("poster") List<String> poster, @JsonProperty("fanart") List<String> fanart, @JsonProperty(TvContractCompat.Channels.Logo.CONTENT_DIRECTORY) List<String> logo, @JsonProperty("clearart") List<String> clearArt, @JsonProperty("banner") List<String> banner, @JsonProperty("thumb") List<String> thumb, @JsonProperty("screenshot") List<String> screenshot, @JsonProperty("headshot") List<String> headshot) {
            return new Images(poster, fanart, logo, clearArt, banner, thumb, screenshot, headshot);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Images)) {
                return false;
            }
            Images images = (Images) other;
            return Intrinsics.areEqual(this.poster, images.poster) && Intrinsics.areEqual(this.fanart, images.fanart) && Intrinsics.areEqual(this.logo, images.logo) && Intrinsics.areEqual(this.clearArt, images.clearArt) && Intrinsics.areEqual(this.banner, images.banner) && Intrinsics.areEqual(this.thumb, images.thumb) && Intrinsics.areEqual(this.screenshot, images.screenshot) && Intrinsics.areEqual(this.headshot, images.headshot);
        }

        public int hashCode() {
            List<String> list = this.poster;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            List<String> list2 = this.fanart;
            int iHashCode2 = (iHashCode + (list2 == null ? 0 : list2.hashCode())) * 31;
            List<String> list3 = this.logo;
            int iHashCode3 = (iHashCode2 + (list3 == null ? 0 : list3.hashCode())) * 31;
            List<String> list4 = this.clearArt;
            int iHashCode4 = (iHashCode3 + (list4 == null ? 0 : list4.hashCode())) * 31;
            List<String> list5 = this.banner;
            int iHashCode5 = (iHashCode4 + (list5 == null ? 0 : list5.hashCode())) * 31;
            List<String> list6 = this.thumb;
            int iHashCode6 = (iHashCode5 + (list6 == null ? 0 : list6.hashCode())) * 31;
            List<String> list7 = this.screenshot;
            int iHashCode7 = (iHashCode6 + (list7 == null ? 0 : list7.hashCode())) * 31;
            List<String> list8 = this.headshot;
            return iHashCode7 + (list8 != null ? list8.hashCode() : 0);
        }

        public String toString() {
            return "Images(poster=" + this.poster + ", fanart=" + this.fanart + ", logo=" + this.logo + ", clearArt=" + this.clearArt + ", banner=" + this.banner + ", thumb=" + this.thumb + ", screenshot=" + this.screenshot + ", headshot=" + this.headshot + ')';
        }

        public Images(@JsonProperty("poster") List<String> list, @JsonProperty("fanart") List<String> list2, @JsonProperty(TvContractCompat.Channels.Logo.CONTENT_DIRECTORY) List<String> list3, @JsonProperty("clearart") List<String> list4, @JsonProperty("banner") List<String> list5, @JsonProperty("thumb") List<String> list6, @JsonProperty("screenshot") List<String> list7, @JsonProperty("headshot") List<String> list8) {
            this.poster = list;
            this.fanart = list2;
            this.logo = list3;
            this.clearArt = list4;
            this.banner = list5;
            this.thumb = list6;
            this.screenshot = list7;
            this.headshot = list8;
        }

        public /* synthetic */ Images(List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2, (i & 4) != 0 ? null : list3, (i & 8) != 0 ? null : list4, (i & 16) != 0 ? null : list5, (i & 32) != 0 ? null : list6, (i & 64) != 0 ? null : list7, (i & 128) != 0 ? null : list8);
        }

        public final List<String> getPoster() {
            return this.poster;
        }

        public final List<String> getFanart() {
            return this.fanart;
        }

        public final List<String> getLogo() {
            return this.logo;
        }

        public final List<String> getClearArt() {
            return this.clearArt;
        }

        public final List<String> getBanner() {
            return this.banner;
        }

        public final List<String> getThumb() {
            return this.thumb;
        }

        public final List<String> getScreenshot() {
            return this.screenshot;
        }

        public final List<String> getHeadshot() {
            return this.headshot;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$People;", "", "cast", "", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Cast;", "<init>", "(Ljava/util/List;)V", "getCast", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class People {

        @JsonProperty("cast")
        private final List<Cast> cast;

        /* JADX WARN: Multi-variable type inference failed */
        public People() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ People copy$default(People people, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = people.cast;
            }
            return people.copy(list);
        }

        public final List<Cast> component1() {
            return this.cast;
        }

        public final People copy(@JsonProperty("cast") List<Cast> cast) {
            return new People(cast);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof People) && Intrinsics.areEqual(this.cast, ((People) other).cast);
        }

        public int hashCode() {
            List<Cast> list = this.cast;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "People(cast=" + this.cast + ')';
        }

        public People(@JsonProperty("cast") List<Cast> list) {
            this.cast = list;
        }

        public /* synthetic */ People(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list);
        }

        public final List<Cast> getCast() {
            return this.cast;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u000bHÆ\u0003JP\u0010\u001e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Cast;", "", "character", "", "characters", "", "episodeCount", "", "person", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Person;", "images", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Person;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;)V", "getCharacter", "()Ljava/lang/String;", "getCharacters", "()Ljava/util/List;", "getEpisodeCount", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPerson", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Person;", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Person;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;)Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Cast;", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Cast {

        @JsonProperty("character")
        private final String character;

        @JsonProperty("characters")
        private final List<String> characters;

        @JsonProperty("episode_count")
        private final Long episodeCount;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("person")
        private final Person person;

        public Cast() {
            this(null, null, null, null, null, 31, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Cast copy$default(Cast cast, String str, List list, Long l, Person person, Images images, int i, Object obj) {
            if ((i & 1) != 0) {
                str = cast.character;
            }
            if ((i & 2) != 0) {
                list = cast.characters;
            }
            if ((i & 4) != 0) {
                l = cast.episodeCount;
            }
            if ((i & 8) != 0) {
                person = cast.person;
            }
            if ((i & 16) != 0) {
                images = cast.images;
            }
            Images images2 = images;
            Long l2 = l;
            return cast.copy(str, list, l2, person, images2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCharacter() {
            return this.character;
        }

        public final List<String> component2() {
            return this.characters;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getEpisodeCount() {
            return this.episodeCount;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Person getPerson() {
            return this.person;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        public final Cast copy(@JsonProperty("character") String character, @JsonProperty("characters") List<String> characters, @JsonProperty("episode_count") Long episodeCount, @JsonProperty("person") Person person, @JsonProperty("images") Images images) {
            return new Cast(character, characters, episodeCount, person, images);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Cast)) {
                return false;
            }
            Cast cast = (Cast) other;
            return Intrinsics.areEqual(this.character, cast.character) && Intrinsics.areEqual(this.characters, cast.characters) && Intrinsics.areEqual(this.episodeCount, cast.episodeCount) && Intrinsics.areEqual(this.person, cast.person) && Intrinsics.areEqual(this.images, cast.images);
        }

        public int hashCode() {
            String str = this.character;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            List<String> list = this.characters;
            int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
            Long l = this.episodeCount;
            int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
            Person person = this.person;
            int iHashCode4 = (iHashCode3 + (person == null ? 0 : person.hashCode())) * 31;
            Images images = this.images;
            return iHashCode4 + (images != null ? images.hashCode() : 0);
        }

        public String toString() {
            return "Cast(character=" + this.character + ", characters=" + this.characters + ", episodeCount=" + this.episodeCount + ", person=" + this.person + ", images=" + this.images + ')';
        }

        public Cast(@JsonProperty("character") String str, @JsonProperty("characters") List<String> list, @JsonProperty("episode_count") Long l, @JsonProperty("person") Person person, @JsonProperty("images") Images images) {
            this.character = str;
            this.characters = list;
            this.episodeCount = l;
            this.person = person;
            this.images = images;
        }

        public /* synthetic */ Cast(String str, List list, Long l, Person person, Images images, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : person, (i & 16) != 0 ? null : images);
        }

        public final String getCharacter() {
            return this.character;
        }

        public final List<String> getCharacters() {
            return this.characters;
        }

        public final Long getEpisodeCount() {
            return this.episodeCount;
        }

        public final Person getPerson() {
            return this.person;
        }

        public final Images getImages() {
            return this.images;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Person;", "", "name", "", "ids", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "images", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;)V", "getName", "()Ljava/lang/String;", "getIds", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Person {

        @JsonProperty("ids")
        private final Ids ids;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("name")
        private final String name;

        public Person() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ Person copy$default(Person person, String str, Ids ids, Images images, int i, Object obj) {
            if ((i & 1) != 0) {
                str = person.name;
            }
            if ((i & 2) != 0) {
                ids = person.ids;
            }
            if ((i & 4) != 0) {
                images = person.images;
            }
            return person.copy(str, ids, images);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Ids getIds() {
            return this.ids;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        public final Person copy(@JsonProperty("name") String name, @JsonProperty("ids") Ids ids, @JsonProperty("images") Images images) {
            return new Person(name, ids, images);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Person)) {
                return false;
            }
            Person person = (Person) other;
            return Intrinsics.areEqual(this.name, person.name) && Intrinsics.areEqual(this.ids, person.ids) && Intrinsics.areEqual(this.images, person.images);
        }

        public int hashCode() {
            String str = this.name;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Ids ids = this.ids;
            int iHashCode2 = (iHashCode + (ids == null ? 0 : ids.hashCode())) * 31;
            Images images = this.images;
            return iHashCode2 + (images != null ? images.hashCode() : 0);
        }

        public String toString() {
            return "Person(name=" + this.name + ", ids=" + this.ids + ", images=" + this.images + ')';
        }

        public Person(@JsonProperty("name") String str, @JsonProperty("ids") Ids ids, @JsonProperty("images") Images images) {
            this.name = str;
            this.ids = ids;
            this.images = images;
        }

        public /* synthetic */ Person(String str, Ids ids, Images images, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : ids, (i & 4) != 0 ? null : images);
        }

        public final String getName() {
            return this.name;
        }

        public final Ids getIds() {
            return this.ids;
        }

        public final Images getImages() {
            return this.images;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B©\u0001\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u0011\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u00105\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u0010(J\u000b\u00107\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\tHÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J°\u0001\u0010:\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010;J\u0014\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010?\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010@\u001a\u00020\tHÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001b\u0010\u0019R\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0018\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b%\u0010\u0019R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b,\u0010\u0019¨\u0006A"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Seasons;", "", "airedEpisodes", "", "episodeCount", "episodes", "", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$TraktEpisode;", "firstAired", "", "ids", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "images", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "network", "number", "overview", "rating", "", "title", "updatedAt", "votes", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAiredEpisodes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodeCount", "getEpisodes", "()Ljava/util/List;", "getFirstAired", "()Ljava/lang/String;", "getIds", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "getNetwork", "getNumber", "getOverview", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getTitle", "getUpdatedAt", "getVotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Seasons;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Seasons {

        @JsonProperty("aired_episodes")
        private final Integer airedEpisodes;

        @JsonProperty("episode_count")
        private final Integer episodeCount;

        @JsonProperty("episodes")
        private final List<TraktEpisode> episodes;

        @JsonProperty("first_aired")
        private final String firstAired;

        @JsonProperty("ids")
        private final Ids ids;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("network")
        private final String network;

        @JsonProperty("number")
        private final Integer number;

        @JsonProperty("overview")
        private final String overview;

        @JsonProperty("rating")
        private final Double rating;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("updated_at")
        private final String updatedAt;

        @JsonProperty("votes")
        private final Integer votes;

        public Seasons() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Seasons copy$default(Seasons seasons, Integer num, Integer num2, List list, String str, Ids ids, Images images, String str2, Integer num3, String str3, Double d, String str4, String str5, Integer num4, int i, Object obj) {
            if ((i & 1) != 0) {
                num = seasons.airedEpisodes;
            }
            return seasons.copy(num, (i & 2) != 0 ? seasons.episodeCount : num2, (i & 4) != 0 ? seasons.episodes : list, (i & 8) != 0 ? seasons.firstAired : str, (i & 16) != 0 ? seasons.ids : ids, (i & 32) != 0 ? seasons.images : images, (i & 64) != 0 ? seasons.network : str2, (i & 128) != 0 ? seasons.number : num3, (i & 256) != 0 ? seasons.overview : str3, (i & 512) != 0 ? seasons.rating : d, (i & 1024) != 0 ? seasons.title : str4, (i & 2048) != 0 ? seasons.updatedAt : str5, (i & 4096) != 0 ? seasons.votes : num4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getAiredEpisodes() {
            return this.airedEpisodes;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Double getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Integer getVotes() {
            return this.votes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getEpisodeCount() {
            return this.episodeCount;
        }

        public final List<TraktEpisode> component3() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFirstAired() {
            return this.firstAired;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Ids getIds() {
            return this.ids;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getNetwork() {
            return this.network;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getOverview() {
            return this.overview;
        }

        public final Seasons copy(@JsonProperty("aired_episodes") Integer airedEpisodes, @JsonProperty("episode_count") Integer episodeCount, @JsonProperty("episodes") List<TraktEpisode> episodes, @JsonProperty("first_aired") String firstAired, @JsonProperty("ids") Ids ids, @JsonProperty("images") Images images, @JsonProperty("network") String network, @JsonProperty("number") Integer number, @JsonProperty("overview") String overview, @JsonProperty("rating") Double rating, @JsonProperty("title") String title, @JsonProperty("updated_at") String updatedAt, @JsonProperty("votes") Integer votes) {
            return new Seasons(airedEpisodes, episodeCount, episodes, firstAired, ids, images, network, number, overview, rating, title, updatedAt, votes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Seasons)) {
                return false;
            }
            Seasons seasons = (Seasons) other;
            return Intrinsics.areEqual(this.airedEpisodes, seasons.airedEpisodes) && Intrinsics.areEqual(this.episodeCount, seasons.episodeCount) && Intrinsics.areEqual(this.episodes, seasons.episodes) && Intrinsics.areEqual(this.firstAired, seasons.firstAired) && Intrinsics.areEqual(this.ids, seasons.ids) && Intrinsics.areEqual(this.images, seasons.images) && Intrinsics.areEqual(this.network, seasons.network) && Intrinsics.areEqual(this.number, seasons.number) && Intrinsics.areEqual(this.overview, seasons.overview) && Intrinsics.areEqual((Object) this.rating, (Object) seasons.rating) && Intrinsics.areEqual(this.title, seasons.title) && Intrinsics.areEqual(this.updatedAt, seasons.updatedAt) && Intrinsics.areEqual(this.votes, seasons.votes);
        }

        public int hashCode() {
            Integer num = this.airedEpisodes;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.episodeCount;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            List<TraktEpisode> list = this.episodes;
            int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
            String str = this.firstAired;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            Ids ids = this.ids;
            int iHashCode5 = (iHashCode4 + (ids == null ? 0 : ids.hashCode())) * 31;
            Images images = this.images;
            int iHashCode6 = (iHashCode5 + (images == null ? 0 : images.hashCode())) * 31;
            String str2 = this.network;
            int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num3 = this.number;
            int iHashCode8 = (iHashCode7 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str3 = this.overview;
            int iHashCode9 = (iHashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Double d = this.rating;
            int iHashCode10 = (iHashCode9 + (d == null ? 0 : d.hashCode())) * 31;
            String str4 = this.title;
            int iHashCode11 = (iHashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.updatedAt;
            int iHashCode12 = (iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Integer num4 = this.votes;
            return iHashCode12 + (num4 != null ? num4.hashCode() : 0);
        }

        public String toString() {
            return "Seasons(airedEpisodes=" + this.airedEpisodes + ", episodeCount=" + this.episodeCount + ", episodes=" + this.episodes + ", firstAired=" + this.firstAired + ", ids=" + this.ids + ", images=" + this.images + ", network=" + this.network + ", number=" + this.number + ", overview=" + this.overview + ", rating=" + this.rating + ", title=" + this.title + ", updatedAt=" + this.updatedAt + ", votes=" + this.votes + ')';
        }

        public Seasons(@JsonProperty("aired_episodes") Integer num, @JsonProperty("episode_count") Integer num2, @JsonProperty("episodes") List<TraktEpisode> list, @JsonProperty("first_aired") String str, @JsonProperty("ids") Ids ids, @JsonProperty("images") Images images, @JsonProperty("network") String str2, @JsonProperty("number") Integer num3, @JsonProperty("overview") String str3, @JsonProperty("rating") Double d, @JsonProperty("title") String str4, @JsonProperty("updated_at") String str5, @JsonProperty("votes") Integer num4) {
            this.airedEpisodes = num;
            this.episodeCount = num2;
            this.episodes = list;
            this.firstAired = str;
            this.ids = ids;
            this.images = images;
            this.network = str2;
            this.number = num3;
            this.overview = str3;
            this.rating = d;
            this.title = str4;
            this.updatedAt = str5;
            this.votes = num4;
        }

        public /* synthetic */ Seasons(Integer num, Integer num2, List list, String str, Ids ids, Images images, String str2, Integer num3, String str3, Double d, String str4, String str5, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : ids, (i & 32) != 0 ? null : images, (i & 64) != 0 ? null : str2, (i & 128) != 0 ? null : num3, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : d, (i & 1024) != 0 ? null : str4, (i & 2048) != 0 ? null : str5, (i & 4096) != 0 ? null : num4);
        }

        public final Integer getAiredEpisodes() {
            return this.airedEpisodes;
        }

        public final Integer getEpisodeCount() {
            return this.episodeCount;
        }

        public final List<TraktEpisode> getEpisodes() {
            return this.episodes;
        }

        public final String getFirstAired() {
            return this.firstAired;
        }

        public final Ids getIds() {
            return this.ids;
        }

        public final Images getImages() {
            return this.images;
        }

        public final String getNetwork() {
            return this.network;
        }

        public final Integer getNumber() {
            return this.number;
        }

        public final String getOverview() {
            return this.overview;
        }

        public final Double getRating() {
            return this.rating;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final Integer getVotes() {
            return this.votes;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BÁ\u0001\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0017\u0010\u0018J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00108\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0002\u0010)J\u0010\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010<\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001cJÈ\u0001\u0010?\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010@J\u0014\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010D\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010E\u001a\u00020\u0004HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b%\u0010\u001cR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b&\u0010\u001cR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b+\u0010\u001cR\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b,\u0010\u001cR\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001fR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001fR\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b/\u0010\u001c¨\u0006F"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$TraktEpisode;", "", "availableTranslations", "", "", "commentCount", "", "episodeType", "firstAired", "ids", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "images", "Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "number", "numberAbs", "overview", "rating", "", "runtime", "season", "title", "updatedAt", "votes", "<init>", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAvailableTranslations", "()Ljava/util/List;", "getCommentCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodeType", "()Ljava/lang/String;", "getFirstAired", "getIds", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;", "getNumber", "getNumberAbs", "getOverview", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRuntime", "getSeason", "getTitle", "getUpdatedAt", "getVotes", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Ids;Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$Images;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$TraktEpisode;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TraktEpisode {

        @JsonProperty("available_translations")
        private final List<String> availableTranslations;

        @JsonProperty("comment_count")
        private final Integer commentCount;

        @JsonProperty("episode_type")
        private final String episodeType;

        @JsonProperty("first_aired")
        private final String firstAired;

        @JsonProperty("ids")
        private final Ids ids;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("number")
        private final Integer number;

        @JsonProperty("number_abs")
        private final Integer numberAbs;

        @JsonProperty("overview")
        private final String overview;

        @JsonProperty("rating")
        private final Double rating;

        @JsonProperty("runtime")
        private final Integer runtime;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("updated_at")
        private final String updatedAt;

        @JsonProperty("votes")
        private final Integer votes;

        public TraktEpisode() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
        }

        public final List<String> component1() {
            return this.availableTranslations;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Double getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getRuntime() {
            return this.runtime;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Integer getVotes() {
            return this.votes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getCommentCount() {
            return this.commentCount;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEpisodeType() {
            return this.episodeType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFirstAired() {
            return this.firstAired;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Ids getIds() {
            return this.ids;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getNumber() {
            return this.number;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getNumberAbs() {
            return this.numberAbs;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getOverview() {
            return this.overview;
        }

        public final TraktEpisode copy(@JsonProperty("available_translations") List<String> availableTranslations, @JsonProperty("comment_count") Integer commentCount, @JsonProperty("episode_type") String episodeType, @JsonProperty("first_aired") String firstAired, @JsonProperty("ids") Ids ids, @JsonProperty("images") Images images, @JsonProperty("number") Integer number, @JsonProperty("number_abs") Integer numberAbs, @JsonProperty("overview") String overview, @JsonProperty("rating") Double rating, @JsonProperty("runtime") Integer runtime, @JsonProperty("season") Integer season, @JsonProperty("title") String title, @JsonProperty("updated_at") String updatedAt, @JsonProperty("votes") Integer votes) {
            return new TraktEpisode(availableTranslations, commentCount, episodeType, firstAired, ids, images, number, numberAbs, overview, rating, runtime, season, title, updatedAt, votes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TraktEpisode)) {
                return false;
            }
            TraktEpisode traktEpisode = (TraktEpisode) other;
            return Intrinsics.areEqual(this.availableTranslations, traktEpisode.availableTranslations) && Intrinsics.areEqual(this.commentCount, traktEpisode.commentCount) && Intrinsics.areEqual(this.episodeType, traktEpisode.episodeType) && Intrinsics.areEqual(this.firstAired, traktEpisode.firstAired) && Intrinsics.areEqual(this.ids, traktEpisode.ids) && Intrinsics.areEqual(this.images, traktEpisode.images) && Intrinsics.areEqual(this.number, traktEpisode.number) && Intrinsics.areEqual(this.numberAbs, traktEpisode.numberAbs) && Intrinsics.areEqual(this.overview, traktEpisode.overview) && Intrinsics.areEqual((Object) this.rating, (Object) traktEpisode.rating) && Intrinsics.areEqual(this.runtime, traktEpisode.runtime) && Intrinsics.areEqual(this.season, traktEpisode.season) && Intrinsics.areEqual(this.title, traktEpisode.title) && Intrinsics.areEqual(this.updatedAt, traktEpisode.updatedAt) && Intrinsics.areEqual(this.votes, traktEpisode.votes);
        }

        public int hashCode() {
            List<String> list = this.availableTranslations;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            Integer num = this.commentCount;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            String str = this.episodeType;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.firstAired;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Ids ids = this.ids;
            int iHashCode5 = (iHashCode4 + (ids == null ? 0 : ids.hashCode())) * 31;
            Images images = this.images;
            int iHashCode6 = (iHashCode5 + (images == null ? 0 : images.hashCode())) * 31;
            Integer num2 = this.number;
            int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.numberAbs;
            int iHashCode8 = (iHashCode7 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str3 = this.overview;
            int iHashCode9 = (iHashCode8 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Double d = this.rating;
            int iHashCode10 = (iHashCode9 + (d == null ? 0 : d.hashCode())) * 31;
            Integer num4 = this.runtime;
            int iHashCode11 = (iHashCode10 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.season;
            int iHashCode12 = (iHashCode11 + (num5 == null ? 0 : num5.hashCode())) * 31;
            String str4 = this.title;
            int iHashCode13 = (iHashCode12 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.updatedAt;
            int iHashCode14 = (iHashCode13 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Integer num6 = this.votes;
            return iHashCode14 + (num6 != null ? num6.hashCode() : 0);
        }

        public String toString() {
            return "TraktEpisode(availableTranslations=" + this.availableTranslations + ", commentCount=" + this.commentCount + ", episodeType=" + this.episodeType + ", firstAired=" + this.firstAired + ", ids=" + this.ids + ", images=" + this.images + ", number=" + this.number + ", numberAbs=" + this.numberAbs + ", overview=" + this.overview + ", rating=" + this.rating + ", runtime=" + this.runtime + ", season=" + this.season + ", title=" + this.title + ", updatedAt=" + this.updatedAt + ", votes=" + this.votes + ')';
        }

        public TraktEpisode(@JsonProperty("available_translations") List<String> list, @JsonProperty("comment_count") Integer num, @JsonProperty("episode_type") String str, @JsonProperty("first_aired") String str2, @JsonProperty("ids") Ids ids, @JsonProperty("images") Images images, @JsonProperty("number") Integer num2, @JsonProperty("number_abs") Integer num3, @JsonProperty("overview") String str3, @JsonProperty("rating") Double d, @JsonProperty("runtime") Integer num4, @JsonProperty("season") Integer num5, @JsonProperty("title") String str4, @JsonProperty("updated_at") String str5, @JsonProperty("votes") Integer num6) {
            this.availableTranslations = list;
            this.commentCount = num;
            this.episodeType = str;
            this.firstAired = str2;
            this.ids = ids;
            this.images = images;
            this.number = num2;
            this.numberAbs = num3;
            this.overview = str3;
            this.rating = d;
            this.runtime = num4;
            this.season = num5;
            this.title = str4;
            this.updatedAt = str5;
            this.votes = num6;
        }

        public /* synthetic */ TraktEpisode(List list, Integer num, String str, String str2, Ids ids, Images images, Integer num2, Integer num3, String str3, Double d, Integer num4, Integer num5, String str4, String str5, Integer num6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : ids, (i & 32) != 0 ? null : images, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : num3, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : d, (i & 1024) != 0 ? null : num4, (i & 2048) != 0 ? null : num5, (i & 4096) != 0 ? null : str4, (i & 8192) != 0 ? null : str5, (i & 16384) != 0 ? null : num6);
        }

        public final List<String> getAvailableTranslations() {
            return this.availableTranslations;
        }

        public final Integer getCommentCount() {
            return this.commentCount;
        }

        public final String getEpisodeType() {
            return this.episodeType;
        }

        public final String getFirstAired() {
            return this.firstAired;
        }

        public final Ids getIds() {
            return this.ids;
        }

        public final Images getImages() {
            return this.images;
        }

        public final Integer getNumber() {
            return this.number;
        }

        public final Integer getNumberAbs() {
            return this.numberAbs;
        }

        public final String getOverview() {
            return this.overview;
        }

        public final Double getRating() {
            return this.rating;
        }

        public final Integer getRuntime() {
            return this.runtime;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final Integer getVotes() {
            return this.votes;
        }
    }

    /* JADX INFO: compiled from: TraktProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\bD\b\u0086\b\u0018\u00002\u00020\u0001B«\u0002\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u001b\u001a\u00020\u0014\u0012\b\b\u0003\u0010\u001c\u001a\u00020\u0014\u0012\b\b\u0003\u0010\u001d\u001a\u00020\u0014¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010C\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010G\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010H\u001a\u00020\u0014HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u0010\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u0010K\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010O\u001a\u00020\u0014HÆ\u0003J\t\u0010P\u001a\u00020\u0014HÆ\u0003J\t\u0010Q\u001a\u00020\u0014HÆ\u0003J²\u0002\u0010R\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0013\u001a\u00020\u00142\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u001b\u001a\u00020\u00142\b\b\u0003\u0010\u001c\u001a\u00020\u00142\b\b\u0003\u0010\u001d\u001a\u00020\u0014HÆ\u0001¢\u0006\u0002\u0010SJ\u0014\u0010T\u001a\u00020\u00142\b\u0010U\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010V\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010W\u001a\u00020\u0006HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b#\u0010!R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b&\u0010!R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b(\u0010!R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010%R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010%R\u001a\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b+\u0010!R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b,\u0010!R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010%R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010%R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010%R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b0\u0010!R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010%R\u0016\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u00102R\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b3\u0010!R\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b4\u0010!R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010%R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010%R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010%R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010%R\u0016\u0010\u001b\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u00102R\u0016\u0010\u001c\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u00102R\u0016\u0010\u001d\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u00102¨\u0006X"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$LinkData;", "", "id", "", "traktId", "traktSlug", "", "tmdbId", "imdbId", "tvdbId", "tvrageId", "type", "season", "episode", "aniId", "animeId", "title", "year", "orgTitle", "isAnime", "", "airedYear", "lastSeason", "epsTitle", "jpTitle", "date", "airedDate", "isAsian", "isBollywood", "isCartoon", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTraktId", "getTraktSlug", "()Ljava/lang/String;", "getTmdbId", "getImdbId", "getTvdbId", "getTvrageId", "getType", "getSeason", "getEpisode", "getAniId", "getAnimeId", "getTitle", "getYear", "getOrgTitle", "()Z", "getAiredYear", "getLastSeason", "getEpsTitle", "getJpTitle", "getDate", "getAiredDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)Lcom/lagradost/cloudstream3/metaproviders/TraktProvider$LinkData;", "equals", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LinkData {

        @JsonProperty("aired_date")
        private final String airedDate;

        @JsonProperty("aired_year")
        private final Integer airedYear;

        @JsonProperty("ani_id")
        private final String aniId;

        @JsonProperty("anime_id")
        private final String animeId;

        @JsonProperty("date")
        private final String date;

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("eps_title")
        private final String epsTitle;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("imdb_id")
        private final String imdbId;

        @JsonProperty("is_anime")
        private final boolean isAnime;

        @JsonProperty("is_asian")
        private final boolean isAsian;

        @JsonProperty("is_bollywood")
        private final boolean isBollywood;

        @JsonProperty("is_cartoon")
        private final boolean isCartoon;

        @JsonProperty("jp_title")
        private final String jpTitle;

        @JsonProperty("last_season")
        private final Integer lastSeason;

        @JsonProperty("org_title")
        private final String orgTitle;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("tmdb_id")
        private final Integer tmdbId;

        @JsonProperty("trakt_id")
        private final Integer traktId;

        @JsonProperty("trakt_slug")
        private final String traktSlug;

        @JsonProperty("tvdb_id")
        private final Integer tvdbId;

        @JsonProperty("tvrage_id")
        private final String tvrageId;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("year")
        private final Integer year;

        public LinkData() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, false, false, false, 33554431, null);
        }

        public static /* synthetic */ LinkData copy$default(LinkData linkData, Integer num, Integer num2, String str, Integer num3, String str2, Integer num4, String str3, String str4, Integer num5, Integer num6, String str5, String str6, String str7, Integer num7, String str8, boolean z, Integer num8, Integer num9, String str9, String str10, String str11, String str12, boolean z2, boolean z3, boolean z4, int i, Object obj) {
            boolean z5;
            boolean z6;
            Integer num10 = (i & 1) != 0 ? linkData.id : num;
            Integer num11 = (i & 2) != 0 ? linkData.traktId : num2;
            String str13 = (i & 4) != 0 ? linkData.traktSlug : str;
            Integer num12 = (i & 8) != 0 ? linkData.tmdbId : num3;
            String str14 = (i & 16) != 0 ? linkData.imdbId : str2;
            Integer num13 = (i & 32) != 0 ? linkData.tvdbId : num4;
            String str15 = (i & 64) != 0 ? linkData.tvrageId : str3;
            String str16 = (i & 128) != 0 ? linkData.type : str4;
            Integer num14 = (i & 256) != 0 ? linkData.season : num5;
            Integer num15 = (i & 512) != 0 ? linkData.episode : num6;
            String str17 = (i & 1024) != 0 ? linkData.aniId : str5;
            String str18 = (i & 2048) != 0 ? linkData.animeId : str6;
            String str19 = (i & 4096) != 0 ? linkData.title : str7;
            Integer num16 = (i & 8192) != 0 ? linkData.year : num7;
            Integer num17 = num10;
            String str20 = (i & 16384) != 0 ? linkData.orgTitle : str8;
            boolean z7 = (i & 32768) != 0 ? linkData.isAnime : z;
            Integer num18 = (i & 65536) != 0 ? linkData.airedYear : num8;
            Integer num19 = (i & 131072) != 0 ? linkData.lastSeason : num9;
            String str21 = (i & 262144) != 0 ? linkData.epsTitle : str9;
            String str22 = (i & 524288) != 0 ? linkData.jpTitle : str10;
            String str23 = (i & 1048576) != 0 ? linkData.date : str11;
            String str24 = (i & 2097152) != 0 ? linkData.airedDate : str12;
            boolean z8 = (i & 4194304) != 0 ? linkData.isAsian : z2;
            boolean z9 = (i & 8388608) != 0 ? linkData.isBollywood : z3;
            if ((i & 16777216) != 0) {
                z6 = z9;
                z5 = linkData.isCartoon;
            } else {
                z5 = z4;
                z6 = z9;
            }
            return linkData.copy(num17, num11, str13, num12, str14, num13, str15, str16, num14, num15, str17, str18, str19, num16, str20, z7, num18, num19, str21, str22, str23, str24, z8, z6, z5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getAniId() {
            return this.aniId;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getAnimeId() {
            return this.animeId;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getOrgTitle() {
            return this.orgTitle;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final boolean getIsAnime() {
            return this.isAnime;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final Integer getAiredYear() {
            return this.airedYear;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final Integer getLastSeason() {
            return this.lastSeason;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getEpsTitle() {
            return this.epsTitle;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTraktId() {
            return this.traktId;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getJpTitle() {
            return this.jpTitle;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final String getDate() {
            return this.date;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final String getAiredDate() {
            return this.airedDate;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final boolean getIsAsian() {
            return this.isAsian;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final boolean getIsBollywood() {
            return this.isBollywood;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final boolean getIsCartoon() {
            return this.isCartoon;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTraktSlug() {
            return this.traktSlug;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getTvdbId() {
            return this.tvdbId;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getTvrageId() {
            return this.tvrageId;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        public final LinkData copy(@JsonProperty("id") Integer id, @JsonProperty("trakt_id") Integer traktId, @JsonProperty("trakt_slug") String traktSlug, @JsonProperty("tmdb_id") Integer tmdbId, @JsonProperty("imdb_id") String imdbId, @JsonProperty("tvdb_id") Integer tvdbId, @JsonProperty("tvrage_id") String tvrageId, @JsonProperty("type") String type, @JsonProperty("season") Integer season, @JsonProperty("episode") Integer episode, @JsonProperty("ani_id") String aniId, @JsonProperty("anime_id") String animeId, @JsonProperty("title") String title, @JsonProperty("year") Integer year, @JsonProperty("org_title") String orgTitle, @JsonProperty("is_anime") boolean isAnime, @JsonProperty("aired_year") Integer airedYear, @JsonProperty("last_season") Integer lastSeason, @JsonProperty("eps_title") String epsTitle, @JsonProperty("jp_title") String jpTitle, @JsonProperty("date") String date, @JsonProperty("aired_date") String airedDate, @JsonProperty("is_asian") boolean isAsian, @JsonProperty("is_bollywood") boolean isBollywood, @JsonProperty("is_cartoon") boolean isCartoon) {
            return new LinkData(id, traktId, traktSlug, tmdbId, imdbId, tvdbId, tvrageId, type, season, episode, aniId, animeId, title, year, orgTitle, isAnime, airedYear, lastSeason, epsTitle, jpTitle, date, airedDate, isAsian, isBollywood, isCartoon);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LinkData)) {
                return false;
            }
            LinkData linkData = (LinkData) other;
            return Intrinsics.areEqual(this.id, linkData.id) && Intrinsics.areEqual(this.traktId, linkData.traktId) && Intrinsics.areEqual(this.traktSlug, linkData.traktSlug) && Intrinsics.areEqual(this.tmdbId, linkData.tmdbId) && Intrinsics.areEqual(this.imdbId, linkData.imdbId) && Intrinsics.areEqual(this.tvdbId, linkData.tvdbId) && Intrinsics.areEqual(this.tvrageId, linkData.tvrageId) && Intrinsics.areEqual(this.type, linkData.type) && Intrinsics.areEqual(this.season, linkData.season) && Intrinsics.areEqual(this.episode, linkData.episode) && Intrinsics.areEqual(this.aniId, linkData.aniId) && Intrinsics.areEqual(this.animeId, linkData.animeId) && Intrinsics.areEqual(this.title, linkData.title) && Intrinsics.areEqual(this.year, linkData.year) && Intrinsics.areEqual(this.orgTitle, linkData.orgTitle) && this.isAnime == linkData.isAnime && Intrinsics.areEqual(this.airedYear, linkData.airedYear) && Intrinsics.areEqual(this.lastSeason, linkData.lastSeason) && Intrinsics.areEqual(this.epsTitle, linkData.epsTitle) && Intrinsics.areEqual(this.jpTitle, linkData.jpTitle) && Intrinsics.areEqual(this.date, linkData.date) && Intrinsics.areEqual(this.airedDate, linkData.airedDate) && this.isAsian == linkData.isAsian && this.isBollywood == linkData.isBollywood && this.isCartoon == linkData.isCartoon;
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.traktId;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str = this.traktSlug;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Integer num3 = this.tmdbId;
            int iHashCode4 = (iHashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str2 = this.imdbId;
            int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num4 = this.tvdbId;
            int iHashCode6 = (iHashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
            String str3 = this.tvrageId;
            int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.type;
            int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num5 = this.season;
            int iHashCode9 = (iHashCode8 + (num5 == null ? 0 : num5.hashCode())) * 31;
            Integer num6 = this.episode;
            int iHashCode10 = (iHashCode9 + (num6 == null ? 0 : num6.hashCode())) * 31;
            String str5 = this.aniId;
            int iHashCode11 = (iHashCode10 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.animeId;
            int iHashCode12 = (iHashCode11 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.title;
            int iHashCode13 = (iHashCode12 + (str7 == null ? 0 : str7.hashCode())) * 31;
            Integer num7 = this.year;
            int iHashCode14 = (iHashCode13 + (num7 == null ? 0 : num7.hashCode())) * 31;
            String str8 = this.orgTitle;
            int iHashCode15 = (((iHashCode14 + (str8 == null ? 0 : str8.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isAnime)) * 31;
            Integer num8 = this.airedYear;
            int iHashCode16 = (iHashCode15 + (num8 == null ? 0 : num8.hashCode())) * 31;
            Integer num9 = this.lastSeason;
            int iHashCode17 = (iHashCode16 + (num9 == null ? 0 : num9.hashCode())) * 31;
            String str9 = this.epsTitle;
            int iHashCode18 = (iHashCode17 + (str9 == null ? 0 : str9.hashCode())) * 31;
            String str10 = this.jpTitle;
            int iHashCode19 = (iHashCode18 + (str10 == null ? 0 : str10.hashCode())) * 31;
            String str11 = this.date;
            int iHashCode20 = (iHashCode19 + (str11 == null ? 0 : str11.hashCode())) * 31;
            String str12 = this.airedDate;
            return ((((((iHashCode20 + (str12 != null ? str12.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isAsian)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isBollywood)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isCartoon);
        }

        public String toString() {
            return "LinkData(id=" + this.id + ", traktId=" + this.traktId + ", traktSlug=" + this.traktSlug + ", tmdbId=" + this.tmdbId + ", imdbId=" + this.imdbId + ", tvdbId=" + this.tvdbId + ", tvrageId=" + this.tvrageId + ", type=" + this.type + ", season=" + this.season + ", episode=" + this.episode + ", aniId=" + this.aniId + ", animeId=" + this.animeId + ", title=" + this.title + ", year=" + this.year + ", orgTitle=" + this.orgTitle + ", isAnime=" + this.isAnime + ", airedYear=" + this.airedYear + ", lastSeason=" + this.lastSeason + ", epsTitle=" + this.epsTitle + ", jpTitle=" + this.jpTitle + ", date=" + this.date + ", airedDate=" + this.airedDate + ", isAsian=" + this.isAsian + ", isBollywood=" + this.isBollywood + ", isCartoon=" + this.isCartoon + ')';
        }

        public LinkData(@JsonProperty("id") Integer num, @JsonProperty("trakt_id") Integer num2, @JsonProperty("trakt_slug") String str, @JsonProperty("tmdb_id") Integer num3, @JsonProperty("imdb_id") String str2, @JsonProperty("tvdb_id") Integer num4, @JsonProperty("tvrage_id") String str3, @JsonProperty("type") String str4, @JsonProperty("season") Integer num5, @JsonProperty("episode") Integer num6, @JsonProperty("ani_id") String str5, @JsonProperty("anime_id") String str6, @JsonProperty("title") String str7, @JsonProperty("year") Integer num7, @JsonProperty("org_title") String str8, @JsonProperty("is_anime") boolean z, @JsonProperty("aired_year") Integer num8, @JsonProperty("last_season") Integer num9, @JsonProperty("eps_title") String str9, @JsonProperty("jp_title") String str10, @JsonProperty("date") String str11, @JsonProperty("aired_date") String str12, @JsonProperty("is_asian") boolean z2, @JsonProperty("is_bollywood") boolean z3, @JsonProperty("is_cartoon") boolean z4) {
            this.id = num;
            this.traktId = num2;
            this.traktSlug = str;
            this.tmdbId = num3;
            this.imdbId = str2;
            this.tvdbId = num4;
            this.tvrageId = str3;
            this.type = str4;
            this.season = num5;
            this.episode = num6;
            this.aniId = str5;
            this.animeId = str6;
            this.title = str7;
            this.year = num7;
            this.orgTitle = str8;
            this.isAnime = z;
            this.airedYear = num8;
            this.lastSeason = num9;
            this.epsTitle = str9;
            this.jpTitle = str10;
            this.date = str11;
            this.airedDate = str12;
            this.isAsian = z2;
            this.isBollywood = z3;
            this.isCartoon = z4;
        }

        public /* synthetic */ LinkData(Integer num, Integer num2, String str, Integer num3, String str2, Integer num4, String str3, String str4, Integer num5, Integer num6, String str5, String str6, String str7, Integer num7, String str8, boolean z, Integer num8, Integer num9, String str9, String str10, String str11, String str12, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : num4, (i & 64) != 0 ? null : str3, (i & 128) != 0 ? null : str4, (i & 256) != 0 ? null : num5, (i & 512) != 0 ? null : num6, (i & 1024) != 0 ? null : str5, (i & 2048) != 0 ? null : str6, (i & 4096) != 0 ? null : str7, (i & 8192) != 0 ? null : num7, (i & 16384) != 0 ? null : str8, (i & 32768) != 0 ? false : z, (i & 65536) != 0 ? null : num8, (i & 131072) != 0 ? null : num9, (i & 262144) != 0 ? null : str9, (i & 524288) != 0 ? null : str10, (i & 1048576) != 0 ? null : str11, (i & 2097152) != 0 ? null : str12, (i & 4194304) != 0 ? false : z2, (i & 8388608) != 0 ? false : z3, (i & 16777216) != 0 ? false : z4);
        }

        public final Integer getId() {
            return this.id;
        }

        public final Integer getTraktId() {
            return this.traktId;
        }

        public final String getTraktSlug() {
            return this.traktSlug;
        }

        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final Integer getTvdbId() {
            return this.tvdbId;
        }

        public final String getTvrageId() {
            return this.tvrageId;
        }

        public final String getType() {
            return this.type;
        }

        public final Integer getSeason() {
            return this.season;
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final String getAniId() {
            return this.aniId;
        }

        public final String getAnimeId() {
            return this.animeId;
        }

        public final String getTitle() {
            return this.title;
        }

        public final Integer getYear() {
            return this.year;
        }

        public final String getOrgTitle() {
            return this.orgTitle;
        }

        public final boolean isAnime() {
            return this.isAnime;
        }

        public final Integer getAiredYear() {
            return this.airedYear;
        }

        public final Integer getLastSeason() {
            return this.lastSeason;
        }

        public final String getEpsTitle() {
            return this.epsTitle;
        }

        public final String getJpTitle() {
            return this.jpTitle;
        }

        public final String getDate() {
            return this.date;
        }

        public final String getAiredDate() {
            return this.airedDate;
        }

        public final boolean isAsian() {
            return this.isAsian;
        }

        public final boolean isBollywood() {
            return this.isBollywood;
        }

        public final boolean isCartoon() {
            return this.isCartoon;
        }
    }
}
