package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public final class SingleSampleMediaChunk extends BaseMediaChunk {
    private volatile int bytesLoaded;
    private volatile boolean loadCanceled;
    private final DrmInitData sampleDrmInitData;
    private final MediaFormat sampleFormat;

    public SingleSampleMediaChunk(DataSource dataSource, DataSpec dataSpec, int i, Format format, long j, long j2, int i2, MediaFormat mediaFormat, DrmInitData drmInitData, int i3) {
        super(dataSource, dataSpec, i, format, j, j2, i2, true, i3);
        this.sampleFormat = mediaFormat;
        this.sampleDrmInitData = drmInitData;
    }

    @Override // com.google.android.exoplayer.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer.chunk.BaseMediaChunk
    public MediaFormat getMediaFormat() {
        return this.sampleFormat;
    }

    @Override // com.google.android.exoplayer.chunk.BaseMediaChunk
    public DrmInitData getDrmInitData() {
        return this.sampleDrmInitData;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        try {
            this.dataSource.open(Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded));
            int iSampleData = 0;
            while (iSampleData != -1) {
                this.bytesLoaded += iSampleData;
                iSampleData = getOutput().sampleData(this.dataSource, IntCompanionObject.MAX_VALUE, true);
            }
            getOutput().sampleMetadata(this.startTimeUs, 1, this.bytesLoaded, 0, null);
        } finally {
            Util.closeQuietly(this.dataSource);
        }
    }
}
