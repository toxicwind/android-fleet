package com.google.android.exoplayer.text.vobsub;

import android.graphics.Bitmap;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class InternalVobSubSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final long[] sortedCueTimesUs;
    private final InternalVobSubParser.VobCue vc;

    public InternalVobSubSubtitle(InternalVobSubParser.VobCue vobCue) {
        long j = vobCue.startTimeUs;
        long[] jArr = {vobCue.startTime, vobCue.endTime};
        this.cueTimesUs = jArr;
        long[] jArrCopyOf = Arrays.copyOf(jArr, 2);
        this.sortedCueTimesUs = jArrCopyOf;
        this.vc = vobCue;
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
        Bitmap bitmapCreateBitmap = (jArr[0] > j || j >= jArr[1]) ? null : this.vc.createBitmap();
        MtkDebugLogUtil.log("InternalVobSubSubtitle", "getData timeUs = " + j + " data = " + bitmapCreateBitmap, 16);
        if (bitmapCreateBitmap != null) {
            return Collections.singletonList(new Cue(bitmapCreateBitmap));
        }
        return Collections.emptyList();
    }
}
