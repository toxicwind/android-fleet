package com.google.android.exoplayer.upstream;

import android.os.Handler;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Clock;
import com.google.android.exoplayer.util.SlidingPercentile;
import com.google.android.exoplayer.util.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultBandwidthMeter implements BandwidthMeter {
    private long bitrateEstimate;
    private long bytesAccumulator;
    private final Clock clock;
    private final Handler eventHandler;
    private final BandwidthMeter.EventListener eventListener;
    private final SlidingPercentile slidingPercentile;
    private long startTimeMs;
    private int streamCount;

    public DefaultBandwidthMeter() {
        this(null, null);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener) {
        this(handler, eventListener, new SystemClock());
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener, Clock clock) {
        this(handler, eventListener, clock, 2000);
    }

    public DefaultBandwidthMeter(Handler handler, BandwidthMeter.EventListener eventListener, Clock clock, int i) {
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.clock = clock;
        this.slidingPercentile = new SlidingPercentile(i);
        this.bitrateEstimate = -1L;
    }

    @Override // com.google.android.exoplayer.upstream.BandwidthMeter
    public synchronized long getBitrateEstimate() {
        return this.bitrateEstimate;
    }

    @Override // com.google.android.exoplayer.upstream.TransferListener
    public synchronized void onTransferStart() {
        if (this.streamCount == 0) {
            this.startTimeMs = this.clock.elapsedRealtime();
        }
        this.streamCount++;
    }

    @Override // com.google.android.exoplayer.upstream.TransferListener
    public synchronized void onBytesTransferred(int i) {
        this.bytesAccumulator += (long) i;
    }

    @Override // com.google.android.exoplayer.upstream.TransferListener
    public synchronized void onTransferEnd() {
        Assertions.checkState(this.streamCount > 0);
        long jElapsedRealtime = this.clock.elapsedRealtime();
        int i = (int) (jElapsedRealtime - this.startTimeMs);
        if (i > 0) {
            this.slidingPercentile.addSample((int) Math.sqrt(this.bytesAccumulator), (this.bytesAccumulator * 8000) / ((long) i));
            float percentile = this.slidingPercentile.getPercentile(0.5f);
            long j = Float.isNaN(percentile) ? -1L : (long) percentile;
            this.bitrateEstimate = j;
            notifyBandwidthSample(i, this.bytesAccumulator, j);
        }
        int i2 = this.streamCount - 1;
        this.streamCount = i2;
        if (i2 > 0) {
            this.startTimeMs = jElapsedRealtime;
        }
        this.bytesAccumulator = 0L;
    }

    private void notifyBandwidthSample(final int i, final long j, final long j2) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer.upstream.DefaultBandwidthMeter.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultBandwidthMeter.this.eventListener.onBandwidthSample(i, j, j2);
            }
        });
    }
}
