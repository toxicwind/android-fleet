package coil3.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import coil3.network.internal.Utils_androidKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ConnectivityChecker.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¨\u0006\u0005"}, d2 = {"ConnectivityChecker", "Lcoil3/network/ConnectivityChecker;", "context", "Landroid/content/Context;", "Lcoil3/PlatformContext;", "coil-network-core_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ConnectivityCheckerKt {
    public static final ConnectivityChecker ConnectivityChecker(Context context) {
        Context applicationContext = context.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.getSystemService(applicationContext, ConnectivityManager.class);
        if (connectivityManager == null || !Utils_androidKt.isPermissionGranted(applicationContext, "android.permission.ACCESS_NETWORK_STATE")) {
            return ConnectivityChecker.ONLINE;
        }
        try {
            if (Build.VERSION.SDK_INT > 23) {
                return new ConnectivityCheckerApi23(connectivityManager);
            }
            return new ConnectivityCheckerApi21(connectivityManager);
        } catch (Exception unused) {
            return ConnectivityChecker.ONLINE;
        }
    }
}
