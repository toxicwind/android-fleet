package androidx.core.widget;

import android.os.Build;
import android.widget.ListView;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ListViewCompat {
    public static boolean canScrollList(@NonNull ListView listView, int direction) {
        int i = Build.VERSION.SDK_INT;
        return listView.canScrollList(direction);
    }

    public static void scrollListBy(@NonNull ListView listView, int y) {
        int i = Build.VERSION.SDK_INT;
        listView.scrollListBy(y);
    }
}
