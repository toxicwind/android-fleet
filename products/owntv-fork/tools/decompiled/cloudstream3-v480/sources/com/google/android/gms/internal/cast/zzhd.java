package com.google.android.gms.internal.cast;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzhd extends zzha {
    public zzhd() {
        super(4);
    }

    public final zzhg zzc() {
        this.zzc = true;
        return zzhg.zzi(this.zza, this.zzb);
    }

    public final zzhd zzb(Object obj) {
        obj.getClass();
        int length = this.zza.length;
        int iZza = zzha.zza(length, this.zzb + 1);
        if (iZza > length || this.zzc) {
            this.zza = Arrays.copyOf(this.zza, iZza);
            this.zzc = false;
        }
        Object[] objArr = this.zza;
        int i = this.zzb;
        this.zzb = i + 1;
        objArr[i] = obj;
        return this;
    }
}
