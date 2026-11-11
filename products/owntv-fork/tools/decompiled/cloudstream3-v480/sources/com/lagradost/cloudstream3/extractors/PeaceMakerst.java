package com.lagradost.cloudstream3.extractors;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import io.ktor.http.LinkHeader;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001:\u0005\u0018\u0019\u001a\u001b\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PeaceMakerst;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "PeaceResponse", "VideoSource", "Teve2ApiResponse", "Teve2Media", "Teve2Link", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class PeaceMakerst extends ExtractorApi {
    private final String name = "PeaceMakerst";
    private final String mainUrl = "https://peacemakerst.com";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.PeaceMakerst$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.PeaceMakerst", f = "PeaceMakerstExtractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {20, 35, 52}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "postUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", "postUrl", "response", "teve2Id", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "m3uLink", "extRef", "postUrl", "response"}, nl = {33, 38, 51}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
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
            return PeaceMakerst.getUrl$suspendImpl(PeaceMakerst.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
        }
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public Object getUrl(String str, String str2, Function1<? super SubtitleFile, Unit> function1, Function1<? super ExtractorLink, Unit> function2, Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function2, continuation);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public String getMainUrl() {
        return this.mainUrl;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorApi
    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x0268  */
    /* JADX WARN: Code duplicated, block: B:40:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:60:0x02fc  */
    /* JADX WARN: Code duplicated, block: B:65:0x0350  */
    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0347, code lost:
    
        if (r2 == r3) goto L62;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:39:0x0268, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v7, types: [kotlin.coroutines.Continuation] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.PeaceMakerst r28, java.lang.String r29, java.lang.String r30, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r31, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r32, kotlin.coroutines.Continuation<? super kotlin.Unit> r33) throws com.lagradost.cloudstream3.ErrorLoadingException {
        /*
            Method dump skipped, instruction units count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.PeaceMakerst.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.PeaceMakerst, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.PeaceMakerst$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.PeaceMakerst$getUrl$2", f = "PeaceMakerstExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $extRef;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$extRef = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$extRef, continuation);
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
            extractorLink.setReferer(this.$extRef);
            extractorLink.setQuality(Qualities.Unknown.getValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0001\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\u0014\b\u0001\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0003\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00032\u0014\b\u0003\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\"\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$PeaceResponse;", "", "videoImage", "", "videoSources", "", "Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$VideoSource;", "sIndex", "sourceList", "", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;)V", "getVideoImage", "()Ljava/lang/String;", "getVideoSources", "()Ljava/util/List;", "getSIndex", "getSourceList", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PeaceResponse {

        @JsonProperty("sIndex")
        private final String sIndex;

        @JsonProperty("sourceList")
        private final Map<String, String> sourceList;

        @JsonProperty("videoImage")
        private final String videoImage;

        @JsonProperty("videoSources")
        private final List<VideoSource> videoSources;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PeaceResponse copy$default(PeaceResponse peaceResponse, String str, List list, String str2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = peaceResponse.videoImage;
            }
            if ((i & 2) != 0) {
                list = peaceResponse.videoSources;
            }
            if ((i & 4) != 0) {
                str2 = peaceResponse.sIndex;
            }
            if ((i & 8) != 0) {
                map = peaceResponse.sourceList;
            }
            return peaceResponse.copy(str, list, str2, map);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getVideoImage() {
            return this.videoImage;
        }

        public final List<VideoSource> component2() {
            return this.videoSources;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSIndex() {
            return this.sIndex;
        }

        public final Map<String, String> component4() {
            return this.sourceList;
        }

        public final PeaceResponse copy(@JsonProperty("videoImage") String videoImage, @JsonProperty("videoSources") List<VideoSource> videoSources, @JsonProperty("sIndex") String sIndex, @JsonProperty("sourceList") Map<String, String> sourceList) {
            Intrinsics.checkNotNullParameter(videoSources, "videoSources");
            Intrinsics.checkNotNullParameter(sIndex, "sIndex");
            Intrinsics.checkNotNullParameter(sourceList, "sourceList");
            return new PeaceResponse(videoImage, videoSources, sIndex, sourceList);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PeaceResponse)) {
                return false;
            }
            PeaceResponse peaceResponse = (PeaceResponse) other;
            return Intrinsics.areEqual(this.videoImage, peaceResponse.videoImage) && Intrinsics.areEqual(this.videoSources, peaceResponse.videoSources) && Intrinsics.areEqual(this.sIndex, peaceResponse.sIndex) && Intrinsics.areEqual(this.sourceList, peaceResponse.sourceList);
        }

        public int hashCode() {
            String str = this.videoImage;
            return ((((((str == null ? 0 : str.hashCode()) * 31) + this.videoSources.hashCode()) * 31) + this.sIndex.hashCode()) * 31) + this.sourceList.hashCode();
        }

        public String toString() {
            return "PeaceResponse(videoImage=" + this.videoImage + ", videoSources=" + this.videoSources + ", sIndex=" + this.sIndex + ", sourceList=" + this.sourceList + ')';
        }

        public PeaceResponse(@JsonProperty("videoImage") String str, @JsonProperty("videoSources") List<VideoSource> videoSources, @JsonProperty("sIndex") String sIndex, @JsonProperty("sourceList") Map<String, String> sourceList) {
            Intrinsics.checkNotNullParameter(videoSources, "videoSources");
            Intrinsics.checkNotNullParameter(sIndex, "sIndex");
            Intrinsics.checkNotNullParameter(sourceList, "sourceList");
            this.videoImage = str;
            this.videoSources = videoSources;
            this.sIndex = sIndex;
            this.sourceList = sourceList;
        }

        public final String getVideoImage() {
            return this.videoImage;
        }

        public final List<VideoSource> getVideoSources() {
            return this.videoSources;
        }

        public final String getSIndex() {
            return this.sIndex;
        }

        public final Map<String, String> getSourceList() {
            return this.sourceList;
        }
    }

    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$VideoSource;", "", UtilsKt.SCHEME_FILE, "", "label", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getLabel", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoSource {

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("label")
        private final String label;

        @JsonProperty("type")
        private final String type;

        public static /* synthetic */ VideoSource copy$default(VideoSource videoSource, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoSource.file;
            }
            if ((i & 2) != 0) {
                str2 = videoSource.label;
            }
            if ((i & 4) != 0) {
                str3 = videoSource.type;
            }
            return videoSource.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        public final VideoSource copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(type, "type");
            return new VideoSource(file, label, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoSource)) {
                return false;
            }
            VideoSource videoSource = (VideoSource) other;
            return Intrinsics.areEqual(this.file, videoSource.file) && Intrinsics.areEqual(this.label, videoSource.label) && Intrinsics.areEqual(this.type, videoSource.type);
        }

        public int hashCode() {
            return (((this.file.hashCode() * 31) + this.label.hashCode()) * 31) + this.type.hashCode();
        }

        public String toString() {
            return "VideoSource(file=" + this.file + ", label=" + this.label + ", type=" + this.type + ')';
        }

        public VideoSource(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("label") String label, @JsonProperty("type") String type) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(label, "label");
            Intrinsics.checkNotNullParameter(type, "type");
            this.file = file;
            this.label = label;
            this.type = type;
        }

        public final String getFile() {
            return this.file;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getType() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2ApiResponse;", "", LinkHeader.Parameters.Media, "Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Media;", "<init>", "(Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Media;)V", "getMedia", "()Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Media;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Teve2ApiResponse {

        @JsonProperty("Media")
        private final Teve2Media media;

        public static /* synthetic */ Teve2ApiResponse copy$default(Teve2ApiResponse teve2ApiResponse, Teve2Media teve2Media, int i, Object obj) {
            if ((i & 1) != 0) {
                teve2Media = teve2ApiResponse.media;
            }
            return teve2ApiResponse.copy(teve2Media);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Teve2Media getMedia() {
            return this.media;
        }

        public final Teve2ApiResponse copy(@JsonProperty("Media") Teve2Media media) {
            Intrinsics.checkNotNullParameter(media, "media");
            return new Teve2ApiResponse(media);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Teve2ApiResponse) && Intrinsics.areEqual(this.media, ((Teve2ApiResponse) other).media);
        }

        public int hashCode() {
            return this.media.hashCode();
        }

        public String toString() {
            return "Teve2ApiResponse(media=" + this.media + ')';
        }

        public Teve2ApiResponse(@JsonProperty("Media") Teve2Media media) {
            Intrinsics.checkNotNullParameter(media, "media");
            this.media = media;
        }

        public final Teve2Media getMedia() {
            return this.media;
        }
    }

    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Media;", "", "link", "Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Link;", "<init>", "(Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Link;)V", "getLink", "()Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Link;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Teve2Media {

        @JsonProperty("Link")
        private final Teve2Link link;

        public static /* synthetic */ Teve2Media copy$default(Teve2Media teve2Media, Teve2Link teve2Link, int i, Object obj) {
            if ((i & 1) != 0) {
                teve2Link = teve2Media.link;
            }
            return teve2Media.copy(teve2Link);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Teve2Link getLink() {
            return this.link;
        }

        public final Teve2Media copy(@JsonProperty("Link") Teve2Link link) {
            Intrinsics.checkNotNullParameter(link, "link");
            return new Teve2Media(link);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Teve2Media) && Intrinsics.areEqual(this.link, ((Teve2Media) other).link);
        }

        public int hashCode() {
            return this.link.hashCode();
        }

        public String toString() {
            return "Teve2Media(link=" + this.link + ')';
        }

        public Teve2Media(@JsonProperty("Link") Teve2Link link) {
            Intrinsics.checkNotNullParameter(link, "link");
            this.link = link;
        }

        public final Teve2Link getLink() {
            return this.link;
        }
    }

    /* JADX INFO: compiled from: PeaceMakerstExtractor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PeaceMakerst$Teve2Link;", "", "serviceUrl", "", "securePath", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getServiceUrl", "()Ljava/lang/String;", "getSecurePath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Teve2Link {

        @JsonProperty("SecurePath")
        private final String securePath;

        @JsonProperty("ServiceUrl")
        private final String serviceUrl;

        public static /* synthetic */ Teve2Link copy$default(Teve2Link teve2Link, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = teve2Link.serviceUrl;
            }
            if ((i & 2) != 0) {
                str2 = teve2Link.securePath;
            }
            return teve2Link.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getServiceUrl() {
            return this.serviceUrl;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSecurePath() {
            return this.securePath;
        }

        public final Teve2Link copy(@JsonProperty("ServiceUrl") String serviceUrl, @JsonProperty("SecurePath") String securePath) {
            Intrinsics.checkNotNullParameter(serviceUrl, "serviceUrl");
            Intrinsics.checkNotNullParameter(securePath, "securePath");
            return new Teve2Link(serviceUrl, securePath);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Teve2Link)) {
                return false;
            }
            Teve2Link teve2Link = (Teve2Link) other;
            return Intrinsics.areEqual(this.serviceUrl, teve2Link.serviceUrl) && Intrinsics.areEqual(this.securePath, teve2Link.securePath);
        }

        public int hashCode() {
            return (this.serviceUrl.hashCode() * 31) + this.securePath.hashCode();
        }

        public String toString() {
            return "Teve2Link(serviceUrl=" + this.serviceUrl + ", securePath=" + this.securePath + ')';
        }

        public Teve2Link(@JsonProperty("ServiceUrl") String serviceUrl, @JsonProperty("SecurePath") String securePath) {
            Intrinsics.checkNotNullParameter(serviceUrl, "serviceUrl");
            Intrinsics.checkNotNullParameter(securePath, "securePath");
            this.serviceUrl = serviceUrl;
            this.securePath = securePath;
        }

        public final String getServiceUrl() {
            return this.serviceUrl;
        }

        public final String getSecurePath() {
            return this.securePath;
        }
    }
}
