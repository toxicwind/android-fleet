package androidx.media3.exoplayer.text;

import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.extractor.text.CuesWithTiming;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
final class ReplacingCuesResolver implements CuesResolver {
    private final ArrayList<CuesWithTiming> cuesWithTimingList = new ArrayList<>();

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public boolean addCues(CuesWithTiming cuesWithTiming, long j) {
        Preconditions.checkArgument(cuesWithTiming.startTimeUs != C.TIME_UNSET);
        boolean z = cuesWithTiming.startTimeUs <= j && (cuesWithTiming.endTimeUs == C.TIME_UNSET || j < cuesWithTiming.endTimeUs);
        for (int size = this.cuesWithTimingList.size() - 1; size >= 0; size--) {
            long j2 = cuesWithTiming.startTimeUs;
            long j3 = this.cuesWithTimingList.get(size).startTimeUs;
            ArrayList<CuesWithTiming> arrayList = this.cuesWithTimingList;
            if (j2 >= j3) {
                arrayList.add(size + 1, cuesWithTiming);
                return z;
            }
            if (arrayList.get(size).startTimeUs <= j) {
                z = false;
            }
        }
        this.cuesWithTimingList.add(0, cuesWithTiming);
        return z;
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public ImmutableList<Cue> getCuesAtTimeUs(long j) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j);
        if (indexOfCuesStartingAfter == 0) {
            return ImmutableList.of();
        }
        CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1);
        if (cuesWithTiming.endTimeUs == C.TIME_UNSET || j < cuesWithTiming.endTimeUs) {
            return cuesWithTiming.cues;
        }
        return ImmutableList.of();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void discardCuesBeforeTimeUs(long j) {
        int indexOfCuesStartingAfter = getIndexOfCuesStartingAfter(j);
        if (indexOfCuesStartingAfter == 0) {
            return;
        }
        CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(indexOfCuesStartingAfter - 1);
        if (cuesWithTiming.endTimeUs == C.TIME_UNSET || cuesWithTiming.endTimeUs >= j) {
            indexOfCuesStartingAfter--;
        }
        this.cuesWithTimingList.subList(0, indexOfCuesStartingAfter).clear();
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getPreviousCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty() || j < this.cuesWithTimingList.get(0).startTimeUs) {
            return C.TIME_UNSET;
        }
        int i = 1;
        while (true) {
            int size = this.cuesWithTimingList.size();
            ArrayList<CuesWithTiming> arrayList = this.cuesWithTimingList;
            if (i < size) {
                long j2 = arrayList.get(i).startTimeUs;
                if (j == j2) {
                    return j2;
                }
                if (j < j2) {
                    CuesWithTiming cuesWithTiming = this.cuesWithTimingList.get(i - 1);
                    if (cuesWithTiming.endTimeUs != C.TIME_UNSET && cuesWithTiming.endTimeUs <= j) {
                        return cuesWithTiming.endTimeUs;
                    }
                    return cuesWithTiming.startTimeUs;
                }
                i++;
            } else {
                CuesWithTiming cuesWithTiming2 = (CuesWithTiming) Iterables.getLast(arrayList);
                if (cuesWithTiming2.endTimeUs == C.TIME_UNSET || j < cuesWithTiming2.endTimeUs) {
                    return cuesWithTiming2.startTimeUs;
                }
                return cuesWithTiming2.endTimeUs;
            }
        }
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public long getNextCueChangeTimeUs(long j) {
        if (this.cuesWithTimingList.isEmpty()) {
            return Long.MIN_VALUE;
        }
        if (j < this.cuesWithTimingList.get(0).startTimeUs) {
            return this.cuesWithTimingList.get(0).startTimeUs;
        }
        int i = 1;
        while (true) {
            int size = this.cuesWithTimingList.size();
            ArrayList<CuesWithTiming> arrayList = this.cuesWithTimingList;
            if (i < size) {
                CuesWithTiming cuesWithTiming = arrayList.get(i);
                if (j < cuesWithTiming.startTimeUs) {
                    CuesWithTiming cuesWithTiming2 = this.cuesWithTimingList.get(i - 1);
                    if (cuesWithTiming2.endTimeUs != C.TIME_UNSET && cuesWithTiming2.endTimeUs > j && cuesWithTiming2.endTimeUs < cuesWithTiming.startTimeUs) {
                        return cuesWithTiming2.endTimeUs;
                    }
                    return cuesWithTiming.startTimeUs;
                }
                i++;
            } else {
                CuesWithTiming cuesWithTiming3 = (CuesWithTiming) Iterables.getLast(arrayList);
                if (cuesWithTiming3.endTimeUs == C.TIME_UNSET || j >= cuesWithTiming3.endTimeUs) {
                    return Long.MIN_VALUE;
                }
                return cuesWithTiming3.endTimeUs;
            }
        }
    }

    @Override // androidx.media3.exoplayer.text.CuesResolver
    public void clear() {
        this.cuesWithTimingList.clear();
    }

    private int getIndexOfCuesStartingAfter(long j) {
        int i = 0;
        while (true) {
            int size = this.cuesWithTimingList.size();
            ArrayList<CuesWithTiming> arrayList = this.cuesWithTimingList;
            if (i >= size) {
                return arrayList.size();
            }
            if (j < arrayList.get(i).startTimeUs) {
                return i;
            }
            i++;
        }
    }
}
