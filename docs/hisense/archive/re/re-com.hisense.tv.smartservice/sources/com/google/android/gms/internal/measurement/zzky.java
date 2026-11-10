package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzky {
    public static final zzkx zza;
    public static final zzkx zzb;

    static {
        zzkx zzkxVar;
        try {
            zzkxVar = (zzkx) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzkxVar = null;
        }
        zza = zzkxVar;
        zzb = new zzkx();
    }

    public static zzkx zza() {
        return zza;
    }

    public static zzkx zzb() {
        return zzb;
    }
}
