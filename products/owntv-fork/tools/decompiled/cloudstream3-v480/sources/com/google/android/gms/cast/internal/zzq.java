package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class zzq {
    protected final Logger zza;
    private final String zzb;
    private zzas zzc;

    protected zzq(String str, String str2, String str3) {
        CastUtils.throwIfInvalidNamespace(str);
        this.zzb = str;
        this.zza = new Logger("MediaControlChannel", null);
    }

    protected final long zzd() {
        zzas zzasVar = this.zzc;
        if (zzasVar != null) {
            return zzasVar.zza();
        }
        this.zza.e("Attempt to generate requestId without a sink", new Object[0]);
        return 0L;
    }

    public final String zze() {
        return this.zzb;
    }

    public void zzf() {
        throw null;
    }

    protected final void zzg(String str, long j, String str2) throws IllegalStateException {
        Logger logger = this.zza;
        logger.v("Sending text message: %s to: %s", str, null);
        zzas zzasVar = this.zzc;
        if (zzasVar == null) {
            logger.e("Attempt to send text message without a sink", new Object[0]);
        } else {
            zzasVar.zzb(this.zzb, str, j, null);
        }
    }

    public final void zzh(zzas zzasVar) {
        this.zzc = zzasVar;
        if (zzasVar == null) {
            zzf();
        }
    }
}
