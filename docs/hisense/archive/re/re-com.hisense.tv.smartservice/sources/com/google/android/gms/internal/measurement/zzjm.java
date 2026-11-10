package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzjm {
    public static final zzjk<?> zza = new zzjl();
    public static final zzjk<?> zzb;

    static {
        zzjk<?> zzjkVar;
        try {
            zzjkVar = (zzjk) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzjkVar = null;
        }
        zzb = zzjkVar;
    }

    public static zzjk<?> zza() {
        zzjk<?> zzjkVar = zzb;
        if (zzjkVar != null) {
            return zzjkVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    public static zzjk<?> zzb() {
        return zza;
    }
}
