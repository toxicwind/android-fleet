package androidx.compose.ui.input.indirect;

import android.view.MotionEvent;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidIndirectTouchEvent.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0007\u001a\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\u0010\r\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"nativeEvent", "Landroid/view/MotionEvent;", "Landroidx/compose/ui/input/indirect/IndirectTouchEvent;", "getNativeEvent$annotations", "(Landroidx/compose/ui/input/indirect/IndirectTouchEvent;)V", "getNativeEvent", "(Landroidx/compose/ui/input/indirect/IndirectTouchEvent;)Landroid/view/MotionEvent;", "IndirectTouchEvent", "motionEvent", "convertActionToIndirectTouchEventType", "Landroidx/compose/ui/input/indirect/IndirectTouchEventType;", "actionMasked", "", "(I)I", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidIndirectTouchEvent_androidKt {
    public static /* synthetic */ void getNativeEvent$annotations(IndirectTouchEvent indirectTouchEvent) {
    }

    public static final MotionEvent getNativeEvent(IndirectTouchEvent indirectTouchEvent) {
        Intrinsics.checkNotNull(indirectTouchEvent, "null cannot be cast to non-null type androidx.compose.ui.input.indirect.AndroidIndirectTouchEvent");
        return ((AndroidIndirectTouchEvent) indirectTouchEvent).getNativeEvent();
    }

    public static final IndirectTouchEvent IndirectTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        return new AndroidIndirectTouchEvent(Offset.m3862constructorimpl((((long) Float.floatToRawIntBits(motionEvent.getY())) & 4294967295L) | (Float.floatToRawIntBits(x) << 32)), motionEvent.getEventTime(), convertActionToIndirectTouchEventType(motionEvent.getActionMasked()), motionEvent, null);
    }

    public static final int convertActionToIndirectTouchEventType(int i) {
        if (i == 0) {
            return IndirectTouchEventType.INSTANCE.m4986getPressLxEHWp8();
        }
        if (i == 1) {
            return IndirectTouchEventType.INSTANCE.m4987getReleaseLxEHWp8();
        }
        if (i == 2) {
            return IndirectTouchEventType.INSTANCE.m4985getMoveLxEHWp8();
        }
        return IndirectTouchEventType.INSTANCE.m4988getUnknownLxEHWp8();
    }
}
