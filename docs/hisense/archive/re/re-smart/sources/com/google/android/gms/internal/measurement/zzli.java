package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzli {
    public static final zzlh zza;
    public static final zzlh zzb;

    static {
        zzlh zzlhVar;
        try {
            zzlhVar = (zzlh) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzlhVar = null;
        }
        zza = zzlhVar;
        zzb = new zzlh();
    }

    public static zzlh zza() {
        return zza;
    }

    public static zzlh zzb() {
        return zzb;
    }
}
