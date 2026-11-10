package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zzao {
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final long zzd;
    public final long zze;
    public final zzar zzf;

    public zzao(zzfv zzfvVar, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzar zzarVar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = true == TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzfvVar.zzay().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzel.zzn(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzarVar = new zzar(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzfvVar.zzay().zzd().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object objZzA = zzfvVar.zzv().zzA(next, bundle2.get(next));
                    if (objZzA == null) {
                        zzfvVar.zzay().zzk().zzb("Param value can't be null", zzfvVar.zzj().zze(next));
                        it.remove();
                    } else {
                        zzfvVar.zzv().zzN(bundle2, next, objZzA);
                    }
                }
            }
            zzarVar = new zzar(bundle2);
        }
        this.zzf = zzarVar;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String string = this.zzf.toString();
        StringBuilder sb = new StringBuilder(string.length() + String.valueOf(str).length() + 33 + String.valueOf(str2).length());
        a.a(sb, "Event{appId='", str, "', name='", str2);
        sb.append("', params=");
        sb.append(string);
        sb.append('}');
        return sb.toString();
    }

    public final zzao zza(zzfv zzfvVar, long j) {
        return new zzao(zzfvVar, this.zzc, this.zza, this.zzb, this.zzd, j, this.zzf);
    }

    public zzao(zzfv zzfvVar, String str, String str2, String str3, long j, long j2, zzar zzarVar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzarVar);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = true == TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzfvVar.zzay().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzel.zzn(str2), zzel.zzn(str3));
        }
        this.zzf = zzarVar;
    }
}
