package pl.edu.icm.jlargearrays;

import sun.misc.Cleaner;

/* JADX INFO: loaded from: classes.dex */
public class LongLargeArray extends LargeArray {
    private static final long serialVersionUID = -2579271120060523901L;
    private long[] data;

    public LongLargeArray(long j) {
        this(j, true);
    }

    public LongLargeArray(long j, boolean z) {
        this.type = LargeArrayType.LONG;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        if (j > LargeArray.getMaxSizeOf32bitArray()) {
            this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.length * this.sizeof);
            if (z) {
                zeroNativeMemory(j);
            }
            Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.length, this.sizeof));
            MemoryCounter.increaseCounter(this.length * this.sizeof);
            return;
        }
        this.data = new long[(int) j];
    }

    public LongLargeArray(long j, long j2) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.isConstant = true;
        this.data = new long[]{j2};
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public LongLargeArray clone() {
        if (this.isConstant) {
            return new LongLargeArray(this.length, getLong(0L));
        }
        LongLargeArray longLargeArray = new LongLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0L, longLargeArray, 0L, this.length);
        return longLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        return super.equals(obj) && this.data == ((LongLargeArray) obj).data;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        long[] jArr = this.data;
        return iHashCode + (jArr != null ? jArr.hashCode() : 0);
    }

    public final long getLong(long j) {
        long j2 = this.ptr;
        if (j2 != 0) {
            return LargeArrayUtils.UNSAFE.getLong(j2 + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final void setLong(long j, long j2) {
        long j3 = this.ptr;
        if (j3 != 0) {
            LargeArrayUtils.UNSAFE.putLong(j3 + (this.sizeof * j), j2);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = j2;
        }
    }
}
