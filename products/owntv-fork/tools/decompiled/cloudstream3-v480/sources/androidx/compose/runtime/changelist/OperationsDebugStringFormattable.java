package androidx.compose.runtime.changelist;

import kotlin.Metadata;

/* JADX INFO: compiled from: Operations.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "", "<init>", "()V", "toDebugString", "", "linePrefix", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class OperationsDebugStringFormattable {
    public static final int $stable = 0;

    public abstract String toDebugString(String linePrefix);

    public static /* synthetic */ String toDebugString$default(OperationsDebugStringFormattable operationsDebugStringFormattable, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toDebugString");
        }
        if ((i & 1) != 0) {
            str = "  ";
        }
        return operationsDebugStringFormattable.toDebugString(str);
    }
}
