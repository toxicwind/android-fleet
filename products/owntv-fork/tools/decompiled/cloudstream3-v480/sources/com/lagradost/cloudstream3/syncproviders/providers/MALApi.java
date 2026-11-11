package com.lagradost.cloudstream3.syncproviders.providers;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.BuildConfig;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AuthAPI;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthLoginPage;
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
import com.uwetrottmann.tmdb2.TmdbHelper;
import j$.time.Instant;
import j$.time.format.DateTimeFormatter;
import j$.util.DesugarDate;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;
import org.mozilla.universalchardet.prober.HebrewProber;

/* JADX INFO: compiled from: MALApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#\u0018\u0000 g2\u00020\u0001:\u001e_`abcdefghijklmnopqrstuvwxyz{|B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010)J\u001a\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010&H\u0096@¢\u0006\u0002\u0010-J(\u0010.\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010/2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00104J\u0012\u00105\u001a\u0004\u0018\u00010\u00052\u0006\u00106\u001a\u00020\u0005H\u0016J(\u00107\u001a\u00020\u000f2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020:H\u0096@¢\u0006\u0002\u0010;J\u0019\u0010<\u001a\u0004\u0018\u00010=2\b\u0010>\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0002\u0010?J\u0014\u0010@\u001a\u0004\u0018\u0001002\b\u0010A\u001a\u0004\u0018\u00010BH\u0002J\"\u0010C\u001a\u0004\u0018\u00010D2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00108\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00104J\"\u0010E\u001a\u0004\u0018\u00010:2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00108\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00104J\n\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u0018\u0010H\u001a\u0004\u0018\u00010&2\u0006\u0010,\u001a\u00020&H\u0096@¢\u0006\u0002\u0010-J\u001a\u0010N\u001a\u0004\u0018\u00010O2\b\u00101\u001a\u0004\u0018\u000102H\u0096@¢\u0006\u0002\u0010PJ\u001e\u0010Q\u001a\n\u0012\u0004\u0012\u00020S\u0018\u00010R2\u0006\u00101\u001a\u000202H\u0082@¢\u0006\u0002\u0010PJ\u001c\u0010T\u001a\b\u0012\u0004\u0012\u00020S0R2\u0006\u0010,\u001a\u00020&H\u0082@¢\u0006\u0002\u0010-J \u0010U\u001a\u00020V2\u0006\u0010,\u001a\u00020&2\b\b\u0002\u0010W\u001a\u00020\u0017H\u0082@¢\u0006\u0002\u0010XJB\u0010Y\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020&2\u0006\u00108\u001a\u00020\u00172\n\b\u0002\u0010E\u001a\u0004\u0018\u00010Z2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0002\u0010]JD\u0010Y\u001a\u0004\u0018\u00010\u00052\u0006\u0010,\u001a\u00020&2\u0006\u00108\u001a\u00020\u00172\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\u0017H\u0082@¢\u0006\u0002\u0010^R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0014\u0010\u0014\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010I\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010J\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020L0Kj\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020L`MX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "idPrefix", "getIdPrefix", "key", "apiUrl", "hasOAuth2", "", "getHasOAuth2", "()Z", "redirectUrlIdentifier", "getRedirectUrlIdentifier", "mainUrl", "getMainUrl", "icon", "", "getIcon", "()Ljava/lang/Integer;", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "createAccountUrl", "getCreateAccountUrl", "supportedWatchTypes", "", "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "getSupportedWatchTypes", "()Ljava/util/Set;", "login", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "redirectUrl", "payload", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlToId", ImagesContract.URL, "updateStatus", "id", "newStatus", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseDate", "", TypedValues.Custom.S_STRING, "(Ljava/lang/String;)Ljava/lang/Long;", "toSearchResult", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "load", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", NotificationCompat.CATEGORY_STATUS, "loginRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginPage;", "refreshToken", "requestIdCounter", "allTitles", "Ljava/util/HashMap;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalTitleHolder;", "Lkotlin/collections/HashMap;", "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMalAnimeListSmart", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Data;", "getMalAnimeList", "getMalAnimeListSlice", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalList;", TypedValues.CycleType.S_WAVE_OFFSET, "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setScoreRequest", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Companion$MalStatusType;", "score", "numWatchedEpisodes", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Companion$MalStatusType;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Payload", "MalAnime", "Recommendations", "Studios", "MyListStatus", "RelatedAnime", "Status", "Statistics", "Companion", "MalList", "MainPicture", "Node", "ListStatus", "Data", "Paging", "AlternativeTitles", "Genres", "StartSeason", "Broadcast", "ResponseToken", "MalRoot", "MalDatum", "MalNode", "MalStatus", "MalUser", "MalMainPicture", "SmallMalAnime", "MalSearchNode", "MalSearch", "MalTitleHolder", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MALApi extends SyncAPI {
    public static final String MAL_CACHED_LIST = "mal_cached_list";
    private int requestIdCounter;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] malStatusAsString = {"watching", "completed", "on_hold", "dropped", "plan_to_watch"};
    private String name = "MAL";
    private final String idPrefix = "mal";
    private final String key = BuildConfig.MAL_KEY;
    private final String apiUrl = "https://api.myanimelist.net";
    private final boolean hasOAuth2 = true;
    private final String redirectUrlIdentifier = "mallogin";
    private final String mainUrl = "https://myanimelist.net";
    private final int icon = R.drawable.mal_logo;
    private final SyncIdName syncIdName = SyncIdName.MyAnimeList;
    private final String createAccountUrl = getMainUrl() + "/register.php";
    private final Set<SyncWatchType> supportedWatchTypes = SetsKt.setOf((Object[]) new SyncWatchType[]{SyncWatchType.WATCHING, SyncWatchType.COMPLETED, SyncWatchType.PLANTOWATCH, SyncWatchType.DROPPED, SyncWatchType.ONHOLD, SyncWatchType.NONE});
    private final HashMap<Integer, MalTitleHolder> allTitles = new HashMap<>();

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeList$1, reason: invalid class name */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0}, l = {539}, m = "getMalAnimeList", n = {"token", "fullList", "offsetRegex", TypedValues.CycleType.S_WAVE_OFFSET}, nl = {540}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return MALApi.this.getMalAnimeList(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSlice$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0}, l = {553}, m = "getMalAnimeListSlice", n = {"token", "user", ImagesContract.URL, TypedValues.CycleType.S_WAVE_OFFSET}, nl = {557}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C07961 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C07961(Continuation<? super C07961> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.getMalAnimeListSlice(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSmart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0}, l = {526}, m = "getMalAnimeListSmart", n = {"auth"}, nl = {527}, s = {"L$0"}, v = 2)
    static final class C07971 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07971(Continuation<? super C07971> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.getMalAnimeListSmart(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$library$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0}, l = {497}, m = "library", n = {"auth"}, nl = {690}, s = {"L$0"}, v = 2)
    static final class C07981 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07981(Continuation<? super C07981> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.library(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0, 0}, l = {HebrewProber.FINAL_TSADI}, m = "load", n = {"auth", "id", "auth", ImagesContract.URL, "internalId"}, nl = {249}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
    static final class C07991 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C07991(Continuation<? super C07991> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.load(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$login$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0, 0, 0}, l = {75}, m = "login", n = {"redirectUrl", "payload", "payloadData", "sanitizer", "state", "currentCode"}, nl = {83}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 2)
    static final class C08001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C08001(Continuation<? super C08001> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.login(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$refreshToken$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0}, l = {366}, m = "refreshToken", n = {"token"}, nl = {373}, s = {"L$0"}, v = 2)
    static final class C08011 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08011(Continuation<? super C08011> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.refreshToken(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0}, l = {108}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY, "auth", ImagesContract.URL}, nl = {112}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C08021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C08021(Continuation<? super C08021> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.search(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$setScoreRequest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0, 0}, l = {568}, m = "setScoreRequest", n = {"token", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "id"}, nl = {576}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
    static final class C08031 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C08031(Continuation<? super C08031> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.setScoreRequest((AuthToken) null, 0, (Companion.MalStatusType) null, (Integer) null, (Integer) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$setScoreRequest$2, reason: invalid class name */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0, 0, 0}, l = {TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR}, m = "setScoreRequest", n = {"token", NotificationCompat.CATEGORY_STATUS, "score", "numWatchedEpisodes", "data", "id"}, nl = {TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 2)
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
            return MALApi.this.setScoreRequest((AuthToken) null, 0, (String) null, (Integer) null, (Integer) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$status$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0, 0, 0, 0}, l = {291}, m = NotificationCompat.CATEGORY_STATUS, n = {"auth", "id", "auth", ImagesContract.URL}, nl = {295}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C08041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C08041(Continuation<? super C08041> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.status(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.MALApi$user$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.MALApi", f = "MALApi.kt", i = {0}, l = {92}, m = "user", n = {"token"}, nl = {97}, s = {"L$0"}, v = 2)
    static final class C08051 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08051(Continuation<? super C08051> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MALApi.this.user(null, this);
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
    public boolean getHasOAuth2() {
        return this.hasOAuth2;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getRedirectUrlIdentifier() {
        return this.redirectUrlIdentifier;
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

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Payload;", "", "requestId", "", "codeVerifier", "", "<init>", "(ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRequestId$annotations", "()V", "getRequestId", "()I", "getCodeVerifier$annotations", "getCodeVerifier", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Payload {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("codeVerifier")
        private final String codeVerifier;

        @JsonProperty("requestId")
        private final int requestId;

        public static /* synthetic */ Payload copy$default(Payload payload, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = payload.requestId;
            }
            if ((i2 & 2) != 0) {
                str = payload.codeVerifier;
            }
            return payload.copy(i, str);
        }

        @SerialName("codeVerifier")
        public static /* synthetic */ void getCodeVerifier$annotations() {
        }

        @SerialName("requestId")
        public static /* synthetic */ void getRequestId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRequestId() {
            return this.requestId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCodeVerifier() {
            return this.codeVerifier;
        }

        public final Payload copy(@JsonProperty("requestId") int requestId, @JsonProperty("codeVerifier") String codeVerifier) {
            Intrinsics.checkNotNullParameter(codeVerifier, "codeVerifier");
            return new Payload(requestId, codeVerifier);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Payload)) {
                return false;
            }
            Payload payload = (Payload) other;
            return this.requestId == payload.requestId && Intrinsics.areEqual(this.codeVerifier, payload.codeVerifier);
        }

        public int hashCode() {
            return (this.requestId * 31) + this.codeVerifier.hashCode();
        }

        public String toString() {
            return "Payload(requestId=" + this.requestId + ", codeVerifier=" + this.codeVerifier + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Payload$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Payload;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Payload> serializer() {
                return MALApi$Payload$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Payload(int i, int i2, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$Payload$$serializer.INSTANCE.getDescriptor());
            }
            this.requestId = i2;
            this.codeVerifier = str;
        }

        public Payload(@JsonProperty("requestId") int i, @JsonProperty("codeVerifier") String codeVerifier) {
            Intrinsics.checkNotNullParameter(codeVerifier, "codeVerifier");
            this.requestId = i;
            this.codeVerifier = codeVerifier;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Payload self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.requestId);
            output.encodeStringElement(serialDesc, 1, self.codeVerifier);
        }

        public final int getRequestId() {
            return this.requestId;
        }

        public final String getCodeVerifier() {
            return this.codeVerifier;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(String str, String str2, Continuation<? super AuthToken> continuation) throws MalformedURLException, JsonProcessingException {
        C08001 c08001;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof C08001) {
            c08001 = (C08001) continuation;
            if ((c08001.label & Integer.MIN_VALUE) != 0) {
                c08001.label -= Integer.MIN_VALUE;
            } else {
                c08001 = new C08001(continuation);
            }
        } else {
            c08001 = new C08001(continuation);
        }
        Object objPost$default = c08001.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08001.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objPost$default);
            AppUtils appUtils = AppUtils.INSTANCE;
            Intrinsics.checkNotNull(str2);
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(Payload.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Payload.class), null, 2, null));
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
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<Payload>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$login$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<Payload>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$login$$inlined$parseJson$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<Payload>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$login$$inlined$parseJson$1
                });
            }
            Payload payload = (Payload) objDecodeFromString;
            Map<String, String> mapSplitRedirectUrl = AuthAPI.INSTANCE.splitRedirectUrl(str);
            String str3 = mapSplitRedirectUrl.get("state");
            Intrinsics.checkNotNull(str3);
            String str4 = str3;
            if (!Intrinsics.areEqual(str4, "RequestID" + payload.getRequestId())) {
                return null;
            }
            String str5 = mapSplitRedirectUrl.get("code");
            Intrinsics.checkNotNull(str5);
            String str6 = str5;
            Requests app2 = MainActivityKt.getApp();
            String str7 = getMainUrl() + "/v1/oauth2/token";
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("client_id", this.key), TuplesKt.to("code", str6), TuplesKt.to("code_verifier", payload.getCodeVerifier()), TuplesKt.to("grant_type", "authorization_code"));
            c08001.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c08001.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c08001.L$2 = SpillingKt.nullOutSpilledVariable(payload);
            c08001.L$3 = SpillingKt.nullOutSpilledVariable(mapSplitRedirectUrl);
            c08001.L$4 = SpillingKt.nullOutSpilledVariable(str4);
            c08001.L$5 = SpillingKt.nullOutSpilledVariable(str6);
            c08001.label = 1;
            objPost$default = Requests.post$default(app2, str7, null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c08001, 65502, null);
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
    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) {
        C08051 c08051;
        String accessToken;
        if (continuation instanceof C08051) {
            c08051 = (C08051) continuation;
            if ((c08051.label & Integer.MIN_VALUE) != 0) {
                c08051.label -= Integer.MIN_VALUE;
            } else {
                c08051 = new C08051(continuation);
            }
        } else {
            c08051 = new C08051(continuation);
        }
        Object obj = c08051.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08051.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = this.apiUrl + "/v2/users/@me";
            StringBuilder sb = new StringBuilder("Bearer ");
            if (authToken == null || (accessToken = authToken.getAccessToken()) == null) {
                return null;
            }
            sb.append(accessToken);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", sb.toString()));
            c08051.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c08051.label = 1;
            Object obj2 = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c08051, 4028, null);
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
        MalUser malUser = (MalUser) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(MalUser.class));
        return new AuthUser(malUser.getName(), malUser.getId(), malUser.getPicture(), (Map) null, 8, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object search(AuthData authData, String str, Continuation<? super List<SyncAPI.SyncSearchResult>> continuation) {
        C08021 c08021;
        AuthToken token;
        String accessToken;
        String medium;
        if (continuation instanceof C08021) {
            c08021 = (C08021) continuation;
            if ((c08021.label & Integer.MIN_VALUE) != 0) {
                c08021.label -= Integer.MIN_VALUE;
            } else {
                c08021 = new C08021(continuation);
            }
        } else {
            c08021 = new C08021(continuation);
        }
        Object obj = c08021.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08021.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (token = authData.getToken()) == null || (accessToken = token.getAccessToken()) == null) {
                return null;
            }
            String str2 = this.apiUrl + "/v2/anime?q=" + str + "&limit=25";
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + accessToken));
            c08021.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08021.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c08021.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c08021.L$3 = SpillingKt.nullOutSpilledVariable(str2);
            c08021.label = 1;
            Object obj2 = Requests.get$default(app2, str2, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c08021, 4028, null);
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
        List<MalSearchNode> data = ((MalSearch) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(MalSearch.class))).getData();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(data, 10));
        Iterator<T> it = data.iterator();
        while (it.hasNext()) {
            Node node = ((MalSearchNode) it.next()).getNode();
            String title = node.getTitle();
            String name = getName();
            String strValueOf = String.valueOf(node.getId());
            String str3 = getMainUrl() + "/anime/" + node.getId() + '/';
            MainPicture mainPicture = node.getMainPicture();
            if (mainPicture == null || (medium = mainPicture.getLarge()) == null) {
                MainPicture mainPicture2 = node.getMainPicture();
                medium = mainPicture2 != null ? mainPicture2.getMedium() : null;
            }
            arrayList.add(new SyncAPI.SyncSearchResult(title, name, strValueOf, str3, medium, null, null, null, null, null, 992, null));
        }
        return arrayList;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public String urlToId(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        MatchResult matchResultFind$default = Regex.find$default(new Regex("/anime/((.*)/|(.*))"), url, 0, 2, null);
        Intrinsics.checkNotNull(matchResultFind$default);
        return (String) CollectionsKt.first((List) matchResultFind$default.getGroupValues());
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object updateStatus(AuthData authData, String str, SyncAPI.AbstractSyncStatus abstractSyncStatus, Continuation<? super Boolean> continuation) {
        AuthToken token;
        if (authData == null || (token = authData.getToken()) == null) {
            return Boxing.boxBoolean(false);
        }
        Integer intOrNull = StringsKt.toIntOrNull(str);
        if (intOrNull == null) {
            return Boxing.boxBoolean(false);
        }
        int iIntValue = intOrNull.intValue();
        Companion.MalStatusType malStatusTypeFromIntToAnimeStatus = INSTANCE.fromIntToAnimeStatus(abstractSyncStatus.getStatus());
        Score score = abstractSyncStatus.getScore();
        return setScoreRequest(token, iIntValue, malStatusTypeFromIntToAnimeStatus, score != null ? Boxing.boxInt(score.toInt(10)) : null, abstractSyncStatus.getWatchedEpisodes(), continuation);
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\bq\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 µ\u00012\u00020\u0001:\u0004´\u0001µ\u0001Bó\u0003\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0001\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u001b\u0012\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0001\u0010#\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010%\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0001\u0010&\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u001b\u0012\n\b\u0001\u0010'\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0001\u0010(\u001a\u0016\u0012\u0004\u0012\u00020)\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020)\u0018\u0001`\u001b\u0012\u001c\b\u0001\u0010*\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u001b\u0012\u001c\b\u0001\u0010+\u001a\u0016\u0012\u0004\u0012\u00020,\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020,\u0018\u0001`\u001b\u0012\u001c\b\u0001\u0010-\u001a\u0016\u0012\u0004\u0012\u00020.\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020.\u0018\u0001`\u001b\u0012\n\b\u0001\u0010/\u001a\u0004\u0018\u000100¢\u0006\u0004\b1\u00102B\u0087\u0003\b\u0010\u0012\u0006\u00103\u001a\u00020\u0003\u0012\u0006\u00104\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\b\u0010!\u001a\u0004\u0018\u00010\"\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010%\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0019\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u0019\u0012\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0019\u0012\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020,\u0018\u00010\u0019\u0012\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u0019\u0012\b\u0010/\u001a\u0004\u0018\u000100\u0012\b\u00105\u001a\u0004\u0018\u000106¢\u0006\u0004\b1\u00107J\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\tHÆ\u0003J\f\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010NJ\u0011\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\u0011\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\u0011\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\u0011\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\f\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001e\u0010\u0096\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u001bHÆ\u0003J\f\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u001dHÆ\u0003J\u0011\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\f\u0010\u0099\u0001\u001a\u0004\u0018\u00010 HÆ\u0003J\f\u0010\u009a\u0001\u001a\u0004\u0018\u00010\"HÆ\u0003J\f\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010;J\f\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001e\u0010\u009e\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u001bHÆ\u0003J\f\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001e\u0010 \u0001\u001a\u0016\u0012\u0004\u0012\u00020)\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020)\u0018\u0001`\u001bHÆ\u0003J\u001e\u0010¡\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u001bHÆ\u0003J\u001e\u0010¢\u0001\u001a\u0016\u0012\u0004\u0012\u00020,\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020,\u0018\u0001`\u001bHÆ\u0003J\u001e\u0010£\u0001\u001a\u0016\u0012\u0004\u0012\u00020.\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020.\u0018\u0001`\u001bHÆ\u0003J\f\u0010¤\u0001\u001a\u0004\u0018\u000100HÆ\u0003Jü\u0003\u0010¥\u0001\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u001c\b\u0003\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u001b2\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0003\u0010#\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010$\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u00052\u001c\b\u0003\u0010&\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u001b2\n\b\u0003\u0010'\u001a\u0004\u0018\u00010\u00052\u001c\b\u0003\u0010(\u001a\u0016\u0012\u0004\u0012\u00020)\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020)\u0018\u0001`\u001b2\u001c\b\u0003\u0010*\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u001b2\u001c\b\u0003\u0010+\u001a\u0016\u0012\u0004\u0012\u00020,\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020,\u0018\u0001`\u001b2\u001c\b\u0003\u0010-\u001a\u0016\u0012\u0004\u0012\u00020.\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020.\u0018\u0001`\u001b2\n\b\u0003\u0010/\u001a\u0004\u0018\u000100HÆ\u0001¢\u0006\u0003\u0010¦\u0001J\u0017\u0010§\u0001\u001a\u00030¨\u00012\t\u0010©\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010ª\u0001\u001a\u00020\u0003HÖ\u0081\u0004J\u000b\u0010«\u0001\u001a\u00020\u0005HÖ\u0081\u0004J-\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0007\u0010®\u0001\u001a\u00020\u00002\b\u0010¯\u0001\u001a\u00030°\u00012\b\u0010±\u0001\u001a\u00030²\u0001H\u0001¢\u0006\u0003\b³\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b=\u00109\u001a\u0004\b>\u0010?R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b@\u00109\u001a\u0004\bA\u0010BR\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bC\u00109\u001a\u0004\bD\u0010ER\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bF\u00109\u001a\u0004\bG\u0010?R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bH\u00109\u001a\u0004\bI\u0010?R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bJ\u00109\u001a\u0004\bK\u0010?R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010O\u0012\u0004\bL\u00109\u001a\u0004\bM\u0010NR \u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\bP\u00109\u001a\u0004\bQ\u0010;R \u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\bR\u00109\u001a\u0004\bS\u0010;R \u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\bT\u00109\u001a\u0004\bU\u0010;R \u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\bV\u00109\u001a\u0004\bW\u0010;R\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bX\u00109\u001a\u0004\bY\u0010?R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bZ\u00109\u001a\u0004\b[\u0010?R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\\\u00109\u001a\u0004\b]\u0010?R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b^\u00109\u001a\u0004\b_\u0010?R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b`\u00109\u001a\u0004\ba\u0010?R0\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u001a\u0018\u0001`\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bb\u00109\u001a\u0004\bc\u0010dR\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u001d8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\be\u00109\u001a\u0004\bf\u0010gR \u0010\u001e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\bh\u00109\u001a\u0004\bi\u0010;R\u001e\u0010\u001f\u001a\u0004\u0018\u00010 8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bj\u00109\u001a\u0004\bk\u0010lR\u001e\u0010!\u001a\u0004\u0018\u00010\"8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bm\u00109\u001a\u0004\bn\u0010oR\u001e\u0010#\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bp\u00109\u001a\u0004\bq\u0010?R \u0010$\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010<\u0012\u0004\br\u00109\u001a\u0004\bs\u0010;R\u001e\u0010%\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bt\u00109\u001a\u0004\bu\u0010?R0\u0010&\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bv\u00109\u001a\u0004\bw\u0010dR\u001e\u0010'\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bx\u00109\u001a\u0004\by\u0010?R0\u0010(\u001a\u0016\u0012\u0004\u0012\u00020)\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020)\u0018\u0001`\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bz\u00109\u001a\u0004\b{\u0010dR0\u0010*\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001`\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b|\u00109\u001a\u0004\b}\u0010dR0\u0010+\u001a\u0016\u0012\u0004\u0012\u00020,\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020,\u0018\u0001`\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b~\u00109\u001a\u0004\b\u007f\u0010dR2\u0010-\u001a\u0016\u0012\u0004\u0012\u00020.\u0018\u00010\u0019j\n\u0012\u0004\u0012\u00020.\u0018\u0001`\u001b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0000\u0012\u0005\b\u0080\u0001\u00109\u001a\u0005\b\u0081\u0001\u0010dR!\u0010/\u001a\u0004\u0018\u0001008\u0006X\u0087\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b\u0082\u0001\u00109\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001¨\u0006¶\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalAnime;", "", "id", "", "title", "", "mainPicture", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;", "alternativeTitles", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;", "startDate", "endDate", "synopsis", "mean", "", "rank", "popularity", "numListUsers", "numScoringUsers", "nsfw", "createdAt", "updatedAt", "mediaType", NotificationCompat.CATEGORY_STATUS, "genres", "Ljava/util/ArrayList;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Genres;", "Lkotlin/collections/ArrayList;", "myListStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;", "numEpisodes", "startSeason", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;", "broadcast", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;", "source", "averageEpisodeDuration", "rating", "pictures", "background", "relatedAnime", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$RelatedAnime;", "relatedManga", "recommendations", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Recommendations;", "studios", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Studios;", "statistics", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;)V", "seen0", "seen1", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "getMainPicture$annotations", "getMainPicture", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;", "getAlternativeTitles$annotations", "getAlternativeTitles", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;", "getStartDate$annotations", "getStartDate", "getEndDate$annotations", "getEndDate", "getSynopsis$annotations", "getSynopsis", "getMean$annotations", "getMean", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getRank$annotations", "getRank", "getPopularity$annotations", "getPopularity", "getNumListUsers$annotations", "getNumListUsers", "getNumScoringUsers$annotations", "getNumScoringUsers", "getNsfw$annotations", "getNsfw", "getCreatedAt$annotations", "getCreatedAt", "getUpdatedAt$annotations", "getUpdatedAt", "getMediaType$annotations", "getMediaType", "getStatus$annotations", "getStatus", "getGenres$annotations", "getGenres", "()Ljava/util/ArrayList;", "getMyListStatus$annotations", "getMyListStatus", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;", "getNumEpisodes$annotations", "getNumEpisodes", "getStartSeason$annotations", "getStartSeason", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;", "getBroadcast$annotations", "getBroadcast", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;", "getSource$annotations", "getSource", "getAverageEpisodeDuration$annotations", "getAverageEpisodeDuration", "getRating$annotations", "getRating", "getPictures$annotations", "getPictures", "getBackground$annotations", "getBackground", "getRelatedAnime$annotations", "getRelatedAnime", "getRelatedManga$annotations", "getRelatedManga", "getRecommendations$annotations", "getRecommendations", "getStudios$annotations", "getStudios", "getStatistics$annotations", "getStatistics", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;)Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalAnime;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalAnime {

        @JsonProperty("alternative_titles")
        private final AlternativeTitles alternativeTitles;

        @JsonProperty("average_episode_duration")
        private final Integer averageEpisodeDuration;

        @JsonProperty("background")
        private final String background;

        @JsonProperty("broadcast")
        private final Broadcast broadcast;

        @JsonProperty("created_at")
        private final String createdAt;

        @JsonProperty("end_date")
        private final String endDate;

        @JsonProperty("genres")
        private final ArrayList<Genres> genres;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("main_picture")
        private final MainPicture mainPicture;

        @JsonProperty("mean")
        private final Double mean;

        @JsonProperty("media_type")
        private final String mediaType;

        @JsonProperty("my_list_status")
        private final MyListStatus myListStatus;

        @JsonProperty("nsfw")
        private final String nsfw;

        @JsonProperty("num_episodes")
        private final Integer numEpisodes;

        @JsonProperty("num_list_users")
        private final Integer numListUsers;

        @JsonProperty("num_scoring_users")
        private final Integer numScoringUsers;

        @JsonProperty("pictures")
        private final ArrayList<MainPicture> pictures;

        @JsonProperty("popularity")
        private final Integer popularity;

        @JsonProperty("rank")
        private final Integer rank;

        @JsonProperty("rating")
        private final String rating;

        @JsonProperty("recommendations")
        private final ArrayList<Recommendations> recommendations;

        @JsonProperty("related_anime")
        private final ArrayList<RelatedAnime> relatedAnime;

        @JsonProperty("related_manga")
        private final ArrayList<String> relatedManga;

        @JsonProperty("source")
        private final String source;

        @JsonProperty("start_date")
        private final String startDate;

        @JsonProperty("start_season")
        private final StartSeason startSeason;

        @JsonProperty("statistics")
        private final Statistics statistics;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("studios")
        private final ArrayList<Studios> studios;

        @JsonProperty("synopsis")
        private final String synopsis;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("updated_at")
        private final String updatedAt;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalAnime$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalAnime._childSerializers$_anonymous_();
            }
        }), null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalAnime$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalAnime._childSerializers$_anonymous_$0();
            }
        }), null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalAnime$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalAnime._childSerializers$_anonymous_$1();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalAnime$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalAnime._childSerializers$_anonymous_$2();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalAnime$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalAnime._childSerializers$_anonymous_$3();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalAnime$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalAnime._childSerializers$_anonymous_$4();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MALApi$Genres$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(MALApi$MainPicture$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$1() {
            return new ArrayListSerializer(MALApi$RelatedAnime$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$2() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$3() {
            return new ArrayListSerializer(MALApi$Recommendations$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$4() {
            return new ArrayListSerializer(MALApi$Studios$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MalAnime copy$default(MalAnime malAnime, Integer num, String str, MainPicture mainPicture, AlternativeTitles alternativeTitles, String str2, String str3, String str4, Double d, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, String str7, String str8, String str9, ArrayList arrayList, MyListStatus myListStatus, Integer num6, StartSeason startSeason, Broadcast broadcast, String str10, Integer num7, String str11, ArrayList arrayList2, String str12, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5, ArrayList arrayList6, Statistics statistics, int i, Object obj) {
            Statistics statistics2;
            ArrayList arrayList7;
            Integer num8 = (i & 1) != 0 ? malAnime.id : num;
            String str13 = (i & 2) != 0 ? malAnime.title : str;
            MainPicture mainPicture2 = (i & 4) != 0 ? malAnime.mainPicture : mainPicture;
            AlternativeTitles alternativeTitles2 = (i & 8) != 0 ? malAnime.alternativeTitles : alternativeTitles;
            String str14 = (i & 16) != 0 ? malAnime.startDate : str2;
            String str15 = (i & 32) != 0 ? malAnime.endDate : str3;
            String str16 = (i & 64) != 0 ? malAnime.synopsis : str4;
            Double d2 = (i & 128) != 0 ? malAnime.mean : d;
            Integer num9 = (i & 256) != 0 ? malAnime.rank : num2;
            Integer num10 = (i & 512) != 0 ? malAnime.popularity : num3;
            Integer num11 = (i & 1024) != 0 ? malAnime.numListUsers : num4;
            Integer num12 = (i & 2048) != 0 ? malAnime.numScoringUsers : num5;
            String str17 = (i & 4096) != 0 ? malAnime.nsfw : str5;
            String str18 = (i & 8192) != 0 ? malAnime.createdAt : str6;
            Integer num13 = num8;
            String str19 = (i & 16384) != 0 ? malAnime.updatedAt : str7;
            String str20 = (i & 32768) != 0 ? malAnime.mediaType : str8;
            String str21 = (i & 65536) != 0 ? malAnime.status : str9;
            ArrayList arrayList8 = (i & 131072) != 0 ? malAnime.genres : arrayList;
            MyListStatus myListStatus2 = (i & 262144) != 0 ? malAnime.myListStatus : myListStatus;
            Integer num14 = (i & 524288) != 0 ? malAnime.numEpisodes : num6;
            StartSeason startSeason2 = (i & 1048576) != 0 ? malAnime.startSeason : startSeason;
            Broadcast broadcast2 = (i & 2097152) != 0 ? malAnime.broadcast : broadcast;
            String str22 = (i & 4194304) != 0 ? malAnime.source : str10;
            Integer num15 = (i & 8388608) != 0 ? malAnime.averageEpisodeDuration : num7;
            String str23 = (i & 16777216) != 0 ? malAnime.rating : str11;
            ArrayList arrayList9 = (i & 33554432) != 0 ? malAnime.pictures : arrayList2;
            String str24 = (i & 67108864) != 0 ? malAnime.background : str12;
            ArrayList arrayList10 = (i & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? malAnime.relatedAnime : arrayList3;
            ArrayList arrayList11 = (i & 268435456) != 0 ? malAnime.relatedManga : arrayList4;
            ArrayList arrayList12 = (i & 536870912) != 0 ? malAnime.recommendations : arrayList5;
            ArrayList arrayList13 = (i & 1073741824) != 0 ? malAnime.studios : arrayList6;
            if ((i & Integer.MIN_VALUE) != 0) {
                arrayList7 = arrayList13;
                statistics2 = malAnime.statistics;
            } else {
                statistics2 = statistics;
                arrayList7 = arrayList13;
            }
            return malAnime.copy(num13, str13, mainPicture2, alternativeTitles2, str14, str15, str16, d2, num9, num10, num11, num12, str17, str18, str19, str20, str21, arrayList8, myListStatus2, num14, startSeason2, broadcast2, str22, num15, str23, arrayList9, str24, arrayList10, arrayList11, arrayList12, arrayList7, statistics2);
        }

        @SerialName("alternative_titles")
        public static /* synthetic */ void getAlternativeTitles$annotations() {
        }

        @SerialName("average_episode_duration")
        public static /* synthetic */ void getAverageEpisodeDuration$annotations() {
        }

        @SerialName("background")
        public static /* synthetic */ void getBackground$annotations() {
        }

        @SerialName("broadcast")
        public static /* synthetic */ void getBroadcast$annotations() {
        }

        @SerialName("created_at")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @SerialName("end_date")
        public static /* synthetic */ void getEndDate$annotations() {
        }

        @SerialName("genres")
        public static /* synthetic */ void getGenres$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("main_picture")
        public static /* synthetic */ void getMainPicture$annotations() {
        }

        @SerialName("mean")
        public static /* synthetic */ void getMean$annotations() {
        }

        @SerialName("media_type")
        public static /* synthetic */ void getMediaType$annotations() {
        }

        @SerialName("my_list_status")
        public static /* synthetic */ void getMyListStatus$annotations() {
        }

        @SerialName("nsfw")
        public static /* synthetic */ void getNsfw$annotations() {
        }

        @SerialName("num_episodes")
        public static /* synthetic */ void getNumEpisodes$annotations() {
        }

        @SerialName("num_list_users")
        public static /* synthetic */ void getNumListUsers$annotations() {
        }

        @SerialName("num_scoring_users")
        public static /* synthetic */ void getNumScoringUsers$annotations() {
        }

        @SerialName("pictures")
        public static /* synthetic */ void getPictures$annotations() {
        }

        @SerialName("popularity")
        public static /* synthetic */ void getPopularity$annotations() {
        }

        @SerialName("rank")
        public static /* synthetic */ void getRank$annotations() {
        }

        @SerialName("rating")
        public static /* synthetic */ void getRating$annotations() {
        }

        @SerialName("recommendations")
        public static /* synthetic */ void getRecommendations$annotations() {
        }

        @SerialName("related_anime")
        public static /* synthetic */ void getRelatedAnime$annotations() {
        }

        @SerialName("related_manga")
        public static /* synthetic */ void getRelatedManga$annotations() {
        }

        @SerialName("source")
        public static /* synthetic */ void getSource$annotations() {
        }

        @SerialName("start_date")
        public static /* synthetic */ void getStartDate$annotations() {
        }

        @SerialName("start_season")
        public static /* synthetic */ void getStartSeason$annotations() {
        }

        @SerialName("statistics")
        public static /* synthetic */ void getStatistics$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("studios")
        public static /* synthetic */ void getStudios$annotations() {
        }

        @SerialName("synopsis")
        public static /* synthetic */ void getSynopsis$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getPopularity() {
            return this.popularity;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Integer getNumListUsers() {
            return this.numListUsers;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getNumScoringUsers() {
            return this.numScoringUsers;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getNsfw() {
            return this.nsfw;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final ArrayList<Genres> component18() {
            return this.genres;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final MyListStatus getMyListStatus() {
            return this.myListStatus;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final Integer getNumEpisodes() {
            return this.numEpisodes;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final StartSeason getStartSeason() {
            return this.startSeason;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final Broadcast getBroadcast() {
            return this.broadcast;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final Integer getAverageEpisodeDuration() {
            return this.averageEpisodeDuration;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final String getRating() {
            return this.rating;
        }

        public final ArrayList<MainPicture> component26() {
            return this.pictures;
        }

        /* JADX INFO: renamed from: component27, reason: from getter */
        public final String getBackground() {
            return this.background;
        }

        public final ArrayList<RelatedAnime> component28() {
            return this.relatedAnime;
        }

        public final ArrayList<String> component29() {
            return this.relatedManga;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MainPicture getMainPicture() {
            return this.mainPicture;
        }

        public final ArrayList<Recommendations> component30() {
            return this.recommendations;
        }

        public final ArrayList<Studios> component31() {
            return this.studios;
        }

        /* JADX INFO: renamed from: component32, reason: from getter */
        public final Statistics getStatistics() {
            return this.statistics;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final AlternativeTitles getAlternativeTitles() {
            return this.alternativeTitles;
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
        public final String getSynopsis() {
            return this.synopsis;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Double getMean() {
            return this.mean;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getRank() {
            return this.rank;
        }

        public final MalAnime copy(@JsonProperty("id") Integer id, @JsonProperty("title") String title, @JsonProperty("main_picture") MainPicture mainPicture, @JsonProperty("alternative_titles") AlternativeTitles alternativeTitles, @JsonProperty("start_date") String startDate, @JsonProperty("end_date") String endDate, @JsonProperty("synopsis") String synopsis, @JsonProperty("mean") Double mean, @JsonProperty("rank") Integer rank, @JsonProperty("popularity") Integer popularity, @JsonProperty("num_list_users") Integer numListUsers, @JsonProperty("num_scoring_users") Integer numScoringUsers, @JsonProperty("nsfw") String nsfw, @JsonProperty("created_at") String createdAt, @JsonProperty("updated_at") String updatedAt, @JsonProperty("media_type") String mediaType, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("genres") ArrayList<Genres> genres, @JsonProperty("my_list_status") MyListStatus myListStatus, @JsonProperty("num_episodes") Integer numEpisodes, @JsonProperty("start_season") StartSeason startSeason, @JsonProperty("broadcast") Broadcast broadcast, @JsonProperty("source") String source, @JsonProperty("average_episode_duration") Integer averageEpisodeDuration, @JsonProperty("rating") String rating, @JsonProperty("pictures") ArrayList<MainPicture> pictures, @JsonProperty("background") String background, @JsonProperty("related_anime") ArrayList<RelatedAnime> relatedAnime, @JsonProperty("related_manga") ArrayList<String> relatedManga, @JsonProperty("recommendations") ArrayList<Recommendations> recommendations, @JsonProperty("studios") ArrayList<Studios> studios, @JsonProperty("statistics") Statistics statistics) {
            return new MalAnime(id, title, mainPicture, alternativeTitles, startDate, endDate, synopsis, mean, rank, popularity, numListUsers, numScoringUsers, nsfw, createdAt, updatedAt, mediaType, status, genres, myListStatus, numEpisodes, startSeason, broadcast, source, averageEpisodeDuration, rating, pictures, background, relatedAnime, relatedManga, recommendations, studios, statistics);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalAnime)) {
                return false;
            }
            MalAnime malAnime = (MalAnime) other;
            return Intrinsics.areEqual(this.id, malAnime.id) && Intrinsics.areEqual(this.title, malAnime.title) && Intrinsics.areEqual(this.mainPicture, malAnime.mainPicture) && Intrinsics.areEqual(this.alternativeTitles, malAnime.alternativeTitles) && Intrinsics.areEqual(this.startDate, malAnime.startDate) && Intrinsics.areEqual(this.endDate, malAnime.endDate) && Intrinsics.areEqual(this.synopsis, malAnime.synopsis) && Intrinsics.areEqual((Object) this.mean, (Object) malAnime.mean) && Intrinsics.areEqual(this.rank, malAnime.rank) && Intrinsics.areEqual(this.popularity, malAnime.popularity) && Intrinsics.areEqual(this.numListUsers, malAnime.numListUsers) && Intrinsics.areEqual(this.numScoringUsers, malAnime.numScoringUsers) && Intrinsics.areEqual(this.nsfw, malAnime.nsfw) && Intrinsics.areEqual(this.createdAt, malAnime.createdAt) && Intrinsics.areEqual(this.updatedAt, malAnime.updatedAt) && Intrinsics.areEqual(this.mediaType, malAnime.mediaType) && Intrinsics.areEqual(this.status, malAnime.status) && Intrinsics.areEqual(this.genres, malAnime.genres) && Intrinsics.areEqual(this.myListStatus, malAnime.myListStatus) && Intrinsics.areEqual(this.numEpisodes, malAnime.numEpisodes) && Intrinsics.areEqual(this.startSeason, malAnime.startSeason) && Intrinsics.areEqual(this.broadcast, malAnime.broadcast) && Intrinsics.areEqual(this.source, malAnime.source) && Intrinsics.areEqual(this.averageEpisodeDuration, malAnime.averageEpisodeDuration) && Intrinsics.areEqual(this.rating, malAnime.rating) && Intrinsics.areEqual(this.pictures, malAnime.pictures) && Intrinsics.areEqual(this.background, malAnime.background) && Intrinsics.areEqual(this.relatedAnime, malAnime.relatedAnime) && Intrinsics.areEqual(this.relatedManga, malAnime.relatedManga) && Intrinsics.areEqual(this.recommendations, malAnime.recommendations) && Intrinsics.areEqual(this.studios, malAnime.studios) && Intrinsics.areEqual(this.statistics, malAnime.statistics);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.title;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            MainPicture mainPicture = this.mainPicture;
            int iHashCode3 = (iHashCode2 + (mainPicture == null ? 0 : mainPicture.hashCode())) * 31;
            AlternativeTitles alternativeTitles = this.alternativeTitles;
            int iHashCode4 = (iHashCode3 + (alternativeTitles == null ? 0 : alternativeTitles.hashCode())) * 31;
            String str2 = this.startDate;
            int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.endDate;
            int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.synopsis;
            int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Double d = this.mean;
            int iHashCode8 = (iHashCode7 + (d == null ? 0 : d.hashCode())) * 31;
            Integer num2 = this.rank;
            int iHashCode9 = (iHashCode8 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.popularity;
            int iHashCode10 = (iHashCode9 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.numListUsers;
            int iHashCode11 = (iHashCode10 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.numScoringUsers;
            int iHashCode12 = (iHashCode11 + (num5 == null ? 0 : num5.hashCode())) * 31;
            String str5 = this.nsfw;
            int iHashCode13 = (iHashCode12 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.createdAt;
            int iHashCode14 = (iHashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.updatedAt;
            int iHashCode15 = (iHashCode14 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.mediaType;
            int iHashCode16 = (iHashCode15 + (str8 == null ? 0 : str8.hashCode())) * 31;
            String str9 = this.status;
            int iHashCode17 = (iHashCode16 + (str9 == null ? 0 : str9.hashCode())) * 31;
            ArrayList<Genres> arrayList = this.genres;
            int iHashCode18 = (iHashCode17 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
            MyListStatus myListStatus = this.myListStatus;
            int iHashCode19 = (iHashCode18 + (myListStatus == null ? 0 : myListStatus.hashCode())) * 31;
            Integer num6 = this.numEpisodes;
            int iHashCode20 = (iHashCode19 + (num6 == null ? 0 : num6.hashCode())) * 31;
            StartSeason startSeason = this.startSeason;
            int iHashCode21 = (iHashCode20 + (startSeason == null ? 0 : startSeason.hashCode())) * 31;
            Broadcast broadcast = this.broadcast;
            int iHashCode22 = (iHashCode21 + (broadcast == null ? 0 : broadcast.hashCode())) * 31;
            String str10 = this.source;
            int iHashCode23 = (iHashCode22 + (str10 == null ? 0 : str10.hashCode())) * 31;
            Integer num7 = this.averageEpisodeDuration;
            int iHashCode24 = (iHashCode23 + (num7 == null ? 0 : num7.hashCode())) * 31;
            String str11 = this.rating;
            int iHashCode25 = (iHashCode24 + (str11 == null ? 0 : str11.hashCode())) * 31;
            ArrayList<MainPicture> arrayList2 = this.pictures;
            int iHashCode26 = (iHashCode25 + (arrayList2 == null ? 0 : arrayList2.hashCode())) * 31;
            String str12 = this.background;
            int iHashCode27 = (iHashCode26 + (str12 == null ? 0 : str12.hashCode())) * 31;
            ArrayList<RelatedAnime> arrayList3 = this.relatedAnime;
            int iHashCode28 = (iHashCode27 + (arrayList3 == null ? 0 : arrayList3.hashCode())) * 31;
            ArrayList<String> arrayList4 = this.relatedManga;
            int iHashCode29 = (iHashCode28 + (arrayList4 == null ? 0 : arrayList4.hashCode())) * 31;
            ArrayList<Recommendations> arrayList5 = this.recommendations;
            int iHashCode30 = (iHashCode29 + (arrayList5 == null ? 0 : arrayList5.hashCode())) * 31;
            ArrayList<Studios> arrayList6 = this.studios;
            int iHashCode31 = (iHashCode30 + (arrayList6 == null ? 0 : arrayList6.hashCode())) * 31;
            Statistics statistics = this.statistics;
            return iHashCode31 + (statistics != null ? statistics.hashCode() : 0);
        }

        public String toString() {
            return "MalAnime(id=" + this.id + ", title=" + this.title + ", mainPicture=" + this.mainPicture + ", alternativeTitles=" + this.alternativeTitles + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", synopsis=" + this.synopsis + ", mean=" + this.mean + ", rank=" + this.rank + ", popularity=" + this.popularity + ", numListUsers=" + this.numListUsers + ", numScoringUsers=" + this.numScoringUsers + ", nsfw=" + this.nsfw + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", mediaType=" + this.mediaType + ", status=" + this.status + ", genres=" + this.genres + ", myListStatus=" + this.myListStatus + ", numEpisodes=" + this.numEpisodes + ", startSeason=" + this.startSeason + ", broadcast=" + this.broadcast + ", source=" + this.source + ", averageEpisodeDuration=" + this.averageEpisodeDuration + ", rating=" + this.rating + ", pictures=" + this.pictures + ", background=" + this.background + ", relatedAnime=" + this.relatedAnime + ", relatedManga=" + this.relatedManga + ", recommendations=" + this.recommendations + ", studios=" + this.studios + ", statistics=" + this.statistics + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalAnime$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalAnime;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalAnime> serializer() {
                return MALApi$MalAnime$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalAnime(int i, int i2, Integer num, String str, MainPicture mainPicture, AlternativeTitles alternativeTitles, String str2, String str3, String str4, Double d, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, String str7, String str8, String str9, ArrayList arrayList, MyListStatus myListStatus, Integer num6, StartSeason startSeason, Broadcast broadcast, String str10, Integer num7, String str11, ArrayList arrayList2, String str12, ArrayList arrayList3, ArrayList arrayList4, ArrayList arrayList5, ArrayList arrayList6, Statistics statistics, SerializationConstructorMarker serializationConstructorMarker) {
            if (-1 != i) {
                PluginExceptionsKt.throwArrayMissingFieldException(new int[]{i, i2}, new int[]{-1, 0}, MALApi$MalAnime$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.title = str;
            this.mainPicture = mainPicture;
            this.alternativeTitles = alternativeTitles;
            this.startDate = str2;
            this.endDate = str3;
            this.synopsis = str4;
            this.mean = d;
            this.rank = num2;
            this.popularity = num3;
            this.numListUsers = num4;
            this.numScoringUsers = num5;
            this.nsfw = str5;
            this.createdAt = str6;
            this.updatedAt = str7;
            this.mediaType = str8;
            this.status = str9;
            this.genres = arrayList;
            this.myListStatus = myListStatus;
            this.numEpisodes = num6;
            this.startSeason = startSeason;
            this.broadcast = broadcast;
            this.source = str10;
            this.averageEpisodeDuration = num7;
            this.rating = str11;
            this.pictures = arrayList2;
            this.background = str12;
            this.relatedAnime = arrayList3;
            this.relatedManga = arrayList4;
            this.recommendations = arrayList5;
            this.studios = arrayList6;
            this.statistics = statistics;
        }

        public MalAnime(@JsonProperty("id") Integer num, @JsonProperty("title") String str, @JsonProperty("main_picture") MainPicture mainPicture, @JsonProperty("alternative_titles") AlternativeTitles alternativeTitles, @JsonProperty("start_date") String str2, @JsonProperty("end_date") String str3, @JsonProperty("synopsis") String str4, @JsonProperty("mean") Double d, @JsonProperty("rank") Integer num2, @JsonProperty("popularity") Integer num3, @JsonProperty("num_list_users") Integer num4, @JsonProperty("num_scoring_users") Integer num5, @JsonProperty("nsfw") String str5, @JsonProperty("created_at") String str6, @JsonProperty("updated_at") String str7, @JsonProperty("media_type") String str8, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String str9, @JsonProperty("genres") ArrayList<Genres> arrayList, @JsonProperty("my_list_status") MyListStatus myListStatus, @JsonProperty("num_episodes") Integer num6, @JsonProperty("start_season") StartSeason startSeason, @JsonProperty("broadcast") Broadcast broadcast, @JsonProperty("source") String str10, @JsonProperty("average_episode_duration") Integer num7, @JsonProperty("rating") String str11, @JsonProperty("pictures") ArrayList<MainPicture> arrayList2, @JsonProperty("background") String str12, @JsonProperty("related_anime") ArrayList<RelatedAnime> arrayList3, @JsonProperty("related_manga") ArrayList<String> arrayList4, @JsonProperty("recommendations") ArrayList<Recommendations> arrayList5, @JsonProperty("studios") ArrayList<Studios> arrayList6, @JsonProperty("statistics") Statistics statistics) {
            this.id = num;
            this.title = str;
            this.mainPicture = mainPicture;
            this.alternativeTitles = alternativeTitles;
            this.startDate = str2;
            this.endDate = str3;
            this.synopsis = str4;
            this.mean = d;
            this.rank = num2;
            this.popularity = num3;
            this.numListUsers = num4;
            this.numScoringUsers = num5;
            this.nsfw = str5;
            this.createdAt = str6;
            this.updatedAt = str7;
            this.mediaType = str8;
            this.status = str9;
            this.genres = arrayList;
            this.myListStatus = myListStatus;
            this.numEpisodes = num6;
            this.startSeason = startSeason;
            this.broadcast = broadcast;
            this.source = str10;
            this.averageEpisodeDuration = num7;
            this.rating = str11;
            this.pictures = arrayList2;
            this.background = str12;
            this.relatedAnime = arrayList3;
            this.relatedManga = arrayList4;
            this.recommendations = arrayList5;
            this.studios = arrayList6;
            this.statistics = statistics;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalAnime self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, MALApi$MainPicture$$serializer.INSTANCE, self.mainPicture);
            output.encodeNullableSerializableElement(serialDesc, 3, MALApi$AlternativeTitles$$serializer.INSTANCE, self.alternativeTitles);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.startDate);
            output.encodeNullableSerializableElement(serialDesc, 5, StringSerializer.INSTANCE, self.endDate);
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.synopsis);
            output.encodeNullableSerializableElement(serialDesc, 7, DoubleSerializer.INSTANCE, self.mean);
            output.encodeNullableSerializableElement(serialDesc, 8, IntSerializer.INSTANCE, self.rank);
            output.encodeNullableSerializableElement(serialDesc, 9, IntSerializer.INSTANCE, self.popularity);
            output.encodeNullableSerializableElement(serialDesc, 10, IntSerializer.INSTANCE, self.numListUsers);
            output.encodeNullableSerializableElement(serialDesc, 11, IntSerializer.INSTANCE, self.numScoringUsers);
            output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.nsfw);
            output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.createdAt);
            output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.updatedAt);
            output.encodeNullableSerializableElement(serialDesc, 15, StringSerializer.INSTANCE, self.mediaType);
            output.encodeNullableSerializableElement(serialDesc, 16, StringSerializer.INSTANCE, self.status);
            output.encodeNullableSerializableElement(serialDesc, 17, lazyArr[17].getValue(), self.genres);
            output.encodeNullableSerializableElement(serialDesc, 18, MALApi$MyListStatus$$serializer.INSTANCE, self.myListStatus);
            output.encodeNullableSerializableElement(serialDesc, 19, IntSerializer.INSTANCE, self.numEpisodes);
            output.encodeNullableSerializableElement(serialDesc, 20, MALApi$StartSeason$$serializer.INSTANCE, self.startSeason);
            output.encodeNullableSerializableElement(serialDesc, 21, MALApi$Broadcast$$serializer.INSTANCE, self.broadcast);
            output.encodeNullableSerializableElement(serialDesc, 22, StringSerializer.INSTANCE, self.source);
            output.encodeNullableSerializableElement(serialDesc, 23, IntSerializer.INSTANCE, self.averageEpisodeDuration);
            output.encodeNullableSerializableElement(serialDesc, 24, StringSerializer.INSTANCE, self.rating);
            output.encodeNullableSerializableElement(serialDesc, 25, lazyArr[25].getValue(), self.pictures);
            output.encodeNullableSerializableElement(serialDesc, 26, StringSerializer.INSTANCE, self.background);
            output.encodeNullableSerializableElement(serialDesc, 27, lazyArr[27].getValue(), self.relatedAnime);
            output.encodeNullableSerializableElement(serialDesc, 28, lazyArr[28].getValue(), self.relatedManga);
            output.encodeNullableSerializableElement(serialDesc, 29, lazyArr[29].getValue(), self.recommendations);
            output.encodeNullableSerializableElement(serialDesc, 30, lazyArr[30].getValue(), self.studios);
            output.encodeNullableSerializableElement(serialDesc, 31, MALApi$Statistics$$serializer.INSTANCE, self.statistics);
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final MainPicture getMainPicture() {
            return this.mainPicture;
        }

        public final AlternativeTitles getAlternativeTitles() {
            return this.alternativeTitles;
        }

        public final String getStartDate() {
            return this.startDate;
        }

        public final String getEndDate() {
            return this.endDate;
        }

        public final String getSynopsis() {
            return this.synopsis;
        }

        public final Double getMean() {
            return this.mean;
        }

        public final Integer getRank() {
            return this.rank;
        }

        public final Integer getPopularity() {
            return this.popularity;
        }

        public final Integer getNumListUsers() {
            return this.numListUsers;
        }

        public final Integer getNumScoringUsers() {
            return this.numScoringUsers;
        }

        public final String getNsfw() {
            return this.nsfw;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final String getMediaType() {
            return this.mediaType;
        }

        public final String getStatus() {
            return this.status;
        }

        public final ArrayList<Genres> getGenres() {
            return this.genres;
        }

        public final MyListStatus getMyListStatus() {
            return this.myListStatus;
        }

        public final Integer getNumEpisodes() {
            return this.numEpisodes;
        }

        public final StartSeason getStartSeason() {
            return this.startSeason;
        }

        public final Broadcast getBroadcast() {
            return this.broadcast;
        }

        public final String getSource() {
            return this.source;
        }

        public final Integer getAverageEpisodeDuration() {
            return this.averageEpisodeDuration;
        }

        public final String getRating() {
            return this.rating;
        }

        public final ArrayList<MainPicture> getPictures() {
            return this.pictures;
        }

        public final String getBackground() {
            return this.background;
        }

        public final ArrayList<RelatedAnime> getRelatedAnime() {
            return this.relatedAnime;
        }

        public final ArrayList<String> getRelatedManga() {
            return this.relatedManga;
        }

        public final ArrayList<Recommendations> getRecommendations() {
            return this.recommendations;
        }

        public final ArrayList<Studios> getStudios() {
            return this.studios;
        }

        public final Statistics getStatistics() {
            return this.statistics;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J&\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Recommendations;", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "numRecommendations", "", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNode$annotations", "()V", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "getNumRecommendations$annotations", "getNumRecommendations", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Recommendations;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Recommendations {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("node")
        private final Node node;

        @JsonProperty("num_recommendations")
        private final Integer numRecommendations;

        /* JADX WARN: Multi-variable type inference failed */
        public Recommendations() {
            this((Node) null, (Integer) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ Recommendations copy$default(Recommendations recommendations, Node node, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                node = recommendations.node;
            }
            if ((i & 2) != 0) {
                num = recommendations.numRecommendations;
            }
            return recommendations.copy(node, num);
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        @SerialName("num_recommendations")
        public static /* synthetic */ void getNumRecommendations$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Node getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getNumRecommendations() {
            return this.numRecommendations;
        }

        public final Recommendations copy(@JsonProperty("node") Node node, @JsonProperty("num_recommendations") Integer numRecommendations) {
            return new Recommendations(node, numRecommendations);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Recommendations)) {
                return false;
            }
            Recommendations recommendations = (Recommendations) other;
            return Intrinsics.areEqual(this.node, recommendations.node) && Intrinsics.areEqual(this.numRecommendations, recommendations.numRecommendations);
        }

        public int hashCode() {
            Node node = this.node;
            int iHashCode = (node == null ? 0 : node.hashCode()) * 31;
            Integer num = this.numRecommendations;
            return iHashCode + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "Recommendations(node=" + this.node + ", numRecommendations=" + this.numRecommendations + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Recommendations$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Recommendations;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Recommendations> serializer() {
                return MALApi$Recommendations$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Recommendations(int i, Node node, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.node = null;
            } else {
                this.node = node;
            }
            if ((i & 2) == 0) {
                this.numRecommendations = null;
            } else {
                this.numRecommendations = num;
            }
        }

        public Recommendations(@JsonProperty("node") Node node, @JsonProperty("num_recommendations") Integer num) {
            this.node = node;
            this.numRecommendations = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Recommendations self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.node != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, MALApi$Node$$serializer.INSTANCE, self.node);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.numRecommendations == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.numRecommendations);
        }

        public /* synthetic */ Recommendations(Node node, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : node, (i & 2) != 0 ? null : num);
        }

        public final Node getNode() {
            return this.node;
        }

        public final Integer getNumRecommendations() {
            return this.numRecommendations;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Studios;", "", "id", "", "name", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getName$annotations", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Studios;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Studios {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("name")
        private final String name;

        /* JADX WARN: Multi-variable type inference failed */
        public Studios() {
            this((Integer) null, (String) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ Studios copy$default(Studios studios, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                num = studios.id;
            }
            if ((i & 2) != 0) {
                str = studios.name;
            }
            return studios.copy(num, str);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Studios copy(@JsonProperty("id") Integer id, @JsonProperty("name") String name) {
            return new Studios(id, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Studios)) {
                return false;
            }
            Studios studios = (Studios) other;
            return Intrinsics.areEqual(this.id, studios.id) && Intrinsics.areEqual(this.name, studios.name);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.name;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Studios(id=" + this.id + ", name=" + this.name + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Studios$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Studios;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Studios> serializer() {
                return MALApi$Studios$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Studios(int i, Integer num, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = num;
            }
            if ((i & 2) == 0) {
                this.name = null;
            } else {
                this.name = str;
            }
        }

        public Studios(@JsonProperty("id") Integer num, @JsonProperty("name") String str) {
            this.id = num;
            this.name = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Studios self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.id != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.name == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.name);
        }

        public /* synthetic */ Studios(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str);
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223BC\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bBM\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010$\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010%J\u0014\u0010&\u001a\u00020\b2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0017\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0017\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u0016R \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001c\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u0007\u0010\u001bR\u001e\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001e\u0010\u0013¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;", "", NotificationCompat.CATEGORY_STATUS, "", "score", "", "numEpisodesWatched", "isRewatching", "", "updatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Ljava/lang/String;", "getScore$annotations", "getScore", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumEpisodesWatched$annotations", "getNumEpisodesWatched", "isRewatching$annotations", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getUpdatedAt$annotations", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MyListStatus {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("is_rewatching")
        private final Boolean isRewatching;

        @JsonProperty("num_episodes_watched")
        private final Integer numEpisodesWatched;

        @JsonProperty("score")
        private final Integer score;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("updated_at")
        private final String updatedAt;

        public MyListStatus() {
            this((String) null, (Integer) null, (Integer) null, (Boolean) null, (String) null, 31, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ MyListStatus copy$default(MyListStatus myListStatus, String str, Integer num, Integer num2, Boolean bool, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = myListStatus.status;
            }
            if ((i & 2) != 0) {
                num = myListStatus.score;
            }
            if ((i & 4) != 0) {
                num2 = myListStatus.numEpisodesWatched;
            }
            if ((i & 8) != 0) {
                bool = myListStatus.isRewatching;
            }
            if ((i & 16) != 0) {
                str2 = myListStatus.updatedAt;
            }
            String str3 = str2;
            Integer num3 = num2;
            return myListStatus.copy(str, num, num3, bool, str3);
        }

        @SerialName("num_episodes_watched")
        public static /* synthetic */ void getNumEpisodesWatched$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName("is_rewatching")
        public static /* synthetic */ void isRewatching$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getNumEpisodesWatched() {
            return this.numEpisodesWatched;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getIsRewatching() {
            return this.isRewatching;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final MyListStatus copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("score") Integer score, @JsonProperty("num_episodes_watched") Integer numEpisodesWatched, @JsonProperty("is_rewatching") Boolean isRewatching, @JsonProperty("updated_at") String updatedAt) {
            return new MyListStatus(status, score, numEpisodesWatched, isRewatching, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MyListStatus)) {
                return false;
            }
            MyListStatus myListStatus = (MyListStatus) other;
            return Intrinsics.areEqual(this.status, myListStatus.status) && Intrinsics.areEqual(this.score, myListStatus.score) && Intrinsics.areEqual(this.numEpisodesWatched, myListStatus.numEpisodesWatched) && Intrinsics.areEqual(this.isRewatching, myListStatus.isRewatching) && Intrinsics.areEqual(this.updatedAt, myListStatus.updatedAt);
        }

        public int hashCode() {
            String str = this.status;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.score;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.numEpisodesWatched;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Boolean bool = this.isRewatching;
            int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str2 = this.updatedAt;
            return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "MyListStatus(status=" + this.status + ", score=" + this.score + ", numEpisodesWatched=" + this.numEpisodesWatched + ", isRewatching=" + this.isRewatching + ", updatedAt=" + this.updatedAt + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MyListStatus;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MyListStatus> serializer() {
                return MALApi$MyListStatus$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MyListStatus(int i, String str, Integer num, Integer num2, Boolean bool, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.status = null;
            } else {
                this.status = str;
            }
            if ((i & 2) == 0) {
                this.score = null;
            } else {
                this.score = num;
            }
            if ((i & 4) == 0) {
                this.numEpisodesWatched = null;
            } else {
                this.numEpisodesWatched = num2;
            }
            if ((i & 8) == 0) {
                this.isRewatching = null;
            } else {
                this.isRewatching = bool;
            }
            if ((i & 16) == 0) {
                this.updatedAt = null;
            } else {
                this.updatedAt = str2;
            }
        }

        public MyListStatus(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String str, @JsonProperty("score") Integer num, @JsonProperty("num_episodes_watched") Integer num2, @JsonProperty("is_rewatching") Boolean bool, @JsonProperty("updated_at") String str2) {
            this.status = str;
            this.score = num;
            this.numEpisodesWatched = num2;
            this.isRewatching = bool;
            this.updatedAt = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MyListStatus self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.status != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.status);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.score != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.score);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.numEpisodesWatched != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.numEpisodesWatched);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.isRewatching != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, BooleanSerializer.INSTANCE, self.isRewatching);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.updatedAt == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.updatedAt);
        }

        public /* synthetic */ MyListStatus(String str, Integer num, Integer num2, Boolean bool, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : str2);
        }

        public final String getStatus() {
            return this.status;
        }

        public final Integer getScore() {
            return this.score;
        }

        public final Integer getNumEpisodesWatched() {
            return this.numEpisodesWatched;
        }

        public final Boolean isRewatching() {
            return this.isRewatching;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bB9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J-\u0010\u001a\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$RelatedAnime;", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "relationType", "", "relationTypeFormatted", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNode$annotations", "()V", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "getRelationType$annotations", "getRelationType", "()Ljava/lang/String;", "getRelationTypeFormatted$annotations", "getRelationTypeFormatted", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class RelatedAnime {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("node")
        private final Node node;

        @JsonProperty("relation_type")
        private final String relationType;

        @JsonProperty("relation_type_formatted")
        private final String relationTypeFormatted;

        public RelatedAnime() {
            this((Node) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ RelatedAnime copy$default(RelatedAnime relatedAnime, Node node, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                node = relatedAnime.node;
            }
            if ((i & 2) != 0) {
                str = relatedAnime.relationType;
            }
            if ((i & 4) != 0) {
                str2 = relatedAnime.relationTypeFormatted;
            }
            return relatedAnime.copy(node, str, str2);
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        @SerialName("relation_type")
        public static /* synthetic */ void getRelationType$annotations() {
        }

        @SerialName("relation_type_formatted")
        public static /* synthetic */ void getRelationTypeFormatted$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Node getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRelationType() {
            return this.relationType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getRelationTypeFormatted() {
            return this.relationTypeFormatted;
        }

        public final RelatedAnime copy(@JsonProperty("node") Node node, @JsonProperty("relation_type") String relationType, @JsonProperty("relation_type_formatted") String relationTypeFormatted) {
            return new RelatedAnime(node, relationType, relationTypeFormatted);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RelatedAnime)) {
                return false;
            }
            RelatedAnime relatedAnime = (RelatedAnime) other;
            return Intrinsics.areEqual(this.node, relatedAnime.node) && Intrinsics.areEqual(this.relationType, relatedAnime.relationType) && Intrinsics.areEqual(this.relationTypeFormatted, relatedAnime.relationTypeFormatted);
        }

        public int hashCode() {
            Node node = this.node;
            int iHashCode = (node == null ? 0 : node.hashCode()) * 31;
            String str = this.relationType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.relationTypeFormatted;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "RelatedAnime(node=" + this.node + ", relationType=" + this.relationType + ", relationTypeFormatted=" + this.relationTypeFormatted + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$RelatedAnime$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$RelatedAnime;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<RelatedAnime> serializer() {
                return MALApi$RelatedAnime$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ RelatedAnime(int i, Node node, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.node = null;
            } else {
                this.node = node;
            }
            if ((i & 2) == 0) {
                this.relationType = null;
            } else {
                this.relationType = str;
            }
            if ((i & 4) == 0) {
                this.relationTypeFormatted = null;
            } else {
                this.relationTypeFormatted = str2;
            }
        }

        public RelatedAnime(@JsonProperty("node") Node node, @JsonProperty("relation_type") String str, @JsonProperty("relation_type_formatted") String str2) {
            this.node = node;
            this.relationType = str;
            this.relationTypeFormatted = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(RelatedAnime self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.node != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, MALApi$Node$$serializer.INSTANCE, self.node);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.relationType != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.relationType);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 2) && self.relationTypeFormatted == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.relationTypeFormatted);
        }

        public /* synthetic */ RelatedAnime(Node node, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : node, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2);
        }

        public final Node getNode() {
            return this.node;
        }

        public final String getRelationType() {
            return this.relationType;
        }

        public final String getRelationTypeFormatted() {
            return this.relationTypeFormatted;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./BC\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tBM\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010 \u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u001a\u0010\u0012¨\u00060"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;", "", "watching", "", "completed", "onHold", "dropped", "planToWatch", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getWatching$annotations", "()V", "getWatching", "()Ljava/lang/String;", "getCompleted$annotations", "getCompleted", "getOnHold$annotations", "getOnHold", "getDropped$annotations", "getDropped", "getPlanToWatch$annotations", "getPlanToWatch", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Status {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("completed")
        private final String completed;

        @JsonProperty("dropped")
        private final String dropped;

        @JsonProperty("on_hold")
        private final String onHold;

        @JsonProperty("plan_to_watch")
        private final String planToWatch;

        @JsonProperty("watching")
        private final String watching;

        public Status() {
            this((String) null, (String) null, (String) null, (String) null, (String) null, 31, (DefaultConstructorMarker) null);
        }

        public static /* synthetic */ Status copy$default(Status status, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = status.watching;
            }
            if ((i & 2) != 0) {
                str2 = status.completed;
            }
            if ((i & 4) != 0) {
                str3 = status.onHold;
            }
            if ((i & 8) != 0) {
                str4 = status.dropped;
            }
            if ((i & 16) != 0) {
                str5 = status.planToWatch;
            }
            String str6 = str5;
            String str7 = str3;
            return status.copy(str, str2, str7, str4, str6);
        }

        @SerialName("completed")
        public static /* synthetic */ void getCompleted$annotations() {
        }

        @SerialName("dropped")
        public static /* synthetic */ void getDropped$annotations() {
        }

        @SerialName("on_hold")
        public static /* synthetic */ void getOnHold$annotations() {
        }

        @SerialName("plan_to_watch")
        public static /* synthetic */ void getPlanToWatch$annotations() {
        }

        @SerialName("watching")
        public static /* synthetic */ void getWatching$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getWatching() {
            return this.watching;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCompleted() {
            return this.completed;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getOnHold() {
            return this.onHold;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDropped() {
            return this.dropped;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPlanToWatch() {
            return this.planToWatch;
        }

        public final Status copy(@JsonProperty("watching") String watching, @JsonProperty("completed") String completed, @JsonProperty("on_hold") String onHold, @JsonProperty("dropped") String dropped, @JsonProperty("plan_to_watch") String planToWatch) {
            return new Status(watching, completed, onHold, dropped, planToWatch);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Status)) {
                return false;
            }
            Status status = (Status) other;
            return Intrinsics.areEqual(this.watching, status.watching) && Intrinsics.areEqual(this.completed, status.completed) && Intrinsics.areEqual(this.onHold, status.onHold) && Intrinsics.areEqual(this.dropped, status.dropped) && Intrinsics.areEqual(this.planToWatch, status.planToWatch);
        }

        public int hashCode() {
            String str = this.watching;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.completed;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.onHold;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.dropped;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.planToWatch;
            return iHashCode4 + (str5 != null ? str5.hashCode() : 0);
        }

        public String toString() {
            return "Status(watching=" + this.watching + ", completed=" + this.completed + ", onHold=" + this.onHold + ", dropped=" + this.dropped + ", planToWatch=" + this.planToWatch + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Status> serializer() {
                return MALApi$Status$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Status(int i, String str, String str2, String str3, String str4, String str5, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.watching = null;
            } else {
                this.watching = str;
            }
            if ((i & 2) == 0) {
                this.completed = null;
            } else {
                this.completed = str2;
            }
            if ((i & 4) == 0) {
                this.onHold = null;
            } else {
                this.onHold = str3;
            }
            if ((i & 8) == 0) {
                this.dropped = null;
            } else {
                this.dropped = str4;
            }
            if ((i & 16) == 0) {
                this.planToWatch = null;
            } else {
                this.planToWatch = str5;
            }
        }

        public Status(@JsonProperty("watching") String str, @JsonProperty("completed") String str2, @JsonProperty("on_hold") String str3, @JsonProperty("dropped") String str4, @JsonProperty("plan_to_watch") String str5) {
            this.watching = str;
            this.completed = str2;
            this.onHold = str3;
            this.dropped = str4;
            this.planToWatch = str5;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Status self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.watching != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.watching);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 1) || self.completed != null) {
                output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.completed);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 2) || self.onHold != null) {
                output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.onHold);
            }
            if (output.shouldEncodeElementDefault(serialDesc, 3) || self.dropped != null) {
                output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.dropped);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.planToWatch == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.planToWatch);
        }

        public /* synthetic */ Status(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5);
        }

        public final String getWatching() {
            return this.watching;
        }

        public final String getCompleted() {
            return this.completed;
        }

        public final String getOnHold() {
            return this.onHold;
        }

        public final String getDropped() {
            return this.dropped;
        }

        public final String getPlanToWatch() {
            return this.planToWatch;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0012J&\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;", "", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;", "numListUsers", "", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;", "getNumListUsers$annotations", "getNumListUsers", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Status;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Statistics {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("num_list_users")
        private final Integer numListUsers;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final Status status;

        /* JADX WARN: Multi-variable type inference failed */
        public Statistics() {
            this((Status) null, (Integer) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ Statistics copy$default(Statistics statistics, Status status, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                status = statistics.status;
            }
            if ((i & 2) != 0) {
                num = statistics.numListUsers;
            }
            return statistics.copy(status, num);
        }

        @SerialName("num_list_users")
        public static /* synthetic */ void getNumListUsers$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Status getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getNumListUsers() {
            return this.numListUsers;
        }

        public final Statistics copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) Status status, @JsonProperty("num_list_users") Integer numListUsers) {
            return new Statistics(status, numListUsers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Statistics)) {
                return false;
            }
            Statistics statistics = (Statistics) other;
            return Intrinsics.areEqual(this.status, statistics.status) && Intrinsics.areEqual(this.numListUsers, statistics.numListUsers);
        }

        public int hashCode() {
            Status status = this.status;
            int iHashCode = (status == null ? 0 : status.hashCode()) * 31;
            Integer num = this.numListUsers;
            return iHashCode + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "Statistics(status=" + this.status + ", numListUsers=" + this.numListUsers + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Statistics;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Statistics> serializer() {
                return MALApi$Statistics$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Statistics(int i, Status status, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.status = null;
            } else {
                this.status = status;
            }
            if ((i & 2) == 0) {
                this.numListUsers = null;
            } else {
                this.numListUsers = num;
            }
        }

        public Statistics(@JsonProperty(NotificationCompat.CATEGORY_STATUS) Status status, @JsonProperty("num_list_users") Integer num) {
            this.status = status;
            this.numListUsers = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Statistics self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (output.shouldEncodeElementDefault(serialDesc, 0) || self.status != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, MALApi$Status$$serializer.INSTANCE, self.status);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && self.numListUsers == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.numListUsers);
        }

        public /* synthetic */ Statistics(Status status, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : status, (i & 2) != 0 ? null : num);
        }

        public final Status getStatus() {
            return this.status;
        }

        public final Integer getNumListUsers() {
            return this.numListUsers;
        }
    }

    private final Long parseDate(String string) {
        Date date;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TmdbHelper.TMDB_DATE_PATTERN, Locale.getDefault());
            if (string == null || (date = simpleDateFormat.parse(string)) == null) {
                return null;
            }
            return Long.valueOf(date.getTime());
        } catch (Exception unused) {
            return null;
        }
    }

    private final SyncAPI.SyncSearchResult toSearchResult(Node node) {
        String title;
        if (node != null && (title = node.getTitle()) != null) {
            String name = getName();
            String strValueOf = String.valueOf(node.getId());
            String str = getMainUrl() + "/anime/" + node.getId();
            MainPicture mainPicture = node.getMainPicture();
            return new SyncAPI.SyncSearchResult(title, name, strValueOf, str, mainPicture != null ? mainPicture.getLarge() : null, null, null, null, null, null, 992, null);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x028e  */
    /* JADX WARN: Code duplicated, block: B:103:0x0296  */
    /* JADX WARN: Code duplicated, block: B:106:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:112:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:113:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:128:0x01d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:130:0x01c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:133:0x024c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:136:0x0232 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:138:0x027e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:0x02b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:62:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:66:0x01db  */
    /* JADX WARN: Code duplicated, block: B:69:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:72:0x01fb A[LOOP:1: B:70:0x01f5->B:72:0x01fb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:74:0x020e  */
    /* JADX WARN: Code duplicated, block: B:77:0x0225  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:80:0x0238  */
    /* JADX WARN: Code duplicated, block: B:82:0x0244  */
    /* JADX WARN: Code duplicated, block: B:83:0x0246  */
    /* JADX WARN: Code duplicated, block: B:87:0x0256  */
    /* JADX WARN: Code duplicated, block: B:90:0x025e  */
    /* JADX WARN: Code duplicated, block: B:93:0x026a  */
    /* JADX WARN: Code duplicated, block: B:99:0x0283  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object load(AuthData authData, String str, Continuation<? super SyncAPI.SyncResult> continuation) throws JsonProcessingException {
        C07991 c07991;
        AuthToken token;
        String accessToken;
        ArrayList arrayList;
        int i;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        ShowStatus showStatus;
        ShowStatus showStatus2;
        ArrayList<Studios> studios;
        ArrayList arrayList2;
        ArrayList<Genres> genres;
        ArrayList arrayList3;
        ArrayList<Recommendations> recommendations;
        ArrayList arrayList4;
        ArrayList<RelatedAnime> relatedAnime;
        SyncAPI.SyncSearchResult searchResult;
        ArrayList<RelatedAnime> relatedAnime2;
        SyncAPI.SyncSearchResult searchResult2;
        Iterator<T> it;
        Object next;
        RelatedAnime relatedAnime3;
        Iterator<T> it2;
        Object next2;
        RelatedAnime relatedAnime4;
        ArrayList arrayList5;
        Iterator<T> it3;
        Node node;
        Object searchResult3;
        ArrayList arrayList6;
        Iterator<T> it4;
        ArrayList arrayList7;
        Iterator<T> it5;
        String name;
        if (continuation instanceof C07991) {
            c07991 = (C07991) continuation;
            if ((c07991.label & Integer.MIN_VALUE) != 0) {
                c07991.label -= Integer.MIN_VALUE;
            } else {
                c07991 = new C07991(continuation);
            }
        } else {
            c07991 = new C07991(continuation);
        }
        Object obj = c07991.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07991.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (token = authData.getToken()) == null || (accessToken = token.getAccessToken()) == null) {
                return null;
            }
            Integer intOrNull = StringsKt.toIntOrNull(str);
            if (intOrNull == null) {
                return null;
            }
            int iIntValue = intOrNull.intValue();
            String str2 = this.apiUrl + "/v2/anime/" + iIntValue + "?fields=id,title,main_picture,alternative_titles,start_date,end_date,synopsis,mean,rank,popularity,num_list_users,num_scoring_users,nsfw,created_at,updated_at,media_type,status,genres,my_list_status,num_episodes,start_season,broadcast,source,average_episode_duration,rating,pictures,background,related_anime,related_manga,recommendations,studios,statistics";
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + accessToken));
            c07991.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07991.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07991.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c07991.L$3 = SpillingKt.nullOutSpilledVariable(str2);
            c07991.I$0 = iIntValue;
            c07991.label = 1;
            C07991 c07992 = c07991;
            arrayList = null;
            Object obj2 = Requests.get$default(app2, str2, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c07992, 4092, null);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = obj2;
            i = iIntValue;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = c07991.I$0;
            ResultKt.throwOnFailure(obj);
            arrayList = null;
        }
        String text = ((NiceResponse) obj).getText();
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(MalAnime.INSTANCE.serializer());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(MalAnime.class), arrayList, 2, arrayList));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        Object obj3 = objM8936constructorimpl;
        if (Result.m8942isFailureimpl(obj3)) {
            obj3 = arrayList;
        }
        KSerializer kSerializer = (KSerializer) obj3;
        if (kSerializer != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<MalAnime>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$load$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<MalAnime>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$load$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<MalAnime>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$load$$inlined$parseJson$1
            });
        }
        MalAnime malAnime = (MalAnime) objDecodeFromString;
        String strValueOf = String.valueOf(i);
        Integer numEpisodes = malAnime.getNumEpisodes();
        String title = malAnime.getTitle();
        Score scoreFrom10 = Score.INSTANCE.from10(malAnime.getMean());
        Integer averageEpisodeDuration = malAnime.getAverageEpisodeDuration();
        String synopsis = malAnime.getSynopsis();
        String status = malAnime.getStatus();
        if (!Intrinsics.areEqual(status, "finished_airing")) {
            if (Intrinsics.areEqual(status, "currently_airing")) {
                showStatus2 = ShowStatus.Ongoing;
            } else {
                showStatus = arrayList;
            }
            studios = malAnime.getStudios();
            if (studios != null) {
                arrayList7 = new ArrayList();
                it5 = studios.iterator();
                while (it5.hasNext()) {
                    name = ((Studios) it5.next()).getName();
                    if (name != null) {
                        arrayList7.add(name);
                    }
                }
                arrayList2 = arrayList7;
            } else {
                arrayList2 = arrayList;
            }
            genres = malAnime.getGenres();
            if (genres != null) {
                ArrayList<Genres> arrayList8 = genres;
                arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList8, 10));
                it4 = arrayList8.iterator();
                while (it4.hasNext()) {
                    arrayList6.add(((Genres) it4.next()).getName());
                }
                arrayList3 = arrayList6;
            } else {
                arrayList3 = arrayList;
            }
            Long date = parseDate(malAnime.getStartDate());
            Long date2 = parseDate(malAnime.getEndDate());
            recommendations = malAnime.getRecommendations();
            if (recommendations != null) {
                arrayList5 = new ArrayList();
                it3 = recommendations.iterator();
                while (it3.hasNext()) {
                    node = ((Recommendations) it3.next()).getNode();
                    if (node == null) {
                        searchResult3 = arrayList;
                    } else {
                        searchResult3 = toSearchResult(node);
                    }
                    if (searchResult3 != null) {
                        arrayList5.add(searchResult3);
                    }
                }
                arrayList4 = arrayList5;
            } else {
                arrayList4 = arrayList;
            }
            relatedAnime = malAnime.getRelatedAnime();
            if (relatedAnime != null) {
                it2 = relatedAnime.iterator();
                do {
                    if (it2.hasNext()) {
                        next2 = arrayList;
                        break;
                    }
                    next2 = it2.next();
                } while (!Intrinsics.areEqual(((RelatedAnime) next2).getRelationType(), "sequel"));
                relatedAnime4 = (RelatedAnime) next2;
                if (relatedAnime4 != null) {
                    searchResult = toSearchResult(relatedAnime4.getNode());
                } else {
                    searchResult = arrayList;
                }
            } else {
                searchResult = arrayList;
            }
            relatedAnime2 = malAnime.getRelatedAnime();
            if (relatedAnime2 != null) {
                it = relatedAnime2.iterator();
                do {
                    if (it.hasNext()) {
                        next = arrayList;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((RelatedAnime) next).getRelationType(), "prequel"));
                relatedAnime3 = (RelatedAnime) next;
                if (relatedAnime3 != null) {
                    searchResult2 = toSearchResult(relatedAnime3.getNode());
                } else {
                    searchResult2 = arrayList;
                }
            } else {
                searchResult2 = arrayList;
            }
            return new SyncAPI.SyncResult(strValueOf, numEpisodes, title, scoreFrom10, averageEpisodeDuration, synopsis, showStatus, null, arrayList2, arrayList3, null, null, null, null, null, date, date2, arrayList4, searchResult, searchResult2, null, 29696, null);
        }
        showStatus2 = ShowStatus.Completed;
        showStatus = showStatus2;
        studios = malAnime.getStudios();
        if (studios != null) {
            arrayList7 = new ArrayList();
            it5 = studios.iterator();
            while (it5.hasNext()) {
                name = ((Studios) it5.next()).getName();
                if (name != null) {
                    arrayList7.add(name);
                }
            }
            arrayList2 = arrayList7;
        } else {
            arrayList2 = arrayList;
        }
        genres = malAnime.getGenres();
        if (genres != null) {
            ArrayList<Genres> arrayList9 = genres;
            arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList9, 10));
            it4 = arrayList9.iterator();
            while (it4.hasNext()) {
                arrayList6.add(((Genres) it4.next()).getName());
            }
            arrayList3 = arrayList6;
        } else {
            arrayList3 = arrayList;
        }
        Long date3 = parseDate(malAnime.getStartDate());
        Long date4 = parseDate(malAnime.getEndDate());
        recommendations = malAnime.getRecommendations();
        if (recommendations != null) {
            arrayList5 = new ArrayList();
            it3 = recommendations.iterator();
            while (it3.hasNext()) {
                node = ((Recommendations) it3.next()).getNode();
                if (node == null) {
                    searchResult3 = arrayList;
                } else {
                    searchResult3 = toSearchResult(node);
                }
                if (searchResult3 != null) {
                    arrayList5.add(searchResult3);
                }
            }
            arrayList4 = arrayList5;
        } else {
            arrayList4 = arrayList;
        }
        relatedAnime = malAnime.getRelatedAnime();
        if (relatedAnime != null) {
            it2 = relatedAnime.iterator();
            do {
                if (it2.hasNext()) {
                    next2 = arrayList;
                    break;
                }
                next2 = it2.next();
            } while (!Intrinsics.areEqual(((RelatedAnime) next2).getRelationType(), "sequel"));
            relatedAnime4 = (RelatedAnime) next2;
            if (relatedAnime4 != null) {
                searchResult = toSearchResult(relatedAnime4.getNode());
            } else {
                searchResult = arrayList;
            }
        } else {
            searchResult = arrayList;
        }
        relatedAnime2 = malAnime.getRelatedAnime();
        if (relatedAnime2 != null) {
            it = relatedAnime2.iterator();
            do {
                if (it.hasNext()) {
                    next = arrayList;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((RelatedAnime) next).getRelationType(), "prequel"));
            relatedAnime3 = (RelatedAnime) next;
            if (relatedAnime3 != null) {
                searchResult2 = toSearchResult(relatedAnime3.getNode());
            } else {
                searchResult2 = arrayList;
            }
        } else {
            searchResult2 = arrayList;
        }
        return new SyncAPI.SyncResult(strValueOf, numEpisodes, title, scoreFrom10, averageEpisodeDuration, synopsis, showStatus, null, arrayList2, arrayList3, null, null, null, null, null, date3, date4, arrayList4, searchResult, searchResult2, null, 29696, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object status(AuthData authData, String str, Continuation<? super SyncAPI.AbstractSyncStatus> continuation) {
        C08041 c08041;
        AuthToken token;
        String accessToken;
        if (continuation instanceof C08041) {
            c08041 = (C08041) continuation;
            if ((c08041.label & Integer.MIN_VALUE) != 0) {
                c08041.label -= Integer.MIN_VALUE;
            } else {
                c08041 = new C08041(continuation);
            }
        } else {
            c08041 = new C08041(continuation);
        }
        Object obj = c08041.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08041.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (authData == null || (token = authData.getToken()) == null || (accessToken = token.getAccessToken()) == null) {
                return null;
            }
            String str2 = this.apiUrl + "/v2/anime/" + str + "?fields=id,title,num_episodes,my_list_status";
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + accessToken));
            c08041.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08041.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c08041.L$2 = SpillingKt.nullOutSpilledVariable(accessToken);
            c08041.L$3 = SpillingKt.nullOutSpilledVariable(str2);
            c08041.label = 1;
            obj = Requests.get$default(app2, str2, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c08041, 4028, null);
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
        MalStatus myListStatus = ((SmallMalAnime) parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(SmallMalAnime.class))).getMyListStatus();
        return new SyncAPI.SyncStatus(SyncWatchType.INSTANCE.fromInternalId(Boxing.boxInt(ArraysKt.indexOf(malStatusAsString, myListStatus != null ? myListStatus.getStatus() : null))), Score.INSTANCE.from10(myListStatus != null ? Boxing.boxInt(myListStatus.getScore()) : null), myListStatus != null ? Boxing.boxInt(myListStatus.getNumEpisodesWatched()) : null, null, null, 16, null);
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010\u0011R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Companion;", "", "<init>", "()V", "malStatusAsString", "", "", "[Ljava/lang/String;", "MAL_CACHED_LIST", "convertToStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Companion$MalStatusType;", TypedValues.Custom.S_STRING, "fromIntToAnimeStatus", "inp", "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "parseDateLong", "", "(Ljava/lang/String;)Ljava/lang/Long;", "MalStatusType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: MALApi.kt */
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

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final MalStatusType convertToStatus(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            switch (string.hashCode()) {
                case -1433185695:
                    if (string.equals("plan_to_watch")) {
                        return MalStatusType.PlanToWatch;
                    }
                    break;
                case -1402931637:
                    if (string.equals("completed")) {
                        return MalStatusType.Completed;
                    }
                    break;
                case -1326157025:
                    if (string.equals("on_hold")) {
                        return MalStatusType.OnHold;
                    }
                    break;
                case 545156275:
                    if (string.equals("watching")) {
                        return MalStatusType.Watching;
                    }
                    break;
                case 1925736384:
                    if (string.equals("dropped")) {
                        return MalStatusType.Dropped;
                    }
                    break;
            }
            return MalStatusType.None;
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Companion$MalStatusType;", "", ES6Iterator.VALUE_PROPERTY, "", "stringRes", "<init>", "(Ljava/lang/String;III)V", "getValue", "()I", "setValue", "(I)V", "getStringRes", "Watching", "Completed", "OnHold", "Dropped", "PlanToWatch", "None", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum MalStatusType {
            Watching(0, R.string.type_watching),
            Completed(1, R.string.type_completed),
            OnHold(2, R.string.type_on_hold),
            Dropped(3, R.string.type_dropped),
            PlanToWatch(4, R.string.type_plan_to_watch),
            None(-1, R.string.type_none);

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
            private final int stringRes;
            private int value;

            public static EnumEntries<MalStatusType> getEntries() {
                return $ENTRIES;
            }

            MalStatusType(int i, int i2) {
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
        public final MalStatusType fromIntToAnimeStatus(SyncWatchType inp) {
            switch (WhenMappings.$EnumSwitchMapping$0[inp.ordinal()]) {
                case 1:
                    return MalStatusType.None;
                case 2:
                    return MalStatusType.Watching;
                case 3:
                    return MalStatusType.Completed;
                case 4:
                    return MalStatusType.OnHold;
                case 5:
                    return MalStatusType.Dropped;
                case 6:
                    return MalStatusType.PlanToWatch;
                case 7:
                    return MalStatusType.Watching;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Long parseDateLong(String string) {
            Date date;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
                if (string == null || (date = simpleDateFormat.parse(string)) == null) {
                    return null;
                }
                return Long.valueOf(date.getTime() / 1000);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public AuthLoginPage loginRequest() {
        String strGenerateCodeVerifier = AuthAPI.INSTANCE.generateCodeVerifier();
        int i = this.requestIdCounter + 1;
        this.requestIdCounter = i;
        return new AuthLoginPage(getMainUrl() + "/v1/oauth2/authorize?response_type=code&client_id=" + this.key + "&code_challenge=" + strGenerateCodeVerifier + "&state=RequestID" + i, AppUtils.INSTANCE.toJson(new Payload(i, strGenerateCodeVerifier)));
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object refreshToken(AuthToken authToken, Continuation<? super AuthToken> continuation) {
        C08011 c08011;
        if (continuation instanceof C08011) {
            c08011 = (C08011) continuation;
            if ((c08011.label & Integer.MIN_VALUE) != 0) {
                c08011.label -= Integer.MIN_VALUE;
            } else {
                c08011 = new C08011(continuation);
            }
        } else {
            c08011 = new C08011(continuation);
        }
        Object obj = c08011.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08011.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = getMainUrl() + "/v1/oauth2/token";
            String refreshToken = authToken.getRefreshToken();
            Intrinsics.checkNotNull(refreshToken);
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("client_id", this.key), TuplesKt.to("grant_type", "refresh_token"), TuplesKt.to("refresh_token", refreshToken));
            c08011.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c08011.label = 1;
            Object objPost$default = Requests.post$default(app2, str, null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c08011, 65502, null);
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

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B!\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J#\u0010\u0017\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalList;", "", "data", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Data;", "paging", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging;", "<init>", "(Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Ljava/util/List;", "getPaging$annotations", "getPaging", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalList {

        @JsonProperty("data")
        private final List<Data> data;

        @JsonProperty("paging")
        private final Paging paging;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalList$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalList._childSerializers$_anonymous_();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MALApi$Data$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MalList copy$default(MalList malList, List list, Paging paging, int i, Object obj) {
            if ((i & 1) != 0) {
                list = malList.data;
            }
            if ((i & 2) != 0) {
                paging = malList.paging;
            }
            return malList.copy(list, paging);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        @SerialName("paging")
        public static /* synthetic */ void getPaging$annotations() {
        }

        public final List<Data> component1() {
            return this.data;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Paging getPaging() {
            return this.paging;
        }

        public final MalList copy(@JsonProperty("data") List<Data> data, @JsonProperty("paging") Paging paging) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(paging, "paging");
            return new MalList(data, paging);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalList)) {
                return false;
            }
            MalList malList = (MalList) other;
            return Intrinsics.areEqual(this.data, malList.data) && Intrinsics.areEqual(this.paging, malList.paging);
        }

        public int hashCode() {
            return (this.data.hashCode() * 31) + this.paging.hashCode();
        }

        public String toString() {
            return "MalList(data=" + this.data + ", paging=" + this.paging + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalList$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalList;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalList> serializer() {
                return MALApi$MalList$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalList(int i, List list, Paging paging, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$MalList$$serializer.INSTANCE.getDescriptor());
            }
            this.data = list;
            this.paging = paging;
        }

        public MalList(@JsonProperty("data") List<Data> data, @JsonProperty("paging") Paging paging) {
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(paging, "paging");
            this.data = data;
            this.paging = paging;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalList self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, $childSerializers[0].getValue(), self.data);
            output.encodeSerializableElement(serialDesc, 1, MALApi$Paging$$serializer.INSTANCE, self.paging);
        }

        public final List<Data> getData() {
            return this.data;
        }

        public final Paging getPaging() {
            return this.paging;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0014\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;", "", "medium", "", "large", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMedium$annotations", "()V", "getMedium", "()Ljava/lang/String;", "getLarge$annotations", "getLarge", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MainPicture {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ MainPicture copy$default(MainPicture mainPicture, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mainPicture.medium;
            }
            if ((i & 2) != 0) {
                str2 = mainPicture.large;
            }
            return mainPicture.copy(str, str2);
        }

        @SerialName("large")
        public static /* synthetic */ void getLarge$annotations() {
        }

        @SerialName("medium")
        public static /* synthetic */ void getMedium$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMedium() {
            return this.medium;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLarge() {
            return this.large;
        }

        public final MainPicture copy(@JsonProperty("medium") String medium, @JsonProperty("large") String large) {
            Intrinsics.checkNotNullParameter(medium, "medium");
            Intrinsics.checkNotNullParameter(large, "large");
            return new MainPicture(medium, large);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MainPicture)) {
                return false;
            }
            MainPicture mainPicture = (MainPicture) other;
            return Intrinsics.areEqual(this.medium, mainPicture.medium) && Intrinsics.areEqual(this.large, mainPicture.large);
        }

        public int hashCode() {
            return (this.medium.hashCode() * 31) + this.large.hashCode();
        }

        public String toString() {
            return "MainPicture(medium=" + this.medium + ", large=" + this.large + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MainPicture> serializer() {
                return MALApi$MainPicture$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MainPicture(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$MainPicture$$serializer.INSTANCE.getDescriptor());
            }
            this.medium = str;
            this.large = str2;
        }

        public MainPicture(@JsonProperty("medium") String medium, @JsonProperty("large") String large) {
            Intrinsics.checkNotNullParameter(medium, "medium");
            Intrinsics.checkNotNullParameter(large, "large");
            this.medium = medium;
            this.large = large;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MainPicture self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.medium);
            output.encodeStringElement(serialDesc, 1, self.large);
        }

        public final String getMedium() {
            return this.medium;
        }

        public final String getLarge() {
            return this.large;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\bU\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u0089\u00012\u00020\u0001:\u0004\u0088\u0001\u0089\u0001B\u009d\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\"\u0010#B\u0085\u0002\b\u0010\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0005\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010%\u001a\u0004\u0018\u00010&¢\u0006\u0004\b\"\u0010'J\t\u0010b\u001a\u00020\u0003HÆ\u0003J\t\u0010c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u000b\u0010h\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010k\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u000b\u0010l\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010m\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u0010GJ\u0011\u0010n\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014HÆ\u0003J\u0010\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u0010\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u0010\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u0010\u0010r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u0010\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u00109J\u000b\u0010t\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u001eHÆ\u0003J\u000b\u0010v\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010w\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0005HÆ\u0003J¤\u0002\u0010y\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0010\b\u0003\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010zJ\u0014\u0010{\u001a\u00020|2\b\u0010}\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010~\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u007f\u001a\u00020\u0005HÖ\u0081\u0004J-\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u00002\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001H\u0001¢\u0006\u0003\b\u0087\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b,\u0010)\u001a\u0004\b-\u0010.R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010)\u001a\u0004\b0\u00101R\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010)\u001a\u0004\b3\u00104R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010)\u001a\u0004\b6\u0010.R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\b7\u0010)\u001a\u0004\b8\u00109R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u0010)\u001a\u0004\b<\u0010.R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b=\u0010)\u001a\u0004\b>\u0010.R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b?\u0010)\u001a\u0004\b@\u0010.R \u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\bA\u0010)\u001a\u0004\bB\u00109R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bC\u0010)\u001a\u0004\bD\u0010.R \u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010H\u0012\u0004\bE\u0010)\u001a\u0004\bF\u0010GR$\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bI\u0010)\u001a\u0004\bJ\u0010KR \u0010\u0016\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\bL\u0010)\u001a\u0004\bM\u00109R \u0010\u0017\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\bN\u0010)\u001a\u0004\bO\u00109R \u0010\u0018\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\bP\u0010)\u001a\u0004\bQ\u00109R \u0010\u0019\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\bR\u0010)\u001a\u0004\bS\u00109R \u0010\u001a\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010:\u0012\u0004\bT\u0010)\u001a\u0004\bU\u00109R\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u001c8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bV\u0010)\u001a\u0004\bW\u0010XR\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bY\u0010)\u001a\u0004\bZ\u0010[R\u001e\u0010\u001f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\\\u0010)\u001a\u0004\b]\u0010.R\u001e\u0010 \u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b^\u0010)\u001a\u0004\b_\u0010.R\u001e\u0010!\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b`\u0010)\u001a\u0004\ba\u0010.¨\u0006\u008a\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "", "id", "", "title", "", "mainPicture", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;", "alternativeTitles", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;", "mediaType", "numEpisodes", NotificationCompat.CATEGORY_STATUS, "startDate", "endDate", "averageEpisodeDuration", "synopsis", "mean", "", "genres", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Genres;", "rank", "popularity", "numListUsers", "numFavorites", "numScoringUsers", "startSeason", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;", "broadcast", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;", "nsfw", "createdAt", "updatedAt", "<init>", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "getMainPicture$annotations", "getMainPicture", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;", "getAlternativeTitles$annotations", "getAlternativeTitles", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;", "getMediaType$annotations", "getMediaType", "getNumEpisodes$annotations", "getNumEpisodes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatus$annotations", "getStatus", "getStartDate$annotations", "getStartDate", "getEndDate$annotations", "getEndDate", "getAverageEpisodeDuration$annotations", "getAverageEpisodeDuration", "getSynopsis$annotations", "getSynopsis", "getMean$annotations", "getMean", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getGenres$annotations", "getGenres", "()Ljava/util/List;", "getRank$annotations", "getRank", "getPopularity$annotations", "getPopularity", "getNumListUsers$annotations", "getNumListUsers", "getNumFavorites$annotations", "getNumFavorites", "getNumScoringUsers$annotations", "getNumScoringUsers", "getStartSeason$annotations", "getStartSeason", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;", "getBroadcast$annotations", "getBroadcast", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;", "getNsfw$annotations", "getNsfw", "getCreatedAt$annotations", "getCreatedAt", "getUpdatedAt$annotations", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MainPicture;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Node {

        @JsonProperty("alternative_titles")
        private final AlternativeTitles alternativeTitles;

        @JsonProperty("average_episode_duration")
        private final Integer averageEpisodeDuration;

        @JsonProperty("broadcast")
        private final Broadcast broadcast;

        @JsonProperty("created_at")
        private final String createdAt;

        @JsonProperty("end_date")
        private final String endDate;

        @JsonProperty("genres")
        private final List<Genres> genres;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("main_picture")
        private final MainPicture mainPicture;

        @JsonProperty("mean")
        private final Double mean;

        @JsonProperty("media_type")
        private final String mediaType;

        @JsonProperty("nsfw")
        private final String nsfw;

        @JsonProperty("num_episodes")
        private final Integer numEpisodes;

        @JsonProperty("num_favorites")
        private final Integer numFavorites;

        @JsonProperty("num_list_users")
        private final Integer numListUsers;

        @JsonProperty("num_scoring_users")
        private final Integer numScoringUsers;

        @JsonProperty("popularity")
        private final Integer popularity;

        @JsonProperty("rank")
        private final Integer rank;

        @JsonProperty("start_date")
        private final String startDate;

        @JsonProperty("start_season")
        private final StartSeason startSeason;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("synopsis")
        private final String synopsis;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("updated_at")
        private final String updatedAt;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$Node$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.Node._childSerializers$_anonymous_();
            }
        }), null, null, null, null, null, null, null, null, null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MALApi$Genres$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Node copy$default(Node node, int i, String str, MainPicture mainPicture, AlternativeTitles alternativeTitles, String str2, Integer num, String str3, String str4, String str5, Integer num2, String str6, Double d, List list, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, StartSeason startSeason, Broadcast broadcast, String str7, String str8, String str9, int i2, Object obj) {
            String str10;
            String str11;
            int i3 = (i2 & 1) != 0 ? node.id : i;
            String str12 = (i2 & 2) != 0 ? node.title : str;
            MainPicture mainPicture2 = (i2 & 4) != 0 ? node.mainPicture : mainPicture;
            AlternativeTitles alternativeTitles2 = (i2 & 8) != 0 ? node.alternativeTitles : alternativeTitles;
            String str13 = (i2 & 16) != 0 ? node.mediaType : str2;
            Integer num8 = (i2 & 32) != 0 ? node.numEpisodes : num;
            String str14 = (i2 & 64) != 0 ? node.status : str3;
            String str15 = (i2 & 128) != 0 ? node.startDate : str4;
            String str16 = (i2 & 256) != 0 ? node.endDate : str5;
            Integer num9 = (i2 & 512) != 0 ? node.averageEpisodeDuration : num2;
            String str17 = (i2 & 1024) != 0 ? node.synopsis : str6;
            Double d2 = (i2 & 2048) != 0 ? node.mean : d;
            List list2 = (i2 & 4096) != 0 ? node.genres : list;
            Integer num10 = (i2 & 8192) != 0 ? node.rank : num3;
            int i4 = i3;
            Integer num11 = (i2 & 16384) != 0 ? node.popularity : num4;
            Integer num12 = (i2 & 32768) != 0 ? node.numListUsers : num5;
            Integer num13 = (i2 & 65536) != 0 ? node.numFavorites : num6;
            Integer num14 = (i2 & 131072) != 0 ? node.numScoringUsers : num7;
            StartSeason startSeason2 = (i2 & 262144) != 0 ? node.startSeason : startSeason;
            Broadcast broadcast2 = (i2 & 524288) != 0 ? node.broadcast : broadcast;
            String str18 = (i2 & 1048576) != 0 ? node.nsfw : str7;
            String str19 = (i2 & 2097152) != 0 ? node.createdAt : str8;
            if ((i2 & 4194304) != 0) {
                str11 = str19;
                str10 = node.updatedAt;
            } else {
                str10 = str9;
                str11 = str19;
            }
            return node.copy(i4, str12, mainPicture2, alternativeTitles2, str13, num8, str14, str15, str16, num9, str17, d2, list2, num10, num11, num12, num13, num14, startSeason2, broadcast2, str18, str11, str10);
        }

        @SerialName("alternative_titles")
        public static /* synthetic */ void getAlternativeTitles$annotations() {
        }

        @SerialName("average_episode_duration")
        public static /* synthetic */ void getAverageEpisodeDuration$annotations() {
        }

        @SerialName("broadcast")
        public static /* synthetic */ void getBroadcast$annotations() {
        }

        @SerialName("created_at")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @SerialName("end_date")
        public static /* synthetic */ void getEndDate$annotations() {
        }

        @SerialName("genres")
        public static /* synthetic */ void getGenres$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("main_picture")
        public static /* synthetic */ void getMainPicture$annotations() {
        }

        @SerialName("mean")
        public static /* synthetic */ void getMean$annotations() {
        }

        @SerialName("media_type")
        public static /* synthetic */ void getMediaType$annotations() {
        }

        @SerialName("nsfw")
        public static /* synthetic */ void getNsfw$annotations() {
        }

        @SerialName("num_episodes")
        public static /* synthetic */ void getNumEpisodes$annotations() {
        }

        @SerialName("num_favorites")
        public static /* synthetic */ void getNumFavorites$annotations() {
        }

        @SerialName("num_list_users")
        public static /* synthetic */ void getNumListUsers$annotations() {
        }

        @SerialName("num_scoring_users")
        public static /* synthetic */ void getNumScoringUsers$annotations() {
        }

        @SerialName("popularity")
        public static /* synthetic */ void getPopularity$annotations() {
        }

        @SerialName("rank")
        public static /* synthetic */ void getRank$annotations() {
        }

        @SerialName("start_date")
        public static /* synthetic */ void getStartDate$annotations() {
        }

        @SerialName("start_season")
        public static /* synthetic */ void getStartSeason$annotations() {
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

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getAverageEpisodeDuration() {
            return this.averageEpisodeDuration;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getSynopsis() {
            return this.synopsis;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Double getMean() {
            return this.mean;
        }

        public final List<Genres> component13() {
            return this.genres;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Integer getRank() {
            return this.rank;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Integer getPopularity() {
            return this.popularity;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Integer getNumListUsers() {
            return this.numListUsers;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final Integer getNumFavorites() {
            return this.numFavorites;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final Integer getNumScoringUsers() {
            return this.numScoringUsers;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final StartSeason getStartSeason() {
            return this.startSeason;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final Broadcast getBroadcast() {
            return this.broadcast;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final String getNsfw() {
            return this.nsfw;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MainPicture getMainPicture() {
            return this.mainPicture;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final AlternativeTitles getAlternativeTitles() {
            return this.alternativeTitles;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMediaType() {
            return this.mediaType;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getNumEpisodes() {
            return this.numEpisodes;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getStartDate() {
            return this.startDate;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getEndDate() {
            return this.endDate;
        }

        public final Node copy(@JsonProperty("id") int id, @JsonProperty("title") String title, @JsonProperty("main_picture") MainPicture mainPicture, @JsonProperty("alternative_titles") AlternativeTitles alternativeTitles, @JsonProperty("media_type") String mediaType, @JsonProperty("num_episodes") Integer numEpisodes, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("start_date") String startDate, @JsonProperty("end_date") String endDate, @JsonProperty("average_episode_duration") Integer averageEpisodeDuration, @JsonProperty("synopsis") String synopsis, @JsonProperty("mean") Double mean, @JsonProperty("genres") List<Genres> genres, @JsonProperty("rank") Integer rank, @JsonProperty("popularity") Integer popularity, @JsonProperty("num_list_users") Integer numListUsers, @JsonProperty("num_favorites") Integer numFavorites, @JsonProperty("num_scoring_users") Integer numScoringUsers, @JsonProperty("start_season") StartSeason startSeason, @JsonProperty("broadcast") Broadcast broadcast, @JsonProperty("nsfw") String nsfw, @JsonProperty("created_at") String createdAt, @JsonProperty("updated_at") String updatedAt) {
            Intrinsics.checkNotNullParameter(title, "title");
            return new Node(id, title, mainPicture, alternativeTitles, mediaType, numEpisodes, status, startDate, endDate, averageEpisodeDuration, synopsis, mean, genres, rank, popularity, numListUsers, numFavorites, numScoringUsers, startSeason, broadcast, nsfw, createdAt, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node node = (Node) other;
            return this.id == node.id && Intrinsics.areEqual(this.title, node.title) && Intrinsics.areEqual(this.mainPicture, node.mainPicture) && Intrinsics.areEqual(this.alternativeTitles, node.alternativeTitles) && Intrinsics.areEqual(this.mediaType, node.mediaType) && Intrinsics.areEqual(this.numEpisodes, node.numEpisodes) && Intrinsics.areEqual(this.status, node.status) && Intrinsics.areEqual(this.startDate, node.startDate) && Intrinsics.areEqual(this.endDate, node.endDate) && Intrinsics.areEqual(this.averageEpisodeDuration, node.averageEpisodeDuration) && Intrinsics.areEqual(this.synopsis, node.synopsis) && Intrinsics.areEqual((Object) this.mean, (Object) node.mean) && Intrinsics.areEqual(this.genres, node.genres) && Intrinsics.areEqual(this.rank, node.rank) && Intrinsics.areEqual(this.popularity, node.popularity) && Intrinsics.areEqual(this.numListUsers, node.numListUsers) && Intrinsics.areEqual(this.numFavorites, node.numFavorites) && Intrinsics.areEqual(this.numScoringUsers, node.numScoringUsers) && Intrinsics.areEqual(this.startSeason, node.startSeason) && Intrinsics.areEqual(this.broadcast, node.broadcast) && Intrinsics.areEqual(this.nsfw, node.nsfw) && Intrinsics.areEqual(this.createdAt, node.createdAt) && Intrinsics.areEqual(this.updatedAt, node.updatedAt);
        }

        public int hashCode() {
            int iHashCode = ((this.id * 31) + this.title.hashCode()) * 31;
            MainPicture mainPicture = this.mainPicture;
            int iHashCode2 = (iHashCode + (mainPicture == null ? 0 : mainPicture.hashCode())) * 31;
            AlternativeTitles alternativeTitles = this.alternativeTitles;
            int iHashCode3 = (iHashCode2 + (alternativeTitles == null ? 0 : alternativeTitles.hashCode())) * 31;
            String str = this.mediaType;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.numEpisodes;
            int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
            String str2 = this.status;
            int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.startDate;
            int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.endDate;
            int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Integer num2 = this.averageEpisodeDuration;
            int iHashCode9 = (iHashCode8 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str5 = this.synopsis;
            int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
            Double d = this.mean;
            int iHashCode11 = (iHashCode10 + (d == null ? 0 : d.hashCode())) * 31;
            List<Genres> list = this.genres;
            int iHashCode12 = (iHashCode11 + (list == null ? 0 : list.hashCode())) * 31;
            Integer num3 = this.rank;
            int iHashCode13 = (iHashCode12 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.popularity;
            int iHashCode14 = (iHashCode13 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.numListUsers;
            int iHashCode15 = (iHashCode14 + (num5 == null ? 0 : num5.hashCode())) * 31;
            Integer num6 = this.numFavorites;
            int iHashCode16 = (iHashCode15 + (num6 == null ? 0 : num6.hashCode())) * 31;
            Integer num7 = this.numScoringUsers;
            int iHashCode17 = (iHashCode16 + (num7 == null ? 0 : num7.hashCode())) * 31;
            StartSeason startSeason = this.startSeason;
            int iHashCode18 = (iHashCode17 + (startSeason == null ? 0 : startSeason.hashCode())) * 31;
            Broadcast broadcast = this.broadcast;
            int iHashCode19 = (iHashCode18 + (broadcast == null ? 0 : broadcast.hashCode())) * 31;
            String str6 = this.nsfw;
            int iHashCode20 = (iHashCode19 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.createdAt;
            int iHashCode21 = (iHashCode20 + (str7 == null ? 0 : str7.hashCode())) * 31;
            String str8 = this.updatedAt;
            return iHashCode21 + (str8 != null ? str8.hashCode() : 0);
        }

        public String toString() {
            return "Node(id=" + this.id + ", title=" + this.title + ", mainPicture=" + this.mainPicture + ", alternativeTitles=" + this.alternativeTitles + ", mediaType=" + this.mediaType + ", numEpisodes=" + this.numEpisodes + ", status=" + this.status + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", averageEpisodeDuration=" + this.averageEpisodeDuration + ", synopsis=" + this.synopsis + ", mean=" + this.mean + ", genres=" + this.genres + ", rank=" + this.rank + ", popularity=" + this.popularity + ", numListUsers=" + this.numListUsers + ", numFavorites=" + this.numFavorites + ", numScoringUsers=" + this.numScoringUsers + ", startSeason=" + this.startSeason + ", broadcast=" + this.broadcast + ", nsfw=" + this.nsfw + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Node> serializer() {
                return MALApi$Node$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Node(int i, int i2, String str, MainPicture mainPicture, AlternativeTitles alternativeTitles, String str2, Integer num, String str3, String str4, String str5, Integer num2, String str6, Double d, List list, Integer num3, Integer num4, Integer num5, Integer num6, Integer num7, StartSeason startSeason, Broadcast broadcast, String str7, String str8, String str9, SerializationConstructorMarker serializationConstructorMarker) {
            if (8388607 != (i & 8388607)) {
                PluginExceptionsKt.throwMissingFieldException(i, 8388607, MALApi$Node$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.title = str;
            this.mainPicture = mainPicture;
            this.alternativeTitles = alternativeTitles;
            this.mediaType = str2;
            this.numEpisodes = num;
            this.status = str3;
            this.startDate = str4;
            this.endDate = str5;
            this.averageEpisodeDuration = num2;
            this.synopsis = str6;
            this.mean = d;
            this.genres = list;
            this.rank = num3;
            this.popularity = num4;
            this.numListUsers = num5;
            this.numFavorites = num6;
            this.numScoringUsers = num7;
            this.startSeason = startSeason;
            this.broadcast = broadcast;
            this.nsfw = str7;
            this.createdAt = str8;
            this.updatedAt = str9;
        }

        public Node(@JsonProperty("id") int i, @JsonProperty("title") String title, @JsonProperty("main_picture") MainPicture mainPicture, @JsonProperty("alternative_titles") AlternativeTitles alternativeTitles, @JsonProperty("media_type") String str, @JsonProperty("num_episodes") Integer num, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String str2, @JsonProperty("start_date") String str3, @JsonProperty("end_date") String str4, @JsonProperty("average_episode_duration") Integer num2, @JsonProperty("synopsis") String str5, @JsonProperty("mean") Double d, @JsonProperty("genres") List<Genres> list, @JsonProperty("rank") Integer num3, @JsonProperty("popularity") Integer num4, @JsonProperty("num_list_users") Integer num5, @JsonProperty("num_favorites") Integer num6, @JsonProperty("num_scoring_users") Integer num7, @JsonProperty("start_season") StartSeason startSeason, @JsonProperty("broadcast") Broadcast broadcast, @JsonProperty("nsfw") String str6, @JsonProperty("created_at") String str7, @JsonProperty("updated_at") String str8) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.id = i;
            this.title = title;
            this.mainPicture = mainPicture;
            this.alternativeTitles = alternativeTitles;
            this.mediaType = str;
            this.numEpisodes = num;
            this.status = str2;
            this.startDate = str3;
            this.endDate = str4;
            this.averageEpisodeDuration = num2;
            this.synopsis = str5;
            this.mean = d;
            this.genres = list;
            this.rank = num3;
            this.popularity = num4;
            this.numListUsers = num5;
            this.numFavorites = num6;
            this.numScoringUsers = num7;
            this.startSeason = startSeason;
            this.broadcast = broadcast;
            this.nsfw = str6;
            this.createdAt = str7;
            this.updatedAt = str8;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Node self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, MALApi$MainPicture$$serializer.INSTANCE, self.mainPicture);
            output.encodeNullableSerializableElement(serialDesc, 3, MALApi$AlternativeTitles$$serializer.INSTANCE, self.alternativeTitles);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.mediaType);
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.numEpisodes);
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.status);
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.startDate);
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.endDate);
            output.encodeNullableSerializableElement(serialDesc, 9, IntSerializer.INSTANCE, self.averageEpisodeDuration);
            output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.synopsis);
            output.encodeNullableSerializableElement(serialDesc, 11, DoubleSerializer.INSTANCE, self.mean);
            output.encodeNullableSerializableElement(serialDesc, 12, lazyArr[12].getValue(), self.genres);
            output.encodeNullableSerializableElement(serialDesc, 13, IntSerializer.INSTANCE, self.rank);
            output.encodeNullableSerializableElement(serialDesc, 14, IntSerializer.INSTANCE, self.popularity);
            output.encodeNullableSerializableElement(serialDesc, 15, IntSerializer.INSTANCE, self.numListUsers);
            output.encodeNullableSerializableElement(serialDesc, 16, IntSerializer.INSTANCE, self.numFavorites);
            output.encodeNullableSerializableElement(serialDesc, 17, IntSerializer.INSTANCE, self.numScoringUsers);
            output.encodeNullableSerializableElement(serialDesc, 18, MALApi$StartSeason$$serializer.INSTANCE, self.startSeason);
            output.encodeNullableSerializableElement(serialDesc, 19, MALApi$Broadcast$$serializer.INSTANCE, self.broadcast);
            output.encodeNullableSerializableElement(serialDesc, 20, StringSerializer.INSTANCE, self.nsfw);
            output.encodeNullableSerializableElement(serialDesc, 21, StringSerializer.INSTANCE, self.createdAt);
            output.encodeNullableSerializableElement(serialDesc, 22, StringSerializer.INSTANCE, self.updatedAt);
        }

        public final int getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final MainPicture getMainPicture() {
            return this.mainPicture;
        }

        public final AlternativeTitles getAlternativeTitles() {
            return this.alternativeTitles;
        }

        public final String getMediaType() {
            return this.mediaType;
        }

        public final Integer getNumEpisodes() {
            return this.numEpisodes;
        }

        public final String getStatus() {
            return this.status;
        }

        public final String getStartDate() {
            return this.startDate;
        }

        public final String getEndDate() {
            return this.endDate;
        }

        public final Integer getAverageEpisodeDuration() {
            return this.averageEpisodeDuration;
        }

        public final String getSynopsis() {
            return this.synopsis;
        }

        public final Double getMean() {
            return this.mean;
        }

        public final List<Genres> getGenres() {
            return this.genres;
        }

        public final Integer getRank() {
            return this.rank;
        }

        public final Integer getPopularity() {
            return this.popularity;
        }

        public final Integer getNumListUsers() {
            return this.numListUsers;
        }

        public final Integer getNumFavorites() {
            return this.numFavorites;
        }

        public final Integer getNumScoringUsers() {
            return this.numScoringUsers;
        }

        public final StartSeason getStartSeason() {
            return this.startSeason;
        }

        public final Broadcast getBroadcast() {
            return this.broadcast;
        }

        public final String getNsfw() {
            return this.nsfw;
        }

        public final String getCreatedAt() {
            return this.createdAt;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B;\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bBG\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J=\u0010\"\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0016R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u0007\u0010\u001aR\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0013¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus;", "", NotificationCompat.CATEGORY_STATUS, "", "score", "", "numEpisodesWatched", "isRewatching", "", "updatedAt", "<init>", "(Ljava/lang/String;IIZLjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;IIZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Ljava/lang/String;", "getScore$annotations", "getScore", "()I", "getNumEpisodesWatched$annotations", "getNumEpisodesWatched", "isRewatching$annotations", "()Z", "getUpdatedAt$annotations", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class ListStatus {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("is_rewatching")
        private final boolean isRewatching;

        @JsonProperty("num_episodes_watched")
        private final int numEpisodesWatched;

        @JsonProperty("score")
        private final int score;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("updated_at")
        private final String updatedAt;

        public static /* synthetic */ ListStatus copy$default(ListStatus listStatus, String str, int i, int i2, boolean z, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = listStatus.status;
            }
            if ((i3 & 2) != 0) {
                i = listStatus.score;
            }
            if ((i3 & 4) != 0) {
                i2 = listStatus.numEpisodesWatched;
            }
            if ((i3 & 8) != 0) {
                z = listStatus.isRewatching;
            }
            if ((i3 & 16) != 0) {
                str2 = listStatus.updatedAt;
            }
            String str3 = str2;
            int i4 = i2;
            return listStatus.copy(str, i, i4, z, str3);
        }

        @SerialName("num_episodes_watched")
        public static /* synthetic */ void getNumEpisodesWatched$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName("is_rewatching")
        public static /* synthetic */ void isRewatching$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getNumEpisodesWatched() {
            return this.numEpisodesWatched;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsRewatching() {
            return this.isRewatching;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final ListStatus copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("score") int score, @JsonProperty("num_episodes_watched") int numEpisodesWatched, @JsonProperty("is_rewatching") boolean isRewatching, @JsonProperty("updated_at") String updatedAt) {
            Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
            return new ListStatus(status, score, numEpisodesWatched, isRewatching, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ListStatus)) {
                return false;
            }
            ListStatus listStatus = (ListStatus) other;
            return Intrinsics.areEqual(this.status, listStatus.status) && this.score == listStatus.score && this.numEpisodesWatched == listStatus.numEpisodesWatched && this.isRewatching == listStatus.isRewatching && Intrinsics.areEqual(this.updatedAt, listStatus.updatedAt);
        }

        public int hashCode() {
            String str = this.status;
            return ((((((((str == null ? 0 : str.hashCode()) * 31) + this.score) * 31) + this.numEpisodesWatched) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isRewatching)) * 31) + this.updatedAt.hashCode();
        }

        public String toString() {
            return "ListStatus(status=" + this.status + ", score=" + this.score + ", numEpisodesWatched=" + this.numEpisodesWatched + ", isRewatching=" + this.isRewatching + ", updatedAt=" + this.updatedAt + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ListStatus> serializer() {
                return MALApi$ListStatus$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ListStatus(int i, String str, int i2, int i3, boolean z, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MALApi$ListStatus$$serializer.INSTANCE.getDescriptor());
            }
            this.status = str;
            this.score = i2;
            this.numEpisodesWatched = i3;
            this.isRewatching = z;
            this.updatedAt = str2;
        }

        public ListStatus(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String str, @JsonProperty("score") int i, @JsonProperty("num_episodes_watched") int i2, @JsonProperty("is_rewatching") boolean z, @JsonProperty("updated_at") String updatedAt) {
            Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
            this.status = str;
            this.score = i;
            this.numEpisodesWatched = i2;
            this.isRewatching = z;
            this.updatedAt = updatedAt;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(ListStatus self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.status);
            output.encodeIntElement(serialDesc, 1, self.score);
            output.encodeIntElement(serialDesc, 2, self.numEpisodesWatched);
            output.encodeBooleanElement(serialDesc, 3, self.isRewatching);
            output.encodeStringElement(serialDesc, 4, self.updatedAt);
        }

        public final String getStatus() {
            return this.status;
        }

        public final int getScore() {
            return this.score;
        }

        public final int getNumEpisodesWatched() {
            return this.numEpisodesWatched;
        }

        public final boolean isRewatching() {
            return this.isRewatching;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J%\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\b&R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Data;", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "listStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNode$annotations", "()V", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "getListStatus$annotations", "getListStatus", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ListStatus;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Data {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("list_status")
        private final ListStatus listStatus;

        @JsonProperty("node")
        private final Node node;

        public static /* synthetic */ Data copy$default(Data data, Node node, ListStatus listStatus, int i, Object obj) {
            if ((i & 1) != 0) {
                node = data.node;
            }
            if ((i & 2) != 0) {
                listStatus = data.listStatus;
            }
            return data.copy(node, listStatus);
        }

        @SerialName("list_status")
        public static /* synthetic */ void getListStatus$annotations() {
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Node getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final ListStatus getListStatus() {
            return this.listStatus;
        }

        public final Data copy(@JsonProperty("node") Node node, @JsonProperty("list_status") ListStatus listStatus) {
            Intrinsics.checkNotNullParameter(node, "node");
            return new Data(node, listStatus);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.node, data.node) && Intrinsics.areEqual(this.listStatus, data.listStatus);
        }

        public int hashCode() {
            int iHashCode = this.node.hashCode() * 31;
            ListStatus listStatus = this.listStatus;
            return iHashCode + (listStatus == null ? 0 : listStatus.hashCode());
        }

        public String toString() {
            return "Data(node=" + this.node + ", listStatus=" + this.listStatus + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Data$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Data;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Data> serializer() {
                return MALApi$Data$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Data(int i, Node node, ListStatus listStatus, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$Data$$serializer.INSTANCE.getDescriptor());
            }
            this.node = node;
            this.listStatus = listStatus;
        }

        public Data(@JsonProperty("node") Node node, @JsonProperty("list_status") ListStatus listStatus) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
            this.listStatus = listStatus;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Data self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, MALApi$Node$$serializer.INSTANCE, self.node);
            output.encodeNullableSerializableElement(serialDesc, 1, MALApi$ListStatus$$serializer.INSTANCE, self.listStatus);
        }

        public final Node getNode() {
            return this.node;
        }

        public final ListStatus getListStatus() {
            return this.listStatus;
        }

        public final SyncAPI.LibraryItem toLibraryItem() {
            String str;
            String str2;
            String title = this.node.getTitle();
            String str3 = "https://myanimelist.net/anime/" + this.node.getId() + '/';
            String strValueOf = String.valueOf(this.node.getId());
            ListStatus listStatus = this.listStatus;
            Date dateFrom = null;
            Integer numValueOf = listStatus != null ? Integer.valueOf(listStatus.getNumEpisodesWatched()) : null;
            Integer numEpisodes = this.node.getNumEpisodes();
            Score.Companion companion = Score.INSTANCE;
            ListStatus listStatus2 = this.listStatus;
            Score scoreFrom10 = companion.from10(listStatus2 != null ? Integer.valueOf(listStatus2.getScore()) : null);
            Companion companion2 = MALApi.INSTANCE;
            ListStatus listStatus3 = this.listStatus;
            Long dateLong = companion2.parseDateLong(listStatus3 != null ? listStatus3.getUpdatedAt() : null);
            TvType tvType = TvType.Anime;
            MainPicture mainPicture = this.node.getMainPicture();
            if (mainPicture == null || (medium = mainPicture.getLarge()) == null) {
                MainPicture mainPicture2 = this.node.getMainPicture();
                if (mainPicture2 != null) {
                    String medium = mainPicture2.getMedium();
                    str = medium;
                } else {
                    str = null;
                }
            } else {
                str = medium;
            }
            String synopsis = this.node.getSynopsis();
            if (this.node.getStartDate() != null) {
                try {
                    if (this.node.getStartDate().length() == 4) {
                        str2 = "yyyy";
                    } else {
                        str2 = this.node.getStartDate().length() == 7 ? "yyyy-MM" : TmdbHelper.TMDB_DATE_PATTERN;
                    }
                    dateFrom = DesugarDate.from(Instant.from(DateTimeFormatter.ofPattern(str2).parse(this.node.getStartDate())));
                } catch (RuntimeException unused) {
                }
            }
            return new SyncAPI.LibraryItem(title, str3, strValueOf, numValueOf, numEpisodes, scoreFrom10, dateLong, "MAL", tvType, str, null, null, dateFrom, null, synopsis, null, null, 106496, null);
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging;", "", "next", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNext$annotations", "()V", "getNext", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Paging {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("next")
        private final String next;

        public static /* synthetic */ Paging copy$default(Paging paging, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = paging.next;
            }
            return paging.copy(str);
        }

        @SerialName("next")
        public static /* synthetic */ void getNext$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getNext() {
            return this.next;
        }

        public final Paging copy(@JsonProperty("next") String next) {
            return new Paging(next);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Paging) && Intrinsics.areEqual(this.next, ((Paging) other).next);
        }

        public int hashCode() {
            String str = this.next;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Paging(next=" + this.next + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Paging;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Paging> serializer() {
                return MALApi$Paging$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Paging(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, MALApi$Paging$$serializer.INSTANCE.getDescriptor());
            }
            this.next = str;
        }

        public Paging(@JsonProperty("next") String str) {
            this.next = str;
        }

        public final String getNext() {
            return this.next;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()B+\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bB?\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J-\u0010\u001a\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00042\b\b\u0003\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0004HÖ\u0081\u0004J%\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0001¢\u0006\u0002\b'R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0014¨\u0006*"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;", "", "synonyms", "", "", "en", "ja", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSynonyms$annotations", "()V", "getSynonyms", "()Ljava/util/List;", "getEn$annotations", "getEn", "()Ljava/lang/String;", "getJa$annotations", "getJa", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AlternativeTitles {

        @JsonProperty("en")
        private final String en;

        @JsonProperty("ja")
        private final String ja;

        @JsonProperty("synonyms")
        private final List<String> synonyms;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$AlternativeTitles$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.AlternativeTitles._childSerializers$_anonymous_();
            }
        }), null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AlternativeTitles copy$default(AlternativeTitles alternativeTitles, List list, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = alternativeTitles.synonyms;
            }
            if ((i & 2) != 0) {
                str = alternativeTitles.en;
            }
            if ((i & 4) != 0) {
                str2 = alternativeTitles.ja;
            }
            return alternativeTitles.copy(list, str, str2);
        }

        @SerialName("en")
        public static /* synthetic */ void getEn$annotations() {
        }

        @SerialName("ja")
        public static /* synthetic */ void getJa$annotations() {
        }

        @SerialName("synonyms")
        public static /* synthetic */ void getSynonyms$annotations() {
        }

        public final List<String> component1() {
            return this.synonyms;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEn() {
            return this.en;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getJa() {
            return this.ja;
        }

        public final AlternativeTitles copy(@JsonProperty("synonyms") List<String> synonyms, @JsonProperty("en") String en, @JsonProperty("ja") String ja) {
            Intrinsics.checkNotNullParameter(synonyms, "synonyms");
            Intrinsics.checkNotNullParameter(en, "en");
            Intrinsics.checkNotNullParameter(ja, "ja");
            return new AlternativeTitles(synonyms, en, ja);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AlternativeTitles)) {
                return false;
            }
            AlternativeTitles alternativeTitles = (AlternativeTitles) other;
            return Intrinsics.areEqual(this.synonyms, alternativeTitles.synonyms) && Intrinsics.areEqual(this.en, alternativeTitles.en) && Intrinsics.areEqual(this.ja, alternativeTitles.ja);
        }

        public int hashCode() {
            return (((this.synonyms.hashCode() * 31) + this.en.hashCode()) * 31) + this.ja.hashCode();
        }

        public String toString() {
            return "AlternativeTitles(synonyms=" + this.synonyms + ", en=" + this.en + ", ja=" + this.ja + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$AlternativeTitles;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AlternativeTitles> serializer() {
                return MALApi$AlternativeTitles$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AlternativeTitles(int i, List list, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, MALApi$AlternativeTitles$$serializer.INSTANCE.getDescriptor());
            }
            this.synonyms = list;
            this.en = str;
            this.ja = str2;
        }

        public AlternativeTitles(@JsonProperty("synonyms") List<String> synonyms, @JsonProperty("en") String en, @JsonProperty("ja") String ja) {
            Intrinsics.checkNotNullParameter(synonyms, "synonyms");
            Intrinsics.checkNotNullParameter(en, "en");
            Intrinsics.checkNotNullParameter(ja, "ja");
            this.synonyms = synonyms;
            this.en = en;
            this.ja = ja;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(AlternativeTitles self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, $childSerializers[0].getValue(), self.synonyms);
            output.encodeStringElement(serialDesc, 1, self.en);
            output.encodeStringElement(serialDesc, 2, self.ja);
        }

        public final List<String> getSynonyms() {
            return this.synonyms;
        }

        public final String getEn() {
            return this.en;
        }

        public final String getJa() {
            return this.ja;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Genres;", "", "id", "", "name", "", "<init>", "(ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getName$annotations", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Genres {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final int id;

        @JsonProperty("name")
        private final String name;

        public static /* synthetic */ Genres copy$default(Genres genres, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = genres.id;
            }
            if ((i2 & 2) != 0) {
                str = genres.name;
            }
            return genres.copy(i, str);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final Genres copy(@JsonProperty("id") int id, @JsonProperty("name") String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new Genres(id, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Genres)) {
                return false;
            }
            Genres genres = (Genres) other;
            return this.id == genres.id && Intrinsics.areEqual(this.name, genres.name);
        }

        public int hashCode() {
            return (this.id * 31) + this.name.hashCode();
        }

        public String toString() {
            return "Genres(id=" + this.id + ", name=" + this.name + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Genres$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Genres;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Genres> serializer() {
                return MALApi$Genres$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Genres(int i, int i2, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$Genres$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.name = str;
        }

        public Genres(@JsonProperty("id") int i, @JsonProperty("name") String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.id = i;
            this.name = name;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Genres self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;", "", "year", "", "season", "", "<init>", "(ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getYear$annotations", "()V", "getYear", "()I", "getSeason$annotations", "getSeason", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class StartSeason {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("season")
        private final String season;

        @JsonProperty("year")
        private final int year;

        public static /* synthetic */ StartSeason copy$default(StartSeason startSeason, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = startSeason.year;
            }
            if ((i2 & 2) != 0) {
                str = startSeason.season;
            }
            return startSeason.copy(i, str);
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        public final StartSeason copy(@JsonProperty("year") int year, @JsonProperty("season") String season) {
            Intrinsics.checkNotNullParameter(season, "season");
            return new StartSeason(year, season);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StartSeason)) {
                return false;
            }
            StartSeason startSeason = (StartSeason) other;
            return this.year == startSeason.year && Intrinsics.areEqual(this.season, startSeason.season);
        }

        public int hashCode() {
            return (this.year * 31) + this.season.hashCode();
        }

        public String toString() {
            return "StartSeason(year=" + this.year + ", season=" + this.season + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$StartSeason;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<StartSeason> serializer() {
                return MALApi$StartSeason$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ StartSeason(int i, int i2, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$StartSeason$$serializer.INSTANCE.getDescriptor());
            }
            this.year = i2;
            this.season = str;
        }

        public StartSeason(@JsonProperty("year") int i, @JsonProperty("season") String season) {
            Intrinsics.checkNotNullParameter(season, "season");
            this.year = i;
            this.season = season;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(StartSeason self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.year);
            output.encodeStringElement(serialDesc, 1, self.season);
        }

        public final int getYear() {
            return this.year;
        }

        public final String getSeason() {
            return this.season;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;", "", "dayOfTheWeek", "", "startTime", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getDayOfTheWeek$annotations", "()V", "getDayOfTheWeek", "()Ljava/lang/String;", "getStartTime$annotations", "getStartTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Broadcast {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("day_of_the_week")
        private final String dayOfTheWeek;

        @JsonProperty(TvContractCompat.PARAM_START_TIME)
        private final String startTime;

        public static /* synthetic */ Broadcast copy$default(Broadcast broadcast, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = broadcast.dayOfTheWeek;
            }
            if ((i & 2) != 0) {
                str2 = broadcast.startTime;
            }
            return broadcast.copy(str, str2);
        }

        @SerialName("day_of_the_week")
        public static /* synthetic */ void getDayOfTheWeek$annotations() {
        }

        @SerialName(TvContractCompat.PARAM_START_TIME)
        public static /* synthetic */ void getStartTime$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDayOfTheWeek() {
            return this.dayOfTheWeek;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStartTime() {
            return this.startTime;
        }

        public final Broadcast copy(@JsonProperty("day_of_the_week") String dayOfTheWeek, @JsonProperty(TvContractCompat.PARAM_START_TIME) String startTime) {
            return new Broadcast(dayOfTheWeek, startTime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Broadcast)) {
                return false;
            }
            Broadcast broadcast = (Broadcast) other;
            return Intrinsics.areEqual(this.dayOfTheWeek, broadcast.dayOfTheWeek) && Intrinsics.areEqual(this.startTime, broadcast.startTime);
        }

        public int hashCode() {
            String str = this.dayOfTheWeek;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.startTime;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Broadcast(dayOfTheWeek=" + this.dayOfTheWeek + ", startTime=" + this.startTime + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Broadcast;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Broadcast> serializer() {
                return MALApi$Broadcast$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Broadcast(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$Broadcast$$serializer.INSTANCE.getDescriptor());
            }
            this.dayOfTheWeek = str;
            this.startTime = str2;
        }

        public Broadcast(@JsonProperty("day_of_the_week") String str, @JsonProperty(TvContractCompat.PARAM_START_TIME) String str2) {
            this.dayOfTheWeek = str;
            this.startTime = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Broadcast self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.dayOfTheWeek);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.startTime);
        }

        public final String getDayOfTheWeek() {
            return this.dayOfTheWeek;
        }

        public final String getStartTime() {
            return this.startTime;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object library(AuthData authData, Continuation<? super SyncAPI.LibraryMetadata> continuation) {
        C07981 c07981;
        LinkedHashMap linkedHashMapEmptyMap;
        String status;
        if (continuation instanceof C07981) {
            c07981 = (C07981) continuation;
            if ((c07981.label & Integer.MIN_VALUE) != 0) {
                c07981.label -= Integer.MIN_VALUE;
            } else {
                c07981 = new C07981(continuation);
            }
        } else {
            c07981 = new C07981(continuation);
        }
        Object malAnimeListSmart = c07981.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07981.label;
        if (i == 0) {
            ResultKt.throwOnFailure(malAnimeListSmart);
            if (authData == null) {
                return null;
            }
            c07981.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07981.label = 1;
            malAnimeListSmart = getMalAnimeListSmart(authData, c07981);
            if (malAnimeListSmart == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(malAnimeListSmart);
        }
        Data[] dataArr = (Data[]) malAnimeListSmart;
        if (dataArr == null) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Data data : dataArr) {
                Companion companion = INSTANCE;
                ListStatus listStatus = data.getListStatus();
                if (listStatus == null || (status = listStatus.getStatus()) == null) {
                    status = "";
                }
                Integer numBoxInt = Boxing.boxInt(companion.convertToStatus(status).getStringRes());
                Object obj = linkedHashMap.get(numBoxInt);
                if (obj == null) {
                    obj = (List) new ArrayList();
                    linkedHashMap.put(numBoxInt, obj);
                }
                ((List) obj).add(data);
            }
            linkedHashMapEmptyMap = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Object key = entry.getKey();
                Iterable iterable = (Iterable) entry.getValue();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Data) it.next()).toLibraryItem());
                }
                linkedHashMapEmptyMap.put(key, arrayList);
            }
        }
        EnumEntries<Companion.MalStatusType> entries = Companion.MalStatusType.getEntries();
        ArrayList arrayList2 = new ArrayList();
        for (Companion.MalStatusType malStatusType : entries) {
            if (malStatusType.getValue() >= 0) {
                arrayList2.add(malStatusType);
            }
        }
        ArrayList arrayList3 = arrayList2;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList3, 10)), 16));
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            Pair pair = TuplesKt.to(Boxing.boxInt(((Companion.MalStatusType) it2.next()).getStringRes()), CollectionsKt.emptyList());
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
    /* JADX WARN: Code duplicated, block: B:41:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:48:0x00f9 A[Catch: Exception -> 0x010b, TRY_LEAVE, TryCatch #1 {Exception -> 0x010b, blocks: (B:24:0x0080, B:27:0x008c, B:32:0x00b1, B:39:0x00dd, B:42:0x00e4, B:48:0x00f9, B:47:0x00f4, B:38:0x00d3, B:31:0x00a7, B:35:0x00b8, B:28:0x008e, B:44:0x00e8), top: B:59:0x0080, inners: #0, #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getMalAnimeListSmart(AuthData authData, Continuation<? super Data[]> continuation) {
        C07971 c07971;
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        if (continuation instanceof C07971) {
            c07971 = (C07971) continuation;
            if ((c07971.label & Integer.MIN_VALUE) != 0) {
                c07971.label -= Integer.MIN_VALUE;
            } else {
                c07971 = new C07971(continuation);
            }
        } else {
            c07971 = new C07971(continuation);
        }
        Object malAnimeList = c07971.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07971.label;
        if (i == 0) {
            ResultKt.throwOnFailure(malAnimeList);
            if (getRequireLibraryRefresh()) {
                AuthToken token = authData.getToken();
                c07971.L$0 = authData;
                c07971.label = 1;
                malAnimeList = getMalAnimeList(token, c07971);
                if (malAnimeList == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
                String strValueOf = String.valueOf(authData.getUser().getId());
                Context context = companion.getContext();
                if (context != null) {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(MAL_CACHED_LIST, strValueOf), null);
                        if (string == null) {
                            objDecodeFromString = null;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(Data.class), Data.INSTANCE.serializer()));
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
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Data[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSmart$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Data[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSmart$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Data[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSmart$$inlined$getKey$1
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Data[].class), null, 2, null));
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
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Data[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSmart$$inlined$getKey$1
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
                if (objDecodeFromString instanceof Data[]) {
                    return (Data[]) objDecodeFromString;
                }
                return null;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            authData = (AuthData) c07971.L$0;
            ResultKt.throwOnFailure(malAnimeList);
        }
        Data[] dataArr = (Data[]) malAnimeList;
        CloudStreamApp.INSTANCE.setKey(MAL_CACHED_LIST, String.valueOf(authData.getUser().getId()), dataArr);
        return dataArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0066 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x0067  */
    /* JADX WARN: Code duplicated, block: B:21:0x0080  */
    /* JADX WARN: Code duplicated, block: B:23:0x008a  */
    /* JADX WARN: Code duplicated, block: B:29:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0067 -> B:19:0x006b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object getMalAnimeList(com.lagradost.cloudstream3.syncproviders.AuthToken r10, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.syncproviders.providers.MALApi.Data[]> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.lagradost.cloudstream3.syncproviders.providers.MALApi.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeList$1 r0 = (com.lagradost.cloudstream3.syncproviders.providers.MALApi.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeList$1 r0 = new com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeList$1
            r0.<init>(r11)
        L19:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L44
            if (r2 != r4) goto L3c
            int r10 = r0.I$0
            java.lang.Object r10 = r0.L$2
            kotlin.text.Regex r10 = (kotlin.text.Regex) r10
            java.lang.Object r2 = r0.L$1
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r5 = r0.L$0
            com.lagradost.cloudstream3.syncproviders.AuthToken r5 = (com.lagradost.cloudstream3.syncproviders.AuthToken) r5
            kotlin.ResultKt.throwOnFailure(r11)
            r8 = r5
            r5 = r10
            r10 = r8
            goto L6b
        L3c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L44:
            kotlin.ResultKt.throwOnFailure(r11)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.List r11 = (java.util.List) r11
            kotlin.text.Regex r2 = new kotlin.text.Regex
            java.lang.String r5 = "offset=(\\d+)"
            r2.<init>(r5)
            r5 = r3
        L56:
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r2
            r0.I$0 = r5
            r0.label = r4
            java.lang.Object r5 = r9.getMalAnimeListSlice(r10, r5, r0)
            if (r5 != r1) goto L67
            return r1
        L67:
            r8 = r2
            r2 = r11
            r11 = r5
            r5 = r8
        L6b:
            com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalList r11 = (com.lagradost.cloudstream3.syncproviders.providers.MALApi.MalList) r11
            java.util.List r6 = r11.getData()
            java.util.Collection r6 = (java.util.Collection) r6
            r2.addAll(r6)
            com.lagradost.cloudstream3.syncproviders.providers.MALApi$Paging r11 = r11.getPaging()
            java.lang.String r11 = r11.getNext()
            if (r11 == 0) goto Lab
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            r6 = 2
            r7 = 0
            kotlin.text.MatchResult r11 = kotlin.text.Regex.find$default(r5, r11, r3, r6, r7)
            if (r11 == 0) goto La0
            java.util.List r11 = r11.getGroupValues()
            if (r11 == 0) goto La0
            java.lang.Object r11 = r11.get(r4)
            java.lang.String r11 = (java.lang.String) r11
            if (r11 == 0) goto La0
            int r11 = java.lang.Integer.parseInt(r11)
            java.lang.Integer r7 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r11)
        La0:
            if (r7 == 0) goto Lab
            int r11 = r7.intValue()
            r8 = r5
            r5 = r11
            r11 = r2
            r2 = r8
            goto L56
        Lab:
            java.util.Collection r2 = (java.util.Collection) r2
            com.lagradost.cloudstream3.syncproviders.providers.MALApi$Data[] r9 = new com.lagradost.cloudstream3.syncproviders.providers.MALApi.Data[r3]
            java.lang.Object[] r9 = r2.toArray(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.MALApi.getMalAnimeList(com.lagradost.cloudstream3.syncproviders.AuthToken, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object getMalAnimeListSlice(AuthToken authToken, int i, Continuation<? super MalList> continuation) {
        C07961 c07961;
        Object objM8936constructorimpl;
        if (continuation instanceof C07961) {
            c07961 = (C07961) continuation;
            if ((c07961.label & Integer.MIN_VALUE) != 0) {
                c07961.label -= Integer.MIN_VALUE;
            } else {
                c07961 = new C07961(continuation);
            }
        } else {
            c07961 = new C07961(continuation);
        }
        Object obj = c07961.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07961.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            String str = this.apiUrl + "/v2/users/@me/animelist?fields=list_status,num_episodes,media_type,status,start_date,end_date,synopsis,alternative_titles,mean,genres,rank,num_list_users,nsfw,average_episode_duration,num_favorites,popularity,num_scoring_users,start_season,favorites_info,broadcast,created_at,updated_at&nsfw=1&limit=100&offset=" + i;
            Requests app2 = MainActivityKt.getApp();
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + authToken.getAccessToken()));
            c07961.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07961.L$1 = SpillingKt.nullOutSpilledVariable("@me");
            c07961.L$2 = SpillingKt.nullOutSpilledVariable(str);
            c07961.I$0 = i;
            c07961.label = 1;
            obj = Requests.get$default(app2, str, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, c07961, 4028, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07961.I$0;
            ResultKt.throwOnFailure(obj);
        }
        String text = ((NiceResponse) obj).getText();
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(MalList.INSTANCE.serializer());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(MalList.class), null, 2, null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
            }
        }
        KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        if (kSerializer != null) {
            try {
                return MainAPIKt.getJson().decodeFromString(kSerializer, text);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
            } catch (Throwable unused) {
            }
        }
        return MainAPIKt.getMapper().readValue(text, new TypeReference<MalList>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$getMalAnimeListSlice$$inlined$parseJson$1
        });
    }

    static /* synthetic */ Object getMalAnimeListSlice$default(MALApi mALApi, AuthToken authToken, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return mALApi.getMalAnimeListSlice(authToken, i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object setScoreRequest(AuthToken authToken, int i, Companion.MalStatusType malStatusType, Integer num, Integer num2, Continuation<? super Boolean> continuation) throws JsonProcessingException {
        C08031 c08031;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof C08031) {
            c08031 = (C08031) continuation;
            if ((c08031.label & Integer.MIN_VALUE) != 0) {
                c08031.label -= Integer.MIN_VALUE;
            } else {
                c08031 = new C08031(continuation);
            }
        } else {
            c08031 = new C08031(continuation);
        }
        C08031 c08032 = c08031;
        Object scoreRequest = c08032.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c08032.label;
        boolean z = false;
        if (i2 == 0) {
            ResultKt.throwOnFailure(scoreRequest);
            String str = malStatusType == null ? null : malStatusAsString[Math.max(0, malStatusType.getValue())];
            c08032.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c08032.L$1 = SpillingKt.nullOutSpilledVariable(malStatusType);
            c08032.L$2 = SpillingKt.nullOutSpilledVariable(num);
            c08032.L$3 = SpillingKt.nullOutSpilledVariable(num2);
            c08032.I$0 = i;
            c08032.label = 1;
            scoreRequest = setScoreRequest(authToken, i, str, num, num2, c08032);
            if (scoreRequest == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = c08032.I$0;
            ResultKt.throwOnFailure(scoreRequest);
        }
        String str2 = (String) scoreRequest;
        String str3 = str2;
        if (str3 != null && !StringsKt.isBlank(str3)) {
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(MalStatus.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(MalStatus.class), null, 2, null));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
            }
            KSerializer kSerializer = (KSerializer) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str2);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<MalStatus>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$setScoreRequest$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<MalStatus>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$setScoreRequest$$inlined$parseJson$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<MalStatus>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$setScoreRequest$$inlined$parseJson$1
                });
            }
            MalStatus malStatus = (MalStatus) objDecodeFromString;
            boolean zContainsKey = this.allTitles.containsKey(Boxing.boxInt(i));
            HashMap<Integer, MalTitleHolder> map = this.allTitles;
            if (zContainsKey) {
                MalTitleHolder malTitleHolder = map.get(Boxing.boxInt(i));
                Intrinsics.checkNotNull(malTitleHolder);
                this.allTitles.put(Boxing.boxInt(i), new MalTitleHolder(malStatus, i, malTitleHolder.getName()));
            } else {
                map.put(Boxing.boxInt(i), new MalTitleHolder(malStatus, i, ""));
            }
            z = true;
        }
        return Boxing.boxBoolean(z);
    }

    static /* synthetic */ Object setScoreRequest$default(MALApi mALApi, AuthToken authToken, int i, Companion.MalStatusType malStatusType, Integer num, Integer num2, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            malStatusType = null;
        }
        if ((i2 & 8) != 0) {
            num = null;
        }
        if ((i2 & 16) != 0) {
            num2 = null;
        }
        return mALApi.setScoreRequest(authToken, i, malStatusType, num, num2, (Continuation<? super Boolean>) continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object setScoreRequest(AuthToken authToken, int i, String str, Integer num, Integer num2, Continuation<? super String> continuation) {
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
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Pair[] pairArr = new Pair[3];
            pairArr[0] = TuplesKt.to(NotificationCompat.CATEGORY_STATUS, str);
            pairArr[1] = TuplesKt.to("score", num != null ? String.valueOf(num.intValue()) : null);
            pairArr[2] = TuplesKt.to("num_watched_episodes", num2 != null ? String.valueOf(num2.intValue()) : null);
            Map mapMapOf = MapsKt.mapOf(pairArr);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : mapMapOf.entrySet()) {
                if (((String) entry.getValue()) != null) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            LinkedHashMap linkedHashMap2 = linkedHashMap;
            Requests app2 = MainActivityKt.getApp();
            String str2 = this.apiUrl + "/v2/anime/" + i + "/my_list_status";
            Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + authToken.getAccessToken()));
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(num);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(num2);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(linkedHashMap2);
            anonymousClass2.I$0 = i;
            anonymousClass2.label = 1;
            Object objPut$default = Requests.put$default(app2, str2, mapMapOf2, null, null, null, linkedHashMap2, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass2, 65500, null);
            if (objPut$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPut$default;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass2.I$0;
            ResultKt.throwOnFailure(obj);
        }
        return ((NiceResponse) obj).getText();
    }

    static /* synthetic */ Object setScoreRequest$default(MALApi mALApi, AuthToken authToken, int i, String str, Integer num, Integer num2, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            str = null;
        }
        if ((i2 & 8) != 0) {
            num = null;
        }
        if ((i2 & 16) != 0) {
            num2 = null;
        }
        return mALApi.setScoreRequest(authToken, i, str, num, num2, (Continuation<? super String>) continuation);
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B/\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tBA\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J1\u0010\u001d\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0011R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0018\u0010\u0011¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ResponseToken;", "", "tokenType", "", "expiresIn", "", "accessToken", "refreshToken", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTokenType$annotations", "()V", "getTokenType", "()Ljava/lang/String;", "getExpiresIn$annotations", "getExpiresIn", "()I", "getAccessToken$annotations", "getAccessToken", "getRefreshToken$annotations", "getRefreshToken", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
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

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ResponseToken$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$ResponseToken;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ResponseToken> serializer() {
                return MALApi$ResponseToken$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ResponseToken(int i, String str, int i2, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, MALApi$ResponseToken$$serializer.INSTANCE.getDescriptor());
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

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalRoot;", "", "data", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalDatum;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalRoot {

        @JsonProperty("data")
        private final List<MalDatum> data;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalRoot$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalRoot._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MALApi$MalDatum$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MalRoot copy$default(MalRoot malRoot, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = malRoot.data;
            }
            return malRoot.copy(list);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        public final List<MalDatum> component1() {
            return this.data;
        }

        public final MalRoot copy(@JsonProperty("data") List<MalDatum> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new MalRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MalRoot) && Intrinsics.areEqual(this.data, ((MalRoot) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "MalRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalRoot> serializer() {
                return MALApi$MalRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalRoot(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, MALApi$MalRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = list;
        }

        public MalRoot(@JsonProperty("data") List<MalDatum> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<MalDatum> getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalDatum;", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode;", "listStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNode$annotations", "()V", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode;", "getListStatus$annotations", "getListStatus", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalDatum {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("list_status")
        private final MalStatus listStatus;

        @JsonProperty("node")
        private final MalNode node;

        public static /* synthetic */ MalDatum copy$default(MalDatum malDatum, MalNode malNode, MalStatus malStatus, int i, Object obj) {
            if ((i & 1) != 0) {
                malNode = malDatum.node;
            }
            if ((i & 2) != 0) {
                malStatus = malDatum.listStatus;
            }
            return malDatum.copy(malNode, malStatus);
        }

        @SerialName("list_status")
        public static /* synthetic */ void getListStatus$annotations() {
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MalNode getNode() {
            return this.node;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MalStatus getListStatus() {
            return this.listStatus;
        }

        public final MalDatum copy(@JsonProperty("node") MalNode node, @JsonProperty("list_status") MalStatus listStatus) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(listStatus, "listStatus");
            return new MalDatum(node, listStatus);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalDatum)) {
                return false;
            }
            MalDatum malDatum = (MalDatum) other;
            return Intrinsics.areEqual(this.node, malDatum.node) && Intrinsics.areEqual(this.listStatus, malDatum.listStatus);
        }

        public int hashCode() {
            return (this.node.hashCode() * 31) + this.listStatus.hashCode();
        }

        public String toString() {
            return "MalDatum(node=" + this.node + ", listStatus=" + this.listStatus + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalDatum$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalDatum;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalDatum> serializer() {
                return MALApi$MalDatum$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalDatum(int i, MalNode malNode, MalStatus malStatus, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$MalDatum$$serializer.INSTANCE.getDescriptor());
            }
            this.node = malNode;
            this.listStatus = malStatus;
        }

        public MalDatum(@JsonProperty("node") MalNode node, @JsonProperty("list_status") MalStatus listStatus) {
            Intrinsics.checkNotNullParameter(node, "node");
            Intrinsics.checkNotNullParameter(listStatus, "listStatus");
            this.node = node;
            this.listStatus = listStatus;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalDatum self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, MALApi$MalNode$$serializer.INSTANCE, self.node);
            output.encodeSerializableElement(serialDesc, 1, MALApi$MalStatus$$serializer.INSTANCE, self.listStatus);
        }

        public final MalNode getNode() {
            return this.node;
        }

        public final MalStatus getListStatus() {
            return this.listStatus;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 $2\u00020\u0001:\u0002#$B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B-\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0001¢\u0006\u0002\b\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u0012¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode;", "", "id", "", "title", "", "<init>", "(ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalNode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final int id;

        @JsonProperty("title")
        private final String title;

        public static /* synthetic */ MalNode copy$default(MalNode malNode, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = malNode.id;
            }
            if ((i2 & 2) != 0) {
                str = malNode.title;
            }
            return malNode.copy(i, str);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final MalNode copy(@JsonProperty("id") int id, @JsonProperty("title") String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            return new MalNode(id, title);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalNode)) {
                return false;
            }
            MalNode malNode = (MalNode) other;
            return this.id == malNode.id && Intrinsics.areEqual(this.title, malNode.title);
        }

        public int hashCode() {
            return (this.id * 31) + this.title.hashCode();
        }

        public String toString() {
            return "MalNode(id=" + this.id + ", title=" + this.title + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalNode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalNode> serializer() {
                return MALApi$MalNode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalNode(int i, int i2, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$MalNode$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.title = str;
        }

        public MalNode(@JsonProperty("id") int i, @JsonProperty("title") String title) {
            Intrinsics.checkNotNullParameter(title, "title");
            this.id = i;
            this.title = title;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalNode self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.title);
        }

        public final int getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bBG\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J;\u0010\"\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0016R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u0007\u0010\u001aR\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u0013¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "", NotificationCompat.CATEGORY_STATUS, "", "score", "", "numEpisodesWatched", "isRewatching", "", "updatedAt", "<init>", "(Ljava/lang/String;IIZLjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;IIZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Ljava/lang/String;", "getScore$annotations", "getScore", "()I", "getNumEpisodesWatched$annotations", "getNumEpisodesWatched", "isRewatching$annotations", "()Z", "getUpdatedAt$annotations", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalStatus {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("is_rewatching")
        private final boolean isRewatching;

        @JsonProperty("num_episodes_watched")
        private final int numEpisodesWatched;

        @JsonProperty("score")
        private final int score;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("updated_at")
        private final String updatedAt;

        public static /* synthetic */ MalStatus copy$default(MalStatus malStatus, String str, int i, int i2, boolean z, String str2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = malStatus.status;
            }
            if ((i3 & 2) != 0) {
                i = malStatus.score;
            }
            if ((i3 & 4) != 0) {
                i2 = malStatus.numEpisodesWatched;
            }
            if ((i3 & 8) != 0) {
                z = malStatus.isRewatching;
            }
            if ((i3 & 16) != 0) {
                str2 = malStatus.updatedAt;
            }
            String str3 = str2;
            int i4 = i2;
            return malStatus.copy(str, i, i4, z, str3);
        }

        @SerialName("num_episodes_watched")
        public static /* synthetic */ void getNumEpisodesWatched$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @SerialName("is_rewatching")
        public static /* synthetic */ void isRewatching$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getNumEpisodesWatched() {
            return this.numEpisodesWatched;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsRewatching() {
            return this.isRewatching;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        public final MalStatus copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("score") int score, @JsonProperty("num_episodes_watched") int numEpisodesWatched, @JsonProperty("is_rewatching") boolean isRewatching, @JsonProperty("updated_at") String updatedAt) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
            return new MalStatus(status, score, numEpisodesWatched, isRewatching, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalStatus)) {
                return false;
            }
            MalStatus malStatus = (MalStatus) other;
            return Intrinsics.areEqual(this.status, malStatus.status) && this.score == malStatus.score && this.numEpisodesWatched == malStatus.numEpisodesWatched && this.isRewatching == malStatus.isRewatching && Intrinsics.areEqual(this.updatedAt, malStatus.updatedAt);
        }

        public int hashCode() {
            return (((((((this.status.hashCode() * 31) + this.score) * 31) + this.numEpisodesWatched) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isRewatching)) * 31) + this.updatedAt.hashCode();
        }

        public String toString() {
            return "MalStatus(status=" + this.status + ", score=" + this.score + ", numEpisodesWatched=" + this.numEpisodesWatched + ", isRewatching=" + this.isRewatching + ", updatedAt=" + this.updatedAt + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalStatus> serializer() {
                return MALApi$MalStatus$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalStatus(int i, String str, int i2, int i3, boolean z, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MALApi$MalStatus$$serializer.INSTANCE.getDescriptor());
            }
            this.status = str;
            this.score = i2;
            this.numEpisodesWatched = i3;
            this.isRewatching = z;
            this.updatedAt = str2;
        }

        public MalStatus(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("score") int i, @JsonProperty("num_episodes_watched") int i2, @JsonProperty("is_rewatching") boolean z, @JsonProperty("updated_at") String updatedAt) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
            this.status = status;
            this.score = i;
            this.numEpisodesWatched = i2;
            this.isRewatching = z;
            this.updatedAt = updatedAt;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalStatus self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.status);
            output.encodeIntElement(serialDesc, 1, self.score);
            output.encodeIntElement(serialDesc, 2, self.numEpisodesWatched);
            output.encodeBooleanElement(serialDesc, 3, self.isRewatching);
            output.encodeStringElement(serialDesc, 4, self.updatedAt);
        }

        public final String getStatus() {
            return this.status;
        }

        public final int getScore() {
            return this.score;
        }

        public final int getNumEpisodesWatched() {
            return this.numEpisodesWatched;
        }

        public final boolean isRewatching() {
            return this.isRewatching;
        }

        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B;\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nBK\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000eJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J=\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0015R\u001c\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0015R\u001e\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u0015¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalUser;", "", "id", "", "name", "", "location", "joinedAt", "picture", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getName$annotations", "getName", "()Ljava/lang/String;", "getLocation$annotations", "getLocation", "getJoinedAt$annotations", "getJoinedAt", "getPicture$annotations", "getPicture", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalUser {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final int id;

        @JsonProperty("joined_at")
        private final String joinedAt;

        @JsonProperty("location")
        private final String location;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("picture")
        private final String picture;

        public static /* synthetic */ MalUser copy$default(MalUser malUser, int i, String str, String str2, String str3, String str4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = malUser.id;
            }
            if ((i2 & 2) != 0) {
                str = malUser.name;
            }
            if ((i2 & 4) != 0) {
                str2 = malUser.location;
            }
            if ((i2 & 8) != 0) {
                str3 = malUser.joinedAt;
            }
            if ((i2 & 16) != 0) {
                str4 = malUser.picture;
            }
            String str5 = str4;
            String str6 = str2;
            return malUser.copy(i, str, str6, str3, str5);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("joined_at")
        public static /* synthetic */ void getJoinedAt$annotations() {
        }

        @SerialName("location")
        public static /* synthetic */ void getLocation$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("picture")
        public static /* synthetic */ void getPicture$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLocation() {
            return this.location;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getJoinedAt() {
            return this.joinedAt;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPicture() {
            return this.picture;
        }

        public final MalUser copy(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("location") String location, @JsonProperty("joined_at") String joinedAt, @JsonProperty("picture") String picture) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            Intrinsics.checkNotNullParameter(joinedAt, "joinedAt");
            return new MalUser(id, name, location, joinedAt, picture);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalUser)) {
                return false;
            }
            MalUser malUser = (MalUser) other;
            return this.id == malUser.id && Intrinsics.areEqual(this.name, malUser.name) && Intrinsics.areEqual(this.location, malUser.location) && Intrinsics.areEqual(this.joinedAt, malUser.joinedAt) && Intrinsics.areEqual(this.picture, malUser.picture);
        }

        public int hashCode() {
            int iHashCode = ((((((this.id * 31) + this.name.hashCode()) * 31) + this.location.hashCode()) * 31) + this.joinedAt.hashCode()) * 31;
            String str = this.picture;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "MalUser(id=" + this.id + ", name=" + this.name + ", location=" + this.location + ", joinedAt=" + this.joinedAt + ", picture=" + this.picture + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalUser$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalUser;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalUser> serializer() {
                return MALApi$MalUser$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalUser(int i, int i2, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MALApi$MalUser$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.name = str;
            this.location = str2;
            this.joinedAt = str3;
            this.picture = str4;
        }

        public MalUser(@JsonProperty("id") int i, @JsonProperty("name") String name, @JsonProperty("location") String location, @JsonProperty("joined_at") String joinedAt, @JsonProperty("picture") String str) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(location, "location");
            Intrinsics.checkNotNullParameter(joinedAt, "joinedAt");
            this.id = i;
            this.name = name;
            this.location = location;
            this.joinedAt = joinedAt;
            this.picture = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalUser self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeStringElement(serialDesc, 2, self.location);
            output.encodeStringElement(serialDesc, 3, self.joinedAt);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.picture);
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getLocation() {
            return this.location;
        }

        public final String getJoinedAt() {
            return this.joinedAt;
        }

        public final String getPicture() {
            return this.picture;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture;", "", "large", "", "medium", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLarge$annotations", "()V", "getLarge", "()Ljava/lang/String;", "getMedium$annotations", "getMedium", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalMainPicture {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ MalMainPicture copy$default(MalMainPicture malMainPicture, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = malMainPicture.large;
            }
            if ((i & 2) != 0) {
                str2 = malMainPicture.medium;
            }
            return malMainPicture.copy(str, str2);
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

        public final MalMainPicture copy(@JsonProperty("large") String large, @JsonProperty("medium") String medium) {
            return new MalMainPicture(large, medium);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalMainPicture)) {
                return false;
            }
            MalMainPicture malMainPicture = (MalMainPicture) other;
            return Intrinsics.areEqual(this.large, malMainPicture.large) && Intrinsics.areEqual(this.medium, malMainPicture.medium);
        }

        public int hashCode() {
            String str = this.large;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.medium;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "MalMainPicture(large=" + this.large + ", medium=" + this.medium + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalMainPicture> serializer() {
                return MALApi$MalMainPicture$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalMainPicture(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, MALApi$MalMainPicture$$serializer.INSTANCE.getDescriptor());
            }
            this.large = str;
            this.medium = str2;
        }

        public MalMainPicture(@JsonProperty("large") String str, @JsonProperty("medium") String str2) {
            this.large = str;
            this.medium = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MalMainPicture self, CompositeEncoder output, SerialDescriptor serialDesc) {
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

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 42\u00020\u0001:\u000234B?\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fBI\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\nHÆ\u0003JA\u0010%\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0014\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0001¢\u0006\u0002\b2R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u0014R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0012\u001a\u0004\b\u001e\u0010\u001f¨\u00065"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$SmallMalAnime;", "", "id", "", "title", "", "numEpisodes", "myListStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "mainPicture", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture;", "<init>", "(ILjava/lang/String;ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getTitle$annotations", "getTitle", "()Ljava/lang/String;", "getNumEpisodes$annotations", "getNumEpisodes", "getMyListStatus$annotations", "getMyListStatus", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "getMainPicture$annotations", "getMainPicture", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalMainPicture;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SmallMalAnime {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final int id;

        @JsonProperty("main_picture")
        private final MalMainPicture mainPicture;

        @JsonProperty("my_list_status")
        private final MalStatus myListStatus;

        @JsonProperty("num_episodes")
        private final int numEpisodes;

        @JsonProperty("title")
        private final String title;

        public static /* synthetic */ SmallMalAnime copy$default(SmallMalAnime smallMalAnime, int i, String str, int i2, MalStatus malStatus, MalMainPicture malMainPicture, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = smallMalAnime.id;
            }
            if ((i3 & 2) != 0) {
                str = smallMalAnime.title;
            }
            if ((i3 & 4) != 0) {
                i2 = smallMalAnime.numEpisodes;
            }
            if ((i3 & 8) != 0) {
                malStatus = smallMalAnime.myListStatus;
            }
            if ((i3 & 16) != 0) {
                malMainPicture = smallMalAnime.mainPicture;
            }
            MalMainPicture malMainPicture2 = malMainPicture;
            int i4 = i2;
            return smallMalAnime.copy(i, str, i4, malStatus, malMainPicture2);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("main_picture")
        public static /* synthetic */ void getMainPicture$annotations() {
        }

        @SerialName("my_list_status")
        public static /* synthetic */ void getMyListStatus$annotations() {
        }

        @SerialName("num_episodes")
        public static /* synthetic */ void getNumEpisodes$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getNumEpisodes() {
            return this.numEpisodes;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final MalStatus getMyListStatus() {
            return this.myListStatus;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final MalMainPicture getMainPicture() {
            return this.mainPicture;
        }

        public final SmallMalAnime copy(@JsonProperty("id") int id, @JsonProperty("title") String title, @JsonProperty("num_episodes") int numEpisodes, @JsonProperty("my_list_status") MalStatus myListStatus, @JsonProperty("main_picture") MalMainPicture mainPicture) {
            return new SmallMalAnime(id, title, numEpisodes, myListStatus, mainPicture);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SmallMalAnime)) {
                return false;
            }
            SmallMalAnime smallMalAnime = (SmallMalAnime) other;
            return this.id == smallMalAnime.id && Intrinsics.areEqual(this.title, smallMalAnime.title) && this.numEpisodes == smallMalAnime.numEpisodes && Intrinsics.areEqual(this.myListStatus, smallMalAnime.myListStatus) && Intrinsics.areEqual(this.mainPicture, smallMalAnime.mainPicture);
        }

        public int hashCode() {
            int i = this.id * 31;
            String str = this.title;
            int iHashCode = (((i + (str == null ? 0 : str.hashCode())) * 31) + this.numEpisodes) * 31;
            MalStatus malStatus = this.myListStatus;
            int iHashCode2 = (iHashCode + (malStatus == null ? 0 : malStatus.hashCode())) * 31;
            MalMainPicture malMainPicture = this.mainPicture;
            return iHashCode2 + (malMainPicture != null ? malMainPicture.hashCode() : 0);
        }

        public String toString() {
            return "SmallMalAnime(id=" + this.id + ", title=" + this.title + ", numEpisodes=" + this.numEpisodes + ", myListStatus=" + this.myListStatus + ", mainPicture=" + this.mainPicture + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$SmallMalAnime$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$SmallMalAnime;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SmallMalAnime> serializer() {
                return MALApi$SmallMalAnime$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SmallMalAnime(int i, int i2, String str, int i3, MalStatus malStatus, MalMainPicture malMainPicture, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, MALApi$SmallMalAnime$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.title = str;
            this.numEpisodes = i3;
            this.myListStatus = malStatus;
            this.mainPicture = malMainPicture;
        }

        public SmallMalAnime(@JsonProperty("id") int i, @JsonProperty("title") String str, @JsonProperty("num_episodes") int i2, @JsonProperty("my_list_status") MalStatus malStatus, @JsonProperty("main_picture") MalMainPicture malMainPicture) {
            this.id = i;
            this.title = str;
            this.numEpisodes = i2;
            this.myListStatus = malStatus;
            this.mainPicture = malMainPicture;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SmallMalAnime self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.title);
            output.encodeIntElement(serialDesc, 2, self.numEpisodes);
            output.encodeNullableSerializableElement(serialDesc, 3, MALApi$MalStatus$$serializer.INSTANCE, self.myListStatus);
            output.encodeNullableSerializableElement(serialDesc, 4, MALApi$MalMainPicture$$serializer.INSTANCE, self.mainPicture);
        }

        public final int getId() {
            return this.id;
        }

        public final String getTitle() {
            return this.title;
        }

        public final int getNumEpisodes() {
            return this.numEpisodes;
        }

        public final MalStatus getMyListStatus() {
            return this.myListStatus;
        }

        public final MalMainPicture getMainPicture() {
            return this.mainPicture;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearchNode;", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNode$annotations", "()V", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$Node;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalSearchNode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("node")
        private final Node node;

        public static /* synthetic */ MalSearchNode copy$default(MalSearchNode malSearchNode, Node node, int i, Object obj) {
            if ((i & 1) != 0) {
                node = malSearchNode.node;
            }
            return malSearchNode.copy(node);
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Node getNode() {
            return this.node;
        }

        public final MalSearchNode copy(@JsonProperty("node") Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            return new MalSearchNode(node);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MalSearchNode) && Intrinsics.areEqual(this.node, ((MalSearchNode) other).node);
        }

        public int hashCode() {
            return this.node.hashCode();
        }

        public String toString() {
            return "MalSearchNode(node=" + this.node + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearchNode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearchNode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalSearchNode> serializer() {
                return MALApi$MalSearchNode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalSearchNode(int i, Node node, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, MALApi$MalSearchNode$$serializer.INSTANCE.getDescriptor());
            }
            this.node = node;
        }

        public MalSearchNode(@JsonProperty("node") Node node) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
        }

        public final Node getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearch;", "", "data", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearchNode;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MalSearch {

        @JsonProperty("data")
        private final List<MalSearchNode> data;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.MALApi$MalSearch$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MALApi.MalSearch._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(MALApi$MalSearchNode$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MalSearch copy$default(MalSearch malSearch, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = malSearch.data;
            }
            return malSearch.copy(list);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        public final List<MalSearchNode> component1() {
            return this.data;
        }

        public final MalSearch copy(@JsonProperty("data") List<MalSearchNode> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new MalSearch(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MalSearch) && Intrinsics.areEqual(this.data, ((MalSearch) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "MalSearch(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: MALApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearch$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalSearch;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MalSearch> serializer() {
                return MALApi$MalSearch$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MalSearch(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, MALApi$MalSearch$$serializer.INSTANCE.getDescriptor());
            }
            this.data = list;
        }

        public MalSearch(@JsonProperty("data") List<MalSearchNode> data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final List<MalSearchNode> getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: MALApi.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalTitleHolder;", "", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "id", "", "name", "", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;ILjava/lang/String;)V", "getStatus", "()Lcom/lagradost/cloudstream3/syncproviders/providers/MALApi$MalStatus;", "getId", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MalTitleHolder {
        private final int id;
        private final String name;
        private final MalStatus status;

        public static /* synthetic */ MalTitleHolder copy$default(MalTitleHolder malTitleHolder, MalStatus malStatus, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                malStatus = malTitleHolder.status;
            }
            if ((i2 & 2) != 0) {
                i = malTitleHolder.id;
            }
            if ((i2 & 4) != 0) {
                str = malTitleHolder.name;
            }
            return malTitleHolder.copy(malStatus, i, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MalStatus getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final MalTitleHolder copy(MalStatus status, int id, String name) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(name, "name");
            return new MalTitleHolder(status, id, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MalTitleHolder)) {
                return false;
            }
            MalTitleHolder malTitleHolder = (MalTitleHolder) other;
            return Intrinsics.areEqual(this.status, malTitleHolder.status) && this.id == malTitleHolder.id && Intrinsics.areEqual(this.name, malTitleHolder.name);
        }

        public int hashCode() {
            return (((this.status.hashCode() * 31) + this.id) * 31) + this.name.hashCode();
        }

        public String toString() {
            return "MalTitleHolder(status=" + this.status + ", id=" + this.id + ", name=" + this.name + ')';
        }

        public MalTitleHolder(MalStatus status, int i, String name) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(name, "name");
            this.status = status;
            this.id = i;
            this.name = name;
        }

        public final MalStatus getStatus() {
            return this.status;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }
    }
}
