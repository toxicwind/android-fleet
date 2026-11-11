package androidx.compose.ui.node;

import kotlin.Metadata;

/* JADX INFO: compiled from: HitTestResult.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a'\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"IS_IN_LAYER", "", "IS_IN_EXPANDED_BOUNDS", "DistanceAndFlags", "Landroidx/compose/ui/node/DistanceAndFlags;", "distance", "", "isInLayer", "", "isInExpandedBounds", "(FZZ)J", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class HitTestResultKt {
    private static final long IS_IN_EXPANDED_BOUNDS = 2;
    private static final long IS_IN_LAYER = 1;

    static /* synthetic */ long DistanceAndFlags$default(float f, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        return DistanceAndFlags(f, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long DistanceAndFlags(float f, boolean z, boolean z2) {
        return DistanceAndFlags.m5751constructorimpl((((z ? 1L : 0L) | (z2 ? 2L : 0L)) & 4294967295L) | (((long) Float.floatToRawIntBits(f)) << 32));
    }
}
