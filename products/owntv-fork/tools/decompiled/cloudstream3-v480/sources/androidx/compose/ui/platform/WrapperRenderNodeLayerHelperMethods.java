package androidx.compose.ui.platform;

import android.view.ViewParent;
import kotlin.Metadata;

/* JADX INFO: compiled from: RenderNodeLayer.android.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/platform/WrapperRenderNodeLayerHelperMethods;", "", "<init>", "()V", "onDescendantInvalidated", "", "ownerView", "Landroidx/compose/ui/platform/AndroidComposeView;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WrapperRenderNodeLayerHelperMethods {
    public static final int $stable = 0;
    public static final WrapperRenderNodeLayerHelperMethods INSTANCE = new WrapperRenderNodeLayerHelperMethods();

    private WrapperRenderNodeLayerHelperMethods() {
    }

    public final void onDescendantInvalidated(AndroidComposeView ownerView) {
        ViewParent parent = ownerView.getParent();
        if (parent != null) {
            AndroidComposeView androidComposeView = ownerView;
            parent.onDescendantInvalidated(androidComposeView, androidComposeView);
        }
    }
}
