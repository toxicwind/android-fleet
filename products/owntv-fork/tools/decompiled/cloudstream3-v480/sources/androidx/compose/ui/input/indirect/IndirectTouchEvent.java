package androidx.compose.ui.input.indirect;

import kotlin.Metadata;

/* JADX INFO: compiled from: IndirectTouchEvent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005R\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0001\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/input/indirect/IndirectTouchEvent;", "", "position", "Landroidx/compose/ui/geometry/Offset;", "getPosition-F1C5BW0", "()J", "uptimeMillis", "", "getUptimeMillis", "type", "Landroidx/compose/ui/input/indirect/IndirectTouchEventType;", "getType-LxEHWp8", "()I", "Landroidx/compose/ui/input/indirect/PlatformIndirectTouchEvent;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IndirectTouchEvent {
    /* JADX INFO: renamed from: getPosition-F1C5BW0 */
    long mo4976getPositionF1C5BW0();

    /* JADX INFO: renamed from: getType-LxEHWp8 */
    int mo4977getTypeLxEHWp8();

    long getUptimeMillis();
}
