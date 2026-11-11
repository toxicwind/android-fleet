package androidx.compose.ui.graphics.layer.view;

import android.content.Context;
import android.graphics.Canvas;
import kotlin.Metadata;

/* JADX INFO: compiled from: ViewLayerContainer.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u0007H\u0004¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/graphics/layer/view/ViewLayerContainer;", "Landroidx/compose/ui/graphics/layer/view/DrawChildContainer;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "dispatchDraw", "", "canvas", "Landroid/graphics/Canvas;", "dispatchGetDisplayList", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ViewLayerContainer extends DrawChildContainer {
    @Override // androidx.compose.ui.graphics.layer.view.DrawChildContainer, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
    }

    protected final void dispatchGetDisplayList() {
    }

    public ViewLayerContainer(Context context) {
        super(context);
    }
}
