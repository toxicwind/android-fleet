package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzxe implements zzyj {
    private static final zzxe zza = new zzxe();

    private zzxe() {
    }

    public static zzxe zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.cast.zzyj
    public final zzyi zzb(Class cls) {
        if (!zzxi.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzyi) zzxi.zzz(cls.asSubclass(zzxi.class)).zzb(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzyj
    public final boolean zzc(Class cls) {
        return zzxi.class.isAssignableFrom(cls);
    }
}
