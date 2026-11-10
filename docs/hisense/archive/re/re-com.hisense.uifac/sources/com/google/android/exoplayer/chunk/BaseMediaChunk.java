package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMediaChunk extends MediaChunk {
    private int firstSampleIndex;
    public final boolean isMediaFormatFinal;
    private DefaultTrackOutput output;

    public abstract DrmInitData getDrmInitData();

    public abstract MediaFormat getMediaFormat();

    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, int i, Format format, long j, long j2, int i2, boolean z, int i3) {
        super(dataSource, dataSpec, i, format, j, j2, i2, i3);
        this.isMediaFormatFinal = z;
    }

    public void init(DefaultTrackOutput defaultTrackOutput) {
        this.output = defaultTrackOutput;
        this.firstSampleIndex = defaultTrackOutput.getWriteIndex();
    }

    public final int getFirstSampleIndex() {
        return this.firstSampleIndex;
    }

    protected final DefaultTrackOutput getOutput() {
        return this.output;
    }
}
