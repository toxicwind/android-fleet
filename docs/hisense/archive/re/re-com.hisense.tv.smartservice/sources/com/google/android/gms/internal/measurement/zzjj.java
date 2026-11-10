package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzjj {
    public static final zzjj zza = new zzjj(true);
    public static volatile boolean zzb = false;
    public static volatile zzjj zzc;
    public static volatile zzjj zzd;
    public final Map<zzji, zzjv<?, ?>> zze;

    public zzjj() {
        this.zze = new HashMap();
    }

    public static zzjj zza() {
        zzjj zzjjVar = zzc;
        if (zzjjVar == null) {
            synchronized (zzjj.class) {
                zzjjVar = zzc;
                if (zzjjVar == null) {
                    zzjjVar = zza;
                    zzc = zzjjVar;
                }
            }
        }
        return zzjjVar;
    }

    public static zzjj zzb() {
        zzjj zzjjVar = zzd;
        if (zzjjVar != null) {
            return zzjjVar;
        }
        synchronized (zzjj.class) {
            zzjj zzjjVar2 = zzd;
            if (zzjjVar2 != null) {
                return zzjjVar2;
            }
            zzjj zzjjVarZzb = zzjr.zzb(zzjj.class);
            zzd = zzjjVarZzb;
            return zzjjVarZzb;
        }
    }

    public final <ContainingType extends zzlc> zzjv<ContainingType, ?> zzc(ContainingType containingtype, int i) {
        return (zzjv) this.zze.get(new zzji(containingtype, i));
    }

    public zzjj(boolean z) {
        this.zze = Collections.emptyMap();
    }
}
