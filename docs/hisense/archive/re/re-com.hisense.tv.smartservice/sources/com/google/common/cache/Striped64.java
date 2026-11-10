package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Random;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class Striped64 extends Number {
    public static final Unsafe UNSAFE;
    public static final long baseOffset;
    public static final long busyOffset;
    public volatile transient long base;
    public volatile transient int busy;
    public volatile transient Cell[] cells;
    public static final ThreadLocal<int[]> threadHashCode = new ThreadLocal<>();
    public static final Random rng = new Random();
    public static final int NCPU = Runtime.getRuntime().availableProcessors();

    static final class Cell {
        public static final Unsafe UNSAFE;
        public static final long valueOffset;
        public volatile long p0;
        public volatile long p1;
        public volatile long p2;
        public volatile long p3;
        public volatile long p4;
        public volatile long p5;
        public volatile long p6;
        public volatile long q0;
        public volatile long q1;
        public volatile long q2;
        public volatile long q3;
        public volatile long q4;
        public volatile long q5;
        public volatile long q6;
        public volatile long value;

        static {
            try {
                UNSAFE = Striped64.getUnsafe();
                valueOffset = UNSAFE.objectFieldOffset(Cell.class.getDeclaredField("value"));
            } catch (Exception e2) {
                throw new Error(e2);
            }
        }

        public Cell(long j) {
            this.value = j;
        }

        public final boolean cas(long j, long j2) {
            return UNSAFE.compareAndSwapLong(this, valueOffset, j, j2);
        }
    }

    static {
        try {
            UNSAFE = getUnsafe();
            baseOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("base"));
            busyOffset = UNSAFE.objectFieldOffset(Striped64.class.getDeclaredField("busy"));
        } catch (Exception e2) {
            throw new Error(e2);
        }
    }

    public static Unsafe getUnsafe() {
        try {
            try {
                return Unsafe.getUnsafe();
            } catch (PrivilegedActionException e2) {
                throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
            }
        } catch (SecurityException unused) {
            return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.cache.Striped64.1
                @Override // java.security.PrivilegedExceptionAction
                public Unsafe run() throws IllegalAccessException {
                    for (Field field : Unsafe.class.getDeclaredFields()) {
                        field.setAccessible(true);
                        Object obj = field.get(null);
                        if (Unsafe.class.isInstance(obj)) {
                            return (Unsafe) Unsafe.class.cast(obj);
                        }
                    }
                    throw new NoSuchFieldError("the Unsafe");
                }
            });
        }
    }

    public final boolean casBase(long j, long j2) {
        return UNSAFE.compareAndSwapLong(this, baseOffset, j, j2);
    }

    public final boolean casBusy() {
        return UNSAFE.compareAndSwapInt(this, busyOffset, 0, 1);
    }

    public abstract long fn(long j, long j2);

    public final void internalReset(long j) {
        Cell[] cellArr = this.cells;
        this.base = j;
        if (cellArr != null) {
            for (Cell cell : cellArr) {
                if (cell != null) {
                    cell.value = j;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0055  */
    public final void retryUpdate(long j, int[] iArr, boolean z) {
        int iNextInt;
        int[] iArr2;
        Cell[] cellArr;
        boolean z2;
        int length;
        boolean z3;
        int length2;
        if (iArr == null) {
            iArr2 = new int[1];
            threadHashCode.set(iArr2);
            iNextInt = rng.nextInt();
            if (iNextInt == 0) {
                iNextInt = 1;
            }
            iArr2[0] = iNextInt;
        } else {
            iNextInt = iArr[0];
            iArr2 = iArr;
        }
        boolean z4 = z;
        while (true) {
            boolean z5 = false;
            while (true) {
                cellArr = this.cells;
                if (cellArr != null && (length = cellArr.length) > 0) {
                    Cell cell = cellArr[(length - 1) & iNextInt];
                    if (cell != null) {
                        if (z4) {
                            long j2 = cell.value;
                            if (cell.cas(j2, fn(j2, j))) {
                                return;
                            }
                            if (length < NCPU && this.cells == cellArr) {
                                if (z5) {
                                    if (this.busy == 0 && casBusy()) {
                                        break;
                                    }
                                } else {
                                    z5 = true;
                                }
                            }
                        } else {
                            z4 = true;
                        }
                        int i = iNextInt ^ (iNextInt << 13);
                        int i2 = i ^ (i >>> 17);
                        iNextInt = i2 ^ (i2 << 5);
                        iArr2[0] = iNextInt;
                    } else if (this.busy == 0) {
                        Cell cell2 = new Cell(j);
                        if (this.busy == 0 && casBusy()) {
                            try {
                                Cell[] cellArr2 = this.cells;
                                if (cellArr2 == null || (length2 = cellArr2.length) <= 0) {
                                    z3 = false;
                                } else {
                                    int i3 = (length2 - 1) & iNextInt;
                                    if (cellArr2[i3] == null) {
                                        cellArr2[i3] = cell2;
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                }
                                this.busy = 0;
                                if (z3) {
                                    return;
                                }
                            } catch (Throwable th) {
                                this.busy = 0;
                                throw th;
                            }
                        }
                    }
                    z5 = false;
                    int i4 = iNextInt ^ (iNextInt << 13);
                    int i5 = i4 ^ (i4 >>> 17);
                    iNextInt = i5 ^ (i5 << 5);
                    iArr2[0] = iNextInt;
                } else if (this.busy == 0 && this.cells == cellArr && casBusy()) {
                    try {
                        if (this.cells == cellArr) {
                            Cell[] cellArr3 = new Cell[2];
                            cellArr3[iNextInt & 1] = new Cell(j);
                            this.cells = cellArr3;
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        this.busy = 0;
                        if (z2) {
                            return;
                        }
                    } catch (Throwable th2) {
                        this.busy = 0;
                        throw th2;
                    }
                } else {
                    long j3 = this.base;
                    if (casBase(j3, fn(j3, j))) {
                        return;
                    }
                }
            }
            try {
                if (this.cells == cellArr) {
                    Cell[] cellArr4 = new Cell[length << 1];
                    for (int i6 = 0; i6 < length; i6++) {
                        cellArr4[i6] = cellArr[i6];
                    }
                    this.cells = cellArr4;
                }
                this.busy = 0;
            } catch (Throwable th3) {
                this.busy = 0;
                throw th3;
            }
        }
    }
}
