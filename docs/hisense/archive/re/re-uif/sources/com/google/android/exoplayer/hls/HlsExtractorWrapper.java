package com.google.android.exoplayer.hls;

import android.util.SparseArray;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.DefaultTrackOutput;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class HlsExtractorWrapper implements ExtractorOutput {
    private final int adaptiveMaxHeight;
    private final int adaptiveMaxWidth;
    private Allocator allocator;
    private final Extractor extractor;
    public final Format format;
    private boolean prepared;
    private MediaFormat[] sampleQueueFormats;
    private final SparseArray<DefaultTrackOutput> sampleQueues = new SparseArray<>();
    private final boolean shouldSpliceIn;
    private boolean spliceConfigured;
    public final long startTimeUs;
    private volatile boolean tracksBuilt;
    public final int trigger;

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void drmInitData(DrmInitData drmInitData) {
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
    }

    public HlsExtractorWrapper(int i, Format format, long j, Extractor extractor, boolean z, int i2, int i3) {
        this.trigger = i;
        this.format = format;
        this.startTimeUs = j;
        this.extractor = extractor;
        this.shouldSpliceIn = z;
        this.adaptiveMaxWidth = i2;
        this.adaptiveMaxHeight = i3;
    }

    public void init(Allocator allocator) {
        this.allocator = allocator;
        this.extractor.init(this);
    }

    public boolean isPrepared() {
        if (!this.prepared && this.tracksBuilt) {
            for (int i = 0; i < this.sampleQueues.size(); i++) {
                if (!this.sampleQueues.valueAt(i).hasFormat()) {
                    return false;
                }
            }
            this.prepared = true;
            this.sampleQueueFormats = new MediaFormat[this.sampleQueues.size()];
            for (int i2 = 0; i2 < this.sampleQueueFormats.length; i2++) {
                MediaFormat format = this.sampleQueues.valueAt(i2).getFormat();
                if (MimeTypes.isVideo(format.mimeType) && (this.adaptiveMaxWidth != -1 || this.adaptiveMaxHeight != -1)) {
                    format = format.copyWithMaxVideoDimensions(this.adaptiveMaxWidth, this.adaptiveMaxHeight);
                }
                this.sampleQueueFormats[i2] = format;
            }
        }
        return this.prepared;
    }

    public void clear() {
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            this.sampleQueues.valueAt(i).clear();
        }
    }

    public long getLargestParsedTimestampUs() {
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            jMax = Math.max(jMax, this.sampleQueues.valueAt(i).getLargestParsedTimestampUs());
        }
        return jMax;
    }

    public final void configureSpliceTo(HlsExtractorWrapper hlsExtractorWrapper) {
        Assertions.checkState(isPrepared());
        if (!this.spliceConfigured && hlsExtractorWrapper.shouldSpliceIn && hlsExtractorWrapper.isPrepared()) {
            int trackCount = getTrackCount();
            boolean zConfigureSpliceTo = true;
            for (int i = 0; i < trackCount; i++) {
                DefaultTrackOutput defaultTrackOutputValueAt = this.sampleQueues.valueAt(i);
                DefaultTrackOutput defaultTrackOutputValueAt2 = hlsExtractorWrapper.sampleQueues.valueAt(i);
                zConfigureSpliceTo = defaultTrackOutputValueAt2 != null ? zConfigureSpliceTo & defaultTrackOutputValueAt.configureSpliceTo(defaultTrackOutputValueAt2) : false;
            }
            this.spliceConfigured = zConfigureSpliceTo;
        }
    }

    public int getTrackCount() {
        Assertions.checkState(isPrepared());
        return this.sampleQueues.size();
    }

    public MediaFormat getMediaFormat(int i) {
        Assertions.checkState(isPrepared());
        return this.sampleQueueFormats[i];
    }

    public boolean getSample(int i, SampleHolder sampleHolder) {
        Assertions.checkState(isPrepared());
        return this.sampleQueues.valueAt(i).getSample(sampleHolder);
    }

    public void discardUntil(int i, long j) {
        Assertions.checkState(isPrepared());
        this.sampleQueues.valueAt(i).discardUntil(j);
    }

    public boolean hasSamples(int i) {
        Assertions.checkState(isPrepared());
        return !this.sampleQueues.valueAt(i).isEmpty();
    }

    public int read(ExtractorInput extractorInput) throws InterruptedException, IOException {
        Assertions.checkState(extractorInput != null);
        int i = this.extractor.read(extractorInput, null);
        Assertions.checkState(i != 1);
        return i;
    }

    public long getAdjustedEndTimeUs() {
        long jMax = Long.MIN_VALUE;
        for (int i = 0; i < this.sampleQueues.size(); i++) {
            jMax = Math.max(jMax, this.sampleQueues.valueAt(i).getLargestParsedTimestampUs());
        }
        return jMax;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public TrackOutput track(int i) {
        DefaultTrackOutput defaultTrackOutput = this.sampleQueues.get(i);
        if (defaultTrackOutput != null) {
            return defaultTrackOutput;
        }
        DefaultTrackOutput defaultTrackOutput2 = new DefaultTrackOutput(this.allocator);
        this.sampleQueues.put(i, defaultTrackOutput2);
        return defaultTrackOutput2;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void endTracks() {
        this.tracksBuilt = true;
    }
}
