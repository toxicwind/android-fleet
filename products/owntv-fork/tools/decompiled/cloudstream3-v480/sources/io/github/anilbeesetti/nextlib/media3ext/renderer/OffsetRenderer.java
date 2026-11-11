package io.github.anilbeesetti.nextlib.media3ext.renderer;

import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: OffsetRenderer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lio/github/anilbeesetti/nextlib/media3ext/renderer/OffsetRenderer;", "", "<init>", "()V", "syncOffsetMilliseconds", "", "getSyncOffsetMilliseconds", "()J", "setSyncOffsetMilliseconds", "(J)V", ES6Iterator.VALUE_PROPERTY, "", "syncSpeedMultiplier", "getSyncSpeedMultiplier", "()F", "setSyncSpeedMultiplier", "(F)V", "getOffsetAdjustedPositionUs", "positionUs", "media3ext"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class OffsetRenderer {
    private long syncOffsetMilliseconds;
    private float syncSpeedMultiplier = 1.0f;

    public final long getSyncOffsetMilliseconds() {
        return this.syncOffsetMilliseconds;
    }

    public final void setSyncOffsetMilliseconds(long j) {
        this.syncOffsetMilliseconds = j;
    }

    public final float getSyncSpeedMultiplier() {
        return this.syncSpeedMultiplier;
    }

    public final void setSyncSpeedMultiplier(float f) {
        this.syncSpeedMultiplier = RangesKt.coerceIn(f, 0.1f, 10.0f);
    }

    public final long getOffsetAdjustedPositionUs(long positionUs) {
        return ((long) (positionUs * this.syncSpeedMultiplier)) - (this.syncOffsetMilliseconds * 1000);
    }
}
