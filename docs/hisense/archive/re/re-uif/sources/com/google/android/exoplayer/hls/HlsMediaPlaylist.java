package com.google.android.exoplayer.hls;

import com.google.android.exoplayer.util.MtkDebugLogUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HlsMediaPlaylist extends HlsPlaylist {
    public final long durationUs;
    public final boolean live;
    public final int mediaSequence;
    public final long seekableRangeEnd;
    public final long seekableRangeStart;
    public final List<Segment> segments;
    public final int targetDurationSecs;

    public static final class Segment implements Comparable<Long> {
        public final long byterangeLength;
        public final long byterangeOffset;
        public final int discontinuitySequenceNumber;
        public final double durationSecs;
        public final String encryptionIV;
        public final String encryptionKeyUri;
        public final boolean isEncrypted;
        public final int mediaSequence;
        public final long startTimeUs;
        public final String url;

        @Override // java.lang.Comparable
        public int compareTo(Long l) {
            if (this.startTimeUs > l.longValue()) {
                return 1;
            }
            return this.startTimeUs < l.longValue() ? -1 : 0;
        }

        public Segment(String str, double d, int i, long j, boolean z, String str2, String str3, long j2, long j3, int i2) {
            this.url = str;
            this.durationSecs = d;
            this.discontinuitySequenceNumber = i;
            this.startTimeUs = j;
            this.isEncrypted = z;
            this.encryptionKeyUri = str2;
            this.encryptionIV = str3;
            this.byterangeOffset = j2;
            this.byterangeLength = j3;
            this.mediaSequence = i2;
            MtkDebugLogUtil.log("HlsSegment", "URL[" + this.url + "]\ndurationSecs[" + this.durationSecs + "]\ndiscontinuity[" + this.discontinuitySequenceNumber + "]\nstartTimeUs[" + this.startTimeUs + "]\nisEncrypted[" + this.isEncrypted + "]\nencryptionKeyUri[" + this.encryptionKeyUri + "]\nencryptionIV[" + this.encryptionIV + "]\nbyterangeOffset[" + this.byterangeOffset + "]\nbyterangeLength[" + this.byterangeLength + "]\nmediaSequence[" + this.mediaSequence + "]\n", 4);
        }
    }

    public HlsMediaPlaylist(String str, int i, int i2, int i3, boolean z, List<Segment> list) {
        Segment segment;
        super(str, 1);
        this.mediaSequence = i;
        this.targetDurationSecs = i2;
        this.live = z;
        this.segments = list;
        if (!list.isEmpty()) {
            Segment segment2 = list.get(list.size() - 1);
            this.durationUs = segment2.startTimeUs + ((long) (segment2.durationSecs * 1000000.0d));
            this.seekableRangeStart = list.get(0).startTimeUs;
            if (this.live) {
                if (list.size() > 3) {
                    segment = list.get(list.size() - 3);
                } else {
                    segment = list.get(0);
                }
                this.seekableRangeEnd = segment.startTimeUs;
            } else {
                this.seekableRangeEnd = this.durationUs;
            }
        } else {
            this.durationUs = 0L;
            this.seekableRangeStart = 0L;
            this.seekableRangeEnd = 0L;
        }
        MtkDebugLogUtil.log("HlsMediaPlaylist", "seekable range(" + this.seekableRangeStart + "," + this.seekableRangeEnd + ")", 4);
    }

    public HlsMediaPlaylist(String str, int i, int i2, int i3, boolean z, List<Segment> list, long j) {
        this(str, i, i2, i3, z, list);
    }
}
