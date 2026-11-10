package com.google.android.exoplayer.text.webvtt;

import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class Mp4WebvttSubtitle implements Subtitle {
    private final List<Cue> cues;

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getEventTimeCount() {
        return 1;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        return j < 0 ? 0 : -1;
    }

    public Mp4WebvttSubtitle(List<Cue> list) {
        this.cues = Collections.unmodifiableList(list);
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
