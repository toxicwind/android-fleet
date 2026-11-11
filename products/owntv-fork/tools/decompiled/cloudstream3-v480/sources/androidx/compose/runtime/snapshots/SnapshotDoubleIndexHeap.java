package androidx.compose.runtime.snapshots;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: SnapshotDoubleIndexHeap.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0011\u001a\u00060\u0012j\u0002`\u00132\f\b\u0002\u0010\u0014\u001a\u00060\u0012j\u0002`\u0013¢\u0006\u0002\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00052\n\u0010\u0004\u001a\u00060\u0012j\u0002`\u0013¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005J\b\u0010\u001b\u001a\u00020\u0019H\u0007J!\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00052\n\u0010\u0004\u001a\u00060\u0012j\u0002`\u0013H\u0007¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0018\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0002J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u0005H\u0002J\b\u0010%\u001a\u00020\u0005H\u0002J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u0002R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00060\nj\u0002`\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotDoubleIndexHeap;", "", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", ContentDisposition.Parameters.Size, "getSize", "()I", "values", "", "Landroidx/compose/runtime/snapshots/SnapshotIdArray;", "[J", "index", "", "handles", "firstFreeHandle", "lowestOrDefault", "", "Landroidx/compose/runtime/snapshots/SnapshotId;", "default", "(J)J", "add", "(J)I", "remove", "", "handle", "validate", "validateHandle", "(IJ)V", "shiftUp", "shiftDown", "swap", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "ensure", "atLeast", "allocateHandle", "freeHandle", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SnapshotDoubleIndexHeap {
    public static final int $stable = 8;
    private int firstFreeHandle;
    private int[] handles;
    private int size;
    private long[] values = SnapshotId_jvmKt.snapshotIdArrayWithCapacity(16);
    private int[] index = new int[16];

    public SnapshotDoubleIndexHeap() {
        int[] iArr = new int[16];
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            iArr[i] = i2;
            i = i2;
        }
        this.handles = iArr;
    }

    public final int getSize() {
        return this.size;
    }

    public static /* synthetic */ long lowestOrDefault$default(SnapshotDoubleIndexHeap snapshotDoubleIndexHeap, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        return snapshotDoubleIndexHeap.lowestOrDefault(j);
    }

    public final long lowestOrDefault(long j) {
        return this.size > 0 ? this.values[0] : j;
    }

    public final int add(long value) {
        ensure(this.size + 1);
        int i = this.size;
        this.size = i + 1;
        int iAllocateHandle = allocateHandle();
        this.values[i] = value;
        this.index[i] = iAllocateHandle;
        this.handles[iAllocateHandle] = i;
        shiftUp(i);
        return iAllocateHandle;
    }

    public final void remove(int handle) {
        int i = this.handles[handle];
        swap(i, this.size - 1);
        this.size--;
        shiftUp(i);
        shiftDown(i);
        freeHandle(handle);
    }

    public final void validate() {
        int i = this.size;
        int i2 = 1;
        while (i2 < i) {
            int i3 = i2 + 1;
            long[] jArr = this.values;
            if (Intrinsics.compare(jArr[(i3 >> 1) - 1], jArr[i2]) > 0) {
                throw new IllegalStateException(("Index " + i2 + " is out of place").toString());
            }
            i2 = i3;
        }
    }

    public final void validateHandle(int handle, long value) {
        int i = this.handles[handle];
        if (this.index[i] != handle) {
            throw new IllegalStateException(("Index for handle " + handle + " is corrupted").toString());
        }
        if (this.values[i] == value) {
            return;
        }
        throw new IllegalStateException(("Value for handle " + handle + " was " + this.values[i] + " but was supposed to be " + value).toString());
    }

    private final void shiftUp(int index) {
        long[] jArr = this.values;
        long j = jArr[index];
        while (index > 0) {
            int i = ((index + 1) >> 1) - 1;
            if (Intrinsics.compare(jArr[i], j) <= 0) {
                return;
            }
            swap(i, index);
            index = i;
        }
    }

    private final void shiftDown(int index) {
        long[] jArr = this.values;
        int i = this.size >> 1;
        while (index < i) {
            int i2 = (index + 1) << 1;
            int i3 = i2 - 1;
            if (i2 < this.size && Intrinsics.compare(jArr[i2], jArr[i3]) < 0) {
                if (Intrinsics.compare(jArr[i2], jArr[index]) >= 0) {
                    return;
                }
                swap(i2, index);
                index = i2;
            } else {
                if (Intrinsics.compare(jArr[i3], jArr[index]) >= 0) {
                    return;
                }
                swap(i3, index);
                index = i3;
            }
        }
    }

    private final void swap(int a, int b) {
        long[] jArr = this.values;
        int[] iArr = this.index;
        int[] iArr2 = this.handles;
        long j = jArr[a];
        jArr[a] = jArr[b];
        jArr[b] = j;
        int i = iArr[a];
        int i2 = iArr[b];
        iArr[a] = i2;
        iArr[b] = i;
        iArr2[i2] = a;
        iArr2[i] = b;
    }

    private final void ensure(int atLeast) {
        int length = this.values.length;
        if (atLeast <= length) {
            return;
        }
        int i = length * 2;
        long[] jArrSnapshotIdArrayWithCapacity = SnapshotId_jvmKt.snapshotIdArrayWithCapacity(i);
        int[] iArr = new int[i];
        ArraysKt.copyInto$default(this.values, jArrSnapshotIdArrayWithCapacity, 0, 0, 0, 12, (Object) null);
        ArraysKt.copyInto$default(this.index, iArr, 0, 0, 0, 14, (Object) null);
        this.values = jArrSnapshotIdArrayWithCapacity;
        this.index = iArr;
    }

    private final int allocateHandle() {
        int length = this.handles.length;
        if (this.firstFreeHandle >= length) {
            int i = length * 2;
            int[] iArr = new int[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 + 1;
                iArr[i2] = i3;
                i2 = i3;
            }
            ArraysKt.copyInto$default(this.handles, iArr, 0, 0, 0, 14, (Object) null);
            this.handles = iArr;
        }
        int i4 = this.firstFreeHandle;
        this.firstFreeHandle = this.handles[i4];
        return i4;
    }

    private final void freeHandle(int handle) {
        this.handles[handle] = this.firstFreeHandle;
        this.firstFreeHandle = handle;
    }
}
