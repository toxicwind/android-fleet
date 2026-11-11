package androidx.compose.ui.input.pointer;

import androidx.compose.ui.internal.PlatformOptimizedCancellationException;
import kotlin.Metadata;

/* JADX INFO: compiled from: SuspendingPointerInputFilter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputResetException;", "Landroidx/compose/ui/internal/PlatformOptimizedCancellationException;", "<init>", "()V", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PointerInputResetException extends PlatformOptimizedCancellationException {
    public static final int $stable = 8;

    public PointerInputResetException() {
        super("Pointer input was reset");
    }
}
