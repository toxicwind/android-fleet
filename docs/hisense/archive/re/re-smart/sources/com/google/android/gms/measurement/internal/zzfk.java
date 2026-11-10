package com.google.android.gms.measurement.internal;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzfk implements com.google.android.gms.internal.measurement.zzr {
    public final /* synthetic */ zzfm zza;

    public zzfk(zzfm zzfmVar) {
        this.zza = zzfmVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int i, String str, List<String> list, boolean z, boolean z2) {
        zzej zzejVarZzc;
        int i2 = i - 1;
        if (i2 == 0) {
            zzejVarZzc = this.zza.zzs.zzay().zzc();
        } else if (i2 != 1) {
            if (i2 == 3) {
                zzejVarZzc = this.zza.zzs.zzay().zzj();
            } else if (i2 != 4) {
                zzejVarZzc = this.zza.zzs.zzay().zzi();
            } else if (z) {
                zzejVarZzc = this.zza.zzs.zzay().zzm();
            } else {
                zzejVarZzc = !z2 ? this.zza.zzs.zzay().zzl() : this.zza.zzs.zzay().zzk();
            }
        } else if (z) {
            zzejVarZzc = this.zza.zzs.zzay().zzh();
        } else {
            zzejVarZzc = !z2 ? this.zza.zzs.zzay().zze() : this.zza.zzs.zzay().zzd();
        }
        int size = list.size();
        if (size == 1) {
            zzejVarZzc.zzb(str, list.get(0));
            return;
        }
        if (size == 2) {
            zzejVarZzc.zzc(str, list.get(0), list.get(1));
        } else if (size != 3) {
            zzejVarZzc.zza(str);
        } else {
            zzejVarZzc.zzd(str, list.get(0), list.get(1), list.get(2));
        }
    }
}
