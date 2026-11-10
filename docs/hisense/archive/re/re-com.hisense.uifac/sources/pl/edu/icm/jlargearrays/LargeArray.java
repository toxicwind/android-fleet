package pl.edu.icm.jlargearrays;

import com.mediatek.twoworlds.tv.common.MtkTvSvctxNotifyCode;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

/* JADX INFO: loaded from: classes.dex */
public abstract class LargeArray implements Serializable, Cloneable {
    private static int maxSizeOf32bitArray = 1073741824;
    private static final long serialVersionUID = 7921589398878016801L;
    protected long length;
    protected long sizeof;
    protected LargeArrayType type;
    protected boolean isConstant = false;
    protected Object parent = null;
    protected long ptr = 0;

    protected LargeArray() {
    }

    public long length() {
        return this.length;
    }

    public boolean isLarge() {
        return this.ptr != 0;
    }

    public boolean isConstant() {
        return this.isConstant;
    }

    public static int getMaxSizeOf32bitArray() {
        return maxSizeOf32bitArray;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public boolean equals(Object obj) {
        Object obj2;
        if (obj == null || !(obj instanceof LargeArray)) {
            return false;
        }
        LargeArray largeArray = (LargeArray) obj;
        boolean z = this.type == largeArray.type && this.length == largeArray.length && this.sizeof == largeArray.sizeof && this.isConstant == largeArray.isConstant && this.ptr == largeArray.ptr;
        Object obj3 = this.parent;
        if (obj3 != null && (obj2 = largeArray.parent) != null) {
            return z && obj3.equals(obj2);
        }
        if (this.parent == null && largeArray.parent == null) {
            return z;
        }
        return false;
    }

    public int hashCode() {
        LargeArrayType largeArrayType = this.type;
        int iHashCode = (MtkTvSvctxNotifyCode.SVCTX_NTFY_CODE_DRM_TOKEN_ACQUISITION_ERROR + (largeArrayType != null ? largeArrayType.hashCode() : 0)) * 29;
        long j = this.length;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 29;
        long j2 = this.sizeof;
        int i2 = (((i + ((int) (j2 ^ (j2 >>> 32)))) * 29) + (this.isConstant ? 1 : 0)) * 29;
        Object obj = this.parent;
        int iHashCode2 = obj != null ? obj.hashCode() : 0;
        long j3 = this.ptr;
        return ((i2 + iHashCode2) * 29) + ((int) ((j3 >>> 32) ^ j3));
    }

    protected static class Deallocator implements Runnable {
        private final long length;
        private long ptr;
        private final long sizeof;

        public Deallocator(long j, long j2, long j3) {
            this.ptr = j;
            this.length = j2;
            this.sizeof = j3;
        }

        @Override // java.lang.Runnable
        public void run() {
            long j = this.ptr;
            if (j != 0) {
                LargeArrayUtils.UNSAFE.freeMemory(j);
                this.ptr = 0L;
                MemoryCounter.decreaseCounter(this.length * this.sizeof);
            }
        }
    }

    protected void zeroNativeMemory(long j) {
        if (this.ptr != 0) {
            int iMin = (int) FastMath.min(j, ConcurrencyUtils.getNumberOfThreads());
            if (iMin <= 2 || j < ConcurrencyUtils.getConcurrentThreshold()) {
                LargeArrayUtils.UNSAFE.setMemory(this.ptr, j * this.sizeof, (byte) 0);
                return;
            }
            long j2 = j / ((long) iMin);
            Future[] futureArr = new Future[iMin];
            final long j3 = this.ptr;
            int i = 0;
            while (i < iMin) {
                final long j4 = ((long) i) * j2;
                final long j5 = i == iMin + (-1) ? j : j4 + j2;
                futureArr[i] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArray.1
                    @Override // java.lang.Runnable
                    public void run() {
                        switch (AnonymousClass2.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArray.this.type.ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                for (long j6 = j4; j6 < j5; j6++) {
                                    LargeArrayUtils.UNSAFE.putByte(j3 + (LargeArray.this.sizeof * j6), (byte) 0);
                                }
                                return;
                            case 6:
                                for (long j7 = j4; j7 < j5; j7++) {
                                    LargeArrayUtils.UNSAFE.putShort(j3 + (LargeArray.this.sizeof * j7), (short) 0);
                                }
                                return;
                            case 7:
                                for (long j8 = j4; j8 < j5; j8++) {
                                    LargeArrayUtils.UNSAFE.putInt(j3 + (LargeArray.this.sizeof * j8), 0);
                                }
                                return;
                            case 8:
                                for (long j9 = j4; j9 < j5; j9++) {
                                    LargeArrayUtils.UNSAFE.putLong(j3 + (LargeArray.this.sizeof * j9), 0L);
                                }
                                return;
                            case 9:
                                for (long j10 = j4; j10 < j5; j10++) {
                                    LargeArrayUtils.UNSAFE.putFloat(j3 + (LargeArray.this.sizeof * j10), 0.0f);
                                }
                                return;
                            case 10:
                                for (long j11 = j4; j11 < j5; j11++) {
                                    LargeArrayUtils.UNSAFE.putDouble(j3 + (LargeArray.this.sizeof * j11), 0.0d);
                                }
                                return;
                            default:
                                throw new IllegalArgumentException("Invalid array type.");
                        }
                    }
                });
                i++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                LargeArrayUtils.UNSAFE.setMemory(this.ptr, j * this.sizeof, (byte) 0);
            } catch (ExecutionException unused2) {
                LargeArrayUtils.UNSAFE.setMemory(this.ptr, this.sizeof * j, (byte) 0);
            }
        }
    }

    /* JADX INFO: renamed from: pl.edu.icm.jlargearrays.LargeArray$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType;

        static {
            int[] iArr = new int[LargeArrayType.values().length];
            $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType = iArr;
            try {
                iArr[LargeArrayType.LOGIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.UNSIGNED_BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.SHORT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.INT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.LONG.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.FLOAT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArrayType.DOUBLE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }
}
