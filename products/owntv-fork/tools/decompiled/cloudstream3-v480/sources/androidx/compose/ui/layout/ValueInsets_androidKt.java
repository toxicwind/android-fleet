package androidx.compose.ui.layout;

import androidx.core.graphics.Insets;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: compiled from: ValueInsets.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0080\b¢\u0006\u0002\u0010\u0004\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0080\b¢\u0006\u0002\u0010\n\"\u0016\u0010\u000b\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\r\"\u0016\u0010\u000f\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u0010\u0010\r¨\u0006\u0011"}, d2 = {"ValueInsets", "Landroidx/compose/ui/layout/ValueInsets;", "insets", "Landroidx/core/graphics/Insets;", "(Landroidx/core/graphics/Insets;)J", TtmlNode.LEFT, "", "top", TtmlNode.RIGHT, "bottom", "(IIII)J", "ZeroValueInsets", "getZeroValueInsets", "()J", "J", "UnsetValueInsets", "getUnsetValueInsets", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ValueInsets_androidKt {
    private static final long ZeroValueInsets = ValueInsets.m5697constructorimpl(0);
    private static final long UnsetValueInsets = ValueInsets.m5697constructorimpl(-1);

    public static final long ValueInsets(Insets insets) {
        return ValueInsets.m5697constructorimpl((((long) insets.left) << 48) | (((long) insets.top) << 32) | (((long) insets.right) << 16) | ((long) insets.bottom));
    }

    public static final long ValueInsets(int i, int i2, int i3, int i4) {
        return ValueInsets.m5697constructorimpl((((long) i2) << 32) | (((long) i) << 48) | (((long) i3) << 16) | ((long) i4));
    }

    public static final long getZeroValueInsets() {
        return ZeroValueInsets;
    }

    public static final long getUnsetValueInsets() {
        return UnsetValueInsets;
    }
}
