package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class PopupMenuCompat {
    @Nullable
    public static View.OnTouchListener getDragToOpenListener(@NonNull Object popupMenu) {
        int i = Build.VERSION.SDK_INT;
        return ((PopupMenu) popupMenu).getDragToOpenListener();
    }
}
