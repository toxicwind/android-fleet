package com.google.android.gms.internal.measurement;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzc {
    public final zzf zza;
    public zzg zzb;
    public final zzab zzc;
    public final zzz zzd;

    public zzc() {
        zzf zzfVar = new zzf();
        this.zza = zzfVar;
        this.zzb = zzfVar.zzb.zza();
        this.zzc = new zzab();
        this.zzd = new zzz();
        zzf zzfVar2 = this.zza;
        zzfVar2.zzd.zza("internal.registerCallback", new Callable() { // from class: com.google.android.gms.internal.measurement.zza
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zzb();
            }
        });
        zzf zzfVar3 = this.zza;
        zzfVar3.zzd.zza("internal.eventLogger", new Callable() { // from class: com.google.android.gms.internal.measurement.zzb
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzk(this.zza.zzc);
            }
        });
    }

    public final zzab zza() {
        return this.zzc;
    }

    public final /* synthetic */ zzai zzb() {
        return new zzv(this.zzd);
    }

    public final void zzc(zzgo zzgoVar) throws zzd {
        zzai zzaiVar;
        try {
            this.zzb = this.zza.zzb.zza();
            if (this.zza.zza(this.zzb, (zzgt[]) zzgoVar.zzc().toArray(new zzgt[0])) instanceof zzag) {
                throw new IllegalStateException("Program loading failed");
            }
            for (zzgm zzgmVar : zzgoVar.zza().zzd()) {
                List<zzgt> listZzc = zzgmVar.zzc();
                String strZzb = zzgmVar.zzb();
                Iterator<zzgt> it = listZzc.iterator();
                while (it.hasNext()) {
                    zzap zzapVarZza = this.zza.zza(this.zzb, it.next());
                    if (!(zzapVarZza instanceof zzam)) {
                        throw new IllegalArgumentException("Invalid rule definition");
                    }
                    zzg zzgVar = this.zzb;
                    if (zzgVar.zzh(strZzb)) {
                        zzap zzapVarZzd = zzgVar.zzd(strZzb);
                        if (!(zzapVarZzd instanceof zzai)) {
                            String strValueOf = String.valueOf(strZzb);
                            throw new IllegalStateException(strValueOf.length() != 0 ? "Invalid function name: ".concat(strValueOf) : new String("Invalid function name: "));
                        }
                        zzaiVar = (zzai) zzapVarZzd;
                    } else {
                        zzaiVar = null;
                    }
                    if (zzaiVar == null) {
                        String strValueOf2 = String.valueOf(strZzb);
                        throw new IllegalStateException(strValueOf2.length() != 0 ? "Rule function is undefined: ".concat(strValueOf2) : new String("Rule function is undefined: "));
                    }
                    zzaiVar.zza(this.zzb, Collections.singletonList(zzapVarZza));
                }
            }
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final void zzd(String str, Callable<? extends zzai> callable) {
        this.zza.zzd.zza(str, callable);
    }

    public final boolean zze(zzaa zzaaVar) throws zzd {
        try {
            this.zzc.zzd(zzaaVar);
            this.zza.zzc.zzg("runtime.counter", new zzah(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)));
            this.zzd.zzb(this.zzb.zza(), this.zzc);
            return zzg() || zzf();
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final boolean zzf() {
        return !this.zzc.zzc().isEmpty();
    }

    public final boolean zzg() {
        return !this.zzc.zzb().equals(this.zzc.zza());
    }
}
