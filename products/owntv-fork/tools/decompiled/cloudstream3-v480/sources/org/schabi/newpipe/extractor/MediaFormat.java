package org.schabi.newpipe.extractor;

import androidx.media3.datasource.cache.CacheDataSink;
import coil3.util.UtilsKt;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.material.internal.ViewUtils;
import j$.util.DesugarArrays;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import j$.util.stream.Collectors;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes8.dex */
public enum MediaFormat {
    MPEG_4(0, "MPEG-4", "mp4", "video/mp4"),
    v3GPP(16, "3GPP", "3gp", "video/3gpp"),
    WEBM(32, "WebM", "webm", "video/webm"),
    M4A(256, "m4a", "m4a", "audio/mp4"),
    WEBMA(512, "WebM", "webm", "audio/webm"),
    MP3(ViewUtils.EDGE_TO_EDGE_FLAGS, "MP3", HlsSegmentFormat.MP3, "audio/mpeg"),
    MP2(784, "MP2", "mp2", "audio/mpeg"),
    OPUS(1024, "opus", "opus", "audio/opus"),
    OGG(1280, "ogg", "ogg", "audio/ogg"),
    WEBMA_OPUS(512, "WebM Opus", "webm", "audio/webm"),
    AIFF(1536, "AIFF", "aiff", "audio/aiff"),
    AIF(1536, "AIFF", "aif", "audio/aiff"),
    WAV(1792, "WAV", "wav", "audio/wav"),
    FLAC(2048, "FLAC", "flac", "audio/flac"),
    ALAC(2304, "ALAC", "alac", "audio/alac"),
    VTT(4096, "WebVTT", "vtt", "text/vtt"),
    TTML(8192, "Timed Text Markup Language", "ttml", "application/ttml+xml"),
    TRANSCRIPT1(12288, "TranScript v1", "srv1", UtilsKt.MIME_TYPE_XML),
    TRANSCRIPT2(16384, "TranScript v2", "srv2", UtilsKt.MIME_TYPE_XML),
    TRANSCRIPT3(CacheDataSink.DEFAULT_BUFFER_SIZE, "TranScript v3", "srv3", UtilsKt.MIME_TYPE_XML),
    SRT(24576, "SubRip file format", "srt", "text/srt");

    public final int id;

    @Nonnull
    public final String mimeType;

    @Nonnull
    public final String name;

    @Nonnull
    public final String suffix;

    static /* synthetic */ MediaFormat lambda$getFormatById$3(MediaFormat mediaFormat) {
        return mediaFormat;
    }

    MediaFormat(@Nonnull int i, @Nonnull String str, String str2, String str3) {
        this.id = i;
        this.name = str;
        this.suffix = str2;
        this.mimeType = str3;
    }

    private static <T> T getById(final int i, Function<MediaFormat, T> function, T t) {
        return (T) DesugarArrays.stream(values()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda7
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MediaFormat.lambda$getById$0(i, (MediaFormat) obj);
            }
        }).map(function).findFirst().orElse(t);
    }

    static /* synthetic */ boolean lambda$getById$0(int i, MediaFormat mediaFormat) {
        return mediaFormat.id == i;
    }

    @Nonnull
    public static String getNameById(int i) {
        return (String) getById(i, new Function() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaFormat) obj).getName();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, "");
    }

    @Nonnull
    public static String getSuffixById(int i) {
        return (String) getById(i, new Function() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaFormat) obj).getSuffix();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, "");
    }

    @Nullable
    public static String getMimeById(int i) {
        return (String) getById(i, new Function() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaFormat) obj).getMimeType();
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, null);
    }

    @Nullable
    public static MediaFormat getFromMimeType(final String str) {
        return (MediaFormat) DesugarArrays.stream(values()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda1
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MediaFormat) obj).mimeType.equals(str);
            }
        }).findFirst().orElse(null);
    }

    @Nonnull
    public static List<MediaFormat> getAllFromMimeType(final String str) {
        return (List) DesugarArrays.stream(values()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda3
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MediaFormat) obj).mimeType.equals(str);
            }
        }).collect(Collectors.toList());
    }

    @Nullable
    public static MediaFormat getFormatById(int i) {
        return (MediaFormat) getById(i, new Function() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public /* synthetic */ Function andThen(Function function) {
                return Function$CC.$default$andThen(this, function);
            }

            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaFormat.lambda$getFormatById$3((MediaFormat) obj);
            }

            public /* synthetic */ Function compose(Function function) {
                return Function$CC.$default$compose(this, function);
            }
        }, null);
    }

    @Nullable
    public static MediaFormat getFromSuffix(final String str) {
        return (MediaFormat) DesugarArrays.stream(values()).filter(new Predicate() { // from class: org.schabi.newpipe.extractor.MediaFormat$$ExternalSyntheticLambda6
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MediaFormat) obj).suffix.equals(str);
            }
        }).findFirst().orElse(null);
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getSuffix() {
        return this.suffix;
    }

    @Nonnull
    public String getMimeType() {
        return this.mimeType;
    }
}
