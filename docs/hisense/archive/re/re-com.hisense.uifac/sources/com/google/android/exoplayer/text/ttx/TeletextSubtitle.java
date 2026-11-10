package com.google.android.exoplayer.text.ttx;

import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class TeletextSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final String txt;

    public TeletextSubtitle() {
        this.txt = "";
        this.cueTimesUs = new long[]{0, LongCompanionObject.MAX_VALUE};
    }

    public TeletextSubtitle(String str) {
        this.txt = str;
        this.cueTimesUs = new long[]{0, LongCompanionObject.MAX_VALUE};
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        Assertions.checkArgument(j >= 0);
        int iBinarySearchCeil = Util.binarySearchCeil(this.cueTimesUs, j, false, false);
        if (iBinarySearchCeil < this.cueTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getEventTimeCount() {
        return this.cueTimesUs.length;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public long getEventTime(int i) {
        Assertions.checkArgument(i >= 0);
        Assertions.checkArgument(i < this.cueTimesUs.length);
        return this.cueTimesUs[i];
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public List<Cue> getCues(long j) {
        ArrayList arrayList;
        long[] jArr = this.cueTimesUs;
        if (jArr[0] > j || j >= jArr[1]) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            arrayList.add(new Cue(this.txt));
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }
}
