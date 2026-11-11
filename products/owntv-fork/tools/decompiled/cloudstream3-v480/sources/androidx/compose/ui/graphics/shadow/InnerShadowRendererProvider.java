package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: compiled from: InnerShadowPainter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bà\u0080\u0001\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010J7\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¢\u0006\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/graphics/shadow/InnerShadowRendererProvider;", "", "obtainInnerShadowRenderer", "Landroidx/compose/ui/graphics/shadow/InnerShadowRenderer;", "shape", "Landroidx/compose/ui/graphics/Shape;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "shadow", "Landroidx/compose/ui/graphics/shadow/Shadow;", "obtainInnerShadowRenderer-eZhPAX0", "(Landroidx/compose/ui/graphics/Shape;JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/graphics/shadow/Shadow;)Landroidx/compose/ui/graphics/shadow/InnerShadowRenderer;", "Companion", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface InnerShadowRendererProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: renamed from: obtainInnerShadowRenderer-eZhPAX0 */
    InnerShadowRenderer mo4863obtainInnerShadowRenderereZhPAX0(Shape shape, long size, LayoutDirection layoutDirection, Density density, Shadow shadow);

    /* JADX INFO: compiled from: InnerShadowPainter.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/graphics/shadow/InnerShadowRendererProvider$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/ui/graphics/shadow/InnerShadowRendererProvider;", "getDefault", "()Landroidx/compose/ui/graphics/shadow/InnerShadowRendererProvider;", "ui-graphics_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final InnerShadowRendererProvider Default = new InnerShadowRendererProvider() { // from class: androidx.compose.ui.graphics.shadow.InnerShadowRendererProvider$Companion$Default$1
            @Override // androidx.compose.ui.graphics.shadow.InnerShadowRendererProvider
            /* JADX INFO: renamed from: obtainInnerShadowRenderer-eZhPAX0 */
            public final InnerShadowRenderer mo4863obtainInnerShadowRenderereZhPAX0(Shape shape, long j, LayoutDirection layoutDirection, Density density, Shadow shadow) {
                return new InnerShadowRenderer(shadow, shape.mo317createOutlinePq9zytI(j, layoutDirection, density));
            }
        };

        private Companion() {
        }

        public final InnerShadowRendererProvider getDefault() {
            return Default;
        }
    }
}
