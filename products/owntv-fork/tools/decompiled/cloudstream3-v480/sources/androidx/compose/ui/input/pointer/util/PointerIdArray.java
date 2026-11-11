package androidx.compose.ui.input.pointer.util;

import androidx.compose.ui.input.pointer.PointerId;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: PointerIdArray.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0086\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0086\b¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0005J\u0006\u0010\u001a\u001a\u00020\u0013J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0018J\u0018\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0086\b¢\u0006\u0004\b\u001c\u0010\u0016J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0018H\u0086\u0002J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u0005H\u0002J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u000eH\u0086\n¢\u0006\u0004\b!\u0010\"J\u0006\u0010#\u001a\u00020\u001eJ\u0018\u0010$\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000eH\u0086\b¢\u0006\u0004\b%\u0010\u0016J\u000e\u0010$\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/compose/ui/input/pointer/util/PointerIdArray;", "", "<init>", "()V", ES6Iterator.VALUE_PROPERTY, "", ContentDisposition.Parameters.Size, "getSize", "()I", "lastIndex", "getLastIndex", "internalArray", "", "get", "Landroidx/compose/ui/input/pointer/PointerId;", "index", "get-_I2yYro", "(I)J", "remove", "", "pointerId", "remove-0FcD4WY", "(J)Z", "pointerIdValue", "", "removeAt", "isEmpty", "add", "add-0FcD4WY", "set", "", "resizeStorage", "minSize", "set-DmW0f2w", "(IJ)V", "clear", "contains", "contains-0FcD4WY", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PointerIdArray {
    public static final int $stable = 8;
    private long[] internalArray = new long[2];
    private int size;

    public final int getSize() {
        return this.size;
    }

    public final int getLastIndex() {
        return getSize() - 1;
    }

    /* JADX INFO: renamed from: get-_I2yYro, reason: not valid java name */
    public final long m5546get_I2yYro(int index) {
        return PointerId.m5431constructorimpl(this.internalArray[index]);
    }

    /* JADX INFO: renamed from: remove-0FcD4WY, reason: not valid java name */
    public final boolean m5547remove0FcD4WY(long pointerId) {
        return remove(pointerId);
    }

    public final boolean remove(long pointerIdValue) {
        int i = this.size;
        int i2 = 0;
        while (i2 < i) {
            if (pointerIdValue == this.internalArray[i2]) {
                int i3 = this.size - 1;
                while (i2 < i3) {
                    long[] jArr = this.internalArray;
                    int i4 = i2 + 1;
                    jArr[i2] = jArr[i4];
                    i2 = i4;
                }
                this.size--;
                return true;
            }
            i2++;
        }
        return false;
    }

    public final boolean removeAt(int index) {
        int i = this.size;
        if (index >= i) {
            return false;
        }
        int i2 = i - 1;
        while (index < i2) {
            long[] jArr = this.internalArray;
            int i3 = index + 1;
            jArr[index] = jArr[i3];
            index = i3;
        }
        this.size--;
        return true;
    }

    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean add(long value) {
        if (contains(value)) {
            return false;
        }
        set(this.size, value);
        return true;
    }

    /* JADX INFO: renamed from: add-0FcD4WY, reason: not valid java name */
    public final boolean m5544add0FcD4WY(long pointerId) {
        return add(pointerId);
    }

    public final void set(int index, long value) {
        long[] jArrResizeStorage = this.internalArray;
        if (index >= jArrResizeStorage.length) {
            jArrResizeStorage = resizeStorage(index + 1);
        }
        jArrResizeStorage[index] = value;
        if (index >= this.size) {
            this.size = index + 1;
        }
    }

    private final long[] resizeStorage(int minSize) {
        long[] jArr = this.internalArray;
        long[] jArrCopyOf = Arrays.copyOf(jArr, Math.max(minSize, jArr.length * 2));
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(...)");
        this.internalArray = jArrCopyOf;
        return jArrCopyOf;
    }

    /* JADX INFO: renamed from: set-DmW0f2w, reason: not valid java name */
    public final void m5548setDmW0f2w(int index, long pointerId) {
        set(index, pointerId);
    }

    public final void clear() {
        this.size = 0;
    }

    /* JADX INFO: renamed from: contains-0FcD4WY, reason: not valid java name */
    public final boolean m5545contains0FcD4WY(long pointerId) {
        return contains(pointerId);
    }

    public final boolean contains(long pointerIdValue) {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.internalArray[i2] == pointerIdValue) {
                return true;
            }
        }
        return false;
    }
}
