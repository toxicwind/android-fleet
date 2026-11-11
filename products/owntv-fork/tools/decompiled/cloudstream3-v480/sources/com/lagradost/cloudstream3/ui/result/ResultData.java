package com.lagradost.cloudstream3.ui.result;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\bV\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B§\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0013\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\t\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u001c\u001a\u00020\t\u0012\u0006\u0010\u001d\u001a\u00020\t\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\t\u0012\b\u0010 \u001a\u0004\u0018\u00010\t\u0012\u0006\u0010!\u001a\u00020\t\u0012\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\u0004\b#\u0010$J\t\u0010G\u001a\u00020\u0003HÆ\u0003J\u000f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u0011\u0010I\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010K\u001a\u00020\u000bHÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\u0015\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fHÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010R\u001a\u00020\tHÆ\u0003J\t\u0010S\u001a\u00020\tHÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010[\u001a\u00020\tHÆ\u0003J\t\u0010\\\u001a\u00020\tHÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010`\u001a\u00020\tHÆ\u0003J\u0017\u0010a\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0003JÝ\u0002\u0010b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010!\u001a\u00020\t2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0001J\u0014\u0010c\u001a\u00020\u000b2\b\u0010d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010e\u001a\u00020fHÖ\u0081\u0004J\n\u0010g\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R&\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010&R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010&R\u0011\u0010\u0013\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b7\u0010+R\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b8\u0010+R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b9\u0010+R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b:\u0010+R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b;\u0010+R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b<\u0010+R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b=\u0010+R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b>\u0010+R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b?\u0010+R\u0011\u0010\u001c\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b@\u0010+R\u0011\u0010\u001d\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bA\u0010+R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\bB\u0010+R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\bC\u0010+R\u0013\u0010 \u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\bD\u0010+R\u0011\u0010!\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\bE\u0010+R\u001f\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\bF\u00101¨\u0006h"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultData;", "", ImagesContract.URL, "", "tags", "", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "actorsText", "Lcom/lagradost/cloudstream3/utils/UiText;", "comingSoon", "", "backgroundPosterUrl", "title", "syncData", "", "posterImage", "posterBackgroundImage", "logoUrl", "plotText", "apiName", "ratingText", "contentRatingText", "vpnText", "metaText", "durationText", "onGoingText", "noEpisodesFoundText", "titleText", "typeText", "yearText", "nextAiringDate", "nextAiringEpisode", "plotHeaderText", "posterHeaders", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/lagradost/cloudstream3/utils/UiText;ZLjava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Lcom/lagradost/cloudstream3/utils/UiText;Ljava/util/Map;)V", "getUrl", "()Ljava/lang/String;", "getTags", "()Ljava/util/List;", "getActors", "getActorsText", "()Lcom/lagradost/cloudstream3/utils/UiText;", "getComingSoon", "()Z", "getBackgroundPosterUrl", "getTitle", "getSyncData", "()Ljava/util/Map;", "setSyncData", "(Ljava/util/Map;)V", "getPosterImage", "getPosterBackgroundImage", "getLogoUrl", "getPlotText", "getApiName", "getRatingText", "getContentRatingText", "getVpnText", "getMetaText", "getDurationText", "getOnGoingText", "getNoEpisodesFoundText", "getTitleText", "getTypeText", "getYearText", "getNextAiringDate", "getNextAiringEpisode", "getPlotHeaderText", "getPosterHeaders", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ResultData {
    private final List<ActorData> actors;
    private final UiText actorsText;
    private final UiText apiName;
    private final String backgroundPosterUrl;
    private final boolean comingSoon;
    private final UiText contentRatingText;
    private final UiText durationText;
    private final String logoUrl;
    private final UiText metaText;
    private final UiText nextAiringDate;
    private final UiText nextAiringEpisode;
    private final UiText noEpisodesFoundText;
    private final UiText onGoingText;
    private final UiText plotHeaderText;
    private final UiText plotText;
    private final String posterBackgroundImage;
    private final Map<String, String> posterHeaders;
    private final String posterImage;
    private final UiText ratingText;
    private Map<String, String> syncData;
    private final List<String> tags;
    private final String title;
    private final UiText titleText;
    private final UiText typeText;
    private final String url;
    private final UiText vpnText;
    private final UiText yearText;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ResultData copy$default(ResultData resultData, String str, List list, List list2, UiText uiText, boolean z, String str2, String str3, Map map, String str4, String str5, String str6, UiText uiText2, UiText uiText3, UiText uiText4, UiText uiText5, UiText uiText6, UiText uiText7, UiText uiText8, UiText uiText9, UiText uiText10, UiText uiText11, UiText uiText12, UiText uiText13, UiText uiText14, UiText uiText15, UiText uiText16, Map map2, int i, Object obj) {
        Map map3;
        UiText uiText17;
        String str7 = (i & 1) != 0 ? resultData.url : str;
        List list3 = (i & 2) != 0 ? resultData.tags : list;
        List list4 = (i & 4) != 0 ? resultData.actors : list2;
        UiText uiText18 = (i & 8) != 0 ? resultData.actorsText : uiText;
        boolean z2 = (i & 16) != 0 ? resultData.comingSoon : z;
        String str8 = (i & 32) != 0 ? resultData.backgroundPosterUrl : str2;
        String str9 = (i & 64) != 0 ? resultData.title : str3;
        Map map4 = (i & 128) != 0 ? resultData.syncData : map;
        String str10 = (i & 256) != 0 ? resultData.posterImage : str4;
        String str11 = (i & 512) != 0 ? resultData.posterBackgroundImage : str5;
        String str12 = (i & 1024) != 0 ? resultData.logoUrl : str6;
        UiText uiText19 = (i & 2048) != 0 ? resultData.plotText : uiText2;
        UiText uiText20 = (i & 4096) != 0 ? resultData.apiName : uiText3;
        UiText uiText21 = (i & 8192) != 0 ? resultData.ratingText : uiText4;
        String str13 = str7;
        UiText uiText22 = (i & 16384) != 0 ? resultData.contentRatingText : uiText5;
        UiText uiText23 = (i & 32768) != 0 ? resultData.vpnText : uiText6;
        UiText uiText24 = (i & 65536) != 0 ? resultData.metaText : uiText7;
        UiText uiText25 = (i & 131072) != 0 ? resultData.durationText : uiText8;
        UiText uiText26 = (i & 262144) != 0 ? resultData.onGoingText : uiText9;
        UiText uiText27 = (i & 524288) != 0 ? resultData.noEpisodesFoundText : uiText10;
        UiText uiText28 = (i & 1048576) != 0 ? resultData.titleText : uiText11;
        UiText uiText29 = (i & 2097152) != 0 ? resultData.typeText : uiText12;
        UiText uiText30 = (i & 4194304) != 0 ? resultData.yearText : uiText13;
        UiText uiText31 = (i & 8388608) != 0 ? resultData.nextAiringDate : uiText14;
        UiText uiText32 = (i & 16777216) != 0 ? resultData.nextAiringEpisode : uiText15;
        UiText uiText33 = (i & 33554432) != 0 ? resultData.plotHeaderText : uiText16;
        if ((i & 67108864) != 0) {
            uiText17 = uiText33;
            map3 = resultData.posterHeaders;
        } else {
            map3 = map2;
            uiText17 = uiText33;
        }
        return resultData.copy(str13, list3, list4, uiText18, z2, str8, str9, map4, str10, str11, str12, uiText19, uiText20, uiText21, uiText22, uiText23, uiText24, uiText25, uiText26, uiText27, uiText28, uiText29, uiText30, uiText31, uiText32, uiText17, map3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getPosterBackgroundImage() {
        return this.posterBackgroundImage;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final UiText getPlotText() {
        return this.plotText;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final UiText getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final UiText getRatingText() {
        return this.ratingText;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final UiText getContentRatingText() {
        return this.contentRatingText;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final UiText getVpnText() {
        return this.vpnText;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final UiText getMetaText() {
        return this.metaText;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final UiText getDurationText() {
        return this.durationText;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final UiText getOnGoingText() {
        return this.onGoingText;
    }

    public final List<String> component2() {
        return this.tags;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final UiText getNoEpisodesFoundText() {
        return this.noEpisodesFoundText;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final UiText getTitleText() {
        return this.titleText;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final UiText getTypeText() {
        return this.typeText;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final UiText getYearText() {
        return this.yearText;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final UiText getNextAiringDate() {
        return this.nextAiringDate;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final UiText getNextAiringEpisode() {
        return this.nextAiringEpisode;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final UiText getPlotHeaderText() {
        return this.plotHeaderText;
    }

    public final Map<String, String> component27() {
        return this.posterHeaders;
    }

    public final List<ActorData> component3() {
        return this.actors;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final UiText getActorsText() {
        return this.actorsText;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getComingSoon() {
        return this.comingSoon;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final Map<String, String> component8() {
        return this.syncData;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPosterImage() {
        return this.posterImage;
    }

    public final ResultData copy(String url, List<String> tags, List<ActorData> actors, UiText actorsText, boolean comingSoon, String backgroundPosterUrl, String title, Map<String, String> syncData, String posterImage, String posterBackgroundImage, String logoUrl, UiText plotText, UiText apiName, UiText ratingText, UiText contentRatingText, UiText vpnText, UiText metaText, UiText durationText, UiText onGoingText, UiText noEpisodesFoundText, UiText titleText, UiText typeText, UiText yearText, UiText nextAiringDate, UiText nextAiringEpisode, UiText plotHeaderText, Map<String, String> posterHeaders) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(plotText, "plotText");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(titleText, "titleText");
        Intrinsics.checkNotNullParameter(typeText, "typeText");
        Intrinsics.checkNotNullParameter(plotHeaderText, "plotHeaderText");
        return new ResultData(url, tags, actors, actorsText, comingSoon, backgroundPosterUrl, title, syncData, posterImage, posterBackgroundImage, logoUrl, plotText, apiName, ratingText, contentRatingText, vpnText, metaText, durationText, onGoingText, noEpisodesFoundText, titleText, typeText, yearText, nextAiringDate, nextAiringEpisode, plotHeaderText, posterHeaders);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResultData)) {
            return false;
        }
        ResultData resultData = (ResultData) other;
        return Intrinsics.areEqual(this.url, resultData.url) && Intrinsics.areEqual(this.tags, resultData.tags) && Intrinsics.areEqual(this.actors, resultData.actors) && Intrinsics.areEqual(this.actorsText, resultData.actorsText) && this.comingSoon == resultData.comingSoon && Intrinsics.areEqual(this.backgroundPosterUrl, resultData.backgroundPosterUrl) && Intrinsics.areEqual(this.title, resultData.title) && Intrinsics.areEqual(this.syncData, resultData.syncData) && Intrinsics.areEqual(this.posterImage, resultData.posterImage) && Intrinsics.areEqual(this.posterBackgroundImage, resultData.posterBackgroundImage) && Intrinsics.areEqual(this.logoUrl, resultData.logoUrl) && Intrinsics.areEqual(this.plotText, resultData.plotText) && Intrinsics.areEqual(this.apiName, resultData.apiName) && Intrinsics.areEqual(this.ratingText, resultData.ratingText) && Intrinsics.areEqual(this.contentRatingText, resultData.contentRatingText) && Intrinsics.areEqual(this.vpnText, resultData.vpnText) && Intrinsics.areEqual(this.metaText, resultData.metaText) && Intrinsics.areEqual(this.durationText, resultData.durationText) && Intrinsics.areEqual(this.onGoingText, resultData.onGoingText) && Intrinsics.areEqual(this.noEpisodesFoundText, resultData.noEpisodesFoundText) && Intrinsics.areEqual(this.titleText, resultData.titleText) && Intrinsics.areEqual(this.typeText, resultData.typeText) && Intrinsics.areEqual(this.yearText, resultData.yearText) && Intrinsics.areEqual(this.nextAiringDate, resultData.nextAiringDate) && Intrinsics.areEqual(this.nextAiringEpisode, resultData.nextAiringEpisode) && Intrinsics.areEqual(this.plotHeaderText, resultData.plotHeaderText) && Intrinsics.areEqual(this.posterHeaders, resultData.posterHeaders);
    }

    public int hashCode() {
        int iHashCode = ((this.url.hashCode() * 31) + this.tags.hashCode()) * 31;
        List<ActorData> list = this.actors;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        UiText uiText = this.actorsText;
        int iHashCode3 = (((iHashCode2 + (uiText == null ? 0 : uiText.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.comingSoon)) * 31;
        String str = this.backgroundPosterUrl;
        int iHashCode4 = (((((iHashCode3 + (str == null ? 0 : str.hashCode())) * 31) + this.title.hashCode()) * 31) + this.syncData.hashCode()) * 31;
        String str2 = this.posterImage;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.posterBackgroundImage;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.logoUrl;
        int iHashCode7 = (((((iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.plotText.hashCode()) * 31) + this.apiName.hashCode()) * 31;
        UiText uiText2 = this.ratingText;
        int iHashCode8 = (iHashCode7 + (uiText2 == null ? 0 : uiText2.hashCode())) * 31;
        UiText uiText3 = this.contentRatingText;
        int iHashCode9 = (iHashCode8 + (uiText3 == null ? 0 : uiText3.hashCode())) * 31;
        UiText uiText4 = this.vpnText;
        int iHashCode10 = (iHashCode9 + (uiText4 == null ? 0 : uiText4.hashCode())) * 31;
        UiText uiText5 = this.metaText;
        int iHashCode11 = (iHashCode10 + (uiText5 == null ? 0 : uiText5.hashCode())) * 31;
        UiText uiText6 = this.durationText;
        int iHashCode12 = (iHashCode11 + (uiText6 == null ? 0 : uiText6.hashCode())) * 31;
        UiText uiText7 = this.onGoingText;
        int iHashCode13 = (iHashCode12 + (uiText7 == null ? 0 : uiText7.hashCode())) * 31;
        UiText uiText8 = this.noEpisodesFoundText;
        int iHashCode14 = (((((iHashCode13 + (uiText8 == null ? 0 : uiText8.hashCode())) * 31) + this.titleText.hashCode()) * 31) + this.typeText.hashCode()) * 31;
        UiText uiText9 = this.yearText;
        int iHashCode15 = (iHashCode14 + (uiText9 == null ? 0 : uiText9.hashCode())) * 31;
        UiText uiText10 = this.nextAiringDate;
        int iHashCode16 = (iHashCode15 + (uiText10 == null ? 0 : uiText10.hashCode())) * 31;
        UiText uiText11 = this.nextAiringEpisode;
        int iHashCode17 = (((iHashCode16 + (uiText11 == null ? 0 : uiText11.hashCode())) * 31) + this.plotHeaderText.hashCode()) * 31;
        Map<String, String> map = this.posterHeaders;
        return iHashCode17 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "ResultData(url=" + this.url + ", tags=" + this.tags + ", actors=" + this.actors + ", actorsText=" + this.actorsText + ", comingSoon=" + this.comingSoon + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", title=" + this.title + ", syncData=" + this.syncData + ", posterImage=" + this.posterImage + ", posterBackgroundImage=" + this.posterBackgroundImage + ", logoUrl=" + this.logoUrl + ", plotText=" + this.plotText + ", apiName=" + this.apiName + ", ratingText=" + this.ratingText + ", contentRatingText=" + this.contentRatingText + ", vpnText=" + this.vpnText + ", metaText=" + this.metaText + ", durationText=" + this.durationText + ", onGoingText=" + this.onGoingText + ", noEpisodesFoundText=" + this.noEpisodesFoundText + ", titleText=" + this.titleText + ", typeText=" + this.typeText + ", yearText=" + this.yearText + ", nextAiringDate=" + this.nextAiringDate + ", nextAiringEpisode=" + this.nextAiringEpisode + ", plotHeaderText=" + this.plotHeaderText + ", posterHeaders=" + this.posterHeaders + ')';
    }

    public ResultData(String url, List<String> tags, List<ActorData> list, UiText uiText, boolean z, String str, String title, Map<String, String> syncData, String str2, String str3, String str4, UiText plotText, UiText apiName, UiText uiText2, UiText uiText3, UiText uiText4, UiText uiText5, UiText uiText6, UiText uiText7, UiText uiText8, UiText titleText, UiText typeText, UiText uiText9, UiText uiText10, UiText uiText11, UiText plotHeaderText, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(plotText, "plotText");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(titleText, "titleText");
        Intrinsics.checkNotNullParameter(typeText, "typeText");
        Intrinsics.checkNotNullParameter(plotHeaderText, "plotHeaderText");
        this.url = url;
        this.tags = tags;
        this.actors = list;
        this.actorsText = uiText;
        this.comingSoon = z;
        this.backgroundPosterUrl = str;
        this.title = title;
        this.syncData = syncData;
        this.posterImage = str2;
        this.posterBackgroundImage = str3;
        this.logoUrl = str4;
        this.plotText = plotText;
        this.apiName = apiName;
        this.ratingText = uiText2;
        this.contentRatingText = uiText3;
        this.vpnText = uiText4;
        this.metaText = uiText5;
        this.durationText = uiText6;
        this.onGoingText = uiText7;
        this.noEpisodesFoundText = uiText8;
        this.titleText = titleText;
        this.typeText = typeText;
        this.yearText = uiText9;
        this.nextAiringDate = uiText10;
        this.nextAiringEpisode = uiText11;
        this.plotHeaderText = plotHeaderText;
        this.posterHeaders = map;
    }

    public /* synthetic */ ResultData(String str, List list, List list2, UiText uiText, boolean z, String str2, String str3, Map map, String str4, String str5, String str6, UiText uiText2, UiText uiText3, UiText uiText4, UiText uiText5, UiText uiText6, UiText uiText7, UiText uiText8, UiText uiText9, UiText uiText10, UiText uiText11, UiText uiText12, UiText uiText13, UiText uiText14, UiText uiText15, UiText uiText16, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, list2, uiText, z, str2, str3, map, str4, str5, str6, uiText2, uiText3, uiText4, uiText5, uiText6, uiText7, uiText8, uiText9, uiText10, uiText11, uiText12, uiText13, uiText14, uiText15, uiText16, (i & 67108864) != 0 ? null : map2);
    }

    public final String getUrl() {
        return this.url;
    }

    public final List<String> getTags() {
        return this.tags;
    }

    public final List<ActorData> getActors() {
        return this.actors;
    }

    public final UiText getActorsText() {
        return this.actorsText;
    }

    public final boolean getComingSoon() {
        return this.comingSoon;
    }

    public final String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Map<String, String> getSyncData() {
        return this.syncData;
    }

    public final void setSyncData(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncData = map;
    }

    public final String getPosterImage() {
        return this.posterImage;
    }

    public final String getPosterBackgroundImage() {
        return this.posterBackgroundImage;
    }

    public final String getLogoUrl() {
        return this.logoUrl;
    }

    public final UiText getPlotText() {
        return this.plotText;
    }

    public final UiText getApiName() {
        return this.apiName;
    }

    public final UiText getRatingText() {
        return this.ratingText;
    }

    public final UiText getContentRatingText() {
        return this.contentRatingText;
    }

    public final UiText getVpnText() {
        return this.vpnText;
    }

    public final UiText getMetaText() {
        return this.metaText;
    }

    public final UiText getDurationText() {
        return this.durationText;
    }

    public final UiText getOnGoingText() {
        return this.onGoingText;
    }

    public final UiText getNoEpisodesFoundText() {
        return this.noEpisodesFoundText;
    }

    public final UiText getTitleText() {
        return this.titleText;
    }

    public final UiText getTypeText() {
        return this.typeText;
    }

    public final UiText getYearText() {
        return this.yearText;
    }

    public final UiText getNextAiringDate() {
        return this.nextAiringDate;
    }

    public final UiText getNextAiringEpisode() {
        return this.nextAiringEpisode;
    }

    public final UiText getPlotHeaderText() {
        return this.plotHeaderText;
    }

    public final Map<String, String> getPosterHeaders() {
        return this.posterHeaders;
    }
}
