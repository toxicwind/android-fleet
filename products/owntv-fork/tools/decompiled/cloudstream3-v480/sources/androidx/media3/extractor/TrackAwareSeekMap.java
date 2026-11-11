package androidx.media3.extractor;

/* JADX INFO: loaded from: classes4.dex */
public interface TrackAwareSeekMap extends SeekMap {
    SeekMap.SeekPoints getSeekPoints(long j, int i);

    boolean isSeekable(int i);
}
