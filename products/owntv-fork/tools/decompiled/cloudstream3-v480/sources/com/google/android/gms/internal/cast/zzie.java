package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class zzie {
    private final String zza;
    private final Class zzb;
    private final boolean zzc;

    protected zzie(String str, Class cls, boolean z) {
        this(str, cls, z, true);
    }

    public static zzie zza(String str, Class cls) {
        return new zzie(str, cls, false, false);
    }

    public final String toString() {
        Class cls = this.zzb;
        return getClass().getName() + "/" + this.zza + "[" + cls.getName() + "]";
    }

    public final boolean zzb() {
        return this.zzc;
    }

    private zzie(String str, Class cls, boolean z, boolean z2) {
        zzka.zzb(str);
        this.zza = str;
        this.zzb = cls;
        this.zzc = z;
        System.identityHashCode(this);
        for (int i = 0; i < 5; i++) {
        }
    }
}
