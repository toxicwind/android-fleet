package androidx.compose.ui.layout;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: WindowInsetsRulers.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R+\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00078V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00118V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R+\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u00198V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR+\u0010!\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00118V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u0018\u001a\u0004\b\"\u0010\u0014\"\u0004\b#\u0010\u0016R\u0014\u0010%\u001a\u00020&X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u001c\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b-\u0010\u001c\"\u0004\b.\u0010\u001eR\u001c\u00100\u001a\u00020,X\u0086\u000e¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010\u001eR\u001c\u00103\u001a\u00020,X\u0086\u000e¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u0010\u001eR\u001c\u00106\u001a\u00020,X\u0086\u000e¢\u0006\u0010\n\u0002\u0010/\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u0010\u001e¨\u00069"}, d2 = {"Landroidx/compose/ui/layout/WindowWindowInsetsAnimationValues;", "Landroidx/compose/ui/layout/PlatformWindowInsetsAnimation;", "name", "", "<init>", "(Ljava/lang/String;)V", "<set-?>", "", "isVisible", "()Z", "setVisible", "(Z)V", "isVisible$delegate", "Landroidx/compose/runtime/MutableState;", "isAnimating", "setAnimating", "isAnimating$delegate", "", "fraction", "getFraction", "()F", "setFraction", "(F)V", "fraction$delegate", "Landroidx/compose/runtime/MutableFloatState;", "", "durationMillis", "getDurationMillis", "()J", "setDurationMillis", "(J)V", "durationMillis$delegate", "Landroidx/compose/runtime/MutableLongState;", "alpha", "getAlpha", "setAlpha", "alpha$delegate", "source", "Landroidx/compose/ui/layout/RectRulers;", "getSource", "()Landroidx/compose/ui/layout/RectRulers;", TypedValues.AttributesType.S_TARGET, "getTarget", "current", "Landroidx/compose/ui/layout/ValueInsets;", "getCurrent-hdzbrEE", "setCurrent-Ynlvx88", "J", "maximum", "getMaximum-hdzbrEE", "setMaximum-Ynlvx88", "sourceValueInsets", "getSourceValueInsets-hdzbrEE", "setSourceValueInsets-Ynlvx88", "targetValueInsets", "getTargetValueInsets-hdzbrEE", "setTargetValueInsets-Ynlvx88", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WindowWindowInsetsAnimationValues implements PlatformWindowInsetsAnimation {
    public static final int $stable = 8;
    private final RectRulers source;
    private final RectRulers target;

    /* JADX INFO: renamed from: isVisible$delegate, reason: from kotlin metadata */
    private final MutableState isVisible = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);

    /* JADX INFO: renamed from: isAnimating$delegate, reason: from kotlin metadata */
    private final MutableState isAnimating = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);

    /* JADX INFO: renamed from: fraction$delegate, reason: from kotlin metadata */
    private final MutableFloatState fraction = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);

    /* JADX INFO: renamed from: durationMillis$delegate, reason: from kotlin metadata */
    private final MutableLongState durationMillis = SnapshotLongStateKt.mutableLongStateOf(0);

    /* JADX INFO: renamed from: alpha$delegate, reason: from kotlin metadata */
    private final MutableFloatState alpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);
    private long current = ValueInsets_androidKt.getUnsetValueInsets();
    private long maximum = ValueInsets_androidKt.getUnsetValueInsets();
    private long sourceValueInsets = ValueInsets_androidKt.getUnsetValueInsets();
    private long targetValueInsets = ValueInsets_androidKt.getUnsetValueInsets();

    public WindowWindowInsetsAnimationValues(String str) {
        this.source = RectRulersKt.RectRulers(str + " source");
        this.target = RectRulersKt.RectRulers(str + " target");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public boolean isVisible() {
        return ((Boolean) this.isVisible.getValue()).booleanValue();
    }

    public void setVisible(boolean z) {
        this.isVisible.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public boolean isAnimating() {
        return ((Boolean) this.isAnimating.getValue()).booleanValue();
    }

    public void setAnimating(boolean z) {
        this.isAnimating.setValue(Boolean.valueOf(z));
    }

    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public float getFraction() {
        return this.fraction.getFloatValue();
    }

    public void setFraction(float f) {
        this.fraction.setFloatValue(f);
    }

    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public long getDurationMillis() {
        return this.durationMillis.getLongValue();
    }

    public void setDurationMillis(long j) {
        this.durationMillis.setLongValue(j);
    }

    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public float getAlpha() {
        return this.alpha.getFloatValue();
    }

    public void setAlpha(float f) {
        this.alpha.setFloatValue(f);
    }

    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public RectRulers getSource() {
        return this.source;
    }

    @Override // androidx.compose.ui.layout.WindowInsetsAnimation
    public RectRulers getTarget() {
        return this.target;
    }

    /* JADX INFO: renamed from: getCurrent-hdzbrEE, reason: not valid java name and from getter */
    public final long getCurrent() {
        return this.current;
    }

    /* JADX INFO: renamed from: setCurrent-Ynlvx88, reason: not valid java name */
    public final void m5713setCurrentYnlvx88(long j) {
        this.current = j;
    }

    /* JADX INFO: renamed from: getMaximum-hdzbrEE, reason: not valid java name and from getter */
    public final long getMaximum() {
        return this.maximum;
    }

    /* JADX INFO: renamed from: setMaximum-Ynlvx88, reason: not valid java name */
    public final void m5714setMaximumYnlvx88(long j) {
        this.maximum = j;
    }

    /* JADX INFO: renamed from: getSourceValueInsets-hdzbrEE, reason: not valid java name and from getter */
    public final long getSourceValueInsets() {
        return this.sourceValueInsets;
    }

    /* JADX INFO: renamed from: setSourceValueInsets-Ynlvx88, reason: not valid java name */
    public final void m5715setSourceValueInsetsYnlvx88(long j) {
        this.sourceValueInsets = j;
    }

    /* JADX INFO: renamed from: getTargetValueInsets-hdzbrEE, reason: not valid java name and from getter */
    public final long getTargetValueInsets() {
        return this.targetValueInsets;
    }

    /* JADX INFO: renamed from: setTargetValueInsets-Ynlvx88, reason: not valid java name */
    public final void m5716setTargetValueInsetsYnlvx88(long j) {
        this.targetValueInsets = j;
    }
}
