package com.google.android.exoplayer.text;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class DefaultSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final List<Cue> cues;
    private final int numCues;
    private final long[] sortedCueTimesUs;

    public DefaultSubtitle() {
        this.cues = null;
        this.numCues = 0;
        long[] jArr = {0, LongCompanionObject.MAX_VALUE};
        this.cueTimesUs = jArr;
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    public DefaultSubtitle(String[] strArr, long j, long[] jArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(new Cue(str));
        }
        this.cues = arrayList;
        this.numCues = arrayList.size();
        this.cueTimesUs = jArr;
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    public DefaultSubtitle(String str, long j, long j2) {
        List<Cue> listSingletonList = Collections.singletonList(new Cue(str));
        this.cues = listSingletonList;
        int size = listSingletonList.size();
        this.numCues = size;
        long[] jArr = new long[size * 2];
        this.cueTimesUs = jArr;
        jArr[0] = j;
        jArr[1] = j2;
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
        Assertions.checkArgument(j >= 0);
        int iBinarySearchCeil = Util.binarySearchCeil(this.sortedCueTimesUs, j, false, false);
        if (iBinarySearchCeil < this.sortedCueTimesUs.length) {
            return iBinarySearchCeil;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getEventTimeCount() {
        return this.sortedCueTimesUs.length;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public long getEventTime(int i) {
        Assertions.checkArgument(i >= 0);
        Assertions.checkArgument(i < this.sortedCueTimesUs.length);
        return this.sortedCueTimesUs[i];
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public List<Cue> getCues(long j) {
        ArrayList arrayList = null;
        if (this.numCues > 0) {
            for (int i = 0; i < this.numCues; i++) {
                long[] jArr = this.cueTimesUs;
                int i2 = i * 2;
                if (jArr[i2] <= j && j < jArr[i2 + 1]) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(this.cues.get(i));
                }
            }
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }
}
