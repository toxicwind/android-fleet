package com.lagradost.cloudstream3.syncproviders.providers;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.Actor;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.ActorRole;
import com.lagradost.cloudstream3.BuildConfig;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.ErrorLoadingException;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.NextAiring;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
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
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import io.ktor.http.LinkHeader;
import j$.net.URLEncoder;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
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
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.ReferenceArraySerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: compiled from: AniListApi.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bD\u0018\u0000 ^2\u00020\u0001:b^_`abcdefghijklmnopqrstuvwxyz{|}~\u007f\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001\u0090\u0001\u0091\u0001\u0092\u0001\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010$\u001a\u0004\u0018\u00010%H\u0016J\"\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010*J\u0018\u0010+\u001a\u0004\u0018\u00010'2\u0006\u0010,\u001a\u00020'H\u0096@¢\u0006\u0002\u0010-J\u001a\u0010.\u001a\u0004\u0018\u00010/2\b\u0010,\u001a\u0004\u0018\u00010'H\u0096@¢\u0006\u0002\u0010-J\u0012\u00100\u001a\u0004\u0018\u00010\u00052\u0006\u00101\u001a\u00020\u0005H\u0016J\u0010\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u001bH\u0002J(\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u0001052\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00109\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010:J\"\u0010;\u001a\u0004\u0018\u00010<2\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00103\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010:J\"\u0010=\u001a\u0004\u0018\u00010>2\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00103\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010:J(\u0010?\u001a\u00020\u00102\b\u00107\u001a\u0004\u0018\u0001082\u0006\u00103\u001a\u00020\u00052\u0006\u0010@\u001a\u00020>H\u0096@¢\u0006\u0002\u0010AJ \u0010B\u001a\u0004\u0018\u00010C2\u0006\u00107\u001a\u0002082\u0006\u00103\u001a\u00020\u001bH\u0082@¢\u0006\u0002\u0010DJ*\u0010E\u001a\u0004\u0018\u00010\u00052\u0006\u0010,\u001a\u00020'2\u0006\u0010F\u001a\u00020\u00052\b\b\u0002\u0010G\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010HJ\u001e\u0010I\u001a\n\u0012\u0004\u0012\u00020K\u0018\u00010J2\u0006\u00107\u001a\u000208H\u0082@¢\u0006\u0002\u0010LJ\u001a\u0010M\u001a\u0004\u0018\u00010N2\b\u00107\u001a\u0004\u0018\u000108H\u0096@¢\u0006\u0002\u0010LJ\u0018\u0010O\u001a\u0004\u0018\u00010P2\u0006\u00107\u001a\u000208H\u0082@¢\u0006\u0002\u0010LJ\u001e\u0010Q\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u00103\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010DJ:\u0010R\u001a\u00020\u00102\u0006\u00107\u001a\u0002082\u0006\u00103\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020T2\b\u0010U\u001a\u0004\u0018\u00010V2\b\u0010W\u001a\u0004\u0018\u00010\u001bH\u0082@¢\u0006\u0002\u0010XJ\u0018\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010,\u001a\u00020'H\u0082@¢\u0006\u0002\u0010-J\u001e\u0010[\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\\052\u0006\u00103\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010]R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0017\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0007\"\u0004\b\u0019\u0010\tR\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0014\u0010 \u001a\u00020!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006 \u0001"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "idPrefix", "getIdPrefix", "key", "redirectUrlIdentifier", "getRedirectUrlIdentifier", "requireLibraryRefresh", "", "getRequireLibraryRefresh", "()Z", "setRequireLibraryRefresh", "(Z)V", "hasOAuth2", "getHasOAuth2", "mainUrl", "getMainUrl", "setMainUrl", "icon", "", "getIcon", "()Ljava/lang/Integer;", "createAccountUrl", "getCreateAccountUrl", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "loginRequest", "Lcom/lagradost/cloudstream3/syncproviders/AuthLoginPage;", "login", "Lcom/lagradost/cloudstream3/syncproviders/AuthToken;", "redirectUrl", "payload", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshToken", "token", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "user", "Lcom/lagradost/cloudstream3/syncproviders/AuthUser;", "urlToId", ImagesContract.URL, "getUrlFromId", "id", "search", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", SearchIntents.EXTRA_QUERY, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "updateStatus", "newStatus", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDataAboutId", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListTitleHolder;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postApi", "q", "cache", "(Lcom/lagradost/cloudstream3/syncproviders/AuthToken;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAniListAnimeListSmart", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Lists;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "getFullAniListList", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$FullAnilistList;", "toggleLike", "postDataAboutId", "type", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;", "score", "Lcom/lagradost/cloudstream3/Score;", "progress", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUser", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListUser;", "getAllSeasons", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonResponse;", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Variables", "MediaRecommendation", "FullAnilistList", "CompletedAt", "StartedAt", "Title", "CoverImage", "Media", "Entries", "Lists", "MediaListCollection", "Data", "MediaListItemRoot", "MediaListItem", "MediaListId", "SeasonResponse", "SeasonData", "RecommendedMedia", "CharacterMedia", "SeasonMedia", "RecommendationConnection", "RecommendationEdge", "Recommendation", "CharacterName", "CharacterImage", "Character", "CharacterEdge", "StaffImage", "StaffName", "Staff", "CharacterConnection", "MediaTrailer", "MediaCoverImage", "SeasonNextAiringEpisode", "SeasonEdges", "SeasonEdge", "AniListFavoritesMediaConnection", "AniListFavourites", "MediaTitle", "SeasonNode", "AniListAvatar", "AniListViewer", "AniListData", "AniListRoot", "AniListUser", "LikeNode", "LikePageInfo", "LikeAnime", "LikeFavourites", "LikeViewer", "LikeData", "LikeRoot", "AniListTitleHolder", "GetDataMediaListEntry", "Nodes", "GetDataMedia", "Recommendations", "GetDataData", "GetDataRoot", "GetSearchTitle", "TrailerObject", "GetSearchMedia", "GetSearchPage", "GetSearchData", "GetSearchRoot", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AniListApi extends SyncAPI {
    public static final String ANILIST_CACHED_LIST = "anilist_cached_list";
    public static final int MAX_STALE = 600;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] aniListStatusString = {"CURRENT", "COMPLETED", "PAUSED", "DROPPED", "PLANNING", "REPEATING"};
    private String name = "AniList";
    private final String idPrefix = "anilist";
    private final String key = BuildConfig.ANILIST_KEY;
    private final String redirectUrlIdentifier = "anilistlogin";
    private boolean requireLibraryRefresh = true;
    private final boolean hasOAuth2 = true;
    private String mainUrl = "https://anilist.co";
    private final int icon = R.drawable.ic_anilist_icon;
    private final String createAccountUrl = getMainUrl() + "/signup";
    private final SyncIdName syncIdName = SyncIdName.Anilist;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getAllSeasons$1, reason: invalid class name */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0}, l = {860}, m = "getAllSeasons", n = {"seasons", "id"}, nl = {861}, s = {"L$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return AniListApi.this.getAllSeasons(0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getAniListAnimeListSmart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0}, l = {644}, m = "getAniListAnimeListSmart", n = {"auth"}, nl = {1218}, s = {"L$0"}, v = 2)
    static final class C07691 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07691(Continuation<? super C07691> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.getAniListAnimeListSmart(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getDataAboutId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0}, l = {482}, m = "getDataAboutId", n = {"auth", "q", "id"}, nl = {483}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07701 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07701(Continuation<? super C07701> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.getDataAboutId(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getFullAniListList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0, 0}, l = {728}, m = "getFullAniListList", n = {"auth", "mediaType", SearchIntents.EXTRA_QUERY, "userID"}, nl = {729}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C07711 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C07711(Continuation<? super C07711> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.getFullAniListList(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getUser$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0}, l = {832}, m = "getUser", n = {"token", "q"}, nl = {833}, s = {"L$0", "L$1"}, v = 2)
    static final class C07721 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07721(Continuation<? super C07721> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.getUser(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$library$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0}, l = {658}, m = "library", n = {"auth"}, nl = {1218}, s = {"L$0"}, v = 2)
    static final class C07731 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07731(Continuation<? super C07731> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.library(null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0}, l = {105}, m = "load", n = {"auth", "id", "internalId"}, nl = {107}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07741 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07741(Continuation<? super C07741> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.load(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$postApi$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0}, l = {TypedValues.PositionType.TYPE_POSITION_TYPE}, m = "postApi", n = {"token", "q", "cache"}, nl = {524}, s = {"L$0", "L$1", "Z$0"}, v = 2)
    static final class C07751 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C07751(Continuation<? super C07751> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.postApi(null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$postDataAboutId$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {784, 810}, m = "postDataAboutId", n = {"auth", "type", "score", "progress", "idQuery", "id", "userID", "auth", "type", "score", "progress", "q", "id", "userID"}, nl = {786, 811}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "I$1"}, v = 2)
    static final class C07761 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C07761(Continuation<? super C07761> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.postDataAboutId(null, 0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0}, l = {90}, m = "search", n = {"auth", SearchIntents.EXTRA_QUERY}, nl = {91}, s = {"L$0", "L$1"}, v = 2)
    static final class C07771 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07771(Continuation<? super C07771> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.search(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$status$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0}, l = {165}, m = NotificationCompat.CATEGORY_STATUS, n = {"auth", "id", "internalId"}, nl = {167}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07781 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07781(Continuation<? super C07781> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.status(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$toggleLike$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0, 0, 0}, l = {745}, m = "toggleLike", n = {"auth", "q", "id"}, nl = {746}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C07791 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C07791(Continuation<? super C07791> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.toggleLike(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$user$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.syncproviders.providers.AniListApi", f = "AniListApi.kt", i = {0}, l = {72}, m = "user", n = {"token"}, nl = {73}, s = {"L$0"}, v = 2)
    static final class C07801 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C07801(Continuation<? super C07801> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AniListApi.this.user(null, this);
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
    public String getRedirectUrlIdentifier() {
        return this.redirectUrlIdentifier;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public boolean getRequireLibraryRefresh() {
        return this.requireLibraryRefresh;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public void setRequireLibraryRefresh(boolean z) {
        this.requireLibraryRefresh = z;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public boolean getHasOAuth2() {
        return this.hasOAuth2;
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

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public AuthLoginPage loginRequest() {
        return new AuthLoginPage("https://anilist.co/api/v2/oauth/authorize?client_id=" + this.key + "&response_type=token", null, 2, null);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object login(String str, String str2, Continuation<? super AuthToken> continuation) throws MalformedURLException, ErrorLoadingException {
        Map<String, String> mapSplitRedirectUrl = AuthAPI.INSTANCE.splitRedirectUrl(str);
        String str3 = mapSplitRedirectUrl.get("access_token");
        if (str3 == null) {
            throw new ErrorLoadingException("No access token");
        }
        long unixTime = APIHolder.INSTANCE.getUnixTime();
        String str4 = mapSplitRedirectUrl.get("expires_in");
        Intrinsics.checkNotNull(str4);
        return new AuthToken(str3, (String) null, Boxing.boxLong(unixTime + Long.parseLong(str4)), (Long) null, (String) null, 26, (DefaultConstructorMarker) null);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object refreshToken(AuthToken authToken, Continuation<? super AuthToken> continuation) {
        return super.refreshToken(authToken, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.AuthAPI
    public Object user(AuthToken authToken, Continuation<? super AuthUser> continuation) throws ErrorLoadingException, JsonProcessingException {
        C07801 c07801;
        if (continuation instanceof C07801) {
            c07801 = (C07801) continuation;
            if ((c07801.label & Integer.MIN_VALUE) != 0) {
                c07801.label -= Integer.MIN_VALUE;
            } else {
                c07801 = new C07801(continuation);
            }
        } else {
            c07801 = new C07801(continuation);
        }
        Object user = c07801.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07801.label;
        if (i == 0) {
            ResultKt.throwOnFailure(user);
            if (authToken == null) {
                return null;
            }
            c07801.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07801.label = 1;
            user = getUser(authToken, c07801);
            if (user == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(user);
        }
        AniListUser aniListUser = (AniListUser) user;
        if (aniListUser == null) {
            throw new ErrorLoadingException("Unable to fetch user data");
        }
        return new AuthUser(aniListUser.getName(), aniListUser.getId(), aniListUser.getPicture(), (Map) null, 8, (DefaultConstructorMarker) null);
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public String urlToId(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.removeSuffix(StringsKt.removePrefix(url, (CharSequence) (getMainUrl() + "/anime/")), (CharSequence) "/");
    }

    private final String getUrlFromId(int id) {
        return getMainUrl() + "/anime/" + id;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object search(AuthData authData, String str, Continuation<? super List<SyncAPI.SyncSearchResult>> continuation) {
        C07771 c07771;
        GetSearchPage data;
        GetSearchData page;
        List<GetSearchMedia> media;
        if (continuation instanceof C07771) {
            c07771 = (C07771) continuation;
            if ((c07771.label & Integer.MIN_VALUE) != 0) {
                c07771.label -= Integer.MIN_VALUE;
            } else {
                c07771 = new C07771(continuation);
            }
        } else {
            c07771 = new C07771(continuation);
        }
        Object objSearchShows = c07771.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07771.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objSearchShows);
            Companion companion = INSTANCE;
            c07771.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07771.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07771.label = 1;
            objSearchShows = companion.searchShows(str, c07771);
            if (objSearchShows == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objSearchShows);
        }
        GetSearchRoot getSearchRoot = (GetSearchRoot) objSearchShows;
        if (getSearchRoot == null || (data = getSearchRoot.getData()) == null || (page = data.getPage()) == null || (media = page.getMedia()) == null) {
            return null;
        }
        List<GetSearchMedia> list = media;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (GetSearchMedia getSearchMedia : list) {
            String romaji = getSearchMedia.getTitle().getRomaji();
            if (romaji == null) {
                return null;
            }
            arrayList.add(new SyncAPI.SyncSearchResult(romaji, getName(), String.valueOf(getSearchMedia.getId()), getUrlFromId(getSearchMedia.getId()), getSearchMedia.getBannerImage(), null, null, null, null, null, 992, null));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:102:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:104:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:106:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:107:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:109:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:111:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:112:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:117:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:119:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:121:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:122:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:124:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:126:0x0203  */
    /* JADX WARN: Code duplicated, block: B:156:0x0296 A[PHI: r5
      0x0296: PHI (r5v12 java.lang.String) = (r5v9 java.lang.String), (r5v10 java.lang.String), (r5v13 java.lang.String) binds: [B:161:0x02a5, B:164:0x02ad, B:154:0x0293] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:167:0x02ca  */
    /* JADX WARN: Code duplicated, block: B:179:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:33:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:49:0x0114  */
    /* JADX WARN: Code duplicated, block: B:65:0x0140 A[PHI: r7
      0x0140: PHI (r7v13 java.lang.String) = (r7v7 java.lang.String), (r7v10 java.lang.String), (r7v34 java.lang.String) binds: [B:58:0x012f, B:63:0x013d, B:53:0x0121] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:67:0x0146  */
    /* JADX WARN: Code duplicated, block: B:69:0x014c  */
    /* JADX WARN: Code duplicated, block: B:71:0x0152  */
    /* JADX WARN: Code duplicated, block: B:72:0x0157  */
    /* JADX WARN: Code duplicated, block: B:75:0x0163  */
    /* JADX WARN: Code duplicated, block: B:77:0x016c  */
    /* JADX WARN: Code duplicated, block: B:79:0x0171  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code duplicated, block: B:82:0x0177  */
    /* JADX WARN: Code duplicated, block: B:86:0x0183  */
    /* JADX WARN: Code duplicated, block: B:89:0x018c  */
    /* JADX WARN: Code duplicated, block: B:90:0x018f  */
    /* JADX WARN: Code duplicated, block: B:93:0x0198  */
    /* JADX WARN: Code duplicated, block: B:95:0x019d  */
    /* JADX WARN: Code duplicated, block: B:98:0x01a5  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object load(AuthData authData, String str, Continuation<? super SyncAPI.SyncResult> continuation) throws ErrorLoadingException {
        C07741 c07741;
        String str2;
        List<String> groupValues;
        NextAiring nextAiring;
        ArrayList arrayList;
        ArrayList arrayList2;
        String string;
        String site;
        List<RecommendationEdge> edges;
        SyncAPI.SyncSearchResult syncSearchResult;
        MediaTitle title;
        String userPreferred;
        String strValueOf;
        String large;
        String str3;
        List<CharacterEdge> edges2;
        String full;
        CharacterImage image;
        CharacterImage image2;
        String medium;
        String role;
        ActorRole actorRole;
        List<Staff> voiceActors;
        Actor actor;
        ActorData actorData;
        Iterator<T> it;
        Actor actor2;
        Staff staff;
        StaffName name;
        StaffName name2;
        String full2;
        StaffName name3;
        StaffImage image3;
        StaffImage image4;
        String medium2;
        int iHashCode;
        ActorRole actorRole2;
        NextAiring nextAiring2;
        if (continuation instanceof C07741) {
            c07741 = (C07741) continuation;
            if ((c07741.label & Integer.MIN_VALUE) != 0) {
                c07741.label -= Integer.MIN_VALUE;
            } else {
                c07741 = new C07741(continuation);
            }
        } else {
            c07741 = new C07741(continuation);
        }
        Object season = c07741.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07741.label;
        if (i == 0) {
            ResultKt.throwOnFailure(season);
            MatchResult matchResultFind$default = Regex.find$default(new Regex("anilist\\.co/anime/(\\d*)"), str, 0, 2, null);
            if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str2 = (String) CollectionsKt.getOrNull(groupValues, 1)) == null) {
                str2 = str;
            }
            Integer intOrNull = StringsKt.toIntOrNull(str2);
            if (intOrNull != null) {
                int iIntValue = intOrNull.intValue();
                Companion companion = INSTANCE;
                c07741.L$0 = SpillingKt.nullOutSpilledVariable(authData);
                c07741.L$1 = SpillingKt.nullOutSpilledVariable(str);
                c07741.I$0 = iIntValue;
                c07741.label = 1;
                season = companion.getSeason(iIntValue, c07741);
                if (season == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw new ErrorLoadingException("Invalid internalId");
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c07741.I$0;
            ResultKt.throwOnFailure(season);
        }
        SeasonMedia media = ((SeasonResponse) season).getData().getMedia();
        String strValueOf2 = String.valueOf(media.getId());
        SeasonNextAiringEpisode nextAiringEpisode = media.getNextAiringEpisode();
        if (nextAiringEpisode != null) {
            Integer episode = nextAiringEpisode.getEpisode();
            if (episode != null) {
                int iIntValue2 = episode.intValue();
                Integer timeUntilAiring = nextAiringEpisode.getTimeUntilAiring();
                if (timeUntilAiring != null) {
                    nextAiring2 = new NextAiring(iIntValue2, ((long) timeUntilAiring.intValue()) + APIHolder.INSTANCE.getUnixTime(), null, 4, null);
                } else {
                    nextAiring2 = null;
                }
            } else {
                nextAiring2 = null;
            }
            nextAiring = nextAiring2;
        } else {
            nextAiring = null;
        }
        MediaTitle title2 = media.getTitle();
        String userPreferred2 = title2 != null ? title2.getUserPreferred() : null;
        List<String> synonyms = media.getSynonyms();
        Boolean boolIsAdult = media.isAdult();
        Integer episodes = media.getEpisodes();
        String description = media.getDescription();
        CharacterConnection characters = media.getCharacters();
        if (characters == null || (edges2 = characters.getEdges()) == null) {
            arrayList = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (CharacterEdge characterEdge : edges2) {
                Character node = characterEdge.getNode();
                if (node != null) {
                    CharacterName name4 = node.getName();
                    if (name4 == null || (full = name4.getUserPreferred()) == null) {
                        CharacterName name5 = node.getName();
                        full = name5 != null ? name5.getFull() : null;
                        if (full != null) {
                            image = node.getImage();
                            if (image != null || (medium = image.getLarge()) == null) {
                                image2 = node.getImage();
                                if (image2 != null) {
                                    medium = image2.getMedium();
                                } else {
                                    medium = null;
                                }
                            }
                            Actor actor3 = new Actor(full, medium);
                            role = characterEdge.getRole();
                            if (role == null) {
                                actorRole = null;
                            } else {
                                iHashCode = role.hashCode();
                                if (iHashCode != -847101650) {
                                    if (iHashCode != -128795213) {
                                        if (iHashCode == 2358713 && role.equals("MAIN")) {
                                            actorRole2 = ActorRole.Main;
                                            actorRole = actorRole2;
                                        } else {
                                            actorRole = null;
                                        }
                                    } else if (role.equals("SUPPORTING")) {
                                        actorRole2 = ActorRole.Supporting;
                                        actorRole = actorRole2;
                                    } else {
                                        actorRole = null;
                                    }
                                } else if (role.equals("BACKGROUND")) {
                                    actorRole2 = ActorRole.Background;
                                    actorRole = actorRole2;
                                } else {
                                    actorRole = null;
                                }
                            }
                            voiceActors = characterEdge.getVoiceActors();
                            if (voiceActors != null) {
                                it = voiceActors.iterator();
                                if (it.hasNext()) {
                                    staff = (Staff) it.next();
                                    name = staff.getName();
                                    if (name != null || (full2 = name.getUserPreferred()) == null) {
                                        name2 = staff.getName();
                                        if (name2 != null) {
                                            full2 = name2.getFull();
                                        } else {
                                            full2 = null;
                                        }
                                        if (full2 == null) {
                                            name3 = staff.getName();
                                            if (name3 != null) {
                                                full2 = name3.getNative();
                                            } else {
                                                full2 = null;
                                            }
                                            if (full2 == null) {
                                                actorData = null;
                                            }
                                        }
                                    }
                                    image3 = staff.getImage();
                                    if (image3 != null || (medium2 = image3.getLarge()) == null) {
                                        image4 = staff.getImage();
                                        if (image4 != null) {
                                            medium2 = image4.getMedium();
                                        } else {
                                            medium2 = null;
                                        }
                                    }
                                    actor2 = new Actor(full2, medium2);
                                } else {
                                    actor2 = null;
                                }
                                actor = actor2;
                            } else {
                                actor = null;
                            }
                            actorData = new ActorData(actor3, actorRole, null, actor, 4, null);
                        } else {
                            CharacterName name6 = node.getName();
                            full = name6 != null ? name6.getNative() : null;
                            if (full == null) {
                                actorData = null;
                            } else {
                                image = node.getImage();
                                if (image != null) {
                                    image2 = node.getImage();
                                    if (image2 != null) {
                                        medium = image2.getMedium();
                                    } else {
                                        medium = null;
                                    }
                                } else {
                                    image2 = node.getImage();
                                    if (image2 != null) {
                                        medium = image2.getMedium();
                                    } else {
                                        medium = null;
                                    }
                                }
                                Actor actor4 = new Actor(full, medium);
                                role = characterEdge.getRole();
                                if (role == null) {
                                    actorRole = null;
                                } else {
                                    iHashCode = role.hashCode();
                                    if (iHashCode != -847101650) {
                                        if (iHashCode != -128795213) {
                                            if (iHashCode == 2358713) {
                                                actorRole2 = ActorRole.Main;
                                                actorRole = actorRole2;
                                            }
                                            actorRole = null;
                                        } else if (role.equals("SUPPORTING")) {
                                            actorRole = null;
                                        } else {
                                            actorRole2 = ActorRole.Supporting;
                                            actorRole = actorRole2;
                                        }
                                    } else if (role.equals("BACKGROUND")) {
                                        actorRole = null;
                                    } else {
                                        actorRole2 = ActorRole.Background;
                                        actorRole = actorRole2;
                                    }
                                }
                                voiceActors = characterEdge.getVoiceActors();
                                if (voiceActors != null) {
                                    it = voiceActors.iterator();
                                    if (it.hasNext()) {
                                        staff = (Staff) it.next();
                                        name = staff.getName();
                                        if (name != null) {
                                            name2 = staff.getName();
                                            if (name2 != null) {
                                                full2 = name2.getFull();
                                            } else {
                                                full2 = null;
                                            }
                                            if (full2 == null) {
                                                name3 = staff.getName();
                                                if (name3 != null) {
                                                    full2 = name3.getNative();
                                                } else {
                                                    full2 = null;
                                                }
                                                if (full2 == null) {
                                                    actorData = null;
                                                }
                                            }
                                        } else {
                                            name2 = staff.getName();
                                            if (name2 != null) {
                                                full2 = name2.getFull();
                                            } else {
                                                full2 = null;
                                            }
                                            if (full2 == null) {
                                                name3 = staff.getName();
                                                if (name3 != null) {
                                                    full2 = name3.getNative();
                                                } else {
                                                    full2 = null;
                                                }
                                                if (full2 == null) {
                                                    actorData = null;
                                                }
                                            }
                                        }
                                        image3 = staff.getImage();
                                        if (image3 != null) {
                                            image4 = staff.getImage();
                                            if (image4 != null) {
                                                medium2 = image4.getMedium();
                                            } else {
                                                medium2 = null;
                                            }
                                        } else {
                                            image4 = staff.getImage();
                                            if (image4 != null) {
                                                medium2 = image4.getMedium();
                                            } else {
                                                medium2 = null;
                                            }
                                        }
                                        actor2 = new Actor(full2, medium2);
                                    } else {
                                        actor2 = null;
                                    }
                                    actor = actor2;
                                } else {
                                    actor = null;
                                }
                                actorData = new ActorData(actor4, actorRole, null, actor, 4, null);
                            }
                        }
                    } else {
                        image = node.getImage();
                        if (image != null) {
                            image2 = node.getImage();
                            if (image2 != null) {
                                medium = image2.getMedium();
                            } else {
                                medium = null;
                            }
                        } else {
                            image2 = node.getImage();
                            if (image2 != null) {
                                medium = image2.getMedium();
                            } else {
                                medium = null;
                            }
                        }
                        Actor actor5 = new Actor(full, medium);
                        role = characterEdge.getRole();
                        if (role == null) {
                            actorRole = null;
                        } else {
                            iHashCode = role.hashCode();
                            if (iHashCode != -847101650) {
                                if (iHashCode != -128795213) {
                                    if (iHashCode == 2358713) {
                                        actorRole2 = ActorRole.Main;
                                        actorRole = actorRole2;
                                    }
                                    actorRole = null;
                                } else if (role.equals("SUPPORTING")) {
                                    actorRole = null;
                                } else {
                                    actorRole2 = ActorRole.Supporting;
                                    actorRole = actorRole2;
                                }
                            } else if (role.equals("BACKGROUND")) {
                                actorRole = null;
                            } else {
                                actorRole2 = ActorRole.Background;
                                actorRole = actorRole2;
                            }
                        }
                        voiceActors = characterEdge.getVoiceActors();
                        if (voiceActors != null) {
                            it = voiceActors.iterator();
                            if (it.hasNext()) {
                                staff = (Staff) it.next();
                                name = staff.getName();
                                if (name != null) {
                                    name2 = staff.getName();
                                    if (name2 != null) {
                                        full2 = name2.getFull();
                                    } else {
                                        full2 = null;
                                    }
                                    if (full2 == null) {
                                        name3 = staff.getName();
                                        if (name3 != null) {
                                            full2 = name3.getNative();
                                        } else {
                                            full2 = null;
                                        }
                                        if (full2 == null) {
                                            actorData = null;
                                        }
                                    }
                                } else {
                                    name2 = staff.getName();
                                    if (name2 != null) {
                                        full2 = name2.getFull();
                                    } else {
                                        full2 = null;
                                    }
                                    if (full2 == null) {
                                        name3 = staff.getName();
                                        if (name3 != null) {
                                            full2 = name3.getNative();
                                        } else {
                                            full2 = null;
                                        }
                                        if (full2 == null) {
                                            actorData = null;
                                        }
                                    }
                                }
                                image3 = staff.getImage();
                                if (image3 != null) {
                                    image4 = staff.getImage();
                                    if (image4 != null) {
                                        medium2 = image4.getMedium();
                                    } else {
                                        medium2 = null;
                                    }
                                } else {
                                    image4 = staff.getImage();
                                    if (image4 != null) {
                                        medium2 = image4.getMedium();
                                    } else {
                                        medium2 = null;
                                    }
                                }
                                actor2 = new Actor(full2, medium2);
                            } else {
                                actor2 = null;
                            }
                            actor = actor2;
                        } else {
                            actor = null;
                        }
                        actorData = new ActorData(actor5, actorRole, null, actor, 4, null);
                    }
                } else {
                    actorData = null;
                }
                if (actorData != null) {
                    arrayList3.add(actorData);
                }
            }
            arrayList = arrayList3;
        }
        Score scoreFrom100 = Score.INSTANCE.from100(media.getAverageScore());
        RecommendationConnection recommendations = media.getRecommendations();
        if (recommendations == null || (edges = recommendations.getEdges()) == null) {
            arrayList2 = null;
        } else {
            ArrayList arrayList4 = new ArrayList();
            Iterator<T> it2 = edges.iterator();
            while (it2.hasNext()) {
                RecommendedMedia mediaRecommendation = ((RecommendationEdge) it2.next()).getNode().getMediaRecommendation();
                if (mediaRecommendation == null || (title = mediaRecommendation.getTitle()) == null || (userPreferred = title.getUserPreferred()) == null) {
                    syncSearchResult = null;
                } else {
                    String name7 = getName();
                    Integer id = mediaRecommendation.getId();
                    if (id == null || (strValueOf = String.valueOf(id.intValue())) == null) {
                        syncSearchResult = null;
                    } else {
                        String urlFromId = getUrlFromId(mediaRecommendation.getId().intValue());
                        MediaCoverImage coverImage = mediaRecommendation.getCoverImage();
                        if (coverImage == null || (large = coverImage.getExtraLarge()) == null) {
                            MediaCoverImage coverImage2 = mediaRecommendation.getCoverImage();
                            large = coverImage2 != null ? coverImage2.getLarge() : null;
                            if (large != null) {
                                str3 = large;
                            } else {
                                MediaCoverImage coverImage3 = mediaRecommendation.getCoverImage();
                                if (coverImage3 != null) {
                                    large = coverImage3.getMedium();
                                    str3 = large;
                                } else {
                                    str3 = null;
                                }
                            }
                        } else {
                            str3 = large;
                        }
                        syncSearchResult = new SyncAPI.SyncSearchResult(userPreferred, name7, strValueOf, urlFromId, str3, null, null, null, null, null, 992, null);
                    }
                }
                if (syncSearchResult != null) {
                    arrayList4.add(syncSearchResult);
                }
            }
            arrayList2 = arrayList4;
        }
        MediaTrailer trailer = media.getTrailer();
        if (trailer == null || (site = trailer.getSite()) == null) {
            string = null;
        } else {
            String lowerCase = site.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                string = StringsKt.trim((CharSequence) lowerCase).toString();
            } else {
                string = null;
            }
        }
        return new SyncAPI.SyncResult(strValueOf2, episodes, userPreferred2, scoreFrom100, null, description, null, nextAiring, null, null, synonyms, Intrinsics.areEqual(string, "youtube") ? CollectionsKt.listOf("https://www.youtube.com/watch?v=" + media.getTrailer().getId()) : null, boolIsAdult, null, null, null, null, arrayList2, null, null, arrayList, 910160, null);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object status(AuthData authData, String str, Continuation<? super SyncAPI.AbstractSyncStatus> continuation) throws JsonProcessingException {
        C07781 c07781;
        if (continuation instanceof C07781) {
            c07781 = (C07781) continuation;
            if ((c07781.label & Integer.MIN_VALUE) != 0) {
                c07781.label -= Integer.MIN_VALUE;
            } else {
                c07781 = new C07781(continuation);
            }
        } else {
            c07781 = new C07781(continuation);
        }
        Object dataAboutId = c07781.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07781.label;
        if (i == 0) {
            ResultKt.throwOnFailure(dataAboutId);
            Integer intOrNull = StringsKt.toIntOrNull(str);
            if (intOrNull != null) {
                int iIntValue = intOrNull.intValue();
                if (authData == null) {
                    return null;
                }
                c07781.L$0 = SpillingKt.nullOutSpilledVariable(authData);
                c07781.L$1 = SpillingKt.nullOutSpilledVariable(str);
                c07781.I$0 = iIntValue;
                c07781.label = 1;
                dataAboutId = getDataAboutId(authData, iIntValue, c07781);
                if (dataAboutId == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return null;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = c07781.I$0;
        ResultKt.throwOnFailure(dataAboutId);
        AniListTitleHolder aniListTitleHolder = (AniListTitleHolder) dataAboutId;
        if (aniListTitleHolder == null) {
            return null;
        }
        Score scoreFrom100 = Score.INSTANCE.from100(aniListTitleHolder.getScore());
        Integer progress = aniListTitleHolder.getProgress();
        SyncWatchType.Companion companion = SyncWatchType.INSTANCE;
        Companion.AniListStatusType type = aniListTitleHolder.getType();
        if (type != null) {
            return new SyncAPI.SyncStatus(companion.fromInternalId(Boxing.boxInt(type.getValue())), scoreFrom100, progress, aniListTitleHolder.isFavourite(), aniListTitleHolder.getEpisodes());
        }
        return null;
    }

    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object updateStatus(AuthData authData, String str, SyncAPI.AbstractSyncStatus abstractSyncStatus, Continuation<? super Boolean> continuation) {
        if (authData == null) {
            return Boxing.boxBoolean(false);
        }
        Integer intOrNull = StringsKt.toIntOrNull(str);
        if (intOrNull != null) {
            return postDataAboutId(authData, intOrNull.intValue(), INSTANCE.fromIntToAnimeStatus(abstractSyncStatus.getStatus().getInternalId()), abstractSyncStatus.getScore(), abstractSyncStatus.getWatchedEpisodes(), continuation);
        }
        return Boxing.boxBoolean(false);
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0002J\u0018\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\f\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010\u000fJ,\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u0086@¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\bJ\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion;", "", "<init>", "()V", "MAX_STALE", "", "aniListStatusString", "", "", "[Ljava/lang/String;", "ANILIST_CACHED_LIST", "fixName", "name", "searchShows", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchRoot;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getShowId", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia;", "malId", "year", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fromIntToAnimeStatus", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;", "inp", "convertAniListStringToStatus", TypedValues.Custom.S_STRING, "getSeason", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonResponse;", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AniListStatusType", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final String fixName(String name) {
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String lowerCase = name.toLowerCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return new Regex("[^a-zA-Z0-9]").replace(StringsKt.replace$default(lowerCase, Stream.ID_UNKNOWN, "", false, 4, (Object) null), "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0016  */
        public final Object searchShows(String str, Continuation<? super GetSearchRoot> continuation) {
            AniListApi$Companion$searchShows$1 aniListApi$Companion$searchShows$1;
            List list;
            Object objM8936constructorimpl;
            if (continuation instanceof AniListApi$Companion$searchShows$1) {
                aniListApi$Companion$searchShows$1 = (AniListApi$Companion$searchShows$1) continuation;
                if ((aniListApi$Companion$searchShows$1.label & Integer.MIN_VALUE) != 0) {
                    aniListApi$Companion$searchShows$1.label -= Integer.MIN_VALUE;
                } else {
                    aniListApi$Companion$searchShows$1 = new AniListApi$Companion$searchShows$1(this, continuation);
                }
            } else {
                aniListApi$Companion$searchShows$1 = new AniListApi$Companion$searchShows$1(this, continuation);
            }
            Object obj = aniListApi$Companion$searchShows$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = aniListApi$Companion$searchShows$1.label;
            List list2 = null;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Map mapMapOf = MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, "\n                query ($id: Int, $page: Int, $search: String, $type: MediaType) {\n                    Page (page: $page, perPage: 10) {\n                        media (id: $id, search: $search, type: $type) {\n                            id\n                            idMal\n                            seasonYear\n                            startDate { year month day }\n                            title {\n                                romaji\n                            }\n                            averageScore\n                            meanScore\n                            nextAiringEpisode {\n                                timeUntilAiring\n                                episode\n                            }\n                            trailer { id site thumbnail }\n                            bannerImage\n                            recommendations {\n                                nodes {\n                                    id\n                                    mediaRecommendation {\n                                        id\n                                        title {\n                                            english\n                                            romaji\n                                        }\n                                        idMal\n                                        coverImage { medium large extraLarge }\n                                        averageScore\n                                    }\n                                }\n                            }\n                            relations {\n                                edges {\n                                    id\n                                    relationType(version: 2)\n                                    node {\n                                        format\n                                        id\n                                        idMal\n                                        coverImage { medium large extraLarge }\n                                        averageScore\n                                        title {\n                                            english\n                                            romaji\n                                        }\n                                    }\n                                }\n                            }\n                        }\n                    }\n                }\n                "), TuplesKt.to("variables", AppUtils.INSTANCE.toJson(new Variables(str, 1, "ANIME"))));
                    Requests app2 = MainActivityKt.getApp();
                    aniListApi$Companion$searchShows$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                    aniListApi$Companion$searchShows$1.L$1 = SpillingKt.nullOutSpilledVariable("\n                query ($id: Int, $page: Int, $search: String, $type: MediaType) {\n                    Page (page: $page, perPage: 10) {\n                        media (id: $id, search: $search, type: $type) {\n                            id\n                            idMal\n                            seasonYear\n                            startDate { year month day }\n                            title {\n                                romaji\n                            }\n                            averageScore\n                            meanScore\n                            nextAiringEpisode {\n                                timeUntilAiring\n                                episode\n                            }\n                            trailer { id site thumbnail }\n                            bannerImage\n                            recommendations {\n                                nodes {\n                                    id\n                                    mediaRecommendation {\n                                        id\n                                        title {\n                                            english\n                                            romaji\n                                        }\n                                        idMal\n                                        coverImage { medium large extraLarge }\n                                        averageScore\n                                    }\n                                }\n                            }\n                            relations {\n                                edges {\n                                    id\n                                    relationType(version: 2)\n                                    node {\n                                        format\n                                        id\n                                        idMal\n                                        coverImage { medium large extraLarge }\n                                        averageScore\n                                        title {\n                                            english\n                                            romaji\n                                        }\n                                    }\n                                }\n                            }\n                        }\n                    }\n                }\n                ");
                    aniListApi$Companion$searchShows$1.L$2 = SpillingKt.nullOutSpilledVariable(mapMapOf);
                    aniListApi$Companion$searchShows$1.label = 1;
                    AniListApi$Companion$searchShows$1 aniListApi$Companion$searchShows$2 = aniListApi$Companion$searchShows$1;
                    list = null;
                    try {
                        Object objPost$default = Requests.post$default(app2, "https://graphql.anilist.co/", null, null, null, null, mapMapOf, null, null, null, false, 0, null, 5000L, null, false, null, aniListApi$Companion$searchShows$2, 61406, null);
                        if (objPost$default == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = objPost$default;
                    } catch (Exception e) {
                        e = e;
                        ArchComponentExtKt.logError(e);
                        return list;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    list = null;
                }
                list2 = null;
                String strReplace$default = StringsKt.replace$default(((NiceResponse) obj).getText(), "\\", "", false, 4, (Object) null);
                AppUtils appUtils = AppUtils.INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(GetSearchRoot.INSTANCE.serializer());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(GetSearchRoot.class), list, 2, list));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                    }
                }
                Object obj2 = objM8936constructorimpl;
                if (Result.m8942isFailureimpl(obj2)) {
                    obj2 = list;
                }
                KSerializer kSerializer = (KSerializer) obj2;
                if (kSerializer != null) {
                    try {
                        return MainAPIKt.getJson().decodeFromString(kSerializer, strReplace$default);
                    } catch (SerializationException e2) {
                        ArchComponentExtKt.logError(e2);
                    } catch (Throwable unused) {
                    }
                }
                return MainAPIKt.getMapper().readValue(strReplace$default, new TypeReference<GetSearchRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Companion$searchShows$$inlined$parseJson$1
                });
            } catch (Exception e3) {
                e = e3;
                list = list2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:7:0x001a  */
        public final Object getShowId(String str, String str2, Integer num, Continuation<? super GetSearchMedia> continuation) {
            AniListApi$Companion$getShowId$1 aniListApi$Companion$getShowId$1;
            String str3;
            Integer num2;
            ArrayList<GetSearchMedia> arrayList;
            GetSearchPage data;
            GetSearchData page;
            List<GetSearchMedia> media;
            GetSearchPage data2;
            GetSearchData page2;
            List<GetSearchMedia> media2;
            Object next;
            String str4 = str2;
            if (continuation instanceof AniListApi$Companion$getShowId$1) {
                aniListApi$Companion$getShowId$1 = (AniListApi$Companion$getShowId$1) continuation;
                if ((aniListApi$Companion$getShowId$1.label & Integer.MIN_VALUE) != 0) {
                    aniListApi$Companion$getShowId$1.label -= Integer.MIN_VALUE;
                } else {
                    aniListApi$Companion$getShowId$1 = new AniListApi$Companion$getShowId$1(this, continuation);
                }
            } else {
                aniListApi$Companion$getShowId$1 = new AniListApi$Companion$getShowId$1(this, continuation);
            }
            Object objSearchShows = aniListApi$Companion$getShowId$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = aniListApi$Companion$getShowId$1.label;
            if (i == 0) {
                ResultKt.throwOnFailure(objSearchShows);
                List listListOf = CollectionsKt.listOf((Object[]) new String[]{"TV Dubbed", "(Dub)", "Subbed", "(TV)", "(Uncensored)", "(Censored)", "(\\d+)"});
                Regex regex = new Regex(" (" + StringsKt.replace$default(StringsKt.replace$default(CollectionsKt.joinToString$default(listListOf, "|", null, null, 0, null, null, 62, null), "(", "\\(", false, 4, (Object) null), ")", "\\)", false, 4, (Object) null) + ')');
                String strReplace = regex.replace(str4, "");
                aniListApi$Companion$getShowId$1.L$0 = str;
                aniListApi$Companion$getShowId$1.L$1 = str4;
                aniListApi$Companion$getShowId$1.L$2 = num;
                aniListApi$Companion$getShowId$1.L$3 = SpillingKt.nullOutSpilledVariable(listListOf);
                aniListApi$Companion$getShowId$1.L$4 = SpillingKt.nullOutSpilledVariable(regex);
                aniListApi$Companion$getShowId$1.label = 1;
                objSearchShows = searchShows(strReplace, aniListApi$Companion$getShowId$1);
                if (objSearchShows == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str3 = str;
                num2 = num;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                num2 = (Integer) aniListApi$Companion$getShowId$1.L$2;
                str4 = (String) aniListApi$Companion$getShowId$1.L$1;
                str3 = (String) aniListApi$Companion$getShowId$1.L$0;
                ResultKt.throwOnFailure(objSearchShows);
            }
            GetSearchRoot getSearchRoot = (GetSearchRoot) objSearchShows;
            if (getSearchRoot != null && (data2 = getSearchRoot.getData()) != null && (page2 = data2.getPage()) != null && (media2 = page2.getMedia()) != null) {
                Iterator<T> it = media2.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(str3 == null ? "NONE" : str3, String.valueOf(((GetSearchMedia) next).getIdMal())));
                GetSearchMedia getSearchMedia = (GetSearchMedia) next;
                if (getSearchMedia != null) {
                    return getSearchMedia;
                }
            }
            if (getSearchRoot == null || (data = getSearchRoot.getData()) == null || (page = data.getPage()) == null || (media = page.getMedia()) == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : media) {
                    String year = ((GetSearchMedia) obj).getStartDate().getYear();
                    if (year == null) {
                        year = String.valueOf(num2);
                    }
                    if (Intrinsics.areEqual(year, String.valueOf(num2)) || num2 == null) {
                        arrayList2.add(obj);
                    }
                }
                arrayList = arrayList2;
            }
            if (arrayList != null) {
                for (GetSearchMedia getSearchMedia2 : arrayList) {
                    String romaji = getSearchMedia2.getTitle().getRomaji();
                    if (romaji != null && Intrinsics.areEqual(AniListApi.INSTANCE.fixName(romaji), AniListApi.INSTANCE.fixName(str4))) {
                        return getSearchMedia2;
                    }
                }
            }
            if (arrayList != null) {
                return (GetSearchMedia) CollectionsKt.firstOrNull((List) arrayList);
            }
            return null;
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;", "", ES6Iterator.VALUE_PROPERTY, "", "stringRes", "<init>", "(Ljava/lang/String;III)V", "getValue", "()I", "setValue", "(I)V", "getStringRes", "Watching", "Completed", "Paused", "Dropped", "Planning", "ReWatching", "None", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public enum AniListStatusType {
            Watching(0, R.string.type_watching),
            Completed(1, R.string.type_completed),
            Paused(2, R.string.type_on_hold),
            Dropped(3, R.string.type_dropped),
            Planning(4, R.string.type_plan_to_watch),
            ReWatching(5, R.string.type_re_watching),
            None(-1, R.string.none);

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
            private final int stringRes;
            private int value;

            public static EnumEntries<AniListStatusType> getEntries() {
                return $ENTRIES;
            }

            AniListStatusType(int i, int i2) {
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

        public final AniListStatusType fromIntToAnimeStatus(int inp) {
            switch (inp) {
                case -1:
                    return AniListStatusType.None;
                case 0:
                    return AniListStatusType.Watching;
                case 1:
                    return AniListStatusType.Completed;
                case 2:
                    return AniListStatusType.Paused;
                case 3:
                    return AniListStatusType.Dropped;
                case 4:
                    return AniListStatusType.Planning;
                case 5:
                    return AniListStatusType.ReWatching;
                default:
                    return AniListStatusType.None;
            }
        }

        public final AniListStatusType convertAniListStringToStatus(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return fromIntToAnimeStatus(ArraysKt.indexOf(AniListApi.aniListStatusString, string));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code duplicated, block: B:7:0x0018  */
        public final Object getSeason(int i, Continuation<? super SeasonResponse> continuation) throws ErrorLoadingException {
            AniListApi$Companion$getSeason$1 aniListApi$Companion$getSeason$1;
            Object objM8936constructorimpl;
            Object objDecodeFromString;
            if (continuation instanceof AniListApi$Companion$getSeason$1) {
                aniListApi$Companion$getSeason$1 = (AniListApi$Companion$getSeason$1) continuation;
                if ((aniListApi$Companion$getSeason$1.label & Integer.MIN_VALUE) != 0) {
                    aniListApi$Companion$getSeason$1.label -= Integer.MIN_VALUE;
                } else {
                    aniListApi$Companion$getSeason$1 = new AniListApi$Companion$getSeason$1(this, continuation);
                }
            } else {
                aniListApi$Companion$getSeason$1 = new AniListApi$Companion$getSeason$1(this, continuation);
            }
            Object objPost$default = aniListApi$Companion$getSeason$1.result;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = aniListApi$Companion$getSeason$1.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(objPost$default);
                String str = "\n               query ($id: Int = " + i + ") {\n                   Media (id: $id, type: ANIME) {\n                       id\n                       idMal\n                       coverImage {\n                           extraLarge\n                           large\n                           medium\n                           color\n                       }\n                       title {\n                           romaji\n                           english\n                           native\n                           userPreferred\n                       }\n                       duration\n                       episodes\n                       genres\n                       synonyms\n                       averageScore\n                       isAdult\n                       description(asHtml: false)\n                       characters(sort: ROLE page: 1 perPage: 20) {\n                           edges {\n                               role\n                               voiceActors {\n                                   name {\n                                       userPreferred\n                                       full\n                                       native\n                                   }\n                                   age\n                                   image {\n                                       large\n                                       medium\n                                   }\n                               }\n                               node {\n                                   name {\n                                       userPreferred\n                                       full\n                                       native\n                                   }\n                                   age\n                                   image {\n                                       large\n                                       medium\n                                   }\n                               }\n                           }\n                       }\n                       trailer {\n                           id\n                           site\n                           thumbnail\n                       }\n                       relations {\n                           edges {\n                                id\n                                relationType(version: 2)\n                                node {\n                                     id\n                                     coverImage {\n                                         extraLarge\n                                         large\n                                         medium\n                                         color\n                                     }\n                                }\n                           }\n                       }\n                       recommendations {\n                           edges {\n                               node {\n                                   mediaRecommendation {\n                                       id\n                                       coverImage {\n                                           extraLarge\n                                           large\n                                           medium\n                                           color\n                                       }\n                                       title {\n                                           romaji\n                                           english\n                                           native\n                                           userPreferred\n                                       }\n                                   }\n                               }\n                           }\n                       }\n                       nextAiringEpisode {\n                           timeUntilAiring\n                           episode\n                       }\n                       format\n                   }\n               }\n        ";
                Requests app2 = MainActivityKt.getApp();
                Map mapMapOf = MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, str));
                aniListApi$Companion$getSeason$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                aniListApi$Companion$getSeason$1.I$0 = i;
                aniListApi$Companion$getSeason$1.label = 1;
                objPost$default = Requests.post$default(app2, "https://graphql.anilist.co", null, null, null, null, mapMapOf, null, null, null, false, 0, null, 0L, null, false, null, aniListApi$Companion$getSeason$1, 64478, null);
                if (objPost$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = aniListApi$Companion$getSeason$1.I$0;
                ResultKt.throwOnFailure(objPost$default);
            }
            String text = ((NiceResponse) objPost$default).getText();
            AppUtils appUtils = AppUtils.INSTANCE;
            Object obj = null;
            try {
                if (text != null) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(SeasonResponse.INSTANCE.serializer());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(SeasonResponse.class), null, 2, null));
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
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, text);
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<SeasonResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Companion$getSeason$$inlined$tryParseJson$1
                            });
                        } catch (Throwable unused) {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<SeasonResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Companion$getSeason$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        objDecodeFromString = MainAPIKt.getMapper().readValue(text, new TypeReference<SeasonResponse>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Companion$getSeason$$inlined$tryParseJson$1
                        });
                    }
                    obj = objDecodeFromString;
                }
            } catch (Exception unused2) {
            }
            SeasonResponse seasonResponse = (SeasonResponse) obj;
            if (seasonResponse != null) {
                return seasonResponse;
            }
            throw new ErrorLoadingException("Error parsing " + text);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getDataAboutId(AuthData authData, int i, Continuation<? super AniListTitleHolder> continuation) throws JsonProcessingException {
        C07701 c07701;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        if (continuation instanceof C07701) {
            c07701 = (C07701) continuation;
            if ((c07701.label & Integer.MIN_VALUE) != 0) {
                c07701.label -= Integer.MIN_VALUE;
            } else {
                c07701 = new C07701(continuation);
            }
        } else {
            c07701 = new C07701(continuation);
        }
        Object objPostApi = c07701.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07701.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objPostApi);
            String str = "query ($id: Int = " + i + ") { # Define which variables will be used in the query (id)\n                Media (id: $id, type: ANIME) { # Insert our variables into the query arguments (id) (type: ANIME is hard-coded in the query)\n                    id\n                    episodes\n                    isFavourite\n                    mediaListEntry {\n                        progress\n                        status\n                        score (format: POINT_100)\n                    }\n                    title {\n                        english\n                        romaji\n                    }\n                }\n            }";
            AuthToken token = authData.getToken();
            c07701.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07701.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07701.I$0 = i;
            c07701.label = 1;
            objPostApi = postApi(token, str, true, c07701);
            if (objPostApi == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = c07701.I$0;
            ResultKt.throwOnFailure(objPostApi);
        }
        String str2 = (String) objPostApi;
        AppUtils appUtils = AppUtils.INSTANCE;
        if (str2 == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(GetDataRoot.INSTANCE.serializer());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(GetDataRoot.class), null, 2, null));
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
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<GetDataRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getDataAboutId$$inlined$parseJson$1
                });
            } catch (Throwable unused) {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<GetDataRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getDataAboutId$$inlined$parseJson$1
                });
            }
        } else {
            objDecodeFromString = MainAPIKt.getMapper().readValue(str2, new TypeReference<GetDataRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getDataAboutId$$inlined$parseJson$1
            });
        }
        GetDataData data = ((GetDataRoot) objDecodeFromString).getData();
        GetDataMedia media = data != null ? data.getMedia() : null;
        if ((media != null ? media.getMediaListEntry() : null) != null) {
            return new AniListTitleHolder(media.getTitle(), media.isFavourite(), Boxing.boxInt(i), media.getMediaListEntry().getProgress(), media.getEpisodes(), media.getMediaListEntry().getScore(), INSTANCE.fromIntToAnimeStatus(ArraysKt.indexOf(aniListStatusString, media.getMediaListEntry().getStatus())));
        }
        return new AniListTitleHolder(media != null ? media.getTitle() : null, media != null ? media.isFavourite() : null, Boxing.boxInt(i), Boxing.boxInt(0), media != null ? media.getEpisodes() : null, Boxing.boxInt(0), Companion.AniListStatusType.None);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object postApi(AuthToken authToken, String str, boolean z, Continuation<? super String> continuation) {
        C07751 c07751;
        if (continuation instanceof C07751) {
            c07751 = (C07751) continuation;
            if ((c07751.label & Integer.MIN_VALUE) != 0) {
                c07751.label -= Integer.MIN_VALUE;
            } else {
                c07751 = new C07751(continuation);
            }
        } else {
            c07751 = new C07751(continuation);
        }
        Object obj = c07751.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07751.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Requests app2 = MainActivityKt.getApp();
            Pair[] pairArr = new Pair[2];
            StringBuilder sb = new StringBuilder("Bearer ");
            String accessToken = authToken.getAccessToken();
            if (accessToken == null) {
                return null;
            }
            sb.append(accessToken);
            pairArr[0] = TuplesKt.to("Authorization", sb.toString());
            pairArr[1] = TuplesKt.to("Cache-Control", z ? "max-stale=600" : "no-cache");
            Map mapMapOf = MapsKt.mapOf(pairArr);
            Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to(SearchIntents.EXTRA_QUERY, URLEncoder.encode(str, "UTF-8")));
            c07751.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07751.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07751.Z$0 = z;
            c07751.label = 1;
            Object objPost$default = Requests.post$default(app2, "https://graphql.anilist.co/", mapMapOf, null, null, null, mapMapOf2, null, null, null, false, 0, null, 5L, null, false, null, c07751, 60380, null);
            if (objPost$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            obj = objPost$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            boolean z2 = c07751.Z$0;
            ResultKt.throwOnFailure(obj);
        }
        return StringsKt.replace$default(((NiceResponse) obj).getText(), "\\/", "/", false, 4, (Object) null);
    }

    static /* synthetic */ Object postApi$default(AniListApi aniListApi, AuthToken authToken, String str, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return aniListApi.postApi(authToken, str, z, continuation);
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bB7\b\u0010\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J'\u0010\u0019\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\b&R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0010¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Variables;", "", "search", "", "page", "", "type", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getSearch$annotations", "()V", "getSearch", "()Ljava/lang/String;", "getPage$annotations", "getPage", "()I", "getType$annotations", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Variables {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("page")
        private final int page;

        @JsonProperty("search")
        private final String search;

        @JsonProperty("type")
        private final String type;

        public static /* synthetic */ Variables copy$default(Variables variables, String str, int i, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = variables.search;
            }
            if ((i2 & 2) != 0) {
                i = variables.page;
            }
            if ((i2 & 4) != 0) {
                str2 = variables.type;
            }
            return variables.copy(str, i, str2);
        }

        @SerialName("page")
        public static /* synthetic */ void getPage$annotations() {
        }

        @SerialName("search")
        public static /* synthetic */ void getSearch$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSearch() {
            return this.search;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getPage() {
            return this.page;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final Variables copy(@JsonProperty("search") String search, @JsonProperty("page") int page, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(search, "search");
            Intrinsics.checkNotNullParameter(type, "type");
            return new Variables(search, page, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Variables)) {
                return false;
            }
            Variables variables = (Variables) other;
            return Intrinsics.areEqual(this.search, variables.search) && this.page == variables.page && Intrinsics.areEqual(this.type, variables.type);
        }

        public int hashCode() {
            return (((this.search.hashCode() * 31) + this.page) * 31) + this.type.hashCode();
        }

        public String toString() {
            return "Variables(search=" + this.search + ", page=" + this.page + ", type=" + this.type + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Variables$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Variables;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Variables> serializer() {
                return AniListApi$Variables$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Variables(int i, String str, int i2, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$Variables$$serializer.INSTANCE.getDescriptor());
            }
            this.search = str;
            this.page = i2;
            this.type = str2;
        }

        public Variables(@JsonProperty("search") String search, @JsonProperty("page") int i, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(search, "search");
            Intrinsics.checkNotNullParameter(type, "type");
            this.search = search;
            this.page = i;
            this.type = type;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Variables self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.search);
            output.encodeIntElement(serialDesc, 1, self.page);
            output.encodeStringElement(serialDesc, 2, self.type);
        }

        public final String getSearch() {
            return this.search;
        }

        public final int getPage() {
            return this.page;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 62\u00020\u0001:\u000256BA\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bBK\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010#\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019JH\u0010%\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010&J\u0014\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004J%\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\b4R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u001dR \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001f\u0010\u0019¨\u00067"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;", "", "id", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "idMal", "coverImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "averageScore", "<init>", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "getIdMal$annotations", "getIdMal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCoverImage$annotations", "getCoverImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "getAverageScore$annotations", "getAverageScore", "component1", "component2", "component3", "component4", "component5", "copy", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaRecommendation {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("averageScore")
        private final Integer averageScore;

        @JsonProperty("coverImage")
        private final CoverImage coverImage;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("idMal")
        private final Integer idMal;

        @JsonProperty("title")
        private final Title title;

        public static /* synthetic */ MediaRecommendation copy$default(MediaRecommendation mediaRecommendation, int i, Title title, Integer num, CoverImage coverImage, Integer num2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = mediaRecommendation.id;
            }
            if ((i2 & 2) != 0) {
                title = mediaRecommendation.title;
            }
            if ((i2 & 4) != 0) {
                num = mediaRecommendation.idMal;
            }
            if ((i2 & 8) != 0) {
                coverImage = mediaRecommendation.coverImage;
            }
            if ((i2 & 16) != 0) {
                num2 = mediaRecommendation.averageScore;
            }
            Integer num3 = num2;
            Integer num4 = num;
            return mediaRecommendation.copy(i, title, num4, coverImage, num3);
        }

        @SerialName("averageScore")
        public static /* synthetic */ void getAverageScore$annotations() {
        }

        @SerialName("coverImage")
        public static /* synthetic */ void getCoverImage$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("idMal")
        public static /* synthetic */ void getIdMal$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Title getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getIdMal() {
            return this.idMal;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final CoverImage getCoverImage() {
            return this.coverImage;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getAverageScore() {
            return this.averageScore;
        }

        public final MediaRecommendation copy(@JsonProperty("id") int id, @JsonProperty("title") Title title, @JsonProperty("idMal") Integer idMal, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("averageScore") Integer averageScore) {
            return new MediaRecommendation(id, title, idMal, coverImage, averageScore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaRecommendation)) {
                return false;
            }
            MediaRecommendation mediaRecommendation = (MediaRecommendation) other;
            return this.id == mediaRecommendation.id && Intrinsics.areEqual(this.title, mediaRecommendation.title) && Intrinsics.areEqual(this.idMal, mediaRecommendation.idMal) && Intrinsics.areEqual(this.coverImage, mediaRecommendation.coverImage) && Intrinsics.areEqual(this.averageScore, mediaRecommendation.averageScore);
        }

        public int hashCode() {
            int i = this.id * 31;
            Title title = this.title;
            int iHashCode = (i + (title == null ? 0 : title.hashCode())) * 31;
            Integer num = this.idMal;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            CoverImage coverImage = this.coverImage;
            int iHashCode3 = (iHashCode2 + (coverImage == null ? 0 : coverImage.hashCode())) * 31;
            Integer num2 = this.averageScore;
            return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "MediaRecommendation(id=" + this.id + ", title=" + this.title + ", idMal=" + this.idMal + ", coverImage=" + this.coverImage + ", averageScore=" + this.averageScore + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaRecommendation> serializer() {
                return AniListApi$MediaRecommendation$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaRecommendation(int i, int i2, Title title, Integer num, CoverImage coverImage, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, AniListApi$MediaRecommendation$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.title = title;
            this.idMal = num;
            this.coverImage = coverImage;
            this.averageScore = num2;
        }

        public MediaRecommendation(@JsonProperty("id") int i, @JsonProperty("title") Title title, @JsonProperty("idMal") Integer num, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("averageScore") Integer num2) {
            this.id = i;
            this.title = title;
            this.idMal = num;
            this.coverImage = coverImage;
            this.averageScore = num2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaRecommendation self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$Title$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.idMal);
            output.encodeNullableSerializableElement(serialDesc, 3, AniListApi$CoverImage$$serializer.INSTANCE, self.coverImage);
            output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.averageScore);
        }

        public final int getId() {
            return this.id;
        }

        public final Title getTitle() {
            return this.title;
        }

        public final Integer getIdMal() {
            return this.idMal;
        }

        public final CoverImage getCoverImage() {
            return this.coverImage;
        }

        public final Integer getAverageScore() {
            return this.averageScore;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$FullAnilistList;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class FullAnilistList {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final Data data;

        public static /* synthetic */ FullAnilistList copy$default(FullAnilistList fullAnilistList, Data data, int i, Object obj) {
            if ((i & 1) != 0) {
                data = fullAnilistList.data;
            }
            return fullAnilistList.copy(data);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Data getData() {
            return this.data;
        }

        public final FullAnilistList copy(@JsonProperty("data") Data data) {
            return new FullAnilistList(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FullAnilistList) && Intrinsics.areEqual(this.data, ((FullAnilistList) other).data);
        }

        public int hashCode() {
            Data data = this.data;
            if (data == null) {
                return 0;
            }
            return data.hashCode();
        }

        public String toString() {
            return "FullAnilistList(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$FullAnilistList$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$FullAnilistList;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<FullAnilistList> serializer() {
                return AniListApi$FullAnilistList$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ FullAnilistList(int i, Data data, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$FullAnilistList$$serializer.INSTANCE.getDescriptor());
            }
            this.data = data;
        }

        public FullAnilistList(@JsonProperty("data") Data data) {
            this.data = data;
        }

        public final Data getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007B3\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000fR\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\r\u001a\u0004\b\u0013\u0010\u000f¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt;", "", "year", "", "month", "day", "<init>", "(III)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getYear$annotations", "()V", "getYear", "()I", "getMonth$annotations", "getMonth", "getDay$annotations", "getDay", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CompletedAt {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("day")
        private final int day;

        @JsonProperty("month")
        private final int month;

        @JsonProperty("year")
        private final int year;

        public static /* synthetic */ CompletedAt copy$default(CompletedAt completedAt, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = completedAt.year;
            }
            if ((i4 & 2) != 0) {
                i2 = completedAt.month;
            }
            if ((i4 & 4) != 0) {
                i3 = completedAt.day;
            }
            return completedAt.copy(i, i2, i3);
        }

        @SerialName("day")
        public static /* synthetic */ void getDay$annotations() {
        }

        @SerialName("month")
        public static /* synthetic */ void getMonth$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMonth() {
            return this.month;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getDay() {
            return this.day;
        }

        public final CompletedAt copy(@JsonProperty("year") int year, @JsonProperty("month") int month, @JsonProperty("day") int day) {
            return new CompletedAt(year, month, day);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CompletedAt)) {
                return false;
            }
            CompletedAt completedAt = (CompletedAt) other;
            return this.year == completedAt.year && this.month == completedAt.month && this.day == completedAt.day;
        }

        public int hashCode() {
            return (((this.year * 31) + this.month) * 31) + this.day;
        }

        public String toString() {
            return "CompletedAt(year=" + this.year + ", month=" + this.month + ", day=" + this.day + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CompletedAt> serializer() {
                return AniListApi$CompletedAt$$serializer.INSTANCE;
            }
        }

        public CompletedAt(@JsonProperty("year") int i, @JsonProperty("month") int i2, @JsonProperty("day") int i3) {
            this.year = i;
            this.month = i2;
            this.day = i3;
        }

        public /* synthetic */ CompletedAt(int i, int i2, int i3, int i4, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$CompletedAt$$serializer.INSTANCE.getDescriptor());
            }
            this.year = i2;
            this.month = i3;
            this.day = i4;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CompletedAt self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.year);
            output.encodeIntElement(serialDesc, 1, self.month);
            output.encodeIntElement(serialDesc, 2, self.day);
        }

        public final int getYear() {
            return this.year;
        }

        public final int getMonth() {
            return this.month;
        }

        public final int getDay() {
            return this.day;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;", "", "year", "", "month", "day", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getYear$annotations", "()V", "getYear", "()Ljava/lang/String;", "getMonth$annotations", "getMonth", "getDay$annotations", "getDay", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class StartedAt {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("day")
        private final String day;

        @JsonProperty("month")
        private final String month;

        @JsonProperty("year")
        private final String year;

        public static /* synthetic */ StartedAt copy$default(StartedAt startedAt, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = startedAt.year;
            }
            if ((i & 2) != 0) {
                str2 = startedAt.month;
            }
            if ((i & 4) != 0) {
                str3 = startedAt.day;
            }
            return startedAt.copy(str, str2, str3);
        }

        @SerialName("day")
        public static /* synthetic */ void getDay$annotations() {
        }

        @SerialName("month")
        public static /* synthetic */ void getMonth$annotations() {
        }

        @SerialName("year")
        public static /* synthetic */ void getYear$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMonth() {
            return this.month;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDay() {
            return this.day;
        }

        public final StartedAt copy(@JsonProperty("year") String year, @JsonProperty("month") String month, @JsonProperty("day") String day) {
            return new StartedAt(year, month, day);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StartedAt)) {
                return false;
            }
            StartedAt startedAt = (StartedAt) other;
            return Intrinsics.areEqual(this.year, startedAt.year) && Intrinsics.areEqual(this.month, startedAt.month) && Intrinsics.areEqual(this.day, startedAt.day);
        }

        public int hashCode() {
            String str = this.year;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.month;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.day;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "StartedAt(year=" + this.year + ", month=" + this.month + ", day=" + this.day + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<StartedAt> serializer() {
                return AniListApi$StartedAt$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ StartedAt(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$StartedAt$$serializer.INSTANCE.getDescriptor());
            }
            this.year = str;
            this.month = str2;
            this.day = str3;
        }

        public StartedAt(@JsonProperty("year") String str, @JsonProperty("month") String str2, @JsonProperty("day") String str3) {
            this.year = str;
            this.month = str2;
            this.day = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(StartedAt self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.year);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.month);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.day);
        }

        public final String getYear() {
            return this.year;
        }

        public final String getMonth() {
            return this.month;
        }

        public final String getDay() {
            return this.day;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "", "english", "", "romaji", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEnglish$annotations", "()V", "getEnglish", "()Ljava/lang/String;", "getRomaji$annotations", "getRomaji", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Title {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("english")
        private final String english;

        @JsonProperty("romaji")
        private final String romaji;

        public static /* synthetic */ Title copy$default(Title title, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = title.english;
            }
            if ((i & 2) != 0) {
                str2 = title.romaji;
            }
            return title.copy(str, str2);
        }

        @SerialName("english")
        public static /* synthetic */ void getEnglish$annotations() {
        }

        @SerialName("romaji")
        public static /* synthetic */ void getRomaji$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEnglish() {
            return this.english;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRomaji() {
            return this.romaji;
        }

        public final Title copy(@JsonProperty("english") String english, @JsonProperty("romaji") String romaji) {
            return new Title(english, romaji);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Title)) {
                return false;
            }
            Title title = (Title) other;
            return Intrinsics.areEqual(this.english, title.english) && Intrinsics.areEqual(this.romaji, title.romaji);
        }

        public int hashCode() {
            String str = this.english;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.romaji;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Title(english=" + this.english + ", romaji=" + this.romaji + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Title> serializer() {
                return AniListApi$Title$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Title(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$Title$$serializer.INSTANCE.getDescriptor());
            }
            this.english = str;
            this.romaji = str2;
        }

        public Title(@JsonProperty("english") String str, @JsonProperty("romaji") String str2) {
            this.english = str;
            this.romaji = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Title self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.english);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.romaji);
        }

        public final String getEnglish() {
            return this.english;
        }

        public final String getRomaji() {
            return this.romaji;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "", "medium", "", "large", "extraLarge", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMedium$annotations", "()V", "getMedium", "()Ljava/lang/String;", "getLarge$annotations", "getLarge", "getExtraLarge$annotations", "getExtraLarge", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CoverImage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("extraLarge")
        private final String extraLarge;

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ CoverImage copy$default(CoverImage coverImage, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = coverImage.medium;
            }
            if ((i & 2) != 0) {
                str2 = coverImage.large;
            }
            if ((i & 4) != 0) {
                str3 = coverImage.extraLarge;
            }
            return coverImage.copy(str, str2, str3);
        }

        @SerialName("extraLarge")
        public static /* synthetic */ void getExtraLarge$annotations() {
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

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getExtraLarge() {
            return this.extraLarge;
        }

        public final CoverImage copy(@JsonProperty("medium") String medium, @JsonProperty("large") String large, @JsonProperty("extraLarge") String extraLarge) {
            return new CoverImage(medium, large, extraLarge);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CoverImage)) {
                return false;
            }
            CoverImage coverImage = (CoverImage) other;
            return Intrinsics.areEqual(this.medium, coverImage.medium) && Intrinsics.areEqual(this.large, coverImage.large) && Intrinsics.areEqual(this.extraLarge, coverImage.extraLarge);
        }

        public int hashCode() {
            String str = this.medium;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.large;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.extraLarge;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "CoverImage(medium=" + this.medium + ", large=" + this.large + ", extraLarge=" + this.extraLarge + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CoverImage> serializer() {
                return AniListApi$CoverImage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CoverImage(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$CoverImage$$serializer.INSTANCE.getDescriptor());
            }
            this.medium = str;
            this.large = str2;
            this.extraLarge = str3;
        }

        public CoverImage(@JsonProperty("medium") String str, @JsonProperty("large") String str2, @JsonProperty("extraLarge") String str3) {
            this.medium = str;
            this.large = str2;
            this.extraLarge = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CoverImage self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.medium);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.large);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.extraLarge);
        }

        public final String getMedium() {
            return this.medium;
        }

        public final String getLarge() {
            return this.large;
        }

        public final String getExtraLarge() {
            return this.extraLarge;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 S2\u00020\u0001:\u0002RSB\u0085\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\t\u001a\u00020\u0003\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\r\u001a\u00020\u000e\u0012\u000e\b\u0001\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014B\u0089\u0001\b\u0010\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0013\u0010\u0018J\t\u00108\u001a\u00020\u0003HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u000bHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010@\u001a\u00020\u000eHÆ\u0003J\u000f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u008c\u0001\u0010C\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\t\u001a\u00020\u00032\b\b\u0003\u0010\n\u001a\u00020\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\r\u001a\u00020\u000e2\u000e\b\u0003\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00102\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u0010DJ\u0014\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010H\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010I\u001a\u00020\u0006HÖ\u0081\u0004J%\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u00002\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0001¢\u0006\u0002\bQR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010 \u0012\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u001a\u001a\u0004\b\"\u0010#R\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u001a\u001a\u0004\b%\u0010\u001cR\u001e\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001a\u001a\u0004\b'\u0010#R\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b(\u0010\u001a\u001a\u0004\b)\u0010\u001cR\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u001a\u001a\u0004\b+\u0010,R\u001e\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b-\u0010\u001a\u001a\u0004\b.\u0010#R\u001c\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b/\u0010\u001a\u001a\u0004\b0\u00101R\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b2\u0010\u001a\u001a\u0004\b3\u00104R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b5\u0010\u001a\u001a\u0004\b6\u00107¨\u0006T"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;", "", "id", "", "idMal", "season", "", "seasonYear", "format", "episodes", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "description", "coverImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "synonyms", "", "nextAiringEpisode", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "<init>", "(ILjava/lang/Integer;Ljava/lang/String;ILjava/lang/String;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/Integer;Ljava/lang/String;ILjava/lang/String;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getIdMal$annotations", "getIdMal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSeason$annotations", "getSeason", "()Ljava/lang/String;", "getSeasonYear$annotations", "getSeasonYear", "getFormat$annotations", "getFormat", "getEpisodes$annotations", "getEpisodes", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "getDescription$annotations", "getDescription", "getCoverImage$annotations", "getCoverImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "getSynonyms$annotations", "getSynonyms", "()Ljava/util/List;", "getNextAiringEpisode$annotations", "getNextAiringEpisode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(ILjava/lang/Integer;Ljava/lang/String;ILjava/lang/String;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Media {

        @JsonProperty("coverImage")
        private final CoverImage coverImage;

        @JsonProperty("description")
        private final String description;

        @JsonProperty("episodes")
        private final int episodes;

        @JsonProperty("format")
        private final String format;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("idMal")
        private final Integer idMal;

        @JsonProperty("nextAiringEpisode")
        private final SeasonNextAiringEpisode nextAiringEpisode;

        @JsonProperty("season")
        private final String season;

        @JsonProperty("seasonYear")
        private final int seasonYear;

        @JsonProperty("synonyms")
        private final List<String> synonyms;

        @JsonProperty("title")
        private final Title title;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Media$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.Media._childSerializers$_anonymous_();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Media copy$default(Media media, int i, Integer num, String str, int i2, String str2, int i3, Title title, String str3, CoverImage coverImage, List list, SeasonNextAiringEpisode seasonNextAiringEpisode, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = media.id;
            }
            if ((i4 & 2) != 0) {
                num = media.idMal;
            }
            if ((i4 & 4) != 0) {
                str = media.season;
            }
            if ((i4 & 8) != 0) {
                i2 = media.seasonYear;
            }
            if ((i4 & 16) != 0) {
                str2 = media.format;
            }
            if ((i4 & 32) != 0) {
                i3 = media.episodes;
            }
            if ((i4 & 64) != 0) {
                title = media.title;
            }
            if ((i4 & 128) != 0) {
                str3 = media.description;
            }
            if ((i4 & 256) != 0) {
                coverImage = media.coverImage;
            }
            if ((i4 & 512) != 0) {
                list = media.synonyms;
            }
            if ((i4 & 1024) != 0) {
                seasonNextAiringEpisode = media.nextAiringEpisode;
            }
            List list2 = list;
            SeasonNextAiringEpisode seasonNextAiringEpisode2 = seasonNextAiringEpisode;
            String str4 = str3;
            CoverImage coverImage2 = coverImage;
            int i5 = i3;
            Title title2 = title;
            String str5 = str2;
            String str6 = str;
            return media.copy(i, num, str6, i2, str5, i5, title2, str4, coverImage2, list2, seasonNextAiringEpisode2);
        }

        @SerialName("coverImage")
        public static /* synthetic */ void getCoverImage$annotations() {
        }

        @SerialName("description")
        public static /* synthetic */ void getDescription$annotations() {
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("format")
        public static /* synthetic */ void getFormat$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("idMal")
        public static /* synthetic */ void getIdMal$annotations() {
        }

        @SerialName("nextAiringEpisode")
        public static /* synthetic */ void getNextAiringEpisode$annotations() {
        }

        @SerialName("season")
        public static /* synthetic */ void getSeason$annotations() {
        }

        @SerialName("seasonYear")
        public static /* synthetic */ void getSeasonYear$annotations() {
        }

        @SerialName("synonyms")
        public static /* synthetic */ void getSynonyms$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        public final List<String> component10() {
            return this.synonyms;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final SeasonNextAiringEpisode getNextAiringEpisode() {
            return this.nextAiringEpisode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getIdMal() {
            return this.idMal;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getSeasonYear() {
            return this.seasonYear;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getEpisodes() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Title getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final CoverImage getCoverImage() {
            return this.coverImage;
        }

        public final Media copy(@JsonProperty("id") int id, @JsonProperty("idMal") Integer idMal, @JsonProperty("season") String season, @JsonProperty("seasonYear") int seasonYear, @JsonProperty("format") String format, @JsonProperty("episodes") int episodes, @JsonProperty("title") Title title, @JsonProperty("description") String description, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("synonyms") List<String> synonyms, @JsonProperty("nextAiringEpisode") SeasonNextAiringEpisode nextAiringEpisode) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(coverImage, "coverImage");
            Intrinsics.checkNotNullParameter(synonyms, "synonyms");
            return new Media(id, idMal, season, seasonYear, format, episodes, title, description, coverImage, synonyms, nextAiringEpisode);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Media)) {
                return false;
            }
            Media media = (Media) other;
            return this.id == media.id && Intrinsics.areEqual(this.idMal, media.idMal) && Intrinsics.areEqual(this.season, media.season) && this.seasonYear == media.seasonYear && Intrinsics.areEqual(this.format, media.format) && this.episodes == media.episodes && Intrinsics.areEqual(this.title, media.title) && Intrinsics.areEqual(this.description, media.description) && Intrinsics.areEqual(this.coverImage, media.coverImage) && Intrinsics.areEqual(this.synonyms, media.synonyms) && Intrinsics.areEqual(this.nextAiringEpisode, media.nextAiringEpisode);
        }

        public int hashCode() {
            int i = this.id * 31;
            Integer num = this.idMal;
            int iHashCode = (i + (num == null ? 0 : num.hashCode())) * 31;
            String str = this.season;
            int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.seasonYear) * 31;
            String str2 = this.format;
            int iHashCode3 = (((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.episodes) * 31) + this.title.hashCode()) * 31;
            String str3 = this.description;
            int iHashCode4 = (((((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.coverImage.hashCode()) * 31) + this.synonyms.hashCode()) * 31;
            SeasonNextAiringEpisode seasonNextAiringEpisode = this.nextAiringEpisode;
            return iHashCode4 + (seasonNextAiringEpisode != null ? seasonNextAiringEpisode.hashCode() : 0);
        }

        public String toString() {
            return "Media(id=" + this.id + ", idMal=" + this.idMal + ", season=" + this.season + ", seasonYear=" + this.seasonYear + ", format=" + this.format + ", episodes=" + this.episodes + ", title=" + this.title + ", description=" + this.description + ", coverImage=" + this.coverImage + ", synonyms=" + this.synonyms + ", nextAiringEpisode=" + this.nextAiringEpisode + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Media> serializer() {
                return AniListApi$Media$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Media(int i, int i2, Integer num, String str, int i3, String str2, int i4, Title title, String str3, CoverImage coverImage, List list, SeasonNextAiringEpisode seasonNextAiringEpisode, SerializationConstructorMarker serializationConstructorMarker) {
            if (2047 != (i & 2047)) {
                PluginExceptionsKt.throwMissingFieldException(i, 2047, AniListApi$Media$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.idMal = num;
            this.season = str;
            this.seasonYear = i3;
            this.format = str2;
            this.episodes = i4;
            this.title = title;
            this.description = str3;
            this.coverImage = coverImage;
            this.synonyms = list;
            this.nextAiringEpisode = seasonNextAiringEpisode;
        }

        public Media(@JsonProperty("id") int i, @JsonProperty("idMal") Integer num, @JsonProperty("season") String str, @JsonProperty("seasonYear") int i2, @JsonProperty("format") String str2, @JsonProperty("episodes") int i3, @JsonProperty("title") Title title, @JsonProperty("description") String str3, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("synonyms") List<String> synonyms, @JsonProperty("nextAiringEpisode") SeasonNextAiringEpisode seasonNextAiringEpisode) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(coverImage, "coverImage");
            Intrinsics.checkNotNullParameter(synonyms, "synonyms");
            this.id = i;
            this.idMal = num;
            this.season = str;
            this.seasonYear = i2;
            this.format = str2;
            this.episodes = i3;
            this.title = title;
            this.description = str3;
            this.coverImage = coverImage;
            this.synonyms = synonyms;
            this.nextAiringEpisode = seasonNextAiringEpisode;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Media self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.idMal);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.season);
            output.encodeIntElement(serialDesc, 3, self.seasonYear);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.format);
            output.encodeIntElement(serialDesc, 5, self.episodes);
            output.encodeSerializableElement(serialDesc, 6, AniListApi$Title$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.description);
            output.encodeSerializableElement(serialDesc, 8, AniListApi$CoverImage$$serializer.INSTANCE, self.coverImage);
            output.encodeSerializableElement(serialDesc, 9, lazyArr[9].getValue(), self.synonyms);
            output.encodeNullableSerializableElement(serialDesc, 10, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, self.nextAiringEpisode);
        }

        public final int getId() {
            return this.id;
        }

        public final Integer getIdMal() {
            return this.idMal;
        }

        public final String getSeason() {
            return this.season;
        }

        public final int getSeasonYear() {
            return this.seasonYear;
        }

        public final String getFormat() {
            return this.format;
        }

        public final int getEpisodes() {
            return this.episodes;
        }

        public final Title getTitle() {
            return this.title;
        }

        public final String getDescription() {
            return this.description;
        }

        public final CoverImage getCoverImage() {
            return this.coverImage;
        }

        public final List<String> getSynonyms() {
            return this.synonyms;
        }

        public final SeasonNextAiringEpisode getNextAiringEpisode() {
            return this.nextAiringEpisode;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 E2\u00020\u0001:\u0002DEBY\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\t\u0012\b\b\u0001\u0010\u000b\u001a\u00020\t\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011Bc\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0010\u0010\u0015J\u0006\u0010-\u001a\u00020.J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\t\u00103\u001a\u00020\tHÆ\u0003J\t\u00104\u001a\u00020\tHÆ\u0003J\t\u00105\u001a\u00020\rHÆ\u0003J\t\u00106\u001a\u00020\u000fHÆ\u0003J[\u00107\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0003\u0010\u000b\u001a\u00020\t2\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0014\u00108\u001a\u00020\r2\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010:\u001a\u00020\tHÖ\u0081\u0004J\n\u0010;\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00002\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020BH\u0001¢\u0006\u0002\bCR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0017\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0017\u001a\u0004\b!\u0010\"R\u001c\u0010\n\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0017\u001a\u0004\b$\u0010\"R\u001c\u0010\u000b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b%\u0010\u0017\u001a\u0004\b&\u0010\"R\u001c\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0017\u001a\u0004\b(\u0010)R\u001c\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u0017\u001a\u0004\b+\u0010,¨\u0006F"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Entries;", "", NotificationCompat.CATEGORY_STATUS, "", "completedAt", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt;", "startedAt", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;", "updatedAt", "", "progress", "score", "private", "", LinkHeader.Parameters.Media, "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;", "<init>", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;IIIZLcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;IIIZLcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Ljava/lang/String;", "getCompletedAt$annotations", "getCompletedAt", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CompletedAt;", "getStartedAt$annotations", "getStartedAt", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;", "getUpdatedAt$annotations", "getUpdatedAt", "()I", "getProgress$annotations", "getProgress", "getScore$annotations", "getScore", "getPrivate$annotations", "getPrivate", "()Z", "getMedia$annotations", "getMedia", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Media;", "toLibraryItem", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Entries {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("completedAt")
        private final CompletedAt completedAt;

        @JsonProperty(LinkHeader.Parameters.Media)
        private final Media media;

        @JsonProperty("private")
        private final boolean private;

        @JsonProperty("progress")
        private final int progress;

        @JsonProperty("score")
        private final int score;

        @JsonProperty("startedAt")
        private final StartedAt startedAt;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        @JsonProperty("updatedAt")
        private final int updatedAt;

        public static /* synthetic */ Entries copy$default(Entries entries, String str, CompletedAt completedAt, StartedAt startedAt, int i, int i2, int i3, boolean z, Media media, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = entries.status;
            }
            if ((i4 & 2) != 0) {
                completedAt = entries.completedAt;
            }
            if ((i4 & 4) != 0) {
                startedAt = entries.startedAt;
            }
            if ((i4 & 8) != 0) {
                i = entries.updatedAt;
            }
            if ((i4 & 16) != 0) {
                i2 = entries.progress;
            }
            if ((i4 & 32) != 0) {
                i3 = entries.score;
            }
            if ((i4 & 64) != 0) {
                z = entries.private;
            }
            if ((i4 & 128) != 0) {
                media = entries.media;
            }
            boolean z2 = z;
            Media media2 = media;
            int i5 = i2;
            int i6 = i3;
            return entries.copy(str, completedAt, startedAt, i, i5, i6, z2, media2);
        }

        @SerialName("completedAt")
        public static /* synthetic */ void getCompletedAt$annotations() {
        }

        @SerialName(LinkHeader.Parameters.Media)
        public static /* synthetic */ void getMedia$annotations() {
        }

        @SerialName("private")
        public static /* synthetic */ void getPrivate$annotations() {
        }

        @SerialName("progress")
        public static /* synthetic */ void getProgress$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName("startedAt")
        public static /* synthetic */ void getStartedAt$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        @SerialName("updatedAt")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final CompletedAt getCompletedAt() {
            return this.completedAt;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final StartedAt getStartedAt() {
            return this.startedAt;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getUpdatedAt() {
            return this.updatedAt;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getProgress() {
            return this.progress;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getPrivate() {
            return this.private;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Media getMedia() {
            return this.media;
        }

        public final Entries copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("completedAt") CompletedAt completedAt, @JsonProperty("startedAt") StartedAt startedAt, @JsonProperty("updatedAt") int updatedAt, @JsonProperty("progress") int progress, @JsonProperty("score") int score, @JsonProperty("private") boolean z, @JsonProperty(LinkHeader.Parameters.Media) Media media) {
            Intrinsics.checkNotNullParameter(completedAt, "completedAt");
            Intrinsics.checkNotNullParameter(startedAt, "startedAt");
            Intrinsics.checkNotNullParameter(media, "media");
            return new Entries(status, completedAt, startedAt, updatedAt, progress, score, z, media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entries)) {
                return false;
            }
            Entries entries = (Entries) other;
            return Intrinsics.areEqual(this.status, entries.status) && Intrinsics.areEqual(this.completedAt, entries.completedAt) && Intrinsics.areEqual(this.startedAt, entries.startedAt) && this.updatedAt == entries.updatedAt && this.progress == entries.progress && this.score == entries.score && this.private == entries.private && Intrinsics.areEqual(this.media, entries.media);
        }

        public int hashCode() {
            String str = this.status;
            return ((((((((((((((str == null ? 0 : str.hashCode()) * 31) + this.completedAt.hashCode()) * 31) + this.startedAt.hashCode()) * 31) + this.updatedAt) * 31) + this.progress) * 31) + this.score) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.private)) * 31) + this.media.hashCode();
        }

        public String toString() {
            return "Entries(status=" + this.status + ", completedAt=" + this.completedAt + ", startedAt=" + this.startedAt + ", updatedAt=" + this.updatedAt + ", progress=" + this.progress + ", score=" + this.score + ", private=" + this.private + ", media=" + this.media + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Entries$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Entries;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Entries> serializer() {
                return AniListApi$Entries$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Entries(int i, String str, CompletedAt completedAt, StartedAt startedAt, int i2, int i3, int i4, boolean z, Media media, SerializationConstructorMarker serializationConstructorMarker) {
            if (255 != (i & 255)) {
                PluginExceptionsKt.throwMissingFieldException(i, 255, AniListApi$Entries$$serializer.INSTANCE.getDescriptor());
            }
            this.status = str;
            this.completedAt = completedAt;
            this.startedAt = startedAt;
            this.updatedAt = i2;
            this.progress = i3;
            this.score = i4;
            this.private = z;
            this.media = media;
        }

        public Entries(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String str, @JsonProperty("completedAt") CompletedAt completedAt, @JsonProperty("startedAt") StartedAt startedAt, @JsonProperty("updatedAt") int i, @JsonProperty("progress") int i2, @JsonProperty("score") int i3, @JsonProperty("private") boolean z, @JsonProperty(LinkHeader.Parameters.Media) Media media) {
            Intrinsics.checkNotNullParameter(completedAt, "completedAt");
            Intrinsics.checkNotNullParameter(startedAt, "startedAt");
            Intrinsics.checkNotNullParameter(media, "media");
            this.status = str;
            this.completedAt = completedAt;
            this.startedAt = startedAt;
            this.updatedAt = i;
            this.progress = i2;
            this.score = i3;
            this.private = z;
            this.media = media;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Entries self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.status);
            output.encodeSerializableElement(serialDesc, 1, AniListApi$CompletedAt$$serializer.INSTANCE, self.completedAt);
            output.encodeSerializableElement(serialDesc, 2, AniListApi$StartedAt$$serializer.INSTANCE, self.startedAt);
            output.encodeIntElement(serialDesc, 3, self.updatedAt);
            output.encodeIntElement(serialDesc, 4, self.progress);
            output.encodeIntElement(serialDesc, 5, self.score);
            output.encodeBooleanElement(serialDesc, 6, self.private);
            output.encodeSerializableElement(serialDesc, 7, AniListApi$Media$$serializer.INSTANCE, self.media);
        }

        public final String getStatus() {
            return this.status;
        }

        public final CompletedAt getCompletedAt() {
            return this.completedAt;
        }

        public final StartedAt getStartedAt() {
            return this.startedAt;
        }

        public final int getUpdatedAt() {
            return this.updatedAt;
        }

        public final int getProgress() {
            return this.progress;
        }

        public final int getScore() {
            return this.score;
        }

        public final boolean getPrivate() {
            return this.private;
        }

        public final Media getMedia() {
            return this.media;
        }

        public final SyncAPI.LibraryItem toLibraryItem() {
            String english = this.media.getTitle().getEnglish();
            if (english == null && (english = this.media.getTitle().getRomaji()) == null && (english = (String) CollectionsKt.firstOrNull((List) this.media.getSynonyms())) == null) {
                english = "";
            }
            String str = "https://anilist.co/anime/" + this.media.getId() + '/';
            String strValueOf = String.valueOf(this.media.getId());
            Integer numValueOf = Integer.valueOf(this.progress);
            Integer numValueOf2 = Integer.valueOf(this.media.getEpisodes());
            Score scoreFrom100 = Score.INSTANCE.from100(Integer.valueOf(this.score));
            Long lValueOf = Long.valueOf(this.updatedAt);
            TvType tvType = TvType.Anime;
            String extraLarge = this.media.getCoverImage().getExtraLarge();
            if (extraLarge == null && (extraLarge = this.media.getCoverImage().getLarge()) == null) {
                extraLarge = this.media.getCoverImage().getMedium();
            }
            return new SyncAPI.LibraryItem(english, str, strValueOf, numValueOf, numValueOf2, scoreFrom100, lValueOf, "AniList", tvType, extraLarge, null, null, DataStoreHelper.INSTANCE.toYear(this.media.getSeasonYear()), null, this.media.getDescription(), null, null, 98304, null);
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B#\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J%\u0010\u0017\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Lists;", "", NotificationCompat.CATEGORY_STATUS, "", "entries", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Entries;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getStatus$annotations", "()V", "getStatus", "()Ljava/lang/String;", "getEntries$annotations", "getEntries", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Lists {

        @JsonProperty("entries")
        private final List<Entries> entries;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Lists$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.Lists._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$Entries$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Lists copy$default(Lists lists, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = lists.status;
            }
            if ((i & 2) != 0) {
                list = lists.entries;
            }
            return lists.copy(str, list);
        }

        @SerialName("entries")
        public static /* synthetic */ void getEntries$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        public final List<Entries> component2() {
            return this.entries;
        }

        public final Lists copy(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("entries") List<Entries> entries) {
            Intrinsics.checkNotNullParameter(entries, "entries");
            return new Lists(status, entries);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Lists)) {
                return false;
            }
            Lists lists = (Lists) other;
            return Intrinsics.areEqual(this.status, lists.status) && Intrinsics.areEqual(this.entries, lists.entries);
        }

        public int hashCode() {
            String str = this.status;
            return ((str == null ? 0 : str.hashCode()) * 31) + this.entries.hashCode();
        }

        public String toString() {
            return "Lists(status=" + this.status + ", entries=" + this.entries + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Lists$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Lists;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Lists> serializer() {
                return AniListApi$Lists$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Lists(int i, String str, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$Lists$$serializer.INSTANCE.getDescriptor());
            }
            this.status = str;
            this.entries = list;
        }

        public Lists(@JsonProperty(NotificationCompat.CATEGORY_STATUS) String str, @JsonProperty("entries") List<Entries> entries) {
            Intrinsics.checkNotNullParameter(entries, "entries");
            this.status = str;
            this.entries = entries;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Lists self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.status);
            output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.entries);
        }

        public final String getStatus() {
            return this.status;
        }

        public final List<Entries> getEntries() {
            return this.entries;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection;", "", "lists", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Lists;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLists$annotations", "()V", "getLists", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaListCollection {

        @JsonProperty("lists")
        private final List<Lists> lists;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$MediaListCollection$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.MediaListCollection._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$Lists$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MediaListCollection copy$default(MediaListCollection mediaListCollection, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = mediaListCollection.lists;
            }
            return mediaListCollection.copy(list);
        }

        @SerialName("lists")
        public static /* synthetic */ void getLists$annotations() {
        }

        public final List<Lists> component1() {
            return this.lists;
        }

        public final MediaListCollection copy(@JsonProperty("lists") List<Lists> lists) {
            Intrinsics.checkNotNullParameter(lists, "lists");
            return new MediaListCollection(lists);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MediaListCollection) && Intrinsics.areEqual(this.lists, ((MediaListCollection) other).lists);
        }

        public int hashCode() {
            return this.lists.hashCode();
        }

        public String toString() {
            return "MediaListCollection(lists=" + this.lists + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaListCollection> serializer() {
                return AniListApi$MediaListCollection$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaListCollection(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$MediaListCollection$$serializer.INSTANCE.getDescriptor());
            }
            this.lists = list;
        }

        public MediaListCollection(@JsonProperty("lists") List<Lists> lists) {
            Intrinsics.checkNotNullParameter(lists, "lists");
            this.lists = lists;
        }

        public final List<Lists> getLists() {
            return this.lists;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data;", "", "mediaListCollection", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMediaListCollection$annotations", "()V", "getMediaListCollection", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListCollection;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Data {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("MediaListCollection")
        private final MediaListCollection mediaListCollection;

        public static /* synthetic */ Data copy$default(Data data, MediaListCollection mediaListCollection, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaListCollection = data.mediaListCollection;
            }
            return data.copy(mediaListCollection);
        }

        @SerialName("MediaListCollection")
        public static /* synthetic */ void getMediaListCollection$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MediaListCollection getMediaListCollection() {
            return this.mediaListCollection;
        }

        public final Data copy(@JsonProperty("MediaListCollection") MediaListCollection mediaListCollection) {
            Intrinsics.checkNotNullParameter(mediaListCollection, "mediaListCollection");
            return new Data(mediaListCollection);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.mediaListCollection, ((Data) other).mediaListCollection);
        }

        public int hashCode() {
            return this.mediaListCollection.hashCode();
        }

        public String toString() {
            return "Data(mediaListCollection=" + this.mediaListCollection + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Data;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Data> serializer() {
                return AniListApi$Data$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Data(int i, MediaListCollection mediaListCollection, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$Data$$serializer.INSTANCE.getDescriptor());
            }
            this.mediaListCollection = mediaListCollection;
        }

        public Data(@JsonProperty("MediaListCollection") MediaListCollection mediaListCollection) {
            Intrinsics.checkNotNullParameter(mediaListCollection, "mediaListCollection");
            this.mediaListCollection = mediaListCollection;
        }

        public final MediaListCollection getMediaListCollection() {
            return this.mediaListCollection;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:51:0x0101  */
    /* JADX WARN: Code duplicated, block: B:58:0x0117 A[Catch: Exception -> 0x0129, TRY_LEAVE, TryCatch #4 {Exception -> 0x0129, blocks: (B:34:0x009e, B:37:0x00aa, B:42:0x00cf, B:49:0x00fb, B:52:0x0102, B:58:0x0117, B:57:0x0112, B:48:0x00f1, B:41:0x00c5, B:45:0x00d6, B:38:0x00ac, B:54:0x0106), top: B:71:0x009e, inners: #0, #2, #5 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getAniListAnimeListSmart(AuthData authData, Continuation<? super Lists[]> continuation) {
        C07691 c07691;
        Object objDecodeFromString;
        Object objM8936constructorimpl;
        KSerializer kSerializer;
        Data data;
        MediaListCollection mediaListCollection;
        List<Lists> lists;
        if (continuation instanceof C07691) {
            c07691 = (C07691) continuation;
            if ((c07691.label & Integer.MIN_VALUE) != 0) {
                c07691.label -= Integer.MIN_VALUE;
            } else {
                c07691 = new C07691(continuation);
            }
        } else {
            c07691 = new C07691(continuation);
        }
        Object fullAniListList = c07691.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07691.label;
        Lists[] listsArr = null;
        if (i == 0) {
            ResultKt.throwOnFailure(fullAniListList);
            if (getRequireLibraryRefresh()) {
                c07691.L$0 = authData;
                c07691.label = 1;
                fullAniListList = getFullAniListList(authData, c07691);
                if (fullAniListList == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                CloudStreamApp.Companion companion = CloudStreamApp.INSTANCE;
                String strValueOf = String.valueOf(authData.getUser().getId());
                Context context = companion.getContext();
                if (context != null) {
                    DataStore dataStore = DataStore.INSTANCE;
                    try {
                        String string = dataStore.getSharedPrefs(context).getString(dataStore.getFolderName(ANILIST_CACHED_LIST, strValueOf), null);
                        if (string == null) {
                            objDecodeFromString = null;
                        } else {
                            AppUtils appUtils = AppUtils.INSTANCE;
                            try {
                                Result.Companion companion2 = Result.INSTANCE;
                                objM8936constructorimpl = Result.m8936constructorimpl(new ReferenceArraySerializer(Reflection.getOrCreateKotlinClass(Lists.class), Lists.INSTANCE.serializer()));
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
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Lists[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getAniListAnimeListSmart$$inlined$getKey$1
                                        });
                                    } catch (Throwable unused) {
                                        objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Lists[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getAniListAnimeListSmart$$inlined$getKey$1
                                        });
                                    }
                                } else {
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Lists[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getAniListAnimeListSmart$$inlined$getKey$1
                                    });
                                }
                            } else {
                                try {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Lists[].class), null, 2, null));
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
                                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<Lists[]>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getAniListAnimeListSmart$$inlined$getKey$1
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
                if (objDecodeFromString instanceof Lists[]) {
                    return (Lists[]) objDecodeFromString;
                }
                return null;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            authData = (AuthData) c07691.L$0;
            ResultKt.throwOnFailure(fullAniListList);
        }
        FullAnilistList fullAnilistList = (FullAnilistList) fullAniListList;
        if (fullAnilistList != null && (data = fullAnilistList.getData()) != null && (mediaListCollection = data.getMediaListCollection()) != null && (lists = mediaListCollection.getLists()) != null) {
            listsArr = (Lists[]) lists.toArray(new Lists[0]);
        }
        if (listsArr != null) {
            CloudStreamApp.INSTANCE.setKey(ANILIST_CACHED_LIST, String.valueOf(authData.getUser().getId()), listsArr);
        }
        return listsArr;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI
    public Object library(AuthData authData, Continuation<? super SyncAPI.LibraryMetadata> continuation) {
        C07731 c07731;
        LinkedHashMap linkedHashMapEmptyMap;
        if (continuation instanceof C07731) {
            c07731 = (C07731) continuation;
            if ((c07731.label & Integer.MIN_VALUE) != 0) {
                c07731.label -= Integer.MIN_VALUE;
            } else {
                c07731 = new C07731(continuation);
            }
        } else {
            c07731 = new C07731(continuation);
        }
        Object aniListAnimeListSmart = c07731.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07731.label;
        if (i == 0) {
            ResultKt.throwOnFailure(aniListAnimeListSmart);
            if (authData == null) {
                return null;
            }
            c07731.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07731.label = 1;
            aniListAnimeListSmart = getAniListAnimeListSmart(authData, c07731);
            if (aniListAnimeListSmart == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(aniListAnimeListSmart);
        }
        Lists[] listsArr = (Lists[]) aniListAnimeListSmart;
        if (listsArr != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Lists lists : listsArr) {
                Companion companion = INSTANCE;
                String status = lists.getStatus();
                if (status == null) {
                    status = "";
                }
                Integer numBoxInt = Boxing.boxInt(companion.convertAniListStringToStatus(status).getStringRes());
                Object obj = linkedHashMap.get(numBoxInt);
                if (obj == null) {
                    obj = (List) new ArrayList();
                    linkedHashMap.put(numBoxInt, obj);
                }
                ((List) obj).add(lists);
            }
            linkedHashMapEmptyMap = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Object key = entry.getKey();
                Iterable iterable = (Iterable) entry.getValue();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    List<Entries> entries = ((Lists) it.next()).getEntries();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
                    Iterator<T> it2 = entries.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((Entries) it2.next()).toLibraryItem());
                    }
                    arrayList.add(arrayList2);
                }
                linkedHashMapEmptyMap.put(key, CollectionsKt.flatten(arrayList));
            }
        } else {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        }
        EnumEntries<Companion.AniListStatusType> entries2 = Companion.AniListStatusType.getEntries();
        ArrayList arrayList3 = new ArrayList();
        for (Companion.AniListStatusType aniListStatusType : entries2) {
            if (aniListStatusType.getValue() >= 0) {
                arrayList3.add(aniListStatusType);
            }
        }
        ArrayList arrayList4 = arrayList3;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList4, 10)), 16));
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            Pair pair = TuplesKt.to(Boxing.boxInt(((Companion.AniListStatusType) it3.next()).getStringRes()), CollectionsKt.emptyList());
            linkedHashMap2.put(pair.getFirst(), pair.getSecond());
        }
        Map mapPlus = MapsKt.plus(linkedHashMap2, linkedHashMapEmptyMap);
        ArrayList arrayList5 = new ArrayList(mapPlus.size());
        for (Map.Entry entry2 : mapPlus.entrySet()) {
            arrayList5.add(new SyncAPI.LibraryList(TextUtilKt.txt(((Number) entry2.getKey()).intValue(), new Object[0]), (List) entry2.getValue()));
        }
        return new SyncAPI.LibraryMetadata(arrayList5, SetsKt.setOf((Object[]) new ListSorting[]{ListSorting.AlphabeticalA, ListSorting.AlphabeticalZ, ListSorting.UpdatedNew, ListSorting.UpdatedOld, ListSorting.ReleaseDateNew, ListSorting.ReleaseDateOld, ListSorting.RatingHigh, ListSorting.RatingLow}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getFullAniListList(AuthData authData, Continuation<? super FullAnilistList> continuation) {
        C07711 c07711;
        Object objM8936constructorimpl;
        if (continuation instanceof C07711) {
            c07711 = (C07711) continuation;
            if ((c07711.label & Integer.MIN_VALUE) != 0) {
                c07711.label -= Integer.MIN_VALUE;
            } else {
                c07711 = new C07711(continuation);
            }
        } else {
            c07711 = new C07711(continuation);
        }
        C07711 c07712 = c07711;
        Object objPostApi$default = c07712.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07712.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objPostApi$default);
            int id = authData.getUser().getId();
            String str = "\n                query ($userID: Int = " + id + ", $MEDIA: MediaType = ANIME) {\n                    MediaListCollection (userId: $userID, type: $MEDIA) { \n                        lists {\n                            status\n                            entries\n                            {\n                                status\n                                completedAt { year month day }\n                                startedAt { year month day }\n                                updatedAt\n                                progress\n                                score (format: POINT_100)\n                                private\n                                media\n                                {\n                                    id\n                                    idMal\n                                    season\n                                    seasonYear\n                                    format\n                                    episodes\n                                    chapters\n                                    title\n                                    {\n                                        english\n                                        romaji\n                                    }\n                                    coverImage { extraLarge large medium }\n                                    synonyms\n                                    nextAiringEpisode {\n                                        timeUntilAiring\n                                        episode\n                                    }\n                                }\n                            }\n                        }\n                    }\n                }\n            ";
            AuthToken token = authData.getToken();
            c07712.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07712.L$1 = SpillingKt.nullOutSpilledVariable("ANIME");
            c07712.L$2 = SpillingKt.nullOutSpilledVariable(str);
            c07712.I$0 = id;
            c07712.label = 1;
            objPostApi$default = postApi$default(this, token, str, false, c07712, 4, null);
            if (objPostApi$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i2 = c07712.I$0;
            ResultKt.throwOnFailure(objPostApi$default);
        }
        String str2 = (String) objPostApi$default;
        AppUtils appUtils = AppUtils.INSTANCE;
        Object objDecodeFromString = null;
        if (str2 == null) {
            return null;
        }
        try {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(FullAnilistList.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(FullAnilistList.class), null, 2, null));
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
                    return objDecodeFromString;
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                } catch (Throwable unused) {
                }
            }
            return MainAPIKt.getMapper().readValue(str2, new TypeReference<FullAnilistList>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getFullAniListList$$inlined$tryParseJson$1
            });
        } catch (Exception unused2) {
            return objDecodeFromString;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object toggleLike(AuthData authData, int i, Continuation<? super Boolean> continuation) {
        C07791 c07791;
        if (continuation instanceof C07791) {
            c07791 = (C07791) continuation;
            if ((c07791.label & Integer.MIN_VALUE) != 0) {
                c07791.label -= Integer.MIN_VALUE;
            } else {
                c07791 = new C07791(continuation);
            }
        } else {
            c07791 = new C07791(continuation);
        }
        C07791 c07792 = c07791;
        Object objPostApi$default = c07792.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c07792.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objPostApi$default);
            String str = "mutation ($animeId: Int = " + i + ") {\n                ToggleFavourite (animeId: $animeId) {\n                    anime {\n                        nodes {\n                            id\n                            title {\n                                romaji\n                            }\n                        }\n                    }\n                }\n            }";
            AuthToken token = authData.getToken();
            c07792.L$0 = SpillingKt.nullOutSpilledVariable(authData);
            c07792.L$1 = SpillingKt.nullOutSpilledVariable(str);
            c07792.I$0 = i;
            c07792.label = 1;
            objPostApi$default = postApi$default(this, token, str, false, c07792, 4, null);
            if (objPostApi$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = c07792.I$0;
            ResultKt.throwOnFailure(objPostApi$default);
        }
        return Boxing.boxBoolean(!Intrinsics.areEqual((String) objPostApi$default, ""));
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItemRoot;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaListItemRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final MediaListItem data;

        /* JADX WARN: Multi-variable type inference failed */
        public MediaListItemRoot() {
            this((MediaListItem) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ MediaListItemRoot copy$default(MediaListItemRoot mediaListItemRoot, MediaListItem mediaListItem, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaListItem = mediaListItemRoot.data;
            }
            return mediaListItemRoot.copy(mediaListItem);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MediaListItem getData() {
            return this.data;
        }

        public final MediaListItemRoot copy(@JsonProperty("data") MediaListItem data) {
            return new MediaListItemRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MediaListItemRoot) && Intrinsics.areEqual(this.data, ((MediaListItemRoot) other).data);
        }

        public int hashCode() {
            MediaListItem mediaListItem = this.data;
            if (mediaListItem == null) {
                return 0;
            }
            return mediaListItem.hashCode();
        }

        public String toString() {
            return "MediaListItemRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItemRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItemRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaListItemRoot> serializer() {
                return AniListApi$MediaListItemRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaListItemRoot(int i, MediaListItem mediaListItem, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.data = null;
            } else {
                this.data = mediaListItem;
            }
        }

        public MediaListItemRoot(@JsonProperty("data") MediaListItem mediaListItem) {
            this.data = mediaListItem;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaListItemRoot self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.data == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, AniListApi$MediaListItem$$serializer.INSTANCE, self.data);
        }

        public /* synthetic */ MediaListItemRoot(MediaListItem mediaListItem, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : mediaListItem);
        }

        public final MediaListItem getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem;", "", "mediaList", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMediaList$annotations", "()V", "getMediaList", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaListItem {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("MediaList")
        private final MediaListId mediaList;

        /* JADX WARN: Multi-variable type inference failed */
        public MediaListItem() {
            this((MediaListId) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ MediaListItem copy$default(MediaListItem mediaListItem, MediaListId mediaListId, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaListId = mediaListItem.mediaList;
            }
            return mediaListItem.copy(mediaListId);
        }

        @SerialName("MediaList")
        public static /* synthetic */ void getMediaList$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MediaListId getMediaList() {
            return this.mediaList;
        }

        public final MediaListItem copy(@JsonProperty("MediaList") MediaListId mediaList) {
            return new MediaListItem(mediaList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MediaListItem) && Intrinsics.areEqual(this.mediaList, ((MediaListItem) other).mediaList);
        }

        public int hashCode() {
            MediaListId mediaListId = this.mediaList;
            if (mediaListId == null) {
                return 0;
            }
            return mediaListId.hashCode();
        }

        public String toString() {
            return "MediaListItem(mediaList=" + this.mediaList + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListItem;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaListItem> serializer() {
                return AniListApi$MediaListItem$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaListItem(int i, MediaListId mediaListId, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.mediaList = null;
            } else {
                this.mediaList = mediaListId;
            }
        }

        public MediaListItem(@JsonProperty("MediaList") MediaListId mediaListId) {
            this.mediaList = mediaListId;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaListItem self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.mediaList == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, AniListApi$MediaListId$$serializer.INSTANCE, self.mediaList);
        }

        public /* synthetic */ MediaListItem(MediaListId mediaListId, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : mediaListId);
        }

        public final MediaListId getMediaList() {
            return this.mediaList;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006#"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;", "", "id", "", "<init>", "(Ljava/lang/Long;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "copy", "(Ljava/lang/Long;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaListId {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final Long id;

        /* JADX WARN: Multi-variable type inference failed */
        public MediaListId() {
            this((Long) null, 1, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        public static /* synthetic */ MediaListId copy$default(MediaListId mediaListId, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                l = mediaListId.id;
            }
            return mediaListId.copy(l);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Long getId() {
            return this.id;
        }

        public final MediaListId copy(@JsonProperty("id") Long id) {
            return new MediaListId(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MediaListId) && Intrinsics.areEqual(this.id, ((MediaListId) other).id);
        }

        public int hashCode() {
            Long l = this.id;
            if (l == null) {
                return 0;
            }
            return l.hashCode();
        }

        public String toString() {
            return "MediaListId(id=" + this.id + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaListId;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaListId> serializer() {
                return AniListApi$MediaListId$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaListId(int i, Long l, SerializationConstructorMarker serializationConstructorMarker) {
            if ((i & 1) == 0) {
                this.id = null;
            } else {
                this.id = l;
            }
        }

        public MediaListId(@JsonProperty("id") Long l) {
            this.id = l;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaListId self, CompositeEncoder output, SerialDescriptor serialDesc) {
            if (!output.shouldEncodeElementDefault(serialDesc, 0) && self.id == null) {
                return;
            }
            output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.id);
        }

        public /* synthetic */ MediaListId(Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : l);
        }

        public final Long getId() {
            return this.id;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0231, code lost:
    
        if (r2 == r3) goto L69;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object postDataAboutId(com.lagradost.cloudstream3.syncproviders.AuthData r18, int r19, com.lagradost.cloudstream3.syncproviders.providers.AniListApi.Companion.AniListStatusType r20, com.lagradost.cloudstream3.Score r21, java.lang.Integer r22, kotlin.coroutines.Continuation<? super java.lang.Boolean> r23) {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.AniListApi.postDataAboutId(com.lagradost.cloudstream3.syncproviders.AuthData, int, com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Companion$AniListStatusType, com.lagradost.cloudstream3.Score, java.lang.Integer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getUser(AuthToken authToken, Continuation<? super AniListUser> continuation) throws JsonProcessingException {
        C07721 c07721;
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        AniListViewer viewer;
        if (continuation instanceof C07721) {
            c07721 = (C07721) continuation;
            if ((c07721.label & Integer.MIN_VALUE) != 0) {
                c07721.label -= Integer.MIN_VALUE;
            } else {
                c07721 = new C07721(continuation);
            }
        } else {
            c07721 = new C07721(continuation);
        }
        C07721 c07722 = c07721;
        Object objPostApi$default = c07722.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c07722.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objPostApi$default);
            c07722.L$0 = SpillingKt.nullOutSpilledVariable(authToken);
            c07722.L$1 = SpillingKt.nullOutSpilledVariable("\n            {\n                Viewer {\n                    id\n                    name\n                    avatar {\n                        large\n                    }\n                    favourites {\n                        anime {\n                            nodes {\n                                id\n                            }\n                        }\n                    }\n                }\n            }");
            c07722.label = 1;
            objPostApi$default = postApi$default(this, authToken, "\n            {\n                Viewer {\n                    id\n                    name\n                    avatar {\n                        large\n                    }\n                    favourites {\n                        anime {\n                            nodes {\n                                id\n                            }\n                        }\n                    }\n                }\n            }", false, c07722, 4, null);
            if (objPostApi$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objPostApi$default);
        }
        String str = (String) objPostApi$default;
        String str2 = str;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(AniListRoot.INSTANCE.serializer());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(AniListRoot.class), null, 2, null));
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
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, str);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<AniListRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getUser$$inlined$parseJson$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<AniListRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getUser$$inlined$parseJson$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(str, new TypeReference<AniListRoot>() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$getUser$$inlined$parseJson$1
                });
            }
            AniListData data = ((AniListRoot) objDecodeFromString).getData();
            if (data != null && (viewer = data.getViewer()) != null) {
                int id = viewer.getId();
                String name = viewer.getName();
                AniListAvatar avatar = viewer.getAvatar();
                return new AniListUser(id, name, avatar != null ? avatar.getLarge() : null);
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getAllSeasons(int i, Continuation<? super List<SeasonResponse>> continuation) {
        AnonymousClass1 anonymousClass1;
        List list;
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
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            anonymousClass1.L$0 = arrayList;
            anonymousClass1.I$0 = i;
            anonymousClass1.label = 1;
            if (getAllSeasons$getSeasonRecursive(arrayList, i, anonymousClass1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            list = arrayList;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = anonymousClass1.I$0;
            list = (List) anonymousClass1.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return CollectionsKt.toList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:30:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:32:0x00be  */
    /* JADX WARN: Code duplicated, block: B:33:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006e, code lost:
    
        if (r15 == r1) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object getAllSeasons$getSeasonRecursive(java.util.List<com.lagradost.cloudstream3.syncproviders.providers.AniListApi.SeasonResponse> r13, int r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) throws com.lagradost.cloudstream3.ErrorLoadingException {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.syncproviders.providers.AniListApi.getAllSeasons$getSeasonRecursive(java.util.List, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonResponse;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonResponse {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final SeasonData data;

        public static /* synthetic */ SeasonResponse copy$default(SeasonResponse seasonResponse, SeasonData seasonData, int i, Object obj) {
            if ((i & 1) != 0) {
                seasonData = seasonResponse.data;
            }
            return seasonResponse.copy(seasonData);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SeasonData getData() {
            return this.data;
        }

        public final SeasonResponse copy(@JsonProperty("data") SeasonData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new SeasonResponse(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SeasonResponse) && Intrinsics.areEqual(this.data, ((SeasonResponse) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        public String toString() {
            return "SeasonResponse(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonResponse$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonResponse;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonResponse> serializer() {
                return AniListApi$SeasonResponse$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonResponse(int i, SeasonData seasonData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$SeasonResponse$$serializer.INSTANCE.getDescriptor());
            }
            this.data = seasonData;
        }

        public SeasonResponse(@JsonProperty("data") SeasonData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            this.data = data;
        }

        public final SeasonData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData;", "", LinkHeader.Parameters.Media, "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMedia$annotations", "()V", "getMedia", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("Media")
        private final SeasonMedia media;

        public static /* synthetic */ SeasonData copy$default(SeasonData seasonData, SeasonMedia seasonMedia, int i, Object obj) {
            if ((i & 1) != 0) {
                seasonMedia = seasonData.media;
            }
            return seasonData.copy(seasonMedia);
        }

        @SerialName("Media")
        public static /* synthetic */ void getMedia$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SeasonMedia getMedia() {
            return this.media;
        }

        public final SeasonData copy(@JsonProperty("Media") SeasonMedia media) {
            Intrinsics.checkNotNullParameter(media, "media");
            return new SeasonData(media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SeasonData) && Intrinsics.areEqual(this.media, ((SeasonData) other).media);
        }

        public int hashCode() {
            return this.media.hashCode();
        }

        public String toString() {
            return "SeasonData(media=" + this.media + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonData> serializer() {
                return AniListApi$SeasonData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonData(int i, SeasonMedia seasonMedia, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$SeasonData$$serializer.INSTANCE.getDescriptor());
            }
            this.media = seasonMedia;
        }

        public SeasonData(@JsonProperty("Media") SeasonMedia media) {
            Intrinsics.checkNotNullParameter(media, "media");
            this.media = media;
        }

        public final SeasonMedia getMedia() {
            return this.media;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0012\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;", "", "id", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "coverImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "<init>", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "getCoverImage$annotations", "getCoverImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class RecommendedMedia {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("coverImage")
        private final MediaCoverImage coverImage;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("title")
        private final MediaTitle title;

        public static /* synthetic */ RecommendedMedia copy$default(RecommendedMedia recommendedMedia, Integer num, MediaTitle mediaTitle, MediaCoverImage mediaCoverImage, int i, Object obj) {
            if ((i & 1) != 0) {
                num = recommendedMedia.id;
            }
            if ((i & 2) != 0) {
                mediaTitle = recommendedMedia.title;
            }
            if ((i & 4) != 0) {
                mediaCoverImage = recommendedMedia.coverImage;
            }
            return recommendedMedia.copy(num, mediaTitle, mediaCoverImage);
        }

        @SerialName("coverImage")
        public static /* synthetic */ void getCoverImage$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaTitle getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MediaCoverImage getCoverImage() {
            return this.coverImage;
        }

        public final RecommendedMedia copy(@JsonProperty("id") Integer id, @JsonProperty("title") MediaTitle title, @JsonProperty("coverImage") MediaCoverImage coverImage) {
            return new RecommendedMedia(id, title, coverImage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommendedMedia)) {
                return false;
            }
            RecommendedMedia recommendedMedia = (RecommendedMedia) other;
            return Intrinsics.areEqual(this.id, recommendedMedia.id) && Intrinsics.areEqual(this.title, recommendedMedia.title) && Intrinsics.areEqual(this.coverImage, recommendedMedia.coverImage);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            MediaTitle mediaTitle = this.title;
            int iHashCode2 = (iHashCode + (mediaTitle == null ? 0 : mediaTitle.hashCode())) * 31;
            MediaCoverImage mediaCoverImage = this.coverImage;
            return iHashCode2 + (mediaCoverImage != null ? mediaCoverImage.hashCode() : 0);
        }

        public String toString() {
            return "RecommendedMedia(id=" + this.id + ", title=" + this.title + ", coverImage=" + this.coverImage + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<RecommendedMedia> serializer() {
                return AniListApi$RecommendedMedia$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ RecommendedMedia(int i, Integer num, MediaTitle mediaTitle, MediaCoverImage mediaCoverImage, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$RecommendedMedia$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.title = mediaTitle;
            this.coverImage = mediaCoverImage;
        }

        public RecommendedMedia(@JsonProperty("id") Integer num, @JsonProperty("title") MediaTitle mediaTitle, @JsonProperty("coverImage") MediaCoverImage mediaCoverImage) {
            this.id = num;
            this.title = mediaTitle;
            this.coverImage = mediaCoverImage;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(RecommendedMedia self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$MediaTitle$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$MediaCoverImage$$serializer.INSTANCE, self.coverImage);
        }

        public final Integer getId() {
            return this.id;
        }

        public final MediaTitle getTitle() {
            return this.title;
        }

        public final MediaCoverImage getCoverImage() {
            return this.coverImage;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0012\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterMedia;", "", "id", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "coverImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "<init>", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "getCoverImage$annotations", "getCoverImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterMedia;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CharacterMedia {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("coverImage")
        private final MediaCoverImage coverImage;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("title")
        private final MediaTitle title;

        public static /* synthetic */ CharacterMedia copy$default(CharacterMedia characterMedia, Integer num, MediaTitle mediaTitle, MediaCoverImage mediaCoverImage, int i, Object obj) {
            if ((i & 1) != 0) {
                num = characterMedia.id;
            }
            if ((i & 2) != 0) {
                mediaTitle = characterMedia.title;
            }
            if ((i & 4) != 0) {
                mediaCoverImage = characterMedia.coverImage;
            }
            return characterMedia.copy(num, mediaTitle, mediaCoverImage);
        }

        @SerialName("coverImage")
        public static /* synthetic */ void getCoverImage$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaTitle getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MediaCoverImage getCoverImage() {
            return this.coverImage;
        }

        public final CharacterMedia copy(@JsonProperty("id") Integer id, @JsonProperty("title") MediaTitle title, @JsonProperty("coverImage") MediaCoverImage coverImage) {
            return new CharacterMedia(id, title, coverImage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CharacterMedia)) {
                return false;
            }
            CharacterMedia characterMedia = (CharacterMedia) other;
            return Intrinsics.areEqual(this.id, characterMedia.id) && Intrinsics.areEqual(this.title, characterMedia.title) && Intrinsics.areEqual(this.coverImage, characterMedia.coverImage);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            MediaTitle mediaTitle = this.title;
            int iHashCode2 = (iHashCode + (mediaTitle == null ? 0 : mediaTitle.hashCode())) * 31;
            MediaCoverImage mediaCoverImage = this.coverImage;
            return iHashCode2 + (mediaCoverImage != null ? mediaCoverImage.hashCode() : 0);
        }

        public String toString() {
            return "CharacterMedia(id=" + this.id + ", title=" + this.title + ", coverImage=" + this.coverImage + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterMedia$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterMedia;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CharacterMedia> serializer() {
                return AniListApi$CharacterMedia$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CharacterMedia(int i, Integer num, MediaTitle mediaTitle, MediaCoverImage mediaCoverImage, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$CharacterMedia$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.title = mediaTitle;
            this.coverImage = mediaCoverImage;
        }

        public CharacterMedia(@JsonProperty("id") Integer num, @JsonProperty("title") MediaTitle mediaTitle, @JsonProperty("coverImage") MediaCoverImage mediaCoverImage) {
            this.id = num;
            this.title = mediaTitle;
            this.coverImage = mediaCoverImage;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CharacterMedia self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$MediaTitle$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$MediaCoverImage$$serializer.INSTANCE, self.coverImage);
        }

        public final Integer getId() {
            return this.id;
        }

        public final MediaTitle getTitle() {
            return this.title;
        }

        public final MediaCoverImage getCoverImage() {
            return this.coverImage;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bH\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 s2\u00020\u0001:\u0002rsBß\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012\u0012\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\b\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u001e\u0010\u001fBÑ\u0001\b\u0010\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010!\u001a\u0004\u0018\u00010\"¢\u0006\u0004\b\u001e\u0010#J\u0010\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u000b\u0010V\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010[\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0011\u0010\\\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012HÆ\u0003J\u0011\u0010]\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0012HÆ\u0003J\u0010\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010_\u001a\u0004\u0018\u00010\u0016HÆ\u0003¢\u0006\u0002\u0010FJ\u000b\u0010`\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u001bHÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u001dHÆ\u0003Jæ\u0001\u0010d\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\u0010\b\u0003\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00122\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\b2\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÆ\u0001¢\u0006\u0002\u0010eJ\u0014\u0010f\u001a\u00020\u00162\b\u0010g\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010h\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010i\u001a\u00020\bHÖ\u0081\u0004J%\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020\u00002\u0006\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020pH\u0001¢\u0006\u0002\bqR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010%\u001a\u0004\b*\u0010+R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\b,\u0010%\u001a\u0004\b-\u0010'R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010%\u001a\u0004\b/\u00100R\u001e\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u0010%\u001a\u0004\b2\u00103R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010%\u001a\u0004\b5\u00106R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b7\u0010%\u001a\u0004\b8\u00109R \u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\b:\u0010%\u001a\u0004\b;\u0010'R \u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\b<\u0010%\u001a\u0004\b=\u0010'R$\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b>\u0010%\u001a\u0004\b?\u0010@R$\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bA\u0010%\u001a\u0004\bB\u0010@R \u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010(\u0012\u0004\bC\u0010%\u001a\u0004\bD\u0010'R \u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010G\u0012\u0004\bE\u0010%\u001a\u0004\b\u0015\u0010FR\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bH\u0010%\u001a\u0004\bI\u0010JR\u001e\u0010\u0019\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bK\u0010%\u001a\u0004\bL\u00100R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u001b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bM\u0010%\u001a\u0004\bN\u0010OR\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u001d8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bP\u0010%\u001a\u0004\bQ\u0010R¨\u0006t"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;", "", "id", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "idMal", "format", "", "nextAiringEpisode", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "relations", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;", "coverImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", TypedValues.TransitionType.S_DURATION, "episodes", "genres", "", "synonyms", "averageScore", "isAdult", "", "trailer", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;", "description", "characters", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;", "recommendations", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;", "<init>", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Boolean;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Boolean;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "getIdMal$annotations", "getIdMal", "getFormat$annotations", "getFormat", "()Ljava/lang/String;", "getNextAiringEpisode$annotations", "getNextAiringEpisode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "getRelations$annotations", "getRelations", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;", "getCoverImage$annotations", "getCoverImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "getDuration$annotations", "getDuration", "getEpisodes$annotations", "getEpisodes", "getGenres$annotations", "getGenres", "()Ljava/util/List;", "getSynonyms$annotations", "getSynonyms", "getAverageScore$annotations", "getAverageScore", "isAdult$annotations", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getTrailer$annotations", "getTrailer", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;", "getDescription$annotations", "getDescription", "getCharacters$annotations", "getCharacters", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;", "getRecommendations$annotations", "getRecommendations", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Boolean;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonMedia {

        @JsonProperty("averageScore")
        private final Integer averageScore;

        @JsonProperty("characters")
        private final CharacterConnection characters;

        @JsonProperty("coverImage")
        private final MediaCoverImage coverImage;

        @JsonProperty("description")
        private final String description;

        @JsonProperty(TypedValues.TransitionType.S_DURATION)
        private final Integer duration;

        @JsonProperty("episodes")
        private final Integer episodes;

        @JsonProperty("format")
        private final String format;

        @JsonProperty("genres")
        private final List<String> genres;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("idMal")
        private final Integer idMal;

        @JsonProperty("isAdult")
        private final Boolean isAdult;

        @JsonProperty("nextAiringEpisode")
        private final SeasonNextAiringEpisode nextAiringEpisode;

        @JsonProperty("recommendations")
        private final RecommendationConnection recommendations;

        @JsonProperty("relations")
        private final SeasonEdges relations;

        @JsonProperty("synonyms")
        private final List<String> synonyms;

        @JsonProperty("title")
        private final MediaTitle title;

        @JsonProperty("trailer")
        private final MediaTrailer trailer;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$SeasonMedia$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.SeasonMedia._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$SeasonMedia$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.SeasonMedia._childSerializers$_anonymous_$0();
            }
        }), null, null, null, null, null, null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SeasonMedia copy$default(SeasonMedia seasonMedia, Integer num, MediaTitle mediaTitle, Integer num2, String str, SeasonNextAiringEpisode seasonNextAiringEpisode, SeasonEdges seasonEdges, MediaCoverImage mediaCoverImage, Integer num3, Integer num4, List list, List list2, Integer num5, Boolean bool, MediaTrailer mediaTrailer, String str2, CharacterConnection characterConnection, RecommendationConnection recommendationConnection, int i, Object obj) {
            RecommendationConnection recommendationConnection2;
            CharacterConnection characterConnection2;
            Integer num6 = (i & 1) != 0 ? seasonMedia.id : num;
            MediaTitle mediaTitle2 = (i & 2) != 0 ? seasonMedia.title : mediaTitle;
            Integer num7 = (i & 4) != 0 ? seasonMedia.idMal : num2;
            String str3 = (i & 8) != 0 ? seasonMedia.format : str;
            SeasonNextAiringEpisode seasonNextAiringEpisode2 = (i & 16) != 0 ? seasonMedia.nextAiringEpisode : seasonNextAiringEpisode;
            SeasonEdges seasonEdges2 = (i & 32) != 0 ? seasonMedia.relations : seasonEdges;
            MediaCoverImage mediaCoverImage2 = (i & 64) != 0 ? seasonMedia.coverImage : mediaCoverImage;
            Integer num8 = (i & 128) != 0 ? seasonMedia.duration : num3;
            Integer num9 = (i & 256) != 0 ? seasonMedia.episodes : num4;
            List list3 = (i & 512) != 0 ? seasonMedia.genres : list;
            List list4 = (i & 1024) != 0 ? seasonMedia.synonyms : list2;
            Integer num10 = (i & 2048) != 0 ? seasonMedia.averageScore : num5;
            Boolean bool2 = (i & 4096) != 0 ? seasonMedia.isAdult : bool;
            MediaTrailer mediaTrailer2 = (i & 8192) != 0 ? seasonMedia.trailer : mediaTrailer;
            Integer num11 = num6;
            String str4 = (i & 16384) != 0 ? seasonMedia.description : str2;
            CharacterConnection characterConnection3 = (i & 32768) != 0 ? seasonMedia.characters : characterConnection;
            if ((i & 65536) != 0) {
                characterConnection2 = characterConnection3;
                recommendationConnection2 = seasonMedia.recommendations;
            } else {
                recommendationConnection2 = recommendationConnection;
                characterConnection2 = characterConnection3;
            }
            return seasonMedia.copy(num11, mediaTitle2, num7, str3, seasonNextAiringEpisode2, seasonEdges2, mediaCoverImage2, num8, num9, list3, list4, num10, bool2, mediaTrailer2, str4, characterConnection2, recommendationConnection2);
        }

        @SerialName("averageScore")
        public static /* synthetic */ void getAverageScore$annotations() {
        }

        @SerialName("characters")
        public static /* synthetic */ void getCharacters$annotations() {
        }

        @SerialName("coverImage")
        public static /* synthetic */ void getCoverImage$annotations() {
        }

        @SerialName("description")
        public static /* synthetic */ void getDescription$annotations() {
        }

        @SerialName(TypedValues.TransitionType.S_DURATION)
        public static /* synthetic */ void getDuration$annotations() {
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("format")
        public static /* synthetic */ void getFormat$annotations() {
        }

        @SerialName("genres")
        public static /* synthetic */ void getGenres$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("idMal")
        public static /* synthetic */ void getIdMal$annotations() {
        }

        @SerialName("nextAiringEpisode")
        public static /* synthetic */ void getNextAiringEpisode$annotations() {
        }

        @SerialName("recommendations")
        public static /* synthetic */ void getRecommendations$annotations() {
        }

        @SerialName("relations")
        public static /* synthetic */ void getRelations$annotations() {
        }

        @SerialName("synonyms")
        public static /* synthetic */ void getSynonyms$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("trailer")
        public static /* synthetic */ void getTrailer$annotations() {
        }

        @SerialName("isAdult")
        public static /* synthetic */ void isAdult$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        public final List<String> component10() {
            return this.genres;
        }

        public final List<String> component11() {
            return this.synonyms;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getAverageScore() {
            return this.averageScore;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Boolean getIsAdult() {
            return this.isAdult;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final MediaTrailer getTrailer() {
            return this.trailer;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final CharacterConnection getCharacters() {
            return this.characters;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final RecommendationConnection getRecommendations() {
            return this.recommendations;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaTitle getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getIdMal() {
            return this.idMal;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final SeasonNextAiringEpisode getNextAiringEpisode() {
            return this.nextAiringEpisode;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final SeasonEdges getRelations() {
            return this.relations;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final MediaCoverImage getCoverImage() {
            return this.coverImage;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getDuration() {
            return this.duration;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getEpisodes() {
            return this.episodes;
        }

        public final SeasonMedia copy(@JsonProperty("id") Integer id, @JsonProperty("title") MediaTitle title, @JsonProperty("idMal") Integer idMal, @JsonProperty("format") String format, @JsonProperty("nextAiringEpisode") SeasonNextAiringEpisode nextAiringEpisode, @JsonProperty("relations") SeasonEdges relations, @JsonProperty("coverImage") MediaCoverImage coverImage, @JsonProperty(TypedValues.TransitionType.S_DURATION) Integer duration, @JsonProperty("episodes") Integer episodes, @JsonProperty("genres") List<String> genres, @JsonProperty("synonyms") List<String> synonyms, @JsonProperty("averageScore") Integer averageScore, @JsonProperty("isAdult") Boolean isAdult, @JsonProperty("trailer") MediaTrailer trailer, @JsonProperty("description") String description, @JsonProperty("characters") CharacterConnection characters, @JsonProperty("recommendations") RecommendationConnection recommendations) {
            return new SeasonMedia(id, title, idMal, format, nextAiringEpisode, relations, coverImage, duration, episodes, genres, synonyms, averageScore, isAdult, trailer, description, characters, recommendations);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonMedia)) {
                return false;
            }
            SeasonMedia seasonMedia = (SeasonMedia) other;
            return Intrinsics.areEqual(this.id, seasonMedia.id) && Intrinsics.areEqual(this.title, seasonMedia.title) && Intrinsics.areEqual(this.idMal, seasonMedia.idMal) && Intrinsics.areEqual(this.format, seasonMedia.format) && Intrinsics.areEqual(this.nextAiringEpisode, seasonMedia.nextAiringEpisode) && Intrinsics.areEqual(this.relations, seasonMedia.relations) && Intrinsics.areEqual(this.coverImage, seasonMedia.coverImage) && Intrinsics.areEqual(this.duration, seasonMedia.duration) && Intrinsics.areEqual(this.episodes, seasonMedia.episodes) && Intrinsics.areEqual(this.genres, seasonMedia.genres) && Intrinsics.areEqual(this.synonyms, seasonMedia.synonyms) && Intrinsics.areEqual(this.averageScore, seasonMedia.averageScore) && Intrinsics.areEqual(this.isAdult, seasonMedia.isAdult) && Intrinsics.areEqual(this.trailer, seasonMedia.trailer) && Intrinsics.areEqual(this.description, seasonMedia.description) && Intrinsics.areEqual(this.characters, seasonMedia.characters) && Intrinsics.areEqual(this.recommendations, seasonMedia.recommendations);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            MediaTitle mediaTitle = this.title;
            int iHashCode2 = (iHashCode + (mediaTitle == null ? 0 : mediaTitle.hashCode())) * 31;
            Integer num2 = this.idMal;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str = this.format;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            SeasonNextAiringEpisode seasonNextAiringEpisode = this.nextAiringEpisode;
            int iHashCode5 = (iHashCode4 + (seasonNextAiringEpisode == null ? 0 : seasonNextAiringEpisode.hashCode())) * 31;
            SeasonEdges seasonEdges = this.relations;
            int iHashCode6 = (iHashCode5 + (seasonEdges == null ? 0 : seasonEdges.hashCode())) * 31;
            MediaCoverImage mediaCoverImage = this.coverImage;
            int iHashCode7 = (iHashCode6 + (mediaCoverImage == null ? 0 : mediaCoverImage.hashCode())) * 31;
            Integer num3 = this.duration;
            int iHashCode8 = (iHashCode7 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.episodes;
            int iHashCode9 = (iHashCode8 + (num4 == null ? 0 : num4.hashCode())) * 31;
            List<String> list = this.genres;
            int iHashCode10 = (iHashCode9 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.synonyms;
            int iHashCode11 = (iHashCode10 + (list2 == null ? 0 : list2.hashCode())) * 31;
            Integer num5 = this.averageScore;
            int iHashCode12 = (iHashCode11 + (num5 == null ? 0 : num5.hashCode())) * 31;
            Boolean bool = this.isAdult;
            int iHashCode13 = (iHashCode12 + (bool == null ? 0 : bool.hashCode())) * 31;
            MediaTrailer mediaTrailer = this.trailer;
            int iHashCode14 = (iHashCode13 + (mediaTrailer == null ? 0 : mediaTrailer.hashCode())) * 31;
            String str2 = this.description;
            int iHashCode15 = (iHashCode14 + (str2 == null ? 0 : str2.hashCode())) * 31;
            CharacterConnection characterConnection = this.characters;
            int iHashCode16 = (iHashCode15 + (characterConnection == null ? 0 : characterConnection.hashCode())) * 31;
            RecommendationConnection recommendationConnection = this.recommendations;
            return iHashCode16 + (recommendationConnection != null ? recommendationConnection.hashCode() : 0);
        }

        public String toString() {
            return "SeasonMedia(id=" + this.id + ", title=" + this.title + ", idMal=" + this.idMal + ", format=" + this.format + ", nextAiringEpisode=" + this.nextAiringEpisode + ", relations=" + this.relations + ", coverImage=" + this.coverImage + ", duration=" + this.duration + ", episodes=" + this.episodes + ", genres=" + this.genres + ", synonyms=" + this.synonyms + ", averageScore=" + this.averageScore + ", isAdult=" + this.isAdult + ", trailer=" + this.trailer + ", description=" + this.description + ", characters=" + this.characters + ", recommendations=" + this.recommendations + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonMedia;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonMedia> serializer() {
                return AniListApi$SeasonMedia$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonMedia(int i, Integer num, MediaTitle mediaTitle, Integer num2, String str, SeasonNextAiringEpisode seasonNextAiringEpisode, SeasonEdges seasonEdges, MediaCoverImage mediaCoverImage, Integer num3, Integer num4, List list, List list2, Integer num5, Boolean bool, MediaTrailer mediaTrailer, String str2, CharacterConnection characterConnection, RecommendationConnection recommendationConnection, SerializationConstructorMarker serializationConstructorMarker) {
            if (131071 != (i & 131071)) {
                PluginExceptionsKt.throwMissingFieldException(i, 131071, AniListApi$SeasonMedia$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.title = mediaTitle;
            this.idMal = num2;
            this.format = str;
            this.nextAiringEpisode = seasonNextAiringEpisode;
            this.relations = seasonEdges;
            this.coverImage = mediaCoverImage;
            this.duration = num3;
            this.episodes = num4;
            this.genres = list;
            this.synonyms = list2;
            this.averageScore = num5;
            this.isAdult = bool;
            this.trailer = mediaTrailer;
            this.description = str2;
            this.characters = characterConnection;
            this.recommendations = recommendationConnection;
        }

        public SeasonMedia(@JsonProperty("id") Integer num, @JsonProperty("title") MediaTitle mediaTitle, @JsonProperty("idMal") Integer num2, @JsonProperty("format") String str, @JsonProperty("nextAiringEpisode") SeasonNextAiringEpisode seasonNextAiringEpisode, @JsonProperty("relations") SeasonEdges seasonEdges, @JsonProperty("coverImage") MediaCoverImage mediaCoverImage, @JsonProperty(TypedValues.TransitionType.S_DURATION) Integer num3, @JsonProperty("episodes") Integer num4, @JsonProperty("genres") List<String> list, @JsonProperty("synonyms") List<String> list2, @JsonProperty("averageScore") Integer num5, @JsonProperty("isAdult") Boolean bool, @JsonProperty("trailer") MediaTrailer mediaTrailer, @JsonProperty("description") String str2, @JsonProperty("characters") CharacterConnection characterConnection, @JsonProperty("recommendations") RecommendationConnection recommendationConnection) {
            this.id = num;
            this.title = mediaTitle;
            this.idMal = num2;
            this.format = str;
            this.nextAiringEpisode = seasonNextAiringEpisode;
            this.relations = seasonEdges;
            this.coverImage = mediaCoverImage;
            this.duration = num3;
            this.episodes = num4;
            this.genres = list;
            this.synonyms = list2;
            this.averageScore = num5;
            this.isAdult = bool;
            this.trailer = mediaTrailer;
            this.description = str2;
            this.characters = characterConnection;
            this.recommendations = recommendationConnection;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SeasonMedia self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$MediaTitle$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.idMal);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.format);
            output.encodeNullableSerializableElement(serialDesc, 4, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, self.nextAiringEpisode);
            output.encodeNullableSerializableElement(serialDesc, 5, AniListApi$SeasonEdges$$serializer.INSTANCE, self.relations);
            output.encodeNullableSerializableElement(serialDesc, 6, AniListApi$MediaCoverImage$$serializer.INSTANCE, self.coverImage);
            output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.duration);
            output.encodeNullableSerializableElement(serialDesc, 8, IntSerializer.INSTANCE, self.episodes);
            output.encodeNullableSerializableElement(serialDesc, 9, lazyArr[9].getValue(), self.genres);
            output.encodeNullableSerializableElement(serialDesc, 10, lazyArr[10].getValue(), self.synonyms);
            output.encodeNullableSerializableElement(serialDesc, 11, IntSerializer.INSTANCE, self.averageScore);
            output.encodeNullableSerializableElement(serialDesc, 12, BooleanSerializer.INSTANCE, self.isAdult);
            output.encodeNullableSerializableElement(serialDesc, 13, AniListApi$MediaTrailer$$serializer.INSTANCE, self.trailer);
            output.encodeNullableSerializableElement(serialDesc, 14, StringSerializer.INSTANCE, self.description);
            output.encodeNullableSerializableElement(serialDesc, 15, AniListApi$CharacterConnection$$serializer.INSTANCE, self.characters);
            output.encodeNullableSerializableElement(serialDesc, 16, AniListApi$RecommendationConnection$$serializer.INSTANCE, self.recommendations);
        }

        public final Integer getId() {
            return this.id;
        }

        public final MediaTitle getTitle() {
            return this.title;
        }

        public final Integer getIdMal() {
            return this.idMal;
        }

        public final String getFormat() {
            return this.format;
        }

        public final SeasonNextAiringEpisode getNextAiringEpisode() {
            return this.nextAiringEpisode;
        }

        public final SeasonEdges getRelations() {
            return this.relations;
        }

        public final MediaCoverImage getCoverImage() {
            return this.coverImage;
        }

        public final Integer getDuration() {
            return this.duration;
        }

        public final Integer getEpisodes() {
            return this.episodes;
        }

        public final List<String> getGenres() {
            return this.genres;
        }

        public final List<String> getSynonyms() {
            return this.synonyms;
        }

        public final Integer getAverageScore() {
            return this.averageScore;
        }

        public final Boolean isAdult() {
            return this.isAdult;
        }

        public final MediaTrailer getTrailer() {
            return this.trailer;
        }

        public final String getDescription() {
            return this.description;
        }

        public final CharacterConnection getCharacters() {
            return this.characters;
        }

        public final RecommendationConnection getRecommendations() {
            return this.recommendations;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B'\u0012\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bB;\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\u0016\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;", "", "edges", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationEdge;", "nodes", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEdges$annotations", "()V", "getEdges", "()Ljava/util/List;", "getNodes$annotations", "getNodes", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class RecommendationConnection {

        @JsonProperty("edges")
        private final List<RecommendationEdge> edges;

        @JsonProperty("nodes")
        private final List<Recommendation> nodes;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$RecommendationConnection$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.RecommendationConnection._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$RecommendationConnection$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.RecommendationConnection._childSerializers$_anonymous_$0();
            }
        })};

        /* JADX WARN: Multi-variable type inference failed */
        public RecommendationConnection() {
            this((List) null, (List) (0 == true ? 1 : 0), 3, (DefaultConstructorMarker) (0 == true ? 1 : 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$RecommendationEdge$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(AniListApi$Recommendation$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ RecommendationConnection copy$default(RecommendationConnection recommendationConnection, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = recommendationConnection.edges;
            }
            if ((i & 2) != 0) {
                list2 = recommendationConnection.nodes;
            }
            return recommendationConnection.copy(list, list2);
        }

        @SerialName("edges")
        public static /* synthetic */ void getEdges$annotations() {
        }

        @SerialName("nodes")
        public static /* synthetic */ void getNodes$annotations() {
        }

        public final List<RecommendationEdge> component1() {
            return this.edges;
        }

        public final List<Recommendation> component2() {
            return this.nodes;
        }

        public final RecommendationConnection copy(@JsonProperty("edges") List<RecommendationEdge> edges, @JsonProperty("nodes") List<Recommendation> nodes) {
            Intrinsics.checkNotNullParameter(edges, "edges");
            Intrinsics.checkNotNullParameter(nodes, "nodes");
            return new RecommendationConnection(edges, nodes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommendationConnection)) {
                return false;
            }
            RecommendationConnection recommendationConnection = (RecommendationConnection) other;
            return Intrinsics.areEqual(this.edges, recommendationConnection.edges) && Intrinsics.areEqual(this.nodes, recommendationConnection.nodes);
        }

        public int hashCode() {
            return (this.edges.hashCode() * 31) + this.nodes.hashCode();
        }

        public String toString() {
            return "RecommendationConnection(edges=" + this.edges + ", nodes=" + this.nodes + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationConnection;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<RecommendationConnection> serializer() {
                return AniListApi$RecommendationConnection$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ RecommendationConnection(int i, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
            this.edges = (i & 1) == 0 ? CollectionsKt.emptyList() : list;
            if ((i & 2) == 0) {
                this.nodes = CollectionsKt.emptyList();
            } else {
                this.nodes = list2;
            }
        }

        public RecommendationConnection(@JsonProperty("edges") List<RecommendationEdge> edges, @JsonProperty("nodes") List<Recommendation> nodes) {
            Intrinsics.checkNotNullParameter(edges, "edges");
            Intrinsics.checkNotNullParameter(nodes, "nodes");
            this.edges = edges;
            this.nodes = nodes;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(RecommendationConnection self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.edges, CollectionsKt.emptyList())) {
                output.encodeSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.edges);
            }
            if (!output.shouldEncodeElementDefault(serialDesc, 1) && Intrinsics.areEqual(self.nodes, CollectionsKt.emptyList())) {
                return;
            }
            output.encodeSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.nodes);
        }

        public /* synthetic */ RecommendationConnection(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2);
        }

        public final List<RecommendationEdge> getEdges() {
            return this.edges;
        }

        public final List<Recommendation> getNodes() {
            return this.nodes;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationEdge;", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNode$annotations", "()V", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class RecommendationEdge {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("node")
        private final Recommendation node;

        public static /* synthetic */ RecommendationEdge copy$default(RecommendationEdge recommendationEdge, Recommendation recommendation, int i, Object obj) {
            if ((i & 1) != 0) {
                recommendation = recommendationEdge.node;
            }
            return recommendationEdge.copy(recommendation);
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Recommendation getNode() {
            return this.node;
        }

        public final RecommendationEdge copy(@JsonProperty("node") Recommendation node) {
            Intrinsics.checkNotNullParameter(node, "node");
            return new RecommendationEdge(node);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof RecommendationEdge) && Intrinsics.areEqual(this.node, ((RecommendationEdge) other).node);
        }

        public int hashCode() {
            return this.node.hashCode();
        }

        public String toString() {
            return "RecommendationEdge(node=" + this.node + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationEdge$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendationEdge;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<RecommendationEdge> serializer() {
                return AniListApi$RecommendationEdge$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ RecommendationEdge(int i, Recommendation recommendation, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$RecommendationEdge$$serializer.INSTANCE.getDescriptor());
            }
            this.node = recommendation;
        }

        public RecommendationEdge(@JsonProperty("node") Recommendation node) {
            Intrinsics.checkNotNullParameter(node, "node");
            this.node = node;
        }

        public final Recommendation getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;", "", "mediaRecommendation", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMediaRecommendation$annotations", "()V", "getMediaRecommendation", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$RecommendedMedia;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Recommendation {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("mediaRecommendation")
        private final RecommendedMedia mediaRecommendation;

        public static /* synthetic */ Recommendation copy$default(Recommendation recommendation, RecommendedMedia recommendedMedia, int i, Object obj) {
            if ((i & 1) != 0) {
                recommendedMedia = recommendation.mediaRecommendation;
            }
            return recommendation.copy(recommendedMedia);
        }

        @SerialName("mediaRecommendation")
        public static /* synthetic */ void getMediaRecommendation$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final RecommendedMedia getMediaRecommendation() {
            return this.mediaRecommendation;
        }

        public final Recommendation copy(@JsonProperty("mediaRecommendation") RecommendedMedia mediaRecommendation) {
            return new Recommendation(mediaRecommendation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Recommendation) && Intrinsics.areEqual(this.mediaRecommendation, ((Recommendation) other).mediaRecommendation);
        }

        public int hashCode() {
            RecommendedMedia recommendedMedia = this.mediaRecommendation;
            if (recommendedMedia == null) {
                return 0;
            }
            return recommendedMedia.hashCode();
        }

        public String toString() {
            return "Recommendation(mediaRecommendation=" + this.mediaRecommendation + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendation;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Recommendation> serializer() {
                return AniListApi$Recommendation$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Recommendation(int i, RecommendedMedia recommendedMedia, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$Recommendation$$serializer.INSTANCE.getDescriptor());
            }
            this.mediaRecommendation = recommendedMedia;
        }

        public Recommendation(@JsonProperty("mediaRecommendation") RecommendedMedia recommendedMedia) {
            this.mediaRecommendation = recommendedMedia;
        }

        public final RecommendedMedia getMediaRecommendation() {
            return this.mediaRecommendation;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 =2\u00020\u0001:\u0002<=Bs\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0001\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rBw\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\f\u0010\u0012J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ju\u0010.\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0003\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u00020\u000fHÖ\u0081\u0004J\n\u00103\u001a\u00020\u0003HÖ\u0081\u0004J%\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00002\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0001¢\u0006\u0002\b;R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u0016R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001e\u0010\u0016R$\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0014\u001a\u0004\b \u0010!R$\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\"\u0010\u0014\u001a\u0004\b#\u0010!R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0014\u001a\u0004\b%\u0010\u0016¨\u0006>"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName;", "", "first", "", "middle", "last", "full", "native", "alternative", "", "alternativeSpoiler", "userPreferred", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFirst$annotations", "()V", "getFirst", "()Ljava/lang/String;", "getMiddle$annotations", "getMiddle", "getLast$annotations", "getLast", "getFull$annotations", "getFull", "getNative$annotations", "getNative", "getAlternative$annotations", "getAlternative", "()Ljava/util/List;", "getAlternativeSpoiler$annotations", "getAlternativeSpoiler", "getUserPreferred$annotations", "getUserPreferred", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CharacterName {

        @JsonProperty("alternative")
        private final List<String> alternative;

        @JsonProperty("alternativeSpoiler")
        private final List<String> alternativeSpoiler;

        @JsonProperty("name")
        private final String first;

        @JsonProperty("full")
        private final String full;

        @JsonProperty("last")
        private final String last;

        @JsonProperty("middle")
        private final String middle;

        @JsonProperty("native")
        private final String native;

        @JsonProperty("userPreferred")
        private final String userPreferred;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$CharacterName$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.CharacterName._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$CharacterName$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.CharacterName._childSerializers$_anonymous_$0();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CharacterName copy$default(CharacterName characterName, String str, String str2, String str3, String str4, String str5, List list, List list2, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = characterName.first;
            }
            if ((i & 2) != 0) {
                str2 = characterName.middle;
            }
            if ((i & 4) != 0) {
                str3 = characterName.last;
            }
            if ((i & 8) != 0) {
                str4 = characterName.full;
            }
            if ((i & 16) != 0) {
                str5 = characterName.native;
            }
            if ((i & 32) != 0) {
                list = characterName.alternative;
            }
            if ((i & 64) != 0) {
                list2 = characterName.alternativeSpoiler;
            }
            if ((i & 128) != 0) {
                str6 = characterName.userPreferred;
            }
            List list3 = list2;
            String str7 = str6;
            String str8 = str5;
            List list4 = list;
            return characterName.copy(str, str2, str3, str4, str8, list4, list3, str7);
        }

        @SerialName("alternative")
        public static /* synthetic */ void getAlternative$annotations() {
        }

        @SerialName("alternativeSpoiler")
        public static /* synthetic */ void getAlternativeSpoiler$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getFirst$annotations() {
        }

        @SerialName("full")
        public static /* synthetic */ void getFull$annotations() {
        }

        @SerialName("last")
        public static /* synthetic */ void getLast$annotations() {
        }

        @SerialName("middle")
        public static /* synthetic */ void getMiddle$annotations() {
        }

        @SerialName("native")
        public static /* synthetic */ void getNative$annotations() {
        }

        @SerialName("userPreferred")
        public static /* synthetic */ void getUserPreferred$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFirst() {
            return this.first;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMiddle() {
            return this.middle;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLast() {
            return this.last;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFull() {
            return this.full;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getNative() {
            return this.native;
        }

        public final List<String> component6() {
            return this.alternative;
        }

        public final List<String> component7() {
            return this.alternativeSpoiler;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getUserPreferred() {
            return this.userPreferred;
        }

        public final CharacterName copy(@JsonProperty("name") String first, @JsonProperty("middle") String middle, @JsonProperty("last") String last, @JsonProperty("full") String full, @JsonProperty("native") String str, @JsonProperty("alternative") List<String> alternative, @JsonProperty("alternativeSpoiler") List<String> alternativeSpoiler, @JsonProperty("userPreferred") String userPreferred) {
            return new CharacterName(first, middle, last, full, str, alternative, alternativeSpoiler, userPreferred);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CharacterName)) {
                return false;
            }
            CharacterName characterName = (CharacterName) other;
            return Intrinsics.areEqual(this.first, characterName.first) && Intrinsics.areEqual(this.middle, characterName.middle) && Intrinsics.areEqual(this.last, characterName.last) && Intrinsics.areEqual(this.full, characterName.full) && Intrinsics.areEqual(this.native, characterName.native) && Intrinsics.areEqual(this.alternative, characterName.alternative) && Intrinsics.areEqual(this.alternativeSpoiler, characterName.alternativeSpoiler) && Intrinsics.areEqual(this.userPreferred, characterName.userPreferred);
        }

        public int hashCode() {
            String str = this.first;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.middle;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.last;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.full;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.native;
            int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            List<String> list = this.alternative;
            int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.alternativeSpoiler;
            int iHashCode7 = (iHashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
            String str6 = this.userPreferred;
            return iHashCode7 + (str6 != null ? str6.hashCode() : 0);
        }

        public String toString() {
            return "CharacterName(first=" + this.first + ", middle=" + this.middle + ", last=" + this.last + ", full=" + this.full + ", native=" + this.native + ", alternative=" + this.alternative + ", alternativeSpoiler=" + this.alternativeSpoiler + ", userPreferred=" + this.userPreferred + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CharacterName> serializer() {
                return AniListApi$CharacterName$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CharacterName(int i, String str, String str2, String str3, String str4, String str5, List list, List list2, String str6, SerializationConstructorMarker serializationConstructorMarker) {
            if (255 != (i & 255)) {
                PluginExceptionsKt.throwMissingFieldException(i, 255, AniListApi$CharacterName$$serializer.INSTANCE.getDescriptor());
            }
            this.first = str;
            this.middle = str2;
            this.last = str3;
            this.full = str4;
            this.native = str5;
            this.alternative = list;
            this.alternativeSpoiler = list2;
            this.userPreferred = str6;
        }

        public CharacterName(@JsonProperty("name") String str, @JsonProperty("middle") String str2, @JsonProperty("last") String str3, @JsonProperty("full") String str4, @JsonProperty("native") String str5, @JsonProperty("alternative") List<String> list, @JsonProperty("alternativeSpoiler") List<String> list2, @JsonProperty("userPreferred") String str6) {
            this.first = str;
            this.middle = str2;
            this.last = str3;
            this.full = str4;
            this.native = str5;
            this.alternative = list;
            this.alternativeSpoiler = list2;
            this.userPreferred = str6;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CharacterName self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.first);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.middle);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.last);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.full);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.native);
            output.encodeNullableSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.alternative);
            output.encodeNullableSerializableElement(serialDesc, 6, lazyArr[6].getValue(), self.alternativeSpoiler);
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.userPreferred);
        }

        public final String getFirst() {
            return this.first;
        }

        public final String getMiddle() {
            return this.middle;
        }

        public final String getLast() {
            return this.last;
        }

        public final String getFull() {
            return this.full;
        }

        public final String getNative() {
            return this.native;
        }

        public final List<String> getAlternative() {
            return this.alternative;
        }

        public final List<String> getAlternativeSpoiler() {
            return this.alternativeSpoiler;
        }

        public final String getUserPreferred() {
            return this.userPreferred;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage;", "", "large", "", "medium", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLarge$annotations", "()V", "getLarge", "()Ljava/lang/String;", "getMedium$annotations", "getMedium", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CharacterImage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ CharacterImage copy$default(CharacterImage characterImage, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = characterImage.large;
            }
            if ((i & 2) != 0) {
                str2 = characterImage.medium;
            }
            return characterImage.copy(str, str2);
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

        public final CharacterImage copy(@JsonProperty("large") String large, @JsonProperty("medium") String medium) {
            return new CharacterImage(large, medium);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CharacterImage)) {
                return false;
            }
            CharacterImage characterImage = (CharacterImage) other;
            return Intrinsics.areEqual(this.large, characterImage.large) && Intrinsics.areEqual(this.medium, characterImage.medium);
        }

        public int hashCode() {
            String str = this.large;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.medium;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "CharacterImage(large=" + this.large + ", medium=" + this.medium + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CharacterImage> serializer() {
                return AniListApi$CharacterImage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CharacterImage(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$CharacterImage$$serializer.INSTANCE.getDescriptor());
            }
            this.large = str;
            this.medium = str2;
        }

        public CharacterImage(@JsonProperty("large") String str, @JsonProperty("medium") String str2) {
            this.large = str;
            this.medium = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CharacterImage self, CompositeEncoder output, SerialDescriptor serialDesc) {
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

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;", "", "name", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName;", "age", "", "image", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName$annotations", "()V", "getName", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterName;", "getAge$annotations", "getAge", "()Ljava/lang/String;", "getImage$annotations", "getImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterImage;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Character {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("age")
        private final String age;

        @JsonProperty("image")
        private final CharacterImage image;

        @JsonProperty("name")
        private final CharacterName name;

        public static /* synthetic */ Character copy$default(Character character, CharacterName characterName, String str, CharacterImage characterImage, int i, Object obj) {
            if ((i & 1) != 0) {
                characterName = character.name;
            }
            if ((i & 2) != 0) {
                str = character.age;
            }
            if ((i & 4) != 0) {
                characterImage = character.image;
            }
            return character.copy(characterName, str, characterImage);
        }

        @SerialName("age")
        public static /* synthetic */ void getAge$annotations() {
        }

        @SerialName("image")
        public static /* synthetic */ void getImage$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final CharacterName getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAge() {
            return this.age;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final CharacterImage getImage() {
            return this.image;
        }

        public final Character copy(@JsonProperty("name") CharacterName name, @JsonProperty("age") String age, @JsonProperty("image") CharacterImage image) {
            return new Character(name, age, image);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Character)) {
                return false;
            }
            Character character = (Character) other;
            return Intrinsics.areEqual(this.name, character.name) && Intrinsics.areEqual(this.age, character.age) && Intrinsics.areEqual(this.image, character.image);
        }

        public int hashCode() {
            CharacterName characterName = this.name;
            int iHashCode = (characterName == null ? 0 : characterName.hashCode()) * 31;
            String str = this.age;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            CharacterImage characterImage = this.image;
            return iHashCode2 + (characterImage != null ? characterImage.hashCode() : 0);
        }

        public String toString() {
            return "Character(name=" + this.name + ", age=" + this.age + ", image=" + this.image + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Character> serializer() {
                return AniListApi$Character$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Character(int i, CharacterName characterName, String str, CharacterImage characterImage, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$Character$$serializer.INSTANCE.getDescriptor());
            }
            this.name = characterName;
            this.age = str;
            this.image = characterImage;
        }

        public Character(@JsonProperty("name") CharacterName characterName, @JsonProperty("age") String str, @JsonProperty("image") CharacterImage characterImage) {
            this.name = characterName;
            this.age = str;
            this.image = characterImage;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Character self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, AniListApi$CharacterName$$serializer.INSTANCE, self.name);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.age);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$CharacterImage$$serializer.INSTANCE, self.image);
        }

        public final CharacterName getName() {
            return this.name;
        }

        public final String getAge() {
            return this.age;
        }

        public final CharacterImage getImage() {
            return this.image;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 @2\u00020\u0001:\u0002?@Bg\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010Bm\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u000f\u0010\u0014J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0011\u0010.\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\bHÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u000eHÆ\u0003Jn\u00100\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u00101J\u0014\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00105\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00106\u001a\u00020\u0005HÖ\u0081\u0004J%\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001e\u0010\u001cR$\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010!R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\"\u0010\u0016\u001a\u0004\b#\u0010\u0018R$\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0016\u001a\u0004\b%\u0010!R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u0016\u001a\u0004\b'\u0010(¨\u0006A"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterEdge;", "", "id", "", "role", "", "name", "voiceActors", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Staff;", "favouriteOrder", LinkHeader.Parameters.Media, "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterMedia;", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRole$annotations", "getRole", "()Ljava/lang/String;", "getName$annotations", "getName", "getVoiceActors$annotations", "getVoiceActors", "()Ljava/util/List;", "getFavouriteOrder$annotations", "getFavouriteOrder", "getMedia$annotations", "getMedia", "getNode$annotations", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterEdge;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CharacterEdge {

        @JsonProperty("favouriteOrder")
        private final Integer favouriteOrder;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty(LinkHeader.Parameters.Media)
        private final List<CharacterMedia> media;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("node")
        private final Character node;

        @JsonProperty("role")
        private final String role;

        @JsonProperty("voiceActors")
        private final List<Staff> voiceActors;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$CharacterEdge$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.CharacterEdge._childSerializers$_anonymous_();
            }
        }), null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$CharacterEdge$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.CharacterEdge._childSerializers$_anonymous_$0();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$Staff$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(AniListApi$CharacterMedia$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CharacterEdge copy$default(CharacterEdge characterEdge, Integer num, String str, String str2, List list, Integer num2, List list2, Character character, int i, Object obj) {
            if ((i & 1) != 0) {
                num = characterEdge.id;
            }
            if ((i & 2) != 0) {
                str = characterEdge.role;
            }
            if ((i & 4) != 0) {
                str2 = characterEdge.name;
            }
            if ((i & 8) != 0) {
                list = characterEdge.voiceActors;
            }
            if ((i & 16) != 0) {
                num2 = characterEdge.favouriteOrder;
            }
            if ((i & 32) != 0) {
                list2 = characterEdge.media;
            }
            if ((i & 64) != 0) {
                character = characterEdge.node;
            }
            List list3 = list2;
            Character character2 = character;
            Integer num3 = num2;
            String str3 = str2;
            return characterEdge.copy(num, str, str3, list, num3, list3, character2);
        }

        @SerialName("favouriteOrder")
        public static /* synthetic */ void getFavouriteOrder$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName(LinkHeader.Parameters.Media)
        public static /* synthetic */ void getMedia$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        @SerialName("role")
        public static /* synthetic */ void getRole$annotations() {
        }

        @SerialName("voiceActors")
        public static /* synthetic */ void getVoiceActors$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRole() {
            return this.role;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<Staff> component4() {
            return this.voiceActors;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getFavouriteOrder() {
            return this.favouriteOrder;
        }

        public final List<CharacterMedia> component6() {
            return this.media;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Character getNode() {
            return this.node;
        }

        public final CharacterEdge copy(@JsonProperty("id") Integer id, @JsonProperty("role") String role, @JsonProperty("name") String name, @JsonProperty("voiceActors") List<Staff> voiceActors, @JsonProperty("favouriteOrder") Integer favouriteOrder, @JsonProperty(LinkHeader.Parameters.Media) List<CharacterMedia> media, @JsonProperty("node") Character node) {
            return new CharacterEdge(id, role, name, voiceActors, favouriteOrder, media, node);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CharacterEdge)) {
                return false;
            }
            CharacterEdge characterEdge = (CharacterEdge) other;
            return Intrinsics.areEqual(this.id, characterEdge.id) && Intrinsics.areEqual(this.role, characterEdge.role) && Intrinsics.areEqual(this.name, characterEdge.name) && Intrinsics.areEqual(this.voiceActors, characterEdge.voiceActors) && Intrinsics.areEqual(this.favouriteOrder, characterEdge.favouriteOrder) && Intrinsics.areEqual(this.media, characterEdge.media) && Intrinsics.areEqual(this.node, characterEdge.node);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.role;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.name;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            List<Staff> list = this.voiceActors;
            int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
            Integer num2 = this.favouriteOrder;
            int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
            List<CharacterMedia> list2 = this.media;
            int iHashCode6 = (iHashCode5 + (list2 == null ? 0 : list2.hashCode())) * 31;
            Character character = this.node;
            return iHashCode6 + (character != null ? character.hashCode() : 0);
        }

        public String toString() {
            return "CharacterEdge(id=" + this.id + ", role=" + this.role + ", name=" + this.name + ", voiceActors=" + this.voiceActors + ", favouriteOrder=" + this.favouriteOrder + ", media=" + this.media + ", node=" + this.node + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterEdge$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterEdge;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CharacterEdge> serializer() {
                return AniListApi$CharacterEdge$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CharacterEdge(int i, Integer num, String str, String str2, List list, Integer num2, List list2, Character character, SerializationConstructorMarker serializationConstructorMarker) {
            if (127 != (i & 127)) {
                PluginExceptionsKt.throwMissingFieldException(i, 127, AniListApi$CharacterEdge$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.role = str;
            this.name = str2;
            this.voiceActors = list;
            this.favouriteOrder = num2;
            this.media = list2;
            this.node = character;
        }

        public CharacterEdge(@JsonProperty("id") Integer num, @JsonProperty("role") String str, @JsonProperty("name") String str2, @JsonProperty("voiceActors") List<Staff> list, @JsonProperty("favouriteOrder") Integer num2, @JsonProperty(LinkHeader.Parameters.Media) List<CharacterMedia> list2, @JsonProperty("node") Character character) {
            this.id = num;
            this.role = str;
            this.name = str2;
            this.voiceActors = list;
            this.favouriteOrder = num2;
            this.media = list2;
            this.node = character;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CharacterEdge self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.role);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.name);
            output.encodeNullableSerializableElement(serialDesc, 3, lazyArr[3].getValue(), self.voiceActors);
            output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.favouriteOrder);
            output.encodeNullableSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.media);
            output.encodeNullableSerializableElement(serialDesc, 6, AniListApi$Character$$serializer.INSTANCE, self.node);
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getRole() {
            return this.role;
        }

        public final String getName() {
            return this.name;
        }

        public final List<Staff> getVoiceActors() {
            return this.voiceActors;
        }

        public final Integer getFavouriteOrder() {
            return this.favouriteOrder;
        }

        public final List<CharacterMedia> getMedia() {
            return this.media;
        }

        public final Character getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\r\u001a\u0004\b\u0011\u0010\u000f¨\u0006$"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;", "", "large", "", "medium", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLarge$annotations", "()V", "getLarge", "()Ljava/lang/String;", "getMedium$annotations", "getMedium", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class StaffImage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ StaffImage copy$default(StaffImage staffImage, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = staffImage.large;
            }
            if ((i & 2) != 0) {
                str2 = staffImage.medium;
            }
            return staffImage.copy(str, str2);
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

        public final StaffImage copy(@JsonProperty("large") String large, @JsonProperty("medium") String medium) {
            return new StaffImage(large, medium);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StaffImage)) {
                return false;
            }
            StaffImage staffImage = (StaffImage) other;
            return Intrinsics.areEqual(this.large, staffImage.large) && Intrinsics.areEqual(this.medium, staffImage.medium);
        }

        public int hashCode() {
            String str = this.large;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.medium;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "StaffImage(large=" + this.large + ", medium=" + this.medium + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<StaffImage> serializer() {
                return AniListApi$StaffImage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ StaffImage(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$StaffImage$$serializer.INSTANCE.getDescriptor());
            }
            this.large = str;
            this.medium = str2;
        }

        public StaffImage(@JsonProperty("large") String str, @JsonProperty("medium") String str2) {
            this.large = str;
            this.medium = str2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(StaffImage self, CompositeEncoder output, SerialDescriptor serialDesc) {
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

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 92\u00020\u0001:\u000289Ba\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000b\u0010\fBg\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u000b\u0010\u0011J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jc\u0010*\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010/\u001a\u00020\u0003HÖ\u0081\u0004J%\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0001¢\u0006\u0002\b7R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0015R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0019\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0013\u001a\u0004\b\u001b\u0010\u0015R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0015R$\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0013\u001a\u0004\b\u001f\u0010 R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010\u0015¨\u0006:"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;", "", "first", "", "middle", "last", "full", "native", "alternative", "", "userPreferred", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFirst$annotations", "()V", "getFirst", "()Ljava/lang/String;", "getMiddle$annotations", "getMiddle", "getLast$annotations", "getLast", "getFull$annotations", "getFull", "getNative$annotations", "getNative", "getAlternative$annotations", "getAlternative", "()Ljava/util/List;", "getUserPreferred$annotations", "getUserPreferred", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class StaffName {

        @JsonProperty("alternative")
        private final List<String> alternative;

        @JsonProperty("name")
        private final String first;

        @JsonProperty("full")
        private final String full;

        @JsonProperty("last")
        private final String last;

        @JsonProperty("middle")
        private final String middle;

        @JsonProperty("native")
        private final String native;

        @JsonProperty("userPreferred")
        private final String userPreferred;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$StaffName$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.StaffName._childSerializers$_anonymous_();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(StringSerializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StaffName copy$default(StaffName staffName, String str, String str2, String str3, String str4, String str5, List list, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = staffName.first;
            }
            if ((i & 2) != 0) {
                str2 = staffName.middle;
            }
            if ((i & 4) != 0) {
                str3 = staffName.last;
            }
            if ((i & 8) != 0) {
                str4 = staffName.full;
            }
            if ((i & 16) != 0) {
                str5 = staffName.native;
            }
            if ((i & 32) != 0) {
                list = staffName.alternative;
            }
            if ((i & 64) != 0) {
                str6 = staffName.userPreferred;
            }
            List list2 = list;
            String str7 = str6;
            String str8 = str5;
            String str9 = str3;
            return staffName.copy(str, str2, str9, str4, str8, list2, str7);
        }

        @SerialName("alternative")
        public static /* synthetic */ void getAlternative$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getFirst$annotations() {
        }

        @SerialName("full")
        public static /* synthetic */ void getFull$annotations() {
        }

        @SerialName("last")
        public static /* synthetic */ void getLast$annotations() {
        }

        @SerialName("middle")
        public static /* synthetic */ void getMiddle$annotations() {
        }

        @SerialName("native")
        public static /* synthetic */ void getNative$annotations() {
        }

        @SerialName("userPreferred")
        public static /* synthetic */ void getUserPreferred$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFirst() {
            return this.first;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMiddle() {
            return this.middle;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLast() {
            return this.last;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getFull() {
            return this.full;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getNative() {
            return this.native;
        }

        public final List<String> component6() {
            return this.alternative;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getUserPreferred() {
            return this.userPreferred;
        }

        public final StaffName copy(@JsonProperty("name") String first, @JsonProperty("middle") String middle, @JsonProperty("last") String last, @JsonProperty("full") String full, @JsonProperty("native") String str, @JsonProperty("alternative") List<String> alternative, @JsonProperty("userPreferred") String userPreferred) {
            return new StaffName(first, middle, last, full, str, alternative, userPreferred);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StaffName)) {
                return false;
            }
            StaffName staffName = (StaffName) other;
            return Intrinsics.areEqual(this.first, staffName.first) && Intrinsics.areEqual(this.middle, staffName.middle) && Intrinsics.areEqual(this.last, staffName.last) && Intrinsics.areEqual(this.full, staffName.full) && Intrinsics.areEqual(this.native, staffName.native) && Intrinsics.areEqual(this.alternative, staffName.alternative) && Intrinsics.areEqual(this.userPreferred, staffName.userPreferred);
        }

        public int hashCode() {
            String str = this.first;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.middle;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.last;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.full;
            int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.native;
            int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
            List<String> list = this.alternative;
            int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
            String str6 = this.userPreferred;
            return iHashCode6 + (str6 != null ? str6.hashCode() : 0);
        }

        public String toString() {
            return "StaffName(first=" + this.first + ", middle=" + this.middle + ", last=" + this.last + ", full=" + this.full + ", native=" + this.native + ", alternative=" + this.alternative + ", userPreferred=" + this.userPreferred + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<StaffName> serializer() {
                return AniListApi$StaffName$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ StaffName(int i, String str, String str2, String str3, String str4, String str5, List list, String str6, SerializationConstructorMarker serializationConstructorMarker) {
            if (127 != (i & 127)) {
                PluginExceptionsKt.throwMissingFieldException(i, 127, AniListApi$StaffName$$serializer.INSTANCE.getDescriptor());
            }
            this.first = str;
            this.middle = str2;
            this.last = str3;
            this.full = str4;
            this.native = str5;
            this.alternative = list;
            this.userPreferred = str6;
        }

        public StaffName(@JsonProperty("name") String str, @JsonProperty("middle") String str2, @JsonProperty("last") String str3, @JsonProperty("full") String str4, @JsonProperty("native") String str5, @JsonProperty("alternative") List<String> list, @JsonProperty("userPreferred") String str6) {
            this.first = str;
            this.middle = str2;
            this.last = str3;
            this.full = str4;
            this.native = str5;
            this.alternative = list;
            this.userPreferred = str6;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(StaffName self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.first);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.middle);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.last);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.full);
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.native);
            output.encodeNullableSerializableElement(serialDesc, 5, lazyArr[5].getValue(), self.alternative);
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.userPreferred);
        }

        public final String getFirst() {
            return this.first;
        }

        public final String getMiddle() {
            return this.middle;
        }

        public final String getLast() {
            return this.last;
        }

        public final String getFull() {
            return this.full;
        }

        public final String getNative() {
            return this.native;
        }

        public final List<String> getAlternative() {
            return this.alternative;
        }

        public final String getUserPreferred() {
            return this.userPreferred;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002,-B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0017J2\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001¢\u0006\u0002\b+R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0018\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017¨\u0006."}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Staff;", "", "image", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;", "name", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;", "age", "", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getImage$annotations", "()V", "getImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;", "getName$annotations", "getName", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;", "getAge$annotations", "getAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffImage;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StaffName;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Staff;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Staff {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("age")
        private final Integer age;

        @JsonProperty("image")
        private final StaffImage image;

        @JsonProperty("name")
        private final StaffName name;

        public static /* synthetic */ Staff copy$default(Staff staff, StaffImage staffImage, StaffName staffName, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                staffImage = staff.image;
            }
            if ((i & 2) != 0) {
                staffName = staff.name;
            }
            if ((i & 4) != 0) {
                num = staff.age;
            }
            return staff.copy(staffImage, staffName, num);
        }

        @SerialName("age")
        public static /* synthetic */ void getAge$annotations() {
        }

        @SerialName("image")
        public static /* synthetic */ void getImage$annotations() {
        }

        @SerialName("name")
        public static /* synthetic */ void getName$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final StaffImage getImage() {
            return this.image;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final StaffName getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getAge() {
            return this.age;
        }

        public final Staff copy(@JsonProperty("image") StaffImage image, @JsonProperty("name") StaffName name, @JsonProperty("age") Integer age) {
            return new Staff(image, name, age);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Staff)) {
                return false;
            }
            Staff staff = (Staff) other;
            return Intrinsics.areEqual(this.image, staff.image) && Intrinsics.areEqual(this.name, staff.name) && Intrinsics.areEqual(this.age, staff.age);
        }

        public int hashCode() {
            StaffImage staffImage = this.image;
            int iHashCode = (staffImage == null ? 0 : staffImage.hashCode()) * 31;
            StaffName staffName = this.name;
            int iHashCode2 = (iHashCode + (staffName == null ? 0 : staffName.hashCode())) * 31;
            Integer num = this.age;
            return iHashCode2 + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "Staff(image=" + this.image + ", name=" + this.name + ", age=" + this.age + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Staff$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Staff;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Staff> serializer() {
                return AniListApi$Staff$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Staff(int i, StaffImage staffImage, StaffName staffName, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$Staff$$serializer.INSTANCE.getDescriptor());
            }
            this.image = staffImage;
            this.name = staffName;
            this.age = num;
        }

        public Staff(@JsonProperty("image") StaffImage staffImage, @JsonProperty("name") StaffName staffName, @JsonProperty("age") Integer num) {
            this.image = staffImage;
            this.name = staffName;
            this.age = num;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Staff self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, AniListApi$StaffImage$$serializer.INSTANCE, self.image);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$StaffName$$serializer.INSTANCE, self.name);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.age);
        }

        public final StaffImage getImage() {
            return this.image;
        }

        public final StaffName getName() {
            return this.name;
        }

        public final Integer getAge() {
            return this.age;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B+\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bB;\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\u0016\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J%\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$R$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006'"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;", "", "edges", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterEdge;", "nodes", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Character;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEdges$annotations", "()V", "getEdges", "()Ljava/util/List;", "getNodes$annotations", "getNodes", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class CharacterConnection {

        @JsonProperty("edges")
        private final List<CharacterEdge> edges;

        @JsonProperty("nodes")
        private final List<Character> nodes;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$CharacterConnection$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.CharacterConnection._childSerializers$_anonymous_();
            }
        }), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$CharacterConnection$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.CharacterConnection._childSerializers$_anonymous_$0();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$CharacterEdge$$serializer.INSTANCE);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_$0() {
            return new ArrayListSerializer(AniListApi$Character$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CharacterConnection copy$default(CharacterConnection characterConnection, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = characterConnection.edges;
            }
            if ((i & 2) != 0) {
                list2 = characterConnection.nodes;
            }
            return characterConnection.copy(list, list2);
        }

        @SerialName("edges")
        public static /* synthetic */ void getEdges$annotations() {
        }

        @SerialName("nodes")
        public static /* synthetic */ void getNodes$annotations() {
        }

        public final List<CharacterEdge> component1() {
            return this.edges;
        }

        public final List<Character> component2() {
            return this.nodes;
        }

        public final CharacterConnection copy(@JsonProperty("edges") List<CharacterEdge> edges, @JsonProperty("nodes") List<Character> nodes) {
            return new CharacterConnection(edges, nodes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CharacterConnection)) {
                return false;
            }
            CharacterConnection characterConnection = (CharacterConnection) other;
            return Intrinsics.areEqual(this.edges, characterConnection.edges) && Intrinsics.areEqual(this.nodes, characterConnection.nodes);
        }

        public int hashCode() {
            List<CharacterEdge> list = this.edges;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            List<Character> list2 = this.nodes;
            return iHashCode + (list2 != null ? list2.hashCode() : 0);
        }

        public String toString() {
            return "CharacterConnection(edges=" + this.edges + ", nodes=" + this.nodes + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CharacterConnection;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<CharacterConnection> serializer() {
                return AniListApi$CharacterConnection$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ CharacterConnection(int i, List list, List list2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$CharacterConnection$$serializer.INSTANCE.getDescriptor());
            }
            this.edges = list;
            this.nodes = list2;
        }

        public CharacterConnection(@JsonProperty("edges") List<CharacterEdge> list, @JsonProperty("nodes") List<Character> list2) {
            this.edges = list;
            this.nodes = list2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(CharacterConnection self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, lazyArr[0].getValue(), self.edges);
            output.encodeNullableSerializableElement(serialDesc, 1, lazyArr[1].getValue(), self.nodes);
        }

        public final List<CharacterEdge> getEdges() {
            return this.edges;
        }

        public final List<Character> getNodes() {
            return this.nodes;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;", "", "id", "", "site", "thumbnail", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getSite$annotations", "getSite", "getThumbnail$annotations", "getThumbnail", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaTrailer {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final String id;

        @JsonProperty("site")
        private final String site;

        @JsonProperty("thumbnail")
        private final String thumbnail;

        public static /* synthetic */ MediaTrailer copy$default(MediaTrailer mediaTrailer, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mediaTrailer.id;
            }
            if ((i & 2) != 0) {
                str2 = mediaTrailer.site;
            }
            if ((i & 4) != 0) {
                str3 = mediaTrailer.thumbnail;
            }
            return mediaTrailer.copy(str, str2, str3);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("site")
        public static /* synthetic */ void getSite$annotations() {
        }

        @SerialName("thumbnail")
        public static /* synthetic */ void getThumbnail$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSite() {
            return this.site;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getThumbnail() {
            return this.thumbnail;
        }

        public final MediaTrailer copy(@JsonProperty("id") String id, @JsonProperty("site") String site, @JsonProperty("thumbnail") String thumbnail) {
            return new MediaTrailer(id, site, thumbnail);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaTrailer)) {
                return false;
            }
            MediaTrailer mediaTrailer = (MediaTrailer) other;
            return Intrinsics.areEqual(this.id, mediaTrailer.id) && Intrinsics.areEqual(this.site, mediaTrailer.site) && Intrinsics.areEqual(this.thumbnail, mediaTrailer.thumbnail);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.site;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.thumbnail;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "MediaTrailer(id=" + this.id + ", site=" + this.site + ", thumbnail=" + this.thumbnail + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTrailer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaTrailer> serializer() {
                return AniListApi$MediaTrailer$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaTrailer(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$MediaTrailer$$serializer.INSTANCE.getDescriptor());
            }
            this.id = str;
            this.site = str2;
            this.thumbnail = str3;
        }

        public MediaTrailer(@JsonProperty("id") String str, @JsonProperty("site") String str2, @JsonProperty("thumbnail") String str3) {
            this.id = str;
            this.site = str2;
            this.thumbnail = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaTrailer self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.site);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.thumbnail);
        }

        public final String getId() {
            return this.id;
        }

        public final String getSite() {
            return this.site;
        }

        public final String getThumbnail() {
            return this.thumbnail;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B7\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "", "extraLarge", "", "large", "medium", "color", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getExtraLarge$annotations", "()V", "getExtraLarge", "()Ljava/lang/String;", "getLarge$annotations", "getLarge", "getMedium$annotations", "getMedium", "getColor$annotations", "getColor", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaCoverImage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("color")
        private final String color;

        @JsonProperty("extraLarge")
        private final String extraLarge;

        @JsonProperty("large")
        private final String large;

        @JsonProperty("medium")
        private final String medium;

        public static /* synthetic */ MediaCoverImage copy$default(MediaCoverImage mediaCoverImage, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mediaCoverImage.extraLarge;
            }
            if ((i & 2) != 0) {
                str2 = mediaCoverImage.large;
            }
            if ((i & 4) != 0) {
                str3 = mediaCoverImage.medium;
            }
            if ((i & 8) != 0) {
                str4 = mediaCoverImage.color;
            }
            return mediaCoverImage.copy(str, str2, str3, str4);
        }

        @SerialName("color")
        public static /* synthetic */ void getColor$annotations() {
        }

        @SerialName("extraLarge")
        public static /* synthetic */ void getExtraLarge$annotations() {
        }

        @SerialName("large")
        public static /* synthetic */ void getLarge$annotations() {
        }

        @SerialName("medium")
        public static /* synthetic */ void getMedium$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getExtraLarge() {
            return this.extraLarge;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLarge() {
            return this.large;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMedium() {
            return this.medium;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getColor() {
            return this.color;
        }

        public final MediaCoverImage copy(@JsonProperty("extraLarge") String extraLarge, @JsonProperty("large") String large, @JsonProperty("medium") String medium, @JsonProperty("color") String color) {
            return new MediaCoverImage(extraLarge, large, medium, color);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaCoverImage)) {
                return false;
            }
            MediaCoverImage mediaCoverImage = (MediaCoverImage) other;
            return Intrinsics.areEqual(this.extraLarge, mediaCoverImage.extraLarge) && Intrinsics.areEqual(this.large, mediaCoverImage.large) && Intrinsics.areEqual(this.medium, mediaCoverImage.medium) && Intrinsics.areEqual(this.color, mediaCoverImage.color);
        }

        public int hashCode() {
            String str = this.extraLarge;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.large;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.medium;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.color;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "MediaCoverImage(extraLarge=" + this.extraLarge + ", large=" + this.large + ", medium=" + this.medium + ", color=" + this.color + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaCoverImage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaCoverImage> serializer() {
                return AniListApi$MediaCoverImage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaCoverImage(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AniListApi$MediaCoverImage$$serializer.INSTANCE.getDescriptor());
            }
            this.extraLarge = str;
            this.large = str2;
            this.medium = str3;
            this.color = str4;
        }

        public MediaCoverImage(@JsonProperty("extraLarge") String str, @JsonProperty("large") String str2, @JsonProperty("medium") String str3, @JsonProperty("color") String str4) {
            this.extraLarge = str;
            this.large = str2;
            this.medium = str3;
            this.color = str4;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaCoverImage self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.extraLarge);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.large);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.medium);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.color);
        }

        public final String getExtraLarge() {
            return this.extraLarge;
        }

        public final String getLarge() {
            return this.large;
        }

        public final String getMedium() {
            return this.medium;
        }

        public final String getColor() {
            return this.color;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 %2\u00020\u0001:\u0002$%B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0005\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ&\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J%\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0001¢\u0006\u0002\b#R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\u0010\u0010\f\u001a\u0004\b\u0011\u0010\u000e¨\u0006&"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "", "episode", "", "timeUntilAiring", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEpisode$annotations", "()V", "getEpisode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTimeUntilAiring$annotations", "getTimeUntilAiring", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonNextAiringEpisode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("episode")
        private final Integer episode;

        @JsonProperty("timeUntilAiring")
        private final Integer timeUntilAiring;

        public static /* synthetic */ SeasonNextAiringEpisode copy$default(SeasonNextAiringEpisode seasonNextAiringEpisode, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = seasonNextAiringEpisode.episode;
            }
            if ((i & 2) != 0) {
                num2 = seasonNextAiringEpisode.timeUntilAiring;
            }
            return seasonNextAiringEpisode.copy(num, num2);
        }

        @SerialName("episode")
        public static /* synthetic */ void getEpisode$annotations() {
        }

        @SerialName("timeUntilAiring")
        public static /* synthetic */ void getTimeUntilAiring$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTimeUntilAiring() {
            return this.timeUntilAiring;
        }

        public final SeasonNextAiringEpisode copy(@JsonProperty("episode") Integer episode, @JsonProperty("timeUntilAiring") Integer timeUntilAiring) {
            return new SeasonNextAiringEpisode(episode, timeUntilAiring);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonNextAiringEpisode)) {
                return false;
            }
            SeasonNextAiringEpisode seasonNextAiringEpisode = (SeasonNextAiringEpisode) other;
            return Intrinsics.areEqual(this.episode, seasonNextAiringEpisode.episode) && Intrinsics.areEqual(this.timeUntilAiring, seasonNextAiringEpisode.timeUntilAiring);
        }

        public int hashCode() {
            Integer num = this.episode;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.timeUntilAiring;
            return iHashCode + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "SeasonNextAiringEpisode(episode=" + this.episode + ", timeUntilAiring=" + this.timeUntilAiring + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonNextAiringEpisode> serializer() {
                return AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonNextAiringEpisode(int i, Integer num, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE.getDescriptor());
            }
            this.episode = num;
            this.timeUntilAiring = num2;
        }

        public SeasonNextAiringEpisode(@JsonProperty("episode") Integer num, @JsonProperty("timeUntilAiring") Integer num2) {
            this.episode = num;
            this.timeUntilAiring = num2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SeasonNextAiringEpisode self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.episode);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.timeUntilAiring);
        }

        public final Integer getEpisode() {
            return this.episode;
        }

        public final Integer getTimeUntilAiring() {
            return this.timeUntilAiring;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0019\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\u0011\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;", "", "edges", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdge;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEdges$annotations", "()V", "getEdges", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonEdges {

        @JsonProperty("edges")
        private final List<SeasonEdge> edges;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$SeasonEdges$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.SeasonEdges._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$SeasonEdge$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SeasonEdges copy$default(SeasonEdges seasonEdges, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = seasonEdges.edges;
            }
            return seasonEdges.copy(list);
        }

        @SerialName("edges")
        public static /* synthetic */ void getEdges$annotations() {
        }

        public final List<SeasonEdge> component1() {
            return this.edges;
        }

        public final SeasonEdges copy(@JsonProperty("edges") List<SeasonEdge> edges) {
            return new SeasonEdges(edges);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SeasonEdges) && Intrinsics.areEqual(this.edges, ((SeasonEdges) other).edges);
        }

        public int hashCode() {
            List<SeasonEdge> list = this.edges;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "SeasonEdges(edges=" + this.edges + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonEdges> serializer() {
                return AniListApi$SeasonEdges$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonEdges(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$SeasonEdges$$serializer.INSTANCE.getDescriptor());
            }
            this.edges = list;
        }

        public SeasonEdges(@JsonProperty("edges") List<SeasonEdge> list) {
            this.edges = list;
        }

        public final List<SeasonEdge> getEdges() {
            return this.edges;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0002+,B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB9\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\rJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0001¢\u0006\u0002\b*R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0012\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdge;", "", "id", "", "relationType", "", "node", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRelationType$annotations", "getRelationType", "()Ljava/lang/String;", "getNode$annotations", "getNode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdge;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonEdge {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("node")
        private final SeasonNode node;

        @JsonProperty("relationType")
        private final String relationType;

        public static /* synthetic */ SeasonEdge copy$default(SeasonEdge seasonEdge, Integer num, String str, SeasonNode seasonNode, int i, Object obj) {
            if ((i & 1) != 0) {
                num = seasonEdge.id;
            }
            if ((i & 2) != 0) {
                str = seasonEdge.relationType;
            }
            if ((i & 4) != 0) {
                seasonNode = seasonEdge.node;
            }
            return seasonEdge.copy(num, str, seasonNode);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("node")
        public static /* synthetic */ void getNode$annotations() {
        }

        @SerialName("relationType")
        public static /* synthetic */ void getRelationType$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRelationType() {
            return this.relationType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SeasonNode getNode() {
            return this.node;
        }

        public final SeasonEdge copy(@JsonProperty("id") Integer id, @JsonProperty("relationType") String relationType, @JsonProperty("node") SeasonNode node) {
            return new SeasonEdge(id, relationType, node);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonEdge)) {
                return false;
            }
            SeasonEdge seasonEdge = (SeasonEdge) other;
            return Intrinsics.areEqual(this.id, seasonEdge.id) && Intrinsics.areEqual(this.relationType, seasonEdge.relationType) && Intrinsics.areEqual(this.node, seasonEdge.node);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.relationType;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            SeasonNode seasonNode = this.node;
            return iHashCode2 + (seasonNode != null ? seasonNode.hashCode() : 0);
        }

        public String toString() {
            return "SeasonEdge(id=" + this.id + ", relationType=" + this.relationType + ", node=" + this.node + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdge$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdge;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonEdge> serializer() {
                return AniListApi$SeasonEdge$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonEdge(int i, Integer num, String str, SeasonNode seasonNode, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$SeasonEdge$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.relationType = str;
            this.node = seasonNode;
        }

        public SeasonEdge(@JsonProperty("id") Integer num, @JsonProperty("relationType") String str, @JsonProperty("node") SeasonNode seasonNode) {
            this.id = num;
            this.relationType = str;
            this.node = seasonNode;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SeasonEdge self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.relationType);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$SeasonNode$$serializer.INSTANCE, self.node);
        }

        public final Integer getId() {
            return this.id;
        }

        public final String getRelationType() {
            return this.relationType;
        }

        public final SeasonNode getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u0011\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection;", "", "nodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeNode;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNodes$annotations", "()V", "getNodes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListFavoritesMediaConnection {

        @JsonProperty("nodes")
        private final List<LikeNode> nodes;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$AniListFavoritesMediaConnection$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.AniListFavoritesMediaConnection._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$LikeNode$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AniListFavoritesMediaConnection copy$default(AniListFavoritesMediaConnection aniListFavoritesMediaConnection, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = aniListFavoritesMediaConnection.nodes;
            }
            return aniListFavoritesMediaConnection.copy(list);
        }

        @SerialName("nodes")
        public static /* synthetic */ void getNodes$annotations() {
        }

        public final List<LikeNode> component1() {
            return this.nodes;
        }

        public final AniListFavoritesMediaConnection copy(@JsonProperty("nodes") List<LikeNode> nodes) {
            Intrinsics.checkNotNullParameter(nodes, "nodes");
            return new AniListFavoritesMediaConnection(nodes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AniListFavoritesMediaConnection) && Intrinsics.areEqual(this.nodes, ((AniListFavoritesMediaConnection) other).nodes);
        }

        public int hashCode() {
            return this.nodes.hashCode();
        }

        public String toString() {
            return "AniListFavoritesMediaConnection(nodes=" + this.nodes + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListFavoritesMediaConnection> serializer() {
                return AniListApi$AniListFavoritesMediaConnection$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListFavoritesMediaConnection(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$AniListFavoritesMediaConnection$$serializer.INSTANCE.getDescriptor());
            }
            this.nodes = list;
        }

        public AniListFavoritesMediaConnection(@JsonProperty("nodes") List<LikeNode> nodes) {
            Intrinsics.checkNotNullParameter(nodes, "nodes");
            this.nodes = nodes;
        }

        public final List<LikeNode> getNodes() {
            return this.nodes;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites;", "", "anime", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAnime$annotations", "()V", "getAnime", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavoritesMediaConnection;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListFavourites {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("anime")
        private final AniListFavoritesMediaConnection anime;

        public static /* synthetic */ AniListFavourites copy$default(AniListFavourites aniListFavourites, AniListFavoritesMediaConnection aniListFavoritesMediaConnection, int i, Object obj) {
            if ((i & 1) != 0) {
                aniListFavoritesMediaConnection = aniListFavourites.anime;
            }
            return aniListFavourites.copy(aniListFavoritesMediaConnection);
        }

        @SerialName("anime")
        public static /* synthetic */ void getAnime$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AniListFavoritesMediaConnection getAnime() {
            return this.anime;
        }

        public final AniListFavourites copy(@JsonProperty("anime") AniListFavoritesMediaConnection anime) {
            Intrinsics.checkNotNullParameter(anime, "anime");
            return new AniListFavourites(anime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AniListFavourites) && Intrinsics.areEqual(this.anime, ((AniListFavourites) other).anime);
        }

        public int hashCode() {
            return this.anime.hashCode();
        }

        public String toString() {
            return "AniListFavourites(anime=" + this.anime + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListFavourites> serializer() {
                return AniListApi$AniListFavourites$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListFavourites(int i, AniListFavoritesMediaConnection aniListFavoritesMediaConnection, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$AniListFavourites$$serializer.INSTANCE.getDescriptor());
            }
            this.anime = aniListFavoritesMediaConnection;
        }

        public AniListFavourites(@JsonProperty("anime") AniListFavoritesMediaConnection anime) {
            Intrinsics.checkNotNullParameter(anime, "anime");
            this.anime = anime;
        }

        public final AniListFavoritesMediaConnection getAnime() {
            return this.anime;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 +2\u00020\u0001:\u0002*+B7\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bBC\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J9\u0010\u001c\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020\nHÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011¨\u0006,"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "", "romaji", "", "english", "native", "userPreferred", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRomaji$annotations", "()V", "getRomaji", "()Ljava/lang/String;", "getEnglish$annotations", "getEnglish", "getNative$annotations", "getNative", "getUserPreferred$annotations", "getUserPreferred", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class MediaTitle {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("english")
        private final String english;

        @JsonProperty("native")
        private final String native;

        @JsonProperty("romaji")
        private final String romaji;

        @JsonProperty("userPreferred")
        private final String userPreferred;

        public static /* synthetic */ MediaTitle copy$default(MediaTitle mediaTitle, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = mediaTitle.romaji;
            }
            if ((i & 2) != 0) {
                str2 = mediaTitle.english;
            }
            if ((i & 4) != 0) {
                str3 = mediaTitle.native;
            }
            if ((i & 8) != 0) {
                str4 = mediaTitle.userPreferred;
            }
            return mediaTitle.copy(str, str2, str3, str4);
        }

        @SerialName("english")
        public static /* synthetic */ void getEnglish$annotations() {
        }

        @SerialName("native")
        public static /* synthetic */ void getNative$annotations() {
        }

        @SerialName("romaji")
        public static /* synthetic */ void getRomaji$annotations() {
        }

        @SerialName("userPreferred")
        public static /* synthetic */ void getUserPreferred$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRomaji() {
            return this.romaji;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEnglish() {
            return this.english;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getNative() {
            return this.native;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUserPreferred() {
            return this.userPreferred;
        }

        public final MediaTitle copy(@JsonProperty("romaji") String romaji, @JsonProperty("english") String english, @JsonProperty("native") String str, @JsonProperty("userPreferred") String userPreferred) {
            return new MediaTitle(romaji, english, str, userPreferred);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaTitle)) {
                return false;
            }
            MediaTitle mediaTitle = (MediaTitle) other;
            return Intrinsics.areEqual(this.romaji, mediaTitle.romaji) && Intrinsics.areEqual(this.english, mediaTitle.english) && Intrinsics.areEqual(this.native, mediaTitle.native) && Intrinsics.areEqual(this.userPreferred, mediaTitle.userPreferred);
        }

        public int hashCode() {
            String str = this.romaji;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.english;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.native;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.userPreferred;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }

        public String toString() {
            return "MediaTitle(romaji=" + this.romaji + ", english=" + this.english + ", native=" + this.native + ", userPreferred=" + this.userPreferred + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaTitle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<MediaTitle> serializer() {
                return AniListApi$MediaTitle$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ MediaTitle(int i, String str, String str2, String str3, String str4, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AniListApi$MediaTitle$$serializer.INSTANCE.getDescriptor());
            }
            this.romaji = str;
            this.english = str2;
            this.native = str3;
            this.userPreferred = str4;
        }

        public MediaTitle(@JsonProperty("romaji") String str, @JsonProperty("english") String str2, @JsonProperty("native") String str3, @JsonProperty("userPreferred") String str4) {
            this.romaji = str;
            this.english = str2;
            this.native = str3;
            this.userPreferred = str4;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(MediaTitle self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.romaji);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.english);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.native);
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.userPreferred);
        }

        public final String getRomaji() {
            return this.romaji;
        }

        public final String getEnglish() {
            return this.english;
        }

        public final String getNative() {
            return this.native;
        }

        public final String getUserPreferred() {
            return this.userPreferred;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ;2\u00020\u0001:\u0002:;BM\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rBU\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\f\u0010\u0011J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u0010)\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001eJT\u0010+\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010,J\u0014\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00101\u001a\u00020\u0005HÖ\u0081\u0004J%\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0001¢\u0006\u0002\b9R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u001bR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001f\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u001eR\u001e\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0013\u001a\u0004\b!\u0010\"R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001f\u0012\u0004\b#\u0010\u0013\u001a\u0004\b$\u0010\u001e¨\u0006<"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;", "", "id", "", "format", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "idMal", "coverImage", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "averageScore", "<init>", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getFormat$annotations", "getFormat", "()Ljava/lang/String;", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "getIdMal$annotations", "getIdMal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCoverImage$annotations", "getCoverImage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;", "getAverageScore$annotations", "getAverageScore", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$CoverImage;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class SeasonNode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("averageScore")
        private final Integer averageScore;

        @JsonProperty("coverImage")
        private final CoverImage coverImage;

        @JsonProperty("format")
        private final String format;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("idMal")
        private final Integer idMal;

        @JsonProperty("title")
        private final Title title;

        public static /* synthetic */ SeasonNode copy$default(SeasonNode seasonNode, int i, String str, Title title, Integer num, CoverImage coverImage, Integer num2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = seasonNode.id;
            }
            if ((i2 & 2) != 0) {
                str = seasonNode.format;
            }
            if ((i2 & 4) != 0) {
                title = seasonNode.title;
            }
            if ((i2 & 8) != 0) {
                num = seasonNode.idMal;
            }
            if ((i2 & 16) != 0) {
                coverImage = seasonNode.coverImage;
            }
            if ((i2 & 32) != 0) {
                num2 = seasonNode.averageScore;
            }
            CoverImage coverImage2 = coverImage;
            Integer num3 = num2;
            return seasonNode.copy(i, str, title, num, coverImage2, num3);
        }

        @SerialName("averageScore")
        public static /* synthetic */ void getAverageScore$annotations() {
        }

        @SerialName("coverImage")
        public static /* synthetic */ void getCoverImage$annotations() {
        }

        @SerialName("format")
        public static /* synthetic */ void getFormat$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("idMal")
        public static /* synthetic */ void getIdMal$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Title getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getIdMal() {
            return this.idMal;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final CoverImage getCoverImage() {
            return this.coverImage;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getAverageScore() {
            return this.averageScore;
        }

        public final SeasonNode copy(@JsonProperty("id") int id, @JsonProperty("format") String format, @JsonProperty("title") Title title, @JsonProperty("idMal") Integer idMal, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("averageScore") Integer averageScore) {
            return new SeasonNode(id, format, title, idMal, coverImage, averageScore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeasonNode)) {
                return false;
            }
            SeasonNode seasonNode = (SeasonNode) other;
            return this.id == seasonNode.id && Intrinsics.areEqual(this.format, seasonNode.format) && Intrinsics.areEqual(this.title, seasonNode.title) && Intrinsics.areEqual(this.idMal, seasonNode.idMal) && Intrinsics.areEqual(this.coverImage, seasonNode.coverImage) && Intrinsics.areEqual(this.averageScore, seasonNode.averageScore);
        }

        public int hashCode() {
            int i = this.id * 31;
            String str = this.format;
            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            Title title = this.title;
            int iHashCode2 = (iHashCode + (title == null ? 0 : title.hashCode())) * 31;
            Integer num = this.idMal;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            CoverImage coverImage = this.coverImage;
            int iHashCode4 = (iHashCode3 + (coverImage == null ? 0 : coverImage.hashCode())) * 31;
            Integer num2 = this.averageScore;
            return iHashCode4 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "SeasonNode(id=" + this.id + ", format=" + this.format + ", title=" + this.title + ", idMal=" + this.idMal + ", coverImage=" + this.coverImage + ", averageScore=" + this.averageScore + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<SeasonNode> serializer() {
                return AniListApi$SeasonNode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ SeasonNode(int i, int i2, String str, Title title, Integer num, CoverImage coverImage, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (i & 63)) {
                PluginExceptionsKt.throwMissingFieldException(i, 63, AniListApi$SeasonNode$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.format = str;
            this.title = title;
            this.idMal = num;
            this.coverImage = coverImage;
            this.averageScore = num2;
        }

        public SeasonNode(@JsonProperty("id") int i, @JsonProperty("format") String str, @JsonProperty("title") Title title, @JsonProperty("idMal") Integer num, @JsonProperty("coverImage") CoverImage coverImage, @JsonProperty("averageScore") Integer num2) {
            this.id = i;
            this.format = str;
            this.title = title;
            this.idMal = num;
            this.coverImage = coverImage;
            this.averageScore = num2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(SeasonNode self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.format);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$Title$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.idMal);
            output.encodeNullableSerializableElement(serialDesc, 4, AniListApi$CoverImage$$serializer.INSTANCE, self.coverImage);
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.averageScore);
        }

        public final int getId() {
            return this.id;
        }

        public final String getFormat() {
            return this.format;
        }

        public final Title getTitle() {
            return this.title;
        }

        public final Integer getIdMal() {
            return this.idMal;
        }

        public final CoverImage getCoverImage() {
            return this.coverImage;
        }

        public final Integer getAverageScore() {
            return this.averageScore;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar;", "", "large", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getLarge$annotations", "()V", "getLarge", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListAvatar {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("large")
        private final String large;

        public static /* synthetic */ AniListAvatar copy$default(AniListAvatar aniListAvatar, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = aniListAvatar.large;
            }
            return aniListAvatar.copy(str);
        }

        @SerialName("large")
        public static /* synthetic */ void getLarge$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLarge() {
            return this.large;
        }

        public final AniListAvatar copy(@JsonProperty("large") String large) {
            return new AniListAvatar(large);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AniListAvatar) && Intrinsics.areEqual(this.large, ((AniListAvatar) other).large);
        }

        public int hashCode() {
            String str = this.large;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "AniListAvatar(large=" + this.large + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListAvatar> serializer() {
                return AniListApi$AniListAvatar$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListAvatar(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$AniListAvatar$$serializer.INSTANCE.getDescriptor());
            }
            this.large = str;
        }

        public AniListAvatar(@JsonProperty("large") String str) {
            this.large = str;
        }

        public final String getLarge() {
            return this.large;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 02\u00020\u0001:\u0002/0B3\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBA\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010!\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c¨\u00061"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer;", "", "id", "", "name", "", "avatar", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar;", "favourites", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites;", "<init>", "(ILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getName$annotations", "getName", "()Ljava/lang/String;", "getAvatar$annotations", "getAvatar", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListAvatar;", "getFavourites$annotations", "getFavourites", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListFavourites;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListViewer {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("avatar")
        private final AniListAvatar avatar;

        @JsonProperty("favourites")
        private final AniListFavourites favourites;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("name")
        private final String name;

        public static /* synthetic */ AniListViewer copy$default(AniListViewer aniListViewer, int i, String str, AniListAvatar aniListAvatar, AniListFavourites aniListFavourites, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = aniListViewer.id;
            }
            if ((i2 & 2) != 0) {
                str = aniListViewer.name;
            }
            if ((i2 & 4) != 0) {
                aniListAvatar = aniListViewer.avatar;
            }
            if ((i2 & 8) != 0) {
                aniListFavourites = aniListViewer.favourites;
            }
            return aniListViewer.copy(i, str, aniListAvatar, aniListFavourites);
        }

        @SerialName("avatar")
        public static /* synthetic */ void getAvatar$annotations() {
        }

        @SerialName("favourites")
        public static /* synthetic */ void getFavourites$annotations() {
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

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final AniListAvatar getAvatar() {
            return this.avatar;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final AniListFavourites getFavourites() {
            return this.favourites;
        }

        public final AniListViewer copy(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("avatar") AniListAvatar avatar, @JsonProperty("favourites") AniListFavourites favourites) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new AniListViewer(id, name, avatar, favourites);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AniListViewer)) {
                return false;
            }
            AniListViewer aniListViewer = (AniListViewer) other;
            return this.id == aniListViewer.id && Intrinsics.areEqual(this.name, aniListViewer.name) && Intrinsics.areEqual(this.avatar, aniListViewer.avatar) && Intrinsics.areEqual(this.favourites, aniListViewer.favourites);
        }

        public int hashCode() {
            int iHashCode = ((this.id * 31) + this.name.hashCode()) * 31;
            AniListAvatar aniListAvatar = this.avatar;
            int iHashCode2 = (iHashCode + (aniListAvatar == null ? 0 : aniListAvatar.hashCode())) * 31;
            AniListFavourites aniListFavourites = this.favourites;
            return iHashCode2 + (aniListFavourites != null ? aniListFavourites.hashCode() : 0);
        }

        public String toString() {
            return "AniListViewer(id=" + this.id + ", name=" + this.name + ", avatar=" + this.avatar + ", favourites=" + this.favourites + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListViewer> serializer() {
                return AniListApi$AniListViewer$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListViewer(int i, int i2, String str, AniListAvatar aniListAvatar, AniListFavourites aniListFavourites, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AniListApi$AniListViewer$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.name = str;
            this.avatar = aniListAvatar;
            this.favourites = aniListFavourites;
        }

        public AniListViewer(@JsonProperty("id") int i, @JsonProperty("name") String name, @JsonProperty("avatar") AniListAvatar aniListAvatar, @JsonProperty("favourites") AniListFavourites aniListFavourites) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.id = i;
            this.name = name;
            this.avatar = aniListAvatar;
            this.favourites = aniListFavourites;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(AniListViewer self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$AniListAvatar$$serializer.INSTANCE, self.avatar);
            output.encodeNullableSerializableElement(serialDesc, 3, AniListApi$AniListFavourites$$serializer.INSTANCE, self.favourites);
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final AniListAvatar getAvatar() {
            return this.avatar;
        }

        public final AniListFavourites getFavourites() {
            return this.favourites;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData;", "", "viewer", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getViewer$annotations", "()V", "getViewer", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListViewer;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("Viewer")
        private final AniListViewer viewer;

        public static /* synthetic */ AniListData copy$default(AniListData aniListData, AniListViewer aniListViewer, int i, Object obj) {
            if ((i & 1) != 0) {
                aniListViewer = aniListData.viewer;
            }
            return aniListData.copy(aniListViewer);
        }

        @SerialName("Viewer")
        public static /* synthetic */ void getViewer$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AniListViewer getViewer() {
            return this.viewer;
        }

        public final AniListData copy(@JsonProperty("Viewer") AniListViewer viewer) {
            return new AniListData(viewer);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AniListData) && Intrinsics.areEqual(this.viewer, ((AniListData) other).viewer);
        }

        public int hashCode() {
            AniListViewer aniListViewer = this.viewer;
            if (aniListViewer == null) {
                return 0;
            }
            return aniListViewer.hashCode();
        }

        public String toString() {
            return "AniListData(viewer=" + this.viewer + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListData> serializer() {
                return AniListApi$AniListData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListData(int i, AniListViewer aniListViewer, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$AniListData$$serializer.INSTANCE.getDescriptor());
            }
            this.viewer = aniListViewer;
        }

        public AniListData(@JsonProperty("Viewer") AniListViewer aniListViewer) {
            this.viewer = aniListViewer;
        }

        public final AniListViewer getViewer() {
            return this.viewer;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListRoot;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final AniListData data;

        public static /* synthetic */ AniListRoot copy$default(AniListRoot aniListRoot, AniListData aniListData, int i, Object obj) {
            if ((i & 1) != 0) {
                aniListData = aniListRoot.data;
            }
            return aniListRoot.copy(aniListData);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AniListData getData() {
            return this.data;
        }

        public final AniListRoot copy(@JsonProperty("data") AniListData data) {
            return new AniListRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof AniListRoot) && Intrinsics.areEqual(this.data, ((AniListRoot) other).data);
        }

        public int hashCode() {
            AniListData aniListData = this.data;
            if (aniListData == null) {
                return 0;
            }
            return aniListData.hashCode();
        }

        public String toString() {
            return "AniListRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListRoot> serializer() {
                return AniListApi$AniListRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListRoot(int i, AniListData aniListData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$AniListRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = aniListData;
        }

        public AniListRoot(@JsonProperty("data") AniListData aniListData) {
            this.data = aniListData;
        }

        public final AniListData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0002'(B'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bB7\b\u0010\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0019\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004J%\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001¢\u0006\u0002\b&R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0013¨\u0006)"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListUser;", "", "id", "", "name", "", "picture", "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getName$annotations", "getName", "()Ljava/lang/String;", "getPicture$annotations", "getPicture", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListUser {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final int id;

        @JsonProperty("name")
        private final String name;

        @JsonProperty("picture")
        private final String picture;

        public static /* synthetic */ AniListUser copy$default(AniListUser aniListUser, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = aniListUser.id;
            }
            if ((i2 & 2) != 0) {
                str = aniListUser.name;
            }
            if ((i2 & 4) != 0) {
                str2 = aniListUser.picture;
            }
            return aniListUser.copy(i, str, str2);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
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
        public final String getPicture() {
            return this.picture;
        }

        public final AniListUser copy(@JsonProperty("id") int id, @JsonProperty("name") String name, @JsonProperty("picture") String picture) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new AniListUser(id, name, picture);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AniListUser)) {
                return false;
            }
            AniListUser aniListUser = (AniListUser) other;
            return this.id == aniListUser.id && Intrinsics.areEqual(this.name, aniListUser.name) && Intrinsics.areEqual(this.picture, aniListUser.picture);
        }

        public int hashCode() {
            int iHashCode = ((this.id * 31) + this.name.hashCode()) * 31;
            String str = this.picture;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "AniListUser(id=" + this.id + ", name=" + this.name + ", picture=" + this.picture + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListUser$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListUser;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListUser> serializer() {
                return AniListApi$AniListUser$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListUser(int i, int i2, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$AniListUser$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.name = str;
            this.picture = str2;
        }

        public AniListUser(@JsonProperty("id") int i, @JsonProperty("name") String name, @JsonProperty("picture") String str) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.id = i;
            this.name = name;
            this.picture = str;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(AniListUser self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeStringElement(serialDesc, 1, self.name);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.picture);
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPicture() {
            return this.picture;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0004\u0010\tJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeNode;", "", "id", "", "<init>", "(Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "copy", "(Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeNode;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikeNode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final Integer id;

        public static /* synthetic */ LikeNode copy$default(LikeNode likeNode, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                num = likeNode.id;
            }
            return likeNode.copy(num);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        public final LikeNode copy(@JsonProperty("id") Integer id) {
            return new LikeNode(id);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LikeNode) && Intrinsics.areEqual(this.id, ((LikeNode) other).id);
        }

        public int hashCode() {
            Integer num = this.id;
            if (num == null) {
                return 0;
            }
            return num.hashCode();
        }

        public String toString() {
            return "LikeNode(id=" + this.id + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeNode$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeNode;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikeNode> serializer() {
                return AniListApi$LikeNode$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikeNode(int i, Integer num, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$LikeNode$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
        }

        public LikeNode(@JsonProperty("id") Integer num) {
            this.id = num;
        }

        public final Integer getId() {
            return this.id;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u000223BC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nBM\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\t\u0010\u000eJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001cJJ\u0010#\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010$J\u0014\u0010%\u001a\u00020\b2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J%\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0001¢\u0006\u0002\b1R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0015\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012R \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u001c¨\u00064"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;", "", "total", "", "currentPage", "lastPage", "perPage", "hasNextPage", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTotal$annotations", "()V", "getTotal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCurrentPage$annotations", "getCurrentPage", "getLastPage$annotations", "getLastPage", "getPerPage$annotations", "getPerPage", "getHasNextPage$annotations", "getHasNextPage", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikePageInfo {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("currentPage")
        private final Integer currentPage;

        @JsonProperty("hasNextPage")
        private final Boolean hasNextPage;

        @JsonProperty("lastPage")
        private final Integer lastPage;

        @JsonProperty("perPage")
        private final Integer perPage;

        @JsonProperty("total")
        private final Integer total;

        public static /* synthetic */ LikePageInfo copy$default(LikePageInfo likePageInfo, Integer num, Integer num2, Integer num3, Integer num4, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                num = likePageInfo.total;
            }
            if ((i & 2) != 0) {
                num2 = likePageInfo.currentPage;
            }
            if ((i & 4) != 0) {
                num3 = likePageInfo.lastPage;
            }
            if ((i & 8) != 0) {
                num4 = likePageInfo.perPage;
            }
            if ((i & 16) != 0) {
                bool = likePageInfo.hasNextPage;
            }
            Boolean bool2 = bool;
            Integer num5 = num3;
            return likePageInfo.copy(num, num2, num5, num4, bool2);
        }

        @SerialName("currentPage")
        public static /* synthetic */ void getCurrentPage$annotations() {
        }

        @SerialName("hasNextPage")
        public static /* synthetic */ void getHasNextPage$annotations() {
        }

        @SerialName("lastPage")
        public static /* synthetic */ void getLastPage$annotations() {
        }

        @SerialName("perPage")
        public static /* synthetic */ void getPerPage$annotations() {
        }

        @SerialName("total")
        public static /* synthetic */ void getTotal$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getCurrentPage() {
            return this.currentPage;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getLastPage() {
            return this.lastPage;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getPerPage() {
            return this.perPage;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Boolean getHasNextPage() {
            return this.hasNextPage;
        }

        public final LikePageInfo copy(@JsonProperty("total") Integer total, @JsonProperty("currentPage") Integer currentPage, @JsonProperty("lastPage") Integer lastPage, @JsonProperty("perPage") Integer perPage, @JsonProperty("hasNextPage") Boolean hasNextPage) {
            return new LikePageInfo(total, currentPage, lastPage, perPage, hasNextPage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LikePageInfo)) {
                return false;
            }
            LikePageInfo likePageInfo = (LikePageInfo) other;
            return Intrinsics.areEqual(this.total, likePageInfo.total) && Intrinsics.areEqual(this.currentPage, likePageInfo.currentPage) && Intrinsics.areEqual(this.lastPage, likePageInfo.lastPage) && Intrinsics.areEqual(this.perPage, likePageInfo.perPage) && Intrinsics.areEqual(this.hasNextPage, likePageInfo.hasNextPage);
        }

        public int hashCode() {
            Integer num = this.total;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.currentPage;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.lastPage;
            int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.perPage;
            int iHashCode4 = (iHashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Boolean bool = this.hasNextPage;
            return iHashCode4 + (bool != null ? bool.hashCode() : 0);
        }

        public String toString() {
            return "LikePageInfo(total=" + this.total + ", currentPage=" + this.currentPage + ", lastPage=" + this.lastPage + ", perPage=" + this.perPage + ", hasNextPage=" + this.hasNextPage + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikePageInfo> serializer() {
                return AniListApi$LikePageInfo$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikePageInfo(int i, Integer num, Integer num2, Integer num3, Integer num4, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
            if (31 != (i & 31)) {
                PluginExceptionsKt.throwMissingFieldException(i, 31, AniListApi$LikePageInfo$$serializer.INSTANCE.getDescriptor());
            }
            this.total = num;
            this.currentPage = num2;
            this.lastPage = num3;
            this.perPage = num4;
            this.hasNextPage = bool;
        }

        public LikePageInfo(@JsonProperty("total") Integer num, @JsonProperty("currentPage") Integer num2, @JsonProperty("lastPage") Integer num3, @JsonProperty("perPage") Integer num4, @JsonProperty("hasNextPage") Boolean bool) {
            this.total = num;
            this.currentPage = num2;
            this.lastPage = num3;
            this.perPage = num4;
            this.hasNextPage = bool;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(LikePageInfo self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.total);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.currentPage);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.lastPage);
            output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.perPage);
            output.encodeNullableSerializableElement(serialDesc, 4, BooleanSerializer.INSTANCE, self.hasNextPage);
        }

        public final Integer getTotal() {
            return this.total;
        }

        public final Integer getCurrentPage() {
            return this.currentPage;
        }

        public final Integer getLastPage() {
            return this.lastPage;
        }

        public final Integer getPerPage() {
            return this.perPage;
        }

        public final Boolean getHasNextPage() {
            return this.hasNextPage;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B%\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB5\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\nHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime;", "", "nodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeNode;", "pageInfo", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;", "<init>", "(Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNodes$annotations", "()V", "getNodes", "()Ljava/util/List;", "getPageInfo$annotations", "getPageInfo", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikePageInfo;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikeAnime {

        @JsonProperty("nodes")
        private final List<LikeNode> nodes;

        @JsonProperty("pageInfo")
        private final LikePageInfo pageInfo;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$LikeAnime$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.LikeAnime._childSerializers$_anonymous_();
            }
        }), null};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$LikeNode$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LikeAnime copy$default(LikeAnime likeAnime, List list, LikePageInfo likePageInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                list = likeAnime.nodes;
            }
            if ((i & 2) != 0) {
                likePageInfo = likeAnime.pageInfo;
            }
            return likeAnime.copy(list, likePageInfo);
        }

        @SerialName("nodes")
        public static /* synthetic */ void getNodes$annotations() {
        }

        @SerialName("pageInfo")
        public static /* synthetic */ void getPageInfo$annotations() {
        }

        public final List<LikeNode> component1() {
            return this.nodes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final LikePageInfo getPageInfo() {
            return this.pageInfo;
        }

        public final LikeAnime copy(@JsonProperty("nodes") List<LikeNode> nodes, @JsonProperty("pageInfo") LikePageInfo pageInfo) {
            return new LikeAnime(nodes, pageInfo);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LikeAnime)) {
                return false;
            }
            LikeAnime likeAnime = (LikeAnime) other;
            return Intrinsics.areEqual(this.nodes, likeAnime.nodes) && Intrinsics.areEqual(this.pageInfo, likeAnime.pageInfo);
        }

        public int hashCode() {
            List<LikeNode> list = this.nodes;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            LikePageInfo likePageInfo = this.pageInfo;
            return iHashCode + (likePageInfo != null ? likePageInfo.hashCode() : 0);
        }

        public String toString() {
            return "LikeAnime(nodes=" + this.nodes + ", pageInfo=" + this.pageInfo + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikeAnime> serializer() {
                return AniListApi$LikeAnime$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikeAnime(int i, List list, LikePageInfo likePageInfo, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$LikeAnime$$serializer.INSTANCE.getDescriptor());
            }
            this.nodes = list;
            this.pageInfo = likePageInfo;
        }

        public LikeAnime(@JsonProperty("nodes") List<LikeNode> list, @JsonProperty("pageInfo") LikePageInfo likePageInfo) {
            this.nodes = list;
            this.pageInfo = likePageInfo;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(LikeAnime self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, $childSerializers[0].getValue(), self.nodes);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$LikePageInfo$$serializer.INSTANCE, self.pageInfo);
        }

        public final List<LikeNode> getNodes() {
            return this.nodes;
        }

        public final LikePageInfo getPageInfo() {
            return this.pageInfo;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites;", "", "anime", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAnime$annotations", "()V", "getAnime", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeAnime;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikeFavourites {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("anime")
        private final LikeAnime anime;

        public static /* synthetic */ LikeFavourites copy$default(LikeFavourites likeFavourites, LikeAnime likeAnime, int i, Object obj) {
            if ((i & 1) != 0) {
                likeAnime = likeFavourites.anime;
            }
            return likeFavourites.copy(likeAnime);
        }

        @SerialName("anime")
        public static /* synthetic */ void getAnime$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LikeAnime getAnime() {
            return this.anime;
        }

        public final LikeFavourites copy(@JsonProperty("anime") LikeAnime anime) {
            return new LikeFavourites(anime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LikeFavourites) && Intrinsics.areEqual(this.anime, ((LikeFavourites) other).anime);
        }

        public int hashCode() {
            LikeAnime likeAnime = this.anime;
            if (likeAnime == null) {
                return 0;
            }
            return likeAnime.hashCode();
        }

        public String toString() {
            return "LikeFavourites(anime=" + this.anime + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikeFavourites> serializer() {
                return AniListApi$LikeFavourites$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikeFavourites(int i, LikeAnime likeAnime, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$LikeFavourites$$serializer.INSTANCE.getDescriptor());
            }
            this.anime = likeAnime;
        }

        public LikeFavourites(@JsonProperty("anime") LikeAnime likeAnime) {
            this.anime = likeAnime;
        }

        public final LikeAnime getAnime() {
            return this.anime;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer;", "", "favourites", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getFavourites$annotations", "()V", "getFavourites", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeFavourites;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikeViewer {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("favourites")
        private final LikeFavourites favourites;

        public static /* synthetic */ LikeViewer copy$default(LikeViewer likeViewer, LikeFavourites likeFavourites, int i, Object obj) {
            if ((i & 1) != 0) {
                likeFavourites = likeViewer.favourites;
            }
            return likeViewer.copy(likeFavourites);
        }

        @SerialName("favourites")
        public static /* synthetic */ void getFavourites$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LikeFavourites getFavourites() {
            return this.favourites;
        }

        public final LikeViewer copy(@JsonProperty("favourites") LikeFavourites favourites) {
            return new LikeViewer(favourites);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LikeViewer) && Intrinsics.areEqual(this.favourites, ((LikeViewer) other).favourites);
        }

        public int hashCode() {
            LikeFavourites likeFavourites = this.favourites;
            if (likeFavourites == null) {
                return 0;
            }
            return likeFavourites.hashCode();
        }

        public String toString() {
            return "LikeViewer(favourites=" + this.favourites + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikeViewer> serializer() {
                return AniListApi$LikeViewer$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikeViewer(int i, LikeFavourites likeFavourites, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$LikeViewer$$serializer.INSTANCE.getDescriptor());
            }
            this.favourites = likeFavourites;
        }

        public LikeViewer(@JsonProperty("favourites") LikeFavourites likeFavourites) {
            this.favourites = likeFavourites;
        }

        public final LikeFavourites getFavourites() {
            return this.favourites;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData;", "", "viewer", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getViewer$annotations", "()V", "getViewer", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeViewer;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikeData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("Viewer")
        private final LikeViewer viewer;

        public static /* synthetic */ LikeData copy$default(LikeData likeData, LikeViewer likeViewer, int i, Object obj) {
            if ((i & 1) != 0) {
                likeViewer = likeData.viewer;
            }
            return likeData.copy(likeViewer);
        }

        @SerialName("Viewer")
        public static /* synthetic */ void getViewer$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LikeViewer getViewer() {
            return this.viewer;
        }

        public final LikeData copy(@JsonProperty("Viewer") LikeViewer viewer) {
            return new LikeData(viewer);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LikeData) && Intrinsics.areEqual(this.viewer, ((LikeData) other).viewer);
        }

        public int hashCode() {
            LikeViewer likeViewer = this.viewer;
            if (likeViewer == null) {
                return 0;
            }
            return likeViewer.hashCode();
        }

        public String toString() {
            return "LikeData(viewer=" + this.viewer + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikeData> serializer() {
                return AniListApi$LikeData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikeData(int i, LikeViewer likeViewer, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$LikeData$$serializer.INSTANCE.getDescriptor());
            }
            this.viewer = likeViewer;
        }

        public LikeData(@JsonProperty("Viewer") LikeViewer likeViewer) {
            this.viewer = likeViewer;
        }

        public final LikeViewer getViewer() {
            return this.viewer;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeRoot;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class LikeRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final LikeData data;

        public static /* synthetic */ LikeRoot copy$default(LikeRoot likeRoot, LikeData likeData, int i, Object obj) {
            if ((i & 1) != 0) {
                likeData = likeRoot.data;
            }
            return likeRoot.copy(likeData);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LikeData getData() {
            return this.data;
        }

        public final LikeRoot copy(@JsonProperty("data") LikeData data) {
            return new LikeRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LikeRoot) && Intrinsics.areEqual(this.data, ((LikeRoot) other).data);
        }

        public int hashCode() {
            LikeData likeData = this.data;
            if (likeData == null) {
                return 0;
            }
            return likeData.hashCode();
        }

        public String toString() {
            return "LikeRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$LikeRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<LikeRoot> serializer() {
                return AniListApi$LikeRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ LikeRoot(int i, LikeData likeData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$LikeRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = likeData;
        }

        public LikeRoot(@JsonProperty("data") LikeData likeData) {
            this.data = likeData;
        }

        public final LikeData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0002=>B[\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eBa\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\r\u0010\u0012J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010-\u001a\u0004\u0018\u00010\fHÆ\u0003Jb\u0010.\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010/J\u0014\u00100\u001a\u00020\u00052\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u00020\u0007HÖ\u0081\u0004J\n\u00103\u001a\u000204HÖ\u0081\u0004J%\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u00002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0001¢\u0006\u0002\b<R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0004\u0010\u0018R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001a\u0010\u0014\u001a\u0004\b\u001b\u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001f\u0010\u001cR \u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b \u0010\u0014\u001a\u0004\b!\u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\"\u0010\u0014\u001a\u0004\b#\u0010\u001cR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0014\u001a\u0004\b%\u0010&¨\u0006?"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListTitleHolder;", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "isFavourite", "", "id", "", "progress", "episodes", "score", "type", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTitle$annotations", "()V", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "isFavourite$annotations", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getId$annotations", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProgress$annotations", "getProgress", "getEpisodes$annotations", "getEpisodes", "getScore$annotations", "getScore", "getType$annotations", "getType", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Companion$AniListStatusType;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListTitleHolder;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class AniListTitleHolder {

        @JsonProperty("episodes")
        private final Integer episodes;

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("isFavourite")
        private final Boolean isFavourite;

        @JsonProperty("progress")
        private final Integer progress;

        @JsonProperty("score")
        private final Integer score;

        @JsonProperty("title")
        private final Title title;

        @JsonProperty("type")
        private final Companion.AniListStatusType type;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {null, null, null, null, null, null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$AniListTitleHolder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.AniListTitleHolder._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return EnumsKt.createSimpleEnumSerializer("com.lagradost.cloudstream3.syncproviders.providers.AniListApi.Companion.AniListStatusType", Companion.AniListStatusType.values());
        }

        public static /* synthetic */ AniListTitleHolder copy$default(AniListTitleHolder aniListTitleHolder, Title title, Boolean bool, Integer num, Integer num2, Integer num3, Integer num4, Companion.AniListStatusType aniListStatusType, int i, Object obj) {
            if ((i & 1) != 0) {
                title = aniListTitleHolder.title;
            }
            if ((i & 2) != 0) {
                bool = aniListTitleHolder.isFavourite;
            }
            if ((i & 4) != 0) {
                num = aniListTitleHolder.id;
            }
            if ((i & 8) != 0) {
                num2 = aniListTitleHolder.progress;
            }
            if ((i & 16) != 0) {
                num3 = aniListTitleHolder.episodes;
            }
            if ((i & 32) != 0) {
                num4 = aniListTitleHolder.score;
            }
            if ((i & 64) != 0) {
                aniListStatusType = aniListTitleHolder.type;
            }
            Integer num5 = num4;
            Companion.AniListStatusType aniListStatusType2 = aniListStatusType;
            Integer num6 = num3;
            Integer num7 = num;
            return aniListTitleHolder.copy(title, bool, num7, num2, num6, num5, aniListStatusType2);
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("progress")
        public static /* synthetic */ void getProgress$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("type")
        public static /* synthetic */ void getType$annotations() {
        }

        @SerialName("isFavourite")
        public static /* synthetic */ void isFavourite$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Title getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Boolean getIsFavourite() {
            return this.isFavourite;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getProgress() {
            return this.progress;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getEpisodes() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Companion.AniListStatusType getType() {
            return this.type;
        }

        public final AniListTitleHolder copy(@JsonProperty("title") Title title, @JsonProperty("isFavourite") Boolean isFavourite, @JsonProperty("id") Integer id, @JsonProperty("progress") Integer progress, @JsonProperty("episodes") Integer episodes, @JsonProperty("score") Integer score, @JsonProperty("type") Companion.AniListStatusType type) {
            return new AniListTitleHolder(title, isFavourite, id, progress, episodes, score, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AniListTitleHolder)) {
                return false;
            }
            AniListTitleHolder aniListTitleHolder = (AniListTitleHolder) other;
            return Intrinsics.areEqual(this.title, aniListTitleHolder.title) && Intrinsics.areEqual(this.isFavourite, aniListTitleHolder.isFavourite) && Intrinsics.areEqual(this.id, aniListTitleHolder.id) && Intrinsics.areEqual(this.progress, aniListTitleHolder.progress) && Intrinsics.areEqual(this.episodes, aniListTitleHolder.episodes) && Intrinsics.areEqual(this.score, aniListTitleHolder.score) && this.type == aniListTitleHolder.type;
        }

        public int hashCode() {
            Title title = this.title;
            int iHashCode = (title == null ? 0 : title.hashCode()) * 31;
            Boolean bool = this.isFavourite;
            int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
            Integer num = this.id;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.progress;
            int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.episodes;
            int iHashCode5 = (iHashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.score;
            int iHashCode6 = (iHashCode5 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Companion.AniListStatusType aniListStatusType = this.type;
            return iHashCode6 + (aniListStatusType != null ? aniListStatusType.hashCode() : 0);
        }

        public String toString() {
            return "AniListTitleHolder(title=" + this.title + ", isFavourite=" + this.isFavourite + ", id=" + this.id + ", progress=" + this.progress + ", episodes=" + this.episodes + ", score=" + this.score + ", type=" + this.type + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListTitleHolder$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$AniListTitleHolder;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<AniListTitleHolder> serializer() {
                return AniListApi$AniListTitleHolder$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ AniListTitleHolder(int i, Title title, Boolean bool, Integer num, Integer num2, Integer num3, Integer num4, Companion.AniListStatusType aniListStatusType, SerializationConstructorMarker serializationConstructorMarker) {
            if (127 != (i & 127)) {
                PluginExceptionsKt.throwMissingFieldException(i, 127, AniListApi$AniListTitleHolder$$serializer.INSTANCE.getDescriptor());
            }
            this.title = title;
            this.isFavourite = bool;
            this.id = num;
            this.progress = num2;
            this.episodes = num3;
            this.score = num4;
            this.type = aniListStatusType;
        }

        public AniListTitleHolder(@JsonProperty("title") Title title, @JsonProperty("isFavourite") Boolean bool, @JsonProperty("id") Integer num, @JsonProperty("progress") Integer num2, @JsonProperty("episodes") Integer num3, @JsonProperty("score") Integer num4, @JsonProperty("type") Companion.AniListStatusType aniListStatusType) {
            this.title = title;
            this.isFavourite = bool;
            this.id = num;
            this.progress = num2;
            this.episodes = num3;
            this.score = num4;
            this.type = aniListStatusType;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(AniListTitleHolder self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArr = $childSerializers;
            output.encodeNullableSerializableElement(serialDesc, 0, AniListApi$Title$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 1, BooleanSerializer.INSTANCE, self.isFavourite);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 3, IntSerializer.INSTANCE, self.progress);
            output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.episodes);
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.score);
            output.encodeNullableSerializableElement(serialDesc, 6, lazyArr[6].getValue(), self.type);
        }

        public final Title getTitle() {
            return this.title;
        }

        public final Boolean isFavourite() {
            return this.isFavourite;
        }

        public final Integer getId() {
            return this.id;
        }

        public final Integer getProgress() {
            return this.progress;
        }

        public final Integer getEpisodes() {
            return this.episodes;
        }

        public final Integer getScore() {
            return this.score;
        }

        public final Companion.AniListStatusType getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002)*B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bB9\b\u0010\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J2\u0010\u001a\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0005HÖ\u0081\u0004J%\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0001¢\u0006\u0002\b(R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0011\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0013\u0010\u0014R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0011\u0012\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;", "", "progress", "", NotificationCompat.CATEGORY_STATUS, "", "score", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getProgress$annotations", "()V", "getProgress", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatus$annotations", "getStatus", "()Ljava/lang/String;", "getScore$annotations", "getScore", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetDataMediaListEntry {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("progress")
        private final Integer progress;

        @JsonProperty("score")
        private final Integer score;

        @JsonProperty(NotificationCompat.CATEGORY_STATUS)
        private final String status;

        public static /* synthetic */ GetDataMediaListEntry copy$default(GetDataMediaListEntry getDataMediaListEntry, Integer num, String str, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = getDataMediaListEntry.progress;
            }
            if ((i & 2) != 0) {
                str = getDataMediaListEntry.status;
            }
            if ((i & 4) != 0) {
                num2 = getDataMediaListEntry.score;
            }
            return getDataMediaListEntry.copy(num, str, num2);
        }

        @SerialName("progress")
        public static /* synthetic */ void getProgress$annotations() {
        }

        @SerialName("score")
        public static /* synthetic */ void getScore$annotations() {
        }

        @SerialName(NotificationCompat.CATEGORY_STATUS)
        public static /* synthetic */ void getStatus$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getProgress() {
            return this.progress;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getScore() {
            return this.score;
        }

        public final GetDataMediaListEntry copy(@JsonProperty("progress") Integer progress, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String status, @JsonProperty("score") Integer score) {
            return new GetDataMediaListEntry(progress, status, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GetDataMediaListEntry)) {
                return false;
            }
            GetDataMediaListEntry getDataMediaListEntry = (GetDataMediaListEntry) other;
            return Intrinsics.areEqual(this.progress, getDataMediaListEntry.progress) && Intrinsics.areEqual(this.status, getDataMediaListEntry.status) && Intrinsics.areEqual(this.score, getDataMediaListEntry.score);
        }

        public int hashCode() {
            Integer num = this.progress;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.status;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num2 = this.score;
            return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "GetDataMediaListEntry(progress=" + this.progress + ", status=" + this.status + ", score=" + this.score + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetDataMediaListEntry> serializer() {
                return AniListApi$GetDataMediaListEntry$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetDataMediaListEntry(int i, Integer num, String str, Integer num2, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$GetDataMediaListEntry$$serializer.INSTANCE.getDescriptor());
            }
            this.progress = num;
            this.status = str;
            this.score = num2;
        }

        public GetDataMediaListEntry(@JsonProperty("progress") Integer num, @JsonProperty(NotificationCompat.CATEGORY_STATUS) String str, @JsonProperty("score") Integer num2) {
            this.progress = num;
            this.status = str;
            this.score = num2;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(GetDataMediaListEntry self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.progress);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.status);
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.score);
        }

        public final Integer getProgress() {
            return this.progress;
        }

        public final String getStatus() {
            return this.status;
        }

        public final Integer getScore() {
            return this.score;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u0016\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0010\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\r\u001a\u0004\b\u0012\u0010\u0013¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Nodes;", "", "id", "", "mediaRecommendation", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;", "<init>", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMediaRecommendation$annotations", "getMediaRecommendation", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;", "component1", "component2", "copy", "(Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$MediaRecommendation;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Nodes;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Nodes {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final Integer id;

        @JsonProperty("mediaRecommendation")
        private final MediaRecommendation mediaRecommendation;

        public static /* synthetic */ Nodes copy$default(Nodes nodes, Integer num, MediaRecommendation mediaRecommendation, int i, Object obj) {
            if ((i & 1) != 0) {
                num = nodes.id;
            }
            if ((i & 2) != 0) {
                mediaRecommendation = nodes.mediaRecommendation;
            }
            return nodes.copy(num, mediaRecommendation);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("mediaRecommendation")
        public static /* synthetic */ void getMediaRecommendation$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaRecommendation getMediaRecommendation() {
            return this.mediaRecommendation;
        }

        public final Nodes copy(@JsonProperty("id") Integer id, @JsonProperty("mediaRecommendation") MediaRecommendation mediaRecommendation) {
            return new Nodes(id, mediaRecommendation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Nodes)) {
                return false;
            }
            Nodes nodes = (Nodes) other;
            return Intrinsics.areEqual(this.id, nodes.id) && Intrinsics.areEqual(this.mediaRecommendation, nodes.mediaRecommendation);
        }

        public int hashCode() {
            Integer num = this.id;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            MediaRecommendation mediaRecommendation = this.mediaRecommendation;
            return iHashCode + (mediaRecommendation != null ? mediaRecommendation.hashCode() : 0);
        }

        public String toString() {
            return "Nodes(id=" + this.id + ", mediaRecommendation=" + this.mediaRecommendation + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Nodes$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Nodes;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Nodes> serializer() {
                return AniListApi$Nodes$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Nodes(int i, Integer num, MediaRecommendation mediaRecommendation, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, AniListApi$Nodes$$serializer.INSTANCE.getDescriptor());
            }
            this.id = num;
            this.mediaRecommendation = mediaRecommendation;
        }

        public Nodes(@JsonProperty("id") Integer num, @JsonProperty("mediaRecommendation") MediaRecommendation mediaRecommendation) {
            this.id = num;
            this.mediaRecommendation = mediaRecommendation;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(Nodes self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, IntSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, AniListApi$MediaRecommendation$$serializer.INSTANCE, self.mediaRecommendation);
        }

        public final Integer getId() {
            return this.id;
        }

        public final MediaRecommendation getMediaRecommendation() {
            return this.mediaRecommendation;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u000212B7\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bBC\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\tHÆ\u0003J>\u0010\"\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010#J\u0014\u0010$\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J%\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\b0R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0013\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0002\u0010\u0012R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u0017\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u0011\u001a\u0004\b\u001c\u0010\u001d¨\u00063"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;", "", "isFavourite", "", "episodes", "", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "mediaListEntry", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Boolean;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "isFavourite$annotations", "()V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getEpisodes$annotations", "getEpisodes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;", "getMediaListEntry$annotations", "getMediaListEntry", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Boolean;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Title;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMediaListEntry;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetDataMedia {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("episodes")
        private final Integer episodes;

        @JsonProperty("isFavourite")
        private final Boolean isFavourite;

        @JsonProperty("mediaListEntry")
        private final GetDataMediaListEntry mediaListEntry;

        @JsonProperty("title")
        private final Title title;

        public static /* synthetic */ GetDataMedia copy$default(GetDataMedia getDataMedia, Boolean bool, Integer num, Title title, GetDataMediaListEntry getDataMediaListEntry, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = getDataMedia.isFavourite;
            }
            if ((i & 2) != 0) {
                num = getDataMedia.episodes;
            }
            if ((i & 4) != 0) {
                title = getDataMedia.title;
            }
            if ((i & 8) != 0) {
                getDataMediaListEntry = getDataMedia.mediaListEntry;
            }
            return getDataMedia.copy(bool, num, title, getDataMediaListEntry);
        }

        @SerialName("episodes")
        public static /* synthetic */ void getEpisodes$annotations() {
        }

        @SerialName("mediaListEntry")
        public static /* synthetic */ void getMediaListEntry$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("isFavourite")
        public static /* synthetic */ void isFavourite$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Boolean getIsFavourite() {
            return this.isFavourite;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getEpisodes() {
            return this.episodes;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Title getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final GetDataMediaListEntry getMediaListEntry() {
            return this.mediaListEntry;
        }

        public final GetDataMedia copy(@JsonProperty("isFavourite") Boolean isFavourite, @JsonProperty("episodes") Integer episodes, @JsonProperty("title") Title title, @JsonProperty("mediaListEntry") GetDataMediaListEntry mediaListEntry) {
            return new GetDataMedia(isFavourite, episodes, title, mediaListEntry);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GetDataMedia)) {
                return false;
            }
            GetDataMedia getDataMedia = (GetDataMedia) other;
            return Intrinsics.areEqual(this.isFavourite, getDataMedia.isFavourite) && Intrinsics.areEqual(this.episodes, getDataMedia.episodes) && Intrinsics.areEqual(this.title, getDataMedia.title) && Intrinsics.areEqual(this.mediaListEntry, getDataMedia.mediaListEntry);
        }

        public int hashCode() {
            Boolean bool = this.isFavourite;
            int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
            Integer num = this.episodes;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Title title = this.title;
            int iHashCode3 = (iHashCode2 + (title == null ? 0 : title.hashCode())) * 31;
            GetDataMediaListEntry getDataMediaListEntry = this.mediaListEntry;
            return iHashCode3 + (getDataMediaListEntry != null ? getDataMediaListEntry.hashCode() : 0);
        }

        public String toString() {
            return "GetDataMedia(isFavourite=" + this.isFavourite + ", episodes=" + this.episodes + ", title=" + this.title + ", mediaListEntry=" + this.mediaListEntry + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetDataMedia> serializer() {
                return AniListApi$GetDataMedia$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetDataMedia(int i, Boolean bool, Integer num, Title title, GetDataMediaListEntry getDataMediaListEntry, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (i & 15)) {
                PluginExceptionsKt.throwMissingFieldException(i, 15, AniListApi$GetDataMedia$$serializer.INSTANCE.getDescriptor());
            }
            this.isFavourite = bool;
            this.episodes = num;
            this.title = title;
            this.mediaListEntry = getDataMediaListEntry;
        }

        public GetDataMedia(@JsonProperty("isFavourite") Boolean bool, @JsonProperty("episodes") Integer num, @JsonProperty("title") Title title, @JsonProperty("mediaListEntry") GetDataMediaListEntry getDataMediaListEntry) {
            this.isFavourite = bool;
            this.episodes = num;
            this.title = title;
            this.mediaListEntry = getDataMediaListEntry;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(GetDataMedia self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, BooleanSerializer.INSTANCE, self.isFavourite);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.episodes);
            output.encodeNullableSerializableElement(serialDesc, 2, AniListApi$Title$$serializer.INSTANCE, self.title);
            output.encodeNullableSerializableElement(serialDesc, 3, AniListApi$GetDataMediaListEntry$$serializer.INSTANCE, self.mediaListEntry);
        }

        public final Boolean isFavourite() {
            return this.isFavourite;
        }

        public final Integer getEpisodes() {
            return this.episodes;
        }

        public final Title getTitle() {
            return this.title;
        }

        public final GetDataMediaListEntry getMediaListEntry() {
            return this.mediaListEntry;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0019\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\u0011\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;", "", "nodes", "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Nodes;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getNodes$annotations", "()V", "getNodes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class Recommendations {

        @JsonProperty("nodes")
        private final List<Nodes> nodes;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$Recommendations$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.Recommendations._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$Nodes$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Recommendations copy$default(Recommendations recommendations, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = recommendations.nodes;
            }
            return recommendations.copy(list);
        }

        @SerialName("nodes")
        public static /* synthetic */ void getNodes$annotations() {
        }

        public final List<Nodes> component1() {
            return this.nodes;
        }

        public final Recommendations copy(@JsonProperty("nodes") List<Nodes> nodes) {
            return new Recommendations(nodes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Recommendations) && Intrinsics.areEqual(this.nodes, ((Recommendations) other).nodes);
        }

        public int hashCode() {
            List<Nodes> list = this.nodes;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "Recommendations(nodes=" + this.nodes + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<Recommendations> serializer() {
                return AniListApi$Recommendations$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ Recommendations(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$Recommendations$$serializer.INSTANCE.getDescriptor());
            }
            this.nodes = list;
        }

        public Recommendations(@JsonProperty("nodes") List<Nodes> list) {
            this.nodes = list;
        }

        public final List<Nodes> getNodes() {
            return this.nodes;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData;", "", LinkHeader.Parameters.Media, "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMedia$annotations", "()V", "getMedia", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataMedia;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetDataData {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("Media")
        private final GetDataMedia media;

        public static /* synthetic */ GetDataData copy$default(GetDataData getDataData, GetDataMedia getDataMedia, int i, Object obj) {
            if ((i & 1) != 0) {
                getDataMedia = getDataData.media;
            }
            return getDataData.copy(getDataMedia);
        }

        @SerialName("Media")
        public static /* synthetic */ void getMedia$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final GetDataMedia getMedia() {
            return this.media;
        }

        public final GetDataData copy(@JsonProperty("Media") GetDataMedia media) {
            return new GetDataData(media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GetDataData) && Intrinsics.areEqual(this.media, ((GetDataData) other).media);
        }

        public int hashCode() {
            GetDataMedia getDataMedia = this.media;
            if (getDataMedia == null) {
                return 0;
            }
            return getDataMedia.hashCode();
        }

        public String toString() {
            return "GetDataData(media=" + this.media + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetDataData> serializer() {
                return AniListApi$GetDataData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetDataData(int i, GetDataMedia getDataMedia, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$GetDataData$$serializer.INSTANCE.getDescriptor());
            }
            this.media = getDataMedia;
        }

        public GetDataData(@JsonProperty("Media") GetDataMedia getDataMedia) {
            this.media = getDataMedia;
        }

        public final GetDataMedia getMedia() {
            return this.media;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataRoot;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetDataRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final GetDataData data;

        public static /* synthetic */ GetDataRoot copy$default(GetDataRoot getDataRoot, GetDataData getDataData, int i, Object obj) {
            if ((i & 1) != 0) {
                getDataData = getDataRoot.data;
            }
            return getDataRoot.copy(getDataData);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final GetDataData getData() {
            return this.data;
        }

        public final GetDataRoot copy(@JsonProperty("data") GetDataData data) {
            return new GetDataRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GetDataRoot) && Intrinsics.areEqual(this.data, ((GetDataRoot) other).data);
        }

        public int hashCode() {
            GetDataData getDataData = this.data;
            if (getDataData == null) {
                return 0;
            }
            return getDataData.hashCode();
        }

        public String toString() {
            return "GetDataRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetDataRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetDataRoot> serializer() {
                return AniListApi$GetDataRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetDataRoot(int i, GetDataData getDataData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$GetDataRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = getDataData;
        }

        public GetDataRoot(@JsonProperty("data") GetDataData getDataData) {
            this.data = getDataData;
        }

        public final GetDataData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0001¢\u0006\u0002\b\u001dR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;", "", "romaji", "", "<init>", "(Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRomaji$annotations", "()V", "getRomaji", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetSearchTitle {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("romaji")
        private final String romaji;

        public static /* synthetic */ GetSearchTitle copy$default(GetSearchTitle getSearchTitle, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = getSearchTitle.romaji;
            }
            return getSearchTitle.copy(str);
        }

        @SerialName("romaji")
        public static /* synthetic */ void getRomaji$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRomaji() {
            return this.romaji;
        }

        public final GetSearchTitle copy(@JsonProperty("romaji") String romaji) {
            return new GetSearchTitle(romaji);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GetSearchTitle) && Intrinsics.areEqual(this.romaji, ((GetSearchTitle) other).romaji);
        }

        public int hashCode() {
            String str = this.romaji;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "GetSearchTitle(romaji=" + this.romaji + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetSearchTitle> serializer() {
                return AniListApi$GetSearchTitle$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetSearchTitle(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$GetSearchTitle$$serializer.INSTANCE.getDescriptor());
            }
            this.romaji = str;
        }

        public GetSearchTitle(@JsonProperty("romaji") String str) {
            this.romaji = str;
        }

        public final String getRomaji() {
            return this.romaji;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007B9\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0014\u0010\u0010¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;", "", "id", "", "thumbnail", "site", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()Ljava/lang/String;", "getThumbnail$annotations", "getThumbnail", "getSite$annotations", "getSite", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class TrailerObject {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("id")
        private final String id;

        @JsonProperty("site")
        private final String site;

        @JsonProperty("thumbnail")
        private final String thumbnail;

        public static /* synthetic */ TrailerObject copy$default(TrailerObject trailerObject, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = trailerObject.id;
            }
            if ((i & 2) != 0) {
                str2 = trailerObject.thumbnail;
            }
            if ((i & 4) != 0) {
                str3 = trailerObject.site;
            }
            return trailerObject.copy(str, str2, str3);
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("site")
        public static /* synthetic */ void getSite$annotations() {
        }

        @SerialName("thumbnail")
        public static /* synthetic */ void getThumbnail$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getThumbnail() {
            return this.thumbnail;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSite() {
            return this.site;
        }

        public final TrailerObject copy(@JsonProperty("id") String id, @JsonProperty("thumbnail") String thumbnail, @JsonProperty("site") String site) {
            return new TrailerObject(id, thumbnail, site);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TrailerObject)) {
                return false;
            }
            TrailerObject trailerObject = (TrailerObject) other;
            return Intrinsics.areEqual(this.id, trailerObject.id) && Intrinsics.areEqual(this.thumbnail, trailerObject.thumbnail) && Intrinsics.areEqual(this.site, trailerObject.site);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.thumbnail;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.site;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "TrailerObject(id=" + this.id + ", thumbnail=" + this.thumbnail + ", site=" + this.site + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<TrailerObject> serializer() {
                return AniListApi$TrailerObject$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ TrailerObject(int i, String str, String str2, String str3, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (i & 7)) {
                PluginExceptionsKt.throwMissingFieldException(i, 7, AniListApi$TrailerObject$$serializer.INSTANCE.getDescriptor());
            }
            this.id = str;
            this.thumbnail = str2;
            this.site = str3;
        }

        public TrailerObject(@JsonProperty("id") String str, @JsonProperty("thumbnail") String str2, @JsonProperty("site") String str3) {
            this.id = str;
            this.thumbnail = str2;
            this.site = str3;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(TrailerObject self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.thumbnail);
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.site);
        }

        public final String getId() {
            return this.id;
        }

        public final String getThumbnail() {
            return this.thumbnail;
        }

        public final String getSite() {
            return this.site;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 [2\u00020\u0001:\u0002Z[B\u008f\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017B\u008f\u0001\b\u0010\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u0016\u0010\u001bJ\t\u0010?\u001a\u00020\u0003HÆ\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\"J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0007HÆ\u0003J\t\u0010C\u001a\u00020\tHÆ\u0003J\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\"J\u000b\u0010F\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u0096\u0001\u0010K\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÆ\u0001¢\u0006\u0002\u0010LJ\u0014\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010P\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010Q\u001a\u00020\rHÖ\u0081\u0004J%\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00002\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020XH\u0001¢\u0006\u0002\bYR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010#\u0012\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\"R\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u001d\u001a\u0004\b%\u0010\u001fR\u001c\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u001d\u001a\u0004\b'\u0010(R\u001c\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b)\u0010\u001d\u001a\u0004\b*\u0010+R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010#\u0012\u0004\b,\u0010\u001d\u001a\u0004\b-\u0010\"R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010#\u0012\u0004\b.\u0010\u001d\u001a\u0004\b/\u0010\"R\u001e\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b0\u0010\u001d\u001a\u0004\b1\u00102R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u001d\u001a\u0004\b4\u00105R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b6\u0010\u001d\u001a\u0004\b7\u00108R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b9\u0010\u001d\u001a\u0004\b:\u0010;R\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b<\u0010\u001d\u001a\u0004\b=\u0010>¨\u0006\\"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia;", "", "id", "", "idMal", "seasonYear", "title", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;", "startDate", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;", "averageScore", "meanScore", "bannerImage", "", "trailer", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;", "nextAiringEpisode", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "recommendations", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;", "relations", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;", "<init>", "(ILjava/lang/Integer;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/Integer;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId$annotations", "()V", "getId", "()I", "getIdMal$annotations", "getIdMal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSeasonYear$annotations", "getSeasonYear", "getTitle$annotations", "getTitle", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;", "getStartDate$annotations", "getStartDate", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;", "getAverageScore$annotations", "getAverageScore", "getMeanScore$annotations", "getMeanScore", "getBannerImage$annotations", "getBannerImage", "()Ljava/lang/String;", "getTrailer$annotations", "getTrailer", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;", "getNextAiringEpisode$annotations", "getNextAiringEpisode", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;", "getRecommendations$annotations", "getRecommendations", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;", "getRelations$annotations", "getRelations", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(ILjava/lang/Integer;ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchTitle;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$StartedAt;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$TrailerObject;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonNextAiringEpisode;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$Recommendations;Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$SeasonEdges;)Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetSearchMedia {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("averageScore")
        private final Integer averageScore;

        @JsonProperty("bannerImage")
        private final String bannerImage;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("idMal")
        private final Integer idMal;

        @JsonProperty("meanScore")
        private final Integer meanScore;

        @JsonProperty("nextAiringEpisode")
        private final SeasonNextAiringEpisode nextAiringEpisode;

        @JsonProperty("recommendations")
        private final Recommendations recommendations;

        @JsonProperty("relations")
        private final SeasonEdges relations;

        @JsonProperty("seasonYear")
        private final int seasonYear;

        @JsonProperty("startDate")
        private final StartedAt startDate;

        @JsonProperty("title")
        private final GetSearchTitle title;

        @JsonProperty("trailer")
        private final TrailerObject trailer;

        public static /* synthetic */ GetSearchMedia copy$default(GetSearchMedia getSearchMedia, int i, Integer num, int i2, GetSearchTitle getSearchTitle, StartedAt startedAt, Integer num2, Integer num3, String str, TrailerObject trailerObject, SeasonNextAiringEpisode seasonNextAiringEpisode, Recommendations recommendations, SeasonEdges seasonEdges, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = getSearchMedia.id;
            }
            if ((i3 & 2) != 0) {
                num = getSearchMedia.idMal;
            }
            if ((i3 & 4) != 0) {
                i2 = getSearchMedia.seasonYear;
            }
            if ((i3 & 8) != 0) {
                getSearchTitle = getSearchMedia.title;
            }
            if ((i3 & 16) != 0) {
                startedAt = getSearchMedia.startDate;
            }
            if ((i3 & 32) != 0) {
                num2 = getSearchMedia.averageScore;
            }
            if ((i3 & 64) != 0) {
                num3 = getSearchMedia.meanScore;
            }
            if ((i3 & 128) != 0) {
                str = getSearchMedia.bannerImage;
            }
            if ((i3 & 256) != 0) {
                trailerObject = getSearchMedia.trailer;
            }
            if ((i3 & 512) != 0) {
                seasonNextAiringEpisode = getSearchMedia.nextAiringEpisode;
            }
            if ((i3 & 1024) != 0) {
                recommendations = getSearchMedia.recommendations;
            }
            if ((i3 & 2048) != 0) {
                seasonEdges = getSearchMedia.relations;
            }
            Recommendations recommendations2 = recommendations;
            SeasonEdges seasonEdges2 = seasonEdges;
            TrailerObject trailerObject2 = trailerObject;
            SeasonNextAiringEpisode seasonNextAiringEpisode2 = seasonNextAiringEpisode;
            Integer num4 = num3;
            String str2 = str;
            StartedAt startedAt2 = startedAt;
            Integer num5 = num2;
            return getSearchMedia.copy(i, num, i2, getSearchTitle, startedAt2, num5, num4, str2, trailerObject2, seasonNextAiringEpisode2, recommendations2, seasonEdges2);
        }

        @SerialName("averageScore")
        public static /* synthetic */ void getAverageScore$annotations() {
        }

        @SerialName("bannerImage")
        public static /* synthetic */ void getBannerImage$annotations() {
        }

        @SerialName("id")
        public static /* synthetic */ void getId$annotations() {
        }

        @SerialName("idMal")
        public static /* synthetic */ void getIdMal$annotations() {
        }

        @SerialName("meanScore")
        public static /* synthetic */ void getMeanScore$annotations() {
        }

        @SerialName("nextAiringEpisode")
        public static /* synthetic */ void getNextAiringEpisode$annotations() {
        }

        @SerialName("recommendations")
        public static /* synthetic */ void getRecommendations$annotations() {
        }

        @SerialName("relations")
        public static /* synthetic */ void getRelations$annotations() {
        }

        @SerialName("seasonYear")
        public static /* synthetic */ void getSeasonYear$annotations() {
        }

        @SerialName("startDate")
        public static /* synthetic */ void getStartDate$annotations() {
        }

        @SerialName("title")
        public static /* synthetic */ void getTitle$annotations() {
        }

        @SerialName("trailer")
        public static /* synthetic */ void getTrailer$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final SeasonNextAiringEpisode getNextAiringEpisode() {
            return this.nextAiringEpisode;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Recommendations getRecommendations() {
            return this.recommendations;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final SeasonEdges getRelations() {
            return this.relations;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getIdMal() {
            return this.idMal;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getSeasonYear() {
            return this.seasonYear;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final GetSearchTitle getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final StartedAt getStartDate() {
            return this.startDate;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getAverageScore() {
            return this.averageScore;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getMeanScore() {
            return this.meanScore;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getBannerImage() {
            return this.bannerImage;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final TrailerObject getTrailer() {
            return this.trailer;
        }

        public final GetSearchMedia copy(@JsonProperty("id") int id, @JsonProperty("idMal") Integer idMal, @JsonProperty("seasonYear") int seasonYear, @JsonProperty("title") GetSearchTitle title, @JsonProperty("startDate") StartedAt startDate, @JsonProperty("averageScore") Integer averageScore, @JsonProperty("meanScore") Integer meanScore, @JsonProperty("bannerImage") String bannerImage, @JsonProperty("trailer") TrailerObject trailer, @JsonProperty("nextAiringEpisode") SeasonNextAiringEpisode nextAiringEpisode, @JsonProperty("recommendations") Recommendations recommendations, @JsonProperty("relations") SeasonEdges relations) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            return new GetSearchMedia(id, idMal, seasonYear, title, startDate, averageScore, meanScore, bannerImage, trailer, nextAiringEpisode, recommendations, relations);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GetSearchMedia)) {
                return false;
            }
            GetSearchMedia getSearchMedia = (GetSearchMedia) other;
            return this.id == getSearchMedia.id && Intrinsics.areEqual(this.idMal, getSearchMedia.idMal) && this.seasonYear == getSearchMedia.seasonYear && Intrinsics.areEqual(this.title, getSearchMedia.title) && Intrinsics.areEqual(this.startDate, getSearchMedia.startDate) && Intrinsics.areEqual(this.averageScore, getSearchMedia.averageScore) && Intrinsics.areEqual(this.meanScore, getSearchMedia.meanScore) && Intrinsics.areEqual(this.bannerImage, getSearchMedia.bannerImage) && Intrinsics.areEqual(this.trailer, getSearchMedia.trailer) && Intrinsics.areEqual(this.nextAiringEpisode, getSearchMedia.nextAiringEpisode) && Intrinsics.areEqual(this.recommendations, getSearchMedia.recommendations) && Intrinsics.areEqual(this.relations, getSearchMedia.relations);
        }

        public int hashCode() {
            int i = this.id * 31;
            Integer num = this.idMal;
            int iHashCode = (((((((i + (num == null ? 0 : num.hashCode())) * 31) + this.seasonYear) * 31) + this.title.hashCode()) * 31) + this.startDate.hashCode()) * 31;
            Integer num2 = this.averageScore;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.meanScore;
            int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str = this.bannerImage;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            TrailerObject trailerObject = this.trailer;
            int iHashCode5 = (iHashCode4 + (trailerObject == null ? 0 : trailerObject.hashCode())) * 31;
            SeasonNextAiringEpisode seasonNextAiringEpisode = this.nextAiringEpisode;
            int iHashCode6 = (iHashCode5 + (seasonNextAiringEpisode == null ? 0 : seasonNextAiringEpisode.hashCode())) * 31;
            Recommendations recommendations = this.recommendations;
            int iHashCode7 = (iHashCode6 + (recommendations == null ? 0 : recommendations.hashCode())) * 31;
            SeasonEdges seasonEdges = this.relations;
            return iHashCode7 + (seasonEdges != null ? seasonEdges.hashCode() : 0);
        }

        public String toString() {
            return "GetSearchMedia(id=" + this.id + ", idMal=" + this.idMal + ", seasonYear=" + this.seasonYear + ", title=" + this.title + ", startDate=" + this.startDate + ", averageScore=" + this.averageScore + ", meanScore=" + this.meanScore + ", bannerImage=" + this.bannerImage + ", trailer=" + this.trailer + ", nextAiringEpisode=" + this.nextAiringEpisode + ", recommendations=" + this.recommendations + ", relations=" + this.relations + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetSearchMedia> serializer() {
                return AniListApi$GetSearchMedia$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetSearchMedia(int i, int i2, Integer num, int i3, GetSearchTitle getSearchTitle, StartedAt startedAt, Integer num2, Integer num3, String str, TrailerObject trailerObject, SeasonNextAiringEpisode seasonNextAiringEpisode, Recommendations recommendations, SeasonEdges seasonEdges, SerializationConstructorMarker serializationConstructorMarker) {
            if (4095 != (i & 4095)) {
                PluginExceptionsKt.throwMissingFieldException(i, 4095, AniListApi$GetSearchMedia$$serializer.INSTANCE.getDescriptor());
            }
            this.id = i2;
            this.idMal = num;
            this.seasonYear = i3;
            this.title = getSearchTitle;
            this.startDate = startedAt;
            this.averageScore = num2;
            this.meanScore = num3;
            this.bannerImage = str;
            this.trailer = trailerObject;
            this.nextAiringEpisode = seasonNextAiringEpisode;
            this.recommendations = recommendations;
            this.relations = seasonEdges;
        }

        public GetSearchMedia(@JsonProperty("id") int i, @JsonProperty("idMal") Integer num, @JsonProperty("seasonYear") int i2, @JsonProperty("title") GetSearchTitle title, @JsonProperty("startDate") StartedAt startDate, @JsonProperty("averageScore") Integer num2, @JsonProperty("meanScore") Integer num3, @JsonProperty("bannerImage") String str, @JsonProperty("trailer") TrailerObject trailerObject, @JsonProperty("nextAiringEpisode") SeasonNextAiringEpisode seasonNextAiringEpisode, @JsonProperty("recommendations") Recommendations recommendations, @JsonProperty("relations") SeasonEdges seasonEdges) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(startDate, "startDate");
            this.id = i;
            this.idMal = num;
            this.seasonYear = i2;
            this.title = title;
            this.startDate = startDate;
            this.averageScore = num2;
            this.meanScore = num3;
            this.bannerImage = str;
            this.trailer = trailerObject;
            this.nextAiringEpisode = seasonNextAiringEpisode;
            this.recommendations = recommendations;
            this.relations = seasonEdges;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$app(GetSearchMedia self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.id);
            output.encodeNullableSerializableElement(serialDesc, 1, IntSerializer.INSTANCE, self.idMal);
            output.encodeIntElement(serialDesc, 2, self.seasonYear);
            output.encodeSerializableElement(serialDesc, 3, AniListApi$GetSearchTitle$$serializer.INSTANCE, self.title);
            output.encodeSerializableElement(serialDesc, 4, AniListApi$StartedAt$$serializer.INSTANCE, self.startDate);
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.averageScore);
            output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.meanScore);
            output.encodeNullableSerializableElement(serialDesc, 7, StringSerializer.INSTANCE, self.bannerImage);
            output.encodeNullableSerializableElement(serialDesc, 8, AniListApi$TrailerObject$$serializer.INSTANCE, self.trailer);
            output.encodeNullableSerializableElement(serialDesc, 9, AniListApi$SeasonNextAiringEpisode$$serializer.INSTANCE, self.nextAiringEpisode);
            output.encodeNullableSerializableElement(serialDesc, 10, AniListApi$Recommendations$$serializer.INSTANCE, self.recommendations);
            output.encodeNullableSerializableElement(serialDesc, 11, AniListApi$SeasonEdges$$serializer.INSTANCE, self.relations);
        }

        public final int getId() {
            return this.id;
        }

        public final Integer getIdMal() {
            return this.idMal;
        }

        public final int getSeasonYear() {
            return this.seasonYear;
        }

        public final GetSearchTitle getTitle() {
            return this.title;
        }

        public final StartedAt getStartDate() {
            return this.startDate;
        }

        public final Integer getAverageScore() {
            return this.averageScore;
        }

        public final Integer getMeanScore() {
            return this.meanScore;
        }

        public final String getBannerImage() {
            return this.bannerImage;
        }

        public final TrailerObject getTrailer() {
            return this.trailer;
        }

        public final SeasonNextAiringEpisode getNextAiringEpisode() {
            return this.nextAiringEpisode;
        }

        public final Recommendations getRecommendations() {
            return this.recommendations;
        }

        public final SeasonEdges getRelations() {
            return this.relations;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage;", "", "page", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPage$annotations", "()V", "getPage", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetSearchPage {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("Page")
        private final GetSearchData page;

        public static /* synthetic */ GetSearchPage copy$default(GetSearchPage getSearchPage, GetSearchData getSearchData, int i, Object obj) {
            if ((i & 1) != 0) {
                getSearchData = getSearchPage.page;
            }
            return getSearchPage.copy(getSearchData);
        }

        @SerialName("Page")
        public static /* synthetic */ void getPage$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final GetSearchData getPage() {
            return this.page;
        }

        public final GetSearchPage copy(@JsonProperty("Page") GetSearchData page) {
            return new GetSearchPage(page);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GetSearchPage) && Intrinsics.areEqual(this.page, ((GetSearchPage) other).page);
        }

        public int hashCode() {
            GetSearchData getSearchData = this.page;
            if (getSearchData == null) {
                return 0;
            }
            return getSearchData.hashCode();
        }

        public String toString() {
            return "GetSearchPage(page=" + this.page + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetSearchPage> serializer() {
                return AniListApi$GetSearchPage$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetSearchPage(int i, GetSearchData getSearchData, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$GetSearchPage$$serializer.INSTANCE.getDescriptor());
            }
            this.page = getSearchData;
        }

        public GetSearchPage(@JsonProperty("Page") GetSearchData getSearchData) {
            this.page = getSearchData;
        }

        public final GetSearchData getPage() {
            return this.page;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0019\u0012\u0010\b\u0001\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006B+\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\u0011\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001¢\u0006\u0002\b\u001fR$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData;", "", LinkHeader.Parameters.Media, "", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchMedia;", "<init>", "(Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getMedia$annotations", "()V", "getMedia", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetSearchData {

        @JsonProperty(LinkHeader.Parameters.Media)
        private final List<GetSearchMedia> media;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Lazy<KSerializer<Object>>[] $childSerializers = {LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: com.lagradost.cloudstream3.syncproviders.providers.AniListApi$GetSearchData$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AniListApi.GetSearchData._childSerializers$_anonymous_();
            }
        })};

        /* JADX INFO: Access modifiers changed from: private */
        public static final /* synthetic */ KSerializer _childSerializers$_anonymous_() {
            return new ArrayListSerializer(AniListApi$GetSearchMedia$$serializer.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ GetSearchData copy$default(GetSearchData getSearchData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = getSearchData.media;
            }
            return getSearchData.copy(list);
        }

        @SerialName(LinkHeader.Parameters.Media)
        public static /* synthetic */ void getMedia$annotations() {
        }

        public final List<GetSearchMedia> component1() {
            return this.media;
        }

        public final GetSearchData copy(@JsonProperty(LinkHeader.Parameters.Media) List<GetSearchMedia> media) {
            return new GetSearchData(media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GetSearchData) && Intrinsics.areEqual(this.media, ((GetSearchData) other).media);
        }

        public int hashCode() {
            List<GetSearchMedia> list = this.media;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "GetSearchData(media=" + this.media + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchData;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetSearchData> serializer() {
                return AniListApi$GetSearchData$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetSearchData(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$GetSearchData$$serializer.INSTANCE.getDescriptor());
            }
            this.media = list;
        }

        public GetSearchData(@JsonProperty(LinkHeader.Parameters.Media) List<GetSearchMedia> list) {
            this.media = list;
        }

        public final List<GetSearchMedia> getMedia() {
            return this.media;
        }
    }

    /* JADX INFO: compiled from: AniListApi.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0004\u0010\nJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchRoot;", "", "data", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage;", "<init>", "(Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData$annotations", "()V", "getData", "()Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchPage;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app", "$serializer", "Companion", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @Serializable
    public static final /* data */ class GetSearchRoot {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        @JsonProperty("data")
        private final GetSearchPage data;

        public static /* synthetic */ GetSearchRoot copy$default(GetSearchRoot getSearchRoot, GetSearchPage getSearchPage, int i, Object obj) {
            if ((i & 1) != 0) {
                getSearchPage = getSearchRoot.data;
            }
            return getSearchRoot.copy(getSearchPage);
        }

        @SerialName("data")
        public static /* synthetic */ void getData$annotations() {
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final GetSearchPage getData() {
            return this.data;
        }

        public final GetSearchRoot copy(@JsonProperty("data") GetSearchPage data) {
            return new GetSearchRoot(data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GetSearchRoot) && Intrinsics.areEqual(this.data, ((GetSearchRoot) other).data);
        }

        public int hashCode() {
            GetSearchPage getSearchPage = this.data;
            if (getSearchPage == null) {
                return 0;
            }
            return getSearchPage.hashCode();
        }

        public String toString() {
            return "GetSearchRoot(data=" + this.data + ')';
        }

        /* JADX INFO: compiled from: AniListApi.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchRoot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/lagradost/cloudstream3/syncproviders/providers/AniListApi$GetSearchRoot;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<GetSearchRoot> serializer() {
                return AniListApi$GetSearchRoot$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ GetSearchRoot(int i, GetSearchPage getSearchPage, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, AniListApi$GetSearchRoot$$serializer.INSTANCE.getDescriptor());
            }
            this.data = getSearchPage;
        }

        public GetSearchRoot(@JsonProperty("data") GetSearchPage getSearchPage) {
            this.data = getSearchPage;
        }

        public final GetSearchPage getData() {
            return this.data;
        }
    }
}
