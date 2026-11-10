package com.ju.lib.utils.system;

import b.a.a.a.a;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class SpaceInfo implements Serializable {
    public long available;
    public long availableCount;
    public long blockCount;
    public long blockSize;
    public long freeCount;
    public long total;

    public SpaceInfo() {
    }

    public long getAvailable() {
        return this.available;
    }

    public long getAvailableCount() {
        return this.availableCount;
    }

    public long getBlockCount() {
        return this.blockCount;
    }

    public long getBlockSize() {
        return this.blockSize;
    }

    public long getFreeCount() {
        return this.freeCount;
    }

    public long getTotal() {
        return this.total;
    }

    public void setAvailable(long j) {
        this.available = j;
    }

    public void setAvailableCount(long j) {
        this.availableCount = j;
    }

    public void setBlockCount(long j) {
        this.blockCount = j;
    }

    public void setBlockSize(long j) {
        this.blockSize = j;
    }

    public void setFreeCount(long j) {
        this.freeCount = j;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public String toString() {
        StringBuilder sbA = a.a("SpaceInfo{blockSize=");
        sbA.append(this.blockSize);
        sbA.append(", blockCount=");
        sbA.append(this.blockCount);
        sbA.append(", availableCount=");
        sbA.append(this.availableCount);
        sbA.append(", freeCount=");
        sbA.append(this.freeCount);
        sbA.append(", total=");
        sbA.append(this.total);
        sbA.append(", available=");
        sbA.append(this.available);
        sbA.append('}');
        return sbA.toString();
    }

    public SpaceInfo(long j, long j2, long j3, long j4) {
        this.blockSize = j;
        this.blockCount = j2;
        this.availableCount = j3;
        this.freeCount = j4;
        this.total = j2 * j;
        this.available = j * j3;
    }
}
