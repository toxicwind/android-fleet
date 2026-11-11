package com.lagradost.cloudstream3.extractors;

import coil3.util.UtilsKt;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.List;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.schabi.newpipe.extractor.services.youtube.YoutubeParsingHelper;

/* JADX INFO: compiled from: VideoSeyredExtractor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001:\u0003\u0018\u0019\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/VideoSeyred;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", ImagesContract.URL, "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "VideoSeyredSource", "VSSource", "VSTrack", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class VideoSeyred extends ExtractorApi {
    private final String name = "VideoSeyred";
    private final String mainUrl = "https://videoseyred.in";
    private final boolean requiresReferer = true;

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.VideoSeyred$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: VideoSeyredExtractor.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.VideoSeyred", f = "VideoSeyredExtractor.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {21, 28, 38}, m = "getUrl$suspendImpl", n = {"$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", YoutubeParsingHelper.VIDEO_ID, "videoUrl", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", YoutubeParsingHelper.VIDEO_ID, "videoUrl", "responseRaw", "responseList", "response", "track", "$this", ImagesContract.URL, "referer", "subtitleCallback", "callback", "extRef", YoutubeParsingHelper.VIDEO_ID, "videoUrl", "responseRaw", "responseList", "response", "source"}, nl = {22, 27, 37}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
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
            return VideoSeyred.getUrl$suspendImpl(VideoSeyred.this, (String) null, (String) null, (Function1<? super SubtitleFile, Unit>) null, (Function1<? super ExtractorLink, Unit>) null, (Continuation<? super Unit>) this);
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

    /* JADX WARN: Code duplicated, block: B:55:0x024b  */
    /* JADX WARN: Code duplicated, block: B:57:0x0257  */
    /* JADX WARN: Code duplicated, block: B:59:0x0265  */
    /* JADX WARN: Code duplicated, block: B:62:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:65:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:66:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x02cc -> B:63:0x02cd). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x036e -> B:75:0x0372). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.VideoSeyred r23, java.lang.String r24, java.lang.String r25, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r26, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) {
        /*
            Method dump skipped, instruction units count: 901
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lagradost.cloudstream3.extractors.VideoSeyred.getUrl$suspendImpl(com.lagradost.cloudstream3.extractors.VideoSeyred, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.extractors.VideoSeyred$getUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: VideoSeyredExtractor.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.extractors.VideoSeyred$getUrl$2", f = "VideoSeyredExtractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = VideoSeyred.this.new AnonymousClass2(continuation);
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
            extractorLink.setReferer(VideoSeyred.this.getMainUrl() + '/');
            extractorLink.setQuality(Qualities.Unknown.getValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: VideoSeyredExtractor.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0001\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0003\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/VideoSeyred$VideoSeyredSource;", "", "image", "", "title", "sources", "", "Lcom/lagradost/cloudstream3/extractors/VideoSeyred$VSSource;", "tracks", "Lcom/lagradost/cloudstream3/extractors/VideoSeyred$VSTrack;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getImage", "()Ljava/lang/String;", "getTitle", "getSources", "()Ljava/util/List;", "getTracks", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoSeyredSource {

        @JsonProperty("image")
        private final String image;

        @JsonProperty("sources")
        private final List<VSSource> sources;

        @JsonProperty("title")
        private final String title;

        @JsonProperty("tracks")
        private final List<VSTrack> tracks;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoSeyredSource copy$default(VideoSeyredSource videoSeyredSource, String str, String str2, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = videoSeyredSource.image;
            }
            if ((i & 2) != 0) {
                str2 = videoSeyredSource.title;
            }
            if ((i & 4) != 0) {
                list = videoSeyredSource.sources;
            }
            if ((i & 8) != 0) {
                list2 = videoSeyredSource.tracks;
            }
            return videoSeyredSource.copy(str, str2, list, list2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getImage() {
            return this.image;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final List<VSSource> component3() {
            return this.sources;
        }

        public final List<VSTrack> component4() {
            return this.tracks;
        }

        public final VideoSeyredSource copy(@JsonProperty("image") String image, @JsonProperty("title") String title, @JsonProperty("sources") List<VSSource> sources, @JsonProperty("tracks") List<VSTrack> tracks) {
            Intrinsics.checkNotNullParameter(image, "image");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(sources, "sources");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            return new VideoSeyredSource(image, title, sources, tracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoSeyredSource)) {
                return false;
            }
            VideoSeyredSource videoSeyredSource = (VideoSeyredSource) other;
            return Intrinsics.areEqual(this.image, videoSeyredSource.image) && Intrinsics.areEqual(this.title, videoSeyredSource.title) && Intrinsics.areEqual(this.sources, videoSeyredSource.sources) && Intrinsics.areEqual(this.tracks, videoSeyredSource.tracks);
        }

        public int hashCode() {
            return (((((this.image.hashCode() * 31) + this.title.hashCode()) * 31) + this.sources.hashCode()) * 31) + this.tracks.hashCode();
        }

        public String toString() {
            return "VideoSeyredSource(image=" + this.image + ", title=" + this.title + ", sources=" + this.sources + ", tracks=" + this.tracks + ')';
        }

        public VideoSeyredSource(@JsonProperty("image") String image, @JsonProperty("title") String title, @JsonProperty("sources") List<VSSource> sources, @JsonProperty("tracks") List<VSTrack> tracks) {
            Intrinsics.checkNotNullParameter(image, "image");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(sources, "sources");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            this.image = image;
            this.title = title;
            this.sources = sources;
            this.tracks = tracks;
        }

        public final String getImage() {
            return this.image;
        }

        public final String getTitle() {
            return this.title;
        }

        public final List<VSSource> getSources() {
            return this.sources;
        }

        public final List<VSTrack> getTracks() {
            return this.tracks;
        }
    }

    /* JADX INFO: compiled from: VideoSeyredExtractor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/VideoSeyred$VSSource;", "", UtilsKt.SCHEME_FILE, "", "type", "default", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getType", "getDefault", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VSSource {

        @JsonProperty("default")
        private final String default;

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("type")
        private final String type;

        public static /* synthetic */ VSSource copy$default(VSSource vSSource, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = vSSource.file;
            }
            if ((i & 2) != 0) {
                str2 = vSSource.type;
            }
            if ((i & 4) != 0) {
                str3 = vSSource.default;
            }
            return vSSource.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDefault() {
            return this.default;
        }

        public final VSSource copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("type") String type, @JsonProperty("default") String str) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(str, "default");
            return new VSSource(file, type, str);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VSSource)) {
                return false;
            }
            VSSource vSSource = (VSSource) other;
            return Intrinsics.areEqual(this.file, vSSource.file) && Intrinsics.areEqual(this.type, vSSource.type) && Intrinsics.areEqual(this.default, vSSource.default);
        }

        public int hashCode() {
            return (((this.file.hashCode() * 31) + this.type.hashCode()) * 31) + this.default.hashCode();
        }

        public String toString() {
            return "VSSource(file=" + this.file + ", type=" + this.type + ", default=" + this.default + ')';
        }

        public VSSource(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("type") String type, @JsonProperty("default") String str) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(str, "default");
            this.file = file;
            this.type = type;
            this.default = str;
        }

        public final String getFile() {
            return this.file;
        }

        public final String getType() {
            return this.type;
        }

        public final String getDefault() {
            return this.default;
        }
    }

    /* JADX INFO: compiled from: VideoSeyredExtractor.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003JA\u0010\u0015\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/extractors/VideoSeyred$VSTrack;", "", UtilsKt.SCHEME_FILE, "", "kind", "language", "label", "default", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/lang/String;", "getKind", "getLanguage", "getLabel", "getDefault", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VSTrack {

        @JsonProperty("default")
        private final String default;

        @JsonProperty(UtilsKt.SCHEME_FILE)
        private final String file;

        @JsonProperty("kind")
        private final String kind;

        @JsonProperty("label")
        private final String label;

        @JsonProperty("language")
        private final String language;

        public static /* synthetic */ VSTrack copy$default(VSTrack vSTrack, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = vSTrack.file;
            }
            if ((i & 2) != 0) {
                str2 = vSTrack.kind;
            }
            if ((i & 4) != 0) {
                str3 = vSTrack.language;
            }
            if ((i & 8) != 0) {
                str4 = vSTrack.label;
            }
            if ((i & 16) != 0) {
                str5 = vSTrack.default;
            }
            String str6 = str5;
            String str7 = str3;
            return vSTrack.copy(str, str2, str7, str4, str6);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getKind() {
            return this.kind;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDefault() {
            return this.default;
        }

        public final VSTrack copy(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("kind") String kind, @JsonProperty("language") String language, @JsonProperty("label") String label, @JsonProperty("default") String str) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(kind, "kind");
            return new VSTrack(file, kind, language, label, str);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VSTrack)) {
                return false;
            }
            VSTrack vSTrack = (VSTrack) other;
            return Intrinsics.areEqual(this.file, vSTrack.file) && Intrinsics.areEqual(this.kind, vSTrack.kind) && Intrinsics.areEqual(this.language, vSTrack.language) && Intrinsics.areEqual(this.label, vSTrack.label) && Intrinsics.areEqual(this.default, vSTrack.default);
        }

        public int hashCode() {
            int iHashCode = ((this.file.hashCode() * 31) + this.kind.hashCode()) * 31;
            String str = this.language;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.label;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.default;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "VSTrack(file=" + this.file + ", kind=" + this.kind + ", language=" + this.language + ", label=" + this.label + ", default=" + this.default + ')';
        }

        public VSTrack(@JsonProperty(UtilsKt.SCHEME_FILE) String file, @JsonProperty("kind") String kind, @JsonProperty("language") String str, @JsonProperty("label") String str2, @JsonProperty("default") String str3) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(kind, "kind");
            this.file = file;
            this.kind = kind;
            this.language = str;
            this.label = str2;
            this.default = str3;
        }

        public /* synthetic */ VSTrack(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5);
        }

        public final String getFile() {
            return this.file;
        }

        public final String getKind() {
            return this.kind;
        }

        public final String getLanguage() {
            return this.language;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getDefault() {
            return this.default;
        }
    }
}
