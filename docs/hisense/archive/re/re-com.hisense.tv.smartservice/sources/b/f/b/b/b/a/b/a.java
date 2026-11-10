package b.f.b.b.b.a.b;

import android.os.RemoteException;
import android.util.Log;
import com.android.internal.content.PackageHelper;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f690a = "a";

    public static void a() {
        try {
            PackageHelper.getStorageManager().runMaintenance();
            Log.d(f690a, "[Emmc fstrim] fstrim finish!!");
        } catch (RemoteException e2) {
            Log.e(f690a, e2.toString());
        } catch (Exception e3) {
            Log.e(f690a, e3.toString());
        }
    }
}
