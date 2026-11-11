package com.lagradost.cloudstream3.utils;

import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.AudioFile;
import com.lagradost.cloudstream3.IDownloadableMinimum;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.nicehttp.NiceResponse;
import com.lagradost.nicehttp.Requests;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\t\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001Bk\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012Bg\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0011\u0010\u0013B]\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0011\u0010\u0014B]\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0011\u0010\u0017Be\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0016¢\u0006\u0004\b\u0011\u0010\u0019J\u001a\u00107\u001a\u0004\u0018\u0001052\b\b\u0002\u00108\u001a\u000205H\u0086@¢\u0006\u0002\u00109J\u0014\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nH\u0007J\n\u0010;\u001a\u00020\u0003H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u001a\u0010\u0006\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010 R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R&\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001b\"\u0004\b*\u0010 R\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0015\u00103R\u0011\u0010\u0018\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0018\u00103R\u0012\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0004\n\u0002\u00106¨\u0006<"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "Lcom/lagradost/cloudstream3/IDownloadableMinimum;", "source", "", "name", ImagesContract.URL, "referer", "quality", "", "headers", "", "extractorData", "type", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "audioTracks", "", "Lcom/lagradost/cloudstream3/AudioFile;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/List;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/Map;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/Map;Ljava/lang/String;)V", "isM3u8", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/util/Map;Ljava/lang/String;)V", "isDash", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/util/Map;Ljava/lang/String;Z)V", "getSource", "()Ljava/lang/String;", "getName", "getUrl", "getReferer", "setReferer", "(Ljava/lang/String;)V", "getQuality", "()I", "setQuality", "(I)V", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "getExtractorData", "setExtractorData", "getType", "()Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "setType", "(Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;)V", "getAudioTracks", "()Ljava/util/List;", "setAudioTracks", "(Ljava/util/List;)V", "()Z", "videoSize", "", "Ljava/lang/Long;", "getVideoSize", "timeoutSeconds", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllHeaders", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class ExtractorLink implements IDownloadableMinimum {
    private List<AudioFile> audioTracks;
    private String extractorData;
    private Map<String, String> headers;
    private final String name;
    private int quality;
    private String referer;
    private final String source;
    private ExtractorLinkType type;
    private final String url;
    private Long videoSize;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.ExtractorLink$getVideoSize$1, reason: invalid class name */
    /* JADX INFO: compiled from: ExtractorApi.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.ExtractorLink", f = "ExtractorApi.kt", i = {0, 0, 0}, l = {AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD}, m = "getVideoSize", n = {"$this$getVideoSize_u24lambda_u240", "timeoutSeconds", "$i$a$-runCatching-ExtractorLink$getVideoSize$2"}, nl = {718}, s = {"L$0", "J$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ExtractorLink.this.getVideoSize(0L, this);
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use newExtractorLink")
    public ExtractorLink(String source, String name, String url, String referer, int i, Map<String, String> headers, String str, ExtractorLinkType type, List<AudioFile> audioTracks) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(audioTracks, "audioTracks");
        this.source = source;
        this.name = name;
        this.url = url;
        this.referer = referer;
        this.quality = i;
        this.headers = headers;
        this.extractorData = str;
        this.type = type;
        this.audioTracks = audioTracks;
    }

    public String getSource() {
        return this.source;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.IDownloadableMinimum
    public String getUrl() {
        return this.url;
    }

    @Override // com.lagradost.cloudstream3.IDownloadableMinimum
    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.referer = str;
    }

    public int getQuality() {
        return this.quality;
    }

    public void setQuality(int i) {
        this.quality = i;
    }

    public /* synthetic */ ExtractorLink(String str, String str2, String str3, String str4, int i, Map map, String str5, ExtractorLinkType extractorLinkType, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i, (Map<String, String>) ((i2 & 32) != 0 ? MapsKt.emptyMap() : map), (i2 & 64) != 0 ? null : str5, extractorLinkType, (List<AudioFile>) ((i2 & 256) != 0 ? CollectionsKt.emptyList() : list));
    }

    @Override // com.lagradost.cloudstream3.IDownloadableMinimum
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.headers = map;
    }

    public String getExtractorData() {
        return this.extractorData;
    }

    public void setExtractorData(String str) {
        this.extractorData = str;
    }

    public ExtractorLinkType getType() {
        return this.type;
    }

    public void setType(ExtractorLinkType extractorLinkType) {
        Intrinsics.checkNotNullParameter(extractorLinkType, "<set-?>");
        this.type = extractorLinkType;
    }

    public List<AudioFile> getAudioTracks() {
        return this.audioTracks;
    }

    public void setAudioTracks(List<AudioFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.audioTracks = list;
    }

    public final boolean isM3u8() {
        return getType() == ExtractorLinkType.M3U8;
    }

    public final boolean isDash() {
        return getType() == ExtractorLinkType.DASH;
    }

    public static /* synthetic */ Object getVideoSize$default(ExtractorLink extractorLink, long j, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVideoSize");
        }
        if ((i & 1) != 0) {
            j = 3;
        }
        return extractorLink.getVideoSize(j, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    public final Object getVideoSize(long j, Continuation<? super Long> continuation) {
        AnonymousClass1 anonymousClass1;
        Long l;
        ExtractorLink extractorLink;
        Object objM8936constructorimpl;
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
            if (getType() != ExtractorLinkType.VIDEO) {
                return null;
            }
            l = this.videoSize;
            if (l == null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    ExtractorLink extractorLink2 = this;
                    Requests app2 = MainActivityKt.getApp();
                    String url = getUrl();
                    Map<String, String> headers = getHeaders();
                    String referer = getReferer();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(this);
                    anonymousClass1.L$1 = this;
                    anonymousClass1.J$0 = j;
                    anonymousClass1.I$0 = 0;
                    anonymousClass1.label = 1;
                    Object objHead$default = Requests.head$default(app2, url, headers, referer, null, null, false, 0, null, j, null, false, null, anonymousClass1, 3832, null);
                    if (objHead$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    extractorLink = this;
                    obj = objHead$default;
                } catch (Throwable th) {
                    th = th;
                    extractorLink = this;
                    Result.Companion companion2 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
                }
            } else {
                extractorLink = this;
            }
            extractorLink.videoSize = l;
            return this.videoSize;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        int i2 = anonymousClass1.I$0;
        long j2 = anonymousClass1.J$0;
        extractorLink = (ExtractorLink) anonymousClass1.L$1;
        try {
            ResultKt.throwOnFailure(obj);
        } catch (Throwable th2) {
            th = th2;
            Result.Companion companion3 = Result.INSTANCE;
            objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
        }
        String str = ((NiceResponse) obj).getHeaders().get("Content-Length");
        objM8936constructorimpl = Result.m8936constructorimpl(str != null ? Boxing.boxLong(Long.parseLong(str)) : null);
        l = (Long) (Result.m8942isFailureimpl(objM8936constructorimpl) ? null : objM8936constructorimpl);
        extractorLink.videoSize = l;
        return this.videoSize;
    }

    @JsonIgnore
    public final Map<String, String> getAllHeaders() {
        if (StringsKt.isBlank(getReferer())) {
            return getHeaders();
        }
        Set<String> setKeySet = getHeaders().keySet();
        if (!(setKeySet instanceof Collection) || !setKeySet.isEmpty()) {
            Iterator<T> it = setKeySet.iterator();
            while (it.hasNext()) {
                if (StringsKt.equals((String) it.next(), "referer", true)) {
                    return getHeaders();
                }
            }
        }
        return MapsKt.plus(getHeaders(), MapsKt.mapOf(TuplesKt.to("referer", getReferer())));
    }

    public /* synthetic */ ExtractorLink(String str, String str2, String str3, String str4, Integer num, ExtractorLinkType extractorLinkType, Map map, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : num, (i & 32) != 0 ? ExtractorApiKt.getINFER_TYPE() : extractorLinkType, (Map<String, String>) ((i & 64) != 0 ? MapsKt.emptyMap() : map), (i & 128) != 0 ? null : str5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newExtractorLink")
    public ExtractorLink(String source, String name, String url, String str, Integer num, ExtractorLinkType extractorLinkType, Map<String, String> headers, String str2) {
        this(source, name, url, str == null ? "" : str, num != null ? num.intValue() : Qualities.Unknown.getValue(), headers, str2, extractorLinkType == null ? ExtractorApiKt.inferTypeFromUrl(url) : extractorLinkType, (List) null, 256, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
    }

    public /* synthetic */ ExtractorLink(String str, String str2, String str3, String str4, int i, ExtractorLinkType extractorLinkType, Map map, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i, extractorLinkType, (Map<String, String>) ((i2 & 64) != 0 ? MapsKt.emptyMap() : map), (i2 & 128) != 0 ? null : str5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newExtractorLink")
    public ExtractorLink(String source, String name, String url, String referer, int i, ExtractorLinkType extractorLinkType, Map<String, String> headers, String str) {
        this(source, name, url, referer, i, headers, str, extractorLinkType == null ? ExtractorApiKt.inferTypeFromUrl(url) : extractorLinkType, (List) null, 256, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
    }

    public /* synthetic */ ExtractorLink(String str, String str2, String str3, String str4, int i, boolean z, Map map, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i, (i2 & 32) != 0 ? false : z, (Map<String, String>) ((i2 & 64) != 0 ? MapsKt.emptyMap() : map), (i2 & 128) != 0 ? null : str5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newExtractorLink")
    public ExtractorLink(String source, String name, String url, String referer, int i, boolean z, Map<String, String> headers, String str) {
        this(source, name, url, referer, i, z, headers, str, false);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
    }

    public /* synthetic */ ExtractorLink(String str, String str2, String str3, String str4, int i, boolean z, Map map, String str5, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i, (i2 & 32) != 0 ? false : z, (Map<String, String>) ((i2 & 64) != 0 ? MapsKt.emptyMap() : map), (i2 & 128) != 0 ? null : str5, z2);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newExtractorLink")
    public ExtractorLink(String source, String name, String url, String referer, int i, boolean z, Map<String, String> headers, String str, boolean z2) {
        ExtractorLinkType extractorLinkType;
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        if (z2) {
            extractorLinkType = ExtractorLinkType.DASH;
        } else {
            extractorLinkType = z ? ExtractorLinkType.M3U8 : ExtractorLinkType.VIDEO;
        }
        this(source, name, url, referer, i, headers, str, extractorLinkType, (List) null, 256, (DefaultConstructorMarker) null);
    }

    public String toString() {
        return "ExtractorLink(name=" + getName() + ", url=" + getUrl() + ", referer=" + getReferer() + ", type=" + getType() + ')';
    }
}
