package com.lagradost.cloudstream3.metaproviders;

import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.BuildConfig;
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
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ProviderType;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.mozilla.classfile.ByteCode;

/* JADX INFO: compiled from: MyDramaList.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\b&\u0018\u0000 :2\u00020\u0001:\u0013:;<=>?@ABCDEFGHIJKLB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u001a\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0096@¢\u0006\u0002\u0010!J\u001e\u0010\"\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u00182\u0006\u0010$\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010%J\f\u0010&\u001a\u00020#*\u00020'H\u0002J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010%J\u001a\u0010+\u001a\u00020)*\u00020,2\u0006\u0010-\u001a\u00020.H\u0082@¢\u0006\u0002\u0010/J\u001a\u00100\u001a\u00020)*\u00020)2\u0006\u00101\u001a\u00020,H\u0082@¢\u0006\u0002\u00102J\u0014\u00103\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0002J\u0014\u00106\u001a\u0004\u0018\u00010\u00052\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0002J\u0018\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0018*\u00020,H\u0082@¢\u0006\u0002\u00109R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006M"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI;", "Lcom/lagradost/cloudstream3/MainAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "hasMainPage", "", "getHasMainPage", "()Z", "providerType", "Lcom/lagradost/cloudstream3/ProviderType;", "getProviderType", "()Lcom/lagradost/cloudstream3/ProviderType;", "supportedTypes", "", "Lcom/lagradost/cloudstream3/TvType;", "getSupportedTypes", "()Ljava/util/Set;", "mainPage", "", "Lcom/lagradost/cloudstream3/MainPageData;", "getMainPage", "()Ljava/util/List;", "Lcom/lagradost/cloudstream3/HomePageResponse;", "page", "", "request", "Lcom/lagradost/cloudstream3/MainPageRequest;", "(ILcom/lagradost/cloudstream3/MainPageRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "Lcom/lagradost/cloudstream3/SearchResponse;", SearchIntents.EXTRA_QUERY, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSearchResponse", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "load", "Lcom/lagradost/cloudstream3/LoadResponse;", ImagesContract.URL, "toLoadResponse", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;", "data", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Data;", "(Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Data;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "applyMedia", LinkHeader.Parameters.Media, "(Lcom/lagradost/cloudstream3/LoadResponse;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStatus", "Lcom/lagradost/cloudstream3/ShowStatus;", NotificationCompat.CATEGORY_STATUS, "fixCertification", "fetchEpisodes", "Lcom/lagradost/cloudstream3/Episode;", "(Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "MyDramaListInterceptor", "Data", "MediaSummary", "Images", "Media", "Genre", "Tag", "Source", "Trailer", "Credits", "Cast", "Crew", "ShowEpisodesItem", "ShowEpisode", "TrailerRoot", "TrailerNode", "TrailerDetails", "LinkData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class MyDramaListAPI extends MainAPI {
    public static final String API_HOST = "https://api.mydramalist.com/v1";
    public static final String SITE_HOST = "https://mydramalist.com";
    public static final String TAG = "MyDramaList";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String API_KEY = BuildConfig.INSTANCE.getMDL_API_KEY();
    private static final MyDramaListInterceptor headerInterceptor = new MyDramaListInterceptor();
    private String name = TAG;
    private final boolean hasMainPage = true;
    private final ProviderType providerType = ProviderType.MetaProvider;
    private final Set<TvType> supportedTypes = SetsKt.setOf((Object[]) new TvType[]{TvType.Movie, TvType.TvSeries, TvType.AsianDrama});
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf((Pair<String, String>[]) new Pair[]{TuplesKt.to("https://api.mydramalist.com/v1/titles/trending?type=shows", "Trending Shows This week"), TuplesKt.to("https://api.mydramalist.com/v1/titles/top_airing?type=shows", "Top Airing Shows"), TuplesKt.to("https://api.mydramalist.com/v1/titles/upcoming?type=shows", "Upcoming Shows"), TuplesKt.to("https://api.mydramalist.com/v1/titles/trending?type=movies", "Trending Movies This week"), TuplesKt.to("https://api.mydramalist.com/v1/titles/top_movies?type=movies", "Top Movies"), TuplesKt.to("https://api.mydramalist.com/v1/titles/upcoming?type=movies", "Upcoming Movies")});

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$applyMedia$1, reason: invalid class name */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI", f = "MyDramaList.kt", i = {0, 0, 1, 1, 2, 2, 3, 3}, l = {182, 183, 188, 187}, m = "applyMedia", n = {"$this$applyMedia", LinkHeader.Parameters.Media, "$this$applyMedia", LinkHeader.Parameters.Media, "$this$applyMedia", LinkHeader.Parameters.Media, "$this$applyMedia", LinkHeader.Parameters.Media}, nl = {458, 184, 187, ByteCode.MONITORENTER}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"}, v = 2)
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
            return MyDramaListAPI.this.applyMedia(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$fetchEpisodes$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI", f = "MyDramaList.kt", i = {0}, l = {MediaError.DetailedErrorCode.DASH_NO_INIT}, m = "fetchEpisodes", n = {"$this$fetchEpisodes"}, nl = {325}, s = {"L$0"}, v = 2)
    static final class C07271 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07271(Continuation<? super C07271> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MyDramaListAPI.this.fetchEpisodes(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI", f = "MyDramaList.kt", i = {0, 0, 0}, l = {80}, m = "getMainPage$suspendImpl", n = {"$this", "request", "page"}, nl = {83}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07281 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07281(Continuation<? super C07281> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MyDramaListAPI.getMainPage$suspendImpl(MyDramaListAPI.this, 0, (MainPageRequest) null, (Continuation<? super HomePageResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI", f = "MyDramaList.kt", i = {0, 0, 0, 1, 1, 1}, l = {131, 134}, m = "load$suspendImpl", n = {"$this", ImagesContract.URL, "data", "$this", ImagesContract.URL, "data"}, nl = {134, 131}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, v = 2)
    static final class C07291 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07291(Continuation<? super C07291> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MyDramaListAPI.load$suspendImpl(MyDramaListAPI.this, (String) null, (Continuation<? super LoadResponse>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI", f = "MyDramaList.kt", i = {0, 0}, l = {90}, m = "search$suspendImpl", n = {"$this", SearchIntents.EXTRA_QUERY}, nl = {94}, s = {"L$0", "L$1"}, v = 2)
    static final class C07301 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07301(Continuation<? super C07301> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MyDramaListAPI.search$suspendImpl(MyDramaListAPI.this, (String) null, (Continuation<? super List<? extends SearchResponse>>) this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$toLoadResponse$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI", f = "MyDramaList.kt", i = {0, 0, 0, 1, 1, 2, 2, 3, 3}, l = {152, 165, 161, 171}, m = "toLoadResponse", n = {"$this$toLoadResponse", "data", "link", "$this$toLoadResponse", "data", "$this$toLoadResponse", "data", "$this$toLoadResponse", "data"}, nl = {161, 161, 171, 139}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"}, v = 2)
    static final class C07311 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C07311(Continuation<? super C07311> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MyDramaListAPI.this.toLoadResponse(null, null, this);
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
    public Object search(String str, Continuation<? super List<? extends SearchResponse>> continuation) {
        return search$suspendImpl(this, str, continuation);
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

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Companion;", "", "<init>", "()V", "TAG", "", "API_KEY", "getAPI_KEY", "()Ljava/lang/String;", "API_HOST", "SITE_HOST", "headerInterceptor", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MyDramaListInterceptor;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getAPI_KEY() {
            return MyDramaListAPI.API_KEY;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MyDramaListInterceptor;", "Lokhttp3/Interceptor;", "<init>", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class MyDramaListInterceptor implements Interceptor {
        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Intrinsics.checkNotNullParameter(chain, "chain");
            return chain.proceed(chain.request().newBuilder().removeHeader("user-agent").addHeader("user-agent", "Dart/3.6 (dart:io)").addHeader("mdl-api-key", MyDramaListAPI.INSTANCE.getAPI_KEY()).build());
        }
    }

    @Override // com.lagradost.cloudstream3.MainAPI
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    static /* synthetic */ Object getMainPage$suspendImpl(MyDramaListAPI myDramaListAPI, int i, MainPageRequest mainPageRequest, Continuation<? super HomePageResponse> continuation) {
        C07281 c07281;
        MainPageRequest mainPageRequest2;
        MyDramaListAPI myDramaListAPI2 = myDramaListAPI;
        if (continuation instanceof C07281) {
            c07281 = (C07281) continuation;
            if ((c07281.label & Integer.MIN_VALUE) != 0) {
                c07281.label -= Integer.MIN_VALUE;
            } else {
                c07281 = myDramaListAPI2.new C07281(continuation);
            }
        } else {
            c07281 = myDramaListAPI2.new C07281(continuation);
        }
        Object obj = c07281.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07281.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = mainPageRequest.getData() + "&limit=20&page=" + i + "&lang=en-US";
            MyDramaListInterceptor myDramaListInterceptor = headerInterceptor;
            c07281.L$0 = myDramaListAPI2;
            c07281.L$1 = mainPageRequest;
            c07281.I$0 = i;
            c07281.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, c07281, 3582, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
            mainPageRequest2 = mainPageRequest;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07281.I$0;
            MainPageRequest mainPageRequest3 = (MainPageRequest) c07281.L$1;
            MyDramaListAPI myDramaListAPI3 = (MyDramaListAPI) c07281.L$0;
            ResultKt.throwOnFailure(obj);
            mainPageRequest2 = mainPageRequest3;
            myDramaListAPI2 = myDramaListAPI3;
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        Iterable iterable = (Iterable) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ArrayList.class));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(myDramaListAPI2.toSearchResponse((MediaSummary) it.next()));
        }
        return MainAPIKt.newHomePageResponse$default(mainPageRequest2.getName(), arrayList, (Boolean) null, 4, (Object) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    static /* synthetic */ Object search$suspendImpl(MyDramaListAPI myDramaListAPI, String str, Continuation<? super List<? extends SearchResponse>> continuation) {
        C07301 c07301;
        MyDramaListAPI myDramaListAPI2 = myDramaListAPI;
        if (continuation instanceof C07301) {
            c07301 = (C07301) continuation;
            if ((c07301.label & Integer.MIN_VALUE) != 0) {
                c07301.label -= Integer.MIN_VALUE;
            } else {
                c07301 = myDramaListAPI2.new C07301(continuation);
            }
        } else {
            c07301 = myDramaListAPI2.new C07301(continuation);
        }
        Object obj = c07301.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07301.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("q", str));
            MyDramaListInterceptor myDramaListInterceptor = headerInterceptor;
            c07301.L$0 = myDramaListAPI2;
            c07301.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07301.label = 1;
            Object objPost$default = Requests.post$default(app2, "https://api.mydramalist.com/v1/search/titles", null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, c07301, 57310, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            myDramaListAPI2 = (MyDramaListAPI) c07301.L$0;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        Iterable iterable = (Iterable) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ArrayList.class));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(myDramaListAPI2.toSearchResponse((MediaSummary) it.next()));
        }
        return arrayList;
    }

    private final SearchResponse toSearchResponse(final MediaSummary mediaSummary) {
        TvType tvType = Intrinsics.areEqual(mediaSummary.getType(), "Movie") ? TvType.Movie : TvType.TvSeries;
        if (tvType == TvType.Movie) {
            return MainAPIKt.newMovieSearchResponse$default(this, mediaSummary.getTitle(), AppUtils.INSTANCE.toJson(new Data(tvType, mediaSummary)), TvType.Movie, false, new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MyDramaListAPI.toSearchResponse$lambda$0(mediaSummary, (MovieSearchResponse) obj);
                }
            }, 8, null);
        }
        return MainAPIKt.newTvSeriesSearchResponse$default(this, mediaSummary.getTitle(), AppUtils.INSTANCE.toJson(new Data(tvType, mediaSummary)), TvType.TvSeries, false, new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MyDramaListAPI.toSearchResponse$lambda$1(mediaSummary, (TvSeriesSearchResponse) obj);
            }
        }, 8, null);
    }

    static final Unit toSearchResponse$lambda$0(MediaSummary mediaSummary, MovieSearchResponse newMovieSearchResponse) {
        Intrinsics.checkNotNullParameter(newMovieSearchResponse, "$this$newMovieSearchResponse");
        newMovieSearchResponse.setPosterUrl(mediaSummary.getImages().getPoster());
        return Unit.INSTANCE;
    }

    static final Unit toSearchResponse$lambda$1(MediaSummary mediaSummary, TvSeriesSearchResponse newTvSeriesSearchResponse) {
        Intrinsics.checkNotNullParameter(newTvSeriesSearchResponse, "$this$newTvSeriesSearchResponse");
        newTvSeriesSearchResponse.setPosterUrl(mediaSummary.getImages().getPoster());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    static /* synthetic */ Object load$suspendImpl(MyDramaListAPI myDramaListAPI, String str, Continuation<? super LoadResponse> continuation) throws JsonProcessingException {
        C07291 c07291;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        Object obj;
        Object obj2;
        Object obj3;
        C07291 c07292;
        MyDramaListAPI myDramaListAPI2;
        Data data;
        MyDramaListAPI myDramaListAPI3;
        String str2 = str;
        if (continuation instanceof C07291) {
            c07291 = (C07291) continuation;
            if ((c07291.label & Integer.MIN_VALUE) != 0) {
                c07291.label -= Integer.MIN_VALUE;
            } else {
                c07291 = myDramaListAPI.new C07291(continuation);
            }
        } else {
            c07291 = myDramaListAPI.new C07291(continuation);
        }
        Object obj4 = c07291.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07291.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj4);
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(Data.INSTANCE.serializer());
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
            KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str2);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<Data>() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$load$suspendImpl$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<Data>() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$load$suspendImpl$$inlined$parseJson$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<Data>() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$load$suspendImpl$$inlined$parseJson$1
                });
            }
            Data data2 = (Data) objDecodeFromString;
            Requests app2 = MainActivityKt.getApp();
            StringBuilder sb = new StringBuilder("https://api.mydramalist.com/v1/titles/");
            MediaSummary media = data2.getMedia();
            sb.append(media != null ? Boxing.boxLong(media.getId()) : null);
            String string = sb.toString();
            MyDramaListInterceptor myDramaListInterceptor = headerInterceptor;
            c07291.L$0 = SpillingKt.nullOutSpilledVariable(myDramaListAPI);
            c07291.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c07291.L$2 = data2;
            c07291.L$3 = myDramaListAPI;
            c07291.label = 1;
            obj = null;
            C07291 c07293 = c07291;
            obj2 = coroutine_suspended;
            obj3 = Requests.get$default(app2, string, null, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, c07293, 3582, null);
            c07292 = c07293;
            if (obj3 != obj2) {
                myDramaListAPI2 = myDramaListAPI;
                data = data2;
                myDramaListAPI3 = myDramaListAPI2;
            }
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj4);
            return obj4;
        }
        MyDramaListAPI myDramaListAPI4 = (MyDramaListAPI) c07291.L$3;
        Data data3 = (Data) c07291.L$2;
        String str3 = (String) c07291.L$1;
        myDramaListAPI2 = (MyDramaListAPI) c07291.L$0;
        ResultKt.throwOnFailure(obj4);
        data = data3;
        str2 = str3;
        c07292 = c07291;
        obj2 = coroutine_suspended;
        obj3 = obj4;
        myDramaListAPI3 = myDramaListAPI4;
        obj = null;
        NiceResponse niceResponse = (NiceResponse) obj3;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        Media media2 = (Media) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Media.class));
        c07292.L$0 = SpillingKt.nullOutSpilledVariable(myDramaListAPI2);
        c07292.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c07292.L$2 = SpillingKt.nullOutSpilledVariable(data);
        c07292.L$3 = obj;
        c07292.label = 2;
        Object loadResponse = myDramaListAPI3.toLoadResponse(media2, data, c07292);
        return loadResponse == obj2 ? obj2 : loadResponse;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:34:0x017c  */
    /* JADX WARN: Code duplicated, block: B:39:0x01a6 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x011a, code lost:
    
        if (r3 == r4) goto L38;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object toLoadResponse(com.lagradost.cloudstream3.metaproviders.MyDramaListAPI.Media r29, com.lagradost.cloudstream3.metaproviders.MyDramaListAPI.Data r30, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r31) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI.toLoadResponse(com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Media, com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Data, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$toLoadResponse$2, reason: invalid class name */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$toLoadResponse$2", f = "MyDramaList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
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
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            movieLoadResponse.setType(TvType.Movie);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$toLoadResponse$3, reason: invalid class name */
    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$toLoadResponse$3", f = "MyDramaList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Media $this_toLoadResponse;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Media media, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$this_toLoadResponse = media;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = MyDramaListAPI.this.new AnonymousClass3(this.$this_toLoadResponse, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(tvSeriesLoadResponse, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TvSeriesLoadResponse tvSeriesLoadResponse = (TvSeriesLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            tvSeriesLoadResponse.setType(TvType.AsianDrama);
            tvSeriesLoadResponse.setShowStatus(MyDramaListAPI.this.getStatus(this.$this_toLoadResponse.getStatus()));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:36:0x0152  */
    /* JADX WARN: Code duplicated, block: B:40:0x017f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object applyMedia(LoadResponse loadResponse, Media media, Continuation<? super LoadResponse> continuation) {
        AnonymousClass1 anonymousClass1;
        LoadResponse loadResponse2;
        LoadResponse loadResponse3;
        LoadResponse.Companion companion;
        LoadResponse loadResponse4;
        LoadResponse.Companion companion2;
        LoadResponse loadResponse5;
        String str;
        Map mapMapOf;
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
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objFetchRecommendations = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i != 0) {
            if (i == 1) {
                loadResponse = (LoadResponse) anonymousClass2.L$2;
                media = (Media) anonymousClass2.L$1;
                loadResponse2 = (LoadResponse) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objFetchRecommendations);
            } else {
                if (i == 2) {
                    loadResponse3 = (LoadResponse) anonymousClass2.L$2;
                    media = (Media) anonymousClass2.L$1;
                    loadResponse2 = (LoadResponse) anonymousClass2.L$0;
                    ResultKt.throwOnFailure(objFetchRecommendations);
                    loadResponse3.setActors((List) objFetchRecommendations);
                    loadResponse2.setComingSoon(MainAPIKt.isUpcoming(media.getAiredStart()));
                    loadResponse2.setBackgroundPosterUrl(media.getImages().getPoster());
                    loadResponse2.setContentRating(fixCertification(media.getCertification()));
                    companion = LoadResponse.INSTANCE;
                    anonymousClass2.L$0 = loadResponse2;
                    anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(media);
                    anonymousClass2.L$2 = companion;
                    anonymousClass2.L$3 = loadResponse2;
                    anonymousClass2.label = 3;
                    objFetchRecommendations = media.fetchTrailer(anonymousClass2);
                    if (objFetchRecommendations != coroutine_suspended) {
                        loadResponse4 = loadResponse2;
                        companion2 = companion;
                        loadResponse5 = loadResponse4;
                    }
                    return coroutine_suspended;
                }
                if (i != 3) {
                    if (i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    LoadResponse loadResponse6 = (LoadResponse) anonymousClass2.L$0;
                    ResultKt.throwOnFailure(objFetchRecommendations);
                    return loadResponse6;
                }
                loadResponse4 = (LoadResponse) anonymousClass2.L$3;
                LoadResponse.Companion companion3 = (LoadResponse.Companion) anonymousClass2.L$2;
                media = (Media) anonymousClass2.L$1;
                LoadResponse loadResponse7 = (LoadResponse) anonymousClass2.L$0;
                ResultKt.throwOnFailure(objFetchRecommendations);
                companion2 = companion3;
                loadResponse5 = loadResponse7;
            }
            str = (String) objFetchRecommendations;
            mapMapOf = MapsKt.mapOf(TuplesKt.to("User-Agent", "Dart/3.6 (dart:io)"));
            anonymousClass2.L$0 = loadResponse5;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(media);
            anonymousClass2.L$2 = null;
            anonymousClass2.L$3 = null;
            anonymousClass2.label = 4;
            if (LoadResponse.Companion.addTrailer$default(companion2, loadResponse4, str, null, true, mapMapOf, anonymousClass2, 2, null) != coroutine_suspended) {
                return coroutine_suspended;
            }
            return loadResponse5;
        }
        ResultKt.throwOnFailure(objFetchRecommendations);
        loadResponse.setName(media.getTitle());
        loadResponse.setPosterUrl(media.getImages().getPoster());
        loadResponse.setYear(Boxing.boxInt(media.getMediaYear()));
        loadResponse.setPlot(media.getSynopsis());
        loadResponse.setScore(Score.INSTANCE.from10(Boxing.boxDouble(media.getMediaRating())));
        loadResponse.setTags(media.fixGenres());
        loadResponse.setDuration(Boxing.boxInt((int) media.getRuntime()));
        anonymousClass2.L$0 = loadResponse;
        anonymousClass2.L$1 = media;
        anonymousClass2.L$2 = loadResponse;
        anonymousClass2.label = 1;
        objFetchRecommendations = media.fetchRecommendations(anonymousClass2);
        if (objFetchRecommendations != coroutine_suspended) {
            loadResponse2 = loadResponse;
        }
        return coroutine_suspended;
        Iterable iterable = (Iterable) objFetchRecommendations;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(toSearchResponse((MediaSummary) it.next()));
        }
        loadResponse.setRecommendations(arrayList);
        anonymousClass2.L$0 = loadResponse2;
        anonymousClass2.L$1 = media;
        anonymousClass2.L$2 = loadResponse2;
        anonymousClass2.label = 2;
        objFetchRecommendations = media.fetchCredits(anonymousClass2);
        if (objFetchRecommendations != coroutine_suspended) {
            loadResponse3 = loadResponse2;
            loadResponse3.setActors((List) objFetchRecommendations);
            loadResponse2.setComingSoon(MainAPIKt.isUpcoming(media.getAiredStart()));
            loadResponse2.setBackgroundPosterUrl(media.getImages().getPoster());
            loadResponse2.setContentRating(fixCertification(media.getCertification()));
            companion = LoadResponse.INSTANCE;
            anonymousClass2.L$0 = loadResponse2;
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(media);
            anonymousClass2.L$2 = companion;
            anonymousClass2.L$3 = loadResponse2;
            anonymousClass2.label = 3;
            objFetchRecommendations = media.fetchTrailer(anonymousClass2);
            if (objFetchRecommendations != coroutine_suspended) {
                loadResponse4 = loadResponse2;
                companion2 = companion;
                loadResponse5 = loadResponse4;
                str = (String) objFetchRecommendations;
                mapMapOf = MapsKt.mapOf(TuplesKt.to("User-Agent", "Dart/3.6 (dart:io)"));
                anonymousClass2.L$0 = loadResponse5;
                anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(media);
                anonymousClass2.L$2 = null;
                anonymousClass2.L$3 = null;
                anonymousClass2.label = 4;
                if (LoadResponse.Companion.addTrailer$default(companion2, loadResponse4, str, null, true, mapMapOf, anonymousClass2, 2, null) != coroutine_suspended) {
                    return loadResponse5;
                }
            }
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ShowStatus getStatus(String status) {
        if (Intrinsics.areEqual(status, "Airing")) {
            return ShowStatus.Ongoing;
        }
        if (Intrinsics.areEqual(status, "Finished Airing")) {
            return ShowStatus.Completed;
        }
        return null;
    }

    private final String fixCertification(String status) {
        if (status == null) {
            return AbstractJsonLexerKt.NULL;
        }
        switch (status.hashCode()) {
            case -1546859841:
                return !status.equals("G - All Ages") ? AbstractJsonLexerKt.NULL : "G";
            case -1348737791:
                return !status.equals("18+ Restricted (violence & profanity)") ? AbstractJsonLexerKt.NULL : "18+";
            case -1107137825:
                if (status.equals("Not Yet Rated")) {
                    return null;
                }
                return AbstractJsonLexerKt.NULL;
            case 1545352672:
                return !status.equals("15+ - Teens 15 or older") ? AbstractJsonLexerKt.NULL : "15+";
            case 1719524964:
                return !status.equals("13+ - Teens 13 or older") ? AbstractJsonLexerKt.NULL : "13+";
            default:
                return AbstractJsonLexerKt.NULL;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Data;", "", "type", "Lcom/lagradost/cloudstream3/TvType;", LinkHeader.Parameters.Media, "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "<init>", "(Lcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getType$annotations", "()V", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "getMedia$annotations", "getMedia", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Data {

        @JsonProperty(LinkHeader.Parameters.Media)
        private final MediaSummary media;

        @JsonProperty("type")
        private final TvType type;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Data$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.Data._childSerializers$_anonymous_();
            }
        }), null};

        /* JADX WARN: Multi-variable type inference failed */
        public Data() {
            this((TvType) null, (MediaSummary) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return EnumsKt.createSimpleEnumSerializer("com.lagradost.cloudstream3.TvType", TvType.values());
        }

        public static /* synthetic */ Data copy$default(Data data, TvType tvType, MediaSummary mediaSummary, int i, Object obj) {
            if ((i & 1) != 0) {
                tvType = data.type;
            }
            if ((i & 2) != 0) {
                mediaSummary = data.media;
            }
            return data.copy(tvType, mediaSummary);
        }

        @SerialName(LinkHeader.Parameters.Media)
        public static /* synthetic */ void getMedia$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaSummary getMedia() {
            return this.media;
        }

        public final Data copy(@JsonProperty("type") TvType type, @JsonProperty(LinkHeader.Parameters.Media) MediaSummary media) {
            return new Data(type, media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return this.type == data.type && Intrinsics.areEqual(this.media, data.media);
        }

        public int hashCode() {
            TvType tvType = this.type;
            int iHashCode = (tvType == null ? 0 : tvType.hashCode()) * 31;
            MediaSummary mediaSummary = this.media;
            return iHashCode + (mediaSummary != null ? mediaSummary.hashCode() : 0);
        }

        public String toString() {
            return "Data(type=" + this.type + ", media=" + this.media + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Data$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Data;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Data> serializer() {
                return MyDramaListAPI$Data$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Data(int i, TvType tvType, MediaSummary mediaSummary, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.type = null;
            } else {
                this.type = tvType;
            }
            if ((i & 2) == 0) {
                this.media = null;
            } else {
                this.media = mediaSummary;
            }
        }

        public Data(@JsonProperty("type") TvType tvType, @JsonProperty(LinkHeader.Parameters.Media) MediaSummary mediaSummary) {
            this.type = tvType;
            this.media = mediaSummary;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Data self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.type != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.type);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.media == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, MyDramaListAPI$MediaSummary$$serializer.INSTANCE, self.media);
        }

        public /* synthetic */ Data(TvType tvType, MediaSummary mediaSummary, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : tvType, (i & 2) != 0 ? null : mediaSummary);
        }

        public final TvType getType() {
            return this.type;
        }

        public final MediaSummary getMedia() {
            return this.media;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 Q2\u00020\u0001:\u0002PQB\u0081\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\f\u001a\u00020\u0005\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013B\u0087\u0001\b\u0010\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0012\u0010\u0017J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010:\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010'J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010@\u001a\u00020\u0011HÆ\u0003J\u0088\u0001\u0010A\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\f\u001a\u00020\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0010\u001a\u00020\u0011HÆ\u0001¢\u0006\u0002\u0010BJ\u0014\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010F\u001a\u00020\bHÖ\u0081\u0004J\n\u0010G\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u00002\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0001¢\u0006\u0002\bOR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0019\u001a\u0004\b \u0010\u001eR \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010$\u0012\u0004\b!\u0010\u0019\u001a\u0004\b\"\u0010#R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\b%\u0010\u0019\u001a\u0004\b&\u0010'R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u0019\u001a\u0004\b*\u0010\u001eR\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u0019\u001a\u0004\b,\u0010\u001eR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0019\u001a\u0004\b.\u0010\u001eR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u0019\u001a\u0004\b0\u0010\u001eR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u0019\u001a\u0004\b2\u0010\u001eR\u001c\u0010\u0010\u001a\u00020\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u0019\u001a\u0004\b4\u00105¨\u0006R"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "", "id", "", "title", "", "originalTitle", "year", "", "rating", "", "permalink", "type", "mediaType", "country", "language", "images", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "getOriginalTitle$annotations", "getOriginalTitle", "getYear$annotations", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRating$annotations", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPermalink$annotations", "getPermalink", "getType$annotations", "getType", "getMediaType$annotations", "getMediaType", "getCountry$annotations", "getCountry", "getLanguage$annotations", "getLanguage", "getImages$annotations", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;)Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaSummary {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("country")
        private final String country;

        @JsonProperty("id")
        private final long id;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("language")
        private final String language;

        @JsonProperty("media_type")
        private final String mediaType;

        @JsonProperty("original_title")
        private final String originalTitle;

        @JsonProperty("permalink")
        private final String permalink;

        @JsonProperty("rating")
        private final Double rating;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("year")
        private final Integer year;

        public static /* synthetic */ MediaSummary copy$default(MediaSummary mediaSummary, long j, String str, String str2, Integer num, Double d, String str3, String str4, String str5, String str6, String str7, Images images, int i, Object obj) {
            if ((i & 1) != 0) {
                j = mediaSummary.id;
            }
            return mediaSummary.copy(j, (i & 2) != 0 ? mediaSummary.title : str, (i & 4) != 0 ? mediaSummary.originalTitle : str2, (i & 8) != 0 ? mediaSummary.year : num, (i & 16) != 0 ? mediaSummary.rating : d, (i & 32) != 0 ? mediaSummary.permalink : str3, (i & 64) != 0 ? mediaSummary.type : str4, (i & 128) != 0 ? mediaSummary.mediaType : str5, (i & 256) != 0 ? mediaSummary.country : str6, (i & 512) != 0 ? mediaSummary.language : str7, (i & 1024) != 0 ? mediaSummary.images : images);
        }

        @SerialName("country")
        public static /* synthetic */ void getCountry$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("images")
        public static /* synthetic */ void getImages$annotations() {
        }

        @SerialName("language")
        public static /* synthetic */ void getLanguage$annotations() {
        }

        @SerialName("media_type")
        public static /* synthetic */ void getMediaType$annotations() {
        }

        @SerialName("original_title")
        public static /* synthetic */ void getOriginalTitle$annotations() {
        }

        @SerialName("permalink")
        public static /* synthetic */ void getPermalink$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getRating$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Double getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPermalink() {
            return this.permalink;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getCountry() {
            return this.country;
        }

        public final MediaSummary copy(@JsonProperty("id") long id, @JsonProperty("title") String title, @JsonProperty("original_title") String originalTitle, @JsonProperty("year") Integer year, @JsonProperty("rating") Double rating, @JsonProperty("permalink") String permalink, @JsonProperty("type") String type, @JsonProperty("media_type") String mediaType, @JsonProperty("country") String country, @JsonProperty("language") String language, @JsonProperty("images") Images images) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(originalTitle, "originalTitle");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(images, "images");
            return new MediaSummary(id, title, originalTitle, year, rating, permalink, type, mediaType, country, language, images);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaSummary)) {
                return false;
            }
            MediaSummary mediaSummary = (MediaSummary) other;
            return this.id == mediaSummary.id && Intrinsics.areEqual(this.title, mediaSummary.title) && Intrinsics.areEqual(this.originalTitle, mediaSummary.originalTitle) && Intrinsics.areEqual(this.year, mediaSummary.year) && Intrinsics.areEqual((Object) this.rating, (Object) mediaSummary.rating) && Intrinsics.areEqual(this.permalink, mediaSummary.permalink) && Intrinsics.areEqual(this.type, mediaSummary.type) && Intrinsics.areEqual(this.mediaType, mediaSummary.mediaType) && Intrinsics.areEqual(this.country, mediaSummary.country) && Intrinsics.areEqual(this.language, mediaSummary.language) && Intrinsics.areEqual(this.images, mediaSummary.images);
        }

        public int hashCode() {
            int iM = ((((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.title.hashCode()) * 31) + this.originalTitle.hashCode()) * 31;
            Integer num = this.year;
            int iHashCode = (iM + (num == null ? 0 : num.hashCode())) * 31;
            Double d = this.rating;
            int iHashCode2 = (iHashCode + (d == null ? 0 : d.hashCode())) * 31;
            String str = this.permalink;
            int iHashCode3 = (((iHashCode2 + (str == null ? 0 : str.hashCode())) * 31) + this.type.hashCode()) * 31;
            String str2 = this.mediaType;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.country;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.language;
            return ((iHashCode5 + (str4 != null ? str4.hashCode() : 0)) * 31) + this.images.hashCode();
        }

        public String toString() {
            return "MediaSummary(id=" + this.id + ", title=" + this.title + ", originalTitle=" + this.originalTitle + ", year=" + this.year + ", rating=" + this.rating + ", permalink=" + this.permalink + ", type=" + this.type + ", mediaType=" + this.mediaType + ", country=" + this.country + ", language=" + this.language + ", images=" + this.images + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaSummary> serializer() {
                return MyDramaListAPI$MediaSummary$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaSummary(int i, long j, String str, String str2, Integer num, Double d, String str3, String str4, String str5, String str6, String str7, Images images, SerializationConstructorMarker serializationConstructorMarker) {
            if (1095 != (i & 1095)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1095, MyDramaListAPI$MediaSummary$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.title = str;
            this.originalTitle = str2;
            if ((i & 8) == 0) {
                this.year = null;
            } else {
                this.year = num;
            }
            if ((i & 16) == 0) {
                this.rating = null;
            } else {
                this.rating = d;
            }
            if ((i & 32) == 0) {
                this.permalink = null;
            } else {
                this.permalink = str3;
            }
            this.type = str4;
            if ((i & 128) == 0) {
                this.mediaType = null;
            } else {
                this.mediaType = str5;
            }
            if ((i & 256) == 0) {
                this.country = null;
            } else {
                this.country = str6;
            }
            if ((i & 512) == 0) {
                this.language = null;
            } else {
                this.language = str7;
            }
            this.images = images;
        }

        public MediaSummary(@JsonProperty("id") long j, @JsonProperty("title") String title, @JsonProperty("original_title") String originalTitle, @JsonProperty("year") Integer num, @JsonProperty("rating") Double d, @JsonProperty("permalink") String str, @JsonProperty("type") String type, @JsonProperty("media_type") String str2, @JsonProperty("country") String str3, @JsonProperty("language") String str4, @JsonProperty("images") Images images) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(originalTitle, "originalTitle");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(images, "images");
            this.id = j;
            this.title = title;
            this.originalTitle = originalTitle;
            this.year = num;
            this.rating = d;
            this.permalink = str;
            this.type = type;
            this.mediaType = str2;
            this.country = str3;
            this.language = str4;
            this.images = images;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(MediaSummary self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.title);
            output.encodeStringElement(serialDesc, 2, self.originalTitle);
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.year != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.year);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.rating != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, DoubleSerializer.INSTANCE, self.rating);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.permalink != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.permalink);
            }
            output.encodeStringElement(serialDesc, 6, self.type);
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.mediaType != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.mediaType);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.country != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.country);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || self.language != null) {
                output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.language);
            }
            output.encodeSerializableElement(serialDesc, 10, MyDramaListAPI$Images$$serializer.INSTANCE, self.images);
        }

        public /* synthetic */ MediaSummary(long j, String str, String str2, Integer num, Double d, String str3, String str4, String str5, String str6, String str7, Images images, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, str2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : d, (i & 32) != 0 ? null : str3, str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : str6, (i & 512) != 0 ? null : str7, images);
        }

        public final long getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        public final Integer getYear() {
            return this.year;
        }

        public final Double getRating() {
            return this.rating;
        }

        public final String getPermalink() {
            return this.permalink;
        }

        public final String getType() {
            return this.type;
        }

        public final String getMediaType() {
            return this.mediaType;
        }

        public final String getCountry() {
            return this.country;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final Images getImages() {
            return this.images;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "", "thumb", "", "medium", "poster", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getThumb$annotations", "()V", "getThumb", "()Ljava/lang/String;", "getMedium$annotations", "getMedium", "getPoster$annotations", "getPoster", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Images {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("medium")
        private final String medium;

        @JsonProperty("poster")
        private final String poster;

        @JsonProperty("thumb")
        private final String thumb;

        public Images() {
            this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Images copy$default(Images images, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = images.thumb;
            }
            if ((i & 2) != 0) {
                str2 = images.medium;
            }
            if ((i & 4) != 0) {
                str3 = images.poster;
            }
            return images.copy(str, str2, str3);
        }

        @SerialName("medium")
        public static /* synthetic */ void getMedium$annotations() {
        }

        @SerialName("poster")
        public static /* synthetic */ void getPoster$annotations() {
        }

        @SerialName("thumb")
        public static /* synthetic */ void getThumb$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getThumb() {
            return this.thumb;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMedium() {
            return this.medium;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        public final Images copy(@JsonProperty("thumb") String thumb, @JsonProperty("medium") String medium, @JsonProperty("poster") String poster) {
            return new Images(thumb, medium, poster);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Images)) {
                return false;
            }
            Images images = (Images) other;
            return Intrinsics.areEqual(this.thumb, images.thumb) && Intrinsics.areEqual(this.medium, images.medium) && Intrinsics.areEqual(this.poster, images.poster);
        }

        public int hashCode() {
            String str = this.thumb;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.medium;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.poster;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "Images(thumb=" + this.thumb + ", medium=" + this.medium + ", poster=" + this.poster + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Images> serializer() {
                return MyDramaListAPI$Images$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Images(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.thumb = null;
            } else {
                this.thumb = str;
            }
            if ((i & 2) == 0) {
                this.medium = null;
            } else {
                this.medium = str2;
            }
            if ((i & 4) == 0) {
                this.poster = null;
            } else {
                this.poster = str3;
            }
        }

        public Images(@JsonProperty("thumb") String str, @JsonProperty("medium") String str2, @JsonProperty("poster") String str3) {
            this.thumb = str;
            this.medium = str2;
            this.poster = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Images self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.thumb != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.thumb);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.medium != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.medium);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.poster == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.poster);
        }

        public /* synthetic */ Images(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        public final String getThumb() {
            return this.thumb;
        }

        public final String getMedium() {
            return this.medium;
        }

        public final String getPoster() {
            return this.poster;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\bO\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 º\u00012\u00020\u0001:\u0004¹\u0001º\u0001Bû\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0014\u0012\u0010\b\u0003\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0016\u0012\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u001a\u001a\u00020\u0005\u0012\u0010\b\u0003\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0016\u0012\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0001\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0001\u0010 \u001a\u00020\u0003\u0012\b\b\u0001\u0010!\u001a\u00020\u0003\u0012\b\b\u0001\u0010\"\u001a\u00020\u0003\u0012\b\b\u0001\u0010#\u001a\u00020\u0003\u0012\b\b\u0001\u0010$\u001a\u00020\u0003\u0012\b\b\u0001\u0010%\u001a\u00020\u0003\u0012\b\b\u0001\u0010&\u001a\u00020\u0005\u0012\b\b\u0001\u0010'\u001a\u00020\u0005\u0012\b\b\u0001\u0010(\u001a\u00020)\u0012\u000e\b\u0001\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u0016\u0012\b\b\u0001\u0010,\u001a\u00020\u0003¢\u0006\u0004\b-\u0010.Bå\u0002\b\u0010\u0012\u0006\u0010/\u001a\u00020\t\u0012\u0006\u00100\u001a\u00020\t\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0016\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\b\u0010&\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010(\u001a\u00020)\u0012\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010\u0016\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\b\u00101\u001a\u0004\u0018\u000102¢\u0006\u0004\b-\u00103J\u0017\u0010\u0081\u0001\u001a\t\u0012\u0005\u0012\u00030\u0082\u00010\u0016H\u0086@¢\u0006\u0003\u0010\u0083\u0001J$\u0010\u0084\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0086\u00010\u0085\u0001j\n\u0012\u0005\u0012\u00030\u0086\u0001`\u0087\u0001H\u0086@¢\u0006\u0003\u0010\u0083\u0001J\u0012\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0005H\u0086@¢\u0006\u0003\u0010\u0083\u0001J\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016J\n\u0010\u008a\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\tHÆ\u0003J\n\u0010\u008f\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0090\u0001\u001a\u00020\fHÆ\u0003J\f\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\n\u0010\u0097\u0001\u001a\u00020\u0014HÆ\u0003J\u0012\u0010\u0098\u0001\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0016HÆ\u0003J\u0011\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010[J\f\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\n\u0010\u009c\u0001\u001a\u00020\u0005HÆ\u0003J\u0012\u0010\u009d\u0001\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0016HÆ\u0003J\f\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u001eHÆ\u0003J\n\u0010\u009f\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010 \u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¢\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010£\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¤\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¥\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010¦\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010§\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¨\u0001\u001a\u00020)HÆ\u0003J\u0010\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020+0\u0016HÆ\u0003J\n\u0010ª\u0001\u001a\u00020\u0003HÆ\u0003J\u0084\u0003\u0010«\u0001\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u00032\b\b\u0003\u0010\u000b\u001a\u00020\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0013\u001a\u00020\u00142\u0010\b\u0003\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00162\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u001a\u001a\u00020\u00052\u0010\b\u0003\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00162\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0003\u0010\u001f\u001a\u00020\u00032\b\b\u0003\u0010 \u001a\u00020\u00032\b\b\u0003\u0010!\u001a\u00020\u00032\b\b\u0003\u0010\"\u001a\u00020\u00032\b\b\u0003\u0010#\u001a\u00020\u00032\b\b\u0003\u0010$\u001a\u00020\u00032\b\b\u0003\u0010%\u001a\u00020\u00032\b\b\u0003\u0010&\u001a\u00020\u00052\b\b\u0003\u0010'\u001a\u00020\u00052\b\b\u0003\u0010(\u001a\u00020)2\u000e\b\u0003\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00162\b\b\u0003\u0010,\u001a\u00020\u0003HÆ\u0001¢\u0006\u0003\u0010¬\u0001J\u0016\u0010\u00ad\u0001\u001a\u00020)2\t\u0010®\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010¯\u0001\u001a\u00020\tHÖ\u0081\u0004J\u000b\u0010°\u0001\u001a\u00020\u0005HÖ\u0081\u0004J-\u0010±\u0001\u001a\u00030²\u00012\u0007\u0010³\u0001\u001a\u00020\u00002\b\u0010´\u0001\u001a\u00030µ\u00012\b\u0010¶\u0001\u001a\u00030·\u0001H\u0001¢\u0006\u0003\b¸\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u00105\u001a\u0004\b6\u00107R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b8\u00105\u001a\u0004\b9\u0010:R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u00105\u001a\u0004\b<\u0010:R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b=\u00105\u001a\u0004\b>\u0010:R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b?\u00105\u001a\u0004\b@\u0010AR\u001c\u0010\n\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bB\u00105\u001a\u0004\bC\u00107R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bD\u00105\u001a\u0004\bE\u0010FR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bG\u00105\u001a\u0004\bH\u0010:R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bI\u00105\u001a\u0004\bJ\u0010:R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bK\u00105\u001a\u0004\bL\u0010:R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bM\u00105\u001a\u0004\bN\u0010:R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bO\u00105\u001a\u0004\bP\u0010:R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bQ\u00105\u001a\u0004\bR\u0010:R\u001c\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bS\u00105\u001a\u0004\bT\u0010UR$\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00168\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bV\u00105\u001a\u0004\bW\u0010XR \u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\\\u0012\u0004\bY\u00105\u001a\u0004\bZ\u0010[R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b]\u00105\u001a\u0004\b^\u0010:R\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b_\u00105\u001a\u0004\b`\u0010:R\u001c\u0010\u001a\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\ba\u00105\u001a\u0004\bb\u0010:R$\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00168\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bc\u00105\u001a\u0004\bd\u0010XR\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\be\u00105\u001a\u0004\bf\u0010gR\u001c\u0010\u001f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bh\u00105\u001a\u0004\bi\u00107R\u001c\u0010 \u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bj\u00105\u001a\u0004\bk\u00107R\u001c\u0010!\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bl\u00105\u001a\u0004\bm\u00107R\u001c\u0010\"\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bn\u00105\u001a\u0004\bo\u00107R\u001c\u0010#\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bp\u00105\u001a\u0004\bq\u00107R\u001c\u0010$\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\br\u00105\u001a\u0004\bs\u00107R\u001c\u0010%\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bt\u00105\u001a\u0004\bu\u00107R\u001c\u0010&\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bv\u00105\u001a\u0004\bw\u0010:R\u001c\u0010'\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bx\u00105\u001a\u0004\by\u0010:R\u001c\u0010(\u001a\u00020)8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bz\u00105\u001a\u0004\b{\u0010|R\"\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00168\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b}\u00105\u001a\u0004\b~\u0010XR\u001d\u0010,\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000f\n\u0000\u0012\u0004\b\u007f\u00105\u001a\u0005\b\u0080\u0001\u00107¨\u0006»\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;", "", "id", "", "slug", "", "title", "originalTitle", "mediaYear", "", "episodes", "mediaRating", "", "permalink", "synopsis", "type", "mediaType", "country", "language", "images", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "altTitles", "", "votes", "airedStart", "released", "releaseDatesFmt", "genres", "Lkotlinx/serialization/json/JsonElement;", "trailer", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;", "watchers", "ranked", "popularity", "runtime", "reviewsCount", "recsCount", "commentsCount", "certification", NotificationCompat.CATEGORY_STATUS, "enableAds", "", "sources", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Source;", "updatedAt", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;JJJJJJJLjava/lang/String;Ljava/lang/String;ZLjava/util/List;J)V", "seen0", "seen1", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;JJJJJJJLjava/lang/String;Ljava/lang/String;ZLjava/util/List;JLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getSlug$annotations", "getSlug", "()Ljava/lang/String;", "getTitle$annotations", "getTitle", "getOriginalTitle$annotations", "getOriginalTitle", "getMediaYear$annotations", "getMediaYear", "()I", "getEpisodes$annotations", "getEpisodes", "getMediaRating$annotations", "getMediaRating", "()D", "getPermalink$annotations", "getPermalink", "getSynopsis$annotations", "getSynopsis", "getType$annotations", "getType", "getMediaType$annotations", "getMediaType", "getCountry$annotations", "getCountry", "getLanguage$annotations", "getLanguage", "getImages$annotations", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "getAltTitles$annotations", "getAltTitles", "()Ljava/util/List;", "getVotes$annotations", "getVotes", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAiredStart$annotations", "getAiredStart", "getReleased$annotations", "getReleased", "getReleaseDatesFmt$annotations", "getReleaseDatesFmt", "getGenres$annotations", "getGenres", "getTrailer$annotations", "getTrailer", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;", "getWatchers$annotations", "getWatchers", "getRanked$annotations", "getRanked", "getPopularity$annotations", "getPopularity", "getRuntime$annotations", "getRuntime", "getReviewsCount$annotations", "getReviewsCount", "getRecsCount$annotations", "getRecsCount", "getCommentsCount$annotations", "getCommentsCount", "getCertification$annotations", "getCertification", "getStatus$annotations", "getStatus", "getEnableAds$annotations", "getEnableAds", "()Z", "getSources$annotations", "getSources", "getUpdatedAt$annotations", "getUpdatedAt", "fetchCredits", "Lcom/lagradost/cloudstream3/ActorData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchRecommendations", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$MediaSummary;", "Lkotlin/collections/ArrayList;", "fetchTrailer", "fixGenres", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;JJJJJJJLjava/lang/String;Ljava/lang/String;ZLjava/util/List;J)Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Media {

        @JsonProperty("aired_start")
        private final String airedStart;

        @JsonProperty("alt_titles")
        private final List<String> altTitles;

        @JsonProperty("certification")
        private final String certification;

        @JsonProperty("comments_count")
        private final long commentsCount;

        @JsonProperty("country")
        private final String country;

        @JsonProperty("enable_ads")
        private final boolean enableAds;

        @JsonProperty("episodes")
        private final long episodes;

        @JsonProperty("genres")
        private final List<JsonElement> genres;

        @JsonProperty("id")
        private final long id;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("language")
        private final String language;

        @JsonProperty("rating")
        private final double mediaRating;

        @JsonProperty("media_type")
        private final String mediaType;

        @JsonProperty("year")
        private final int mediaYear;

        @JsonProperty("original_title")
        private final String originalTitle;

        @JsonProperty("permalink")
        private final String permalink;

        @JsonProperty("popularity")
        private final long popularity;

        @JsonProperty("ranked")
        private final long ranked;

        @JsonProperty("recs_count")
        private final long recsCount;

        @JsonProperty("release_dates_fmt")
        private final String releaseDatesFmt;

        @JsonProperty("released")
        private final String released;

        @JsonProperty("reviews_count")
        private final long reviewsCount;

        @JsonProperty("runtime")
        private final long runtime;

        @JsonProperty("slug")
        private final String slug;

        @JsonProperty("sources")
        private final List<Source> sources;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("synopsis")
        private final String synopsis;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("trailer")
        private final Trailer trailer;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("updated_at")
        private final long updatedAt;

        @JsonProperty("votes")
        private final Long votes;

        @JsonProperty("watchers")
        private final long watchers;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Media$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.Media._childSerializers$_anonymous_();
            }
        }), null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Media$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.Media._childSerializers$_anonymous_$0();
            }
        }), null, null, null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Media$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.Media._childSerializers$_anonymous_$1();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(JsonElementSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
            return new ArrayListSerializer(MyDramaListAPI$Source$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Media copy$default(Media media, long j, String str, String str2, String str3, int i, long j2, double d, String str4, String str5, String str6, String str7, String str8, String str9, Images images, List list, Long l, String str10, String str11, String str12, List list2, Trailer trailer, long j3, long j4, long j5, long j6, long j7, long j8, long j9, String str13, String str14, boolean z, List list3, long j10, int i2, int i3, Object obj) {
            long j11;
            List list4;
            String str15;
            long j12 = (i2 & 1) != 0 ? media.id : j;
            String str16 = (i2 & 2) != 0 ? media.slug : str;
            String str17 = (i2 & 4) != 0 ? media.title : str2;
            String str18 = (i2 & 8) != 0 ? media.originalTitle : str3;
            int i4 = (i2 & 16) != 0 ? media.mediaYear : i;
            long j13 = (i2 & 32) != 0 ? media.episodes : j2;
            double d2 = (i2 & 64) != 0 ? media.mediaRating : d;
            String str19 = (i2 & 128) != 0 ? media.permalink : str4;
            String str20 = (i2 & 256) != 0 ? media.synopsis : str5;
            String str21 = (i2 & 512) != 0 ? media.type : str6;
            String str22 = (i2 & 1024) != 0 ? media.mediaType : str7;
            long j14 = j12;
            String str23 = (i2 & 2048) != 0 ? media.country : str8;
            String str24 = (i2 & 4096) != 0 ? media.language : str9;
            String str25 = str23;
            Images images2 = (i2 & 8192) != 0 ? media.images : images;
            List list5 = (i2 & 16384) != 0 ? media.altTitles : list;
            Long l2 = (i2 & 32768) != 0 ? media.votes : l;
            String str26 = (i2 & 65536) != 0 ? media.airedStart : str10;
            String str27 = (i2 & 131072) != 0 ? media.released : str11;
            String str28 = (i2 & 262144) != 0 ? media.releaseDatesFmt : str12;
            List list6 = (i2 & 524288) != 0 ? media.genres : list2;
            Trailer trailer2 = (i2 & 1048576) != 0 ? media.trailer : trailer;
            List list7 = list5;
            long j15 = (i2 & 2097152) != 0 ? media.watchers : j3;
            long j16 = (i2 & 4194304) != 0 ? media.ranked : j4;
            long j17 = (i2 & 8388608) != 0 ? media.popularity : j5;
            long j18 = (i2 & 16777216) != 0 ? media.runtime : j6;
            long j19 = (i2 & 33554432) != 0 ? media.reviewsCount : j7;
            long j20 = (i2 & 67108864) != 0 ? media.recsCount : j8;
            long j21 = (i2 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? media.commentsCount : j9;
            String str29 = (i2 & 268435456) != 0 ? media.certification : str13;
            String str30 = (i2 & 536870912) != 0 ? media.status : str14;
            String str31 = str29;
            boolean z2 = (i2 & 1073741824) != 0 ? media.enableAds : z;
            List list8 = (i2 & Integer.MIN_VALUE) != 0 ? media.sources : list3;
            if ((i3 & 1) != 0) {
                str15 = str30;
                list4 = list8;
                j11 = media.updatedAt;
            } else {
                j11 = j10;
                list4 = list8;
                str15 = str30;
            }
            return media.copy(j14, str16, str17, str18, i4, j13, d2, str19, str20, str21, str22, str25, str24, images2, list7, l2, str26, str27, str28, list6, trailer2, j15, j16, j17, j18, j19, j20, j21, str31, str15, z2, list4, j11);
        }

        @SerialName("aired_start")
        public static /* synthetic */ void getAiredStart$annotations() {
        }

        @SerialName("alt_titles")
        public static /* synthetic */ void getAltTitles$annotations() {
        }

        @SerialName("certification")
        public static /* synthetic */ void getCertification$annotations() {
        }

        @SerialName("comments_count")
        public static /* synthetic */ void getCommentsCount$annotations() {
        }

        @SerialName("country")
        public static /* synthetic */ void getCountry$annotations() {
        }

        @SerialName("enable_ads")
        public static /* synthetic */ void getEnableAds$annotations() {
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("genres")
        public static /* synthetic */ void getGenres$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("images")
        public static /* synthetic */ void getImages$annotations() {
        }

        @SerialName("language")
        public static /* synthetic */ void getLanguage$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getMediaRating$annotations() {
        }

        @SerialName("media_type")
        public static /* synthetic */ void getMediaType$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getMediaYear$annotations() {
        }

        @SerialName("original_title")
        public static /* synthetic */ void getOriginalTitle$annotations() {
        }

        @SerialName("permalink")
        public static /* synthetic */ void getPermalink$annotations() {
        }

        @SerialName("popularity")
        public static /* synthetic */ void getPopularity$annotations() {
        }

        @SerialName("ranked")
        public static /* synthetic */ void getRanked$annotations() {
        }

        @SerialName("recs_count")
        public static /* synthetic */ void getRecsCount$annotations() {
        }

        @SerialName("release_dates_fmt")
        public static /* synthetic */ void getReleaseDatesFmt$annotations() {
        }

        @SerialName("released")
        public static /* synthetic */ void getReleased$annotations() {
        }

        @SerialName("reviews_count")
        public static /* synthetic */ void getReviewsCount$annotations() {
        }

        @SerialName("runtime")
        public static /* synthetic */ void getRuntime$annotations() {
        }

        @SerialName("slug")
        public static /* synthetic */ void getSlug$annotations() {
        }

        @SerialName("sources")
        public static /* synthetic */ void getSources$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("synopsis")
        public static /* synthetic */ void getSynopsis$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("trailer")
        public static /* synthetic */ void getTrailer$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName("votes")
        public static /* synthetic */ void getVotes$annotations() {
        }

        @SerialName("watchers")
        public static /* synthetic */ void getWatchers$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getCountry() {
            return this.country;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        public final List<String> component15() {
            return this.altTitles;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Long getVotes() {
            return this.votes;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getAiredStart() {
            return this.airedStart;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getReleased() {
            return this.released;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getReleaseDatesFmt() {
            return this.releaseDatesFmt;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        public final List<JsonElement> component20() {
            return this.genres;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final Trailer getTrailer() {
            return this.trailer;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final long getWatchers() {
            return this.watchers;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final long getRanked() {
            return this.ranked;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final long getPopularity() {
            return this.popularity;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final long getRuntime() {
            return this.runtime;
        }

        /* JADX INFO: renamed from: component26, reason: from getter */
        public final long getReviewsCount() {
            return this.reviewsCount;
        }

        /* JADX INFO: renamed from: component27, reason: from getter */
        public final long getRecsCount() {
            return this.recsCount;
        }

        /* JADX INFO: renamed from: component28, reason: from getter */
        public final long getCommentsCount() {
            return this.commentsCount;
        }

        /* JADX INFO: renamed from: component29, reason: from getter */
        public final String getCertification() {
            return this.certification;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component30, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component31, reason: from getter */
        public final boolean getEnableAds() {
            return this.enableAds;
        }

        public final List<Source> component32() {
            return this.sources;
        }

        /* JADX INFO: renamed from: component33, reason: from getter */
        public final long getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getMediaYear() {
            return this.mediaYear;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getEpisodes() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final double getMediaRating() {
            return this.mediaRating;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPermalink() {
            return this.permalink;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSynopsis() {
            return this.synopsis;
        }

        public final Media copy(@JsonProperty("id") long id, @JsonProperty("slug") String slug, @JsonProperty("title") String title, @JsonProperty("original_title") String originalTitle, @JsonProperty("year") int mediaYear, @JsonProperty("episodes") long episodes, @JsonProperty("rating") double mediaRating, @JsonProperty("permalink") String permalink, @JsonProperty("synopsis") String synopsis, @JsonProperty("type") String type, @JsonProperty("media_type") String mediaType, @JsonProperty("country") String country, @JsonProperty("language") String language, @JsonProperty("images") Images images, @JsonProperty("alt_titles") List<String> altTitles, @JsonProperty("votes") Long votes, @JsonProperty("aired_start") String airedStart, @JsonProperty("released") String released, @JsonProperty("release_dates_fmt") String releaseDatesFmt, @JsonProperty("genres") List<? extends JsonElement> genres, @JsonProperty("trailer") Trailer trailer, @JsonProperty("watchers") long watchers, @JsonProperty("ranked") long ranked, @JsonProperty("popularity") long popularity, @JsonProperty("runtime") long runtime, @JsonProperty("reviews_count") long reviewsCount, @JsonProperty("recs_count") long recsCount, @JsonProperty("comments_count") long commentsCount, @JsonProperty("certification") String certification, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("enable_ads") boolean enableAds, @JsonProperty("sources") List<Source> sources, @JsonProperty("updated_at") long updatedAt) {
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(originalTitle, "originalTitle");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(releaseDatesFmt, "releaseDatesFmt");
            Intrinsics.checkNotNullParameter(certification, "certification");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(sources, "sources");
            return new Media(id, slug, title, originalTitle, mediaYear, episodes, mediaRating, permalink, synopsis, type, mediaType, country, language, images, altTitles, votes, airedStart, released, releaseDatesFmt, genres, trailer, watchers, ranked, popularity, runtime, reviewsCount, recsCount, commentsCount, certification, status, enableAds, sources, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Media)) {
                return false;
            }
            Media media = (Media) other;
            return this.id == media.id && Intrinsics.areEqual(this.slug, media.slug) && Intrinsics.areEqual(this.title, media.title) && Intrinsics.areEqual(this.originalTitle, media.originalTitle) && this.mediaYear == media.mediaYear && this.episodes == media.episodes && Double.compare(this.mediaRating, media.mediaRating) == 0 && Intrinsics.areEqual(this.permalink, media.permalink) && Intrinsics.areEqual(this.synopsis, media.synopsis) && Intrinsics.areEqual(this.type, media.type) && Intrinsics.areEqual(this.mediaType, media.mediaType) && Intrinsics.areEqual(this.country, media.country) && Intrinsics.areEqual(this.language, media.language) && Intrinsics.areEqual(this.images, media.images) && Intrinsics.areEqual(this.altTitles, media.altTitles) && Intrinsics.areEqual(this.votes, media.votes) && Intrinsics.areEqual(this.airedStart, media.airedStart) && Intrinsics.areEqual(this.released, media.released) && Intrinsics.areEqual(this.releaseDatesFmt, media.releaseDatesFmt) && Intrinsics.areEqual(this.genres, media.genres) && Intrinsics.areEqual(this.trailer, media.trailer) && this.watchers == media.watchers && this.ranked == media.ranked && this.popularity == media.popularity && this.runtime == media.runtime && this.reviewsCount == media.reviewsCount && this.recsCount == media.recsCount && this.commentsCount == media.commentsCount && Intrinsics.areEqual(this.certification, media.certification) && Intrinsics.areEqual(this.status, media.status) && this.enableAds == media.enableAds && Intrinsics.areEqual(this.sources, media.sources) && this.updatedAt == media.updatedAt;
        }

        public int hashCode() {
            int iM = ((((((((((((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.slug.hashCode()) * 31) + this.title.hashCode()) * 31) + this.originalTitle.hashCode()) * 31) + this.mediaYear) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.episodes)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.mediaRating)) * 31;
            String str = this.permalink;
            int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.synopsis;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.type;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.mediaType;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.country;
            int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.language;
            int iHashCode6 = (((iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31) + this.images.hashCode()) * 31;
            List<String> list = this.altTitles;
            int iHashCode7 = (iHashCode6 + (list == null ? 0 : list.hashCode())) * 31;
            Long l = this.votes;
            int iHashCode8 = (iHashCode7 + (l == null ? 0 : l.hashCode())) * 31;
            String str7 = this.airedStart;
            int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.released;
            int iHashCode10 = (((iHashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31) + this.releaseDatesFmt.hashCode()) * 31;
            List<JsonElement> list2 = this.genres;
            int iHashCode11 = (iHashCode10 + (list2 == null ? 0 : list2.hashCode())) * 31;
            Trailer trailer = this.trailer;
            return ((((((((((((((((((((((((iHashCode11 + (trailer != null ? trailer.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.watchers)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.ranked)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.popularity)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.runtime)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.reviewsCount)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.recsCount)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.commentsCount)) * 31) + this.certification.hashCode()) * 31) + this.status.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.enableAds)) * 31) + this.sources.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.updatedAt);
        }

        public String toString() {
            return "Media(id=" + this.id + ", slug=" + this.slug + ", title=" + this.title + ", originalTitle=" + this.originalTitle + ", mediaYear=" + this.mediaYear + ", episodes=" + this.episodes + ", mediaRating=" + this.mediaRating + ", permalink=" + this.permalink + ", synopsis=" + this.synopsis + ", type=" + this.type + ", mediaType=" + this.mediaType + ", country=" + this.country + ", language=" + this.language + ", images=" + this.images + ", altTitles=" + this.altTitles + ", votes=" + this.votes + ", airedStart=" + this.airedStart + ", released=" + this.released + ", releaseDatesFmt=" + this.releaseDatesFmt + ", genres=" + this.genres + ", trailer=" + this.trailer + ", watchers=" + this.watchers + ", ranked=" + this.ranked + ", popularity=" + this.popularity + ", runtime=" + this.runtime + ", reviewsCount=" + this.reviewsCount + ", recsCount=" + this.recsCount + ", commentsCount=" + this.commentsCount + ", certification=" + this.certification + ", status=" + this.status + ", enableAds=" + this.enableAds + ", sources=" + this.sources + ", updatedAt=" + this.updatedAt + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Media;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Media> serializer() {
                return MyDramaListAPI$Media$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Media(int i, int i2, long j, String str, String str2, String str3, int i3, long j2, double d, String str4, String str5, String str6, String str7, String str8, String str9, Images images, List list, Long l, String str10, String str11, String str12, List list2, Trailer trailer, long j3, long j4, long j5, long j6, long j7, long j8, long j9, String str13, String str14, boolean z, List list3, long j10, SerializationConstructorMarker serializationConstructorMarker) {
            if ((-778113 != (i & (-778113))) | (1 != (i2 & 1))) {
                PluginExceptionsKt.throwArrayMissingFieldException(new int[]{i, i2}, new int[]{-778113, 1}, MyDramaListAPI$Media$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.slug = str;
            this.title = str2;
            this.originalTitle = str3;
            this.mediaYear = i3;
            this.episodes = j2;
            this.mediaRating = d;
            if ((i & 128) == 0) {
                this.permalink = null;
            } else {
                this.permalink = str4;
            }
            if ((i & 256) == 0) {
                this.synopsis = null;
            } else {
                this.synopsis = str5;
            }
            if ((i & 512) == 0) {
                this.type = null;
            } else {
                this.type = str6;
            }
            if ((i & 1024) == 0) {
                this.mediaType = null;
            } else {
                this.mediaType = str7;
            }
            if ((i & 2048) == 0) {
                this.country = null;
            } else {
                this.country = str8;
            }
            if ((i & 4096) == 0) {
                this.language = null;
            } else {
                this.language = str9;
            }
            this.images = images;
            if ((i & 16384) == 0) {
                this.altTitles = null;
            } else {
                this.altTitles = list;
            }
            if ((32768 & i) == 0) {
                this.votes = null;
            } else {
                this.votes = l;
            }
            if ((65536 & i) == 0) {
                this.airedStart = null;
            } else {
                this.airedStart = str10;
            }
            if ((131072 & i) == 0) {
                this.released = null;
            } else {
                this.released = str11;
            }
            this.releaseDatesFmt = str12;
            if ((i & 524288) == 0) {
                this.genres = null;
            } else {
                this.genres = list2;
            }
            this.trailer = trailer;
            this.watchers = j3;
            this.ranked = j4;
            this.popularity = j5;
            this.runtime = j6;
            this.reviewsCount = j7;
            this.recsCount = j8;
            this.commentsCount = j9;
            this.certification = str13;
            this.status = str14;
            this.enableAds = z;
            this.sources = list3;
            this.updatedAt = j10;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Media(@JsonProperty("id") long j, @JsonProperty("slug") String slug, @JsonProperty("title") String title, @JsonProperty("original_title") String originalTitle, @JsonProperty("year") int i, @JsonProperty("episodes") long j2, @JsonProperty("rating") double d, @JsonProperty("permalink") String str, @JsonProperty("synopsis") String str2, @JsonProperty("type") String str3, @JsonProperty("media_type") String str4, @JsonProperty("country") String str5, @JsonProperty("language") String str6, @JsonProperty("images") Images images, @JsonProperty("alt_titles") List<String> list, @JsonProperty("votes") Long l, @JsonProperty("aired_start") String str7, @JsonProperty("released") String str8, @JsonProperty("release_dates_fmt") String releaseDatesFmt, @JsonProperty("genres") List<? extends JsonElement> list2, @JsonProperty("trailer") Trailer trailer, @JsonProperty("watchers") long j3, @JsonProperty("ranked") long j4, @JsonProperty("popularity") long j5, @JsonProperty("runtime") long j6, @JsonProperty("reviews_count") long j7, @JsonProperty("recs_count") long j8, @JsonProperty("comments_count") long j9, @JsonProperty("certification") String certification, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("enable_ads") boolean z, @JsonProperty("sources") List<Source> sources, @JsonProperty("updated_at") long j10) {
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(originalTitle, "originalTitle");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(releaseDatesFmt, "releaseDatesFmt");
            Intrinsics.checkNotNullParameter(certification, "certification");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(sources, "sources");
            this.id = j;
            this.slug = slug;
            this.title = title;
            this.originalTitle = originalTitle;
            this.mediaYear = i;
            this.episodes = j2;
            this.mediaRating = d;
            this.permalink = str;
            this.synopsis = str2;
            this.type = str3;
            this.mediaType = str4;
            this.country = str5;
            this.language = str6;
            this.images = images;
            this.altTitles = list;
            this.votes = l;
            this.airedStart = str7;
            this.released = str8;
            this.releaseDatesFmt = releaseDatesFmt;
            this.genres = list2;
            this.trailer = trailer;
            this.watchers = j3;
            this.ranked = j4;
            this.popularity = j5;
            this.runtime = j6;
            this.reviewsCount = j7;
            this.recsCount = j8;
            this.commentsCount = j9;
            this.certification = certification;
            this.status = status;
            this.enableAds = z;
            this.sources = sources;
            this.updatedAt = j10;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Media self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.slug);
            output.encodeStringElement(serialDesc, 2, self.title);
            output.encodeStringElement(serialDesc, 3, self.originalTitle);
            output.encodeIntElement(serialDesc, 4, self.mediaYear);
            output.encodeLongElement(serialDesc, 5, self.episodes);
            output.encodeDoubleElement(serialDesc, 6, self.mediaRating);
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.permalink != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.permalink);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.synopsis != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.synopsis);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 9) || self.type != null) {
                output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.type);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 10) || self.mediaType != null) {
                output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.mediaType);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 11) || self.country != null) {
                output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.country);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 12) || self.language != null) {
                output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.language);
            }
            output.encodeSerializableElement(serialDesc, 13, MyDramaListAPI$Images$$serializer.INSTANCE, self.images);
            if (output.shouldEncodeElementDefault(serialDesc, 14) || self.altTitles != null) {
                output.encodeNullableSerializableElement(serialDesc, 14, lazyArr[14].getValue(), self.altTitles);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 15) || self.votes != null) {
                output.encodeNullableSerializableElement(serialDesc, 15, LongSerializer.INSTANCE, self.votes);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 16) || self.airedStart != null) {
                output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.airedStart);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 17) || self.released != null) {
                output.encodeNullableSerializableElement(serialDesc, 17, StringSerializer.INSTANCE, self.released);
            }
            output.encodeStringElement(serialDesc, 18, self.releaseDatesFmt);
            if (output.shouldEncodeElementDefault(serialDesc, 19) || self.genres != null) {
                output.encodeNullableSerializableElement(serialDesc, 19, lazyArr[19].getValue(), self.genres);
            }
            output.encodeNullableSerializableElement(serialDesc, 20, MyDramaListAPI$Trailer$$serializer.INSTANCE, self.trailer);
            output.encodeLongElement(serialDesc, 21, self.watchers);
            output.encodeLongElement(serialDesc, 22, self.ranked);
            output.encodeLongElement(serialDesc, 23, self.popularity);
            output.encodeLongElement(serialDesc, 24, self.runtime);
            output.encodeLongElement(serialDesc, 25, self.reviewsCount);
            output.encodeLongElement(serialDesc, 26, self.recsCount);
            output.encodeLongElement(serialDesc, 27, self.commentsCount);
            output.encodeStringElement(serialDesc, 28, self.certification);
            output.encodeStringElement(serialDesc, 29, self.status);
            output.encodeBooleanElement(serialDesc, 30, self.enableAds);
            output.encodeSerializableElement(serialDesc, 31, lazyArr[31].getValue(), self.sources);
            output.encodeLongElement(serialDesc, 32, self.updatedAt);
        }

        public /* synthetic */ Media(long j, String str, String str2, String str3, int i, long j2, double d, String str4, String str5, String str6, String str7, String str8, String str9, Images images, List list, Long l, String str10, String str11, String str12, List list2, Trailer trailer, long j3, long j4, long j5, long j6, long j7, long j8, long j9, String str13, String str14, boolean z, List list3, long j10, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, str, str2, str3, i, j2, d, (i2 & 128) != 0 ? null : str4, (i2 & 256) != 0 ? null : str5, (i2 & 512) != 0 ? null : str6, (i2 & 1024) != 0 ? null : str7, (i2 & 2048) != 0 ? null : str8, (i2 & 4096) != 0 ? null : str9, images, (i2 & 16384) != 0 ? null : list, (32768 & i2) != 0 ? null : l, (65536 & i2) != 0 ? null : str10, (131072 & i2) != 0 ? null : str11, str12, (i2 & 524288) != 0 ? null : list2, trailer, j3, j4, j5, j6, j7, j8, j9, str13, str14, z, list3, j10);
        }

        public final long getId() {
            return this.id;
        }

        public final String getSlug() {
            return this.slug;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getOriginalTitle() {
            return this.originalTitle;
        }

        public final int getMediaYear() {
            return this.mediaYear;
        }

        public final long getEpisodes() {
            return this.episodes;
        }

        public final double getMediaRating() {
            return this.mediaRating;
        }

        public final String getPermalink() {
            return this.permalink;
        }

        public final String getSynopsis() {
            return this.synopsis;
        }

        public final String getType() {
            return this.type;
        }

        public final String getMediaType() {
            return this.mediaType;
        }

        public final String getCountry() {
            return this.country;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final Images getImages() {
            return this.images;
        }

        public final List<String> getAltTitles() {
            return this.altTitles;
        }

        public final Long getVotes() {
            return this.votes;
        }

        public final String getAiredStart() {
            return this.airedStart;
        }

        public final String getReleased() {
            return this.released;
        }

        public final String getReleaseDatesFmt() {
            return this.releaseDatesFmt;
        }

        public final List<JsonElement> getGenres() {
            return this.genres;
        }

        public final Trailer getTrailer() {
            return this.trailer;
        }

        public final long getWatchers() {
            return this.watchers;
        }

        public final long getRanked() {
            return this.ranked;
        }

        public final long getPopularity() {
            return this.popularity;
        }

        public final long getRuntime() {
            return this.runtime;
        }

        public final long getReviewsCount() {
            return this.reviewsCount;
        }

        public final long getRecsCount() {
            return this.recsCount;
        }

        public final long getCommentsCount() {
            return this.commentsCount;
        }

        public final String getCertification() {
            return this.certification;
        }

        public final String getStatus() {
            return this.status;
        }

        public final boolean getEnableAds() {
            return this.enableAds;
        }

        public final List<Source> getSources() {
            return this.sources;
        }

        public final long getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        public final Object fetchCredits(Continuation<? super List<ActorData>> continuation) {
            MyDramaListAPI$Media$fetchCredits$1 myDramaListAPI$Media$fetchCredits$1;
            if (continuation instanceof MyDramaListAPI$Media$fetchCredits$1) {
                myDramaListAPI$Media$fetchCredits$1 = (MyDramaListAPI$Media$fetchCredits$1) continuation;
                if ((myDramaListAPI$Media$fetchCredits$1.label & Integer.MIN_VALUE) != 0) {
                    myDramaListAPI$Media$fetchCredits$1.label -= Integer.MIN_VALUE;
                } else {
                    myDramaListAPI$Media$fetchCredits$1 = new MyDramaListAPI$Media$fetchCredits$1(this, continuation);
                }
            } else {
                myDramaListAPI$Media$fetchCredits$1 = new MyDramaListAPI$Media$fetchCredits$1(this, continuation);
            }
            Object obj = myDramaListAPI$Media$fetchCredits$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = myDramaListAPI$Media$fetchCredits$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Requests app2 = MainActivityKt.getApp();
                String str = "https://api.mydramalist.com/v1/titles/" + this.id + "/credits";
                MyDramaListInterceptor myDramaListInterceptor = MyDramaListAPI.headerInterceptor;
                myDramaListAPI$Media$fetchCredits$1.label = 1;
                Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, myDramaListAPI$Media$fetchCredits$1, 3582, null);
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
            NiceResponse niceResponse = (NiceResponse) obj;
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            List<Cast> cast = ((Credits) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Credits.class))).getCast();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(cast, 10));
            for (Cast cast2 : cast) {
                arrayList.add(new ActorData(new Actor(cast2.getName(), cast2.getImages().getPoster()), null, cast2.getCharacterName(), null, 10, null));
            }
            return arrayList;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        public final Object fetchRecommendations(Continuation<? super ArrayList<MediaSummary>> continuation) {
            MyDramaListAPI$Media$fetchRecommendations$1 myDramaListAPI$Media$fetchRecommendations$1;
            if (continuation instanceof MyDramaListAPI$Media$fetchRecommendations$1) {
                myDramaListAPI$Media$fetchRecommendations$1 = (MyDramaListAPI$Media$fetchRecommendations$1) continuation;
                if ((myDramaListAPI$Media$fetchRecommendations$1.label & Integer.MIN_VALUE) != 0) {
                    myDramaListAPI$Media$fetchRecommendations$1.label -= Integer.MIN_VALUE;
                } else {
                    myDramaListAPI$Media$fetchRecommendations$1 = new MyDramaListAPI$Media$fetchRecommendations$1(this, continuation);
                }
            } else {
                myDramaListAPI$Media$fetchRecommendations$1 = new MyDramaListAPI$Media$fetchRecommendations$1(this, continuation);
            }
            Object obj = myDramaListAPI$Media$fetchRecommendations$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = myDramaListAPI$Media$fetchRecommendations$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Requests app2 = MainActivityKt.getApp();
                String str = "https://api.mydramalist.com/v1/titles/" + this.id + "/recommendations";
                MyDramaListInterceptor myDramaListInterceptor = MyDramaListAPI.headerInterceptor;
                myDramaListAPI$Media$fetchRecommendations$1.label = 1;
                Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, myDramaListAPI$Media$fetchRecommendations$1, 3582, null);
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
            NiceResponse niceResponse = (NiceResponse) obj;
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ArrayList.class));
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        public final Object fetchTrailer(Continuation<? super String> continuation) {
            MyDramaListAPI$Media$fetchTrailer$1 myDramaListAPI$Media$fetchTrailer$1;
            Object safe;
            TrailerNode trailer;
            TrailerDetails trailerDetails;
            if (continuation instanceof MyDramaListAPI$Media$fetchTrailer$1) {
                myDramaListAPI$Media$fetchTrailer$1 = (MyDramaListAPI$Media$fetchTrailer$1) continuation;
                if ((myDramaListAPI$Media$fetchTrailer$1.label & Integer.MIN_VALUE) != 0) {
                    myDramaListAPI$Media$fetchTrailer$1.label -= Integer.MIN_VALUE;
                } else {
                    myDramaListAPI$Media$fetchTrailer$1 = new MyDramaListAPI$Media$fetchTrailer$1(this, continuation);
                }
            } else {
                myDramaListAPI$Media$fetchTrailer$1 = new MyDramaListAPI$Media$fetchTrailer$1(this, continuation);
            }
            Object obj = myDramaListAPI$Media$fetchTrailer$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = myDramaListAPI$Media$fetchTrailer$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Requests app2 = MainActivityKt.getApp();
                StringBuilder sb = new StringBuilder("https://mydramalist.com/v1/trailers/");
                Trailer trailer2 = this.trailer;
                sb.append(trailer2 != null ? trailer2.getId() : null);
                String string = sb.toString();
                MyDramaListInterceptor myDramaListInterceptor = MyDramaListAPI.headerInterceptor;
                myDramaListAPI$Media$fetchTrailer$1.label = 1;
                Object obj2 = Requests.get$default(app2, string, null, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, myDramaListAPI$Media$fetchTrailer$1, 3582, null);
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
            NiceResponse niceResponse = (NiceResponse) obj;
            try {
                ResponseParser parser = niceResponse.getParser();
                Intrinsics.checkNotNull(parser);
                safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(TrailerRoot.class));
            } catch (Exception e) {
                e.printStackTrace();
                safe = null;
            }
            TrailerRoot trailerRoot = (TrailerRoot) safe;
            if (trailerRoot == null || (trailer = trailerRoot.getTrailer()) == null || (trailerDetails = trailer.getTrailerDetails()) == null) {
                return null;
            }
            return trailerDetails.getSource();
        }

        /* JADX WARN: Code duplicated, block: B:18:0x003c  */
        public final List<String> fixGenres() {
            String str;
            List<JsonElement> list = this.genres;
            if (!(list instanceof List)) {
                list = null;
            }
            if (list == null) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof String) {
                    str = (String) obj;
                } else if (obj instanceof Map) {
                    Object obj2 = ((Map) obj).get("name");
                    if (obj2 instanceof String) {
                        str = (String) obj2;
                    } else {
                        str = null;
                    }
                } else {
                    str = null;
                }
                if (str != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object fetchEpisodes(Media media, Continuation<? super List<Episode>> continuation) {
        C07271 c07271;
        Media media2;
        if (continuation instanceof C07271) {
            c07271 = (C07271) continuation;
            if ((c07271.label & Integer.MIN_VALUE) != 0) {
                c07271.label -= Integer.MIN_VALUE;
            } else {
                c07271 = new C07271(continuation);
            }
        } else {
            c07271 = new C07271(continuation);
        }
        Object obj = c07271.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07271.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = "https://api.mydramalist.com/v1/titles/" + media.getId() + "/episodes";
            MyDramaListInterceptor myDramaListInterceptor = headerInterceptor;
            c07271.L$0 = media;
            c07271.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, myDramaListInterceptor, false, null, c07271, 3582, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
            media2 = media;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            media2 = (Media) c07271.L$0;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        Iterable iterable = (Iterable) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ArrayList.class));
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((ShowEpisodesItem) it.next()).getEpisodes());
        }
        List<ShowEpisode> listFlatten = CollectionsKt.flatten(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFlatten, 10));
        for (final ShowEpisode showEpisode : listFlatten) {
            arrayList2.add(MainAPIKt.newEpisode(this, AppUtils.INSTANCE.toJson(new LinkData(Boxing.boxLong(media2.getId()), "Series", Boxing.boxInt(0), Boxing.boxInt(showEpisode.getEpisodeNumber()), media2.getTitle(), Boxing.boxInt(media2.getMediaYear()), media2.getOriginalTitle(), (Integer) null, media2.getReleased(), media2.getAiredStart(), 128, (DefaultConstructorMarker) null)), new Function1() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return MyDramaListAPI.fetchEpisodes$lambda$1$0(showEpisode, (Episode) obj3);
                }
            }));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fetchEpisodes$lambda$1$0(ShowEpisode showEpisode, Episode newEpisode) {
        Intrinsics.checkNotNullParameter(newEpisode, "$this$newEpisode");
        newEpisode.setName(null);
        newEpisode.setSeason(null);
        newEpisode.setEpisode(Integer.valueOf(showEpisode.getEpisodeNumber()));
        newEpisode.setPosterUrl(null);
        newEpisode.setScore(Score.INSTANCE.from10(Double.valueOf(showEpisode.getRating())));
        newEpisode.setDescription(null);
        newEpisode.setRunTime(null);
        MainAPIKt.addDate$default(newEpisode, showEpisode.getReleasedAt(), null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB7\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J'\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Genre;", "", "id", "", "name", "", "slug", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getName$annotations", "getName", "()Ljava/lang/String;", "getSlug$annotations", "getSlug", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Genre {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final long id;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("slug")
        private final String slug;

        public static /* synthetic */ Genre copy$default(Genre genre, long j, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = genre.id;
            }
            if ((i & 2) != 0) {
                str = genre.name;
            }
            if ((i & 4) != 0) {
                str2 = genre.slug;
            }
            return genre.copy(j, str, str2);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("slug")
        public static /* synthetic */ void getSlug$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        public final Genre copy(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("slug") String slug) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(slug, "slug");
            return new Genre(id, name, slug);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Genre)) {
                return false;
            }
            Genre genre = (Genre) other;
            return this.id == genre.id && Intrinsics.areEqual(this.name, genre.name) && Intrinsics.areEqual(this.slug, genre.slug);
        }

        public int hashCode() {
            return (((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.name.hashCode()) * 31) + this.slug.hashCode();
        }

        public String toString() {
            return "Genre(id=" + this.id + ", name=" + this.name + ", slug=" + this.slug + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Genre$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Genre;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Genre> serializer() {
                return MyDramaListAPI$Genre$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Genre(int i, long j, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, MyDramaListAPI$Genre$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.name = str;
            this.slug = str2;
        }

        public Genre(@JsonProperty("id") long j, @JsonProperty("name") String name, @JsonProperty("slug") String slug) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(slug, "slug");
            this.id = j;
            this.name = name;
            this.slug = slug;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Genre self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.slug);
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getSlug() {
            return this.slug;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB7\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J'\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Tag;", "", "id", "", "name", "", "slug", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getName$annotations", "getName", "()Ljava/lang/String;", "getSlug$annotations", "getSlug", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Tag {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final long id;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("slug")
        private final String slug;

        public static /* synthetic */ Tag copy$default(Tag tag, long j, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = tag.id;
            }
            if ((i & 2) != 0) {
                str = tag.name;
            }
            if ((i & 4) != 0) {
                str2 = tag.slug;
            }
            return tag.copy(j, str, str2);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("slug")
        public static /* synthetic */ void getSlug$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        public final Tag copy(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("slug") String slug) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(slug, "slug");
            return new Tag(id, name, slug);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Tag)) {
                return false;
            }
            Tag tag = (Tag) other;
            return this.id == tag.id && Intrinsics.areEqual(this.name, tag.name) && Intrinsics.areEqual(this.slug, tag.slug);
        }

        public int hashCode() {
            return (((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.name.hashCode()) * 31) + this.slug.hashCode();
        }

        public String toString() {
            return "Tag(id=" + this.id + ", name=" + this.name + ", slug=" + this.slug + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Tag$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Tag;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Tag> serializer() {
                return MyDramaListAPI$Tag$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Tag(int i, long j, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, MyDramaListAPI$Tag$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.name = str;
            this.slug = str2;
        }

        public Tag(@JsonProperty("id") long j, @JsonProperty("name") String name, @JsonProperty("slug") String slug) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(slug, "slug");
            this.id = j;
            this.name = name;
            this.slug = slug;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Tag self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.slug);
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getSlug() {
            return this.slug;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223BC\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nBW\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003JE\u0010$\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020\fHÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0013R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u0013R\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0011\u001a\u0004\b\u001d\u0010\u0013¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Source;", "", "xid", "", "name", "source", "sourceType", "link", "image", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getXid$annotations", "()V", "getXid", "()Ljava/lang/String;", "getName$annotations", "getName", "getSource$annotations", "getSource", "getSourceType$annotations", "getSourceType", "getLink$annotations", "getLink", "getImage$annotations", "getImage", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Source {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("image")
        private final String image;

        @JsonProperty("link")
        private final String link;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("source")
        private final String source;

        @JsonProperty("source_type")
        private final String sourceType;

        @JsonProperty("xid")
        private final String xid;

        public static /* synthetic */ Source copy$default(Source source, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = source.xid;
            }
            if ((i & 2) != 0) {
                str2 = source.name;
            }
            if ((i & 4) != 0) {
                str3 = source.source;
            }
            if ((i & 8) != 0) {
                str4 = source.sourceType;
            }
            if ((i & 16) != 0) {
                str5 = source.link;
            }
            if ((i & 32) != 0) {
                str6 = source.image;
            }
            String str7 = str5;
            String str8 = str6;
            return source.copy(str, str2, str3, str4, str7, str8);
        }

        @SerialName("image")
        public static /* synthetic */ void getImage$annotations() {
        }

        @SerialName("link")
        public static /* synthetic */ void getLink$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("source")
        public static /* synthetic */ void getSource$annotations() {
        }

        @SerialName("source_type")
        public static /* synthetic */ void getSourceType$annotations() {
        }

        @SerialName("xid")
        public static /* synthetic */ void getXid$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getXid() {
            return this.xid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSourceType() {
            return this.sourceType;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        public final Source copy(@JsonProperty("xid") String xid, @JsonProperty("name") String name, @JsonProperty("source") String source, @JsonProperty("source_type") String sourceType, @JsonProperty("link") String link, @JsonProperty("image") String image) {
            Intrinsics.checkNotNullParameter(xid, "xid");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sourceType, "sourceType");
            Intrinsics.checkNotNullParameter(link, "link");
            Intrinsics.checkNotNullParameter(image, "image");
            return new Source(xid, name, source, sourceType, link, image);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Source)) {
                return false;
            }
            Source source = (Source) other;
            return Intrinsics.areEqual(this.xid, source.xid) && Intrinsics.areEqual(this.name, source.name) && Intrinsics.areEqual(this.source, source.source) && Intrinsics.areEqual(this.sourceType, source.sourceType) && Intrinsics.areEqual(this.link, source.link) && Intrinsics.areEqual(this.image, source.image);
        }

        public int hashCode() {
            return (((((((((this.xid.hashCode() * 31) + this.name.hashCode()) * 31) + this.source.hashCode()) * 31) + this.sourceType.hashCode()) * 31) + this.link.hashCode()) * 31) + this.image.hashCode();
        }

        public String toString() {
            return "Source(xid=" + this.xid + ", name=" + this.name + ", source=" + this.source + ", sourceType=" + this.sourceType + ", link=" + this.link + ", image=" + this.image + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Source$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Source;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Source> serializer() {
                return MyDramaListAPI$Source$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Source(int i, String str, String str2, String str3, String str4, String str5, String str6, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, MyDramaListAPI$Source$$serializer.INSTANCE.getDescriptor());
            }
            this.xid = str;
            this.name = str2;
            this.source = str3;
            this.sourceType = str4;
            this.link = str5;
            this.image = str6;
        }

        public Source(@JsonProperty("xid") String xid, @JsonProperty("name") String name, @JsonProperty("source") String source, @JsonProperty("source_type") String sourceType, @JsonProperty("link") String link, @JsonProperty("image") String image) {
            Intrinsics.checkNotNullParameter(xid, "xid");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sourceType, "sourceType");
            Intrinsics.checkNotNullParameter(link, "link");
            Intrinsics.checkNotNullParameter(image, "image");
            this.xid = xid;
            this.name = name;
            this.source = source;
            this.sourceType = sourceType;
            this.link = link;
            this.image = image;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Source self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.xid);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.source);
            output.encodeStringElement(serialDesc, 3, self.sourceType);
            output.encodeStringElement(serialDesc, 4, self.link);
            output.encodeStringElement(serialDesc, 5, self.image);
        }

        public final String getXid() {
            return this.xid;
        }

        public final String getName() {
            return this.name;
        }

        public final String getSource() {
            return this.source;
        }

        public final String getSourceType() {
            return this.sourceType;
        }

        public final String getLink() {
            return this.link;
        }

        public final String getImage() {
            return this.image;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;", "", "id", "", "<init>", "(Ljava/lang/Long;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "copy", "(Ljava/lang/Long;)Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Trailer {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final Long id;

        /* JADX WARN: Multi-variable type inference failed */
        public Trailer() {
            this((Long) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ Trailer copy$default(Trailer trailer, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                l = trailer.id;
            }
            return trailer.copy(l);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        public final Trailer copy(@JsonProperty("id") Long id) {
            return new Trailer(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Trailer) && Intrinsics.areEqual(this.id, ((Trailer) other).id);
        }

        public int hashCode() {
            Long l = this.id;
            if (l == null) {
                return 0;
            }
            return l.hashCode();
        }

        public String toString() {
            return "Trailer(id=" + this.id + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Trailer;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Trailer> serializer() {
                return MyDramaListAPI$Trailer$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Trailer(int i, Long l, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = l;
            }
        }

        public Trailer(@JsonProperty("id") Long l) {
            this.id = l;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Trailer self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.id == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.id);
        }

        public /* synthetic */ Trailer(Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l);
        }

        public final Long getId() {
            return this.id;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B'\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bB;\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\u0016\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Credits;", "", "cast", "", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Cast;", "crew", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Crew;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getCast$annotations", "()V", "getCast", "()Ljava/util/List;", "getCrew$annotations", "getCrew", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Credits {

        @JsonProperty("cast")
        private final List<Cast> cast;

        @JsonProperty("crew")
        private final List<Crew> crew;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Credits$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.Credits._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$Credits$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.Credits._childSerializers$_anonymous_$0();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MyDramaListAPI$Cast$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(MyDramaListAPI$Crew$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Credits copy$default(Credits credits, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = credits.cast;
            }
            if ((i & 2) != 0) {
                list2 = credits.crew;
            }
            return credits.copy(list, list2);
        }

        @SerialName("cast")
        public static /* synthetic */ void getCast$annotations() {
        }

        @SerialName("crew")
        public static /* synthetic */ void getCrew$annotations() {
        }

        public final List<Cast> component1() {
            return this.cast;
        }

        public final List<Crew> component2() {
            return this.crew;
        }

        public final Credits copy(@JsonProperty("cast") List<Cast> cast, @JsonProperty("crew") List<Crew> crew) {
            Intrinsics.checkNotNullParameter(cast, "cast");
            Intrinsics.checkNotNullParameter(crew, "crew");
            return new Credits(cast, crew);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Credits)) {
                return false;
            }
            Credits credits = (Credits) other;
            return Intrinsics.areEqual(this.cast, credits.cast) && Intrinsics.areEqual(this.crew, credits.crew);
        }

        public int hashCode() {
            return (this.cast.hashCode() * 31) + this.crew.hashCode();
        }

        public String toString() {
            return "Credits(cast=" + this.cast + ", crew=" + this.crew + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Credits$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Credits;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Credits> serializer() {
                return MyDramaListAPI$Credits$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Credits(int i, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MyDramaListAPI$Credits$$serializer.INSTANCE.getDescriptor());
            }
            this.cast = list;
            this.crew = list2;
        }

        public Credits(@JsonProperty("cast") List<Cast> cast, @JsonProperty("crew") List<Crew> crew) {
            Intrinsics.checkNotNullParameter(cast, "cast");
            Intrinsics.checkNotNullParameter(crew, "crew");
            this.cast = cast;
            this.crew = crew;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Credits self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.cast);
            output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.crew);
        }

        public final List<Cast> getCast() {
            return this.cast;
        }

        public final List<Crew> getCrew() {
            return this.crew;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ;2\u00020\u0001:\u0002:;BM\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rB_\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\f\u0010\u0012J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003JO\u0010,\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u00052\b\b\u0003\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u00020\u000fHÖ\u0081\u0004J\n\u00101\u001a\u00020\u0005HÖ\u0081\u0004J%\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0001¢\u0006\u0002\b9R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u0019R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0014\u001a\u0004\b\u001d\u0010\u0019R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001f\u0010 R\u001c\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0014\u001a\u0004\b\"\u0010\u0019R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0014\u001a\u0004\b$\u0010\u0019¨\u0006<"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Cast;", "", "id", "", "name", "", ImagesContract.URL, "slug", "images", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "characterName", "role", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getName$annotations", "getName", "()Ljava/lang/String;", "getUrl$annotations", "getUrl", "getSlug$annotations", "getSlug", "getImages$annotations", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "getCharacterName$annotations", "getCharacterName", "getRole$annotations", "getRole", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Cast {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("character_name")
        private final String characterName;

        @JsonProperty("id")
        private final long id;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("role")
        private final String role;

        @JsonProperty("slug")
        private final String slug;

        @JsonProperty(ImagesContract.URL)
        private final String url;

        public static /* synthetic */ Cast copy$default(Cast cast, long j, String str, String str2, String str3, Images images, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                j = cast.id;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = cast.name;
            }
            String str6 = str;
            if ((i & 4) != 0) {
                str2 = cast.url;
            }
            String str7 = str2;
            if ((i & 8) != 0) {
                str3 = cast.slug;
            }
            String str8 = str3;
            if ((i & 16) != 0) {
                images = cast.images;
            }
            return cast.copy(j2, str6, str7, str8, images, (i & 32) != 0 ? cast.characterName : str4, (i & 64) != 0 ? cast.role : str5);
        }

        @SerialName("character_name")
        public static /* synthetic */ void getCharacterName$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("images")
        public static /* synthetic */ void getImages$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("role")
        public static /* synthetic */ void getRole$annotations() {
        }

        @SerialName("slug")
        public static /* synthetic */ void getSlug$annotations() {
        }

        @SerialName(ImagesContract.URL)
        public static /* synthetic */ void getUrl$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getCharacterName() {
            return this.characterName;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        public final Cast copy(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("slug") String slug, @JsonProperty("images") Images images, @JsonProperty("character_name") String characterName, @JsonProperty("role") String role) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(characterName, "characterName");
            Intrinsics.checkNotNullParameter(role, "role");
            return new Cast(id, name, url, slug, images, characterName, role);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Cast)) {
                return false;
            }
            Cast cast = (Cast) other;
            return this.id == cast.id && Intrinsics.areEqual(this.name, cast.name) && Intrinsics.areEqual(this.url, cast.url) && Intrinsics.areEqual(this.slug, cast.slug) && Intrinsics.areEqual(this.images, cast.images) && Intrinsics.areEqual(this.characterName, cast.characterName) && Intrinsics.areEqual(this.role, cast.role);
        }

        public int hashCode() {
            return (((((((((((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.slug.hashCode()) * 31) + this.images.hashCode()) * 31) + this.characterName.hashCode()) * 31) + this.role.hashCode();
        }

        public String toString() {
            return "Cast(id=" + this.id + ", name=" + this.name + ", url=" + this.url + ", slug=" + this.slug + ", images=" + this.images + ", characterName=" + this.characterName + ", role=" + this.role + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Cast$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Cast;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Cast> serializer() {
                return MyDramaListAPI$Cast$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Cast(int i, long j, String str, String str2, String str3, Images images, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
            if (127 != (i & 127)) {
                PluginExceptionsKt.throwMissingFieldException(i, 127, MyDramaListAPI$Cast$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.name = str;
            this.url = str2;
            this.slug = str3;
            this.images = images;
            this.characterName = str4;
            this.role = str5;
        }

        public Cast(@JsonProperty("id") long j, @JsonProperty("name") String name, @JsonProperty(ImagesContract.URL) String url, @JsonProperty("slug") String slug, @JsonProperty("images") Images images, @JsonProperty("character_name") String characterName, @JsonProperty("role") String role) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(characterName, "characterName");
            Intrinsics.checkNotNullParameter(role, "role");
            this.id = j;
            this.name = name;
            this.url = url;
            this.slug = slug;
            this.images = images;
            this.characterName = characterName;
            this.role = role;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Cast self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.url);
            output.encodeStringElement(serialDesc, 3, self.slug);
            output.encodeSerializableElement(serialDesc, 4, MyDramaListAPI$Images$$serializer.INSTANCE, self.images);
            output.encodeStringElement(serialDesc, 5, self.characterName);
            output.encodeStringElement(serialDesc, 6, self.role);
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getUrl() {
            return this.url;
        }

        public final String getSlug() {
            return this.slug;
        }

        public final Images getImages() {
            return this.images;
        }

        public final String getCharacterName() {
            return this.characterName;
        }

        public final String getRole() {
            return this.role;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bBK\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J;\u0010$\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0014\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020\rHÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001e\u0010\u0017¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Crew;", "", "id", "", "name", "", "slug", "images", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "job", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getName$annotations", "getName", "()Ljava/lang/String;", "getSlug$annotations", "getSlug", "getImages$annotations", "getImages", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Images;", "getJob$annotations", "getJob", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Crew {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final long id;

        @JsonProperty("images")
        private final Images images;

        @JsonProperty("job")
        private final String job;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("slug")
        private final String slug;

        public static /* synthetic */ Crew copy$default(Crew crew, long j, String str, String str2, Images images, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                j = crew.id;
            }
            long j2 = j;
            if ((i & 2) != 0) {
                str = crew.name;
            }
            String str4 = str;
            if ((i & 4) != 0) {
                str2 = crew.slug;
            }
            String str5 = str2;
            if ((i & 8) != 0) {
                images = crew.images;
            }
            Images images2 = images;
            if ((i & 16) != 0) {
                str3 = crew.job;
            }
            return crew.copy(j2, str4, str5, images2, str3);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("images")
        public static /* synthetic */ void getImages$annotations() {
        }

        @SerialName("job")
        public static /* synthetic */ void getJob$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("slug")
        public static /* synthetic */ void getSlug$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Images getImages() {
            return this.images;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getJob() {
            return this.job;
        }

        public final Crew copy(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("slug") String slug, @JsonProperty("images") Images images, @JsonProperty("job") String job) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(job, "job");
            return new Crew(id, name, slug, images, job);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Crew)) {
                return false;
            }
            Crew crew = (Crew) other;
            return this.id == crew.id && Intrinsics.areEqual(this.name, crew.name) && Intrinsics.areEqual(this.slug, crew.slug) && Intrinsics.areEqual(this.images, crew.images) && Intrinsics.areEqual(this.job, crew.job);
        }

        public int hashCode() {
            return (((((((NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.name.hashCode()) * 31) + this.slug.hashCode()) * 31) + this.images.hashCode()) * 31) + this.job.hashCode();
        }

        public String toString() {
            return "Crew(id=" + this.id + ", name=" + this.name + ", slug=" + this.slug + ", images=" + this.images + ", job=" + this.job + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Crew$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$Crew;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Crew> serializer() {
                return MyDramaListAPI$Crew$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Crew(int i, long j, String str, String str2, Images images, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MyDramaListAPI$Crew$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.name = str;
            this.slug = str2;
            this.images = images;
            this.job = str3;
        }

        public Crew(@JsonProperty("id") long j, @JsonProperty("name") String name, @JsonProperty("slug") String slug, @JsonProperty("images") Images images, @JsonProperty("job") String job) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(slug, "slug");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(job, "job");
            this.id = j;
            this.name = name;
            this.slug = slug;
            this.images = images;
            this.job = job;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(Crew self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.slug);
            output.encodeSerializableElement(serialDesc, 3, MyDramaListAPI$Images$$serializer.INSTANCE, self.images);
            output.encodeStringElement(serialDesc, 4, self.job);
        }

        public final long getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getSlug() {
            return this.slug;
        }

        public final Images getImages() {
            return this.images;
        }

        public final String getJob() {
            return this.job;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223B?\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\b\b\u0001\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fBQ\b\u0010\u0012\u0006\u0010\r\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003JA\u0010$\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0003\u0010\b\u001a\u00020\u00032\b\b\u0003\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020\nHÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0014R\u001c\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u001e¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisodesItem;", "", "name", "", "releaseDate", "episodes", "", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisode;", "timezone", "total", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "getReleaseDate$annotations", "getReleaseDate", "getEpisodes$annotations", "getEpisodes", "()Ljava/util/List;", "getTimezone$annotations", "getTimezone", "getTotal$annotations", "getTotal", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ShowEpisodesItem {

        @JsonProperty("episodes")
        private final List<ShowEpisode> episodes;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("release_date")
        private final String releaseDate;

        @JsonProperty("timezone")
        private final String timezone;

        @JsonProperty("total")
        private final int total;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.metaproviders.MyDramaListAPI$ShowEpisodesItem$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MyDramaListAPI.ShowEpisodesItem._childSerializers$_anonymous_();
            }
        }), null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MyDramaListAPI$ShowEpisode$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ShowEpisodesItem copy$default(ShowEpisodesItem showEpisodesItem, String str, String str2, List list, String str3, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = showEpisodesItem.name;
            }
            if ((i2 & 2) != 0) {
                str2 = showEpisodesItem.releaseDate;
            }
            if ((i2 & 4) != 0) {
                list = showEpisodesItem.episodes;
            }
            if ((i2 & 8) != 0) {
                str3 = showEpisodesItem.timezone;
            }
            if ((i2 & 16) != 0) {
                i = showEpisodesItem.total;
            }
            int i3 = i;
            List list2 = list;
            return showEpisodesItem.copy(str, str2, list2, str3, i3);
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("release_date")
        public static /* synthetic */ void getReleaseDate$annotations() {
        }

        @SerialName("timezone")
        public static /* synthetic */ void getTimezone$annotations() {
        }

        @SerialName("total")
        public static /* synthetic */ void getTotal$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getReleaseDate() {
            return this.releaseDate;
        }

        public final List<ShowEpisode> component3() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getTimezone() {
            return this.timezone;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        public final ShowEpisodesItem copy(@JsonProperty("name") String name, @JsonProperty("release_date") String releaseDate, @JsonProperty("episodes") List<ShowEpisode> episodes, @JsonProperty("timezone") String timezone, @JsonProperty("total") int total) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(releaseDate, "releaseDate");
            Intrinsics.checkNotNullParameter(episodes, "episodes");
            Intrinsics.checkNotNullParameter(timezone, "timezone");
            return new ShowEpisodesItem(name, releaseDate, episodes, timezone, total);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ShowEpisodesItem)) {
                return false;
            }
            ShowEpisodesItem showEpisodesItem = (ShowEpisodesItem) other;
            return Intrinsics.areEqual(this.name, showEpisodesItem.name) && Intrinsics.areEqual(this.releaseDate, showEpisodesItem.releaseDate) && Intrinsics.areEqual(this.episodes, showEpisodesItem.episodes) && Intrinsics.areEqual(this.timezone, showEpisodesItem.timezone) && this.total == showEpisodesItem.total;
        }

        public int hashCode() {
            return (((((((this.name.hashCode() * 31) + this.releaseDate.hashCode()) * 31) + this.episodes.hashCode()) * 31) + this.timezone.hashCode()) * 31) + this.total;
        }

        public String toString() {
            return "ShowEpisodesItem(name=" + this.name + ", releaseDate=" + this.releaseDate + ", episodes=" + this.episodes + ", timezone=" + this.timezone + ", total=" + this.total + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisodesItem$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisodesItem;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ShowEpisodesItem> serializer() {
                return MyDramaListAPI$ShowEpisodesItem$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ShowEpisodesItem(int i, String str, String str2, List list, String str3, int i2, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MyDramaListAPI$ShowEpisodesItem$$serializer.INSTANCE.getDescriptor());
            }
            this.name = str;
            this.releaseDate = str2;
            this.episodes = list;
            this.timezone = str3;
            this.total = i2;
        }

        public ShowEpisodesItem(@JsonProperty("name") String name, @JsonProperty("release_date") String releaseDate, @JsonProperty("episodes") List<ShowEpisode> episodes, @JsonProperty("timezone") String timezone, @JsonProperty("total") int i) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(releaseDate, "releaseDate");
            Intrinsics.checkNotNullParameter(episodes, "episodes");
            Intrinsics.checkNotNullParameter(timezone, "timezone");
            this.name = name;
            this.releaseDate = releaseDate;
            this.episodes = episodes;
            this.timezone = timezone;
            this.total = i;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(ShowEpisodesItem self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.name);
            output.encodeStringElement(serialDesc, 1, self.releaseDate);
            output.encodeSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.episodes);
            output.encodeStringElement(serialDesc, 3, self.timezone);
            output.encodeIntElement(serialDesc, 4, self.total);
        }

        public final String getName() {
            return this.name;
        }

        public final String getReleaseDate() {
            return this.releaseDate;
        }

        public final List<ShowEpisode> getEpisodes() {
            return this.episodes;
        }

        public final String getTimezone() {
            return this.timezone;
        }

        public final int getTotal() {
            return this.total;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u000212B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBE\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J;\u0010#\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00032\b\b\u0003\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010(\u001a\u00020\tHÖ\u0081\u0004J%\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\b0R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u001a\u0010\u0013R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u001d¨\u00063"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisode;", "", "id", "", "episodeNumber", "rating", "", "votes", "releasedAt", "", "<init>", "(IIDILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIDILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getEpisodeNumber$annotations", "getEpisodeNumber", "getRating$annotations", "getRating", "()D", "getVotes$annotations", "getVotes", "getReleasedAt$annotations", "getReleasedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ShowEpisode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER)
        private final int episodeNumber;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("rating")
        private final double rating;

        @JsonProperty("released_at")
        private final String releasedAt;

        @JsonProperty("votes")
        private final int votes;

        public static /* synthetic */ ShowEpisode copy$default(ShowEpisode showEpisode, int i, int i2, double d, int i3, String str, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = showEpisode.id;
            }
            if ((i4 & 2) != 0) {
                i2 = showEpisode.episodeNumber;
            }
            if ((i4 & 4) != 0) {
                d = showEpisode.rating;
            }
            if ((i4 & 8) != 0) {
                i3 = showEpisode.votes;
            }
            if ((i4 & 16) != 0) {
                str = showEpisode.releasedAt;
            }
            double d2 = d;
            return showEpisode.copy(i, i2, d2, i3, str);
        }

        @SerialName(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER)
        public static /* synthetic */ void getEpisodeNumber$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getRating$annotations() {
        }

        @SerialName("released_at")
        public static /* synthetic */ void getReleasedAt$annotations() {
        }

        @SerialName("votes")
        public static /* synthetic */ void getVotes$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getEpisodeNumber() {
            return this.episodeNumber;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final double getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getVotes() {
            return this.votes;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getReleasedAt() {
            return this.releasedAt;
        }

        public final ShowEpisode copy(@JsonProperty("id") int id, @JsonProperty(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER) int episodeNumber, @JsonProperty("rating") double rating, @JsonProperty("votes") int votes, @JsonProperty("released_at") String releasedAt) {
            Intrinsics.checkNotNullParameter(releasedAt, "releasedAt");
            return new ShowEpisode(id, episodeNumber, rating, votes, releasedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ShowEpisode)) {
                return false;
            }
            ShowEpisode showEpisode = (ShowEpisode) other;
            return this.id == showEpisode.id && this.episodeNumber == showEpisode.episodeNumber && Double.compare(this.rating, showEpisode.rating) == 0 && this.votes == showEpisode.votes && Intrinsics.areEqual(this.releasedAt, showEpisode.releasedAt);
        }

        public int hashCode() {
            return (((((((this.id * 31) + this.episodeNumber) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.rating)) * 31) + this.votes) * 31) + this.releasedAt.hashCode();
        }

        public String toString() {
            return "ShowEpisode(id=" + this.id + ", episodeNumber=" + this.episodeNumber + ", rating=" + this.rating + ", votes=" + this.votes + ", releasedAt=" + this.releasedAt + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$ShowEpisode;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ShowEpisode> serializer() {
                return MyDramaListAPI$ShowEpisode$$serializer.INSTANCE;
            }
        }

        public ShowEpisode(@JsonProperty("id") int i, @JsonProperty(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER) int i2, @JsonProperty("rating") double d, @JsonProperty("votes") int i3, @JsonProperty("released_at") String releasedAt) {
            Intrinsics.checkNotNullParameter(releasedAt, "releasedAt");
            this.id = i;
            this.episodeNumber = i2;
            this.rating = d;
            this.votes = i3;
            this.releasedAt = releasedAt;
        }

        public /* synthetic */ ShowEpisode(int i, int i2, int i3, double d, int i4, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MyDramaListAPI$ShowEpisode$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.episodeNumber = i3;
            this.rating = d;
            this.votes = i4;
            this.releasedAt = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(ShowEpisode self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeIntElement(serialDesc, 1, self.episodeNumber);
            output.encodeDoubleElement(serialDesc, 2, self.rating);
            output.encodeIntElement(serialDesc, 3, self.votes);
            output.encodeStringElement(serialDesc, 4, self.releasedAt);
        }

        public final int getId() {
            return this.id;
        }

        public final int getEpisodeNumber() {
            return this.episodeNumber;
        }

        public final double getRating() {
            return this.rating;
        }

        public final int getVotes() {
            return this.votes;
        }

        public final String getReleasedAt() {
            return this.releasedAt;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerRoot;", "", "trailer", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode;", "<init>", "(Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTrailer$annotations", "()V", "getTrailer", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class TrailerRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("trailer")
        private final TrailerNode trailer;

        public static /* synthetic */ TrailerRoot copy$default(TrailerRoot trailerRoot, TrailerNode trailerNode, int i, Object obj) {
            if ((i & 1) != 0) {
                trailerNode = trailerRoot.trailer;
            }
            return trailerRoot.copy(trailerNode);
        }

        @SerialName("trailer")
        public static /* synthetic */ void getTrailer$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TrailerNode getTrailer() {
            return this.trailer;
        }

        public final TrailerRoot copy(@JsonProperty("trailer") TrailerNode trailer) {
            Intrinsics.checkNotNullParameter(trailer, "trailer");
            return new TrailerRoot(trailer);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TrailerRoot) && Intrinsics.areEqual(this.trailer, ((TrailerRoot) other).trailer);
        }

        public int hashCode() {
            return this.trailer.hashCode();
        }

        public String toString() {
            return "TrailerRoot(trailer=" + this.trailer + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerRoot;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TrailerRoot> serializer() {
                return MyDramaListAPI$TrailerRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ TrailerRoot(int i, TrailerNode trailerNode, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, MyDramaListAPI$TrailerRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.trailer = trailerNode;
        }

        public TrailerRoot(@JsonProperty("trailer") TrailerNode trailer) {
            Intrinsics.checkNotNullParameter(trailer, "trailer");
            this.trailer = trailer;
        }

        public final TrailerNode getTrailer() {
            return this.trailer;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode;", "", "trailerDetails", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails;", "<init>", "(Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTrailerDetails$annotations", "()V", "getTrailerDetails", "()Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class TrailerNode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("trailer")
        private final TrailerDetails trailerDetails;

        public static /* synthetic */ TrailerNode copy$default(TrailerNode trailerNode, TrailerDetails trailerDetails, int i, Object obj) {
            if ((i & 1) != 0) {
                trailerDetails = trailerNode.trailerDetails;
            }
            return trailerNode.copy(trailerDetails);
        }

        @SerialName("trailer")
        public static /* synthetic */ void getTrailerDetails$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TrailerDetails getTrailerDetails() {
            return this.trailerDetails;
        }

        public final TrailerNode copy(@JsonProperty("trailer") TrailerDetails trailerDetails) {
            Intrinsics.checkNotNullParameter(trailerDetails, "trailerDetails");
            return new TrailerNode(trailerDetails);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof TrailerNode) && Intrinsics.areEqual(this.trailerDetails, ((TrailerNode) other).trailerDetails);
        }

        public int hashCode() {
            return this.trailerDetails.hashCode();
        }

        public String toString() {
            return "TrailerNode(trailerDetails=" + this.trailerDetails + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerNode;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TrailerNode> serializer() {
                return MyDramaListAPI$TrailerNode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ TrailerNode(int i, TrailerDetails trailerDetails, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, MyDramaListAPI$TrailerNode$$serializer.INSTANCE.getDescriptor());
            }
            this.trailerDetails = trailerDetails;
        }

        public TrailerNode(@JsonProperty("trailer") TrailerDetails trailerDetails) {
            Intrinsics.checkNotNullParameter(trailerDetails, "trailerDetails");
            this.trailerDetails = trailerDetails;
        }

        public final TrailerDetails getTrailerDetails() {
            return this.trailerDetails;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails;", "", "id", "", "source", "", "<init>", "(JLjava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()J", "getSource$annotations", "getSource", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class TrailerDetails {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final long id;

        @JsonProperty("source")
        private final String source;

        public static /* synthetic */ TrailerDetails copy$default(TrailerDetails trailerDetails, long j, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                j = trailerDetails.id;
            }
            if ((i & 2) != 0) {
                str = trailerDetails.source;
            }
            return trailerDetails.copy(j, str);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("source")
        public static /* synthetic */ void getSource$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        public final TrailerDetails copy(@JsonProperty("id") long id, @JsonProperty("source") String source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new TrailerDetails(id, source);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TrailerDetails)) {
                return false;
            }
            TrailerDetails trailerDetails = (TrailerDetails) other;
            return this.id == trailerDetails.id && Intrinsics.areEqual(this.source, trailerDetails.source);
        }

        public int hashCode() {
            return (NextAiring$$ExternalSyntheticBackport0.m(this.id) * 31) + this.source.hashCode();
        }

        public String toString() {
            return "TrailerDetails(id=" + this.id + ", source=" + this.source + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$TrailerDetails;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TrailerDetails> serializer() {
                return MyDramaListAPI$TrailerDetails$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ TrailerDetails(int i, long j, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MyDramaListAPI$TrailerDetails$$serializer.INSTANCE.getDescriptor());
            }
            this.id = j;
            this.source = str;
        }

        public TrailerDetails(@JsonProperty("id") long j, @JsonProperty("source") String source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.id = j;
            this.source = source;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(TrailerDetails self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.source);
        }

        public final long getId() {
            return this.id;
        }

        public final String getSource() {
            return this.source;
        }
    }

    /* JADX INFO: compiled from: MyDramaList.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 I2\u00020\u0001:\u0002HIB\u007f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010B\u007f\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u00102\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0086\u0001\u00109\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010:J\u0014\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010>\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010?\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0001¢\u0006\u0002\bGR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u001fR \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b!\u0010\u0016\u001a\u0004\b\"\u0010\u001fR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0016\u001a\u0004\b$\u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010\u001fR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0016\u001a\u0004\b(\u0010\u001cR \u0010\f\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b)\u0010\u0016\u001a\u0004\b*\u0010\u001fR\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u0016\u001a\u0004\b,\u0010\u001cR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u0016\u001a\u0004\b.\u0010\u001c¨\u0006J"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$LinkData;", "", "id", "", "type", "", "season", "", "episode", "title", "year", "orgTitle", "lastSeason", "date", "airedDate", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getType$annotations", "getType", "()Ljava/lang/String;", "getSeason$annotations", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode$annotations", "getEpisode", "getTitle$annotations", "getTitle", "getYear$annotations", "getYear", "getOrgTitle$annotations", "getOrgTitle", "getLastSeason$annotations", "getLastSeason", "getDate$annotations", "getDate", "getAiredDate$annotations", "getAiredDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$LinkData;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$library", "$serializer", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LinkData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("airedDate")
        private final String airedDate;

        @JsonProperty("date")
        private final String date;

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("id")
        private final Long id;

        @JsonProperty("lastSeason")
        private final Integer lastSeason;

        @JsonProperty("orgTitle")
        private final String orgTitle;

        @JsonProperty("season")
        private final Integer season;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("type")
        private final String type;

        @JsonProperty("year")
        private final Integer year;

        public LinkData() {
            this((Long) null, (String) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (String) null, (Integer) null, (String) null, (String) null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ LinkData copy$default(LinkData linkData, Long l, String str, Integer num, Integer num2, String str2, Integer num3, String str3, Integer num4, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                l = linkData.id;
            }
            if ((i & 2) != 0) {
                str = linkData.type;
            }
            if ((i & 4) != 0) {
                num = linkData.season;
            }
            if ((i & 8) != 0) {
                num2 = linkData.episode;
            }
            if ((i & 16) != 0) {
                str2 = linkData.title;
            }
            if ((i & 32) != 0) {
                num3 = linkData.year;
            }
            if ((i & 64) != 0) {
                str3 = linkData.orgTitle;
            }
            if ((i & 128) != 0) {
                num4 = linkData.lastSeason;
            }
            if ((i & 256) != 0) {
                str4 = linkData.date;
            }
            if ((i & 512) != 0) {
                str5 = linkData.airedDate;
            }
            String str6 = str4;
            String str7 = str5;
            String str8 = str3;
            Integer num5 = num4;
            String str9 = str2;
            Integer num6 = num3;
            return linkData.copy(l, str, num, num2, str9, num6, str8, num5, str6, str7);
        }

        @SerialName("airedDate")
        public static /* synthetic */ void getAiredDate$annotations() {
        }

        @SerialName("date")
        public static /* synthetic */ void getDate$annotations() {
        }

        @SerialName("episode")
        public static /* synthetic */ void getEpisode$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("lastSeason")
        public static /* synthetic */ void getLastSeason$annotations() {
        }

        @SerialName("orgTitle")
        public static /* synthetic */ void getOrgTitle$annotations() {
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getAiredDate() {
            return this.airedDate;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getOrgTitle() {
            return this.orgTitle;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getLastSeason() {
            return this.lastSeason;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getDate() {
            return this.date;
        }

        public final LinkData copy(@JsonProperty("id") Long id, @JsonProperty("type") String type, @JsonProperty("season") Integer season, @JsonProperty("episode") Integer episode, @JsonProperty("title") String title, @JsonProperty("year") Integer year, @JsonProperty("orgTitle") String orgTitle, @JsonProperty("lastSeason") Integer lastSeason, @JsonProperty("date") String date, @JsonProperty("airedDate") String airedDate) {
            return new LinkData(id, type, season, episode, title, year, orgTitle, lastSeason, date, airedDate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LinkData)) {
                return false;
            }
            LinkData linkData = (LinkData) other;
            return Intrinsics.areEqual(this.id, linkData.id) && Intrinsics.areEqual(this.type, linkData.type) && Intrinsics.areEqual(this.season, linkData.season) && Intrinsics.areEqual(this.episode, linkData.episode) && Intrinsics.areEqual(this.title, linkData.title) && Intrinsics.areEqual(this.year, linkData.year) && Intrinsics.areEqual(this.orgTitle, linkData.orgTitle) && Intrinsics.areEqual(this.lastSeason, linkData.lastSeason) && Intrinsics.areEqual(this.date, linkData.date) && Intrinsics.areEqual(this.airedDate, linkData.airedDate);
        }

        public int hashCode() {
            Long l = this.id;
            int iHashCode = (l == null ? 0 : l.hashCode()) * 31;
            String str = this.type;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.season;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episode;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str2 = this.title;
            int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num3 = this.year;
            int iHashCode6 = (iHashCode5 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str3 = this.orgTitle;
            int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num4 = this.lastSeason;
            int iHashCode8 = (iHashCode7 + (num4 == null ? 0 : num4.hashCode())) * 31;
            String str4 = this.date;
            int iHashCode9 = (iHashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.airedDate;
            return iHashCode9 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "LinkData(id=" + this.id + ", type=" + this.type + ", season=" + this.season + ", episode=" + this.episode + ", title=" + this.title + ", year=" + this.year + ", orgTitle=" + this.orgTitle + ", lastSeason=" + this.lastSeason + ", date=" + this.date + ", airedDate=" + this.airedDate + ')';
        }

        /* JADX INFO: compiled from: MyDramaList.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$LinkData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/metaproviders/MyDramaListAPI$LinkData;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LinkData> serializer() {
                return MyDramaListAPI$LinkData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LinkData(int i, Long l, String str, Integer num, Integer num2, String str2, Integer num3, String str3, Integer num4, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = l;
            }
            if ((i & 2) == 0) {
                this.type = null;
            } else {
                this.type = str;
            }
            if ((i & 4) == 0) {
                this.season = null;
            } else {
                this.season = num;
            }
            if ((i & 8) == 0) {
                this.episode = null;
            } else {
                this.episode = num2;
            }
            if ((i & 16) == 0) {
                this.title = null;
            } else {
                this.title = str2;
            }
            if ((i & 32) == 0) {
                this.year = null;
            } else {
                this.year = num3;
            }
            if ((i & 64) == 0) {
                this.orgTitle = null;
            } else {
                this.orgTitle = str3;
            }
            if ((i & 128) == 0) {
                this.lastSeason = null;
            } else {
                this.lastSeason = num4;
            }
            if ((i & 256) == 0) {
                this.date = null;
            } else {
                this.date = str4;
            }
            if ((i & 512) == 0) {
                this.airedDate = null;
            } else {
                this.airedDate = str5;
            }
        }

        public LinkData(@JsonProperty("id") Long l, @JsonProperty("type") String str, @JsonProperty("season") Integer num, @JsonProperty("episode") Integer num2, @JsonProperty("title") String str2, @JsonProperty("year") Integer num3, @JsonProperty("orgTitle") String str3, @JsonProperty("lastSeason") Integer num4, @JsonProperty("date") String str4, @JsonProperty("airedDate") String str5) {
            this.id = l;
            this.type = str;
            this.season = num;
            this.episode = num2;
            this.title = str2;
            this.year = num3;
            this.orgTitle = str3;
            this.lastSeason = num4;
            this.date = str4;
            this.airedDate = str5;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$library(LinkData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.id);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.type != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.type);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.season != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.season);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.episode != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.episode);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 4) || self.title != null) {
                output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.title);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 5) || self.year != null) {
                output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.year);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 6) || self.orgTitle != null) {
                output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.orgTitle);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 7) || self.lastSeason != null) {
                output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.lastSeason);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 8) || self.date != null) {
                output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.date);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 9) && self.airedDate == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.airedDate);
        }

        public /* synthetic */ LinkData(Long l, String str, Integer num, Integer num2, String str2, Integer num3, String str3, Integer num4, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : num3, (i & 64) != 0 ? null : str3, (i & 128) != 0 ? null : num4, (i & 256) != 0 ? null : str4, (i & 512) != 0 ? null : str5);
        }

        public final Long getId() {
            return this.id;
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

        public final String getTitle() {
            return this.title;
        }

        public final Integer getYear() {
            return this.year;
        }

        public final String getOrgTitle() {
            return this.orgTitle;
        }

        public final Integer getLastSeason() {
            return this.lastSeason;
        }

        public final String getDate() {
            return this.date;
        }

        public final String getAiredDate() {
            return this.airedDate;
        }
    }
}
