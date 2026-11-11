package com.lagradost.safefile;

import android.util.Log;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SafeFile.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0003*\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"TAG", "", "logError", "", "throwable", "", "closeQuietly", "Ljava/io/Closeable;", "library_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SafeFileKt {
    public static final String TAG = "SafeFile";

    public static final void closeQuietly(Closeable closeable) {
        Intrinsics.checkNotNullParameter(closeable, "<this>");
        try {
            closeable.close();
        } catch (Throwable unused) {
        }
    }

    public static final void logError(Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Log.d(TAG, "-------------------------------------------------------------------");
        Log.d(TAG, "safeApiCall: " + throwable.getLocalizedMessage());
        Log.d(TAG, "safeApiCall: " + throwable.getMessage());
        throwable.printStackTrace();
        Log.d(TAG, "-------------------------------------------------------------------");
    }
}
