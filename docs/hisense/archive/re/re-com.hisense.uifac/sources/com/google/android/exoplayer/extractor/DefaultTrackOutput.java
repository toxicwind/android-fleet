package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DefaultTrackOutput implements TrackOutput {
    private volatile MediaFormat format;
    private final RollingSampleBuffer rollingBuffer;
    private final SampleHolder sampleInfoHolder = new SampleHolder(0);
    private boolean needKeyframe = true;
    private long lastReadTimeUs = Long.MIN_VALUE;
    private long spliceOutTimeUs = Long.MIN_VALUE;
    private volatile long largestParsedTimestampUs = Long.MIN_VALUE;

    public DefaultTrackOutput(Allocator allocator) {
        this.rollingBuffer = new RollingSampleBuffer(allocator);
    }

    public void clear() {
        this.rollingBuffer.clear();
        this.needKeyframe = true;
        this.lastReadTimeUs = Long.MIN_VALUE;
        this.spliceOutTimeUs = Long.MIN_VALUE;
        this.largestParsedTimestampUs = Long.MIN_VALUE;
    }

    public int getWriteIndex() {
        return this.rollingBuffer.getWriteIndex();
    }

    public void discardUpstreamSamples(int i) {
        this.rollingBuffer.discardUpstreamSamples(i);
        this.largestParsedTimestampUs = this.rollingBuffer.peekSample(this.sampleInfoHolder) ? this.sampleInfoHolder.timeUs : Long.MIN_VALUE;
    }

    public int getReadIndex() {
        return this.rollingBuffer.getReadIndex();
    }

    public boolean hasFormat() {
        return this.format != null;
    }

    public MediaFormat getFormat() {
        return this.format;
    }

    public long getLargestParsedTimestampUs() {
        return this.largestParsedTimestampUs;
    }

    public boolean isEmpty() {
        return !advanceToEligibleSample();
    }

    public boolean getSample(SampleHolder sampleHolder) {
        if (!advanceToEligibleSample()) {
            return false;
        }
        this.rollingBuffer.readSample(sampleHolder);
        this.needKeyframe = false;
        if (sampleHolder == null) {
            return true;
        }
        this.lastReadTimeUs = sampleHolder.timeUs;
        return true;
    }

    public void discardUntil(long j) {
        while (this.rollingBuffer.peekSample(this.sampleInfoHolder) && this.sampleInfoHolder.timeUs < j) {
            this.rollingBuffer.skipSample();
            this.needKeyframe = true;
        }
        this.lastReadTimeUs = Long.MIN_VALUE;
    }

    public boolean skipToKeyframeBefore(long j) {
        return this.rollingBuffer.skipToKeyframeBefore(j);
    }

    public boolean configureSpliceTo(DefaultTrackOutput defaultTrackOutput) {
        long j;
        if (this.spliceOutTimeUs != Long.MIN_VALUE) {
            return true;
        }
        if (this.rollingBuffer.peekSample(this.sampleInfoHolder)) {
            j = this.sampleInfoHolder.timeUs;
        } else {
            j = this.lastReadTimeUs + 1;
        }
        RollingSampleBuffer rollingSampleBuffer = defaultTrackOutput.rollingBuffer;
        while (rollingSampleBuffer.peekSample(this.sampleInfoHolder)) {
            SampleHolder sampleHolder = this.sampleInfoHolder;
            if (sampleHolder.timeUs >= j && sampleHolder.isSyncFrame()) {
                break;
            }
            rollingSampleBuffer.skipSample();
        }
        if (!rollingSampleBuffer.peekSample(this.sampleInfoHolder)) {
            return false;
        }
        this.spliceOutTimeUs = this.sampleInfoHolder.timeUs;
        return true;
    }

    private boolean advanceToEligibleSample() {
        boolean zPeekSample = this.rollingBuffer.peekSample(this.sampleInfoHolder);
        if (this.needKeyframe) {
            while (zPeekSample && !this.sampleInfoHolder.isSyncFrame()) {
                this.rollingBuffer.skipSample();
                zPeekSample = this.rollingBuffer.peekSample(this.sampleInfoHolder);
            }
        }
        if (!zPeekSample) {
            return false;
        }
        long j = this.spliceOutTimeUs;
        return j == Long.MIN_VALUE || this.sampleInfoHolder.timeUs < j;
    }

    public int sampleData(DataSource dataSource, int i, boolean z) throws IOException {
        return this.rollingBuffer.appendData(dataSource, i, z);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void format(MediaFormat mediaFormat) {
        this.format = mediaFormat;
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
        return this.rollingBuffer.appendData(extractorInput, i, z);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        this.rollingBuffer.appendData(parsableByteArray, i);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void sampleMetadata(long j, int i, int i2, int i3, byte[] bArr) {
        this.largestParsedTimestampUs = Math.max(this.largestParsedTimestampUs, j);
        RollingSampleBuffer rollingSampleBuffer = this.rollingBuffer;
        rollingSampleBuffer.commitSample(j, i, (rollingSampleBuffer.getWritePosition() - ((long) i2)) - ((long) i3), i2, bArr);
    }

    public boolean getCurrentSampleInfo(SampleHolder sampleHolder) {
        if (sampleHolder == null) {
            return false;
        }
        return this.rollingBuffer.peekSample(sampleHolder);
    }
}
