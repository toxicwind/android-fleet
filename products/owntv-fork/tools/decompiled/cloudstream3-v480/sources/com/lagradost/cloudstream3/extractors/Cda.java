package com.lagradost.cloudstream3.extractors;

import androidx.media3.exoplayer.upstream.CmcdData;
import coil3.util.UtilsKt;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.schabi.newpipe.extractor.utils.Utils;

/* JADX INFO: compiled from: Cda.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0096@¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0005H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u0014\u0010\r\u001a\u00020\u000eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Cda;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "mainUrl", "", "getMainUrl", "()Ljava/lang/String;", "setMainUrl", "(Ljava/lang/String;)V", "name", "getName", "setName", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", ImagesContract.URL, "referer", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rot13", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "cdaUggc", "cdaDecrypt", "b", "getFile", "VideoPlayerData", "PlayerData", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class Cda extends ExtractorApi {
    private String mainUrl = "https://ebd.cda.pl";
    private String name = "Cda";
    private final boolean requiresReferer;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Cda$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Cda.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Cda", f = "Cda.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {21, 31}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "mediaId", "$this", ImagesContract.URL, "referer", "mediaId", "doc", "dataRaw", "playerData"}, nl = {27, 30}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Cda.getUrl$suspendImpl(Cda.this, (String) null, (String) null, (Continuation<? super List<? extends ExtractorLink>>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Continuation<? super List<? extends ExtractorLink>> continuation) {
        return getUrl$suspendImpl(this, str, str2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mainUrl = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x018e  */
    /* JADX WARN: Code duplicated, block: B:46:0x01a4 A[Catch: Exception -> 0x01b6, TRY_LEAVE, TryCatch #3 {Exception -> 0x01b6, blocks: (B:30:0x015d, B:37:0x0188, B:40:0x018f, B:46:0x01a4, B:45:0x019f, B:36:0x017e, B:29:0x0153, B:26:0x0145, B:33:0x0164, B:42:0x0193), top: B:59:0x0145, inners: #1, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x0193 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x020f, code lost:
    
        if (r1 == r2) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Cda r22, java.lang.String r23, java.lang.String r24, kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.utils.ExtractorLink>> r25) {
        /*
            Method dump skipped, instruction units count: 536
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.Cda.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.Cda, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.Cda$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: Cda.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.Cda$getUrl$2", f = "Cda.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mediaId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$mediaId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$mediaId, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(extractorLink, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ExtractorLink extractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            extractorLink.setReferer("https://ebd.cda.pl/647x500/" + this.$mediaId);
            extractorLink.setQuality(Qualities.Unknown.getValue());
            return Unit.INSTANCE;
        }
    }

    private final String rot13(String a) {
        int i;
        String str = a;
        ArrayList arrayList = new ArrayList(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (('A' > cCharAt || cCharAt >= 'N') && ('a' > cCharAt || cCharAt >= 'n')) {
                if (('N' <= cCharAt && cCharAt < '[') || ('n' <= cCharAt && cCharAt < '{')) {
                    i = cCharAt - '\r';
                }
                arrayList.add(Character.valueOf(cCharAt));
            } else {
                i = cCharAt + '\r';
            }
            cCharAt = (char) i;
            arrayList.add(Character.valueOf(cCharAt));
        }
        return CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null);
    }

    private final String cdaUggc(String a) {
        String strRot13 = rot13(a);
        return StringsKt.endsWith$default(strRot13, "adc.mp4", false, 2, (Object) null) ? StringsKt.replace$default(strRot13, "adc.mp4", ".mp4", false, 4, (Object) null) : strRot13;
    }

    private final String cdaDecrypt(String b) {
        Object objValueOf;
        String strDecodeUrl = StringUtils.INSTANCE.decodeUrl(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(b, "_XDDD", "", false, 4, (Object) null), "_CDA", "", false, 4, (Object) null), "_ADC", "", false, 4, (Object) null), "_CXD", "", false, 4, (Object) null), "_QWE", "", false, 4, (Object) null), "_Q5", "", false, 4, (Object) null), "_IKSDE", "", false, 4, (Object) null));
        ArrayList arrayList = new ArrayList(strDecodeUrl.length());
        for (int i = 0; i < strDecodeUrl.length(); i++) {
            char cCharAt = strDecodeUrl.charAt(i);
            if ('!' <= cCharAt && cCharAt < 127) {
                objValueOf = String.valueOf((char) (((cCharAt + 14) % 94) + 33));
            } else {
                objValueOf = Character.valueOf(cCharAt);
            }
            arrayList.add(objValueOf);
        }
        String strReplace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(CollectionsKt.joinToString$default(arrayList, "", null, null, 0, null, null, 62, null), ".cda.mp4", "", false, 4, (Object) null), ".2cda.pl", ".cda.pl", false, 4, (Object) null), ".3cda.pl", ".cda.pl", false, 4, (Object) null);
        if (StringsKt.contains$default((CharSequence) strReplace$default, (CharSequence) "/upstream", false, 2, (Object) null)) {
            return Utils.HTTPS + StringsKt.replace$default(strReplace$default, "/upstream", ".mp4/upstream", false, 4, (Object) null);
        }
        return Utils.HTTPS + strReplace$default + ".mp4";
    }

    private final String getFile(String a) {
        if (StringsKt.startsWith$default(a, "uggc", false, 2, (Object) null)) {
            return cdaUggc(a);
        }
        return !StringsKt.startsWith$default(a, "http", false, 2, (Object) null) ? cdaDecrypt(a) : a;
    }

    /* JADX INFO: compiled from: Cda.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JR\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\bHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006!"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Cda$VideoPlayerData;", "", UtilsKt.SCHEME_FILE, "", "qualities", "", "quality", HlsSegmentFormat.TS, "", "hash2", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getQualities", "()Ljava/util/Map;", "getQuality", "getTs", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHash2", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/extractors/Cda$VideoPlayerData;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoPlayerData {
        private final String file;
        private final String hash2;
        private final Map<String, String> qualities;
        private final String quality;
        private final Integer ts;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoPlayerData copy$default(VideoPlayerData videoPlayerData, String str, Map map, String str2, Integer num, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoPlayerData.file;
            }
            if ((i & 2) != 0) {
                map = videoPlayerData.qualities;
            }
            if ((i & 4) != 0) {
                str2 = videoPlayerData.quality;
            }
            if ((i & 8) != 0) {
                num = videoPlayerData.ts;
            }
            if ((i & 16) != 0) {
                str3 = videoPlayerData.hash2;
            }
            String str4 = str3;
            String str5 = str2;
            return videoPlayerData.copy(str, map, str5, num, str4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        public final Map<String, String> component2() {
            return this.qualities;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getQuality() {
            return this.quality;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getTs() {
            return this.ts;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getHash2() {
            return this.hash2;
        }

        public final VideoPlayerData copy(String file, Map<String, String> qualities, String quality, Integer ts, String hash2) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(qualities, "qualities");
            return new VideoPlayerData(file, qualities, quality, ts, hash2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoPlayerData)) {
                return false;
            }
            VideoPlayerData videoPlayerData = (VideoPlayerData) other;
            return Intrinsics.areEqual(this.file, videoPlayerData.file) && Intrinsics.areEqual(this.qualities, videoPlayerData.qualities) && Intrinsics.areEqual(this.quality, videoPlayerData.quality) && Intrinsics.areEqual(this.ts, videoPlayerData.ts) && Intrinsics.areEqual(this.hash2, videoPlayerData.hash2);
        }

        public int hashCode() {
            int iHashCode = ((this.file.hashCode() * 31) + this.qualities.hashCode()) * 31;
            String str = this.quality;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Integer num = this.ts;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            String str2 = this.hash2;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "VideoPlayerData(file=" + this.file + ", qualities=" + this.qualities + ", quality=" + this.quality + ", ts=" + this.ts + ", hash2=" + this.hash2 + ')';
        }

        public VideoPlayerData(String file, Map<String, String> qualities, String str, Integer num, String str2) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(qualities, "qualities");
            this.file = file;
            this.qualities = qualities;
            this.quality = str;
            this.ts = num;
            this.hash2 = str2;
        }

        public final String getFile() {
            return this.file;
        }

        public /* synthetic */ VideoPlayerData(String str, Map map, String str2, Integer num, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? MapsKt.emptyMap() : map, str2, num, str3);
        }

        public final Map<String, String> getQualities() {
            return this.qualities;
        }

        public final String getQuality() {
            return this.quality;
        }

        public final Integer getTs() {
            return this.ts;
        }

        public final String getHash2() {
            return this.hash2;
        }
    }

    /* JADX INFO: compiled from: Cda.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/Cda$PlayerData;", "", "video", "Lcom/lagradost/cloudstream3/extractors/Cda$VideoPlayerData;", "<init>", "(Lcom/lagradost/cloudstream3/extractors/Cda$VideoPlayerData;)V", "getVideo", "()Lcom/lagradost/cloudstream3/extractors/Cda$VideoPlayerData;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PlayerData {
        private final VideoPlayerData video;

        public static /* synthetic */ PlayerData copy$default(PlayerData playerData, VideoPlayerData videoPlayerData, int i, Object obj) {
            if ((i & 1) != 0) {
                videoPlayerData = playerData.video;
            }
            return playerData.copy(videoPlayerData);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VideoPlayerData getVideo() {
            return this.video;
        }

        public final PlayerData copy(VideoPlayerData video) {
            Intrinsics.checkNotNullParameter(video, "video");
            return new PlayerData(video);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof PlayerData) && Intrinsics.areEqual(this.video, ((PlayerData) other).video);
        }

        public int hashCode() {
            return this.video.hashCode();
        }

        public String toString() {
            return "PlayerData(video=" + this.video + ')';
        }

        public PlayerData(VideoPlayerData video) {
            Intrinsics.checkNotNullParameter(video, "video");
            this.video = video;
        }

        public final VideoPlayerData getVideo() {
            return this.video;
        }
    }
}
