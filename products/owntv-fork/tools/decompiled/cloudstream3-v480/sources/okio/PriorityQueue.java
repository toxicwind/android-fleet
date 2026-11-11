package okio;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsyncTimeout.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0002J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0002J\u0015\u0010\u0012\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u0013\u001a\u00020\bH\u0082\nR\u0012\u0010\u0004\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0014"}, d2 = {"Lokio/PriorityQueue;", "", "<init>", "()V", ContentDisposition.Parameters.Size, "", "array", "", "Lokio/AsyncTimeout;", "[Lokio/AsyncTimeout;", "first", "add", "", "node", "remove", "heapifyUp", "vacantIndex", "heapifyDown", "compareTo", "other", "okio"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PriorityQueue {
    public AsyncTimeout[] array = new AsyncTimeout[8];
    public int size;

    public final AsyncTimeout first() {
        return this.array[1];
    }

    public final void add(AsyncTimeout node) {
        Intrinsics.checkNotNullParameter(node, "node");
        int i = this.size + 1;
        this.size = i;
        AsyncTimeout[] asyncTimeoutArr = this.array;
        if (i == asyncTimeoutArr.length) {
            AsyncTimeout[] asyncTimeoutArr2 = new AsyncTimeout[i * 2];
            ArraysKt.copyInto$default(asyncTimeoutArr, asyncTimeoutArr2, 0, 0, 0, 14, (Object) null);
            this.array = asyncTimeoutArr2;
        }
        heapifyUp(i, node);
    }

    public final void remove(AsyncTimeout node) {
        Intrinsics.checkNotNullParameter(node, "node");
        if (node.index == -1) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i = this.size;
        int i2 = node.index;
        AsyncTimeout asyncTimeout = this.array[i];
        Intrinsics.checkNotNull(asyncTimeout);
        node.index = -1;
        this.array[i] = null;
        this.size = i - 1;
        if (node == asyncTimeout) {
            return;
        }
        int iCompare = Intrinsics.compare(0L, asyncTimeout.getTimeoutAt() - node.getTimeoutAt());
        if (iCompare == 0) {
            this.array[i2] = asyncTimeout;
            asyncTimeout.index = i2;
        } else if (iCompare < 0) {
            heapifyDown(i2, asyncTimeout);
        } else {
            heapifyUp(i2, asyncTimeout);
        }
    }

    private final void heapifyUp(int vacantIndex, AsyncTimeout node) {
        while (true) {
            int i = vacantIndex >> 1;
            if (i == 0) {
                break;
            }
            AsyncTimeout asyncTimeout = this.array[i];
            Intrinsics.checkNotNull(asyncTimeout);
            if (Intrinsics.compare(0L, node.getTimeoutAt() - asyncTimeout.getTimeoutAt()) <= 0) {
                break;
            }
            asyncTimeout.index = vacantIndex;
            this.array[vacantIndex] = asyncTimeout;
            vacantIndex = i;
        }
        this.array[vacantIndex] = node;
        node.index = vacantIndex;
    }

    private final void heapifyDown(int vacantIndex, AsyncTimeout node) {
        AsyncTimeout asyncTimeout;
        while (true) {
            int i = vacantIndex << 1;
            int i2 = i + 1;
            int i3 = this.size;
            if (i2 > i3) {
                if (i > i3) {
                    break;
                }
                asyncTimeout = this.array[i];
                Intrinsics.checkNotNull(asyncTimeout);
            } else {
                asyncTimeout = this.array[i];
                Intrinsics.checkNotNull(asyncTimeout);
                AsyncTimeout asyncTimeout2 = this.array[i2];
                Intrinsics.checkNotNull(asyncTimeout2);
                if (Intrinsics.compare(0L, asyncTimeout2.getTimeoutAt() - asyncTimeout.getTimeoutAt()) >= 0) {
                    asyncTimeout = asyncTimeout2;
                }
            }
            if (Intrinsics.compare(0L, asyncTimeout.getTimeoutAt() - node.getTimeoutAt()) <= 0) {
                break;
            }
            int i4 = asyncTimeout.index;
            asyncTimeout.index = vacantIndex;
            this.array[vacantIndex] = asyncTimeout;
            vacantIndex = i4;
        }
        this.array[vacantIndex] = node;
        node.index = vacantIndex;
    }

    private final int compareTo(AsyncTimeout asyncTimeout, AsyncTimeout asyncTimeout2) {
        Intrinsics.checkNotNullParameter(asyncTimeout, "<this>");
        return Intrinsics.compare(0L, asyncTimeout2.getTimeoutAt() - asyncTimeout.getTimeoutAt());
    }
}
