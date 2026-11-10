package com.google.android.exoplayer.text;

import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
final class PlayableSubtitle implements Subtitle {
    private final long offsetUs;
    public final long startTimeUs;
    private final Subtitle subtitle;

    public PlayableSubtitle(Subtitle subtitle, boolean z, long j, long j2) {
        this.subtitle = subtitle;
        this.startTimeUs = j;
        this.offsetUs = (z ? j : 0L) + j2;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getEventTimeCount() {
        return this.subtitle.getEventTimeCount();
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public long getEventTime(int i) {
        return this.subtitle.getEventTime(i) == LongCompanionObject.MAX_VALUE ? LongCompanionObject.MAX_VALUE : this.subtitle.getEventTime(i) + this.offsetUs;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        return this.subtitle.getNextEventTimeIndex(j - this.offsetUs);
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public List<Cue> getCues(long j) {
        return this.subtitle.getCues(j - this.offsetUs);
    }
}
