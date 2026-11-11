package com.google.android.gms.internal.cast;

import android.os.Build;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.text.Typography;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzjg extends zziz {
    static final boolean zza;
    static final boolean zzb;
    static final boolean zzc;
    private static final AtomicReference zzd;
    private static final AtomicLong zze;
    private static final ConcurrentLinkedQueue zzf;
    private volatile zzii zzg;

    static {
        zza = Build.FINGERPRINT == null || "robolectric".equals(Build.FINGERPRINT);
        zzb = "goldfish".equals(Build.HARDWARE) || "ranchu".equals(Build.HARDWARE);
        zzc = "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
        zzd = new AtomicReference();
        zze = new AtomicLong();
        zzf = new ConcurrentLinkedQueue();
    }

    private zzjg(String str) {
        super(str);
        if (zza || zzb) {
            this.zzg = new zzja().zza(zza());
        } else if (zzc) {
            this.zzg = zzjl.zzc().zzb(false).zza(zza());
        } else {
            this.zzg = null;
        }
    }

    public static zzii zzb(String str) {
        char cCharAt;
        AtomicReference atomicReference = zzd;
        if (atomicReference.get() != null) {
            return ((zzjb) atomicReference.get()).zza(str);
        }
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                break;
            }
            cCharAt = str.charAt(length);
            if (cCharAt == '$') {
                str = str.replace(Typography.dollar, '.');
                break;
            }
        } while (cCharAt != '.');
        zzjg zzjgVar = new zzjg(str);
        ConcurrentLinkedQueue concurrentLinkedQueue = zzje.zza;
        concurrentLinkedQueue.offer(zzjgVar);
        if (atomicReference.get() != null) {
            while (true) {
                zzjg zzjgVar2 = (zzjg) concurrentLinkedQueue.poll();
                if (zzjgVar2 == null) {
                    break;
                }
                zzjgVar2.zzg = ((zzjb) atomicReference.get()).zza(zzjgVar2.zza());
            }
            if (((zzjf) zzf.poll()) != null) {
                zze.getAndDecrement();
                throw null;
            }
        }
        return zzjgVar;
    }
}
