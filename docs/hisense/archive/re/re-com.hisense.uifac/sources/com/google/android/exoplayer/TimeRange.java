package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Clock;

/* JADX INFO: loaded from: classes.dex */
public interface TimeRange {
    long[] getCurrentBoundsUs(long[] jArr);

    public static final class StaticTimeRange implements TimeRange {
        private final long endTimeUs;
        private final long startTimeUs;

        public StaticTimeRange(long j, long j2) {
            this.startTimeUs = j;
            this.endTimeUs = j2;
        }

        @Override // com.google.android.exoplayer.TimeRange
        public long[] getCurrentBoundsUs(long[] jArr) {
            if (jArr == null || jArr.length < 2) {
                jArr = new long[2];
            }
            jArr[0] = this.startTimeUs;
            jArr[1] = this.endTimeUs;
            return jArr;
        }

        public int hashCode() {
            return ((527 + ((int) this.startTimeUs)) * 31) + ((int) this.endTimeUs);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || StaticTimeRange.class != obj.getClass()) {
                return false;
            }
            StaticTimeRange staticTimeRange = (StaticTimeRange) obj;
            return staticTimeRange.startTimeUs == this.startTimeUs && staticTimeRange.endTimeUs == this.endTimeUs;
        }
    }

    public static final class DynamicTimeRange implements TimeRange {
        private final long bufferDepthUs;
        private final long elapsedRealtimeAtStartUs;
        private final long maxEndTimeUs;
        private final long minStartTimeUs;
        private final Clock systemClock;

        public DynamicTimeRange(long j, long j2, long j3, long j4, Clock clock) {
            this.minStartTimeUs = j;
            this.maxEndTimeUs = j2;
            this.elapsedRealtimeAtStartUs = j3;
            this.bufferDepthUs = j4;
            this.systemClock = clock;
        }

        @Override // com.google.android.exoplayer.TimeRange
        public long[] getCurrentBoundsUs(long[] jArr) {
            if (jArr == null || jArr.length < 2) {
                jArr = new long[2];
            }
            long jMin = Math.min(this.maxEndTimeUs, (this.systemClock.elapsedRealtime() * 1000) - this.elapsedRealtimeAtStartUs);
            long jMax = this.minStartTimeUs;
            long j = this.bufferDepthUs;
            if (j != -1) {
                jMax = Math.max(jMax, jMin - j);
            }
            jArr[0] = jMax;
            jArr[1] = jMin;
            return jArr;
        }

        public int hashCode() {
            return ((((((527 + ((int) this.minStartTimeUs)) * 31) + ((int) this.maxEndTimeUs)) * 31) + ((int) this.elapsedRealtimeAtStartUs)) * 31) + ((int) this.bufferDepthUs);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || DynamicTimeRange.class != obj.getClass()) {
                return false;
            }
            DynamicTimeRange dynamicTimeRange = (DynamicTimeRange) obj;
            return dynamicTimeRange.minStartTimeUs == this.minStartTimeUs && dynamicTimeRange.maxEndTimeUs == this.maxEndTimeUs && dynamicTimeRange.elapsedRealtimeAtStartUs == this.elapsedRealtimeAtStartUs && dynamicTimeRange.bufferDepthUs == this.bufferDepthUs;
        }
    }
}
