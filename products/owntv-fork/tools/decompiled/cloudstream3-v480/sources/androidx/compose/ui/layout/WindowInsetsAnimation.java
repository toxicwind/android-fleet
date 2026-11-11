package androidx.compose.ui.layout;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: WindowInsetsRulers.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0012\u0010\u000b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\nR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118gX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\r8gX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000f\u0082\u0001\u0003\u0016\u0017\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/WindowInsetsAnimation;", "", "source", "Landroidx/compose/ui/layout/RectRulers;", "getSource", "()Landroidx/compose/ui/layout/RectRulers;", TypedValues.AttributesType.S_TARGET, "getTarget", "isVisible", "", "()Z", "isAnimating", "fraction", "", "getFraction", "()F", "durationMillis", "", "getDurationMillis", "()J", "alpha", "getAlpha", "Landroidx/compose/ui/layout/InnermostAnimationProperties;", "Landroidx/compose/ui/layout/NoWindowInsetsAnimation;", "Landroidx/compose/ui/layout/PlatformWindowInsetsAnimation;", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface WindowInsetsAnimation {
    float getAlpha();

    long getDurationMillis();

    float getFraction();

    RectRulers getSource();

    RectRulers getTarget();

    boolean isAnimating();

    boolean isVisible();
}
