package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzvs extends zzux.zzf implements Runnable {
    private final Runnable zzd;

    @Override // com.google.android.gms.internal.cast.zzux
    protected final String zzb() {
        return "task=[" + this.zzd.toString() + "]";
    }

    public zzvs(Runnable runnable) {
        runnable.getClass();
        this.zzd = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzd.run();
        } catch (Throwable th) {
            zzg(th);
            throw th;
        }
    }
}
