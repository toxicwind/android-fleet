package androidx.compose.ui;

import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: FrameRate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\b"}, d2 = {"preferredFrameRate", "Landroidx/compose/ui/Modifier;", "frameRate", "", "frameRateCategory", "Landroidx/compose/ui/FrameRateCategory;", "preferredFrameRate-kI47g10", "(Landroidx/compose/ui/Modifier;F)Landroidx/compose/ui/Modifier;", "ui_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FrameRateKt {
    public static final Modifier preferredFrameRate(Modifier modifier, float f) {
        return ComposeUiFlags.isAdaptiveRefreshRateEnabled ? frameRate(GraphicsLayerModifierKt.m4317graphicsLayer_6ThJ44$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 0, null, 524287, null), f) : modifier;
    }

    /* JADX INFO: renamed from: preferredFrameRate-kI47g10, reason: not valid java name */
    public static final Modifier m3604preferredFrameRatekI47g10(Modifier modifier, float f) {
        return ComposeUiFlags.isAdaptiveRefreshRateEnabled ? frameRate(GraphicsLayerModifierKt.m4317graphicsLayer_6ThJ44$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 0, null, 524287, null), f) : modifier;
    }

    private static final Modifier frameRate(Modifier modifier, float f) {
        return modifier.then(new FrameRateElement(f));
    }
}
