package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzet extends Handler {
    public zzet() {
        Looper.getMainLooper();
    }

    public zzet(Looper looper) {
        super(looper);
        Looper.getMainLooper();
    }
}
