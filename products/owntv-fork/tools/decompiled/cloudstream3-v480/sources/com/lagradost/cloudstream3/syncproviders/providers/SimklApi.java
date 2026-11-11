package com.lagradost.cloudstream3.syncproviders.providers;

import android.content.Context;
import android.net.Uri;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SimklSyncServices;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.syncproviders.AuthData;
import com.lagradost.cloudstream3.syncproviders.AuthLoginPage;
import com.lagradost.cloudstream3.syncproviders.AuthPinData;
import com.lagradost.cloudstream3.syncproviders.AuthToken;
import com.lagradost.cloudstream3.syncproviders.AuthUser;
import com.lagradost.cloudstream3.syncproviders.SyncAPI;
import com.lagradost.cloudstream3.syncproviders.SyncIdName;
import com.lagradost.cloudstream3.ui.SyncWatchType;
import com.lagradost.cloudstream3.ui.library.ListSorting;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import com.lagradost.nicehttp.ResponseParser;
import io.ktor.http.ContentType;
import j$.time.Instant;
import j$.util.DesugarDate;
import j$.util.DesugarTimeZone;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SimklApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 ^2\u00020\u0001:\u0004]^_`B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0082@¢\u0006\u0002\u0010-J\"\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00103J(\u00104\u001a\u00020\u00112\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u00052\u0006\u00105\u001a\u00020/H\u0096@¢\u0006\u0002\u00106J*\u00107\u001a\n\u0012\u0004\u0012\u000209\u0018\u0001082\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u00050;H\u0082@¢\u0006\u0002\u0010=J(\u0010>\u001a\n\u0012\u0004\u0012\u00020@\u0018\u00010?2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010A\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00103J\n\u0010B\u001a\u0004\u0018\u00010CH\u0016J\"\u0010D\u001a\u0004\u0018\u00010E2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u00103J\"\u0010F\u001a\u0004\u0018\u00010G2\u0006\u00100\u001a\u0002012\b\u0010H\u001a\u0004\u0018\u00010(H\u0082@¢\u0006\u0002\u0010IJ\u0018\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010+\u001a\u00020,H\u0082@¢\u0006\u0002\u0010-J\u0012\u0010L\u001a\u0004\u0018\u00010G2\u0006\u00100\u001a\u000201H\u0002J\u0018\u0010M\u001a\u0004\u0018\u00010G2\u0006\u00100\u001a\u000201H\u0082@¢\u0006\u0002\u0010NJ\u001a\u0010O\u001a\u0004\u0018\u00010P2\b\u00100\u001a\u0004\u0018\u000101H\u0096@¢\u0006\u0002\u0010NJ\u0012\u0010Q\u001a\u0004\u0018\u00010\u00052\u0006\u0010R\u001a\u00020\u0005H\u0016J\u0010\u0010S\u001a\u0004\u0018\u00010TH\u0096@¢\u0006\u0002\u0010UJ\u0018\u0010V\u001a\u0004\u0018\u00010,2\u0006\u0010W\u001a\u00020TH\u0096@¢\u0006\u0002\u0010XJ\"\u0010V\u001a\u0004\u0018\u00010,2\u0006\u0010Y\u001a\u00020\u00052\b\u0010W\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010ZJ\u001a\u0010[\u001a\u0004\u0018\u00010\\2\b\u0010+\u001a\u0004\u0018\u00010,H\u0096@¢\u0006\u0002\u0010-R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0011X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0016\u001a\u00020\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0007\"\u0004\b\u001c\u0010\tR\u0014\u0010\u001d\u001a\u00020\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0014\u0010#\u001a\u00020$X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "idPrefix", "getIdPrefix", "key", "getKey", "redirectUrlIdentifier", "getRedirectUrlIdentifier", "hasOAuth2", "", "getHasOAuth2", "()Z", "hasPin", "getHasPin", "requireLibraryRefresh", "getRequireLibraryRefresh", "setRequireLibraryRefresh", "(Z)V", "mainUrl", "getMainUrl", "setMainUrl", "icon", "", "getIcon", "()Ljava/lang/Integer;", "createAccountUrl", "getCreateAccountUrl", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "lastScoreTime", "", "getUser", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse;", "token", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "id", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStatus", "newStatus", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchByIds", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject;", "serviceMap", "", "Lcom/lagradost/cloudstream3/SimklSyncServices;", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", SearchIntents.EXTRA_QUERY, "loginRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginPage;", "load", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "getSyncListSince", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse;", "since", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActivities", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse;", "getSyncListCached", "getSyncListSmart", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "urlToId", ImagesContract.URL, "pinRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthPinData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "payload", "(Lcom/lagradost/cloudstream3/syncproviders/AuthPinData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "redirectUrl", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "SimklCache", "Companion", "SimklEpisodeConstructor", "SimklSyncStatus", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SimklApi extends SyncAPI {
    private static final String CLIENT_ID = "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e";
    private static final String CLIENT_SECRET = "1e8f8cd198588a544e2063c8d50c29ea6b5a4c7b7d8ee65e96fe4aaf001872e0";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String SIMKL_CACHED_LIST = "simkl_cached_list";
    public static final String SIMKL_CACHED_LIST_TIME = "simkl_cached_time";
    private static final String SIMKL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private String name = "Simkl";
    private final String idPrefix = "simkl";
    private final String key = "simkl-key";
    private final String redirectUrlIdentifier = "simkl";
    private final boolean hasOAuth2 = true;
    private final boolean hasPin = true;
    private boolean requireLibraryRefresh = true;
    private String mainUrl = "https://api.simkl.com";
    private final int icon = R.drawable.simkl_logo;
    private final String createAccountUrl = getMainUrl() + "/signup";
    private final SyncIdName syncIdName = SyncIdName.Simkl;
    private long lastScoreTime = -1;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getActivities$1, reason: invalid class name */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0}, l = {946}, m = "getActivities", n = {"token"}, nl = {1088}, s = {"L$0"}, v = 2)
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
            return SimklApi.this.getActivities(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListSince$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0, 0, 0}, l = {938}, m = "getSyncListSince", n = {"auth", "since", "params"}, nl = {942}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C08081 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C08081(Continuation<? super C08081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.getSyncListSince(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListSmart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {954, 977, 983}, m = "getSyncListSmart", n = {"auth", "auth", "activities", "userId", "lastCacheUpdate", "lastRemoval", "lastRealUpdate", "auth", "activities", "userId", "lastCacheUpdate", "lastRemoval", "lastRealUpdate"}, nl = {955, 978, 981}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "J$0", "J$1", "L$0", "L$1", "L$2", "L$3", "J$0", "J$1"}, v = 2)
    static final class C08091 extends ContinuationImpl {
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C08091(Continuation<? super C08091> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.getSyncListSmart(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0}, l = {763}, m = "getUser", n = {"token"}, nl = {764}, s = {"L$0"}, v = 2)
    static final class C08101 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08101(Continuation<? super C08101> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.getUser(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$library$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0}, l = {997}, m = "library", n = {"auth"}, nl = {1000}, s = {"L$0"}, v = 2)
    static final class C08111 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08111(Continuation<? super C08111> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.library(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$login$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0}, l = {1053}, m = "login", n = {"payload"}, nl = {1055}, s = {"L$0"}, v = 2)
    static final class C08121 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08121(Continuation<? super C08121> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.login((AuthPinData) null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$login$2, reason: invalid class name */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0, 0, 0, 0, 0}, l = {1069}, m = "login", n = {"redirectUrl", "payload", "uri", "state", "code"}, nl = {1071}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class AnonymousClass2 extends ContinuationImpl {
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
            return SimklApi.this.login(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$pinRequest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {}, l = {1039}, m = "pinRequest", n = {}, nl = {1041}, s = {}, v = 2)
    static final class C08131 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C08131(Continuation<? super C08131> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.pinRequest(this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0, 0}, l = {914}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY}, nl = {916}, s = {"L$0", "L$1"}, v = 2)
    static final class C08141 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C08141(Continuation<? super C08141> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.search(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$searchByIds$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0}, l = {905}, m = "searchByIds", n = {"serviceMap"}, nl = {910}, s = {"L$0"}, v = 2)
    static final class C08151 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08151(Continuation<? super C08151> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.searchByIds(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$status$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {807, 820}, m = NotificationCompat.CATEGORY_STATUS, n = {"auth", "id", "realIds", "idKey", "cachedObject", "auth", "id", "realIds", "idKey", "cachedObject", "searchResult", "episodeConstructor"}, nl = {809, 821}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class C08161 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        C08161(Continuation<? super C08161> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.status(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$updateStatus$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {884, 897}, m = "updateStatus", n = {"auth", "id", "newStatus", "parsedId", "simklStatus", "builder", "auth", "id", "newStatus", "parsedId", "simklStatus", "builder", "episodes", "watchedEpisodes"}, nl = {888, -1}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7"}, v = 2)
    static final class C08171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C08171(Continuation<? super C08171> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.updateStatus(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$user$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.SimklApi", f = "SimklApi.kt", i = {0}, l = {1079}, m = "user", n = {"token"}, nl = {1081}, s = {"L$0"}, v = 2)
    static final class C08181 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C08181(Continuation<? super C08181> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SimklApi.this.user(null, this);
        }
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object load(AuthData authData, String str, Continuation<? super SyncAPI.SyncResult> continuation) {
        return null;
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

    public final String getKey() {
        return this.key;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getRedirectUrlIdentifier() {
        return this.redirectUrlIdentifier;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getHasOAuth2() {
        return this.hasOAuth2;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getHasPin() {
        return this.hasPin;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public boolean getRequireLibraryRefresh() {
        return this.requireLibraryRefresh;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public void setRequireLibraryRefresh(boolean z) {
        this.requireLibraryRefresh = z;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainUrl = str;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Integer getIcon() {
        return Integer.valueOf(this.icon);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public String getCreateAccountUrl() {
        return this.createAccountUrl;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public SyncIdName getSyncIdName() {
        return this.syncIdName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\bÂ\u0002\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J+\u0010\b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u0002H\t2\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ$\u0010\u0010\u001a\u0004\u0018\u0001H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u00012\u0006\u0010\n\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklCache;", "", "<init>", "()V", "SIMKL_CACHE_KEY", "", "cleanOldCache", "", "setKey", ExifInterface.GPS_DIRECTION_TRUE, "path", ES6Iterator.VALUE_PROPERTY, "cacheTime", "Lkotlin/time/Duration;", "setKey-SxA4cEA", "(Ljava/lang/String;Ljava/lang/Object;J)V", "getKey", "(Ljava/lang/String;)Ljava/lang/Object;", "CacheTimes", "SimklCacheWrapper", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    static final class SimklCache {
        public static final SimklCache INSTANCE = new SimklCache();
        private static final String SIMKL_CACHE_KEY = "SIMKL_API_CACHE";

        private SimklCache() {
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklCache$CacheTimes;", "", ES6Iterator.VALUE_PROPERTY, "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "OneMonth", "ThirtyMinutes", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum CacheTimes {
            OneMonth("30d"),
            ThirtyMinutes("30m");

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
            private final String value;

            public static EnumEntries<CacheTimes> getEntries() {
                return $ENTRIES;
            }

            CacheTimes(String str) {
                this.value = str;
            }

            public final String getValue() {
                return this.value;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B'\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0010J\r\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\rR\u001a\u0010\u0003\u001a\u0004\u0018\u00018\u00008\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklCache$SimklCacheWrapper;", ExifInterface.GPS_DIRECTION_TRUE, "", "obj", "validUntil", "", "cacheTime", "<init>", "(Ljava/lang/Object;JJ)V", "getObj", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValidUntil", "()J", "getCacheTime", "isFresh", "", "remainingTime", "Lkotlin/time/Duration;", "remainingTime-UwyO8pc", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        static final class SimklCacheWrapper<T> {

            @JsonProperty("cacheTime")
            private final long cacheTime;

            @JsonProperty("obj")
            private final T obj;

            @JsonProperty("validUntil")
            private final long validUntil;

            public SimklCacheWrapper(@JsonProperty("obj") T t, @JsonProperty("validUntil") long j, @JsonProperty("cacheTime") long j2) {
                this.obj = t;
                this.validUntil = j;
                this.cacheTime = j2;
            }

            public final T getObj() {
                return this.obj;
            }

            public final long getValidUntil() {
                return this.validUntil;
            }

            public /* synthetic */ SimklCacheWrapper(Object obj, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(obj, j, (i & 4) != 0 ? APIHolder.INSTANCE.getUnixTime() : j2);
            }

            public final long getCacheTime() {
                return this.cacheTime;
            }

            public final boolean isFresh() {
                return this.validUntil > APIHolder.INSTANCE.getUnixTime();
            }

            /* JADX INFO: renamed from: remainingTime-UwyO8pc, reason: not valid java name */
            public final long m8448remainingTimeUwyO8pc() {
                long unixTime = APIHolder.INSTANCE.getUnixTime();
                long j = this.validUntil;
                if (j > unixTime) {
                    return DurationKt.toDuration(j - unixTime, DurationUnit.SECONDS);
                }
                return Duration.INSTANCE.m10413getZEROUwyO8pc();
            }
        }

        public final void cleanOldCache() {
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            List<String> keys = CloudStreamApp.INSTANCE.getKeys(SIMKL_CACHE_KEY);
            if (keys != null) {
                for (String str : keys) {
                    Context context = CloudStreamApp.INSTANCE.getContext();
                    Object obj = null;
                    if (context != null) {
                        try {
                            String string = DataStore.INSTANCE.getSharedPrefs(context).getString(str, null);
                            if (string != null) {
                                AppUtils appUtils = AppUtils.INSTANCE;
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.syncproviders.providers.SimklApi.SimklCache.SimklCacheWrapper"));
                                } catch (Throwable th) {
                                    Result.Companion companion2 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                                }
                                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                                    try {
                                        Result.Companion companion3 = Result.INSTANCE;
                                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SimklCacheWrapper.class), null, 2, null));
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
                                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                                    } catch (SerializationException e) {
                                        ArchComponentExtKt.logError(e);
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SimklCacheWrapper<Object>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$cleanOldCache$lambda$0$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SimklCacheWrapper<Object>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$cleanOldCache$lambda$0$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<SimklCacheWrapper<Object>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$cleanOldCache$lambda$0$$inlined$getKey$1
                                    });
                                }
                                obj = objDecodeFromString;
                            }
                        } catch (Exception unused2) {
                        }
                    }
                    SimklCacheWrapper simklCacheWrapper = (SimklCacheWrapper) obj;
                    if (simklCacheWrapper != null && !simklCacheWrapper.isFresh()) {
                        CloudStreamApp.INSTANCE.removeKey(str);
                    }
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:22:0x006a  */
        /* JADX WARN: Code duplicated, block: B:29:0x0080 A[Catch: Exception -> 0x0092, TRY_LEAVE, TryCatch #8 {Exception -> 0x0092, blocks: (B:5:0x0017, B:8:0x0023, B:13:0x0039, B:20:0x0064, B:23:0x006b, B:29:0x0080, B:28:0x007b, B:19:0x005a, B:12:0x002f, B:16:0x0040, B:9:0x0025, B:25:0x006f), top: B:85:0x0017, inners: #6, #7, #10 }] */
        /* JADX WARN: Code duplicated, block: B:49:0x00e3  */
        /* JADX WARN: Code duplicated, block: B:56:0x00f9 A[Catch: Exception -> 0x010e, TRY_LEAVE, TryCatch #2 {Exception -> 0x010e, blocks: (B:39:0x00b1, B:47:0x00dd, B:50:0x00e4, B:56:0x00f9, B:55:0x00f4, B:45:0x00d2, B:38:0x00a7, B:42:0x00b8, B:35:0x0099, B:52:0x00e8), top: B:79:0x0099, inners: #1, #3, #9, #9 }] */
        /* JADX WARN: Code duplicated, block: B:86:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:88:0x00e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        public final /* synthetic */ <T> T getKey(String path) {
            Object objDecodeFromString;
            SimklCacheWrapper simklCacheWrapper;
            Object objM8936constructorimpl;
            Object objDecodeFromString2;
            Object objM8936constructorimpl2;
            KSerializer kSerializer;
            Object objM8936constructorimpl3;
            KSerializer kSerializer2;
            Intrinsics.checkNotNullParameter(path, "path");
            Context context = CloudStreamApp.INSTANCE.getContext();
            if (context != null) {
                DataStore dataStore = DataStore.INSTANCE;
                try {
                    String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(SIMKL_CACHE_KEY, path), null);
                    if (string == null) {
                        objDecodeFromString = null;
                    } else {
                        AppUtils appUtils = AppUtils.INSTANCE;
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM8936constructorimpl3 = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                        }
                        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl3) == null) {
                            if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                                objM8936constructorimpl3 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl3;
                            if (kSerializer2 != null) {
                                try {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                                } catch (SerializationException e) {
                                    ArchComponentExtKt.logError(e);
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                                } catch (Throwable unused) {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                                }
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                            }
                        } else {
                            try {
                                Result.Companion companion3 = Result.INSTANCE;
                                objM8936constructorimpl3 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                            } catch (Throwable th2) {
                                Result.Companion companion4 = Result.INSTANCE;
                                objM8936constructorimpl3 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                            }
                            if (Result.m8942isFailureimpl(objM8936constructorimpl3)) {
                                objM8936constructorimpl3 = null;
                            }
                            kSerializer2 = (KSerializer) objM8936constructorimpl3;
                            if (kSerializer2 != null) {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                            } else {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                            }
                        }
                    }
                } catch (Exception unused2) {
                    objDecodeFromString = null;
                }
            } else {
                objDecodeFromString = null;
            }
            String str = (String) objDecodeFromString;
            if (str != null) {
                AppUtils appUtils2 = AppUtils.INSTANCE;
                try {
                    try {
                        Result.Companion companion5 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.syncproviders.providers.SimklApi.SimklCache.SimklCacheWrapper"));
                    } catch (Throwable th3) {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, str);
                            } catch (SerializationException e2) {
                                ArchComponentExtKt.logError(e2);
                                JsonMapper mapper = MainAPIKt.getMapper();
                                Intrinsics.needClassReification();
                                objDecodeFromString2 = mapper.readValue(str, new TypeReference<SimklCacheWrapper<T>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$getKey$lambda$0$$inlined$tryParseJson$1
                                });
                            } catch (Throwable unused3) {
                                JsonMapper mapper2 = MainAPIKt.getMapper();
                                Intrinsics.needClassReification();
                                objDecodeFromString2 = mapper2.readValue(str, new TypeReference<SimklCacheWrapper<T>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$getKey$lambda$0$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            JsonMapper mapper3 = MainAPIKt.getMapper();
                            Intrinsics.needClassReification();
                            objDecodeFromString2 = mapper3.readValue(str, new TypeReference<SimklCacheWrapper<T>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$getKey$lambda$0$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SimklCacheWrapper.class), null, 2, null));
                        } catch (Throwable th4) {
                            Result.Companion companion8 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                        }
                        objM8936constructorimpl = objM8936constructorimpl2;
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, str);
                        } else {
                            JsonMapper mapper4 = MainAPIKt.getMapper();
                            Intrinsics.needClassReification();
                            objDecodeFromString2 = mapper4.readValue(str, new TypeReference<SimklCacheWrapper<T>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$SimklCache$getKey$lambda$0$$inlined$tryParseJson$1
                            });
                        }
                    }
                } catch (Exception unused4) {
                    objDecodeFromString2 = null;
                }
                simklCacheWrapper = (SimklCacheWrapper) objDecodeFromString2;
            } else {
                simklCacheWrapper = null;
            }
            if (simklCacheWrapper == null || !simklCacheWrapper.isFresh()) {
                if (AppDebug.INSTANCE.isDebug()) {
                    Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache miss at: SIMKL_API_CACHE/" + path);
                }
                CloudStreamApp.INSTANCE.removeKey(SIMKL_CACHE_KEY, path);
                return null;
            }
            if (AppDebug.INSTANCE.isDebug()) {
                Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache hit at: SIMKL_API_CACHE/" + path + ". Remains fresh for " + Duration.m10326getInWholeDaysimpl(simklCacheWrapper.m8448remainingTimeUwyO8pc()) + " days or " + Duration.m10332getInWholeSecondsimpl(simklCacheWrapper.m8448remainingTimeUwyO8pc()) + " seconds.");
            }
            return (T) simklCacheWrapper.getObj();
        }

        /* JADX INFO: renamed from: setKey-SxA4cEA, reason: not valid java name */
        public final <T> void m8447setKeySxA4cEA(String path, T value, long cacheTime) {
            Intrinsics.checkNotNullParameter(path, "path");
            if (AppDebug.INSTANCE.isDebug()) {
                Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Set cache: SIMKL_API_CACHE/" + path + " for " + Duration.m10326getInWholeDaysimpl(cacheTime) + " days or " + Duration.m10332getInWholeSecondsimpl(cacheTime) + " seconds.");
            }
            CloudStreamApp.INSTANCE.setKey(SIMKL_CACHE_KEY, path, AppUtils.INSTANCE.toJson(new SimklCacheWrapper(value, APIHolder.INSTANCE.getUnixTime() + Duration.m10332getInWholeSecondsimpl(cacheTime), 0L, 4, null)));
        }
    }

    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001:\u000f#$%&'()*+,-./01B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u0018\u001a\u00020\u0019J>\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00152\b\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u001f\u001a\u0004\u0018\u00010\u00152\b\u0010 \u001a\u0004\u0018\u00010!H\u0086@¢\u0006\u0002\u0010\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion;", "", "<init>", "()V", "CLIENT_ID", "", "CLIENT_SECRET", "SIMKL_CACHED_LIST", "SIMKL_CACHED_LIST_TIME", "SIMKL_DATE_FORMAT", "getUnixTime", "", TypedValues.Custom.S_STRING, "(Ljava/lang/String;)Ljava/lang/Long;", "getDateTime", "unixTime", "(Ljava/lang/Long;)Ljava/lang/String;", "getPosterUrl", "poster", "getUrlFromId", "id", "", "getHeaders", "", "token", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "getEpisodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata;", "simklId", "type", "episodes", "hasEnded", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "SimklListStatusType", "TokenRequest", "TokenResponse", "SettingsResponse", "PinAuthResponse", "PinExchangeResponse", "ActivitiesResponse", "EpisodeMetadata", "MediaObject", "SimklScoreBuilder", "HistoryMediaObject", "RatingMediaObject", "StatusMediaObject", "StatusRequest", "AllItemsResponse", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Long getUnixTime(String string) {
            Date date;
            Instant instant;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SimklApi.SIMKL_DATE_FORMAT, Locale.getDefault());
                simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
                if (string == null || (date = simpleDateFormat.parse(string)) == null || (instant = DesugarDate.toInstant(date)) == null) {
                    return null;
                }
                return Long.valueOf(instant.getEpochSecond());
            } catch (Exception e) {
                ArchComponentExtKt.logError(e);
                return null;
            }
        }

        public final String getDateTime(Long unixTime) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SimklApi.SIMKL_DATE_FORMAT, Locale.getDefault());
                simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
                if (unixTime != null) {
                    return simpleDateFormat.format(DesugarDate.from(Instant.ofEpochSecond(unixTime.longValue())));
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }

        public final String getPosterUrl(String poster) {
            Intrinsics.checkNotNullParameter(poster, "poster");
            return "https://wsrv.nl/?url=https://simkl.in/posters/" + poster + "_m.webp";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getUrlFromId(int id) {
            return "https://simkl.com/shows/" + id;
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklListStatusType;", "", ES6Iterator.VALUE_PROPERTY, "", "stringRes", "originalName", "", "<init>", "(Ljava/lang/String;IIILjava/lang/String;)V", "getValue", "()I", "setValue", "(I)V", "getStringRes", "getOriginalName", "()Ljava/lang/String;", "Watching", "Completed", "Paused", "Dropped", "Planning", "ReWatching", "None", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum SimklListStatusType {
            Watching(0, R.string.type_watching, "watching"),
            Completed(1, R.string.type_completed, "completed"),
            Paused(2, R.string.type_on_hold, "hold"),
            Dropped(3, R.string.type_dropped, "dropped"),
            Planning(4, R.string.type_plan_to_watch, "plantowatch"),
            ReWatching(5, R.string.type_re_watching, "watching"),
            None(-1, R.string.none, null);

            private final String originalName;
            private final int stringRes;
            private int value;
            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            public static EnumEntries<SimklListStatusType> getEntries() {
                return $ENTRIES;
            }

            SimklListStatusType(int i, int i2, String str) {
                this.value = i;
                this.stringRes = i2;
                this.originalName = str;
            }

            public final int getValue() {
                return this.value;
            }

            public final void setValue(int i) {
                this.value = i;
            }

            public final int getStringRes() {
                return this.stringRes;
            }

            public final String getOriginalName() {
                return this.originalName;
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklListStatusType$Companion;", "", "<init>", "()V", "fromString", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklListStatusType;", TypedValues.Custom.S_STRING, "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final SimklListStatusType fromString(String string) {
                    SimklListStatusType next;
                    Intrinsics.checkNotNullParameter(string, "string");
                    Iterator<SimklListStatusType> it = SimklListStatusType.getEntries().iterator();
                    while (it.hasNext()) {
                        next = it.next();
                        if (Intrinsics.areEqual(next.getOriginalName(), string)) {
                            return next;
                        }
                    }
                    next = null;
                    return next;
                }
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$TokenRequest;", "", "code", "", "clientId", "clientSecret", "redirectUri", "grantType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getClientId", "getClientSecret", "getRedirectUri", "getGrantType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class TokenRequest {

            @JsonProperty("client_id")
            private final String clientId;

            @JsonProperty("client_secret")
            private final String clientSecret;

            @JsonProperty("code")
            private final String code;

            @JsonProperty("grant_type")
            private final String grantType;

            @JsonProperty("redirect_uri")
            private final String redirectUri;

            public static /* synthetic */ TokenRequest copy$default(TokenRequest tokenRequest, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = tokenRequest.code;
                }
                if ((i & 2) != 0) {
                    str2 = tokenRequest.clientId;
                }
                if ((i & 4) != 0) {
                    str3 = tokenRequest.clientSecret;
                }
                if ((i & 8) != 0) {
                    str4 = tokenRequest.redirectUri;
                }
                if ((i & 16) != 0) {
                    str5 = tokenRequest.grantType;
                }
                String str6 = str5;
                String str7 = str3;
                return tokenRequest.copy(str, str2, str7, str4, str6);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getCode() {
                return this.code;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getClientId() {
                return this.clientId;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getClientSecret() {
                return this.clientSecret;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getRedirectUri() {
                return this.redirectUri;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final String getGrantType() {
                return this.grantType;
            }

            public final TokenRequest copy(@JsonProperty("code") String code, @JsonProperty("client_id") String clientId, @JsonProperty("client_secret") String clientSecret, @JsonProperty("redirect_uri") String redirectUri, @JsonProperty("grant_type") String grantType) {
                Intrinsics.checkNotNullParameter(code, "code");
                Intrinsics.checkNotNullParameter(clientId, "clientId");
                Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
                Intrinsics.checkNotNullParameter(redirectUri, "redirectUri");
                Intrinsics.checkNotNullParameter(grantType, "grantType");
                return new TokenRequest(code, clientId, clientSecret, redirectUri, grantType);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof TokenRequest)) {
                    return false;
                }
                TokenRequest tokenRequest = (TokenRequest) other;
                return Intrinsics.areEqual(this.code, tokenRequest.code) && Intrinsics.areEqual(this.clientId, tokenRequest.clientId) && Intrinsics.areEqual(this.clientSecret, tokenRequest.clientSecret) && Intrinsics.areEqual(this.redirectUri, tokenRequest.redirectUri) && Intrinsics.areEqual(this.grantType, tokenRequest.grantType);
            }

            public int hashCode() {
                return (((((((this.code.hashCode() * 31) + this.clientId.hashCode()) * 31) + this.clientSecret.hashCode()) * 31) + this.redirectUri.hashCode()) * 31) + this.grantType.hashCode();
            }

            public String toString() {
                return "TokenRequest(code=" + this.code + ", clientId=" + this.clientId + ", clientSecret=" + this.clientSecret + ", redirectUri=" + this.redirectUri + ", grantType=" + this.grantType + ')';
            }

            public TokenRequest(@JsonProperty("code") String code, @JsonProperty("client_id") String clientId, @JsonProperty("client_secret") String clientSecret, @JsonProperty("redirect_uri") String redirectUri, @JsonProperty("grant_type") String grantType) {
                Intrinsics.checkNotNullParameter(code, "code");
                Intrinsics.checkNotNullParameter(clientId, "clientId");
                Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
                Intrinsics.checkNotNullParameter(redirectUri, "redirectUri");
                Intrinsics.checkNotNullParameter(grantType, "grantType");
                this.code = code;
                this.clientId = clientId;
                this.clientSecret = clientSecret;
                this.redirectUri = redirectUri;
                this.grantType = grantType;
            }

            public final String getCode() {
                return this.code;
            }

            public /* synthetic */ TokenRequest(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e" : str2, (i & 4) != 0 ? "1e8f8cd198588a544e2063c8d50c29ea6b5a4c7b7d8ee65e96fe4aaf001872e0" : str3, (i & 8) != 0 ? "cloudstreamapp://simkl" : str4, (i & 16) != 0 ? "authorization_code" : str5);
            }

            public final String getClientId() {
                return this.clientId;
            }

            public final String getClientSecret() {
                return this.clientSecret;
            }

            public final String getRedirectUri() {
                return this.redirectUri;
            }

            public final String getGrantType() {
                return this.grantType;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$TokenResponse;", "", "accessToken", "", "tokenType", "scope", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getTokenType", "getScope", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class TokenResponse {

            @JsonProperty("access_token")
            private final String accessToken;

            @JsonProperty("scope")
            private final String scope;

            @JsonProperty("token_type")
            private final String tokenType;

            public static /* synthetic */ TokenResponse copy$default(TokenResponse tokenResponse, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = tokenResponse.accessToken;
                }
                if ((i & 2) != 0) {
                    str2 = tokenResponse.tokenType;
                }
                if ((i & 4) != 0) {
                    str3 = tokenResponse.scope;
                }
                return tokenResponse.copy(str, str2, str3);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getTokenType() {
                return this.tokenType;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getScope() {
                return this.scope;
            }

            public final TokenResponse copy(@JsonProperty("access_token") String accessToken, @JsonProperty("token_type") String tokenType, @JsonProperty("scope") String scope) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(tokenType, "tokenType");
                Intrinsics.checkNotNullParameter(scope, "scope");
                return new TokenResponse(accessToken, tokenType, scope);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof TokenResponse)) {
                    return false;
                }
                TokenResponse tokenResponse = (TokenResponse) other;
                return Intrinsics.areEqual(this.accessToken, tokenResponse.accessToken) && Intrinsics.areEqual(this.tokenType, tokenResponse.tokenType) && Intrinsics.areEqual(this.scope, tokenResponse.scope);
            }

            public int hashCode() {
                return (((this.accessToken.hashCode() * 31) + this.tokenType.hashCode()) * 31) + this.scope.hashCode();
            }

            public String toString() {
                return "TokenResponse(accessToken=" + this.accessToken + ", tokenType=" + this.tokenType + ", scope=" + this.scope + ')';
            }

            public TokenResponse(@JsonProperty("access_token") String accessToken, @JsonProperty("token_type") String tokenType, @JsonProperty("scope") String scope) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(tokenType, "tokenType");
                Intrinsics.checkNotNullParameter(scope, "scope");
                this.accessToken = accessToken;
                this.tokenType = tokenType;
                this.scope = scope;
            }

            public final String getAccessToken() {
                return this.accessToken;
            }

            public final String getTokenType() {
                return this.tokenType;
            }

            public final String getScope() {
                return this.scope;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0016\u0017B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse;", "", "user", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$User;", "account", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$Account;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$User;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$Account;)V", "getUser", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$User;", "getAccount", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$Account;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "User", "Account", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class SettingsResponse {

            @JsonProperty("account")
            private final Account account;

            @JsonProperty("user")
            private final User user;

            public static /* synthetic */ SettingsResponse copy$default(SettingsResponse settingsResponse, User user, Account account, int i, Object obj) {
                if ((i & 1) != 0) {
                    user = settingsResponse.user;
                }
                if ((i & 2) != 0) {
                    account = settingsResponse.account;
                }
                return settingsResponse.copy(user, account);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final User getUser() {
                return this.user;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final Account getAccount() {
                return this.account;
            }

            public final SettingsResponse copy(@JsonProperty("user") User user, @JsonProperty("account") Account account) {
                Intrinsics.checkNotNullParameter(user, "user");
                Intrinsics.checkNotNullParameter(account, "account");
                return new SettingsResponse(user, account);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SettingsResponse)) {
                    return false;
                }
                SettingsResponse settingsResponse = (SettingsResponse) other;
                return Intrinsics.areEqual(this.user, settingsResponse.user) && Intrinsics.areEqual(this.account, settingsResponse.account);
            }

            public int hashCode() {
                return (this.user.hashCode() * 31) + this.account.hashCode();
            }

            public String toString() {
                return "SettingsResponse(user=" + this.user + ", account=" + this.account + ')';
            }

            public SettingsResponse(@JsonProperty("user") User user, @JsonProperty("account") Account account) {
                Intrinsics.checkNotNullParameter(user, "user");
                Intrinsics.checkNotNullParameter(account, "account");
                this.user = user;
                this.account = account;
            }

            public final User getUser() {
                return this.user;
            }

            public final Account getAccount() {
                return this.account;
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$User;", "", "name", "", "avatar", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getAvatar", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class User {

                @JsonProperty("avatar")
                private final String avatar;

                @JsonProperty("name")
                private final String name;

                public static /* synthetic */ User copy$default(User user, String str, String str2, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = user.name;
                    }
                    if ((i & 2) != 0) {
                        str2 = user.avatar;
                    }
                    return user.copy(str, str2);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getName() {
                    return this.name;
                }

                /* JADX INFO: renamed from: component2, reason: from getter */
                public final String getAvatar() {
                    return this.avatar;
                }

                public final User copy(@JsonProperty("name") String name, @JsonProperty("avatar") String avatar) {
                    Intrinsics.checkNotNullParameter(name, "name");
                    Intrinsics.checkNotNullParameter(avatar, "avatar");
                    return new User(name, avatar);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof User)) {
                        return false;
                    }
                    User user = (User) other;
                    return Intrinsics.areEqual(this.name, user.name) && Intrinsics.areEqual(this.avatar, user.avatar);
                }

                public int hashCode() {
                    return (this.name.hashCode() * 31) + this.avatar.hashCode();
                }

                public String toString() {
                    return "User(name=" + this.name + ", avatar=" + this.avatar + ')';
                }

                public User(@JsonProperty("name") String name, @JsonProperty("avatar") String avatar) {
                    Intrinsics.checkNotNullParameter(name, "name");
                    Intrinsics.checkNotNullParameter(avatar, "avatar");
                    this.name = name;
                    this.avatar = avatar;
                }

                public final String getName() {
                    return this.name;
                }

                public final String getAvatar() {
                    return this.avatar;
                }
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SettingsResponse$Account;", "", "id", "", "<init>", "(I)V", "getId", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class Account {

                @JsonProperty("id")
                private final int id;

                public static /* synthetic */ Account copy$default(Account account, int i, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        i = account.id;
                    }
                    return account.copy(i);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final int getId() {
                    return this.id;
                }

                public final Account copy(@JsonProperty("id") int id) {
                    return new Account(id);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof Account) && this.id == ((Account) other).id;
                }

                public int hashCode() {
                    return this.id;
                }

                public String toString() {
                    return "Account(id=" + this.id + ')';
                }

                public Account(@JsonProperty("id") int i) {
                    this.id = i;
                }

                public final int getId() {
                    return this.id;
                }
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\bHÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$PinAuthResponse;", "", "result", "", "deviceCode", "userCode", "verificationUrl", "expiresIn", "", "interval", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getResult", "()Ljava/lang/String;", "getDeviceCode", "getUserCode", "getVerificationUrl", "getExpiresIn", "()I", "getInterval", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class PinAuthResponse {

            @JsonProperty("device_code")
            private final String deviceCode;

            @JsonProperty("expires_in")
            private final int expiresIn;

            @JsonProperty("interval")
            private final int interval;

            @JsonProperty("result")
            private final String result;

            @JsonProperty("user_code")
            private final String userCode;

            @JsonProperty("verification_url")
            private final String verificationUrl;

            public static /* synthetic */ PinAuthResponse copy$default(PinAuthResponse pinAuthResponse, String str, String str2, String str3, String str4, int i, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = pinAuthResponse.result;
                }
                if ((i3 & 2) != 0) {
                    str2 = pinAuthResponse.deviceCode;
                }
                if ((i3 & 4) != 0) {
                    str3 = pinAuthResponse.userCode;
                }
                if ((i3 & 8) != 0) {
                    str4 = pinAuthResponse.verificationUrl;
                }
                if ((i3 & 16) != 0) {
                    i = pinAuthResponse.expiresIn;
                }
                if ((i3 & 32) != 0) {
                    i2 = pinAuthResponse.interval;
                }
                int i4 = i;
                int i5 = i2;
                return pinAuthResponse.copy(str, str2, str3, str4, i4, i5);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getResult() {
                return this.result;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getDeviceCode() {
                return this.deviceCode;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getUserCode() {
                return this.userCode;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final String getVerificationUrl() {
                return this.verificationUrl;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final int getExpiresIn() {
                return this.expiresIn;
            }

            /* JADX INFO: renamed from: component6, reason: from getter */
            public final int getInterval() {
                return this.interval;
            }

            public final PinAuthResponse copy(@JsonProperty("result") String result, @JsonProperty("device_code") String deviceCode, @JsonProperty("user_code") String userCode, @JsonProperty("verification_url") String verificationUrl, @JsonProperty("expires_in") int expiresIn, @JsonProperty("interval") int interval) {
                Intrinsics.checkNotNullParameter(result, "result");
                Intrinsics.checkNotNullParameter(deviceCode, "deviceCode");
                Intrinsics.checkNotNullParameter(userCode, "userCode");
                Intrinsics.checkNotNullParameter(verificationUrl, "verificationUrl");
                return new PinAuthResponse(result, deviceCode, userCode, verificationUrl, expiresIn, interval);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof PinAuthResponse)) {
                    return false;
                }
                PinAuthResponse pinAuthResponse = (PinAuthResponse) other;
                return Intrinsics.areEqual(this.result, pinAuthResponse.result) && Intrinsics.areEqual(this.deviceCode, pinAuthResponse.deviceCode) && Intrinsics.areEqual(this.userCode, pinAuthResponse.userCode) && Intrinsics.areEqual(this.verificationUrl, pinAuthResponse.verificationUrl) && this.expiresIn == pinAuthResponse.expiresIn && this.interval == pinAuthResponse.interval;
            }

            public int hashCode() {
                return (((((((((this.result.hashCode() * 31) + this.deviceCode.hashCode()) * 31) + this.userCode.hashCode()) * 31) + this.verificationUrl.hashCode()) * 31) + this.expiresIn) * 31) + this.interval;
            }

            public String toString() {
                return "PinAuthResponse(result=" + this.result + ", deviceCode=" + this.deviceCode + ", userCode=" + this.userCode + ", verificationUrl=" + this.verificationUrl + ", expiresIn=" + this.expiresIn + ", interval=" + this.interval + ')';
            }

            public PinAuthResponse(@JsonProperty("result") String result, @JsonProperty("device_code") String deviceCode, @JsonProperty("user_code") String userCode, @JsonProperty("verification_url") String verificationUrl, @JsonProperty("expires_in") int i, @JsonProperty("interval") int i2) {
                Intrinsics.checkNotNullParameter(result, "result");
                Intrinsics.checkNotNullParameter(deviceCode, "deviceCode");
                Intrinsics.checkNotNullParameter(userCode, "userCode");
                Intrinsics.checkNotNullParameter(verificationUrl, "verificationUrl");
                this.result = result;
                this.deviceCode = deviceCode;
                this.userCode = userCode;
                this.verificationUrl = verificationUrl;
                this.expiresIn = i;
                this.interval = i2;
            }

            public final String getResult() {
                return this.result;
            }

            public final String getDeviceCode() {
                return this.deviceCode;
            }

            public final String getUserCode() {
                return this.userCode;
            }

            public final String getVerificationUrl() {
                return this.verificationUrl;
            }

            public final int getExpiresIn() {
                return this.expiresIn;
            }

            public final int getInterval() {
                return this.interval;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$PinExchangeResponse;", "", "result", "", ContentType.Message.TYPE, "accessToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getResult", "()Ljava/lang/String;", "getMessage", "getAccessToken", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class PinExchangeResponse {

            @JsonProperty("access_token")
            private final String accessToken;

            @JsonProperty(ContentType.Message.TYPE)
            private final String message;

            @JsonProperty("result")
            private final String result;

            public static /* synthetic */ PinExchangeResponse copy$default(PinExchangeResponse pinExchangeResponse, String str, String str2, String str3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = pinExchangeResponse.result;
                }
                if ((i & 2) != 0) {
                    str2 = pinExchangeResponse.message;
                }
                if ((i & 4) != 0) {
                    str3 = pinExchangeResponse.accessToken;
                }
                return pinExchangeResponse.copy(str, str2, str3);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getResult() {
                return this.result;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            public final PinExchangeResponse copy(@JsonProperty("result") String result, @JsonProperty(ContentType.Message.TYPE) String message, @JsonProperty("access_token") String accessToken) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new PinExchangeResponse(result, message, accessToken);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof PinExchangeResponse)) {
                    return false;
                }
                PinExchangeResponse pinExchangeResponse = (PinExchangeResponse) other;
                return Intrinsics.areEqual(this.result, pinExchangeResponse.result) && Intrinsics.areEqual(this.message, pinExchangeResponse.message) && Intrinsics.areEqual(this.accessToken, pinExchangeResponse.accessToken);
            }

            public int hashCode() {
                int iHashCode = this.result.hashCode() * 31;
                String str = this.message;
                int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                String str2 = this.accessToken;
                return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
            }

            public String toString() {
                return "PinExchangeResponse(result=" + this.result + ", message=" + this.message + ", accessToken=" + this.accessToken + ')';
            }

            public PinExchangeResponse(@JsonProperty("result") String result, @JsonProperty(ContentType.Message.TYPE) String str, @JsonProperty("access_token") String str2) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
                this.message = str;
                this.accessToken = str2;
            }

            public /* synthetic */ PinExchangeResponse(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
            }

            public final String getResult() {
                return this.result;
            }

            public final String getMessage() {
                return this.message;
            }

            public final String getAccessToken() {
                return this.accessToken;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001bB1\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse;", "", "all", "", "tvShows", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse$UpdatedAt;", "anime", "movies", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse$UpdatedAt;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse$UpdatedAt;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse$UpdatedAt;)V", "getAll", "()Ljava/lang/String;", "getTvShows", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse$UpdatedAt;", "getAnime", "getMovies", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "UpdatedAt", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class ActivitiesResponse {

            @JsonProperty("all")
            private final String all;

            @JsonProperty("anime")
            private final UpdatedAt anime;

            @JsonProperty("movies")
            private final UpdatedAt movies;

            @JsonProperty("tv_shows")
            private final UpdatedAt tvShows;

            public static /* synthetic */ ActivitiesResponse copy$default(ActivitiesResponse activitiesResponse, String str, UpdatedAt updatedAt, UpdatedAt updatedAt2, UpdatedAt updatedAt3, int i, Object obj) {
                if ((i & 1) != 0) {
                    str = activitiesResponse.all;
                }
                if ((i & 2) != 0) {
                    updatedAt = activitiesResponse.tvShows;
                }
                if ((i & 4) != 0) {
                    updatedAt2 = activitiesResponse.anime;
                }
                if ((i & 8) != 0) {
                    updatedAt3 = activitiesResponse.movies;
                }
                return activitiesResponse.copy(str, updatedAt, updatedAt2, updatedAt3);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAll() {
                return this.all;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final UpdatedAt getTvShows() {
                return this.tvShows;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final UpdatedAt getAnime() {
                return this.anime;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final UpdatedAt getMovies() {
                return this.movies;
            }

            public final ActivitiesResponse copy(@JsonProperty("all") String all, @JsonProperty("tv_shows") UpdatedAt tvShows, @JsonProperty("anime") UpdatedAt anime, @JsonProperty("movies") UpdatedAt movies) {
                Intrinsics.checkNotNullParameter(tvShows, "tvShows");
                Intrinsics.checkNotNullParameter(anime, "anime");
                Intrinsics.checkNotNullParameter(movies, "movies");
                return new ActivitiesResponse(all, tvShows, anime, movies);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ActivitiesResponse)) {
                    return false;
                }
                ActivitiesResponse activitiesResponse = (ActivitiesResponse) other;
                return Intrinsics.areEqual(this.all, activitiesResponse.all) && Intrinsics.areEqual(this.tvShows, activitiesResponse.tvShows) && Intrinsics.areEqual(this.anime, activitiesResponse.anime) && Intrinsics.areEqual(this.movies, activitiesResponse.movies);
            }

            public int hashCode() {
                String str = this.all;
                return ((((((str == null ? 0 : str.hashCode()) * 31) + this.tvShows.hashCode()) * 31) + this.anime.hashCode()) * 31) + this.movies.hashCode();
            }

            public String toString() {
                return "ActivitiesResponse(all=" + this.all + ", tvShows=" + this.tvShows + ", anime=" + this.anime + ", movies=" + this.movies + ')';
            }

            public ActivitiesResponse(@JsonProperty("all") String str, @JsonProperty("tv_shows") UpdatedAt tvShows, @JsonProperty("anime") UpdatedAt anime, @JsonProperty("movies") UpdatedAt movies) {
                Intrinsics.checkNotNullParameter(tvShows, "tvShows");
                Intrinsics.checkNotNullParameter(anime, "anime");
                Intrinsics.checkNotNullParameter(movies, "movies");
                this.all = str;
                this.tvShows = tvShows;
                this.anime = anime;
                this.movies = movies;
            }

            public final String getAll() {
                return this.all;
            }

            public final UpdatedAt getTvShows() {
                return this.tvShows;
            }

            public final UpdatedAt getAnime() {
                return this.anime;
            }

            public final UpdatedAt getMovies() {
                return this.movies;
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$ActivitiesResponse$UpdatedAt;", "", "all", "", "removedFromList", "ratedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAll", "()Ljava/lang/String;", "getRemovedFromList", "getRatedAt", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class UpdatedAt {

                @JsonProperty("all")
                private final String all;

                @JsonProperty("rated_at")
                private final String ratedAt;

                @JsonProperty("removed_from_list")
                private final String removedFromList;

                public static /* synthetic */ UpdatedAt copy$default(UpdatedAt updatedAt, String str, String str2, String str3, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = updatedAt.all;
                    }
                    if ((i & 2) != 0) {
                        str2 = updatedAt.removedFromList;
                    }
                    if ((i & 4) != 0) {
                        str3 = updatedAt.ratedAt;
                    }
                    return updatedAt.copy(str, str2, str3);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getAll() {
                    return this.all;
                }

                /* JADX INFO: renamed from: component2, reason: from getter */
                public final String getRemovedFromList() {
                    return this.removedFromList;
                }

                /* JADX INFO: renamed from: component3, reason: from getter */
                public final String getRatedAt() {
                    return this.ratedAt;
                }

                public final UpdatedAt copy(@JsonProperty("all") String all, @JsonProperty("removed_from_list") String removedFromList, @JsonProperty("rated_at") String ratedAt) {
                    return new UpdatedAt(all, removedFromList, ratedAt);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof UpdatedAt)) {
                        return false;
                    }
                    UpdatedAt updatedAt = (UpdatedAt) other;
                    return Intrinsics.areEqual(this.all, updatedAt.all) && Intrinsics.areEqual(this.removedFromList, updatedAt.removedFromList) && Intrinsics.areEqual(this.ratedAt, updatedAt.ratedAt);
                }

                public int hashCode() {
                    String str = this.all;
                    int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                    String str2 = this.removedFromList;
                    int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                    String str3 = this.ratedAt;
                    return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
                }

                public String toString() {
                    return "UpdatedAt(all=" + this.all + ", removedFromList=" + this.removedFromList + ", ratedAt=" + this.ratedAt + ')';
                }

                public UpdatedAt(@JsonProperty("all") String str, @JsonProperty("removed_from_list") String str2, @JsonProperty("rated_at") String str3) {
                    this.all = str;
                    this.removedFromList = str2;
                    this.ratedAt = str3;
                }

                public final String getAll() {
                    return this.all;
                }

                public final String getRemovedFromList() {
                    return this.removedFromList;
                }

                public final String getRatedAt() {
                    return this.ratedAt;
                }
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0001 BA\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003JH\u0010\u0019\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata;", "", "title", "", "description", "season", "", "episode", "img", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getDescription", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "()I", "getImg", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata;", "equals", "", "other", "hashCode", "toString", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static final /* data */ class EpisodeMetadata {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("description")
            private final String description;

            @JsonProperty("episode")
            private final int episode;

            @JsonProperty("img")
            private final String img;

            @JsonProperty("season")
            private final Integer season;

            @JsonProperty("title")
            private final String title;

            public static /* synthetic */ EpisodeMetadata copy$default(EpisodeMetadata episodeMetadata, String str, String str2, Integer num, int i, String str3, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    str = episodeMetadata.title;
                }
                if ((i2 & 2) != 0) {
                    str2 = episodeMetadata.description;
                }
                if ((i2 & 4) != 0) {
                    num = episodeMetadata.season;
                }
                if ((i2 & 8) != 0) {
                    i = episodeMetadata.episode;
                }
                if ((i2 & 16) != 0) {
                    str3 = episodeMetadata.img;
                }
                String str4 = str3;
                Integer num2 = num;
                return episodeMetadata.copy(str, str2, num2, i, str4);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getTitle() {
                return this.title;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getDescription() {
                return this.description;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Integer getSeason() {
                return this.season;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final int getEpisode() {
                return this.episode;
            }

            /* JADX INFO: renamed from: component5, reason: from getter */
            public final String getImg() {
                return this.img;
            }

            public final EpisodeMetadata copy(@JsonProperty("title") String title, @JsonProperty("description") String description, @JsonProperty("season") Integer season, @JsonProperty("episode") int episode, @JsonProperty("img") String img) {
                return new EpisodeMetadata(title, description, season, episode, img);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof EpisodeMetadata)) {
                    return false;
                }
                EpisodeMetadata episodeMetadata = (EpisodeMetadata) other;
                return Intrinsics.areEqual(this.title, episodeMetadata.title) && Intrinsics.areEqual(this.description, episodeMetadata.description) && Intrinsics.areEqual(this.season, episodeMetadata.season) && this.episode == episodeMetadata.episode && Intrinsics.areEqual(this.img, episodeMetadata.img);
            }

            public int hashCode() {
                String str = this.title;
                int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                String str2 = this.description;
                int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                Integer num = this.season;
                int iHashCode3 = (((iHashCode2 + (num == null ? 0 : num.hashCode())) * 31) + this.episode) * 31;
                String str3 = this.img;
                return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
            }

            public String toString() {
                return "EpisodeMetadata(title=" + this.title + ", description=" + this.description + ", season=" + this.season + ", episode=" + this.episode + ", img=" + this.img + ')';
            }

            public EpisodeMetadata(@JsonProperty("title") String str, @JsonProperty("description") String str2, @JsonProperty("season") Integer num, @JsonProperty("episode") int i, @JsonProperty("img") String str3) {
                this.title = str;
                this.description = str2;
                this.season = num;
                this.episode = i;
                this.img = str3;
            }

            public final String getTitle() {
                return this.title;
            }

            public final String getDescription() {
                return this.description;
            }

            public final Integer getSeason() {
                return this.season;
            }

            public final int getEpisode() {
                return this.episode;
            }

            public final String getImg() {
                return this.img;
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005J\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00052\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¨\u0006\u000b"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata$Companion;", "", "<init>", "()V", "convertToEpisodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season$Episode;", "list", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata;", "convertToSeasons", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final List<MediaObject.Season.Episode> convertToEpisodes(List<EpisodeMetadata> list) {
                    if (list == null) {
                        return null;
                    }
                    List<EpisodeMetadata> list2 = list;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    Iterator<T> it = list2.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new MediaObject.Season.Episode(((EpisodeMetadata) it.next()).getEpisode()));
                    }
                    return arrayList;
                }

                public final List<MediaObject.Season> convertToSeasons(List<EpisodeMetadata> list) {
                    MediaObject.Season season;
                    if (list == null) {
                        return null;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : list) {
                        if (((EpisodeMetadata) obj).getSeason() != null) {
                            arrayList.add(obj);
                        }
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Object obj2 : arrayList) {
                        Integer season2 = ((EpisodeMetadata) obj2).getSeason();
                        Object obj3 = linkedHashMap.get(season2);
                        if (obj3 == null) {
                            obj3 = (List) new ArrayList();
                            linkedHashMap.put(season2, obj3);
                        }
                        ((List) obj3).add(obj2);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Map.Entry entry : linkedHashMap.entrySet()) {
                        Integer num = (Integer) entry.getKey();
                        List<MediaObject.Season.Episode> listConvertToEpisodes = EpisodeMetadata.INSTANCE.convertToEpisodes((List) entry.getValue());
                        if (listConvertToEpisodes != null) {
                            Intrinsics.checkNotNull(num);
                            season = new MediaObject.Season(num.intValue(), listConvertToEpisodes);
                        } else {
                            season = null;
                        }
                        if (season != null) {
                            arrayList2.add(season);
                        }
                    }
                    ArrayList arrayList3 = arrayList2;
                    return arrayList3.isEmpty() ? null : arrayList3;
                }
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001:\u0002%&B\u007f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u0010\b\u0003\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r¢\u0006\u0004\b\u0011\u0010\u0012J\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u0004\u0018\u00010$R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001a\u0010\u0016R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001f¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject;", "", "title", "", "year", "", "ids", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "totalEpisodes", NotificationCompat.CATEGORY_STATUS, "poster", "type", "seasons", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season;", "episodes", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season$Episode;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "getTotalEpisodes", "getStatus", "getPoster", "getType", "getSeasons", "()Ljava/util/List;", "getEpisodes", "hasEnded", "", "toSyncSearchResult", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "Season", "Ids", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static class MediaObject {

            @JsonProperty("episodes")
            private final List<Season.Episode> episodes;

            @JsonProperty("ids")
            private final Ids ids;

            @JsonProperty("poster")
            private final String poster;

            @JsonProperty("seasons")
            private final List<Season> seasons;

            @JsonProperty(NotificationCompat.CATEGORY_STATUS)
            private final String status;

            @JsonProperty("title")
            private final String title;

            @JsonProperty("total_episodes")
            private final Integer totalEpisodes;

            @JsonProperty("type")
            private final String type;

            @JsonProperty("year")
            private final Integer year;

            public MediaObject(@JsonProperty("title") String str, @JsonProperty("year") Integer num, @JsonProperty("ids") Ids ids, @JsonProperty("total_episodes") Integer num2, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String str2, @JsonProperty("poster") String str3, @JsonProperty("type") String str4, @JsonProperty("seasons") List<Season> list, @JsonProperty("episodes") List<Season.Episode> list2) {
                this.title = str;
                this.year = num;
                this.ids = ids;
                this.totalEpisodes = num2;
                this.status = str2;
                this.poster = str3;
                this.type = str4;
                this.seasons = list;
                this.episodes = list2;
            }

            public /* synthetic */ MediaObject(String str, Integer num, Ids ids, Integer num2, String str2, String str3, String str4, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, num, ids, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : list, (i & 256) != 0 ? null : list2);
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

            public final Integer getTotalEpisodes() {
                return this.totalEpisodes;
            }

            public final String getStatus() {
                return this.status;
            }

            public final String getPoster() {
                return this.poster;
            }

            public final String getType() {
                return this.type;
            }

            public final List<Season> getSeasons() {
                return this.seasons;
            }

            public final List<Season.Episode> getEpisodes() {
                return this.episodes;
            }

            public final boolean hasEnded() {
                return Intrinsics.areEqual(this.status, "released") || Intrinsics.areEqual(this.status, "ended");
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0016B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season;", "", "number", "", "episodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season$Episode;", "<init>", "(ILjava/util/List;)V", "getNumber", "()I", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "Episode", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            public static final /* data */ class Season {

                @JsonProperty("episodes")
                private final List<Episode> episodes;

                @JsonProperty("number")
                private final int number;

                /* JADX WARN: Multi-variable type inference failed */
                public static /* synthetic */ Season copy$default(Season season, int i, List list, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        i = season.number;
                    }
                    if ((i2 & 2) != 0) {
                        list = season.episodes;
                    }
                    return season.copy(i, list);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final int getNumber() {
                    return this.number;
                }

                public final List<Episode> component2() {
                    return this.episodes;
                }

                public final Season copy(@JsonProperty("number") int number, @JsonProperty("episodes") List<Episode> episodes) {
                    Intrinsics.checkNotNullParameter(episodes, "episodes");
                    return new Season(number, episodes);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof Season)) {
                        return false;
                    }
                    Season season = (Season) other;
                    return this.number == season.number && Intrinsics.areEqual(this.episodes, season.episodes);
                }

                public int hashCode() {
                    return (this.number * 31) + this.episodes.hashCode();
                }

                public String toString() {
                    return "Season(number=" + this.number + ", episodes=" + this.episodes + ')';
                }

                public Season(@JsonProperty("number") int i, @JsonProperty("episodes") List<Episode> episodes) {
                    Intrinsics.checkNotNullParameter(episodes, "episodes");
                    this.number = i;
                    this.episodes = episodes;
                }

                public final int getNumber() {
                    return this.number;
                }

                public final List<Episode> getEpisodes() {
                    return this.episodes;
                }

                /* JADX INFO: compiled from: SimklApi.kt */
                @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season$Episode;", "", "number", "", "<init>", "(I)V", "getNumber", "()I", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class Episode {

                    @JsonProperty("number")
                    private final int number;

                    public static /* synthetic */ Episode copy$default(Episode episode, int i, int i2, Object obj) {
                        if ((i2 & 1) != 0) {
                            i = episode.number;
                        }
                        return episode.copy(i);
                    }

                    /* JADX INFO: renamed from: component1, reason: from getter */
                    public final int getNumber() {
                        return this.number;
                    }

                    public final Episode copy(@JsonProperty("number") int number) {
                        return new Episode(number);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        return (other instanceof Episode) && this.number == ((Episode) other).number;
                    }

                    public int hashCode() {
                        return this.number;
                    }

                    public String toString() {
                        return "Episode(number=" + this.number + ')';
                    }

                    public Episode(@JsonProperty("number") int i) {
                        this.number = i;
                    }

                    public final int getNumber() {
                        return this.number;
                    }
                }
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fBC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003JJ\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "", "simkl", "", "imdb", "", "tmdb", "mal", "anilist", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSimkl", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getImdb", "()Ljava/lang/String;", "getTmdb", "getMal", "getAnilist", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "equals", "", "other", "hashCode", "toString", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            @JsonInclude(JsonInclude.Include.NON_EMPTY)
            public static final /* data */ class Ids {

                /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
                public static final Companion INSTANCE = new Companion(null);

                @JsonProperty("anilist")
                private final String anilist;

                @JsonProperty("imdb")
                private final String imdb;

                @JsonProperty("mal")
                private final String mal;

                @JsonProperty("simkl")
                private final Integer simkl;

                @JsonProperty("tmdb")
                private final String tmdb;

                public static /* synthetic */ Ids copy$default(Ids ids, Integer num, String str, String str2, String str3, String str4, int i, Object obj) {
                    if ((i & 1) != 0) {
                        num = ids.simkl;
                    }
                    if ((i & 2) != 0) {
                        str = ids.imdb;
                    }
                    if ((i & 4) != 0) {
                        str2 = ids.tmdb;
                    }
                    if ((i & 8) != 0) {
                        str3 = ids.mal;
                    }
                    if ((i & 16) != 0) {
                        str4 = ids.anilist;
                    }
                    String str5 = str4;
                    String str6 = str2;
                    return ids.copy(num, str, str6, str3, str5);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final Integer getSimkl() {
                    return this.simkl;
                }

                /* JADX INFO: renamed from: component2, reason: from getter */
                public final String getImdb() {
                    return this.imdb;
                }

                /* JADX INFO: renamed from: component3, reason: from getter */
                public final String getTmdb() {
                    return this.tmdb;
                }

                /* JADX INFO: renamed from: component4, reason: from getter */
                public final String getMal() {
                    return this.mal;
                }

                /* JADX INFO: renamed from: component5, reason: from getter */
                public final String getAnilist() {
                    return this.anilist;
                }

                public final Ids copy(@JsonProperty("simkl") Integer simkl, @JsonProperty("imdb") String imdb, @JsonProperty("tmdb") String tmdb, @JsonProperty("mal") String mal, @JsonProperty("anilist") String anilist) {
                    return new Ids(simkl, imdb, tmdb, mal, anilist);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof Ids)) {
                        return false;
                    }
                    Ids ids = (Ids) other;
                    return Intrinsics.areEqual(this.simkl, ids.simkl) && Intrinsics.areEqual(this.imdb, ids.imdb) && Intrinsics.areEqual(this.tmdb, ids.tmdb) && Intrinsics.areEqual(this.mal, ids.mal) && Intrinsics.areEqual(this.anilist, ids.anilist);
                }

                public int hashCode() {
                    Integer num = this.simkl;
                    int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
                    String str = this.imdb;
                    int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                    String str2 = this.tmdb;
                    int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
                    String str3 = this.mal;
                    int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
                    String str4 = this.anilist;
                    return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
                }

                public String toString() {
                    return "Ids(simkl=" + this.simkl + ", imdb=" + this.imdb + ", tmdb=" + this.tmdb + ", mal=" + this.mal + ", anilist=" + this.anilist + ')';
                }

                public Ids(@JsonProperty("simkl") Integer num, @JsonProperty("imdb") String str, @JsonProperty("tmdb") String str2, @JsonProperty("mal") String str3, @JsonProperty("anilist") String str4) {
                    this.simkl = num;
                    this.imdb = str;
                    this.tmdb = str2;
                    this.mal = str3;
                    this.anilist = str4;
                }

                public /* synthetic */ Ids(Integer num, String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this(num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4);
                }

                public final Integer getSimkl() {
                    return this.simkl;
                }

                public final String getImdb() {
                    return this.imdb;
                }

                public final String getTmdb() {
                    return this.tmdb;
                }

                public final String getMal() {
                    return this.mal;
                }

                public final String getAnilist() {
                    return this.anilist;
                }

                /* JADX INFO: compiled from: SimklApi.kt */
                @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¨\u0006\n"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids$Companion;", "", "<init>", "()V", "fromMap", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "map", "", "Lcom/lagradost/cloudstream3/SimklSyncServices;", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final class Companion {
                    public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                        this();
                    }

                    private Companion() {
                    }

                    public final Ids fromMap(Map<SimklSyncServices, String> map) {
                        Intrinsics.checkNotNullParameter(map, "map");
                        String str = map.get(SimklSyncServices.Simkl);
                        return new Ids(str != null ? StringsKt.toIntOrNull(str) : null, map.get(SimklSyncServices.Imdb), map.get(SimklSyncServices.Tmdb), map.get(SimklSyncServices.Mal), map.get(SimklSyncServices.AniList));
                    }
                }
            }

            public final SyncAPI.SyncSearchResult toSyncSearchResult() {
                Ids ids;
                Integer simkl;
                String strValueOf;
                String str = this.title;
                if (str == null || (ids = this.ids) == null || (simkl = ids.getSimkl()) == null || (strValueOf = String.valueOf(simkl.intValue())) == null) {
                    return null;
                }
                String urlFromId = SimklApi.INSTANCE.getUrlFromId(this.ids.getSimkl().intValue());
                String str2 = this.poster;
                return new SyncAPI.SyncSearchResult(str, "Simkl", strValueOf, urlFromId, str2 != null ? SimklApi.INSTANCE.getPosterUrl(str2) : null, Intrinsics.areEqual(this.type, "movie") ? TvType.Movie : TvType.TvSeries, null, null, null, null, 960, null);
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklScoreBuilder;", "", "<init>", "()V", "Builder", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class SimklScoreBuilder {

            /* JADX INFO: compiled from: SimklApi.kt */
            @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001B©\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012&\b\u0002\u0010\u000b\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\f\u0012&\b\u0002\u0010\u0010\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u001f\u0010\b\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u001aJ\u001f\u0010\n\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\t2\b\u0010\u001c\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u001aJ/\u0010\u001d\u001a\u00020\u00002\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\r2\b\u0010 \u001a\u0004\u0018\u00010\t2\b\u0010!\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\"J\u000e\u0010#\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010$J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u0017\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÂ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0007HÂ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\tHÂ\u0003¢\u0006\u0002\u0010)J\u0010\u0010*\u001a\u0004\u0018\u00010\tHÂ\u0003¢\u0006\u0002\u0010)J'\u0010+\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\fHÂ\u0003J'\u0010,\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\fHÂ\u0003J\t\u0010-\u001a\u00020\u0012HÂ\u0003J°\u0001\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2&\b\u0002\u0010\u000b\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\f2&\b\u0002\u0010\u0010\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001¢\u0006\u0002\u0010/J\u0014\u00100\u001a\u00020\u00122\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u00020\tHÖ\u0081\u0004J\n\u00103\u001a\u00020\u0003HÖ\u0081\u0004R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R,\u0010\u000b\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R,\u0010\u0010\u001a \u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklScoreBuilder$Builder;", "", ImagesContract.URL, "", "headers", "", "ids", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "score", "", NotificationCompat.CATEGORY_STATUS, "addEpisodes", "Lkotlin/Pair;", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season$Episode;", "removeEpisodes", "onList", "", "<init>", "(Ljava/lang/String;Ljava/util/Map;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/Pair;Lkotlin/Pair;Z)V", "Ljava/lang/Integer;", "token", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "apiUrl", "oldScore", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklScoreBuilder$Builder;", "newStatus", "oldStatus", "episodes", "allEpisodes", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata;", "newEpisodes", "oldEpisodes", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklScoreBuilder$Builder;", "execute", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "component1", "component2", "component3", "component4", "()Ljava/lang/Integer;", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/util/Map;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/Pair;Lkotlin/Pair;Z)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$SimklScoreBuilder$Builder;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class Builder {
                private Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> addEpisodes;
                private Map<String, String> headers;
                private MediaObject.Ids ids;
                private boolean onList;
                private Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> removeEpisodes;
                private Integer score;
                private Integer status;
                private String url;

                public Builder() {
                    this(null, null, null, null, null, null, null, false, 255, null);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                private final String getUrl() {
                    return this.url;
                }

                private final Map<String, String> component2() {
                    return this.headers;
                }

                /* JADX INFO: renamed from: component3, reason: from getter */
                private final MediaObject.Ids getIds() {
                    return this.ids;
                }

                /* JADX INFO: renamed from: component4, reason: from getter */
                private final Integer getScore() {
                    return this.score;
                }

                /* JADX INFO: renamed from: component5, reason: from getter */
                private final Integer getStatus() {
                    return this.status;
                }

                private final Pair<List<MediaObject.Season>, List<MediaObject.Season.Episode>> component6() {
                    return this.addEpisodes;
                }

                private final Pair<List<MediaObject.Season>, List<MediaObject.Season.Episode>> component7() {
                    return this.removeEpisodes;
                }

                /* JADX INFO: renamed from: component8, reason: from getter */
                private final boolean getOnList() {
                    return this.onList;
                }

                /* JADX WARN: Multi-variable type inference failed */
                public static /* synthetic */ Builder copy$default(Builder builder, String str, Map map, MediaObject.Ids ids, Integer num, Integer num2, Pair pair, Pair pair2, boolean z, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = builder.url;
                    }
                    if ((i & 2) != 0) {
                        map = builder.headers;
                    }
                    if ((i & 4) != 0) {
                        ids = builder.ids;
                    }
                    if ((i & 8) != 0) {
                        num = builder.score;
                    }
                    if ((i & 16) != 0) {
                        num2 = builder.status;
                    }
                    if ((i & 32) != 0) {
                        pair = builder.addEpisodes;
                    }
                    if ((i & 64) != 0) {
                        pair2 = builder.removeEpisodes;
                    }
                    if ((i & 128) != 0) {
                        z = builder.onList;
                    }
                    Pair pair3 = pair2;
                    boolean z2 = z;
                    Integer num3 = num2;
                    Pair pair4 = pair;
                    return builder.copy(str, map, ids, num, num3, pair4, pair3, z2);
                }

                public final Builder copy(String url, Map<String, String> headers, MediaObject.Ids ids, Integer score, Integer status, Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> addEpisodes, Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> removeEpisodes, boolean onList) {
                    return new Builder(url, headers, ids, score, status, addEpisodes, removeEpisodes, onList);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof Builder)) {
                        return false;
                    }
                    Builder builder = (Builder) other;
                    return Intrinsics.areEqual(this.url, builder.url) && Intrinsics.areEqual(this.headers, builder.headers) && Intrinsics.areEqual(this.ids, builder.ids) && Intrinsics.areEqual(this.score, builder.score) && Intrinsics.areEqual(this.status, builder.status) && Intrinsics.areEqual(this.addEpisodes, builder.addEpisodes) && Intrinsics.areEqual(this.removeEpisodes, builder.removeEpisodes) && this.onList == builder.onList;
                }

                public int hashCode() {
                    String str = this.url;
                    int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
                    Map<String, String> map = this.headers;
                    int iHashCode2 = (iHashCode + (map == null ? 0 : map.hashCode())) * 31;
                    MediaObject.Ids ids = this.ids;
                    int iHashCode3 = (iHashCode2 + (ids == null ? 0 : ids.hashCode())) * 31;
                    Integer num = this.score;
                    int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
                    Integer num2 = this.status;
                    int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
                    Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> pair = this.addEpisodes;
                    int iHashCode6 = (iHashCode5 + (pair == null ? 0 : pair.hashCode())) * 31;
                    Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> pair2 = this.removeEpisodes;
                    return ((iHashCode6 + (pair2 != null ? pair2.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.onList);
                }

                public String toString() {
                    return "Builder(url=" + this.url + ", headers=" + this.headers + ", ids=" + this.ids + ", score=" + this.score + ", status=" + this.status + ", addEpisodes=" + this.addEpisodes + ", removeEpisodes=" + this.removeEpisodes + ", onList=" + this.onList + ')';
                }

                public Builder(String str, Map<String, String> map, MediaObject.Ids ids, Integer num, Integer num2, Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> pair, Pair<? extends List<MediaObject.Season>, ? extends List<MediaObject.Season.Episode>> pair2, boolean z) {
                    this.url = str;
                    this.headers = map;
                    this.ids = ids;
                    this.score = num;
                    this.status = num2;
                    this.addEpisodes = pair;
                    this.removeEpisodes = pair2;
                    this.onList = z;
                }

                public /* synthetic */ Builder(String str, Map map, MediaObject.Ids ids, Integer num, Integer num2, Pair pair, Pair pair2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
                    this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : map, (i & 4) != 0 ? null : ids, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : pair, (i & 64) != 0 ? null : pair2, (i & 128) != 0 ? false : z);
                }

                public final Builder token(AuthToken token) {
                    Intrinsics.checkNotNullParameter(token, "token");
                    this.headers = SimklApi.INSTANCE.getHeaders(token);
                    return this;
                }

                public final Builder apiUrl(String url) {
                    Intrinsics.checkNotNullParameter(url, "url");
                    this.url = url;
                    return this;
                }

                public final Builder ids(MediaObject.Ids ids) {
                    Intrinsics.checkNotNullParameter(ids, "ids");
                    this.ids = ids;
                    return this;
                }

                public final Builder score(Integer score, Integer oldScore) {
                    if (!Intrinsics.areEqual(score, oldScore)) {
                        this.score = score;
                    }
                    return this;
                }

                public final Builder status(Integer newStatus, Integer oldStatus) {
                    this.onList = oldStatus != null;
                    if (!Intrinsics.areEqual(newStatus, oldStatus)) {
                        this.status = newStatus;
                        return this;
                    }
                    this.status = null;
                    return this;
                }

                public final Builder episodes(List<EpisodeMetadata> allEpisodes, Integer newEpisodes, Integer oldEpisodes) {
                    if (allEpisodes != null && newEpisodes != null) {
                        if (newEpisodes.intValue() > (oldEpisodes != null ? oldEpisodes.intValue() : 0)) {
                            this.addEpisodes = episodes$lambda$0$getEpisodes(CollectionsKt.take(allEpisodes, newEpisodes.intValue()));
                            if (!this.onList) {
                                this.status = Integer.valueOf(SimklListStatusType.Watching.getValue());
                            }
                        }
                        if ((oldEpisodes != null ? oldEpisodes.intValue() : 0) > newEpisodes.intValue()) {
                            this.removeEpisodes = episodes$lambda$0$getEpisodes(CollectionsKt.drop(allEpisodes, newEpisodes.intValue()));
                        }
                    }
                    return this;
                }

                private static final Pair<List<MediaObject.Season>, List<MediaObject.Season.Episode>> episodes$lambda$0$getEpisodes(List<EpisodeMetadata> list) {
                    List<EpisodeMetadata> list2 = list;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator<T> it = list2.iterator();
                        while (it.hasNext()) {
                            if (((EpisodeMetadata) it.next()).getSeason() != null) {
                                return TuplesKt.to(EpisodeMetadata.INSTANCE.convertToSeasons(list), null);
                            }
                        }
                    }
                    return TuplesKt.to(null, EpisodeMetadata.INSTANCE.convertToEpisodes(list));
                }

                /* JADX WARN: Code duplicated, block: B:55:0x0234  */
                /* JADX WARN: Code duplicated, block: B:58:0x02ce  */
                /* JADX WARN: Code duplicated, block: B:61:0x02db  */
                /* JADX WARN: Code duplicated, block: B:64:0x02e8  */
                /* JADX WARN: Code duplicated, block: B:67:0x02f3  */
                /* JADX WARN: Code duplicated, block: B:70:0x02fc  */
                /* JADX WARN: Code duplicated, block: B:72:0x0300  */
                /* JADX WARN: Code duplicated, block: B:73:0x0305  */
                /* JADX WARN: Code duplicated, block: B:76:0x030b A[ADDED_TO_REGION] */
                /* JADX WARN: Code duplicated, block: B:7:0x0018  */
                /* JADX WARN: Code duplicated, block: B:81:0x032f  */
                /* JADX WARN: Code duplicated, block: B:82:0x0337  */
                /* JADX WARN: Code duplicated, block: B:85:0x033c  */
                /* JADX WARN: Code duplicated, block: B:86:0x0345  */
                /* JADX WARN: Code duplicated, block: B:88:0x0349  */
                /* JADX WARN: Code duplicated, block: B:89:0x0353  */
                /* JADX WARN: Code duplicated, block: B:93:0x03a8  */
                /* JADX WARN: Code duplicated, block: B:96:0x03b7 A[ADDED_TO_REGION] */
                /* JADX WARN: Code duplicated, block: B:99:0x03be  */
                /* JADX WARN: Code restructure failed: missing block: B:29:0x013b, code lost:
                
                    if (r1 == r2) goto L92;
                 */
                /* JADX WARN: Instruction removed from duplicated block: B:55:0x0234, please report this as an issue */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r1v15 */
                /* JADX WARN: Type inference failed for: r1v19 */
                /* JADX WARN: Type inference failed for: r1v22 */
                /* JADX WARN: Type inference failed for: r1v29 */
                /* JADX WARN: Type inference failed for: r25v1 */
                /* JADX WARN: Type inference failed for: r25v3 */
                /* JADX WARN: Type inference failed for: r25v6 */
                /* JADX WARN: Type inference failed for: r3v18 */
                /* JADX WARN: Type inference failed for: r3v20 */
                /* JADX WARN: Type inference failed for: r3v27 */
                /* JADX WARN: Type inference failed for: r6v1 */
                /* JADX WARN: Type inference failed for: r6v6, types: [int] */
                /* JADX WARN: Type inference failed for: r6v8 */
                /* JADX WARN: Type inference failed for: r9v10, types: [int] */
                /* JADX WARN: Type inference failed for: r9v12 */
                /* JADX WARN: Type inference failed for: r9v7 */
                /* JADX WARN: Type inference failed for: r9v9 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object execute(kotlin.coroutines.Continuation<? super java.lang.Boolean> r32) {
                    /*
                        Method dump skipped, instruction units count: 965
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.SimklScoreBuilder.Builder.execute(kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            private SimklScoreBuilder() {
            }
        }

        public final Map<String, String> getHeaders(AuthToken token) {
            Intrinsics.checkNotNullParameter(token, "token");
            return MapsKt.mapOf(TuplesKt.to("Authorization", "Bearer " + token.getAccessToken()), TuplesKt.to("simkl-api-key", "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e"));
        }

        /* JADX WARN: Code duplicated, block: B:127:0x02cc  */
        /* JADX WARN: Code duplicated, block: B:130:0x034a A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:131:0x034b  */
        /* JADX WARN: Code duplicated, block: B:164:0x00df A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:166:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:37:0x00da  */
        /* JADX WARN: Code duplicated, block: B:44:0x00f0 A[Catch: Exception -> 0x0102, TRY_LEAVE, TryCatch #10 {Exception -> 0x0102, blocks: (B:20:0x0087, B:23:0x0093, B:28:0x00a9, B:35:0x00d4, B:38:0x00db, B:44:0x00f0, B:43:0x00eb, B:34:0x00ca, B:27:0x009f, B:31:0x00b0, B:24:0x0095, B:40:0x00df), top: B:163:0x0087, inners: #5, #9, #11 }] */
        /* JADX WARN: Code duplicated, block: B:65:0x0158  */
        /* JADX WARN: Code duplicated, block: B:72:0x016e A[Catch: Exception -> 0x010c, TRY_LEAVE, TryCatch #1 {Exception -> 0x010c, blocks: (B:56:0x0127, B:63:0x0152, B:66:0x0159, B:72:0x016e, B:71:0x0169, B:62:0x0148, B:55:0x011d, B:59:0x012e, B:68:0x015d, B:52:0x010f), top: B:152:0x010a, inners: #3, #4, #7, #10 }] */
        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        /* JADX WARN: Instruction removed from duplicated block: B:127:0x02cc, please report this as an issue */
        public final Object getEpisodes(Integer num, String str, Integer num2, Boolean bool, Continuation<? super EpisodeMetadata[]> continuation) {
            SimklApi$Companion$getEpisodes$1 simklApi$Companion$getEpisodes$1;
            Object objDecodeFromString;
            SimklCache.SimklCacheWrapper simklCacheWrapper;
            Object obj;
            String str2;
            Boolean bool2;
            boolean z;
            Object obj2;
            String str3;
            Object objDecodeFromString2;
            Object objM8936constructorimpl;
            KSerializer kSerializer;
            Object objM8936constructorimpl2;
            KSerializer kSerializer2;
            Object safe;
            if (continuation instanceof SimklApi$Companion$getEpisodes$1) {
                simklApi$Companion$getEpisodes$1 = (SimklApi$Companion$getEpisodes$1) continuation;
                if ((simklApi$Companion$getEpisodes$1.label & Integer.MIN_VALUE) != 0) {
                    simklApi$Companion$getEpisodes$1.label -= Integer.MIN_VALUE;
                } else {
                    simklApi$Companion$getEpisodes$1 = new SimklApi$Companion$getEpisodes$1(this, continuation);
                }
            } else {
                simklApi$Companion$getEpisodes$1 = new SimklApi$Companion$getEpisodes$1(this, continuation);
            }
            Object obj3 = simklApi$Companion$getEpisodes$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = simklApi$Companion$getEpisodes$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj3);
                if (num == null) {
                    return null;
                }
                String str4 = "Episodes/" + num.intValue();
                SimklCache simklCache = SimklCache.INSTANCE;
                Context context = CloudStreamApp.INSTANCE.getContext();
                if (context != null) {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName("SIMKL_API_CACHE", str4), null);
                        if (string == null) {
                            objDecodeFromString = null;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                            } catch (Throwable th) {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                                if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                    objM8936constructorimpl2 = null;
                                }
                                kSerializer2 = (KSerializer) objM8936constructorimpl2;
                                if (kSerializer2 != null) {
                                    try {
                                        objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                                    } catch (SerializationException e) {
                                        ArchComponentExtKt.logError(e);
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                                }
                            } else {
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                                } catch (Throwable th2) {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                                    objM8936constructorimpl2 = null;
                                }
                                kSerializer2 = (KSerializer) objM8936constructorimpl2;
                                if (kSerializer2 != null) {
                                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                                }
                            }
                        }
                    } catch (Exception unused2) {
                        objDecodeFromString = null;
                    }
                } else {
                    objDecodeFromString = null;
                }
                String str5 = (String) objDecodeFromString;
                if (str5 != null) {
                    AppUtils appUtils2 = AppUtils.INSTANCE;
                    try {
                        if (str5 == null) {
                            objDecodeFromString2 = null;
                        } else {
                            try {
                                Result.Companion companion5 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.syncproviders.providers.SimklApi.SimklCache.SimklCacheWrapper"));
                            } catch (Throwable th3) {
                                Result.Companion companion6 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                            }
                            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    try {
                                        objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, str5);
                                    } catch (SerializationException e2) {
                                        ArchComponentExtKt.logError(e2);
                                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<EpisodeMetadata[]>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion$getEpisodes$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused3) {
                                        objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<EpisodeMetadata[]>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion$getEpisodes$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<EpisodeMetadata[]>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion$getEpisodes$$inlined$getKey$1
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion7 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SimklCache.SimklCacheWrapper.class), null, 2, null));
                                } catch (Throwable th4) {
                                    Result.Companion companion8 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                                }
                                if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                                    objM8936constructorimpl = null;
                                }
                                kSerializer = (KSerializer) objM8936constructorimpl;
                                if (kSerializer != null) {
                                    objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, str5);
                                } else {
                                    objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<EpisodeMetadata[]>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion$getEpisodes$$inlined$getKey$1
                                    });
                                }
                            }
                        }
                    } catch (Exception unused4) {
                    }
                    simklCacheWrapper = (SimklCache.SimklCacheWrapper) objDecodeFromString2;
                } else {
                    simklCacheWrapper = null;
                }
                if (simklCacheWrapper == null || !simklCacheWrapper.isFresh()) {
                    if (AppDebug.INSTANCE.isDebug()) {
                        Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache miss at: SIMKL_API_CACHE/" + str4);
                    }
                    CloudStreamApp.INSTANCE.removeKey("SIMKL_API_CACHE", str4);
                    obj = null;
                } else {
                    if (AppDebug.INSTANCE.isDebug()) {
                        Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache hit at: SIMKL_API_CACHE/" + str4 + ". Remains fresh for " + Duration.m10326getInWholeDaysimpl(simklCacheWrapper.m8448remainingTimeUwyO8pc()) + " days or " + Duration.m10332getInWholeSecondsimpl(simklCacheWrapper.m8448remainingTimeUwyO8pc()) + " seconds.");
                    }
                    obj = simklCacheWrapper.getObj();
                }
                EpisodeMetadata[] episodeMetadataArr = (EpisodeMetadata[]) obj;
                if (episodeMetadataArr != null) {
                    if (episodeMetadataArr.length >= (num2 != null ? num2.intValue() : 0)) {
                        return episodeMetadataArr;
                    }
                }
                if (Intrinsics.areEqual(str, "anime") && num2 != null) {
                    Integer num3 = num2.intValue() > 0 ? num2 : null;
                    if (num3 == null) {
                        return null;
                    }
                    IntRange intRange = new IntRange(1, num3.intValue());
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
                    Iterator<Integer> it = intRange.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new EpisodeMetadata(null, null, null, ((IntIterator) it).nextInt(), null));
                    }
                    return (EpisodeMetadata[]) arrayList.toArray(new EpisodeMetadata[0]);
                }
                if (str != null) {
                    int iHashCode = str.hashCode();
                    if (iHashCode != 3714) {
                        if (iHashCode != 92962932) {
                            if (iHashCode == 104087344) {
                                str.equals("movie");
                            }
                        } else if (str.equals("anime")) {
                            str2 = "https://api.simkl.com/anime/episodes/" + num.intValue();
                            if (AppDebug.INSTANCE.isDebug()) {
                                Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Requesting episodes from " + str2);
                            }
                            Requests app2 = MainActivityKt.getApp();
                            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("client_id", "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e"));
                            simklApi$Companion$getEpisodes$1.L$0 = SpillingKt.nullOutSpilledVariable(num);
                            simklApi$Companion$getEpisodes$1.L$1 = SpillingKt.nullOutSpilledVariable(str);
                            simklApi$Companion$getEpisodes$1.L$2 = SpillingKt.nullOutSpilledVariable(num2);
                            bool2 = bool;
                            simklApi$Companion$getEpisodes$1.L$3 = bool2;
                            simklApi$Companion$getEpisodes$1.L$4 = str4;
                            simklApi$Companion$getEpisodes$1.L$5 = SpillingKt.nullOutSpilledVariable(episodeMetadataArr);
                            simklApi$Companion$getEpisodes$1.L$6 = SpillingKt.nullOutSpilledVariable(str2);
                            simklApi$Companion$getEpisodes$1.label = 1;
                            z = true;
                            obj2 = null;
                            obj3 = Requests.get$default(app2, str2, null, null, mapMapOf, null, false, 0, null, 0L, null, false, null, simklApi$Companion$getEpisodes$1, 4086, null);
                            if (obj3 == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            str3 = str4;
                        }
                    } else if (str.equals("tv")) {
                        str2 = "https://api.simkl.com/tv/episodes/" + num.intValue();
                        if (AppDebug.INSTANCE.isDebug()) {
                            Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Requesting episodes from " + str2);
                        }
                        Requests app3 = MainActivityKt.getApp();
                        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to("client_id", "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e"));
                        simklApi$Companion$getEpisodes$1.L$0 = SpillingKt.nullOutSpilledVariable(num);
                        simklApi$Companion$getEpisodes$1.L$1 = SpillingKt.nullOutSpilledVariable(str);
                        simklApi$Companion$getEpisodes$1.L$2 = SpillingKt.nullOutSpilledVariable(num2);
                        bool2 = bool;
                        simklApi$Companion$getEpisodes$1.L$3 = bool2;
                        simklApi$Companion$getEpisodes$1.L$4 = str4;
                        simklApi$Companion$getEpisodes$1.L$5 = SpillingKt.nullOutSpilledVariable(episodeMetadataArr);
                        simklApi$Companion$getEpisodes$1.L$6 = SpillingKt.nullOutSpilledVariable(str2);
                        simklApi$Companion$getEpisodes$1.label = 1;
                        z = true;
                        obj2 = null;
                        obj3 = Requests.get$default(app3, str2, null, null, mapMapOf2, null, false, 0, null, 0L, null, false, null, simklApi$Companion$getEpisodes$1, 4086, null);
                        if (obj3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        str3 = str4;
                    }
                }
                return null;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String str6 = (String) simklApi$Companion$getEpisodes$1.L$4;
            Boolean bool3 = (Boolean) simklApi$Companion$getEpisodes$1.L$3;
            ResultKt.throwOnFailure(obj3);
            str3 = str6;
            bool2 = bool3;
            z = true;
            obj2 = null;
            NiceResponse niceResponse = (NiceResponse) obj3;
            try {
                ResponseParser parser = niceResponse.getParser();
                Intrinsics.checkNotNull(parser);
                safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(EpisodeMetadata[].class));
            } catch (Exception e3) {
                e3.printStackTrace();
                safe = obj2;
            }
            EpisodeMetadata[] episodeMetadataArr2 = (EpisodeMetadata[]) safe;
            if (episodeMetadataArr2 == null) {
                return obj2;
            }
            SimklCache.INSTANCE.m8447setKeySxA4cEA(str3, episodeMetadataArr2, Duration.INSTANCE.m10414parseUwyO8pc((Intrinsics.areEqual(bool2, Boxing.boxBoolean(z)) ? SimklCache.CacheTimes.OneMonth : SimklCache.CacheTimes.ThirtyMinutes).getValue()));
            return episodeMetadataArr2;
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0003\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0010\b\u0003\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\t\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$HistoryMediaObject;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject;", "title", "", "year", "", "ids", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "seasons", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season;", "episodes", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Season$Episode;", "rating", "ratedAt", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;)V", "getRating", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRatedAt", "()Ljava/lang/String;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class HistoryMediaObject extends MediaObject {

            @JsonProperty("rated_at")
            private final String ratedAt;

            @JsonProperty("rating")
            private final Integer rating;

            public HistoryMediaObject() {
                this(null, null, null, null, null, null, null, 127, null);
            }

            public HistoryMediaObject(@JsonProperty("title") String str, @JsonProperty("year") Integer num, @JsonProperty("ids") MediaObject.Ids ids, @JsonProperty("seasons") List<MediaObject.Season> list, @JsonProperty("episodes") List<MediaObject.Season.Episode> list2, @JsonProperty("rating") Integer num2, @JsonProperty("rated_at") String str2) {
                super(str, num, ids, null, null, null, null, list, list2, 120, null);
                this.rating = num2;
                this.ratedAt = str2;
            }

            public /* synthetic */ HistoryMediaObject(String str, Integer num, MediaObject.Ids ids, List list, List list2, Integer num2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : ids, (i & 8) != 0 ? null : list, (i & 16) != 0 ? null : list2, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : str2);
            }

            public final Integer getRating() {
                return this.rating;
            }

            public final String getRatedAt() {
                return this.ratedAt;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001BA\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0005\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$RatingMediaObject;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject;", "title", "", "year", "", "ids", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", "rating", "ratedAt", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;ILjava/lang/String;)V", "getRating", "()I", "getRatedAt", "()Ljava/lang/String;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class RatingMediaObject extends MediaObject {

            @JsonProperty("rated_at")
            private final String ratedAt;

            @JsonProperty("rating")
            private final int rating;

            public RatingMediaObject(@JsonProperty("title") String str, @JsonProperty("year") Integer num, @JsonProperty("ids") MediaObject.Ids ids, @JsonProperty("rating") int i, @JsonProperty("rated_at") String str2) {
                super(str, num, ids, null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
                this.rating = i;
                this.ratedAt = str2;
            }

            public final int getRating() {
                return this.rating;
            }

            public /* synthetic */ RatingMediaObject(String str, Integer num, MediaObject.Ids ids, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, num, ids, i, (i2 & 16) != 0 ? SimklApi.INSTANCE.getDateTime(Long.valueOf(APIHolder.INSTANCE.getUnixTime())) : str2);
            }

            public final String getRatedAt() {
                return this.ratedAt;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001BA\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$StatusMediaObject;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject;", "title", "", "year", "", "ids", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;", TypedValues.TransitionType.S_TO, "watchedAt", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject$Ids;Ljava/lang/String;Ljava/lang/String;)V", "getTo", "()Ljava/lang/String;", "getWatchedAt", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class StatusMediaObject extends MediaObject {

            @JsonProperty(TypedValues.TransitionType.S_TO)
            private final String to;

            @JsonProperty("watched_at")
            private final String watchedAt;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StatusMediaObject(@JsonProperty("title") String str, @JsonProperty("year") Integer num, @JsonProperty("ids") MediaObject.Ids ids, @JsonProperty(TypedValues.TransitionType.S_TO) String to, @JsonProperty("watched_at") String str2) {
                super(str, num, ids, null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
                Intrinsics.checkNotNullParameter(to, "to");
                this.to = to;
                this.watchedAt = str2;
            }

            public final String getTo() {
                return this.to;
            }

            public /* synthetic */ StatusMediaObject(String str, Integer num, MediaObject.Ids ids, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, num, ids, str2, (i & 16) != 0 ? SimklApi.INSTANCE.getDateTime(Long.valueOf(APIHolder.INSTANCE.getUnixTime())) : str3);
            }

            public final String getWatchedAt() {
                return this.watchedAt;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$StatusRequest;", "", "movies", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$MediaObject;", "shows", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getMovies", "()Ljava/util/List;", "getShows", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class StatusRequest {

            @JsonProperty("movies")
            private final List<MediaObject> movies;

            @JsonProperty("shows")
            private final List<MediaObject> shows;

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ StatusRequest copy$default(StatusRequest statusRequest, List list, List list2, int i, Object obj) {
                if ((i & 1) != 0) {
                    list = statusRequest.movies;
                }
                if ((i & 2) != 0) {
                    list2 = statusRequest.shows;
                }
                return statusRequest.copy(list, list2);
            }

            public final List<MediaObject> component1() {
                return this.movies;
            }

            public final List<MediaObject> component2() {
                return this.shows;
            }

            public final StatusRequest copy(@JsonProperty("movies") List<? extends MediaObject> movies, @JsonProperty("shows") List<? extends MediaObject> shows) {
                Intrinsics.checkNotNullParameter(movies, "movies");
                Intrinsics.checkNotNullParameter(shows, "shows");
                return new StatusRequest(movies, shows);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof StatusRequest)) {
                    return false;
                }
                StatusRequest statusRequest = (StatusRequest) other;
                return Intrinsics.areEqual(this.movies, statusRequest.movies) && Intrinsics.areEqual(this.shows, statusRequest.shows);
            }

            public int hashCode() {
                return (this.movies.hashCode() * 31) + this.shows.hashCode();
            }

            public String toString() {
                return "StatusRequest(movies=" + this.movies + ", shows=" + this.shows + ')';
            }

            /* JADX WARN: Multi-variable type inference failed */
            public StatusRequest(@JsonProperty("movies") List<? extends MediaObject> movies, @JsonProperty("shows") List<? extends MediaObject> shows) {
                Intrinsics.checkNotNullParameter(movies, "movies");
                Intrinsics.checkNotNullParameter(shows, "shows");
                this.movies = movies;
                this.shows = shows;
            }

            public final List<MediaObject> getMovies() {
                return this.movies;
            }

            public final List<MediaObject> getShows() {
                return this.shows;
            }
        }

        /* JADX INFO: compiled from: SimklApi.kt */
        @kotlin.Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u00192\u00020\u0001:\u0004\u0019\u001a\u001b\u001cB7\u0012\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse;", "", "shows", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata;", "anime", "movies", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$MovieMetadata;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getShows", "()Ljava/util/List;", "getAnime", "getMovies", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "Metadata", "MovieMetadata", "ShowMetadata", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class AllItemsResponse {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);

            @JsonProperty("anime")
            private final List<ShowMetadata> anime;

            @JsonProperty("movies")
            private final List<MovieMetadata> movies;

            @JsonProperty("shows")
            private final List<ShowMetadata> shows;

            /* JADX INFO: compiled from: SimklApi.kt */
            @kotlin.Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u0016À\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$Metadata;", "", "lastWatchedAt", "", "getLastWatchedAt", "()Ljava/lang/String;", NotificationCompat.CATEGORY_STATUS, "getStatus", "userRating", "", "getUserRating", "()Ljava/lang/Integer;", "lastWatched", "getLastWatched", "watchedEpisodesCount", "getWatchedEpisodesCount", "totalEpisodesCount", "getTotalEpisodesCount", "getIds", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public interface Metadata {
                ShowMetadata.Show.Ids getIds();

                String getLastWatched();

                String getLastWatchedAt();

                String getStatus();

                Integer getTotalEpisodesCount();

                Integer getUserRating();

                Integer getWatchedEpisodesCount();

                SyncAPI.LibraryItem toLibraryItem();
            }

            public AllItemsResponse() {
                this(null, null, null, 7, null);
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ AllItemsResponse copy$default(AllItemsResponse allItemsResponse, List list, List list2, List list3, int i, Object obj) {
                if ((i & 1) != 0) {
                    list = allItemsResponse.shows;
                }
                if ((i & 2) != 0) {
                    list2 = allItemsResponse.anime;
                }
                if ((i & 4) != 0) {
                    list3 = allItemsResponse.movies;
                }
                return allItemsResponse.copy(list, list2, list3);
            }

            public final List<ShowMetadata> component1() {
                return this.shows;
            }

            public final List<ShowMetadata> component2() {
                return this.anime;
            }

            public final List<MovieMetadata> component3() {
                return this.movies;
            }

            public final AllItemsResponse copy(@JsonProperty("shows") List<ShowMetadata> shows, @JsonProperty("anime") List<ShowMetadata> anime, @JsonProperty("movies") List<MovieMetadata> movies) {
                Intrinsics.checkNotNullParameter(shows, "shows");
                Intrinsics.checkNotNullParameter(anime, "anime");
                Intrinsics.checkNotNullParameter(movies, "movies");
                return new AllItemsResponse(shows, anime, movies);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof AllItemsResponse)) {
                    return false;
                }
                AllItemsResponse allItemsResponse = (AllItemsResponse) other;
                return Intrinsics.areEqual(this.shows, allItemsResponse.shows) && Intrinsics.areEqual(this.anime, allItemsResponse.anime) && Intrinsics.areEqual(this.movies, allItemsResponse.movies);
            }

            public int hashCode() {
                return (((this.shows.hashCode() * 31) + this.anime.hashCode()) * 31) + this.movies.hashCode();
            }

            public String toString() {
                return "AllItemsResponse(shows=" + this.shows + ", anime=" + this.anime + ", movies=" + this.movies + ')';
            }

            public AllItemsResponse(@JsonProperty("shows") List<ShowMetadata> shows, @JsonProperty("anime") List<ShowMetadata> anime, @JsonProperty("movies") List<MovieMetadata> movies) {
                Intrinsics.checkNotNullParameter(shows, "shows");
                Intrinsics.checkNotNullParameter(anime, "anime");
                Intrinsics.checkNotNullParameter(movies, "movies");
                this.shows = shows;
                this.anime = anime;
                this.movies = movies;
            }

            public /* synthetic */ AllItemsResponse(List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
                this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3);
            }

            public final List<ShowMetadata> getShows() {
                return this.shows;
            }

            public final List<ShowMetadata> getAnime() {
                return this.anime;
            }

            public final List<MovieMetadata> getMovies() {
                return this.movies;
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¨\u0006\b"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$Companion;", "", "<init>", "()V", "merge", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse;", "first", "second", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                private static final <T> void merge$replaceOrAddItem(List<T> list, T t, Function1<? super T, Boolean> function1) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        if (function1.invoke(list.get(i)).booleanValue()) {
                            list.set(i, t);
                            return;
                        }
                    }
                    list.add(t);
                }

                private static final <T extends Metadata> List<T> merge$merge(List<? extends T> list, List<? extends T> list2) {
                    ArrayList arrayList;
                    if (list == null || (arrayList = CollectionsKt.toMutableList((Collection) list)) == null) {
                        arrayList = new ArrayList();
                    }
                    if (list2 != null) {
                        Iterator<T> it = list2.iterator();
                        while (it.hasNext()) {
                            final Metadata metadata = (Metadata) it.next();
                            merge$replaceOrAddItem(arrayList, metadata, new Function1() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$Companion$AllItemsResponse$Companion$$ExternalSyntheticLambda0
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Boolean.valueOf(SimklApi.Companion.AllItemsResponse.Companion.merge$merge$lambda$0$0$0(metadata, (SimklApi.Companion.AllItemsResponse.Metadata) obj));
                                }
                            });
                        }
                    }
                    return arrayList;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final boolean merge$merge$lambda$0$0$0(Metadata metadata, Metadata it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return it.getIds().getSimkl() == metadata.getIds().getSimkl();
                }

                public final AllItemsResponse merge(AllItemsResponse first, AllItemsResponse second) {
                    return new AllItemsResponse(merge$merge(first != null ? first.getShows() : null, second != null ? second.getShows() : null), merge$merge(first != null ? first.getAnime() : null, second != null ? second.getAnime() : null), merge$merge(first != null ? first.getMovies() : null, second != null ? second.getMovies() : null));
                }
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @kotlin.Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u000bHÆ\u0003J^\u0010$\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010%J\u0014\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00068\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00068\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$MovieMetadata;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$Metadata;", "lastWatchedAt", "", NotificationCompat.CATEGORY_STATUS, "userRating", "", "lastWatched", "watchedEpisodesCount", "totalEpisodesCount", "movie", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;)V", "getLastWatchedAt", "()Ljava/lang/String;", "getStatus", "getUserRating", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLastWatched", "getWatchedEpisodesCount", "getTotalEpisodesCount", "getMovie", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;", "getIds", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$MovieMetadata;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class MovieMetadata implements Metadata {

                @JsonProperty("last_watched")
                private final String lastWatched;

                @JsonProperty("last_watched_at")
                private final String lastWatchedAt;
                private final ShowMetadata.Show movie;

                @JsonProperty(NotificationCompat.CATEGORY_STATUS)
                private final String status;

                @JsonProperty("total_episodes_count")
                private final Integer totalEpisodesCount;

                @JsonProperty("user_rating")
                private final Integer userRating;

                @JsonProperty("watched_episodes_count")
                private final Integer watchedEpisodesCount;

                public static /* synthetic */ MovieMetadata copy$default(MovieMetadata movieMetadata, String str, String str2, Integer num, String str3, Integer num2, Integer num3, ShowMetadata.Show show, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = movieMetadata.lastWatchedAt;
                    }
                    if ((i & 2) != 0) {
                        str2 = movieMetadata.status;
                    }
                    if ((i & 4) != 0) {
                        num = movieMetadata.userRating;
                    }
                    if ((i & 8) != 0) {
                        str3 = movieMetadata.lastWatched;
                    }
                    if ((i & 16) != 0) {
                        num2 = movieMetadata.watchedEpisodesCount;
                    }
                    if ((i & 32) != 0) {
                        num3 = movieMetadata.totalEpisodesCount;
                    }
                    if ((i & 64) != 0) {
                        show = movieMetadata.movie;
                    }
                    Integer num4 = num3;
                    ShowMetadata.Show show2 = show;
                    Integer num5 = num2;
                    Integer num6 = num;
                    return movieMetadata.copy(str, str2, num6, str3, num5, num4, show2);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getLastWatchedAt() {
                    return this.lastWatchedAt;
                }

                /* JADX INFO: renamed from: component2, reason: from getter */
                public final String getStatus() {
                    return this.status;
                }

                /* JADX INFO: renamed from: component3, reason: from getter */
                public final Integer getUserRating() {
                    return this.userRating;
                }

                /* JADX INFO: renamed from: component4, reason: from getter */
                public final String getLastWatched() {
                    return this.lastWatched;
                }

                /* JADX INFO: renamed from: component5, reason: from getter */
                public final Integer getWatchedEpisodesCount() {
                    return this.watchedEpisodesCount;
                }

                /* JADX INFO: renamed from: component6, reason: from getter */
                public final Integer getTotalEpisodesCount() {
                    return this.totalEpisodesCount;
                }

                /* JADX INFO: renamed from: component7, reason: from getter */
                public final ShowMetadata.Show getMovie() {
                    return this.movie;
                }

                public final MovieMetadata copy(@JsonProperty("last_watched_at") String lastWatchedAt, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("user_rating") Integer userRating, @JsonProperty("last_watched") String lastWatched, @JsonProperty("watched_episodes_count") Integer watchedEpisodesCount, @JsonProperty("total_episodes_count") Integer totalEpisodesCount, ShowMetadata.Show movie) {
                    Intrinsics.checkNotNullParameter(status, "status");
                    Intrinsics.checkNotNullParameter(movie, "movie");
                    return new MovieMetadata(lastWatchedAt, status, userRating, lastWatched, watchedEpisodesCount, totalEpisodesCount, movie);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof MovieMetadata)) {
                        return false;
                    }
                    MovieMetadata movieMetadata = (MovieMetadata) other;
                    return Intrinsics.areEqual(this.lastWatchedAt, movieMetadata.lastWatchedAt) && Intrinsics.areEqual(this.status, movieMetadata.status) && Intrinsics.areEqual(this.userRating, movieMetadata.userRating) && Intrinsics.areEqual(this.lastWatched, movieMetadata.lastWatched) && Intrinsics.areEqual(this.watchedEpisodesCount, movieMetadata.watchedEpisodesCount) && Intrinsics.areEqual(this.totalEpisodesCount, movieMetadata.totalEpisodesCount) && Intrinsics.areEqual(this.movie, movieMetadata.movie);
                }

                public int hashCode() {
                    String str = this.lastWatchedAt;
                    int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.status.hashCode()) * 31;
                    Integer num = this.userRating;
                    int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
                    String str2 = this.lastWatched;
                    int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
                    Integer num2 = this.watchedEpisodesCount;
                    int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
                    Integer num3 = this.totalEpisodesCount;
                    return ((iHashCode4 + (num3 != null ? num3.hashCode() : 0)) * 31) + this.movie.hashCode();
                }

                public String toString() {
                    return "MovieMetadata(lastWatchedAt=" + this.lastWatchedAt + ", status=" + this.status + ", userRating=" + this.userRating + ", lastWatched=" + this.lastWatched + ", watchedEpisodesCount=" + this.watchedEpisodesCount + ", totalEpisodesCount=" + this.totalEpisodesCount + ", movie=" + this.movie + ')';
                }

                public MovieMetadata(@JsonProperty("last_watched_at") String str, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("user_rating") Integer num, @JsonProperty("last_watched") String str2, @JsonProperty("watched_episodes_count") Integer num2, @JsonProperty("total_episodes_count") Integer num3, ShowMetadata.Show movie) {
                    Intrinsics.checkNotNullParameter(status, "status");
                    Intrinsics.checkNotNullParameter(movie, "movie");
                    this.lastWatchedAt = str;
                    this.status = status;
                    this.userRating = num;
                    this.lastWatched = str2;
                    this.watchedEpisodesCount = num2;
                    this.totalEpisodesCount = num3;
                    this.movie = movie;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public String getLastWatchedAt() {
                    return this.lastWatchedAt;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public String getStatus() {
                    return this.status;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Integer getUserRating() {
                    return this.userRating;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public String getLastWatched() {
                    return this.lastWatched;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Integer getWatchedEpisodesCount() {
                    return this.watchedEpisodesCount;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Integer getTotalEpisodesCount() {
                    return this.totalEpisodesCount;
                }

                public final ShowMetadata.Show getMovie() {
                    return this.movie;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public ShowMetadata.Show.Ids getIds() {
                    return this.movie.getIds();
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public SyncAPI.LibraryItem toLibraryItem() {
                    String title = this.movie.getTitle();
                    String str = "https://simkl.com/tv/" + this.movie.getIds().getSimkl();
                    String strValueOf = String.valueOf(this.movie.getIds().getSimkl());
                    Integer watchedEpisodesCount = getWatchedEpisodesCount();
                    Integer totalEpisodesCount = getTotalEpisodesCount();
                    Score scoreFrom10 = Score.INSTANCE.from10(getUserRating());
                    Long unixTime = SimklApi.INSTANCE.getUnixTime(getLastWatchedAt());
                    Long lValueOf = Long.valueOf(unixTime != null ? unixTime.longValue() : 0L);
                    TvType tvType = TvType.Movie;
                    String poster = this.movie.getPoster();
                    String posterUrl = poster != null ? SimklApi.INSTANCE.getPosterUrl(poster) : null;
                    Integer year = this.movie.getYear();
                    return new SyncAPI.LibraryItem(title, str, strValueOf, watchedEpisodesCount, totalEpisodesCount, scoreFrom10, lValueOf, "Simkl", tvType, posterUrl, null, null, year != null ? DataStoreHelper.INSTANCE.toYear(year.intValue()) : null, Integer.valueOf(this.movie.getIds().getSimkl()), null, null, null, 114688, null);
                }
            }

            /* JADX INFO: compiled from: SimklApi.kt */
            @kotlin.Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001,BW\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u000bHÆ\u0003J^\u0010$\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\n\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010%J\u0014\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010+\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00068\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00068\u0016X\u0097\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0016\u0010\u0012R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata;", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$Metadata;", "lastWatchedAt", "", NotificationCompat.CATEGORY_STATUS, "userRating", "", "lastWatched", "watchedEpisodesCount", "totalEpisodesCount", "show", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;)V", "getLastWatchedAt", "()Ljava/lang/String;", "getStatus", "getUserRating", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLastWatched", "getWatchedEpisodesCount", "getTotalEpisodesCount", "getShow", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;", "getIds", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata;", "equals", "", "other", "", "hashCode", "toString", "Show", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
            public static final /* data */ class ShowMetadata implements Metadata {

                @JsonProperty("last_watched")
                private final String lastWatched;

                @JsonProperty("last_watched_at")
                private final String lastWatchedAt;

                @JsonProperty("show")
                private final Show show;

                @JsonProperty(NotificationCompat.CATEGORY_STATUS)
                private final String status;

                @JsonProperty("total_episodes_count")
                private final Integer totalEpisodesCount;

                @JsonProperty("user_rating")
                private final Integer userRating;

                @JsonProperty("watched_episodes_count")
                private final Integer watchedEpisodesCount;

                public static /* synthetic */ ShowMetadata copy$default(ShowMetadata showMetadata, String str, String str2, Integer num, String str3, Integer num2, Integer num3, Show show, int i, Object obj) {
                    if ((i & 1) != 0) {
                        str = showMetadata.lastWatchedAt;
                    }
                    if ((i & 2) != 0) {
                        str2 = showMetadata.status;
                    }
                    if ((i & 4) != 0) {
                        num = showMetadata.userRating;
                    }
                    if ((i & 8) != 0) {
                        str3 = showMetadata.lastWatched;
                    }
                    if ((i & 16) != 0) {
                        num2 = showMetadata.watchedEpisodesCount;
                    }
                    if ((i & 32) != 0) {
                        num3 = showMetadata.totalEpisodesCount;
                    }
                    if ((i & 64) != 0) {
                        show = showMetadata.show;
                    }
                    Integer num4 = num3;
                    Show show2 = show;
                    Integer num5 = num2;
                    Integer num6 = num;
                    return showMetadata.copy(str, str2, num6, str3, num5, num4, show2);
                }

                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getLastWatchedAt() {
                    return this.lastWatchedAt;
                }

                /* JADX INFO: renamed from: component2, reason: from getter */
                public final String getStatus() {
                    return this.status;
                }

                /* JADX INFO: renamed from: component3, reason: from getter */
                public final Integer getUserRating() {
                    return this.userRating;
                }

                /* JADX INFO: renamed from: component4, reason: from getter */
                public final String getLastWatched() {
                    return this.lastWatched;
                }

                /* JADX INFO: renamed from: component5, reason: from getter */
                public final Integer getWatchedEpisodesCount() {
                    return this.watchedEpisodesCount;
                }

                /* JADX INFO: renamed from: component6, reason: from getter */
                public final Integer getTotalEpisodesCount() {
                    return this.totalEpisodesCount;
                }

                /* JADX INFO: renamed from: component7, reason: from getter */
                public final Show getShow() {
                    return this.show;
                }

                public final ShowMetadata copy(@JsonProperty("last_watched_at") String lastWatchedAt, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("user_rating") Integer userRating, @JsonProperty("last_watched") String lastWatched, @JsonProperty("watched_episodes_count") Integer watchedEpisodesCount, @JsonProperty("total_episodes_count") Integer totalEpisodesCount, @JsonProperty("show") Show show) {
                    Intrinsics.checkNotNullParameter(status, "status");
                    Intrinsics.checkNotNullParameter(show, "show");
                    return new ShowMetadata(lastWatchedAt, status, userRating, lastWatched, watchedEpisodesCount, totalEpisodesCount, show);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof ShowMetadata)) {
                        return false;
                    }
                    ShowMetadata showMetadata = (ShowMetadata) other;
                    return Intrinsics.areEqual(this.lastWatchedAt, showMetadata.lastWatchedAt) && Intrinsics.areEqual(this.status, showMetadata.status) && Intrinsics.areEqual(this.userRating, showMetadata.userRating) && Intrinsics.areEqual(this.lastWatched, showMetadata.lastWatched) && Intrinsics.areEqual(this.watchedEpisodesCount, showMetadata.watchedEpisodesCount) && Intrinsics.areEqual(this.totalEpisodesCount, showMetadata.totalEpisodesCount) && Intrinsics.areEqual(this.show, showMetadata.show);
                }

                public int hashCode() {
                    String str = this.lastWatchedAt;
                    int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.status.hashCode()) * 31;
                    Integer num = this.userRating;
                    int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
                    String str2 = this.lastWatched;
                    int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
                    Integer num2 = this.watchedEpisodesCount;
                    int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
                    Integer num3 = this.totalEpisodesCount;
                    return ((iHashCode4 + (num3 != null ? num3.hashCode() : 0)) * 31) + this.show.hashCode();
                }

                public String toString() {
                    return "ShowMetadata(lastWatchedAt=" + this.lastWatchedAt + ", status=" + this.status + ", userRating=" + this.userRating + ", lastWatched=" + this.lastWatched + ", watchedEpisodesCount=" + this.watchedEpisodesCount + ", totalEpisodesCount=" + this.totalEpisodesCount + ", show=" + this.show + ')';
                }

                public ShowMetadata(@JsonProperty("last_watched_at") String str, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("user_rating") Integer num, @JsonProperty("last_watched") String str2, @JsonProperty("watched_episodes_count") Integer num2, @JsonProperty("total_episodes_count") Integer num3, @JsonProperty("show") Show show) {
                    Intrinsics.checkNotNullParameter(status, "status");
                    Intrinsics.checkNotNullParameter(show, "show");
                    this.lastWatchedAt = str;
                    this.status = status;
                    this.userRating = num;
                    this.lastWatched = str2;
                    this.watchedEpisodesCount = num2;
                    this.totalEpisodesCount = num3;
                    this.show = show;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public String getLastWatchedAt() {
                    return this.lastWatchedAt;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public String getStatus() {
                    return this.status;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Integer getUserRating() {
                    return this.userRating;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public String getLastWatched() {
                    return this.lastWatched;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Integer getWatchedEpisodesCount() {
                    return this.watchedEpisodesCount;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Integer getTotalEpisodesCount() {
                    return this.totalEpisodesCount;
                }

                public final Show getShow() {
                    return this.show;
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public Show.Ids getIds() {
                    return this.show.getIds();
                }

                @Override // com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse.Metadata
                public SyncAPI.LibraryItem toLibraryItem() {
                    String title = this.show.getTitle();
                    String str = "https://simkl.com/tv/" + this.show.getIds().getSimkl();
                    String strValueOf = String.valueOf(this.show.getIds().getSimkl());
                    Integer watchedEpisodesCount = getWatchedEpisodesCount();
                    Integer totalEpisodesCount = getTotalEpisodesCount();
                    Score scoreFrom10 = Score.INSTANCE.from10(getUserRating());
                    Long unixTime = SimklApi.INSTANCE.getUnixTime(getLastWatchedAt());
                    Long lValueOf = Long.valueOf(unixTime != null ? unixTime.longValue() : 0L);
                    TvType tvType = TvType.Anime;
                    String poster = this.show.getPoster();
                    String posterUrl = poster != null ? SimklApi.INSTANCE.getPosterUrl(poster) : null;
                    Integer year = this.show.getYear();
                    return new SyncAPI.LibraryItem(title, str, strValueOf, watchedEpisodesCount, totalEpisodesCount, scoreFrom10, lValueOf, "Simkl", tvType, posterUrl, null, null, year != null ? DataStoreHelper.INSTANCE.toYear(year.intValue()) : null, Integer.valueOf(this.show.getIds().getSimkl()), null, null, null, 114688, null);
                }

                /* JADX INFO: compiled from: SimklApi.kt */
                @kotlin.Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001:\u0001\u001eB3\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0016\u001a\u00020\bHÆ\u0003J:\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;", "", "title", "", "poster", "year", "", "ids", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;)V", "getTitle", "()Ljava/lang/String;", "getPoster", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIds", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;)Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show;", "equals", "", "other", "hashCode", "toString", "Ids", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
                public static final /* data */ class Show {

                    @JsonProperty("ids")
                    private final Ids ids;

                    @JsonProperty("poster")
                    private final String poster;

                    @JsonProperty("title")
                    private final String title;

                    @JsonProperty("year")
                    private final Integer year;

                    public static /* synthetic */ Show copy$default(Show show, String str, String str2, Integer num, Ids ids, int i, Object obj) {
                        if ((i & 1) != 0) {
                            str = show.title;
                        }
                        if ((i & 2) != 0) {
                            str2 = show.poster;
                        }
                        if ((i & 4) != 0) {
                            num = show.year;
                        }
                        if ((i & 8) != 0) {
                            ids = show.ids;
                        }
                        return show.copy(str, str2, num, ids);
                    }

                    /* JADX INFO: renamed from: component1, reason: from getter */
                    public final String getTitle() {
                        return this.title;
                    }

                    /* JADX INFO: renamed from: component2, reason: from getter */
                    public final String getPoster() {
                        return this.poster;
                    }

                    /* JADX INFO: renamed from: component3, reason: from getter */
                    public final Integer getYear() {
                        return this.year;
                    }

                    /* JADX INFO: renamed from: component4, reason: from getter */
                    public final Ids getIds() {
                        return this.ids;
                    }

                    public final Show copy(@JsonProperty("title") String title, @JsonProperty("poster") String poster, @JsonProperty("year") Integer year, @JsonProperty("ids") Ids ids) {
                        Intrinsics.checkNotNullParameter(title, "title");
                        Intrinsics.checkNotNullParameter(ids, "ids");
                        return new Show(title, poster, year, ids);
                    }

                    public boolean equals(Object other) {
                        if (this == other) {
                            return true;
                        }
                        if (!(other instanceof Show)) {
                            return false;
                        }
                        Show show = (Show) other;
                        return Intrinsics.areEqual(this.title, show.title) && Intrinsics.areEqual(this.poster, show.poster) && Intrinsics.areEqual(this.year, show.year) && Intrinsics.areEqual(this.ids, show.ids);
                    }

                    public int hashCode() {
                        int iHashCode = this.title.hashCode() * 31;
                        String str = this.poster;
                        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
                        Integer num = this.year;
                        return ((iHashCode2 + (num != null ? num.hashCode() : 0)) * 31) + this.ids.hashCode();
                    }

                    public String toString() {
                        return "Show(title=" + this.title + ", poster=" + this.poster + ", year=" + this.year + ", ids=" + this.ids + ')';
                    }

                    public Show(@JsonProperty("title") String title, @JsonProperty("poster") String str, @JsonProperty("year") Integer num, @JsonProperty("ids") Ids ids) {
                        Intrinsics.checkNotNullParameter(title, "title");
                        Intrinsics.checkNotNullParameter(ids, "ids");
                        this.title = title;
                        this.poster = str;
                        this.year = num;
                        this.ids = ids;
                    }

                    public final String getTitle() {
                        return this.title;
                    }

                    public final String getPoster() {
                        return this.poster;
                    }

                    public final Integer getYear() {
                        return this.year;
                    }

                    public final Ids getIds() {
                        return this.ids;
                    }

                    /* JADX INFO: compiled from: SimklApi.kt */
                    @kotlin.Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0005J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u008b\u0001\u0010.\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010/\u001a\u00020\u001f2\b\u00100\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00101\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00102\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014¨\u00063"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$AllItemsResponse$ShowMetadata$Show$Ids;", "", "simkl", "", "slug", "", "imdb", "zap2it", "tmdb", "offen", "tvdb", "mal", "anidb", "anilist", "traktslug", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSimkl", "()I", "getSlug", "()Ljava/lang/String;", "getImdb", "getZap2it", "getTmdb", "getOffen", "getTvdb", "getMal", "getAnidb", "getAnilist", "getTraktslug", "matchesId", "", "database", "Lcom/lagradost/cloudstream3/SimklSyncServices;", "id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
                    public static final /* data */ class Ids {

                        @JsonProperty("anidb")
                        private final String anidb;

                        @JsonProperty("anilist")
                        private final String anilist;

                        @JsonProperty("imdb")
                        private final String imdb;

                        @JsonProperty("mal")
                        private final String mal;

                        @JsonProperty("offen")
                        private final String offen;

                        @JsonProperty("simkl")
                        private final int simkl;

                        @JsonProperty("slug")
                        private final String slug;

                        @JsonProperty("tmdb")
                        private final String tmdb;

                        @JsonProperty("traktslug")
                        private final String traktslug;

                        @JsonProperty("tvdb")
                        private final String tvdb;

                        @JsonProperty("zap2it")
                        private final String zap2it;

                        /* JADX INFO: compiled from: SimklApi.kt */
                        @kotlin.Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                        public static final /* synthetic */ class WhenMappings {
                            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                            static {
                                int[] iArr = new int[SimklSyncServices.values().length];
                                try {
                                    iArr[SimklSyncServices.Simkl.ordinal()] = 1;
                                } catch (NoSuchFieldError unused) {
                                }
                                try {
                                    iArr[SimklSyncServices.AniList.ordinal()] = 2;
                                } catch (NoSuchFieldError unused2) {
                                }
                                try {
                                    iArr[SimklSyncServices.Mal.ordinal()] = 3;
                                } catch (NoSuchFieldError unused3) {
                                }
                                try {
                                    iArr[SimklSyncServices.Tmdb.ordinal()] = 4;
                                } catch (NoSuchFieldError unused4) {
                                }
                                try {
                                    iArr[SimklSyncServices.Imdb.ordinal()] = 5;
                                } catch (NoSuchFieldError unused5) {
                                }
                                $EnumSwitchMapping$0 = iArr;
                            }
                        }

                        public static /* synthetic */ Ids copy$default(Ids ids, int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i2, Object obj) {
                            if ((i2 & 1) != 0) {
                                i = ids.simkl;
                            }
                            if ((i2 & 2) != 0) {
                                str = ids.slug;
                            }
                            if ((i2 & 4) != 0) {
                                str2 = ids.imdb;
                            }
                            if ((i2 & 8) != 0) {
                                str3 = ids.zap2it;
                            }
                            if ((i2 & 16) != 0) {
                                str4 = ids.tmdb;
                            }
                            if ((i2 & 32) != 0) {
                                str5 = ids.offen;
                            }
                            if ((i2 & 64) != 0) {
                                str6 = ids.tvdb;
                            }
                            if ((i2 & 128) != 0) {
                                str7 = ids.mal;
                            }
                            if ((i2 & 256) != 0) {
                                str8 = ids.anidb;
                            }
                            if ((i2 & 512) != 0) {
                                str9 = ids.anilist;
                            }
                            if ((i2 & 1024) != 0) {
                                str10 = ids.traktslug;
                            }
                            String str11 = str9;
                            String str12 = str10;
                            String str13 = str7;
                            String str14 = str8;
                            String str15 = str5;
                            String str16 = str6;
                            String str17 = str4;
                            String str18 = str2;
                            return ids.copy(i, str, str18, str3, str17, str15, str16, str13, str14, str11, str12);
                        }

                        /* JADX INFO: renamed from: component1, reason: from getter */
                        public final int getSimkl() {
                            return this.simkl;
                        }

                        /* JADX INFO: renamed from: component10, reason: from getter */
                        public final String getAnilist() {
                            return this.anilist;
                        }

                        /* JADX INFO: renamed from: component11, reason: from getter */
                        public final String getTraktslug() {
                            return this.traktslug;
                        }

                        /* JADX INFO: renamed from: component2, reason: from getter */
                        public final String getSlug() {
                            return this.slug;
                        }

                        /* JADX INFO: renamed from: component3, reason: from getter */
                        public final String getImdb() {
                            return this.imdb;
                        }

                        /* JADX INFO: renamed from: component4, reason: from getter */
                        public final String getZap2it() {
                            return this.zap2it;
                        }

                        /* JADX INFO: renamed from: component5, reason: from getter */
                        public final String getTmdb() {
                            return this.tmdb;
                        }

                        /* JADX INFO: renamed from: component6, reason: from getter */
                        public final String getOffen() {
                            return this.offen;
                        }

                        /* JADX INFO: renamed from: component7, reason: from getter */
                        public final String getTvdb() {
                            return this.tvdb;
                        }

                        /* JADX INFO: renamed from: component8, reason: from getter */
                        public final String getMal() {
                            return this.mal;
                        }

                        /* JADX INFO: renamed from: component9, reason: from getter */
                        public final String getAnidb() {
                            return this.anidb;
                        }

                        public final Ids copy(@JsonProperty("simkl") int simkl, @JsonProperty("slug") String slug, @JsonProperty("imdb") String imdb, @JsonProperty("zap2it") String zap2it, @JsonProperty("tmdb") String tmdb, @JsonProperty("offen") String offen, @JsonProperty("tvdb") String tvdb, @JsonProperty("mal") String mal, @JsonProperty("anidb") String anidb, @JsonProperty("anilist") String anilist, @JsonProperty("traktslug") String traktslug) {
                            return new Ids(simkl, slug, imdb, zap2it, tmdb, offen, tvdb, mal, anidb, anilist, traktslug);
                        }

                        public boolean equals(Object other) {
                            if (this == other) {
                                return true;
                            }
                            if (!(other instanceof Ids)) {
                                return false;
                            }
                            Ids ids = (Ids) other;
                            return this.simkl == ids.simkl && Intrinsics.areEqual(this.slug, ids.slug) && Intrinsics.areEqual(this.imdb, ids.imdb) && Intrinsics.areEqual(this.zap2it, ids.zap2it) && Intrinsics.areEqual(this.tmdb, ids.tmdb) && Intrinsics.areEqual(this.offen, ids.offen) && Intrinsics.areEqual(this.tvdb, ids.tvdb) && Intrinsics.areEqual(this.mal, ids.mal) && Intrinsics.areEqual(this.anidb, ids.anidb) && Intrinsics.areEqual(this.anilist, ids.anilist) && Intrinsics.areEqual(this.traktslug, ids.traktslug);
                        }

                        public int hashCode() {
                            int i = this.simkl * 31;
                            String str = this.slug;
                            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
                            String str2 = this.imdb;
                            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
                            String str3 = this.zap2it;
                            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
                            String str4 = this.tmdb;
                            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
                            String str5 = this.offen;
                            int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
                            String str6 = this.tvdb;
                            int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
                            String str7 = this.mal;
                            int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
                            String str8 = this.anidb;
                            int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
                            String str9 = this.anilist;
                            int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
                            String str10 = this.traktslug;
                            return iHashCode9 + (str10 != null ? str10.hashCode() : 0);
                        }

                        public String toString() {
                            return "Ids(simkl=" + this.simkl + ", slug=" + this.slug + ", imdb=" + this.imdb + ", zap2it=" + this.zap2it + ", tmdb=" + this.tmdb + ", offen=" + this.offen + ", tvdb=" + this.tvdb + ", mal=" + this.mal + ", anidb=" + this.anidb + ", anilist=" + this.anilist + ", traktslug=" + this.traktslug + ')';
                        }

                        public Ids(@JsonProperty("simkl") int i, @JsonProperty("slug") String str, @JsonProperty("imdb") String str2, @JsonProperty("zap2it") String str3, @JsonProperty("tmdb") String str4, @JsonProperty("offen") String str5, @JsonProperty("tvdb") String str6, @JsonProperty("mal") String str7, @JsonProperty("anidb") String str8, @JsonProperty("anilist") String str9, @JsonProperty("traktslug") String str10) {
                            this.simkl = i;
                            this.slug = str;
                            this.imdb = str2;
                            this.zap2it = str3;
                            this.tmdb = str4;
                            this.offen = str5;
                            this.tvdb = str6;
                            this.mal = str7;
                            this.anidb = str8;
                            this.anilist = str9;
                            this.traktslug = str10;
                        }

                        public final int getSimkl() {
                            return this.simkl;
                        }

                        public final String getSlug() {
                            return this.slug;
                        }

                        public final String getImdb() {
                            return this.imdb;
                        }

                        public final String getZap2it() {
                            return this.zap2it;
                        }

                        public final String getTmdb() {
                            return this.tmdb;
                        }

                        public final String getOffen() {
                            return this.offen;
                        }

                        public final String getTvdb() {
                            return this.tvdb;
                        }

                        public final String getMal() {
                            return this.mal;
                        }

                        public final String getAnidb() {
                            return this.anidb;
                        }

                        public final String getAnilist() {
                            return this.anilist;
                        }

                        public final String getTraktslug() {
                            return this.traktslug;
                        }

                        public final boolean matchesId(SimklSyncServices database, String id) {
                            Intrinsics.checkNotNullParameter(database, "database");
                            Intrinsics.checkNotNullParameter(id, "id");
                            int i = WhenMappings.$EnumSwitchMapping$0[database.ordinal()];
                            if (i == 1) {
                                int i2 = this.simkl;
                                Integer intOrNull = StringsKt.toIntOrNull(id);
                                return intOrNull != null && i2 == intOrNull.intValue();
                            }
                            if (i == 2) {
                                return Intrinsics.areEqual(this.anilist, id);
                            }
                            if (i == 3) {
                                return Intrinsics.areEqual(this.mal, id);
                            }
                            if (i == 4) {
                                return Intrinsics.areEqual(this.tmdb, id);
                            }
                            if (i != 5) {
                                throw new NoWhenBranchMatchedException();
                            }
                            return Intrinsics.areEqual(this.imdb, id);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getUser(AuthToken authToken, Continuation<? super Companion.SettingsResponse> continuation) {
        C08101 c08101;
        if (continuation instanceof C08101) {
            c08101 = (C08101) continuation;
            if ((c08101.label & Integer.MIN_VALUE) != 0) {
                c08101.label -= Integer.MIN_VALUE;
            } else {
                c08101 = new C08101(continuation);
            }
        } else {
            c08101 = new C08101(continuation);
        }
        Object obj = c08101.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08101.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = getMainUrl() + "/users/settings";
            Map<String, String> headers = INSTANCE.getHeaders(authToken);
            c08101.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c08101.label = 1;
            Object objPost$default = Requests.post$default(app2, str, headers, null, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, c08101, 65532, null);
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
        return parser.parse(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.SettingsResponse.class));
    }

    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0086@¢\u0006\u0002\u0010\u0010R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklEpisodeConstructor;", "", "simklId", "", "type", "", "totalEpisodeCount", "hasEnded", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", "getEpisodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$Companion$EpisodeMetadata;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class SimklEpisodeConstructor {
        private final Boolean hasEnded;
        private final Integer simklId;
        private final Integer totalEpisodeCount;
        private final String type;

        public SimklEpisodeConstructor(Integer num, String str, Integer num2, Boolean bool) {
            this.simklId = num;
            this.type = str;
            this.totalEpisodeCount = num2;
            this.hasEnded = bool;
        }

        public final Object getEpisodes(Continuation<? super Companion.EpisodeMetadata[]> continuation) {
            return SimklApi.INSTANCE.getEpisodes(this.simklId, this.type, this.totalEpisodeCount, this.hasEnded, continuation);
        }
    }

    /* JADX INFO: compiled from: SimklApi.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001d\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001c\"\u0004\b\u001f\u0010 R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\u000b\u0010#\"\u0004\b$\u0010%R\u001e\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b'\u0010\u001c\"\u0004\b(\u0010 R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklSyncStatus;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "score", "Lcom/lagradost/cloudstream3/Score;", "oldScore", "", "watchedEpisodes", "episodeConstructor", "Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklEpisodeConstructor;", "isFavorite", "", "maxEpisodes", "oldEpisodes", "oldStatus", "", "<init>", "(Lcom/lagradost/cloudstream3/ui/SyncWatchType;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklEpisodeConstructor;Ljava/lang/Boolean;Ljava/lang/Integer;ILjava/lang/String;)V", "getStatus", "()Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "setStatus", "(Lcom/lagradost/cloudstream3/ui/SyncWatchType;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getOldScore", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWatchedEpisodes", "setWatchedEpisodes", "(Ljava/lang/Integer;)V", "getEpisodeConstructor", "()Lcom/lagradost/cloudstream3/syncproviders/providers/SimklApi$SimklEpisodeConstructor;", "()Ljava/lang/Boolean;", "setFavorite", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getMaxEpisodes", "setMaxEpisodes", "getOldEpisodes", "()I", "getOldStatus", "()Ljava/lang/String;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class SimklSyncStatus extends SyncAPI.AbstractSyncStatus {
        private final SimklEpisodeConstructor episodeConstructor;
        private Boolean isFavorite;
        private Integer maxEpisodes;
        private final int oldEpisodes;
        private final Integer oldScore;
        private final String oldStatus;
        private Score score;
        private SyncWatchType status;
        private Integer watchedEpisodes;

        public SimklSyncStatus(SyncWatchType status, Score score, Integer num, Integer num2, SimklEpisodeConstructor episodeConstructor, Boolean bool, Integer num3, int i, String str) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(episodeConstructor, "episodeConstructor");
            this.status = status;
            this.score = score;
            this.oldScore = num;
            this.watchedEpisodes = num2;
            this.episodeConstructor = episodeConstructor;
            this.isFavorite = bool;
            this.maxEpisodes = num3;
            this.oldEpisodes = i;
            this.oldStatus = str;
        }

        public /* synthetic */ SimklSyncStatus(SyncWatchType syncWatchType, Score score, Integer num, Integer num2, SimklEpisodeConstructor simklEpisodeConstructor, Boolean bool, Integer num3, int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(syncWatchType, score, num, num2, simklEpisodeConstructor, (i2 & 32) != 0 ? null : bool, (i2 & 64) != 0 ? null : num3, i, str);
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public SyncWatchType getStatus() {
            return this.status;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public void setStatus(SyncWatchType syncWatchType) {
            Intrinsics.checkNotNullParameter(syncWatchType, "<set-?>");
            this.status = syncWatchType;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public void setScore(Score score) {
            this.score = score;
        }

        public final Integer getOldScore() {
            return this.oldScore;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public Integer getWatchedEpisodes() {
            return this.watchedEpisodes;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public void setWatchedEpisodes(Integer num) {
            this.watchedEpisodes = num;
        }

        public final SimklEpisodeConstructor getEpisodeConstructor() {
            return this.episodeConstructor;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        /* JADX INFO: renamed from: isFavorite, reason: from getter */
        public Boolean getIsFavorite() {
            return this.isFavorite;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public void setFavorite(Boolean bool) {
            this.isFavorite = bool;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public Integer getMaxEpisodes() {
            return this.maxEpisodes;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public void setMaxEpisodes(Integer num) {
            this.maxEpisodes = num;
        }

        public final int getOldEpisodes() {
            return this.oldEpisodes;
        }

        public final String getOldStatus() {
            return this.oldStatus;
        }
    }

    /* JADX WARN: Code duplicated, block: B:115:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:116:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:120:0x0314  */
    /* JADX WARN: Code duplicated, block: B:123:0x031c  */
    /* JADX WARN: Code duplicated, block: B:126:0x0347  */
    /* JADX WARN: Code duplicated, block: B:132:0x0363  */
    /* JADX WARN: Code duplicated, block: B:137:0x0384  */
    /* JADX WARN: Code duplicated, block: B:139:0x0387  */
    /* JADX WARN: Code duplicated, block: B:141:0x038d  */
    /* JADX WARN: Code duplicated, block: B:143:0x0397  */
    /* JADX WARN: Code duplicated, block: B:144:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:148:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:150:0x03c0  */
    /* JADX WARN: Code duplicated, block: B:154:0x03da  */
    /* JADX WARN: Code duplicated, block: B:156:0x03f6  */
    /* JADX WARN: Code duplicated, block: B:157:0x03fb  */
    /* JADX WARN: Code duplicated, block: B:174:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:176:0x01db A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:178:0x0380 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x0355 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x0381 A[EDGE_INSN: B:180:0x0381->B:136:0x0381 BREAK  A[LOOP:0: B:124:0x0341->B:181:0x0341, LOOP_LABEL: LOOP:0: B:124:0x0341->B:181:0x0341], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:182:0x0341 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:? A[LOOP:1: B:130:0x035d->B:184:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x0158  */
    /* JADX WARN: Code duplicated, block: B:50:0x016e A[Catch: Exception -> 0x0180, TRY_LEAVE, TryCatch #0 {Exception -> 0x0180, blocks: (B:26:0x0105, B:29:0x0111, B:34:0x0127, B:41:0x0152, B:44:0x0159, B:50:0x016e, B:49:0x0169, B:40:0x0148, B:33:0x011d, B:37:0x012e, B:46:0x015d, B:30:0x0113), top: B:164:0x0105, inners: #3, #4, #8, #10 }] */
    /* JADX WARN: Code duplicated, block: B:71:0x01d6  */
    /* JADX WARN: Code duplicated, block: B:78:0x01ec A[Catch: Exception -> 0x018a, TRY_LEAVE, TryCatch #2 {Exception -> 0x018a, blocks: (B:62:0x01a5, B:69:0x01d0, B:72:0x01d7, B:78:0x01ec, B:77:0x01e7, B:68:0x01c6, B:61:0x019b, B:65:0x01ac, B:74:0x01db, B:58:0x018d), top: B:167:0x0188, inners: #1, #5, #6, #9 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object status(AuthData authData, String str, Continuation<? super SyncAPI.AbstractSyncStatus> continuation) {
        C08161 c08161;
        String str2;
        String strJoinToString$default;
        Object objDecodeFromString;
        SimklCache.SimklCacheWrapper simklCacheWrapper;
        Object obj;
        Companion.MediaObject mediaObject;
        Map<SimklSyncServices, String> map;
        Companion.MediaObject mediaObject2;
        Companion.MediaObject mediaObject3;
        Object objDecodeFromString2;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Object objM8936constructorimpl2;
        KSerializer kSerializer2;
        SimklEpisodeConstructor simklEpisodeConstructor;
        Companion.MediaObject.Ids ids;
        Integer simkl;
        Companion.MediaObject mediaObject4;
        Map<SimklSyncServices, String> map2;
        SimklEpisodeConstructor simklEpisodeConstructor2;
        Companion.MediaObject mediaObject5;
        Companion.AllItemsResponse allItemsResponse;
        Companion.AllItemsResponse.Metadata metadata;
        Integer totalEpisodes;
        String status;
        Companion.SimklListStatusType simklListStatusTypeFromString;
        Integer numBoxInt;
        SyncWatchType syncWatchTypeFromInternalId;
        Iterator it;
        Object next;
        Companion.AllItemsResponse.Metadata metadata2;
        AuthData authData2 = authData;
        if (continuation instanceof C08161) {
            c08161 = (C08161) continuation;
            if ((c08161.label & Integer.MIN_VALUE) != 0) {
                c08161.label -= Integer.MIN_VALUE;
            } else {
                c08161 = new C08161(continuation);
            }
        } else {
            c08161 = new C08161(continuation);
        }
        Object syncListSmart = c08161.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08161.label;
        if (i != 0) {
            if (i == 1) {
                Companion.MediaObject mediaObject6 = (Companion.MediaObject) c08161.L$4;
                String str3 = (String) c08161.L$3;
                Map<SimklSyncServices, String> map3 = (Map) c08161.L$2;
                String str4 = (String) c08161.L$1;
                AuthData authData3 = (AuthData) c08161.L$0;
                ResultKt.throwOnFailure(syncListSmart);
                mediaObject3 = mediaObject6;
                authData2 = authData3;
                map = map3;
                strJoinToString$default = str3;
                str2 = str4;
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                SimklEpisodeConstructor simklEpisodeConstructor3 = (SimklEpisodeConstructor) c08161.L$6;
                mediaObject4 = (Companion.MediaObject) c08161.L$5;
                map2 = (Map) c08161.L$2;
                ResultKt.throwOnFailure(syncListSmart);
                simklEpisodeConstructor2 = simklEpisodeConstructor3;
            }
            allItemsResponse = (Companion.AllItemsResponse) syncListSmart;
            if (allItemsResponse != null) {
                it = CollectionsKt.flatten(CollectionsKt.listOf((Object[]) new List[]{allItemsResponse.getShows(), allItemsResponse.getAnime(), allItemsResponse.getMovies()})).iterator();
                loop0: while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    metadata2 = (Companion.AllItemsResponse.Metadata) next;
                    if (!map2.isEmpty()) {
                        for (Map.Entry<SimklSyncServices, String> entry : map2.entrySet()) {
                            if (metadata2.getIds().matchesId(entry.getKey(), entry.getValue())) {
                                break loop0;
                            }
                        }
                    }
                }
                metadata = (Companion.AllItemsResponse.Metadata) next;
            } else {
                metadata = null;
            }
            if (metadata == null) {
                SyncWatchType syncWatchTypeFromInternalId2 = SyncWatchType.INSTANCE.fromInternalId(Boxing.boxInt(Companion.SimklListStatusType.None.getValue()));
                if (Intrinsics.areEqual(mediaObject4.getType(), "movie")) {
                    totalEpisodes = Boxing.boxInt(0);
                } else {
                    totalEpisodes = mediaObject4.getTotalEpisodes();
                }
                return new SimklSyncStatus(syncWatchTypeFromInternalId2, null, null, Boxing.boxInt(0), simklEpisodeConstructor2, null, totalEpisodes, 0, null, 32, null);
            }
            status = metadata.getStatus();
            if (status != null) {
                SyncWatchType.Companion companion = SyncWatchType.INSTANCE;
                simklListStatusTypeFromString = Companion.SimklListStatusType.INSTANCE.fromString(status);
                if (simklListStatusTypeFromString != null) {
                    numBoxInt = Boxing.boxInt(simklListStatusTypeFromString.getValue());
                } else {
                    numBoxInt = null;
                }
                syncWatchTypeFromInternalId = companion.fromInternalId(numBoxInt);
                if (syncWatchTypeFromInternalId != null) {
                    Score scoreFrom10 = Score.INSTANCE.from10(metadata.getUserRating());
                    Integer watchedEpisodesCount = metadata.getWatchedEpisodesCount();
                    Integer totalEpisodes2 = mediaObject4.getTotalEpisodes();
                    Integer watchedEpisodesCount2 = metadata.getWatchedEpisodesCount();
                    return new SimklSyncStatus(syncWatchTypeFromInternalId, scoreFrom10, metadata.getUserRating(), watchedEpisodesCount, simklEpisodeConstructor2, null, totalEpisodes2, watchedEpisodesCount2 != null ? watchedEpisodesCount2.intValue() : 0, metadata.getStatus(), 32, null);
                }
            }
            return null;
        }
        ResultKt.throwOnFailure(syncListSmart);
        if (authData2 == null) {
            return null;
        }
        str2 = str;
        Map<SimklSyncServices, String> idFromString = LoadResponse.INSTANCE.readIdFromString(str2);
        List<Pair> list = MapsKt.toList(idFromString);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (Pair pair : list) {
            arrayList.add(((SimklSyncServices) pair.getFirst()).getOriginalName() + '=' + ((String) pair.getSecond()));
        }
        strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.sorted(arrayList), null, null, null, 0, null, null, 63, null);
        SimklCache simklCache = SimklCache.INSTANCE;
        Context context = CloudStreamApp.INSTANCE.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName("SIMKL_API_CACHE", strJoinToString$default), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(StringSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl2) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer2 = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer2 != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                        }
                    } else {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(String.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl2 = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl2)) {
                            objM8936constructorimpl2 = null;
                        }
                        kSerializer2 = (KSerializer) objM8936constructorimpl2;
                        if (kSerializer2 != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer2, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new SimklApi$SimklCache$getKey$$inlined$getKey$1());
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        } else {
            objDecodeFromString = null;
        }
        String str5 = (String) objDecodeFromString;
        if (str5 != null) {
            AppUtils appUtils2 = AppUtils.INSTANCE;
            try {
                if (str5 == null) {
                    objDecodeFromString2 = null;
                } else {
                    try {
                        Result.Companion companion6 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.syncproviders.providers.SimklApi.SimklCache.SimklCacheWrapper"));
                    } catch (Throwable th3) {
                        Result.Companion companion7 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th3));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, str5);
                            } catch (SerializationException e2) {
                                ArchComponentExtKt.logError(e2);
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<Companion.MediaObject>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$status$$inlined$getKey$1
                                });
                            } catch (Throwable unused3) {
                                objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<Companion.MediaObject>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$status$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<Companion.MediaObject>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$status$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion8 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SimklCache.SimklCacheWrapper.class), null, 2, null));
                        } catch (Throwable th4) {
                            Result.Companion companion9 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th4));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString2 = MainAPIKt.getJson().decodeFromString(kSerializer, str5);
                        } else {
                            objDecodeFromString2 = MainAPIKt.getMapper().readValue(str5, new TypeReference<SimklCache.SimklCacheWrapper<Companion.MediaObject>>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$status$$inlined$getKey$1
                            });
                        }
                    }
                }
            } catch (Exception unused4) {
                objDecodeFromString2 = null;
            }
            simklCacheWrapper = (SimklCache.SimklCacheWrapper) objDecodeFromString2;
        } else {
            simklCacheWrapper = null;
        }
        if (simklCacheWrapper == null || !simklCacheWrapper.isFresh()) {
            if (AppDebug.INSTANCE.isDebug()) {
                Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache miss at: SIMKL_API_CACHE/" + strJoinToString$default);
            }
            CloudStreamApp.INSTANCE.removeKey("SIMKL_API_CACHE", strJoinToString$default);
            obj = null;
        } else {
            if (AppDebug.INSTANCE.isDebug()) {
                Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache hit at: SIMKL_API_CACHE/" + strJoinToString$default + ". Remains fresh for " + Duration.m10326getInWholeDaysimpl(simklCacheWrapper.m8448remainingTimeUwyO8pc()) + " days or " + Duration.m10332getInWholeSecondsimpl(simklCacheWrapper.m8448remainingTimeUwyO8pc()) + " seconds.");
            }
            obj = simklCacheWrapper.getObj();
        }
        mediaObject = (Companion.MediaObject) obj;
        if (mediaObject == null) {
            c08161.L$0 = authData2;
            c08161.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c08161.L$2 = idFromString;
            c08161.L$3 = strJoinToString$default;
            c08161.L$4 = SpillingKt.nullOutSpilledVariable(mediaObject);
            c08161.label = 1;
            Object objSearchByIds = searchByIds(idFromString, c08161);
            if (objSearchByIds != coroutine_suspended) {
                mediaObject3 = mediaObject;
                syncListSmart = objSearchByIds;
                map = idFromString;
            }
        } else {
            map = idFromString;
            mediaObject2 = mediaObject;
            ids = mediaObject2.getIds();
            if (ids != null) {
                simkl = ids.getSimkl();
            } else {
                simkl = null;
            }
            simklEpisodeConstructor = new SimklEpisodeConstructor(simkl, mediaObject2.getType(), mediaObject2.getTotalEpisodes(), Boxing.boxBoolean(mediaObject2.hasEnded()));
            c08161.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
            c08161.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c08161.L$2 = map;
            c08161.L$3 = SpillingKt.nullOutSpilledVariable(strJoinToString$default);
            c08161.L$4 = SpillingKt.nullOutSpilledVariable(mediaObject);
            c08161.L$5 = mediaObject2;
            c08161.L$6 = simklEpisodeConstructor;
            c08161.label = 2;
            syncListSmart = getSyncListSmart(authData2, c08161);
            if (syncListSmart != coroutine_suspended) {
                mediaObject4 = mediaObject2;
                map2 = map;
                simklEpisodeConstructor2 = simklEpisodeConstructor;
                allItemsResponse = (Companion.AllItemsResponse) syncListSmart;
                if (allItemsResponse != null) {
                    it = CollectionsKt.flatten(CollectionsKt.listOf((Object[]) new List[]{allItemsResponse.getShows(), allItemsResponse.getAnime(), allItemsResponse.getMovies()})).iterator();
                    loop0: while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        metadata2 = (Companion.AllItemsResponse.Metadata) next;
                        if (!map2.isEmpty()) {
                            while (r6.hasNext()) {
                                if (metadata2.getIds().matchesId(entry.getKey(), entry.getValue())) {
                                    break loop0;
                                    break loop0;
                                }
                            }
                        }
                    }
                    metadata = (Companion.AllItemsResponse.Metadata) next;
                } else {
                    metadata = null;
                }
                if (metadata == null) {
                    SyncWatchType syncWatchTypeFromInternalId3 = SyncWatchType.INSTANCE.fromInternalId(Boxing.boxInt(Companion.SimklListStatusType.None.getValue()));
                    if (Intrinsics.areEqual(mediaObject4.getType(), "movie")) {
                        totalEpisodes = Boxing.boxInt(0);
                    } else {
                        totalEpisodes = mediaObject4.getTotalEpisodes();
                    }
                    return new SimklSyncStatus(syncWatchTypeFromInternalId3, null, null, Boxing.boxInt(0), simklEpisodeConstructor2, null, totalEpisodes, 0, null, 32, null);
                }
                status = metadata.getStatus();
                if (status != null) {
                    SyncWatchType.Companion companion10 = SyncWatchType.INSTANCE;
                    simklListStatusTypeFromString = Companion.SimklListStatusType.INSTANCE.fromString(status);
                    if (simklListStatusTypeFromString != null) {
                        numBoxInt = Boxing.boxInt(simklListStatusTypeFromString.getValue());
                    } else {
                        numBoxInt = null;
                    }
                    syncWatchTypeFromInternalId = companion10.fromInternalId(numBoxInt);
                    if (syncWatchTypeFromInternalId != null) {
                        Score scoreFrom11 = Score.INSTANCE.from10(metadata.getUserRating());
                        Integer watchedEpisodesCount3 = metadata.getWatchedEpisodesCount();
                        Integer totalEpisodes3 = mediaObject4.getTotalEpisodes();
                        Integer watchedEpisodesCount4 = metadata.getWatchedEpisodesCount();
                        return new SimklSyncStatus(syncWatchTypeFromInternalId, scoreFrom11, metadata.getUserRating(), watchedEpisodesCount3, simklEpisodeConstructor2, null, totalEpisodes3, watchedEpisodesCount4 != null ? watchedEpisodesCount4.intValue() : 0, metadata.getStatus(), 32, null);
                    }
                }
                return null;
            }
        }
        return coroutine_suspended;
        Companion.MediaObject[] mediaObjectArr = (Companion.MediaObject[]) syncListSmart;
        if (mediaObjectArr == null || (mediaObject5 = (Companion.MediaObject) ArraysKt.firstOrNull(mediaObjectArr)) == null) {
            mediaObject5 = null;
        } else {
            SimklCache.INSTANCE.m8447setKeySxA4cEA(strJoinToString$default, mediaObject5, Duration.INSTANCE.m10414parseUwyO8pc((mediaObject5.hasEnded() ? SimklCache.CacheTimes.OneMonth : SimklCache.CacheTimes.ThirtyMinutes).getValue()));
        }
        if (mediaObject5 == null) {
            return null;
        }
        Companion.MediaObject mediaObject7 = mediaObject3;
        mediaObject2 = mediaObject5;
        mediaObject = mediaObject7;
        ids = mediaObject2.getIds();
        if (ids != null) {
            simkl = ids.getSimkl();
        } else {
            simkl = null;
        }
        simklEpisodeConstructor = new SimklEpisodeConstructor(simkl, mediaObject2.getType(), mediaObject2.getTotalEpisodes(), Boxing.boxBoolean(mediaObject2.hasEnded()));
        c08161.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
        c08161.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c08161.L$2 = map;
        c08161.L$3 = SpillingKt.nullOutSpilledVariable(strJoinToString$default);
        c08161.L$4 = SpillingKt.nullOutSpilledVariable(mediaObject);
        c08161.L$5 = mediaObject2;
        c08161.L$6 = simklEpisodeConstructor;
        c08161.label = 2;
        syncListSmart = getSyncListSmart(authData2, c08161);
        if (syncListSmart != coroutine_suspended) {
            mediaObject4 = mediaObject2;
            map2 = map;
            simklEpisodeConstructor2 = simklEpisodeConstructor;
            allItemsResponse = (Companion.AllItemsResponse) syncListSmart;
            if (allItemsResponse != null) {
                it = CollectionsKt.flatten(CollectionsKt.listOf((Object[]) new List[]{allItemsResponse.getShows(), allItemsResponse.getAnime(), allItemsResponse.getMovies()})).iterator();
                loop0: while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    metadata2 = (Companion.AllItemsResponse.Metadata) next;
                    if (!map2.isEmpty()) {
                        while (r6.hasNext()) {
                            if (metadata2.getIds().matchesId(entry.getKey(), entry.getValue())) {
                                break loop0;
                                break loop0;
                            }
                        }
                    }
                }
                metadata = (Companion.AllItemsResponse.Metadata) next;
            } else {
                metadata = null;
            }
            if (metadata == null) {
                SyncWatchType syncWatchTypeFromInternalId4 = SyncWatchType.INSTANCE.fromInternalId(Boxing.boxInt(Companion.SimklListStatusType.None.getValue()));
                if (Intrinsics.areEqual(mediaObject4.getType(), "movie")) {
                    totalEpisodes = Boxing.boxInt(0);
                } else {
                    totalEpisodes = mediaObject4.getTotalEpisodes();
                }
                return new SimklSyncStatus(syncWatchTypeFromInternalId4, null, null, Boxing.boxInt(0), simklEpisodeConstructor2, null, totalEpisodes, 0, null, 32, null);
            }
            status = metadata.getStatus();
            if (status != null) {
                SyncWatchType.Companion companion11 = SyncWatchType.INSTANCE;
                simklListStatusTypeFromString = Companion.SimklListStatusType.INSTANCE.fromString(status);
                if (simklListStatusTypeFromString != null) {
                    numBoxInt = Boxing.boxInt(simklListStatusTypeFromString.getValue());
                } else {
                    numBoxInt = null;
                }
                syncWatchTypeFromInternalId = companion11.fromInternalId(numBoxInt);
                if (syncWatchTypeFromInternalId != null) {
                    Score scoreFrom12 = Score.INSTANCE.from10(metadata.getUserRating());
                    Integer watchedEpisodesCount5 = metadata.getWatchedEpisodesCount();
                    Integer totalEpisodes4 = mediaObject4.getTotalEpisodes();
                    Integer watchedEpisodesCount6 = metadata.getWatchedEpisodesCount();
                    return new SimklSyncStatus(syncWatchTypeFromInternalId, scoreFrom12, metadata.getUserRating(), watchedEpisodesCount5, simklEpisodeConstructor2, null, totalEpisodes4, watchedEpisodesCount6 != null ? watchedEpisodesCount6.intValue() : 0, metadata.getStatus(), 32, null);
                }
            }
            return null;
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0122  */
    /* JADX WARN: Code duplicated, block: B:63:0x0185 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x0187  */
    /* JADX WARN: Code duplicated, block: B:65:0x018d  */
    /* JADX WARN: Code duplicated, block: B:66:0x018f  */
    /* JADX WARN: Code duplicated, block: B:68:0x0195  */
    /* JADX WARN: Code duplicated, block: B:69:0x019a  */
    /* JADX WARN: Code duplicated, block: B:71:0x019d  */
    /* JADX WARN: Code duplicated, block: B:75:0x01e4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object updateStatus(AuthData authData, String str, SyncAPI.AbstractSyncStatus abstractSyncStatus, Continuation<? super Boolean> continuation) {
        C08171 c08171;
        Map<SimklSyncServices, String> idFromString;
        SimklSyncStatus simklSyncStatus;
        Integer numBoxInt;
        AuthToken token;
        Companion.SimklScoreBuilder.Builder builderIds;
        AuthData authData2;
        String str2;
        Companion.EpisodeMetadata[] episodeMetadataArr;
        SimklEpisodeConstructor episodeConstructor;
        Object obj;
        String oldStatus;
        Companion.SimklListStatusType next;
        Integer watchedEpisodes;
        List<Companion.EpisodeMetadata> list;
        Object objExecute;
        SyncAPI.AbstractSyncStatus abstractSyncStatus2 = abstractSyncStatus;
        if (continuation instanceof C08171) {
            c08171 = (C08171) continuation;
            if ((c08171.label & Integer.MIN_VALUE) != 0) {
                c08171.label -= Integer.MIN_VALUE;
            } else {
                c08171 = new C08171(continuation);
            }
        } else {
            c08171 = new C08171(continuation);
        }
        Object obj2 = c08171.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08171.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            idFromString = LoadResponse.INSTANCE.readIdFromString(str);
            this.lastScoreTime = APIHolder.INSTANCE.getUnixTime();
            boolean z = abstractSyncStatus2 instanceof SimklSyncStatus;
            simklSyncStatus = z ? (SimklSyncStatus) abstractSyncStatus2 : null;
            Companion.SimklScoreBuilder.Builder builderApiUrl = new Companion.SimklScoreBuilder.Builder(null, null, null, null, null, null, null, false, 255, null).apiUrl(getMainUrl());
            Score score = abstractSyncStatus2.getScore();
            Companion.SimklScoreBuilder.Builder builderScore = builderApiUrl.score(score != null ? Boxing.boxInt(score.toInt(10)) : null, simklSyncStatus != null ? simklSyncStatus.getOldScore() : null);
            Integer numBoxInt2 = Boxing.boxInt(abstractSyncStatus2.getStatus().getInternalId());
            SimklSyncStatus simklSyncStatus2 = z ? (SimklSyncStatus) abstractSyncStatus2 : null;
            if (simklSyncStatus2 == null || (oldStatus = simklSyncStatus2.getOldStatus()) == null) {
                numBoxInt = null;
            } else {
                Iterator<Companion.SimklListStatusType> it = Companion.SimklListStatusType.getEntries().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(next.getOriginalName(), oldStatus));
                Companion.SimklListStatusType simklListStatusType = next;
                if (simklListStatusType != null) {
                    numBoxInt = Boxing.boxInt(simklListStatusType.getValue());
                } else {
                    numBoxInt = null;
                }
            }
            Companion.SimklScoreBuilder.Builder builderStatus = builderScore.status(numBoxInt2, numBoxInt);
            if (authData == null || (token = authData.getToken()) == null) {
                return Boxing.boxBoolean(false);
            }
            builderIds = builderStatus.token(token).ids(Companion.MediaObject.Ids.INSTANCE.fromMap(idFromString));
            if (simklSyncStatus == null || (episodeConstructor = simklSyncStatus.getEpisodeConstructor()) == null) {
                authData2 = authData;
                str2 = str;
                episodeMetadataArr = null;
                if (abstractSyncStatus2.getStatus().getInternalId() == Companion.SimklListStatusType.Completed.getValue()) {
                    watchedEpisodes = abstractSyncStatus2.getWatchedEpisodes();
                } else if (episodeMetadataArr != null) {
                    watchedEpisodes = Boxing.boxInt(episodeMetadataArr.length);
                } else {
                    watchedEpisodes = null;
                }
                if (episodeMetadataArr != null) {
                    list = ArraysKt.toList(episodeMetadataArr);
                } else {
                    list = null;
                }
                builderIds.episodes(list, watchedEpisodes, simklSyncStatus != null ? Boxing.boxInt(simklSyncStatus.getOldEpisodes()) : null);
                setRequireLibraryRefresh(true);
                c08171.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
                c08171.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                c08171.L$2 = SpillingKt.nullOutSpilledVariable(abstractSyncStatus2);
                c08171.L$3 = SpillingKt.nullOutSpilledVariable(idFromString);
                c08171.L$4 = SpillingKt.nullOutSpilledVariable(simklSyncStatus);
                c08171.L$5 = SpillingKt.nullOutSpilledVariable(builderIds);
                c08171.L$6 = SpillingKt.nullOutSpilledVariable(episodeMetadataArr);
                c08171.L$7 = SpillingKt.nullOutSpilledVariable(watchedEpisodes);
                c08171.label = 2;
                objExecute = builderIds.execute(c08171);
                if (objExecute != coroutine_suspended) {
                    return objExecute;
                }
            } else {
                c08171.L$0 = SpillingKt.nullOutSpilledVariable(authData);
                c08171.L$1 = SpillingKt.nullOutSpilledVariable(str);
                c08171.L$2 = abstractSyncStatus2;
                c08171.L$3 = SpillingKt.nullOutSpilledVariable(idFromString);
                c08171.L$4 = simklSyncStatus;
                c08171.L$5 = builderIds;
                c08171.label = 1;
                Object episodes = episodeConstructor.getEpisodes(c08171);
                if (episodes != coroutine_suspended) {
                    str2 = str;
                    obj = episodes;
                    authData2 = authData;
                }
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj2);
            return obj2;
        }
        Companion.SimklScoreBuilder.Builder builder = (Companion.SimklScoreBuilder.Builder) c08171.L$5;
        SimklSyncStatus simklSyncStatus3 = (SimklSyncStatus) c08171.L$4;
        idFromString = (Map) c08171.L$3;
        SyncAPI.AbstractSyncStatus abstractSyncStatus3 = (SyncAPI.AbstractSyncStatus) c08171.L$2;
        str2 = (String) c08171.L$1;
        authData2 = (AuthData) c08171.L$0;
        ResultKt.throwOnFailure(obj2);
        builderIds = builder;
        abstractSyncStatus2 = abstractSyncStatus3;
        simklSyncStatus = simklSyncStatus3;
        obj = obj2;
        episodeMetadataArr = (Companion.EpisodeMetadata[]) obj;
        if (abstractSyncStatus2.getStatus().getInternalId() == Companion.SimklListStatusType.Completed.getValue()) {
            watchedEpisodes = abstractSyncStatus2.getWatchedEpisodes();
        } else if (episodeMetadataArr != null) {
            watchedEpisodes = Boxing.boxInt(episodeMetadataArr.length);
        } else {
            watchedEpisodes = null;
        }
        if (episodeMetadataArr != null) {
            list = ArraysKt.toList(episodeMetadataArr);
        } else {
            list = null;
        }
        builderIds.episodes(list, watchedEpisodes, simklSyncStatus != null ? Boxing.boxInt(simklSyncStatus.getOldEpisodes()) : null);
        setRequireLibraryRefresh(true);
        c08171.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
        c08171.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        c08171.L$2 = SpillingKt.nullOutSpilledVariable(abstractSyncStatus2);
        c08171.L$3 = SpillingKt.nullOutSpilledVariable(idFromString);
        c08171.L$4 = SpillingKt.nullOutSpilledVariable(simklSyncStatus);
        c08171.L$5 = SpillingKt.nullOutSpilledVariable(builderIds);
        c08171.L$6 = SpillingKt.nullOutSpilledVariable(episodeMetadataArr);
        c08171.L$7 = SpillingKt.nullOutSpilledVariable(watchedEpisodes);
        c08171.label = 2;
        objExecute = builderIds.execute(c08171);
        if (objExecute != coroutine_suspended) {
            return coroutine_suspended;
        }
        return objExecute;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object searchByIds(Map<SimklSyncServices, String> map, Continuation<? super Companion.MediaObject[]> continuation) {
        C08151 c08151;
        if (continuation instanceof C08151) {
            c08151 = (C08151) continuation;
            if ((c08151.label & Integer.MIN_VALUE) != 0) {
                c08151.label -= Integer.MIN_VALUE;
            } else {
                c08151 = new C08151(continuation);
            }
        } else {
            c08151 = new C08151(continuation);
        }
        Object obj = c08151.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08151.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (map.isEmpty()) {
                return new Companion.MediaObject[0];
            }
            Requests app2 = MainActivityKt.getApp();
            String str = getMainUrl() + "/search/id";
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("client_id", "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e"));
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<SimklSyncServices, String> entry : map.entrySet()) {
                arrayList.add(TuplesKt.to(entry.getKey().getOriginalName(), entry.getValue()));
            }
            Map mapPlus = MapsKt.plus(mapMapOf, arrayList);
            c08151.L$0 = SpillingKt.nullOutSpilledVariable(map);
            c08151.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, mapPlus, null, false, 0, null, 0L, null, false, null, c08151, 4086, null);
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
            return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.MediaObject[].class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object search(AuthData authData, String str, Continuation<? super List<SyncAPI.SyncSearchResult>> continuation) {
        C08141 c08141;
        Object safe;
        if (continuation instanceof C08141) {
            c08141 = (C08141) continuation;
            if ((c08141.label & Integer.MIN_VALUE) != 0) {
                c08141.label -= Integer.MIN_VALUE;
            } else {
                c08141 = new C08141(continuation);
            }
        } else {
            c08141 = new C08141(continuation);
        }
        Object obj = c08141.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08141.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str2 = getMainUrl() + "/search/";
            Map mapMapOf = MapsKt.mapOf(TuplesKt.to("client_id", "39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e"), TuplesKt.to("q", str));
            c08141.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08141.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c08141.label = 1;
            Object obj2 = Requests.get$default(app2, str2, null, null, mapMapOf, null, false, 0, null, 0L, null, false, null, c08141, 4086, null);
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
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.MediaObject[].class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        Companion.MediaObject[] mediaObjectArr = (Companion.MediaObject[]) safe;
        if (mediaObjectArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Companion.MediaObject mediaObject : mediaObjectArr) {
            SyncAPI.SyncSearchResult syncSearchResult = mediaObject.toSyncSearchResult();
            if (syncSearchResult != null) {
                arrayList.add(syncSearchResult);
            }
        }
        return arrayList;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public AuthLoginPage loginRequest() {
        String string = new BigInteger(130, new SecureRandom()).toString(32);
        return new AuthLoginPage("https://simkl.com/oauth/authorize?response_type=code&client_id=39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e&redirect_uri=cloudstreamapp://" + getRedirectUrlIdentifier() + "&state=" + string, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getSyncListSince(AuthData authData, Long l, Continuation<? super Companion.AllItemsResponse> continuation) {
        C08081 c08081;
        Map mapEmptyMap;
        if (continuation instanceof C08081) {
            c08081 = (C08081) continuation;
            if ((c08081.label & Integer.MIN_VALUE) != 0) {
                c08081.label -= Integer.MIN_VALUE;
            } else {
                c08081 = new C08081(continuation);
            }
        } else {
            c08081 = new C08081(continuation);
        }
        Object obj = c08081.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08081.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Companion companion = INSTANCE;
            String dateTime = companion.getDateTime(l);
            if (dateTime == null || (mapEmptyMap = MapsKt.mapOf(TuplesKt.to("date_from", dateTime))) == null) {
                mapEmptyMap = MapsKt.emptyMap();
            }
            Requests app2 = MainActivityKt.getApp();
            String str = getMainUrl() + "/sync/all-items/";
            Map<String, String> headers = companion.getHeaders(authData.getToken());
            c08081.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08081.L$1 = SpillingKt.nullOutSpilledVariable(l);
            c08081.L$2 = SpillingKt.nullOutSpilledVariable(mapEmptyMap);
            c08081.label = 1;
            Object obj2 = Requests.get$default(app2, str, headers, null, mapEmptyMap, null, false, 0, null, 0L, null, false, null, c08081, 4084, null);
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
            return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.AllItemsResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getActivities(AuthToken authToken, Continuation<? super Companion.ActivitiesResponse> continuation) {
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
            String str = getMainUrl() + "/sync/activities";
            Map<String, String> headers = INSTANCE.getHeaders(authToken);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            anonymousClass1.label = 1;
            Object objPost$default = Requests.post$default(app2, str, headers, null, null, null, null, null, null, null, false, 0, null, 0L, null, false, null, anonymousClass1, 65532, null);
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
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            return parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.ActivitiesResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final Companion.AllItemsResponse getSyncListCached(AuthData auth) {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
        String strValueOf = String.valueOf(auth.getUser().getId());
        Context context = companion.getContext();
        Object obj = null;
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(SIMKL_CACHED_LIST, strValueOf), null);
                if (string != null) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.syncproviders.providers.SimklApi.Companion.AllItemsResponse"));
                    } catch (Throwable th) {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Companion.AllItemsResponse.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                    if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                        objM8936constructorimpl = null;
                    }
                    KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
                    if (kSerializer != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Companion.AllItemsResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListCached$$inlined$getKey$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Companion.AllItemsResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListCached$$inlined$getKey$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Companion.AllItemsResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListCached$$inlined$getKey$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
        }
        return (Companion.AllItemsResponse) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:153:0x036c  */
    /* JADX WARN: Code duplicated, block: B:155:0x0377  */
    /* JADX WARN: Code duplicated, block: B:158:0x0386  */
    /* JADX WARN: Code duplicated, block: B:161:0x0391  */
    /* JADX WARN: Code duplicated, block: B:164:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:167:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:170:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:185:0x0118 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:41:0x0113  */
    /* JADX WARN: Code duplicated, block: B:48:0x0129 A[Catch: Exception -> 0x013b, TRY_LEAVE, TryCatch #1 {Exception -> 0x013b, blocks: (B:24:0x00c0, B:27:0x00cc, B:32:0x00e2, B:39:0x010d, B:42:0x0114, B:48:0x0129, B:47:0x0124, B:38:0x0103, B:31:0x00d8, B:35:0x00e9, B:28:0x00ce, B:44:0x0118), top: B:182:0x00c0, inners: #0, #2, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getSyncListSmart(AuthData authData, Continuation<? super Companion.AllItemsResponse> continuation) {
        C08091 c08091;
        AuthData authData2;
        String strValueOf;
        Object objDecodeFromString;
        String str;
        Integer num;
        String str2;
        Companion.AllItemsResponse.Companion companion;
        Companion.AllItemsResponse allItemsResponse;
        String str3;
        Companion.AllItemsResponse syncListCached;
        Companion.ActivitiesResponse.UpdatedAt movies;
        Companion.ActivitiesResponse.UpdatedAt anime;
        Companion.ActivitiesResponse.UpdatedAt tvShows;
        Companion.ActivitiesResponse.UpdatedAt movies2;
        Companion.ActivitiesResponse.UpdatedAt anime2;
        Companion.ActivitiesResponse.UpdatedAt tvShows2;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Integer numBoxInt;
        Integer numBoxInt2;
        Integer numBoxInt3;
        List<Companion.AllItemsResponse.ShowMetadata> anime3;
        List<Companion.AllItemsResponse.ShowMetadata> shows;
        List<Companion.AllItemsResponse.MovieMetadata> movies3;
        if (continuation instanceof C08091) {
            c08091 = (C08091) continuation;
            if ((c08091.label & Integer.MIN_VALUE) != 0) {
                c08091.label -= Integer.MIN_VALUE;
            } else {
                c08091 = new C08091(continuation);
            }
        } else {
            c08091 = new C08091(continuation);
        }
        Object activities = c08091.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08091.label;
        if (i == 0) {
            ResultKt.throwOnFailure(activities);
            AuthToken token = authData.getToken();
            authData2 = authData;
            c08091.L$0 = authData2;
            c08091.label = 1;
            activities = getActivities(token, c08091);
            if (activities != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i != 1) {
            if (i == 2) {
                long j = c08091.J$1;
                long j2 = c08091.J$0;
                str2 = (String) c08091.L$2;
                ResultKt.throwOnFailure(activities);
                str = ArchComponentExtKt.DEBUG_PRINT;
                num = null;
                syncListCached = (Companion.AllItemsResponse) activities;
                strValueOf = str2;
                if (AppDebug.INSTANCE.isDebug()) {
                    Log log = Log.INSTANCE;
                    StringBuilder sb = new StringBuilder("List sizes: movies=");
                    if (syncListCached != null) {
                        numBoxInt = num;
                    } else {
                        numBoxInt = num;
                    }
                    sb.append(numBoxInt);
                    sb.append(", shows=");
                    if (syncListCached != null) {
                        numBoxInt2 = num;
                    } else {
                        numBoxInt2 = num;
                    }
                    sb.append(numBoxInt2);
                    sb.append(", anime=");
                    if (syncListCached != null) {
                        numBoxInt3 = num;
                    } else {
                        numBoxInt3 = num;
                    }
                    sb.append(numBoxInt3);
                    log.d(str, sb.toString());
                }
                CloudStreamApp.INSTANCE.setKey(SIMKL_CACHED_LIST, strValueOf, syncListCached);
                return syncListCached;
            }
            if (i != 3) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            long j3 = c08091.J$1;
            long j4 = c08091.J$0;
            allItemsResponse = (Companion.AllItemsResponse) c08091.L$5;
            companion = (Companion.AllItemsResponse.Companion) c08091.L$4;
            str3 = (String) c08091.L$2;
            ResultKt.throwOnFailure(activities);
            str = ArchComponentExtKt.DEBUG_PRINT;
            syncListCached = companion.merge(allItemsResponse, (Companion.AllItemsResponse) activities);
            strValueOf = str3;
            num = null;
            if (AppDebug.INSTANCE.isDebug()) {
                Log log2 = Log.INSTANCE;
                StringBuilder sb2 = new StringBuilder("List sizes: movies=");
                if (syncListCached != null || (movies3 = syncListCached.getMovies()) == null) {
                    numBoxInt = num;
                } else {
                    numBoxInt = Boxing.boxInt(movies3.size());
                }
                sb2.append(numBoxInt);
                sb2.append(", shows=");
                if (syncListCached != null || (shows = syncListCached.getShows()) == null) {
                    numBoxInt2 = num;
                } else {
                    numBoxInt2 = Boxing.boxInt(shows.size());
                }
                sb2.append(numBoxInt2);
                sb2.append(", anime=");
                if (syncListCached != null || (anime3 = syncListCached.getAnime()) == null) {
                    numBoxInt3 = num;
                } else {
                    numBoxInt3 = Boxing.boxInt(anime3.size());
                }
                sb2.append(numBoxInt3);
                log2.d(str, sb2.toString());
            }
            CloudStreamApp.INSTANCE.setKey(SIMKL_CACHED_LIST, strValueOf, syncListCached);
            return syncListCached;
        }
        authData2 = (AuthData) c08091.L$0;
        ResultKt.throwOnFailure(activities);
        Companion.ActivitiesResponse activitiesResponse = (Companion.ActivitiesResponse) activities;
        strValueOf = String.valueOf(authData2.getUser().getId());
        CloudStreamApp.Companion companion2 = CloudStreamApp.INSTANCE;
        String strValueOf2 = String.valueOf(authData2.getUser().getId());
        Context context = companion2.getContext();
        if (context != null) {
            DataStore dataStore = DataStore.INSTANCE;
            try {
                String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(SIMKL_CACHED_LIST_TIME, strValueOf2), null);
                if (string == null) {
                    objDecodeFromString = null;
                } else {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(LongSerializer.INSTANCE);
                    } catch (Throwable th) {
                        Result.Companion companion4 = Result.INSTANCE;
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
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Long>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListSmart$$inlined$getKey$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Long>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListSmart$$inlined$getKey$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Long>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListSmart$$inlined$getKey$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Long.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion6 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Long>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.SimklApi$getSyncListSmart$$inlined$getKey$1
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
        Long l = (Long) objDecodeFromString;
        Iterator it = CollectionsKt.listOf((Object[]) new String[]{(activitiesResponse == null || (tvShows2 = activitiesResponse.getTvShows()) == null) ? null : tvShows2.getRemovedFromList(), (activitiesResponse == null || (anime2 = activitiesResponse.getAnime()) == null) ? null : anime2.getRemovedFromList(), (activitiesResponse == null || (movies2 = activitiesResponse.getMovies()) == null) ? null : movies2.getRemovedFromList()}).iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        Long unixTime = INSTANCE.getUnixTime((String) it.next());
        long jLongValue = unixTime != null ? unixTime.longValue() : -1L;
        while (it.hasNext()) {
            Long unixTime2 = INSTANCE.getUnixTime((String) it.next());
            long jLongValue2 = unixTime2 != null ? unixTime2.longValue() : -1L;
            if (jLongValue < jLongValue2) {
                jLongValue = jLongValue2;
            }
        }
        Iterator it2 = CollectionsKt.listOf((Object[]) new String[]{(activitiesResponse == null || (tvShows = activitiesResponse.getTvShows()) == null) ? null : tvShows.getAll(), (activitiesResponse == null || (anime = activitiesResponse.getAnime()) == null) ? null : anime.getAll(), (activitiesResponse == null || (movies = activitiesResponse.getMovies()) == null) ? null : movies.getAll()}).iterator();
        if (!it2.hasNext()) {
            throw new NoSuchElementException();
        }
        Long unixTime3 = INSTANCE.getUnixTime((String) it2.next());
        long jLongValue3 = unixTime3 != null ? unixTime3.longValue() : -1L;
        while (it2.hasNext()) {
            Long unixTime4 = INSTANCE.getUnixTime((String) it2.next());
            long jLongValue4 = unixTime4 != null ? unixTime4.longValue() : -1L;
            if (jLongValue3 < jLongValue4) {
                jLongValue3 = jLongValue4;
            }
        }
        if (AppDebug.INSTANCE.isDebug()) {
            Log.INSTANCE.d(ArchComponentExtKt.DEBUG_PRINT, "Cache times: lastCacheUpdate=" + l + ", lastRemoval=" + jLongValue + ", lastRealUpdate=" + jLongValue3);
        }
        if (l == null || l.longValue() < jLongValue) {
            str = ArchComponentExtKt.DEBUG_PRINT;
            if (AppDebug.INSTANCE.isDebug()) {
                Log.INSTANCE.d(str, "Full list update in " + getName() + '.');
            }
            CloudStreamApp.INSTANCE.setKey(SIMKL_CACHED_LIST_TIME, strValueOf, Boxing.boxLong(jLongValue));
            c08091.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
            c08091.L$1 = SpillingKt.nullOutSpilledVariable(activitiesResponse);
            c08091.L$2 = strValueOf;
            c08091.L$3 = SpillingKt.nullOutSpilledVariable(l);
            c08091.J$0 = jLongValue;
            c08091.J$1 = jLongValue3;
            c08091.label = 2;
            num = null;
            activities = getSyncListSince(authData2, null, c08091);
            if (activities != coroutine_suspended) {
                str2 = strValueOf;
                syncListCached = (Companion.AllItemsResponse) activities;
                strValueOf = str2;
                if (AppDebug.INSTANCE.isDebug()) {
                    Log log3 = Log.INSTANCE;
                    StringBuilder sb3 = new StringBuilder("List sizes: movies=");
                    if (syncListCached != null) {
                        numBoxInt = num;
                    } else {
                        numBoxInt = num;
                    }
                    sb3.append(numBoxInt);
                    sb3.append(", shows=");
                    if (syncListCached != null) {
                        numBoxInt2 = num;
                    } else {
                        numBoxInt2 = num;
                    }
                    sb3.append(numBoxInt2);
                    sb3.append(", anime=");
                    if (syncListCached != null) {
                        numBoxInt3 = num;
                    } else {
                        numBoxInt3 = num;
                    }
                    sb3.append(numBoxInt3);
                    log3.d(str, sb3.toString());
                }
                CloudStreamApp.INSTANCE.setKey(SIMKL_CACHED_LIST, strValueOf, syncListCached);
                return syncListCached;
            }
        } else {
            if (l.longValue() < jLongValue3 || l.longValue() < this.lastScoreTime) {
                str = ArchComponentExtKt.DEBUG_PRINT;
                if (AppDebug.INSTANCE.isDebug()) {
                    Log.INSTANCE.d(str, "Partial list update in " + getName() + '.');
                }
                CloudStreamApp.INSTANCE.setKey(SIMKL_CACHED_LIST_TIME, strValueOf, l);
                Companion.AllItemsResponse.Companion companion7 = Companion.AllItemsResponse.INSTANCE;
                Companion.AllItemsResponse syncListCached2 = getSyncListCached(authData2);
                c08091.L$0 = SpillingKt.nullOutSpilledVariable(authData2);
                c08091.L$1 = SpillingKt.nullOutSpilledVariable(activitiesResponse);
                c08091.L$2 = strValueOf;
                c08091.L$3 = SpillingKt.nullOutSpilledVariable(l);
                c08091.L$4 = companion7;
                c08091.L$5 = syncListCached2;
                c08091.J$0 = jLongValue;
                c08091.J$1 = jLongValue3;
                c08091.label = 3;
                activities = getSyncListSince(authData2, l, c08091);
                if (activities != coroutine_suspended) {
                    companion = companion7;
                    allItemsResponse = syncListCached2;
                    str3 = strValueOf;
                    syncListCached = companion.merge(allItemsResponse, (Companion.AllItemsResponse) activities);
                    strValueOf = str3;
                }
            } else {
                if (AppDebug.INSTANCE.isDebug()) {
                    Log log4 = Log.INSTANCE;
                    String str4 = "Cached list update in " + getName() + '.';
                    str = ArchComponentExtKt.DEBUG_PRINT;
                    log4.d(str, str4);
                } else {
                    str = ArchComponentExtKt.DEBUG_PRINT;
                }
                syncListCached = getSyncListCached(authData2);
            }
            num = null;
            if (AppDebug.INSTANCE.isDebug()) {
                Log log5 = Log.INSTANCE;
                StringBuilder sb4 = new StringBuilder("List sizes: movies=");
                if (syncListCached != null) {
                    numBoxInt = num;
                } else {
                    numBoxInt = num;
                }
                sb4.append(numBoxInt);
                sb4.append(", shows=");
                if (syncListCached != null) {
                    numBoxInt2 = num;
                } else {
                    numBoxInt2 = num;
                }
                sb4.append(numBoxInt2);
                sb4.append(", anime=");
                if (syncListCached != null) {
                    numBoxInt3 = num;
                } else {
                    numBoxInt3 = num;
                }
                sb4.append(numBoxInt3);
                log5.d(str, sb4.toString());
            }
            CloudStreamApp.INSTANCE.setKey(SIMKL_CACHED_LIST, strValueOf, syncListCached);
            return syncListCached;
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:57:0x0192  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object library(AuthData authData, Continuation<? super SyncAPI.LibraryMetadata> continuation) {
        C08111 c08111;
        Pair pair;
        if (continuation instanceof C08111) {
            c08111 = (C08111) continuation;
            if ((c08111.label & Integer.MIN_VALUE) != 0) {
                c08111.label -= Integer.MIN_VALUE;
            } else {
                c08111 = new C08111(continuation);
            }
        } else {
            c08111 = new C08111(continuation);
        }
        Object syncListSmart = c08111.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08111.label;
        if (i == 0) {
            ResultKt.throwOnFailure(syncListSmart);
            if (authData == null) {
                return null;
            }
            c08111.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c08111.label = 1;
            syncListSmart = getSyncListSmart(authData, c08111);
            if (syncListSmart == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(syncListSmart);
        }
        Companion.AllItemsResponse allItemsResponse = (Companion.AllItemsResponse) syncListSmart;
        if (allItemsResponse == null) {
            return null;
        }
        EnumEntries<Companion.SimklListStatusType> entries = Companion.SimklListStatusType.getEntries();
        ArrayList arrayList = new ArrayList();
        for (Companion.SimklListStatusType simklListStatusType : entries) {
            Companion.SimklListStatusType simklListStatusType2 = simklListStatusType;
            if (simklListStatusType2.getValue() >= 0 && simklListStatusType2.getValue() != Companion.SimklListStatusType.ReWatching.getValue()) {
                arrayList.add(simklListStatusType);
            }
        }
        ArrayList arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Pair pair2 = TuplesKt.to(Boxing.boxInt(((Companion.SimklListStatusType) it.next()).getStringRes()), CollectionsKt.emptyList());
            linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
        }
        List listFlatten = CollectionsKt.flatten(CollectionsKt.listOf((Object[]) new List[]{allItemsResponse.getAnime(), allItemsResponse.getMovies(), allItemsResponse.getShows()}));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Object obj : listFlatten) {
            String status = ((Companion.AllItemsResponse.Metadata) obj).getStatus();
            Object obj2 = linkedHashMap2.get(status);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap2.put(status, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (str == null) {
                pair = null;
            } else {
                Companion.SimklListStatusType simklListStatusTypeFromString = Companion.SimklListStatusType.INSTANCE.fromString(str);
                Integer numBoxInt = simklListStatusTypeFromString != null ? Boxing.boxInt(simklListStatusTypeFromString.getStringRes()) : null;
                if (numBoxInt != null) {
                    int iIntValue = numBoxInt.intValue();
                    List list2 = list;
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    Iterator it2 = list2.iterator();
                    while (it2.hasNext()) {
                        arrayList4.add(((Companion.AllItemsResponse.Metadata) it2.next()).toLibraryItem());
                    }
                    pair = TuplesKt.to(Boxing.boxInt(iIntValue), arrayList4);
                } else {
                    pair = null;
                }
            }
            if (pair != null) {
                arrayList3.add(pair);
            }
        }
        Map mapPlus = MapsKt.plus(linkedHashMap, MapsKt.toMap(arrayList3));
        ArrayList arrayList5 = new ArrayList(mapPlus.size());
        for (Map.Entry entry2 : mapPlus.entrySet()) {
            arrayList5.add(new SyncAPI.LibraryList(TextUtilKt.txt(((Number) entry2.getKey()).intValue(), new Object[0]), (List) entry2.getValue()));
        }
        return new SyncAPI.LibraryMetadata(arrayList5, SetsKt.setOf((Object[]) new ListSorting[]{ListSorting.AlphabeticalA, ListSorting.AlphabeticalZ, ListSorting.UpdatedNew, ListSorting.UpdatedOld, ListSorting.ReleaseDateNew, ListSorting.ReleaseDateOld, ListSorting.RatingHigh, ListSorting.RatingLow}));
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public String urlToId(String url) {
        List<String> groupValues;
        String str;
        Intrinsics.checkNotNullParameter(url, "url");
        MatchResult matchResultFind$default = Regex.find$default(new Regex("https://simkl\\.com/[^/]*/(\\d+).*"), url, 0, 2, null);
        return (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) ? "" : str;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object pinRequest(Continuation<? super AuthPinData> continuation) {
        C08131 c08131;
        Object safe;
        if (continuation instanceof C08131) {
            c08131 = (C08131) continuation;
            if ((c08131.label & Integer.MIN_VALUE) != 0) {
                c08131.label -= Integer.MIN_VALUE;
            } else {
                c08131 = new C08131(continuation);
            }
        } else {
            c08131 = new C08131(continuation);
        }
        Object obj = c08131.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08131.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = getMainUrl() + "/oauth/pin?client_id=39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e&redirect_uri=cloudstreamapp://" + getRedirectUrlIdentifier();
            c08131.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c08131, 4094, null);
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
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.PinAuthResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        Companion.PinAuthResponse pinAuthResponse = (Companion.PinAuthResponse) safe;
        if (pinAuthResponse == null) {
            return null;
        }
        return new AuthPinData(pinAuthResponse.getDeviceCode(), pinAuthResponse.getUserCode(), pinAuthResponse.getVerificationUrl(), pinAuthResponse.getExpiresIn(), pinAuthResponse.getInterval());
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(AuthPinData authPinData, Continuation<? super AuthToken> continuation) {
        C08121 c08121;
        Object safe;
        String accessToken;
        if (continuation instanceof C08121) {
            c08121 = (C08121) continuation;
            if ((c08121.label & Integer.MIN_VALUE) != 0) {
                c08121.label -= Integer.MIN_VALUE;
            } else {
                c08121 = new C08121(continuation);
            }
        } else {
            c08121 = new C08121(continuation);
        }
        Object obj = c08121.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08121.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            String str = getMainUrl() + "/oauth/pin/" + authPinData.getUserCode() + "?client_id=39f470a9f2ec1aa2383269ca831bc7be0e47da48d6d708ccad9bed4e1a60993e";
            c08121.L$0 = SpillingKt.nullOutSpilledVariable(authPinData);
            c08121.label = 1;
            Object obj2 = Requests.get$default(app2, str, null, null, null, null, false, 0, null, 0L, null, false, null, c08121, 4094, null);
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
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.PinExchangeResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        Companion.PinExchangeResponse pinExchangeResponse = (Companion.PinExchangeResponse) safe;
        if (pinExchangeResponse == null || (accessToken = pinExchangeResponse.getAccessToken()) == null) {
            return null;
        }
        return new AuthToken(accessToken, (String) null, (Long) null, (Long) null, (String) null, 30, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(String str, String str2, Continuation<? super AuthToken> continuation) {
        AnonymousClass2 anonymousClass2;
        String queryParameter;
        Object safe;
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
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Uri uri = Uri.parse(str);
            String queryParameter2 = uri.getQueryParameter("state");
            if (!Intrinsics.areEqual(queryParameter2, str2) || (queryParameter = uri.getQueryParameter("code")) == null) {
                return null;
            }
            Requests app2 = MainActivityKt.getApp();
            String str3 = getMainUrl() + "/oauth/token";
            Companion.TokenRequest tokenRequest = new Companion.TokenRequest(queryParameter, null, null, null, null, 30, null);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(uri);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(queryParameter2);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(queryParameter);
            anonymousClass2.label = 1;
            Object objPost$default = Requests.post$default(app2, str3, null, null, null, null, null, null, tokenRequest, null, false, 0, null, 0L, null, false, null, anonymousClass2, 65406, null);
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
        try {
            ResponseParser parser = niceResponse.getParser();
            Intrinsics.checkNotNull(parser);
            safe = parser.parseSafe(niceResponse.getText(), Reflection.getOrCreateKotlinClass(Companion.TokenResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
            safe = null;
        }
        Companion.TokenResponse tokenResponse = (Companion.TokenResponse) safe;
        if (tokenResponse == null) {
            return null;
        }
        return new AuthToken(tokenResponse.getAccessToken(), (String) null, (Long) null, (Long) null, (String) null, 30, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) {
        C08181 c08181;
        if (continuation instanceof C08181) {
            c08181 = (C08181) continuation;
            if ((c08181.label & Integer.MIN_VALUE) != 0) {
                c08181.label -= Integer.MIN_VALUE;
            } else {
                c08181 = new C08181(continuation);
            }
        } else {
            c08181 = new C08181(continuation);
        }
        Object user = c08181.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c08181.label;
        if (i == 0) {
            ResultKt.throwOnFailure(user);
            if (authToken == null) {
                return null;
            }
            c08181.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c08181.label = 1;
            user = getUser(authToken, c08181);
            if (user == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(user);
        }
        Companion.SettingsResponse settingsResponse = (Companion.SettingsResponse) user;
        return new AuthUser(settingsResponse.getUser().getName(), settingsResponse.getAccount().getId(), settingsResponse.getUser().getAvatar(), (Map) null, 8, (DefaultConstructorMarker) null);
    }
}
