package com.google.android.exoplayer.text.tx3g;

import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class Tx3gSubtitle implements Subtitle {
    public static final Tx3gSubtitle EMPTY = new Tx3gSubtitle();
    private final List<Cue> cues;

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getEventTimeCount() {
        return 1;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        return j < 0 ? 0 : -1;
    }

    public Tx3gSubtitle(Cue cue) {
        this.cues = Collections.singletonList(cue);
    }

    private Tx3gSubtitle() {
        this.cues = Collections.emptyList();
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public long getEventTime(int i) {
        Assertions.checkArgument(i == 0);
        return 0L;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public List<Cue> getCues(long j) {
        return j >= 0 ? this.cues : Collections.emptyList();
    }
}
