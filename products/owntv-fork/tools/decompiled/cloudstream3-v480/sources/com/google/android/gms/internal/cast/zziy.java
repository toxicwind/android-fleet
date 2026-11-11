package com.google.android.gms.internal.cast;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zziy {
    private static String zza = "com.google.android.gms.internal.cast.zzjc";
    private static String zzb = "com.google.common.flogger.backend.google.GooglePlatform";
    private static String zzc = "com.google.common.flogger.backend.system.DefaultPlatform";
    private static final String[] zzd = {"com.google.android.gms.internal.cast.zzjc", "com.google.common.flogger.backend.google.GooglePlatform", "com.google.common.flogger.backend.system.DefaultPlatform"};

    public static int zza() {
        return zzkc.zza();
    }

    public static long zzb() {
        return zziw.zza.zzc();
    }

    public static zzii zzd(String str) {
        return zziw.zza.zze(str);
    }

    public static zzik zzf() {
        return zzi().zza();
    }

    public static zzix zzg() {
        return zziw.zza.zzh();
    }

    public static zzjm zzi() {
        return zziw.zza.zzj();
    }

    public static zzjy zzk() {
        return zzi().zzc();
    }

    public static String zzl() {
        return zziw.zza.zzm();
    }

    public static boolean zzn(String str, Level level, boolean z) {
        zzi().zzd(str, level, z);
        return false;
    }

    protected long zzc() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }

    protected abstract zzii zze(String str);

    protected abstract zzix zzh();

    protected zzjm zzj() {
        return zzjm.zze();
    }

    protected abstract String zzm();
}
