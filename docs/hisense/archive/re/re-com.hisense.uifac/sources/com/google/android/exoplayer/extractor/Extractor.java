package com.google.android.exoplayer.extractor;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface Extractor {
    void init(ExtractorOutput extractorOutput);

    int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException;

    void release();

    void seek();

    boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException;
}
