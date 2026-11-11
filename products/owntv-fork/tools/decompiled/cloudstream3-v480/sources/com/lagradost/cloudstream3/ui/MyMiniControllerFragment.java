package com.lagradost.cloudstream3.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.media.widget.MiniControllerFragment;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.utils.UIHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MiniControllerFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001a\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0012H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/lagradost/cloudstream3/ui/MyMiniControllerFragment;", "Lcom/google/android/gms/cast/framework/media/widget/MiniControllerFragment;", "<init>", "()V", "currentColor", "", "getCurrentColor", "()I", "setCurrentColor", "(I)V", "onDestroy", "", "onInflate", "context", "Landroid/content/Context;", "attributeSet", "Landroid/util/AttributeSet;", "bundle", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MyMiniControllerFragment extends MiniControllerFragment {
    private int currentColor;

    public final int getCurrentColor() {
        return this.currentColor;
    }

    public final void setCurrentColor(int i) {
        this.currentColor = i;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.MiniControllerFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.currentColor = 0;
        super.onDestroy();
    }

    @Override // com.google.android.gms.cast.framework.media.widget.MiniControllerFragment, androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        if (this.currentColor == 0) {
            int[] CustomCast = R.styleable.CustomCast;
            Intrinsics.checkNotNullExpressionValue(CustomCast, "CustomCast");
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, CustomCast, 0, 0);
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.CustomCast_customCastBackgroundColor)) {
                this.currentColor = typedArrayObtainStyledAttributes.getColor(R.styleable.CustomCast_customCastBackgroundColor, 0);
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        super.onInflate(context, attributeSet, bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        try {
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(com.google.android.gms.cast.framework.R.id.container_all);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(com.google.android.gms.cast.framework.R.id.container_current);
            Context context = getContext();
            if (context != null) {
                if (progressBar != null) {
                    progressBar.setBackgroundColor(UIHelper.INSTANCE.adjustAlpha(UIHelper.INSTANCE.colorFromAttribute(context, R.attr.colorPrimary), 0.35f));
                }
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, UIHelper.INSTANCE.getToPx(2));
                if (progressBar != null) {
                    progressBar.setLayoutParams(layoutParams);
                }
                int i = this.currentColor;
                if (i != 0 && relativeLayout != null) {
                    relativeLayout.setBackgroundColor(i);
                }
            }
            View childAt = linearLayout != null ? linearLayout.getChildAt(0) : null;
            if (childAt != null) {
                childAt.setAlpha(0.0f);
            }
        } catch (Exception unused) {
        }
    }
}
