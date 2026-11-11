package com.google.android.gms.internal.cast;

import java.util.Set;
import java.util.logging.Level;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzji implements zzjb {
    private final String zza;
    private final Level zzb;
    private final Set zzc;
    private final zzis zzd;
    private final int zze;

    private zzji() {
        this("", true, 2, Level.ALL, false, zzjl.zzb, zzjl.zzc);
    }

    private zzji(String str, boolean z, int i, Level level, boolean z2, Set set, zzis zzisVar) {
        this.zza = "";
        this.zze = 2;
        this.zzb = level;
        this.zzc = set;
        this.zzd = zzisVar;
    }

    @Override // com.google.android.gms.internal.cast.zzjb
    public final zzii zza(String str) {
        return new zzjl(this.zza, str, true, 2, this.zzb, this.zzc, this.zzd, null);
    }

    public final zzji zzb(boolean z) {
        Set set = this.zzc;
        zzis zzisVar = this.zzd;
        return new zzji(this.zza, true, 2, Level.OFF, false, set, zzisVar);
    }
}
