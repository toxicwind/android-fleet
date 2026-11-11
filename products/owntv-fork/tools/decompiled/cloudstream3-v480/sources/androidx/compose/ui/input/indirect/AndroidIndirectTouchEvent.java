package androidx.compose.ui.input.indirect;

import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidIndirectTouchEvent.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/input/indirect/AndroidIndirectTouchEvent;", "Landroidx/compose/ui/input/indirect/PlatformIndirectTouchEvent;", "position", "Landroidx/compose/ui/geometry/Offset;", "uptimeMillis", "", "type", "Landroidx/compose/ui/input/indirect/IndirectTouchEventType;", "nativeEvent", "Landroid/view/MotionEvent;", "<init>", "(JJILandroid/view/MotionEvent;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPosition-F1C5BW0", "()J", "J", "getUptimeMillis", "getType-LxEHWp8", "()I", "I", "getNativeEvent$ui_release", "()Landroid/view/MotionEvent;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidIndirectTouchEvent implements PlatformIndirectTouchEvent {
    public static final int $stable = 8;
    private final MotionEvent nativeEvent;
    private final long position;
    private final int type;
    private final long uptimeMillis;

    public /* synthetic */ AndroidIndirectTouchEvent(long j, long j2, int i, MotionEvent motionEvent, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, i, motionEvent);
    }

    private AndroidIndirectTouchEvent(long j, long j2, int i, MotionEvent motionEvent) {
        this.position = j;
        this.uptimeMillis = j2;
        this.type = i;
        this.nativeEvent = motionEvent;
    }

    @Override // androidx.compose.ui.input.indirect.IndirectTouchEvent
    /* JADX INFO: renamed from: getPosition-F1C5BW0, reason: not valid java name and from getter */
    public long getPosition() {
        return this.position;
    }

    @Override // androidx.compose.ui.input.indirect.IndirectTouchEvent
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    @Override // androidx.compose.ui.input.indirect.IndirectTouchEvent
    /* JADX INFO: renamed from: getType-LxEHWp8, reason: not valid java name and from getter */
    public int getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: getNativeEvent$ui_release, reason: from getter */
    public final MotionEvent getNativeEvent() {
        return this.nativeEvent;
    }
}
