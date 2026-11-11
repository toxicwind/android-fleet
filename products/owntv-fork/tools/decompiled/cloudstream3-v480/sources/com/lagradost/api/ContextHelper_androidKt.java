package com.lagradost.api;

import android.content.Context;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContextHelper.android.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0001\"\"\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"ctx", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "getCtx", "()Ljava/lang/ref/WeakReference;", "setCtx", "(Ljava/lang/ref/WeakReference;)V", "getContext", "", "setContext", "", "context", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ContextHelper_androidKt {
    private static WeakReference<Context> ctx;

    public static final WeakReference<Context> getCtx() {
        return ctx;
    }

    public static final void setCtx(WeakReference<Context> weakReference) {
        ctx = weakReference;
    }

    public static final Object getContext() {
        WeakReference<Context> weakReference = ctx;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public static final void setContext(WeakReference<Object> context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object obj = context.get();
        Context context2 = obj instanceof Context ? (Context) obj : null;
        if (context2 != null) {
            ctx = new WeakReference<>(context2);
        }
    }
}
