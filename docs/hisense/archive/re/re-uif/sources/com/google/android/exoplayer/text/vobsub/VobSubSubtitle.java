package com.google.android.exoplayer.text.vobsub;

import android.graphics.Bitmap;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VobSubSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;
    private List<VobSubParser.VobCue> vobCueList;

    public VobSubSubtitle(long j, long[] jArr, String str, int i, List<VobSubParser.VobCue> list) {
        this.cueTimesUs = jArr;
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        this.sortedCueTimesUs = jArrCopyOf;
        this.vobCueList = list;
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
        MtkDebugLogUtil.log("VobSubSubtitle", "getCues timeUs: " + j, 16);
        ArrayList arrayList = null;
        Bitmap bitmapCreateBitmap = null;
        for (int i = 0; i < this.vobCueList.size(); i++) {
            long[] jArr = this.cueTimesUs;
            int i2 = i * 2;
            if (jArr[i2] <= j) {
                int i3 = i2 + 1;
                if (j < jArr[i3]) {
                    MtkDebugLogUtil.log("VobSubSubtitle", "getCues " + i + " timeUs = " + this.cueTimesUs[i2] + " " + this.cueTimesUs[i3], 16);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    bitmapCreateBitmap = this.vobCueList.get(i).createBitmap();
                }
            }
            if (bitmapCreateBitmap != null) {
                return Collections.singletonList(new Cue(bitmapCreateBitmap));
            }
        }
        return Collections.emptyList();
    }
}
