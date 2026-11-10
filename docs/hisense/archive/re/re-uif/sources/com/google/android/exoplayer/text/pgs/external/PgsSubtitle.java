package com.google.android.exoplayer.text.pgs.external;

import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.pgs.PgsFactory;
import com.google.android.exoplayer.text.pgs.SubPictureBD;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MtkDebugLogUtil;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PgsSubtitle implements Subtitle {
    private int counts;
    private final long[] cueTimesUs;
    private PgsFactory sb;
    private final long[] sortedCueTimesUs;
    private List<SubPictureBD> subPictures;

    public PgsSubtitle(PgsFactory pgsFactory, long j) {
        this.sb = pgsFactory;
        this.subPictures = pgsFactory.getSubPictureList();
        int size = pgsFactory.getSubPictureList().size();
        this.counts = size;
        this.cueTimesUs = new long[size * 2];
        int i = 0;
        for (SubPictureBD subPictureBD : this.subPictures) {
            if (subPictureBD.getStartTime() == 0) {
                this.cueTimesUs[i * 2] = j;
            } else {
                this.cueTimesUs[i * 2] = (subPictureBD.getStartTime() * 1000) / 90;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("time ");
            sb.append(i);
            sb.append(" start = ");
            int i2 = i * 2;
            sb.append(this.cueTimesUs[i2]);
            MtkDebugLogUtil.log("PGSSubtitle", sb.toString(), 16);
            if (subPictureBD.getStartTime() == 0) {
                this.cueTimesUs[i2 + 1] = j + 1000000;
            } else {
                this.cueTimesUs[i2 + 1] = (subPictureBD.getEndTime() * 1000) / 90;
            }
            MtkDebugLogUtil.log("PGSSubtitle", "time " + i + " end = " + this.cueTimesUs[i2 + 1], 16);
            i++;
        }
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
        MtkDebugLogUtil.log("PGSSubtitle", "getCues timeUs = " + j, 16);
        ArrayList arrayList = null;
        for (int i = 0; i < this.counts; i++) {
            long[] jArr = this.cueTimesUs;
            int i2 = i * 2;
            if (jArr[i2] <= j) {
                int i3 = i2 + 1;
                if (j < jArr[i3]) {
                    MtkDebugLogUtil.log("PGSSubtitle", "getCues " + i + "cueTimesUs[i * 2] = " + this.cueTimesUs[i2] + " cueTimesUs[i * 2 + 1]) = " + this.cueTimesUs[i3], 16);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    try {
                        this.sb.decode(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    arrayList.add(new Cue(this.sb.getBitmap().getBitmap()));
                }
            }
        }
        return arrayList != null ? arrayList : Collections.emptyList();
    }
}
