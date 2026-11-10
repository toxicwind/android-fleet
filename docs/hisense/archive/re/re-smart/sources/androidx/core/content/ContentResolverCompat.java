package androidx.core.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.OperationCanceledException;
import androidx.core.os.CancellationSignal;

/* JADX INFO: loaded from: classes.dex */
public final class ContentResolverCompat {
    public static Cursor query(ContentResolver resolver, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) throws Exception {
        Object cancellationSignalObject;
        int i = Build.VERSION.SDK_INT;
        if (cancellationSignal != null) {
            try {
                cancellationSignalObject = cancellationSignal.getCancellationSignalObject();
            } catch (Exception e2) {
                if (e2 instanceof OperationCanceledException) {
                    throw new androidx.core.os.OperationCanceledException(null);
                }
                throw e2;
            }
        } else {
            cancellationSignalObject = null;
        }
        return resolver.query(uri, projection, selection, selectionArgs, sortOrder, (android.os.CancellationSignal) cancellationSignalObject);
    }
}
