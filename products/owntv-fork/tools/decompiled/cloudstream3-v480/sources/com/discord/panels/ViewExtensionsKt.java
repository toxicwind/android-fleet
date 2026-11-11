package com.discord.panels;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"setEnabledAlpha", "", "Landroid/view/View;", "enabled", "", "disabledAlpha", "", "overlapping_panels_release"}, k = 2, mv = {1, 1, 16})
public final class ViewExtensionsKt {
    public static /* synthetic */ void setEnabledAlpha$default(View view, boolean z, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 0.5f;
        }
        setEnabledAlpha(view, z, f);
    }

    public static final void setEnabledAlpha(View setEnabledAlpha, boolean z, float f) {
        Intrinsics.checkParameterIsNotNull(setEnabledAlpha, "$this$setEnabledAlpha");
        if (z) {
            f = 1.0f;
        }
        setEnabledAlpha.setAlpha(f);
    }
}
