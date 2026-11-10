package com.google.android.exoplayer.text.pgs.internal;

import android.graphics.Bitmap;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.pgs.PgsFactory;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes.dex */
public class PgsSubtitle implements Subtitle {
    private long[] cueTimesUs;
    private PgsFactory pgsFactory;
    private long[] sortedCueTimesUs;

    public PgsSubtitle() {
        long[] jArr = {0, LongCompanionObject.MAX_VALUE};
        this.cueTimesUs = jArr;
        long[] jArrCopyOf = Arrays.copyOf(jArr, jArr.length);
        this.sortedCueTimesUs = jArrCopyOf;
        Arrays.sort(jArrCopyOf);
    }

    public PgsSubtitle(PgsFactory pgsFactory, long j) {
        this.pgsFactory = pgsFactory;
        this.cueTimesUs = new long[]{j, 0};
        MtkDebugLogUtil.log("PGSSubtitle", " start = " + this.cueTimesUs[0], 16);
        this.cueTimesUs[1] = Long.MAX_VALUE;
        MtkDebugLogUtil.log("PGSSubtitle", " end = " + this.cueTimesUs[1], 16);
        long[] jArr = this.cueTimesUs;
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
        if (this.pgsFactory == null) {
            return Collections.emptyList();
        }
        Bitmap bitmap = null;
        MtkDebugLogUtil.log("PGSSubtitle", "getCues timeUs = " + j, 16);
        long[] jArr = this.cueTimesUs;
        if (jArr[0] <= j && j < jArr[1]) {
            try {
                this.pgsFactory.decode(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            bitmap = this.pgsFactory.getBitmap().getBitmap();
            MtkDebugLogUtil.log("PGSSubtitle", "getCues bmp width = " + bitmap.getWidth() + " heigth = " + bitmap.getHeight(), 16);
        }
        if (bitmap != null) {
            return Collections.singletonList(new Cue(bitmap));
        }
        return Collections.emptyList();
    }
}
