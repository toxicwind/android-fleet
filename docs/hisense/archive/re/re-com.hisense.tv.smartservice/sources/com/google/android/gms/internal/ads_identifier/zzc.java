package com.google.android.gms.internal.ads_identifier;

import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class zzc {
    public static final ClassLoader zza = zzc.class.getClassLoader();

    public static void zza(Parcel parcel, boolean z) {
        parcel.writeInt(1);
    }

    public static boolean zzb(Parcel parcel) {
        return parcel.readInt() != 0;
    }
}
