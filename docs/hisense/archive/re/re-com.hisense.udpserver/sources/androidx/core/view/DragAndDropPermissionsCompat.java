package androidx.core.view;

import android.app.Activity;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
public final class DragAndDropPermissionsCompat {
    public Object mDragAndDropPermissions;

    public DragAndDropPermissionsCompat(Object obj) {
        this.mDragAndDropPermissions = obj;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static DragAndDropPermissionsCompat request(Activity activity, DragEvent dragEvent) {
        DragAndDropPermissions dragAndDropPermissionsRequestDragAndDropPermissions = activity.requestDragAndDropPermissions(dragEvent);
        if (dragAndDropPermissionsRequestDragAndDropPermissions != null) {
            return new DragAndDropPermissionsCompat(dragAndDropPermissionsRequestDragAndDropPermissions);
        }
        return null;
    }

    public void release() {
        ((DragAndDropPermissions) this.mDragAndDropPermissions).release();
    }
}
