package androidx.compose.ui.node;

import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: LookaheadDelegate.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0004\u001a\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0080\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"MaxLayoutDimension", "", "MaxLayoutMask", "checkMeasuredSize", "", "width", "height", "AccessNoChange", "", "AccessChanged", "AccessNotSet", "AccessNewValue", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LookaheadDelegateKt {
    private static final byte AccessChanged = 1;
    private static final byte AccessNewValue = 3;
    private static final byte AccessNoChange = 0;
    private static final byte AccessNotSet = 2;
    private static final int MaxLayoutDimension = 16777215;
    private static final int MaxLayoutMask = -16777216;

    public static final void checkMeasuredSize(int i, int i2) {
        if ((i & (-16777216)) == 0 && ((-16777216) & i2) == 0) {
            return;
        }
        InlineClassHelperKt.throwIllegalStateException("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.");
    }
}
