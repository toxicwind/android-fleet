package com.lagradost.cloudstream3.extractors;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import io.ktor.http.CodecsKt;
import io.ktor.http.URLUtilsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Dailymotion.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001:\u0004\u001d\u001e\u001f B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00120\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00120\u0016H\u0096@¢\u0006\u0002\u0010\u001aJ\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Dailymotion;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "mainUrl", "", "getMainUrl", "()Ljava/lang/String;", "name", "getName", "requiresReferer", "", "getRequiresReferer", "()Z", "baseUrl", "videoIdRegex", "Lkotlin/text/Regex;", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEmbedUrl", "getVideoId", "MetaData", "Quality", "SubtitlesWrapper", "SubtitleData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Dailymotion extends ExtractorApi {
    private final boolean requiresReferer;
    private final String mainUrl = "https://www.dailymotion.com";
    private final String name = "Dailymotion";
    private final String baseUrl = "https://www.dailymotion.com";
    private final Regex videoIdRegex = new Regex("^[kx][a-zA-Z0-9]+$");

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Dailymotion$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Dailymotion.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Dailymotion", f = "Dailymotion.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {37, 44, 56}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "embedUrl", "id", "metaDataUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "embedUrl", "id", "metaDataUrl", "response", "gson", "meta", "$this$forEach$iv", "element$iv", "quality", "videoUrl", "$i$f$forEach", "$i$a$-forEach-Dailymotion$getUrl$2", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "embedUrl", "id", "metaDataUrl", "response", "gson", "meta", "$this$forEach$iv", "element$iv", "subData", "$this$forEach$iv", "element$iv", "subUrl", "$i$f$forEach", "$i$a$-forEach-Dailymotion$getUrl$3", "$i$f$forEach", "$i$a$-forEach-Dailymotion$getUrl$3$1"}, nl = {38, 51, 55}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "L$15", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "L$15", "L$17", "L$18", "I$0", "I$1", "I$2", "I$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Dailymotion.getUrl$suspendImpl(Dailymotion.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x022c  */
    /* JADX WARN: Code duplicated, block: B:52:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:64:0x034f  */
    /* JADX WARN: Code duplicated, block: B:67:0x0381  */
    /* JADX WARN: Code duplicated, block: B:70:0x041a  */
    /* JADX WARN: Code duplicated, block: B:72:0x0443  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v6, types: [int] */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x02cd -> B:48:0x02e2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0307 -> B:49:0x02f5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x034f -> B:65:0x037b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x041a -> B:71:0x042e). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Dailymotion r30, java.lang.String r31, java.lang.String r32, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r33, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instruction units count: 1118
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Dailymotion.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Dailymotion, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String getEmbedUrl(String url) {
        String str = url;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "/embed/", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "/video/", false, 2, (Object) null)) {
            return url;
        }
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "geo.dailymotion.com", false, 2, (Object) null)) {
            return null;
        }
        return this.baseUrl + "/embed/video/" + StringsKt.substringAfter$default(url, "video=", (String) null, 2, (Object) null);
    }

    private final String getVideoId(String url) {
        String strSubstringAfter$default = StringsKt.substringAfter$default(CodecsKt.decodeURLPart$default(URLUtilsKt.Url(url).getEncodedPath(), 0, 0, null, 7, null), "/video/", (String) null, 2, (Object) null);
        if (this.videoIdRegex.matches(strSubstringAfter$default)) {
            return strSubstringAfter$default;
        }
        return null;
    }

    /* JADX INFO: compiled from: Dailymotion.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0011\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0004HÖ\u0081\u0004R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Dailymotion$MetaData;", "", "qualities", "", "", "", "Lcom/lagradost/cloudstream3/extractors/Dailymotion$Quality;", "subtitles", "Lcom/lagradost/cloudstream3/extractors/Dailymotion$SubtitlesWrapper;", "<init>", "(Ljava/util/Map;Lcom/lagradost/cloudstream3/extractors/Dailymotion$SubtitlesWrapper;)V", "getQualities", "()Ljava/util/Map;", "getSubtitles", "()Lcom/lagradost/cloudstream3/extractors/Dailymotion$SubtitlesWrapper;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MetaData {
        private final Map<String, List<Quality>> qualities;
        private final SubtitlesWrapper subtitles;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MetaData copy$default(MetaData metaData, Map map, SubtitlesWrapper subtitlesWrapper, int i, Object obj) {
            if ((i & 1) != 0) {
                map = metaData.qualities;
            }
            if ((i & 2) != 0) {
                subtitlesWrapper = metaData.subtitles;
            }
            return metaData.copy(map, subtitlesWrapper);
        }

        public final Map<String, List<Quality>> component1() {
            return this.qualities;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final SubtitlesWrapper getSubtitles() {
            return this.subtitles;
        }

        public final MetaData copy(Map<String, ? extends List<Quality>> qualities, SubtitlesWrapper subtitles) {
            return new MetaData(qualities, subtitles);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MetaData)) {
                return false;
            }
            MetaData metaData = (MetaData) other;
            return Intrinsics.areEqual(this.qualities, metaData.qualities) && Intrinsics.areEqual(this.subtitles, metaData.subtitles);
        }

        public int hashCode() {
            Map<String, List<Quality>> map = this.qualities;
            int iHashCode = (map == null ? 0 : map.hashCode()) * 31;
            SubtitlesWrapper subtitlesWrapper = this.subtitles;
            return iHashCode + (subtitlesWrapper != null ? subtitlesWrapper.hashCode() : 0);
        }

        public String toString() {
            return "MetaData(qualities=" + this.qualities + ", subtitles=" + this.subtitles + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public MetaData(Map<String, ? extends List<Quality>> map, SubtitlesWrapper subtitlesWrapper) {
            this.qualities = map;
            this.subtitles = subtitlesWrapper;
        }

        public final Map<String, List<Quality>> getQualities() {
            return this.qualities;
        }

        public final SubtitlesWrapper getSubtitles() {
            return this.subtitles;
        }
    }

    /* JADX INFO: compiled from: Dailymotion.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Dailymotion$Quality;", "", "type", "", ImagesContract.URL, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getType", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Quality {
        private final String type;
        private final String url;

        public static /* synthetic */ Quality copy$default(Quality quality, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = quality.type;
            }
            if ((i & 2) != 0) {
                str2 = quality.url;
            }
            return quality.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final Quality copy(String type, String url) {
            return new Quality(type, url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Quality)) {
                return false;
            }
            Quality quality = (Quality) other;
            return Intrinsics.areEqual(this.type, quality.type) && Intrinsics.areEqual(this.url, quality.url);
        }

        public int hashCode() {
            String str = this.type;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.url;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Quality(type=" + this.type + ", url=" + this.url + ')';
        }

        public Quality(String str, String str2) {
            this.type = str;
            this.url = str2;
        }

        public final String getType() {
            return this.type;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: compiled from: Dailymotion.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005HÆ\u0003J+\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Dailymotion$SubtitlesWrapper;", "", "enable", "", "data", "", "", "Lcom/lagradost/cloudstream3/extractors/Dailymotion$SubtitleData;", "<init>", "(ZLjava/util/Map;)V", "getEnable", "()Z", "getData", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SubtitlesWrapper {
        private final Map<String, SubtitleData> data;
        private final boolean enable;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SubtitlesWrapper copy$default(SubtitlesWrapper subtitlesWrapper, boolean z, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                z = subtitlesWrapper.enable;
            }
            if ((i & 2) != 0) {
                map = subtitlesWrapper.data;
            }
            return subtitlesWrapper.copy(z, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getEnable() {
            return this.enable;
        }

        public final Map<String, SubtitleData> component2() {
            return this.data;
        }

        public final SubtitlesWrapper copy(boolean enable, Map<String, SubtitleData> data) {
            return new SubtitlesWrapper(enable, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitlesWrapper)) {
                return false;
            }
            SubtitlesWrapper subtitlesWrapper = (SubtitlesWrapper) other;
            return this.enable == subtitlesWrapper.enable && Intrinsics.areEqual(this.data, subtitlesWrapper.data);
        }

        public int hashCode() {
            int iM = NextAiring$$ExternalSyntheticBackport0.m(this.enable) * 31;
            Map<String, SubtitleData> map = this.data;
            return iM + (map == null ? 0 : map.hashCode());
        }

        public String toString() {
            return "SubtitlesWrapper(enable=" + this.enable + ", data=" + this.data + ')';
        }

        public SubtitlesWrapper(boolean z, Map<String, SubtitleData> map) {
            this.enable = z;
            this.data = map;
        }

        public final boolean getEnable() {
            return this.enable;
        }

        public final Map<String, SubtitleData> getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: Dailymotion.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Dailymotion$SubtitleData;", "", "label", "", "urls", "", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getLabel", "()Ljava/lang/String;", "getUrls", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SubtitleData {
        private final String label;
        private final List<String> urls;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SubtitleData copy$default(SubtitleData subtitleData, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = subtitleData.label;
            }
            if ((i & 2) != 0) {
                list = subtitleData.urls;
            }
            return subtitleData.copy(str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final List<String> component2() {
            return this.urls;
        }

        public final SubtitleData copy(String label, List<String> urls) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(urls, "urls");
            return new SubtitleData(label, urls);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SubtitleData)) {
                return false;
            }
            SubtitleData subtitleData = (SubtitleData) other;
            return Intrinsics.areEqual(this.label, subtitleData.label) && Intrinsics.areEqual(this.urls, subtitleData.urls);
        }

        public int hashCode() {
            return (this.label.hashCode() * 31) + this.urls.hashCode();
        }

        public String toString() {
            return "SubtitleData(label=" + this.label + ", urls=" + this.urls + ')';
        }

        public SubtitleData(String label, List<String> urls) {
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(urls, "urls");
            this.label = label;
            this.urls = urls;
        }

        public final String getLabel() {
            return this.label;
        }

        public final List<String> getUrls() {
            return this.urls;
        }
    }
}
