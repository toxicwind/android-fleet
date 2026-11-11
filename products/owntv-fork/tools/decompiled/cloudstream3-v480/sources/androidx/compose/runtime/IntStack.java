package androidx.compose.runtime;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Stack.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\u0005H\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007J\u0006\u0010\u0012\u001a\u00020\u0007J\u0006\u0010\u0013\u001a\u00020\u0007J\u000e\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007J\t\u0010\u0015\u001a\u00020\u0016H\u0086\bJ\t\u0010\u0017\u001a\u00020\u0016H\u0086\bJ\u0006\u0010\u0018\u001a\u00020\rJ\u000e\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007R\u0012\u0010\u0004\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/IntStack;", "", "<init>", "()V", "slots", "", "tos", "", ContentDisposition.Parameters.Size, "getSize", "()I", "resize", "push", "", ES6Iterator.VALUE_PROPERTY, "pop", "peekOr", "default", "peek", "peek2", "index", "isEmpty", "", "isNotEmpty", "clear", "indexOf", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IntStack {
    public static final int $stable = 8;
    public int[] slots = new int[10];
    public int tos;

    /* JADX INFO: renamed from: getSize, reason: from getter */
    public final int getTos() {
        return this.tos;
    }

    private final int[] resize() {
        int[] iArr = this.slots;
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length * 2);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(...)");
        this.slots = iArrCopyOf;
        return iArrCopyOf;
    }

    public final void push(int value) {
        int[] iArrResize = this.slots;
        if (this.tos >= iArrResize.length) {
            iArrResize = resize();
        }
        int i = this.tos;
        this.tos = i + 1;
        iArrResize[i] = value;
    }

    public final int pop() {
        int[] iArr = this.slots;
        int i = this.tos - 1;
        this.tos = i;
        return iArr[i];
    }

    public final int peekOr(int i) {
        int i2 = this.tos - 1;
        return i2 >= 0 ? this.slots[i2] : i;
    }

    public final int peek() {
        return this.slots[this.tos - 1];
    }

    public final int peek2() {
        return this.slots[this.tos - 2];
    }

    public final int peek(int index) {
        return this.slots[index];
    }

    public final boolean isEmpty() {
        return this.tos == 0;
    }

    public final boolean isNotEmpty() {
        return this.tos != 0;
    }

    public final void clear() {
        this.tos = 0;
    }

    public final int indexOf(int value) {
        int[] iArr = this.slots;
        int iMin = Math.min(iArr.length, this.tos);
        for (int i = 0; i < iMin; i++) {
            if (iArr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
