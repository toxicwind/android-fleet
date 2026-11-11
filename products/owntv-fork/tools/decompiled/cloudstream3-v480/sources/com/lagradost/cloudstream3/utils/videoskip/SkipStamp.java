package com.lagradost.cloudstream3.utils.videoskip;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipAPI.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/utils/videoskip/SkipStamp;", "", "type", "Lcom/lagradost/cloudstream3/utils/videoskip/SkipType;", "startMs", "", "endMs", "label", "", "<init>", "(Lcom/lagradost/cloudstream3/utils/videoskip/SkipType;JJLjava/lang/String;)V", "getType", "()Lcom/lagradost/cloudstream3/utils/videoskip/SkipType;", "getStartMs", "()J", "getEndMs", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SkipStamp {
    private final long endMs;
    private final String label;
    private final long startMs;
    private final SkipType type;

    public static /* synthetic */ SkipStamp copy$default(SkipStamp skipStamp, SkipType skipType, long j, long j2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            skipType = skipStamp.type;
        }
        if ((i & 2) != 0) {
            j = skipStamp.startMs;
        }
        if ((i & 4) != 0) {
            j2 = skipStamp.endMs;
        }
        if ((i & 8) != 0) {
            str = skipStamp.label;
        }
        String str2 = str;
        return skipStamp.copy(skipType, j, j2, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SkipType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    public final SkipStamp copy(SkipType type, long startMs, long endMs, String label) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new SkipStamp(type, startMs, endMs, label);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkipStamp)) {
            return false;
        }
        SkipStamp skipStamp = (SkipStamp) other;
        return this.type == skipStamp.type && this.startMs == skipStamp.startMs && this.endMs == skipStamp.endMs && Intrinsics.areEqual(this.label, skipStamp.label);
    }

    public int hashCode() {
        int iHashCode = ((((this.type.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.startMs)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.endMs)) * 31;
        String str = this.label;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SkipStamp(type=" + this.type + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ", label=" + this.label + ')';
    }

    public SkipStamp(SkipType type, long j, long j2, String str) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.startMs = j;
        this.endMs = j2;
        this.label = str;
    }

    public /* synthetic */ SkipStamp(SkipType skipType, long j, long j2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(skipType, j, j2, (i & 8) != 0 ? null : str);
    }

    public final SkipType getType() {
        return this.type;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final long getEndMs() {
        return this.endMs;
    }

    public final String getLabel() {
        return this.label;
    }
}
