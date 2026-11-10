package androidx.core.app;

import android.app.Dialog;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class DialogCompat {
    @NonNull
    public static View requireViewById(@NonNull Dialog dialog, int id) {
        if (Build.VERSION.SDK_INT >= 28) {
            return dialog.requireViewById(id);
        }
        View viewFindViewById = dialog.findViewById(id);
        if (viewFindViewById != null) {
            return viewFindViewById;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Dialog");
    }
}
