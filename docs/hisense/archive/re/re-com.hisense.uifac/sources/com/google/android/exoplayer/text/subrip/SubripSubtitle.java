package com.google.android.exoplayer.text.subrip;

import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class SubripSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final Cue[] cues;

    public SubripSubtitle(Cue[] cueArr, long[] jArr) {
        this.cues = cueArr;
        this.cueTimesUs = jArr;
    }

    @Override // com.google.android.exoplayer.text.Subtitle
    public int getNextEventTimeIndex(long j) {
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
        int iBinarySearchFloor = Util.binarySearchFloor(this.cueTimesUs, j, true, false);
        if (iBinarySearchFloor != -1) {
            Cue[] cueArr = this.cues;
            if (cueArr[iBinarySearchFloor] != null) {
                return Collections.singletonList(cueArr[iBinarySearchFloor]);
            }
        }
        return Collections.emptyList();
    }
}
