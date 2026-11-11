package com.lagradost.cloudstream3.actions.temp.fcast;

import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: Packets.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/VolumeUpdateMessage;", "", "generationTime", "", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "", "<init>", "(JD)V", "getGenerationTime", "()J", "getVolume", "()D", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class VolumeUpdateMessage {
    private final long generationTime;
    private final double volume;

    public static /* synthetic */ VolumeUpdateMessage copy$default(VolumeUpdateMessage volumeUpdateMessage, long j, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            j = volumeUpdateMessage.generationTime;
        }
        if ((i & 2) != 0) {
            d = volumeUpdateMessage.volume;
        }
        return volumeUpdateMessage.copy(j, d);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getGenerationTime() {
        return this.generationTime;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final double getVolume() {
        return this.volume;
    }

    public final VolumeUpdateMessage copy(long generationTime, double volume) {
        return new VolumeUpdateMessage(generationTime, volume);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VolumeUpdateMessage)) {
            return false;
        }
        VolumeUpdateMessage volumeUpdateMessage = (VolumeUpdateMessage) other;
        return this.generationTime == volumeUpdateMessage.generationTime && Double.compare(this.volume, volumeUpdateMessage.volume) == 0;
    }

    public int hashCode() {
        return (NextAiring$$ExternalSyntheticBackport0.m(this.generationTime) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.volume);
    }

    public String toString() {
        return "VolumeUpdateMessage(generationTime=" + this.generationTime + ", volume=" + this.volume + ')';
    }

    public VolumeUpdateMessage(long j, double d) {
        this.generationTime = j;
        this.volume = d;
    }

    public final long getGenerationTime() {
        return this.generationTime;
    }

    public final double getVolume() {
        return this.volume;
    }
}
