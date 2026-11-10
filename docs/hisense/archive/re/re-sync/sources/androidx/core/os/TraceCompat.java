package androidx.core.os;

import android.os.Trace;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class TraceCompat {
    public static void beginSection(@NonNull String str) {
        Trace.beginSection(str);
    }

    public static void endSection() {
        Trace.endSection();
    }
}
