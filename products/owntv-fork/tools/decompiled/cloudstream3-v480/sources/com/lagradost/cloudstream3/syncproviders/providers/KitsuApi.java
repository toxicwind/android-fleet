package com.lagradost.cloudstream3.syncproviders.providers;

import android.content.Context;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthLoginRequirement;
import com.lagradost.cloudstream3.syncproviders.AuthLoginResponse;
import com.lagradost.cloudstream3.syncproviders.AuthToken;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import com.lagradost.cloudstream3.ui.SyncWatchType;
import com.lagradost.cloudstream3.ui.library.ListSorting;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import j$.time.LocalDate;
import j$.time.ZoneId;
import j$.util.DesugarDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.mozilla.classfile.ByteCode;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.soundcloud.linkHandler.SoundcloudSearchQueryHandlerFactory;

/* JADX INFO: compiled from: KitsuApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 k2\u00020\u0001:\u000e^_`abcdefghijkB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/H\u0096@¢\u0006\u0002\u00100J\u0016\u00101\u001a\u00020-2\u0006\u00102\u001a\u00020-H\u0096@¢\u0006\u0002\u00103J\u001a\u00104\u001a\u0004\u0018\u0001052\b\u00102\u001a\u0004\u0018\u00010-H\u0096@¢\u0006\u0002\u00103J(\u00106\u001a\n\u0012\u0004\u0012\u000208\u0018\u0001072\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010<J\"\u0010=\u001a\u0004\u0018\u00010>2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010?\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010<J\"\u0010@\u001a\u0004\u0018\u00010A2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010?\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010<J\u0018\u0010B\u001a\u0004\u0018\u00010\u00052\u0006\u0010C\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010DJ\u0012\u0010E\u001a\u0004\u0018\u00010\u00052\u0006\u0010F\u001a\u00020\u0005H\u0016J(\u0010G\u001a\u00020\u00112\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010?\u001a\u00020\u00052\u0006\u0010H\u001a\u00020AH\u0096@¢\u0006\u0002\u0010IJB\u0010J\u001a\u00020\u00112\u0006\u00109\u001a\u00020:2\u0006\u0010?\u001a\u00020\u00172\n\b\u0002\u0010@\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0002\u0010NJB\u0010J\u001a\u00020\u00112\u0006\u00109\u001a\u00020:2\u0006\u0010?\u001a\u00020\u00172\n\b\u0002\u0010@\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0002\u0010OJ \u0010P\u001a\u0004\u0018\u00010\u00172\u0006\u00109\u001a\u00020:2\u0006\u0010?\u001a\u00020\u0017H\u0082@¢\u0006\u0002\u0010QJ\u001a\u0010R\u001a\u0004\u0018\u00010S2\b\u00109\u001a\u0004\u0018\u00010:H\u0096@¢\u0006\u0002\u0010TJ\u001e\u0010U\u001a\n\u0012\u0004\u0012\u00020W\u0018\u00010V2\u0006\u00109\u001a\u00020:H\u0082@¢\u0006\u0002\u0010TJ$\u0010X\u001a\b\u0012\u0004\u0012\u00020W0V2\u0006\u00102\u001a\u00020-2\u0006\u0010Y\u001a\u00020\u0017H\u0082@¢\u0006\u0002\u0010ZJ\u001e\u0010[\u001a\u00020\\2\u0006\u00102\u001a\u00020-2\u0006\u0010F\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010]R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020&X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006l"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "idPrefix", "getIdPrefix", "apiUrl", "fallbackApiUrl", "oauthUrl", "fallbackOauthUrl", "hasInApp", "", "getHasInApp", "()Z", "mainUrl", "getMainUrl", "icon", "", "getIcon", "()Ljava/lang/Integer;", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "createAccountUrl", "getCreateAccountUrl", "supportedWatchTypes", "", "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "getSupportedWatchTypes", "()Ljava/util/Set;", "inAppLoginRequirement", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "getInAppLoginRequirement", "()Lcom/lagradost/cloudstream3/syncproviders/AuthLoginRequirement;", "apiFallbackInterceptor", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$FallbackInterceptor;", "oauthFallbackInterceptor", "login", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "form", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthLoginResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "search", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "id", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "getAnimeIdByTitle", "title", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlToId", ImagesContract.URL, "updateStatus", "newStatus", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setScoreRequest", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$Companion$KitsuStatusType;", "score", "numWatchedEpisodes", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;ILcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$Companion$KitsuStatusType;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnimeLibraryEntryId", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKitsuAnimeListSmart", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "getKitsuAnimeList", "userId", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getKitsuAnimeListSlice", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuResponse;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "FallbackInterceptor", "ResponseToken", "KitsuNode", "KitsuAnimeAttributes", "KitsuAnimeData", "KitsuNodeAttributes", "KitsuRelationships", "KitsuRelationshipsAnime", "KitsuPosterImage", "KitsuTitles", "KitsuUserAvatar", "KitsuLinks", "KitsuResponse", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class KitsuApi extends SyncAPI {
    public static final String KITSU_CACHED_LIST = "kitsu_cached_list";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] kitsuStatusAsString = {"current", "completed", "on_hold", "dropped", "planned"};
    private String name = "Kitsu";
    private final String idPrefix = "kitsu";
    private final String apiUrl = "https://kitsu.io/api/edge";
    private final String fallbackApiUrl = "https://kitsu.app/api/edge";
    private final String oauthUrl = "https://kitsu.io/api/oauth";
    private final String fallbackOauthUrl = "https://kitsu.app/api/oauth";
    private final boolean hasInApp = true;
    private final String mainUrl = "https://kitsu.app";
    private final int icon = R.drawable.kitsu_icon;
    private final SyncIdName syncIdName = SyncIdName.Kitsu;
    private final String createAccountUrl = getMainUrl();
    private final Set<SyncWatchType> supportedWatchTypes = SetsKt.setOf((Object[]) new SyncWatchType[]{SyncWatchType.WATCHING, SyncWatchType.COMPLETED, SyncWatchType.PLANTOWATCH, SyncWatchType.DROPPED, SyncWatchType.ONHOLD, SyncWatchType.NONE});
    private final AuthLoginRequirement inAppLoginRequirement = new AuthLoginRequirement(true, false, true, false, 10, null);
    private final FallbackInterceptor apiFallbackInterceptor = new FallbackInterceptor("https://kitsu.io/api/edge", "https://kitsu.app/api/edge");
    private final FallbackInterceptor oauthFallbackInterceptor = new FallbackInterceptor("https://kitsu.io/api/oauth", "https://kitsu.app/api/oauth");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getAnimeIdByTitle$1, reason: invalid class name */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED}, m = "getAnimeIdByTitle", n = {"title", "animeSelectedFields", ImagesContract.URL}, nl = {805}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.getAnimeIdByTitle(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getAnimeLibraryEntryId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0}, l = {386}, m = "getAnimeLibraryEntryId", n = {"auth", "id", "userId"}, nl = {392}, s = {"L$0", "I$0", "I$1"}, v = 2)
    static final class C07831 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07831(Continuation<? super C07831> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.getAnimeLibraryEntryId(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {444}, m = "getKitsuAnimeList", n = {"token", "animeSelectedFields", "libraryEntriesSelectedFields", ImagesContract.URL, "fullList", "userId", "limit"}, nl = {445}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 2)
    static final class C07841 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07841(Continuation<? super C07841> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.getKitsuAnimeList(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeListSlice$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0}, l = {457}, m = "getKitsuAnimeListSlice", n = {"token", ImagesContract.URL}, nl = {462}, s = {"L$0", "L$1"}, v = 2)
    static final class C07851 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07851(Continuation<? super C07851> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.getKitsuAnimeListSlice(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeListSmart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0}, l = {427}, m = "getKitsuAnimeListSmart", n = {"auth"}, nl = {428}, s = {"L$0"}, v = 2)
    static final class C07861 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07861(Continuation<? super C07861> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.getKitsuAnimeListSmart(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$library$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0}, l = {398}, m = "library", n = {"auth"}, nl = {805}, s = {"L$0"}, v = 2)
    static final class C07871 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07871(Continuation<? super C07871> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.library(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0}, l = {ByteCode.ARRAYLENGTH}, m = "load", n = {"auth", "id", "auth", ImagesContract.URL}, nl = {ByteCode.MONITOREXIT}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C07881 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07881(Continuation<? super C07881> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.load(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$login$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0}, l = {95}, m = "login", n = {"form", HintConstants.AUTOFILL_HINT_USERNAME, HintConstants.AUTOFILL_HINT_PASSWORD, "grantType"}, nl = {103}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C07901 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07901(Continuation<? super C07901> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.login((AuthLoginResponse) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$refreshToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0}, l = {113}, m = "refreshToken", n = {"token"}, nl = {120}, s = {"L$0"}, v = 2)
    static final class C07911 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07911(Continuation<? super C07911> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.refreshToken(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0, 0}, l = {154}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY, "auth", "animeSelectedFields", ImagesContract.URL}, nl = {159}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class C07921 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07921(Continuation<? super C07921> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.search(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$setScoreRequest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3}, l = {287, 293, 304, 338}, m = "setScoreRequest", n = {"auth", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "id", "auth", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "libraryEntryId", "id", "auth", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "libraryEntryId", "id", "auth", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "libraryEntryId", "data", "id"}, nl = {290, 301, 314, 348}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, v = 2)
    static final class C07931 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C07931(Continuation<? super C07931> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.setScoreRequest((AuthData) null, 0, (Companion.KitsuStatusType) null, (Integer) null, (Integer) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$setScoreRequest$2, reason: invalid class name */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0, 0, 0}, l = {371}, m = "setScoreRequest", n = {"auth", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "data", "id"}, nl = {381}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.setScoreRequest((AuthData) null, 0, (String) null, (Integer) null, (Integer) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$status$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0, 0, 0, 0, 0, 0}, l = {231}, m = NotificationCompat.CATEGORY_STATUS, n = {"auth", "id", "accessToken", "selectedFields", ImagesContract.URL, "userId"}, nl = {236}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 2)
    static final class C07941 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07941(Continuation<? super C07941> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.status(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$user$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.KitsuApi", f = "KitsuApi.kt", i = {0}, l = {130}, m = "user", n = {"token"}, nl = {136}, s = {"L$0"}, v = 2)
    static final class C07951 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07951(Continuation<? super C07951> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KitsuApi.this.user(null, this);
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getIdPrefix() {
        return this.idPrefix;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getHasInApp() {
        return this.hasInApp;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Integer getIcon() {
        return Integer.valueOf(this.icon);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public SyncIdName getSyncIdName() {
        return this.syncIdName;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Set<SyncWatchType> getSupportedWatchTypes() {
        return this.supportedWatchTypes;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public AuthLoginRequirement getInAppLoginRequirement() {
        return this.inAppLoginRequirement;
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$FallbackInterceptor;", "Lokhttp3/Interceptor;", "apiUrl", "", "fallbackApiUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private static final class FallbackInterceptor implements Interceptor {
        private final String apiUrl;
        private final String fallbackApiUrl;

        public FallbackInterceptor(String apiUrl, String fallbackApiUrl) {
            Intrinsics.checkNotNullParameter(apiUrl, "apiUrl");
            Intrinsics.checkNotNullParameter(fallbackApiUrl, "fallbackApiUrl");
            this.apiUrl = apiUrl;
            this.fallbackApiUrl = fallbackApiUrl;
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Intrinsics.checkNotNullParameter(chain, "chain");
            Request request = chain.request();
            try {
                Response responseProceed = chain.proceed(request);
                if (responseProceed.getIsSuccessful()) {
                    return responseProceed;
                }
                responseProceed.close();
            } catch (Exception unused) {
            }
            return chain.proceed(request.newBuilder().url(StringsKt.replaceFirst$default(request.url().getUrl(), this.apiUrl, this.fallbackApiUrl, false, 4, (Object) null)).build());
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(AuthLoginResponse authLoginResponse, Continuation<? super AuthToken> continuation) {
        C07901 c07901;
        String password;
        if (continuation instanceof C07901) {
            c07901 = (C07901) continuation;
            if ((c07901.label & Integer.MIN_VALUE) != 0) {
                c07901.label -= Integer.MIN_VALUE;
            } else {
                c07901 = new C07901(continuation);
            }
        } else {
            c07901 = new C07901(continuation);
        }
        Object objPost$default = c07901.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07901.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objPost$default);
            String email = authLoginResponse.getEmail();
            if (email == null || (password = authLoginResponse.getPassword()) == null) {
                return null;
            }
            Requests app2 = MainActivityKt.getApp();
            String str = this.oauthUrl + "/token";
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("grant_type", HintConstants.AUTOFILL_HINT_PASSWORD), TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, email), TuplesKt.to(HintConstants.AUTOFILL_HINT_PASSWORD, password));
            FallbackInterceptor fallbackInterceptor = this.oauthFallbackInterceptor;
            c07901.L$0 = SpillingKt.nullOutSpilledVariable(authLoginResponse);
            c07901.L$1 = SpillingKt.nullOutSpilledVariable(email);
            c07901.L$2 = SpillingKt.nullOutSpilledVariable(password);
            c07901.L$3 = SpillingKt.nullOutSpilledVariable(HintConstants.AUTOFILL_HINT_PASSWORD);
            c07901.label = 1;
            objPost$default = Requests.post$default(app2, str, null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07901, 57310, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objPost$default);
        }
        NiceResponse niceResponse = (NiceResponse) objPost$default;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        ResponseToken responseToken = (ResponseToken) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ResponseToken.class));
        return new AuthToken(responseToken.getAccessToken(), responseToken.getRefreshToken(), Boxing.boxLong(APIHolder.INSTANCE.getUnixTime() + ((long) responseToken.getExpiresIn())), (Long) null, (String) null, 24, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object refreshToken(AuthToken authToken, Continuation<? super AuthToken> continuation) {
        C07911 c07911;
        if (continuation instanceof C07911) {
            c07911 = (C07911) continuation;
            if ((c07911.label & Integer.MIN_VALUE) != 0) {
                c07911.label -= Integer.MIN_VALUE;
            } else {
                c07911 = new C07911(continuation);
            }
        } else {
            c07911 = new C07911(continuation);
        }
        Object obj = c07911.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07911.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = this.oauthUrl + "/token";
            String refreshToken = authToken.getRefreshToken();
            Intrinsics.checkNotNull(refreshToken);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("grant_type", "refresh_token"), TuplesKt.to("refresh_token", refreshToken));
            FallbackInterceptor fallbackInterceptor = this.oauthFallbackInterceptor;
            c07911.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07911.label = 1;
            Object objPost$default = Requests.post$default(app2, str, null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07911, 57310, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        ResponseToken responseToken = (ResponseToken) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(ResponseToken.class));
        return new AuthToken(responseToken.getAccessToken(), responseToken.getRefreshToken(), Boxing.boxLong(APIHolder.INSTANCE.getUnixTime() + ((long) responseToken.getExpiresIn())), (Long) null, (String) null, 24, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) {
        C07951 c07951;
        String accessToken;
        if (continuation instanceof C07951) {
            c07951 = (C07951) continuation;
            if ((c07951.label & Integer.MIN_VALUE) != 0) {
                c07951.label -= Integer.MIN_VALUE;
            } else {
                c07951 = new C07951(continuation);
            }
        } else {
            c07951 = new C07951(continuation);
        }
        Object obj = c07951.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07951.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = this.apiUrl + "/users?filter[self]=true";
            StringBuilder sb = new StringBuilder("Bearer ");
            if (authToken == null || (accessToken = authToken.getAccessToken()) == null) {
                return null;
            }
            sb.append(accessToken);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", sb.toString()));
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07951.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07951.label = 1;
            Object obj2 = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07951, 3516, null);
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
        KitsuResponse kitsuResponse = (KitsuResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class));
        if (kitsuResponse.getData().isEmpty()) {
            return null;
        }
        int i2 = Integer.parseInt(kitsuResponse.getData().get(0).getId());
        String name = kitsuResponse.getData().get(0).getAttributes().getName();
        KitsuUserAvatar avatar = kitsuResponse.getData().get(0).getAttributes().getAvatar();
        return new AuthUser(name, i2, avatar != null ? avatar.getOriginal() : null, (Map) null, 8, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object search(AuthData authData, String str, Continuation<? super List<SyncAPI.SyncSearchResult>> continuation) {
        C07921 c07921;
        AuthToken token;
        String accessToken;
        String str2;
        if (continuation instanceof C07921) {
            c07921 = (C07921) continuation;
            if ((c07921.label & Integer.MIN_VALUE) != 0) {
                c07921.label -= Integer.MIN_VALUE;
            } else {
                c07921 = new C07921(continuation);
            }
        } else {
            c07921 = new C07921(continuation);
        }
        Object obj = c07921.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07921.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (token = authData.getToken()) == null || (accessToken = token.getAccessToken()) == null) {
                return null;
            }
            String[] strArr = {"titles", "canonicalTitle", "posterImage", "episodeCount"};
            String str3 = this.apiUrl + "/anime?filter[text]=" + str + "&page[limit]=20&fields[anime]=" + ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + accessToken));
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07921.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07921.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07921.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c07921.L$3 = SpillingKt.nullOutSpilledVariable(strArr);
            c07921.L$4 = SpillingKt.nullOutSpilledVariable(str3);
            c07921.label = 1;
            Object obj2 = Requests.get$default(app2, str3, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07921, 3516, null);
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
        List<KitsuNode> data = ((KitsuResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class))).getData();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(data, 10));
        for (KitsuNode kitsuNode : data) {
            KitsuNodeAttributes attributes = kitsuNode.getAttributes();
            String canonicalTitle = attributes.getCanonicalTitle();
            if (canonicalTitle == null) {
                KitsuTitles titles = attributes.getTitles();
                canonicalTitle = titles != null ? titles.getEnJp() : null;
                if (canonicalTitle == null) {
                    KitsuTitles titles2 = attributes.getTitles();
                    canonicalTitle = titles2 != null ? titles2.getJaJp() : null;
                    if (canonicalTitle == null) {
                        canonicalTitle = "No title";
                    }
                }
            }
            String str4 = canonicalTitle;
            String name = getName();
            String id = kitsuNode.getId();
            String str5 = getMainUrl() + "/anime/" + kitsuNode.getId() + '/';
            KitsuPosterImage posterImage = attributes.getPosterImage();
            if (posterImage == null || (medium = posterImage.getLarge()) == null) {
                KitsuPosterImage posterImage2 = attributes.getPosterImage();
                if (posterImage2 != null) {
                    String medium = posterImage2.getMedium();
                    str2 = medium;
                } else {
                    str2 = null;
                }
            } else {
                str2 = medium;
            }
            arrayList.add(new SyncAPI.SyncSearchResult(str4, name, id, str5, str2, null, null, null, null, null, 992, null));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object load(AuthData authData, String str, Continuation<? super SyncAPI.SyncResult> continuation) {
        C07881 c07881;
        AuthToken token;
        String accessToken;
        String str2;
        if (continuation instanceof C07881) {
            c07881 = (C07881) continuation;
            if ((c07881.label & Integer.MIN_VALUE) != 0) {
                c07881.label -= Integer.MIN_VALUE;
            } else {
                c07881 = new C07881(continuation);
            }
        } else {
            c07881 = new C07881(continuation);
        }
        Object obj = c07881.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07881.label;
        ShowStatus showStatus = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (token = authData.getToken()) == null || (accessToken = token.getAccessToken()) == null || StringsKt.toIntOrNull(str) == null) {
                return null;
            }
            String str3 = this.apiUrl + "/anime/" + str;
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + accessToken));
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07881.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07881.L$1 = str;
            c07881.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c07881.L$3 = SpillingKt.nullOutSpilledVariable(str3);
            c07881.label = 1;
            obj = Requests.get$default(app2, str3, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07881, 3580, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            str2 = str;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str4 = (String) c07881.L$1;
            ResultKt.throwOnFailure(obj);
            str2 = str4;
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        KitsuNodeAttributes attributes = ((C0789KitsuResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(C0789KitsuResponse.class))).getData().getAttributes();
        Integer episodeCount = attributes.getEpisodeCount();
        String canonicalTitle = attributes.getCanonicalTitle();
        if (canonicalTitle == null) {
            KitsuTitles titles = attributes.getTitles();
            canonicalTitle = titles != null ? titles.getEnJp() : null;
            if (canonicalTitle == null) {
                KitsuTitles titles2 = attributes.getTitles();
                canonicalTitle = titles2 != null ? titles2.getJaJp() : null;
                if (canonicalTitle == null) {
                    canonicalTitle = "";
                }
            }
        }
        String str5 = canonicalTitle;
        Score scoreFrom = Score.INSTANCE.from(attributes.getRatingTwenty(), 20);
        Integer episodeLength = attributes.getEpisodeLength();
        String synopsis = attributes.getSynopsis();
        String status = attributes.getStatus();
        if (Intrinsics.areEqual(status, "finished")) {
            showStatus = ShowStatus.Completed;
        } else if (Intrinsics.areEqual(status, "current")) {
            showStatus = ShowStatus.Ongoing;
        }
        return new SyncAPI.SyncResult(str2, episodeCount, str5, scoreFrom, episodeLength, synopsis, showStatus, null, null, null, null, null, null, null, null, Boxing.boxLong(LocalDate.parse(attributes.getStartDate()).toEpochDay()), Boxing.boxLong(LocalDate.parse(attributes.getEndDate()).toEpochDay()), null, null, null, null, 29696, null);
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$load$KitsuResponse, reason: case insensitive filesystem */
    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\u0000\b\u008b\b\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u0018\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\u0016"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/KitsuApi$load$KitsuResponse", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "component1", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;)Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$load$KitsuResponse;", "equals", "", "other", "hashCode", "", "toString", "", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class C0789KitsuResponse {
        static final Companion Companion = new Companion(null);

        @JsonProperty("data")
        private final KitsuNode data;

        public static /* synthetic */ C0789KitsuResponse copy$default(C0789KitsuResponse c0789KitsuResponse, KitsuNode kitsuNode, int i, Object obj) {
            if ((i & 1) != 0) {
                kitsuNode = c0789KitsuResponse.data;
            }
            return c0789KitsuResponse.copy(kitsuNode);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final KitsuNode getData() {
            return this.data;
        }

        public final C0789KitsuResponse copy(@JsonProperty("data") KitsuNode data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new C0789KitsuResponse(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof C0789KitsuResponse) && Intrinsics.areEqual(this.data, ((C0789KitsuResponse) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "KitsuResponse(data=" + this.data + ')';
        }

        /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$load$KitsuResponse$Companion */
        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002\u0000\u0006\b\u008a\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"com/lagradost/cloudstream3/syncproviders/providers/KitsuApi$load$KitsuResponse.Companion", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "com/lagradost/cloudstream3/syncproviders/providers/KitsuApi$load$KitsuResponse", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<C0789KitsuResponse> serializer() {
                return KitsuApi$load$KitsuResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ C0789KitsuResponse(int i, KitsuNode kitsuNode, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, KitsuApi$load$KitsuResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.data = kitsuNode;
        }

        public C0789KitsuResponse(@JsonProperty("data") KitsuNode data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final KitsuNode getData() {
            return this.data;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object status(AuthData authData, String str, Continuation<? super SyncAPI.AbstractSyncStatus> continuation) {
        C07941 c07941;
        AuthToken token;
        String accessToken;
        if (continuation instanceof C07941) {
            c07941 = (C07941) continuation;
            if ((c07941.label & Integer.MIN_VALUE) != 0) {
                c07941.label -= Integer.MIN_VALUE;
            } else {
                c07941 = new C07941(continuation);
            }
        } else {
            c07941 = new C07941(continuation);
        }
        Object obj = c07941.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07941.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (token = authData.getToken()) == null || (accessToken = token.getAccessToken()) == null) {
                return null;
            }
            int id = authData.getUser().getId();
            String[] strArr = {NotificationCompat.CATEGORY_STATUS, "ratingTwenty", "progress"};
            String str2 = this.apiUrl + "/library-entries?filter[userId]=" + id + "&filter[animeId]=" + str + "&fields[libraryEntries]=" + ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + accessToken));
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07941.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07941.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07941.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c07941.L$3 = SpillingKt.nullOutSpilledVariable(strArr);
            c07941.L$4 = SpillingKt.nullOutSpilledVariable(str2);
            c07941.I$0 = id;
            c07941.label = 1;
            obj = Requests.get$default(app2, str2, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07941, 3580, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c07941.I$0;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        KitsuNode kitsuNode = (KitsuNode) CollectionsKt.firstOrNull((List) ((KitsuResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class))).getData());
        KitsuNodeAttributes attributes = kitsuNode != null ? kitsuNode.getAttributes() : null;
        if (attributes == null) {
            return new SyncAPI.SyncStatus(SyncWatchType.NONE, null, null, null, null, 16, null);
        }
        return new SyncAPI.SyncStatus(SyncWatchType.INSTANCE.fromInternalId(Boxing.boxInt(ArraysKt.indexOf(kitsuStatusAsString, attributes.getStatus()))), Score.INSTANCE.from(attributes.getRatingTwenty(), 20), attributes.getProgress(), null, null, 16, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getAnimeIdByTitle(String str, Continuation<? super String> continuation) {
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
            String[] strArr = {"titles", "canonicalTitle"};
            String str2 = this.apiUrl + "/anime?filter[text]=" + str + "&page[limit]=20&fields[anime]=" + ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            Requests app2 = MainActivityKt.getApp();
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(strArr);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass1.label = 1;
            obj = Requests.get$default(app2, str2, null, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, anonymousClass1, 3582, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        KitsuNode kitsuNode = (KitsuNode) CollectionsKt.firstOrNull((List) ((KitsuResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class))).getData());
        if (kitsuNode != null) {
            return kitsuNode.getId();
        }
        return null;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public String urlToId(String url) {
        List<String> groupValues;
        Intrinsics.checkNotNullParameter(url, "url");
        MatchResult matchResultFind$default = Regex.find$default(new Regex("/anime/((.*)/|(.*))"), url, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null) {
            return null;
        }
        return (String) CollectionsKt.first((List) groupValues);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object updateStatus(AuthData authData, String str, SyncAPI.AbstractSyncStatus abstractSyncStatus, Continuation<? super Boolean> continuation) {
        if (authData == null) {
            return Boxing.boxBoolean(false);
        }
        Integer intOrNull = StringsKt.toIntOrNull(str);
        if (intOrNull == null) {
            return Boxing.boxBoolean(false);
        }
        int iIntValue = intOrNull.intValue();
        Companion.KitsuStatusType kitsuStatusTypeFromIntToAnimeStatus = INSTANCE.fromIntToAnimeStatus(abstractSyncStatus.getStatus());
        Score score = abstractSyncStatus.getScore();
        return setScoreRequest(authData, iIntValue, kitsuStatusTypeFromIntToAnimeStatus, score != null ? Boxing.boxInt(score.toInt(20)) : null, abstractSyncStatus.getWatchedEpisodes(), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    public final Object setScoreRequest(AuthData authData, int i, Companion.KitsuStatusType kitsuStatusType, Integer num, Integer num2, Continuation<? super Boolean> continuation) {
        C07931 c07931;
        Integer num3;
        Integer num4;
        Companion.KitsuStatusType kitsuStatusType2;
        AuthData authData2 = authData;
        int i2 = i;
        if (continuation instanceof C07931) {
            c07931 = (C07931) continuation;
            if ((c07931.label & Integer.MIN_VALUE) != 0) {
                c07931.label -= Integer.MIN_VALUE;
            } else {
                c07931 = new C07931(continuation);
            }
        } else {
            c07931 = new C07931(continuation);
        }
        C07931 c07932 = c07931;
        Object objPost$default = c07932.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c07932.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objPost$default);
            c07932.L$0 = authData2;
            c07932.L$1 = kitsuStatusType;
            num3 = num;
            c07932.L$2 = num3;
            c07932.L$3 = num2;
            c07932.I$0 = i2;
            c07932.label = 1;
            Object animeLibraryEntryId = getAnimeLibraryEntryId(authData2, i2, c07932);
            if (animeLibraryEntryId != coroutine_suspended) {
                num4 = num2;
                kitsuStatusType2 = kitsuStatusType;
                objPost$default = animeLibraryEntryId;
            }
            return coroutine_suspended;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                int i4 = c07932.I$0;
                ResultKt.throwOnFailure(objPost$default);
                return Boxing.boxBoolean(((NiceResponse) objPost$default).getIsSuccessful());
            }
            if (i3 == 3) {
                int i5 = c07932.I$0;
                ResultKt.throwOnFailure(objPost$default);
                return objPost$default;
            }
            if (i3 != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i6 = c07932.I$0;
            ResultKt.throwOnFailure(objPost$default);
            return Boxing.boxBoolean(((NiceResponse) objPost$default).getIsSuccessful());
        }
        int i7 = c07932.I$0;
        Integer num5 = (Integer) c07932.L$3;
        num3 = (Integer) c07932.L$2;
        kitsuStatusType2 = (Companion.KitsuStatusType) c07932.L$1;
        AuthData authData3 = (AuthData) c07932.L$0;
        ResultKt.throwOnFailure(objPost$default);
        num4 = num5;
        i2 = i7;
        authData2 = authData3;
        Integer num6 = num4;
        Integer num7 = (Integer) objPost$default;
        if (num7 == null) {
            Pair[] pairArr = new Pair[3];
            pairArr[0] = TuplesKt.to("type", "libraryEntries");
            Pair[] pairArr2 = new Pair[3];
            pairArr2[0] = TuplesKt.to("ratingTwenty", num3);
            pairArr2[1] = TuplesKt.to("progress", num6);
            pairArr2[2] = TuplesKt.to(NotificationCompat.CATEGORY_STATUS, kitsuStatusType2 == null ? null : kitsuStatusAsString[Math.max(0, kitsuStatusType2.getValue())]);
            pairArr[1] = TuplesKt.to("attributes", MapsKt.mapOf(pairArr2));
            AuthData authData4 = authData2;
            pairArr[2] = TuplesKt.to("relationships", MapsKt.mapOf(TuplesKt.to("anime", MapsKt.mapOf(TuplesKt.to("data", MapsKt.mapOf(TuplesKt.to("type", "anime"), TuplesKt.to("id", String.valueOf(i2)))))), TuplesKt.to("user", MapsKt.mapOf(TuplesKt.to("data", MapsKt.mapOf(TuplesKt.to("type", SoundcloudSearchQueryHandlerFactory.USERS), TuplesKt.to("id", Boxing.boxInt(authData4.getUser().getId()))))))));
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("data", MapsKt.mapOf(pairArr)));
            Requests app2 = MainActivityKt.getApp();
            String str = this.apiUrl + "/library-entries";
            Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("content-type", "application/vnd.api+json"), TuplesKt.to("Authorization", "Bearer " + authData4.getToken().getAccessToken()));
            RequestBody requestBodyCreate$default = RequestBody.Companion.create$default(RequestBody.INSTANCE, AppUtils.INSTANCE.toJson(mapMapOf), (MediaType) null, 1, (Object) null);
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07932.L$0 = SpillingKt.nullOutSpilledVariable(authData4);
            c07932.L$1 = SpillingKt.nullOutSpilledVariable(kitsuStatusType2);
            c07932.L$2 = SpillingKt.nullOutSpilledVariable(num3);
            c07932.L$3 = SpillingKt.nullOutSpilledVariable(num6);
            c07932.L$4 = SpillingKt.nullOutSpilledVariable(num7);
            c07932.L$5 = SpillingKt.nullOutSpilledVariable(mapMapOf);
            c07932.I$0 = i2;
            c07932.label = 4;
            objPost$default = Requests.post$default(app2, str, mapMapOf2, null, null, null, null, null, null, requestBodyCreate$default, false, 0, null, 0L, fallbackInterceptor, false, null, c07932, 57084, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Boxing.boxBoolean(((NiceResponse) objPost$default).getIsSuccessful());
        }
        if (kitsuStatusType2 != null && kitsuStatusType2 != Companion.KitsuStatusType.None) {
            int iIntValue = num7.intValue();
            String str2 = kitsuStatusAsString[Math.max(0, kitsuStatusType2.getValue())];
            c07932.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
            c07932.L$1 = SpillingKt.nullOutSpilledVariable(kitsuStatusType2);
            c07932.L$2 = SpillingKt.nullOutSpilledVariable(num3);
            c07932.L$3 = SpillingKt.nullOutSpilledVariable(num6);
            c07932.L$4 = SpillingKt.nullOutSpilledVariable(num7);
            c07932.I$0 = i2;
            c07932.label = 3;
            Object scoreRequest = setScoreRequest(authData2, iIntValue, str2, num3, num6, c07932);
            if (scoreRequest != coroutine_suspended) {
                return scoreRequest;
            }
            return coroutine_suspended;
        }
        Requests app3 = MainActivityKt.getApp();
        String str3 = this.apiUrl + "/library-entries/" + num7.intValue();
        Map mapMapOf3 = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + authData2.getToken().getAccessToken()));
        FallbackInterceptor fallbackInterceptor2 = this.apiFallbackInterceptor;
        c07932.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
        c07932.L$1 = SpillingKt.nullOutSpilledVariable(kitsuStatusType2);
        c07932.L$2 = SpillingKt.nullOutSpilledVariable(num3);
        c07932.L$3 = SpillingKt.nullOutSpilledVariable(num6);
        c07932.L$4 = SpillingKt.nullOutSpilledVariable(num7);
        c07932.I$0 = i2;
        c07932.label = 2;
        objPost$default = Requests.delete$default(app3, str3, mapMapOf3, null, null, null, null, null, null, null, false, 0, null, 0L, fallbackInterceptor2, false, null, c07932, 57340, null);
        if (objPost$default == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Boxing.boxBoolean(((NiceResponse) objPost$default).getIsSuccessful());
    }

    static /* synthetic */ Object setScoreRequest$default(KitsuApi kitsuApi, AuthData authData, int i, Companion.KitsuStatusType kitsuStatusType, Integer num, Integer num2, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            kitsuStatusType = null;
        }
        if ((i2 & 8) != 0) {
            num = null;
        }
        if ((i2 & 16) != 0) {
            num2 = null;
        }
        return kitsuApi.setScoreRequest(authData, i, kitsuStatusType, num, num2, (Continuation<? super Boolean>) continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object setScoreRequest(AuthData authData, int i, String str, Integer num, Integer num2, Continuation<? super Boolean> continuation) {
        AnonymousClass2 anonymousClass2;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        } else {
            anonymousClass2 = new AnonymousClass2(continuation);
        }
        Object objPatch$default = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objPatch$default);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("data", MapsKt.mapOf(TuplesKt.to("type", "libraryEntries"), TuplesKt.to("id", String.valueOf(i)), TuplesKt.to("attributes", MapsKt.mapOf(TuplesKt.to("ratingTwenty", num), TuplesKt.to("progress", num2), TuplesKt.to(NotificationCompat.CATEGORY_STATUS, str))))));
            Requests app2 = MainActivityKt.getApp();
            String str2 = this.apiUrl + "/library-entries/" + i;
            Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("content-type", "application/vnd.api+json"), TuplesKt.to("Authorization", "Bearer " + authData.getToken().getAccessToken()));
            RequestBody requestBodyCreate$default = RequestBody.Companion.create$default(RequestBody.INSTANCE, AppUtils.INSTANCE.toJson(mapMapOf), (MediaType) null, 1, (Object) null);
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(num);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(num2);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(mapMapOf);
            anonymousClass2.I$0 = i;
            anonymousClass2.label = 1;
            objPatch$default = Requests.patch$default(app2, str2, mapMapOf2, null, null, null, null, null, null, requestBodyCreate$default, false, 0, null, 0L, fallbackInterceptor, false, null, anonymousClass2, 57084, null);
            if (objPatch$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass2.I$0;
            ResultKt.throwOnFailure(objPatch$default);
        }
        return Boxing.boxBoolean(((NiceResponse) objPatch$default).getIsSuccessful());
    }

    static /* synthetic */ Object setScoreRequest$default(KitsuApi kitsuApi, AuthData authData, int i, String str, Integer num, Integer num2, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        if ((i2 & 8) != 0) {
            num = null;
        }
        if ((i2 & 16) != 0) {
            num2 = null;
        }
        return kitsuApi.setScoreRequest(authData, i, str, num, num2, (Continuation<? super Boolean>) continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object getAnimeLibraryEntryId(AuthData authData, int i, Continuation<? super Integer> continuation) {
        C07831 c07831;
        if (continuation instanceof C07831) {
            c07831 = (C07831) continuation;
            if ((c07831.label & Integer.MIN_VALUE) != 0) {
                c07831.label -= Integer.MIN_VALUE;
            } else {
                c07831 = new C07831(continuation);
            }
        } else {
            c07831 = new C07831(continuation);
        }
        Object obj = c07831.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07831.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            int id = authData.getUser().getId();
            Requests app2 = MainActivityKt.getApp();
            String str = this.apiUrl + "/library-entries?filter[userId]=" + id + "&filter[animeId]=" + i;
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + authData.getToken().getAccessToken()));
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07831.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07831.I$0 = i;
            c07831.I$1 = id;
            c07831.label = 1;
            obj = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07831, 3580, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07831.I$1;
            int i4 = c07831.I$0;
            ResultKt.throwOnFailure(obj);
        }
        NiceResponse niceResponse = (NiceResponse) obj;
        ResponseParser parser = niceResponse.getParser();
        Intrinsics.checkNotNull(parser);
        KitsuNode kitsuNode = (KitsuNode) CollectionsKt.firstOrNull((List) ((KitsuResponse) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class))).getData());
        if (kitsuNode == null) {
            return null;
        }
        return Boxing.boxInt(Integer.parseInt(kitsuNode.getId()));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object library(AuthData authData, Continuation<? super SyncAPI.LibraryMetadata> continuation) {
        C07871 c07871;
        LinkedHashMap linkedHashMapEmptyMap;
        if (continuation instanceof C07871) {
            c07871 = (C07871) continuation;
            if ((c07871.label & Integer.MIN_VALUE) != 0) {
                c07871.label -= Integer.MIN_VALUE;
            } else {
                c07871 = new C07871(continuation);
            }
        } else {
            c07871 = new C07871(continuation);
        }
        Object kitsuAnimeListSmart = c07871.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07871.label;
        if (i == 0) {
            ResultKt.throwOnFailure(kitsuAnimeListSmart);
            if (authData == null) {
                return null;
            }
            c07871.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07871.label = 1;
            kitsuAnimeListSmart = getKitsuAnimeListSmart(authData, c07871);
            if (kitsuAnimeListSmart == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(kitsuAnimeListSmart);
        }
        KitsuNode[] kitsuNodeArr = (KitsuNode[]) kitsuAnimeListSmart;
        if (kitsuNodeArr == null) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (KitsuNode kitsuNode : kitsuNodeArr) {
                Companion companion = INSTANCE;
                String status = kitsuNode.getAttributes().getStatus();
                if (status == null) {
                    status = "";
                }
                Integer numBoxInt = Boxing.boxInt(companion.convertToStatus(status).getStringRes());
                Object obj = linkedHashMap.get(numBoxInt);
                if (obj == null) {
                    obj = (List) new ArrayList();
                    linkedHashMap.put(numBoxInt, obj);
                }
                ((List) obj).add(kitsuNode);
            }
            linkedHashMapEmptyMap = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Object key = entry.getKey();
                Iterable iterable = (Iterable) entry.getValue();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(((KitsuNode) it.next()).toLibraryItem());
                }
                linkedHashMapEmptyMap.put(key, arrayList);
            }
        }
        EnumEntries<Companion.KitsuStatusType> entries = Companion.KitsuStatusType.getEntries();
        ArrayList arrayList2 = new ArrayList();
        for (Companion.KitsuStatusType kitsuStatusType : entries) {
            if (kitsuStatusType.getValue() >= 0) {
                arrayList2.add(kitsuStatusType);
            }
        }
        ArrayList arrayList3 = arrayList2;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList3, 10)), 16));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            Pair pair = TuplesKt.to(Boxing.boxInt(((Companion.KitsuStatusType) it2.next()).getStringRes()), CollectionsKt.emptyList());
            linkedHashMap2.put(pair.getFirst(), pair.getSecond());
        }
        Map mapPlus = MapsKt.plus(linkedHashMap2, linkedHashMapEmptyMap);
        ArrayList arrayList4 = new ArrayList(mapPlus.size());
        for (Map.Entry entry2 : mapPlus.entrySet()) {
            arrayList4.add(new SyncAPI.LibraryList(TextUtilKt.txt(((Number) entry2.getKey()).intValue(), new Object[0]), (List) entry2.getValue()));
        }
        return new SyncAPI.LibraryMetadata(arrayList4, SetsKt.setOf((Object[]) new ListSorting[]{ListSorting.AlphabeticalA, ListSorting.AlphabeticalZ, ListSorting.UpdatedNew, ListSorting.UpdatedOld, ListSorting.ReleaseDateNew, ListSorting.ReleaseDateOld, ListSorting.RatingHigh, ListSorting.RatingLow}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:41:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:48:0x0101 A[Catch: Exception -> 0x0113, TRY_LEAVE, TryCatch #3 {Exception -> 0x0113, blocks: (B:24:0x0088, B:27:0x0094, B:32:0x00b9, B:39:0x00e5, B:42:0x00ec, B:48:0x0101, B:47:0x00fc, B:38:0x00db, B:31:0x00af, B:35:0x00c0, B:28:0x0096, B:44:0x00f0), top: B:59:0x0088, inners: #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getKitsuAnimeListSmart(AuthData authData, Continuation<? super KitsuNode[]> continuation) {
        C07861 c07861;
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        if (continuation instanceof C07861) {
            c07861 = (C07861) continuation;
            if ((c07861.label & Integer.MIN_VALUE) != 0) {
                c07861.label -= Integer.MIN_VALUE;
            } else {
                c07861 = new C07861(continuation);
            }
        } else {
            c07861 = new C07861(continuation);
        }
        Object kitsuAnimeList = c07861.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07861.label;
        if (i == 0) {
            ResultKt.throwOnFailure(kitsuAnimeList);
            if (getRequireLibraryRefresh()) {
                AuthToken token = authData.getToken();
                int id = authData.getUser().getId();
                c07861.L$0 = authData;
                c07861.label = 1;
                kitsuAnimeList = getKitsuAnimeList(token, id, c07861);
                if (kitsuAnimeList == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
                String strValueOf = String.valueOf(authData.getUser().getId());
                Context context = companion.getContext();
                if (context != null) {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(KITSU_CACHED_LIST, strValueOf), null);
                        if (string == null) {
                            objDecodeFromString = null;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(KitsuNode.class), KitsuNode.INSTANCE.serializer()));
                            } catch (Throwable th) {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    try {
                                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                    } catch (SerializationException e) {
                                        ArchComponentExtKt.logError(e);
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<KitsuNode[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeListSmart$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<KitsuNode[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeListSmart$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<KitsuNode[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeListSmart$$inlined$getKey$1
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(KitsuNode[].class), null, 2, null));
                                } catch (Throwable th2) {
                                    Result.Companion companion5 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<KitsuNode[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$getKitsuAnimeListSmart$$inlined$getKey$1
                                    });
                                }
                            }
                        }
                    } catch (Exception unused2) {
                        objDecodeFromString = null;
                    }
                } else {
                    objDecodeFromString = null;
                }
                if (objDecodeFromString instanceof KitsuNode[]) {
                    return (KitsuNode[]) objDecodeFromString;
                }
                return null;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            authData = (AuthData) c07861.L$0;
            ResultKt.throwOnFailure(kitsuAnimeList);
        }
        KitsuNode[] kitsuNodeArr = (KitsuNode[]) kitsuAnimeList;
        CloudStreamApp.INSTANCE.setKey(KITSU_CACHED_LIST, String.valueOf(authData.getUser().getId()), kitsuNodeArr);
        return kitsuNodeArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0114 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x0115  */
    /* JADX WARN: Code duplicated, block: B:22:0x012e  */
    /* JADX WARN: Code duplicated, block: B:24:0x0136  */
    /* JADX WARN: Code duplicated, block: B:27:0x0141  */
    /* JADX WARN: Code duplicated, block: B:28:0x0148  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0115 -> B:19:0x011b). Please report as a decompilation issue!!! */
    public final Object getKitsuAnimeList(AuthToken authToken, int i, Continuation<? super KitsuNode[]> continuation) {
        C07841 c07841;
        String[] strArr;
        int i2;
        List arrayList;
        String[] strArr2;
        String str;
        C07841 c07842;
        AuthToken authToken2;
        int i3;
        Object kitsuAnimeListSlice;
        AuthToken authToken3;
        String[] strArr3;
        String next;
        List<KitsuAnimeData> included;
        KitsuAnimeData kitsuAnimeData;
        if (continuation instanceof C07841) {
            c07841 = (C07841) continuation;
            if ((c07841.label & Integer.MIN_VALUE) != 0) {
                c07841.label -= Integer.MIN_VALUE;
            } else {
                c07841 = new C07841(continuation);
            }
        } else {
            c07841 = new C07841(continuation);
        }
        Object obj = c07841.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = c07841.label;
        int i5 = 1;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            String[] strArr4 = {"titles", "canonicalTitle", "posterImage", "synopsis", "startDate", "endDate", "episodeCount"};
            String[] strArr5 = {"progress", "ratingTwenty", "updatedAt", NotificationCompat.CATEGORY_STATUS};
            StringBuilder sb = new StringBuilder();
            sb.append(this.apiUrl);
            sb.append("/library-entries?filter[userId]=");
            sb.append(i);
            sb.append("&filter[kind]=anime&include=anime&page[limit]=500&page[offset]=0&fields[anime]=");
            sb.append(ArraysKt.joinToString$default(strArr4, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            sb.append("&fields[libraryEntries]=");
            strArr = strArr5;
            sb.append(ArraysKt.joinToString$default(strArr, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
            String string = sb.toString();
            i2 = 500;
            arrayList = new ArrayList();
            strArr2 = strArr4;
            str = string;
            c07842 = c07841;
            authToken2 = authToken;
            i3 = i;
            c07842.L$0 = authToken2;
            c07842.L$1 = SpillingKt.nullOutSpilledVariable(strArr2);
            c07842.L$2 = SpillingKt.nullOutSpilledVariable(strArr);
            c07842.L$3 = SpillingKt.nullOutSpilledVariable(str);
            c07842.L$4 = arrayList;
            c07842.I$0 = i3;
            c07842.I$1 = i2;
            c07842.label = i5;
            kitsuAnimeListSlice = getKitsuAnimeListSlice(authToken2, str, c07842);
            if (kitsuAnimeListSlice == coroutine_suspended) {
                return coroutine_suspended;
            }
            String[] strArr6 = strArr;
            authToken3 = authToken2;
            obj = kitsuAnimeListSlice;
            strArr3 = strArr6;
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i6 = c07841.I$1;
            int i7 = c07841.I$0;
            List list = (List) c07841.L$4;
            String[] strArr7 = (String[]) c07841.L$2;
            strArr2 = (String[]) c07841.L$1;
            authToken3 = (AuthToken) c07841.L$0;
            ResultKt.throwOnFailure(obj);
            c07842 = c07841;
            i3 = i7;
            i2 = i6;
            strArr3 = strArr7;
            arrayList = list;
        }
        KitsuResponse kitsuResponse = (KitsuResponse) obj;
        int i8 = 0;
        for (Object obj2 : kitsuResponse.getData()) {
            int i9 = i8 + 1;
            if (i8 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KitsuNode kitsuNode = (KitsuNode) obj2;
            included = kitsuResponse.getIncluded();
            if (included != null) {
                kitsuAnimeData = included.get(i8);
            } else {
                kitsuAnimeData = null;
            }
            kitsuNode.setAnime(kitsuAnimeData);
            i8 = i9;
        }
        arrayList.addAll(kitsuResponse.getData());
        KitsuLinks links = kitsuResponse.getLinks();
        if (links == null && (next = links.getNext()) != null) {
            String[] strArr8 = strArr3;
            str = next;
            authToken2 = authToken3;
            strArr = strArr8;
            i5 = 1;
            c07842.L$0 = authToken2;
            c07842.L$1 = SpillingKt.nullOutSpilledVariable(strArr2);
            c07842.L$2 = SpillingKt.nullOutSpilledVariable(strArr);
            c07842.L$3 = SpillingKt.nullOutSpilledVariable(str);
            c07842.L$4 = arrayList;
            c07842.I$0 = i3;
            c07842.I$1 = i2;
            c07842.label = i5;
            kitsuAnimeListSlice = getKitsuAnimeListSlice(authToken2, str, c07842);
            if (kitsuAnimeListSlice == coroutine_suspended) {
                return coroutine_suspended;
            }
            String[] strArr9 = strArr;
            authToken3 = authToken2;
            obj = kitsuAnimeListSlice;
            strArr3 = strArr9;
            KitsuResponse kitsuResponse2 = (KitsuResponse) obj;
            int i10 = 0;
            while (r12.hasNext()) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                KitsuNode kitsuNode2 = (KitsuNode) obj2;
                included = kitsuResponse2.getIncluded();
                if (included != null) {
                    kitsuAnimeData = included.get(i10);
                } else {
                    kitsuAnimeData = null;
                }
                kitsuNode2.setAnime(kitsuAnimeData);
                i10 = i11;
            }
            arrayList.addAll(kitsuResponse2.getData());
            KitsuLinks links2 = kitsuResponse2.getLinks();
            if (links2 == null) {
            }
            return arrayList.toArray(new KitsuNode[0]);
        }
        return arrayList.toArray(new KitsuNode[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getKitsuAnimeListSlice(AuthToken authToken, String str, Continuation<? super KitsuResponse> continuation) {
        C07851 c07851;
        if (continuation instanceof C07851) {
            c07851 = (C07851) continuation;
            if ((c07851.label & Integer.MIN_VALUE) != 0) {
                c07851.label -= Integer.MIN_VALUE;
            } else {
                c07851 = new C07851(continuation);
            }
        } else {
            c07851 = new C07851(continuation);
        }
        Object obj = c07851.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07851.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + authToken.getAccessToken()));
            FallbackInterceptor fallbackInterceptor = this.apiFallbackInterceptor;
            c07851.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07851.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07851.label = 1;
            Object obj2 = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, fallbackInterceptor, false, null, c07851, 3580, null);
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
        return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(KitsuResponse.class));
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tBA\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J1\u0010\u001d\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0011R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$ResponseToken;", "", "tokenType", "", "expiresIn", "", "accessToken", "refreshToken", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTokenType$annotations", "()V", "getTokenType", "()Ljava/lang/String;", "getExpiresIn$annotations", "getExpiresIn", "()I", "getAccessToken$annotations", "getAccessToken", "getRefreshToken$annotations", "getRefreshToken", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ResponseToken {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("access_token")
        private final String accessToken;

        @JsonProperty("expires_in")
        private final int expiresIn;

        @JsonProperty("refresh_token")
        private final String refreshToken;

        @JsonProperty("token_type")
        private final String tokenType;

        public static /* synthetic */ ResponseToken copy$default(ResponseToken responseToken, String str, int i, String str2, String str3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = responseToken.tokenType;
            }
            if ((i2 & 2) != 0) {
                i = responseToken.expiresIn;
            }
            if ((i2 & 4) != 0) {
                str2 = responseToken.accessToken;
            }
            if ((i2 & 8) != 0) {
                str3 = responseToken.refreshToken;
            }
            return responseToken.copy(str, i, str2, str3);
        }

        @SerialName("access_token")
        public static /* synthetic */ void getAccessToken$annotations() {
        }

        @SerialName("expires_in")
        public static /* synthetic */ void getExpiresIn$annotations() {
        }

        @SerialName("refresh_token")
        public static /* synthetic */ void getRefreshToken$annotations() {
        }

        @SerialName("token_type")
        public static /* synthetic */ void getTokenType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTokenType() {
            return this.tokenType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getExpiresIn() {
            return this.expiresIn;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public final ResponseToken copy(@JsonProperty("token_type") String tokenType, @JsonProperty("expires_in") int expiresIn, @JsonProperty("access_token") String accessToken, @JsonProperty("refresh_token") String refreshToken) {
            Intrinsics.checkNotNullParameter(tokenType, "tokenType");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            return new ResponseToken(tokenType, expiresIn, accessToken, refreshToken);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResponseToken)) {
                return false;
            }
            ResponseToken responseToken = (ResponseToken) other;
            return Intrinsics.areEqual(this.tokenType, responseToken.tokenType) && this.expiresIn == responseToken.expiresIn && Intrinsics.areEqual(this.accessToken, responseToken.accessToken) && Intrinsics.areEqual(this.refreshToken, responseToken.refreshToken);
        }

        public int hashCode() {
            return (((((this.tokenType.hashCode() * 31) + this.expiresIn) * 31) + this.accessToken.hashCode()) * 31) + this.refreshToken.hashCode();
        }

        public String toString() {
            return "ResponseToken(tokenType=" + this.tokenType + ", expiresIn=" + this.expiresIn + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$ResponseToken$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$ResponseToken;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResponseToken> serializer() {
                return KitsuApi$ResponseToken$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResponseToken(int i, String str, int i2, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, KitsuApi$ResponseToken$$serializer.INSTANCE.getDescriptor());
            }
            this.tokenType = str;
            this.expiresIn = i2;
            this.accessToken = str2;
            this.refreshToken = str3;
        }

        public ResponseToken(@JsonProperty("token_type") String tokenType, @JsonProperty("expires_in") int i, @JsonProperty("access_token") String accessToken, @JsonProperty("refresh_token") String refreshToken) {
            Intrinsics.checkNotNullParameter(tokenType, "tokenType");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.tokenType = tokenType;
            this.expiresIn = i;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ResponseToken self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.tokenType);
            output.encodeIntElement(serialDesc, 1, self.expiresIn);
            output.encodeStringElement(serialDesc, 2, self.accessToken);
            output.encodeStringElement(serialDesc, 3, self.refreshToken);
        }

        public final String getTokenType() {
            return this.tokenType;
        }

        public final int getExpiresIn() {
            return this.expiresIn;
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getRefreshToken() {
            return this.refreshToken;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 52\u00020\u0001:\u000245B3\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBC\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\u0006\u0010 \u001a\u00020!J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010&\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0014\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020\rHÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00002\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0001¢\u0006\u0002\b3R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u001aR&\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u0012\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "", "id", "", "attributes", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;", "relationships", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships;", "anime", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getAttributes$annotations", "getAttributes", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;", "getRelationships$annotations", "getRelationships", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships;", "getAnime$annotations", "getAnime", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;", "setAnime", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;)V", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuNode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("anime")
        private KitsuAnimeData anime;

        @JsonProperty("attributes")
        private final KitsuNodeAttributes attributes;

        @JsonProperty("id")
        private final String id;

        @JsonProperty("relationships")
        private final KitsuRelationships relationships;

        public static /* synthetic */ KitsuNode copy$default(KitsuNode kitsuNode, String str, KitsuNodeAttributes kitsuNodeAttributes, KitsuRelationships kitsuRelationships, KitsuAnimeData kitsuAnimeData, int i, Object obj) {
            if ((i & 1) != 0) {
                str = kitsuNode.id;
            }
            if ((i & 2) != 0) {
                kitsuNodeAttributes = kitsuNode.attributes;
            }
            if ((i & 4) != 0) {
                kitsuRelationships = kitsuNode.relationships;
            }
            if ((i & 8) != 0) {
                kitsuAnimeData = kitsuNode.anime;
            }
            return kitsuNode.copy(str, kitsuNodeAttributes, kitsuRelationships, kitsuAnimeData);
        }

        @SerialName("anime")
        public static /* synthetic */ void getAnime$annotations() {
        }

        @SerialName("attributes")
        public static /* synthetic */ void getAttributes$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("relationships")
        public static /* synthetic */ void getRelationships$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final KitsuNodeAttributes getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final KitsuRelationships getRelationships() {
            return this.relationships;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final KitsuAnimeData getAnime() {
            return this.anime;
        }

        public final KitsuNode copy(@JsonProperty("id") String id, @JsonProperty("attributes") KitsuNodeAttributes attributes, @JsonProperty("relationships") KitsuRelationships relationships, @JsonProperty("anime") KitsuAnimeData anime) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new KitsuNode(id, attributes, relationships, anime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuNode)) {
                return false;
            }
            KitsuNode kitsuNode = (KitsuNode) other;
            return Intrinsics.areEqual(this.id, kitsuNode.id) && Intrinsics.areEqual(this.attributes, kitsuNode.attributes) && Intrinsics.areEqual(this.relationships, kitsuNode.relationships) && Intrinsics.areEqual(this.anime, kitsuNode.anime);
        }

        public int hashCode() {
            int iHashCode = ((this.id.hashCode() * 31) + this.attributes.hashCode()) * 31;
            KitsuRelationships kitsuRelationships = this.relationships;
            int iHashCode2 = (iHashCode + (kitsuRelationships == null ? 0 : kitsuRelationships.hashCode())) * 31;
            KitsuAnimeData kitsuAnimeData = this.anime;
            return iHashCode2 + (kitsuAnimeData != null ? kitsuAnimeData.hashCode() : 0);
        }

        public String toString() {
            return "KitsuNode(id=" + this.id + ", attributes=" + this.attributes + ", relationships=" + this.relationships + ", anime=" + this.anime + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuNode> serializer() {
                return KitsuApi$KitsuNode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuNode(int i, String str, KitsuNodeAttributes kitsuNodeAttributes, KitsuRelationships kitsuRelationships, KitsuAnimeData kitsuAnimeData, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, KitsuApi$KitsuNode$$serializer.INSTANCE.getDescriptor());
            }
            this.id = str;
            this.attributes = kitsuNodeAttributes;
            this.relationships = kitsuRelationships;
            this.anime = kitsuAnimeData;
        }

        public KitsuNode(@JsonProperty("id") String id, @JsonProperty("attributes") KitsuNodeAttributes attributes, @JsonProperty("relationships") KitsuRelationships kitsuRelationships, @JsonProperty("anime") KitsuAnimeData kitsuAnimeData) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            this.id = id;
            this.attributes = attributes;
            this.relationships = kitsuRelationships;
            this.anime = kitsuAnimeData;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuNode self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.id);
            output.encodeSerializableElement(serialDesc, 1, KitsuApi$KitsuNodeAttributes$$serializer.INSTANCE, self.attributes);
            output.encodeNullableSerializableElement(serialDesc, 2, KitsuApi$KitsuRelationships$$serializer.INSTANCE, self.relationships);
            output.encodeNullableSerializableElement(serialDesc, 3, KitsuApi$KitsuAnimeData$$serializer.INSTANCE, self.anime);
        }

        public final String getId() {
            return this.id;
        }

        public final KitsuNodeAttributes getAttributes() {
            return this.attributes;
        }

        public final KitsuRelationships getRelationships() {
            return this.relationships;
        }

        public final KitsuAnimeData getAnime() {
            return this.anime;
        }

        public final void setAnime(KitsuAnimeData kitsuAnimeData) {
            this.anime = kitsuAnimeData;
        }

        public final SyncAPI.LibraryItem toLibraryItem() {
            String str;
            KitsuAnimeAttributes attributes;
            KitsuAnimeAttributes attributes2;
            KitsuAnimeAttributes attributes3;
            KitsuAnimeAttributes attributes4;
            KitsuAnimeAttributes attributes5;
            KitsuAnimeAttributes attributes6;
            KitsuAnimeData kitsuAnimeData = this.anime;
            Date dateFrom = null;
            Integer episodeCount = (kitsuAnimeData == null || (attributes6 = kitsuAnimeData.getAttributes()) == null) ? null : attributes6.getEpisodeCount();
            String startDate = (kitsuAnimeData == null || (attributes5 = kitsuAnimeData.getAttributes()) == null) ? null : attributes5.getStartDate();
            KitsuPosterImage posterImage = (kitsuAnimeData == null || (attributes4 = kitsuAnimeData.getAttributes()) == null) ? null : attributes4.getPosterImage();
            String canonicalTitle = (kitsuAnimeData == null || (attributes3 = kitsuAnimeData.getAttributes()) == null) ? null : attributes3.getCanonicalTitle();
            KitsuTitles titles = (kitsuAnimeData == null || (attributes2 = kitsuAnimeData.getAttributes()) == null) ? null : attributes2.getTitles();
            String id = kitsuAnimeData != null ? kitsuAnimeData.getId() : null;
            String synopsis = (kitsuAnimeData == null || (attributes = kitsuAnimeData.getAttributes()) == null) ? null : attributes.getSynopsis();
            if (canonicalTitle == null) {
                canonicalTitle = titles != null ? titles.getEnJp() : null;
                if (canonicalTitle == null) {
                    canonicalTitle = titles != null ? titles.getJaJp() : null;
                    if (canonicalTitle == null) {
                        canonicalTitle = "";
                    }
                }
            }
            String str2 = "https://kitsu.app/anime/" + id + '/';
            String str3 = this.id;
            Integer progress = this.attributes.getProgress();
            Score scoreFrom = Score.INSTANCE.from(this.attributes.getRatingTwenty(), 20);
            Long dateLong = KitsuApi.INSTANCE.parseDateLong(this.attributes.getUpdatedAt());
            TvType tvType = TvType.Anime;
            if (posterImage != null && (medium = posterImage.getLarge()) != null) {
                str = medium;
            } else if (posterImage != null) {
                String medium = posterImage.getMedium();
                str = medium;
            } else {
                str = null;
            }
            if (startDate != null) {
                try {
                    dateFrom = DesugarDate.from(LocalDate.parse(startDate).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                } catch (RuntimeException unused) {
                }
            }
            return new SyncAPI.LibraryItem(canonicalTitle, str2, str3, progress, episodeCount, scoreFrom, dateLong, "Kitsu", tvType, str, null, null, dateFrom, null, synopsis, null, null, 106496, null);
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 B2\u00020\u0001:\u0002ABBg\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fBk\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0013J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010&J\u0010\u00101\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010&Jn\u00102\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u00103J\u0014\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00107\u001a\u00020\fHÖ\u0081\u0004J\n\u00108\u001a\u00020\u0005HÖ\u0081\u0004J%\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?H\u0001¢\u0006\u0002\b@R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0015\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010\u001aR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0015\u001a\u0004\b!\u0010\u001aR\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0015\u001a\u0004\b#\u0010\u001aR \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010'\u0012\u0004\b$\u0010\u0015\u001a\u0004\b%\u0010&R \u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010'\u0012\u0004\b(\u0010\u0015\u001a\u0004\b)\u0010&¨\u0006C"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;", "", "titles", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;", "canonicalTitle", "", "posterImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;", "synopsis", "startDate", "endDate", "episodeCount", "", "episodeLength", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTitles$annotations", "()V", "getTitles", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;", "getCanonicalTitle$annotations", "getCanonicalTitle", "()Ljava/lang/String;", "getPosterImage$annotations", "getPosterImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;", "getSynopsis$annotations", "getSynopsis", "getStartDate$annotations", "getStartDate", "getEndDate$annotations", "getEndDate", "getEpisodeCount$annotations", "getEpisodeCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodeLength$annotations", "getEpisodeLength", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuAnimeAttributes {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("canonicalTitle")
        private final String canonicalTitle;

        @JsonProperty("endDate")
        private final String endDate;

        @JsonProperty("episodeCount")
        private final Integer episodeCount;

        @JsonProperty("episodeLength")
        private final Integer episodeLength;

        @JsonProperty("posterImage")
        private final KitsuPosterImage posterImage;

        @JsonProperty("startDate")
        private final String startDate;

        @JsonProperty("synopsis")
        private final String synopsis;

        @JsonProperty("titles")
        private final KitsuTitles titles;

        public static /* synthetic */ KitsuAnimeAttributes copy$default(KitsuAnimeAttributes kitsuAnimeAttributes, KitsuTitles kitsuTitles, String str, KitsuPosterImage kitsuPosterImage, String str2, String str3, String str4, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                kitsuTitles = kitsuAnimeAttributes.titles;
            }
            if ((i & 2) != 0) {
                str = kitsuAnimeAttributes.canonicalTitle;
            }
            if ((i & 4) != 0) {
                kitsuPosterImage = kitsuAnimeAttributes.posterImage;
            }
            if ((i & 8) != 0) {
                str2 = kitsuAnimeAttributes.synopsis;
            }
            if ((i & 16) != 0) {
                str3 = kitsuAnimeAttributes.startDate;
            }
            if ((i & 32) != 0) {
                str4 = kitsuAnimeAttributes.endDate;
            }
            if ((i & 64) != 0) {
                num = kitsuAnimeAttributes.episodeCount;
            }
            if ((i & 128) != 0) {
                num2 = kitsuAnimeAttributes.episodeLength;
            }
            Integer num3 = num;
            Integer num4 = num2;
            String str5 = str3;
            String str6 = str4;
            return kitsuAnimeAttributes.copy(kitsuTitles, str, kitsuPosterImage, str2, str5, str6, num3, num4);
        }

        @SerialName("canonicalTitle")
        public static /* synthetic */ void getCanonicalTitle$annotations() {
        }

        @SerialName("endDate")
        public static /* synthetic */ void getEndDate$annotations() {
        }

        @SerialName("episodeCount")
        public static /* synthetic */ void getEpisodeCount$annotations() {
        }

        @SerialName("episodeLength")
        public static /* synthetic */ void getEpisodeLength$annotations() {
        }

        @SerialName("posterImage")
        public static /* synthetic */ void getPosterImage$annotations() {
        }

        @SerialName("startDate")
        public static /* synthetic */ void getStartDate$annotations() {
        }

        @SerialName("synopsis")
        public static /* synthetic */ void getSynopsis$annotations() {
        }

        @SerialName("titles")
        public static /* synthetic */ void getTitles$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final KitsuTitles getTitles() {
            return this.titles;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCanonicalTitle() {
            return this.canonicalTitle;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final KitsuPosterImage getPosterImage() {
            return this.posterImage;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSynopsis() {
            return this.synopsis;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getStartDate() {
            return this.startDate;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getEndDate() {
            return this.endDate;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getEpisodeCount() {
            return this.episodeCount;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getEpisodeLength() {
            return this.episodeLength;
        }

        public final KitsuAnimeAttributes copy(@JsonProperty("titles") KitsuTitles titles, @JsonProperty("canonicalTitle") String canonicalTitle, @JsonProperty("posterImage") KitsuPosterImage posterImage, @JsonProperty("synopsis") String synopsis, @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate, @JsonProperty("episodeCount") Integer episodeCount, @JsonProperty("episodeLength") Integer episodeLength) {
            return new KitsuAnimeAttributes(titles, canonicalTitle, posterImage, synopsis, startDate, endDate, episodeCount, episodeLength);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuAnimeAttributes)) {
                return false;
            }
            KitsuAnimeAttributes kitsuAnimeAttributes = (KitsuAnimeAttributes) other;
            return Intrinsics.areEqual(this.titles, kitsuAnimeAttributes.titles) && Intrinsics.areEqual(this.canonicalTitle, kitsuAnimeAttributes.canonicalTitle) && Intrinsics.areEqual(this.posterImage, kitsuAnimeAttributes.posterImage) && Intrinsics.areEqual(this.synopsis, kitsuAnimeAttributes.synopsis) && Intrinsics.areEqual(this.startDate, kitsuAnimeAttributes.startDate) && Intrinsics.areEqual(this.endDate, kitsuAnimeAttributes.endDate) && Intrinsics.areEqual(this.episodeCount, kitsuAnimeAttributes.episodeCount) && Intrinsics.areEqual(this.episodeLength, kitsuAnimeAttributes.episodeLength);
        }

        public int hashCode() {
            KitsuTitles kitsuTitles = this.titles;
            int iHashCode = (kitsuTitles == null ? 0 : kitsuTitles.hashCode()) * 31;
            String str = this.canonicalTitle;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            KitsuPosterImage kitsuPosterImage = this.posterImage;
            int iHashCode3 = (iHashCode2 + (kitsuPosterImage == null ? 0 : kitsuPosterImage.hashCode())) * 31;
            String str2 = this.synopsis;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.startDate;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.endDate;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num = this.episodeCount;
            int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episodeLength;
            return iHashCode7 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "KitsuAnimeAttributes(titles=" + this.titles + ", canonicalTitle=" + this.canonicalTitle + ", posterImage=" + this.posterImage + ", synopsis=" + this.synopsis + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", episodeCount=" + this.episodeCount + ", episodeLength=" + this.episodeLength + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuAnimeAttributes> serializer() {
                return KitsuApi$KitsuAnimeAttributes$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuAnimeAttributes(int i, KitsuTitles kitsuTitles, String str, KitsuPosterImage kitsuPosterImage, String str2, String str3, String str4, Integer num, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
            if (255 != (i & 255)) {
                PluginExceptionsKt.throwMissingFieldException(i, 255, KitsuApi$KitsuAnimeAttributes$$serializer.INSTANCE.getDescriptor());
            }
            this.titles = kitsuTitles;
            this.canonicalTitle = str;
            this.posterImage = kitsuPosterImage;
            this.synopsis = str2;
            this.startDate = str3;
            this.endDate = str4;
            this.episodeCount = num;
            this.episodeLength = num2;
        }

        public KitsuAnimeAttributes(@JsonProperty("titles") KitsuTitles kitsuTitles, @JsonProperty("canonicalTitle") String str, @JsonProperty("posterImage") KitsuPosterImage kitsuPosterImage, @JsonProperty("synopsis") String str2, @JsonProperty("startDate") String str3, @JsonProperty("endDate") String str4, @JsonProperty("episodeCount") Integer num, @JsonProperty("episodeLength") Integer num2) {
            this.titles = kitsuTitles;
            this.canonicalTitle = str;
            this.posterImage = kitsuPosterImage;
            this.synopsis = str2;
            this.startDate = str3;
            this.endDate = str4;
            this.episodeCount = num;
            this.episodeLength = num2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuAnimeAttributes self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, KitsuApi$KitsuTitles$$serializer.INSTANCE, self.titles);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.canonicalTitle);
            output.encodeNullableSerializableElement(serialDesc, 2, KitsuApi$KitsuPosterImage$$serializer.INSTANCE, self.posterImage);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.synopsis);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.startDate);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.endDate);
            output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.episodeCount);
            output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.episodeLength);
        }

        public final KitsuTitles getTitles() {
            return this.titles;
        }

        public final String getCanonicalTitle() {
            return this.canonicalTitle;
        }

        public final KitsuPosterImage getPosterImage() {
            return this.posterImage;
        }

        public final String getSynopsis() {
            return this.synopsis;
        }

        public final String getStartDate() {
            return this.startDate;
        }

        public final String getEndDate() {
            return this.endDate;
        }

        public final Integer getEpisodeCount() {
            return this.episodeCount;
        }

        public final Integer getEpisodeLength() {
            return this.episodeLength;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;", "", "id", "", "attributes", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getAttributes$annotations", "getAttributes", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeAttributes;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuAnimeData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("attributes")
        private final KitsuAnimeAttributes attributes;

        @JsonProperty("id")
        private final String id;

        public static /* synthetic */ KitsuAnimeData copy$default(KitsuAnimeData kitsuAnimeData, String str, KitsuAnimeAttributes kitsuAnimeAttributes, int i, Object obj) {
            if ((i & 1) != 0) {
                str = kitsuAnimeData.id;
            }
            if ((i & 2) != 0) {
                kitsuAnimeAttributes = kitsuAnimeData.attributes;
            }
            return kitsuAnimeData.copy(str, kitsuAnimeAttributes);
        }

        @SerialName("attributes")
        public static /* synthetic */ void getAttributes$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final KitsuAnimeAttributes getAttributes() {
            return this.attributes;
        }

        public final KitsuAnimeData copy(@JsonProperty("id") String id, @JsonProperty("attributes") KitsuAnimeAttributes attributes) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new KitsuAnimeData(id, attributes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuAnimeData)) {
                return false;
            }
            KitsuAnimeData kitsuAnimeData = (KitsuAnimeData) other;
            return Intrinsics.areEqual(this.id, kitsuAnimeData.id) && Intrinsics.areEqual(this.attributes, kitsuAnimeData.attributes);
        }

        public int hashCode() {
            return (this.id.hashCode() * 31) + this.attributes.hashCode();
        }

        public String toString() {
            return "KitsuAnimeData(id=" + this.id + ", attributes=" + this.attributes + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuAnimeData> serializer() {
                return KitsuApi$KitsuAnimeData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuAnimeData(int i, String str, KitsuAnimeAttributes kitsuAnimeAttributes, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, KitsuApi$KitsuAnimeData$$serializer.INSTANCE.getDescriptor());
            }
            this.id = str;
            this.attributes = kitsuAnimeAttributes;
        }

        public KitsuAnimeData(@JsonProperty("id") String id, @JsonProperty("attributes") KitsuAnimeAttributes attributes) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            this.id = id;
            this.attributes = attributes;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuAnimeData self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.id);
            output.encodeSerializableElement(serialDesc, 1, KitsuApi$KitsuAnimeAttributes$$serializer.INSTANCE, self.attributes);
        }

        public final String getId() {
            return this.id;
        }

        public final KitsuAnimeAttributes getAttributes() {
            return this.attributes;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b;\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 d2\u00020\u0001:\u0002cdBÇ\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0017\u0010\u0018B»\u0001\b\u0010\u0012\u0006\u0010\u0019\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u0017\u0010\u001cJ\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010K\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010L\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u0010\u0010P\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010/J\u0010\u0010Q\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010/J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0005HÆ\u0003JÎ\u0001\u0010T\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010UJ\u0014\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010Y\u001a\u00020\fHÖ\u0081\u0004J\n\u0010Z\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020\u00002\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0001¢\u0006\u0002\bbR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001e\u001a\u0004\b\"\u0010#R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u001e\u001a\u0004\b%\u0010&R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u001e\u001a\u0004\b(\u0010#R\u001e\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001e\u001a\u0004\b*\u0010#R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010\u001e\u001a\u0004\b,\u0010#R \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b-\u0010\u001e\u001a\u0004\b.\u0010/R \u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b1\u0010\u001e\u001a\u0004\b2\u0010/R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001e\u001a\u0004\b4\u0010#R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001e\u001a\u0004\b6\u0010#R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b7\u0010\u001e\u001a\u0004\b8\u0010#R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001e\u001a\u0004\b:\u0010;R \u0010\u0013\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b<\u0010\u001e\u001a\u0004\b=\u0010/R \u0010\u0014\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u00100\u0012\u0004\b>\u0010\u001e\u001a\u0004\b?\u0010/R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b@\u0010\u001e\u001a\u0004\bA\u0010#R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bB\u0010\u001e\u001a\u0004\bC\u0010#¨\u0006e"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;", "", "titles", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;", "canonicalTitle", "", "posterImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;", "synopsis", "startDate", "endDate", "episodeCount", "", "episodeLength", "name", "location", "createdAt", "avatar", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;", "progress", "ratingTwenty", "updatedAt", NotificationCompat.CATEGORY_STATUS, "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTitles$annotations", "()V", "getTitles", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;", "getCanonicalTitle$annotations", "getCanonicalTitle", "()Ljava/lang/String;", "getPosterImage$annotations", "getPosterImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;", "getSynopsis$annotations", "getSynopsis", "getStartDate$annotations", "getStartDate", "getEndDate$annotations", "getEndDate", "getEpisodeCount$annotations", "getEpisodeCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodeLength$annotations", "getEpisodeLength", "getName$annotations", "getName", "getLocation$annotations", "getLocation", "getCreatedAt$annotations", "getCreatedAt", "getAvatar$annotations", "getAvatar", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;", "getProgress$annotations", "getProgress", "getRatingTwenty$annotations", "getRatingTwenty", "getUpdatedAt$annotations", "getUpdatedAt", "getStatus$annotations", "getStatus", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuNodeAttributes {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("avatar")
        private final KitsuUserAvatar avatar;

        @JsonProperty("canonicalTitle")
        private final String canonicalTitle;

        @JsonProperty("createdAt")
        private final String createdAt;

        @JsonProperty("endDate")
        private final String endDate;

        @JsonProperty("episodeCount")
        private final Integer episodeCount;

        @JsonProperty("episodeLength")
        private final Integer episodeLength;

        @JsonProperty("location")
        private final String location;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("posterImage")
        private final KitsuPosterImage posterImage;

        @JsonProperty("progress")
        private final Integer progress;

        @JsonProperty("ratingTwenty")
        private final Integer ratingTwenty;

        @JsonProperty("startDate")
        private final String startDate;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("synopsis")
        private final String synopsis;

        @JsonProperty("titles")
        private final KitsuTitles titles;

        @JsonProperty("updatedAt")
        private final String updatedAt;

        public static /* synthetic */ KitsuNodeAttributes copy$default(KitsuNodeAttributes kitsuNodeAttributes, KitsuTitles kitsuTitles, String str, KitsuPosterImage kitsuPosterImage, String str2, String str3, String str4, Integer num, Integer num2, String str5, String str6, String str7, KitsuUserAvatar kitsuUserAvatar, Integer num3, Integer num4, String str8, String str9, int i, Object obj) {
            KitsuTitles kitsuTitles2 = (i & 1) != 0 ? kitsuNodeAttributes.titles : kitsuTitles;
            return kitsuNodeAttributes.copy(kitsuTitles2, (i & 2) != 0 ? kitsuNodeAttributes.canonicalTitle : str, (i & 4) != 0 ? kitsuNodeAttributes.posterImage : kitsuPosterImage, (i & 8) != 0 ? kitsuNodeAttributes.synopsis : str2, (i & 16) != 0 ? kitsuNodeAttributes.startDate : str3, (i & 32) != 0 ? kitsuNodeAttributes.endDate : str4, (i & 64) != 0 ? kitsuNodeAttributes.episodeCount : num, (i & 128) != 0 ? kitsuNodeAttributes.episodeLength : num2, (i & 256) != 0 ? kitsuNodeAttributes.name : str5, (i & 512) != 0 ? kitsuNodeAttributes.location : str6, (i & 1024) != 0 ? kitsuNodeAttributes.createdAt : str7, (i & 2048) != 0 ? kitsuNodeAttributes.avatar : kitsuUserAvatar, (i & 4096) != 0 ? kitsuNodeAttributes.progress : num3, (i & 8192) != 0 ? kitsuNodeAttributes.ratingTwenty : num4, (i & 16384) != 0 ? kitsuNodeAttributes.updatedAt : str8, (i & 32768) != 0 ? kitsuNodeAttributes.status : str9);
        }

        @SerialName("avatar")
        public static /* synthetic */ void getAvatar$annotations() {
        }

        @SerialName("canonicalTitle")
        public static /* synthetic */ void getCanonicalTitle$annotations() {
        }

        @SerialName("createdAt")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @SerialName("endDate")
        public static /* synthetic */ void getEndDate$annotations() {
        }

        @SerialName("episodeCount")
        public static /* synthetic */ void getEpisodeCount$annotations() {
        }

        @SerialName("episodeLength")
        public static /* synthetic */ void getEpisodeLength$annotations() {
        }

        @SerialName("location")
        public static /* synthetic */ void getLocation$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("posterImage")
        public static /* synthetic */ void getPosterImage$annotations() {
        }

        @SerialName("progress")
        public static /* synthetic */ void getProgress$annotations() {
        }

        @SerialName("ratingTwenty")
        public static /* synthetic */ void getRatingTwenty$annotations() {
        }

        @SerialName("startDate")
        public static /* synthetic */ void getStartDate$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("synopsis")
        public static /* synthetic */ void getSynopsis$annotations() {
        }

        @SerialName("titles")
        public static /* synthetic */ void getTitles$annotations() {
        }

        @SerialName("updatedAt")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final KitsuTitles getTitles() {
            return this.titles;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getLocation() {
            return this.location;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final KitsuUserAvatar getAvatar() {
            return this.avatar;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Integer getProgress() {
            return this.progress;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Integer getRatingTwenty() {
            return this.ratingTwenty;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCanonicalTitle() {
            return this.canonicalTitle;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final KitsuPosterImage getPosterImage() {
            return this.posterImage;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSynopsis() {
            return this.synopsis;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getStartDate() {
            return this.startDate;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getEndDate() {
            return this.endDate;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getEpisodeCount() {
            return this.episodeCount;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getEpisodeLength() {
            return this.episodeLength;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final KitsuNodeAttributes copy(@JsonProperty("titles") KitsuTitles titles, @JsonProperty("canonicalTitle") String canonicalTitle, @JsonProperty("posterImage") KitsuPosterImage posterImage, @JsonProperty("synopsis") String synopsis, @JsonProperty("startDate") String startDate, @JsonProperty("endDate") String endDate, @JsonProperty("episodeCount") Integer episodeCount, @JsonProperty("episodeLength") Integer episodeLength, @JsonProperty("name") String name, @JsonProperty("location") String location, @JsonProperty("createdAt") String createdAt, @JsonProperty("avatar") KitsuUserAvatar avatar, @JsonProperty("progress") Integer progress, @JsonProperty("ratingTwenty") Integer ratingTwenty, @JsonProperty("updatedAt") String updatedAt, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status) {
            return new KitsuNodeAttributes(titles, canonicalTitle, posterImage, synopsis, startDate, endDate, episodeCount, episodeLength, name, location, createdAt, avatar, progress, ratingTwenty, updatedAt, status);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuNodeAttributes)) {
                return false;
            }
            KitsuNodeAttributes kitsuNodeAttributes = (KitsuNodeAttributes) other;
            return Intrinsics.areEqual(this.titles, kitsuNodeAttributes.titles) && Intrinsics.areEqual(this.canonicalTitle, kitsuNodeAttributes.canonicalTitle) && Intrinsics.areEqual(this.posterImage, kitsuNodeAttributes.posterImage) && Intrinsics.areEqual(this.synopsis, kitsuNodeAttributes.synopsis) && Intrinsics.areEqual(this.startDate, kitsuNodeAttributes.startDate) && Intrinsics.areEqual(this.endDate, kitsuNodeAttributes.endDate) && Intrinsics.areEqual(this.episodeCount, kitsuNodeAttributes.episodeCount) && Intrinsics.areEqual(this.episodeLength, kitsuNodeAttributes.episodeLength) && Intrinsics.areEqual(this.name, kitsuNodeAttributes.name) && Intrinsics.areEqual(this.location, kitsuNodeAttributes.location) && Intrinsics.areEqual(this.createdAt, kitsuNodeAttributes.createdAt) && Intrinsics.areEqual(this.avatar, kitsuNodeAttributes.avatar) && Intrinsics.areEqual(this.progress, kitsuNodeAttributes.progress) && Intrinsics.areEqual(this.ratingTwenty, kitsuNodeAttributes.ratingTwenty) && Intrinsics.areEqual(this.updatedAt, kitsuNodeAttributes.updatedAt) && Intrinsics.areEqual(this.status, kitsuNodeAttributes.status);
        }

        public int hashCode() {
            KitsuTitles kitsuTitles = this.titles;
            int iHashCode = (kitsuTitles == null ? 0 : kitsuTitles.hashCode()) * 31;
            String str = this.canonicalTitle;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            KitsuPosterImage kitsuPosterImage = this.posterImage;
            int iHashCode3 = (iHashCode2 + (kitsuPosterImage == null ? 0 : kitsuPosterImage.hashCode())) * 31;
            String str2 = this.synopsis;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.startDate;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.endDate;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num = this.episodeCount;
            int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episodeLength;
            int iHashCode8 = (iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str5 = this.name;
            int iHashCode9 = (iHashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.location;
            int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.createdAt;
            int iHashCode11 = (iHashCode10 + (str7 == null ? 0 : str7.hashCode())) * 31;
            KitsuUserAvatar kitsuUserAvatar = this.avatar;
            int iHashCode12 = (iHashCode11 + (kitsuUserAvatar == null ? 0 : kitsuUserAvatar.hashCode())) * 31;
            Integer num3 = this.progress;
            int iHashCode13 = (iHashCode12 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.ratingTwenty;
            int iHashCode14 = (iHashCode13 + (num4 == null ? 0 : num4.hashCode())) * 31;
            String str8 = this.updatedAt;
            int iHashCode15 = (iHashCode14 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.status;
            return iHashCode15 + (str9 != null ? str9.hashCode() : 0);
        }

        public String toString() {
            return "KitsuNodeAttributes(titles=" + this.titles + ", canonicalTitle=" + this.canonicalTitle + ", posterImage=" + this.posterImage + ", synopsis=" + this.synopsis + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", episodeCount=" + this.episodeCount + ", episodeLength=" + this.episodeLength + ", name=" + this.name + ", location=" + this.location + ", createdAt=" + this.createdAt + ", avatar=" + this.avatar + ", progress=" + this.progress + ", ratingTwenty=" + this.ratingTwenty + ", updatedAt=" + this.updatedAt + ", status=" + this.status + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNodeAttributes;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuNodeAttributes> serializer() {
                return KitsuApi$KitsuNodeAttributes$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuNodeAttributes(int i, KitsuTitles kitsuTitles, String str, KitsuPosterImage kitsuPosterImage, String str2, String str3, String str4, Integer num, Integer num2, String str5, String str6, String str7, KitsuUserAvatar kitsuUserAvatar, Integer num3, Integer num4, String str8, String str9, SerializationConstructorMarker serializationConstructorMarker) {
            if (65535 != (i & 65535)) {
                PluginExceptionsKt.throwMissingFieldException(i, 65535, KitsuApi$KitsuNodeAttributes$$serializer.INSTANCE.getDescriptor());
            }
            this.titles = kitsuTitles;
            this.canonicalTitle = str;
            this.posterImage = kitsuPosterImage;
            this.synopsis = str2;
            this.startDate = str3;
            this.endDate = str4;
            this.episodeCount = num;
            this.episodeLength = num2;
            this.name = str5;
            this.location = str6;
            this.createdAt = str7;
            this.avatar = kitsuUserAvatar;
            this.progress = num3;
            this.ratingTwenty = num4;
            this.updatedAt = str8;
            this.status = str9;
        }

        public KitsuNodeAttributes(@JsonProperty("titles") KitsuTitles kitsuTitles, @JsonProperty("canonicalTitle") String str, @JsonProperty("posterImage") KitsuPosterImage kitsuPosterImage, @JsonProperty("synopsis") String str2, @JsonProperty("startDate") String str3, @JsonProperty("endDate") String str4, @JsonProperty("episodeCount") Integer num, @JsonProperty("episodeLength") Integer num2, @JsonProperty("name") String str5, @JsonProperty("location") String str6, @JsonProperty("createdAt") String str7, @JsonProperty("avatar") KitsuUserAvatar kitsuUserAvatar, @JsonProperty("progress") Integer num3, @JsonProperty("ratingTwenty") Integer num4, @JsonProperty("updatedAt") String str8, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String str9) {
            this.titles = kitsuTitles;
            this.canonicalTitle = str;
            this.posterImage = kitsuPosterImage;
            this.synopsis = str2;
            this.startDate = str3;
            this.endDate = str4;
            this.episodeCount = num;
            this.episodeLength = num2;
            this.name = str5;
            this.location = str6;
            this.createdAt = str7;
            this.avatar = kitsuUserAvatar;
            this.progress = num3;
            this.ratingTwenty = num4;
            this.updatedAt = str8;
            this.status = str9;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuNodeAttributes self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, KitsuApi$KitsuTitles$$serializer.INSTANCE, self.titles);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.canonicalTitle);
            output.encodeNullableSerializableElement(serialDesc, 2, KitsuApi$KitsuPosterImage$$serializer.INSTANCE, self.posterImage);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.synopsis);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.startDate);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.endDate);
            output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.episodeCount);
            output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.episodeLength);
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.name);
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.location);
            output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.createdAt);
            output.encodeNullableSerializableElement(serialDesc, 11, KitsuApi$KitsuUserAvatar$$serializer.INSTANCE, self.avatar);
            output.encodeNullableSerializableElement(serialDesc, 12, IntSerializer.INSTANCE, self.progress);
            output.encodeNullableSerializableElement(serialDesc, 13, IntSerializer.INSTANCE, self.ratingTwenty);
            output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.updatedAt);
            output.encodeNullableSerializableElement(serialDesc, 15, StringSerializer.INSTANCE, self.status);
        }

        public final KitsuTitles getTitles() {
            return this.titles;
        }

        public final String getCanonicalTitle() {
            return this.canonicalTitle;
        }

        public final KitsuPosterImage getPosterImage() {
            return this.posterImage;
        }

        public final String getSynopsis() {
            return this.synopsis;
        }

        public final String getStartDate() {
            return this.startDate;
        }

        public final String getEndDate() {
            return this.endDate;
        }

        public final Integer getEpisodeCount() {
            return this.episodeCount;
        }

        public final Integer getEpisodeLength() {
            return this.episodeLength;
        }

        public final String getName() {
            return this.name;
        }

        public final String getLocation() {
            return this.location;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final KitsuUserAvatar getAvatar() {
            return this.avatar;
        }

        public final Integer getProgress() {
            return this.progress;
        }

        public final Integer getRatingTwenty() {
            return this.ratingTwenty;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final String getStatus() {
            return this.status;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships;", "", "anime", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAnime$annotations", "()V", "getAnime", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuRelationships {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("anime")
        private final KitsuRelationshipsAnime anime;

        public static /* synthetic */ KitsuRelationships copy$default(KitsuRelationships kitsuRelationships, KitsuRelationshipsAnime kitsuRelationshipsAnime, int i, Object obj) {
            if ((i & 1) != 0) {
                kitsuRelationshipsAnime = kitsuRelationships.anime;
            }
            return kitsuRelationships.copy(kitsuRelationshipsAnime);
        }

        @SerialName("anime")
        public static /* synthetic */ void getAnime$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final KitsuRelationshipsAnime getAnime() {
            return this.anime;
        }

        public final KitsuRelationships copy(@JsonProperty("anime") KitsuRelationshipsAnime anime) {
            return new KitsuRelationships(anime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof KitsuRelationships) && Intrinsics.areEqual(this.anime, ((KitsuRelationships) other).anime);
        }

        public int hashCode() {
            KitsuRelationshipsAnime kitsuRelationshipsAnime = this.anime;
            if (kitsuRelationshipsAnime == null) {
                return 0;
            }
            return kitsuRelationshipsAnime.hashCode();
        }

        public String toString() {
            return "KitsuRelationships(anime=" + this.anime + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationships;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuRelationships> serializer() {
                return KitsuApi$KitsuRelationships$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuRelationships(int i, KitsuRelationshipsAnime kitsuRelationshipsAnime, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, KitsuApi$KitsuRelationships$$serializer.INSTANCE.getDescriptor());
            }
            this.anime = kitsuRelationshipsAnime;
        }

        public KitsuRelationships(@JsonProperty("anime") KitsuRelationshipsAnime kitsuRelationshipsAnime) {
            this.anime = kitsuRelationshipsAnime;
        }

        public final KitsuRelationshipsAnime getAnime() {
            return this.anime;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime;", "", CloudStreamPackage.LINKS_EXTRA, "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLinks$annotations", "()V", "getLinks", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuRelationshipsAnime {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty(CloudStreamPackage.LINKS_EXTRA)
        private final KitsuLinks links;

        public static /* synthetic */ KitsuRelationshipsAnime copy$default(KitsuRelationshipsAnime kitsuRelationshipsAnime, KitsuLinks kitsuLinks, int i, Object obj) {
            if ((i & 1) != 0) {
                kitsuLinks = kitsuRelationshipsAnime.links;
            }
            return kitsuRelationshipsAnime.copy(kitsuLinks);
        }

        @SerialName(CloudStreamPackage.LINKS_EXTRA)
        public static /* synthetic */ void getLinks$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final KitsuLinks getLinks() {
            return this.links;
        }

        public final KitsuRelationshipsAnime copy(@JsonProperty(CloudStreamPackage.LINKS_EXTRA) KitsuLinks links) {
            return new KitsuRelationshipsAnime(links);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof KitsuRelationshipsAnime) && Intrinsics.areEqual(this.links, ((KitsuRelationshipsAnime) other).links);
        }

        public int hashCode() {
            KitsuLinks kitsuLinks = this.links;
            if (kitsuLinks == null) {
                return 0;
            }
            return kitsuLinks.hashCode();
        }

        public String toString() {
            return "KitsuRelationshipsAnime(links=" + this.links + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuRelationshipsAnime;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuRelationshipsAnime> serializer() {
                return KitsuApi$KitsuRelationshipsAnime$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuRelationshipsAnime(int i, KitsuLinks kitsuLinks, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, KitsuApi$KitsuRelationshipsAnime$$serializer.INSTANCE.getDescriptor());
            }
            this.links = kitsuLinks;
        }

        public KitsuRelationshipsAnime(@JsonProperty(CloudStreamPackage.LINKS_EXTRA) KitsuLinks kitsuLinks) {
            this.links = kitsuLinks;
        }

        public final KitsuLinks getLinks() {
            return this.links;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;", "", "large", "", "medium", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLarge$annotations", "()V", "getLarge", "()Ljava/lang/String;", "getMedium$annotations", "getMedium", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuPosterImage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ KitsuPosterImage copy$default(KitsuPosterImage kitsuPosterImage, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = kitsuPosterImage.large;
            }
            if ((i & 2) != 0) {
                str2 = kitsuPosterImage.medium;
            }
            return kitsuPosterImage.copy(str, str2);
        }

        @SerialName("large")
        public static /* synthetic */ void getLarge$annotations() {
        }

        @SerialName("medium")
        public static /* synthetic */ void getMedium$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLarge() {
            return this.large;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMedium() {
            return this.medium;
        }

        public final KitsuPosterImage copy(@JsonProperty("large") String large, @JsonProperty("medium") String medium) {
            return new KitsuPosterImage(large, medium);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuPosterImage)) {
                return false;
            }
            KitsuPosterImage kitsuPosterImage = (KitsuPosterImage) other;
            return Intrinsics.areEqual(this.large, kitsuPosterImage.large) && Intrinsics.areEqual(this.medium, kitsuPosterImage.medium);
        }

        public int hashCode() {
            String str = this.large;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.medium;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "KitsuPosterImage(large=" + this.large + ", medium=" + this.medium + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuPosterImage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuPosterImage> serializer() {
                return KitsuApi$KitsuPosterImage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuPosterImage(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, KitsuApi$KitsuPosterImage$$serializer.INSTANCE.getDescriptor());
            }
            this.large = str;
            this.medium = str2;
        }

        public KitsuPosterImage(@JsonProperty("large") String str, @JsonProperty("medium") String str2) {
            this.large = str;
            this.medium = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuPosterImage self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.large);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.medium);
        }

        public final String getLarge() {
            return this.large;
        }

        public final String getMedium() {
            return this.medium;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;", "", "enJp", "", "jaJp", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEnJp$annotations", "()V", "getEnJp", "()Ljava/lang/String;", "getJaJp$annotations", "getJaJp", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuTitles {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("en_jp")
        private final String enJp;

        @JsonProperty("ja_jp")
        private final String jaJp;

        public static /* synthetic */ KitsuTitles copy$default(KitsuTitles kitsuTitles, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = kitsuTitles.enJp;
            }
            if ((i & 2) != 0) {
                str2 = kitsuTitles.jaJp;
            }
            return kitsuTitles.copy(str, str2);
        }

        @SerialName("en_jp")
        public static /* synthetic */ void getEnJp$annotations() {
        }

        @SerialName("ja_jp")
        public static /* synthetic */ void getJaJp$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEnJp() {
            return this.enJp;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getJaJp() {
            return this.jaJp;
        }

        public final KitsuTitles copy(@JsonProperty("en_jp") String enJp, @JsonProperty("ja_jp") String jaJp) {
            return new KitsuTitles(enJp, jaJp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuTitles)) {
                return false;
            }
            KitsuTitles kitsuTitles = (KitsuTitles) other;
            return Intrinsics.areEqual(this.enJp, kitsuTitles.enJp) && Intrinsics.areEqual(this.jaJp, kitsuTitles.jaJp);
        }

        public int hashCode() {
            String str = this.enJp;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.jaJp;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "KitsuTitles(enJp=" + this.enJp + ", jaJp=" + this.jaJp + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuTitles;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuTitles> serializer() {
                return KitsuApi$KitsuTitles$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuTitles(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, KitsuApi$KitsuTitles$$serializer.INSTANCE.getDescriptor());
            }
            this.enJp = str;
            this.jaJp = str2;
        }

        public KitsuTitles(@JsonProperty("en_jp") String str, @JsonProperty("ja_jp") String str2) {
            this.enJp = str;
            this.jaJp = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuTitles self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.enJp);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.jaJp);
        }

        public final String getEnJp() {
            return this.enJp;
        }

        public final String getJaJp() {
            return this.jaJp;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;", "", "original", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getOriginal$annotations", "()V", "getOriginal", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuUserAvatar {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("original")
        private final String original;

        public static /* synthetic */ KitsuUserAvatar copy$default(KitsuUserAvatar kitsuUserAvatar, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = kitsuUserAvatar.original;
            }
            return kitsuUserAvatar.copy(str);
        }

        @SerialName("original")
        public static /* synthetic */ void getOriginal$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOriginal() {
            return this.original;
        }

        public final KitsuUserAvatar copy(@JsonProperty("original") String original) {
            return new KitsuUserAvatar(original);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof KitsuUserAvatar) && Intrinsics.areEqual(this.original, ((KitsuUserAvatar) other).original);
        }

        public int hashCode() {
            String str = this.original;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "KitsuUserAvatar(original=" + this.original + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuUserAvatar;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuUserAvatar> serializer() {
                return KitsuApi$KitsuUserAvatar$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuUserAvatar(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, KitsuApi$KitsuUserAvatar$$serializer.INSTANCE.getDescriptor());
            }
            this.original = str;
        }

        public KitsuUserAvatar(@JsonProperty("original") String str) {
            this.original = str;
        }

        public final String getOriginal() {
            return this.original;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B7\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;", "", "first", "", "next", "last", "related", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFirst$annotations", "()V", "getFirst", "()Ljava/lang/String;", "getNext$annotations", "getNext", "getLast$annotations", "getLast", "getRelated$annotations", "getRelated", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuLinks {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("first")
        private final String first;

        @JsonProperty("last")
        private final String last;

        @JsonProperty("next")
        private final String next;

        @JsonProperty("related")
        private final String related;

        public static /* synthetic */ KitsuLinks copy$default(KitsuLinks kitsuLinks, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = kitsuLinks.first;
            }
            if ((i & 2) != 0) {
                str2 = kitsuLinks.next;
            }
            if ((i & 4) != 0) {
                str3 = kitsuLinks.last;
            }
            if ((i & 8) != 0) {
                str4 = kitsuLinks.related;
            }
            return kitsuLinks.copy(str, str2, str3, str4);
        }

        @SerialName("first")
        public static /* synthetic */ void getFirst$annotations() {
        }

        @SerialName("last")
        public static /* synthetic */ void getLast$annotations() {
        }

        @SerialName("next")
        public static /* synthetic */ void getNext$annotations() {
        }

        @SerialName("related")
        public static /* synthetic */ void getRelated$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFirst() {
            return this.first;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getNext() {
            return this.next;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLast() {
            return this.last;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRelated() {
            return this.related;
        }

        public final KitsuLinks copy(@JsonProperty("first") String first, @JsonProperty("next") String next, @JsonProperty("last") String last, @JsonProperty("related") String related) {
            return new KitsuLinks(first, next, last, related);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuLinks)) {
                return false;
            }
            KitsuLinks kitsuLinks = (KitsuLinks) other;
            return Intrinsics.areEqual(this.first, kitsuLinks.first) && Intrinsics.areEqual(this.next, kitsuLinks.next) && Intrinsics.areEqual(this.last, kitsuLinks.last) && Intrinsics.areEqual(this.related, kitsuLinks.related);
        }

        public int hashCode() {
            String str = this.first;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.next;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.last;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.related;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "KitsuLinks(first=" + this.first + ", next=" + this.next + ", last=" + this.last + ", related=" + this.related + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuLinks> serializer() {
                return KitsuApi$KitsuLinks$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuLinks(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, KitsuApi$KitsuLinks$$serializer.INSTANCE.getDescriptor());
            }
            this.first = str;
            this.next = str2;
            this.last = str3;
            this.related = str4;
        }

        public KitsuLinks(@JsonProperty("first") String str, @JsonProperty("next") String str2, @JsonProperty("last") String str3, @JsonProperty("related") String str4) {
            this.first = str;
            this.next = str2;
            this.last = str3;
            this.related = str4;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuLinks self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.first);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.next);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.last);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.related);
        }

        public final String getFirst() {
            return this.first;
        }

        public final String getNext() {
            return this.next;
        }

        public final String getLast() {
            return this.last;
        }

        public final String getRelated() {
            return this.related;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B5\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0001\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nBE\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0003J7\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\fHÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0016¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuResponse;", "", CloudStreamPackage.LINKS_EXTRA, "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;", "data", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuNode;", "included", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuAnimeData;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLinks$annotations", "()V", "getLinks", "()Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuLinks;", "getData$annotations", "getData", "()Ljava/util/List;", "getIncluded$annotations", "getIncluded", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class KitsuResponse {

        @JsonProperty("data")
        private final List<KitsuNode> data;

        @JsonProperty("included")
        private final List<KitsuAnimeData> included;

        @JsonProperty(CloudStreamPackage.LINKS_EXTRA)
        private final KitsuLinks links;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$KitsuResponse$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return KitsuApi.KitsuResponse._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.KitsuApi$KitsuResponse$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return KitsuApi.KitsuResponse._childSerializers$_anonymous_$0();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(KitsuApi$KitsuNode$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(KitsuApi$KitsuAnimeData$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ KitsuResponse copy$default(KitsuResponse kitsuResponse, KitsuLinks kitsuLinks, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                kitsuLinks = kitsuResponse.links;
            }
            if ((i & 2) != 0) {
                list = kitsuResponse.data;
            }
            if ((i & 4) != 0) {
                list2 = kitsuResponse.included;
            }
            return kitsuResponse.copy(kitsuLinks, list, list2);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        @SerialName("included")
        public static /* synthetic */ void getIncluded$annotations() {
        }

        @SerialName(CloudStreamPackage.LINKS_EXTRA)
        public static /* synthetic */ void getLinks$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final KitsuLinks getLinks() {
            return this.links;
        }

        public final List<KitsuNode> component2() {
            return this.data;
        }

        public final List<KitsuAnimeData> component3() {
            return this.included;
        }

        public final KitsuResponse copy(@JsonProperty(CloudStreamPackage.LINKS_EXTRA) KitsuLinks links, @JsonProperty("data") List<KitsuNode> data, @JsonProperty("included") List<KitsuAnimeData> included) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new KitsuResponse(links, data, included);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof KitsuResponse)) {
                return false;
            }
            KitsuResponse kitsuResponse = (KitsuResponse) other;
            return Intrinsics.areEqual(this.links, kitsuResponse.links) && Intrinsics.areEqual(this.data, kitsuResponse.data) && Intrinsics.areEqual(this.included, kitsuResponse.included);
        }

        public int hashCode() {
            KitsuLinks kitsuLinks = this.links;
            int iHashCode = (((kitsuLinks == null ? 0 : kitsuLinks.hashCode()) * 31) + this.data.hashCode()) * 31;
            List<KitsuAnimeData> list = this.included;
            return iHashCode + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "KitsuResponse(links=" + this.links + ", data=" + this.data + ", included=" + this.included + ')';
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$KitsuResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<KitsuResponse> serializer() {
                return KitsuApi$KitsuResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ KitsuResponse(int i, KitsuLinks kitsuLinks, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, KitsuApi$KitsuResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.links = kitsuLinks;
            this.data = list;
            this.included = list2;
        }

        public KitsuResponse(@JsonProperty(CloudStreamPackage.LINKS_EXTRA) KitsuLinks kitsuLinks, @JsonProperty("data") List<KitsuNode> data, @JsonProperty("included") List<KitsuAnimeData> list) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.links = kitsuLinks;
            this.data = data;
            this.included = list;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(KitsuResponse self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, KitsuApi$KitsuLinks$$serializer.INSTANCE, self.links);
            output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.data);
            output.encodeNullableSerializableElement(serialDesc, 2, lazyArr[2].getValue(), self.included);
        }

        public final KitsuLinks getLinks() {
            return this.links;
        }

        public final List<KitsuNode> getData() {
            return this.data;
        }

        public final List<KitsuAnimeData> getIncluded() {
            return this.included;
        }
    }

    /* JADX INFO: compiled from: KitsuApi.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010\tJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$Companion;", "", "<init>", "()V", "KITSU_CACHED_LIST", "", "parseDateLong", "", TypedValues.Custom.S_STRING, "(Ljava/lang/String;)Ljava/lang/Long;", "kitsuStatusAsString", "", "[Ljava/lang/String;", "fromIntToAnimeStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$Companion$KitsuStatusType;", "inp", "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "convertToStatus", "KitsuStatusType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[SyncWatchType.values().length];
                try {
                    iArr[SyncWatchType.NONE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[SyncWatchType.WATCHING.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[SyncWatchType.COMPLETED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[SyncWatchType.ONHOLD.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[SyncWatchType.DROPPED.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[SyncWatchType.PLANTOWATCH.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[SyncWatchType.REWATCHING.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Long parseDateLong(String string) {
            Date date;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
                if (string == null || (date = simpleDateFormat.parse(string)) == null) {
                    return null;
                }
                return Long.valueOf(date.getTime() / 1000);
            } catch (Exception unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final KitsuStatusType fromIntToAnimeStatus(SyncWatchType inp) {
            switch (WhenMappings.$EnumSwitchMapping$0[inp.ordinal()]) {
                case 1:
                    return KitsuStatusType.None;
                case 2:
                    return KitsuStatusType.Watching;
                case 3:
                    return KitsuStatusType.Completed;
                case 4:
                    return KitsuStatusType.OnHold;
                case 5:
                    return KitsuStatusType.Dropped;
                case 6:
                    return KitsuStatusType.PlanToWatch;
                case 7:
                    return KitsuStatusType.Watching;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        /* JADX INFO: compiled from: KitsuApi.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/KitsuApi$Companion$KitsuStatusType;", "", ES6Iterator.VALUE_PROPERTY, "", "stringRes", "<init>", "(Ljava/lang/String;III)V", "getValue", "()I", "setValue", "(I)V", "getStringRes", "Watching", "Completed", "OnHold", "Dropped", "PlanToWatch", "None", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum KitsuStatusType {
            Watching(0, R.string.type_watching),
            Completed(1, R.string.type_completed),
            OnHold(2, R.string.type_on_hold),
            Dropped(3, R.string.type_dropped),
            PlanToWatch(4, R.string.type_plan_to_watch),
            None(-1, R.string.type_none);

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
            private final int stringRes;
            private int value;

            public static EnumEntries<KitsuStatusType> getEntries() {
                return $ENTRIES;
            }

            KitsuStatusType(int i, int i2) {
                this.value = i;
                this.stringRes = i2;
            }

            public final int getStringRes() {
                return this.stringRes;
            }

            public final int getValue() {
                return this.value;
            }

            public final void setValue(int i) {
                this.value = i;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final KitsuStatusType convertToStatus(String string) {
            switch (string.hashCode()) {
                case -1402931637:
                    if (string.equals("completed")) {
                        return KitsuStatusType.Completed;
                    }
                    break;
                case -1326157025:
                    if (string.equals("on_hold")) {
                        return KitsuStatusType.OnHold;
                    }
                    break;
                case -493887036:
                    if (string.equals("planned")) {
                        return KitsuStatusType.PlanToWatch;
                    }
                    break;
                case 1126940025:
                    if (string.equals("current")) {
                        return KitsuStatusType.Watching;
                    }
                    break;
                case 1925736384:
                    if (string.equals("dropped")) {
                        return KitsuStatusType.Dropped;
                    }
                    break;
            }
            return KitsuStatusType.None;
        }
    }
}
