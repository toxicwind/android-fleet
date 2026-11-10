package com.google.android.gms.common.internal;

import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public final class zzw extends com.google.android.gms.internal.common.zza implements ICancelToken {
    public zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICancelToken");
    }

    @Override // com.google.android.gms.common.internal.ICancelToken
    public final void cancel() {
        zzD(2, zza());
    }
}
