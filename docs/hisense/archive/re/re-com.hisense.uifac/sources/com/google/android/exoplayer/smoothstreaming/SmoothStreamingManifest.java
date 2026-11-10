package com.google.android.exoplayer.smoothstreaming;

import android.net.Uri;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatWrapper;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.UriUtil;
import com.google.android.exoplayer.util.Util;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class SmoothStreamingManifest {
    public final long durationUs;
    public final boolean isLive;
    public final ProtectionElement protectionElement;
    public final StreamElement[] streamElements;

    public SmoothStreamingManifest(int i, int i2, long j, long j2, long j3, int i3, boolean z, ProtectionElement protectionElement, StreamElement[] streamElementArr) {
        this.isLive = z;
        this.protectionElement = protectionElement;
        this.streamElements = streamElementArr;
        if (j3 != 0) {
            Util.scaleLargeTimestamp(j3, 1000000L, j);
        }
        this.durationUs = j2 == 0 ? -1L : Util.scaleLargeTimestamp(j2, 1000000L, j);
    }

    public static class ProtectionElement {
        public final byte[] data;
        public final UUID uuid;

        public ProtectionElement(UUID uuid, byte[] bArr) {
            this.uuid = uuid;
            this.data = bArr;
        }
    }

    public static class TrackElement implements FormatWrapper {
        public final byte[][] csd;
        public final Format format;

        public TrackElement(int i, int i2, String str, byte[][] bArr, int i3, int i4, int i5, int i6, String str2) {
            this.csd = bArr;
            this.format = new Format(String.valueOf(i), str, i3, i4, -1.0f, i6, i5, i2, str2);
        }

        @Override // com.google.android.exoplayer.chunk.FormatWrapper
        public Format getFormat() {
            return this.format;
        }
    }

    public static class StreamElement {
        private final String baseUri;
        public final int chunkCount;
        private final List<Long> chunkStartTimes;
        private final long[] chunkStartTimesUs;
        private final String chunkTemplate;
        private final long lastChunkDurationUs;
        public final long timescale;
        public final TrackElement[] tracks;
        public final int type;

        public StreamElement(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, int i6, String str5, TrackElement[] trackElementArr, List<Long> list, long j2) {
            this.baseUri = str;
            this.chunkTemplate = str2;
            this.type = i;
            this.timescale = j;
            this.tracks = trackElementArr;
            this.chunkCount = list.size();
            this.chunkStartTimes = list;
            this.lastChunkDurationUs = Util.scaleLargeTimestamp(j2, 1000000L, j);
            this.chunkStartTimesUs = Util.scaleLargeTimestamps(list, 1000000L, j);
        }

        public int getChunkIndex(long j) {
            return Util.binarySearchFloor(this.chunkStartTimesUs, j, true, true);
        }

        public long getStartTimeUs(int i) {
            return this.chunkStartTimesUs[i];
        }

        public long getChunkDurationUs(int i) {
            if (i == this.chunkCount - 1) {
                return this.lastChunkDurationUs;
            }
            long[] jArr = this.chunkStartTimesUs;
            return jArr[i + 1] - jArr[i];
        }

        public Uri buildRequestUri(int i, int i2) {
            Assertions.checkState(this.tracks != null);
            Assertions.checkState(this.chunkStartTimes != null);
            Assertions.checkState(i2 < this.chunkStartTimes.size());
            String string = Integer.toString(this.tracks[i].format.bitrate);
            String string2 = this.chunkStartTimes.get(i2).toString();
            return UriUtil.resolveToUri(this.baseUri, this.chunkTemplate.replace("{bitrate}", string).replace("{Bitrate}", string).replace("{start time}", string2).replace("{start_time}", string2));
        }
    }
}
