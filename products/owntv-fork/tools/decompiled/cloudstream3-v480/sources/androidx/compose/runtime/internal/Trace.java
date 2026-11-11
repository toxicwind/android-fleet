package androidx.compose.runtime.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: Trace.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0001¨\u0006\n"}, d2 = {"Landroidx/compose/runtime/internal/Trace;", "", "<init>", "()V", "beginSection", "name", "", "endSection", "", "token", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Trace {
    public static final int $stable = 0;
    public static final Trace INSTANCE = new Trace();

    private Trace() {
    }

    public final Object beginSection(String name) {
        android.os.Trace.beginSection(name);
        return null;
    }

    public final void endSection(Object token) {
        android.os.Trace.endSection();
    }
}
