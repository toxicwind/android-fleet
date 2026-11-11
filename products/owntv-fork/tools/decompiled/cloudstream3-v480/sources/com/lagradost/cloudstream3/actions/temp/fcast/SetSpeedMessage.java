package com.lagradost.cloudstream3.actions.temp.fcast;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: Packets.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/SetSpeedMessage;", "", "speed", "", "<init>", "(D)V", "getSpeed", "()D", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SetSpeedMessage {
    private final double speed;

    public static /* synthetic */ SetSpeedMessage copy$default(SetSpeedMessage setSpeedMessage, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            d = setSpeedMessage.speed;
        }
        return setSpeedMessage.copy(d);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final double getSpeed() {
        return this.speed;
    }

    public final SetSpeedMessage copy(double speed) {
        return new SetSpeedMessage(speed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SetSpeedMessage) && Double.compare(this.speed, ((SetSpeedMessage) other).speed) == 0;
    }

    public int hashCode() {
        return NextAiring$$ExternalSyntheticBackport0.m(this.speed);
    }

    public String toString() {
        return "SetSpeedMessage(speed=" + this.speed + ')';
    }

    public SetSpeedMessage(double d) {
        this.speed = d;
    }

    public final double getSpeed() {
        return this.speed;
    }
}
