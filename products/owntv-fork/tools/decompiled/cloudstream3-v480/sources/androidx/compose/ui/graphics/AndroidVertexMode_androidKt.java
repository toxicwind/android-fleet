package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidVertexMode.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"toAndroidVertexMode", "Landroid/graphics/Canvas$VertexMode;", "Landroidx/compose/ui/graphics/VertexMode;", "toAndroidVertexMode-JOOmi9M", "(I)Landroid/graphics/Canvas$VertexMode;", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidVertexMode_androidKt {
    /* JADX INFO: renamed from: toAndroidVertexMode-JOOmi9M, reason: not valid java name */
    public static final android.graphics.Canvas.VertexMode m4020toAndroidVertexModeJOOmi9M(int i) {
        if (VertexMode.m4574equalsimpl0(i, VertexMode.INSTANCE.m4580getTrianglesc2xauaI())) {
            return android.graphics.Canvas.VertexMode.TRIANGLES;
        }
        if (VertexMode.m4574equalsimpl0(i, VertexMode.INSTANCE.m4579getTriangleStripc2xauaI())) {
            return android.graphics.Canvas.VertexMode.TRIANGLE_STRIP;
        }
        return VertexMode.m4574equalsimpl0(i, VertexMode.INSTANCE.m4578getTriangleFanc2xauaI()) ? android.graphics.Canvas.VertexMode.TRIANGLE_FAN : android.graphics.Canvas.VertexMode.TRIANGLES;
    }
}
