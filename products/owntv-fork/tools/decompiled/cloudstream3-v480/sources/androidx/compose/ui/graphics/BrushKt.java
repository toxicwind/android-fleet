package androidx.compose.ui.graphics;

import android.graphics.Shader;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Brush.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0017\u0010\u0003\u001a\u00020\u00012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"toShaderBrush", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/Brush;", "ShaderBrush", "shader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "(Landroid/graphics/Shader;)Landroidx/compose/ui/graphics/ShaderBrush;", "ui-graphics_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BrushKt {
    public static final ShaderBrush toShaderBrush(Brush brush) {
        if (brush instanceof ShaderBrush) {
            return (ShaderBrush) brush;
        }
        if (!(brush instanceof SolidColor)) {
            throw new NoWhenBranchMatchedException();
        }
        SolidColor solidColor = (SolidColor) brush;
        Brush brushM4108verticalGradient8A3gB4$default = Brush.Companion.m4108verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m4142boximpl(solidColor.getValue()), Color.m4142boximpl(solidColor.getValue())}), 0.0f, 0.0f, 0, 14, (Object) null);
        Intrinsics.checkNotNull(brushM4108verticalGradient8A3gB4$default, "null cannot be cast to non-null type androidx.compose.ui.graphics.ShaderBrush");
        return (ShaderBrush) brushM4108verticalGradient8A3gB4$default;
    }

    public static final ShaderBrush ShaderBrush(final Shader shader) {
        return new ShaderBrush() { // from class: androidx.compose.ui.graphics.BrushKt.ShaderBrush.1
            @Override // androidx.compose.ui.graphics.ShaderBrush
            /* JADX INFO: renamed from: createShader-uvyYCjk, reason: not valid java name */
            public Shader mo4121createShaderuvyYCjk(long size) {
                return shader;
            }
        };
    }
}
