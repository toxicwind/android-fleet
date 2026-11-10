package com.google.android.exoplayer.text;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface Subtitle {
    List<Cue> getCues(long j);

    long getEventTime(int i);

    int getEventTimeCount();

    int getNextEventTimeIndex(long j);
}
