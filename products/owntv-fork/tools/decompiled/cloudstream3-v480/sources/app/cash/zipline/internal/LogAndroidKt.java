package app.cash.zipline.internal;

import android.util.Log;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import io.ktor.http.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LogAndroid.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¨\u0006\u0007"}, d2 = {"log", "", "level", "", ContentType.Message.TYPE, "throwable", "", "zipline_release"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LogAndroidKt {
    public static final void log(String level, String message, Throwable th) {
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(message, "message");
        if (Intrinsics.areEqual(level, "warn")) {
            Log.w("Zipline", message, th);
        } else if (Intrinsics.areEqual(level, MediaRouteProviderProtocol.SERVICE_DATA_ERROR)) {
            Log.e("Zipline", message, th);
        } else {
            Log.i("Zipline", message, th);
        }
    }
}
