package com.lagradost.cloudstream3.ui.player.live;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LiveManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003J\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/live/LivestreamChunk;", "", "durationMs", "", "receiveTimeMs", "<init>", "(JJ)V", "getReceiveTimeMs", "()J", "targetPosition", "getTargetPosition", "isPositionLive", "", "position", "getTimeAheadOfLive", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LivestreamChunk {
    private final long receiveTimeMs;
    private final long targetPosition;

    public LivestreamChunk(long j, long j2) {
        this.receiveTimeMs = j2;
        this.targetPosition = Math.max(0L, Math.min(j - 5000, (j / 2) - 3000));
    }

    public /* synthetic */ LivestreamChunk(long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? System.currentTimeMillis() : j2);
    }

    public final long getReceiveTimeMs() {
        return this.receiveTimeMs;
    }

    public final long getTargetPosition() {
        return this.targetPosition;
    }

    public final boolean isPositionLive(long position) {
        return position + LiveManagerKt.LIVE_MARGIN > (this.targetPosition + (System.currentTimeMillis() - this.receiveTimeMs)) - 5000;
    }

    public final long getTimeAheadOfLive(long position) {
        return position - (this.targetPosition + (System.currentTimeMillis() - this.receiveTimeMs));
    }
}
