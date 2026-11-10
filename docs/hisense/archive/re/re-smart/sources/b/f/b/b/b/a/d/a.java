package b.f.b.b.b.a.d;

import android.content.Context;
import android.os.storage.StorageManager;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f693a = "a";

    public static long a(Context context) {
        long primaryStorageSize = 0;
        try {
            primaryStorageSize = ((StorageManager) context.getSystemService(StorageManager.class)).getPrimaryStorageSize() / 1024;
            Log.d(f693a, "totalSize=" + primaryStorageSize);
            return primaryStorageSize;
        } catch (Exception e2) {
            Log.e(f693a, e2.toString());
            return primaryStorageSize;
        }
    }
}
