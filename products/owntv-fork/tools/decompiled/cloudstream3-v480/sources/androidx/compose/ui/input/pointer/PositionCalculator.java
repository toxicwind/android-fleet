package androidx.compose.ui.input.pointer;

import kotlin.Metadata;

/* JADX INFO: compiled from: PointerInputEventProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H&¢\u0006\u0004\b\t\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/pointer/PositionCalculator;", "", "screenToLocal", "Landroidx/compose/ui/geometry/Offset;", "positionOnScreen", "screenToLocal-MK-Hz9U", "(J)J", "localToScreen", "localPosition", "localToScreen-MK-Hz9U", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PositionCalculator {
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U, reason: not valid java name */
    long mo5532localToScreenMKHz9U(long localPosition);

    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U, reason: not valid java name */
    long mo5533screenToLocalMKHz9U(long positionOnScreen);
}
