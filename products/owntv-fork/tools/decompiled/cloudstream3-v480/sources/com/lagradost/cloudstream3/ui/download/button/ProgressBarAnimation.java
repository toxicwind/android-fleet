package com.lagradost.cloudstream3.ui.download.button;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ProgressBarAnimation.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/lagradost/cloudstream3/ui/download/button/ProgressBarAnimation;", "Landroid/view/animation/Animation;", "progressBar", "Landroid/widget/ProgressBar;", TypedValues.TransitionType.S_FROM, "", TypedValues.TransitionType.S_TO, "<init>", "(Landroid/widget/ProgressBar;FF)V", "applyTransformation", "", "interpolatedTime", "t", "Landroid/view/animation/Transformation;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProgressBarAnimation extends Animation {
    private final float from;
    private final ProgressBar progressBar;
    private final float to;

    public ProgressBarAnimation(ProgressBar progressBar, float f, float f2) {
        Intrinsics.checkNotNullParameter(progressBar, "progressBar");
        this.progressBar = progressBar;
        this.from = f;
        this.to = f2;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float f = this.from;
        this.progressBar.setProgress((int) (f + ((this.to - f) * interpolatedTime)));
    }
}
