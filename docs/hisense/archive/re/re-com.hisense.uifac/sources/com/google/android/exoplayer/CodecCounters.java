package com.google.android.exoplayer;

/* JADX INFO: loaded from: classes.dex */
public final class CodecCounters {
    public int codecInitCount;
    public int codecReleaseCount;
    public int droppedOutputBufferCount;
    public int inputBufferCount;
    public int maxConsecutiveDroppedOutputBufferCount;
    public int outputBuffersChangedCount;
    public int outputFormatChangedCount;
    public int renderedOutputBufferCount;
    public int skippedOutputBufferCount;

    public synchronized void ensureUpdated() {
    }
}
