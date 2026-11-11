package com.lagradost.cloudstream3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModule;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0016\bf\u0018\u0000 ^2\u00020\u0001:\u0001^R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007R\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0005\"\u0004\b\u0016\u0010\u0007R\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u0018X¦\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u0005\"\u0004\b\u001f\u0010\u0007R\u001a\u0010 \u001a\u0004\u0018\u00010!X¦\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R \u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010'X¦\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u0004\u0018\u00010\u0018X¦\u000e¢\u0006\f\u001a\u0004\b-\u0010\u001a\"\u0004\b.\u0010\u001cR\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00020100X¦\u000e¢\u0006\f\u001a\u0004\b2\u0010)\"\u0004\b3\u0010+R \u00104\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010'X¦\u000e¢\u0006\f\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R \u00108\u001a\n\u0012\u0004\u0012\u000209\u0018\u00010'X¦\u000e¢\u0006\f\u001a\u0004\b:\u0010)\"\u0004\b;\u0010+R\u0018\u0010<\u001a\u00020=X¦\u000e¢\u0006\f\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030CX¦\u000e¢\u0006\f\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR&\u0010H\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010IX¦\u000e¢\u0006\f\u001a\u0004\bJ\u0010E\"\u0004\bK\u0010GR\u001a\u0010L\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\bM\u0010\u0005\"\u0004\bN\u0010\u0007R\u001a\u0010O\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\bP\u0010\u0005\"\u0004\bQ\u0010\u0007R\u001a\u0010R\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\bS\u0010\u0005\"\u0004\bT\u0010\u0007R\u0018\u0010U\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\bV\u0010\u0005\"\u0004\bW\u0010\u0007R.\u0010Y\u001a\u0004\u0018\u00010\u00182\b\u0010X\u001a\u0004\u0018\u00010\u00188V@VX\u0097\u000e¢\u0006\u0012\u0012\u0004\bZ\u0010[\u001a\u0004\b\\\u0010\u001a\"\u0004\b]\u0010\u001c¨\u0006_À\u0006\u0003"}, d2 = {"Lcom/lagradost/cloudstream3/LoadResponse;", "", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", ImagesContract.URL, "getUrl", "setUrl", "apiName", "getApiName", "setApiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "posterUrl", "getPosterUrl", "setPosterUrl", "year", "", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "plot", "getPlot", "setPlot", "score", "Lcom/lagradost/cloudstream3/Score;", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "tags", "", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", TypedValues.TransitionType.S_DURATION, "getDuration", "setDuration", "trailers", "", "Lcom/lagradost/cloudstream3/TrailerData;", "getTrailers", "setTrailers", "recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "getRecommendations", "setRecommendations", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "getActors", "setActors", "comingSoon", "", "getComingSoon", "()Z", "setComingSoon", "(Z)V", "syncData", "", "getSyncData", "()Ljava/util/Map;", "setSyncData", "(Ljava/util/Map;)V", "posterHeaders", "", "getPosterHeaders", "setPosterHeaders", "backgroundPosterUrl", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "logoUrl", "getLogoUrl", "setLogoUrl", "contentRating", "getContentRating", "setContentRating", "uniqueUrl", "getUniqueUrl", "setUniqueUrl", ES6Iterator.VALUE_PROPERTY, "rating", "getRating$annotations", "()V", "getRating", "setRating", "Companion", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface LoadResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    List<ActorData> getActors();

    String getApiName();

    String getBackgroundPosterUrl();

    boolean getComingSoon();

    String getContentRating();

    Integer getDuration();

    String getLogoUrl();

    String getName();

    String getPlot();

    Map<String, String> getPosterHeaders();

    String getPosterUrl();

    Integer getRating();

    List<SearchResponse> getRecommendations();

    Score getScore();

    Map<String, String> getSyncData();

    List<String> getTags();

    List<TrailerData> getTrailers();

    TvType getType();

    String getUniqueUrl();

    String getUrl();

    Integer getYear();

    void setActors(List<ActorData> list);

    void setApiName(String str);

    void setBackgroundPosterUrl(String str);

    void setComingSoon(boolean z);

    void setContentRating(String str);

    void setDuration(Integer num);

    void setLogoUrl(String str);

    void setName(String str);

    void setPlot(String str);

    void setPosterHeaders(Map<String, String> map);

    void setPosterUrl(String str);

    void setRating(Integer num);

    void setRecommendations(List<? extends SearchResponse> list);

    void setScore(Score score);

    void setSyncData(Map<String, String> map);

    void setTags(List<String> list);

    void setTrailers(List<TrailerData> list);

    void setType(TvType tvType);

    void setUniqueUrl(String str);

    void setUrl(String str);

    void setYear(Integer num);

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.LoadResponse$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MainAPI.kt */
    public final /* synthetic */ class CC {
        static {
            Companion companion = LoadResponse.INSTANCE;
        }

        public static void $default$setRating(LoadResponse _this, Integer num) {
            _this.setScore(Score.INSTANCE.fromOld(num));
        }

        public static Integer $default$getRating(LoadResponse _this) {
            Score score = _this.getScore();
            if (score != null) {
                return Integer.valueOf(score.toOld());
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.ERROR, message = "`rating` is the old scoring system, use score instead", replaceWith = @ReplaceWith(expression = "score", imports = {}))
        public static /* synthetic */ void getRating$annotations() {
        }

        @Deprecated
        public static void setRating(LoadResponse loadResponse, Integer num) {
            CC.$default$setRating(loadResponse, num);
        }

        @Deprecated
        public static Integer getRating(LoadResponse loadResponse) {
            return CC.$default$getRating(loadResponse);
        }
    }

    /* JADX INFO: compiled from: MainAPI.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0018\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u001c\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00050\u001e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005J\n\u0010\u001f\u001a\u00020\u0014*\u00020 J!\u0010!\u001a\u00020\"*\u00020 2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010$H\u0007¢\u0006\u0002\b%J*\u0010!\u001a\u00020\"*\u00020 2\u001c\u0010#\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020'\u0012\u0006\u0012\u0004\u0018\u00010\u00050&\u0018\u00010$H\u0007J/\u0010!\u001a\u00020\"*\u00020 2\u001c\u0010#\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020'\u0012\u0006\u0012\u0004\u0018\u00010(0&\u0018\u00010$H\u0007¢\u0006\u0002\b)J\u001e\u0010*\u001a\u00020\"*\u00020 2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005H\u0002J!\u0010!\u001a\u00020\"*\u00020 2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010$H\u0007¢\u0006\u0002\b+J\f\u0010,\u001a\u0004\u0018\u00010\u0005*\u00020 J\f\u0010-\u001a\u0004\u0018\u00010\u0005*\u00020 J\f\u0010.\u001a\u0004\u0018\u00010\u0005*\u00020 J\f\u0010/\u001a\u0004\u0018\u00010\u0005*\u00020 J\f\u00100\u001a\u0004\u0018\u00010\u0005*\u00020 J\u0019\u00101\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u000102¢\u0006\u0002\u00103J\u0019\u00104\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u000102¢\u0006\u0002\u00103J\u0019\u00105\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u000102¢\u0006\u0002\u00103J\u0019\u0010*\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u000102¢\u0006\u0002\u00103J\u0014\u00106\u001a\u00020\"*\u00020 2\b\u00107\u001a\u0004\u0018\u00010\u0005J2\u00108\u001a\u00020\"*\u00020 2\b\u00109\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010;\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010<JH\u00108\u001a\u00020\"*\u00020 2\b\u00109\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010;\u001a\u00020\u00142\u0014\b\u0002\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001eH\u0086@¢\u0006\u0002\u0010>J8\u00108\u001a\u00020\"*\u00020 2\u000e\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010$2\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010;\u001a\u00020\u0014H\u0086@¢\u0006\u0002\u0010@J\u0014\u0010A\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u0014\u0010B\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u0014\u00104\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u0014\u0010C\u001a\u00020\"*\u00020 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0005J\u001e\u0010D\u001a\u00020\"*\u00020 2\b\u0010E\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010F\u001a\u000202J\u0014\u0010D\u001a\u00020\"*\u00020 2\b\u0010E\u001a\u0004\u0018\u00010GJ\u0016\u0010H\u001a\u00020\"*\u00020 2\b\u0010I\u001a\u0004\u0018\u00010\u0005H\u0007J\u001b\u0010H\u001a\u00020\"*\u00020 2\b\u0010J\u001a\u0004\u0018\u000102H\u0007¢\u0006\u0002\u00103J\u0014\u0010K\u001a\u00020\"*\u00020 2\b\u0010L\u001a\u0004\u0018\u00010\u0005R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006M"}, d2 = {"Lcom/lagradost/cloudstream3/LoadResponse$Companion;", "", "<init>", "()V", "malIdPrefix", "", "getMalIdPrefix", "()Ljava/lang/String;", "setMalIdPrefix", "(Ljava/lang/String;)V", "kitsuIdPrefix", "getKitsuIdPrefix", "setKitsuIdPrefix", "aniListIdPrefix", "getAniListIdPrefix", "setAniListIdPrefix", "simklIdPrefix", "getSimklIdPrefix", "setSimklIdPrefix", "isTrailersEnabled", "", "()Z", "setTrailersEnabled", "(Z)V", "addIdToString", "idString", "database", "Lcom/lagradost/cloudstream3/SimklSyncServices;", "id", "readIdFromString", "", "isMovie", "Lcom/lagradost/cloudstream3/LoadResponse;", "addActors", "", "actors", "", "addActorNames", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/Actor;", "Lcom/lagradost/cloudstream3/ActorRole;", "addActorsRole", "addSimklId", "addActorsOnly", "getMalId", "getKitsuId", "getAniListId", "getImdbId", "getTMDbId", "addMalId", "", "(Lcom/lagradost/cloudstream3/LoadResponse;Ljava/lang/Integer;)V", "addKitsuId", "addAniListId", "addImdbUrl", ImagesContract.URL, "addTrailer", "trailerUrl", "referer", "addRaw", "(Lcom/lagradost/cloudstream3/LoadResponse;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "headers", "(Lcom/lagradost/cloudstream3/LoadResponse;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trailerUrls", "(Lcom/lagradost/cloudstream3/LoadResponse;Ljava/util/List;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addImdbId", "addTraktId", "addTMDbId", "addScore", "score", "maxValue", "Lcom/lagradost/cloudstream3/Score;", "addRating", "text", ES6Iterator.VALUE_PROPERTY, "addDuration", TvContractCompat.PARAM_INPUT, "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static String malIdPrefix = "";
        private static String kitsuIdPrefix = "";
        private static String aniListIdPrefix = "";
        private static String simklIdPrefix = "";
        private static boolean isTrailersEnabled = true;

        public final void addKitsuId(LoadResponse loadResponse, String str) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
        }

        public final void addTraktId(LoadResponse loadResponse, String str) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
        }

        private Companion() {
        }

        public final String getMalIdPrefix() {
            return malIdPrefix;
        }

        public final void setMalIdPrefix(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            malIdPrefix = str;
        }

        public final String getKitsuIdPrefix() {
            return kitsuIdPrefix;
        }

        public final void setKitsuIdPrefix(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            kitsuIdPrefix = str;
        }

        public final String getAniListIdPrefix() {
            return aniListIdPrefix;
        }

        public final void setAniListIdPrefix(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            aniListIdPrefix = str;
        }

        public final String getSimklIdPrefix() {
            return simklIdPrefix;
        }

        public final void setSimklIdPrefix(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            simklIdPrefix = str;
        }

        public final boolean isTrailersEnabled() {
            return isTrailersEnabled;
        }

        public final void setTrailersEnabled(boolean z) {
            isTrailersEnabled = z;
        }

        public final String addIdToString(String idString, SimklSyncServices database, String id) {
            Intrinsics.checkNotNullParameter(database, "database");
            return id == null ? idString : AppUtils.INSTANCE.toJson(MapsKt.plus(readIdFromString(idString), MapsKt.mapOf(TuplesKt.to(database, id))));
        }

        /* JADX WARN: Code duplicated, block: B:18:0x0063  */
        /* JADX WARN: Code duplicated, block: B:25:0x0079 A[Catch: Exception -> 0x008a, TRY_LEAVE, TryCatch #1 {Exception -> 0x008a, blocks: (B:9:0x0031, B:16:0x005d, B:19:0x0064, B:25:0x0079, B:24:0x0074, B:15:0x0053, B:8:0x0027, B:12:0x0038, B:5:0x0007, B:21:0x0068), top: B:34:0x0003, inners: #0, #2, #4, #5 }] */
        /* JADX WARN: Code duplicated, block: B:37:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        public final Map<SimklSyncServices, String> readIdFromString(String idString) {
            Object objM8936constructorimpl;
            KSerializer kSerializer;
            AppUtils appUtils = AppUtils.INSTANCE;
            Object objDecodeFromString = null;
            try {
                if (idString != null) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(BuiltinSerializersKt.getNullable(new LinkedHashMapSerializer(new EnumSerializer("com.lagradost.cloudstream3.SimklSyncServices", SimklSyncServices.values()), StringSerializer.INSTANCE)));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) == null) {
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            try {
                                objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, idString);
                            } catch (SerializationException e) {
                                ArchComponentExtKt.logError(e);
                                objDecodeFromString = MainAPIKt.getMapper().readValue(idString, new TypeReference<Map<SimklSyncServices, ? extends String>>() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$readIdFromString$$inlined$tryParseJson$1
                                });
                            } catch (Throwable unused) {
                                objDecodeFromString = MainAPIKt.getMapper().readValue(idString, new TypeReference<Map<SimklSyncServices, ? extends String>>() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$readIdFromString$$inlined$tryParseJson$1
                                });
                            }
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(idString, new TypeReference<Map<SimklSyncServices, ? extends String>>() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$readIdFromString$$inlined$tryParseJson$1
                            });
                        }
                    } else {
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(Map.class), null, 2, null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                            objM8936constructorimpl = null;
                        }
                        kSerializer = (KSerializer) objM8936constructorimpl;
                        if (kSerializer != null) {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, idString);
                        } else {
                            objDecodeFromString = MainAPIKt.getMapper().readValue(idString, new TypeReference<Map<SimklSyncServices, ? extends String>>() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$readIdFromString$$inlined$tryParseJson$1
                            });
                        }
                    }
                }
            } catch (Exception unused2) {
            }
            Map<SimklSyncServices, String> map = (Map) objDecodeFromString;
            return map == null ? MapsKt.emptyMap() : map;
        }

        public final boolean isMovie(LoadResponse loadResponse) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            return MainAPIKt.isMovieType(loadResponse.getType()) || (loadResponse instanceof MovieLoadResponse);
        }

        public final void addActorNames(LoadResponse loadResponse, List<String> list) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            ArrayList arrayList = null;
            if (list != null) {
                List<String> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new ActorData(new Actor((String) it.next(), null, 2, null), null, null, null, 14, null));
                }
                arrayList = arrayList2;
            }
            loadResponse.setActors(arrayList);
        }

        public final void addActors(LoadResponse loadResponse, List<Pair<Actor, String>> list) {
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            if (list != null) {
                List<Pair<Actor, String>> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    arrayList2.add(new ActorData((Actor) pair.component1(), null, (String) pair.component2(), null, 10, null));
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            loadResponse.setActors(arrayList);
        }

        public final void addActorsRole(LoadResponse loadResponse, List<? extends Pair<Actor, ? extends ActorRole>> list) {
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            if (list != null) {
                List<? extends Pair<Actor, ? extends ActorRole>> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    arrayList2.add(new ActorData((Actor) pair.component1(), (ActorRole) pair.component2(), null, null, 12, null));
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            loadResponse.setActors(arrayList);
        }

        private final void addSimklId(final LoadResponse loadResponse, final SimklSyncServices simklSyncServices, final String str) {
            ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LoadResponse.Companion.addSimklId$lambda$0(loadResponse, simklSyncServices, str);
                }
            });
        }

        static final Unit addSimklId$lambda$0(LoadResponse loadResponse, SimklSyncServices simklSyncServices, String str) {
            Map<String, String> syncData = loadResponse.getSyncData();
            String str2 = simklIdPrefix;
            String strAddIdToString = $$INSTANCE.addIdToString(loadResponse.getSyncData().get(simklIdPrefix), simklSyncServices, String.valueOf(str));
            if (strAddIdToString != null) {
                syncData.put(str2, strAddIdToString);
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }

        public final void addActorsOnly(LoadResponse loadResponse, List<Actor> list) {
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            if (list != null) {
                List<Actor> list2 = list;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new ActorData((Actor) it.next(), null, null, null, 14, null));
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            loadResponse.setActors(arrayList);
        }

        public final String getMalId(LoadResponse loadResponse) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            return loadResponse.getSyncData().get(malIdPrefix);
        }

        public final String getKitsuId(LoadResponse loadResponse) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            return loadResponse.getSyncData().get(kitsuIdPrefix);
        }

        public final String getAniListId(LoadResponse loadResponse) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            return loadResponse.getSyncData().get(aniListIdPrefix);
        }

        public final String getImdbId(final LoadResponse loadResponse) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            return (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LoadResponse.Companion.$$INSTANCE.readIdFromString(loadResponse.getSyncData().get(LoadResponse.Companion.simklIdPrefix)).get(SimklSyncServices.Imdb);
                }
            });
        }

        public final String getTMDbId(final LoadResponse loadResponse) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            return (String) ArchComponentExtKt.safe(new Function0() { // from class: com.lagradost.cloudstream3.LoadResponse$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LoadResponse.Companion.$$INSTANCE.readIdFromString(loadResponse.getSyncData().get(LoadResponse.Companion.simklIdPrefix)).get(SimklSyncServices.Tmdb);
                }
            });
        }

        public final void addMalId(LoadResponse loadResponse, Integer num) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            Map<String, String> syncData = loadResponse.getSyncData();
            String str = malIdPrefix;
            if (num != null) {
                syncData.put(str, String.valueOf(num.intValue()));
                addSimklId(loadResponse, SimklSyncServices.Mal, String.valueOf(num.intValue()));
            }
        }

        public final void addKitsuId(LoadResponse loadResponse, Integer num) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            Map<String, String> syncData = loadResponse.getSyncData();
            String str = kitsuIdPrefix;
            if (num != null) {
                syncData.put(str, String.valueOf(num.intValue()));
            }
        }

        public final void addAniListId(LoadResponse loadResponse, Integer num) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            Map<String, String> syncData = loadResponse.getSyncData();
            String str = aniListIdPrefix;
            if (num != null) {
                syncData.put(str, String.valueOf(num.intValue()));
                addSimklId(loadResponse, SimklSyncServices.AniList, String.valueOf(num.intValue()));
            }
        }

        public final void addSimklId(LoadResponse loadResponse, Integer num) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            addSimklId(loadResponse, SimklSyncServices.Simkl, String.valueOf(num));
        }

        public final void addImdbUrl(LoadResponse loadResponse, String str) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            addImdbId(loadResponse, MainAPIKt.imdbUrlToIdNullable(str));
        }

        public static /* synthetic */ Object addTrailer$default(Companion companion, LoadResponse loadResponse, String str, String str2, boolean z, Continuation continuation, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.addTrailer(loadResponse, str, str3, z, (Continuation<? super Unit>) continuation);
        }

        public final Object addTrailer(LoadResponse loadResponse, String str, String str2, boolean z, Continuation<? super Unit> continuation) {
            String str3;
            if (!isTrailersEnabled || (str3 = str) == null || StringsKt.isBlank(str3)) {
                return Unit.INSTANCE;
            }
            loadResponse.getTrailers().add(new TrailerData(str, str2, z, null, 8, null));
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object addTrailer$default(Companion companion, LoadResponse loadResponse, String str, String str2, boolean z, Map map, Continuation continuation, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            String str3 = str2;
            if ((i & 4) != 0) {
                z = false;
            }
            boolean z2 = z;
            if ((i & 8) != 0) {
                map = MapsKt.emptyMap();
            }
            return companion.addTrailer(loadResponse, str, str3, z2, map, continuation);
        }

        public final Object addTrailer(LoadResponse loadResponse, String str, String str2, boolean z, Map<String, String> map, Continuation<? super Unit> continuation) {
            String str3;
            if (!isTrailersEnabled || (str3 = str) == null || StringsKt.isBlank(str3)) {
                return Unit.INSTANCE;
            }
            loadResponse.getTrailers().add(new TrailerData(str, str2, z, map));
            return Unit.INSTANCE;
        }

        public static /* synthetic */ Object addTrailer$default(Companion companion, LoadResponse loadResponse, List list, String str, boolean z, Continuation continuation, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            String str2 = str;
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.addTrailer(loadResponse, (List<String>) list, str2, z, (Continuation<? super Unit>) continuation);
        }

        public final Object addTrailer(LoadResponse loadResponse, List<String> list, String str, boolean z, Continuation<? super Unit> continuation) {
            if (!isTrailersEnabled || list == null) {
                return Unit.INSTANCE;
            }
            List<TrailerData> trailers = loadResponse.getTrailers();
            List<String> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(new TrailerData((String) it.next(), str, z, null, 8, null));
            }
            trailers.addAll(arrayList);
            return Unit.INSTANCE;
        }

        public final void addImdbId(LoadResponse loadResponse, String str) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            addSimklId(loadResponse, SimklSyncServices.Imdb, str);
        }

        public final void addTMDbId(LoadResponse loadResponse, String str) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            addSimklId(loadResponse, SimklSyncServices.Tmdb, str);
        }

        public static /* synthetic */ void addScore$default(Companion companion, LoadResponse loadResponse, String str, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 10;
            }
            companion.addScore(loadResponse, str, i);
        }

        public final void addScore(LoadResponse loadResponse, String str, int i) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            loadResponse.setScore(Score.INSTANCE.from(str, i));
        }

        public final void addScore(LoadResponse loadResponse, Score score) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            loadResponse.setScore(score);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use addScore", replaceWith = @ReplaceWith(expression = "addScore", imports = {}))
        public final void addRating(LoadResponse loadResponse, String str) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            loadResponse.setScore(Score.INSTANCE.from10(str));
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use addScore", replaceWith = @ReplaceWith(expression = "addScore", imports = {}))
        public final void addRating(LoadResponse loadResponse, Integer num) {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            loadResponse.setScore(Score.INSTANCE.fromOld(num));
        }

        public final void addDuration(LoadResponse loadResponse, String str) throws IOException {
            Intrinsics.checkNotNullParameter(loadResponse, "<this>");
            Integer durationFromString = MainAPIKt.getDurationFromString(str);
            if (durationFromString == null) {
                durationFromString = loadResponse.getDuration();
            }
            loadResponse.setDuration(durationFromString);
        }
    }
}
