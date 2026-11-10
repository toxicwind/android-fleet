package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzv extends zzw {
    public final Callable<String> zzd;

    public /* synthetic */ zzv(Callable callable, zzu zzuVar) {
        super(false, null, null);
        this.zzd = callable;
    }

    @Override // com.google.android.gms.common.zzw
    public final String zza() {
        try {
            return this.zzd.call();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
}
