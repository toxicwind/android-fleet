package com.lagradost.cloudstream3.utils;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: M3u8Helper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0086@¢\u0006\u0002\u0010\n¨\u0006\r"}, d2 = {"Lcom/lagradost/cloudstream3/utils/M3u8Helper;", "", "<init>", "()V", "m3u8Generation", "", "Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;", "m3u8", "returnThis", "", "(Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;Ljava/lang/Boolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "M3u8Stream", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class M3u8Helper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/utils/M3u8Helper$Companion;", "", "<init>", "()V", "generateM3u8", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "source", "", "streamUrl", "referer", "quality", "", "headers", "", "name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object generateM3u8$default(Companion companion, String str, String str2, String str3, Integer num, Map map, String str4, Continuation continuation, int i, Object obj) {
            if ((i & 8) != 0) {
                num = null;
            }
            Integer num2 = num;
            if ((i & 16) != 0) {
                map = MapsKt.emptyMap();
            }
            return companion.generateM3u8(str, str2, str3, num2, map, (i & 32) != 0 ? str : str4, continuation);
        }

        public final Object generateM3u8(String str, String str2, String str3, Integer num, Map<String, String> map, String str4, Continuation<? super List<? extends ExtractorLink>> continuation) {
            return M3u8Helper2.INSTANCE.generateM3u8(str, str2, str3, num, map, str4, continuation);
        }
    }

    /* JADX INFO: compiled from: M3u8Helper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\rJ\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J:\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;", "", "streamUrl", "", "quality", "", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;)V", "getStreamUrl", "()Ljava/lang/String;", "getQuality", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;)Lcom/lagradost/cloudstream3/utils/M3u8Helper$M3u8Stream;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class M3u8Stream {
        private final Map<String, String> headers;
        private final Integer quality;
        private final String streamUrl;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ M3u8Stream copy$default(M3u8Stream m3u8Stream, String str, Integer num, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = m3u8Stream.streamUrl;
            }
            if ((i & 2) != 0) {
                num = m3u8Stream.quality;
            }
            if ((i & 4) != 0) {
                map = m3u8Stream.headers;
            }
            return m3u8Stream.copy(str, num, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStreamUrl() {
            return this.streamUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getQuality() {
            return this.quality;
        }

        public final Map<String, String> component3() {
            return this.headers;
        }

        public final M3u8Stream copy(String streamUrl, Integer quality, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(streamUrl, "streamUrl");
            Intrinsics.checkNotNullParameter(headers, "headers");
            return new M3u8Stream(streamUrl, quality, headers);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof M3u8Stream)) {
                return false;
            }
            M3u8Stream m3u8Stream = (M3u8Stream) other;
            return Intrinsics.areEqual(this.streamUrl, m3u8Stream.streamUrl) && Intrinsics.areEqual(this.quality, m3u8Stream.quality) && Intrinsics.areEqual(this.headers, m3u8Stream.headers);
        }

        public int hashCode() {
            int iHashCode = this.streamUrl.hashCode() * 31;
            Integer num = this.quality;
            return ((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + this.headers.hashCode();
        }

        public String toString() {
            return "M3u8Stream(streamUrl=" + this.streamUrl + ", quality=" + this.quality + ", headers=" + this.headers + ')';
        }

        public M3u8Stream(String streamUrl, Integer num, Map<String, String> headers) {
            Intrinsics.checkNotNullParameter(streamUrl, "streamUrl");
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.streamUrl = streamUrl;
            this.quality = num;
            this.headers = headers;
        }

        public final String getStreamUrl() {
            return this.streamUrl;
        }

        public final Integer getQuality() {
            return this.quality;
        }

        public /* synthetic */ M3u8Stream(String str, Integer num, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? MapsKt.emptyMap() : map);
        }

        public final Map<String, String> getHeaders() {
            return this.headers;
        }
    }

    public static /* synthetic */ Object m3u8Generation$default(M3u8Helper m3u8Helper, M3u8Stream m3u8Stream, Boolean bool, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            bool = true;
        }
        return m3u8Helper.m3u8Generation(m3u8Stream, bool, continuation);
    }

    public final Object m3u8Generation(M3u8Stream m3u8Stream, Boolean bool, Continuation<? super List<M3u8Stream>> continuation) {
        return M3u8Helper2.INSTANCE.m3u8Generation(m3u8Stream, bool != null ? bool.booleanValue() : true, continuation);
    }
}
