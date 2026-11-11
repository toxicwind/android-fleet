package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubtitleOffsetItemAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/SubtitleCue;", "", "startTimeMs", "", "durationMs", "text", "", "", "<init>", "(JJLjava/util/List;)V", "getStartTimeMs", "()J", "getDurationMs", "getText", "()Ljava/util/List;", "endTimeMs", "getEndTimeMs", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SubtitleCue {
    private final long durationMs;
    private final long endTimeMs;
    private final long startTimeMs;
    private final List<String> text;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SubtitleCue copy$default(SubtitleCue subtitleCue, long j, long j2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            j = subtitleCue.startTimeMs;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = subtitleCue.durationMs;
        }
        long j4 = j2;
        if ((i & 4) != 0) {
            list = subtitleCue.text;
        }
        return subtitleCue.copy(j3, j4, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getStartTimeMs() {
        return this.startTimeMs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    public final List<String> component3() {
        return this.text;
    }

    public final SubtitleCue copy(long startTimeMs, long durationMs, List<String> text) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new SubtitleCue(startTimeMs, durationMs, text);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleCue)) {
            return false;
        }
        SubtitleCue subtitleCue = (SubtitleCue) other;
        return this.startTimeMs == subtitleCue.startTimeMs && this.durationMs == subtitleCue.durationMs && Intrinsics.areEqual(this.text, subtitleCue.text);
    }

    public int hashCode() {
        return (((NextAiring$$ExternalSyntheticBackport0.m(this.startTimeMs) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.durationMs)) * 31) + this.text.hashCode();
    }

    public String toString() {
        return "SubtitleCue(startTimeMs=" + this.startTimeMs + ", durationMs=" + this.durationMs + ", text=" + this.text + ')';
    }

    public SubtitleCue(long j, long j2, List<String> text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.startTimeMs = j;
        this.durationMs = j2;
        this.text = text;
        this.endTimeMs = j + j2;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final long getStartTimeMs() {
        return this.startTimeMs;
    }

    public final List<String> getText() {
        return this.text;
    }

    public final long getEndTimeMs() {
        return this.endTimeMs;
    }
}
