package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Period {
    public final List<AdaptationSet> adaptationSets;
    public final long startMs;

    public Period(String str, long j, List<AdaptationSet> list) {
        this.startMs = j;
        this.adaptationSets = Collections.unmodifiableList(list);
    }
}
