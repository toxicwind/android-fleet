package androidx.media3.exoplayer.upstream.experimental;

/* JADX INFO: loaded from: classes4.dex */
public interface BandwidthStatistic {
    void addSample(long j, long j2);

    long getBandwidthEstimate();

    void reset();
}
