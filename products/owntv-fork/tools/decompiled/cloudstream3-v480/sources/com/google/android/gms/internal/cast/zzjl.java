package com.google.android.gms.internal.cast;

import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzjl extends zziz {
    public static final /* synthetic */ int zza = 0;
    private static final Set zzb;
    private static final zzis zzc;
    private static final zzji zzd;

    static {
        Set setUnmodifiableSet = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(zzib.zza, zzig.zza, zzih.zza)));
        zzb = setUnmodifiableSet;
        zzc = zziv.zza(setUnmodifiableSet).zza();
        zzd = new zzji();
    }

    /* synthetic */ zzjl(String str, String str2, boolean z, int i, Level level, Set set, zzis zzisVar, zzjk zzjkVar) {
        super(str2);
        if (str2.length() > 23) {
            int i2 = -1;
            for (int length = str2.length() - 1; length >= 0; length--) {
                char cCharAt = str2.charAt(length);
                if (cCharAt == '.' || cCharAt == '$') {
                    i2 = length;
                    break;
                }
            }
            str2 = str2.substring(i2 + 1);
        }
        String strConcat = "".concat(String.valueOf(str2));
        strConcat.substring(0, Math.min(strConcat.length(), 23));
    }

    public static zzji zzc() {
        return zzd;
    }
}
