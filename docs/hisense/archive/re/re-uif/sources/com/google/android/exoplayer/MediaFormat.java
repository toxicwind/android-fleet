package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class MediaFormat implements Parcelable {
    public static final Parcelable.Creator<MediaFormat> CREATOR = new Parcelable.Creator<MediaFormat>() { // from class: com.google.android.exoplayer.MediaFormat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaFormat createFromParcel(Parcel parcel) {
            return new MediaFormat(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaFormat[] newArray(int i) {
            return new MediaFormat[i];
        }
    };
    public final boolean adaptive;
    public final int bitrate;
    public final int channelCount;
    public final long durationUs;
    public final int encoderDelay;
    public final int encoderPadding;
    public int frameRate;
    private android.media.MediaFormat frameworkMediaFormat;
    public final int group;
    private int hashCode;
    public final int height;
    public final List<byte[]> initializationData;
    public final String language;
    public final int maxHeight;
    public final int maxInputSize;
    public final int maxWidth;
    public String mimeType;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public int rotationDegrees;
    public final int sampleRate;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public int timestampMode;
    public final String trackId;
    public final int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setFps(long j) {
    }

    public static MediaFormat createVideoFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list) {
        return createVideoFormat(str, str2, i, i2, j, i3, i4, list, -1, -1.0f, null, -1);
    }

    public static MediaFormat createVideoFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, int i5, float f) {
        return new MediaFormat(str, str2, i, i2, j, i3, i4, i5, f, -1, -1, null, LongCompanionObject.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1);
    }

    public static MediaFormat createVideoFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, int i5, float f, byte[] bArr, int i6) {
        return new MediaFormat(str, str2, i, i2, j, i3, i4, i5, f, -1, -1, null, LongCompanionObject.MAX_VALUE, list, false, -1, -1, -1, -1, -1, bArr, i6);
    }

    public static MediaFormat createAudioFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, String str3) {
        return createAudioFormat(str, str2, i, i2, j, i3, i4, list, str3, -1);
    }

    public static MediaFormat createAudioFormat(String str, String str2, int i, int i2, long j, int i3, int i4, List<byte[]> list, String str3, int i5) {
        return new MediaFormat(str, str2, i, i2, j, -1, -1, -1, -1.0f, i3, i4, str3, LongCompanionObject.MAX_VALUE, list, false, -1, -1, i5, -1, -1, null, -1);
    }

    public static MediaFormat createTextFormat(String str, String str2, int i, long j, String str3) {
        return createTextFormat(str, str2, i, j, str3, LongCompanionObject.MAX_VALUE);
    }

    public static MediaFormat createTextFormat(String str, String str2, int i, long j, String str3, long j2) {
        return new MediaFormat(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, str3, j2, null, false, -1, -1, -1, -1, -1, null, -1);
    }

    public static MediaFormat createImageFormat(String str, String str2, int i, long j, List<byte[]> list, String str3) {
        return new MediaFormat(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, str3, LongCompanionObject.MAX_VALUE, list, false, -1, -1, -1, -1, -1, null, -1);
    }

    public static MediaFormat createFormatForMimeType(String str, String str2, int i, long j) {
        return new MediaFormat(str, str2, i, -1, j, -1, -1, -1, -1.0f, -1, -1, null, LongCompanionObject.MAX_VALUE, null, false, -1, -1, -1, -1, -1, null, -1);
    }

    public static MediaFormat createId3Format() {
        return createFormatForMimeType(null, "application/id3", -1, -1L);
    }

    MediaFormat(Parcel parcel) {
        this.trackId = parcel.readString();
        this.mimeType = parcel.readString();
        this.bitrate = parcel.readInt();
        this.maxInputSize = parcel.readInt();
        this.durationUs = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.rotationDegrees = parcel.readInt();
        this.pixelWidthHeightRatio = parcel.readFloat();
        this.channelCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.language = parcel.readString();
        this.subsampleOffsetUs = parcel.readLong();
        ArrayList arrayList = new ArrayList();
        this.initializationData = arrayList;
        parcel.readList(arrayList, null);
        this.adaptive = parcel.readInt() == 1;
        this.maxWidth = parcel.readInt();
        this.maxHeight = parcel.readInt();
        this.pcmEncoding = parcel.readInt();
        this.encoderDelay = parcel.readInt();
        this.encoderPadding = parcel.readInt();
        this.projectionData = parcel.readInt() != 0 ? parcel.createByteArray() : null;
        this.stereoMode = parcel.readInt();
        this.group = -1;
        this.frameRate = -1;
        this.timestampMode = 0;
    }

    MediaFormat(String str, String str2, int i, int i2, long j, int i3, int i4, int i5, float f, int i6, int i7, String str3, long j2, List<byte[]> list, boolean z, int i8, int i9, int i10, int i11, int i12, byte[] bArr, int i13) {
        this.trackId = str;
        Assertions.checkNotEmpty(str2);
        this.mimeType = str2;
        this.bitrate = i;
        this.maxInputSize = i2;
        this.durationUs = j;
        this.width = i3;
        this.height = i4;
        this.rotationDegrees = i5;
        this.pixelWidthHeightRatio = f;
        this.channelCount = i6;
        this.sampleRate = i7;
        this.language = str3;
        this.subsampleOffsetUs = j2;
        this.initializationData = list == null ? Collections.emptyList() : list;
        this.adaptive = z;
        this.maxWidth = i8;
        this.maxHeight = i9;
        this.pcmEncoding = i10;
        this.encoderDelay = i11;
        this.encoderPadding = i12;
        this.projectionData = bArr;
        this.stereoMode = i13;
        this.group = -1;
        this.frameRate = -1;
        this.timestampMode = 0;
    }

    public MediaFormat copyWithMaxInputSize(int i) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, i, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subsampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }

    public MediaFormat copyWithMaxVideoDimensions(int i, int i2) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subsampleOffsetUs, this.initializationData, this.adaptive, i, i2, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }

    public MediaFormat copyWithSubsampleOffsetUs(long j) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, j, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }

    public MediaFormat copyWithDurationUs(long j) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, j, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subsampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }

    public MediaFormat copyWithLanguage(String str) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, str, this.subsampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }

    public MediaFormat copyWithFixedTrackInfo(String str, int i, int i2, int i3, String str2) {
        return new MediaFormat(str, this.mimeType, i, this.maxInputSize, this.durationUs, i2, i3, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, str2, this.subsampleOffsetUs, this.initializationData, this.adaptive, -1, -1, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.projectionData, this.stereoMode);
    }

    public MediaFormat copyAsAdaptive(String str) {
        return new MediaFormat(str, this.mimeType, -1, -1, this.durationUs, -1, -1, -1, -1.0f, -1, -1, null, LongCompanionObject.MAX_VALUE, null, true, this.maxWidth, this.maxHeight, -1, -1, -1, null, this.stereoMode);
    }

    public MediaFormat copyWithGaplessInfo(int i, int i2) {
        return new MediaFormat(this.trackId, this.mimeType, this.bitrate, this.maxInputSize, this.durationUs, this.width, this.height, this.rotationDegrees, this.pixelWidthHeightRatio, this.channelCount, this.sampleRate, this.language, this.subsampleOffsetUs, this.initializationData, this.adaptive, this.maxWidth, this.maxHeight, this.pcmEncoding, i, i2, this.projectionData, this.stereoMode);
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final android.media.MediaFormat getFrameworkMediaFormatV16() {
        if (this.frameworkMediaFormat == null) {
            android.media.MediaFormat mediaFormat = new android.media.MediaFormat();
            mediaFormat.setString("mime", this.mimeType);
            maybeSetStringV16(mediaFormat, "language", this.language);
            maybeSetIntegerV16(mediaFormat, "max-input-size", this.maxInputSize);
            maybeSetIntegerV16(mediaFormat, "width", this.width);
            maybeSetIntegerV16(mediaFormat, "height", this.height);
            maybeSetIntegerV16(mediaFormat, "rotation-degrees", this.rotationDegrees);
            maybeSetIntegerV16(mediaFormat, "max-width", this.maxWidth);
            maybeSetIntegerV16(mediaFormat, "max-height", this.maxHeight);
            maybeSetIntegerV16(mediaFormat, "channel-count", this.channelCount);
            maybeSetIntegerV16(mediaFormat, "sample-rate", this.sampleRate);
            maybeSetIntegerV16(mediaFormat, "encoder-delay", this.encoderDelay);
            maybeSetIntegerV16(mediaFormat, "encoder-padding", this.encoderPadding);
            maybeSetIntegerV16(mediaFormat, "frame-rate", this.frameRate);
            for (int i = 0; i < this.initializationData.size(); i++) {
                mediaFormat.setByteBuffer("csd-" + i, ByteBuffer.wrap(this.initializationData.get(i)));
            }
            long j = this.durationUs;
            if (j != -1) {
                mediaFormat.setLong("durationUs", j);
            }
            this.frameworkMediaFormat = mediaFormat;
        }
        return this.frameworkMediaFormat;
    }

    @TargetApi(16)
    @Deprecated
    final void setFrameworkFormatV16(android.media.MediaFormat mediaFormat) {
        this.frameworkMediaFormat = mediaFormat;
    }

    public String toString() {
        return "MediaFormat(" + this.trackId + ", " + this.mimeType + ", " + this.bitrate + ", " + this.maxInputSize + ", " + this.width + ", " + this.height + ", " + this.rotationDegrees + ", " + this.pixelWidthHeightRatio + ", " + this.channelCount + ", " + this.sampleRate + ", " + this.language + ", " + this.durationUs + ", " + this.adaptive + ", " + this.maxWidth + ", " + this.maxHeight + ", " + this.pcmEncoding + ", " + this.encoderDelay + ", " + this.encoderPadding + ")";
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.trackId;
            int iHashCode = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.mimeType;
            int iHashCode2 = (((((((((((((((((((((((((((((((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.bitrate) * 31) + this.maxInputSize) * 31) + this.width) * 31) + this.height) * 31) + this.rotationDegrees) * 31) + Float.floatToRawIntBits(this.pixelWidthHeightRatio)) * 31) + ((int) this.durationUs)) * 31) + (this.adaptive ? 1231 : 1237)) * 31) + this.maxWidth) * 31) + this.maxHeight) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31;
            String str3 = this.language;
            int iHashCode3 = ((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + ((int) this.subsampleOffsetUs);
            for (int i = 0; i < this.initializationData.size(); i++) {
                iHashCode3 = (iHashCode3 * 31) + Arrays.hashCode(this.initializationData.get(i));
            }
            this.hashCode = (((iHashCode3 * 31) + Arrays.hashCode(this.projectionData)) * 31) + this.stereoMode;
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && MediaFormat.class == obj.getClass()) {
            MediaFormat mediaFormat = (MediaFormat) obj;
            if (this.adaptive == mediaFormat.adaptive && this.bitrate == mediaFormat.bitrate && this.maxInputSize == mediaFormat.maxInputSize && this.durationUs == mediaFormat.durationUs && this.width == mediaFormat.width && this.height == mediaFormat.height && this.rotationDegrees == mediaFormat.rotationDegrees && this.pixelWidthHeightRatio == mediaFormat.pixelWidthHeightRatio && this.maxWidth == mediaFormat.maxWidth && this.maxHeight == mediaFormat.maxHeight && this.channelCount == mediaFormat.channelCount && this.sampleRate == mediaFormat.sampleRate && this.pcmEncoding == mediaFormat.pcmEncoding && this.encoderDelay == mediaFormat.encoderDelay && this.encoderPadding == mediaFormat.encoderPadding && this.subsampleOffsetUs == mediaFormat.subsampleOffsetUs && Util.areEqual(this.trackId, mediaFormat.trackId) && Util.areEqual(this.language, mediaFormat.language) && Util.areEqual(this.mimeType, mediaFormat.mimeType) && this.initializationData.size() == mediaFormat.initializationData.size() && Arrays.equals(this.projectionData, mediaFormat.projectionData) && this.stereoMode == mediaFormat.stereoMode) {
                for (int i = 0; i < this.initializationData.size(); i++) {
                    if (!Arrays.equals(this.initializationData.get(i), mediaFormat.initializationData.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @TargetApi(16)
    private static final void maybeSetStringV16(android.media.MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    @TargetApi(16)
    private static final void maybeSetIntegerV16(android.media.MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.trackId);
        parcel.writeString(this.mimeType);
        parcel.writeInt(this.bitrate);
        parcel.writeInt(this.maxInputSize);
        parcel.writeLong(this.durationUs);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeFloat(this.pixelWidthHeightRatio);
        parcel.writeInt(this.channelCount);
        parcel.writeInt(this.sampleRate);
        parcel.writeString(this.language);
        parcel.writeLong(this.subsampleOffsetUs);
        parcel.writeList(this.initializationData);
        parcel.writeInt(this.adaptive ? 1 : 0);
        parcel.writeInt(this.maxWidth);
        parcel.writeInt(this.maxHeight);
        parcel.writeInt(this.pcmEncoding);
        parcel.writeInt(this.encoderDelay);
        parcel.writeInt(this.encoderPadding);
        parcel.writeInt(this.projectionData != null ? 1 : 0);
        byte[] bArr = this.projectionData;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.stereoMode);
    }

    MediaFormat(String str, String str2, int i, int i2, long j, int i3, int i4, int i5, float f, int i6, int i7, String str3, long j2, List<byte[]> list, boolean z, int i8, int i9, int i10, int i11, int i12, byte[] bArr, int i13, int i14, int i15, int i16, List<Chapter> list2) {
        this.trackId = str;
        Assertions.checkNotEmpty(str2);
        this.mimeType = str2;
        this.bitrate = i;
        this.maxInputSize = i2;
        this.durationUs = j;
        this.width = i3;
        this.height = i4;
        this.rotationDegrees = i5;
        this.pixelWidthHeightRatio = f;
        this.channelCount = i6;
        this.sampleRate = i7;
        this.language = str3;
        this.subsampleOffsetUs = j2;
        this.initializationData = list == null ? Collections.emptyList() : list;
        this.adaptive = z;
        this.maxWidth = i8;
        this.maxHeight = i9;
        this.pcmEncoding = i10;
        this.encoderDelay = i11;
        this.encoderPadding = i12;
        this.projectionData = bArr;
        this.stereoMode = i13;
        this.group = i14;
        this.frameRate = i15;
        this.timestampMode = i16;
    }

    public void setMimeType(String str) {
        Assertions.checkNotEmpty(str);
        this.mimeType = str;
    }

    public static class Chapter {
        private int hashCode;
        public final int id;
        public final long startTime;
        public final String title;

        Chapter(int i, String str, long j) {
            this.id = i;
            this.title = str;
            this.startTime = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Chapter.class == obj.getClass()) {
                Chapter chapter = (Chapter) obj;
                if (this.id == chapter.id && Util.areEqual(this.title, chapter.title) && this.startTime == chapter.startTime) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int i = (527 + this.id) * 31;
                String str = this.title;
                int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
                long j = this.startTime;
                this.hashCode = iHashCode + ((int) (j ^ (j >>> 32)));
            }
            return this.hashCode;
        }
    }
}
