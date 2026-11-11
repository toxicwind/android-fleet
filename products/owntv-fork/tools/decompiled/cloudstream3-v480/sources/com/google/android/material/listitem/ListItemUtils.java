package com.google.android.material.listitem;

import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;

/* JADX INFO: loaded from: classes6.dex */
class ListItemUtils {
    private ListItemUtils() {
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    static boolean isRightAligned(View view) {
        int i;
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (layoutParams.gravity != -1) {
                i = layoutParams.gravity;
            } else {
                i = GravityCompat.END;
            }
        } else {
            i = GravityCompat.END;
        }
        return (GravityCompat.getAbsoluteGravity(i, view.getLayoutDirection()) & 7) == 5;
    }
}
