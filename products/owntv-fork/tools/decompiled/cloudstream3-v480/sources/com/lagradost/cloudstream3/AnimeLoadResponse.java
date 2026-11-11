package com.lagradost.cloudstream3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\br\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0081\u0003\b\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u001a\b\u0002\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000f\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r\u0012\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0011\u0012\b\b\u0002\u0010\"\u001a\u00020#\u0012\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010&\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u0011\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010.\u001a\u00020\u0004¢\u0006\u0004\b/\u00100J\u0016\u0010z\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r0&H\u0016J\u0018\u0010{\u001a\u00020\r2\u0006\u0010|\u001a\u00020\r2\u0006\u0010}\u001a\u00020\rH\u0016J\u000b\u0010~\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u007f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0080\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0082\u0001\u001a\u00020\u0004HÆ\u0003J\n\u0010\u0083\u0001\u001a\u00020\nHÆ\u0003J\f\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010DJ\u001c\u0010\u0086\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000fHÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0012\u0010\u0089\u0001\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011HÆ\u0003J\u0012\u0010\u008a\u0001\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011HÆ\u0003J\f\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0019HÆ\u0003J\u0011\u0010\u008c\u0001\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010DJ\u0010\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cHÆ\u0003J\u0012\u0010\u008e\u0001\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0011HÆ\u0003J\u0012\u0010\u008f\u0001\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0011HÆ\u0003J\n\u0010\u0090\u0001\u001a\u00020#HÆ\u0003J\u0016\u0010\u0091\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000fHÆ\u0003J\u0018\u0010\u0092\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010&HÆ\u0003J\f\u0010\u0093\u0001\u001a\u0004\u0018\u00010(HÆ\u0003J\u0012\u0010\u0094\u0001\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u0011HÆ\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u0098\u0001\u001a\u00020\u0004HÆ\u0003J\u0090\u0003\u0010\u0099\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u001a\b\u0002\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00112\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\r2\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u00112\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u00112\b\b\u0002\u0010\"\u001a\u00020#2\u0014\b\u0002\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000f2\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010&2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u00112\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010.\u001a\u00020\u0004HÆ\u0001¢\u0006\u0003\u0010\u009a\u0001J\u0017\u0010\u009b\u0001\u001a\u00020#2\n\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009d\u0001HÖ\u0083\u0004J\u000b\u0010\u009e\u0001\u001a\u00020\rHÖ\u0081\u0004J\u000b\u0010\u009f\u0001\u001a\u00020\u0004HÖ\u0081\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00102\"\u0004\b6\u00104R\u001a\u0010\u0006\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00102\"\u0004\b8\u00104R\u001a\u0010\u0007\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u00102\"\u0004\b:\u00104R\u001a\u0010\b\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u00102\"\u0004\b<\u00104R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u00102\"\u0004\bB\u00104R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR,\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u00102\"\u0004\bQ\u00104R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010S\"\u0004\bW\u0010UR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u0010\n\u0002\u0010G\u001a\u0004\b\\\u0010D\"\u0004\b]\u0010FR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010S\"\u0004\b_\u0010UR\"\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010S\"\u0004\ba\u0010UR\"\u0010 \u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010S\"\u0004\bc\u0010UR\u001a\u0010\"\u001a\u00020#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR&\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010I\"\u0004\bi\u0010KR(\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010&X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010I\"\u0004\bk\u0010KR\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\"\u0010)\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010S\"\u0004\bq\u0010UR\u001c\u0010+\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u00102\"\u0004\bs\u00104R\u001c\u0010,\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u00102\"\u0004\bu\u00104R\u001c\u0010-\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u00102\"\u0004\bw\u00104R\u001a\u0010.\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u00102\"\u0004\by\u00104¨\u0006 \u0001"}, d2 = {"Lcom/lagradost/cloudstream3/AnimeLoadResponse;", "Lcom/lagradost/cloudstream3/LoadResponse;", "Lcom/lagradost/cloudstream3/EpisodeResponse;", "engName", "", "japName", "name", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "", "episodes", "", "Lcom/lagradost/cloudstream3/DubStatus;", "", "Lcom/lagradost/cloudstream3/Episode;", "showStatus", "Lcom/lagradost/cloudstream3/ShowStatus;", "plot", "tags", "synonyms", "score", "Lcom/lagradost/cloudstream3/Score;", TypedValues.TransitionType.S_DURATION, "trailers", "", "Lcom/lagradost/cloudstream3/TrailerData;", "recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "comingSoon", "", "syncData", "posterHeaders", "", "nextAiring", "Lcom/lagradost/cloudstream3/NextAiring;", "seasonNames", "Lcom/lagradost/cloudstream3/SeasonData;", "backgroundPosterUrl", "logoUrl", "contentRating", "uniqueUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/ShowStatus;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Lcom/lagradost/cloudstream3/NextAiring;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEngName", "()Ljava/lang/String;", "setEngName", "(Ljava/lang/String;)V", "getJapName", "setJapName", "getName", "setName", "getUrl", "setUrl", "getApiName", "setApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getEpisodes", "()Ljava/util/Map;", "setEpisodes", "(Ljava/util/Map;)V", "getShowStatus", "()Lcom/lagradost/cloudstream3/ShowStatus;", "setShowStatus", "(Lcom/lagradost/cloudstream3/ShowStatus;)V", "getPlot", "setPlot", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "getSynonyms", "setSynonyms", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getDuration", "setDuration", "getTrailers", "setTrailers", "getRecommendations", "setRecommendations", "getActors", "setActors", "getComingSoon", "()Z", "setComingSoon", "(Z)V", "getSyncData", "setSyncData", "getPosterHeaders", "setPosterHeaders", "getNextAiring", "()Lcom/lagradost/cloudstream3/NextAiring;", "setNextAiring", "(Lcom/lagradost/cloudstream3/NextAiring;)V", "getSeasonNames", "setSeasonNames", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "getLogoUrl", "setLogoUrl", "getContentRating", "setContentRating", "getUniqueUrl", "setUniqueUrl", "getLatestEpisodes", "getTotalEpisodeIndex", "episode", "season", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Lcom/lagradost/cloudstream3/ShowStatus;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/lagradost/cloudstream3/Score;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Lcom/lagradost/cloudstream3/NextAiring;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/AnimeLoadResponse;", "equals", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AnimeLoadResponse implements LoadResponse, EpisodeResponse {
    private List<ActorData> actors;
    private String apiName;
    private String backgroundPosterUrl;
    private boolean comingSoon;
    private String contentRating;
    private Integer duration;
    private String engName;
    private Map<DubStatus, List<Episode>> episodes;
    private String japName;
    private String logoUrl;
    private String name;
    private NextAiring nextAiring;
    private String plot;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private List<? extends SearchResponse> recommendations;
    private Score score;
    private List<SeasonData> seasonNames;
    private ShowStatus showStatus;
    private Map<String, String> syncData;
    private List<String> synonyms;
    private List<String> tags;
    private List<TrailerData> trailers;
    private TvType type;
    private String uniqueUrl;
    private String url;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnimeLoadResponse copy$default(AnimeLoadResponse animeLoadResponse, String str, String str2, String str3, String str4, String str5, TvType tvType, String str6, Integer num, Map map, ShowStatus showStatus, String str7, List list, List list2, Score score, Integer num2, List list3, List list4, List list5, boolean z, Map map2, Map map3, NextAiring nextAiring, List list6, String str8, String str9, String str10, String str11, int i, Object obj) {
        String str12;
        String str13;
        String str14 = (i & 1) != 0 ? animeLoadResponse.engName : str;
        String str15 = (i & 2) != 0 ? animeLoadResponse.japName : str2;
        String str16 = (i & 4) != 0 ? animeLoadResponse.name : str3;
        String str17 = (i & 8) != 0 ? animeLoadResponse.url : str4;
        String str18 = (i & 16) != 0 ? animeLoadResponse.apiName : str5;
        TvType tvType2 = (i & 32) != 0 ? animeLoadResponse.type : tvType;
        String str19 = (i & 64) != 0 ? animeLoadResponse.posterUrl : str6;
        Integer num3 = (i & 128) != 0 ? animeLoadResponse.year : num;
        Map map4 = (i & 256) != 0 ? animeLoadResponse.episodes : map;
        ShowStatus showStatus2 = (i & 512) != 0 ? animeLoadResponse.showStatus : showStatus;
        String str20 = (i & 1024) != 0 ? animeLoadResponse.plot : str7;
        List list7 = (i & 2048) != 0 ? animeLoadResponse.tags : list;
        List list8 = (i & 4096) != 0 ? animeLoadResponse.synonyms : list2;
        Score score2 = (i & 8192) != 0 ? animeLoadResponse.score : score;
        String str21 = str14;
        Integer num4 = (i & 16384) != 0 ? animeLoadResponse.duration : num2;
        List list9 = (i & 32768) != 0 ? animeLoadResponse.trailers : list3;
        List list10 = (i & 65536) != 0 ? animeLoadResponse.recommendations : list4;
        List list11 = (i & 131072) != 0 ? animeLoadResponse.actors : list5;
        boolean z2 = (i & 262144) != 0 ? animeLoadResponse.comingSoon : z;
        Map map5 = (i & 524288) != 0 ? animeLoadResponse.syncData : map2;
        Map map6 = (i & 1048576) != 0 ? animeLoadResponse.posterHeaders : map3;
        NextAiring nextAiring2 = (i & 2097152) != 0 ? animeLoadResponse.nextAiring : nextAiring;
        List list12 = (i & 4194304) != 0 ? animeLoadResponse.seasonNames : list6;
        String str22 = (i & 8388608) != 0 ? animeLoadResponse.backgroundPosterUrl : str8;
        String str23 = (i & 16777216) != 0 ? animeLoadResponse.logoUrl : str9;
        String str24 = (i & 33554432) != 0 ? animeLoadResponse.contentRating : str10;
        if ((i & 67108864) != 0) {
            str13 = str24;
            str12 = animeLoadResponse.uniqueUrl;
        } else {
            str12 = str11;
            str13 = str24;
        }
        return animeLoadResponse.copy(str21, str15, str16, str17, str18, tvType2, str19, num3, map4, showStatus2, str20, list7, list8, score2, num4, list9, list10, list11, z2, map5, map6, nextAiring2, list12, str22, str23, str13, str12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEngName() {
        return this.engName;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final ShowStatus getShowStatus() {
        return this.showStatus;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getPlot() {
        return this.plot;
    }

    public final List<String> component12() {
        return this.tags;
    }

    public final List<String> component13() {
        return this.synonyms;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Score getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final Integer getDuration() {
        return this.duration;
    }

    public final List<TrailerData> component16() {
        return this.trailers;
    }

    public final List<SearchResponse> component17() {
        return this.recommendations;
    }

    public final List<ActorData> component18() {
        return this.actors;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getComingSoon() {
        return this.comingSoon;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getJapName() {
        return this.japName;
    }

    public final Map<String, String> component20() {
        return this.syncData;
    }

    public final Map<String, String> component21() {
        return this.posterHeaders;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final NextAiring getNextAiring() {
        return this.nextAiring;
    }

    public final List<SeasonData> component23() {
        return this.seasonNames;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final String getContentRating() {
        return this.contentRating;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getUniqueUrl() {
        return this.uniqueUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TvType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    public final Map<DubStatus, List<Episode>> component9() {
        return this.episodes;
    }

    public final AnimeLoadResponse copy(String engName, String japName, String name, String url, String apiName, TvType type, String posterUrl, Integer year, Map<DubStatus, List<Episode>> episodes, ShowStatus showStatus, String plot, List<String> tags, List<String> synonyms, Score score, Integer duration, List<TrailerData> trailers, List<? extends SearchResponse> recommendations, List<ActorData> actors, boolean comingSoon, Map<String, String> syncData, Map<String, String> posterHeaders, NextAiring nextAiring, List<SeasonData> seasonNames, String backgroundPosterUrl, String logoUrl, String contentRating, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        return new AnimeLoadResponse(engName, japName, name, url, apiName, type, posterUrl, year, episodes, showStatus, plot, tags, synonyms, score, duration, trailers, recommendations, actors, comingSoon, syncData, posterHeaders, nextAiring, seasonNames, backgroundPosterUrl, logoUrl, contentRating, uniqueUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimeLoadResponse)) {
            return false;
        }
        AnimeLoadResponse animeLoadResponse = (AnimeLoadResponse) other;
        return Intrinsics.areEqual(this.engName, animeLoadResponse.engName) && Intrinsics.areEqual(this.japName, animeLoadResponse.japName) && Intrinsics.areEqual(this.name, animeLoadResponse.name) && Intrinsics.areEqual(this.url, animeLoadResponse.url) && Intrinsics.areEqual(this.apiName, animeLoadResponse.apiName) && this.type == animeLoadResponse.type && Intrinsics.areEqual(this.posterUrl, animeLoadResponse.posterUrl) && Intrinsics.areEqual(this.year, animeLoadResponse.year) && Intrinsics.areEqual(this.episodes, animeLoadResponse.episodes) && this.showStatus == animeLoadResponse.showStatus && Intrinsics.areEqual(this.plot, animeLoadResponse.plot) && Intrinsics.areEqual(this.tags, animeLoadResponse.tags) && Intrinsics.areEqual(this.synonyms, animeLoadResponse.synonyms) && Intrinsics.areEqual(this.score, animeLoadResponse.score) && Intrinsics.areEqual(this.duration, animeLoadResponse.duration) && Intrinsics.areEqual(this.trailers, animeLoadResponse.trailers) && Intrinsics.areEqual(this.recommendations, animeLoadResponse.recommendations) && Intrinsics.areEqual(this.actors, animeLoadResponse.actors) && this.comingSoon == animeLoadResponse.comingSoon && Intrinsics.areEqual(this.syncData, animeLoadResponse.syncData) && Intrinsics.areEqual(this.posterHeaders, animeLoadResponse.posterHeaders) && Intrinsics.areEqual(this.nextAiring, animeLoadResponse.nextAiring) && Intrinsics.areEqual(this.seasonNames, animeLoadResponse.seasonNames) && Intrinsics.areEqual(this.backgroundPosterUrl, animeLoadResponse.backgroundPosterUrl) && Intrinsics.areEqual(this.logoUrl, animeLoadResponse.logoUrl) && Intrinsics.areEqual(this.contentRating, animeLoadResponse.contentRating) && Intrinsics.areEqual(this.uniqueUrl, animeLoadResponse.uniqueUrl);
    }

    public int hashCode() {
        String str = this.engName;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.japName;
        int iHashCode2 = (((((((((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.name.hashCode()) * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31) + this.type.hashCode()) * 31;
        String str3 = this.posterUrl;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.year;
        int iHashCode4 = (((iHashCode3 + (num == null ? 0 : num.hashCode())) * 31) + this.episodes.hashCode()) * 31;
        ShowStatus showStatus = this.showStatus;
        int iHashCode5 = (iHashCode4 + (showStatus == null ? 0 : showStatus.hashCode())) * 31;
        String str4 = this.plot;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<String> list = this.tags;
        int iHashCode7 = (iHashCode6 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.synonyms;
        int iHashCode8 = (iHashCode7 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Score score = this.score;
        int data = (iHashCode8 + (score == null ? 0 : score.getData())) * 31;
        Integer num2 = this.duration;
        int iHashCode9 = (((data + (num2 == null ? 0 : num2.hashCode())) * 31) + this.trailers.hashCode()) * 31;
        List<? extends SearchResponse> list3 = this.recommendations;
        int iHashCode10 = (iHashCode9 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<ActorData> list4 = this.actors;
        int iHashCode11 = (((((iHashCode10 + (list4 == null ? 0 : list4.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.comingSoon)) * 31) + this.syncData.hashCode()) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode12 = (iHashCode11 + (map == null ? 0 : map.hashCode())) * 31;
        NextAiring nextAiring = this.nextAiring;
        int iHashCode13 = (iHashCode12 + (nextAiring == null ? 0 : nextAiring.hashCode())) * 31;
        List<SeasonData> list5 = this.seasonNames;
        int iHashCode14 = (iHashCode13 + (list5 == null ? 0 : list5.hashCode())) * 31;
        String str5 = this.backgroundPosterUrl;
        int iHashCode15 = (iHashCode14 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.logoUrl;
        int iHashCode16 = (iHashCode15 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.contentRating;
        return ((iHashCode16 + (str7 != null ? str7.hashCode() : 0)) * 31) + this.uniqueUrl.hashCode();
    }

    public String toString() {
        return "AnimeLoadResponse(engName=" + this.engName + ", japName=" + this.japName + ", name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", episodes=" + this.episodes + ", showStatus=" + this.showStatus + ", plot=" + this.plot + ", tags=" + this.tags + ", synonyms=" + this.synonyms + ", score=" + this.score + ", duration=" + this.duration + ", trailers=" + this.trailers + ", recommendations=" + this.recommendations + ", actors=" + this.actors + ", comingSoon=" + this.comingSoon + ", syncData=" + this.syncData + ", posterHeaders=" + this.posterHeaders + ", nextAiring=" + this.nextAiring + ", seasonNames=" + this.seasonNames + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", logoUrl=" + this.logoUrl + ", contentRating=" + this.contentRating + ", uniqueUrl=" + this.uniqueUrl + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newAnimeLoadResponse method")
    public AnimeLoadResponse(String str, String str2, String name, String url, String apiName, TvType type, String str3, Integer num, Map<DubStatus, List<Episode>> episodes, ShowStatus showStatus, String str4, List<String> list, List<String> list2, Score score, Integer num2, List<TrailerData> trailers, List<? extends SearchResponse> list3, List<ActorData> list4, boolean z, Map<String, String> syncData, Map<String, String> map, NextAiring nextAiring, List<SeasonData> list5, String str5, String str6, String str7, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        this.engName = str;
        this.japName = str2;
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.type = type;
        this.posterUrl = str3;
        this.year = num;
        this.episodes = episodes;
        this.showStatus = showStatus;
        this.plot = str4;
        this.tags = list;
        this.synonyms = list2;
        this.score = score;
        this.duration = num2;
        this.trailers = trailers;
        this.recommendations = list3;
        this.actors = list4;
        this.comingSoon = z;
        this.syncData = syncData;
        this.posterHeaders = map;
        this.nextAiring = nextAiring;
        this.seasonNames = list5;
        this.backgroundPosterUrl = str5;
        this.logoUrl = str6;
        this.contentRating = str7;
        this.uniqueUrl = uniqueUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public /* bridge */ Integer getRating() {
        return LoadResponse.CC.$default$getRating(this);
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public /* bridge */ void setRating(Integer num) {
        LoadResponse.CC.$default$setRating(this, num);
    }

    public final String getEngName() {
        return this.engName;
    }

    public final void setEngName(String str) {
        this.engName = str;
    }

    public final String getJapName() {
        return this.japName;
    }

    public final void setJapName(String str) {
        this.japName = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getUrl() {
        return this.url;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getApiName() {
        return this.apiName;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setApiName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiName = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public TvType getType() {
        return this.type;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setType(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<set-?>");
        this.type = tvType;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getPosterUrl() {
        return this.posterUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPosterUrl(String str) {
        this.posterUrl = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Integer getYear() {
        return this.year;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setYear(Integer num) {
        this.year = num;
    }

    public /* synthetic */ AnimeLoadResponse(String str, String str2, String str3, String str4, String str5, TvType tvType, String str6, Integer num, Map map, ShowStatus showStatus, String str7, List list, List list2, Score score, Integer num2, List list3, List list4, List list5, boolean z, Map map2, Map map3, NextAiring nextAiring, List list6, String str8, String str9, String str10, String str11, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, str3, str4, str5, tvType, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : num, (i & 256) != 0 ? new LinkedHashMap() : map, (i & 512) != 0 ? null : showStatus, (i & 1024) != 0 ? null : str7, (i & 2048) != 0 ? null : list, (i & 4096) != 0 ? null : list2, (i & 8192) != 0 ? null : score, (i & 16384) != 0 ? null : num2, (32768 & i) != 0 ? new ArrayList() : list3, (65536 & i) != 0 ? null : list4, (131072 & i) != 0 ? null : list5, (262144 & i) != 0 ? false : z, (524288 & i) != 0 ? new LinkedHashMap() : map2, (1048576 & i) != 0 ? null : map3, (2097152 & i) != 0 ? null : nextAiring, (4194304 & i) != 0 ? null : list6, (8388608 & i) != 0 ? null : str8, (16777216 & i) != 0 ? null : str9, (33554432 & i) != 0 ? null : str10, (i & 67108864) != 0 ? str4 : str11);
    }

    public final Map<DubStatus, List<Episode>> getEpisodes() {
        return this.episodes;
    }

    public final void setEpisodes(Map<DubStatus, List<Episode>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.episodes = map;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public ShowStatus getShowStatus() {
        return this.showStatus;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public void setShowStatus(ShowStatus showStatus) {
        this.showStatus = showStatus;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getPlot() {
        return this.plot;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPlot(String str) {
        this.plot = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<String> getTags() {
        return this.tags;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setTags(List<String> list) {
        this.tags = list;
    }

    public final List<String> getSynonyms() {
        return this.synonyms;
    }

    public final void setSynonyms(List<String> list) {
        this.synonyms = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Score getScore() {
        return this.score;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setScore(Score score) {
        this.score = score;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Integer getDuration() {
        return this.duration;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setDuration(Integer num) {
        this.duration = num;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<TrailerData> getTrailers() {
        return this.trailers;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setTrailers(List<TrailerData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.trailers = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<SearchResponse> getRecommendations() {
        return this.recommendations;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setRecommendations(List<? extends SearchResponse> list) {
        this.recommendations = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<ActorData> getActors() {
        return this.actors;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setActors(List<ActorData> list) {
        this.actors = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public boolean getComingSoon() {
        return this.comingSoon;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setComingSoon(boolean z) {
        this.comingSoon = z;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Map<String, String> getSyncData() {
        return this.syncData;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setSyncData(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncData = map;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Map<String, String> getPosterHeaders() {
        return this.posterHeaders;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPosterHeaders(Map<String, String> map) {
        this.posterHeaders = map;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public NextAiring getNextAiring() {
        return this.nextAiring;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public void setNextAiring(NextAiring nextAiring) {
        this.nextAiring = nextAiring;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public List<SeasonData> getSeasonNames() {
        return this.seasonNames;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public void setSeasonNames(List<SeasonData> list) {
        this.seasonNames = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setBackgroundPosterUrl(String str) {
        this.backgroundPosterUrl = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getLogoUrl() {
        return this.logoUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getContentRating() {
        return this.contentRating;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setContentRating(String str) {
        this.contentRating = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getUniqueUrl() {
        return this.uniqueUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setUniqueUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uniqueUrl = str;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public Map<DubStatus, Integer> getLatestEpisodes() {
        Integer numValueOf;
        Integer numValueOf2;
        Map<DubStatus, List<Episode>> map = this.episodes;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<DubStatus, List<Episode>> entry : map.entrySet()) {
            DubStatus key = entry.getKey();
            List<Episode> value = entry.getValue();
            Iterator<T> it = value.iterator();
            Integer num = null;
            if (it.hasNext()) {
                Integer season = ((Episode) it.next()).getSeason();
                numValueOf = Integer.valueOf(season != null ? season.intValue() : Integer.MIN_VALUE);
                while (it.hasNext()) {
                    Integer season2 = ((Episode) it.next()).getSeason();
                    Integer numValueOf3 = Integer.valueOf(season2 != null ? season2.intValue() : Integer.MIN_VALUE);
                    if (numValueOf.compareTo(numValueOf3) < 0) {
                        numValueOf = numValueOf3;
                    }
                }
            } else {
                numValueOf = null;
            }
            Integer num2 = numValueOf;
            if (num2 != null && num2.intValue() == Integer.MIN_VALUE) {
                numValueOf = null;
            }
            Integer num3 = numValueOf;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : value) {
                if (Intrinsics.areEqual(((Episode) obj).getSeason(), num3)) {
                    arrayList2.add(obj);
                }
            }
            Iterator it2 = arrayList2.iterator();
            if (it2.hasNext()) {
                Integer episode = ((Episode) it2.next()).getEpisode();
                numValueOf2 = Integer.valueOf(episode != null ? episode.intValue() : Integer.MIN_VALUE);
                while (it2.hasNext()) {
                    Integer episode2 = ((Episode) it2.next()).getEpisode();
                    Integer numValueOf4 = Integer.valueOf(episode2 != null ? episode2.intValue() : Integer.MIN_VALUE);
                    if (numValueOf2.compareTo(numValueOf4) < 0) {
                        numValueOf2 = numValueOf4;
                    }
                }
            } else {
                numValueOf2 = null;
            }
            Integer num4 = numValueOf2;
            boolean z = num4 != null && num4.intValue() == Integer.MIN_VALUE;
            Boolean.valueOf(z).getClass();
            if (!z) {
                num = numValueOf2;
            }
            arrayList.add(TuplesKt.to(key, num));
        }
        return MapsKt.toMap(arrayList);
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public int getTotalEpisodeIndex(int episode, int season) {
        LinkedHashMap linkedHashMapEmptyMap;
        int i;
        int iIntValue;
        int i2;
        int iIntValue2;
        List<SeasonData> seasonNames = getSeasonNames();
        if (seasonNames != null) {
            List<SeasonData> list = seasonNames;
            linkedHashMapEmptyMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
            for (SeasonData seasonData : list) {
                Pair pair = TuplesKt.to(Integer.valueOf(seasonData.getSeason()), seasonData.getDisplaySeason());
                linkedHashMapEmptyMap.put(pair.getFirst(), pair.getSecond());
            }
        } else {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        }
        Iterator<T> it = this.episodes.entrySet().iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        List<Episode> list2 = (List) ((Map.Entry) it.next()).getValue();
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            i = 0;
        } else {
            i = 0;
            for (Episode episode2 : list2) {
                Integer num = (Integer) linkedHashMapEmptyMap.get(episode2.getSeason());
                if (num != null) {
                    iIntValue = num.intValue();
                } else {
                    Integer season2 = episode2.getSeason();
                    iIntValue = season2 != null ? season2.intValue() : Integer.MIN_VALUE;
                }
                if (1 <= iIntValue && iIntValue < season && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        while (it.hasNext()) {
            List<Episode> list3 = (List) ((Map.Entry) it.next()).getValue();
            if ((list3 instanceof Collection) && list3.isEmpty()) {
                i2 = 0;
            } else {
                i2 = 0;
                for (Episode episode3 : list3) {
                    Integer num2 = (Integer) linkedHashMapEmptyMap.get(episode3.getSeason());
                    if (num2 != null) {
                        iIntValue2 = num2.intValue();
                    } else {
                        Integer season3 = episode3.getSeason();
                        iIntValue2 = season3 != null ? season3.intValue() : Integer.MIN_VALUE;
                    }
                    if (1 <= iIntValue2 && iIntValue2 < season && (i2 = i2 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (i < i2) {
                i = i2;
            }
        }
        return i + episode;
    }
}
