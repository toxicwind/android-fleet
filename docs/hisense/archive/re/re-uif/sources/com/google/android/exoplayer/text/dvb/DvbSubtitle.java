package com.google.android.exoplayer.text.dvb;

import android.graphics.Bitmap;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class DvbSubtitle implements Subtitle {
    private final Bitmap bmp;
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;

    public DvbSubtitle() {
        long[] jArr = {0, LongCompanionObject.MAX_VALUE};
        this.cueTimesUs = jArr;
        this.sortedCueTimesUs = jArr;
        this.bmp = null;
    }

    public DvbSubtitle(Bitmap bitmap, long j, int i) {
        long[] jArr = {j, j + (((long) i) * 1000000)};
        this.cueTimesUs = jArr;
        long[] jArrCopyOf = Arrays.copyOf(jArr, 2);
        this.sortedCueTimesUs = jArrCopyOf;
        this.bmp = bitmap;
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
        long[] jArr = this.cueTimesUs;
        Bitmap bitmap = (jArr[0] > j || j >= jArr[1]) ? null : this.bmp;
        MtkDebugLogUtil.log("InternalVobSubSubtitle", "getData timeUs = " + j + " data = " + bitmap, 16);
        if (bitmap != null) {
            return Collections.singletonList(new Cue(bitmap, true));
        }
        return Collections.emptyList();
    }
}
