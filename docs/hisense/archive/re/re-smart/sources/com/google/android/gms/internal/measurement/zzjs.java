package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzjs implements zzla {
    public static final zzjs zza = new zzjs();

    public static zzjs zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzla
    public final zzkz zzb(Class<?> cls) {
        if (!zzjx.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzkz) zzjx.zzbw(cls.asSubclass(zzjx.class)).zzl(3, null, null);
        } catch (Exception e2) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzla
    public final boolean zzc(Class<?> cls) {
        return zzjx.class.isAssignableFrom(cls);
    }
}
