package com.lagradost.cloudstream3.actions.temp.fcast;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;

/* JADX INFO: compiled from: Packets.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/VersionMessage;", "", "version", "", "<init>", "(J)V", "getVersion", "()J", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class VersionMessage {
    private final long version;

    public static /* synthetic */ VersionMessage copy$default(VersionMessage versionMessage, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = versionMessage.version;
        }
        return versionMessage.copy(j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getVersion() {
        return this.version;
    }

    public final VersionMessage copy(long version) {
        return new VersionMessage(version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof VersionMessage) && this.version == ((VersionMessage) other).version;
    }

    public int hashCode() {
        return NextAiring$$ExternalSyntheticBackport0.m(this.version);
    }

    public String toString() {
        return "VersionMessage(version=" + this.version + ')';
    }

    public VersionMessage(long j) {
        this.version = j;
    }

    public final long getVersion() {
        return this.version;
    }
}
