package com.google.android.gms.cast.internal;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzet;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzw extends zzai {
    private final AtomicReference zza;
    private final Handler zzb;

    public zzw(zzx zzxVar) {
        this.zza = new AtomicReference(zzxVar);
        this.zzb = new zzet(zzxVar.getLooper());
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzb(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzh = applicationMetadata;
        zzxVar.zzy = applicationMetadata.getApplicationId();
        zzxVar.zzz = str2;
        zzxVar.zzo = str;
        synchronized (zzx.zzf) {
            if (zzxVar.zzC != null) {
                zzxVar.zzC.setResult(new zzr(new Status(0), applicationMetadata, str, str2, z));
                zzxVar.zzC = null;
            }
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzc(int i) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzR(i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzd(int i) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzy = null;
        zzxVar.zzz = null;
        zzxVar.zzab(i);
        if (zzxVar.zzj != null) {
            this.zzb.post(new zzs(this, zzxVar, i));
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zze(int i) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzab(i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzf(zza zzaVar) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzx.zze.d("onApplicationStatusChanged", new Object[0]);
        this.zzb.post(new zzu(this, zzxVar, zzaVar));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzg(int i) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzab(i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzh(String str, byte[] bArr) {
        if (((zzx) this.zza.get()) == null) {
            return;
        }
        zzx.zze.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzi(int i) {
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzj(zzac zzacVar) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzx.zze.d("onDeviceStatusChanged", new Object[0]);
        this.zzb.post(new zzt(this, zzxVar, zzacVar));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzk(int i) {
        zzx zzxVarZzq = zzq();
        if (zzxVarZzq == null) {
            return;
        }
        zzx.zze.d("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
        if (i != 0) {
            zzxVarZzq.triggerConnectionSuspended(2);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzl(String str, long j) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzaa(j, 0);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzm(String str, long j, int i) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzxVar.zzaa(j, i);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzn(String str, double d, boolean z) {
        zzx.zze.d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzo(int i) {
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzp(String str, String str2) {
        zzx zzxVar = (zzx) this.zza.get();
        if (zzxVar == null) {
            return;
        }
        zzx.zze.d("Receive (type=text, ns=%s) %s", str, str2);
        this.zzb.post(new zzv(this, zzxVar, str, str2));
    }

    public final zzx zzq() {
        zzx zzxVar = (zzx) this.zza.getAndSet(null);
        if (zzxVar == null) {
            return null;
        }
        zzxVar.zzY();
        return zzxVar;
    }

    public final boolean zzr() {
        return this.zza.get() == null;
    }
}
