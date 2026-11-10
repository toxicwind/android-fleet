package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class zzgz extends ContentObserver {
    public final /* synthetic */ zzha zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgz(zzha zzhaVar, Handler handler) {
        super(null);
        this.zza = zzhaVar;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        this.zza.zzf();
    }
}
