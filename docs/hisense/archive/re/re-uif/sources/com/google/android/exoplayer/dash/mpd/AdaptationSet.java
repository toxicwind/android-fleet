package com.google.android.exoplayer.dash.mpd;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AdaptationSet {
    public final List<ContentProtection> contentProtections;
    public final List<Representation> representations;
    public final int type;

    public boolean hasContentProtection() {
        return !this.contentProtections.isEmpty();
    }

    public AdaptationSet(int i, int i2, List<Representation> list, List<ContentProtection> list2, List<Role> list3, String str) {
        this.type = i2;
        this.representations = Collections.unmodifiableList(list);
        if (list2 == null) {
            this.contentProtections = Collections.emptyList();
        } else {
            this.contentProtections = Collections.unmodifiableList(list2);
        }
    }
}
