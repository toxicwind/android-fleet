package pl.edu.icm.jlargearrays;

import sun.misc.Cleaner;

/* JADX INFO: loaded from: classes.dex */
public class DoubleLargeArray extends LargeArray {
    private static final long serialVersionUID = 7436383149749497101L;
    private double[] data;

    public DoubleLargeArray(long j) {
        this(j, true);
    }

    public DoubleLargeArray(long j, boolean z) {
        this.type = LargeArrayType.DOUBLE;
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
        this.data = new double[(int) j];
    }

    public DoubleLargeArray(long j, double d) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        }
        this.length = j;
        this.isConstant = true;
        this.data = new double[]{d};
    }

    public DoubleLargeArray(double[] dArr) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8L;
        this.length = dArr.length;
        this.data = dArr;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public DoubleLargeArray clone() {
        if (this.isConstant) {
            return new DoubleLargeArray(this.length, getDouble(0L));
        }
        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0L, doubleLargeArray, 0L, this.length);
        return doubleLargeArray;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public boolean equals(Object obj) {
        return super.equals(obj) && this.data == ((DoubleLargeArray) obj).data;
    }

    @Override // pl.edu.icm.jlargearrays.LargeArray
    public int hashCode() {
        int iHashCode = super.hashCode() * 29;
        double[] dArr = this.data;
        return iHashCode + (dArr != null ? dArr.hashCode() : 0);
    }

    public final double getDouble(long j) {
        long j2 = this.ptr;
        if (j2 != 0) {
            return LargeArrayUtils.UNSAFE.getDouble(j2 + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final double[] getData() {
        return this.data;
    }

    public final void setDouble(long j, double d) {
        long j2 = this.ptr;
        if (j2 != 0) {
            LargeArrayUtils.UNSAFE.putDouble(j2 + (this.sizeof * j), d);
        } else {
            if (this.isConstant) {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
            this.data[(int) j] = d;
        }
    }
}
