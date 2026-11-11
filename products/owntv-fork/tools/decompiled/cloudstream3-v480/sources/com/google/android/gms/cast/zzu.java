package com.google.android.gms.cast;

import com.google.android.gms.cast.internal.CastUtils;
import java.util.Collection;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzu {
    private final String zza;
    private final String zzb;
    private final Collection zzc;

    /* synthetic */ zzu(String str, String str2, Collection collection, boolean z, boolean z2, zzv zzvVar) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = collection;
    }

    static /* bridge */ /* synthetic */ String zza(zzu zzuVar) {
        StringBuilder sb = new StringBuilder(zzuVar.zza);
        String str = zzuVar.zzb;
        if (str != null) {
            String upperCase = str.toUpperCase(Locale.ROOT);
            if (!upperCase.matches("[A-F0-9]+")) {
                throw new IllegalArgumentException("Invalid application ID: ".concat(str));
            }
            sb.append("/");
            sb.append(upperCase);
        }
        Collection<String> collection = zzuVar.zzc;
        boolean z = false;
        if (collection != null) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
            boolean z2 = str != null;
            if (str == null) {
                sb.append("/");
            }
            sb.append("/");
            boolean z3 = true;
            for (String str2 : collection) {
                CastUtils.throwIfInvalidNamespace(str2);
                if (!z3) {
                    sb.append(",");
                }
                sb.append(CastUtils.zzc(str2));
                z3 = false;
            }
            z = z2;
        } else if (str != null) {
            z = true;
        }
        if (true != z && collection == null) {
            sb.append("/");
        }
        if (collection == null) {
            sb.append("/");
        }
        sb.append("//ALLOW_IPV6");
        return sb.toString();
    }
}
