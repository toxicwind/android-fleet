package io.github.anilbeesetti.nextlib.mediainfo;

import androidx.constraintlayout.motion.widget.Key;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStream.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003Jq\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020\u0003HÖ\u0001J\t\u0010/\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0013R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013¨\u00060"}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/VideoStream;", "", "index", "", "title", "", "codecName", "language", "disposition", "bitRate", "", "frameRate", "", "frameWidth", "frameHeight", Key.ROTATION, "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJDIII)V", "getIndex", "()I", "getTitle", "()Ljava/lang/String;", "getCodecName", "getLanguage", "getDisposition", "getBitRate", "()J", "getFrameRate", "()D", "getFrameWidth", "getFrameHeight", "getRotation", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class VideoStream {
    private final long bitRate;
    private final String codecName;
    private final int disposition;
    private final int frameHeight;
    private final double frameRate;
    private final int frameWidth;
    private final int index;
    private final String language;
    private final int rotation;
    private final String title;

    public static /* synthetic */ VideoStream copy$default(VideoStream videoStream, int i, String str, String str2, String str3, int i2, long j, double d, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = videoStream.index;
        }
        if ((i6 & 2) != 0) {
            str = videoStream.title;
        }
        if ((i6 & 4) != 0) {
            str2 = videoStream.codecName;
        }
        if ((i6 & 8) != 0) {
            str3 = videoStream.language;
        }
        if ((i6 & 16) != 0) {
            i2 = videoStream.disposition;
        }
        if ((i6 & 32) != 0) {
            j = videoStream.bitRate;
        }
        if ((i6 & 64) != 0) {
            d = videoStream.frameRate;
        }
        if ((i6 & 128) != 0) {
            i3 = videoStream.frameWidth;
        }
        if ((i6 & 256) != 0) {
            i4 = videoStream.frameHeight;
        }
        if ((i6 & 512) != 0) {
            i5 = videoStream.rotation;
        }
        int i7 = i5;
        int i8 = i3;
        double d2 = d;
        long j2 = j;
        String str4 = str3;
        int i9 = i2;
        String str5 = str2;
        return videoStream.copy(i, str, str5, str4, i9, j2, d2, i8, i4, i7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getRotation() {
        return this.rotation;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCodecName() {
        return this.codecName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getDisposition() {
        return this.disposition;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getBitRate() {
        return this.bitRate;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final double getFrameRate() {
        return this.frameRate;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getFrameWidth() {
        return this.frameWidth;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getFrameHeight() {
        return this.frameHeight;
    }

    public final VideoStream copy(int index, String title, String codecName, String language, int disposition, long bitRate, double frameRate, int frameWidth, int frameHeight, int rotation) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        return new VideoStream(index, title, codecName, language, disposition, bitRate, frameRate, frameWidth, frameHeight, rotation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VideoStream)) {
            return false;
        }
        VideoStream videoStream = (VideoStream) other;
        return this.index == videoStream.index && Intrinsics.areEqual(this.title, videoStream.title) && Intrinsics.areEqual(this.codecName, videoStream.codecName) && Intrinsics.areEqual(this.language, videoStream.language) && this.disposition == videoStream.disposition && this.bitRate == videoStream.bitRate && Double.compare(this.frameRate, videoStream.frameRate) == 0 && this.frameWidth == videoStream.frameWidth && this.frameHeight == videoStream.frameHeight && this.rotation == videoStream.rotation;
    }

    public int hashCode() {
        int i = this.index * 31;
        String str = this.title;
        int iHashCode = (((i + (str == null ? 0 : str.hashCode())) * 31) + this.codecName.hashCode()) * 31;
        String str2 = this.language;
        return ((((((((((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.disposition) * 31) + UByte$$ExternalSyntheticBackport0.m(this.bitRate)) * 31) + UByte$$ExternalSyntheticBackport0.m(this.frameRate)) * 31) + this.frameWidth) * 31) + this.frameHeight) * 31) + this.rotation;
    }

    public String toString() {
        return "VideoStream(index=" + this.index + ", title=" + this.title + ", codecName=" + this.codecName + ", language=" + this.language + ", disposition=" + this.disposition + ", bitRate=" + this.bitRate + ", frameRate=" + this.frameRate + ", frameWidth=" + this.frameWidth + ", frameHeight=" + this.frameHeight + ", rotation=" + this.rotation + ")";
    }

    public VideoStream(int i, String str, String codecName, String str2, int i2, long j, double d, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        this.index = i;
        this.title = str;
        this.codecName = codecName;
        this.language = str2;
        this.disposition = i2;
        this.bitRate = j;
        this.frameRate = d;
        this.frameWidth = i3;
        this.frameHeight = i4;
        this.rotation = i5;
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getCodecName() {
        return this.codecName;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final int getDisposition() {
        return this.disposition;
    }

    public final long getBitRate() {
        return this.bitRate;
    }

    public final double getFrameRate() {
        return this.frameRate;
    }

    public final int getFrameWidth() {
        return this.frameWidth;
    }

    public final int getFrameHeight() {
        return this.frameHeight;
    }

    public final int getRotation() {
        return this.rotation;
    }
}
