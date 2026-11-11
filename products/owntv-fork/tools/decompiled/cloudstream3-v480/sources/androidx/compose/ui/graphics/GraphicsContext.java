package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.shadow.DropShadowPainter;
import androidx.compose.ui.graphics.shadow.InnerShadowPainter;
import androidx.compose.ui.graphics.shadow.PlatformShadowContext;
import androidx.compose.ui.graphics.shadow.ShadowContext;
import kotlin.Metadata;

/* JADX INFO: compiled from: GraphicsContext.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsContext;", "", "createGraphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "releaseGraphicsLayer", "", "layer", "shadowContext", "Landroidx/compose/ui/graphics/shadow/ShadowContext;", "getShadowContext", "()Landroidx/compose/ui/graphics/shadow/ShadowContext;", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface GraphicsContext {
    GraphicsLayer createGraphicsLayer();

    ShadowContext getShadowContext();

    void releaseGraphicsLayer(GraphicsLayer layer);

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.GraphicsContext$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: GraphicsContext.kt */
    public final /* synthetic */ class CC {
        public static ShadowContext $default$getShadowContext(GraphicsContext _this) {
            return new PlatformShadowContext() { // from class: androidx.compose.ui.graphics.GraphicsContext$shadowContext$1
                @Override // androidx.compose.ui.graphics.shadow.ShadowContext
                public /* synthetic */ void clearCache() {
                    ShadowContext.CC.$default$clearCache(this);
                }

                @Override // androidx.compose.ui.graphics.shadow.ShadowContext
                public /* synthetic */ DropShadowPainter createDropShadowPainter(Shape shape, androidx.compose.ui.graphics.shadow.Shadow shadow) {
                    return ShadowContext.CC.$default$createDropShadowPainter(this, shape, shadow);
                }

                @Override // androidx.compose.ui.graphics.shadow.ShadowContext
                public /* synthetic */ InnerShadowPainter createInnerShadowPainter(Shape shape, androidx.compose.ui.graphics.shadow.Shadow shadow) {
                    return ShadowContext.CC.$default$createInnerShadowPainter(this, shape, shadow);
                }
            };
        }
    }
}
