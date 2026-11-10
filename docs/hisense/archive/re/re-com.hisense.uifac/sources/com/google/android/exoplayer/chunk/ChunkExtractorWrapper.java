package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ChunkExtractorWrapper implements ExtractorOutput, TrackOutput {
    private final Extractor extractor;
    private boolean extractorInitialized;
    private SingleTrackOutput output;
    private boolean seenTrack;
    private int seenTrackId;

    public interface SingleTrackOutput extends TrackOutput {
        void drmInitData(DrmInitData drmInitData);

        void seekMap(SeekMap seekMap);
    }

    public ChunkExtractorWrapper(Extractor extractor) {
        this.extractor = extractor;
    }

    public void init(SingleTrackOutput singleTrackOutput) {
        this.output = singleTrackOutput;
        if (!this.extractorInitialized) {
            this.extractor.init(this);
            this.extractorInitialized = true;
        } else {
            this.extractor.seek();
        }
    }

    public int read(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i = this.extractor.read(extractorInput, null);
        Assertions.checkState(i != 1);
        return i;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public TrackOutput track(int i) {
        Assertions.checkState(!this.seenTrack || i == this.seenTrackId);
        this.seenTrack = true;
        this.seenTrackId = i;
        return this;
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void endTracks() {
        Assertions.checkState(this.seenTrack);
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.output.seekMap(seekMap);
    }

    @Override // com.google.android.exoplayer.extractor.ExtractorOutput
    public void drmInitData(DrmInitData drmInitData) {
        this.output.drmInitData(drmInitData);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void format(MediaFormat mediaFormat) {
        this.output.format(mediaFormat);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
        return this.output.sampleData(extractorInput, i, z);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void sampleData(ParsableByteArray parsableByteArray, int i) {
        this.output.sampleData(parsableByteArray, i);
    }

    @Override // com.google.android.exoplayer.extractor.TrackOutput
    public void sampleMetadata(long j, int i, int i2, int i3, byte[] bArr) {
        this.output.sampleMetadata(j, i, i2, i3, bArr);
    }

    public void setDashInitStage(boolean z) {
        Extractor extractor = this.extractor;
        if (extractor instanceof FragmentedMp4Extractor) {
            ((FragmentedMp4Extractor) extractor).setDashInitStage(z);
        }
    }

    public Extractor getExtractor() {
        return this.extractor;
    }
}
