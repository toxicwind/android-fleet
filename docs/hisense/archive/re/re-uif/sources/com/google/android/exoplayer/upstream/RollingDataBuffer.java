package com.google.android.exoplayer.upstream;

import android.util.Log;
import com.google.android.exoplayer.util.MtkDebugLogUtil;

/* JADX INFO: loaded from: classes.dex */
public class RollingDataBuffer {
    private int capacity;
    private byte[] data;
    private long dataPosition;
    private int dataSize;
    private int mRp;
    private int mRpBase;
    private int mWp;

    private int inc(int i, int i2, int i3) {
        int i4 = i + i2;
        return i4 >= i3 ? i4 - i3 : i4;
    }

    public RollingDataBuffer(int i) {
        Log.d("RollingDataBuffer", "capacity=" + i);
        this.data = new byte[i];
        this.capacity = i;
        reset(0L);
    }

    private void reset(long j) {
        Log.d("RollingDataBuffer", "reset() position=" + j);
        dump();
        this.mRpBase = 0;
        this.mRp = 0;
        this.mWp = 0;
        this.dataPosition = j;
        this.dataSize = 0;
    }

    public synchronized boolean tryReset(long j, int i) {
        Log.d("RollingDataBuffer", "tryReset() position=" + j);
        if (j >= this.dataPosition) {
            if (j <= this.dataPosition + ((long) this.dataSize) + 2097152) {
                return false;
            }
        }
        reset(j);
        return true;
    }

    public synchronized int read(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = (int) (j - this.dataPosition);
        int iInc = inc(this.mRpBase, i4, this.capacity);
        MtkDebugLogUtil.log("RollingDataBuffer", "+read position=" + j + " size=" + i2 + " startOffset=" + i4, 64);
        dump();
        i3 = 0;
        if (j >= this.dataPosition && j < this.dataPosition + ((long) this.dataSize)) {
            while (i3 < Math.min(i2, this.dataSize - i4)) {
                bArr[i3 + i] = this.data[iInc];
                iInc = inc(iInc, 1, this.capacity);
                this.mRp = iInc;
                i3++;
            }
        }
        MtkDebugLogUtil.log("RollingDataBuffer", "-read position=" + j + " size=" + i2 + " i=" + i3, 64);
        return i3;
    }

    public synchronized int write(byte[] bArr, int i) {
        MtkDebugLogUtil.log("RollingDataBuffer", "write size=" + i, 64);
        dump();
        if (i >= this.capacity - this.dataSize) {
            this.mRpBase = inc(this.mRpBase, i, this.capacity);
            this.dataPosition += (long) i;
            this.dataSize -= i;
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.data[this.mWp] = bArr[i2];
            this.mWp = inc(this.mWp, 1, this.capacity);
            this.dataSize++;
        }
        return i;
    }

    public synchronized boolean isFull() {
        int i;
        int i2;
        if (this.mWp >= this.mRp) {
            i = this.mWp;
            i2 = this.mRp;
        } else {
            i = this.mWp + this.capacity;
            i2 = this.mRp;
        }
        return i - i2 >= 10485760;
    }

    public void dump() {
        MtkDebugLogUtil.log("RollingDataBuffer", "mRpBase=" + this.mRpBase, 64);
        MtkDebugLogUtil.log("RollingDataBuffer", "mRp=" + this.mRp, 64);
        MtkDebugLogUtil.log("RollingDataBuffer", "mWp=" + this.mWp, 64);
        MtkDebugLogUtil.log("RollingDataBuffer", "data=" + this.data, 64);
        MtkDebugLogUtil.log("RollingDataBuffer", "capacity=" + this.capacity, 64);
        MtkDebugLogUtil.log("RollingDataBuffer", "dataPosition=" + this.dataPosition, 64);
        MtkDebugLogUtil.log("RollingDataBuffer", "dataSize=" + this.dataSize, 64);
    }
}
