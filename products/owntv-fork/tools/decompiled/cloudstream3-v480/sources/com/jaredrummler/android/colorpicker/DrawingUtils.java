package com.jaredrummler.android.colorpicker;

import android.content.Context;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes6.dex */
final class DrawingUtils {
    DrawingUtils() {
    }

    static int dpToPx(Context context, float f) {
        float fApplyDimension = TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
        int i = (int) (((double) fApplyDimension) + 0.5d);
        if (i != 0 || fApplyDimension <= 0.0f) {
            return i;
        }
        return 1;
    }
}
