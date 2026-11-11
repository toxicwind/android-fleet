package io.github.anilbeesetti.nextlib.mediainfo;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStream.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003Ju\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014¨\u0006."}, d2 = {"Lio/github/anilbeesetti/nextlib/mediainfo/AudioStream;", "", "index", "", "title", "", "codecName", "language", "disposition", "bitRate", "", "sampleFormat", "sampleRate", "channels", "channelLayout", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IJLjava/lang/String;IILjava/lang/String;)V", "getIndex", "()I", "getTitle", "()Ljava/lang/String;", "getCodecName", "getLanguage", "getDisposition", "getBitRate", "()J", "getSampleFormat", "getSampleRate", "getChannels", "getChannelLayout", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "mediainfo"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AudioStream {
    private final long bitRate;
    private final String channelLayout;
    private final int channels;
    private final String codecName;
    private final int disposition;
    private final int index;
    private final String language;
    private final String sampleFormat;
    private final int sampleRate;
    private final String title;

    public static /* synthetic */ AudioStream copy$default(AudioStream audioStream, int i, String str, String str2, String str3, int i2, long j, String str4, int i3, int i4, String str5, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = audioStream.index;
        }
        if ((i5 & 2) != 0) {
            str = audioStream.title;
        }
        if ((i5 & 4) != 0) {
            str2 = audioStream.codecName;
        }
        if ((i5 & 8) != 0) {
            str3 = audioStream.language;
        }
        if ((i5 & 16) != 0) {
            i2 = audioStream.disposition;
        }
        if ((i5 & 32) != 0) {
            j = audioStream.bitRate;
        }
        if ((i5 & 64) != 0) {
            str4 = audioStream.sampleFormat;
        }
        if ((i5 & 128) != 0) {
            i3 = audioStream.sampleRate;
        }
        if ((i5 & 256) != 0) {
            i4 = audioStream.channels;
        }
        if ((i5 & 512) != 0) {
            str5 = audioStream.channelLayout;
        }
        long j2 = j;
        String str6 = str3;
        int i6 = i2;
        String str7 = str2;
        return audioStream.copy(i, str, str7, str6, i6, j2, str4, i3, i4, str5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getChannelLayout() {
        return this.channelLayout;
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
    public final String getSampleFormat() {
        return this.sampleFormat;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getSampleRate() {
        return this.sampleRate;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getChannels() {
        return this.channels;
    }

    public final AudioStream copy(int index, String title, String codecName, String language, int disposition, long bitRate, String sampleFormat, int sampleRate, int channels, String channelLayout) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        return new AudioStream(index, title, codecName, language, disposition, bitRate, sampleFormat, sampleRate, channels, channelLayout);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioStream)) {
            return false;
        }
        AudioStream audioStream = (AudioStream) other;
        return this.index == audioStream.index && Intrinsics.areEqual(this.title, audioStream.title) && Intrinsics.areEqual(this.codecName, audioStream.codecName) && Intrinsics.areEqual(this.language, audioStream.language) && this.disposition == audioStream.disposition && this.bitRate == audioStream.bitRate && Intrinsics.areEqual(this.sampleFormat, audioStream.sampleFormat) && this.sampleRate == audioStream.sampleRate && this.channels == audioStream.channels && Intrinsics.areEqual(this.channelLayout, audioStream.channelLayout);
    }

    public int hashCode() {
        int i = this.index * 31;
        String str = this.title;
        int iHashCode = (((i + (str == null ? 0 : str.hashCode())) * 31) + this.codecName.hashCode()) * 31;
        String str2 = this.language;
        int iHashCode2 = (((((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.disposition) * 31) + UByte$$ExternalSyntheticBackport0.m(this.bitRate)) * 31;
        String str3 = this.sampleFormat;
        int iHashCode3 = (((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.sampleRate) * 31) + this.channels) * 31;
        String str4 = this.channelLayout;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "AudioStream(index=" + this.index + ", title=" + this.title + ", codecName=" + this.codecName + ", language=" + this.language + ", disposition=" + this.disposition + ", bitRate=" + this.bitRate + ", sampleFormat=" + this.sampleFormat + ", sampleRate=" + this.sampleRate + ", channels=" + this.channels + ", channelLayout=" + this.channelLayout + ")";
    }

    public AudioStream(int i, String str, String codecName, String str2, int i2, long j, String str3, int i3, int i4, String str4) {
        Intrinsics.checkNotNullParameter(codecName, "codecName");
        this.index = i;
        this.title = str;
        this.codecName = codecName;
        this.language = str2;
        this.disposition = i2;
        this.bitRate = j;
        this.sampleFormat = str3;
        this.sampleRate = i3;
        this.channels = i4;
        this.channelLayout = str4;
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

    public final String getSampleFormat() {
        return this.sampleFormat;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public final int getChannels() {
        return this.channels;
    }

    public final String getChannelLayout() {
        return this.channelLayout;
    }
}
