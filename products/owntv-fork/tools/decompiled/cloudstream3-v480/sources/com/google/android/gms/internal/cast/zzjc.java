package com.google.android.gms.internal.cast;

import android.os.Build;
import dalvik.system.VMStack;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzjc extends zziy {
    private static final boolean zza = zza.zza();
    private static final boolean zzb;
    private static final zzix zzc;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
    final class zza {
        zza() {
        }

        static boolean zza() {
            return zzjc.zzt();
        }
    }

    static {
        boolean z = true;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        }
        zzb = z;
        zzc = new zzix() { // from class: com.google.android.gms.internal.cast.zzjc.1
            @Override // com.google.android.gms.internal.cast.zzix
            public zzid zza(Class<?> cls, int i) {
                return zzid.zza;
            }

            @Override // com.google.android.gms.internal.cast.zzix
            public String zzb(Class cls) {
                StackTraceElement stackTraceElementZza;
                if (zzjc.zza) {
                    try {
                        if (cls.equals(zzjc.zzp())) {
                            return VMStack.getStackClass2().getName();
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (!zzjc.zzb || (stackTraceElementZza = zzjz.zza(cls, 1)) == null) {
                    return null;
                }
                return stackTraceElementZza.getClassName();
            }
        };
    }

    static Class<?> zzp() {
        return VMStack.getStackClass2();
    }

    static String zzq() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzt() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", null);
            return zza.class.getName().equals(zzq());
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.google.android.gms.internal.cast.zziy
    protected zzii zze(String str) {
        return zzjg.zzb(str);
    }

    @Override // com.google.android.gms.internal.cast.zziy
    protected zzix zzh() {
        return zzc;
    }

    @Override // com.google.android.gms.internal.cast.zziy
    protected zzjm zzj() {
        return zzjh.zzb();
    }

    @Override // com.google.android.gms.internal.cast.zziy
    protected String zzm() {
        return "platform: Android";
    }
}
