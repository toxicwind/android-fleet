package io.github.anilbeesetti.nextlib.mediainfo;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.Key;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MediaInfoBuilder.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0007J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\b\u0010\u001f\u001a\u00020 H\u0003J\u0018\u0010!\u001a\u00020 2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0003Jb\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010\u00072\u0006\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\tH\u0003J^\u00100\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010\u00072\u0006\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\t2\b\u00101\u001a\u0004\u0018\u00010\u00072\u0006\u00102\u001a\u00020$2\u0006\u00103\u001a\u00020$2\b\u00104\u001a\u0004\u0018\u00010\u0007H\u0003J2\u00105\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\b\u0010'\u001a\u0004\u0018\u00010\u00072\u0006\u0010(\u001a\u00020$H\u0003J*\u00106\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u00072\u0006\u00107\u001a\u00020\t2\u0006\u00108\u001a\u00020\tH\u0003J\u0011\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020$H\u0083 J\u0011\u0010;\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u0007H\u0083 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u0012\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/MediaInfoBuilder;", "", "<init>", "()V", "hasError", "", "fileFormatName", "", TypedValues.TransitionType.S_DURATION, "", "Ljava/lang/Long;", "frameLoaderContextHandle", "videoStream", "Lio/github/anilbeesetti/nextlib/mediainfo/VideoStream;", "audioStreams", "", "Lio/github/anilbeesetti/nextlib/mediainfo/AudioStream;", "subtitleStreams", "Lio/github/anilbeesetti/nextlib/mediainfo/SubtitleStream;", "chapters", "Lio/github/anilbeesetti/nextlib/mediainfo/Chapter;", TypedValues.TransitionType.S_FROM, "filePath", "descriptor", "Landroid/os/ParcelFileDescriptor;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/github/anilbeesetti/nextlib/mediainfo/MediaInfo;", "onError", "", "onMediaInfoFound", "onVideoStreamFound", "index", "", "title", "codecName", "language", "disposition", "bitRate", "frameRate", "", "frameWidth", "frameHeight", Key.ROTATION, "frameLoaderContext", "onAudioStreamFound", "sampleFormat", "sampleRate", "channels", "channelLayout", "onSubtitleStreamFound", "onChapterFound", "start", TtmlNode.END, "nativeCreateFromFD", "fileDescriptor", "nativeCreateFromPath", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MediaInfoBuilder {
    private Long duration;
    private String fileFormatName;
    private Long frameLoaderContextHandle;
    private boolean hasError;
    private VideoStream videoStream;
    private final List<AudioStream> audioStreams = new ArrayList();
    private final List<SubtitleStream> subtitleStreams = new ArrayList();
    private final List<Chapter> chapters = new ArrayList();

    private final native void nativeCreateFromFD(int fileDescriptor);

    private final native void nativeCreateFromPath(String filePath);

    public MediaInfoBuilder() {
        System.loadLibrary("mediainfo");
    }

    public final MediaInfoBuilder from(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        nativeCreateFromPath(filePath);
        return this;
    }

    public final MediaInfoBuilder from(ParcelFileDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        nativeCreateFromFD(descriptor.getFd());
        return this;
    }

    public final MediaInfoBuilder from(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String scheme = uri.getScheme();
        if (scheme != null) {
            String lowerCase = scheme.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null && StringsKt.startsWith$default(lowerCase, "http", false, 2, (Object) null)) {
                String string = uri.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                from(string);
                return this;
            }
        }
        String path = PathUtil.INSTANCE.getPath(context, uri);
        if (path != null) {
            from(path);
            return this;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptorOpenFileDescriptor != null) {
                from(parcelFileDescriptorOpenFileDescriptor);
            }
            Unit unit = Unit.INSTANCE;
        } catch (FileNotFoundException e) {
            Integer.valueOf(Log.w(MediaRouteProviderProtocol.SERVICE_DATA_ERROR, e));
        }
        return this;
    }

    public final MediaInfo build() {
        if (this.hasError) {
            return null;
        }
        String str = this.fileFormatName;
        Intrinsics.checkNotNull(str);
        Long l = this.duration;
        Intrinsics.checkNotNull(l);
        return new MediaInfo(str, l.longValue(), this.videoStream, this.audioStreams, this.subtitleStreams, this.chapters, this.frameLoaderContextHandle);
    }

    private final void onError() {
        this.hasError = true;
    }

    private final void onMediaInfoFound(String fileFormatName, long duration) {
        this.fileFormatName = fileFormatName;
        this.duration = Long.valueOf(duration);
    }

    private final void onVideoStreamFound(int index, String title, String codecName, String language, int disposition, long bitRate, double frameRate, int frameWidth, int frameHeight, int rotation, long frameLoaderContext) {
        if (this.videoStream == null) {
            this.videoStream = new VideoStream(index, title, codecName, language, disposition, bitRate, frameRate, frameWidth, frameHeight, rotation);
            if (frameLoaderContext != -1) {
                this.frameLoaderContextHandle = Long.valueOf(frameLoaderContext);
            }
        }
    }

    private final void onAudioStreamFound(int index, String title, String codecName, String language, int disposition, long bitRate, String sampleFormat, int sampleRate, int channels, String channelLayout) {
        this.audioStreams.add(new AudioStream(index, title, codecName, language, disposition, bitRate, sampleFormat, sampleRate, channels, channelLayout));
    }

    private final void onSubtitleStreamFound(int index, String title, String codecName, String language, int disposition) {
        this.subtitleStreams.add(new SubtitleStream(index, title, codecName, language, disposition));
    }

    private final void onChapterFound(int index, String title, long start, long end) {
        this.chapters.add(new Chapter(index, start, end, title));
    }
}
