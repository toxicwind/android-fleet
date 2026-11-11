package io.github.anilbeesetti.nextlib.mediainfo;

import android.graphics.Bitmap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MediaInfo.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0010\u0010\u0011J\u0012\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020\u0005J\u0012\u0010&\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020\u0005J\u0006\u0010'\u001a\u00020(J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\f0\tHÆ\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000e0\tHÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0005HÂ\u0003¢\u0006\u0002\u00100Jj\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u00102J\u0013\u00103\u001a\u00020 2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00068"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/MediaInfo;", "", "format", "", TypedValues.TransitionType.S_DURATION, "", "videoStream", "Lio/github/anilbeesetti/nextlib/mediainfo/VideoStream;", "audioStreams", "", "Lio/github/anilbeesetti/nextlib/mediainfo/AudioStream;", "subtitleStreams", "Lio/github/anilbeesetti/nextlib/mediainfo/SubtitleStream;", "chapters", "Lio/github/anilbeesetti/nextlib/mediainfo/Chapter;", "frameLoaderContext", "<init>", "(Ljava/lang/String;JLio/github/anilbeesetti/nextlib/mediainfo/VideoStream;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)V", "getFormat", "()Ljava/lang/String;", "getDuration", "()J", "getVideoStream", "()Lio/github/anilbeesetti/nextlib/mediainfo/VideoStream;", "getAudioStreams", "()Ljava/util/List;", "getSubtitleStreams", "getChapters", "Ljava/lang/Long;", "frameLoader", "Lio/github/anilbeesetti/nextlib/mediainfo/FrameLoader;", "supportsFrameLoading", "", "getSupportsFrameLoading", "()Z", "getFrame", "Landroid/graphics/Bitmap;", "durationMillis", "getFrameAt", "release", "", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "()Ljava/lang/Long;", "copy", "(Ljava/lang/String;JLio/github/anilbeesetti/nextlib/mediainfo/VideoStream;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;)Lio/github/anilbeesetti/nextlib/mediainfo/MediaInfo;", "equals", "other", "hashCode", "", "toString", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class MediaInfo {
    private final List<AudioStream> audioStreams;
    private final List<Chapter> chapters;
    private final long duration;
    private final String format;
    private FrameLoader frameLoader;
    private final Long frameLoaderContext;
    private final List<SubtitleStream> subtitleStreams;
    private final boolean supportsFrameLoading;
    private final VideoStream videoStream;

    /* JADX INFO: renamed from: component7, reason: from getter */
    private final Long getFrameLoaderContext() {
        return this.frameLoaderContext;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MediaInfo copy$default(MediaInfo mediaInfo, String str, long j, VideoStream videoStream, List list, List list2, List list3, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaInfo.format;
        }
        if ((i & 2) != 0) {
            j = mediaInfo.duration;
        }
        if ((i & 4) != 0) {
            videoStream = mediaInfo.videoStream;
        }
        if ((i & 8) != 0) {
            list = mediaInfo.audioStreams;
        }
        if ((i & 16) != 0) {
            list2 = mediaInfo.subtitleStreams;
        }
        if ((i & 32) != 0) {
            list3 = mediaInfo.chapters;
        }
        if ((i & 64) != 0) {
            l = mediaInfo.frameLoaderContext;
        }
        Long l2 = l;
        List list4 = list2;
        VideoStream videoStream2 = videoStream;
        return mediaInfo.copy(str, j, videoStream2, list, list4, list3, l2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFormat() {
        return this.format;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VideoStream getVideoStream() {
        return this.videoStream;
    }

    public final List<AudioStream> component4() {
        return this.audioStreams;
    }

    public final List<SubtitleStream> component5() {
        return this.subtitleStreams;
    }

    public final List<Chapter> component6() {
        return this.chapters;
    }

    public final MediaInfo copy(String format, long duration, VideoStream videoStream, List<AudioStream> audioStreams, List<SubtitleStream> subtitleStreams, List<Chapter> chapters, Long frameLoaderContext) {
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(audioStreams, "audioStreams");
        Intrinsics.checkNotNullParameter(subtitleStreams, "subtitleStreams");
        Intrinsics.checkNotNullParameter(chapters, "chapters");
        return new MediaInfo(format, duration, videoStream, audioStreams, subtitleStreams, chapters, frameLoaderContext);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) other;
        return Intrinsics.areEqual(this.format, mediaInfo.format) && this.duration == mediaInfo.duration && Intrinsics.areEqual(this.videoStream, mediaInfo.videoStream) && Intrinsics.areEqual(this.audioStreams, mediaInfo.audioStreams) && Intrinsics.areEqual(this.subtitleStreams, mediaInfo.subtitleStreams) && Intrinsics.areEqual(this.chapters, mediaInfo.chapters) && Intrinsics.areEqual(this.frameLoaderContext, mediaInfo.frameLoaderContext);
    }

    public int hashCode() {
        int iHashCode = ((this.format.hashCode() * 31) + UByte$$ExternalSyntheticBackport0.m(this.duration)) * 31;
        VideoStream videoStream = this.videoStream;
        int iHashCode2 = (((((((iHashCode + (videoStream == null ? 0 : videoStream.hashCode())) * 31) + this.audioStreams.hashCode()) * 31) + this.subtitleStreams.hashCode()) * 31) + this.chapters.hashCode()) * 31;
        Long l = this.frameLoaderContext;
        return iHashCode2 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "MediaInfo(format=" + this.format + ", duration=" + this.duration + ", videoStream=" + this.videoStream + ", audioStreams=" + this.audioStreams + ", subtitleStreams=" + this.subtitleStreams + ", chapters=" + this.chapters + ", frameLoaderContext=" + this.frameLoaderContext + ")";
    }

    public MediaInfo(String format, long j, VideoStream videoStream, List<AudioStream> audioStreams, List<SubtitleStream> subtitleStreams, List<Chapter> chapters, Long l) {
        FrameLoader frameLoader;
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(audioStreams, "audioStreams");
        Intrinsics.checkNotNullParameter(subtitleStreams, "subtitleStreams");
        Intrinsics.checkNotNullParameter(chapters, "chapters");
        this.format = format;
        this.duration = j;
        this.videoStream = videoStream;
        this.audioStreams = audioStreams;
        this.subtitleStreams = subtitleStreams;
        this.chapters = chapters;
        this.frameLoaderContext = l;
        if (l != null) {
            l.longValue();
            frameLoader = new FrameLoader(l.longValue());
        } else {
            frameLoader = null;
        }
        this.frameLoader = frameLoader;
        this.supportsFrameLoading = frameLoader != null;
    }

    public final String getFormat() {
        return this.format;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final VideoStream getVideoStream() {
        return this.videoStream;
    }

    public final List<AudioStream> getAudioStreams() {
        return this.audioStreams;
    }

    public final List<SubtitleStream> getSubtitleStreams() {
        return this.subtitleStreams;
    }

    public final List<Chapter> getChapters() {
        return this.chapters;
    }

    public final boolean getSupportsFrameLoading() {
        return this.supportsFrameLoading;
    }

    public static /* synthetic */ Bitmap getFrame$default(MediaInfo mediaInfo, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = -1;
        }
        return mediaInfo.getFrame(j);
    }

    public final Bitmap getFrame(long durationMillis) {
        VideoStream videoStream = this.videoStream;
        if (videoStream == null) {
            return null;
        }
        Integer numValueOf = Integer.valueOf(videoStream.getFrameWidth());
        if (numValueOf.intValue() <= 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 1920;
        Integer numValueOf2 = Integer.valueOf(this.videoStream.getFrameHeight());
        if (numValueOf2.intValue() <= 0) {
            numValueOf2 = null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iIntValue, numValueOf2 != null ? numValueOf2.intValue() : 1080, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        FrameLoader frameLoader = this.frameLoader;
        if (Intrinsics.areEqual((Object) (frameLoader != null ? Boolean.valueOf(frameLoader.loadFrameInto(bitmapCreateBitmap, durationMillis)) : null), (Object) true)) {
            return MediaInfoKt.rotate(bitmapCreateBitmap, this.videoStream.getRotation());
        }
        return null;
    }

    public static /* synthetic */ Bitmap getFrameAt$default(MediaInfo mediaInfo, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = -1;
        }
        return mediaInfo.getFrameAt(j);
    }

    public final Bitmap getFrameAt(long durationMillis) {
        FrameLoader frameLoader;
        Bitmap frame;
        if (this.videoStream == null || (frameLoader = this.frameLoader) == null || (frame = frameLoader.getFrame(durationMillis)) == null) {
            return null;
        }
        return MediaInfoKt.rotate(frame, this.videoStream.getRotation());
    }

    public final void release() {
        FrameLoader frameLoader = this.frameLoader;
        if (frameLoader != null) {
            frameLoader.release();
        }
        this.frameLoader = null;
    }
}
