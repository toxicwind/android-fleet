package pl.edu.icm.jlargearrays;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public class LargeArrayUtils {
    public static final Unsafe UNSAFE;

    static {
        Object obj = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(cls);
            e = null;
            obj = obj2;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            e = e;
        }
        Unsafe unsafe = (Unsafe) obj;
        UNSAFE = unsafe;
        if (unsafe == null) {
            throw new Error("Could not obtain access to sun.misc.Unsafe", e);
        }
    }

    public static void arraycopy(final LongLargeArray longLargeArray, final long j, final LongLargeArray longLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= longLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= longLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (longLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                longLargeArray2.setLong(j5, longLargeArray.getLong(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / ((long) iMin);
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = ((long) i) * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.12
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        longLargeArray2.setLong(j2 + j9, longLargeArray.getLong(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                longLargeArray2.setLong(j10, longLargeArray.getLong(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                longLargeArray2.setLong(j12, longLargeArray.getLong(j11));
                j11++;
                j12++;
            }
        }
    }

    public static void arraycopy(final DoubleLargeArray doubleLargeArray, final long j, final DoubleLargeArray doubleLargeArray2, final long j2, long j3) {
        if (j < 0 || j >= doubleLargeArray.length()) {
            throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
        }
        if (j2 < 0 || j2 >= doubleLargeArray2.length()) {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
        }
        if (j3 < 0) {
            throw new IllegalArgumentException("length < 0");
        }
        if (doubleLargeArray2.isConstant()) {
            throw new IllegalArgumentException("Constant arrays cannot be modified.");
        }
        int iMin = (int) FastMath.min(j3, ConcurrencyUtils.getNumberOfThreads());
        if (iMin < 2 || j3 < ConcurrencyUtils.getConcurrentThreshold()) {
            long j4 = j;
            long j5 = j2;
            while (j4 < j + j3) {
                doubleLargeArray2.setDouble(j5, doubleLargeArray.getDouble(j4));
                j4++;
                j5++;
            }
            return;
        }
        long j6 = j3 / ((long) iMin);
        Future[] futureArr = new Future[iMin];
        int i = 0;
        while (i < iMin) {
            final long j7 = ((long) i) * j6;
            final long j8 = i == iMin + (-1) ? j3 : j7 + j6;
            Future[] futureArr2 = futureArr;
            int i2 = i;
            futureArr2[i2] = ConcurrencyUtils.submit(new Runnable() { // from class: pl.edu.icm.jlargearrays.LargeArrayUtils.16
                @Override // java.lang.Runnable
                public void run() {
                    for (long j9 = j7; j9 < j8; j9++) {
                        doubleLargeArray2.setDouble(j2 + j9, doubleLargeArray.getDouble(j + j9));
                    }
                }
            });
            i = i2 + 1;
            futureArr = futureArr2;
        }
        try {
            ConcurrencyUtils.waitForCompletion(futureArr);
        } catch (InterruptedException unused) {
            long j9 = j;
            long j10 = j2;
            while (j9 < j + j3) {
                doubleLargeArray2.setDouble(j10, doubleLargeArray.getDouble(j9));
                j9++;
                j10++;
            }
        } catch (ExecutionException unused2) {
            long j11 = j;
            long j12 = j2;
            while (j11 < j + j3) {
                doubleLargeArray2.setDouble(j12, doubleLargeArray.getDouble(j11));
                j11++;
                j12++;
            }
        }
    }
}
