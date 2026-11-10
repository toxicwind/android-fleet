package com.google.android.exoplayer.dash.mpd;

import android.net.Uri;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.chunk.FormatWrapper;
import com.google.android.exoplayer.dash.DashSegmentIndex;

/* JADX INFO: loaded from: classes.dex */
public abstract class Representation implements FormatWrapper {
    public final String baseUrl;
    private final String cacheKey;
    public final Format format;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public static Representation newInstance(String str, long j, Format format, String str2, SegmentBase segmentBase, String str3) {
        if (segmentBase instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(str, j, format, (SegmentBase.SingleSegmentBase) segmentBase, str3, -1L, str2);
        }
        if (segmentBase instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(str, j, format, (SegmentBase.MultiSegmentBase) segmentBase, str3, str2);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }

    private Representation(String str, long j, Format format, String str2, SegmentBase segmentBase, String str3) {
        this.format = format;
        if (str3 == null) {
            str3 = str + "." + format.id + "." + j;
        }
        this.cacheKey = str3;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
        this.baseUrl = str2;
    }

    @Override // com.google.android.exoplayer.chunk.FormatWrapper
    public Format getFormat() {
        return this.format;
    }

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public static class SingleSegmentRepresentation extends Representation {
        private final RangedUri indexUri;
        private final DashSingleSegmentIndex segmentIndex;

        public SingleSegmentRepresentation(String str, long j, Format format, SegmentBase.SingleSegmentBase singleSegmentBase, String str2, long j2, String str3) {
            super(str, j, format, str3, singleSegmentBase, str2);
            Uri.parse(str3);
            RangedUri index = singleSegmentBase.getIndex();
            this.indexUri = index;
            this.segmentIndex = index != null ? null : new DashSingleSegmentIndex(new RangedUri("", 0L, j2));
        }

        @Override // com.google.android.exoplayer.dash.mpd.Representation
        public RangedUri getIndexUri() {
            return this.indexUri;
        }

        @Override // com.google.android.exoplayer.dash.mpd.Representation
        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }
    }

    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        private final SegmentBase.MultiSegmentBase segmentBase;

        @Override // com.google.android.exoplayer.dash.mpd.Representation
        public DashSegmentIndex getIndex() {
            return this;
        }

        @Override // com.google.android.exoplayer.dash.mpd.Representation
        public RangedUri getIndexUri() {
            return null;
        }

        public MultiSegmentRepresentation(String str, long j, Format format, SegmentBase.MultiSegmentBase multiSegmentBase, String str2, String str3) {
            super(str, j, format, str3, multiSegmentBase, str2);
            this.segmentBase = multiSegmentBase;
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public RangedUri getSegmentUrl(int i) {
            return this.segmentBase.getSegmentUrl(this, i);
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public int getSegmentNum(long j, long j2) {
            return this.segmentBase.getSegmentNum(j, j2);
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public long getTimeUs(int i) {
            return this.segmentBase.getSegmentTimeUs(i);
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public long getDurationUs(int i, long j) {
            return this.segmentBase.getSegmentDurationUs(i, j);
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public int getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public int getLastSegmentNum(long j) {
            return this.segmentBase.getLastSegmentNum(j);
        }

        @Override // com.google.android.exoplayer.dash.DashSegmentIndex
        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }
}
