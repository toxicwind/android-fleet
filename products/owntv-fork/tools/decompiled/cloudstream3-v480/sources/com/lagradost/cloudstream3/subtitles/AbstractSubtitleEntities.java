package com.lagradost.cloudstream3.subtitles;

import com.google.android.gms.actions.SearchIntents;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.TvType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractSubtitleEntities.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities;", "", "<init>", "()V", "SubtitleEntity", "SubtitleSearch", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AbstractSubtitleEntities {

    /* JADX INFO: compiled from: AbstractSubtitleEntities.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b4\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\bHÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010;\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010%J\u0010\u0010<\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010%J\t\u0010=\u001a\u00020\u000fHÆ\u0003J\u0015\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0003J\u008e\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011HÆ\u0001¢\u0006\u0002\u0010@J\u0014\u0010A\u001a\u00020\u000f2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010C\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010D\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u001e\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b)\u0010%\"\u0004\b*\u0010'R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010(\u001a\u0004\b+\u0010%\"\u0004\b,\u0010'R\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010-\"\u0004\b.\u0010/R&\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006E"}, d2 = {"Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "", "idPrefix", "", "name", "lang", "data", "type", "Lcom/lagradost/cloudstream3/TvType;", "source", "epNumber", "", "seasonNumber", "year", "isHearingImpaired", "", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLjava/util/Map;)V", "getIdPrefix", "()Ljava/lang/String;", "setIdPrefix", "(Ljava/lang/String;)V", "getName", "setName", "getLang", "setLang", "getData", "setData", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getSource", "setSource", "getEpNumber", "()Ljava/lang/Integer;", "setEpNumber", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSeasonNumber", "setSeasonNumber", "getYear", "setYear", "()Z", "setHearingImpaired", "(Z)V", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLjava/util/Map;)Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SubtitleEntity {
        private String data;
        private Integer epNumber;
        private Map<String, String> headers;
        private String idPrefix;
        private boolean isHearingImpaired;
        private String lang;
        private String name;
        private Integer seasonNumber;
        private String source;
        private TvType type;
        private Integer year;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SubtitleEntity copy$default(SubtitleEntity subtitleEntity, String str, String str2, String str3, String str4, TvType tvType, String str5, Integer num, Integer num2, Integer num3, boolean z, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subtitleEntity.idPrefix;
            }
            if ((i & 2) != 0) {
                str2 = subtitleEntity.name;
            }
            if ((i & 4) != 0) {
                str3 = subtitleEntity.lang;
            }
            if ((i & 8) != 0) {
                str4 = subtitleEntity.data;
            }
            if ((i & 16) != 0) {
                tvType = subtitleEntity.type;
            }
            if ((i & 32) != 0) {
                str5 = subtitleEntity.source;
            }
            if ((i & 64) != 0) {
                num = subtitleEntity.epNumber;
            }
            if ((i & 128) != 0) {
                num2 = subtitleEntity.seasonNumber;
            }
            if ((i & 256) != 0) {
                num3 = subtitleEntity.year;
            }
            if ((i & 512) != 0) {
                z = subtitleEntity.isHearingImpaired;
            }
            if ((i & 1024) != 0) {
                map = subtitleEntity.headers;
            }
            boolean z2 = z;
            Map map2 = map;
            Integer num4 = num2;
            Integer num5 = num3;
            String str6 = str5;
            Integer num6 = num;
            TvType tvType2 = tvType;
            String str7 = str3;
            return subtitleEntity.copy(str, str2, str7, str4, tvType2, str6, num6, num4, num5, z2, map2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getIdPrefix() {
            return this.idPrefix;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsHearingImpaired() {
            return this.isHearingImpaired;
        }

        public final Map<String, String> component11() {
            return this.headers;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLang() {
            return this.lang;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getData() {
            return this.data;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final TvType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getEpNumber() {
            return this.epNumber;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final SubtitleEntity copy(String idPrefix, String name, String lang, String data, TvType type, String source, Integer epNumber, Integer seasonNumber, Integer year, boolean isHearingImpaired, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(idPrefix, "idPrefix");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(lang, "lang");
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(headers, "headers");
            return new SubtitleEntity(idPrefix, name, lang, data, type, source, epNumber, seasonNumber, year, isHearingImpaired, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleEntity)) {
                return false;
            }
            SubtitleEntity subtitleEntity = (SubtitleEntity) other;
            return Intrinsics.areEqual(this.idPrefix, subtitleEntity.idPrefix) && Intrinsics.areEqual(this.name, subtitleEntity.name) && Intrinsics.areEqual(this.lang, subtitleEntity.lang) && Intrinsics.areEqual(this.data, subtitleEntity.data) && this.type == subtitleEntity.type && Intrinsics.areEqual(this.source, subtitleEntity.source) && Intrinsics.areEqual(this.epNumber, subtitleEntity.epNumber) && Intrinsics.areEqual(this.seasonNumber, subtitleEntity.seasonNumber) && Intrinsics.areEqual(this.year, subtitleEntity.year) && this.isHearingImpaired == subtitleEntity.isHearingImpaired && Intrinsics.areEqual(this.headers, subtitleEntity.headers);
        }

        public int hashCode() {
            int iHashCode = ((((((((((this.idPrefix.hashCode() * 31) + this.name.hashCode()) * 31) + this.lang.hashCode()) * 31) + this.data.hashCode()) * 31) + this.type.hashCode()) * 31) + this.source.hashCode()) * 31;
            Integer num = this.epNumber;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.seasonNumber;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.year;
            return ((((iHashCode3 + (num3 != null ? num3.hashCode() : 0)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isHearingImpaired)) * 31) + this.headers.hashCode();
        }

        public String toString() {
            return "SubtitleEntity(idPrefix=" + this.idPrefix + ", name=" + this.name + ", lang=" + this.lang + ", data=" + this.data + ", type=" + this.type + ", source=" + this.source + ", epNumber=" + this.epNumber + ", seasonNumber=" + this.seasonNumber + ", year=" + this.year + ", isHearingImpaired=" + this.isHearingImpaired + ", headers=" + this.headers + ')';
        }

        public SubtitleEntity(String idPrefix, String name, String lang, String data, TvType type, String source, Integer num, Integer num2, Integer num3, boolean z, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(idPrefix, "idPrefix");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(lang, "lang");
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.idPrefix = idPrefix;
            this.name = name;
            this.lang = lang;
            this.data = data;
            this.type = type;
            this.source = source;
            this.epNumber = num;
            this.seasonNumber = num2;
            this.year = num3;
            this.isHearingImpaired = z;
            this.headers = headers;
        }

        public /* synthetic */ SubtitleEntity(String str, String str2, String str3, String str4, TvType tvType, String str5, Integer num, Integer num2, Integer num3, boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "en" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? TvType.Movie : tvType, str5, (i & 64) != 0 ? null : num, (i & 128) != 0 ? null : num2, (i & 256) != 0 ? null : num3, (i & 512) != 0 ? false : z, (i & 1024) != 0 ? MapsKt.emptyMap() : map);
        }

        public final String getIdPrefix() {
            return this.idPrefix;
        }

        public final void setIdPrefix(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.idPrefix = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        public final String getLang() {
            return this.lang;
        }

        public final void setLang(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.lang = str;
        }

        public final String getData() {
            return this.data;
        }

        public final void setData(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.data = str;
        }

        public final TvType getType() {
            return this.type;
        }

        public final void setType(TvType tvType) {
            Intrinsics.checkNotNullParameter(tvType, "<set-?>");
            this.type = tvType;
        }

        public final String getSource() {
            return this.source;
        }

        public final void setSource(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.source = str;
        }

        public final Integer getEpNumber() {
            return this.epNumber;
        }

        public final void setEpNumber(Integer num) {
            this.epNumber = num;
        }

        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        public final void setSeasonNumber(Integer num) {
            this.seasonNumber = num;
        }

        public final Integer getYear() {
            return this.year;
        }

        public final void setYear(Integer num) {
            this.year = num;
        }

        public final boolean isHearingImpaired() {
            return this.isHearingImpaired;
        }

        public final void setHearingImpaired(boolean z) {
            this.isHearingImpaired = z;
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }

        public final void setHeaders(Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.headers = map;
        }
    }

    /* JADX INFO: compiled from: AbstractSubtitleEntities.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010-\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010.\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018Jx\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u00100J\u0014\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00104\u001a\u00020\u0007HÖ\u0081\u0004J\n\u00105\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u001e\u0010\u0018\"\u0004\b\u001f\u0010\u001aR\u001e\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\u001aR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001e\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b$\u0010\u0018\"\u0004\b%\u0010\u001a¨\u00066"}, d2 = {"Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "", SearchIntents.EXTRA_QUERY, "", "lang", "imdbId", "tmdbId", "", "malId", "aniListId", "epNumber", "seasonNumber", "year", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getQuery", "()Ljava/lang/String;", "setQuery", "(Ljava/lang/String;)V", "getLang", "setLang", "getImdbId", "setImdbId", "getTmdbId", "()Ljava/lang/Integer;", "setTmdbId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getMalId", "setMalId", "getAniListId", "setAniListId", "getEpNumber", "setEpNumber", "getSeasonNumber", "setSeasonNumber", "getYear", "setYear", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleSearch;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SubtitleSearch {
        private Integer aniListId;
        private Integer epNumber;
        private String imdbId;
        private String lang;
        private Integer malId;
        private String query;
        private Integer seasonNumber;
        private Integer tmdbId;
        private Integer year;

        public SubtitleSearch() {
            this(null, null, null, null, null, null, null, null, null, 511, null);
        }

        public static /* synthetic */ SubtitleSearch copy$default(SubtitleSearch subtitleSearch, String str, String str2, String str3, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subtitleSearch.query;
            }
            if ((i & 2) != 0) {
                str2 = subtitleSearch.lang;
            }
            if ((i & 4) != 0) {
                str3 = subtitleSearch.imdbId;
            }
            if ((i & 8) != 0) {
                num = subtitleSearch.tmdbId;
            }
            if ((i & 16) != 0) {
                num2 = subtitleSearch.malId;
            }
            if ((i & 32) != 0) {
                num3 = subtitleSearch.aniListId;
            }
            if ((i & 64) != 0) {
                num4 = subtitleSearch.epNumber;
            }
            if ((i & 128) != 0) {
                num5 = subtitleSearch.seasonNumber;
            }
            if ((i & 256) != 0) {
                num6 = subtitleSearch.year;
            }
            Integer num7 = num5;
            Integer num8 = num6;
            Integer num9 = num3;
            Integer num10 = num4;
            Integer num11 = num2;
            String str4 = str3;
            return subtitleSearch.copy(str, str2, str4, num, num11, num9, num10, num7, num8);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQuery() {
            return this.query;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLang() {
            return this.lang;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getMalId() {
            return this.malId;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getAniListId() {
            return this.aniListId;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getEpNumber() {
            return this.epNumber;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        public final SubtitleSearch copy(String query, String lang, String imdbId, Integer tmdbId, Integer malId, Integer aniListId, Integer epNumber, Integer seasonNumber, Integer year) {
            Intrinsics.checkNotNullParameter(query, "query");
            return new SubtitleSearch(query, lang, imdbId, tmdbId, malId, aniListId, epNumber, seasonNumber, year);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleSearch)) {
                return false;
            }
            SubtitleSearch subtitleSearch = (SubtitleSearch) other;
            return Intrinsics.areEqual(this.query, subtitleSearch.query) && Intrinsics.areEqual(this.lang, subtitleSearch.lang) && Intrinsics.areEqual(this.imdbId, subtitleSearch.imdbId) && Intrinsics.areEqual(this.tmdbId, subtitleSearch.tmdbId) && Intrinsics.areEqual(this.malId, subtitleSearch.malId) && Intrinsics.areEqual(this.aniListId, subtitleSearch.aniListId) && Intrinsics.areEqual(this.epNumber, subtitleSearch.epNumber) && Intrinsics.areEqual(this.seasonNumber, subtitleSearch.seasonNumber) && Intrinsics.areEqual(this.year, subtitleSearch.year);
        }

        public int hashCode() {
            int iHashCode = this.query.hashCode() * 31;
            String str = this.lang;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.imdbId;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            Integer num = this.tmdbId;
            int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.malId;
            int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.aniListId;
            int iHashCode6 = (iHashCode5 + (num3 == null ? 0 : num3.hashCode())) * 31;
            Integer num4 = this.epNumber;
            int iHashCode7 = (iHashCode6 + (num4 == null ? 0 : num4.hashCode())) * 31;
            Integer num5 = this.seasonNumber;
            int iHashCode8 = (iHashCode7 + (num5 == null ? 0 : num5.hashCode())) * 31;
            Integer num6 = this.year;
            return iHashCode8 + (num6 != null ? num6.hashCode() : 0);
        }

        public String toString() {
            return "SubtitleSearch(query=" + this.query + ", lang=" + this.lang + ", imdbId=" + this.imdbId + ", tmdbId=" + this.tmdbId + ", malId=" + this.malId + ", aniListId=" + this.aniListId + ", epNumber=" + this.epNumber + ", seasonNumber=" + this.seasonNumber + ", year=" + this.year + ')';
        }

        public SubtitleSearch(String query, String str, String str2, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6) {
            Intrinsics.checkNotNullParameter(query, "query");
            this.query = query;
            this.lang = str;
            this.imdbId = str2;
            this.tmdbId = num;
            this.malId = num2;
            this.aniListId = num3;
            this.epNumber = num4;
            this.seasonNumber = num5;
            this.year = num6;
        }

        public /* synthetic */ SubtitleSearch(String str, String str2, String str3, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : num3, (i & 64) != 0 ? null : num4, (i & 128) != 0 ? null : num5, (i & 256) != 0 ? null : num6);
        }

        public final String getQuery() {
            return this.query;
        }

        public final void setQuery(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.query = str;
        }

        public final String getLang() {
            return this.lang;
        }

        public final void setLang(String str) {
            this.lang = str;
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final void setImdbId(String str) {
            this.imdbId = str;
        }

        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        public final void setTmdbId(Integer num) {
            this.tmdbId = num;
        }

        public final Integer getMalId() {
            return this.malId;
        }

        public final void setMalId(Integer num) {
            this.malId = num;
        }

        public final Integer getAniListId() {
            return this.aniListId;
        }

        public final void setAniListId(Integer num) {
            this.aniListId = num;
        }

        public final Integer getEpNumber() {
            return this.epNumber;
        }

        public final void setEpNumber(Integer num) {
            this.epNumber = num;
        }

        public final Integer getSeasonNumber() {
            return this.seasonNumber;
        }

        public final void setSeasonNumber(Integer num) {
            this.seasonNumber = num;
        }

        public final Integer getYear() {
            return this.year;
        }

        public final void setYear(Integer num) {
            this.year = num;
        }
    }
}
