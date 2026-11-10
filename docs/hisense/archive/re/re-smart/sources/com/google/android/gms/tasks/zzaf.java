package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class zzaf implements zzae {
    public final Object zza = new Object();
    public final int zzb;
    public final zzw<Void> zzc;
    public int zzd;
    public int zze;
    public int zzf;
    public Exception zzg;
    public boolean zzh;

    public zzaf(int i, zzw<Void> zzwVar) {
        this.zzb = i;
        this.zzc = zzwVar;
    }

    private final void zza() {
        int i = this.zzd;
        int i2 = this.zze;
        int i3 = i + i2 + this.zzf;
        int i4 = this.zzb;
        if (i3 == i4) {
            if (this.zzg == null) {
                if (this.zzh) {
                    this.zzc.zzc();
                    return;
                } else {
                    this.zzc.zzb(null);
                    return;
                }
            }
            zzw<Void> zzwVar = this.zzc;
            StringBuilder sb = new StringBuilder(54);
            sb.append(i2);
            sb.append(" out of ");
            sb.append(i4);
            sb.append(" underlying tasks failed");
            zzwVar.zza(new ExecutionException(sb.toString(), this.zzg));
        }
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        synchronized (this.zza) {
            this.zzf++;
            this.zzh = true;
            zza();
        }
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exc) {
        synchronized (this.zza) {
            this.zze++;
            this.zzg = exc;
            zza();
        }
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        synchronized (this.zza) {
            this.zzd++;
            zza();
        }
    }
}
