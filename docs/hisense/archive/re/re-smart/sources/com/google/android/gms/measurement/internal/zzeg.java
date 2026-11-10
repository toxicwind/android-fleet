package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public final class zzeg {
    public static final AtomicReference<String[]> zza = new AtomicReference<>();
    public static final AtomicReference<String[]> zzb = new AtomicReference<>();
    public static final AtomicReference<String[]> zzc = new AtomicReference<>();
    public final zzef zzd;

    public zzeg(zzef zzefVar) {
        this.zzd = zzefVar;
    }

    public static final String zzg(String str, String[] strArr, String[] strArr2, AtomicReference<String[]> atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i = 0; i < strArr.length; i++) {
            if (zzkz.zzak(str, strArr[i])) {
                synchronized (atomicReference) {
                    String[] strArr3 = atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    str2 = strArr3[i];
                    if (str2 == null) {
                        str2 = strArr2[i] + "(" + strArr[i] + ")";
                        strArr3[i] = str2;
                    }
                }
                return str2;
            }
        }
        return str;
    }

    public final String zza(Object[] objArr) {
        if (objArr == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sbA = a.a("[");
        for (Object obj : objArr) {
            String strZzb = obj instanceof Bundle ? zzb((Bundle) obj) : String.valueOf(obj);
            if (strZzb != null) {
                if (sbA.length() != 1) {
                    sbA.append(", ");
                }
                sbA.append(strZzb);
            }
        }
        sbA.append("]");
        return sbA.toString();
    }

    public final String zzb(Bundle bundle) {
        String strZza;
        if (bundle == null) {
            return null;
        }
        if (!this.zzd.zza()) {
            return bundle.toString();
        }
        StringBuilder sbA = a.a("Bundle[{");
        for (String str : bundle.keySet()) {
            if (sbA.length() != 8) {
                sbA.append(", ");
            }
            sbA.append(zze(str));
            sbA.append("=");
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                strZza = zza(new Object[]{obj});
            } else if (obj instanceof Object[]) {
                strZza = zza((Object[]) obj);
            } else {
                strZza = obj instanceof ArrayList ? zza(((ArrayList) obj).toArray()) : String.valueOf(obj);
            }
            sbA.append(strZza);
        }
        sbA.append("}]");
        return sbA.toString();
    }

    public final String zzc(zzat zzatVar) {
        String string;
        if (!this.zzd.zza()) {
            return zzatVar.toString();
        }
        StringBuilder sbA = a.a("origin=");
        sbA.append(zzatVar.zzc);
        sbA.append(",name=");
        sbA.append(zzd(zzatVar.zza));
        sbA.append(",params=");
        zzar zzarVar = zzatVar.zzb;
        if (zzarVar == null) {
            string = null;
        } else {
            string = !this.zzd.zza() ? zzarVar.toString() : zzb(zzarVar.zzc());
        }
        sbA.append(string);
        return sbA.toString();
    }

    public final String zzd(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zzg(str, zzgs.zzc, zzgs.zza, zza);
    }

    public final String zze(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zzg(str, zzgt.zzb, zzgt.zza, zzb);
    }

    public final String zzf(String str) {
        if (str == null) {
            return null;
        }
        if (this.zzd.zza()) {
            return str.startsWith("_exp_") ? a.a("experiment_id(", str, ")") : zzg(str, zzgu.zzb, zzgu.zza, zzc);
        }
        return str;
    }
}
