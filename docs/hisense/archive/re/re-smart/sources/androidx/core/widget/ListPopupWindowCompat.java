package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListPopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class ListPopupWindowCompat {
    @Deprecated
    public static View.OnTouchListener createDragToOpenListener(Object listPopupWindow, View src) {
        return createDragToOpenListener((ListPopupWindow) listPopupWindow, src);
    }

    @Nullable
    public static View.OnTouchListener createDragToOpenListener(@NonNull ListPopupWindow listPopupWindow, @NonNull View src) {
        int i = Build.VERSION.SDK_INT;
        return listPopupWindow.createDragToOpenListener(src);
    }
}
