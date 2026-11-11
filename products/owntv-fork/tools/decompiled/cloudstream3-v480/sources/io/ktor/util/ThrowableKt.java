package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Throwable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0010\u0003\n\u0002\b\u0006\" \u0010\u0005\u001a\u0004\u0018\u00010\u0000*\u00020\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0001\u0010\u0002¨\u0006\u0006"}, d2 = {"", "getRootCause", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getRootCause$annotations", "(Ljava/lang/Throwable;)V", "rootCause", "ktor-utils"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ThrowableKt {
    public static /* synthetic */ void getRootCause$annotations(Throwable th) {
    }

    public static final Throwable getRootCause(Throwable th) {
        Intrinsics.checkNotNullParameter(th, "<this>");
        while (true) {
            if ((th != null ? th.getCause() : null) == null) {
                return th;
            }
            th = th.getCause();
        }
    }
}
