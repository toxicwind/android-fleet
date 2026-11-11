package com.lagradost.cloudstream3.syncproviders;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.NextAiring;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.SyncWatchType;
import com.lagradost.cloudstream3.ui.library.ListSorting;
import com.lagradost.cloudstream3.utils.Levenshtein;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyncAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u00002\u00020\u0001:\b+,-./012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0097@¢\u0006\u0002\u0010\u001dJ\"\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u000bH\u0097@¢\u0006\u0002\u0010\u001fJ\"\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u000bH\u0097@¢\u0006\u0002\u0010\u001fJ(\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010%\u001a\u00020\u000bH\u0097@¢\u0006\u0002\u0010\u001fJ\u001a\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0097@¢\u0006\u0002\u0010(J\u0012\u0010)\u001a\u0004\u0018\u00010\u000b2\u0006\u0010*\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u00063"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI;", "Lcom/lagradost/cloudstream3/syncproviders/AuthAPI;", "<init>", "()V", "requireLibraryRefresh", "", "getRequireLibraryRefresh", "()Z", "setRequireLibraryRefresh", "(Z)V", "mainUrl", "", "getMainUrl", "()Ljava/lang/String;", "supportedWatchTypes", "", "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "getSupportedWatchTypes", "()Ljava/util/Set;", "syncIdName", "Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "getSyncIdName", "()Lcom/lagradost/cloudstream3/syncproviders/SyncIdName;", "updateStatus", "auth", "Lcom/lagradost/cloudstream3/syncproviders/AuthData;", "id", "newStatus", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", NotificationCompat.CATEGORY_STATUS, "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "search", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", SearchIntents.EXTRA_QUERY, "library", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "(Lcom/lagradost/cloudstream3/syncproviders/AuthData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlToId", ImagesContract.URL, "SyncSearchResult", "AbstractSyncStatus", "SyncStatus", "SyncResult", "Page", "LibraryMetadata", "LibraryList", "LibraryItem", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class SyncAPI extends AuthAPI {
    private final SyncIdName syncIdName;
    private boolean requireLibraryRefresh = true;
    private final String mainUrl = "NONE";
    private final Set<SyncWatchType> supportedWatchTypes = CollectionsKt.toSet(SyncWatchType.getEntries());

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\u00020\u0005X¦\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u0017X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u0011X¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", "", "<init>", "()V", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "getStatus", "()Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "setStatus", "(Lcom/lagradost/cloudstream3/ui/SyncWatchType;)V", "score", "Lcom/lagradost/cloudstream3/Score;", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "watchedEpisodes", "", "getWatchedEpisodes", "()Ljava/lang/Integer;", "setWatchedEpisodes", "(Ljava/lang/Integer;)V", "isFavorite", "", "()Ljava/lang/Boolean;", "setFavorite", "(Ljava/lang/Boolean;)V", "maxEpisodes", "getMaxEpisodes", "setMaxEpisodes", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static abstract class AbstractSyncStatus {
        public abstract Integer getMaxEpisodes();

        public abstract Score getScore();

        public abstract SyncWatchType getStatus();

        public abstract Integer getWatchedEpisodes();

        public abstract Boolean isFavorite();

        public abstract void setFavorite(Boolean bool);

        public abstract void setMaxEpisodes(Integer num);

        public abstract void setScore(Score score);

        public abstract void setStatus(SyncWatchType syncWatchType);

        public abstract void setWatchedEpisodes(Integer num);
    }

    public Object library(AuthData authData, Continuation<? super LibraryMetadata> continuation) {
        return library$suspendImpl(this, authData, continuation);
    }

    public Object load(AuthData authData, String str, Continuation<? super SyncResult> continuation) {
        return load$suspendImpl(this, authData, str, continuation);
    }

    public Object search(AuthData authData, String str, Continuation<? super List<SyncSearchResult>> continuation) {
        return search$suspendImpl(this, authData, str, continuation);
    }

    public Object status(AuthData authData, String str, Continuation<? super AbstractSyncStatus> continuation) {
        return status$suspendImpl(this, authData, str, continuation);
    }

    public Object updateStatus(AuthData authData, String str, AbstractSyncStatus abstractSyncStatus, Continuation<? super Boolean> continuation) {
        return updateStatus$suspendImpl(this, authData, str, abstractSyncStatus, continuation);
    }

    public String urlToId(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return null;
    }

    public boolean getRequireLibraryRefresh() {
        return this.requireLibraryRefresh;
    }

    public void setRequireLibraryRefresh(boolean z) {
        this.requireLibraryRefresh = z;
    }

    public String getMainUrl() {
        return this.mainUrl;
    }

    public Set<SyncWatchType> getSupportedWatchTypes() {
        return this.supportedWatchTypes;
    }

    public SyncIdName getSyncIdName() {
        return this.syncIdName;
    }

    static /* synthetic */ Object updateStatus$suspendImpl(SyncAPI syncAPI, AuthData authData, String str, AbstractSyncStatus abstractSyncStatus, Continuation<? super Boolean> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object status$suspendImpl(SyncAPI syncAPI, AuthData authData, String str, Continuation<? super AbstractSyncStatus> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object load$suspendImpl(SyncAPI syncAPI, AuthData authData, String str, Continuation<? super SyncResult> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object search$suspendImpl(SyncAPI syncAPI, AuthData authData, String str, Continuation<? super List<SyncSearchResult>> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    static /* synthetic */ Object library$suspendImpl(SyncAPI syncAPI, AuthData authData, Continuation<? super LibraryMetadata> continuation) {
        throw new NotImplementedError(null, 1, null);
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0017\u00109\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rHÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010;\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u008a\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010=J\u0014\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AHÖ\u0083\u0004J\n\u0010B\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010C\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0015\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u0010\n\u0002\u0010-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006D"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", "apiName", "syncId", ImagesContract.URL, "posterUrl", "type", "Lcom/lagradost/cloudstream3/TvType;", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "", "id", "", "score", "Lcom/lagradost/cloudstream3/Score;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/Score;)V", "getName", "()Ljava/lang/String;", "getApiName", "getSyncId", "setSyncId", "(Ljava/lang/String;)V", "getUrl", "getPosterUrl", "setPosterUrl", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "()Ljava/util/Map;", "setPosterHeaders", "(Ljava/util/Map;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/Score;)Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SyncSearchResult implements SearchResponse {
        private final String apiName;
        private Integer id;
        private final String name;
        private Map<String, String> posterHeaders;
        private String posterUrl;
        private SearchQuality quality;
        private Score score;
        private String syncId;
        private TvType type;
        private final String url;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SyncSearchResult copy$default(SyncSearchResult syncSearchResult, String str, String str2, String str3, String str4, String str5, TvType tvType, SearchQuality searchQuality, Map map, Integer num, Score score, int i, Object obj) {
            if ((i & 1) != 0) {
                str = syncSearchResult.name;
            }
            if ((i & 2) != 0) {
                str2 = syncSearchResult.apiName;
            }
            if ((i & 4) != 0) {
                str3 = syncSearchResult.syncId;
            }
            if ((i & 8) != 0) {
                str4 = syncSearchResult.url;
            }
            if ((i & 16) != 0) {
                str5 = syncSearchResult.posterUrl;
            }
            if ((i & 32) != 0) {
                tvType = syncSearchResult.type;
            }
            if ((i & 64) != 0) {
                searchQuality = syncSearchResult.quality;
            }
            if ((i & 128) != 0) {
                map = syncSearchResult.posterHeaders;
            }
            if ((i & 256) != 0) {
                num = syncSearchResult.id;
            }
            if ((i & 512) != 0) {
                score = syncSearchResult.score;
            }
            Integer num2 = num;
            Score score2 = score;
            SearchQuality searchQuality2 = searchQuality;
            Map map2 = map;
            String str6 = str5;
            TvType tvType2 = tvType;
            return syncSearchResult.copy(str, str2, str3, str4, str6, tvType2, searchQuality2, map2, num2, score2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSyncId() {
            return this.syncId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final SearchQuality getQuality() {
            return this.quality;
        }

        public final Map<String, String> component8() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        public final SyncSearchResult copy(String name, String apiName, String syncId, String url, String posterUrl, TvType type, SearchQuality quality, Map<String, String> posterHeaders, Integer id, Score score) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(syncId, "syncId");
            Intrinsics.checkNotNullParameter(url, "url");
            return new SyncSearchResult(name, apiName, syncId, url, posterUrl, type, quality, posterHeaders, id, score);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SyncSearchResult)) {
                return false;
            }
            SyncSearchResult syncSearchResult = (SyncSearchResult) other;
            return Intrinsics.areEqual(this.name, syncSearchResult.name) && Intrinsics.areEqual(this.apiName, syncSearchResult.apiName) && Intrinsics.areEqual(this.syncId, syncSearchResult.syncId) && Intrinsics.areEqual(this.url, syncSearchResult.url) && Intrinsics.areEqual(this.posterUrl, syncSearchResult.posterUrl) && this.type == syncSearchResult.type && this.quality == syncSearchResult.quality && Intrinsics.areEqual(this.posterHeaders, syncSearchResult.posterHeaders) && Intrinsics.areEqual(this.id, syncSearchResult.id) && Intrinsics.areEqual(this.score, syncSearchResult.score);
        }

        public int hashCode() {
            int iHashCode = ((((((this.name.hashCode() * 31) + this.apiName.hashCode()) * 31) + this.syncId.hashCode()) * 31) + this.url.hashCode()) * 31;
            String str = this.posterUrl;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            TvType tvType = this.type;
            int iHashCode3 = (iHashCode2 + (tvType == null ? 0 : tvType.hashCode())) * 31;
            SearchQuality searchQuality = this.quality;
            int iHashCode4 = (iHashCode3 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
            Map<String, String> map = this.posterHeaders;
            int iHashCode5 = (iHashCode4 + (map == null ? 0 : map.hashCode())) * 31;
            Integer num = this.id;
            int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
            Score score = this.score;
            return iHashCode6 + (score != null ? score.getData() : 0);
        }

        public String toString() {
            return "SyncSearchResult(name=" + this.name + ", apiName=" + this.apiName + ", syncId=" + this.syncId + ", url=" + this.url + ", posterUrl=" + this.posterUrl + ", type=" + this.type + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", id=" + this.id + ", score=" + this.score + ')';
        }

        public SyncSearchResult(String name, String apiName, String syncId, String url, String str, TvType tvType, SearchQuality searchQuality, Map<String, String> map, Integer num, Score score) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            Intrinsics.checkNotNullParameter(syncId, "syncId");
            Intrinsics.checkNotNullParameter(url, "url");
            this.name = name;
            this.apiName = apiName;
            this.syncId = syncId;
            this.url = url;
            this.posterUrl = str;
            this.type = tvType;
            this.quality = searchQuality;
            this.posterHeaders = map;
            this.id = num;
            this.score = score;
        }

        public /* synthetic */ SyncSearchResult(String str, String str2, String str3, String str4, String str5, TvType tvType, SearchQuality searchQuality, Map map, Integer num, Score score, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, str5, (i & 32) != 0 ? null : tvType, (i & 64) != 0 ? null : searchQuality, (i & 128) != 0 ? null : map, (i & 256) != 0 ? null : num, (i & 512) != 0 ? null : score);
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        public final String getSyncId() {
            return this.syncId;
        }

        public final void setSyncId(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.syncId = str;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010#\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016JH\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010&J\u0014\u0010'\u001a\u00020\t2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0083\u0004J\n\u0010*\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\b\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001e\u0010\u0016\"\u0004\b\u001f\u0010\u0018¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncStatus;", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$AbstractSyncStatus;", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "score", "Lcom/lagradost/cloudstream3/Score;", "watchedEpisodes", "", "isFavorite", "", "maxEpisodes", "<init>", "(Lcom/lagradost/cloudstream3/ui/SyncWatchType;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "getStatus", "()Lcom/lagradost/cloudstream3/ui/SyncWatchType;", "setStatus", "(Lcom/lagradost/cloudstream3/ui/SyncWatchType;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getWatchedEpisodes", "()Ljava/lang/Integer;", "setWatchedEpisodes", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "()Ljava/lang/Boolean;", "setFavorite", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getMaxEpisodes", "setMaxEpisodes", "component1", "component2", "component3", "component4", "component5", "copy", "(Lcom/lagradost/cloudstream3/ui/SyncWatchType;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncStatus;", "equals", "other", "", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SyncStatus extends AbstractSyncStatus {
        private Boolean isFavorite;
        private Integer maxEpisodes;
        private Score score;
        private SyncWatchType status;
        private Integer watchedEpisodes;

        public static /* synthetic */ SyncStatus copy$default(SyncStatus syncStatus, SyncWatchType syncWatchType, Score score, Integer num, Boolean bool, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                syncWatchType = syncStatus.status;
            }
            if ((i & 2) != 0) {
                score = syncStatus.score;
            }
            if ((i & 4) != 0) {
                num = syncStatus.watchedEpisodes;
            }
            if ((i & 8) != 0) {
                bool = syncStatus.isFavorite;
            }
            if ((i & 16) != 0) {
                num2 = syncStatus.maxEpisodes;
            }
            Integer num3 = num2;
            Integer num4 = num;
            return syncStatus.copy(syncWatchType, score, num4, bool, num3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SyncWatchType getStatus() {
            return this.status;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getWatchedEpisodes() {
            return this.watchedEpisodes;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Boolean getIsFavorite() {
            return this.isFavorite;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getMaxEpisodes() {
            return this.maxEpisodes;
        }

        public final SyncStatus copy(SyncWatchType status, Score score, Integer watchedEpisodes, Boolean isFavorite, Integer maxEpisodes) {
            Intrinsics.checkNotNullParameter(status, "status");
            return new SyncStatus(status, score, watchedEpisodes, isFavorite, maxEpisodes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SyncStatus)) {
                return false;
            }
            SyncStatus syncStatus = (SyncStatus) other;
            return this.status == syncStatus.status && Intrinsics.areEqual(this.score, syncStatus.score) && Intrinsics.areEqual(this.watchedEpisodes, syncStatus.watchedEpisodes) && Intrinsics.areEqual(this.isFavorite, syncStatus.isFavorite) && Intrinsics.areEqual(this.maxEpisodes, syncStatus.maxEpisodes);
        }

        public int hashCode() {
            int iHashCode = this.status.hashCode() * 31;
            Score score = this.score;
            int data = (iHashCode + (score == null ? 0 : score.getData())) * 31;
            Integer num = this.watchedEpisodes;
            int iHashCode2 = (data + (num == null ? 0 : num.hashCode())) * 31;
            Boolean bool = this.isFavorite;
            int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
            Integer num2 = this.maxEpisodes;
            return iHashCode3 + (num2 != null ? num2.hashCode() : 0);
        }

        public String toString() {
            return "SyncStatus(status=" + this.status + ", score=" + this.score + ", watchedEpisodes=" + this.watchedEpisodes + ", isFavorite=" + this.isFavorite + ", maxEpisodes=" + this.maxEpisodes + ')';
        }

        public SyncStatus(SyncWatchType status, Score score, Integer num, Boolean bool, Integer num2) {
            Intrinsics.checkNotNullParameter(status, "status");
            this.status = status;
            this.score = score;
            this.watchedEpisodes = num;
            this.isFavorite = bool;
            this.maxEpisodes = num2;
        }

        public /* synthetic */ SyncStatus(SyncWatchType syncWatchType, Score score, Integer num, Boolean bool, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(syncWatchType, score, num, (i & 8) != 0 ? null : bool, (i & 16) != 0 ? null : num2);
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

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public Integer getWatchedEpisodes() {
            return this.watchedEpisodes;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public void setWatchedEpisodes(Integer num) {
            this.watchedEpisodes = num;
        }

        @Override // com.lagradost.cloudstream3.syncproviders.SyncAPI.AbstractSyncStatus
        public Boolean isFavorite() {
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
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\\\b\u0086\b\u0018\u00002\u00020\u0001B£\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u0012\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0010¢\u0006\u0004\b!\u0010\"J\t\u0010a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010(J\u000b\u0010c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010(J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0011\u0010i\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u0011\u0010j\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u0011\u0010k\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u0011\u0010l\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u0010\u0010m\u001a\u0004\u0018\u00010\u0015HÆ\u0003¢\u0006\u0002\u0010HJ\u000b\u0010n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010p\u001a\u0004\u0018\u00010\u0019HÆ\u0003¢\u0006\u0002\u0010QJ\u0010\u0010q\u001a\u0004\u0018\u00010\u0019HÆ\u0003¢\u0006\u0002\u0010QJ\u0011\u0010r\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0010HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u0011\u0010u\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0010HÆ\u0003J¬\u0002\u0010v\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00102\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010wJ\u0014\u0010x\u001a\u00020\u00152\b\u0010y\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010z\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010{\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010$\"\u0004\b-\u0010&R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b2\u0010(\"\u0004\b3\u0010*R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010$\"\u0004\b5\u0010&R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010?\"\u0004\bC\u0010AR\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010?\"\u0004\bE\u0010AR\"\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010?\"\u0004\bG\u0010AR\u001e\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u0010K\u001a\u0004\b\u0014\u0010H\"\u0004\bI\u0010JR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010$\"\u0004\bM\u0010&R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010$\"\u0004\bO\u0010&R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u0010\n\u0002\u0010T\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u0010\n\u0002\u0010T\u001a\u0004\bU\u0010Q\"\u0004\bV\u0010SR\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010?\"\u0004\bX\u0010AR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010Z\"\u0004\b^\u0010\\R\"\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010?\"\u0004\b`\u0010A¨\u0006|"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "", "id", "", "totalEpisodes", "", "title", "publicScore", "Lcom/lagradost/cloudstream3/Score;", TypedValues.TransitionType.S_DURATION, "synopsis", "airStatus", "Lcom/lagradost/cloudstream3/ShowStatus;", "nextAiring", "Lcom/lagradost/cloudstream3/NextAiring;", "studio", "", "genres", "synonyms", "trailers", "isAdult", "", "posterUrl", "backgroundPosterUrl", "startDate", "", "endDate", "recommendations", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "nextSeason", "prevSeason", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/ShowStatus;Lcom/lagradost/cloudstream3/NextAiring;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getTotalEpisodes", "()Ljava/lang/Integer;", "setTotalEpisodes", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTitle", "setTitle", "getPublicScore", "()Lcom/lagradost/cloudstream3/Score;", "setPublicScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getDuration", "setDuration", "getSynopsis", "setSynopsis", "getAirStatus", "()Lcom/lagradost/cloudstream3/ShowStatus;", "setAirStatus", "(Lcom/lagradost/cloudstream3/ShowStatus;)V", "getNextAiring", "()Lcom/lagradost/cloudstream3/NextAiring;", "setNextAiring", "(Lcom/lagradost/cloudstream3/NextAiring;)V", "getStudio", "()Ljava/util/List;", "setStudio", "(Ljava/util/List;)V", "getGenres", "setGenres", "getSynonyms", "setSynonyms", "getTrailers", "setTrailers", "()Ljava/lang/Boolean;", "setAdult", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getPosterUrl", "setPosterUrl", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "getStartDate", "()Ljava/lang/Long;", "setStartDate", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getEndDate", "setEndDate", "getRecommendations", "setRecommendations", "getNextSeason", "()Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;", "setNextSeason", "(Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;)V", "getPrevSeason", "setPrevSeason", "getActors", "setActors", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/ShowStatus;Lcom/lagradost/cloudstream3/NextAiring;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncSearchResult;Ljava/util/List;)Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$SyncResult;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SyncResult {
        private List<ActorData> actors;
        private ShowStatus airStatus;
        private String backgroundPosterUrl;
        private Integer duration;
        private Long endDate;
        private List<String> genres;
        private String id;
        private Boolean isAdult;
        private NextAiring nextAiring;
        private SyncSearchResult nextSeason;
        private String posterUrl;
        private SyncSearchResult prevSeason;
        private Score publicScore;
        private List<SyncSearchResult> recommendations;
        private Long startDate;
        private List<String> studio;
        private List<String> synonyms;
        private String synopsis;
        private String title;
        private Integer totalEpisodes;
        private List<String> trailers;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SyncResult copy$default(SyncResult syncResult, String str, Integer num, String str2, Score score, Integer num2, String str3, ShowStatus showStatus, NextAiring nextAiring, List list, List list2, List list3, List list4, Boolean bool, String str4, String str5, Long l, Long l2, List list5, SyncSearchResult syncSearchResult, SyncSearchResult syncSearchResult2, List list6, int i, Object obj) {
            List list7;
            SyncSearchResult syncSearchResult3;
            String str6 = (i & 1) != 0 ? syncResult.id : str;
            Integer num3 = (i & 2) != 0 ? syncResult.totalEpisodes : num;
            String str7 = (i & 4) != 0 ? syncResult.title : str2;
            Score score2 = (i & 8) != 0 ? syncResult.publicScore : score;
            Integer num4 = (i & 16) != 0 ? syncResult.duration : num2;
            String str8 = (i & 32) != 0 ? syncResult.synopsis : str3;
            ShowStatus showStatus2 = (i & 64) != 0 ? syncResult.airStatus : showStatus;
            NextAiring nextAiring2 = (i & 128) != 0 ? syncResult.nextAiring : nextAiring;
            List list8 = (i & 256) != 0 ? syncResult.studio : list;
            List list9 = (i & 512) != 0 ? syncResult.genres : list2;
            List list10 = (i & 1024) != 0 ? syncResult.synonyms : list3;
            List list11 = (i & 2048) != 0 ? syncResult.trailers : list4;
            Boolean bool2 = (i & 4096) != 0 ? syncResult.isAdult : bool;
            String str9 = (i & 8192) != 0 ? syncResult.posterUrl : str4;
            String str10 = str6;
            String str11 = (i & 16384) != 0 ? syncResult.backgroundPosterUrl : str5;
            Long l3 = (i & 32768) != 0 ? syncResult.startDate : l;
            Long l4 = (i & 65536) != 0 ? syncResult.endDate : l2;
            List list12 = (i & 131072) != 0 ? syncResult.recommendations : list5;
            SyncSearchResult syncSearchResult4 = (i & 262144) != 0 ? syncResult.nextSeason : syncSearchResult;
            SyncSearchResult syncSearchResult5 = (i & 524288) != 0 ? syncResult.prevSeason : syncSearchResult2;
            if ((i & 1048576) != 0) {
                syncSearchResult3 = syncSearchResult5;
                list7 = syncResult.actors;
            } else {
                list7 = list6;
                syncSearchResult3 = syncSearchResult5;
            }
            return syncResult.copy(str10, num3, str7, score2, num4, str8, showStatus2, nextAiring2, list8, list9, list10, list11, bool2, str9, str11, l3, l4, list12, syncSearchResult4, syncSearchResult3, list7);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        public final List<String> component10() {
            return this.genres;
        }

        public final List<String> component11() {
            return this.synonyms;
        }

        public final List<String> component12() {
            return this.trailers;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Boolean getIsAdult() {
            return this.isAdult;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getBackgroundPosterUrl() {
            return this.backgroundPosterUrl;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Long getStartDate() {
            return this.startDate;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final Long getEndDate() {
            return this.endDate;
        }

        public final List<SyncSearchResult> component18() {
            return this.recommendations;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final SyncSearchResult getNextSeason() {
            return this.nextSeason;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTotalEpisodes() {
            return this.totalEpisodes;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final SyncSearchResult getPrevSeason() {
            return this.prevSeason;
        }

        public final List<ActorData> component21() {
            return this.actors;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Score getPublicScore() {
            return this.publicScore;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getDuration() {
            return this.duration;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSynopsis() {
            return this.synopsis;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final ShowStatus getAirStatus() {
            return this.airStatus;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final NextAiring getNextAiring() {
            return this.nextAiring;
        }

        public final List<String> component9() {
            return this.studio;
        }

        public final SyncResult copy(String id, Integer totalEpisodes, String title, Score publicScore, Integer duration, String synopsis, ShowStatus airStatus, NextAiring nextAiring, List<String> studio, List<String> genres, List<String> synonyms, List<String> trailers, Boolean isAdult, String posterUrl, String backgroundPosterUrl, Long startDate, Long endDate, List<SyncSearchResult> recommendations, SyncSearchResult nextSeason, SyncSearchResult prevSeason, List<ActorData> actors) {
            Intrinsics.checkNotNullParameter(id, "id");
            return new SyncResult(id, totalEpisodes, title, publicScore, duration, synopsis, airStatus, nextAiring, studio, genres, synonyms, trailers, isAdult, posterUrl, backgroundPosterUrl, startDate, endDate, recommendations, nextSeason, prevSeason, actors);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SyncResult)) {
                return false;
            }
            SyncResult syncResult = (SyncResult) other;
            return Intrinsics.areEqual(this.id, syncResult.id) && Intrinsics.areEqual(this.totalEpisodes, syncResult.totalEpisodes) && Intrinsics.areEqual(this.title, syncResult.title) && Intrinsics.areEqual(this.publicScore, syncResult.publicScore) && Intrinsics.areEqual(this.duration, syncResult.duration) && Intrinsics.areEqual(this.synopsis, syncResult.synopsis) && this.airStatus == syncResult.airStatus && Intrinsics.areEqual(this.nextAiring, syncResult.nextAiring) && Intrinsics.areEqual(this.studio, syncResult.studio) && Intrinsics.areEqual(this.genres, syncResult.genres) && Intrinsics.areEqual(this.synonyms, syncResult.synonyms) && Intrinsics.areEqual(this.trailers, syncResult.trailers) && Intrinsics.areEqual(this.isAdult, syncResult.isAdult) && Intrinsics.areEqual(this.posterUrl, syncResult.posterUrl) && Intrinsics.areEqual(this.backgroundPosterUrl, syncResult.backgroundPosterUrl) && Intrinsics.areEqual(this.startDate, syncResult.startDate) && Intrinsics.areEqual(this.endDate, syncResult.endDate) && Intrinsics.areEqual(this.recommendations, syncResult.recommendations) && Intrinsics.areEqual(this.nextSeason, syncResult.nextSeason) && Intrinsics.areEqual(this.prevSeason, syncResult.prevSeason) && Intrinsics.areEqual(this.actors, syncResult.actors);
        }

        public int hashCode() {
            int iHashCode = this.id.hashCode() * 31;
            Integer num = this.totalEpisodes;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            String str = this.title;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            Score score = this.publicScore;
            int data = (iHashCode3 + (score == null ? 0 : score.getData())) * 31;
            Integer num2 = this.duration;
            int iHashCode4 = (data + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str2 = this.synopsis;
            int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
            ShowStatus showStatus = this.airStatus;
            int iHashCode6 = (iHashCode5 + (showStatus == null ? 0 : showStatus.hashCode())) * 31;
            NextAiring nextAiring = this.nextAiring;
            int iHashCode7 = (iHashCode6 + (nextAiring == null ? 0 : nextAiring.hashCode())) * 31;
            List<String> list = this.studio;
            int iHashCode8 = (iHashCode7 + (list == null ? 0 : list.hashCode())) * 31;
            List<String> list2 = this.genres;
            int iHashCode9 = (iHashCode8 + (list2 == null ? 0 : list2.hashCode())) * 31;
            List<String> list3 = this.synonyms;
            int iHashCode10 = (iHashCode9 + (list3 == null ? 0 : list3.hashCode())) * 31;
            List<String> list4 = this.trailers;
            int iHashCode11 = (iHashCode10 + (list4 == null ? 0 : list4.hashCode())) * 31;
            Boolean bool = this.isAdult;
            int iHashCode12 = (iHashCode11 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str3 = this.posterUrl;
            int iHashCode13 = (iHashCode12 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.backgroundPosterUrl;
            int iHashCode14 = (iHashCode13 + (str4 == null ? 0 : str4.hashCode())) * 31;
            Long l = this.startDate;
            int iHashCode15 = (iHashCode14 + (l == null ? 0 : l.hashCode())) * 31;
            Long l2 = this.endDate;
            int iHashCode16 = (iHashCode15 + (l2 == null ? 0 : l2.hashCode())) * 31;
            List<SyncSearchResult> list5 = this.recommendations;
            int iHashCode17 = (iHashCode16 + (list5 == null ? 0 : list5.hashCode())) * 31;
            SyncSearchResult syncSearchResult = this.nextSeason;
            int iHashCode18 = (iHashCode17 + (syncSearchResult == null ? 0 : syncSearchResult.hashCode())) * 31;
            SyncSearchResult syncSearchResult2 = this.prevSeason;
            int iHashCode19 = (iHashCode18 + (syncSearchResult2 == null ? 0 : syncSearchResult2.hashCode())) * 31;
            List<ActorData> list6 = this.actors;
            return iHashCode19 + (list6 != null ? list6.hashCode() : 0);
        }

        public String toString() {
            return "SyncResult(id=" + this.id + ", totalEpisodes=" + this.totalEpisodes + ", title=" + this.title + ", publicScore=" + this.publicScore + ", duration=" + this.duration + ", synopsis=" + this.synopsis + ", airStatus=" + this.airStatus + ", nextAiring=" + this.nextAiring + ", studio=" + this.studio + ", genres=" + this.genres + ", synonyms=" + this.synonyms + ", trailers=" + this.trailers + ", isAdult=" + this.isAdult + ", posterUrl=" + this.posterUrl + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", recommendations=" + this.recommendations + ", nextSeason=" + this.nextSeason + ", prevSeason=" + this.prevSeason + ", actors=" + this.actors + ')';
        }

        public SyncResult(String id, Integer num, String str, Score score, Integer num2, String str2, ShowStatus showStatus, NextAiring nextAiring, List<String> list, List<String> list2, List<String> list3, List<String> list4, Boolean bool, String str3, String str4, Long l, Long l2, List<SyncSearchResult> list5, SyncSearchResult syncSearchResult, SyncSearchResult syncSearchResult2, List<ActorData> list6) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
            this.totalEpisodes = num;
            this.title = str;
            this.publicScore = score;
            this.duration = num2;
            this.synopsis = str2;
            this.airStatus = showStatus;
            this.nextAiring = nextAiring;
            this.studio = list;
            this.genres = list2;
            this.synonyms = list3;
            this.trailers = list4;
            this.isAdult = bool;
            this.posterUrl = str3;
            this.backgroundPosterUrl = str4;
            this.startDate = l;
            this.endDate = l2;
            this.recommendations = list5;
            this.nextSeason = syncSearchResult;
            this.prevSeason = syncSearchResult2;
            this.actors = list6;
        }

        public /* synthetic */ SyncResult(String str, Integer num, String str2, Score score, Integer num2, String str3, ShowStatus showStatus, NextAiring nextAiring, List list, List list2, List list3, List list4, Boolean bool, String str4, String str5, Long l, Long l2, List list5, SyncSearchResult syncSearchResult, SyncSearchResult syncSearchResult2, List list6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : score, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : showStatus, (i & 128) != 0 ? null : nextAiring, (i & 256) != 0 ? null : list, (i & 512) != 0 ? null : list2, (i & 1024) != 0 ? null : list3, (i & 2048) != 0 ? null : list4, (i & 4096) != 0 ? null : bool, (i & 8192) != 0 ? null : str4, (i & 16384) != 0 ? null : str5, (i & 32768) != 0 ? null : l, (i & 65536) != 0 ? null : l2, (i & 131072) != 0 ? null : list5, (i & 262144) != 0 ? null : syncSearchResult, (i & 524288) != 0 ? null : syncSearchResult2, (i & 1048576) != 0 ? null : list6);
        }

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.id = str;
        }

        public final Integer getTotalEpisodes() {
            return this.totalEpisodes;
        }

        public final void setTotalEpisodes(Integer num) {
            this.totalEpisodes = num;
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final Score getPublicScore() {
            return this.publicScore;
        }

        public final void setPublicScore(Score score) {
            this.publicScore = score;
        }

        public final Integer getDuration() {
            return this.duration;
        }

        public final void setDuration(Integer num) {
            this.duration = num;
        }

        public final String getSynopsis() {
            return this.synopsis;
        }

        public final void setSynopsis(String str) {
            this.synopsis = str;
        }

        public final ShowStatus getAirStatus() {
            return this.airStatus;
        }

        public final void setAirStatus(ShowStatus showStatus) {
            this.airStatus = showStatus;
        }

        public final NextAiring getNextAiring() {
            return this.nextAiring;
        }

        public final void setNextAiring(NextAiring nextAiring) {
            this.nextAiring = nextAiring;
        }

        public final List<String> getStudio() {
            return this.studio;
        }

        public final void setStudio(List<String> list) {
            this.studio = list;
        }

        public final List<String> getGenres() {
            return this.genres;
        }

        public final void setGenres(List<String> list) {
            this.genres = list;
        }

        public final List<String> getSynonyms() {
            return this.synonyms;
        }

        public final void setSynonyms(List<String> list) {
            this.synonyms = list;
        }

        public final List<String> getTrailers() {
            return this.trailers;
        }

        public final void setTrailers(List<String> list) {
            this.trailers = list;
        }

        public final Boolean isAdult() {
            return this.isAdult;
        }

        public final void setAdult(Boolean bool) {
            this.isAdult = bool;
        }

        public final String getPosterUrl() {
            return this.posterUrl;
        }

        public final void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        public final String getBackgroundPosterUrl() {
            return this.backgroundPosterUrl;
        }

        public final void setBackgroundPosterUrl(String str) {
            this.backgroundPosterUrl = str;
        }

        public final Long getStartDate() {
            return this.startDate;
        }

        public final void setStartDate(Long l) {
            this.startDate = l;
        }

        public final Long getEndDate() {
            return this.endDate;
        }

        public final void setEndDate(Long l) {
            this.endDate = l;
        }

        public final List<SyncSearchResult> getRecommendations() {
            return this.recommendations;
        }

        public final void setRecommendations(List<SyncSearchResult> list) {
            this.recommendations = list;
        }

        public final SyncSearchResult getNextSeason() {
            return this.nextSeason;
        }

        public final void setNextSeason(SyncSearchResult syncSearchResult) {
            this.nextSeason = syncSearchResult;
        }

        public final SyncSearchResult getPrevSeason() {
            return this.prevSeason;
        }

        public final void setPrevSeason(SyncSearchResult syncSearchResult) {
            this.prevSeason = syncSearchResult;
        }

        public final List<ActorData> getActors() {
            return this.actors;
        }

        public final void setActors(List<ActorData> list) {
            this.actors = list;
        }
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$Page;", "", "title", "Lcom/lagradost/cloudstream3/utils/UiText;", "items", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "<init>", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/util/List;)V", "getTitle", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "sort", "", "method", "Lcom/lagradost/cloudstream3/ui/library/ListSorting;", SearchIntents.EXTRA_QUERY, "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Page {
        private List<LibraryItem> items;
        private final UiText title;

        /* JADX INFO: compiled from: SyncAPI.kt */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ListSorting.values().length];
                try {
                    iArr[ListSorting.Query.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ListSorting.RatingHigh.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ListSorting.RatingLow.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[ListSorting.AlphabeticalA.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[ListSorting.AlphabeticalZ.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr[ListSorting.UpdatedNew.ordinal()] = 6;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr[ListSorting.UpdatedOld.ordinal()] = 7;
                } catch (NoSuchFieldError unused7) {
                }
                try {
                    iArr[ListSorting.ReleaseDateNew.ordinal()] = 8;
                } catch (NoSuchFieldError unused8) {
                }
                try {
                    iArr[ListSorting.ReleaseDateOld.ordinal()] = 9;
                } catch (NoSuchFieldError unused9) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Page copy$default(Page page, UiText uiText, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                uiText = page.title;
            }
            if ((i & 2) != 0) {
                list = page.items;
            }
            return page.copy(uiText, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UiText getTitle() {
            return this.title;
        }

        public final List<LibraryItem> component2() {
            return this.items;
        }

        public final Page copy(UiText title, List<LibraryItem> items) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(items, "items");
            return new Page(title, items);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Page)) {
                return false;
            }
            Page page = (Page) other;
            return Intrinsics.areEqual(this.title, page.title) && Intrinsics.areEqual(this.items, page.items);
        }

        public int hashCode() {
            return (this.title.hashCode() * 31) + this.items.hashCode();
        }

        public String toString() {
            return "Page(title=" + this.title + ", items=" + this.items + ')';
        }

        public Page(UiText title, List<LibraryItem> items) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(items, "items");
            this.title = title;
            this.items = items;
        }

        public final List<LibraryItem> getItems() {
            return this.items;
        }

        public final UiText getTitle() {
            return this.title;
        }

        public final void setItems(List<LibraryItem> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.items = list;
        }

        public static /* synthetic */ void sort$default(Page page, ListSorting listSorting, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            page.sort(listSorting, str);
        }

        public final void sort(ListSorting method, final String query) {
            List<LibraryItem> listSortedWith;
            switch (method == null ? -1 : WhenMappings.$EnumSwitchMapping$0[method.ordinal()]) {
                case 1:
                    listSortedWith = this.items;
                    if (query != null) {
                        listSortedWith = CollectionsKt.sortedWith(listSortedWith, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$1
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                Levenshtein levenshtein = Levenshtein.INSTANCE;
                                String lowerCase = query.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                String lowerCase2 = ((SyncAPI.LibraryItem) t).getName().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                Integer numValueOf = Integer.valueOf(-Levenshtein.partialRatio$default(levenshtein, lowerCase, lowerCase2, null, 4, null));
                                Levenshtein levenshtein2 = Levenshtein.INSTANCE;
                                String lowerCase3 = query.toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                                String lowerCase4 = ((SyncAPI.LibraryItem) t2).getName().toLowerCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                                return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(-Levenshtein.partialRatio$default(levenshtein2, lowerCase3, lowerCase4, null, 4, null)));
                            }
                        });
                    }
                    break;
                case 2:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$2
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            Score personalRating = ((SyncAPI.LibraryItem) t).getPersonalRating();
                            Integer numValueOf = Integer.valueOf(-(personalRating != null ? personalRating.toInt(100) : 0));
                            Score personalRating2 = ((SyncAPI.LibraryItem) t2).getPersonalRating();
                            return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(-(personalRating2 != null ? personalRating2.toInt(100) : 0)));
                        }
                    });
                    break;
                case 3:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$3
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            Score personalRating = ((SyncAPI.LibraryItem) t).getPersonalRating();
                            Integer numValueOf = Integer.valueOf(personalRating != null ? personalRating.toInt(100) : 0);
                            Score personalRating2 = ((SyncAPI.LibraryItem) t2).getPersonalRating();
                            return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(personalRating2 != null ? personalRating2.toInt(100) : 0));
                        }
                    });
                    break;
                case 4:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$4
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(((SyncAPI.LibraryItem) t).getName(), ((SyncAPI.LibraryItem) t2).getName());
                        }
                    });
                    break;
                case 5:
                    listSortedWith = CollectionsKt.reversed(CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$5
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(((SyncAPI.LibraryItem) t).getName(), ((SyncAPI.LibraryItem) t2).getName());
                        }
                    }));
                    break;
                case 6:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$6
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            Long lastUpdatedUnixTime = ((SyncAPI.LibraryItem) t).getLastUpdatedUnixTime();
                            Long lValueOf = lastUpdatedUnixTime != null ? Long.valueOf(lastUpdatedUnixTime.longValue() * (-1)) : null;
                            Long lastUpdatedUnixTime2 = ((SyncAPI.LibraryItem) t2).getLastUpdatedUnixTime();
                            return ComparisonsKt.compareValues(lValueOf, lastUpdatedUnixTime2 != null ? Long.valueOf(lastUpdatedUnixTime2.longValue() * (-1)) : null);
                        }
                    });
                    break;
                case 7:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$7
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(((SyncAPI.LibraryItem) t).getLastUpdatedUnixTime(), ((SyncAPI.LibraryItem) t2).getLastUpdatedUnixTime());
                        }
                    });
                    break;
                case 8:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedByDescending$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(((SyncAPI.LibraryItem) t2).getReleaseDate(), ((SyncAPI.LibraryItem) t).getReleaseDate());
                        }
                    });
                    break;
                case 9:
                    listSortedWith = CollectionsKt.sortedWith(this.items, new Comparator() { // from class: com.lagradost.cloudstream3.syncproviders.SyncAPI$Page$sort$$inlined$sortedBy$8
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(((SyncAPI.LibraryItem) t).getReleaseDate(), ((SyncAPI.LibraryItem) t2).getReleaseDate());
                        }
                    });
                    break;
                default:
                    listSortedWith = this.items;
                    break;
            }
            this.items = listSortedWith;
        }
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryMetadata;", "", "allLibraryLists", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryList;", "supportedListSorting", "", "Lcom/lagradost/cloudstream3/ui/library/ListSorting;", "<init>", "(Ljava/util/List;Ljava/util/Set;)V", "getAllLibraryLists", "()Ljava/util/List;", "getSupportedListSorting", "()Ljava/util/Set;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LibraryMetadata {
        private final List<LibraryList> allLibraryLists;
        private final Set<ListSorting> supportedListSorting;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LibraryMetadata copy$default(LibraryMetadata libraryMetadata, List list, Set set, int i, Object obj) {
            if ((i & 1) != 0) {
                list = libraryMetadata.allLibraryLists;
            }
            if ((i & 2) != 0) {
                set = libraryMetadata.supportedListSorting;
            }
            return libraryMetadata.copy(list, set);
        }

        public final List<LibraryList> component1() {
            return this.allLibraryLists;
        }

        public final Set<ListSorting> component2() {
            return this.supportedListSorting;
        }

        public final LibraryMetadata copy(List<LibraryList> allLibraryLists, Set<? extends ListSorting> supportedListSorting) {
            Intrinsics.checkNotNullParameter(allLibraryLists, "allLibraryLists");
            Intrinsics.checkNotNullParameter(supportedListSorting, "supportedListSorting");
            return new LibraryMetadata(allLibraryLists, supportedListSorting);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LibraryMetadata)) {
                return false;
            }
            LibraryMetadata libraryMetadata = (LibraryMetadata) other;
            return Intrinsics.areEqual(this.allLibraryLists, libraryMetadata.allLibraryLists) && Intrinsics.areEqual(this.supportedListSorting, libraryMetadata.supportedListSorting);
        }

        public int hashCode() {
            return (this.allLibraryLists.hashCode() * 31) + this.supportedListSorting.hashCode();
        }

        public String toString() {
            return "LibraryMetadata(allLibraryLists=" + this.allLibraryLists + ", supportedListSorting=" + this.supportedListSorting + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LibraryMetadata(List<LibraryList> allLibraryLists, Set<? extends ListSorting> supportedListSorting) {
            Intrinsics.checkNotNullParameter(allLibraryLists, "allLibraryLists");
            Intrinsics.checkNotNullParameter(supportedListSorting, "supportedListSorting");
            this.allLibraryLists = allLibraryLists;
            this.supportedListSorting = supportedListSorting;
        }

        public final List<LibraryList> getAllLibraryLists() {
            return this.allLibraryLists;
        }

        public final Set<ListSorting> getSupportedListSorting() {
            return this.supportedListSorting;
        }
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryList;", "", "name", "Lcom/lagradost/cloudstream3/utils/UiText;", "items", "", "Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "<init>", "(Lcom/lagradost/cloudstream3/utils/UiText;Ljava/util/List;)V", "getName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getItems", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LibraryList {
        private final List<LibraryItem> items;
        private final UiText name;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LibraryList copy$default(LibraryList libraryList, UiText uiText, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                uiText = libraryList.name;
            }
            if ((i & 2) != 0) {
                list = libraryList.items;
            }
            return libraryList.copy(uiText, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UiText getName() {
            return this.name;
        }

        public final List<LibraryItem> component2() {
            return this.items;
        }

        public final LibraryList copy(UiText name, List<LibraryItem> items) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(items, "items");
            return new LibraryList(name, items);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LibraryList)) {
                return false;
            }
            LibraryList libraryList = (LibraryList) other;
            return Intrinsics.areEqual(this.name, libraryList.name) && Intrinsics.areEqual(this.items, libraryList.items);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.items.hashCode();
        }

        public String toString() {
            return "LibraryList(name=" + this.name + ", items=" + this.items + ')';
        }

        public LibraryList(UiText name, List<LibraryItem> items) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(items, "items");
            this.name = name;
            this.items = items;
        }

        public final UiText getName() {
            return this.name;
        }

        public final List<LibraryItem> getItems() {
            return this.items;
        }
    }

    /* JADX INFO: compiled from: SyncAPI.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b>\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001BÃ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001b¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010F\u001a\u00020\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010#J\u0010\u0010J\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010K\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u0010L\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010)J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010P\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010V\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001bHÆ\u0003Jä\u0001\u0010W\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001bHÆ\u0001¢\u0006\u0002\u0010XJ\u0014\u0010Y\u001a\u00020Z2\b\u0010[\u001a\u0004\u0018\u00010\\HÖ\u0083\u0004J\n\u0010]\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010^\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010$\u001a\u0004\b%\u0010#R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0014\u0010\r\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001f\"\u0004\b1\u00102R(\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b=\u0010#\"\u0004\b>\u0010?R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u001fR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010'\"\u0004\bB\u0010CR\u0019\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001b¢\u0006\b\n\u0000\u001a\u0004\bD\u0010E¨\u0006_"}, d2 = {"Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", ImagesContract.URL, "syncId", "episodesCompleted", "", "episodesTotal", "personalRating", "Lcom/lagradost/cloudstream3/Score;", "lastUpdatedUnixTime", "", "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "posterHeaders", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "releaseDate", "Ljava/util/Date;", "id", "plot", "score", "tags", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Long;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Date;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getUrl", "getSyncId", "getEpisodesCompleted", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisodesTotal", "getPersonalRating", "()Lcom/lagradost/cloudstream3/Score;", "getLastUpdatedUnixTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getPosterHeaders", "()Ljava/util/Map;", "setPosterHeaders", "(Ljava/util/Map;)V", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getReleaseDate", "()Ljava/util/Date;", "getId", "setId", "(Ljava/lang/Integer;)V", "getPlot", "getScore", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Long;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/util/Map;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Date;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;)Lcom/lagradost/cloudstream3/syncproviders/SyncAPI$LibraryItem;", "equals", "", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LibraryItem implements SearchResponse {
        private final String apiName;
        private final Integer episodesCompleted;
        private final Integer episodesTotal;
        private Integer id;
        private final Long lastUpdatedUnixTime;
        private final String name;
        private final Score personalRating;
        private final String plot;
        private Map<String, String> posterHeaders;
        private String posterUrl;
        private SearchQuality quality;
        private final Date releaseDate;
        private Score score;
        private final String syncId;
        private final List<String> tags;
        private TvType type;
        private final String url;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LibraryItem copy$default(LibraryItem libraryItem, String str, String str2, String str3, Integer num, Integer num2, Score score, Long l, String str4, TvType tvType, String str5, Map map, SearchQuality searchQuality, Date date, Integer num3, String str6, Score score2, List list, int i, Object obj) {
            List list2;
            Score score3;
            String str7 = (i & 1) != 0 ? libraryItem.name : str;
            String str8 = (i & 2) != 0 ? libraryItem.url : str2;
            String str9 = (i & 4) != 0 ? libraryItem.syncId : str3;
            Integer num4 = (i & 8) != 0 ? libraryItem.episodesCompleted : num;
            Integer num5 = (i & 16) != 0 ? libraryItem.episodesTotal : num2;
            Score score4 = (i & 32) != 0 ? libraryItem.personalRating : score;
            Long l2 = (i & 64) != 0 ? libraryItem.lastUpdatedUnixTime : l;
            String str10 = (i & 128) != 0 ? libraryItem.apiName : str4;
            TvType tvType2 = (i & 256) != 0 ? libraryItem.type : tvType;
            String str11 = (i & 512) != 0 ? libraryItem.posterUrl : str5;
            Map map2 = (i & 1024) != 0 ? libraryItem.posterHeaders : map;
            SearchQuality searchQuality2 = (i & 2048) != 0 ? libraryItem.quality : searchQuality;
            Date date2 = (i & 4096) != 0 ? libraryItem.releaseDate : date;
            Integer num6 = (i & 8192) != 0 ? libraryItem.id : num3;
            String str12 = str7;
            String str13 = (i & 16384) != 0 ? libraryItem.plot : str6;
            Score score5 = (i & 32768) != 0 ? libraryItem.score : score2;
            if ((i & 65536) != 0) {
                score3 = score5;
                list2 = libraryItem.tags;
            } else {
                list2 = list;
                score3 = score5;
            }
            return libraryItem.copy(str12, str8, str9, num4, num5, score4, l2, str10, tvType2, str11, map2, searchQuality2, date2, num6, str13, score3, list2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getPosterUrl() {
            return this.posterUrl;
        }

        public final Map<String, String> component11() {
            return this.posterHeaders;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final SearchQuality getQuality() {
            return this.quality;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Date getReleaseDate() {
            return this.releaseDate;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getPlot() {
            return this.plot;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Score getScore() {
            return this.score;
        }

        public final List<String> component17() {
            return this.tags;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSyncId() {
            return this.syncId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getEpisodesCompleted() {
            return this.episodesCompleted;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getEpisodesTotal() {
            return this.episodesTotal;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Score getPersonalRating() {
            return this.personalRating;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Long getLastUpdatedUnixTime() {
            return this.lastUpdatedUnixTime;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getApiName() {
            return this.apiName;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        public final LibraryItem copy(String name, String url, String syncId, Integer episodesCompleted, Integer episodesTotal, Score personalRating, Long lastUpdatedUnixTime, String apiName, TvType type, String posterUrl, Map<String, String> posterHeaders, SearchQuality quality, Date releaseDate, Integer id, String plot, Score score, List<String> tags) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(syncId, "syncId");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            return new LibraryItem(name, url, syncId, episodesCompleted, episodesTotal, personalRating, lastUpdatedUnixTime, apiName, type, posterUrl, posterHeaders, quality, releaseDate, id, plot, score, tags);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LibraryItem)) {
                return false;
            }
            LibraryItem libraryItem = (LibraryItem) other;
            return Intrinsics.areEqual(this.name, libraryItem.name) && Intrinsics.areEqual(this.url, libraryItem.url) && Intrinsics.areEqual(this.syncId, libraryItem.syncId) && Intrinsics.areEqual(this.episodesCompleted, libraryItem.episodesCompleted) && Intrinsics.areEqual(this.episodesTotal, libraryItem.episodesTotal) && Intrinsics.areEqual(this.personalRating, libraryItem.personalRating) && Intrinsics.areEqual(this.lastUpdatedUnixTime, libraryItem.lastUpdatedUnixTime) && Intrinsics.areEqual(this.apiName, libraryItem.apiName) && this.type == libraryItem.type && Intrinsics.areEqual(this.posterUrl, libraryItem.posterUrl) && Intrinsics.areEqual(this.posterHeaders, libraryItem.posterHeaders) && this.quality == libraryItem.quality && Intrinsics.areEqual(this.releaseDate, libraryItem.releaseDate) && Intrinsics.areEqual(this.id, libraryItem.id) && Intrinsics.areEqual(this.plot, libraryItem.plot) && Intrinsics.areEqual(this.score, libraryItem.score) && Intrinsics.areEqual(this.tags, libraryItem.tags);
        }

        public int hashCode() {
            int iHashCode = ((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.syncId.hashCode()) * 31;
            Integer num = this.episodesCompleted;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.episodesTotal;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Score score = this.personalRating;
            int data = (iHashCode3 + (score == null ? 0 : score.getData())) * 31;
            Long l = this.lastUpdatedUnixTime;
            int iHashCode4 = (((data + (l == null ? 0 : l.hashCode())) * 31) + this.apiName.hashCode()) * 31;
            TvType tvType = this.type;
            int iHashCode5 = (iHashCode4 + (tvType == null ? 0 : tvType.hashCode())) * 31;
            String str = this.posterUrl;
            int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
            Map<String, String> map = this.posterHeaders;
            int iHashCode7 = (iHashCode6 + (map == null ? 0 : map.hashCode())) * 31;
            SearchQuality searchQuality = this.quality;
            int iHashCode8 = (iHashCode7 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
            Date date = this.releaseDate;
            int iHashCode9 = (iHashCode8 + (date == null ? 0 : date.hashCode())) * 31;
            Integer num3 = this.id;
            int iHashCode10 = (iHashCode9 + (num3 == null ? 0 : num3.hashCode())) * 31;
            String str2 = this.plot;
            int iHashCode11 = (iHashCode10 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Score score2 = this.score;
            int data2 = (iHashCode11 + (score2 == null ? 0 : score2.getData())) * 31;
            List<String> list = this.tags;
            return data2 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "LibraryItem(name=" + this.name + ", url=" + this.url + ", syncId=" + this.syncId + ", episodesCompleted=" + this.episodesCompleted + ", episodesTotal=" + this.episodesTotal + ", personalRating=" + this.personalRating + ", lastUpdatedUnixTime=" + this.lastUpdatedUnixTime + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", posterHeaders=" + this.posterHeaders + ", quality=" + this.quality + ", releaseDate=" + this.releaseDate + ", id=" + this.id + ", plot=" + this.plot + ", score=" + this.score + ", tags=" + this.tags + ')';
        }

        public LibraryItem(String name, String url, String syncId, Integer num, Integer num2, Score score, Long l, String apiName, TvType tvType, String str, Map<String, String> map, SearchQuality searchQuality, Date date, Integer num3, String str2, Score score2, List<String> list) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(syncId, "syncId");
            Intrinsics.checkNotNullParameter(apiName, "apiName");
            this.name = name;
            this.url = url;
            this.syncId = syncId;
            this.episodesCompleted = num;
            this.episodesTotal = num2;
            this.personalRating = score;
            this.lastUpdatedUnixTime = l;
            this.apiName = apiName;
            this.type = tvType;
            this.posterUrl = str;
            this.posterHeaders = map;
            this.quality = searchQuality;
            this.releaseDate = date;
            this.id = num3;
            this.plot = str2;
            this.score = score2;
            this.tags = list;
        }

        public /* synthetic */ LibraryItem(String str, String str2, String str3, Integer num, Integer num2, Score score, Long l, String str4, TvType tvType, String str5, Map map, SearchQuality searchQuality, Date date, Integer num3, String str6, Score score2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, num, num2, score, l, str4, tvType, str5, map, searchQuality, date, (i & 8192) != 0 ? null : num3, (i & 16384) != 0 ? null : str6, (32768 & i) != 0 ? null : score2, (i & 65536) != 0 ? null : list);
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getName() {
            return this.name;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getUrl() {
            return this.url;
        }

        public final String getSyncId() {
            return this.syncId;
        }

        public final Integer getEpisodesCompleted() {
            return this.episodesCompleted;
        }

        public final Integer getEpisodesTotal() {
            return this.episodesTotal;
        }

        public final Score getPersonalRating() {
            return this.personalRating;
        }

        public final Long getLastUpdatedUnixTime() {
            return this.lastUpdatedUnixTime;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getApiName() {
            return this.apiName;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public TvType getType() {
            return this.type;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setType(TvType tvType) {
            this.type = tvType;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public String getPosterUrl() {
            return this.posterUrl;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterUrl(String str) {
            this.posterUrl = str;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Map<String, String> getPosterHeaders() {
            return this.posterHeaders;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setPosterHeaders(Map<String, String> map) {
            this.posterHeaders = map;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public SearchQuality getQuality() {
            return this.quality;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setQuality(SearchQuality searchQuality) {
            this.quality = searchQuality;
        }

        public final Date getReleaseDate() {
            return this.releaseDate;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Integer getId() {
            return this.id;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setId(Integer num) {
            this.id = num;
        }

        public final String getPlot() {
            return this.plot;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public Score getScore() {
            return this.score;
        }

        @Override // com.lagradost.cloudstream3.SearchResponse
        public void setScore(Score score) {
            this.score = score;
        }

        public final List<String> getTags() {
            return this.tags;
        }
    }
}
