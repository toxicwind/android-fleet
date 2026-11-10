package com.google.android.exoplayer.extractor.ogg;

import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
abstract class StreamReader {
    protected ExtractorOutput extractorOutput;
    protected TrackOutput trackOutput;
    protected final ParsableByteArray scratch = new ParsableByteArray(new byte[65025], 0);
    protected final OggParser oggParser = new OggParser();

    abstract int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException;

    StreamReader() {
    }

    void init(ExtractorOutput extractorOutput, TrackOutput trackOutput) {
        this.extractorOutput = extractorOutput;
        this.trackOutput = trackOutput;
    }

    void seek() {
        this.oggParser.reset();
        this.scratch.reset();
    }
}
