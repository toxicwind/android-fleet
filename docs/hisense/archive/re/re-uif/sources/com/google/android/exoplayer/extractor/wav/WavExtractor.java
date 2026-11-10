package com.google.android.exoplayer.extractor.wav;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.extractor.TrackOutput;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class WavExtractor implements Extractor, SeekMap {
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void release() {
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return WavHeaderReader.peek(extractorInput) != null;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = extractorOutput.track(0);
        this.wavHeader = null;
        extractorOutput.endTracks();
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public void seek() {
        this.pendingBytes = 0;
    }

    @Override // com.google.android.exoplayer.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        if (this.wavHeader == null) {
            WavHeader wavHeaderPeek = WavHeaderReader.peek(extractorInput);
            this.wavHeader = wavHeaderPeek;
            if (wavHeaderPeek == null) {
                throw new ParserException("Error initializing WavHeader. Did you sniff first?");
            }
            this.bytesPerFrame = wavHeaderPeek.getBytesPerFrame();
        }
        if (!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(extractorInput, this.wavHeader);
            this.trackOutput.format(MediaFormat.createAudioFormat(null, "audio/raw", this.wavHeader.getBitrate(), 32768, this.wavHeader.getDurationUs(), this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), null, null, this.wavHeader.getEncoding()));
            this.extractorOutput.seekMap(this);
        }
        int iSampleData = this.trackOutput.sampleData(extractorInput, 32768 - this.pendingBytes, true);
        if (iSampleData != -1) {
            this.pendingBytes += iSampleData;
        }
        int i = this.pendingBytes;
        int i2 = this.bytesPerFrame;
        int i3 = (i / i2) * i2;
        if (i3 > 0) {
            long position = extractorInput.getPosition();
            int i4 = this.pendingBytes;
            this.pendingBytes = i4 - i3;
            this.trackOutput.sampleMetadata(this.wavHeader.getTimeUs(position - ((long) i4)), 1, i3, this.pendingBytes, null);
        }
        return iSampleData == -1 ? -1 : 0;
    }

    @Override // com.google.android.exoplayer.extractor.SeekMap
    public long getPosition(long j) {
        return this.wavHeader.getPosition(j);
    }
}
