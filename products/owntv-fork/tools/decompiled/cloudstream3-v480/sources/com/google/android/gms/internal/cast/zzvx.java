package com.google.android.gms.internal.cast;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzvx extends zzve implements RunnableFuture {
    private volatile zzvn zzd;

    zzvx(Callable callable) {
        this.zzd = new zzvw(this, callable);
    }

    static zzvx zzo(Runnable runnable, Object obj) {
        return new zzvx(Executors.callable(runnable, obj));
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        zzvn zzvnVar = this.zzd;
        if (zzvnVar != null) {
            zzvnVar.run();
        }
        this.zzd = null;
    }

    @Override // com.google.android.gms.internal.cast.zzux
    protected final String zzb() {
        zzvn zzvnVar = this.zzd;
        if (zzvnVar == null) {
            return super.zzb();
        }
        return "task=[" + zzvnVar.toString() + "]";
    }

    @Override // com.google.android.gms.internal.cast.zzux
    protected final void zzd() {
        zzvn zzvnVar;
        Object obj = this.valueField;
        if ((obj instanceof zzux.zza) && ((zzux.zza) obj).zzc && (zzvnVar = this.zzd) != null) {
            zzvnVar.zze();
        }
        this.zzd = null;
    }
}
