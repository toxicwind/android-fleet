package com.lagradost.cloudstream3.actions.temp.fcast;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: Packets.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/PlaybackUpdateMessage;", "", "generationTime", "", "time", "", TypedValues.TransitionType.S_DURATION, "state", "", "speed", "<init>", "(JDDID)V", "getGenerationTime", "()J", "getTime", "()D", "getDuration", "getState", "()I", "getSpeed", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlaybackUpdateMessage {
    private final double duration;
    private final long generationTime;
    private final double speed;
    private final int state;
    private final double time;

    public static /* synthetic */ PlaybackUpdateMessage copy$default(PlaybackUpdateMessage playbackUpdateMessage, long j, double d, double d2, int i, double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = playbackUpdateMessage.generationTime;
        }
        long j2 = j;
        if ((i2 & 2) != 0) {
            d = playbackUpdateMessage.time;
        }
        return playbackUpdateMessage.copy(j2, d, (i2 & 4) != 0 ? playbackUpdateMessage.duration : d2, (i2 & 8) != 0 ? playbackUpdateMessage.state : i, (i2 & 16) != 0 ? playbackUpdateMessage.speed : d3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getGenerationTime() {
        return this.generationTime;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final double getTime() {
        return this.time;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getState() {
        return this.state;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final double getSpeed() {
        return this.speed;
    }

    public final PlaybackUpdateMessage copy(long generationTime, double time, double duration, int state, double speed) {
        return new PlaybackUpdateMessage(generationTime, time, duration, state, speed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackUpdateMessage)) {
            return false;
        }
        PlaybackUpdateMessage playbackUpdateMessage = (PlaybackUpdateMessage) other;
        return this.generationTime == playbackUpdateMessage.generationTime && Double.compare(this.time, playbackUpdateMessage.time) == 0 && Double.compare(this.duration, playbackUpdateMessage.duration) == 0 && this.state == playbackUpdateMessage.state && Double.compare(this.speed, playbackUpdateMessage.speed) == 0;
    }

    public int hashCode() {
        return (((((((NextAiring$$ExternalSyntheticBackport0.m(this.generationTime) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.time)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.duration)) * 31) + this.state) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.speed);
    }

    public String toString() {
        return "PlaybackUpdateMessage(generationTime=" + this.generationTime + ", time=" + this.time + ", duration=" + this.duration + ", state=" + this.state + ", speed=" + this.speed + ')';
    }

    public PlaybackUpdateMessage(long j, double d, double d2, int i, double d3) {
        this.generationTime = j;
        this.time = d;
        this.duration = d2;
        this.state = i;
        this.speed = d3;
    }

    public final long getGenerationTime() {
        return this.generationTime;
    }

    public final double getTime() {
        return this.time;
    }

    public final double getDuration() {
        return this.duration;
    }

    public final int getState() {
        return this.state;
    }

    public final double getSpeed() {
        return this.speed;
    }
}
