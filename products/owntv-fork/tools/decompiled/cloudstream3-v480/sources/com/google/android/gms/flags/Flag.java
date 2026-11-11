package com.google.android.gms.flags;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public abstract class Flag<T> {
    private final int zza;
    private final String zzb;
    private final T zzc;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
    @Deprecated
    public static class BooleanFlag extends Flag<Boolean> {
        public BooleanFlag(int i, String str, Boolean bool) {
            super(i, str, bool, null);
        }

        @Override // com.google.android.gms.flags.Flag
        public final /* bridge */ /* synthetic */ Boolean zza(zze zzeVar) {
            try {
                return Boolean.valueOf(zzeVar.getBooleanFlagValue(zzd(), zzc().booleanValue(), zzb()));
            } catch (RemoteException unused) {
                return zzc();
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
    @Deprecated
    public static class IntegerFlag extends Flag<Integer> {
        public IntegerFlag(int i, String str, Integer num) {
            super(i, str, num, null);
        }

        @Override // com.google.android.gms.flags.Flag
        public final /* bridge */ /* synthetic */ Integer zza(zze zzeVar) {
            try {
                return Integer.valueOf(zzeVar.getIntFlagValue(zzd(), zzc().intValue(), zzb()));
            } catch (RemoteException unused) {
                return zzc();
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
    @Deprecated
    public static class LongFlag extends Flag<Long> {
        public LongFlag(int i, String str, Long l) {
            super(i, str, l, null);
        }

        @Override // com.google.android.gms.flags.Flag
        public final /* bridge */ /* synthetic */ Long zza(zze zzeVar) {
            try {
                return Long.valueOf(zzeVar.getLongFlagValue(zzd(), zzc().longValue(), zzb()));
            } catch (RemoteException unused) {
                return zzc();
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-flags@@17.0.1 */
    @Deprecated
    public static class StringFlag extends Flag<String> {
        public StringFlag(int i, String str, String str2) {
            super(i, str, str2, null);
        }

        @Override // com.google.android.gms.flags.Flag
        public final /* bridge */ /* synthetic */ String zza(zze zzeVar) {
            try {
                return zzeVar.getStringFlagValue(zzd(), zzc(), zzb());
            } catch (RemoteException unused) {
                return zzc();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* synthetic */ Flag(int i, String str, Object obj, zza zzaVar) {
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        Singletons.flagRegistry().zza(this);
    }

    @Deprecated
    public static IntegerFlag define(int i, String str, int i2) {
        return new IntegerFlag(i, str, Integer.valueOf(i2));
    }

    public T get() {
        return (T) Singletons.zza().zza(this);
    }

    protected abstract T zza(zze zzeVar);

    @Deprecated
    public final int zzb() {
        return this.zza;
    }

    public final T zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzb;
    }

    @Deprecated
    public static LongFlag define(int i, String str, long j) {
        return new LongFlag(i, str, Long.valueOf(j));
    }

    @Deprecated
    public static BooleanFlag define(int i, String str, Boolean bool) {
        return new BooleanFlag(i, str, bool);
    }

    @Deprecated
    public static StringFlag define(int i, String str, String str2) {
        return new StringFlag(i, str, str2);
    }
}
