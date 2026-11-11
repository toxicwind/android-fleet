package com.lagradost.cloudstream3.utils;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Coroutines.android.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¨\u0006\u0004"}, d2 = {"runOnMainThreadNative", "", "work", "Lkotlin/Function0;", "library"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class Coroutines_androidKt {
    public static final void runOnMainThreadNative(final Function0<Unit> work) {
        Intrinsics.checkNotNullParameter(work, "work");
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper.isCurrentThread()) {
            work.invoke();
        } else {
            new Handler(mainLooper).post(new Runnable() { // from class: com.lagradost.cloudstream3.utils.Coroutines_androidKt$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    work.invoke();
                }
            });
        }
    }
}
